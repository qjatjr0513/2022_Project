package com.google.firestore.p002v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* renamed from: com.google.firestore.v1.TransactionOptions */
public final class TransactionOptions extends GeneratedMessageLite<TransactionOptions, Builder> implements TransactionOptionsOrBuilder {
    /* access modifiers changed from: private */
    public static final TransactionOptions DEFAULT_INSTANCE;
    private static volatile Parser<TransactionOptions> PARSER = null;
    public static final int READ_ONLY_FIELD_NUMBER = 2;
    public static final int READ_WRITE_FIELD_NUMBER = 3;
    private int modeCase_ = 0;
    private Object mode_;

    /* renamed from: com.google.firestore.v1.TransactionOptions$ReadOnlyOrBuilder */
    public interface ReadOnlyOrBuilder extends MessageLiteOrBuilder {
        ReadOnly.ConsistencySelectorCase getConsistencySelectorCase();

        Timestamp getReadTime();

        boolean hasReadTime();
    }

    /* renamed from: com.google.firestore.v1.TransactionOptions$ReadWriteOrBuilder */
    public interface ReadWriteOrBuilder extends MessageLiteOrBuilder {
        ByteString getRetryTransaction();
    }

    private TransactionOptions() {
    }

    /* renamed from: com.google.firestore.v1.TransactionOptions$ReadWrite */
    public static final class ReadWrite extends GeneratedMessageLite<ReadWrite, Builder> implements ReadWriteOrBuilder {
        /* access modifiers changed from: private */
        public static final ReadWrite DEFAULT_INSTANCE;
        private static volatile Parser<ReadWrite> PARSER = null;
        public static final int RETRY_TRANSACTION_FIELD_NUMBER = 1;
        private ByteString retryTransaction_ = ByteString.EMPTY;

        private ReadWrite() {
        }

        public ByteString getRetryTransaction() {
            return this.retryTransaction_;
        }

        /* access modifiers changed from: private */
        public void setRetryTransaction(ByteString value) {
            value.getClass();
            this.retryTransaction_ = value;
        }

        /* access modifiers changed from: private */
        public void clearRetryTransaction() {
            this.retryTransaction_ = getDefaultInstance().getRetryTransaction();
        }

