package com.google.api;

import com.google.api.AuthProvider;
import com.google.api.AuthenticationRule;
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

public final class Authentication extends GeneratedMessageLite<Authentication, Builder> implements AuthenticationOrBuilder {
    /* access modifiers changed from: private */
    public static final Authentication DEFAULT_INSTANCE;
    private static volatile Parser<Authentication> PARSER = null;
    public static final int PROVIDERS_FIELD_NUMBER = 4;
    public static final int RULES_FIELD_NUMBER = 3;
    private Internal.ProtobufList<AuthProvider> providers_ = emptyProtobufList();
    private Internal.ProtobufList<AuthenticationRule> rules_ = emptyProtobufList();

    private Authentication() {
    }

    public List<AuthenticationRule> getRulesList() {
        return this.rules_;
    }

    public List<? extends AuthenticationRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    public int getRulesCount() {
        return this.rules_.size();
    }

    public AuthenticationRule getRules(int index) {
        return (AuthenticationRule) this.rules_.get(index);
    }

    public AuthenticationRuleOrBuilder getRulesOrBuilder(int index) {
        return (AuthenticationRuleOrBuilder) this.rules_.get(index);
    }

    private void ensureRulesIsMutable() {
        Internal.ProtobufList<AuthenticationRule> tmp = this.rules_;
        if (!tmp.isModifiable()) {
            this.rules_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setRules(int index, AuthenticationRule value) {
        value.getClass();
        ensureRulesIsMutable();
        this.rules_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addRules(AuthenticationRule value) {
        value.getClass();
        ensureRulesIsMutable();
        this.rules_.add(value);
    }

    /* access modifiers changed from: private */
    public void addRules(int index, AuthenticationRule value) {
        value.getClass();
        ensureRulesIsMutable();
        this.rules_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllRules(Iterable<? extends AuthenticationRule> values) {
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

    public List<AuthProvider> getProvidersList() {
        return this.providers_;
    }

    public List<? extends AuthProviderOrBuilder> getProvidersOrBuilderList() {
        return this.providers_;
    }

    public int getProvidersCount() {
        return this.providers_.size();
    }

    public AuthProvider getProviders(int index) {
        return (AuthProvider) this.providers_.get(index);
    }

    public AuthProviderOrBuilder getProvidersOrBuilder(int index) {
        return (AuthProviderOrBuilder) this.providers_.get(index);
    }

    private void ensureProvidersIsMutable() {
        Internal.ProtobufList<AuthProvider> tmp = this.providers_;
        if (!tmp.isModifiable()) {
            this.providers_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setProviders(int index, AuthProvider value) {
        value.getClass();
        ensureProvidersIsMutable();
        this.providers_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addProviders(AuthProvider value) {
        value.getClass();
        ensureProvidersIsMutable();
        this.providers_.add(value);
    }

    /* access modifiers changed from: private */
    public void addProviders(int index, AuthProvider value) {
        value.getClass();
        ensureProvidersIsMutable();
        this.providers_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllProviders(Iterable<? extends AuthProvider> values) {
        ensureProvidersIsMutable();
        AbstractMessageLite.addAll(values, this.providers_);
    }

    /* access modifiers changed from: private */
    public void clearProviders() {
        this.providers_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeProviders(int index) {
        ensureProvidersIsMutable();
        this.providers_.remove(index);
    }

    public static Authentication parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Authentication parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Authentication parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Authentication parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Authentication parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Authentication parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Authentication parseFrom(InputStream input) throws IOException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Authentication parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Authentication parseDelimitedFrom(InputStream input) throws IOException {
        return (Authentication) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Authentication parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Authentication) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Authentication parseFrom(CodedInputStream input) throws IOException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Authentication parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Authentication prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Authentication, Builder> implements AuthenticationOrBuilder {
        /* synthetic */ Builder(C00031 x0) {
            this();
        }

        private Builder() {
            super(Authentication.DEFAULT_INSTANCE);
        }

        public List<AuthenticationRule> getRulesList() {
            return Collections.unmodifiableList(((Authentication) this.instance).getRulesList());
        }

        public int getRulesCount() {
            return ((Authentication) this.instance).getRulesCount();
        }

        public AuthenticationRule getRules(int index) {
            return ((Authentication) this.instance).getRules(index);
        }

        public Builder setRules(int index, AuthenticationRule value) {
            copyOnWrite();
            ((Authentication) this.instance).setRules(index, value);
            return this;
        }

        public Builder setRules(int index, AuthenticationRule.Builder builderForValue) {
            copyOnWrite();
            ((Authentication) this.instance).setRules(index, (AuthenticationRule) builderForValue.build());
            return this;
        }

        public Builder addRules(AuthenticationRule value) {
            copyOnWrite();
            ((Authentication) this.instance).addRules(value);
            return this;
        }

        public Builder addRules(int index, AuthenticationRule value) {
            copyOnWrite();
            ((Authentication) this.instance).addRules(index, value);
            return this;
        }

        public Builder addRules(AuthenticationRule.Builder builderForValue) {
            copyOnWrite();
            ((Authentication) this.instance).addRules((AuthenticationRule) builderForValue.build());
            return this;
        }

        public Builder addRules(int index, AuthenticationRule.Builder builderForValue) {
            copyOnWrite();
            ((Authentication) this.instance).addRules(index, (AuthenticationRule) builderForValue.build());
            return this;
        }

        public Builder addAllRules(Iterable<? extends AuthenticationRule> values) {
            copyOnWrite();
            ((Authentication) this.instance).addAllRules(values);
            return this;
        }

        public Builder clearRules() {
            copyOnWrite();
            ((Authentication) this.instance).clearRules();
            return this;
        }

        public Builder removeRules(int index) {
            copyOnWrite();
            ((Authentication) this.instance).removeRules(index);
            return this;
        }

        public List<AuthProvider> getProvidersList() {
            return Collections.unmodifiableList(((Authentication) this.instance).getProvidersList());
        }

        public int getProvidersCount() {
            return ((Authentication) this.instance).getProvidersCount();
        }

        public AuthProvider getProviders(int index) {
            return ((Authentication) this.instance).getProviders(index);
        }

        public Builder setProviders(int index, AuthProvider value) {
            copyOnWrite();
            ((Authentication) this.instance).setProviders(index, value);
            return this;
        }

        public Builder setProviders(int index, AuthProvider.Builder builderForValue) {
            copyOnWrite();
            ((Authentication) this.instance).setProviders(index, (AuthProvider) builderForValue.build());
            return this;
        }

        public Builder addProviders(AuthProvider value) {
            copyOnWrite();
            ((Authentication) this.instance).addProviders(value);
            return this;
        }

        public Builder addProviders(int index, AuthProvider value) {
            copyOnWrite();
            ((Authentication) this.instance).addProviders(index, value);
            return this;
        }

        public Builder addProviders(AuthProvider.Builder builderForValue) {
            copyOnWrite();
            ((Authentication) this.instance).addProviders((AuthProvider) builderForValue.build());
            return this;
        }

        public Builder addProviders(int index, AuthProvider.Builder builderForValue) {
            copyOnWrite();
            ((Authentication) this.instance).addProviders(index, (AuthProvider) builderForValue.build());
            return this;
        }

        public Builder addAllProviders(Iterable<? extends AuthProvider> values) {
            copyOnWrite();
            ((Authentication) this.instance).addAllProviders(values);
            return this;
        }

        public Builder clearProviders() {
            copyOnWrite();
            ((Authentication) this.instance).clearProviders();
            return this;
        }

        public Builder removeProviders(int index) {
            copyOnWrite();
            ((Authentication) this.instance).removeProviders(index);
            return this;
        }
    }

    /* renamed from: com.google.api.Authentication$1 */
    static /* synthetic */ class C00031 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f3xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f3xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f3xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f3xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f3xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f3xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00031.f3xa1df5c61[method.ordinal()]) {
            case 1:
                return new Authentication();
            case 2:
                return new Builder((C00031) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0003\u0004\u0002\u0000\u0002\u0000\u0003\u001b\u0004\u001b", new Object[]{"rules_", AuthenticationRule.class, "providers_", AuthProvider.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Authentication> parser = PARSER;
                if (parser == null) {
                    synchronized (Authentication.class) {
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
        Authentication defaultInstance = new Authentication();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Authentication.class, defaultInstance);
    }

    public static Authentication getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Authentication> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
