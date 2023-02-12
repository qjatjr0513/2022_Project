package p004io.grpc.okhttp;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nullable;
import okio.Buffer;
import p004io.grpc.okhttp.internal.framed.FrameWriter;

/* renamed from: io.grpc.okhttp.OutboundFlowController */
class OutboundFlowController {
    /* access modifiers changed from: private */
    public final OutboundFlowState connectionState = new OutboundFlowState(0, 65535);
    /* access modifiers changed from: private */
    public final FrameWriter frameWriter;
    private int initialWindowSize = 65535;
    private final OkHttpClientTransport transport;

    OutboundFlowController(OkHttpClientTransport transport2, FrameWriter frameWriter2) {
        this.transport = (OkHttpClientTransport) Preconditions.checkNotNull(transport2, NotificationCompat.CATEGORY_TRANSPORT);
        this.frameWriter = (FrameWriter) Preconditions.checkNotNull(frameWriter2, "frameWriter");
    }

    /* access modifiers changed from: package-private */
    public boolean initialOutboundWindowSize(int newWindowSize) {
        if (newWindowSize >= 0) {
            int delta = newWindowSize - this.initialWindowSize;
            this.initialWindowSize = newWindowSize;
            for (OkHttpClientStream stream : this.transport.getActiveStreams()) {
                OutboundFlowState state = (OutboundFlowState) stream.getOutboundFlowState();
                if (state == null) {
                    stream.setOutboundFlowState(new OutboundFlowState(this, stream, this.initialWindowSize));
                } else {
                    state.incrementStreamWindow(delta);
                }
            }
            if (delta > 0) {
                return true;
            }
            return false;
        }
        throw new IllegalArgumentException("Invalid initial window size: " + newWindowSize);
    }

    /* access modifiers changed from: package-private */
    public int windowUpdate(@Nullable OkHttpClientStream stream, int delta) {
        if (stream == null) {
            int updatedWindow = this.connectionState.incrementStreamWindow(delta);
            writeStreams();
            return updatedWindow;
        }
        OutboundFlowState state = state(stream);
        int updatedWindow2 = state.incrementStreamWindow(delta);
        WriteStatus writeStatus = new WriteStatus();
        state.writeBytes(state.writableWindow(), writeStatus);
        if (writeStatus.hasWritten()) {
            flush();
        }
        return updatedWindow2;
    }

