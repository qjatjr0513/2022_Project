package com.google.api;

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

public final class Endpoint extends GeneratedMessageLite<Endpoint, Builder> implements EndpointOrBuilder {
    public static final int ALIASES_FIELD_NUMBER = 2;
    public static final int ALLOW_CORS_FIELD_NUMBER = 5;
    /* access modifiers changed from: private */
    public static final Endpoint DEFAULT_INSTANCE;
    public static final int FEATURES_FIELD_NUMBER = 4;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<Endpoint> PARSER = null;
    public static final int TARGET_FIELD_NUMBER = 101;
    private Internal.ProtobufList<String> aliases_ = GeneratedMessageLite.emptyProtobufList();
    private boolean allowCors_;
    private Internal.ProtobufList<String> features_ = GeneratedMessageLite.emptyProtobufList();
    private String name_ = "";
    private String target_ = "";

    private Endpoint() {
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

    @Deprecated
    public List<String> getAliasesList() {
        return this.aliases_;
    }

    @Deprecated
    public int getAliasesCount() {
        return this.aliases_.size();
    }

    @Deprecated
    public String getAliases(int index) {
        return (String) this.aliases_.get(index);
    }

    @Deprecated
    public ByteString getAliasesBytes(int index) {
        return ByteString.copyFromUtf8((String) this.aliases_.get(index));
    }

    private void ensureAliasesIsMutable() {
        Internal.ProtobufList<String> tmp = this.aliases_;
        if (!tmp.isModifiable()) {
            this.aliases_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setAliases(int index, String value) {
        value.getClass();
        ensureAliasesIsMutable();
        this.aliases_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addAliases(String value) {
        value.getClass();
        ensureAliasesIsMutable();
        this.aliases_.add(value);
    }

    /* access modifiers changed from: private */
    public void addAllAliases(Iterable<String> values) {
        ensureAliasesIsMutable();
        AbstractMessageLite.addAll(values, this.aliases_);
    }

    /* access modifiers changed from: private */
    public void clearAliases() {
        this.aliases_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void addAliasesBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        ensureAliasesIsMutable();
        this.aliases_.add(value.toStringUtf8());
    }

    public List<String> getFeaturesList() {
        return this.features_;
    }

    public int getFeaturesCount() {
        return this.features_.size();
    }

    public String getFeatures(int index) {
        return (String) this.features_.get(index);
    }

    public ByteString getFeaturesBytes(int index) {
        return ByteString.copyFromUtf8((String) this.features_.get(index));
    }

    private void ensureFeaturesIsMutable() {
        Internal.ProtobufList<String> tmp = this.features_;
        if (!tmp.isModifiable()) {
            this.features_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setFeatures(int index, String value) {
        value.getClass();
        ensureFeaturesIsMutable();
        this.features_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addFeatures(String value) {
        value.getClass();
        ensureFeaturesIsMutable();
        this.features_.add(value);
    }

    /* access modifiers changed from: private */
    public void addAllFeatures(Iterable<String> values) {
        ensureFeaturesIsMutable();
        AbstractMessageLite.addAll(values, this.features_);
    }

    /* access modifiers changed from: private */
    public void clearFeatures() {
        this.features_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void addFeaturesBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        ensureFeaturesIsMutable();
        this.features_.add(value.toStringUtf8());
    }

    public String getTarget() {
        return this.target_;
    }

    public ByteString getTargetBytes() {
        return ByteString.copyFromUtf8(this.target_);
    }

    /* access modifiers changed from: private */
    public void setTarget(String value) {
        value.getClass();
        this.target_ = value;
    }

    /* access modifiers changed from: private */
    public void clearTarget() {
        this.target_ = getDefaultInstance().getTarget();
    }

    /* access modifiers changed from: private */
    public void setTargetBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.target_ = value.toStringUtf8();
    }

    public boolean getAllowCors() {
        return this.allowCors_;
    }

    /* access modifiers changed from: private */
    public void setAllowCors(boolean value) {
        this.allowCors_ = value;
    }

    /* access modifiers changed from: private */
    public void clearAllowCors() {
        this.allowCors_ = false;
    }

    public static Endpoint parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Endpoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Endpoint parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Endpoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Endpoint parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Endpoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Endpoint parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Endpoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Endpoint parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Endpoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Endpoint parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Endpoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Endpoint parseFrom(InputStream input) throws IOException {
        return (Endpoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Endpoint parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Endpoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Endpoint parseDelimitedFrom(InputStream input) throws IOException {
        return (Endpoint) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Endpoint parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Endpoint) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Endpoint parseFrom(CodedInputStream input) throws IOException {
        return (Endpoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Endpoint parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Endpoint) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Endpoint prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Endpoint, Builder> implements EndpointOrBuilder {
        /* synthetic */ Builder(C00181 x0) {
            this();
        }

        private Builder() {
            super(Endpoint.DEFAULT_INSTANCE);
        }

        public String getName() {
            return ((Endpoint) this.instance).getName();
        }

        public ByteString getNameBytes() {
            return ((Endpoint) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((Endpoint) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((Endpoint) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((Endpoint) this.instance).setNameBytes(value);
            return this;
        }

        @Deprecated
        public List<String> getAliasesList() {
            return Collections.unmodifiableList(((Endpoint) this.instance).getAliasesList());
        }

        @Deprecated
        public int getAliasesCount() {
            return ((Endpoint) this.instance).getAliasesCount();
        }

        @Deprecated
        public String getAliases(int index) {
            return ((Endpoint) this.instance).getAliases(index);
        }

        @Deprecated
        public ByteString getAliasesBytes(int index) {
            return ((Endpoint) this.instance).getAliasesBytes(index);
        }

        @Deprecated
        public Builder setAliases(int index, String value) {
            copyOnWrite();
            ((Endpoint) this.instance).setAliases(index, value);
            return this;
        }

        @Deprecated
        public Builder addAliases(String value) {
            copyOnWrite();
            ((Endpoint) this.instance).addAliases(value);
            return this;
        }

        @Deprecated
        public Builder addAllAliases(Iterable<String> values) {
            copyOnWrite();
            ((Endpoint) this.instance).addAllAliases(values);
            return this;
        }

        @Deprecated
        public Builder clearAliases() {
            copyOnWrite();
            ((Endpoint) this.instance).clearAliases();
            return this;
        }

        @Deprecated
        public Builder addAliasesBytes(ByteString value) {
            copyOnWrite();
            ((Endpoint) this.instance).addAliasesBytes(value);
            return this;
        }

        public List<String> getFeaturesList() {
            return Collections.unmodifiableList(((Endpoint) this.instance).getFeaturesList());
        }

        public int getFeaturesCount() {
            return ((Endpoint) this.instance).getFeaturesCount();
        }

        public String getFeatures(int index) {
            return ((Endpoint) this.instance).getFeatures(index);
        }

        public ByteString getFeaturesBytes(int index) {
            return ((Endpoint) this.instance).getFeaturesBytes(index);
        }

        public Builder setFeatures(int index, String value) {
            copyOnWrite();
            ((Endpoint) this.instance).setFeatures(index, value);
            return this;
        }

        public Builder addFeatures(String value) {
            copyOnWrite();
            ((Endpoint) this.instance).addFeatures(value);
            return this;
        }

        public Builder addAllFeatures(Iterable<String> values) {
            copyOnWrite();
            ((Endpoint) this.instance).addAllFeatures(values);
            return this;
        }

        public Builder clearFeatures() {
            copyOnWrite();
            ((Endpoint) this.instance).clearFeatures();
            return this;
        }

        public Builder addFeaturesBytes(ByteString value) {
            copyOnWrite();
            ((Endpoint) this.instance).addFeaturesBytes(value);
            return this;
        }

        public String getTarget() {
            return ((Endpoint) this.instance).getTarget();
        }

        public ByteString getTargetBytes() {
            return ((Endpoint) this.instance).getTargetBytes();
        }

        public Builder setTarget(String value) {
            copyOnWrite();
            ((Endpoint) this.instance).setTarget(value);
            return this;
        }

        public Builder clearTarget() {
            copyOnWrite();
            ((Endpoint) this.instance).clearTarget();
            return this;
        }

        public Builder setTargetBytes(ByteString value) {
            copyOnWrite();
            ((Endpoint) this.instance).setTargetBytes(value);
            return this;
        }

        public boolean getAllowCors() {
            return ((Endpoint) this.instance).getAllowCors();
        }

        public Builder setAllowCors(boolean value) {
            copyOnWrite();
            ((Endpoint) this.instance).setAllowCors(value);
            return this;
        }

        public Builder clearAllowCors() {
            copyOnWrite();
            ((Endpoint) this.instance).clearAllowCors();
            return this;
        }
    }

    /* renamed from: com.google.api.Endpoint$1 */
    static /* synthetic */ class C00181 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f16xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f16xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f16xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f16xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f16xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f16xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f16xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f16xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00181.f16xa1df5c61[method.ordinal()]) {
            case 1:
                return new Endpoint();
            case 2:
                return new Builder((C00181) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001e\u0005\u0000\u0002\u0000\u0001Ȉ\u0002Ț\u0004Ț\u0005\u0007eȈ", new Object[]{"name_", "aliases_", "features_", "allowCors_", "target_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Endpoint> parser = PARSER;
                if (parser == null) {
                    synchronized (Endpoint.class) {
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
        Endpoint defaultInstance = new Endpoint();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Endpoint.class, defaultInstance);
    }

    public static Endpoint getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Endpoint> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
