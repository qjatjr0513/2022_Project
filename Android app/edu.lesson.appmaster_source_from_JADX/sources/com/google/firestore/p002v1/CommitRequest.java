package com.google.firestore.p002v1;

import com.google.firestore.p002v1.Write;
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

/* renamed from: com.google.firestore.v1.CommitRequest */
public final class CommitRequest extends GeneratedMessageLite<CommitRequest, Builder> implements CommitRequestOrBuilder {
    public static final int DATABASE_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final CommitRequest DEFAULT_INSTANCE;
    private static volatile Parser<CommitRequest> PARSER = null;
    public static final int TRANSACTION_FIELD_NUMBER = 3;
    public static final int WRITES_FIELD_NUMBER = 2;
    private String database_ = "";
    private ByteString transaction_ = ByteString.EMPTY;
    private Internal.ProtobufList<Write> writes_ = emptyProtobufList();

    private CommitRequest() {
    }

    public String getDatabase() {
        return this.database_;
    }

    public ByteString getDatabaseBytes() {
        return ByteString.copyFromUtf8(this.database_);
    }

    /* access modifiers changed from: private */
    public void setDatabase(String value) {
        value.getClass();
        this.database_ = value;
    }

    /* access modifiers changed from: private */
    public void clearDatabase() {
        this.database_ = getDefaultInstance().getDatabase();
    }

    /* access modifiers changed from: private */
    public void setDatabaseBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.database_ = value.toStringUtf8();
    }

    public List<Write> getWritesList() {
        return this.writes_;
    }

    public List<? extends WriteOrBuilder> getWritesOrBuilderList() {
        return this.writes_;
    }

    public int getWritesCount() {
        return this.writes_.size();
    }

    public Write getWrites(int index) {
        return (Write) this.writes_.get(index);
    }

    public WriteOrBuilder getWritesOrBuilder(int index) {
        return (WriteOrBuilder) this.writes_.get(index);
    }

    private void ensureWritesIsMutable() {
        Internal.ProtobufList<Write> tmp = this.writes_;
        if (!tmp.isModifiable()) {
            this.writes_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setWrites(int index, Write value) {
        value.getClass();
        ensureWritesIsMutable();
        this.writes_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addWrites(Write value) {
        value.getClass();
        ensureWritesIsMutable();
        this.writes_.add(value);
    }

    /* access modifiers changed from: private */
    public void addWrites(int index, Write value) {
        value.getClass();
        ensureWritesIsMutable();
        this.writes_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllWrites(Iterable<? extends Write> values) {
        ensureWritesIsMutable();
        AbstractMessageLite.addAll(values, this.writes_);
    }

    /* access modifiers changed from: private */
    public void clearWrites() {
        this.writes_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeWrites(int index) {
        ensureWritesIsMutable();
        this.writes_.remove(index);
    }

    public ByteString getTransaction() {
        return this.transaction_;
    }

    /* access modifiers changed from: private */
    public void setTransaction(ByteString value) {
        value.getClass();
        this.transaction_ = value;
    }

    /* access modifiers changed from: private */
    public void clearTransaction() {
        this.transaction_ = getDefaultInstance().getTransaction();
    }

    public static CommitRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static CommitRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static CommitRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static CommitRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static CommitRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static CommitRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static CommitRequest parseFrom(InputStream input) throws IOException {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static CommitRequest parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static CommitRequest parseDelimitedFrom(InputStream input) throws IOException {
        return (CommitRequest) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static CommitRequest parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (CommitRequest) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static CommitRequest parseFrom(CodedInputStream input) throws IOException {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static CommitRequest parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(CommitRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /* renamed from: com.google.firestore.v1.CommitRequest$Builder */
    public static final class Builder extends GeneratedMessageLite.Builder<CommitRequest, Builder> implements CommitRequestOrBuilder {
        /* synthetic */ Builder(C08291 x0) {
            this();
        }

        private Builder() {
            super(CommitRequest.DEFAULT_INSTANCE);
        }

        public String getDatabase() {
            return ((CommitRequest) this.instance).getDatabase();
        }

        public ByteString getDatabaseBytes() {
            return ((CommitRequest) this.instance).getDatabaseBytes();
        }

        public Builder setDatabase(String value) {
            copyOnWrite();
            ((CommitRequest) this.instance).setDatabase(value);
            return this;
        }

        public Builder clearDatabase() {
            copyOnWrite();
            ((CommitRequest) this.instance).clearDatabase();
            return this;
        }

        public Builder setDatabaseBytes(ByteString value) {
            copyOnWrite();
            ((CommitRequest) this.instance).setDatabaseBytes(value);
            return this;
        }

        public List<Write> getWritesList() {
            return Collections.unmodifiableList(((CommitRequest) this.instance).getWritesList());
        }

        public int getWritesCount() {
            return ((CommitRequest) this.instance).getWritesCount();
        }

        public Write getWrites(int index) {
            return ((CommitRequest) this.instance).getWrites(index);
        }

        public Builder setWrites(int index, Write value) {
            copyOnWrite();
            ((CommitRequest) this.instance).setWrites(index, value);
            return this;
        }

        public Builder setWrites(int index, Write.Builder builderForValue) {
            copyOnWrite();
            ((CommitRequest) this.instance).setWrites(index, (Write) builderForValue.build());
            return this;
        }

        public Builder addWrites(Write value) {
            copyOnWrite();
            ((CommitRequest) this.instance).addWrites(value);
            return this;
        }

        public Builder addWrites(int index, Write value) {
            copyOnWrite();
            ((CommitRequest) this.instance).addWrites(index, value);
            return this;
        }

        public Builder addWrites(Write.Builder builderForValue) {
            copyOnWrite();
            ((CommitRequest) this.instance).addWrites((Write) builderForValue.build());
            return this;
        }

        public Builder addWrites(int index, Write.Builder builderForValue) {
            copyOnWrite();
            ((CommitRequest) this.instance).addWrites(index, (Write) builderForValue.build());
            return this;
        }

        public Builder addAllWrites(Iterable<? extends Write> values) {
            copyOnWrite();
            ((CommitRequest) this.instance).addAllWrites(values);
            return this;
        }

        public Builder clearWrites() {
            copyOnWrite();
            ((CommitRequest) this.instance).clearWrites();
            return this;
        }

        public Builder removeWrites(int index) {
            copyOnWrite();
            ((CommitRequest) this.instance).removeWrites(index);
            return this;
        }

        public ByteString getTransaction() {
            return ((CommitRequest) this.instance).getTransaction();
        }

        public Builder setTransaction(ByteString value) {
            copyOnWrite();
            ((CommitRequest) this.instance).setTransaction(value);
            return this;
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((CommitRequest) this.instance).clearTransaction();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.CommitRequest$1 */
    static /* synthetic */ class C08291 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f206xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f206xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f206xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f206xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f206xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f206xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f206xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f206xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C08291.f206xa1df5c61[method.ordinal()]) {
            case 1:
                return new CommitRequest();
            case 2:
                return new Builder((C08291) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001Ȉ\u0002\u001b\u0003\n", new Object[]{"database_", "writes_", Write.class, "transaction_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<CommitRequest> parser = PARSER;
                if (parser == null) {
                    synchronized (CommitRequest.class) {
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
        CommitRequest defaultInstance = new CommitRequest();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(CommitRequest.class, defaultInstance);
    }

    public static CommitRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CommitRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
