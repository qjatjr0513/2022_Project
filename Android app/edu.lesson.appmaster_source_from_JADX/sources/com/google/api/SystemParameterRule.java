package com.google.api;

import com.google.api.SystemParameter;
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

public final class SystemParameterRule extends GeneratedMessageLite<SystemParameterRule, Builder> implements SystemParameterRuleOrBuilder {
    /* access modifiers changed from: private */
    public static final SystemParameterRule DEFAULT_INSTANCE;
    public static final int PARAMETERS_FIELD_NUMBER = 2;
    private static volatile Parser<SystemParameterRule> PARSER = null;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private Internal.ProtobufList<SystemParameter> parameters_ = emptyProtobufList();
    private String selector_ = "";

    private SystemParameterRule() {
    }

    public String getSelector() {
        return this.selector_;
    }

    public ByteString getSelectorBytes() {
        return ByteString.copyFromUtf8(this.selector_);
    }

    /* access modifiers changed from: private */
    public void setSelector(String value) {
        value.getClass();
        this.selector_ = value;
    }

    /* access modifiers changed from: private */
    public void clearSelector() {
        this.selector_ = getDefaultInstance().getSelector();
    }

    /* access modifiers changed from: private */
    public void setSelectorBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.selector_ = value.toStringUtf8();
    }

    public List<SystemParameter> getParametersList() {
        return this.parameters_;
    }

    public List<? extends SystemParameterOrBuilder> getParametersOrBuilderList() {
        return this.parameters_;
    }

    public int getParametersCount() {
        return this.parameters_.size();
    }

    public SystemParameter getParameters(int index) {
        return (SystemParameter) this.parameters_.get(index);
    }

    public SystemParameterOrBuilder getParametersOrBuilder(int index) {
        return (SystemParameterOrBuilder) this.parameters_.get(index);
    }

    private void ensureParametersIsMutable() {
        Internal.ProtobufList<SystemParameter> tmp = this.parameters_;
        if (!tmp.isModifiable()) {
            this.parameters_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setParameters(int index, SystemParameter value) {
        value.getClass();
        ensureParametersIsMutable();
        this.parameters_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addParameters(SystemParameter value) {
        value.getClass();
        ensureParametersIsMutable();
        this.parameters_.add(value);
    }

    /* access modifiers changed from: private */
    public void addParameters(int index, SystemParameter value) {
        value.getClass();
        ensureParametersIsMutable();
        this.parameters_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllParameters(Iterable<? extends SystemParameter> values) {
        ensureParametersIsMutable();
        AbstractMessageLite.addAll(values, this.parameters_);
    }

    /* access modifiers changed from: private */
    public void clearParameters() {
        this.parameters_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeParameters(int index) {
        ensureParametersIsMutable();
        this.parameters_.remove(index);
    }

    public static SystemParameterRule parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (SystemParameterRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SystemParameterRule parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SystemParameterRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SystemParameterRule parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (SystemParameterRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SystemParameterRule parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SystemParameterRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SystemParameterRule parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (SystemParameterRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SystemParameterRule parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SystemParameterRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SystemParameterRule parseFrom(InputStream input) throws IOException {
        return (SystemParameterRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SystemParameterRule parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SystemParameterRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SystemParameterRule parseDelimitedFrom(InputStream input) throws IOException {
        return (SystemParameterRule) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static SystemParameterRule parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SystemParameterRule) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SystemParameterRule parseFrom(CodedInputStream input) throws IOException {
        return (SystemParameterRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SystemParameterRule parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SystemParameterRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(SystemParameterRule prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<SystemParameterRule, Builder> implements SystemParameterRuleOrBuilder {
        /* synthetic */ Builder(C00511 x0) {
            this();
        }

        private Builder() {
            super(SystemParameterRule.DEFAULT_INSTANCE);
        }

        public String getSelector() {
            return ((SystemParameterRule) this.instance).getSelector();
        }

        public ByteString getSelectorBytes() {
            return ((SystemParameterRule) this.instance).getSelectorBytes();
        }

        public Builder setSelector(String value) {
            copyOnWrite();
            ((SystemParameterRule) this.instance).setSelector(value);
            return this;
        }

        public Builder clearSelector() {
            copyOnWrite();
            ((SystemParameterRule) this.instance).clearSelector();
            return this;
        }

        public Builder setSelectorBytes(ByteString value) {
            copyOnWrite();
            ((SystemParameterRule) this.instance).setSelectorBytes(value);
            return this;
        }

        public List<SystemParameter> getParametersList() {
            return Collections.unmodifiableList(((SystemParameterRule) this.instance).getParametersList());
        }

        public int getParametersCount() {
            return ((SystemParameterRule) this.instance).getParametersCount();
        }

        public SystemParameter getParameters(int index) {
            return ((SystemParameterRule) this.instance).getParameters(index);
        }

        public Builder setParameters(int index, SystemParameter value) {
            copyOnWrite();
            ((SystemParameterRule) this.instance).setParameters(index, value);
            return this;
        }

        public Builder setParameters(int index, SystemParameter.Builder builderForValue) {
            copyOnWrite();
            ((SystemParameterRule) this.instance).setParameters(index, (SystemParameter) builderForValue.build());
            return this;
        }

        public Builder addParameters(SystemParameter value) {
            copyOnWrite();
            ((SystemParameterRule) this.instance).addParameters(value);
            return this;
        }

        public Builder addParameters(int index, SystemParameter value) {
            copyOnWrite();
            ((SystemParameterRule) this.instance).addParameters(index, value);
            return this;
        }

        public Builder addParameters(SystemParameter.Builder builderForValue) {
            copyOnWrite();
            ((SystemParameterRule) this.instance).addParameters((SystemParameter) builderForValue.build());
            return this;
        }

        public Builder addParameters(int index, SystemParameter.Builder builderForValue) {
            copyOnWrite();
            ((SystemParameterRule) this.instance).addParameters(index, (SystemParameter) builderForValue.build());
            return this;
        }

        public Builder addAllParameters(Iterable<? extends SystemParameter> values) {
            copyOnWrite();
            ((SystemParameterRule) this.instance).addAllParameters(values);
            return this;
        }

        public Builder clearParameters() {
            copyOnWrite();
            ((SystemParameterRule) this.instance).clearParameters();
            return this;
        }

        public Builder removeParameters(int index) {
            copyOnWrite();
            ((SystemParameterRule) this.instance).removeParameters(index);
            return this;
        }
    }

    /* renamed from: com.google.api.SystemParameterRule$1 */
    static /* synthetic */ class C00511 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f43xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f43xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f43xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f43xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f43xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f43xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f43xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f43xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00511.f43xa1df5c61[method.ordinal()]) {
            case 1:
                return new SystemParameterRule();
            case 2:
                return new Builder((C00511) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"selector_", "parameters_", SystemParameter.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<SystemParameterRule> parser = PARSER;
                if (parser == null) {
                    synchronized (SystemParameterRule.class) {
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
        SystemParameterRule defaultInstance = new SystemParameterRule();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(SystemParameterRule.class, defaultInstance);
    }

    public static SystemParameterRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SystemParameterRule> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
