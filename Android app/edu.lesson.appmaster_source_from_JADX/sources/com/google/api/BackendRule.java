package com.google.api;

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

public final class BackendRule extends GeneratedMessageLite<BackendRule, Builder> implements BackendRuleOrBuilder {
    public static final int ADDRESS_FIELD_NUMBER = 2;
    public static final int DEADLINE_FIELD_NUMBER = 3;
    /* access modifiers changed from: private */
    public static final BackendRule DEFAULT_INSTANCE;
    public static final int DISABLE_AUTH_FIELD_NUMBER = 8;
    public static final int JWT_AUDIENCE_FIELD_NUMBER = 7;
    public static final int MIN_DEADLINE_FIELD_NUMBER = 4;
    public static final int OPERATION_DEADLINE_FIELD_NUMBER = 5;
    private static volatile Parser<BackendRule> PARSER = null;
    public static final int PATH_TRANSLATION_FIELD_NUMBER = 6;
    public static final int PROTOCOL_FIELD_NUMBER = 9;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private String address_ = "";
    private int authenticationCase_ = 0;
    private Object authentication_;
    private double deadline_;
    private double minDeadline_;
    private double operationDeadline_;
    private int pathTranslation_;
    private String protocol_ = "";
    private String selector_ = "";

    private BackendRule() {
    }

    public enum PathTranslation implements Internal.EnumLite {
        PATH_TRANSLATION_UNSPECIFIED(0),
        CONSTANT_ADDRESS(1),
        APPEND_PATH_TO_ADDRESS(2),
        UNRECOGNIZED(-1);
        
        public static final int APPEND_PATH_TO_ADDRESS_VALUE = 2;
        public static final int CONSTANT_ADDRESS_VALUE = 1;
        public static final int PATH_TRANSLATION_UNSPECIFIED_VALUE = 0;
        private static final Internal.EnumLiteMap<PathTranslation> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<PathTranslation>() {
                public PathTranslation findValueByNumber(int number) {
                    return PathTranslation.forNumber(number);
                }
            };
        }

        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static PathTranslation valueOf(int value2) {
            return forNumber(value2);
        }

        public static PathTranslation forNumber(int value2) {
            switch (value2) {
                case 0:
                    return PATH_TRANSLATION_UNSPECIFIED;
                case 1:
                    return CONSTANT_ADDRESS;
                case 2:
                    return APPEND_PATH_TO_ADDRESS;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<PathTranslation> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return PathTranslationVerifier.INSTANCE;
        }

        private static final class PathTranslationVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = null;

            private PathTranslationVerifier() {
            }

            static {
                INSTANCE = new PathTranslationVerifier();
            }

            public boolean isInRange(int number) {
                return PathTranslation.forNumber(number) != null;
            }
        }

