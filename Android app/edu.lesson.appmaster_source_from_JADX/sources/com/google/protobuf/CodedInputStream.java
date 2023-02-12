package com.google.protobuf;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import com.google.protobuf.MessageLite;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class CodedInputStream {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final int DEFAULT_SIZE_LIMIT = Integer.MAX_VALUE;
    private static volatile int defaultRecursionLimit = 100;
    int recursionDepth;
    int recursionLimit;
    private boolean shouldDiscardUnknownFields;
    int sizeLimit;
    CodedInputStreamReader wrapper;

    public abstract void checkLastTagWas(int i) throws InvalidProtocolBufferException;

    public abstract void enableAliasing(boolean z);

    public abstract int getBytesUntilLimit();

    public abstract int getLastTag();

    public abstract int getTotalBytesRead();

    public abstract boolean isAtEnd() throws IOException;

    public abstract void popLimit(int i);

    public abstract int pushLimit(int i) throws InvalidProtocolBufferException;

    public abstract boolean readBool() throws IOException;

    public abstract byte[] readByteArray() throws IOException;

    public abstract ByteBuffer readByteBuffer() throws IOException;

    public abstract ByteString readBytes() throws IOException;

    public abstract double readDouble() throws IOException;

    public abstract int readEnum() throws IOException;

    public abstract int readFixed32() throws IOException;

    public abstract long readFixed64() throws IOException;

    public abstract float readFloat() throws IOException;

    public abstract <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract void readGroup(int i, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract int readInt32() throws IOException;

    public abstract long readInt64() throws IOException;

    public abstract <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract byte readRawByte() throws IOException;

    public abstract byte[] readRawBytes(int i) throws IOException;

    public abstract int readRawLittleEndian32() throws IOException;

    public abstract long readRawLittleEndian64() throws IOException;

    public abstract int readRawVarint32() throws IOException;

    public abstract long readRawVarint64() throws IOException;

    /* access modifiers changed from: package-private */
    public abstract long readRawVarint64SlowPath() throws IOException;

    public abstract int readSFixed32() throws IOException;

    public abstract long readSFixed64() throws IOException;

    public abstract int readSInt32() throws IOException;

    public abstract long readSInt64() throws IOException;

    public abstract String readString() throws IOException;

    public abstract String readStringRequireUtf8() throws IOException;

    public abstract int readTag() throws IOException;

    public abstract int readUInt32() throws IOException;

    public abstract long readUInt64() throws IOException;

    @Deprecated
    public abstract void readUnknownGroup(int i, MessageLite.Builder builder) throws IOException;

    public abstract void resetSizeCounter();

    public abstract boolean skipField(int i) throws IOException;

    @Deprecated
    public abstract boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException;

    public abstract void skipMessage() throws IOException;

    public abstract void skipMessage(CodedOutputStream codedOutputStream) throws IOException;

    public abstract void skipRawBytes(int i) throws IOException;

    public static CodedInputStream newInstance(InputStream input) {
        return newInstance(input, 4096);
    }

    public static CodedInputStream newInstance(InputStream input, int bufferSize) {
        if (bufferSize <= 0) {
            throw new IllegalArgumentException("bufferSize must be > 0");
        } else if (input == null) {
            return newInstance(Internal.EMPTY_BYTE_ARRAY);
        } else {
            return new StreamDecoder(input, bufferSize);
        }
    }

    public static CodedInputStream newInstance(Iterable<ByteBuffer> input) {
        if (!UnsafeDirectNioDecoder.isSupported()) {
            return newInstance((InputStream) new IterableByteBufferInputStream(input));
        }
        return newInstance(input, false);
    }

    static CodedInputStream newInstance(Iterable<ByteBuffer> bufs, boolean bufferIsImmutable) {
        int flag = 0;
        int totalSize = 0;
        for (ByteBuffer buf : bufs) {
            totalSize += buf.remaining();
            if (buf.hasArray()) {
                flag |= 1;
            } else if (buf.isDirect()) {
                flag |= 2;
            } else {
                flag |= 4;
            }
        }
        if (flag == 2) {
            return new IterableDirectByteBufferDecoder(bufs, totalSize, bufferIsImmutable);
        }
        return newInstance((InputStream) new IterableByteBufferInputStream(bufs));
    }

    public static CodedInputStream newInstance(byte[] buf) {
        return newInstance(buf, 0, buf.length);
    }

    public static CodedInputStream newInstance(byte[] buf, int off, int len) {
        return newInstance(buf, off, len, false);
    }

    static CodedInputStream newInstance(byte[] buf, int off, int len, boolean bufferIsImmutable) {
        ArrayDecoder result = new ArrayDecoder(buf, off, len, bufferIsImmutable);
        try {
            result.pushLimit(len);
            return result;
        } catch (InvalidProtocolBufferException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public static CodedInputStream newInstance(ByteBuffer buf) {
        return newInstance(buf, false);
    }

    static CodedInputStream newInstance(ByteBuffer buf, boolean bufferIsImmutable) {
        if (buf.hasArray()) {
            return newInstance(buf.array(), buf.arrayOffset() + buf.position(), buf.remaining(), bufferIsImmutable);
        }
        if (buf.isDirect() && UnsafeDirectNioDecoder.isSupported()) {
            return new UnsafeDirectNioDecoder(buf, bufferIsImmutable);
        }
        byte[] buffer = new byte[buf.remaining()];
        buf.duplicate().get(buffer);
        return newInstance(buffer, 0, buffer.length, true);
    }

    public void checkRecursionLimit() throws InvalidProtocolBufferException {
        if (this.recursionDepth >= this.recursionLimit) {
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
    }

    private CodedInputStream() {
        this.recursionLimit = defaultRecursionLimit;
        this.sizeLimit = Integer.MAX_VALUE;
        this.shouldDiscardUnknownFields = false;
    }

    public final int setRecursionLimit(int limit) {
        if (limit >= 0) {
            int oldLimit = this.recursionLimit;
            this.recursionLimit = limit;
            return oldLimit;
        }
        throw new IllegalArgumentException("Recursion limit cannot be negative: " + limit);
    }

    public final int setSizeLimit(int limit) {
        if (limit >= 0) {
            int oldLimit = this.sizeLimit;
            this.sizeLimit = limit;
            return oldLimit;
        }
        throw new IllegalArgumentException("Size limit cannot be negative: " + limit);
    }

    /* access modifiers changed from: package-private */
    public final void discardUnknownFields() {
        this.shouldDiscardUnknownFields = true;
    }

    /* access modifiers changed from: package-private */
    public final void unsetDiscardUnknownFields() {
        this.shouldDiscardUnknownFields = false;
    }

    /* access modifiers changed from: package-private */
    public final boolean shouldDiscardUnknownFields() {
        return this.shouldDiscardUnknownFields;
    }

    public static int decodeZigZag32(int n) {
        return (n >>> 1) ^ (-(n & 1));
    }

    public static long decodeZigZag64(long n) {
        return (n >>> 1) ^ (-(1 & n));
    }

    public static int readRawVarint32(int firstByte, InputStream input) throws IOException {
        if ((firstByte & 128) == 0) {
            return firstByte;
        }
        int result = firstByte & 127;
        int offset = 7;
        while (offset < 32) {
            int b = input.read();
            if (b != -1) {
                result |= (b & 127) << offset;
                if ((b & 128) == 0) {
                    return result;
                }
                offset += 7;
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }
        while (offset < 64) {
            int b2 = input.read();
            if (b2 == -1) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if ((b2 & 128) == 0) {
                return result;
            } else {
                offset += 7;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    static int readRawVarint32(InputStream input) throws IOException {
        int firstByte = input.read();
        if (firstByte != -1) {
            return readRawVarint32(firstByte, input);
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    private static final class ArrayDecoder extends CodedInputStream {
        private final byte[] buffer;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private boolean enableAliasing;
        private final boolean immutable;
        private int lastTag;
        private int limit;
        private int pos;
        private int startPos;

        private ArrayDecoder(byte[] buffer2, int offset, int len, boolean immutable2) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.buffer = buffer2;
            this.limit = offset + len;
            this.pos = offset;
            this.startPos = offset;
            this.immutable = immutable2;
        }

        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            int readRawVarint32 = readRawVarint32();
            this.lastTag = readRawVarint32;
            if (WireFormat.getTagFieldNumber(readRawVarint32) != 0) {
                return this.lastTag;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        public void checkLastTagWas(int value) throws InvalidProtocolBufferException {
            if (this.lastTag != value) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public int getLastTag() {
            return this.lastTag;
        }

        public boolean skipField(int tag) throws IOException {
            switch (WireFormat.getTagWireType(tag)) {
                case 0:
                    skipRawVarint();
                    return true;
                case 1:
                    skipRawBytes(8);
                    return true;
                case 2:
                    skipRawBytes(readRawVarint32());
                    return true;
                case 3:
                    skipMessage();
                    checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(tag), 4));
                    return true;
                case 4:
                    return false;
                case 5:
                    skipRawBytes(4);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public boolean skipField(int tag, CodedOutputStream output) throws IOException {
            switch (WireFormat.getTagWireType(tag)) {
                case 0:
                    long value = readInt64();
                    output.writeRawVarint32(tag);
                    output.writeUInt64NoTag(value);
                    return true;
                case 1:
                    long value2 = readRawLittleEndian64();
                    output.writeRawVarint32(tag);
                    output.writeFixed64NoTag(value2);
                    return true;
                case 2:
                    ByteString value3 = readBytes();
                    output.writeRawVarint32(tag);
                    output.writeBytesNoTag(value3);
                    return true;
                case 3:
                    output.writeRawVarint32(tag);
                    skipMessage(output);
                    int endtag = WireFormat.makeTag(WireFormat.getTagFieldNumber(tag), 4);
                    checkLastTagWas(endtag);
                    output.writeRawVarint32(endtag);
                    return true;
                case 4:
                    return false;
                case 5:
                    int value4 = readRawLittleEndian32();
                    output.writeRawVarint32(tag);
                    output.writeFixed32NoTag(value4);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        public void skipMessage() throws java.io.IOException {
            /*
                r2 = this;
            L_0x0000:
                int r0 = r2.readTag()
                if (r0 == 0) goto L_0x000e
                boolean r1 = r2.skipField(r0)
                if (r1 != 0) goto L_0x000d
                goto L_0x000e
            L_0x000d:
                goto L_0x0000
            L_0x000e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.ArrayDecoder.skipMessage():void");
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        public void skipMessage(com.google.protobuf.CodedOutputStream r3) throws java.io.IOException {
            /*
                r2 = this;
            L_0x0000:
                int r0 = r2.readTag()
                if (r0 == 0) goto L_0x000e
                boolean r1 = r2.skipField(r0, r3)
                if (r1 != 0) goto L_0x000d
                goto L_0x000e
            L_0x000d:
                goto L_0x0000
            L_0x000e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.ArrayDecoder.skipMessage(com.google.protobuf.CodedOutputStream):void");
        }

        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        public String readString() throws IOException {
            int size = readRawVarint32();
            if (size > 0 && size <= this.limit - this.pos) {
                String result = new String(this.buffer, this.pos, size, Internal.UTF_8);
                this.pos += size;
                return result;
            } else if (size == 0) {
                return "";
            } else {
                if (size < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public String readStringRequireUtf8() throws IOException {
            int size = readRawVarint32();
            if (size > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (size <= i - i2) {
                    String result = Utf8.decodeUtf8(this.buffer, i2, size);
                    this.pos += size;
                    return result;
                }
            }
            if (size == 0) {
                return "";
            }
            if (size <= 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public void readGroup(int fieldNumber, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistry) throws IOException {
            checkRecursionLimit();
            this.recursionDepth++;
            builder.mergeFrom((CodedInputStream) this, extensionRegistry);
            checkLastTagWas(WireFormat.makeTag(fieldNumber, 4));
            this.recursionDepth--;
        }

        public <T extends MessageLite> T readGroup(int fieldNumber, Parser<T> parser, ExtensionRegistryLite extensionRegistry) throws IOException {
            checkRecursionLimit();
            this.recursionDepth++;
            T result = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistry);
            checkLastTagWas(WireFormat.makeTag(fieldNumber, 4));
            this.recursionDepth--;
            return result;
        }

        @Deprecated
        public void readUnknownGroup(int fieldNumber, MessageLite.Builder builder) throws IOException {
            readGroup(fieldNumber, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistry) throws IOException {
            int length = readRawVarint32();
            checkRecursionLimit();
            int oldLimit = pushLimit(length);
            this.recursionDepth++;
            builder.mergeFrom((CodedInputStream) this, extensionRegistry);
            checkLastTagWas(0);
            this.recursionDepth--;
            if (getBytesUntilLimit() == 0) {
                popLimit(oldLimit);
                return;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistry) throws IOException {
            int length = readRawVarint32();
            checkRecursionLimit();
            int oldLimit = pushLimit(length);
            this.recursionDepth++;
            T result = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistry);
            checkLastTagWas(0);
            this.recursionDepth--;
            if (getBytesUntilLimit() == 0) {
                popLimit(oldLimit);
                return result;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public ByteString readBytes() throws IOException {
            ByteString result;
            int size = readRawVarint32();
            if (size > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (size <= i - i2) {
                    if (!this.immutable || !this.enableAliasing) {
                        result = ByteString.copyFrom(this.buffer, i2, size);
                    } else {
                        result = ByteString.wrap(this.buffer, i2, size);
                    }
                    this.pos += size;
                    return result;
                }
            }
            if (size == 0) {
                return ByteString.EMPTY;
            }
            return ByteString.wrap(readRawBytes(size));
        }

        public byte[] readByteArray() throws IOException {
            return readRawBytes(readRawVarint32());
        }

        public ByteBuffer readByteBuffer() throws IOException {
            ByteBuffer result;
            int size = readRawVarint32();
            if (size > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (size <= i - i2) {
                    if (this.immutable || !this.enableAliasing) {
                        result = ByteBuffer.wrap(Arrays.copyOfRange(this.buffer, i2, i2 + size));
                    } else {
                        result = ByteBuffer.wrap(this.buffer, i2, size).slice();
                    }
                    this.pos += size;
                    return result;
                }
            }
            if (size == 0) {
                return Internal.EMPTY_BYTE_BUFFER;
            }
            if (size < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readSInt32() throws IOException {
            return decodeZigZag32(readRawVarint32());
        }

        public long readSInt64() throws IOException {
            return decodeZigZag64(readRawVarint64());
        }

        /* JADX WARNING: Code restructure failed: missing block: B:28:0x006f, code lost:
            if (r2[r1] < 0) goto L_0x0072;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int readRawVarint32() throws java.io.IOException {
            /*
                r6 = this;
                int r0 = r6.pos
                int r1 = r6.limit
                if (r1 != r0) goto L_0x0008
                goto L_0x0072
            L_0x0008:
                byte[] r2 = r6.buffer
                int r3 = r0 + 1
                byte r0 = r2[r0]
                r4 = r0
                if (r0 < 0) goto L_0x0014
                r6.pos = r3
                return r4
            L_0x0014:
                int r1 = r1 - r3
                r0 = 9
                if (r1 >= r0) goto L_0x001a
                goto L_0x0072
            L_0x001a:
                int r0 = r3 + 1
                byte r1 = r2[r3]
                int r1 = r1 << 7
                r1 = r1 ^ r4
                r3 = r1
                if (r1 >= 0) goto L_0x0027
                r1 = r3 ^ -128(0xffffffffffffff80, float:NaN)
                goto L_0x007d
            L_0x0027:
                int r1 = r0 + 1
                byte r0 = r2[r0]
                int r0 = r0 << 14
                r0 = r0 ^ r3
                r3 = r0
                if (r0 < 0) goto L_0x0037
                r0 = r3 ^ 16256(0x3f80, float:2.278E-41)
                r5 = r1
                r1 = r0
                r0 = r5
                goto L_0x007d
            L_0x0037:
                int r0 = r1 + 1
                byte r1 = r2[r1]
                int r1 = r1 << 21
                r1 = r1 ^ r3
                r3 = r1
                if (r1 >= 0) goto L_0x0046
                r1 = -2080896(0xffffffffffe03f80, float:NaN)
                r1 = r1 ^ r3
                goto L_0x007d
            L_0x0046:
                int r1 = r0 + 1
                byte r0 = r2[r0]
                int r4 = r0 << 28
                r3 = r3 ^ r4
                r4 = 266354560(0xfe03f80, float:2.2112565E-29)
                r3 = r3 ^ r4
                if (r0 >= 0) goto L_0x007b
                int r4 = r1 + 1
                byte r1 = r2[r1]
                if (r1 >= 0) goto L_0x0078
                int r1 = r4 + 1
                byte r4 = r2[r4]
                if (r4 >= 0) goto L_0x007b
                int r4 = r1 + 1
                byte r1 = r2[r1]
                if (r1 >= 0) goto L_0x0078
                int r1 = r4 + 1
                byte r4 = r2[r4]
                if (r4 >= 0) goto L_0x007b
                int r4 = r1 + 1
                byte r1 = r2[r1]
                if (r1 >= 0) goto L_0x0078
            L_0x0072:
                long r0 = r6.readRawVarint64SlowPath()
                int r0 = (int) r0
                return r0
            L_0x0078:
                r1 = r3
                r0 = r4
                goto L_0x007d
            L_0x007b:
                r0 = r1
                r1 = r3
            L_0x007d:
                r6.pos = r0
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.ArrayDecoder.readRawVarint32():int");
        }

        private void skipRawVarint() throws IOException {
            if (this.limit - this.pos >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws IOException {
            int i = 0;
            while (i < 10) {
                byte[] bArr = this.buffer;
                int i2 = this.pos;
                this.pos = i2 + 1;
                if (bArr[i2] < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws IOException {
            int i = 0;
            while (i < 10) {
                if (readRawByte() < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:32:0x00bd, code lost:
            if (((long) r2[r1]) < 0) goto L_0x00c0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long readRawVarint64() throws java.io.IOException {
            /*
                r10 = this;
                int r0 = r10.pos
                int r1 = r10.limit
                if (r1 != r0) goto L_0x0008
                goto L_0x00c0
            L_0x0008:
                byte[] r2 = r10.buffer
                int r3 = r0 + 1
                byte r0 = r2[r0]
                r4 = r0
                if (r0 < 0) goto L_0x0015
                r10.pos = r3
                long r0 = (long) r4
                return r0
            L_0x0015:
                int r1 = r1 - r3
                r0 = 9
                if (r1 >= r0) goto L_0x001c
                goto L_0x00c0
            L_0x001c:
                int r0 = r3 + 1
                byte r1 = r2[r3]
                int r1 = r1 << 7
                r1 = r1 ^ r4
                r3 = r1
                if (r1 >= 0) goto L_0x002b
                r1 = r3 ^ -128(0xffffffffffffff80, float:NaN)
                long r4 = (long) r1
                goto L_0x00c6
            L_0x002b:
                int r1 = r0 + 1
                byte r0 = r2[r0]
                int r0 = r0 << 14
                r0 = r0 ^ r3
                r3 = r0
                if (r0 < 0) goto L_0x003b
                r0 = r3 ^ 16256(0x3f80, float:2.278E-41)
                long r4 = (long) r0
                r0 = r1
                goto L_0x00c6
            L_0x003b:
                int r0 = r1 + 1
                byte r1 = r2[r1]
                int r1 = r1 << 21
                r1 = r1 ^ r3
                r3 = r1
                if (r1 >= 0) goto L_0x004c
                r1 = -2080896(0xffffffffffe03f80, float:NaN)
                r1 = r1 ^ r3
                long r4 = (long) r1
                goto L_0x00c6
            L_0x004c:
                long r4 = (long) r3
                int r1 = r0 + 1
                byte r0 = r2[r0]
                long r6 = (long) r0
                r0 = 28
                long r6 = r6 << r0
                long r4 = r4 ^ r6
                r6 = r4
                r8 = 0
                int r0 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
                if (r0 < 0) goto L_0x0063
                r4 = 266354560(0xfe03f80, double:1.315966377E-315)
                long r4 = r4 ^ r6
                r0 = r1
                goto L_0x00c6
            L_0x0063:
                int r0 = r1 + 1
                byte r1 = r2[r1]
                long r4 = (long) r1
                r1 = 35
                long r4 = r4 << r1
                long r4 = r4 ^ r6
                r6 = r4
                int r1 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
                if (r1 >= 0) goto L_0x0078
                r4 = -34093383808(0xfffffff80fe03f80, double:NaN)
                long r4 = r4 ^ r6
                goto L_0x00c6
            L_0x0078:
                int r1 = r0 + 1
                byte r0 = r2[r0]
                long r4 = (long) r0
                r0 = 42
                long r4 = r4 << r0
                long r4 = r4 ^ r6
                r6 = r4
                int r0 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
                if (r0 < 0) goto L_0x008e
                r4 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
                long r4 = r4 ^ r6
                r0 = r1
                goto L_0x00c6
            L_0x008e:
                int r0 = r1 + 1
                byte r1 = r2[r1]
                long r4 = (long) r1
                r1 = 49
                long r4 = r4 << r1
                long r4 = r4 ^ r6
                r6 = r4
                int r1 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
                if (r1 >= 0) goto L_0x00a3
                r4 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
                long r4 = r4 ^ r6
                goto L_0x00c6
            L_0x00a3:
                int r1 = r0 + 1
                byte r0 = r2[r0]
                long r4 = (long) r0
                r0 = 56
                long r4 = r4 << r0
                long r4 = r4 ^ r6
                r6 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
                long r4 = r4 ^ r6
                int r0 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
                if (r0 >= 0) goto L_0x00c5
                int r0 = r1 + 1
                byte r1 = r2[r1]
                long r6 = (long) r1
                int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r1 >= 0) goto L_0x00c6
            L_0x00c0:
                long r0 = r10.readRawVarint64SlowPath()
                return r0
            L_0x00c5:
                r0 = r1
            L_0x00c6:
                r10.pos = r0
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.ArrayDecoder.readRawVarint64():long");
        }

        /* access modifiers changed from: package-private */
        public long readRawVarint64SlowPath() throws IOException {
            long result = 0;
            for (int shift = 0; shift < 64; shift += 7) {
                byte b = readRawByte();
                result |= ((long) (b & Ascii.DEL)) << shift;
                if ((b & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                    return result;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public int readRawLittleEndian32() throws IOException {
            int tempPos = this.pos;
            if (this.limit - tempPos >= 4) {
                byte[] buffer2 = this.buffer;
                this.pos = tempPos + 4;
                return (buffer2[tempPos] & 255) | ((buffer2[tempPos + 1] & 255) << 8) | ((buffer2[tempPos + 2] & 255) << Ascii.DLE) | ((buffer2[tempPos + 3] & 255) << Ascii.CAN);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public long readRawLittleEndian64() throws IOException {
            int tempPos = this.pos;
            if (this.limit - tempPos >= 8) {
                byte[] buffer2 = this.buffer;
                this.pos = tempPos + 8;
                return (((long) buffer2[tempPos]) & 255) | ((((long) buffer2[tempPos + 1]) & 255) << 8) | ((((long) buffer2[tempPos + 2]) & 255) << 16) | ((((long) buffer2[tempPos + 3]) & 255) << 24) | ((((long) buffer2[tempPos + 4]) & 255) << 32) | ((((long) buffer2[tempPos + 5]) & 255) << 40) | ((((long) buffer2[tempPos + 6]) & 255) << 48) | ((((long) buffer2[tempPos + 7]) & 255) << 56);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public void enableAliasing(boolean enabled) {
            this.enableAliasing = enabled;
        }

        public void resetSizeCounter() {
            this.startPos = this.pos;
        }

        public int pushLimit(int byteLimit) throws InvalidProtocolBufferException {
            if (byteLimit >= 0) {
                int byteLimit2 = byteLimit + getTotalBytesRead();
                if (byteLimit2 >= 0) {
                    int oldLimit = this.currentLimit;
                    if (byteLimit2 <= oldLimit) {
                        this.currentLimit = byteLimit2;
                        recomputeBufferSizeAfterLimit();
                        return oldLimit;
                    }
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                throw InvalidProtocolBufferException.parseFailure();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        private void recomputeBufferSizeAfterLimit() {
            int i = this.limit + this.bufferSizeAfterLimit;
            this.limit = i;
            int bufferEnd = i - this.startPos;
            int i2 = this.currentLimit;
            if (bufferEnd > i2) {
                int i3 = bufferEnd - i2;
                this.bufferSizeAfterLimit = i3;
                this.limit = i - i3;
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        public void popLimit(int oldLimit) {
            this.currentLimit = oldLimit;
            recomputeBufferSizeAfterLimit();
        }

        public int getBytesUntilLimit() {
            int i = this.currentLimit;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - getTotalBytesRead();
        }

        public boolean isAtEnd() throws IOException {
            return this.pos == this.limit;
        }

        public int getTotalBytesRead() {
            return this.pos - this.startPos;
        }

        public byte readRawByte() throws IOException {
            int i = this.pos;
            if (i != this.limit) {
                byte[] bArr = this.buffer;
                this.pos = i + 1;
                return bArr[i];
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public byte[] readRawBytes(int length) throws IOException {
            if (length > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (length <= i - i2) {
                    int tempPos = this.pos;
                    int i3 = i2 + length;
                    this.pos = i3;
                    return Arrays.copyOfRange(this.buffer, tempPos, i3);
                }
            }
            if (length > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (length == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            } else {
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        public void skipRawBytes(int length) throws IOException {
            if (length >= 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (length <= i - i2) {
                    this.pos = i2 + length;
                    return;
                }
            }
            if (length < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }

    private static final class UnsafeDirectNioDecoder extends CodedInputStream {
        private final long address;
        private final ByteBuffer buffer;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private boolean enableAliasing;
        private final boolean immutable;
        private int lastTag;
        private long limit;
        private long pos;
        private long startPos;

        static boolean isSupported() {
            return UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        private UnsafeDirectNioDecoder(ByteBuffer buffer2, boolean immutable2) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.buffer = buffer2;
            long addressOffset = UnsafeUtil.addressOffset(buffer2);
            this.address = addressOffset;
            this.limit = ((long) buffer2.limit()) + addressOffset;
            long position = addressOffset + ((long) buffer2.position());
            this.pos = position;
            this.startPos = position;
            this.immutable = immutable2;
        }

        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            int readRawVarint32 = readRawVarint32();
            this.lastTag = readRawVarint32;
            if (WireFormat.getTagFieldNumber(readRawVarint32) != 0) {
                return this.lastTag;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        public void checkLastTagWas(int value) throws InvalidProtocolBufferException {
            if (this.lastTag != value) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public int getLastTag() {
            return this.lastTag;
        }

        public boolean skipField(int tag) throws IOException {
            switch (WireFormat.getTagWireType(tag)) {
                case 0:
                    skipRawVarint();
                    return true;
                case 1:
                    skipRawBytes(8);
                    return true;
                case 2:
                    skipRawBytes(readRawVarint32());
                    return true;
                case 3:
                    skipMessage();
                    checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(tag), 4));
                    return true;
                case 4:
                    return false;
                case 5:
                    skipRawBytes(4);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public boolean skipField(int tag, CodedOutputStream output) throws IOException {
            switch (WireFormat.getTagWireType(tag)) {
                case 0:
                    long value = readInt64();
                    output.writeRawVarint32(tag);
                    output.writeUInt64NoTag(value);
                    return true;
                case 1:
                    long value2 = readRawLittleEndian64();
                    output.writeRawVarint32(tag);
                    output.writeFixed64NoTag(value2);
                    return true;
                case 2:
                    ByteString value3 = readBytes();
                    output.writeRawVarint32(tag);
                    output.writeBytesNoTag(value3);
                    return true;
                case 3:
                    output.writeRawVarint32(tag);
                    skipMessage(output);
                    int endtag = WireFormat.makeTag(WireFormat.getTagFieldNumber(tag), 4);
                    checkLastTagWas(endtag);
                    output.writeRawVarint32(endtag);
                    return true;
                case 4:
                    return false;
                case 5:
                    int value4 = readRawLittleEndian32();
                    output.writeRawVarint32(tag);
                    output.writeFixed32NoTag(value4);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        public void skipMessage() throws java.io.IOException {
            /*
                r2 = this;
            L_0x0000:
                int r0 = r2.readTag()
                if (r0 == 0) goto L_0x000e
                boolean r1 = r2.skipField(r0)
                if (r1 != 0) goto L_0x000d
                goto L_0x000e
            L_0x000d:
                goto L_0x0000
            L_0x000e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.UnsafeDirectNioDecoder.skipMessage():void");
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        public void skipMessage(com.google.protobuf.CodedOutputStream r3) throws java.io.IOException {
            /*
                r2 = this;
            L_0x0000:
                int r0 = r2.readTag()
                if (r0 == 0) goto L_0x000e
                boolean r1 = r2.skipField(r0, r3)
                if (r1 != 0) goto L_0x000d
                goto L_0x000e
            L_0x000d:
                goto L_0x0000
            L_0x000e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.UnsafeDirectNioDecoder.skipMessage(com.google.protobuf.CodedOutputStream):void");
        }

        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        public String readString() throws IOException {
            int size = readRawVarint32();
            if (size > 0 && size <= remaining()) {
                byte[] bytes = new byte[size];
                UnsafeUtil.copyMemory(this.pos, bytes, 0, (long) size);
                String result = new String(bytes, Internal.UTF_8);
                this.pos += (long) size;
                return result;
            } else if (size == 0) {
                return "";
            } else {
                if (size < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public String readStringRequireUtf8() throws IOException {
            int size = readRawVarint32();
            if (size > 0 && size <= remaining()) {
                String result = Utf8.decodeUtf8(this.buffer, bufferPos(this.pos), size);
                this.pos += (long) size;
                return result;
            } else if (size == 0) {
                return "";
            } else {
                if (size <= 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public void readGroup(int fieldNumber, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistry) throws IOException {
            checkRecursionLimit();
            this.recursionDepth++;
            builder.mergeFrom((CodedInputStream) this, extensionRegistry);
            checkLastTagWas(WireFormat.makeTag(fieldNumber, 4));
            this.recursionDepth--;
        }

        public <T extends MessageLite> T readGroup(int fieldNumber, Parser<T> parser, ExtensionRegistryLite extensionRegistry) throws IOException {
            checkRecursionLimit();
            this.recursionDepth++;
            T result = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistry);
            checkLastTagWas(WireFormat.makeTag(fieldNumber, 4));
            this.recursionDepth--;
            return result;
        }

        @Deprecated
        public void readUnknownGroup(int fieldNumber, MessageLite.Builder builder) throws IOException {
            readGroup(fieldNumber, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistry) throws IOException {
            int length = readRawVarint32();
            checkRecursionLimit();
            int oldLimit = pushLimit(length);
            this.recursionDepth++;
            builder.mergeFrom((CodedInputStream) this, extensionRegistry);
            checkLastTagWas(0);
            this.recursionDepth--;
            if (getBytesUntilLimit() == 0) {
                popLimit(oldLimit);
                return;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistry) throws IOException {
            int length = readRawVarint32();
            checkRecursionLimit();
            int oldLimit = pushLimit(length);
            this.recursionDepth++;
            T result = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistry);
            checkLastTagWas(0);
            this.recursionDepth--;
            if (getBytesUntilLimit() == 0) {
                popLimit(oldLimit);
                return result;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public ByteString readBytes() throws IOException {
            int size = readRawVarint32();
            if (size <= 0 || size > remaining()) {
                if (size == 0) {
                    return ByteString.EMPTY;
                }
                if (size < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (!this.immutable || !this.enableAliasing) {
                byte[] bytes = new byte[size];
                UnsafeUtil.copyMemory(this.pos, bytes, 0, (long) size);
                this.pos += (long) size;
                return ByteString.wrap(bytes);
            } else {
                long j = this.pos;
                ByteBuffer result = slice(j, ((long) size) + j);
                this.pos += (long) size;
                return ByteString.wrap(result);
            }
        }

        public byte[] readByteArray() throws IOException {
            return readRawBytes(readRawVarint32());
        }

        public ByteBuffer readByteBuffer() throws IOException {
            int size = readRawVarint32();
            if (size <= 0 || size > remaining()) {
                if (size == 0) {
                    return Internal.EMPTY_BYTE_BUFFER;
                }
                if (size < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (this.immutable || !this.enableAliasing) {
                byte[] bytes = new byte[size];
                UnsafeUtil.copyMemory(this.pos, bytes, 0, (long) size);
                this.pos += (long) size;
                return ByteBuffer.wrap(bytes);
            } else {
                long j = this.pos;
                ByteBuffer result = slice(j, ((long) size) + j);
                this.pos += (long) size;
                return result;
            }
        }

        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readSInt32() throws IOException {
            return decodeZigZag32(readRawVarint32());
        }

        public long readSInt64() throws IOException {
            return decodeZigZag64(readRawVarint64());
        }

        public int readRawVarint32() throws IOException {
            int y;
            long tempPos = this.pos;
            if (this.limit != tempPos) {
                long tempPos2 = tempPos + 1;
                int i = UnsafeUtil.getByte(tempPos);
                int x = i;
                if (i >= 0) {
                    this.pos = tempPos2;
                    return x;
                } else if (this.limit - tempPos2 >= 9) {
                    long tempPos3 = tempPos2 + 1;
                    int i2 = (UnsafeUtil.getByte(tempPos2) << 7) ^ x;
                    int x2 = i2;
                    if (i2 < 0) {
                        y = x2 ^ -128;
                    } else {
                        long tempPos4 = tempPos3 + 1;
                        int i3 = (UnsafeUtil.getByte(tempPos3) << Ascii.f63SO) ^ x2;
                        int x3 = i3;
                        if (i3 >= 0) {
                            y = x3 ^ 16256;
                            tempPos3 = tempPos4;
                        } else {
                            tempPos3 = tempPos4 + 1;
                            int i4 = (UnsafeUtil.getByte(tempPos4) << Ascii.NAK) ^ x3;
                            int x4 = i4;
                            if (i4 < 0) {
                                y = -2080896 ^ x4;
                            } else {
                                long tempPos5 = tempPos3 + 1;
                                int y2 = UnsafeUtil.getByte(tempPos3);
                                int x5 = (x4 ^ (y2 << 28)) ^ 266354560;
                                if (y2 < 0) {
                                    tempPos3 = tempPos5 + 1;
                                    if (UnsafeUtil.getByte(tempPos5) < 0) {
                                        long tempPos6 = tempPos3 + 1;
                                        if (UnsafeUtil.getByte(tempPos3) < 0) {
                                            tempPos3 = tempPos6 + 1;
                                            if (UnsafeUtil.getByte(tempPos6) < 0) {
                                                long tempPos7 = tempPos3 + 1;
                                                if (UnsafeUtil.getByte(tempPos3) < 0) {
                                                    tempPos3 = tempPos7 + 1;
                                                    if (UnsafeUtil.getByte(tempPos7) >= 0) {
                                                        y = x5;
                                                    }
                                                } else {
                                                    y = x5;
                                                    tempPos3 = tempPos7;
                                                }
                                            } else {
                                                y = x5;
                                            }
                                        } else {
                                            y = x5;
                                            tempPos3 = tempPos6;
                                        }
                                    } else {
                                        y = x5;
                                    }
                                } else {
                                    y = x5;
                                    tempPos3 = tempPos5;
                                }
                            }
                        }
                    }
                    this.pos = tempPos3;
                    return y;
                }
            }
            return (int) readRawVarint64SlowPath();
        }

        private void skipRawVarint() throws IOException {
            if (remaining() >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws IOException {
            int i = 0;
            while (i < 10) {
                long j = this.pos;
                this.pos = 1 + j;
                if (UnsafeUtil.getByte(j) < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws IOException {
            int i = 0;
            while (i < 10) {
                if (readRawByte() < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public long readRawVarint64() throws IOException {
            long x;
            long tempPos = this.pos;
            if (this.limit != tempPos) {
                long tempPos2 = tempPos + 1;
                int i = UnsafeUtil.getByte(tempPos);
                int y = i;
                if (i >= 0) {
                    this.pos = tempPos2;
                    return (long) y;
                } else if (this.limit - tempPos2 >= 9) {
                    long tempPos3 = tempPos2 + 1;
                    int i2 = (UnsafeUtil.getByte(tempPos2) << 7) ^ y;
                    int y2 = i2;
                    if (i2 < 0) {
                        x = (long) (y2 ^ -128);
                    } else {
                        long tempPos4 = tempPos3 + 1;
                        int i3 = (UnsafeUtil.getByte(tempPos3) << Ascii.f63SO) ^ y2;
                        int y3 = i3;
                        if (i3 >= 0) {
                            x = (long) (y3 ^ 16256);
                            tempPos3 = tempPos4;
                        } else {
                            tempPos3 = tempPos4 + 1;
                            int i4 = (UnsafeUtil.getByte(tempPos4) << Ascii.NAK) ^ y3;
                            int y4 = i4;
                            if (i4 < 0) {
                                x = (long) (-2080896 ^ y4);
                            } else {
                                long tempPos5 = tempPos3 + 1;
                                long j = ((long) y4) ^ (((long) UnsafeUtil.getByte(tempPos3)) << 28);
                                long x2 = j;
                                if (j >= 0) {
                                    x = 266354560 ^ x2;
                                    tempPos3 = tempPos5;
                                } else {
                                    long tempPos6 = tempPos5 + 1;
                                    long j2 = (((long) UnsafeUtil.getByte(tempPos5)) << 35) ^ x2;
                                    long x3 = j2;
                                    if (j2 < 0) {
                                        x = -34093383808L ^ x3;
                                        tempPos3 = tempPos6;
                                    } else {
                                        long tempPos7 = tempPos6 + 1;
                                        long j3 = (((long) UnsafeUtil.getByte(tempPos6)) << 42) ^ x3;
                                        long x4 = j3;
                                        if (j3 >= 0) {
                                            x = 4363953127296L ^ x4;
                                            tempPos3 = tempPos7;
                                        } else {
                                            long tempPos8 = tempPos7 + 1;
                                            long j4 = (((long) UnsafeUtil.getByte(tempPos7)) << 49) ^ x4;
                                            long x5 = j4;
                                            if (j4 < 0) {
                                                x = -558586000294016L ^ x5;
                                                tempPos3 = tempPos8;
                                            } else {
                                                long tempPos9 = tempPos8 + 1;
                                                long x6 = ((((long) UnsafeUtil.getByte(tempPos8)) << 56) ^ x5) ^ 71499008037633920L;
                                                if (x6 < 0) {
                                                    tempPos3 = tempPos9 + 1;
                                                    if (((long) UnsafeUtil.getByte(tempPos9)) >= 0) {
                                                        x = x6;
                                                    }
                                                } else {
                                                    x = x6;
                                                    tempPos3 = tempPos9;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    this.pos = tempPos3;
                    return x;
                }
            }
            return readRawVarint64SlowPath();
        }

        /* access modifiers changed from: package-private */
        public long readRawVarint64SlowPath() throws IOException {
            long result = 0;
            for (int shift = 0; shift < 64; shift += 7) {
                byte b = readRawByte();
                result |= ((long) (b & Ascii.DEL)) << shift;
                if ((b & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                    return result;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public int readRawLittleEndian32() throws IOException {
            long tempPos = this.pos;
            if (this.limit - tempPos >= 4) {
                this.pos = 4 + tempPos;
                return (UnsafeUtil.getByte(tempPos) & 255) | ((UnsafeUtil.getByte(1 + tempPos) & 255) << 8) | ((UnsafeUtil.getByte(2 + tempPos) & 255) << Ascii.DLE) | ((UnsafeUtil.getByte(3 + tempPos) & 255) << Ascii.CAN);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public long readRawLittleEndian64() throws IOException {
            long tempPos = this.pos;
            if (this.limit - tempPos >= 8) {
                this.pos = 8 + tempPos;
                return (((long) UnsafeUtil.getByte(tempPos)) & 255) | ((((long) UnsafeUtil.getByte(1 + tempPos)) & 255) << 8) | ((((long) UnsafeUtil.getByte(2 + tempPos)) & 255) << 16) | ((((long) UnsafeUtil.getByte(3 + tempPos)) & 255) << 24) | ((((long) UnsafeUtil.getByte(4 + tempPos)) & 255) << 32) | ((((long) UnsafeUtil.getByte(5 + tempPos)) & 255) << 40) | ((((long) UnsafeUtil.getByte(6 + tempPos)) & 255) << 48) | ((255 & ((long) UnsafeUtil.getByte(7 + tempPos))) << 56);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public void enableAliasing(boolean enabled) {
            this.enableAliasing = enabled;
        }

        public void resetSizeCounter() {
            this.startPos = this.pos;
        }

        public int pushLimit(int byteLimit) throws InvalidProtocolBufferException {
            if (byteLimit >= 0) {
                int byteLimit2 = byteLimit + getTotalBytesRead();
                int oldLimit = this.currentLimit;
                if (byteLimit2 <= oldLimit) {
                    this.currentLimit = byteLimit2;
                    recomputeBufferSizeAfterLimit();
                    return oldLimit;
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        public void popLimit(int oldLimit) {
            this.currentLimit = oldLimit;
            recomputeBufferSizeAfterLimit();
        }

        public int getBytesUntilLimit() {
            int i = this.currentLimit;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - getTotalBytesRead();
        }

        public boolean isAtEnd() throws IOException {
            return this.pos == this.limit;
        }

        public int getTotalBytesRead() {
            return (int) (this.pos - this.startPos);
        }

        public byte readRawByte() throws IOException {
            long j = this.pos;
            if (j != this.limit) {
                this.pos = 1 + j;
                return UnsafeUtil.getByte(j);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public byte[] readRawBytes(int length) throws IOException {
            if (length >= 0 && length <= remaining()) {
                byte[] bytes = new byte[length];
                long j = this.pos;
                slice(j, ((long) length) + j).get(bytes);
                this.pos += (long) length;
                return bytes;
            } else if (length > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (length == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            } else {
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        public void skipRawBytes(int length) throws IOException {
            if (length >= 0 && length <= remaining()) {
                this.pos += (long) length;
            } else if (length < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        private void recomputeBufferSizeAfterLimit() {
            long j = this.limit + ((long) this.bufferSizeAfterLimit);
            this.limit = j;
            int bufferEnd = (int) (j - this.startPos);
            int i = this.currentLimit;
            if (bufferEnd > i) {
                int i2 = bufferEnd - i;
                this.bufferSizeAfterLimit = i2;
                this.limit = j - ((long) i2);
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        private int remaining() {
            return (int) (this.limit - this.pos);
        }

        private int bufferPos(long pos2) {
            return (int) (pos2 - this.address);
        }

        private ByteBuffer slice(long begin, long end) throws IOException {
            int prevPos = this.buffer.position();
            int prevLimit = this.buffer.limit();
            Buffer asBuffer = this.buffer;
            try {
                asBuffer.position(bufferPos(begin));
                asBuffer.limit(bufferPos(end));
                ByteBuffer slice = this.buffer.slice();
                asBuffer.position(prevPos);
                asBuffer.limit(prevLimit);
                return slice;
            } catch (IllegalArgumentException e) {
                InvalidProtocolBufferException ex = InvalidProtocolBufferException.truncatedMessage();
                ex.initCause(e);
                throw ex;
            } catch (Throwable th) {
                asBuffer.position(prevPos);
                asBuffer.limit(prevLimit);
                throw th;
            }
        }
    }

    private static final class StreamDecoder extends CodedInputStream {
        /* access modifiers changed from: private */
        public final byte[] buffer;
        private int bufferSize;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private final InputStream input;
        private int lastTag;
        /* access modifiers changed from: private */
        public int pos;
        private RefillCallback refillCallback;
        private int totalBytesRetired;

        private interface RefillCallback {
            void onRefill();
        }

        private StreamDecoder(InputStream input2, int bufferSize2) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.refillCallback = null;
            Internal.checkNotNull(input2, "input");
            this.input = input2;
            this.buffer = new byte[bufferSize2];
            this.bufferSize = 0;
            this.pos = 0;
            this.totalBytesRetired = 0;
        }

        private static int read(InputStream input2, byte[] data, int offset, int length) throws IOException {
            try {
                return input2.read(data, offset, length);
            } catch (InvalidProtocolBufferException e) {
                e.setThrownFromInputStream();
                throw e;
            }
        }

        private static long skip(InputStream input2, long length) throws IOException {
            try {
                return input2.skip(length);
            } catch (InvalidProtocolBufferException e) {
                e.setThrownFromInputStream();
                throw e;
            }
        }

        private static int available(InputStream input2) throws IOException {
            try {
                return input2.available();
            } catch (InvalidProtocolBufferException e) {
                e.setThrownFromInputStream();
                throw e;
            }
        }

        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            int readRawVarint32 = readRawVarint32();
            this.lastTag = readRawVarint32;
            if (WireFormat.getTagFieldNumber(readRawVarint32) != 0) {
                return this.lastTag;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        public void checkLastTagWas(int value) throws InvalidProtocolBufferException {
            if (this.lastTag != value) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public int getLastTag() {
            return this.lastTag;
        }

        public boolean skipField(int tag) throws IOException {
            switch (WireFormat.getTagWireType(tag)) {
                case 0:
                    skipRawVarint();
                    return true;
                case 1:
                    skipRawBytes(8);
                    return true;
                case 2:
                    skipRawBytes(readRawVarint32());
                    return true;
                case 3:
                    skipMessage();
                    checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(tag), 4));
                    return true;
                case 4:
                    return false;
                case 5:
                    skipRawBytes(4);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public boolean skipField(int tag, CodedOutputStream output) throws IOException {
            switch (WireFormat.getTagWireType(tag)) {
                case 0:
                    long value = readInt64();
                    output.writeRawVarint32(tag);
                    output.writeUInt64NoTag(value);
                    return true;
                case 1:
                    long value2 = readRawLittleEndian64();
                    output.writeRawVarint32(tag);
                    output.writeFixed64NoTag(value2);
                    return true;
                case 2:
                    ByteString value3 = readBytes();
                    output.writeRawVarint32(tag);
                    output.writeBytesNoTag(value3);
                    return true;
                case 3:
                    output.writeRawVarint32(tag);
                    skipMessage(output);
                    int endtag = WireFormat.makeTag(WireFormat.getTagFieldNumber(tag), 4);
                    checkLastTagWas(endtag);
                    output.writeRawVarint32(endtag);
                    return true;
                case 4:
                    return false;
                case 5:
                    int value4 = readRawLittleEndian32();
                    output.writeRawVarint32(tag);
                    output.writeFixed32NoTag(value4);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        public void skipMessage() throws java.io.IOException {
            /*
                r2 = this;
            L_0x0000:
                int r0 = r2.readTag()
                if (r0 == 0) goto L_0x000e
                boolean r1 = r2.skipField(r0)
                if (r1 != 0) goto L_0x000d
                goto L_0x000e
            L_0x000d:
                goto L_0x0000
            L_0x000e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.StreamDecoder.skipMessage():void");
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        public void skipMessage(com.google.protobuf.CodedOutputStream r3) throws java.io.IOException {
            /*
                r2 = this;
            L_0x0000:
                int r0 = r2.readTag()
                if (r0 == 0) goto L_0x000e
                boolean r1 = r2.skipField(r0, r3)
                if (r1 != 0) goto L_0x000d
                goto L_0x000e
            L_0x000d:
                goto L_0x0000
            L_0x000e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.StreamDecoder.skipMessage(com.google.protobuf.CodedOutputStream):void");
        }

        private class SkippedDataSink implements RefillCallback {
            private ByteArrayOutputStream byteArrayStream;
            private int lastPos;

            private SkippedDataSink() {
                this.lastPos = StreamDecoder.this.pos;
            }

            public void onRefill() {
                if (this.byteArrayStream == null) {
                    this.byteArrayStream = new ByteArrayOutputStream();
                }
                this.byteArrayStream.write(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos - this.lastPos);
                this.lastPos = 0;
            }

            /* access modifiers changed from: package-private */
            public ByteBuffer getSkippedData() {
                ByteArrayOutputStream byteArrayOutputStream = this.byteArrayStream;
                if (byteArrayOutputStream == null) {
                    return ByteBuffer.wrap(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos - this.lastPos);
                }
                byteArrayOutputStream.write(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos);
                return ByteBuffer.wrap(this.byteArrayStream.toByteArray());
            }
        }

        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        public String readString() throws IOException {
            int size = readRawVarint32();
            if (size > 0 && size <= this.bufferSize - this.pos) {
                String result = new String(this.buffer, this.pos, size, Internal.UTF_8);
                this.pos += size;
                return result;
            } else if (size == 0) {
                return "";
            } else {
                if (size > this.bufferSize) {
                    return new String(readRawBytesSlowPath(size, false), Internal.UTF_8);
                }
                refillBuffer(size);
                String result2 = new String(this.buffer, this.pos, size, Internal.UTF_8);
                this.pos += size;
                return result2;
            }
        }

        public String readStringRequireUtf8() throws IOException {
            int tempPos;
            byte[] bytes;
            int size = readRawVarint32();
            int oldPos = this.pos;
            int i = this.bufferSize;
            if (size <= i - oldPos && size > 0) {
                bytes = this.buffer;
                this.pos = oldPos + size;
                tempPos = oldPos;
            } else if (size == 0) {
                return "";
            } else {
                if (size <= i) {
                    refillBuffer(size);
                    bytes = this.buffer;
                    tempPos = 0;
                    this.pos = 0 + size;
                } else {
                    bytes = readRawBytesSlowPath(size, false);
                    tempPos = 0;
                }
            }
            return Utf8.decodeUtf8(bytes, tempPos, size);
        }

        public void readGroup(int fieldNumber, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistry) throws IOException {
            checkRecursionLimit();
            this.recursionDepth++;
            builder.mergeFrom((CodedInputStream) this, extensionRegistry);
            checkLastTagWas(WireFormat.makeTag(fieldNumber, 4));
            this.recursionDepth--;
        }

        public <T extends MessageLite> T readGroup(int fieldNumber, Parser<T> parser, ExtensionRegistryLite extensionRegistry) throws IOException {
            checkRecursionLimit();
            this.recursionDepth++;
            T result = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistry);
            checkLastTagWas(WireFormat.makeTag(fieldNumber, 4));
            this.recursionDepth--;
            return result;
        }

        @Deprecated
        public void readUnknownGroup(int fieldNumber, MessageLite.Builder builder) throws IOException {
            readGroup(fieldNumber, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistry) throws IOException {
            int length = readRawVarint32();
            checkRecursionLimit();
            int oldLimit = pushLimit(length);
            this.recursionDepth++;
            builder.mergeFrom((CodedInputStream) this, extensionRegistry);
            checkLastTagWas(0);
            this.recursionDepth--;
            if (getBytesUntilLimit() == 0) {
                popLimit(oldLimit);
                return;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistry) throws IOException {
            int length = readRawVarint32();
            checkRecursionLimit();
            int oldLimit = pushLimit(length);
            this.recursionDepth++;
            T result = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistry);
            checkLastTagWas(0);
            this.recursionDepth--;
            if (getBytesUntilLimit() == 0) {
                popLimit(oldLimit);
                return result;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public ByteString readBytes() throws IOException {
            int size = readRawVarint32();
            int i = this.bufferSize;
            int i2 = this.pos;
            if (size <= i - i2 && size > 0) {
                ByteString result = ByteString.copyFrom(this.buffer, i2, size);
                this.pos += size;
                return result;
            } else if (size == 0) {
                return ByteString.EMPTY;
            } else {
                return readBytesSlowPath(size);
            }
        }

        public byte[] readByteArray() throws IOException {
            int size = readRawVarint32();
            int i = this.bufferSize;
            int i2 = this.pos;
            if (size > i - i2 || size <= 0) {
                return readRawBytesSlowPath(size, false);
            }
            byte[] result = Arrays.copyOfRange(this.buffer, i2, i2 + size);
            this.pos += size;
            return result;
        }

        public ByteBuffer readByteBuffer() throws IOException {
            int size = readRawVarint32();
            int i = this.bufferSize;
            int i2 = this.pos;
            if (size <= i - i2 && size > 0) {
                ByteBuffer result = ByteBuffer.wrap(Arrays.copyOfRange(this.buffer, i2, i2 + size));
                this.pos += size;
                return result;
            } else if (size == 0) {
                return Internal.EMPTY_BYTE_BUFFER;
            } else {
                return ByteBuffer.wrap(readRawBytesSlowPath(size, true));
            }
        }

        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readSInt32() throws IOException {
            return decodeZigZag32(readRawVarint32());
        }

        public long readSInt64() throws IOException {
            return decodeZigZag64(readRawVarint64());
        }

        /* JADX WARNING: Code restructure failed: missing block: B:28:0x006f, code lost:
            if (r2[r1] < 0) goto L_0x0072;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int readRawVarint32() throws java.io.IOException {
            /*
                r6 = this;
                int r0 = r6.pos
                int r1 = r6.bufferSize
                if (r1 != r0) goto L_0x0008
                goto L_0x0072
            L_0x0008:
                byte[] r2 = r6.buffer
                int r3 = r0 + 1
                byte r0 = r2[r0]
                r4 = r0
                if (r0 < 0) goto L_0x0014
                r6.pos = r3
                return r4
            L_0x0014:
                int r1 = r1 - r3
                r0 = 9
                if (r1 >= r0) goto L_0x001a
                goto L_0x0072
            L_0x001a:
                int r0 = r3 + 1
                byte r1 = r2[r3]
                int r1 = r1 << 7
                r1 = r1 ^ r4
                r3 = r1
                if (r1 >= 0) goto L_0x0027
                r1 = r3 ^ -128(0xffffffffffffff80, float:NaN)
                goto L_0x007d
            L_0x0027:
                int r1 = r0 + 1
                byte r0 = r2[r0]
                int r0 = r0 << 14
                r0 = r0 ^ r3
                r3 = r0
                if (r0 < 0) goto L_0x0037
                r0 = r3 ^ 16256(0x3f80, float:2.278E-41)
                r5 = r1
                r1 = r0
                r0 = r5
                goto L_0x007d
            L_0x0037:
                int r0 = r1 + 1
                byte r1 = r2[r1]
                int r1 = r1 << 21
                r1 = r1 ^ r3
                r3 = r1
                if (r1 >= 0) goto L_0x0046
                r1 = -2080896(0xffffffffffe03f80, float:NaN)
                r1 = r1 ^ r3
                goto L_0x007d
            L_0x0046:
                int r1 = r0 + 1
                byte r0 = r2[r0]
                int r4 = r0 << 28
                r3 = r3 ^ r4
                r4 = 266354560(0xfe03f80, float:2.2112565E-29)
                r3 = r3 ^ r4
                if (r0 >= 0) goto L_0x007b
                int r4 = r1 + 1
                byte r1 = r2[r1]
                if (r1 >= 0) goto L_0x0078
                int r1 = r4 + 1
                byte r4 = r2[r4]
                if (r4 >= 0) goto L_0x007b
                int r4 = r1 + 1
                byte r1 = r2[r1]
                if (r1 >= 0) goto L_0x0078
                int r1 = r4 + 1
                byte r4 = r2[r4]
                if (r4 >= 0) goto L_0x007b
                int r4 = r1 + 1
                byte r1 = r2[r1]
                if (r1 >= 0) goto L_0x0078
            L_0x0072:
                long r0 = r6.readRawVarint64SlowPath()
                int r0 = (int) r0
                return r0
            L_0x0078:
                r1 = r3
                r0 = r4
                goto L_0x007d
            L_0x007b:
                r0 = r1
                r1 = r3
            L_0x007d:
                r6.pos = r0
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.StreamDecoder.readRawVarint32():int");
        }

        private void skipRawVarint() throws IOException {
            if (this.bufferSize - this.pos >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws IOException {
            int i = 0;
            while (i < 10) {
                byte[] bArr = this.buffer;
                int i2 = this.pos;
                this.pos = i2 + 1;
                if (bArr[i2] < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws IOException {
            int i = 0;
            while (i < 10) {
                if (readRawByte() < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:32:0x00bd, code lost:
            if (((long) r2[r1]) < 0) goto L_0x00c0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long readRawVarint64() throws java.io.IOException {
            /*
                r10 = this;
                int r0 = r10.pos
                int r1 = r10.bufferSize
                if (r1 != r0) goto L_0x0008
                goto L_0x00c0
            L_0x0008:
                byte[] r2 = r10.buffer
                int r3 = r0 + 1
                byte r0 = r2[r0]
                r4 = r0
                if (r0 < 0) goto L_0x0015
                r10.pos = r3
                long r0 = (long) r4
                return r0
            L_0x0015:
                int r1 = r1 - r3
                r0 = 9
                if (r1 >= r0) goto L_0x001c
                goto L_0x00c0
            L_0x001c:
                int r0 = r3 + 1
                byte r1 = r2[r3]
                int r1 = r1 << 7
                r1 = r1 ^ r4
                r3 = r1
                if (r1 >= 0) goto L_0x002b
                r1 = r3 ^ -128(0xffffffffffffff80, float:NaN)
                long r4 = (long) r1
                goto L_0x00c6
            L_0x002b:
                int r1 = r0 + 1
                byte r0 = r2[r0]
                int r0 = r0 << 14
                r0 = r0 ^ r3
                r3 = r0
                if (r0 < 0) goto L_0x003b
                r0 = r3 ^ 16256(0x3f80, float:2.278E-41)
                long r4 = (long) r0
                r0 = r1
                goto L_0x00c6
            L_0x003b:
                int r0 = r1 + 1
                byte r1 = r2[r1]
                int r1 = r1 << 21
                r1 = r1 ^ r3
                r3 = r1
                if (r1 >= 0) goto L_0x004c
                r1 = -2080896(0xffffffffffe03f80, float:NaN)
                r1 = r1 ^ r3
                long r4 = (long) r1
                goto L_0x00c6
            L_0x004c:
                long r4 = (long) r3
                int r1 = r0 + 1
                byte r0 = r2[r0]
                long r6 = (long) r0
                r0 = 28
                long r6 = r6 << r0
                long r4 = r4 ^ r6
                r6 = r4
                r8 = 0
                int r0 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
                if (r0 < 0) goto L_0x0063
                r4 = 266354560(0xfe03f80, double:1.315966377E-315)
                long r4 = r4 ^ r6
                r0 = r1
                goto L_0x00c6
            L_0x0063:
                int r0 = r1 + 1
                byte r1 = r2[r1]
                long r4 = (long) r1
                r1 = 35
                long r4 = r4 << r1
                long r4 = r4 ^ r6
                r6 = r4
                int r1 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
                if (r1 >= 0) goto L_0x0078
                r4 = -34093383808(0xfffffff80fe03f80, double:NaN)
                long r4 = r4 ^ r6
                goto L_0x00c6
            L_0x0078:
                int r1 = r0 + 1
                byte r0 = r2[r0]
                long r4 = (long) r0
                r0 = 42
                long r4 = r4 << r0
                long r4 = r4 ^ r6
                r6 = r4
                int r0 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
                if (r0 < 0) goto L_0x008e
                r4 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
                long r4 = r4 ^ r6
                r0 = r1
                goto L_0x00c6
            L_0x008e:
                int r0 = r1 + 1
                byte r1 = r2[r1]
                long r4 = (long) r1
                r1 = 49
                long r4 = r4 << r1
                long r4 = r4 ^ r6
                r6 = r4
                int r1 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
                if (r1 >= 0) goto L_0x00a3
                r4 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
                long r4 = r4 ^ r6
                goto L_0x00c6
            L_0x00a3:
                int r1 = r0 + 1
                byte r0 = r2[r0]
                long r4 = (long) r0
                r0 = 56
                long r4 = r4 << r0
                long r4 = r4 ^ r6
                r6 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
                long r4 = r4 ^ r6
                int r0 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
                if (r0 >= 0) goto L_0x00c5
                int r0 = r1 + 1
                byte r1 = r2[r1]
                long r6 = (long) r1
                int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r1 >= 0) goto L_0x00c6
            L_0x00c0:
                long r0 = r10.readRawVarint64SlowPath()
                return r0
            L_0x00c5:
                r0 = r1
            L_0x00c6:
                r10.pos = r0
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.StreamDecoder.readRawVarint64():long");
        }

        /* access modifiers changed from: package-private */
        public long readRawVarint64SlowPath() throws IOException {
            long result = 0;
            for (int shift = 0; shift < 64; shift += 7) {
                byte b = readRawByte();
                result |= ((long) (b & Ascii.DEL)) << shift;
                if ((b & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                    return result;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public int readRawLittleEndian32() throws IOException {
            int tempPos = this.pos;
            if (this.bufferSize - tempPos < 4) {
                refillBuffer(4);
                tempPos = this.pos;
            }
            byte[] buffer2 = this.buffer;
            this.pos = tempPos + 4;
            return (buffer2[tempPos] & 255) | ((buffer2[tempPos + 1] & 255) << 8) | ((buffer2[tempPos + 2] & 255) << Ascii.DLE) | ((buffer2[tempPos + 3] & 255) << Ascii.CAN);
        }

        public long readRawLittleEndian64() throws IOException {
            int tempPos = this.pos;
            if (this.bufferSize - tempPos < 8) {
                refillBuffer(8);
                tempPos = this.pos;
            }
            byte[] buffer2 = this.buffer;
            this.pos = tempPos + 8;
            return (((long) buffer2[tempPos]) & 255) | ((((long) buffer2[tempPos + 1]) & 255) << 8) | ((((long) buffer2[tempPos + 2]) & 255) << 16) | ((((long) buffer2[tempPos + 3]) & 255) << 24) | ((((long) buffer2[tempPos + 4]) & 255) << 32) | ((((long) buffer2[tempPos + 5]) & 255) << 40) | ((((long) buffer2[tempPos + 6]) & 255) << 48) | ((((long) buffer2[tempPos + 7]) & 255) << 56);
        }

        public void enableAliasing(boolean enabled) {
        }

        public void resetSizeCounter() {
            this.totalBytesRetired = -this.pos;
        }

        public int pushLimit(int byteLimit) throws InvalidProtocolBufferException {
            if (byteLimit >= 0) {
                int byteLimit2 = byteLimit + this.totalBytesRetired + this.pos;
                int oldLimit = this.currentLimit;
                if (byteLimit2 <= oldLimit) {
                    this.currentLimit = byteLimit2;
                    recomputeBufferSizeAfterLimit();
                    return oldLimit;
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        private void recomputeBufferSizeAfterLimit() {
            int i = this.bufferSize + this.bufferSizeAfterLimit;
            this.bufferSize = i;
            int bufferEnd = this.totalBytesRetired + i;
            int i2 = this.currentLimit;
            if (bufferEnd > i2) {
                int i3 = bufferEnd - i2;
                this.bufferSizeAfterLimit = i3;
                this.bufferSize = i - i3;
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        public void popLimit(int oldLimit) {
            this.currentLimit = oldLimit;
            recomputeBufferSizeAfterLimit();
        }

        public int getBytesUntilLimit() {
            int i = this.currentLimit;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - (this.totalBytesRetired + this.pos);
        }

        public boolean isAtEnd() throws IOException {
            return this.pos == this.bufferSize && !tryRefillBuffer(1);
        }

        public int getTotalBytesRead() {
            return this.totalBytesRetired + this.pos;
        }

        private void refillBuffer(int n) throws IOException {
            if (tryRefillBuffer(n)) {
                return;
            }
            if (n > (this.sizeLimit - this.totalBytesRetired) - this.pos) {
                throw InvalidProtocolBufferException.sizeLimitExceeded();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        private boolean tryRefillBuffer(int n) throws IOException {
            if (this.pos + n > this.bufferSize) {
                int i = this.sizeLimit;
                int i2 = this.totalBytesRetired;
                int i3 = this.pos;
                if (n > (i - i2) - i3 || i2 + i3 + n > this.currentLimit) {
                    return false;
                }
                RefillCallback refillCallback2 = this.refillCallback;
                if (refillCallback2 != null) {
                    refillCallback2.onRefill();
                }
                int tempPos = this.pos;
                if (tempPos > 0) {
                    int i4 = this.bufferSize;
                    if (i4 > tempPos) {
                        byte[] bArr = this.buffer;
                        System.arraycopy(bArr, tempPos, bArr, 0, i4 - tempPos);
                    }
                    this.totalBytesRetired += tempPos;
                    this.bufferSize -= tempPos;
                    this.pos = 0;
                }
                InputStream inputStream = this.input;
                byte[] bArr2 = this.buffer;
                int i5 = this.bufferSize;
                int bytesRead = read(inputStream, bArr2, i5, Math.min(bArr2.length - i5, (this.sizeLimit - this.totalBytesRetired) - this.bufferSize));
                if (bytesRead == 0 || bytesRead < -1 || bytesRead > this.buffer.length) {
                    throw new IllegalStateException(this.input.getClass() + "#read(byte[]) returned invalid result: " + bytesRead + "\nThe InputStream implementation is buggy.");
                } else if (bytesRead <= 0) {
                    return false;
                } else {
                    this.bufferSize += bytesRead;
                    recomputeBufferSizeAfterLimit();
                    if (this.bufferSize >= n) {
                        return true;
                    }
                    return tryRefillBuffer(n);
                }
            } else {
                throw new IllegalStateException("refillBuffer() called when " + n + " bytes were already available in buffer");
            }
        }

        public byte readRawByte() throws IOException {
            if (this.pos == this.bufferSize) {
                refillBuffer(1);
            }
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i + 1;
            return bArr[i];
        }

        public byte[] readRawBytes(int size) throws IOException {
            int tempPos = this.pos;
            if (size > this.bufferSize - tempPos || size <= 0) {
                return readRawBytesSlowPath(size, false);
            }
            this.pos = tempPos + size;
            return Arrays.copyOfRange(this.buffer, tempPos, tempPos + size);
        }

        private byte[] readRawBytesSlowPath(int size, boolean ensureNoLeakedReferences) throws IOException {
            byte[] result = readRawBytesSlowPathOneChunk(size);
            if (result != null) {
                return ensureNoLeakedReferences ? (byte[]) result.clone() : result;
            }
            int originalBufferPos = this.pos;
            int i = this.bufferSize;
            int bufferedBytes = i - this.pos;
            this.totalBytesRetired += i;
            this.pos = 0;
            this.bufferSize = 0;
            List<byte[]> chunks = readRawBytesSlowPathRemainingChunks(size - bufferedBytes);
            byte[] bytes = new byte[size];
            System.arraycopy(this.buffer, originalBufferPos, bytes, 0, bufferedBytes);
            int tempPos = bufferedBytes;
            for (byte[] chunk : chunks) {
                System.arraycopy(chunk, 0, bytes, tempPos, chunk.length);
                tempPos += chunk.length;
            }
            return bytes;
        }

        private byte[] readRawBytesSlowPathOneChunk(int size) throws IOException {
            if (size == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            }
            if (size >= 0) {
                int currentMessageSize = this.totalBytesRetired + this.pos + size;
                if (currentMessageSize - this.sizeLimit <= 0) {
                    int i = this.currentLimit;
                    if (currentMessageSize <= i) {
                        int bufferedBytes = this.bufferSize - this.pos;
                        int sizeLeft = size - bufferedBytes;
                        if (sizeLeft >= 4096 && sizeLeft > available(this.input)) {
                            return null;
                        }
                        byte[] bytes = new byte[size];
                        System.arraycopy(this.buffer, this.pos, bytes, 0, bufferedBytes);
                        this.totalBytesRetired += this.bufferSize;
                        this.pos = 0;
                        this.bufferSize = 0;
                        int tempPos = bufferedBytes;
                        while (tempPos < bytes.length) {
                            int n = read(this.input, bytes, tempPos, size - tempPos);
                            if (n != -1) {
                                this.totalBytesRetired += n;
                                tempPos += n;
                            } else {
                                throw InvalidProtocolBufferException.truncatedMessage();
                            }
                        }
                        return bytes;
                    }
                    skipRawBytes((i - this.totalBytesRetired) - this.pos);
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                throw InvalidProtocolBufferException.sizeLimitExceeded();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        private List<byte[]> readRawBytesSlowPathRemainingChunks(int sizeLeft) throws IOException {
            List<byte[]> chunks = new ArrayList<>();
            while (sizeLeft > 0) {
                byte[] chunk = new byte[Math.min(sizeLeft, 4096)];
                int tempPos = 0;
                while (tempPos < chunk.length) {
                    int n = this.input.read(chunk, tempPos, chunk.length - tempPos);
                    if (n != -1) {
                        this.totalBytesRetired += n;
                        tempPos += n;
                    } else {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                }
                sizeLeft -= chunk.length;
                chunks.add(chunk);
            }
            return chunks;
        }

        private ByteString readBytesSlowPath(int size) throws IOException {
            byte[] result = readRawBytesSlowPathOneChunk(size);
            if (result != null) {
                return ByteString.copyFrom(result);
            }
            int originalBufferPos = this.pos;
            int i = this.bufferSize;
            int bufferedBytes = i - this.pos;
            this.totalBytesRetired += i;
            this.pos = 0;
            this.bufferSize = 0;
            List<byte[]> chunks = readRawBytesSlowPathRemainingChunks(size - bufferedBytes);
            byte[] bytes = new byte[size];
            System.arraycopy(this.buffer, originalBufferPos, bytes, 0, bufferedBytes);
            int tempPos = bufferedBytes;
            for (byte[] chunk : chunks) {
                System.arraycopy(chunk, 0, bytes, tempPos, chunk.length);
                tempPos += chunk.length;
            }
            return ByteString.wrap(bytes);
        }

        public void skipRawBytes(int size) throws IOException {
            int i = this.bufferSize;
            int i2 = this.pos;
            if (size > i - i2 || size < 0) {
                skipRawBytesSlowPath(size);
            } else {
                this.pos = i2 + size;
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0064, code lost:
            throw new java.lang.IllegalStateException(r9.input.getClass() + "#skip returned invalid result: " + r3 + "\nThe InputStream implementation is buggy.");
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void skipRawBytesSlowPath(int r10) throws java.io.IOException {
            /*
                r9 = this;
                if (r10 < 0) goto L_0x00a1
                int r0 = r9.totalBytesRetired
                int r1 = r9.pos
                int r2 = r0 + r1
                int r2 = r2 + r10
                int r3 = r9.currentLimit
                if (r2 > r3) goto L_0x0097
                r2 = 0
                com.google.protobuf.CodedInputStream$StreamDecoder$RefillCallback r3 = r9.refillCallback
                if (r3 != 0) goto L_0x0077
                int r0 = r0 + r1
                r9.totalBytesRetired = r0
                int r0 = r9.bufferSize
                int r0 = r0 - r1
                r1 = 0
                r9.bufferSize = r1
                r9.pos = r1
                r2 = r0
            L_0x001e:
                if (r2 >= r10) goto L_0x006f
                int r0 = r10 - r2
                java.io.InputStream r1 = r9.input     // Catch:{ all -> 0x0065 }
                long r3 = (long) r0     // Catch:{ all -> 0x0065 }
                long r3 = skip(r1, r3)     // Catch:{ all -> 0x0065 }
                r5 = 0
                int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r1 < 0) goto L_0x003c
                long r7 = (long) r0     // Catch:{ all -> 0x0065 }
                int r1 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
                if (r1 > 0) goto L_0x003c
                int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r1 != 0) goto L_0x0039
                goto L_0x006f
            L_0x0039:
                int r1 = (int) r3     // Catch:{ all -> 0x0065 }
                int r2 = r2 + r1
                goto L_0x001e
            L_0x003c:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0065 }
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0065 }
                r5.<init>()     // Catch:{ all -> 0x0065 }
                java.io.InputStream r6 = r9.input     // Catch:{ all -> 0x0065 }
                java.lang.Class r6 = r6.getClass()     // Catch:{ all -> 0x0065 }
                java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x0065 }
                java.lang.String r6 = "#skip returned invalid result: "
                java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x0065 }
                java.lang.StringBuilder r5 = r5.append(r3)     // Catch:{ all -> 0x0065 }
                java.lang.String r6 = "\nThe InputStream implementation is buggy."
                java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x0065 }
                java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0065 }
                r1.<init>(r5)     // Catch:{ all -> 0x0065 }
                throw r1     // Catch:{ all -> 0x0065 }
            L_0x0065:
                r0 = move-exception
                int r1 = r9.totalBytesRetired
                int r1 = r1 + r2
                r9.totalBytesRetired = r1
                r9.recomputeBufferSizeAfterLimit()
                throw r0
            L_0x006f:
                int r0 = r9.totalBytesRetired
                int r0 = r0 + r2
                r9.totalBytesRetired = r0
                r9.recomputeBufferSizeAfterLimit()
            L_0x0077:
                if (r2 >= r10) goto L_0x0096
                int r0 = r9.bufferSize
                int r1 = r9.pos
                int r1 = r0 - r1
                r9.pos = r0
                r0 = 1
                r9.refillBuffer(r0)
            L_0x0085:
                int r3 = r10 - r1
                int r4 = r9.bufferSize
                if (r3 <= r4) goto L_0x0092
                int r1 = r1 + r4
                r9.pos = r4
                r9.refillBuffer(r0)
                goto L_0x0085
            L_0x0092:
                int r0 = r10 - r1
                r9.pos = r0
            L_0x0096:
                return
            L_0x0097:
                int r3 = r3 - r0
                int r3 = r3 - r1
                r9.skipRawBytes(r3)
                com.google.protobuf.InvalidProtocolBufferException r0 = com.google.protobuf.InvalidProtocolBufferException.truncatedMessage()
                throw r0
            L_0x00a1:
                com.google.protobuf.InvalidProtocolBufferException r0 = com.google.protobuf.InvalidProtocolBufferException.negativeSize()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.StreamDecoder.skipRawBytesSlowPath(int):void");
        }
    }

    private static final class IterableDirectByteBufferDecoder extends CodedInputStream {
        private int bufferSizeAfterCurrentLimit;
        private long currentAddress;
        private ByteBuffer currentByteBuffer;
        private long currentByteBufferLimit;
        private long currentByteBufferPos;
        private long currentByteBufferStartPos;
        private int currentLimit;
        private boolean enableAliasing;
        private final boolean immutable;
        private final Iterable<ByteBuffer> input;
        private final Iterator<ByteBuffer> iterator;
        private int lastTag;
        private int startOffset;
        private int totalBufferSize;
        private int totalBytesRead;

        private IterableDirectByteBufferDecoder(Iterable<ByteBuffer> inputBufs, int size, boolean immutableFlag) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.totalBufferSize = size;
            this.input = inputBufs;
            this.iterator = inputBufs.iterator();
            this.immutable = immutableFlag;
            this.totalBytesRead = 0;
            this.startOffset = 0;
            if (size == 0) {
                this.currentByteBuffer = Internal.EMPTY_BYTE_BUFFER;
                this.currentByteBufferPos = 0;
                this.currentByteBufferStartPos = 0;
                this.currentByteBufferLimit = 0;
                this.currentAddress = 0;
                return;
            }
            tryGetNextByteBuffer();
        }

        private void getNextByteBuffer() throws InvalidProtocolBufferException {
            if (this.iterator.hasNext()) {
                tryGetNextByteBuffer();
                return;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        private void tryGetNextByteBuffer() {
            ByteBuffer next = this.iterator.next();
            this.currentByteBuffer = next;
            this.totalBytesRead += (int) (this.currentByteBufferPos - this.currentByteBufferStartPos);
            long position = (long) next.position();
            this.currentByteBufferPos = position;
            this.currentByteBufferStartPos = position;
            this.currentByteBufferLimit = (long) this.currentByteBuffer.limit();
            long addressOffset = UnsafeUtil.addressOffset(this.currentByteBuffer);
            this.currentAddress = addressOffset;
            this.currentByteBufferPos += addressOffset;
            this.currentByteBufferStartPos += addressOffset;
            this.currentByteBufferLimit += addressOffset;
        }

        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            int readRawVarint32 = readRawVarint32();
            this.lastTag = readRawVarint32;
            if (WireFormat.getTagFieldNumber(readRawVarint32) != 0) {
                return this.lastTag;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        public void checkLastTagWas(int value) throws InvalidProtocolBufferException {
            if (this.lastTag != value) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public int getLastTag() {
            return this.lastTag;
        }

        public boolean skipField(int tag) throws IOException {
            switch (WireFormat.getTagWireType(tag)) {
                case 0:
                    skipRawVarint();
                    return true;
                case 1:
                    skipRawBytes(8);
                    return true;
                case 2:
                    skipRawBytes(readRawVarint32());
                    return true;
                case 3:
                    skipMessage();
                    checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(tag), 4));
                    return true;
                case 4:
                    return false;
                case 5:
                    skipRawBytes(4);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public boolean skipField(int tag, CodedOutputStream output) throws IOException {
            switch (WireFormat.getTagWireType(tag)) {
                case 0:
                    long value = readInt64();
                    output.writeRawVarint32(tag);
                    output.writeUInt64NoTag(value);
                    return true;
                case 1:
                    long value2 = readRawLittleEndian64();
                    output.writeRawVarint32(tag);
                    output.writeFixed64NoTag(value2);
                    return true;
                case 2:
                    ByteString value3 = readBytes();
                    output.writeRawVarint32(tag);
                    output.writeBytesNoTag(value3);
                    return true;
                case 3:
                    output.writeRawVarint32(tag);
                    skipMessage(output);
                    int endtag = WireFormat.makeTag(WireFormat.getTagFieldNumber(tag), 4);
                    checkLastTagWas(endtag);
                    output.writeRawVarint32(endtag);
                    return true;
                case 4:
                    return false;
                case 5:
                    int value4 = readRawLittleEndian32();
                    output.writeRawVarint32(tag);
                    output.writeFixed32NoTag(value4);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        public void skipMessage() throws java.io.IOException {
            /*
                r2 = this;
            L_0x0000:
                int r0 = r2.readTag()
                if (r0 == 0) goto L_0x000e
                boolean r1 = r2.skipField(r0)
                if (r1 != 0) goto L_0x000d
                goto L_0x000e
            L_0x000d:
                goto L_0x0000
            L_0x000e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.IterableDirectByteBufferDecoder.skipMessage():void");
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        public void skipMessage(com.google.protobuf.CodedOutputStream r3) throws java.io.IOException {
            /*
                r2 = this;
            L_0x0000:
                int r0 = r2.readTag()
                if (r0 == 0) goto L_0x000e
                boolean r1 = r2.skipField(r0, r3)
                if (r1 != 0) goto L_0x000d
                goto L_0x000e
            L_0x000d:
                goto L_0x0000
            L_0x000e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.IterableDirectByteBufferDecoder.skipMessage(com.google.protobuf.CodedOutputStream):void");
        }

        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        public String readString() throws IOException {
            int size = readRawVarint32();
            if (size > 0) {
                long j = this.currentByteBufferLimit;
                long j2 = this.currentByteBufferPos;
                if (((long) size) <= j - j2) {
                    byte[] bytes = new byte[size];
                    UnsafeUtil.copyMemory(j2, bytes, 0, (long) size);
                    String result = new String(bytes, Internal.UTF_8);
                    this.currentByteBufferPos += (long) size;
                    return result;
                }
            }
            if (size > 0 && size <= remaining()) {
                byte[] bytes2 = new byte[size];
                readRawBytesTo(bytes2, 0, size);
                return new String(bytes2, Internal.UTF_8);
            } else if (size == 0) {
                return "";
            } else {
                if (size < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public String readStringRequireUtf8() throws IOException {
            int size = readRawVarint32();
            if (size > 0) {
                long j = this.currentByteBufferLimit;
                long j2 = this.currentByteBufferPos;
                if (((long) size) <= j - j2) {
                    String result = Utf8.decodeUtf8(this.currentByteBuffer, (int) (j2 - this.currentByteBufferStartPos), size);
                    this.currentByteBufferPos += (long) size;
                    return result;
                }
            }
            if (size >= 0 && size <= remaining()) {
                byte[] bytes = new byte[size];
                readRawBytesTo(bytes, 0, size);
                return Utf8.decodeUtf8(bytes, 0, size);
            } else if (size == 0) {
                return "";
            } else {
                if (size <= 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public void readGroup(int fieldNumber, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistry) throws IOException {
            checkRecursionLimit();
            this.recursionDepth++;
            builder.mergeFrom((CodedInputStream) this, extensionRegistry);
            checkLastTagWas(WireFormat.makeTag(fieldNumber, 4));
            this.recursionDepth--;
        }

        public <T extends MessageLite> T readGroup(int fieldNumber, Parser<T> parser, ExtensionRegistryLite extensionRegistry) throws IOException {
            checkRecursionLimit();
            this.recursionDepth++;
            T result = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistry);
            checkLastTagWas(WireFormat.makeTag(fieldNumber, 4));
            this.recursionDepth--;
            return result;
        }

        @Deprecated
        public void readUnknownGroup(int fieldNumber, MessageLite.Builder builder) throws IOException {
            readGroup(fieldNumber, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistry) throws IOException {
            int length = readRawVarint32();
            checkRecursionLimit();
            int oldLimit = pushLimit(length);
            this.recursionDepth++;
            builder.mergeFrom((CodedInputStream) this, extensionRegistry);
            checkLastTagWas(0);
            this.recursionDepth--;
            if (getBytesUntilLimit() == 0) {
                popLimit(oldLimit);
                return;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistry) throws IOException {
            int length = readRawVarint32();
            checkRecursionLimit();
            int oldLimit = pushLimit(length);
            this.recursionDepth++;
            T result = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistry);
            checkLastTagWas(0);
            this.recursionDepth--;
            if (getBytesUntilLimit() == 0) {
                popLimit(oldLimit);
                return result;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public ByteString readBytes() throws IOException {
            int size = readRawVarint32();
            if (size > 0) {
                long j = this.currentByteBufferLimit;
                long j2 = this.currentByteBufferPos;
                if (((long) size) <= j - j2) {
                    if (!this.immutable || !this.enableAliasing) {
                        byte[] bytes = new byte[size];
                        UnsafeUtil.copyMemory(j2, bytes, 0, (long) size);
                        this.currentByteBufferPos += (long) size;
                        return ByteString.wrap(bytes);
                    }
                    int idx = (int) (j2 - this.currentAddress);
                    ByteString result = ByteString.wrap(slice(idx, idx + size));
                    this.currentByteBufferPos += (long) size;
                    return result;
                }
            }
            if (size <= 0 || size > remaining()) {
                if (size == 0) {
                    return ByteString.EMPTY;
                }
                if (size < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (!this.immutable || !this.enableAliasing) {
                byte[] temp = new byte[size];
                readRawBytesTo(temp, 0, size);
                return ByteString.wrap(temp);
            } else {
                ArrayList<ByteString> byteStrings = new ArrayList<>();
                int l = size;
                while (l > 0) {
                    if (currentRemaining() == 0) {
                        getNextByteBuffer();
                    }
                    int bytesToCopy = Math.min(l, (int) currentRemaining());
                    int idx2 = (int) (this.currentByteBufferPos - this.currentAddress);
                    byteStrings.add(ByteString.wrap(slice(idx2, idx2 + bytesToCopy)));
                    l -= bytesToCopy;
                    this.currentByteBufferPos += (long) bytesToCopy;
                }
                return ByteString.copyFrom((Iterable<ByteString>) byteStrings);
            }
        }

        public byte[] readByteArray() throws IOException {
            return readRawBytes(readRawVarint32());
        }

        public ByteBuffer readByteBuffer() throws IOException {
            int size = readRawVarint32();
            if (size <= 0 || ((long) size) > currentRemaining()) {
                if (size > 0 && size <= remaining()) {
                    byte[] temp = new byte[size];
                    readRawBytesTo(temp, 0, size);
                    return ByteBuffer.wrap(temp);
                } else if (size == 0) {
                    return Internal.EMPTY_BYTE_BUFFER;
                } else {
                    if (size < 0) {
                        throw InvalidProtocolBufferException.negativeSize();
                    }
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
            } else if (this.immutable || !this.enableAliasing) {
                byte[] bytes = new byte[size];
                UnsafeUtil.copyMemory(this.currentByteBufferPos, bytes, 0, (long) size);
                this.currentByteBufferPos += (long) size;
                return ByteBuffer.wrap(bytes);
            } else {
                long j = this.currentByteBufferPos + ((long) size);
                this.currentByteBufferPos = j;
                long j2 = this.currentAddress;
                return slice((int) ((j - j2) - ((long) size)), (int) (j - j2));
            }
        }

        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readSInt32() throws IOException {
            return decodeZigZag32(readRawVarint32());
        }

        public long readSInt64() throws IOException {
            return decodeZigZag64(readRawVarint64());
        }

        public int readRawVarint32() throws IOException {
            int y;
            long tempPos = this.currentByteBufferPos;
            if (this.currentByteBufferLimit != this.currentByteBufferPos) {
                long tempPos2 = tempPos + 1;
                int i = UnsafeUtil.getByte(tempPos);
                int x = i;
                if (i >= 0) {
                    this.currentByteBufferPos++;
                    return x;
                } else if (this.currentByteBufferLimit - this.currentByteBufferPos >= 10) {
                    long tempPos3 = tempPos2 + 1;
                    int i2 = (UnsafeUtil.getByte(tempPos2) << 7) ^ x;
                    int x2 = i2;
                    if (i2 < 0) {
                        y = x2 ^ -128;
                    } else {
                        long tempPos4 = tempPos3 + 1;
                        int i3 = (UnsafeUtil.getByte(tempPos3) << Ascii.f63SO) ^ x2;
                        int x3 = i3;
                        if (i3 >= 0) {
                            y = x3 ^ 16256;
                            tempPos3 = tempPos4;
                        } else {
                            tempPos3 = tempPos4 + 1;
                            int i4 = (UnsafeUtil.getByte(tempPos4) << Ascii.NAK) ^ x3;
                            int x4 = i4;
                            if (i4 < 0) {
                                y = -2080896 ^ x4;
                            } else {
                                long tempPos5 = tempPos3 + 1;
                                int y2 = UnsafeUtil.getByte(tempPos3);
                                int x5 = (x4 ^ (y2 << 28)) ^ 266354560;
                                if (y2 < 0) {
                                    tempPos3 = tempPos5 + 1;
                                    if (UnsafeUtil.getByte(tempPos5) < 0) {
                                        long tempPos6 = tempPos3 + 1;
                                        if (UnsafeUtil.getByte(tempPos3) < 0) {
                                            tempPos3 = tempPos6 + 1;
                                            if (UnsafeUtil.getByte(tempPos6) < 0) {
                                                long tempPos7 = tempPos3 + 1;
                                                if (UnsafeUtil.getByte(tempPos3) < 0) {
                                                    tempPos3 = tempPos7 + 1;
                                                    if (UnsafeUtil.getByte(tempPos7) >= 0) {
                                                        y = x5;
                                                    }
                                                } else {
                                                    y = x5;
                                                    tempPos3 = tempPos7;
                                                }
                                            } else {
                                                y = x5;
                                            }
                                        } else {
                                            y = x5;
                                            tempPos3 = tempPos6;
                                        }
                                    } else {
                                        y = x5;
                                    }
                                } else {
                                    y = x5;
                                    tempPos3 = tempPos5;
                                }
                            }
                        }
                    }
                    this.currentByteBufferPos = tempPos3;
                    return y;
                }
            }
            return (int) readRawVarint64SlowPath();
        }

        public long readRawVarint64() throws IOException {
            long x;
            long tempPos = this.currentByteBufferPos;
            if (this.currentByteBufferLimit != this.currentByteBufferPos) {
                long tempPos2 = tempPos + 1;
                int i = UnsafeUtil.getByte(tempPos);
                int y = i;
                if (i >= 0) {
                    this.currentByteBufferPos++;
                    return (long) y;
                } else if (this.currentByteBufferLimit - this.currentByteBufferPos >= 10) {
                    long tempPos3 = tempPos2 + 1;
                    int i2 = (UnsafeUtil.getByte(tempPos2) << 7) ^ y;
                    int y2 = i2;
                    if (i2 < 0) {
                        x = (long) (y2 ^ -128);
                    } else {
                        long tempPos4 = tempPos3 + 1;
                        int i3 = (UnsafeUtil.getByte(tempPos3) << Ascii.f63SO) ^ y2;
                        int y3 = i3;
                        if (i3 >= 0) {
                            x = (long) (y3 ^ 16256);
                            tempPos3 = tempPos4;
                        } else {
                            tempPos3 = tempPos4 + 1;
                            int i4 = (UnsafeUtil.getByte(tempPos4) << Ascii.NAK) ^ y3;
                            int y4 = i4;
                            if (i4 < 0) {
                                x = (long) (-2080896 ^ y4);
                            } else {
                                long tempPos5 = tempPos3 + 1;
                                long j = ((long) y4) ^ (((long) UnsafeUtil.getByte(tempPos3)) << 28);
                                long x2 = j;
                                if (j >= 0) {
                                    x = 266354560 ^ x2;
                                    tempPos3 = tempPos5;
                                } else {
                                    long tempPos6 = tempPos5 + 1;
                                    long j2 = (((long) UnsafeUtil.getByte(tempPos5)) << 35) ^ x2;
                                    long x3 = j2;
                                    if (j2 < 0) {
                                        x = -34093383808L ^ x3;
                                        tempPos3 = tempPos6;
                                    } else {
                                        long tempPos7 = tempPos6 + 1;
                                        long j3 = (((long) UnsafeUtil.getByte(tempPos6)) << 42) ^ x3;
                                        long x4 = j3;
                                        if (j3 >= 0) {
                                            x = 4363953127296L ^ x4;
                                            tempPos3 = tempPos7;
                                        } else {
                                            long tempPos8 = tempPos7 + 1;
                                            long j4 = (((long) UnsafeUtil.getByte(tempPos7)) << 49) ^ x4;
                                            long x5 = j4;
                                            if (j4 < 0) {
                                                x = -558586000294016L ^ x5;
                                                tempPos3 = tempPos8;
                                            } else {
                                                long tempPos9 = tempPos8 + 1;
                                                long x6 = ((((long) UnsafeUtil.getByte(tempPos8)) << 56) ^ x5) ^ 71499008037633920L;
                                                if (x6 < 0) {
                                                    tempPos3 = tempPos9 + 1;
                                                    if (((long) UnsafeUtil.getByte(tempPos9)) >= 0) {
                                                        x = x6;
                                                    }
                                                } else {
                                                    x = x6;
                                                    tempPos3 = tempPos9;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    this.currentByteBufferPos = tempPos3;
                    return x;
                }
            }
            return readRawVarint64SlowPath();
        }

        /* access modifiers changed from: package-private */
        public long readRawVarint64SlowPath() throws IOException {
            long result = 0;
            for (int shift = 0; shift < 64; shift += 7) {
                byte b = readRawByte();
                result |= ((long) (b & Ascii.DEL)) << shift;
                if ((b & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                    return result;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public int readRawLittleEndian32() throws IOException {
            if (currentRemaining() < 4) {
                return (readRawByte() & 255) | ((readRawByte() & 255) << 8) | ((readRawByte() & 255) << Ascii.DLE) | ((readRawByte() & 255) << Ascii.CAN);
            }
            long tempPos = this.currentByteBufferPos;
            this.currentByteBufferPos += 4;
            return (UnsafeUtil.getByte(tempPos) & 255) | ((UnsafeUtil.getByte(1 + tempPos) & 255) << 8) | ((UnsafeUtil.getByte(2 + tempPos) & 255) << Ascii.DLE) | ((UnsafeUtil.getByte(3 + tempPos) & 255) << Ascii.CAN);
        }

        public long readRawLittleEndian64() throws IOException {
            if (currentRemaining() >= 8) {
                long tempPos = this.currentByteBufferPos;
                this.currentByteBufferPos += 8;
                return ((((long) UnsafeUtil.getByte(7 + tempPos)) & 255) << 56) | (((long) UnsafeUtil.getByte(tempPos)) & 255) | ((((long) UnsafeUtil.getByte(1 + tempPos)) & 255) << 8) | ((((long) UnsafeUtil.getByte(2 + tempPos)) & 255) << 16) | ((((long) UnsafeUtil.getByte(3 + tempPos)) & 255) << 24) | ((((long) UnsafeUtil.getByte(4 + tempPos)) & 255) << 32) | ((((long) UnsafeUtil.getByte(5 + tempPos)) & 255) << 40) | ((((long) UnsafeUtil.getByte(6 + tempPos)) & 255) << 48);
            }
            return ((((long) readRawByte()) & 255) << 56) | (((long) readRawByte()) & 255) | ((((long) readRawByte()) & 255) << 8) | ((((long) readRawByte()) & 255) << 16) | ((((long) readRawByte()) & 255) << 24) | ((((long) readRawByte()) & 255) << 32) | ((((long) readRawByte()) & 255) << 40) | ((((long) readRawByte()) & 255) << 48);
        }

        public void enableAliasing(boolean enabled) {
            this.enableAliasing = enabled;
        }

        public void resetSizeCounter() {
            this.startOffset = (int) ((((long) this.totalBytesRead) + this.currentByteBufferPos) - this.currentByteBufferStartPos);
        }

        public int pushLimit(int byteLimit) throws InvalidProtocolBufferException {
            if (byteLimit >= 0) {
                int byteLimit2 = byteLimit + getTotalBytesRead();
                int oldLimit = this.currentLimit;
                if (byteLimit2 <= oldLimit) {
                    this.currentLimit = byteLimit2;
                    recomputeBufferSizeAfterLimit();
                    return oldLimit;
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        private void recomputeBufferSizeAfterLimit() {
            int i = this.totalBufferSize + this.bufferSizeAfterCurrentLimit;
            this.totalBufferSize = i;
            int bufferEnd = i - this.startOffset;
            int i2 = this.currentLimit;
            if (bufferEnd > i2) {
                int i3 = bufferEnd - i2;
                this.bufferSizeAfterCurrentLimit = i3;
                this.totalBufferSize = i - i3;
                return;
            }
            this.bufferSizeAfterCurrentLimit = 0;
        }

        public void popLimit(int oldLimit) {
            this.currentLimit = oldLimit;
            recomputeBufferSizeAfterLimit();
        }

        public int getBytesUntilLimit() {
            int i = this.currentLimit;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - getTotalBytesRead();
        }

        public boolean isAtEnd() throws IOException {
            return (((long) this.totalBytesRead) + this.currentByteBufferPos) - this.currentByteBufferStartPos == ((long) this.totalBufferSize);
        }

        public int getTotalBytesRead() {
            return (int) ((((long) (this.totalBytesRead - this.startOffset)) + this.currentByteBufferPos) - this.currentByteBufferStartPos);
        }

        public byte readRawByte() throws IOException {
            if (currentRemaining() == 0) {
                getNextByteBuffer();
            }
            long j = this.currentByteBufferPos;
            this.currentByteBufferPos = 1 + j;
            return UnsafeUtil.getByte(j);
        }

        public byte[] readRawBytes(int length) throws IOException {
            if (length >= 0 && ((long) length) <= currentRemaining()) {
                byte[] bytes = new byte[length];
                UnsafeUtil.copyMemory(this.currentByteBufferPos, bytes, 0, (long) length);
                this.currentByteBufferPos += (long) length;
                return bytes;
            } else if (length >= 0 && length <= remaining()) {
                byte[] bytes2 = new byte[length];
                readRawBytesTo(bytes2, 0, length);
                return bytes2;
            } else if (length > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (length == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            } else {
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        private void readRawBytesTo(byte[] bytes, int offset, int length) throws IOException {
            if (length >= 0 && length <= remaining()) {
                int l = length;
                while (l > 0) {
                    if (currentRemaining() == 0) {
                        getNextByteBuffer();
                    }
                    int bytesToCopy = Math.min(l, (int) currentRemaining());
                    UnsafeUtil.copyMemory(this.currentByteBufferPos, bytes, (long) ((length - l) + offset), (long) bytesToCopy);
                    l -= bytesToCopy;
                    this.currentByteBufferPos += (long) bytesToCopy;
                }
            } else if (length > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (length != 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        public void skipRawBytes(int length) throws IOException {
            if (length >= 0 && ((long) length) <= (((long) (this.totalBufferSize - this.totalBytesRead)) - this.currentByteBufferPos) + this.currentByteBufferStartPos) {
                int l = length;
                while (l > 0) {
                    if (currentRemaining() == 0) {
                        getNextByteBuffer();
                    }
                    int rl = Math.min(l, (int) currentRemaining());
                    l -= rl;
                    this.currentByteBufferPos += (long) rl;
                }
            } else if (length < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        private void skipRawVarint() throws IOException {
            int i = 0;
            while (i < 10) {
                if (readRawByte() < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private int remaining() {
            return (int) ((((long) (this.totalBufferSize - this.totalBytesRead)) - this.currentByteBufferPos) + this.currentByteBufferStartPos);
        }

        private long currentRemaining() {
            return this.currentByteBufferLimit - this.currentByteBufferPos;
        }

        private ByteBuffer slice(int begin, int end) throws IOException {
            int prevPos = this.currentByteBuffer.position();
            int prevLimit = this.currentByteBuffer.limit();
            Buffer asBuffer = this.currentByteBuffer;
            try {
                asBuffer.position(begin);
                asBuffer.limit(end);
                ByteBuffer slice = this.currentByteBuffer.slice();
                asBuffer.position(prevPos);
                asBuffer.limit(prevLimit);
                return slice;
            } catch (IllegalArgumentException e) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } catch (Throwable th) {
                asBuffer.position(prevPos);
                asBuffer.limit(prevLimit);
                throw th;
            }
        }
    }
}
