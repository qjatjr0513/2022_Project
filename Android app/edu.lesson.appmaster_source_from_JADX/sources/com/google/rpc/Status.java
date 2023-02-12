package com.google.rpc;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
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

public final class Status extends GeneratedMessageLite<Status, Builder> implements StatusOrBuilder {
    public static final int CODE_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final Status DEFAULT_INSTANCE;
    public static final int DETAILS_FIELD_NUMBER = 3;
    public static final int MESSAGE_FIELD_NUMBER = 2;
    private static volatile Parser<Status> PARSER;
    private int code_;
    private Internal.ProtobufList<Any> details_ = emptyProtobufList();
    private String message_ = "";

    private Status() {
    }

    public int getCode() {
        return this.code_;
    }

    /* access modifiers changed from: private */
    public void setCode(int value) {
        this.code_ = value;
    }

    /* access modifiers changed from: private */
    public void clearCode() {
        this.code_ = 0;
    }

    public String getMessage() {
        return this.message_;
    }

    public ByteString getMessageBytes() {
        return ByteString.copyFromUtf8(this.message_);
    }

    /* access modifiers changed from: private */
    public void setMessage(String value) {
        value.getClass();
        this.message_ = value;
    }

    /* access modifiers changed from: private */
    public void clearMessage() {
        this.message_ = getDefaultInstance().getMessage();
    }

    /* access modifiers changed from: private */
    public void setMessageBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.message_ = value.toStringUtf8();
    }

    public List<Any> getDetailsList() {
        return this.details_;
    }

    public List<? extends AnyOrBuilder> getDetailsOrBuilderList() {
        return this.details_;
    }

    public int getDetailsCount() {
        return this.details_.size();
    }

    public Any getDetails(int index) {
        return (Any) this.details_.get(index);
    }

    public AnyOrBuilder getDetailsOrBuilder(int index) {
        return (AnyOrBuilder) this.details_.get(index);
    }

    private void ensureDetailsIsMutable() {
        Internal.ProtobufList<Any> tmp = this.details_;
        if (!tmp.isModifiable()) {
            this.details_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setDetails(int index, Any value) {
        value.getClass();
        ensureDetailsIsMutable();
        this.details_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addDetails(Any value) {
        value.getClass();
        ensureDetailsIsMutable();
        this.details_.add(value);
    }

    /* access modifiers changed from: private */
    public void addDetails(int index, Any value) {
        value.getClass();
        ensureDetailsIsMutable();
        this.details_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllDetails(Iterable<? extends Any> values) {
        ensureDetailsIsMutable();
        AbstractMessageLite.addAll(values, this.details_);
    }

    /* access modifiers changed from: private */
    public void clearDetails() {
        this.details_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeDetails(int index) {
        ensureDetailsIsMutable();
        this.details_.remove(index);
    }

    public static Status parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Status) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Status parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Status) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Status parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Status) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Status parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Status) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Status parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Status) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Status parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Status) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Status parseFrom(InputStream input) throws IOException {
        return (Status) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Status parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Status) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Status parseDelimitedFrom(InputStream input) throws IOException {
        return (Status) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Status parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Status) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Status parseFrom(CodedInputStream input) throws IOException {
        return (Status) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Status parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Status) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Status prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Status, Builder> implements StatusOrBuilder {
        /* synthetic */ Builder(C10701 x0) {
            this();
        }

        private Builder() {
            super(Status.DEFAULT_INSTANCE);
        }

        public int getCode() {
            return ((Status) this.instance).getCode();
        }

        public Builder setCode(int value) {
            copyOnWrite();
            ((Status) this.instance).setCode(value);
            return this;
        }

        public Builder clearCode() {
            copyOnWrite();
            ((Status) this.instance).clearCode();
            return this;
        }

        public String getMessage() {
            return ((Status) this.instance).getMessage();
        }

        public ByteString getMessageBytes() {
            return ((Status) this.instance).getMessageBytes();
        }

        public Builder setMessage(String value) {
            copyOnWrite();
            ((Status) this.instance).setMessage(value);
            return this;
        }

        public Builder clearMessage() {
            copyOnWrite();
            ((Status) this.instance).clearMessage();
            return this;
        }

        public Builder setMessageBytes(ByteString value) {
            copyOnWrite();
            ((Status) this.instance).setMessageBytes(value);
            return this;
        }

        public List<Any> getDetailsList() {
            return Collections.unmodifiableList(((Status) this.instance).getDetailsList());
        }

        public int getDetailsCount() {
            return ((Status) this.instance).getDetailsCount();
        }

        public Any getDetails(int index) {
            return ((Status) this.instance).getDetails(index);
        }

        public Builder setDetails(int index, Any value) {
            copyOnWrite();
            ((Status) this.instance).setDetails(index, value);
            return this;
        }

        public Builder setDetails(int index, Any.Builder builderForValue) {
            copyOnWrite();
            ((Status) this.instance).setDetails(index, (Any) builderForValue.build());
            return this;
        }

        public Builder addDetails(Any value) {
            copyOnWrite();
            ((Status) this.instance).addDetails(value);
            return this;
        }

        public Builder addDetails(int index, Any value) {
            copyOnWrite();
            ((Status) this.instance).addDetails(index, value);
            return this;
        }

        public Builder addDetails(Any.Builder builderForValue) {
            copyOnWrite();
            ((Status) this.instance).addDetails((Any) builderForValue.build());
            return this;
        }

        public Builder addDetails(int index, Any.Builder builderForValue) {
            copyOnWrite();
            ((Status) this.instance).addDetails(index, (Any) builderForValue.build());
            return this;
        }

        public Builder addAllDetails(Iterable<? extends Any> values) {
            copyOnWrite();
            ((Status) this.instance).addAllDetails(values);
            return this;
        }

        public Builder clearDetails() {
            copyOnWrite();
            ((Status) this.instance).clearDetails();
            return this;
        }

        public Builder removeDetails(int index) {
            copyOnWrite();
            ((Status) this.instance).removeDetails(index);
            return this;
        }
    }

    /* renamed from: com.google.rpc.Status$1 */
    static /* synthetic */ class C10701 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f292xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f292xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f292xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f292xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f292xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f292xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f292xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f292xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C10701.f292xa1df5c61[method.ordinal()]) {
            case 1:
                return new Status();
            case 2:
                return new Builder((C10701) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001\u0004\u0002Ȉ\u0003\u001b", new Object[]{"code_", "message_", "details_", Any.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Status> parser = PARSER;
                if (parser == null) {
                    synchronized (Status.class) {
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
        Status defaultInstance = new Status();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Status.class, defaultInstance);
    }

    public static Status getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Status> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
