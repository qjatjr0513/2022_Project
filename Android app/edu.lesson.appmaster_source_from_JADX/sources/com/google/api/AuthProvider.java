package com.google.api;

import com.google.api.JwtLocation;
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

public final class AuthProvider extends GeneratedMessageLite<AuthProvider, Builder> implements AuthProviderOrBuilder {
    public static final int AUDIENCES_FIELD_NUMBER = 4;
    public static final int AUTHORIZATION_URL_FIELD_NUMBER = 5;
    /* access modifiers changed from: private */
    public static final AuthProvider DEFAULT_INSTANCE;
    public static final int ID_FIELD_NUMBER = 1;
    public static final int ISSUER_FIELD_NUMBER = 2;
    public static final int JWKS_URI_FIELD_NUMBER = 3;
    public static final int JWT_LOCATIONS_FIELD_NUMBER = 6;
    private static volatile Parser<AuthProvider> PARSER;
    private String audiences_ = "";
    private String authorizationUrl_ = "";
    private String id_ = "";
    private String issuer_ = "";
    private String jwksUri_ = "";
    private Internal.ProtobufList<JwtLocation> jwtLocations_ = emptyProtobufList();

    private AuthProvider() {
    }

    public String getId() {
        return this.id_;
    }

    public ByteString getIdBytes() {
        return ByteString.copyFromUtf8(this.id_);
    }

    /* access modifiers changed from: private */
    public void setId(String value) {
        value.getClass();
        this.id_ = value;
    }

    /* access modifiers changed from: private */
    public void clearId() {
        this.id_ = getDefaultInstance().getId();
    }

