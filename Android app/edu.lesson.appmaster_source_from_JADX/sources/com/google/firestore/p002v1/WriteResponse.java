package com.google.firestore.p002v1;

import com.google.firestore.p002v1.WriteResult;
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

/* renamed from: com.google.firestore.v1.WriteResponse */
public final class WriteResponse extends GeneratedMessageLite<WriteResponse, Builder> implements WriteResponseOrBuilder {
    public static final int COMMIT_TIME_FIELD_NUMBER = 4;
    /* access modifiers changed from: private */
    public static final WriteResponse DEFAULT_INSTANCE;
    private static volatile Parser<WriteResponse> PARSER = null;
    public static final int STREAM_ID_FIELD_NUMBER = 1;
    public static final int STREAM_TOKEN_FIELD_NUMBER = 2;
    public static final int WRITE_RESULTS_FIELD_NUMBER = 3;
    private Timestamp commitTime_;
    private String streamId_ = "";
    private ByteString streamToken_ = ByteString.EMPTY;
    private Internal.ProtobufList<WriteResult> writeResults_ = emptyProtobufList();

    private WriteResponse() {
    }

    public String getStreamId() {
        return this.streamId_;
    }

    public ByteString getStreamIdBytes() {
        return ByteString.copyFromUtf8(this.streamId_);
    }

    /* access modifiers changed from: private */
    public void setStreamId(String value) {
        value.getClass();
        this.streamId_ = value;
    }

    /* access modifiers changed from: private */
    public void clearStreamId() {
        this.streamId_ = getDefaultInstance().getStreamId();
    }

