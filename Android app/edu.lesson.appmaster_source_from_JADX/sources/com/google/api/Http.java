package com.google.api;

import com.google.api.HttpRule;
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

public final class Http extends GeneratedMessageLite<Http, Builder> implements HttpOrBuilder {
    /* access modifiers changed from: private */
    public static final Http DEFAULT_INSTANCE;
    public static final int FULLY_DECODE_RESERVED_EXPANSION_FIELD_NUMBER = 2;
    private static volatile Parser<Http> PARSER = null;
    public static final int RULES_FIELD_NUMBER = 1;
    private boolean fullyDecodeReservedExpansion_;
    private Internal.ProtobufList<HttpRule> rules_ = emptyProtobufList();

    private Http() {
    }

    public List<HttpRule> getRulesList() {
        return this.rules_;
    }

    public List<? extends HttpRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    public int getRulesCount() {
        return this.rules_.size();
    }

    public HttpRule getRules(int index) {
        return (HttpRule) this.rules_.get(index);
    }

    public HttpRuleOrBuilder getRulesOrBuilder(int index) {
        return (HttpRuleOrBuilder) this.rules_.get(index);
    }

    private void ensureRulesIsMutable() {
        Internal.ProtobufList<HttpRule> tmp = this.rules_;
        if (!tmp.isModifiable()) {
            this.rules_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setRules(int index, HttpRule value) {
        value.getClass();
        ensureRulesIsMutable();
        this.rules_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addRules(HttpRule value) {
        value.getClass();
        ensureRulesIsMutable();
        this.rules_.add(value);
    }

    /* access modifiers changed from: private */
    public void addRules(int index, HttpRule value) {
        value.getClass();
        ensureRulesIsMutable();
        this.rules_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllRules(Iterable<? extends HttpRule> values) {
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

    public boolean getFullyDecodeReservedExpansion() {
        return this.fullyDecodeReservedExpansion_;
    }

    /* access modifiers changed from: private */
    public void setFullyDecodeReservedExpansion(boolean value) {
        this.fullyDecodeReservedExpansion_ = value;
    }

    /* access modifiers changed from: private */
    public void clearFullyDecodeReservedExpansion() {
        this.fullyDecodeReservedExpansion_ = false;
    }

    public static Http parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Http) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Http parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Http) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Http parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Http) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Http parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Http) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Http parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Http) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Http parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Http) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Http parseFrom(InputStream input) throws IOException {
        return (Http) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Http parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Http) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Http parseDelimitedFrom(InputStream input) throws IOException {
        return (Http) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Http parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Http) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Http parseFrom(CodedInputStream input) throws IOException {
        return (Http) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Http parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Http) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Http prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Http, Builder> implements HttpOrBuilder {
        /* synthetic */ Builder(C00201 x0) {
            this();
        }

        private Builder() {
            super(Http.DEFAULT_INSTANCE);
        }

        public List<HttpRule> getRulesList() {
            return Collections.unmodifiableList(((Http) this.instance).getRulesList());
        }

        public int getRulesCount() {
            return ((Http) this.instance).getRulesCount();
        }

        public HttpRule getRules(int index) {
            return ((Http) this.instance).getRules(index);
        }

        public Builder setRules(int index, HttpRule value) {
            copyOnWrite();
            ((Http) this.instance).setRules(index, value);
            return this;
        }

        public Builder setRules(int index, HttpRule.Builder builderForValue) {
            copyOnWrite();
            ((Http) this.instance).setRules(index, (HttpRule) builderForValue.build());
            return this;
        }

        public Builder addRules(HttpRule value) {
            copyOnWrite();
            ((Http) this.instance).addRules(value);
            return this;
        }

        public Builder addRules(int index, HttpRule value) {
            copyOnWrite();
            ((Http) this.instance).addRules(index, value);
            return this;
        }

        public Builder addRules(HttpRule.Builder builderForValue) {
            copyOnWrite();
            ((Http) this.instance).addRules((HttpRule) builderForValue.build());
            return this;
        }

        public Builder addRules(int index, HttpRule.Builder builderForValue) {
            copyOnWrite();
            ((Http) this.instance).addRules(index, (HttpRule) builderForValue.build());
            return this;
        }

        public Builder addAllRules(Iterable<? extends HttpRule> values) {
            copyOnWrite();
            ((Http) this.instance).addAllRules(values);
            return this;
        }

        public Builder clearRules() {
            copyOnWrite();
            ((Http) this.instance).clearRules();
            return this;
        }

        public Builder removeRules(int index) {
            copyOnWrite();
            ((Http) this.instance).removeRules(index);
            return this;
        }

        public boolean getFullyDecodeReservedExpansion() {
            return ((Http) this.instance).getFullyDecodeReservedExpansion();
        }

        public Builder setFullyDecodeReservedExpansion(boolean value) {
            copyOnWrite();
            ((Http) this.instance).setFullyDecodeReservedExpansion(value);
            return this;
        }

        public Builder clearFullyDecodeReservedExpansion() {
            copyOnWrite();
            ((Http) this.instance).clearFullyDecodeReservedExpansion();
            return this;
        }
    }

    /* renamed from: com.google.api.Http$1 */
    static /* synthetic */ class C00201 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f17xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f17xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f17xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f17xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f17xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f17xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f17xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f17xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00201.f17xa1df5c61[method.ordinal()]) {
            case 1:
                return new Http();
            case 2:
                return new Builder((C00201) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002\u0007", new Object[]{"rules_", HttpRule.class, "fullyDecodeReservedExpansion_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Http> parser = PARSER;
                if (parser == null) {
                    synchronized (Http.class) {
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
        Http defaultInstance = new Http();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Http.class, defaultInstance);
    }

    public static Http getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Http> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
