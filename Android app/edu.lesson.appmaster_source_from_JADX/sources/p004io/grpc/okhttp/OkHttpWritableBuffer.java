package p004io.grpc.okhttp;

import okio.Buffer;
import p004io.grpc.internal.WritableBuffer;

/* renamed from: io.grpc.okhttp.OkHttpWritableBuffer */
class OkHttpWritableBuffer implements WritableBuffer {
    private final Buffer buffer;
    private int readableBytes;
    private int writableBytes;

    OkHttpWritableBuffer(Buffer buffer2, int capacity) {
        this.buffer = buffer2;
        this.writableBytes = capacity;
    }

    public void write(byte[] src, int srcIndex, int length) {
        this.buffer.write(src, srcIndex, length);
        this.writableBytes -= length;
        this.readableBytes += length;
    }

    public void write(byte b) {
        this.buffer.writeByte((int) b);
        this.writableBytes--;
        this.readableBytes++;
    }

    public int writableBytes() {
        return this.writableBytes;
    }

    public int readableBytes() {
        return this.readableBytes;
    }

    public void release() {
    }

    /* access modifiers changed from: package-private */
    public Buffer buffer() {
        return this.buffer;
    }
}
