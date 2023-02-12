package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class JwtLocation extends GeneratedMessageLite<JwtLocation, Builder> implements JwtLocationOrBuilder {
    /* access modifiers changed from: private */
    public static final JwtLocation DEFAULT_INSTANCE;
    public static final int HEADER_FIELD_NUMBER = 1;
    private static volatile Parser<JwtLocation> PARSER = null;
    public static final int QUERY_FIELD_NUMBER = 2;
    public static final int VALUE_PREFIX_FIELD_NUMBER = 3;
    private int inCase_ = 0;
    private Object in_;
    private String valuePrefix_ = "";

    private JwtLocation() {
    }

    public enum InCase {
        HEADER(1),
        QUERY(2),
        IN_NOT_SET(0);
        
        private final int value;

        private InCase(int value2) {
            this.value = value2;
        }

        @Deprecated
        public static InCase valueOf(int value2) {
            return forNumber(value2);
        }

        public static InCase forNumber(int value2) {
            switch (value2) {
                case 0:
                    return IN_NOT_SET;
                case 1:
                    return HEADER;
                case 2:
                    return QUERY;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    public InCase getInCase() {
        return InCase.forNumber(this.inCase_);
    }

    /* access modifiers changed from: private */
    public void clearIn() {
        this.inCase_ = 0;
        this.in_ = null;
    }

    public String getHeader() {
        if (this.inCase_ == 1) {
            return (String) this.in_;
        }
        return "";
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.protobuf.ByteString getHeaderBytes() {
        /*
            r3 = this;
            java.lang.String r0 = ""
            int r1 = r3.inCase_
            r2 = 1
            if (r1 != r2) goto L_0x000c
            java.lang.Object r1 = r3.in_
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0
        L_0x000c:
            com.google.protobuf.ByteString r1 = com.google.protobuf.ByteString.copyFromUtf8(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.JwtLocation.getHeaderBytes():com.google.protobuf.ByteString");
    }

    /* access modifiers changed from: private */
    public void setHeader(String value) {
        value.getClass();
        this.inCase_ = 1;
        this.in_ = value;
    }

    /* access modifiers changed from: private */
    public void clearHeader() {
        if (this.inCase_ == 1) {
            this.inCase_ = 0;
            this.in_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setHeaderBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.in_ = value.toStringUtf8();
        this.inCase_ = 1;
    }

    public String getQuery() {
        if (this.inCase_ == 2) {
            return (String) this.in_;
        }
        return "";
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.protobuf.ByteString getQueryBytes() {
        /*
            r3 = this;
            java.lang.String r0 = ""
            int r1 = r3.inCase_
            r2 = 2
            if (r1 != r2) goto L_0x000c
            java.lang.Object r1 = r3.in_
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0
        L_0x000c:
            com.google.protobuf.ByteString r1 = com.google.protobuf.ByteString.copyFromUtf8(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.JwtLocation.getQueryBytes():com.google.protobuf.ByteString");
    }

    /* access modifiers changed from: private */
    public void setQuery(String value) {
        value.getClass();
        this.inCase_ = 2;
        this.in_ = value;
    }

    /* access modifiers changed from: private */
    public void clearQuery() {
        if (this.inCase_ == 2) {
            this.inCase_ = 0;
            this.in_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setQueryBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.in_ = value.toStringUtf8();
        this.inCase_ = 2;
    }

    public String getValuePrefix() {
        return this.valuePrefix_;
    }

    public ByteString getValuePrefixBytes() {
        return ByteString.copyFromUtf8(this.valuePrefix_);
    }

    /* access modifiers changed from: private */
    public void setValuePrefix(String value) {
        value.getClass();
        this.valuePrefix_ = value;
    }

    /* access modifiers changed from: private */
    public void clearValuePrefix() {
        this.valuePrefix_ = getDefaultInstance().getValuePrefix();
    }

    /* access modifiers changed from: private */
    public void setValuePrefixBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.valuePrefix_ = value.toStringUtf8();
    }

    public static JwtLocation parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (JwtLocation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static JwtLocation parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (JwtLocation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static JwtLocation parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (JwtLocation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static JwtLocation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (JwtLocation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static JwtLocation parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (JwtLocation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static JwtLocation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (JwtLocation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static JwtLocation parseFrom(InputStream input) throws IOException {
        return (JwtLocation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static JwtLocation parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JwtLocation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static JwtLocation parseDelimitedFrom(InputStream input) throws IOException {
        return (JwtLocation) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static JwtLocation parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JwtLocation) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static JwtLocation parseFrom(CodedInputStream input) throws IOException {
        return (JwtLocation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static JwtLocation parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JwtLocation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(JwtLocation prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<JwtLocation, Builder> implements JwtLocationOrBuilder {
        /* synthetic */ Builder(C00231 x0) {
            this();
        }

        private Builder() {
            super(JwtLocation.DEFAULT_INSTANCE);
        }

        public InCase getInCase() {
            return ((JwtLocation) this.instance).getInCase();
        }

        public Builder clearIn() {
            copyOnWrite();
            ((JwtLocation) this.instance).clearIn();
            return this;
        }

        public String getHeader() {
            return ((JwtLocation) this.instance).getHeader();
        }

        public ByteString getHeaderBytes() {
            return ((JwtLocation) this.instance).getHeaderBytes();
        }

        public Builder setHeader(String value) {
            copyOnWrite();
            ((JwtLocation) this.instance).setHeader(value);
            return this;
        }

        public Builder clearHeader() {
            copyOnWrite();
            ((JwtLocation) this.instance).clearHeader();
            return this;
        }

        public Builder setHeaderBytes(ByteString value) {
            copyOnWrite();
            ((JwtLocation) this.instance).setHeaderBytes(value);
            return this;
        }

        public String getQuery() {
            return ((JwtLocation) this.instance).getQuery();
        }

        public ByteString getQueryBytes() {
            return ((JwtLocation) this.instance).getQueryBytes();
        }

        public Builder setQuery(String value) {
            copyOnWrite();
            ((JwtLocation) this.instance).setQuery(value);
            return this;
        }

        public Builder clearQuery() {
            copyOnWrite();
            ((JwtLocation) this.instance).clearQuery();
            return this;
        }

        public Builder setQueryBytes(ByteString value) {
            copyOnWrite();
            ((JwtLocation) this.instance).setQueryBytes(value);
            return this;
        }

        public String getValuePrefix() {
            return ((JwtLocation) this.instance).getValuePrefix();
        }

        public ByteString getValuePrefixBytes() {
            return ((JwtLocation) this.instance).getValuePrefixBytes();
        }

        public Builder setValuePrefix(String value) {
            copyOnWrite();
            ((JwtLocation) this.instance).setValuePrefix(value);
            return this;
        }

        public Builder clearValuePrefix() {
            copyOnWrite();
            ((JwtLocation) this.instance).clearValuePrefix();
            return this;
        }

        public Builder setValuePrefixBytes(ByteString value) {
            copyOnWrite();
            ((JwtLocation) this.instance).setValuePrefixBytes(value);
            return this;
        }
    }

    /* renamed from: com.google.api.JwtLocation$1 */
    static /* synthetic */ class C00231 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f20xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f20xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f20xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f20xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f20xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f20xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f20xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f20xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00231.f20xa1df5c61[method.ordinal()]) {
            case 1:
                return new JwtLocation();
            case 2:
                return new Builder((C00231) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȼ\u0000\u0002Ȼ\u0000\u0003Ȉ", new Object[]{"in_", "inCase_", "valuePrefix_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<JwtLocation> parser = PARSER;
                if (parser == null) {
                    synchronized (JwtLocation.class) {
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
        JwtLocation defaultInstance = new JwtLocation();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(JwtLocation.class, defaultInstance);
    }

    public static JwtLocation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<JwtLocation> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
