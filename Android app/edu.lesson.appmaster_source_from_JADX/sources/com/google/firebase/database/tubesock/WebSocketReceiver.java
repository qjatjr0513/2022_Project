package com.google.firebase.database.tubesock;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import com.google.firebase.database.tubesock.MessageBuilderFactory;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;

class WebSocketReceiver {
    private WebSocketEventHandler eventHandler = null;
    private DataInputStream input = null;
    private byte[] inputHeader = new byte[112];
    private MessageBuilderFactory.Builder pendingBuilder;
    private volatile boolean stop = false;
    private WebSocket websocket = null;

    WebSocketReceiver(WebSocket websocket2) {
        this.websocket = websocket2;
    }

    /* access modifiers changed from: package-private */
    public void setInput(DataInputStream input2) {
        this.input = input2;
    }

    /* access modifiers changed from: package-private */
    public void run() {
        this.eventHandler = this.websocket.getEventHandler();
        while (!this.stop) {
            try {
                int offset = 0 + read(this.inputHeader, 0, 1);
                byte[] bArr = this.inputHeader;
                boolean fin = (bArr[0] & UnsignedBytes.MAX_POWER_OF_TWO) != 0;
                if (!((bArr[0] & 112) != 0)) {
                    byte opcode = (byte) (bArr[0] & Ascii.f62SI);
                    int offset2 = offset + read(bArr, offset, 1);
                    byte[] bArr2 = this.inputHeader;
                    byte length = bArr2[1];
                    long payload_length = 0;
                    if (length < 126) {
                        payload_length = (long) length;
                    } else if (length == 126) {
                        int offset3 = offset2 + read(bArr2, offset2, 2);
                        byte[] bArr3 = this.inputHeader;
                        payload_length = (((long) (bArr3[2] & 255)) << 8) | ((long) (bArr3[3] & 255));
                    } else if (length == Byte.MAX_VALUE) {
                        payload_length = parseLong(this.inputHeader, (offset2 + read(bArr2, offset2, 8)) - 8);
                    }
                    byte[] payload = new byte[((int) payload_length)];
                    read(payload, 0, (int) payload_length);
                    if (opcode == 8) {
                        this.websocket.onCloseOpReceived();
                    } else if (opcode != 10) {
                        if (!(opcode == 1 || opcode == 2 || opcode == 9)) {
                            if (opcode != 0) {
                                throw new WebSocketException("Unsupported opcode: " + opcode);
                            }
                        }
                        appendBytes(fin, opcode, payload);
                    }
                } else {
                    throw new WebSocketException("Invalid frame received");
                }
            } catch (SocketTimeoutException e) {
            } catch (IOException ioe) {
                handleError(new WebSocketException("IO Error", ioe));
            } catch (WebSocketException e2) {
                handleError(e2);
            }
        }
    }

    private void appendBytes(boolean fin, byte opcode, byte[] data) {
        if (opcode != 9) {
            MessageBuilderFactory.Builder builder = this.pendingBuilder;
            if (builder != null && opcode != 0) {
                throw new WebSocketException("Failed to continue outstanding frame");
            } else if (builder == null && opcode == 0) {
                throw new WebSocketException("Received continuing frame, but there's nothing to continue");
            } else {
                if (builder == null) {
                    this.pendingBuilder = MessageBuilderFactory.builder(opcode);
                }
                if (!this.pendingBuilder.appendBytes(data)) {
                    throw new WebSocketException("Failed to decode frame");
                } else if (fin) {
                    WebSocketMessage message = this.pendingBuilder.toMessage();
                    this.pendingBuilder = null;
                    if (message != null) {
                        this.eventHandler.onMessage(message);
                        return;
                    }
                    throw new WebSocketException("Failed to decode whole message");
                }
            }
        } else if (fin) {
            handlePing(data);
        } else {
            throw new WebSocketException("PING must not fragment across frames");
        }
    }

    private void handlePing(byte[] payload) {
        if (payload.length <= 125) {
            this.websocket.pong(payload);
            return;
        }
        throw new WebSocketException("PING frame too long");
    }

    private long parseLong(byte[] buffer, int offset) {
        return (((long) buffer[offset + 0]) << 56) + (((long) (buffer[offset + 1] & 255)) << 48) + (((long) (buffer[offset + 2] & 255)) << 40) + (((long) (buffer[offset + 3] & 255)) << 32) + (((long) (buffer[offset + 4] & 255)) << 24) + ((long) ((buffer[offset + 5] & 255) << Ascii.DLE)) + ((long) ((buffer[offset + 6] & 255) << 8)) + ((long) ((buffer[offset + 7] & 255) << 0));
    }

    private int read(byte[] buffer, int offset, int length) throws IOException {
        this.input.readFully(buffer, offset, length);
        return length;
    }

    /* access modifiers changed from: package-private */
    public void stopit() {
        this.stop = true;
    }

    /* access modifiers changed from: package-private */
    public boolean isRunning() {
        return !this.stop;
    }

    private void handleError(WebSocketException e) {
        stopit();
        this.websocket.handleReceiverError(e);
    }
}