        private PathTranslation(int value2) {
            this.value = value2;
        }
    }

    public enum AuthenticationCase {
        JWT_AUDIENCE(7),
        DISABLE_AUTH(8),
        AUTHENTICATION_NOT_SET(0);
        
        private final int value;

        private AuthenticationCase(int value2) {
            this.value = value2;
        }

        @Deprecated
        public static AuthenticationCase valueOf(int value2) {
            return forNumber(value2);
        }

        public static AuthenticationCase forNumber(int value2) {
            switch (value2) {
                case 0:
                    return AUTHENTICATION_NOT_SET;
                case 7:
                    return JWT_AUDIENCE;
                case 8:
                    return DISABLE_AUTH;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    public AuthenticationCase getAuthenticationCase() {
        return AuthenticationCase.forNumber(this.authenticationCase_);
    }

    /* access modifiers changed from: private */
    public void clearAuthentication() {
        this.authenticationCase_ = 0;
        this.authentication_ = null;
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

    public String getAddress() {
        return this.address_;
    }

    public ByteString getAddressBytes() {
        return ByteString.copyFromUtf8(this.address_);
    }

    /* access modifiers changed from: private */
    public void setAddress(String value) {
        value.getClass();
        this.address_ = value;
    }

    /* access modifiers changed from: private */
    public void clearAddress() {
        this.address_ = getDefaultInstance().getAddress();
    }

    /* access modifiers changed from: private */
    public void setAddressBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.address_ = value.toStringUtf8();
    }

    public double getDeadline() {
        return this.deadline_;
    }

    /* access modifiers changed from: private */
    public void setDeadline(double value) {
        this.deadline_ = value;
    }

    /* access modifiers changed from: private */
    public void clearDeadline() {
        this.deadline_ = 0.0d;
    }

    public double getMinDeadline() {
        return this.minDeadline_;
    }

    /* access modifiers changed from: private */
    public void setMinDeadline(double value) {
        this.minDeadline_ = value;
    }

    /* access modifiers changed from: private */
    public void clearMinDeadline() {
        this.minDeadline_ = 0.0d;
    }

    public double getOperationDeadline() {
        return this.operationDeadline_;
    }

    /* access modifiers changed from: private */
    public void setOperationDeadline(double value) {
        this.operationDeadline_ = value;
    }

    /* access modifiers changed from: private */
    public void clearOperationDeadline() {
        this.operationDeadline_ = 0.0d;
    }

    public int getPathTranslationValue() {
        return this.pathTranslation_;
    }

    public PathTranslation getPathTranslation() {
        PathTranslation result = PathTranslation.forNumber(this.pathTranslation_);
        return result == null ? PathTranslation.UNRECOGNIZED : result;
    }

    /* access modifiers changed from: private */
    public void setPathTranslationValue(int value) {
        this.pathTranslation_ = value;
    }

    /* access modifiers changed from: private */
    public void setPathTranslation(PathTranslation value) {
        this.pathTranslation_ = value.getNumber();
    }

    /* access modifiers changed from: private */
    public void clearPathTranslation() {
        this.pathTranslation_ = 0;
    }

    public String getJwtAudience() {
        if (this.authenticationCase_ == 7) {
            return (String) this.authentication_;
        }
        return "";
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.protobuf.ByteString getJwtAudienceBytes() {
        /*
            r3 = this;
            java.lang.String r0 = ""
            int r1 = r3.authenticationCase_
            r2 = 7
            if (r1 != r2) goto L_0x000c
            java.lang.Object r1 = r3.authentication_
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0
        L_0x000c:
            com.google.protobuf.ByteString r1 = com.google.protobuf.ByteString.copyFromUtf8(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.BackendRule.getJwtAudienceBytes():com.google.protobuf.ByteString");
    }

    /* access modifiers changed from: private */
    public void setJwtAudience(String value) {
        value.getClass();
        this.authenticationCase_ = 7;
        this.authentication_ = value;
    }

    /* access modifiers changed from: private */
    public void clearJwtAudience() {
        if (this.authenticationCase_ == 7) {
            this.authenticationCase_ = 0;
            this.authentication_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setJwtAudienceBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.authentication_ = value.toStringUtf8();
        this.authenticationCase_ = 7;
    }

    public boolean getDisableAuth() {
        if (this.authenticationCase_ == 8) {
            return ((Boolean) this.authentication_).booleanValue();
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void setDisableAuth(boolean value) {
        this.authenticationCase_ = 8;
        this.authentication_ = Boolean.valueOf(value);
    }

    /* access modifiers changed from: private */
    public void clearDisableAuth() {
        if (this.authenticationCase_ == 8) {
            this.authenticationCase_ = 0;
            this.authentication_ = null;
        }
    }

    public String getProtocol() {
        return this.protocol_;
    }

    public ByteString getProtocolBytes() {
        return ByteString.copyFromUtf8(this.protocol_);
    }

    /* access modifiers changed from: private */
    public void setProtocol(String value) {
        value.getClass();
        this.protocol_ = value;
    }

    /* access modifiers changed from: private */
    public void clearProtocol() {
        this.protocol_ = getDefaultInstance().getProtocol();
    }

    /* access modifiers changed from: private */
    public void setProtocolBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.protocol_ = value.toStringUtf8();
    }

    public static BackendRule parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BackendRule parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BackendRule parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BackendRule parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BackendRule parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BackendRule parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BackendRule parseFrom(InputStream input) throws IOException {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BackendRule parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BackendRule parseDelimitedFrom(InputStream input) throws IOException {
        return (BackendRule) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static BackendRule parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BackendRule) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BackendRule parseFrom(CodedInputStream input) throws IOException {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BackendRule parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BackendRule prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BackendRule, Builder> implements BackendRuleOrBuilder {
        /* synthetic */ Builder(C00061 x0) {
            this();
        }

        private Builder() {
            super(BackendRule.DEFAULT_INSTANCE);
        }

        public AuthenticationCase getAuthenticationCase() {
            return ((BackendRule) this.instance).getAuthenticationCase();
        }

        public Builder clearAuthentication() {
            copyOnWrite();
            ((BackendRule) this.instance).clearAuthentication();
            return this;
        }

        public String getSelector() {
            return ((BackendRule) this.instance).getSelector();
        }

        public ByteString getSelectorBytes() {
            return ((BackendRule) this.instance).getSelectorBytes();
        }

        public Builder setSelector(String value) {
            copyOnWrite();
            ((BackendRule) this.instance).setSelector(value);
            return this;
        }

        public Builder clearSelector() {
            copyOnWrite();
            ((BackendRule) this.instance).clearSelector();
            return this;
        }

        public Builder setSelectorBytes(ByteString value) {
            copyOnWrite();
            ((BackendRule) this.instance).setSelectorBytes(value);
            return this;
        }

        public String getAddress() {
            return ((BackendRule) this.instance).getAddress();
        }

        public ByteString getAddressBytes() {
            return ((BackendRule) this.instance).getAddressBytes();
        }

        public Builder setAddress(String value) {
            copyOnWrite();
            ((BackendRule) this.instance).setAddress(value);
            return this;
        }

        public Builder clearAddress() {
            copyOnWrite();
            ((BackendRule) this.instance).clearAddress();
            return this;
        }

        public Builder setAddressBytes(ByteString value) {
            copyOnWrite();
            ((BackendRule) this.instance).setAddressBytes(value);
            return this;
        }

        public double getDeadline() {
            return ((BackendRule) this.instance).getDeadline();
        }

        public Builder setDeadline(double value) {
            copyOnWrite();
            ((BackendRule) this.instance).setDeadline(value);
            return this;
        }

        public Builder clearDeadline() {
            copyOnWrite();
            ((BackendRule) this.instance).clearDeadline();
            return this;
        }

        public double getMinDeadline() {
            return ((BackendRule) this.instance).getMinDeadline();
        }

        public Builder setMinDeadline(double value) {
            copyOnWrite();
            ((BackendRule) this.instance).setMinDeadline(value);
            return this;
        }

        public Builder clearMinDeadline() {
            copyOnWrite();
            ((BackendRule) this.instance).clearMinDeadline();
            return this;
        }

        public double getOperationDeadline() {
            return ((BackendRule) this.instance).getOperationDeadline();
        }

        public Builder setOperationDeadline(double value) {
            copyOnWrite();
            ((BackendRule) this.instance).setOperationDeadline(value);
            return this;
        }

        public Builder clearOperationDeadline() {
            copyOnWrite();
            ((BackendRule) this.instance).clearOperationDeadline();
            return this;
        }

        public int getPathTranslationValue() {
            return ((BackendRule) this.instance).getPathTranslationValue();
        }

        public Builder setPathTranslationValue(int value) {
            copyOnWrite();
            ((BackendRule) this.instance).setPathTranslationValue(value);
            return this;
        }

        public PathTranslation getPathTranslation() {
            return ((BackendRule) this.instance).getPathTranslation();
        }

        public Builder setPathTranslation(PathTranslation value) {
            copyOnWrite();
            ((BackendRule) this.instance).setPathTranslation(value);
            return this;
        }

        public Builder clearPathTranslation() {
            copyOnWrite();
            ((BackendRule) this.instance).clearPathTranslation();
            return this;
        }

        public String getJwtAudience() {
            return ((BackendRule) this.instance).getJwtAudience();
        }

        public ByteString getJwtAudienceBytes() {
            return ((BackendRule) this.instance).getJwtAudienceBytes();
        }

        public Builder setJwtAudience(String value) {
            copyOnWrite();
            ((BackendRule) this.instance).setJwtAudience(value);
            return this;
        }

        public Builder clearJwtAudience() {
            copyOnWrite();
            ((BackendRule) this.instance).clearJwtAudience();
            return this;
        }

        public Builder setJwtAudienceBytes(ByteString value) {
            copyOnWrite();
            ((BackendRule) this.instance).setJwtAudienceBytes(value);
            return this;
        }

        public boolean getDisableAuth() {
            return ((BackendRule) this.instance).getDisableAuth();
        }

        public Builder setDisableAuth(boolean value) {
            copyOnWrite();
            ((BackendRule) this.instance).setDisableAuth(value);
            return this;
        }

        public Builder clearDisableAuth() {
            copyOnWrite();
            ((BackendRule) this.instance).clearDisableAuth();
            return this;
        }

        public String getProtocol() {
            return ((BackendRule) this.instance).getProtocol();
        }

        public ByteString getProtocolBytes() {
            return ((BackendRule) this.instance).getProtocolBytes();
        }

        public Builder setProtocol(String value) {
            copyOnWrite();
            ((BackendRule) this.instance).setProtocol(value);
            return this;
        }

        public Builder clearProtocol() {
            copyOnWrite();
            ((BackendRule) this.instance).clearProtocol();
            return this;
        }

        public Builder setProtocolBytes(ByteString value) {
            copyOnWrite();
            ((BackendRule) this.instance).setProtocolBytes(value);
            return this;
        }
    }

    /* renamed from: com.google.api.BackendRule$1 */
    static /* synthetic */ class C00061 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f6xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f6xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f6xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f6xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f6xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f6xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f6xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f6xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00061.f6xa1df5c61[method.ordinal()]) {
            case 1:
                return new BackendRule();
            case 2:
                return new Builder((C00061) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\t\u0001\u0000\u0001\t\t\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u0000\u0004\u0000\u0005\u0000\u0006\f\u0007Ȼ\u0000\b:\u0000\tȈ", new Object[]{"authentication_", "authenticationCase_", "selector_", "address_", "deadline_", "minDeadline_", "operationDeadline_", "pathTranslation_", "protocol_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<BackendRule> parser = PARSER;
                if (parser == null) {
                    synchronized (BackendRule.class) {
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
        BackendRule defaultInstance = new BackendRule();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(BackendRule.class, defaultInstance);
    }

    public static BackendRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BackendRule> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
