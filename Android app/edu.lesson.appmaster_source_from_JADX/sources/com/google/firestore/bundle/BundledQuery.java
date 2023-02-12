package com.google.firestore.bundle;

import com.google.firestore.p002v1.StructuredQuery;
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

public final class BundledQuery extends GeneratedMessageLite<BundledQuery, Builder> implements BundledQueryOrBuilder {
    /* access modifiers changed from: private */
    public static final BundledQuery DEFAULT_INSTANCE;
    public static final int LIMIT_TYPE_FIELD_NUMBER = 3;
    public static final int PARENT_FIELD_NUMBER = 1;
    private static volatile Parser<BundledQuery> PARSER = null;
    public static final int STRUCTURED_QUERY_FIELD_NUMBER = 2;
    private int limitType_;
    private String parent_ = "";
    private int queryTypeCase_ = 0;
    private Object queryType_;

    private BundledQuery() {
    }

    public enum LimitType implements Internal.EnumLite {
        FIRST(0),
        LAST(1),
        UNRECOGNIZED(-1);
        
        public static final int FIRST_VALUE = 0;
        public static final int LAST_VALUE = 1;
        private static final Internal.EnumLiteMap<LimitType> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<LimitType>() {
                public LimitType findValueByNumber(int number) {
                    return LimitType.forNumber(number);
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
        public static LimitType valueOf(int value2) {
            return forNumber(value2);
        }

        public static LimitType forNumber(int value2) {
            switch (value2) {
                case 0:
                    return FIRST;
                case 1:
                    return LAST;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<LimitType> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return LimitTypeVerifier.INSTANCE;
        }

        private static final class LimitTypeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = null;

            private LimitTypeVerifier() {
            }

            static {
                INSTANCE = new LimitTypeVerifier();
            }

            public boolean isInRange(int number) {
                return LimitType.forNumber(number) != null;
            }
        }

        private LimitType(int value2) {
            this.value = value2;
        }
    }

    public enum QueryTypeCase {
        STRUCTURED_QUERY(2),
        QUERYTYPE_NOT_SET(0);
        
        private final int value;

        private QueryTypeCase(int value2) {
            this.value = value2;
        }

        @Deprecated
        public static QueryTypeCase valueOf(int value2) {
            return forNumber(value2);
        }

        public static QueryTypeCase forNumber(int value2) {
            switch (value2) {
                case 0:
                    return QUERYTYPE_NOT_SET;
                case 2:
                    return STRUCTURED_QUERY;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    public QueryTypeCase getQueryTypeCase() {
        return QueryTypeCase.forNumber(this.queryTypeCase_);
    }

    /* access modifiers changed from: private */
    public void clearQueryType() {
        this.queryTypeCase_ = 0;
        this.queryType_ = null;
    }

    public String getParent() {
        return this.parent_;
    }

    public ByteString getParentBytes() {
        return ByteString.copyFromUtf8(this.parent_);
    }

    /* access modifiers changed from: private */
    public void setParent(String value) {
        value.getClass();
        this.parent_ = value;
    }

    /* access modifiers changed from: private */
    public void clearParent() {
        this.parent_ = getDefaultInstance().getParent();
    }

    /* access modifiers changed from: private */
    public void setParentBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.parent_ = value.toStringUtf8();
    }

    public boolean hasStructuredQuery() {
        return this.queryTypeCase_ == 2;
    }

    public StructuredQuery getStructuredQuery() {
        if (this.queryTypeCase_ == 2) {
            return (StructuredQuery) this.queryType_;
        }
        return StructuredQuery.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setStructuredQuery(StructuredQuery value) {
        value.getClass();
        this.queryType_ = value;
        this.queryTypeCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void mergeStructuredQuery(StructuredQuery value) {
        value.getClass();
        if (this.queryTypeCase_ != 2 || this.queryType_ == StructuredQuery.getDefaultInstance()) {
            this.queryType_ = value;
        } else {
            this.queryType_ = ((StructuredQuery.Builder) StructuredQuery.newBuilder((StructuredQuery) this.queryType_).mergeFrom(value)).buildPartial();
        }
        this.queryTypeCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void clearStructuredQuery() {
        if (this.queryTypeCase_ == 2) {
            this.queryTypeCase_ = 0;
            this.queryType_ = null;
        }
    }

    public int getLimitTypeValue() {
        return this.limitType_;
    }

    public LimitType getLimitType() {
        LimitType result = LimitType.forNumber(this.limitType_);
        return result == null ? LimitType.UNRECOGNIZED : result;
    }

    /* access modifiers changed from: private */
    public void setLimitTypeValue(int value) {
        this.limitType_ = value;
    }

    /* access modifiers changed from: private */
    public void setLimitType(LimitType value) {
        this.limitType_ = value.getNumber();
    }

    /* access modifiers changed from: private */
    public void clearLimitType() {
        this.limitType_ = 0;
    }

    public static BundledQuery parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BundledQuery parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BundledQuery parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BundledQuery parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BundledQuery parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BundledQuery parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BundledQuery parseFrom(InputStream input) throws IOException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BundledQuery parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BundledQuery parseDelimitedFrom(InputStream input) throws IOException {
        return (BundledQuery) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static BundledQuery parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BundledQuery) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BundledQuery parseFrom(CodedInputStream input) throws IOException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BundledQuery parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BundledQuery prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BundledQuery, Builder> implements BundledQueryOrBuilder {
        /* synthetic */ Builder(C08211 x0) {
            this();
        }

        private Builder() {
            super(BundledQuery.DEFAULT_INSTANCE);
        }

        public QueryTypeCase getQueryTypeCase() {
            return ((BundledQuery) this.instance).getQueryTypeCase();
        }

        public Builder clearQueryType() {
            copyOnWrite();
            ((BundledQuery) this.instance).clearQueryType();
            return this;
        }

        public String getParent() {
            return ((BundledQuery) this.instance).getParent();
        }

        public ByteString getParentBytes() {
            return ((BundledQuery) this.instance).getParentBytes();
        }

        public Builder setParent(String value) {
            copyOnWrite();
            ((BundledQuery) this.instance).setParent(value);
            return this;
        }

        public Builder clearParent() {
            copyOnWrite();
            ((BundledQuery) this.instance).clearParent();
            return this;
        }

        public Builder setParentBytes(ByteString value) {
            copyOnWrite();
            ((BundledQuery) this.instance).setParentBytes(value);
            return this;
        }

        public boolean hasStructuredQuery() {
            return ((BundledQuery) this.instance).hasStructuredQuery();
        }

        public StructuredQuery getStructuredQuery() {
            return ((BundledQuery) this.instance).getStructuredQuery();
        }

        public Builder setStructuredQuery(StructuredQuery value) {
            copyOnWrite();
            ((BundledQuery) this.instance).setStructuredQuery(value);
            return this;
        }

        public Builder setStructuredQuery(StructuredQuery.Builder builderForValue) {
            copyOnWrite();
            ((BundledQuery) this.instance).setStructuredQuery((StructuredQuery) builderForValue.build());
            return this;
        }

        public Builder mergeStructuredQuery(StructuredQuery value) {
            copyOnWrite();
            ((BundledQuery) this.instance).mergeStructuredQuery(value);
            return this;
        }

        public Builder clearStructuredQuery() {
            copyOnWrite();
            ((BundledQuery) this.instance).clearStructuredQuery();
            return this;
        }

        public int getLimitTypeValue() {
            return ((BundledQuery) this.instance).getLimitTypeValue();
        }

        public Builder setLimitTypeValue(int value) {
            copyOnWrite();
            ((BundledQuery) this.instance).setLimitTypeValue(value);
            return this;
        }

        public LimitType getLimitType() {
            return ((BundledQuery) this.instance).getLimitType();
        }

        public Builder setLimitType(LimitType value) {
            copyOnWrite();
            ((BundledQuery) this.instance).setLimitType(value);
            return this;
        }

        public Builder clearLimitType() {
            copyOnWrite();
            ((BundledQuery) this.instance).clearLimitType();
            return this;
        }
    }

    /* renamed from: com.google.firestore.bundle.BundledQuery$1 */
    static /* synthetic */ class C08211 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f199xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f199xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f199xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f199xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f199xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f199xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f199xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f199xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C08211.f199xa1df5c61[method.ordinal()]) {
            case 1:
                return new BundledQuery();
            case 2:
                return new Builder((C08211) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002<\u0000\u0003\f", new Object[]{"queryType_", "queryTypeCase_", "parent_", StructuredQuery.class, "limitType_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<BundledQuery> parser = PARSER;
                if (parser == null) {
                    synchronized (BundledQuery.class) {
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
        BundledQuery defaultInstance = new BundledQuery();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(BundledQuery.class, defaultInstance);
    }

    public static BundledQuery getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BundledQuery> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