    /* access modifiers changed from: private */
    public void setStreamIdBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.streamId_ = value.toStringUtf8();
    }

    public ByteString getStreamToken() {
        return this.streamToken_;
    }

    /* access modifiers changed from: private */
    public void setStreamToken(ByteString value) {
        value.getClass();
        this.streamToken_ = value;
    }

    /* access modifiers changed from: private */
    public void clearStreamToken() {
        this.streamToken_ = getDefaultInstance().getStreamToken();
    }

    public List<WriteResult> getWriteResultsList() {
        return this.writeResults_;
    }

    public List<? extends WriteResultOrBuilder> getWriteResultsOrBuilderList() {
        return this.writeResults_;
    }

    public int getWriteResultsCount() {
        return this.writeResults_.size();
    }

    public WriteResult getWriteResults(int index) {
        return (WriteResult) this.writeResults_.get(index);
    }

    public WriteResultOrBuilder getWriteResultsOrBuilder(int index) {
        return (WriteResultOrBuilder) this.writeResults_.get(index);
    }

    private void ensureWriteResultsIsMutable() {
        Internal.ProtobufList<WriteResult> tmp = this.writeResults_;
        if (!tmp.isModifiable()) {
            this.writeResults_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setWriteResults(int index, WriteResult value) {
        value.getClass();
        ensureWriteResultsIsMutable();
        this.writeResults_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addWriteResults(WriteResult value) {
        value.getClass();
        ensureWriteResultsIsMutable();
        this.writeResults_.add(value);
    }

    /* access modifiers changed from: private */
    public void addWriteResults(int index, WriteResult value) {
        value.getClass();
        ensureWriteResultsIsMutable();
        this.writeResults_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllWriteResults(Iterable<? extends WriteResult> values) {
        ensureWriteResultsIsMutable();
        AbstractMessageLite.addAll(values, this.writeResults_);
    }

    /* access modifiers changed from: private */
    public void clearWriteResults() {
        this.writeResults_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeWriteResults(int index) {
        ensureWriteResultsIsMutable();
        this.writeResults_.remove(index);
    }

    public boolean hasCommitTime() {
        return this.commitTime_ != null;
    }

    public Timestamp getCommitTime() {
        Timestamp timestamp = this.commitTime_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    /* access modifiers changed from: private */
    public void setCommitTime(Timestamp value) {
        value.getClass();
        this.commitTime_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeCommitTime(Timestamp value) {
        value.getClass();
        Timestamp timestamp = this.commitTime_;
        if (timestamp == null || timestamp == Timestamp.getDefaultInstance()) {
            this.commitTime_ = value;
        } else {
            this.commitTime_ = (Timestamp) ((Timestamp.Builder) Timestamp.newBuilder(this.commitTime_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearCommitTime() {
        this.commitTime_ = null;
    }

    public static WriteResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WriteResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WriteResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WriteResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WriteResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WriteResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WriteResponse parseFrom(InputStream input) throws IOException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WriteResponse parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WriteResponse parseDelimitedFrom(InputStream input) throws IOException {
        return (WriteResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WriteResponse parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WriteResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WriteResponse parseFrom(CodedInputStream input) throws IOException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WriteResponse parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(WriteResponse prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /* renamed from: com.google.firestore.v1.WriteResponse$Builder */
    public static final class Builder extends GeneratedMessageLite.Builder<WriteResponse, Builder> implements WriteResponseOrBuilder {
        /* synthetic */ Builder(C08701 x0) {
            this();
        }

        private Builder() {
            super(WriteResponse.DEFAULT_INSTANCE);
        }

        public String getStreamId() {
            return ((WriteResponse) this.instance).getStreamId();
        }

        public ByteString getStreamIdBytes() {
            return ((WriteResponse) this.instance).getStreamIdBytes();
        }

        public Builder setStreamId(String value) {
            copyOnWrite();
            ((WriteResponse) this.instance).setStreamId(value);
            return this;
        }

        public Builder clearStreamId() {
            copyOnWrite();
            ((WriteResponse) this.instance).clearStreamId();
            return this;
        }

        public Builder setStreamIdBytes(ByteString value) {
            copyOnWrite();
            ((WriteResponse) this.instance).setStreamIdBytes(value);
            return this;
        }

        public ByteString getStreamToken() {
            return ((WriteResponse) this.instance).getStreamToken();
        }

        public Builder setStreamToken(ByteString value) {
            copyOnWrite();
            ((WriteResponse) this.instance).setStreamToken(value);
            return this;
        }

        public Builder clearStreamToken() {
            copyOnWrite();
            ((WriteResponse) this.instance).clearStreamToken();
            return this;
        }

        public List<WriteResult> getWriteResultsList() {
            return Collections.unmodifiableList(((WriteResponse) this.instance).getWriteResultsList());
        }

        public int getWriteResultsCount() {
            return ((WriteResponse) this.instance).getWriteResultsCount();
        }

        public WriteResult getWriteResults(int index) {
            return ((WriteResponse) this.instance).getWriteResults(index);
        }

        public Builder setWriteResults(int index, WriteResult value) {
            copyOnWrite();
            ((WriteResponse) this.instance).setWriteResults(index, value);
            return this;
        }

        public Builder setWriteResults(int index, WriteResult.Builder builderForValue) {
            copyOnWrite();
            ((WriteResponse) this.instance).setWriteResults(index, (WriteResult) builderForValue.build());
            return this;
        }

        public Builder addWriteResults(WriteResult value) {
            copyOnWrite();
            ((WriteResponse) this.instance).addWriteResults(value);
            return this;
        }

        public Builder addWriteResults(int index, WriteResult value) {
            copyOnWrite();
            ((WriteResponse) this.instance).addWriteResults(index, value);
            return this;
        }

        public Builder addWriteResults(WriteResult.Builder builderForValue) {
            copyOnWrite();
            ((WriteResponse) this.instance).addWriteResults((WriteResult) builderForValue.build());
            return this;
        }

        public Builder addWriteResults(int index, WriteResult.Builder builderForValue) {
            copyOnWrite();
            ((WriteResponse) this.instance).addWriteResults(index, (WriteResult) builderForValue.build());
            return this;
        }

        public Builder addAllWriteResults(Iterable<? extends WriteResult> values) {
            copyOnWrite();
            ((WriteResponse) this.instance).addAllWriteResults(values);
            return this;
        }

        public Builder clearWriteResults() {
            copyOnWrite();
            ((WriteResponse) this.instance).clearWriteResults();
            return this;
        }

        public Builder removeWriteResults(int index) {
            copyOnWrite();
            ((WriteResponse) this.instance).removeWriteResults(index);
            return this;
        }

        public boolean hasCommitTime() {
            return ((WriteResponse) this.instance).hasCommitTime();
        }

        public Timestamp getCommitTime() {
            return ((WriteResponse) this.instance).getCommitTime();
        }

        public Builder setCommitTime(Timestamp value) {
            copyOnWrite();
            ((WriteResponse) this.instance).setCommitTime(value);
            return this;
        }

        public Builder setCommitTime(Timestamp.Builder builderForValue) {
            copyOnWrite();
            ((WriteResponse) this.instance).setCommitTime((Timestamp) builderForValue.build());
            return this;
        }

        public Builder mergeCommitTime(Timestamp value) {
            copyOnWrite();
            ((WriteResponse) this.instance).mergeCommitTime(value);
            return this;
        }

        public Builder clearCommitTime() {
            copyOnWrite();
            ((WriteResponse) this.instance).clearCommitTime();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.WriteResponse$1 */
    static /* synthetic */ class C08701 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f239xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f239xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f239xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f239xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f239xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f239xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f239xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f239xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C08701.f239xa1df5c61[method.ordinal()]) {
            case 1:
                return new WriteResponse();
            case 2:
                return new Builder((C08701) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0001\u0000\u0001Ȉ\u0002\n\u0003\u001b\u0004\t", new Object[]{"streamId_", "streamToken_", "writeResults_", WriteResult.class, "commitTime_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<WriteResponse> parser = PARSER;
                if (parser == null) {
                    synchronized (WriteResponse.class) {
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
        WriteResponse defaultInstance = new WriteResponse();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(WriteResponse.class, defaultInstance);
    }

    public static WriteResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WriteResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
