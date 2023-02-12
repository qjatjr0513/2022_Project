package p004io.grpc.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.InvalidMarkException;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.annotation.Nullable;

/* renamed from: io.grpc.internal.CompositeReadableBuffer */
public class CompositeReadableBuffer extends AbstractReadableBuffer {
    private static final NoThrowReadOperation<byte[]> BYTE_ARRAY_OP = new NoThrowReadOperation<byte[]>() {
        public int read(ReadableBuffer buffer, int length, byte[] dest, int offset) {
            buffer.readBytes(dest, offset, length);
            return offset + length;
        }
    };
    private static final NoThrowReadOperation<ByteBuffer> BYTE_BUF_OP = new NoThrowReadOperation<ByteBuffer>() {
        public int read(ReadableBuffer buffer, int length, ByteBuffer dest, int unused) {
            int prevLimit = dest.limit();
            dest.limit(dest.position() + length);
            buffer.readBytes(dest);
            dest.limit(prevLimit);
            return 0;
        }
    };
    private static final NoThrowReadOperation<Void> SKIP_OP = new NoThrowReadOperation<Void>() {
        public int read(ReadableBuffer buffer, int length, Void unused, int unused2) {
            buffer.skipBytes(length);
            return 0;
        }
    };
    private static final ReadOperation<OutputStream> STREAM_OP = new ReadOperation<OutputStream>() {
        public int read(ReadableBuffer buffer, int length, OutputStream dest, int unused) throws IOException {
            buffer.readBytes(dest, length);
            return 0;
        }
    };
    private static final NoThrowReadOperation<Void> UBYTE_OP = new NoThrowReadOperation<Void>() {
        public int read(ReadableBuffer buffer, int length, Void unused, int value) {
            return buffer.readUnsignedByte();
        }
    };
    private boolean marked;
    private final Deque<ReadableBuffer> readableBuffers;
    private int readableBytes;
    private Deque<ReadableBuffer> rewindableBuffers;

    /* renamed from: io.grpc.internal.CompositeReadableBuffer$NoThrowReadOperation */
    private interface NoThrowReadOperation<T> extends ReadOperation<T> {
        int read(ReadableBuffer readableBuffer, int i, T t, int i2);
    }

    /* renamed from: io.grpc.internal.CompositeReadableBuffer$ReadOperation */
    private interface ReadOperation<T> {
        int read(ReadableBuffer readableBuffer, int i, T t, int i2) throws IOException;
    }

    public CompositeReadableBuffer(int initialCapacity) {
        this.readableBuffers = new ArrayDeque(initialCapacity);
    }

    public CompositeReadableBuffer() {
        this.readableBuffers = new ArrayDeque();
    }

    public void addBuffer(ReadableBuffer buffer) {
        boolean markHead = this.marked && this.readableBuffers.isEmpty();
        enqueueBuffer(buffer);
        if (markHead) {
            this.readableBuffers.peek().mark();
        }
    }

    private void enqueueBuffer(ReadableBuffer buffer) {
        if (!(buffer instanceof CompositeReadableBuffer)) {
            this.readableBuffers.add(buffer);
            this.readableBytes += buffer.readableBytes();
            return;
        }
        CompositeReadableBuffer compositeBuffer = (CompositeReadableBuffer) buffer;
        while (!compositeBuffer.readableBuffers.isEmpty()) {
            this.readableBuffers.add(compositeBuffer.readableBuffers.remove());
        }
        this.readableBytes += compositeBuffer.readableBytes;
        compositeBuffer.readableBytes = 0;
        compositeBuffer.close();
    }

    public int readableBytes() {
        return this.readableBytes;
    }

    public int readUnsignedByte() {
        return executeNoThrow(UBYTE_OP, 1, (Object) null, 0);
    }

    public void skipBytes(int length) {
        executeNoThrow(SKIP_OP, length, (Object) null, 0);
    }

    public void readBytes(byte[] dest, int destOffset, int length) {
        executeNoThrow(BYTE_ARRAY_OP, length, dest, destOffset);
    }

    public void readBytes(ByteBuffer dest) {
        executeNoThrow(BYTE_BUF_OP, dest.remaining(), dest, 0);
    }

    public void readBytes(OutputStream dest, int length) throws IOException {
        execute(STREAM_OP, length, dest, 0);
    }

