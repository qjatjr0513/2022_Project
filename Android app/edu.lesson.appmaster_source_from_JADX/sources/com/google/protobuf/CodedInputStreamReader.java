package com.google.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.util.List;
import java.util.Map;

final class CodedInputStreamReader implements Reader {
    private static final int FIXED32_MULTIPLE_MASK = 3;
    private static final int FIXED64_MULTIPLE_MASK = 7;
    private static final int NEXT_TAG_UNSET = 0;
    private int endGroupTag;
    private final CodedInputStream input;
    private int nextTag = 0;
    private int tag;

    public static CodedInputStreamReader forCodedInput(CodedInputStream input2) {
        if (input2.wrapper != null) {
            return input2.wrapper;
        }
        return new CodedInputStreamReader(input2);
    }

    private CodedInputStreamReader(CodedInputStream input2) {
        CodedInputStream codedInputStream = (CodedInputStream) Internal.checkNotNull(input2, "input");
        this.input = codedInputStream;
        codedInputStream.wrapper = this;
    }

    public boolean shouldDiscardUnknownFields() {
        return this.input.shouldDiscardUnknownFields();
    }

    public int getFieldNumber() throws IOException {
        int i = this.nextTag;
        if (i != 0) {
            this.tag = i;
            this.nextTag = 0;
        } else {
            this.tag = this.input.readTag();
        }
        int i2 = this.tag;
        if (i2 == 0 || i2 == this.endGroupTag) {
            return Integer.MAX_VALUE;
        }
        return WireFormat.getTagFieldNumber(i2);
    }

    public int getTag() {
        return this.tag;
    }

    public boolean skipField() throws IOException {
        int i;
        if (this.input.isAtEnd() || (i = this.tag) == this.endGroupTag) {
            return false;
        }
        return this.input.skipField(i);
    }

