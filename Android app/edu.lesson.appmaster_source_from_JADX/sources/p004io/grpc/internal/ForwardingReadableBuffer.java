package p004io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

/* renamed from: io.grpc.internal.ForwardingReadableBuffer */
public abstract class ForwardingReadableBuffer implements ReadableBuffer {
    private final ReadableBuffer buf;

    protected ForwardingReadableBuffer(ReadableBuffer buf2) {
        this.buf = (ReadableBuffer) Preconditions.checkNotNull(buf2, "buf");
    }

    public int readableBytes() {
        return this.buf.readableBytes();
    }

    public int readUnsignedByte() {
        return this.buf.readUnsignedByte();
    }

    public int readInt() {
        return this.buf.readInt();
    }

    public void skipBytes(int length) {
        this.buf.skipBytes(length);
    }

    public void readBytes(byte[] dest, int destOffset, int length) {
        this.buf.readBytes(dest, destOffset, length);
    }

    public void readBytes(ByteBuffer dest) {
        this.buf.readBytes(dest);
    }

    public void readBytes(OutputStream dest, int length) throws IOException {
        this.buf.readBytes(dest, length);
    }

    public ReadableBuffer readBytes(int length) {
        return this.buf.readBytes(length);
    }

    public boolean hasArray() {
        return this.buf.hasArray();
    }

    public byte[] array() {
        return this.buf.array();
    }

    public int arrayOffset() {
        return this.buf.arrayOffset();
    }

    public boolean markSupported() {
        return this.buf.markSupported();
    }

    public void mark() {
        this.buf.mark();
    }

    public void reset() {
        this.buf.reset();
    }

    public boolean byteBufferSupported() {
        return this.buf.byteBufferSupported();
    }

    @Nullable
    public ByteBuffer getByteBuffer() {
        return this.buf.getByteBuffer();
    }

    public void close() {
        this.buf.close();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) this.buf).toString();
    }
}
