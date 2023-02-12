package com.google.firestore.bundle;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class BundledDocumentMetadata extends GeneratedMessageLite<BundledDocumentMetadata, Builder> implements BundledDocumentMetadataOrBuilder {
    /* access modifiers changed from: private */
    public static final BundledDocumentMetadata DEFAULT_INSTANCE;
    public static final int EXISTS_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<BundledDocumentMetadata> PARSER = null;
    public static final int QUERIES_FIELD_NUMBER = 4;
    public static final int READ_TIME_FIELD_NUMBER = 2;
    private boolean exists_;
    private String name_ = "";
    private Internal.ProtobufList<String> queries_ = GeneratedMessageLite.emptyProtobufList();
    private Timestamp readTime_;

    private BundledDocumentMetadata() {
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

    public boolean hasReadTime() {
        return this.readTime_ != null;
    }

    public Timestamp getReadTime() {
        Timestamp timestamp = this.readTime_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    /* access modifiers changed from: private */
    public void setReadTime(Timestamp value) {
        value.getClass();
        this.readTime_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeReadTime(Timestamp value) {
        value.getClass();
        Timestamp timestamp = this.readTime_;
        if (timestamp == null || timestamp == Timestamp.getDefaultInstance()) {
            this.readTime_ = value;
        } else {
            this.readTime_ = (Timestamp) ((Timestamp.Builder) Timestamp.newBuilder(this.readTime_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearReadTime() {
        this.readTime_ = null;
    }

    public boolean getExists() {
        return this.exists_;
    }

    /* access modifiers changed from: private */
    public void setExists(boolean value) {
        this.exists_ = value;
    }

    /* access modifiers changed from: private */
    public void clearExists() {
        this.exists_ = false;
    }

    public List<String> getQueriesList() {
        return this.queries_;
    }

    public int getQueriesCount() {
        return this.queries_.size();
    }

    public String getQueries(int index) {
        return (String) this.queries_.get(index);
    }

    public ByteString getQueriesBytes(int index) {
        return ByteString.copyFromUtf8((String) this.queries_.get(index));
    }

    private void ensureQueriesIsMutable() {
        Internal.ProtobufList<String> tmp = this.queries_;
        if (!tmp.isModifiable()) {
            this.queries_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setQueries(int index, String value) {
        value.getClass();
        ensureQueriesIsMutable();
        this.queries_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addQueries(String value) {
        value.getClass();
        ensureQueriesIsMutable();
        this.queries_.add(value);
    }

    /* access modifiers changed from: private */
    public void addAllQueries(Iterable<String> values) {
        ensureQueriesIsMutable();
        AbstractMessageLite.addAll(values, this.queries_);
    }

    /* access modifiers changed from: private */
    public void clearQueries() {
        this.queries_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void addQueriesBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        ensureQueriesIsMutable();
        this.queries_.add(value.toStringUtf8());
    }

    public static BundledDocumentMetadata parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BundledDocumentMetadata parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BundledDocumentMetadata parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BundledDocumentMetadata parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BundledDocumentMetadata parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BundledDocumentMetadata parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BundledDocumentMetadata parseFrom(InputStream input) throws IOException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BundledDocumentMetadata parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BundledDocumentMetadata parseDelimitedFrom(InputStream input) throws IOException {
        return (BundledDocumentMetadata) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static BundledDocumentMetadata parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BundledDocumentMetadata) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BundledDocumentMetadata parseFrom(CodedInputStream input) throws IOException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BundledDocumentMetadata parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BundledDocumentMetadata prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BundledDocumentMetadata, Builder> implements BundledDocumentMetadataOrBuilder {
        /* synthetic */ Builder(C08201 x0) {
            this();
        }

        private Builder() {
            super(BundledDocumentMetadata.DEFAULT_INSTANCE);
        }

        public String getName() {
            return ((BundledDocumentMetadata) this.instance).getName();
        }

        public ByteString getNameBytes() {
            return ((BundledDocumentMetadata) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).setNameBytes(value);
            return this;
        }

        public boolean hasReadTime() {
            return ((BundledDocumentMetadata) this.instance).hasReadTime();
        }

        public Timestamp getReadTime() {
            return ((BundledDocumentMetadata) this.instance).getReadTime();
        }

        public Builder setReadTime(Timestamp value) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).setReadTime(value);
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builderForValue) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).setReadTime((Timestamp) builderForValue.build());
            return this;
        }

        public Builder mergeReadTime(Timestamp value) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).mergeReadTime(value);
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).clearReadTime();
            return this;
        }

        public boolean getExists() {
            return ((BundledDocumentMetadata) this.instance).getExists();
        }

        public Builder setExists(boolean value) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).setExists(value);
            return this;
        }

        public Builder clearExists() {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).clearExists();
            return this;
        }

        public List<String> getQueriesList() {
            return Collections.unmodifiableList(((BundledDocumentMetadata) this.instance).getQueriesList());
        }

        public int getQueriesCount() {
            return ((BundledDocumentMetadata) this.instance).getQueriesCount();
        }

        public String getQueries(int index) {
            return ((BundledDocumentMetadata) this.instance).getQueries(index);
        }

        public ByteString getQueriesBytes(int index) {
            return ((BundledDocumentMetadata) this.instance).getQueriesBytes(index);
        }

        public Builder setQueries(int index, String value) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).setQueries(index, value);
            return this;
        }

        public Builder addQueries(String value) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).addQueries(value);
            return this;
        }

        public Builder addAllQueries(Iterable<String> values) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).addAllQueries(values);
            return this;
        }

        public Builder clearQueries() {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).clearQueries();
            return this;
        }

        public Builder addQueriesBytes(ByteString value) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).addQueriesBytes(value);
            return this;
        }
    }

    /* renamed from: com.google.firestore.bundle.BundledDocumentMetadata$1 */
    static /* synthetic */ class C08201 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f198xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f198xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f198xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f198xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f198xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f198xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f198xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f198xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C08201.f198xa1df5c61[method.ordinal()]) {
            case 1:
                return new BundledDocumentMetadata();
            case 2:
                return new Builder((C08201) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0001\u0000\u0001Ȉ\u0002\t\u0003\u0007\u0004Ț", new Object[]{"name_", "readTime_", "exists_", "queries_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<BundledDocumentMetadata> parser = PARSER;
                if (parser == null) {
                    synchronized (BundledDocumentMetadata.class) {
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
        BundledDocumentMetadata defaultInstance = new BundledDocumentMetadata();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(BundledDocumentMetadata.class, defaultInstance);
    }

    public static BundledDocumentMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BundledDocumentMetadata> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
