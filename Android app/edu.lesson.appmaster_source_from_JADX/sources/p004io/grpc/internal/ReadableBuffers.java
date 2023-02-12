package p004io.grpc.internal;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.InvalidMarkException;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import p004io.grpc.Detachable;
import p004io.grpc.HasByteBuffer;
import p004io.grpc.KnownLength;

/* renamed from: io.grpc.internal.ReadableBuffers */
public final class ReadableBuffers {
    private static final ReadableBuffer EMPTY_BUFFER = new ByteArrayWrapper(new byte[0]);

    public static ReadableBuffer empty() {
        return EMPTY_BUFFER;
    }

    public static ReadableBuffer wrap(byte[] bytes) {
        return new ByteArrayWrapper(bytes, 0, bytes.length);
    }

    public static ReadableBuffer wrap(byte[] bytes, int offset, int length) {
        return new ByteArrayWrapper(bytes, offset, length);
    }

    public static ReadableBuffer wrap(ByteBuffer bytes) {
        return new ByteReadableBufferWrapper(bytes);
    }

    public static byte[] readArray(ReadableBuffer buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        int length = buffer.readableBytes();
        byte[] bytes = new byte[length];
        buffer.readBytes(bytes, 0, length);
        return bytes;
    }

    public static String readAsString(ReadableBuffer buffer, Charset charset) {
        Preconditions.checkNotNull(charset, "charset");
        return new String(readArray(buffer), charset);
    }

    public static String readAsStringUtf8(ReadableBuffer buffer) {
        return readAsString(buffer, Charsets.UTF_8);
    }

    public static InputStream openStream(ReadableBuffer buffer, boolean owner) {
        return new BufferInputStream(owner ? buffer : ignoreClose(buffer));
    }

    public static ReadableBuffer ignoreClose(ReadableBuffer buffer) {
        return new ForwardingReadableBuffer(buffer) {
            public void close() {
            }
        };
    }

    /* renamed from: io.grpc.internal.ReadableBuffers$ByteArrayWrapper */
    private static class ByteArrayWrapper extends AbstractReadableBuffer {
        final byte[] bytes;
        final int end;
        int mark;
        int offset;

        ByteArrayWrapper(byte[] bytes2) {
            this(bytes2, 0, bytes2.length);
        }

        ByteArrayWrapper(byte[] bytes2, int offset2, int length) {
            this.mark = -1;
            boolean z = true;
            Preconditions.checkArgument(offset2 >= 0, "offset must be >= 0");
            Preconditions.checkArgument(length >= 0, "length must be >= 0");
            Preconditions.checkArgument(offset2 + length > bytes2.length ? false : z, "offset + length exceeds array boundary");
            this.bytes = (byte[]) Preconditions.checkNotNull(bytes2, "bytes");
            this.offset = offset2;
            this.end = offset2 + length;
        }

        public int readableBytes() {
            return this.end - this.offset;
        }

        public void skipBytes(int length) {
            checkReadable(length);
            this.offset += length;
        }

        public int readUnsignedByte() {
            checkReadable(1);
            byte[] bArr = this.bytes;
            int i = this.offset;
            this.offset = i + 1;
            return bArr[i] & 255;
        }

        public void readBytes(byte[] dest, int destIndex, int length) {
            System.arraycopy(this.bytes, this.offset, dest, destIndex, length);
            this.offset += length;
        }

        public void readBytes(ByteBuffer dest) {
            Preconditions.checkNotNull(dest, "dest");
            int length = dest.remaining();
            checkReadable(length);
            dest.put(this.bytes, this.offset, length);
            this.offset += length;
        }

        public void readBytes(OutputStream dest, int length) throws IOException {
            checkReadable(length);
            dest.write(this.bytes, this.offset, length);
            this.offset += length;
        }

        public ByteArrayWrapper readBytes(int length) {
            checkReadable(length);
            int originalOffset = this.offset;
            this.offset += length;
            return new ByteArrayWrapper(this.bytes, originalOffset, length);
        }

        public boolean hasArray() {
            return true;
        }

        public byte[] array() {
            return this.bytes;
        }

        public int arrayOffset() {
            return this.offset;
        }

        public boolean markSupported() {
            return true;
        }

        public void mark() {
            this.mark = this.offset;
        }

        public void reset() {
            int i = this.mark;
            if (i != -1) {
                this.offset = i;
                return;
            }
            throw new InvalidMarkException();
        }
    }