    /* access modifiers changed from: package-private */
    public void data(boolean outFinished, int streamId, Buffer source, boolean flush) {
        Preconditions.checkNotNull(source, "source");
        OkHttpClientStream stream = this.transport.getStream(streamId);
        if (stream != null) {
            OutboundFlowState state = state(stream);
            int window = state.writableWindow();
            boolean framesAlreadyQueued = state.hasPendingData();
            int size = (int) source.size();
            if (framesAlreadyQueued || window < size) {
                if (!framesAlreadyQueued && window > 0) {
                    state.write(source, window, false);
                }
                state.enqueue(source, (int) source.size(), outFinished);
            } else {
                state.write(source, size, outFinished);
            }
            if (flush) {
                flush();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void flush() {
        try {
            this.frameWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private OutboundFlowState state(OkHttpClientStream stream) {
        OutboundFlowState state = (OutboundFlowState) stream.getOutboundFlowState();
        if (state != null) {
            return state;
        }
        OutboundFlowState state2 = new OutboundFlowState(this, stream, this.initialWindowSize);
        stream.setOutboundFlowState(state2);
        return state2;
    }

    /* access modifiers changed from: package-private */
    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public void writeStreams() {
        /*
            r10 = this;
            io.grpc.okhttp.OkHttpClientTransport r0 = r10.transport
            io.grpc.okhttp.OkHttpClientStream[] r0 = r0.getActiveStreams()
            io.grpc.okhttp.OutboundFlowController$OutboundFlowState r1 = r10.connectionState
            int r1 = r1.window()
            int r2 = r0.length
        L_0x000d:
            if (r2 <= 0) goto L_0x0048
            if (r1 <= 0) goto L_0x0048
            r3 = 0
            float r4 = (float) r1
            float r5 = (float) r2
            float r4 = r4 / r5
            double r4 = (double) r4
            double r4 = java.lang.Math.ceil(r4)
            int r4 = (int) r4
            r5 = 0
        L_0x001c:
            if (r5 >= r2) goto L_0x0046
            if (r1 <= 0) goto L_0x0046
            r6 = r0[r5]
            io.grpc.okhttp.OutboundFlowController$OutboundFlowState r7 = r10.state(r6)
            int r8 = r7.unallocatedBytes()
            int r8 = java.lang.Math.min(r8, r4)
            int r8 = java.lang.Math.min(r1, r8)
            if (r8 <= 0) goto L_0x0038
            r7.allocateBytes(r8)
            int r1 = r1 - r8
        L_0x0038:
            int r9 = r7.unallocatedBytes()
            if (r9 <= 0) goto L_0x0043
            int r9 = r3 + 1
            r0[r3] = r6
            r3 = r9
        L_0x0043:
            int r5 = r5 + 1
            goto L_0x001c
        L_0x0046:
            r2 = r3
            goto L_0x000d
        L_0x0048:
            io.grpc.okhttp.OutboundFlowController$WriteStatus r2 = new io.grpc.okhttp.OutboundFlowController$WriteStatus
            r3 = 0
            r2.<init>()
            io.grpc.okhttp.OkHttpClientTransport r3 = r10.transport
            io.grpc.okhttp.OkHttpClientStream[] r3 = r3.getActiveStreams()
            int r4 = r3.length
            r5 = 0
        L_0x0056:
            if (r5 >= r4) goto L_0x006b
            r6 = r3[r5]
            io.grpc.okhttp.OutboundFlowController$OutboundFlowState r7 = r10.state(r6)
            int r8 = r7.allocatedBytes()
            r7.writeBytes(r8, r2)
            r7.clearAllocatedBytes()
            int r5 = r5 + 1
            goto L_0x0056
        L_0x006b:
            boolean r3 = r2.hasWritten()
            if (r3 == 0) goto L_0x0074
            r10.flush()
        L_0x0074:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.okhttp.OutboundFlowController.writeStreams():void");
    }

    /* renamed from: io.grpc.okhttp.OutboundFlowController$WriteStatus */
    private static final class WriteStatus {
        int numWrites;

        private WriteStatus() {
        }

        /* access modifiers changed from: package-private */
        public void incrementNumWrites() {
            this.numWrites++;
        }

        /* access modifiers changed from: package-private */
        public boolean hasWritten() {
            return this.numWrites > 0;
        }
    }

    /* renamed from: io.grpc.okhttp.OutboundFlowController$OutboundFlowState */
    private final class OutboundFlowState {
        int allocatedBytes;
        boolean pendingBufferHasEndOfStream;
        final Buffer pendingWriteBuffer;
        OkHttpClientStream stream;
        final int streamId;
        int window;

        OutboundFlowState(int streamId2, int initialWindowSize) {
            this.pendingBufferHasEndOfStream = false;
            this.streamId = streamId2;
            this.window = initialWindowSize;
            this.pendingWriteBuffer = new Buffer();
        }

        OutboundFlowState(OutboundFlowController outboundFlowController, OkHttpClientStream stream2, int initialWindowSize) {
            this(stream2.mo17338id(), initialWindowSize);
            this.stream = stream2;
        }

        /* access modifiers changed from: package-private */
        public int window() {
            return this.window;
        }

        /* access modifiers changed from: package-private */
        public void allocateBytes(int bytes) {
            this.allocatedBytes += bytes;
        }

        /* access modifiers changed from: package-private */
        public int allocatedBytes() {
            return this.allocatedBytes;
        }

        /* access modifiers changed from: package-private */
        public int unallocatedBytes() {
            return streamableBytes() - this.allocatedBytes;
        }

        /* access modifiers changed from: package-private */
        public void clearAllocatedBytes() {
            this.allocatedBytes = 0;
        }

        /* access modifiers changed from: package-private */
        public int incrementStreamWindow(int delta) {
            if (delta <= 0 || Integer.MAX_VALUE - delta >= this.window) {
                int i = this.window + delta;
                this.window = i;
                return i;
            }
            throw new IllegalArgumentException("Window size overflow for stream: " + this.streamId);
        }

        /* access modifiers changed from: package-private */
        public int writableWindow() {
            return Math.min(this.window, OutboundFlowController.this.connectionState.window());
        }

        /* access modifiers changed from: package-private */
        public int streamableBytes() {
            return Math.max(0, Math.min(this.window, (int) this.pendingWriteBuffer.size()));
        }

        /* access modifiers changed from: package-private */
        public boolean hasPendingData() {
            return this.pendingWriteBuffer.size() > 0;
        }

        /* access modifiers changed from: package-private */
        public int writeBytes(int bytes, WriteStatus writeStatus) {
            int bytesAttempted = 0;
            int maxBytes = Math.min(bytes, writableWindow());
            while (hasPendingData() && maxBytes > 0) {
                if (((long) maxBytes) >= this.pendingWriteBuffer.size()) {
                    bytesAttempted += (int) this.pendingWriteBuffer.size();
                    Buffer buffer = this.pendingWriteBuffer;
                    write(buffer, (int) buffer.size(), this.pendingBufferHasEndOfStream);
                } else {
                    bytesAttempted += maxBytes;
                    write(this.pendingWriteBuffer, maxBytes, false);
                }
                writeStatus.incrementNumWrites();
                maxBytes = Math.min(bytes - bytesAttempted, writableWindow());
            }
            return bytesAttempted;
        }

        /* access modifiers changed from: package-private */
        public void write(Buffer buffer, int bytesToSend, boolean endOfStream) {
            int bytesToWrite = bytesToSend;
            do {
                int frameBytes = Math.min(bytesToWrite, OutboundFlowController.this.frameWriter.maxDataLength());
                OutboundFlowController.this.connectionState.incrementStreamWindow(-frameBytes);
                incrementStreamWindow(-frameBytes);
                try {
                    OutboundFlowController.this.frameWriter.data(buffer.size() == ((long) frameBytes) && endOfStream, this.streamId, buffer, frameBytes);
                    this.stream.transportState().onSentBytes(frameBytes);
                    bytesToWrite -= frameBytes;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } while (bytesToWrite > 0);
        }

        /* access modifiers changed from: package-private */
        public void enqueue(Buffer buffer, int size, boolean endOfStream) {
            this.pendingWriteBuffer.write(buffer, (long) size);
            this.pendingBufferHasEndOfStream |= endOfStream;
        }
    }
}