    public ReadableBuffer readBytes(int length) {
        ReadableBuffer readBuffer;
        if (length <= 0) {
            return ReadableBuffers.empty();
        }
        checkReadable(length);
        this.readableBytes -= length;
        ReadableBuffer newBuffer = null;
        CompositeReadableBuffer newComposite = null;
        do {
            ReadableBuffer buffer = this.readableBuffers.peek();
            int readable = buffer.readableBytes();
            if (readable > length) {
                readBuffer = buffer.readBytes(length);
                length = 0;
            } else {
                if (this.marked) {
                    readBuffer = buffer.readBytes(readable);
                    advanceBuffer();
                } else {
                    readBuffer = this.readableBuffers.poll();
                }
                length -= readable;
            }
            if (newBuffer == null) {
                newBuffer = readBuffer;
                continue;
            } else {
                if (newComposite == null) {
                    int i = 2;
                    if (length != 0) {
                        i = Math.min(this.readableBuffers.size() + 2, 16);
                    }
                    newComposite = new CompositeReadableBuffer(i);
                    newComposite.addBuffer(newBuffer);
                    newBuffer = newComposite;
                }
                newComposite.addBuffer(readBuffer);
                continue;
            }
        } while (length > 0);
        return newBuffer;
    }

    public boolean markSupported() {
        for (ReadableBuffer buffer : this.readableBuffers) {
            if (!buffer.markSupported()) {
                return false;
            }
        }
        return true;
    }

    public void mark() {
        if (this.rewindableBuffers == null) {
            this.rewindableBuffers = new ArrayDeque(Math.min(this.readableBuffers.size(), 16));
        }
        while (!this.rewindableBuffers.isEmpty()) {
            this.rewindableBuffers.remove().close();
        }
        this.marked = true;
        ReadableBuffer buffer = this.readableBuffers.peek();
        if (buffer != null) {
            buffer.mark();
        }
    }

    public void reset() {
        if (this.marked) {
            ReadableBuffer peek = this.readableBuffers.peek();
            ReadableBuffer buffer = peek;
            if (peek != null) {
                int currentRemain = buffer.readableBytes();
                buffer.reset();
                this.readableBytes += buffer.readableBytes() - currentRemain;
            }
            while (true) {
                ReadableBuffer pollLast = this.rewindableBuffers.pollLast();
                ReadableBuffer buffer2 = pollLast;
                if (pollLast != null) {
                    buffer2.reset();
                    this.readableBuffers.addFirst(buffer2);
                    this.readableBytes += buffer2.readableBytes();
                } else {
                    return;
                }
            }
        } else {
            throw new InvalidMarkException();
        }
    }

    public boolean byteBufferSupported() {
        for (ReadableBuffer buffer : this.readableBuffers) {
            if (!buffer.byteBufferSupported()) {
                return false;
            }
        }
        return true;
    }

    @Nullable
    public ByteBuffer getByteBuffer() {
        if (this.readableBuffers.isEmpty()) {
            return null;
        }
        return this.readableBuffers.peek().getByteBuffer();
    }

    public void close() {
        while (!this.readableBuffers.isEmpty()) {
            this.readableBuffers.remove().close();
        }
        if (this.rewindableBuffers != null) {
            while (!this.rewindableBuffers.isEmpty()) {
                this.rewindableBuffers.remove().close();
            }
        }
    }

    private <T> int execute(ReadOperation<T> op, int length, T dest, int value) throws IOException {
        checkReadable(length);
        if (!this.readableBuffers.isEmpty()) {
            advanceBufferIfNecessary();
        }
        while (length > 0 && !this.readableBuffers.isEmpty()) {
            ReadableBuffer buffer = this.readableBuffers.peek();
            int lengthToCopy = Math.min(length, buffer.readableBytes());
            value = op.read(buffer, lengthToCopy, dest, value);
            length -= lengthToCopy;
            this.readableBytes -= lengthToCopy;
            advanceBufferIfNecessary();
        }
        if (length <= 0) {
            return value;
        }
        throw new AssertionError("Failed executing read operation");
    }

    private <T> int executeNoThrow(NoThrowReadOperation<T> op, int length, T dest, int value) {
        try {
            return execute(op, length, dest, value);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    private void advanceBufferIfNecessary() {
        if (this.readableBuffers.peek().readableBytes() == 0) {
            advanceBuffer();
        }
    }

    private void advanceBuffer() {
        if (this.marked) {
            this.rewindableBuffers.add(this.readableBuffers.remove());
            ReadableBuffer next = this.readableBuffers.peek();
            if (next != null) {
                next.mark();
                return;
            }
            return;
        }
        this.readableBuffers.remove().close();
    }
}
