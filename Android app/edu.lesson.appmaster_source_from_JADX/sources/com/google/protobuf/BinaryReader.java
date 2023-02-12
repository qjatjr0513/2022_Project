package com.google.protobuf;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

abstract class BinaryReader implements Reader {
    private static final int FIXED32_MULTIPLE_MASK = 3;
    private static final int FIXED64_MULTIPLE_MASK = 7;

    public abstract int getTotalBytesRead();

    /* synthetic */ BinaryReader(C09851 x0) {
        this();
    }

    public static BinaryReader newInstance(ByteBuffer buffer, boolean bufferIsImmutable) {
        if (buffer.hasArray()) {
            return new SafeHeapReader(buffer, bufferIsImmutable);
        }
        throw new IllegalArgumentException("Direct buffers not yet supported");
    }

    private BinaryReader() {
    }

    public boolean shouldDiscardUnknownFields() {
        return false;
    }

    private static final class SafeHeapReader extends BinaryReader {
        private final byte[] buffer;
        private final boolean bufferIsImmutable;
        private int endGroupTag;
        private final int initialPos;
        private int limit;
        private int pos;
        private int tag;

        public SafeHeapReader(ByteBuffer bytebuf, boolean bufferIsImmutable2) {
            super((C09851) null);
            this.bufferIsImmutable = bufferIsImmutable2;
            this.buffer = bytebuf.array();
            int arrayOffset = bytebuf.arrayOffset() + bytebuf.position();
            this.pos = arrayOffset;
            this.initialPos = arrayOffset;
            this.limit = bytebuf.arrayOffset() + bytebuf.limit();
        }

        private boolean isAtEnd() {
            return this.pos == this.limit;
        }

        public int getTotalBytesRead() {
            return this.pos - this.initialPos;
        }

        public int getFieldNumber() throws IOException {
            if (isAtEnd()) {
                return Integer.MAX_VALUE;
            }
            int readVarint32 = readVarint32();
            this.tag = readVarint32;
            if (readVarint32 == this.endGroupTag) {
                return Integer.MAX_VALUE;
            }
            return WireFormat.getTagFieldNumber(readVarint32);
        }

        public int getTag() {
            return this.tag;
        }

        public boolean skipField() throws IOException {
            int i;
            if (isAtEnd() || (i = this.tag) == this.endGroupTag) {
                return false;
            }
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    skipVarint();
                    return true;
                case 1:
                    skipBytes(8);
                    return true;
                case 2:
                    skipBytes(readVarint32());
                    return true;
                case 3:
                    skipGroup();
                    return true;
                case 5:
                    skipBytes(4);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public double readDouble() throws IOException {
            requireWireType(1);
            return Double.longBitsToDouble(readLittleEndian64());
        }

        public float readFloat() throws IOException {
            requireWireType(5);
            return Float.intBitsToFloat(readLittleEndian32());
        }

        public long readUInt64() throws IOException {
            requireWireType(0);
            return readVarint64();
        }

        public long readInt64() throws IOException {
            requireWireType(0);
            return readVarint64();
        }

        public int readInt32() throws IOException {
            requireWireType(0);
            return readVarint32();
        }

        public long readFixed64() throws IOException {
            requireWireType(1);
            return readLittleEndian64();
        }

        public int readFixed32() throws IOException {
            requireWireType(5);
            return readLittleEndian32();
        }

        public boolean readBool() throws IOException {
            requireWireType(0);
            if (readVarint32() != 0) {
                return true;
            }
            return false;
        }

        public String readString() throws IOException {
            return readStringInternal(false);
        }

        public String readStringRequireUtf8() throws IOException {
            return readStringInternal(true);
        }

        public String readStringInternal(boolean requireUtf8) throws IOException {
            requireWireType(2);
            int size = readVarint32();
            if (size == 0) {
                return "";
            }
            requireBytes(size);
            if (requireUtf8) {
                byte[] bArr = this.buffer;
                int i = this.pos;
                if (!Utf8.isValidUtf8(bArr, i, i + size)) {
                    throw InvalidProtocolBufferException.invalidUtf8();
                }
            }
            String result = new String(this.buffer, this.pos, size, Internal.UTF_8);
            this.pos += size;
            return result;
        }

        public <T> T readMessage(Class<T> clazz, ExtensionRegistryLite extensionRegistry) throws IOException {
            requireWireType(2);
            return readMessage(Protobuf.getInstance().schemaFor(clazz), extensionRegistry);
        }

        public <T> T readMessageBySchemaWithCheck(Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
            requireWireType(2);
            return readMessage(schema, extensionRegistry);
        }

        private <T> T readMessage(Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
            int size = readVarint32();
            requireBytes(size);
            int prevLimit = this.limit;
            int newLimit = this.pos + size;
            this.limit = newLimit;
            try {
                T message = schema.newInstance();
                schema.mergeFrom(message, this, extensionRegistry);
                schema.makeImmutable(message);
                if (this.pos == newLimit) {
                    return message;
                }
                throw InvalidProtocolBufferException.parseFailure();
            } finally {
                this.limit = prevLimit;
            }
        }

        public <T> T readGroup(Class<T> clazz, ExtensionRegistryLite extensionRegistry) throws IOException {
            requireWireType(3);
            return readGroup(Protobuf.getInstance().schemaFor(clazz), extensionRegistry);
        }

        public <T> T readGroupBySchemaWithCheck(Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
            requireWireType(3);
            return readGroup(schema, extensionRegistry);
        }

        private <T> T readGroup(Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
            int prevEndGroupTag = this.endGroupTag;
            this.endGroupTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(this.tag), 4);
            try {
                T message = schema.newInstance();
                schema.mergeFrom(message, this, extensionRegistry);
                schema.makeImmutable(message);
                if (this.tag == this.endGroupTag) {
                    return message;
                }
                throw InvalidProtocolBufferException.parseFailure();
            } finally {
                this.endGroupTag = prevEndGroupTag;
            }
        }

