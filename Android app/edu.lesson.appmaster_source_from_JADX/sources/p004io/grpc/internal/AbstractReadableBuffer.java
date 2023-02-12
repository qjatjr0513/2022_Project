package p004io.grpc.internal;

import java.nio.ByteBuffer;

/* renamed from: io.grpc.internal.AbstractReadableBuffer */
public abstract class AbstractReadableBuffer implements ReadableBuffer {
    public final int readInt() {
        checkReadable(4);
        return (readUnsignedByte() << 24) | (readUnsignedByte() << 16) | (readUnsignedByte() << 8) | readUnsignedByte();
    }

    public boolean hasArray() {
        return false;
    }

    public byte[] array() {
        throw new UnsupportedOperationException();
    }

    public int arrayOffset() {
        throw new UnsupportedOperationException();
    }

    public boolean markSupported() {
        return false;
    }

    public void mark() {
    }

    public void reset() {
        throw new UnsupportedOperationException();
    }

    public boolean byteBufferSupported() {
        return false;
    }

    public ByteBuffer getByteBuffer() {
        throw new UnsupportedOperationException();
    }

    public void close() {
    }

    /* access modifiers changed from: protected */
    public final void checkReadable(int length) {
        if (readableBytes() < length) {
            throw new IndexOutOfBoundsException();
        }
    }
}
