package com.google.common.p000io;

import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;

/* renamed from: com.google.common.io.ReaderInputStream */
final class ReaderInputStream extends InputStream {
    private ByteBuffer byteBuffer;
    private CharBuffer charBuffer;
    private boolean doneFlushing;
    private boolean draining;
    private final CharsetEncoder encoder;
    private boolean endOfInput;
    private final Reader reader;
    private final byte[] singleByte;

    ReaderInputStream(Reader reader2, Charset charset, int bufferSize) {
        this(reader2, charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE), bufferSize);
    }

    ReaderInputStream(Reader reader2, CharsetEncoder encoder2, int bufferSize) {
        boolean z = true;
        this.singleByte = new byte[1];
        this.reader = (Reader) Preconditions.checkNotNull(reader2);
        this.encoder = (CharsetEncoder) Preconditions.checkNotNull(encoder2);
        Preconditions.checkArgument(bufferSize <= 0 ? false : z, "bufferSize must be positive: %s", bufferSize);
        encoder2.reset();
        CharBuffer allocate = CharBuffer.allocate(bufferSize);
        this.charBuffer = allocate;
        Java8Compatibility.flip(allocate);
        this.byteBuffer = ByteBuffer.allocate(bufferSize);
    }

    public void close() throws IOException {
        this.reader.close();
    }

    public int read() throws IOException {
        if (read(this.singleByte) == 1) {
            return UnsignedBytes.toInt(this.singleByte[0]);
        }
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002d A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read(byte[] r8, int r9, int r10) throws java.io.IOException {
        /*
            r7 = this;
            int r0 = r9 + r10
            int r1 = r8.length
            com.google.common.base.Preconditions.checkPositionIndexes(r9, r0, r1)
            r0 = 0
            if (r10 != 0) goto L_0x000a
            return r0
        L_0x000a:
            r1 = 0
            boolean r2 = r7.endOfInput
        L_0x000d:
            boolean r3 = r7.draining
            if (r3 == 0) goto L_0x002f
            int r3 = r9 + r1
            int r4 = r10 - r1
            int r3 = r7.drain(r8, r3, r4)
            int r1 = r1 + r3
            if (r1 == r10) goto L_0x0029
            boolean r3 = r7.doneFlushing
            if (r3 == 0) goto L_0x0021
            goto L_0x0029
        L_0x0021:
            r7.draining = r0
            java.nio.ByteBuffer r3 = r7.byteBuffer
            com.google.common.p000io.Java8Compatibility.clear(r3)
            goto L_0x002f
        L_0x0029:
            if (r1 <= 0) goto L_0x002d
            r0 = r1
            goto L_0x002e
        L_0x002d:
            r0 = -1
        L_0x002e:
            return r0
        L_0x002f:
            boolean r3 = r7.doneFlushing
            if (r3 == 0) goto L_0x0036
            java.nio.charset.CoderResult r3 = java.nio.charset.CoderResult.UNDERFLOW
            goto L_0x004d
        L_0x0036:
            if (r2 == 0) goto L_0x0041
            java.nio.charset.CharsetEncoder r3 = r7.encoder
            java.nio.ByteBuffer r4 = r7.byteBuffer
            java.nio.charset.CoderResult r3 = r3.flush(r4)
            goto L_0x004d
        L_0x0041:
            java.nio.charset.CharsetEncoder r3 = r7.encoder
            java.nio.CharBuffer r4 = r7.charBuffer
            java.nio.ByteBuffer r5 = r7.byteBuffer
            boolean r6 = r7.endOfInput
            java.nio.charset.CoderResult r3 = r3.encode(r4, r5, r6)
        L_0x004d:
            boolean r4 = r3.isOverflow()
            r5 = 1
            if (r4 == 0) goto L_0x0058
            r7.startDraining(r5)
            goto L_0x000d
        L_0x0058:
            boolean r4 = r3.isUnderflow()
            if (r4 == 0) goto L_0x0070
            if (r2 == 0) goto L_0x0066
            r7.doneFlushing = r5
            r7.startDraining(r0)
            goto L_0x000d
        L_0x0066:
            boolean r4 = r7.endOfInput
            if (r4 == 0) goto L_0x006c
            r2 = 1
            goto L_0x007a
        L_0x006c:
            r7.readMoreChars()
            goto L_0x007a
        L_0x0070:
            boolean r4 = r3.isError()
            if (r4 == 0) goto L_0x007a
            r3.throwException()
            return r0
        L_0x007a:
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.p000io.ReaderInputStream.read(byte[], int, int):int");
    }

    private static CharBuffer grow(CharBuffer buf) {
        CharBuffer bigger = CharBuffer.wrap(Arrays.copyOf(buf.array(), buf.capacity() * 2));
        Java8Compatibility.position(bigger, buf.position());
        Java8Compatibility.limit(bigger, buf.limit());
        return bigger;
    }

    private void readMoreChars() throws IOException {
        if (availableCapacity(this.charBuffer) == 0) {
            if (this.charBuffer.position() > 0) {
                Java8Compatibility.flip(this.charBuffer.compact());
            } else {
                this.charBuffer = grow(this.charBuffer);
            }
        }
        int limit = this.charBuffer.limit();
        int numChars = this.reader.read(this.charBuffer.array(), limit, availableCapacity(this.charBuffer));
        if (numChars == -1) {
            this.endOfInput = true;
        } else {
            Java8Compatibility.limit(this.charBuffer, limit + numChars);
        }
    }

    private static int availableCapacity(Buffer buffer) {
        return buffer.capacity() - buffer.limit();
    }

    private void startDraining(boolean overflow) {
        Java8Compatibility.flip(this.byteBuffer);
        if (!overflow || this.byteBuffer.remaining() != 0) {
            this.draining = true;
        } else {
            this.byteBuffer = ByteBuffer.allocate(this.byteBuffer.capacity() * 2);
        }
    }

    private int drain(byte[] b, int off, int len) {
        int remaining = Math.min(len, this.byteBuffer.remaining());
        this.byteBuffer.get(b, off, remaining);
        return remaining;
    }
}