        public ByteString readBytes() throws IOException {
            ByteString bytes;
            requireWireType(2);
            int size = readVarint32();
            if (size == 0) {
                return ByteString.EMPTY;
            }
            requireBytes(size);
            if (this.bufferIsImmutable) {
                bytes = ByteString.wrap(this.buffer, this.pos, size);
            } else {
                bytes = ByteString.copyFrom(this.buffer, this.pos, size);
            }
            this.pos += size;
            return bytes;
        }

        public int readUInt32() throws IOException {
            requireWireType(0);
            return readVarint32();
        }

        public int readEnum() throws IOException {
            requireWireType(0);
            return readVarint32();
        }

        public int readSFixed32() throws IOException {
            requireWireType(5);
            return readLittleEndian32();
        }

        public long readSFixed64() throws IOException {
            requireWireType(1);
            return readLittleEndian64();
        }

        public int readSInt32() throws IOException {
            requireWireType(0);
            return CodedInputStream.decodeZigZag32(readVarint32());
        }

        public long readSInt64() throws IOException {
            requireWireType(0);
            return CodedInputStream.decodeZigZag64(readVarint64());
        }

        public void readDoubleList(List<Double> target) throws IOException {
            int prevPos;
            int prevPos2;
            if (target instanceof DoubleArrayList) {
                DoubleArrayList plist = (DoubleArrayList) target;
                switch (WireFormat.getTagWireType(this.tag)) {
                    case 1:
                        break;
                    case 2:
                        int bytes = readVarint32();
                        verifyPackedFixed64Length(bytes);
                        int fieldEndPos = this.pos + bytes;
                        while (this.pos < fieldEndPos) {
                            plist.addDouble(Double.longBitsToDouble(readLittleEndian64_NoCheck()));
                        }
                        return;
                    default:
                        throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    plist.addDouble(readDouble());
                    if (!isAtEnd()) {
                        prevPos2 = this.pos;
                    } else {
                        return;
                    }
                } while (readVarint32() == this.tag);
                this.pos = prevPos2;
                return;
            }
            switch (WireFormat.getTagWireType(this.tag)) {
                case 1:
                    break;
                case 2:
                    int bytes2 = readVarint32();
                    verifyPackedFixed64Length(bytes2);
                    int fieldEndPos2 = this.pos + bytes2;
                    while (this.pos < fieldEndPos2) {
                        target.add(Double.valueOf(Double.longBitsToDouble(readLittleEndian64_NoCheck())));
                    }
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                target.add(Double.valueOf(readDouble()));
                if (!isAtEnd()) {
                    prevPos = this.pos;
                } else {
                    return;
                }
            } while (readVarint32() == this.tag);
            this.pos = prevPos;
        }

        public void readFloatList(List<Float> target) throws IOException {
            int prevPos;
            int prevPos2;
            if (target instanceof FloatArrayList) {
                FloatArrayList plist = (FloatArrayList) target;
                switch (WireFormat.getTagWireType(this.tag)) {
                    case 2:
                        int bytes = readVarint32();
                        verifyPackedFixed32Length(bytes);
                        int fieldEndPos = this.pos + bytes;
                        while (this.pos < fieldEndPos) {
                            plist.addFloat(Float.intBitsToFloat(readLittleEndian32_NoCheck()));
                        }
                        return;
                    case 5:
                        break;
                    default:
                        throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    plist.addFloat(readFloat());
                    if (!isAtEnd()) {
                        prevPos2 = this.pos;
                    } else {
                        return;
                    }
                } while (readVarint32() == this.tag);
                this.pos = prevPos2;
                return;
            }
            switch (WireFormat.getTagWireType(this.tag)) {
                case 2:
                    int bytes2 = readVarint32();
                    verifyPackedFixed32Length(bytes2);
                    int fieldEndPos2 = this.pos + bytes2;
                    while (this.pos < fieldEndPos2) {
                        target.add(Float.valueOf(Float.intBitsToFloat(readLittleEndian32_NoCheck())));
                    }
                    return;
                case 5:
                    break;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                target.add(Float.valueOf(readFloat()));
                if (!isAtEnd()) {
                    prevPos = this.pos;
                } else {
                    return;
                }
            } while (readVarint32() == this.tag);
            this.pos = prevPos;
        }

