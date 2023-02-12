package com.google.api;

import com.google.api.SystemParameterRule;
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

public final class SystemParameters extends GeneratedMessageLite<SystemParameters, Builder> implements SystemParametersOrBuilder {
    /* access modifiers changed from: private */
    public static final SystemParameters DEFAULT_INSTANCE;
    private static volatile Parser<SystemParameters> PARSER = null;
    public static final int RULES_FIELD_NUMBER = 1;
    private Internal.ProtobufList<SystemParameterRule> rules_ = emptyProtobufList();

    private SystemParameters() {
    }

    public List<SystemParameterRule> getRulesList() {
        return this.rules_;
    }

    public List<? extends SystemParameterRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    public int getRulesCount() {
        return this.rules_.size();
    }

    public SystemParameterRule getRules(int index) {
        return (SystemParameterRule) this.rules_.get(index);
    }

    public SystemParameterRuleOrBuilder getRulesOrBuilder(int index) {
        return (SystemParameterRuleOrBuilder) this.rules_.get(index);
    }

    private void ensureRulesIsMutable() {
        Internal.ProtobufList<SystemParameterRule> tmp = this.rules_;
        if (!tmp.isModifiable()) {
            this.rules_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setRules(int index, SystemParameterRule value) {
        value.getClass();
        ensureRulesIsMutable();
        this.rules_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addRules(SystemParameterRule value) {
        value.getClass();
        ensureRulesIsMutable();
        this.rules_.add(value);
    }

    /* access modifiers changed from: private */
    public void addRules(int index, SystemParameterRule value) {
        value.getClass();
        ensureRulesIsMutable();
        this.rules_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllRules(Iterable<? extends SystemParameterRule> values) {
        ensureRulesIsMutable();
        AbstractMessageLite.addAll(values, this.rules_);
    }

    /* access modifiers changed from: private */
    public void clearRules() {
        this.rules_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeRules(int index) {
        ensureRulesIsMutable();
        this.rules_.remove(index);
    }

    public static SystemParameters parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (SystemParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SystemParameters parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SystemParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SystemParameters parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (SystemParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SystemParameters parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SystemParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SystemParameters parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (SystemParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SystemParameters parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SystemParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SystemParameters parseFrom(InputStream input) throws IOException {
        return (SystemParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SystemParameters parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SystemParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SystemParameters parseDelimitedFrom(InputStream input) throws IOException {
        return (SystemParameters) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static SystemParameters parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SystemParameters) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SystemParameters parseFrom(CodedInputStream input) throws IOException {
        return (SystemParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SystemParameters parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SystemParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(SystemParameters prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<SystemParameters, Builder> implements SystemParametersOrBuilder {
        /* synthetic */ Builder(C00521 x0) {
            this();
        }

        private Builder() {
            super(SystemParameters.DEFAULT_INSTANCE);
        }

        public List<SystemParameterRule> getRulesList() {
            return Collections.unmodifiableList(((SystemParameters) this.instance).getRulesList());
        }

        public int getRulesCount() {
            return ((SystemParameters) this.instance).getRulesCount();
        }

        public SystemParameterRule getRules(int index) {
            return ((SystemParameters) this.instance).getRules(index);
        }

        public Builder setRules(int index, SystemParameterRule value) {
            copyOnWrite();
            ((SystemParameters) this.instance).setRules(index, value);
            return this;
        }

        public Builder setRules(int index, SystemParameterRule.Builder builderForValue) {
            copyOnWrite();
            ((SystemParameters) this.instance).setRules(index, (SystemParameterRule) builderForValue.build());
            return this;
        }

        public Builder addRules(SystemParameterRule value) {
            copyOnWrite();
            ((SystemParameters) this.instance).addRules(value);
            return this;
        }

        public Builder addRules(int index, SystemParameterRule value) {
            copyOnWrite();
            ((SystemParameters) this.instance).addRules(index, value);
            return this;
        }

        public Builder addRules(SystemParameterRule.Builder builderForValue) {
            copyOnWrite();
            ((SystemParameters) this.instance).addRules((SystemParameterRule) builderForValue.build());
            return this;
        }

        public Builder addRules(int index, SystemParameterRule.Builder builderForValue) {
            copyOnWrite();
            ((SystemParameters) this.instance).addRules(index, (SystemParameterRule) builderForValue.build());
            return this;
        }

        public Builder addAllRules(Iterable<? extends SystemParameterRule> values) {
            copyOnWrite();
            ((SystemParameters) this.instance).addAllRules(values);
            return this;
        }

        public Builder clearRules() {
            copyOnWrite();
            ((SystemParameters) this.instance).clearRules();
            return this;
        }

        public Builder removeRules(int index) {
            copyOnWrite();
            ((SystemParameters) this.instance).removeRules(index);
            return this;
        }
    }

    /* renamed from: com.google.api.SystemParameters$1 */
    static /* synthetic */ class C00521 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f44xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f44xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f44xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f44xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f44xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f44xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f44xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f44xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00521.f44xa1df5c61[method.ordinal()]) {
            case 1:
                return new SystemParameters();
            case 2:
                return new Builder((C00521) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"rules_", SystemParameterRule.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<SystemParameters> parser = PARSER;
                if (parser == null) {
                    synchronized (SystemParameters.class) {
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
        SystemParameters defaultInstance = new SystemParameters();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(SystemParameters.class, defaultInstance);
    }

    public static SystemParameters getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SystemParameters> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