    /* access modifiers changed from: private */
    public void setIdBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.id_ = value.toStringUtf8();
    }

    public String getIssuer() {
        return this.issuer_;
    }

    public ByteString getIssuerBytes() {
        return ByteString.copyFromUtf8(this.issuer_);
    }

    /* access modifiers changed from: private */
    public void setIssuer(String value) {
        value.getClass();
        this.issuer_ = value;
    }

    /* access modifiers changed from: private */
    public void clearIssuer() {
        this.issuer_ = getDefaultInstance().getIssuer();
    }

    /* access modifiers changed from: private */
    public void setIssuerBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.issuer_ = value.toStringUtf8();
    }

    public String getJwksUri() {
        return this.jwksUri_;
    }

    public ByteString getJwksUriBytes() {
        return ByteString.copyFromUtf8(this.jwksUri_);
    }

    /* access modifiers changed from: private */
    public void setJwksUri(String value) {
        value.getClass();
        this.jwksUri_ = value;
    }

    /* access modifiers changed from: private */
    public void clearJwksUri() {
        this.jwksUri_ = getDefaultInstance().getJwksUri();
    }

    /* access modifiers changed from: private */
    public void setJwksUriBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.jwksUri_ = value.toStringUtf8();
    }

    public String getAudiences() {
        return this.audiences_;
    }

    public ByteString getAudiencesBytes() {
        return ByteString.copyFromUtf8(this.audiences_);
    }

    /* access modifiers changed from: private */
    public void setAudiences(String value) {
        value.getClass();
        this.audiences_ = value;
    }

    /* access modifiers changed from: private */
    public void clearAudiences() {
        this.audiences_ = getDefaultInstance().getAudiences();
    }

    /* access modifiers changed from: private */
    public void setAudiencesBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.audiences_ = value.toStringUtf8();
    }

    public String getAuthorizationUrl() {
        return this.authorizationUrl_;
    }

    public ByteString getAuthorizationUrlBytes() {
        return ByteString.copyFromUtf8(this.authorizationUrl_);
    }

    /* access modifiers changed from: private */
    public void setAuthorizationUrl(String value) {
        value.getClass();
        this.authorizationUrl_ = value;
    }

    /* access modifiers changed from: private */
    public void clearAuthorizationUrl() {
        this.authorizationUrl_ = getDefaultInstance().getAuthorizationUrl();
    }

    /* access modifiers changed from: private */
    public void setAuthorizationUrlBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.authorizationUrl_ = value.toStringUtf8();
    }

    public List<JwtLocation> getJwtLocationsList() {
        return this.jwtLocations_;
    }

    public List<? extends JwtLocationOrBuilder> getJwtLocationsOrBuilderList() {
        return this.jwtLocations_;
    }

    public int getJwtLocationsCount() {
        return this.jwtLocations_.size();
    }

    public JwtLocation getJwtLocations(int index) {
        return (JwtLocation) this.jwtLocations_.get(index);
    }

    public JwtLocationOrBuilder getJwtLocationsOrBuilder(int index) {
        return (JwtLocationOrBuilder) this.jwtLocations_.get(index);
    }

    private void ensureJwtLocationsIsMutable() {
        Internal.ProtobufList<JwtLocation> tmp = this.jwtLocations_;
        if (!tmp.isModifiable()) {
            this.jwtLocations_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setJwtLocations(int index, JwtLocation value) {
        value.getClass();
        ensureJwtLocationsIsMutable();
        this.jwtLocations_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addJwtLocations(JwtLocation value) {
        value.getClass();
        ensureJwtLocationsIsMutable();
        this.jwtLocations_.add(value);
    }

    /* access modifiers changed from: private */
    public void addJwtLocations(int index, JwtLocation value) {
        value.getClass();
        ensureJwtLocationsIsMutable();
        this.jwtLocations_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllJwtLocations(Iterable<? extends JwtLocation> values) {
        ensureJwtLocationsIsMutable();
        AbstractMessageLite.addAll(values, this.jwtLocations_);
    }

    /* access modifiers changed from: private */
    public void clearJwtLocations() {
        this.jwtLocations_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeJwtLocations(int index) {
        ensureJwtLocationsIsMutable();
        this.jwtLocations_.remove(index);
    }

    public static AuthProvider parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (AuthProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AuthProvider parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AuthProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AuthProvider parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AuthProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AuthProvider parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AuthProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AuthProvider parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AuthProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AuthProvider parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AuthProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AuthProvider parseFrom(InputStream input) throws IOException {
        return (AuthProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AuthProvider parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AuthProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AuthProvider parseDelimitedFrom(InputStream input) throws IOException {
        return (AuthProvider) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AuthProvider parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AuthProvider) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AuthProvider parseFrom(CodedInputStream input) throws IOException {
        return (AuthProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AuthProvider parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AuthProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AuthProvider prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AuthProvider, Builder> implements AuthProviderOrBuilder {
        /* synthetic */ Builder(C00011 x0) {
            this();
        }

        private Builder() {
            super(AuthProvider.DEFAULT_INSTANCE);
        }

        public String getId() {
            return ((AuthProvider) this.instance).getId();
        }

        public ByteString getIdBytes() {
            return ((AuthProvider) this.instance).getIdBytes();
        }

        public Builder setId(String value) {
            copyOnWrite();
            ((AuthProvider) this.instance).setId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((AuthProvider) this.instance).clearId();
            return this;
        }

        public Builder setIdBytes(ByteString value) {
            copyOnWrite();
            ((AuthProvider) this.instance).setIdBytes(value);
            return this;
        }

        public String getIssuer() {
            return ((AuthProvider) this.instance).getIssuer();
        }

        public ByteString getIssuerBytes() {
            return ((AuthProvider) this.instance).getIssuerBytes();
        }

        public Builder setIssuer(String value) {
            copyOnWrite();
            ((AuthProvider) this.instance).setIssuer(value);
            return this;
        }

        public Builder clearIssuer() {
            copyOnWrite();
            ((AuthProvider) this.instance).clearIssuer();
            return this;
        }

        public Builder setIssuerBytes(ByteString value) {
            copyOnWrite();
            ((AuthProvider) this.instance).setIssuerBytes(value);
            return this;
        }

        public String getJwksUri() {
            return ((AuthProvider) this.instance).getJwksUri();
        }

        public ByteString getJwksUriBytes() {
            return ((AuthProvider) this.instance).getJwksUriBytes();
        }

        public Builder setJwksUri(String value) {
            copyOnWrite();
            ((AuthProvider) this.instance).setJwksUri(value);
            return this;
        }

        public Builder clearJwksUri() {
            copyOnWrite();
            ((AuthProvider) this.instance).clearJwksUri();
            return this;
        }

        public Builder setJwksUriBytes(ByteString value) {
            copyOnWrite();
            ((AuthProvider) this.instance).setJwksUriBytes(value);
            return this;
        }

        public String getAudiences() {
            return ((AuthProvider) this.instance).getAudiences();
        }

        public ByteString getAudiencesBytes() {
            return ((AuthProvider) this.instance).getAudiencesBytes();
        }

        public Builder setAudiences(String value) {
            copyOnWrite();
            ((AuthProvider) this.instance).setAudiences(value);
            return this;
        }

        public Builder clearAudiences() {
            copyOnWrite();
            ((AuthProvider) this.instance).clearAudiences();
            return this;
        }

        public Builder setAudiencesBytes(ByteString value) {
            copyOnWrite();
            ((AuthProvider) this.instance).setAudiencesBytes(value);
            return this;
        }

        public String getAuthorizationUrl() {
            return ((AuthProvider) this.instance).getAuthorizationUrl();
        }

        public ByteString getAuthorizationUrlBytes() {
            return ((AuthProvider) this.instance).getAuthorizationUrlBytes();
        }

        public Builder setAuthorizationUrl(String value) {
            copyOnWrite();
            ((AuthProvider) this.instance).setAuthorizationUrl(value);
            return this;
        }

        public Builder clearAuthorizationUrl() {
            copyOnWrite();
            ((AuthProvider) this.instance).clearAuthorizationUrl();
            return this;
        }

        public Builder setAuthorizationUrlBytes(ByteString value) {
            copyOnWrite();
            ((AuthProvider) this.instance).setAuthorizationUrlBytes(value);
            return this;
        }

        public List<JwtLocation> getJwtLocationsList() {
            return Collections.unmodifiableList(((AuthProvider) this.instance).getJwtLocationsList());
        }

        public int getJwtLocationsCount() {
            return ((AuthProvider) this.instance).getJwtLocationsCount();
        }

        public JwtLocation getJwtLocations(int index) {
            return ((AuthProvider) this.instance).getJwtLocations(index);
        }

        public Builder setJwtLocations(int index, JwtLocation value) {
            copyOnWrite();
            ((AuthProvider) this.instance).setJwtLocations(index, value);
            return this;
        }

        public Builder setJwtLocations(int index, JwtLocation.Builder builderForValue) {
            copyOnWrite();
            ((AuthProvider) this.instance).setJwtLocations(index, (JwtLocation) builderForValue.build());
            return this;
        }

        public Builder addJwtLocations(JwtLocation value) {
            copyOnWrite();
            ((AuthProvider) this.instance).addJwtLocations(value);
            return this;
        }

        public Builder addJwtLocations(int index, JwtLocation value) {
            copyOnWrite();
            ((AuthProvider) this.instance).addJwtLocations(index, value);
            return this;
        }

        public Builder addJwtLocations(JwtLocation.Builder builderForValue) {
            copyOnWrite();
            ((AuthProvider) this.instance).addJwtLocations((JwtLocation) builderForValue.build());
            return this;
        }

        public Builder addJwtLocations(int index, JwtLocation.Builder builderForValue) {
            copyOnWrite();
            ((AuthProvider) this.instance).addJwtLocations(index, (JwtLocation) builderForValue.build());
            return this;
        }

        public Builder addAllJwtLocations(Iterable<? extends JwtLocation> values) {
            copyOnWrite();
            ((AuthProvider) this.instance).addAllJwtLocations(values);
            return this;
        }

        public Builder clearJwtLocations() {
            copyOnWrite();
            ((AuthProvider) this.instance).clearJwtLocations();
            return this;
        }

        public Builder removeJwtLocations(int index) {
            copyOnWrite();
            ((AuthProvider) this.instance).removeJwtLocations(index);
            return this;
        }
    }

    /* renamed from: com.google.api.AuthProvider$1 */
    static /* synthetic */ class C00011 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f1xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f1xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f1xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f1xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f1xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f1xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00011.f1xa1df5c61[method.ordinal()]) {
            case 1:
                return new AuthProvider();
            case 2:
                return new Builder((C00011) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0006\u0000\u0000\u0001\u0006\u0006\u0000\u0001\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004Ȉ\u0005Ȉ\u0006\u001b", new Object[]{"id_", "issuer_", "jwksUri_", "audiences_", "authorizationUrl_", "jwtLocations_", JwtLocation.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<AuthProvider> parser = PARSER;
                if (parser == null) {
                    synchronized (AuthProvider.class) {
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
        AuthProvider defaultInstance = new AuthProvider();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(AuthProvider.class, defaultInstance);
    }

    public static AuthProvider getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuthProvider> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