    /* renamed from: io.grpc.internal.ReadableBuffers$ByteReadableBufferWrapper */
    private static class ByteReadableBufferWrapper extends AbstractReadableBuffer {
        final ByteBuffer bytes;

        ByteReadableBufferWrapper(ByteBuffer bytes2) {
            this.bytes = (ByteBuffer) Preconditions.checkNotNull(bytes2, "bytes");
        }

        public int readableBytes() {
            return this.bytes.remaining();
        }

        public int readUnsignedByte() {
            checkReadable(1);
            return this.bytes.get() & 255;
        }

        public void skipBytes(int length) {
            checkReadable(length);
            ByteBuffer byteBuffer = this.bytes;
            byteBuffer.position(byteBuffer.position() + length);
        }

        public void readBytes(byte[] dest, int destOffset, int length) {
            checkReadable(length);
            this.bytes.get(dest, destOffset, length);
        }

        public void readBytes(ByteBuffer dest) {
            Preconditions.checkNotNull(dest, "dest");
            int length = dest.remaining();
            checkReadable(length);
            int prevLimit = this.bytes.limit();
            ByteBuffer byteBuffer = this.bytes;
            byteBuffer.limit(byteBuffer.position() + length);
            dest.put(this.bytes);
            this.bytes.limit(prevLimit);
        }

        public void readBytes(OutputStream dest, int length) throws IOException {
            checkReadable(length);
            if (hasArray()) {
                dest.write(array(), arrayOffset(), length);
                ByteBuffer byteBuffer = this.bytes;
                byteBuffer.position(byteBuffer.position() + length);
                return;
            }
            byte[] array = new byte[length];
            this.bytes.get(array);
            dest.write(array);
        }

        public ByteReadableBufferWrapper readBytes(int length) {
            checkReadable(length);
            ByteBuffer buffer = this.bytes.duplicate();
            buffer.limit(this.bytes.position() + length);
            ByteBuffer byteBuffer = this.bytes;
            byteBuffer.position(byteBuffer.position() + length);
            return new ByteReadableBufferWrapper(buffer);
        }

        public boolean hasArray() {
            return this.bytes.hasArray();
        }

        public byte[] array() {
            return this.bytes.array();
        }

        public int arrayOffset() {
            return this.bytes.arrayOffset() + this.bytes.position();
        }

        public boolean markSupported() {
            return true;
        }

        public void mark() {
            this.bytes.mark();
        }

        public void reset() {
            this.bytes.reset();
        }

        public boolean byteBufferSupported() {
            return true;
        }

        public ByteBuffer getByteBuffer() {
            return this.bytes.slice();
        }
    }

    /* renamed from: io.grpc.internal.ReadableBuffers$BufferInputStream */
    private static final class BufferInputStream extends InputStream implements KnownLength, HasByteBuffer, Detachable {
        private ReadableBuffer buffer;

        public BufferInputStream(ReadableBuffer buffer2) {
            this.buffer = (ReadableBuffer) Preconditions.checkNotNull(buffer2, "buffer");
        }

        public int available() throws IOException {
            return this.buffer.readableBytes();
        }

        public int read() {
            if (this.buffer.readableBytes() == 0) {
                return -1;
            }
            return this.buffer.readUnsignedByte();
        }

        public int read(byte[] dest, int destOffset, int length) throws IOException {
            if (this.buffer.readableBytes() == 0) {
                return -1;
            }
            int length2 = Math.min(this.buffer.readableBytes(), length);
            this.buffer.readBytes(dest, destOffset, length2);
            return length2;
        }

        public long skip(long n) throws IOException {
            int length = (int) Math.min((long) this.buffer.readableBytes(), n);
            this.buffer.skipBytes(length);
            return (long) length;
        }

        public void mark(int readlimit) {
            this.buffer.mark();
        }

        public void reset() throws IOException {
            this.buffer.reset();
        }

        public boolean markSupported() {
            return this.buffer.markSupported();
        }

        public boolean byteBufferSupported() {
            return this.buffer.byteBufferSupported();
        }

        @Nullable
        public ByteBuffer getByteBuffer() {
            return this.buffer.getByteBuffer();
        }

        public InputStream detach() {
            ReadableBuffer detachedBuffer = this.buffer;
            this.buffer = this.buffer.readBytes(0);
            return new BufferInputStream(detachedBuffer);
        }

        public void close() throws IOException {
            this.buffer.close();
        }
    }

    private ReadableBuffers() {
    }
}
