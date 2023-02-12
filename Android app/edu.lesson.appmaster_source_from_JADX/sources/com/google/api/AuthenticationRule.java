package com.google.api;

import com.google.api.AuthRequirement;
import com.google.api.OAuthRequirements;
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

public final class AuthenticationRule extends GeneratedMessageLite<AuthenticationRule, Builder> implements AuthenticationRuleOrBuilder {
    public static final int ALLOW_WITHOUT_CREDENTIAL_FIELD_NUMBER = 5;
    /* access modifiers changed from: private */
    public static final AuthenticationRule DEFAULT_INSTANCE;
    public static final int OAUTH_FIELD_NUMBER = 2;
    private static volatile Parser<AuthenticationRule> PARSER = null;
    public static final int REQUIREMENTS_FIELD_NUMBER = 7;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private boolean allowWithoutCredential_;
    private OAuthRequirements oauth_;
    private Internal.ProtobufList<AuthRequirement> requirements_ = emptyProtobufList();
    private String selector_ = "";

    private AuthenticationRule() {
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

    public boolean hasOauth() {
        return this.oauth_ != null;
    }

    public OAuthRequirements getOauth() {
        OAuthRequirements oAuthRequirements = this.oauth_;
        return oAuthRequirements == null ? OAuthRequirements.getDefaultInstance() : oAuthRequirements;
    }

    /* access modifiers changed from: private */
    public void setOauth(OAuthRequirements value) {
        value.getClass();
        this.oauth_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeOauth(OAuthRequirements value) {
        value.getClass();
        OAuthRequirements oAuthRequirements = this.oauth_;
        if (oAuthRequirements == null || oAuthRequirements == OAuthRequirements.getDefaultInstance()) {
            this.oauth_ = value;
        } else {
            this.oauth_ = (OAuthRequirements) ((OAuthRequirements.Builder) OAuthRequirements.newBuilder(this.oauth_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearOauth() {
        this.oauth_ = null;
    }

    public boolean getAllowWithoutCredential() {
        return this.allowWithoutCredential_;
    }

    /* access modifiers changed from: private */
    public void setAllowWithoutCredential(boolean value) {
        this.allowWithoutCredential_ = value;
    }

    /* access modifiers changed from: private */
    public void clearAllowWithoutCredential() {
        this.allowWithoutCredential_ = false;
    }

    public List<AuthRequirement> getRequirementsList() {
        return this.requirements_;
    }

    public List<? extends AuthRequirementOrBuilder> getRequirementsOrBuilderList() {
        return this.requirements_;
    }

    public int getRequirementsCount() {
        return this.requirements_.size();
    }

    public AuthRequirement getRequirements(int index) {
        return (AuthRequirement) this.requirements_.get(index);
    }

    public AuthRequirementOrBuilder getRequirementsOrBuilder(int index) {
        return (AuthRequirementOrBuilder) this.requirements_.get(index);
    }

    private void ensureRequirementsIsMutable() {
        Internal.ProtobufList<AuthRequirement> tmp = this.requirements_;
        if (!tmp.isModifiable()) {
            this.requirements_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setRequirements(int index, AuthRequirement value) {
        value.getClass();
        ensureRequirementsIsMutable();
        this.requirements_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addRequirements(AuthRequirement value) {
        value.getClass();
        ensureRequirementsIsMutable();
        this.requirements_.add(value);
    }

    /* access modifiers changed from: private */
    public void addRequirements(int index, AuthRequirement value) {
        value.getClass();
        ensureRequirementsIsMutable();
        this.requirements_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllRequirements(Iterable<? extends AuthRequirement> values) {
        ensureRequirementsIsMutable();
        AbstractMessageLite.addAll(values, this.requirements_);
    }

    /* access modifiers changed from: private */
    public void clearRequirements() {
        this.requirements_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeRequirements(int index) {
        ensureRequirementsIsMutable();
        this.requirements_.remove(index);
    }

    public static AuthenticationRule parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (AuthenticationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AuthenticationRule parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AuthenticationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AuthenticationRule parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AuthenticationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AuthenticationRule parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AuthenticationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AuthenticationRule parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AuthenticationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AuthenticationRule parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AuthenticationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AuthenticationRule parseFrom(InputStream input) throws IOException {
        return (AuthenticationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AuthenticationRule parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AuthenticationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AuthenticationRule parseDelimitedFrom(InputStream input) throws IOException {
        return (AuthenticationRule) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AuthenticationRule parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AuthenticationRule) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AuthenticationRule parseFrom(CodedInputStream input) throws IOException {
        return (AuthenticationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AuthenticationRule parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AuthenticationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AuthenticationRule prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AuthenticationRule, Builder> implements AuthenticationRuleOrBuilder {
        /* synthetic */ Builder(C00041 x0) {
            this();
        }

        private Builder() {
            super(AuthenticationRule.DEFAULT_INSTANCE);
        }

        public String getSelector() {
            return ((AuthenticationRule) this.instance).getSelector();
        }

        public ByteString getSelectorBytes() {
            return ((AuthenticationRule) this.instance).getSelectorBytes();
        }

        public Builder setSelector(String value) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).setSelector(value);
            return this;
        }

        public Builder clearSelector() {
            copyOnWrite();
            ((AuthenticationRule) this.instance).clearSelector();
            return this;
        }

        public Builder setSelectorBytes(ByteString value) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).setSelectorBytes(value);
            return this;
        }

        public boolean hasOauth() {
            return ((AuthenticationRule) this.instance).hasOauth();
        }

        public OAuthRequirements getOauth() {
            return ((AuthenticationRule) this.instance).getOauth();
        }

        public Builder setOauth(OAuthRequirements value) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).setOauth(value);
            return this;
        }

        public Builder setOauth(OAuthRequirements.Builder builderForValue) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).setOauth((OAuthRequirements) builderForValue.build());
            return this;
        }

        public Builder mergeOauth(OAuthRequirements value) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).mergeOauth(value);
            return this;
        }

        public Builder clearOauth() {
            copyOnWrite();
            ((AuthenticationRule) this.instance).clearOauth();
            return this;
        }

        public boolean getAllowWithoutCredential() {
            return ((AuthenticationRule) this.instance).getAllowWithoutCredential();
        }

        public Builder setAllowWithoutCredential(boolean value) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).setAllowWithoutCredential(value);
            return this;
        }

        public Builder clearAllowWithoutCredential() {
            copyOnWrite();
            ((AuthenticationRule) this.instance).clearAllowWithoutCredential();
            return this;
        }

        public List<AuthRequirement> getRequirementsList() {
            return Collections.unmodifiableList(((AuthenticationRule) this.instance).getRequirementsList());
        }

        public int getRequirementsCount() {
            return ((AuthenticationRule) this.instance).getRequirementsCount();
        }

        public AuthRequirement getRequirements(int index) {
            return ((AuthenticationRule) this.instance).getRequirements(index);
        }

        public Builder setRequirements(int index, AuthRequirement value) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).setRequirements(index, value);
            return this;
        }

        public Builder setRequirements(int index, AuthRequirement.Builder builderForValue) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).setRequirements(index, (AuthRequirement) builderForValue.build());
            return this;
        }

        public Builder addRequirements(AuthRequirement value) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).addRequirements(value);
            return this;
        }

        public Builder addRequirements(int index, AuthRequirement value) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).addRequirements(index, value);
            return this;
        }

        public Builder addRequirements(AuthRequirement.Builder builderForValue) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).addRequirements((AuthRequirement) builderForValue.build());
            return this;
        }

        public Builder addRequirements(int index, AuthRequirement.Builder builderForValue) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).addRequirements(index, (AuthRequirement) builderForValue.build());
            return this;
        }

        public Builder addAllRequirements(Iterable<? extends AuthRequirement> values) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).addAllRequirements(values);
            return this;
        }

        public Builder clearRequirements() {
            copyOnWrite();
            ((AuthenticationRule) this.instance).clearRequirements();
            return this;
        }

        public Builder removeRequirements(int index) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).removeRequirements(index);
            return this;
        }
    }

    /* renamed from: com.google.api.AuthenticationRule$1 */
    static /* synthetic */ class C00041 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f4xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f4xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f4xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f4xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f4xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f4xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00041.f4xa1df5c61[method.ordinal()]) {
            case 1:
                return new AuthenticationRule();
            case 2:
                return new Builder((C00041) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0007\u0004\u0000\u0001\u0000\u0001Ȉ\u0002\t\u0005\u0007\u0007\u001b", new Object[]{"selector_", "oauth_", "allowWithoutCredential_", "requirements_", AuthRequirement.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<AuthenticationRule> parser = PARSER;
                if (parser == null) {
                    synchronized (AuthenticationRule.class) {
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
        AuthenticationRule defaultInstance = new AuthenticationRule();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(AuthenticationRule.class, defaultInstance);
    }

    public static AuthenticationRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuthenticationRule> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