        public void readUInt64List(List<Long> target) throws IOException {
            int prevPos;
            int prevPos2;
            if (target instanceof LongArrayList) {
                LongArrayList plist = (LongArrayList) target;
                switch (WireFormat.getTagWireType(this.tag)) {
                    case 0:
                        break;
                    case 2:
                        int fieldEndPos = this.pos + readVarint32();
                        while (this.pos < fieldEndPos) {
                            plist.addLong(readVarint64());
                        }
                        requirePosition(fieldEndPos);
                        return;
                    default:
                        throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    plist.addLong(readUInt64());
                    if (!isAtEnd()) {
                        prevPos2 = this.pos;
                    } else {
                        return;
                    }
                } while (readVarint32() == this.tag);
                this.pos = prevPos2;
                return;
            }
            switch (WireFormat.getTagWireType(this.tag)) {
                case 0:
                    break;
                case 2:
                    int fieldEndPos2 = this.pos + readVarint32();
                    while (this.pos < fieldEndPos2) {
                        target.add(Long.valueOf(readVarint64()));
                    }
                    requirePosition(fieldEndPos2);
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                target.add(Long.valueOf(readUInt64()));
                if (!isAtEnd()) {
                    prevPos = this.pos;
                } else {
                    return;
                }
            } while (readVarint32() == this.tag);
            this.pos = prevPos;
        }

        public void readInt64List(List<Long> target) throws IOException {
            int prevPos;
            int prevPos2;
            if (target instanceof LongArrayList) {
                LongArrayList plist = (LongArrayList) target;
                switch (WireFormat.getTagWireType(this.tag)) {
                    case 0:
                        break;
                    case 2:
                        int fieldEndPos = this.pos + readVarint32();
                        while (this.pos < fieldEndPos) {
                            plist.addLong(readVarint64());
                        }
                        requirePosition(fieldEndPos);
                        return;
                    default:
                        throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    plist.addLong(readInt64());
                    if (!isAtEnd()) {
                        prevPos2 = this.pos;
                    } else {
                        return;
                    }
                } while (readVarint32() == this.tag);
                this.pos = prevPos2;
                return;
            }
            switch (WireFormat.getTagWireType(this.tag)) {
                case 0:
                    break;
                case 2:
                    int fieldEndPos2 = this.pos + readVarint32();
                    while (this.pos < fieldEndPos2) {
                        target.add(Long.valueOf(readVarint64()));
                    }
                    requirePosition(fieldEndPos2);
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                target.add(Long.valueOf(readInt64()));
                if (!isAtEnd()) {
                    prevPos = this.pos;
                } else {
                    return;
                }
            } while (readVarint32() == this.tag);
            this.pos = prevPos;
        }

        public void readInt32List(List<Integer> target) throws IOException {
            int prevPos;
            int prevPos2;
            if (target instanceof IntArrayList) {
                IntArrayList plist = (IntArrayList) target;
                switch (WireFormat.getTagWireType(this.tag)) {
                    case 0:
                        break;
                    case 2:
                        int fieldEndPos = this.pos + readVarint32();
                        while (this.pos < fieldEndPos) {
                            plist.addInt(readVarint32());
                        }
                        requirePosition(fieldEndPos);
                        return;
                    default:
                        throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    plist.addInt(readInt32());
                    if (!isAtEnd()) {
                        prevPos2 = this.pos;
                    } else {
                        return;
                    }
                } while (readVarint32() == this.tag);
                this.pos = prevPos2;
                return;
            }
            switch (WireFormat.getTagWireType(this.tag)) {
                case 0:
                    break;
                case 2:
                    int fieldEndPos2 = this.pos + readVarint32();
                    while (this.pos < fieldEndPos2) {
                        target.add(Integer.valueOf(readVarint32()));
                    }
                    requirePosition(fieldEndPos2);
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                target.add(Integer.valueOf(readInt32()));
                if (!isAtEnd()) {
                    prevPos = this.pos;
                } else {
                    return;
                }
            } while (readVarint32() == this.tag);
            this.pos = prevPos;
        }

        public void readFixed64List(List<Long> target) throws IOException {
            int prevPos;
            int prevPos2;
            if (target instanceof LongArrayList) {
                LongArrayList plist = (LongArrayList) target;
                switch (WireFormat.getTagWireType(this.tag)) {
                    case 1:
                        break;
                    case 2:
                        int bytes = readVarint32();
                        verifyPackedFixed64Length(bytes);
                        int fieldEndPos = this.pos + bytes;
                        while (this.pos < fieldEndPos) {
                            plist.addLong(readLittleEndian64_NoCheck());
                        }
                        return;
                    default:
                        throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    plist.addLong(readFixed64());
                    if (!isAtEnd()) {
                        prevPos2 = this.pos;
                    } else {
                        return;
                    }
                } while (readVarint32() == this.tag);
                this.pos = prevPos2;
                return;
            }
            switch (WireFormat.getTagWireType(this.tag)) {
                case 1:
                    break;
                case 2:
                    int bytes2 = readVarint32();
                    verifyPackedFixed64Length(bytes2);
                    int fieldEndPos2 = this.pos + bytes2;
                    while (this.pos < fieldEndPos2) {
                        target.add(Long.valueOf(readLittleEndian64_NoCheck()));
                    }
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                target.add(Long.valueOf(readFixed64()));
                if (!isAtEnd()) {
                    prevPos = this.pos;
                } else {
                    return;
                }
            } while (readVarint32() == this.tag);
            this.pos = prevPos;
        }