    private void requireWireType(int requiredWireType) throws IOException {
        if (WireFormat.getTagWireType(this.tag) != requiredWireType) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    public double readDouble() throws IOException {
        requireWireType(1);
        return this.input.readDouble();
    }

    public float readFloat() throws IOException {
        requireWireType(5);
        return this.input.readFloat();
    }

    public long readUInt64() throws IOException {
        requireWireType(0);
        return this.input.readUInt64();
    }

    public long readInt64() throws IOException {
        requireWireType(0);
        return this.input.readInt64();
    }

    public int readInt32() throws IOException {
        requireWireType(0);
        return this.input.readInt32();
    }

    public long readFixed64() throws IOException {
        requireWireType(1);
        return this.input.readFixed64();
    }

    public int readFixed32() throws IOException {
        requireWireType(5);
        return this.input.readFixed32();
    }

    public boolean readBool() throws IOException {
        requireWireType(0);
        return this.input.readBool();
    }

    public String readString() throws IOException {
        requireWireType(2);
        return this.input.readString();
    }

    public String readStringRequireUtf8() throws IOException {
        requireWireType(2);
        return this.input.readStringRequireUtf8();
    }

    public <T> T readMessage(Class<T> clazz, ExtensionRegistryLite extensionRegistry) throws IOException {
        requireWireType(2);
        return readMessage(Protobuf.getInstance().schemaFor(clazz), extensionRegistry);
    }

    public <T> T readMessageBySchemaWithCheck(Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        requireWireType(2);
        return readMessage(schema, extensionRegistry);
    }

    public <T> T readGroup(Class<T> clazz, ExtensionRegistryLite extensionRegistry) throws IOException {
        requireWireType(3);
        return readGroup(Protobuf.getInstance().schemaFor(clazz), extensionRegistry);
    }

    public <T> T readGroupBySchemaWithCheck(Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        requireWireType(3);
        return readGroup(schema, extensionRegistry);
    }

    private <T> T readMessage(Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        int size = this.input.readUInt32();
        if (this.input.recursionDepth < this.input.recursionLimit) {
            int prevLimit = this.input.pushLimit(size);
            T message = schema.newInstance();
            this.input.recursionDepth++;
            schema.mergeFrom(message, this, extensionRegistry);
            schema.makeImmutable(message);
            this.input.checkLastTagWas(0);
            CodedInputStream codedInputStream = this.input;
            codedInputStream.recursionDepth--;
            this.input.popLimit(prevLimit);
            return message;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
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
        requireWireType(2);
        return this.input.readBytes();
    }

    public int readUInt32() throws IOException {
        requireWireType(0);
        return this.input.readUInt32();
    }

    public int readEnum() throws IOException {
        requireWireType(0);
        return this.input.readEnum();
    }

    public int readSFixed32() throws IOException {
        requireWireType(5);
        return this.input.readSFixed32();
    }

    public long readSFixed64() throws IOException {
        requireWireType(1);
        return this.input.readSFixed64();
    }

    public int readSInt32() throws IOException {
        requireWireType(0);
        return this.input.readSInt32();
    }

    public long readSInt64() throws IOException {
        requireWireType(0);
        return this.input.readSInt64();
    }

    public void readDoubleList(List<Double> target) throws IOException {
        int nextTag2;
        int nextTag3;
        if (target instanceof DoubleArrayList) {
            DoubleArrayList plist = (DoubleArrayList) target;
            switch (WireFormat.getTagWireType(this.tag)) {
                case 1:
                    break;
                case 2:
                    int bytes = this.input.readUInt32();
                    verifyPackedFixed64Length(bytes);
                    int endPos = this.input.getTotalBytesRead() + bytes;
                    do {
                        plist.addDouble(this.input.readDouble());
                    } while (this.input.getTotalBytesRead() < endPos);
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                plist.addDouble(this.input.readDouble());
                if (!this.input.isAtEnd()) {
                    nextTag3 = this.input.readTag();
                } else {
                    return;
                }
            } while (nextTag3 == this.tag);
            this.nextTag = nextTag3;
            return;
        }
        switch (WireFormat.getTagWireType(this.tag)) {
            case 1:
                break;
            case 2:
                int bytes2 = this.input.readUInt32();
                verifyPackedFixed64Length(bytes2);
                int endPos2 = this.input.getTotalBytesRead() + bytes2;
                do {
                    target.add(Double.valueOf(this.input.readDouble()));
                } while (this.input.getTotalBytesRead() < endPos2);
                return;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Double.valueOf(this.input.readDouble()));
            if (!this.input.isAtEnd()) {
                nextTag2 = this.input.readTag();
            } else {
                return;
            }
        } while (nextTag2 == this.tag);
        this.nextTag = nextTag2;
    }

    public void readFloatList(List<Float> target) throws IOException {
        int nextTag2;
        int nextTag3;
        if (target instanceof FloatArrayList) {
            FloatArrayList plist = (FloatArrayList) target;
            switch (WireFormat.getTagWireType(this.tag)) {
                case 2:
                    int bytes = this.input.readUInt32();
                    verifyPackedFixed32Length(bytes);
                    int endPos = this.input.getTotalBytesRead() + bytes;
                    do {
                        plist.addFloat(this.input.readFloat());
                    } while (this.input.getTotalBytesRead() < endPos);
                    return;
                case 5:
                    break;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                plist.addFloat(this.input.readFloat());
                if (!this.input.isAtEnd()) {
                    nextTag3 = this.input.readTag();
                } else {
                    return;
                }
            } while (nextTag3 == this.tag);
            this.nextTag = nextTag3;
            return;
        }
        switch (WireFormat.getTagWireType(this.tag)) {
            case 2:
                int bytes2 = this.input.readUInt32();
                verifyPackedFixed32Length(bytes2);
                int endPos2 = this.input.getTotalBytesRead() + bytes2;
                do {
                    target.add(Float.valueOf(this.input.readFloat()));
                } while (this.input.getTotalBytesRead() < endPos2);
                return;
            case 5:
                break;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Float.valueOf(this.input.readFloat()));
            if (!this.input.isAtEnd()) {
                nextTag2 = this.input.readTag();
            } else {
                return;
            }
        } while (nextTag2 == this.tag);
        this.nextTag = nextTag2;
    }

