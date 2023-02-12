package com.google.longrunning;

import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.rpc.Status;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Operation extends GeneratedMessageLite<Operation, Builder> implements OperationOrBuilder {
    /* access modifiers changed from: private */
    public static final Operation DEFAULT_INSTANCE;
    public static final int DONE_FIELD_NUMBER = 3;
    public static final int ERROR_FIELD_NUMBER = 4;
    public static final int METADATA_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<Operation> PARSER = null;
    public static final int RESPONSE_FIELD_NUMBER = 5;
    private boolean done_;
    private Any metadata_;
    private String name_ = "";
    private int resultCase_ = 0;
    private Object result_;

    private Operation() {
    }

    public enum ResultCase {
        ERROR(4),
        RESPONSE(5),
        RESULT_NOT_SET(0);
        
        private final int value;

        private ResultCase(int value2) {
            this.value = value2;
        }

        @Deprecated
        public static ResultCase valueOf(int value2) {
            return forNumber(value2);
        }

        public static ResultCase forNumber(int value2) {
            switch (value2) {
                case 0:
                    return RESULT_NOT_SET;
                case 4:
                    return ERROR;
                case 5:
                    return RESPONSE;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    public ResultCase getResultCase() {
        return ResultCase.forNumber(this.resultCase_);
    }

    /* access modifiers changed from: private */
    public void clearResult() {
        this.resultCase_ = 0;
        this.result_ = null;
    }

    public String getName() {
        return this.name_;
    }

    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* access modifiers changed from: private */
    public void setName(String value) {
        value.getClass();
        this.name_ = value;
    }

    /* access modifiers changed from: private */
    public void clearName() {
        this.name_ = getDefaultInstance().getName();
    }

    /* access modifiers changed from: private */
    public void setNameBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.name_ = value.toStringUtf8();
    }

    public boolean hasMetadata() {
        return this.metadata_ != null;
    }

    public Any getMetadata() {
        Any any = this.metadata_;
        return any == null ? Any.getDefaultInstance() : any;
    }

    /* access modifiers changed from: private */
    public void setMetadata(Any value) {
        value.getClass();
        this.metadata_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeMetadata(Any value) {
        value.getClass();
        Any any = this.metadata_;
        if (any == null || any == Any.getDefaultInstance()) {
            this.metadata_ = value;
        } else {
            this.metadata_ = (Any) ((Any.Builder) Any.newBuilder(this.metadata_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearMetadata() {
        this.metadata_ = null;
    }

    public boolean getDone() {
        return this.done_;
    }

    /* access modifiers changed from: private */
    public void setDone(boolean value) {
        this.done_ = value;
    }

    /* access modifiers changed from: private */
    public void clearDone() {
        this.done_ = false;
    }

    public boolean hasError() {
        return this.resultCase_ == 4;
    }

    public Status getError() {
        if (this.resultCase_ == 4) {
            return (Status) this.result_;
        }
        return Status.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setError(Status value) {
        value.getClass();
        this.result_ = value;
        this.resultCase_ = 4;
    }

    /* access modifiers changed from: private */
    public void mergeError(Status value) {
        value.getClass();
        if (this.resultCase_ != 4 || this.result_ == Status.getDefaultInstance()) {
            this.result_ = value;
        } else {
            this.result_ = ((Status.Builder) Status.newBuilder((Status) this.result_).mergeFrom(value)).buildPartial();
        }
        this.resultCase_ = 4;
    }

    /* access modifiers changed from: private */
    public void clearError() {
        if (this.resultCase_ == 4) {
            this.resultCase_ = 0;
            this.result_ = null;
        }
    }

    public boolean hasResponse() {
        return this.resultCase_ == 5;
    }

    public Any getResponse() {
        if (this.resultCase_ == 5) {
            return (Any) this.result_;
        }
        return Any.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setResponse(Any value) {
        value.getClass();
        this.result_ = value;
        this.resultCase_ = 5;
    }

    /* access modifiers changed from: private */
    public void mergeResponse(Any value) {
        value.getClass();
        if (this.resultCase_ != 5 || this.result_ == Any.getDefaultInstance()) {
            this.result_ = value;
        } else {
            this.result_ = ((Any.Builder) Any.newBuilder((Any) this.result_).mergeFrom(value)).buildPartial();
        }
        this.resultCase_ = 5;
    }

    /* access modifiers changed from: private */
    public void clearResponse() {
        if (this.resultCase_ == 5) {
            this.resultCase_ = 0;
            this.result_ = null;
        }
    }

    public static Operation parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Operation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Operation parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Operation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Operation parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Operation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Operation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Operation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Operation parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Operation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Operation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Operation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Operation parseFrom(InputStream input) throws IOException {
        return (Operation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Operation parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Operation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Operation parseDelimitedFrom(InputStream input) throws IOException {
        return (Operation) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Operation parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Operation) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Operation parseFrom(CodedInputStream input) throws IOException {
        return (Operation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Operation parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Operation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Operation prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Operation, Builder> implements OperationOrBuilder {
        /* synthetic */ Builder(C09771 x0) {
            this();
        }

        private Builder() {
            super(Operation.DEFAULT_INSTANCE);
        }

        public ResultCase getResultCase() {
            return ((Operation) this.instance).getResultCase();
        }

        public Builder clearResult() {
            copyOnWrite();
            ((Operation) this.instance).clearResult();
            return this;
        }

        public String getName() {
            return ((Operation) this.instance).getName();
        }

        public ByteString getNameBytes() {
            return ((Operation) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((Operation) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((Operation) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((Operation) this.instance).setNameBytes(value);
            return this;
        }

        public boolean hasMetadata() {
            return ((Operation) this.instance).hasMetadata();
        }

        public Any getMetadata() {
            return ((Operation) this.instance).getMetadata();
        }

        public Builder setMetadata(Any value) {
            copyOnWrite();
            ((Operation) this.instance).setMetadata(value);
            return this;
        }

        public Builder setMetadata(Any.Builder builderForValue) {
            copyOnWrite();
            ((Operation) this.instance).setMetadata((Any) builderForValue.build());
            return this;
        }

        public Builder mergeMetadata(Any value) {
            copyOnWrite();
            ((Operation) this.instance).mergeMetadata(value);
            return this;
        }

        public Builder clearMetadata() {
            copyOnWrite();
            ((Operation) this.instance).clearMetadata();
            return this;
        }

        public boolean getDone() {
            return ((Operation) this.instance).getDone();
        }

        public Builder setDone(boolean value) {
            copyOnWrite();
            ((Operation) this.instance).setDone(value);
            return this;
        }

        public Builder clearDone() {
            copyOnWrite();
            ((Operation) this.instance).clearDone();
            return this;
        }

        public boolean hasError() {
            return ((Operation) this.instance).hasError();
        }

        public Status getError() {
            return ((Operation) this.instance).getError();
        }

        public Builder setError(Status value) {
            copyOnWrite();
            ((Operation) this.instance).setError(value);
            return this;
        }

        public Builder setError(Status.Builder builderForValue) {
            copyOnWrite();
            ((Operation) this.instance).setError((Status) builderForValue.build());
            return this;
        }

        public Builder mergeError(Status value) {
            copyOnWrite();
            ((Operation) this.instance).mergeError(value);
            return this;
        }

        public Builder clearError() {
            copyOnWrite();
            ((Operation) this.instance).clearError();
            return this;
        }

        public boolean hasResponse() {
            return ((Operation) this.instance).hasResponse();
        }

        public Any getResponse() {
            return ((Operation) this.instance).getResponse();
        }

        public Builder setResponse(Any value) {
            copyOnWrite();
            ((Operation) this.instance).setResponse(value);
            return this;
        }

        public Builder setResponse(Any.Builder builderForValue) {
            copyOnWrite();
            ((Operation) this.instance).setResponse((Any) builderForValue.build());
            return this;
        }

        public Builder mergeResponse(Any value) {
            copyOnWrite();
            ((Operation) this.instance).mergeResponse(value);
            return this;
        }

        public Builder clearResponse() {
            copyOnWrite();
            ((Operation) this.instance).clearResponse();
            return this;
        }
    }

    /* renamed from: com.google.longrunning.Operation$1 */
    static /* synthetic */ class C09771 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f249xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f249xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f249xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f249xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f249xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f249xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f249xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f249xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C09771.f249xa1df5c61[method.ordinal()]) {
            case 1:
                return new Operation();
            case 2:
                return new Builder((C09771) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0001\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002\t\u0003\u0007\u0004<\u0000\u0005<\u0000", new Object[]{"result_", "resultCase_", "name_", "metadata_", "done_", Status.class, Any.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Operation> parser = PARSER;
                if (parser == null) {
                    synchronized (Operation.class) {
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
        Operation defaultInstance = new Operation();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Operation.class, defaultInstance);
    }

    public static Operation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Operation> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