        public void readFixed32List(List<Integer> target) throws IOException {
            int prevPos;
            int prevPos2;
            if (target instanceof IntArrayList) {
                IntArrayList plist = (IntArrayList) target;
                switch (WireFormat.getTagWireType(this.tag)) {
                    case 2:
                        int bytes = readVarint32();
                        verifyPackedFixed32Length(bytes);
                        int fieldEndPos = this.pos + bytes;
                        while (this.pos < fieldEndPos) {
                            plist.addInt(readLittleEndian32_NoCheck());
                        }
                        return;
                    case 5:
                        break;
                    default:
                        throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    plist.addInt(readFixed32());
                    if (!isAtEnd()) {
                        prevPos2 = this.pos;
                    } else {
                        return;
                    }
                } while (readVarint32() == this.tag);
                this.pos = prevPos2;
                return;
            }
            switch (WireFormat.getTagWireType(this.tag)) {
                case 2:
                    int bytes2 = readVarint32();
                    verifyPackedFixed32Length(bytes2);
                    int fieldEndPos2 = this.pos + bytes2;
                    while (this.pos < fieldEndPos2) {
                        target.add(Integer.valueOf(readLittleEndian32_NoCheck()));
                    }
                    return;
                case 5:
                    break;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                target.add(Integer.valueOf(readFixed32()));
                if (!isAtEnd()) {
                    prevPos = this.pos;
                } else {
                    return;
                }
            } while (readVarint32() == this.tag);
            this.pos = prevPos;
        }

        public void readBoolList(List<Boolean> target) throws IOException {
            int prevPos;
            int prevPos2;
            if (target instanceof BooleanArrayList) {
                BooleanArrayList plist = (BooleanArrayList) target;
                switch (WireFormat.getTagWireType(this.tag)) {
                    case 0:
                        break;
                    case 2:
                        int fieldEndPos = this.pos + readVarint32();
                        while (this.pos < fieldEndPos) {
                            plist.addBoolean(readVarint32() != 0);
                        }
                        requirePosition(fieldEndPos);
                        return;
                    default:
                        throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    plist.addBoolean(readBool());
                    if (!isAtEnd()) {
                        prevPos2 = this.pos;
                    } else {
                        return;
                    }
                } while (readVarint32() == this.tag);
                this.pos = prevPos2;
                return;
            }
            switch (WireFormat.getTagWireType(this.tag)) {
                case 0:
                    break;
                case 2:
                    int fieldEndPos2 = this.pos + readVarint32();
                    while (this.pos < fieldEndPos2) {
                        target.add(Boolean.valueOf(readVarint32() != 0));
                    }
                    requirePosition(fieldEndPos2);
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                target.add(Boolean.valueOf(readBool()));
                if (!isAtEnd()) {
                    prevPos = this.pos;
                } else {
                    return;
                }
            } while (readVarint32() == this.tag);
            this.pos = prevPos;
        }

        public void readStringList(List<String> target) throws IOException {
            readStringListInternal(target, false);
        }

        public void readStringListRequireUtf8(List<String> target) throws IOException {
            readStringListInternal(target, true);
        }

        public void readStringListInternal(List<String> target, boolean requireUtf8) throws IOException {
            int prevPos;
            int prevPos2;
            if (WireFormat.getTagWireType(this.tag) != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            } else if (!(target instanceof LazyStringList) || requireUtf8) {
                do {
                    target.add(readStringInternal(requireUtf8));
                    if (!isAtEnd()) {
                        prevPos = this.pos;
                    } else {
                        return;
                    }
                } while (readVarint32() == this.tag);
                this.pos = prevPos;
            } else {
                LazyStringList lazyList = (LazyStringList) target;
                do {
                    lazyList.add(readBytes());
                    if (!isAtEnd()) {
                        prevPos2 = this.pos;
                    } else {
                        return;
                    }
                } while (readVarint32() == this.tag);
                this.pos = prevPos2;
            }
        }

        public <T> void readMessageList(List<T> target, Class<T> targetType, ExtensionRegistryLite extensionRegistry) throws IOException {
            readMessageList(target, Protobuf.getInstance().schemaFor(targetType), extensionRegistry);
        }