    public void readUInt64List(List<Long> target) throws IOException {
        int nextTag2;
        int nextTag3;
        if (target instanceof LongArrayList) {
            LongArrayList plist = (LongArrayList) target;
            switch (WireFormat.getTagWireType(this.tag)) {
                case 0:
                    break;
                case 2:
                    int endPos = this.input.getTotalBytesRead() + this.input.readUInt32();
                    do {
                        plist.addLong(this.input.readUInt64());
                    } while (this.input.getTotalBytesRead() < endPos);
                    requirePosition(endPos);
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                plist.addLong(this.input.readUInt64());
                if (!this.input.isAtEnd()) {
                    nextTag3 = this.input.readTag();
                } else {
                    return;
                }
            } while (nextTag3 == this.tag);
            this.nextTag = nextTag3;
            return;
        }
        switch (WireFormat.getTagWireType(this.tag)) {
            case 0:
                break;
            case 2:
                int endPos2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    target.add(Long.valueOf(this.input.readUInt64()));
                } while (this.input.getTotalBytesRead() < endPos2);
                requirePosition(endPos2);
                return;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Long.valueOf(this.input.readUInt64()));
            if (!this.input.isAtEnd()) {
                nextTag2 = this.input.readTag();
            } else {
                return;
            }
        } while (nextTag2 == this.tag);
        this.nextTag = nextTag2;
    }

    public void readInt64List(List<Long> target) throws IOException {
        int nextTag2;
        int nextTag3;
        if (target instanceof LongArrayList) {
            LongArrayList plist = (LongArrayList) target;
            switch (WireFormat.getTagWireType(this.tag)) {
                case 0:
                    break;
                case 2:
                    int endPos = this.input.getTotalBytesRead() + this.input.readUInt32();
                    do {
                        plist.addLong(this.input.readInt64());
                    } while (this.input.getTotalBytesRead() < endPos);
                    requirePosition(endPos);
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                plist.addLong(this.input.readInt64());
                if (!this.input.isAtEnd()) {
                    nextTag3 = this.input.readTag();
                } else {
                    return;
                }
            } while (nextTag3 == this.tag);
            this.nextTag = nextTag3;
            return;
        }
        switch (WireFormat.getTagWireType(this.tag)) {
            case 0:
                break;
            case 2:
                int endPos2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    target.add(Long.valueOf(this.input.readInt64()));
                } while (this.input.getTotalBytesRead() < endPos2);
                requirePosition(endPos2);
                return;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Long.valueOf(this.input.readInt64()));
            if (!this.input.isAtEnd()) {
                nextTag2 = this.input.readTag();
            } else {
                return;
            }
        } while (nextTag2 == this.tag);
        this.nextTag = nextTag2;
    }

    public void readInt32List(List<Integer> target) throws IOException {
        int nextTag2;
        int nextTag3;
        if (target instanceof IntArrayList) {
            IntArrayList plist = (IntArrayList) target;
            switch (WireFormat.getTagWireType(this.tag)) {
                case 0:
                    break;
                case 2:
                    int endPos = this.input.getTotalBytesRead() + this.input.readUInt32();
                    do {
                        plist.addInt(this.input.readInt32());
                    } while (this.input.getTotalBytesRead() < endPos);
                    requirePosition(endPos);
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                plist.addInt(this.input.readInt32());
                if (!this.input.isAtEnd()) {
                    nextTag3 = this.input.readTag();
                } else {
                    return;
                }
            } while (nextTag3 == this.tag);
            this.nextTag = nextTag3;
            return;
        }
        switch (WireFormat.getTagWireType(this.tag)) {
            case 0:
                break;
            case 2:
                int endPos2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    target.add(Integer.valueOf(this.input.readInt32()));
                } while (this.input.getTotalBytesRead() < endPos2);
                requirePosition(endPos2);
                return;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Integer.valueOf(this.input.readInt32()));
            if (!this.input.isAtEnd()) {
                nextTag2 = this.input.readTag();
            } else {
                return;
            }
        } while (nextTag2 == this.tag);
        this.nextTag = nextTag2;
    }

    public void readFixed64List(List<Long> target) throws IOException {
        int nextTag2;
        int nextTag3;
        if (target instanceof LongArrayList) {
            LongArrayList plist = (LongArrayList) target;
            switch (WireFormat.getTagWireType(this.tag)) {
                case 1:
                    break;
                case 2:
                    int bytes = this.input.readUInt32();
                    verifyPackedFixed64Length(bytes);
                    int endPos = this.input.getTotalBytesRead() + bytes;
                    do {
                        plist.addLong(this.input.readFixed64());
                    } while (this.input.getTotalBytesRead() < endPos);
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                plist.addLong(this.input.readFixed64());
                if (!this.input.isAtEnd()) {
                    nextTag3 = this.input.readTag();
                } else {
                    return;
                }
            } while (nextTag3 == this.tag);
            this.nextTag = nextTag3;
            return;
        }
        switch (WireFormat.getTagWireType(this.tag)) {
            case 1:
                break;
            case 2:
                int bytes2 = this.input.readUInt32();
                verifyPackedFixed64Length(bytes2);
                int endPos2 = this.input.getTotalBytesRead() + bytes2;
                do {
                    target.add(Long.valueOf(this.input.readFixed64()));
                } while (this.input.getTotalBytesRead() < endPos2);
                return;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Long.valueOf(this.input.readFixed64()));
            if (!this.input.isAtEnd()) {
                nextTag2 = this.input.readTag();
            } else {
                return;
            }
        } while (nextTag2 == this.tag);
        this.nextTag = nextTag2;
    }

    public void readFixed32List(List<Integer> target) throws IOException {
        int nextTag2;
        int nextTag3;
        if (target instanceof IntArrayList) {
            IntArrayList plist = (IntArrayList) target;
            switch (WireFormat.getTagWireType(this.tag)) {
                case 2:
                    int bytes = this.input.readUInt32();
                    verifyPackedFixed32Length(bytes);
                    int endPos = this.input.getTotalBytesRead() + bytes;
                    do {
                        plist.addInt(this.input.readFixed32());
                    } while (this.input.getTotalBytesRead() < endPos);
                    return;
                case 5:
                    break;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                plist.addInt(this.input.readFixed32());
                if (!this.input.isAtEnd()) {
                    nextTag3 = this.input.readTag();
                } else {
                    return;
                }
            } while (nextTag3 == this.tag);
            this.nextTag = nextTag3;
            return;
        }
        switch (WireFormat.getTagWireType(this.tag)) {
            case 2:
                int bytes2 = this.input.readUInt32();
                verifyPackedFixed32Length(bytes2);
                int endPos2 = this.input.getTotalBytesRead() + bytes2;
                do {
                    target.add(Integer.valueOf(this.input.readFixed32()));
                } while (this.input.getTotalBytesRead() < endPos2);
                return;
            case 5:
                break;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Integer.valueOf(this.input.readFixed32()));
            if (!this.input.isAtEnd()) {
                nextTag2 = this.input.readTag();
            } else {
                return;
            }
        } while (nextTag2 == this.tag);
        this.nextTag = nextTag2;
    }

    public void readBoolList(List<Boolean> target) throws IOException {
        int nextTag2;
        int nextTag3;
        if (target instanceof BooleanArrayList) {
            BooleanArrayList plist = (BooleanArrayList) target;
            switch (WireFormat.getTagWireType(this.tag)) {
                case 0:
                    break;
                case 2:
                    int endPos = this.input.getTotalBytesRead() + this.input.readUInt32();
                    do {
                        plist.addBoolean(this.input.readBool());
                    } while (this.input.getTotalBytesRead() < endPos);
                    requirePosition(endPos);
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                plist.addBoolean(this.input.readBool());
                if (!this.input.isAtEnd()) {
                    nextTag3 = this.input.readTag();
                } else {
                    return;
                }
            } while (nextTag3 == this.tag);
            this.nextTag = nextTag3;
            return;
        }
        switch (WireFormat.getTagWireType(this.tag)) {
            case 0:
                break;
            case 2:
                int endPos2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    target.add(Boolean.valueOf(this.input.readBool()));
                } while (this.input.getTotalBytesRead() < endPos2);
                requirePosition(endPos2);
                return;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Boolean.valueOf(this.input.readBool()));
            if (!this.input.isAtEnd()) {
                nextTag2 = this.input.readTag();
            } else {
                return;
            }
        } while (nextTag2 == this.tag);
        this.nextTag = nextTag2;
    }

    public void readStringList(List<String> target) throws IOException {
        readStringListInternal(target, false);
    }

    public void readStringListRequireUtf8(List<String> target) throws IOException {
        readStringListInternal(target, true);
    }

    public void readStringListInternal(List<String> target, boolean requireUtf8) throws IOException {
        int nextTag2;
        int nextTag3;
        if (WireFormat.getTagWireType(this.tag) != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        } else if (!(target instanceof LazyStringList) || requireUtf8) {
            do {
                target.add(requireUtf8 ? readStringRequireUtf8() : readString());
                if (!this.input.isAtEnd()) {
                    nextTag2 = this.input.readTag();
                } else {
                    return;
                }
            } while (nextTag2 == this.tag);
            this.nextTag = nextTag2;
        } else {
            LazyStringList lazyList = (LazyStringList) target;
            do {
                lazyList.add(readBytes());
                if (!this.input.isAtEnd()) {
                    nextTag3 = this.input.readTag();
                } else {
                    return;
                }
            } while (nextTag3 == this.tag);
            this.nextTag = nextTag3;
        }
    }

    public <T> void readMessageList(List<T> target, Class<T> targetType, ExtensionRegistryLite extensionRegistry) throws IOException {
        readMessageList(target, Protobuf.getInstance().schemaFor(targetType), extensionRegistry);
    }

    public <T> void readMessageList(List<T> target, Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        int nextTag2;
        if (WireFormat.getTagWireType(this.tag) == 2) {
            int listTag = this.tag;
            do {
                target.add(readMessage(schema, extensionRegistry));
                if (!this.input.isAtEnd() && this.nextTag == 0) {
                    nextTag2 = this.input.readTag();
                } else {
                    return;
                }
            } while (nextTag2 == listTag);
            this.nextTag = nextTag2;
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    public <T> void readGroupList(List<T> target, Class<T> targetType, ExtensionRegistryLite extensionRegistry) throws IOException {
        readGroupList(target, Protobuf.getInstance().schemaFor(targetType), extensionRegistry);
    }

    public <T> void readGroupList(List<T> target, Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        int nextTag2;
        if (WireFormat.getTagWireType(this.tag) == 3) {
            int listTag = this.tag;
            do {
                target.add(readGroup(schema, extensionRegistry));
                if (!this.input.isAtEnd() && this.nextTag == 0) {
                    nextTag2 = this.input.readTag();
                } else {
                    return;
                }
            } while (nextTag2 == listTag);
            this.nextTag = nextTag2;
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    public void readBytesList(List<ByteString> target) throws IOException {
        int nextTag2;
        if (WireFormat.getTagWireType(this.tag) == 2) {
            do {
                target.add(readBytes());
                if (!this.input.isAtEnd()) {
                    nextTag2 = this.input.readTag();
                } else {
                    return;
                }
            } while (nextTag2 == this.tag);
            this.nextTag = nextTag2;
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    public void readUInt32List(List<Integer> target) throws IOException {
        int nextTag2;
        int nextTag3;
        if (target instanceof IntArrayList) {
            IntArrayList plist = (IntArrayList) target;
            switch (WireFormat.getTagWireType(this.tag)) {
                case 0:
                    break;
                case 2:
                    int endPos = this.input.getTotalBytesRead() + this.input.readUInt32();
                    do {
                        plist.addInt(this.input.readUInt32());
                    } while (this.input.getTotalBytesRead() < endPos);
                    requirePosition(endPos);
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                plist.addInt(this.input.readUInt32());
                if (!this.input.isAtEnd()) {
                    nextTag3 = this.input.readTag();
                } else {
                    return;
                }
            } while (nextTag3 == this.tag);
            this.nextTag = nextTag3;
            return;
        }
        switch (WireFormat.getTagWireType(this.tag)) {
            case 0:
                break;
            case 2:
                int endPos2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    target.add(Integer.valueOf(this.input.readUInt32()));
                } while (this.input.getTotalBytesRead() < endPos2);
                requirePosition(endPos2);
                return;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Integer.valueOf(this.input.readUInt32()));
            if (!this.input.isAtEnd()) {
                nextTag2 = this.input.readTag();
            } else {
                return;
            }
        } while (nextTag2 == this.tag);
        this.nextTag = nextTag2;
    }

    public void readEnumList(List<Integer> target) throws IOException {
        int nextTag2;
        int nextTag3;
        if (target instanceof IntArrayList) {
            IntArrayList plist = (IntArrayList) target;
            switch (WireFormat.getTagWireType(this.tag)) {
                case 0:
                    break;
                case 2:
                    int endPos = this.input.getTotalBytesRead() + this.input.readUInt32();
                    do {
                        plist.addInt(this.input.readEnum());
                    } while (this.input.getTotalBytesRead() < endPos);
                    requirePosition(endPos);
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                plist.addInt(this.input.readEnum());
                if (!this.input.isAtEnd()) {
                    nextTag3 = this.input.readTag();
                } else {
                    return;
                }
            } while (nextTag3 == this.tag);
            this.nextTag = nextTag3;
            return;
        }
        switch (WireFormat.getTagWireType(this.tag)) {
            case 0:
                break;
            case 2:
                int endPos2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    target.add(Integer.valueOf(this.input.readEnum()));
                } while (this.input.getTotalBytesRead() < endPos2);
                requirePosition(endPos2);
                return;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Integer.valueOf(this.input.readEnum()));
            if (!this.input.isAtEnd()) {
                nextTag2 = this.input.readTag();
            } else {
                return;
            }
        } while (nextTag2 == this.tag);
        this.nextTag = nextTag2;
    }

    public void readSFixed32List(List<Integer> target) throws IOException {
        int nextTag2;
        int nextTag3;
        if (target instanceof IntArrayList) {
            IntArrayList plist = (IntArrayList) target;
            switch (WireFormat.getTagWireType(this.tag)) {
                case 2:
                    int bytes = this.input.readUInt32();
                    verifyPackedFixed32Length(bytes);
                    int endPos = this.input.getTotalBytesRead() + bytes;
                    do {
                        plist.addInt(this.input.readSFixed32());
                    } while (this.input.getTotalBytesRead() < endPos);
                    return;
                case 5:
                    break;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                plist.addInt(this.input.readSFixed32());
                if (!this.input.isAtEnd()) {
                    nextTag3 = this.input.readTag();
                } else {
                    return;
                }
            } while (nextTag3 == this.tag);
            this.nextTag = nextTag3;
            return;
        }
        switch (WireFormat.getTagWireType(this.tag)) {
            case 2:
                int bytes2 = this.input.readUInt32();
                verifyPackedFixed32Length(bytes2);
                int endPos2 = this.input.getTotalBytesRead() + bytes2;
                do {
                    target.add(Integer.valueOf(this.input.readSFixed32()));
                } while (this.input.getTotalBytesRead() < endPos2);
                return;
            case 5:
                break;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Integer.valueOf(this.input.readSFixed32()));
            if (!this.input.isAtEnd()) {
                nextTag2 = this.input.readTag();
            } else {
                return;
            }
        } while (nextTag2 == this.tag);
        this.nextTag = nextTag2;
    }

    public void readSFixed64List(List<Long> target) throws IOException {
        int nextTag2;
        int nextTag3;
        if (target instanceof LongArrayList) {
            LongArrayList plist = (LongArrayList) target;
            switch (WireFormat.getTagWireType(this.tag)) {
                case 1:
                    break;
                case 2:
                    int bytes = this.input.readUInt32();
                    verifyPackedFixed64Length(bytes);
                    int endPos = this.input.getTotalBytesRead() + bytes;
                    do {
                        plist.addLong(this.input.readSFixed64());
                    } while (this.input.getTotalBytesRead() < endPos);
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                plist.addLong(this.input.readSFixed64());
                if (!this.input.isAtEnd()) {
                    nextTag3 = this.input.readTag();
                } else {
                    return;
                }
            } while (nextTag3 == this.tag);
            this.nextTag = nextTag3;
            return;
        }
        switch (WireFormat.getTagWireType(this.tag)) {
            case 1:
                break;
            case 2:
                int bytes2 = this.input.readUInt32();
                verifyPackedFixed64Length(bytes2);
                int endPos2 = this.input.getTotalBytesRead() + bytes2;
                do {
                    target.add(Long.valueOf(this.input.readSFixed64()));
                } while (this.input.getTotalBytesRead() < endPos2);
                return;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Long.valueOf(this.input.readSFixed64()));
            if (!this.input.isAtEnd()) {
                nextTag2 = this.input.readTag();
            } else {
                return;
            }
        } while (nextTag2 == this.tag);
        this.nextTag = nextTag2;
    }

    public void readSInt32List(List<Integer> target) throws IOException {
        int nextTag2;
        int nextTag3;
        if (target instanceof IntArrayList) {
            IntArrayList plist = (IntArrayList) target;
            switch (WireFormat.getTagWireType(this.tag)) {
                case 0:
                    break;
                case 2:
                    int endPos = this.input.getTotalBytesRead() + this.input.readUInt32();
                    do {
                        plist.addInt(this.input.readSInt32());
                    } while (this.input.getTotalBytesRead() < endPos);
                    requirePosition(endPos);
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                plist.addInt(this.input.readSInt32());
                if (!this.input.isAtEnd()) {
                    nextTag3 = this.input.readTag();
                } else {
                    return;
                }
            } while (nextTag3 == this.tag);
            this.nextTag = nextTag3;
            return;
        }
        switch (WireFormat.getTagWireType(this.tag)) {
            case 0:
                break;
            case 2:
                int endPos2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    target.add(Integer.valueOf(this.input.readSInt32()));
                } while (this.input.getTotalBytesRead() < endPos2);
                requirePosition(endPos2);
                return;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Integer.valueOf(this.input.readSInt32()));
            if (!this.input.isAtEnd()) {
                nextTag2 = this.input.readTag();
            } else {
                return;
            }
        } while (nextTag2 == this.tag);
        this.nextTag = nextTag2;
    }

    public void readSInt64List(List<Long> target) throws IOException {
        int nextTag2;
        int nextTag3;
        if (target instanceof LongArrayList) {
            LongArrayList plist = (LongArrayList) target;
            switch (WireFormat.getTagWireType(this.tag)) {
                case 0:
                    break;
                case 2:
                    int endPos = this.input.getTotalBytesRead() + this.input.readUInt32();
                    do {
                        plist.addLong(this.input.readSInt64());
                    } while (this.input.getTotalBytesRead() < endPos);
                    requirePosition(endPos);
                    return;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                plist.addLong(this.input.readSInt64());
                if (!this.input.isAtEnd()) {
                    nextTag3 = this.input.readTag();
                } else {
                    return;
                }
            } while (nextTag3 == this.tag);
            this.nextTag = nextTag3;
            return;
        }
        switch (WireFormat.getTagWireType(this.tag)) {
            case 0:
                break;
            case 2:
                int endPos2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    target.add(Long.valueOf(this.input.readSInt64()));
                } while (this.input.getTotalBytesRead() < endPos2);
                requirePosition(endPos2);
                return;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Long.valueOf(this.input.readSInt64()));
            if (!this.input.isAtEnd()) {
                nextTag2 = this.input.readTag();
            } else {
                return;
            }
        } while (nextTag2 == this.tag);
        this.nextTag = nextTag2;
    }

    private void verifyPackedFixed64Length(int bytes) throws IOException {
        if ((bytes & 7) != 0) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }

    public <K, V> void readMap(Map<K, V> target, MapEntryLite.Metadata<K, V> metadata, ExtensionRegistryLite extensionRegistry) throws IOException {
        requireWireType(2);
        int prevLimit = this.input.pushLimit(this.input.readUInt32());
        K key = metadata.defaultKey;
        V value = metadata.defaultValue;
        while (true) {
            try {
                int number = getFieldNumber();
                if (number != Integer.MAX_VALUE && !this.input.isAtEnd()) {
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
                }
            } catch (InvalidProtocolBufferException.InvalidWireTypeException e) {
                if (!skipField()) {
                    throw new InvalidProtocolBufferException("Unable to parse map entry.");
                }
            } catch (Throwable th) {
                this.input.popLimit(prevLimit);
                throw th;
            }
        }
        target.put(key, value);
        this.input.popLimit(prevLimit);
    }

    /* renamed from: com.google.protobuf.CodedInputStreamReader$1 */
    static /* synthetic */ class C09931 {
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

    private Object readField(WireFormat.FieldType fieldType, Class<?> messageType, ExtensionRegistryLite extensionRegistry) throws IOException {
        switch (C09931.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
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

    private void verifyPackedFixed32Length(int bytes) throws IOException {
        if ((bytes & 3) != 0) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }

    private void requirePosition(int expectedPosition) throws IOException {
        if (this.input.getTotalBytesRead() != expectedPosition) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }
}
