package com.google.api;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class Logging extends GeneratedMessageLite<Logging, Builder> implements LoggingOrBuilder {
    public static final int CONSUMER_DESTINATIONS_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final Logging DEFAULT_INSTANCE;
    private static volatile Parser<Logging> PARSER = null;
    public static final int PRODUCER_DESTINATIONS_FIELD_NUMBER = 1;
    private Internal.ProtobufList<LoggingDestination> consumerDestinations_ = emptyProtobufList();
    private Internal.ProtobufList<LoggingDestination> producerDestinations_ = emptyProtobufList();

    public interface LoggingDestinationOrBuilder extends MessageLiteOrBuilder {
        String getLogs(int i);

        ByteString getLogsBytes(int i);

        int getLogsCount();

        List<String> getLogsList();

        String getMonitoredResource();

        ByteString getMonitoredResourceBytes();
    }

    private Logging() {
    }

    public static final class LoggingDestination extends GeneratedMessageLite<LoggingDestination, Builder> implements LoggingDestinationOrBuilder {
        /* access modifiers changed from: private */
        public static final LoggingDestination DEFAULT_INSTANCE;
        public static final int LOGS_FIELD_NUMBER = 1;
        public static final int MONITORED_RESOURCE_FIELD_NUMBER = 3;
        private static volatile Parser<LoggingDestination> PARSER;
        private Internal.ProtobufList<String> logs_ = GeneratedMessageLite.emptyProtobufList();
        private String monitoredResource_ = "";

        private LoggingDestination() {
        }

        public String getMonitoredResource() {
            return this.monitoredResource_;
        }

        public ByteString getMonitoredResourceBytes() {
            return ByteString.copyFromUtf8(this.monitoredResource_);
        }

        /* access modifiers changed from: private */
        public void setMonitoredResource(String value) {
            value.getClass();
            this.monitoredResource_ = value;
        }

        /* access modifiers changed from: private */
        public void clearMonitoredResource() {
            this.monitoredResource_ = getDefaultInstance().getMonitoredResource();
        }

        /* access modifiers changed from: private */
        public void setMonitoredResourceBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.monitoredResource_ = value.toStringUtf8();
        }

        public List<String> getLogsList() {
            return this.logs_;
        }

        public int getLogsCount() {
            return this.logs_.size();
        }

        public String getLogs(int index) {
            return (String) this.logs_.get(index);
        }

        public ByteString getLogsBytes(int index) {
            return ByteString.copyFromUtf8((String) this.logs_.get(index));
        }

        private void ensureLogsIsMutable() {
            Internal.ProtobufList<String> tmp = this.logs_;
            if (!tmp.isModifiable()) {
                this.logs_ = GeneratedMessageLite.mutableCopy(tmp);
            }
        }

        /* access modifiers changed from: private */
        public void setLogs(int index, String value) {
            value.getClass();
            ensureLogsIsMutable();
            this.logs_.set(index, value);
        }

        /* access modifiers changed from: private */
        public void addLogs(String value) {
            value.getClass();
            ensureLogsIsMutable();
            this.logs_.add(value);
        }

        /* access modifiers changed from: private */
        public void addAllLogs(Iterable<String> values) {
            ensureLogsIsMutable();
            AbstractMessageLite.addAll(values, this.logs_);
        }

        /* access modifiers changed from: private */
        public void clearLogs() {
            this.logs_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void addLogsBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            ensureLogsIsMutable();
            this.logs_.add(value.toStringUtf8());
        }

        public static LoggingDestination parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static LoggingDestination parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static LoggingDestination parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static LoggingDestination parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static LoggingDestination parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static LoggingDestination parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static LoggingDestination parseFrom(InputStream input) throws IOException {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static LoggingDestination parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static LoggingDestination parseDelimitedFrom(InputStream input) throws IOException {
            return (LoggingDestination) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static LoggingDestination parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (LoggingDestination) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static LoggingDestination parseFrom(CodedInputStream input) throws IOException {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static LoggingDestination parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(LoggingDestination prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<LoggingDestination, Builder> implements LoggingDestinationOrBuilder {
            /* synthetic */ Builder(C00281 x0) {
                this();
            }

            private Builder() {
                super(LoggingDestination.DEFAULT_INSTANCE);
            }

            public String getMonitoredResource() {
                return ((LoggingDestination) this.instance).getMonitoredResource();
            }

            public ByteString getMonitoredResourceBytes() {
                return ((LoggingDestination) this.instance).getMonitoredResourceBytes();
            }

            public Builder setMonitoredResource(String value) {
                copyOnWrite();
                ((LoggingDestination) this.instance).setMonitoredResource(value);
                return this;
            }

            public Builder clearMonitoredResource() {
                copyOnWrite();
                ((LoggingDestination) this.instance).clearMonitoredResource();
                return this;
            }

            public Builder setMonitoredResourceBytes(ByteString value) {
                copyOnWrite();
                ((LoggingDestination) this.instance).setMonitoredResourceBytes(value);
                return this;
            }

            public List<String> getLogsList() {
                return Collections.unmodifiableList(((LoggingDestination) this.instance).getLogsList());
            }

            public int getLogsCount() {
                return ((LoggingDestination) this.instance).getLogsCount();
            }

            public String getLogs(int index) {
                return ((LoggingDestination) this.instance).getLogs(index);
            }

            public ByteString getLogsBytes(int index) {
                return ((LoggingDestination) this.instance).getLogsBytes(index);
            }

            public Builder setLogs(int index, String value) {
                copyOnWrite();
                ((LoggingDestination) this.instance).setLogs(index, value);
                return this;
            }

            public Builder addLogs(String value) {
                copyOnWrite();
                ((LoggingDestination) this.instance).addLogs(value);
                return this;
            }

            public Builder addAllLogs(Iterable<String> values) {
                copyOnWrite();
                ((LoggingDestination) this.instance).addAllLogs(values);
                return this;
            }

            public Builder clearLogs() {
                copyOnWrite();
                ((LoggingDestination) this.instance).clearLogs();
                return this;
            }

            public Builder addLogsBytes(ByteString value) {
                copyOnWrite();
                ((LoggingDestination) this.instance).addLogsBytes(value);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C00281.f24xa1df5c61[method.ordinal()]) {
                case 1:
                    return new LoggingDestination();
                case 2:
                    return new Builder((C00281) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0001\u0000\u0001Ț\u0003Ȉ", new Object[]{"logs_", "monitoredResource_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<LoggingDestination> parser = PARSER;
                    if (parser == null) {
                        synchronized (LoggingDestination.class) {
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
            LoggingDestination defaultInstance = new LoggingDestination();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(LoggingDestination.class, defaultInstance);
        }

        public static LoggingDestination getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<LoggingDestination> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.api.Logging$1 */
    static /* synthetic */ class C00281 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f24xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f24xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f24xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f24xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f24xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f24xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f24xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f24xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public List<LoggingDestination> getProducerDestinationsList() {
        return this.producerDestinations_;
    }

    public List<? extends LoggingDestinationOrBuilder> getProducerDestinationsOrBuilderList() {
        return this.producerDestinations_;
    }

    public int getProducerDestinationsCount() {
        return this.producerDestinations_.size();
    }

    public LoggingDestination getProducerDestinations(int index) {
        return (LoggingDestination) this.producerDestinations_.get(index);
    }

    public LoggingDestinationOrBuilder getProducerDestinationsOrBuilder(int index) {
        return (LoggingDestinationOrBuilder) this.producerDestinations_.get(index);
    }

    private void ensureProducerDestinationsIsMutable() {
        Internal.ProtobufList<LoggingDestination> tmp = this.producerDestinations_;
        if (!tmp.isModifiable()) {
            this.producerDestinations_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setProducerDestinations(int index, LoggingDestination value) {
        value.getClass();
        ensureProducerDestinationsIsMutable();
        this.producerDestinations_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addProducerDestinations(LoggingDestination value) {
        value.getClass();
        ensureProducerDestinationsIsMutable();
        this.producerDestinations_.add(value);
    }

    /* access modifiers changed from: private */
    public void addProducerDestinations(int index, LoggingDestination value) {
        value.getClass();
        ensureProducerDestinationsIsMutable();
        this.producerDestinations_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllProducerDestinations(Iterable<? extends LoggingDestination> values) {
        ensureProducerDestinationsIsMutable();
        AbstractMessageLite.addAll(values, this.producerDestinations_);
    }

    /* access modifiers changed from: private */
    public void clearProducerDestinations() {
        this.producerDestinations_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeProducerDestinations(int index) {
        ensureProducerDestinationsIsMutable();
        this.producerDestinations_.remove(index);
    }

    public List<LoggingDestination> getConsumerDestinationsList() {
        return this.consumerDestinations_;
    }

    public List<? extends LoggingDestinationOrBuilder> getConsumerDestinationsOrBuilderList() {
        return this.consumerDestinations_;
    }

    public int getConsumerDestinationsCount() {
        return this.consumerDestinations_.size();
    }

    public LoggingDestination getConsumerDestinations(int index) {
        return (LoggingDestination) this.consumerDestinations_.get(index);
    }

    public LoggingDestinationOrBuilder getConsumerDestinationsOrBuilder(int index) {
        return (LoggingDestinationOrBuilder) this.consumerDestinations_.get(index);
    }

    private void ensureConsumerDestinationsIsMutable() {
        Internal.ProtobufList<LoggingDestination> tmp = this.consumerDestinations_;
        if (!tmp.isModifiable()) {
            this.consumerDestinations_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setConsumerDestinations(int index, LoggingDestination value) {
        value.getClass();
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addConsumerDestinations(LoggingDestination value) {
        value.getClass();
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.add(value);
    }

    /* access modifiers changed from: private */
    public void addConsumerDestinations(int index, LoggingDestination value) {
        value.getClass();
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllConsumerDestinations(Iterable<? extends LoggingDestination> values) {
        ensureConsumerDestinationsIsMutable();
        AbstractMessageLite.addAll(values, this.consumerDestinations_);
    }

    /* access modifiers changed from: private */
    public void clearConsumerDestinations() {
        this.consumerDestinations_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeConsumerDestinations(int index) {
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.remove(index);
    }

    public static Logging parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Logging parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Logging parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Logging parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Logging parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Logging parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Logging parseFrom(InputStream input) throws IOException {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Logging parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Logging parseDelimitedFrom(InputStream input) throws IOException {
        return (Logging) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Logging parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Logging) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Logging parseFrom(CodedInputStream input) throws IOException {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Logging parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Logging prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Logging, Builder> implements LoggingOrBuilder {
        /* synthetic */ Builder(C00281 x0) {
            this();
        }

        private Builder() {
            super(Logging.DEFAULT_INSTANCE);
        }

        public List<LoggingDestination> getProducerDestinationsList() {
            return Collections.unmodifiableList(((Logging) this.instance).getProducerDestinationsList());
        }

        public int getProducerDestinationsCount() {
            return ((Logging) this.instance).getProducerDestinationsCount();
        }

        public LoggingDestination getProducerDestinations(int index) {
            return ((Logging) this.instance).getProducerDestinations(index);
        }

        public Builder setProducerDestinations(int index, LoggingDestination value) {
            copyOnWrite();
            ((Logging) this.instance).setProducerDestinations(index, value);
            return this;
        }

        public Builder setProducerDestinations(int index, LoggingDestination.Builder builderForValue) {
            copyOnWrite();
            ((Logging) this.instance).setProducerDestinations(index, (LoggingDestination) builderForValue.build());
            return this;
        }

        public Builder addProducerDestinations(LoggingDestination value) {
            copyOnWrite();
            ((Logging) this.instance).addProducerDestinations(value);
            return this;
        }

        public Builder addProducerDestinations(int index, LoggingDestination value) {
            copyOnWrite();
            ((Logging) this.instance).addProducerDestinations(index, value);
            return this;
        }

        public Builder addProducerDestinations(LoggingDestination.Builder builderForValue) {
            copyOnWrite();
            ((Logging) this.instance).addProducerDestinations((LoggingDestination) builderForValue.build());
            return this;
        }

        public Builder addProducerDestinations(int index, LoggingDestination.Builder builderForValue) {
            copyOnWrite();
            ((Logging) this.instance).addProducerDestinations(index, (LoggingDestination) builderForValue.build());
            return this;
        }

        public Builder addAllProducerDestinations(Iterable<? extends LoggingDestination> values) {
            copyOnWrite();
            ((Logging) this.instance).addAllProducerDestinations(values);
            return this;
        }

        public Builder clearProducerDestinations() {
            copyOnWrite();
            ((Logging) this.instance).clearProducerDestinations();
            return this;
        }

        public Builder removeProducerDestinations(int index) {
            copyOnWrite();
            ((Logging) this.instance).removeProducerDestinations(index);
            return this;
        }

        public List<LoggingDestination> getConsumerDestinationsList() {
            return Collections.unmodifiableList(((Logging) this.instance).getConsumerDestinationsList());
        }

        public int getConsumerDestinationsCount() {
            return ((Logging) this.instance).getConsumerDestinationsCount();
        }

        public LoggingDestination getConsumerDestinations(int index) {
            return ((Logging) this.instance).getConsumerDestinations(index);
        }

        public Builder setConsumerDestinations(int index, LoggingDestination value) {
            copyOnWrite();
            ((Logging) this.instance).setConsumerDestinations(index, value);
            return this;
        }

        public Builder setConsumerDestinations(int index, LoggingDestination.Builder builderForValue) {
            copyOnWrite();
            ((Logging) this.instance).setConsumerDestinations(index, (LoggingDestination) builderForValue.build());
            return this;
        }

        public Builder addConsumerDestinations(LoggingDestination value) {
            copyOnWrite();
            ((Logging) this.instance).addConsumerDestinations(value);
            return this;
        }

        public Builder addConsumerDestinations(int index, LoggingDestination value) {
            copyOnWrite();
            ((Logging) this.instance).addConsumerDestinations(index, value);
            return this;
        }

        public Builder addConsumerDestinations(LoggingDestination.Builder builderForValue) {
            copyOnWrite();
            ((Logging) this.instance).addConsumerDestinations((LoggingDestination) builderForValue.build());
            return this;
        }

        public Builder addConsumerDestinations(int index, LoggingDestination.Builder builderForValue) {
            copyOnWrite();
            ((Logging) this.instance).addConsumerDestinations(index, (LoggingDestination) builderForValue.build());
            return this;
        }

        public Builder addAllConsumerDestinations(Iterable<? extends LoggingDestination> values) {
            copyOnWrite();
            ((Logging) this.instance).addAllConsumerDestinations(values);
            return this;
        }

        public Builder clearConsumerDestinations() {
            copyOnWrite();
            ((Logging) this.instance).clearConsumerDestinations();
            return this;
        }

        public Builder removeConsumerDestinations(int index) {
            copyOnWrite();
            ((Logging) this.instance).removeConsumerDestinations(index);
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00281.f24xa1df5c61[method.ordinal()]) {
            case 1:
                return new Logging();
            case 2:
                return new Builder((C00281) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0001\u001b\u0002\u001b", new Object[]{"producerDestinations_", LoggingDestination.class, "consumerDestinations_", LoggingDestination.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Logging> parser = PARSER;
                if (parser == null) {
                    synchronized (Logging.class) {
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
        Logging defaultInstance = new Logging();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Logging.class, defaultInstance);
    }

    public static Logging getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Logging> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