        public <T> void readMessageList(List<T> target, Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
            int prevPos;
            if (WireFormat.getTagWireType(this.tag) == 2) {
                int listTag = this.tag;
                do {
                    target.add(readMessage(schema, extensionRegistry));
                    if (!isAtEnd()) {
                        prevPos = this.pos;
                    } else {
                        return;
                    }
                } while (readVarint32() == listTag);
                this.pos = prevPos;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        public <T> void readGroupList(List<T> target, Class<T> targetType, ExtensionRegistryLite extensionRegistry) throws IOException {
            readGroupList(target, Protobuf.getInstance().schemaFor(targetType), extensionRegistry);
        }

        public <T> void readGroupList(List<T> target, Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
            int prevPos;
            if (WireFormat.getTagWireType(this.tag) == 3) {
                int listTag = this.tag;
                do {
                    target.add(readGroup(schema, extensionRegistry));
                    if (!isAtEnd()) {
                        prevPos = this.pos;
                    } else {
                        return;
                    }
                } while (readVarint32() == listTag);
                this.pos = prevPos;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        public void readBytesList(List<ByteString> target) throws IOException {
            int prevPos;
            if (WireFormat.getTagWireType(this.tag) == 2) {
                do {
                    target.add(readBytes());
                    if (!isAtEnd()) {
                        prevPos = this.pos;
                    } else {
                        return;
                    }
                } while (readVarint32() == this.tag);
                this.pos = prevPos;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        public void readUInt32List(List<Integer> target) throws IOException {
            int prevPos;
            int prevPos2;
            if (target instanceof IntArrayList) {
                IntArrayList plist = (IntArrayList) target;
                switch (WireFormat.getTagWireType(this.tag)) {
                    case 0:
                        break;
                    case 2:
                        int fieldEndPos = this.pos + readVarint32();
                        while (this.pos < fieldEndPos) {
                            plist.addInt(readVarint32());
                        }
                        return;
                    default:
                        throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    plist.addInt(readUInt32());
                    if (!isAtEnd()) {
                        prevPos2 = this.pos;
                    } else {
                        return;
                    }
                } while (readVarint32() == this.tag);
                this.pos = prevPos2;
                return;
            }
            switch (WireFormat.getTagWireType(this.tag)) {
                case 0:
                    break;
                case 2:
                    int fieldEndPos2 = this.pos + readVarint32();
                    while (this.pos < fieldEndPos2) {
                        target.add(Integer.valueOf(readVarint32()));
                    }
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                target.add(Integer.valueOf(readUInt32()));
                if (!isAtEnd()) {
                    prevPos = this.pos;
                } else {
                    return;
                }
            } while (readVarint32() == this.tag);
            this.pos = prevPos;
        }

        public void readEnumList(List<Integer> target) throws IOException {
            int prevPos;
            int prevPos2;
            if (target instanceof IntArrayList) {
                IntArrayList plist = (IntArrayList) target;
                switch (WireFormat.getTagWireType(this.tag)) {
                    case 0:
                        break;
                    case 2:
                        int fieldEndPos = this.pos + readVarint32();
                        while (this.pos < fieldEndPos) {
                            plist.addInt(readVarint32());
                        }
                        return;
                    default:
                        throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    plist.addInt(readEnum());
                    if (!isAtEnd()) {
                        prevPos2 = this.pos;
                    } else {
                        return;
                    }
                } while (readVarint32() == this.tag);
                this.pos = prevPos2;
                return;
            }
            switch (WireFormat.getTagWireType(this.tag)) {
                case 0:
                    break;
                case 2:
                    int fieldEndPos2 = this.pos + readVarint32();
                    while (this.pos < fieldEndPos2) {
                        target.add(Integer.valueOf(readVarint32()));
                    }
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                target.add(Integer.valueOf(readEnum()));
                if (!isAtEnd()) {
                    prevPos = this.pos;
                } else {
                    return;
                }
            } while (readVarint32() == this.tag);
            this.pos = prevPos;
        }

        public void readSFixed32List(List<Integer> target) throws IOException {
            int prevPos;
            int prevPos2;
            if (target instanceof IntArrayList) {
                IntArrayList plist = (IntArrayList) target;
                switch (WireFormat.getTagWireType(this.tag)) {
                    case 2:
                        int bytes = readVarint32();
                        verifyPackedFixed32Length(bytes);
                        int fieldEndPos = this.pos + bytes;
                        while (this.pos < fieldEndPos) {
                            plist.addInt(readLittleEndian32_NoCheck());
                        }
                        return;
                    case 5:
                        break;
                    default:
                        throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    plist.addInt(readSFixed32());
                    if (!isAtEnd()) {
                        prevPos2 = this.pos;
                    } else {
                        return;
                    }
                } while (readVarint32() == this.tag);
                this.pos = prevPos2;
                return;
            }
            switch (WireFormat.getTagWireType(this.tag)) {
                case 2:
                    int bytes2 = readVarint32();
                    verifyPackedFixed32Length(bytes2);
                    int fieldEndPos2 = this.pos + bytes2;
                    while (this.pos < fieldEndPos2) {
                        target.add(Integer.valueOf(readLittleEndian32_NoCheck()));
                    }
                    return;
                case 5:
                    break;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                target.add(Integer.valueOf(readSFixed32()));
                if (!isAtEnd()) {
                    prevPos = this.pos;
                } else {
                    return;
                }
            } while (readVarint32() == this.tag);
            this.pos = prevPos;
        }

        public void readSFixed64List(List<Long> target) throws IOException {
            int prevPos;
            int prevPos2;
            if (target instanceof LongArrayList) {
                LongArrayList plist = (LongArrayList) target;
                switch (WireFormat.getTagWireType(this.tag)) {
                    case 1:
                        break;
                    case 2:
                        int bytes = readVarint32();
                        verifyPackedFixed64Length(bytes);
                        int fieldEndPos = this.pos + bytes;
                        while (this.pos < fieldEndPos) {
                            plist.addLong(readLittleEndian64_NoCheck());
                        }
                        return;
                    default:
                        throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    plist.addLong(readSFixed64());
                    if (!isAtEnd()) {
                        prevPos2 = this.pos;
                    } else {
                        return;
                    }
                } while (readVarint32() == this.tag);
                this.pos = prevPos2;
                return;
            }
            switch (WireFormat.getTagWireType(this.tag)) {
                case 1:
                    break;
                case 2:
                    int bytes2 = readVarint32();
                    verifyPackedFixed64Length(bytes2);
                    int fieldEndPos2 = this.pos + bytes2;
                    while (this.pos < fieldEndPos2) {
                        target.add(Long.valueOf(readLittleEndian64_NoCheck()));
                    }
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                target.add(Long.valueOf(readSFixed64()));
                if (!isAtEnd()) {
                    prevPos = this.pos;
                } else {
                    return;
                }
            } while (readVarint32() == this.tag);
            this.pos = prevPos;
        }

        public void readSInt32List(List<Integer> target) throws IOException {
            int prevPos;
            int prevPos2;
            if (target instanceof IntArrayList) {
                IntArrayList plist = (IntArrayList) target;
                switch (WireFormat.getTagWireType(this.tag)) {
                    case 0:
                        break;
                    case 2:
                        int fieldEndPos = this.pos + readVarint32();
                        while (this.pos < fieldEndPos) {
                            plist.addInt(CodedInputStream.decodeZigZag32(readVarint32()));
                        }
                        return;
                    default:
                        throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    plist.addInt(readSInt32());
                    if (!isAtEnd()) {
                        prevPos2 = this.pos;
                    } else {
                        return;
                    }
                } while (readVarint32() == this.tag);
                this.pos = prevPos2;
                return;
            }
            switch (WireFormat.getTagWireType(this.tag)) {
                case 0:
                    break;
                case 2:
                    int fieldEndPos2 = this.pos + readVarint32();
                    while (this.pos < fieldEndPos2) {
                        target.add(Integer.valueOf(CodedInputStream.decodeZigZag32(readVarint32())));
                    }
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                target.add(Integer.valueOf(readSInt32()));
                if (!isAtEnd()) {
                    prevPos = this.pos;
                } else {
                    return;
                }
            } while (readVarint32() == this.tag);
            this.pos = prevPos;
        }

        public void readSInt64List(List<Long> target) throws IOException {
            int prevPos;
            int prevPos2;
            if (target instanceof LongArrayList) {
                LongArrayList plist = (LongArrayList) target;
                switch (WireFormat.getTagWireType(this.tag)) {
                    case 0:
                        break;
                    case 2:
                        int fieldEndPos = this.pos + readVarint32();
                        while (this.pos < fieldEndPos) {
                            plist.addLong(CodedInputStream.decodeZigZag64(readVarint64()));
                        }
                        return;
                    default:
                        throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    plist.addLong(readSInt64());
                    if (!isAtEnd()) {
                        prevPos2 = this.pos;
                    } else {
                        return;
                    }
                } while (readVarint32() == this.tag);
                this.pos = prevPos2;
                return;
            }
            switch (WireFormat.getTagWireType(this.tag)) {
                case 0:
                    break;
                case 2:
                    int fieldEndPos2 = this.pos + readVarint32();
                    while (this.pos < fieldEndPos2) {
                        target.add(Long.valueOf(CodedInputStream.decodeZigZag64(readVarint64())));
                    }
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                target.add(Long.valueOf(readSInt64()));
                if (!isAtEnd()) {
                    prevPos = this.pos;
                } else {
                    return;
                }
            } while (readVarint32() == this.tag);
            this.pos = prevPos;
        }

        public <K, V> void readMap(Map<K, V> target, MapEntryLite.Metadata<K, V> metadata, ExtensionRegistryLite extensionRegistry) throws IOException {
            requireWireType(2);
            int size = readVarint32();
            requireBytes(size);
            int prevLimit = this.limit;
            this.limit = this.pos + size;
            try {
                K key = metadata.defaultKey;
                V value = metadata.defaultValue;
                while (true) {
                    int number = getFieldNumber();
                    if (number != Integer.MAX_VALUE) {
                        switch (number) {
                            case 1:
                                key = readField(metadata.keyType, (Class<?>) null, (ExtensionRegistryLite) null);
                                break;
                            case 2:
                                value = readField(metadata.valueType, metadata.defaultValue.getClass(), extensionRegistry);
                                break;
                            default:
                                if (!skipField()) {
                                    throw new InvalidProtocolBufferException("Unable to parse map entry.");
                                }
                                break;
                        }
                    } else {
                        target.put(key, value);
                        this.limit = prevLimit;
                        return;
                    }
                }
            } catch (InvalidProtocolBufferException.InvalidWireTypeException e) {
                if (!skipField()) {
                    throw new InvalidProtocolBufferException("Unable to parse map entry.");
                }
            } catch (Throwable key2) {
                this.limit = prevLimit;
                throw key2;
            }
        }

        private Object readField(WireFormat.FieldType fieldType, Class<?> messageType, ExtensionRegistryLite extensionRegistry) throws IOException {
            switch (C09851.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
                case 1:
                    return Boolean.valueOf(readBool());
                case 2:
                    return readBytes();
                case 3:
                    return Double.valueOf(readDouble());
                case 4:
                    return Integer.valueOf(readEnum());
                case 5:
                    return Integer.valueOf(readFixed32());
                case 6:
                    return Long.valueOf(readFixed64());
                case 7:
                    return Float.valueOf(readFloat());
                case 8:
                    return Integer.valueOf(readInt32());
                case 9:
                    return Long.valueOf(readInt64());
                case 10:
                    return readMessage(messageType, extensionRegistry);
                case 11:
                    return Integer.valueOf(readSFixed32());
                case 12:
                    return Long.valueOf(readSFixed64());
                case 13:
                    return Integer.valueOf(readSInt32());
                case 14:
                    return Long.valueOf(readSInt64());
                case 15:
                    return readStringRequireUtf8();
                case 16:
                    return Integer.valueOf(readUInt32());
                case 17:
                    return Long.valueOf(readUInt64());
                default:
                    throw new RuntimeException("unsupported field type.");
            }
        }

        private int readVarint32() throws IOException {
            int i;
            int i2 = this.pos;
            int i3 = this.limit;
            if (i3 != this.pos) {
                byte[] bArr = this.buffer;
                int i4 = i2 + 1;
                byte i5 = bArr[i2];
                int x = i5;
                if (i5 >= 0) {
                    this.pos = i4;
                    return x;
                } else if (i3 - i4 < 9) {
                    return (int) readVarint64SlowPath();
                } else {
                    int y = i4 + 1;
                    int i6 = (bArr[i4] << 7) ^ x;
                    int x2 = i6;
                    if (i6 < 0) {
                        i = x2 ^ -128;
                    } else {
                        int i7 = y + 1;
                        int i8 = (bArr[y] << Ascii.f63SO) ^ x2;
                        int x3 = i8;
                        if (i8 >= 0) {
                            int i9 = i7;
                            i = x3 ^ 16256;
                            y = i9;
                        } else {
                            y = i7 + 1;
                            int i10 = (bArr[i7] << Ascii.NAK) ^ x3;
                            int x4 = i10;
                            if (i10 < 0) {
                                i = -2080896 ^ x4;
                            } else {
                                int i11 = y + 1;
                                byte y2 = bArr[y];
                                int x5 = (x4 ^ (y2 << Ascii.f56FS)) ^ 266354560;
                                if (y2 < 0) {
                                    int i12 = i11 + 1;
                                    if (bArr[i11] < 0) {
                                        i11 = i12 + 1;
                                        if (bArr[i12] < 0) {
                                            i12 = i11 + 1;
                                            if (bArr[i11] < 0) {
                                                i11 = i12 + 1;
                                                if (bArr[i12] < 0) {
                                                    i12 = i11 + 1;
                                                    if (bArr[i11] < 0) {
                                                        throw InvalidProtocolBufferException.malformedVarint();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    i = x5;
                                    y = i12;
                                }
                                y = i11;
                                i = x5;
                            }
                        }
                    }
                    this.pos = y;
                    return i;
                }
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public long readVarint64() throws IOException {
            long x;
            int i = this.pos;
            int i2 = this.limit;
            if (i2 != i) {
                byte[] buffer2 = this.buffer;
                int i3 = i + 1;
                byte i4 = buffer2[i];
                int y = i4;
                if (i4 >= 0) {
                    this.pos = i3;
                    return (long) y;
                } else if (i2 - i3 < 9) {
                    return readVarint64SlowPath();
                } else {
                    int i5 = i3 + 1;
                    int i6 = (buffer2[i3] << 7) ^ y;
                    int y2 = i6;
                    if (i6 < 0) {
                        x = (long) (y2 ^ -128);
                    } else {
                        int i7 = i5 + 1;
                        int i8 = (buffer2[i5] << Ascii.f63SO) ^ y2;
                        int y3 = i8;
                        if (i8 >= 0) {
                            x = (long) (y3 ^ 16256);
                            i5 = i7;
                        } else {
                            i5 = i7 + 1;
                            int i9 = (buffer2[i7] << Ascii.NAK) ^ y3;
                            int y4 = i9;
                            if (i9 < 0) {
                                x = (long) (-2080896 ^ y4);
                            } else {
                                int i10 = i5 + 1;
                                long j = ((long) y4) ^ (((long) buffer2[i5]) << 28);
                                long x2 = j;
                                if (j >= 0) {
                                    x = 266354560 ^ x2;
                                    i5 = i10;
                                } else {
                                    i5 = i10 + 1;
                                    long j2 = (((long) buffer2[i10]) << 35) ^ x2;
                                    long x3 = j2;
                                    if (j2 < 0) {
                                        x = -34093383808L ^ x3;
                                    } else {
                                        int i11 = i5 + 1;
                                        long j3 = (((long) buffer2[i5]) << 42) ^ x3;
                                        long x4 = j3;
                                        if (j3 >= 0) {
                                            x = 4363953127296L ^ x4;
                                            i5 = i11;
                                        } else {
                                            i5 = i11 + 1;
                                            long j4 = (((long) buffer2[i11]) << 49) ^ x4;
                                            long x5 = j4;
                                            if (j4 < 0) {
                                                x = -558586000294016L ^ x5;
                                            } else {
                                                int i12 = i5 + 1;
                                                x = ((((long) buffer2[i5]) << 56) ^ x5) ^ 71499008037633920L;
                                                if (x < 0) {
                                                    i5 = i12 + 1;
                                                    if (((long) buffer2[i12]) < 0) {
                                                        throw InvalidProtocolBufferException.malformedVarint();
                                                    }
                                                } else {
                                                    i5 = i12;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    this.pos = i5;
                    return x;
                }
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        private long readVarint64SlowPath() throws IOException {
            long result = 0;
            for (int shift = 0; shift < 64; shift += 7) {
                byte b = readByte();
                result |= ((long) (b & Ascii.DEL)) << shift;
                if ((b & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                    return result;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private byte readByte() throws IOException {
            int i = this.pos;
            if (i != this.limit) {
                byte[] bArr = this.buffer;
                this.pos = i + 1;
                return bArr[i];
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        private int readLittleEndian32() throws IOException {
            requireBytes(4);
            return readLittleEndian32_NoCheck();
        }

        private long readLittleEndian64() throws IOException {
            requireBytes(8);
            return readLittleEndian64_NoCheck();
        }

        private int readLittleEndian32_NoCheck() {
            int p = this.pos;
            byte[] buffer2 = this.buffer;
            this.pos = p + 4;
            return (buffer2[p] & 255) | ((buffer2[p + 1] & 255) << 8) | ((buffer2[p + 2] & 255) << Ascii.DLE) | ((buffer2[p + 3] & 255) << Ascii.CAN);
        }

        private long readLittleEndian64_NoCheck() {
            int p = this.pos;
            byte[] buffer2 = this.buffer;
            this.pos = p + 8;
            return (((long) buffer2[p]) & 255) | ((((long) buffer2[p + 1]) & 255) << 8) | ((((long) buffer2[p + 2]) & 255) << 16) | ((((long) buffer2[p + 3]) & 255) << 24) | ((((long) buffer2[p + 4]) & 255) << 32) | ((((long) buffer2[p + 5]) & 255) << 40) | ((((long) buffer2[p + 6]) & 255) << 48) | ((255 & ((long) buffer2[p + 7])) << 56);
        }

        private void skipVarint() throws IOException {
            if (this.limit - this.pos >= 10) {
                byte[] buffer2 = this.buffer;
                int p = this.pos;
                int i = 0;
                while (i < 10) {
                    int p2 = p + 1;
                    if (buffer2[p] >= 0) {
                        this.pos = p2;
                        return;
                    } else {
                        i++;
                        p = p2;
                    }
                }
            }
            skipVarintSlowPath();
        }

        private void skipVarintSlowPath() throws IOException {
            int i = 0;
            while (i < 10) {
                if (readByte() < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipBytes(int size) throws IOException {
            requireBytes(size);
            this.pos += size;
        }

        /* JADX WARNING: Removed duplicated region for block: B:1:0x000f A[LOOP:0: B:1:0x000f->B:4:0x001c, LOOP_START] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void skipGroup() throws java.io.IOException {
            /*
                r3 = this;
                int r0 = r3.endGroupTag
                int r1 = r3.tag
                int r1 = com.google.protobuf.WireFormat.getTagFieldNumber(r1)
                r2 = 4
                int r1 = com.google.protobuf.WireFormat.makeTag(r1, r2)
                r3.endGroupTag = r1
            L_0x000f:
                int r1 = r3.getFieldNumber()
                r2 = 2147483647(0x7fffffff, float:NaN)
                if (r1 == r2) goto L_0x001e
                boolean r1 = r3.skipField()
                if (r1 != 0) goto L_0x000f
            L_0x001e:
                int r1 = r3.tag
                int r2 = r3.endGroupTag
                if (r1 != r2) goto L_0x0027
                r3.endGroupTag = r0
                return
            L_0x0027:
                com.google.protobuf.InvalidProtocolBufferException r1 = com.google.protobuf.InvalidProtocolBufferException.parseFailure()
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.BinaryReader.SafeHeapReader.skipGroup():void");
        }

        private void requireBytes(int size) throws IOException {
            if (size < 0 || size > this.limit - this.pos) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        private void requireWireType(int requiredWireType) throws IOException {
            if (WireFormat.getTagWireType(this.tag) != requiredWireType) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        private void verifyPackedFixed64Length(int bytes) throws IOException {
            requireBytes(bytes);
            if ((bytes & 7) != 0) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        }

        private void verifyPackedFixed32Length(int bytes) throws IOException {
            requireBytes(bytes);
            if ((bytes & 3) != 0) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        }

        private void requirePosition(int expectedPosition) throws IOException {
            if (this.pos != expectedPosition) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }
    }

    /* renamed from: com.google.protobuf.BinaryReader$1 */
    static /* synthetic */ class C09851 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
        }
    }
}