        public static ReadWrite parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ReadWrite parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ReadWrite parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ReadWrite parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ReadWrite parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ReadWrite parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ReadWrite parseFrom(InputStream input) throws IOException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ReadWrite parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ReadWrite parseDelimitedFrom(InputStream input) throws IOException {
            return (ReadWrite) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ReadWrite parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ReadWrite) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ReadWrite parseFrom(CodedInputStream input) throws IOException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ReadWrite parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(ReadWrite prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /* renamed from: com.google.firestore.v1.TransactionOptions$ReadWrite$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<ReadWrite, Builder> implements ReadWriteOrBuilder {
            /* synthetic */ Builder(C08651 x0) {
                this();
            }

            private Builder() {
                super(ReadWrite.DEFAULT_INSTANCE);
            }

            public ByteString getRetryTransaction() {
                return ((ReadWrite) this.instance).getRetryTransaction();
            }

            public Builder setRetryTransaction(ByteString value) {
                copyOnWrite();
                ((ReadWrite) this.instance).setRetryTransaction(value);
                return this;
            }

            public Builder clearRetryTransaction() {
                copyOnWrite();
                ((ReadWrite) this.instance).clearRetryTransaction();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C08651.f234xa1df5c61[method.ordinal()]) {
                case 1:
                    return new ReadWrite();
                case 2:
                    return new Builder((C08651) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\n", new Object[]{"retryTransaction_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<ReadWrite> parser = PARSER;
                    if (parser == null) {
                        synchronized (ReadWrite.class) {
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
            ReadWrite defaultInstance = new ReadWrite();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(ReadWrite.class, defaultInstance);
        }

        public static ReadWrite getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ReadWrite> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.TransactionOptions$1 */
    static /* synthetic */ class C08651 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f234xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f234xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f234xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f234xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f234xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f234xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f234xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f234xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* renamed from: com.google.firestore.v1.TransactionOptions$ReadOnly */
    public static final class ReadOnly extends GeneratedMessageLite<ReadOnly, Builder> implements ReadOnlyOrBuilder {
        /* access modifiers changed from: private */
        public static final ReadOnly DEFAULT_INSTANCE;
        private static volatile Parser<ReadOnly> PARSER = null;
        public static final int READ_TIME_FIELD_NUMBER = 2;
        private int consistencySelectorCase_ = 0;
        private Object consistencySelector_;

        private ReadOnly() {
        }

        /* renamed from: com.google.firestore.v1.TransactionOptions$ReadOnly$ConsistencySelectorCase */
        public enum ConsistencySelectorCase {
            READ_TIME(2),
            CONSISTENCYSELECTOR_NOT_SET(0);
            
            private final int value;

            private ConsistencySelectorCase(int value2) {
                this.value = value2;
            }

            @Deprecated
            public static ConsistencySelectorCase valueOf(int value2) {
                return forNumber(value2);
            }

            public static ConsistencySelectorCase forNumber(int value2) {
                switch (value2) {
                    case 0:
                        return CONSISTENCYSELECTOR_NOT_SET;
                    case 2:
                        return READ_TIME;
                    default:
                        return null;
                }
            }

            public int getNumber() {
                return this.value;
            }
        }

        public ConsistencySelectorCase getConsistencySelectorCase() {
            return ConsistencySelectorCase.forNumber(this.consistencySelectorCase_);
        }

        /* access modifiers changed from: private */
        public void clearConsistencySelector() {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }

        public boolean hasReadTime() {
            return this.consistencySelectorCase_ == 2;
        }

        public Timestamp getReadTime() {
            if (this.consistencySelectorCase_ == 2) {
                return (Timestamp) this.consistencySelector_;
            }
            return Timestamp.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setReadTime(Timestamp value) {
            value.getClass();
            this.consistencySelector_ = value;
            this.consistencySelectorCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void mergeReadTime(Timestamp value) {
            value.getClass();
            if (this.consistencySelectorCase_ != 2 || this.consistencySelector_ == Timestamp.getDefaultInstance()) {
                this.consistencySelector_ = value;
            } else {
                this.consistencySelector_ = ((Timestamp.Builder) Timestamp.newBuilder((Timestamp) this.consistencySelector_).mergeFrom(value)).buildPartial();
            }
            this.consistencySelectorCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void clearReadTime() {
            if (this.consistencySelectorCase_ == 2) {
                this.consistencySelectorCase_ = 0;
                this.consistencySelector_ = null;
            }
        }

        public static ReadOnly parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ReadOnly parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ReadOnly parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ReadOnly parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ReadOnly parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ReadOnly parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ReadOnly parseFrom(InputStream input) throws IOException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ReadOnly parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ReadOnly parseDelimitedFrom(InputStream input) throws IOException {
            return (ReadOnly) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ReadOnly parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ReadOnly) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ReadOnly parseFrom(CodedInputStream input) throws IOException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ReadOnly parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(ReadOnly prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /* renamed from: com.google.firestore.v1.TransactionOptions$ReadOnly$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<ReadOnly, Builder> implements ReadOnlyOrBuilder {
            /* synthetic */ Builder(C08651 x0) {
                this();
            }

            private Builder() {
                super(ReadOnly.DEFAULT_INSTANCE);
            }

            public ConsistencySelectorCase getConsistencySelectorCase() {
                return ((ReadOnly) this.instance).getConsistencySelectorCase();
            }

            public Builder clearConsistencySelector() {
                copyOnWrite();
                ((ReadOnly) this.instance).clearConsistencySelector();
                return this;
            }

            public boolean hasReadTime() {
                return ((ReadOnly) this.instance).hasReadTime();
            }

            public Timestamp getReadTime() {
                return ((ReadOnly) this.instance).getReadTime();
            }

            public Builder setReadTime(Timestamp value) {
                copyOnWrite();
                ((ReadOnly) this.instance).setReadTime(value);
                return this;
            }

            public Builder setReadTime(Timestamp.Builder builderForValue) {
                copyOnWrite();
                ((ReadOnly) this.instance).setReadTime((Timestamp) builderForValue.build());
                return this;
            }

            public Builder mergeReadTime(Timestamp value) {
                copyOnWrite();
                ((ReadOnly) this.instance).mergeReadTime(value);
                return this;
            }

            public Builder clearReadTime() {
                copyOnWrite();
                ((ReadOnly) this.instance).clearReadTime();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C08651.f234xa1df5c61[method.ordinal()]) {
                case 1:
                    return new ReadOnly();
                case 2:
                    return new Builder((C08651) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0001\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002<\u0000", new Object[]{"consistencySelector_", "consistencySelectorCase_", Timestamp.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<ReadOnly> parser = PARSER;
                    if (parser == null) {
                        synchronized (ReadOnly.class) {
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
            ReadOnly defaultInstance = new ReadOnly();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(ReadOnly.class, defaultInstance);
        }

        public static ReadOnly getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ReadOnly> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.TransactionOptions$ModeCase */
    public enum ModeCase {
        READ_ONLY(2),
        READ_WRITE(3),
        MODE_NOT_SET(0);
        
        private final int value;

        private ModeCase(int value2) {
            this.value = value2;
        }

        @Deprecated
        public static ModeCase valueOf(int value2) {
            return forNumber(value2);
        }

        public static ModeCase forNumber(int value2) {
            switch (value2) {
                case 0:
                    return MODE_NOT_SET;
                case 2:
                    return READ_ONLY;
                case 3:
                    return READ_WRITE;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    public ModeCase getModeCase() {
        return ModeCase.forNumber(this.modeCase_);
    }

    /* access modifiers changed from: private */
    public void clearMode() {
        this.modeCase_ = 0;
        this.mode_ = null;
    }

    public boolean hasReadOnly() {
        return this.modeCase_ == 2;
    }

    public ReadOnly getReadOnly() {
        if (this.modeCase_ == 2) {
            return (ReadOnly) this.mode_;
        }
        return ReadOnly.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setReadOnly(ReadOnly value) {
        value.getClass();
        this.mode_ = value;
        this.modeCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void mergeReadOnly(ReadOnly value) {
        value.getClass();
        if (this.modeCase_ != 2 || this.mode_ == ReadOnly.getDefaultInstance()) {
            this.mode_ = value;
        } else {
            this.mode_ = ((ReadOnly.Builder) ReadOnly.newBuilder((ReadOnly) this.mode_).mergeFrom(value)).buildPartial();
        }
        this.modeCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void clearReadOnly() {
        if (this.modeCase_ == 2) {
            this.modeCase_ = 0;
            this.mode_ = null;
        }
    }

    public boolean hasReadWrite() {
        return this.modeCase_ == 3;
    }

    public ReadWrite getReadWrite() {
        if (this.modeCase_ == 3) {
            return (ReadWrite) this.mode_;
        }
        return ReadWrite.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setReadWrite(ReadWrite value) {
        value.getClass();
        this.mode_ = value;
        this.modeCase_ = 3;
    }

    /* access modifiers changed from: private */
    public void mergeReadWrite(ReadWrite value) {
        value.getClass();
        if (this.modeCase_ != 3 || this.mode_ == ReadWrite.getDefaultInstance()) {
            this.mode_ = value;
        } else {
            this.mode_ = ((ReadWrite.Builder) ReadWrite.newBuilder((ReadWrite) this.mode_).mergeFrom(value)).buildPartial();
        }
        this.modeCase_ = 3;
    }

    /* access modifiers changed from: private */
    public void clearReadWrite() {
        if (this.modeCase_ == 3) {
            this.modeCase_ = 0;
            this.mode_ = null;
        }
    }

    public static TransactionOptions parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static TransactionOptions parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static TransactionOptions parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static TransactionOptions parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static TransactionOptions parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static TransactionOptions parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static TransactionOptions parseFrom(InputStream input) throws IOException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static TransactionOptions parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static TransactionOptions parseDelimitedFrom(InputStream input) throws IOException {
        return (TransactionOptions) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static TransactionOptions parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (TransactionOptions) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static TransactionOptions parseFrom(CodedInputStream input) throws IOException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static TransactionOptions parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(TransactionOptions prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /* renamed from: com.google.firestore.v1.TransactionOptions$Builder */
    public static final class Builder extends GeneratedMessageLite.Builder<TransactionOptions, Builder> implements TransactionOptionsOrBuilder {
        /* synthetic */ Builder(C08651 x0) {
            this();
        }

        private Builder() {
            super(TransactionOptions.DEFAULT_INSTANCE);
        }

        public ModeCase getModeCase() {
            return ((TransactionOptions) this.instance).getModeCase();
        }

        public Builder clearMode() {
            copyOnWrite();
            ((TransactionOptions) this.instance).clearMode();
            return this;
        }

        public boolean hasReadOnly() {
            return ((TransactionOptions) this.instance).hasReadOnly();
        }

        public ReadOnly getReadOnly() {
            return ((TransactionOptions) this.instance).getReadOnly();
        }

        public Builder setReadOnly(ReadOnly value) {
            copyOnWrite();
            ((TransactionOptions) this.instance).setReadOnly(value);
            return this;
        }

        public Builder setReadOnly(ReadOnly.Builder builderForValue) {
            copyOnWrite();
            ((TransactionOptions) this.instance).setReadOnly((ReadOnly) builderForValue.build());
            return this;
        }

        public Builder mergeReadOnly(ReadOnly value) {
            copyOnWrite();
            ((TransactionOptions) this.instance).mergeReadOnly(value);
            return this;
        }

        public Builder clearReadOnly() {
            copyOnWrite();
            ((TransactionOptions) this.instance).clearReadOnly();
            return this;
        }

        public boolean hasReadWrite() {
            return ((TransactionOptions) this.instance).hasReadWrite();
        }

        public ReadWrite getReadWrite() {
            return ((TransactionOptions) this.instance).getReadWrite();
        }

        public Builder setReadWrite(ReadWrite value) {
            copyOnWrite();
            ((TransactionOptions) this.instance).setReadWrite(value);
            return this;
        }

        public Builder setReadWrite(ReadWrite.Builder builderForValue) {
            copyOnWrite();
            ((TransactionOptions) this.instance).setReadWrite((ReadWrite) builderForValue.build());
            return this;
        }

        public Builder mergeReadWrite(ReadWrite value) {
            copyOnWrite();
            ((TransactionOptions) this.instance).mergeReadWrite(value);
            return this;
        }

        public Builder clearReadWrite() {
            copyOnWrite();
            ((TransactionOptions) this.instance).clearReadWrite();
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C08651.f234xa1df5c61[method.ordinal()]) {
            case 1:
                return new TransactionOptions();
            case 2:
                return new Builder((C08651) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002<\u0000\u0003<\u0000", new Object[]{"mode_", "modeCase_", ReadOnly.class, ReadWrite.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<TransactionOptions> parser = PARSER;
                if (parser == null) {
                    synchronized (TransactionOptions.class) {
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
        TransactionOptions defaultInstance = new TransactionOptions();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(TransactionOptions.class, defaultInstance);
    }

    public static TransactionOptions getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TransactionOptions> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
