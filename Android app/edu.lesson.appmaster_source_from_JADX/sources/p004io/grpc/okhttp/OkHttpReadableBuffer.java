package p004io.grpc.okhttp;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import okio.Buffer;
import p004io.grpc.internal.AbstractReadableBuffer;
import p004io.grpc.internal.ReadableBuffer;

/* renamed from: io.grpc.okhttp.OkHttpReadableBuffer */
class OkHttpReadableBuffer extends AbstractReadableBuffer {
    private final Buffer buffer;

    OkHttpReadableBuffer(Buffer buffer2) {
        this.buffer = buffer2;
    }

    public int readableBytes() {
        return (int) this.buffer.size();
    }

    public int readUnsignedByte() {
        try {
            fakeEofExceptionMethod();
            return this.buffer.readByte() & 255;
        } catch (EOFException e) {
            throw new IndexOutOfBoundsException(e.getMessage());
        }
    }

    private void fakeEofExceptionMethod() throws EOFException {
    }

    public void skipBytes(int length) {
        try {
            this.buffer.skip((long) length);
        } catch (EOFException e) {
            throw new IndexOutOfBoundsException(e.getMessage());
        }
    }

    public void readBytes(byte[] dest, int destOffset, int length) {
        while (length > 0) {
            int bytesRead = this.buffer.read(dest, destOffset, length);
            if (bytesRead != -1) {
                length -= bytesRead;
                destOffset += bytesRead;
            } else {
                throw new IndexOutOfBoundsException("EOF trying to read " + length + " bytes");
            }
        }
    }

    public void readBytes(ByteBuffer dest) {
        throw new UnsupportedOperationException();
    }

    public void readBytes(OutputStream dest, int length) throws IOException {
        this.buffer.writeTo(dest, (long) length);
    }

    public ReadableBuffer readBytes(int length) {
        Buffer buf = new Buffer();
        buf.write(this.buffer, (long) length);
        return new OkHttpReadableBuffer(buf);
    }

    public void close() {
        this.buffer.clear();
    }
}
