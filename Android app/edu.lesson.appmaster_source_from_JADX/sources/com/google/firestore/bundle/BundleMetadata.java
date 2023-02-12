package com.google.firestore.bundle;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class BundleMetadata extends GeneratedMessageLite<BundleMetadata, Builder> implements BundleMetadataOrBuilder {
    public static final int CREATE_TIME_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final BundleMetadata DEFAULT_INSTANCE;
    public static final int ID_FIELD_NUMBER = 1;
    private static volatile Parser<BundleMetadata> PARSER = null;
    public static final int TOTAL_BYTES_FIELD_NUMBER = 5;
    public static final int TOTAL_DOCUMENTS_FIELD_NUMBER = 4;
    public static final int VERSION_FIELD_NUMBER = 3;
    private Timestamp createTime_;
    private String id_ = "";
    private long totalBytes_;
    private int totalDocuments_;
    private int version_;

    private BundleMetadata() {
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

    public boolean hasCreateTime() {
        return this.createTime_ != null;
    }

    public Timestamp getCreateTime() {
        Timestamp timestamp = this.createTime_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    /* access modifiers changed from: private */
    public void setCreateTime(Timestamp value) {
        value.getClass();
        this.createTime_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeCreateTime(Timestamp value) {
        value.getClass();
        Timestamp timestamp = this.createTime_;
        if (timestamp == null || timestamp == Timestamp.getDefaultInstance()) {
            this.createTime_ = value;
        } else {
            this.createTime_ = (Timestamp) ((Timestamp.Builder) Timestamp.newBuilder(this.createTime_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearCreateTime() {
        this.createTime_ = null;
    }

    public int getVersion() {
        return this.version_;
    }

    /* access modifiers changed from: private */
    public void setVersion(int value) {
        this.version_ = value;
    }

    /* access modifiers changed from: private */
    public void clearVersion() {
        this.version_ = 0;
    }

    public int getTotalDocuments() {
        return this.totalDocuments_;
    }

    /* access modifiers changed from: private */
    public void setTotalDocuments(int value) {
        this.totalDocuments_ = value;
    }

    /* access modifiers changed from: private */
    public void clearTotalDocuments() {
        this.totalDocuments_ = 0;
    }

    public long getTotalBytes() {
        return this.totalBytes_;
    }

    /* access modifiers changed from: private */
    public void setTotalBytes(long value) {
        this.totalBytes_ = value;
    }

    /* access modifiers changed from: private */
    public void clearTotalBytes() {
        this.totalBytes_ = 0;
    }

    public static BundleMetadata parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BundleMetadata parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BundleMetadata parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BundleMetadata parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BundleMetadata parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BundleMetadata parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BundleMetadata parseFrom(InputStream input) throws IOException {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BundleMetadata parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BundleMetadata parseDelimitedFrom(InputStream input) throws IOException {
        return (BundleMetadata) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static BundleMetadata parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BundleMetadata) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BundleMetadata parseFrom(CodedInputStream input) throws IOException {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BundleMetadata parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BundleMetadata prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BundleMetadata, Builder> implements BundleMetadataOrBuilder {
        /* synthetic */ Builder(C08191 x0) {
            this();
        }

        private Builder() {
            super(BundleMetadata.DEFAULT_INSTANCE);
        }

        public String getId() {
            return ((BundleMetadata) this.instance).getId();
        }

        public ByteString getIdBytes() {
            return ((BundleMetadata) this.instance).getIdBytes();
        }

        public Builder setId(String value) {
            copyOnWrite();
            ((BundleMetadata) this.instance).setId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((BundleMetadata) this.instance).clearId();
            return this;
        }

        public Builder setIdBytes(ByteString value) {
            copyOnWrite();
            ((BundleMetadata) this.instance).setIdBytes(value);
            return this;
        }

        public boolean hasCreateTime() {
            return ((BundleMetadata) this.instance).hasCreateTime();
        }

        public Timestamp getCreateTime() {
            return ((BundleMetadata) this.instance).getCreateTime();
        }

        public Builder setCreateTime(Timestamp value) {
            copyOnWrite();
            ((BundleMetadata) this.instance).setCreateTime(value);
            return this;
        }

        public Builder setCreateTime(Timestamp.Builder builderForValue) {
            copyOnWrite();
            ((BundleMetadata) this.instance).setCreateTime((Timestamp) builderForValue.build());
            return this;
        }

        public Builder mergeCreateTime(Timestamp value) {
            copyOnWrite();
            ((BundleMetadata) this.instance).mergeCreateTime(value);
            return this;
        }

        public Builder clearCreateTime() {
            copyOnWrite();
            ((BundleMetadata) this.instance).clearCreateTime();
            return this;
        }

        public int getVersion() {
            return ((BundleMetadata) this.instance).getVersion();
        }

        public Builder setVersion(int value) {
            copyOnWrite();
            ((BundleMetadata) this.instance).setVersion(value);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((BundleMetadata) this.instance).clearVersion();
            return this;
        }

        public int getTotalDocuments() {
            return ((BundleMetadata) this.instance).getTotalDocuments();
        }

        public Builder setTotalDocuments(int value) {
            copyOnWrite();
            ((BundleMetadata) this.instance).setTotalDocuments(value);
            return this;
        }

        public Builder clearTotalDocuments() {
            copyOnWrite();
            ((BundleMetadata) this.instance).clearTotalDocuments();
            return this;
        }

        public long getTotalBytes() {
            return ((BundleMetadata) this.instance).getTotalBytes();
        }

        public Builder setTotalBytes(long value) {
            copyOnWrite();
            ((BundleMetadata) this.instance).setTotalBytes(value);
            return this;
        }

        public Builder clearTotalBytes() {
            copyOnWrite();
            ((BundleMetadata) this.instance).clearTotalBytes();
            return this;
        }
    }

    /* renamed from: com.google.firestore.bundle.BundleMetadata$1 */
    static /* synthetic */ class C08191 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f197xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f197xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f197xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f197xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f197xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f197xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f197xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f197xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C08191.f197xa1df5c61[method.ordinal()]) {
            case 1:
                return new BundleMetadata();
            case 2:
                return new Builder((C08191) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002\t\u0003\u000b\u0004\u000b\u0005\u0003", new Object[]{"id_", "createTime_", "version_", "totalDocuments_", "totalBytes_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<BundleMetadata> parser = PARSER;
                if (parser == null) {
                    synchronized (BundleMetadata.class) {
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
        BundleMetadata defaultInstance = new BundleMetadata();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(BundleMetadata.class, defaultInstance);
    }

    public static BundleMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BundleMetadata> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
