package com.google.rpc;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class DebugInfo extends GeneratedMessageLite<DebugInfo, Builder> implements DebugInfoOrBuilder {
    /* access modifiers changed from: private */
    public static final DebugInfo DEFAULT_INSTANCE;
    public static final int DETAIL_FIELD_NUMBER = 2;
    private static volatile Parser<DebugInfo> PARSER = null;
    public static final int STACK_ENTRIES_FIELD_NUMBER = 1;
    private String detail_ = "";
    private Internal.ProtobufList<String> stackEntries_ = GeneratedMessageLite.emptyProtobufList();

    private DebugInfo() {
    }

    public List<String> getStackEntriesList() {
        return this.stackEntries_;
    }

    public int getStackEntriesCount() {
        return this.stackEntries_.size();
    }

    public String getStackEntries(int index) {
        return (String) this.stackEntries_.get(index);
    }

    public ByteString getStackEntriesBytes(int index) {
        return ByteString.copyFromUtf8((String) this.stackEntries_.get(index));
    }

    private void ensureStackEntriesIsMutable() {
        Internal.ProtobufList<String> tmp = this.stackEntries_;
        if (!tmp.isModifiable()) {
            this.stackEntries_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setStackEntries(int index, String value) {
        value.getClass();
        ensureStackEntriesIsMutable();
        this.stackEntries_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addStackEntries(String value) {
        value.getClass();
        ensureStackEntriesIsMutable();
        this.stackEntries_.add(value);
    }

    /* access modifiers changed from: private */
    public void addAllStackEntries(Iterable<String> values) {
        ensureStackEntriesIsMutable();
        AbstractMessageLite.addAll(values, this.stackEntries_);
    }

    /* access modifiers changed from: private */
    public void clearStackEntries() {
        this.stackEntries_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void addStackEntriesBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        ensureStackEntriesIsMutable();
        this.stackEntries_.add(value.toStringUtf8());
    }

    public String getDetail() {
        return this.detail_;
    }

    public ByteString getDetailBytes() {
        return ByteString.copyFromUtf8(this.detail_);
    }

    /* access modifiers changed from: private */
    public void setDetail(String value) {
        value.getClass();
        this.detail_ = value;
    }

    /* access modifiers changed from: private */
    public void clearDetail() {
        this.detail_ = getDefaultInstance().getDetail();
    }

    /* access modifiers changed from: private */
    public void setDetailBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.detail_ = value.toStringUtf8();
    }

    public static DebugInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DebugInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DebugInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DebugInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DebugInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DebugInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DebugInfo parseFrom(InputStream input) throws IOException {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DebugInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DebugInfo parseDelimitedFrom(InputStream input) throws IOException {
        return (DebugInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static DebugInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DebugInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DebugInfo parseFrom(CodedInputStream input) throws IOException {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DebugInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(DebugInfo prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DebugInfo, Builder> implements DebugInfoOrBuilder {
        /* synthetic */ Builder(C10611 x0) {
            this();
        }

        private Builder() {
            super(DebugInfo.DEFAULT_INSTANCE);
        }

        public List<String> getStackEntriesList() {
            return Collections.unmodifiableList(((DebugInfo) this.instance).getStackEntriesList());
        }

        public int getStackEntriesCount() {
            return ((DebugInfo) this.instance).getStackEntriesCount();
        }

        public String getStackEntries(int index) {
            return ((DebugInfo) this.instance).getStackEntries(index);
        }

        public ByteString getStackEntriesBytes(int index) {
            return ((DebugInfo) this.instance).getStackEntriesBytes(index);
        }

        public Builder setStackEntries(int index, String value) {
            copyOnWrite();
            ((DebugInfo) this.instance).setStackEntries(index, value);
            return this;
        }

        public Builder addStackEntries(String value) {
            copyOnWrite();
            ((DebugInfo) this.instance).addStackEntries(value);
            return this;
        }

        public Builder addAllStackEntries(Iterable<String> values) {
            copyOnWrite();
            ((DebugInfo) this.instance).addAllStackEntries(values);
            return this;
        }

        public Builder clearStackEntries() {
            copyOnWrite();
            ((DebugInfo) this.instance).clearStackEntries();
            return this;
        }

        public Builder addStackEntriesBytes(ByteString value) {
            copyOnWrite();
            ((DebugInfo) this.instance).addStackEntriesBytes(value);
            return this;
        }

        public String getDetail() {
            return ((DebugInfo) this.instance).getDetail();
        }

        public ByteString getDetailBytes() {
            return ((DebugInfo) this.instance).getDetailBytes();
        }

        public Builder setDetail(String value) {
            copyOnWrite();
            ((DebugInfo) this.instance).setDetail(value);
            return this;
        }

        public Builder clearDetail() {
            copyOnWrite();
            ((DebugInfo) this.instance).clearDetail();
            return this;
        }

        public Builder setDetailBytes(ByteString value) {
            copyOnWrite();
            ((DebugInfo) this.instance).setDetailBytes(value);
            return this;
        }
    }

    /* renamed from: com.google.rpc.DebugInfo$1 */
    static /* synthetic */ class C10611 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f283xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f283xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f283xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f283xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f283xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f283xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f283xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f283xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C10611.f283xa1df5c61[method.ordinal()]) {
            case 1:
                return new DebugInfo();
            case 2:
                return new Builder((C10611) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ț\u0002Ȉ", new Object[]{"stackEntries_", "detail_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<DebugInfo> parser = PARSER;
                if (parser == null) {
                    synchronized (DebugInfo.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        DebugInfo defaultInstance = new DebugInfo();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(DebugInfo.class, defaultInstance);
    }

    public static DebugInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DebugInfo> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
