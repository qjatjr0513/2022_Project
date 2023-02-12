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

public final class Monitoring extends GeneratedMessageLite<Monitoring, Builder> implements MonitoringOrBuilder {
    public static final int CONSUMER_DESTINATIONS_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final Monitoring DEFAULT_INSTANCE;
    private static volatile Parser<Monitoring> PARSER = null;
    public static final int PRODUCER_DESTINATIONS_FIELD_NUMBER = 1;
    private Internal.ProtobufList<MonitoringDestination> consumerDestinations_ = emptyProtobufList();
    private Internal.ProtobufList<MonitoringDestination> producerDestinations_ = emptyProtobufList();

    public interface MonitoringDestinationOrBuilder extends MessageLiteOrBuilder {
        String getMetrics(int i);

        ByteString getMetricsBytes(int i);

        int getMetricsCount();

        List<String> getMetricsList();

        String getMonitoredResource();

        ByteString getMonitoredResourceBytes();
    }

    private Monitoring() {
    }

    public static final class MonitoringDestination extends GeneratedMessageLite<MonitoringDestination, Builder> implements MonitoringDestinationOrBuilder {
        /* access modifiers changed from: private */
        public static final MonitoringDestination DEFAULT_INSTANCE;
        public static final int METRICS_FIELD_NUMBER = 2;
        public static final int MONITORED_RESOURCE_FIELD_NUMBER = 1;
        private static volatile Parser<MonitoringDestination> PARSER;
        private Internal.ProtobufList<String> metrics_ = GeneratedMessageLite.emptyProtobufList();
        private String monitoredResource_ = "";

        private MonitoringDestination() {
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

        public List<String> getMetricsList() {
            return this.metrics_;
        }

        public int getMetricsCount() {
            return this.metrics_.size();
        }

        public String getMetrics(int index) {
            return (String) this.metrics_.get(index);
        }

        public ByteString getMetricsBytes(int index) {
            return ByteString.copyFromUtf8((String) this.metrics_.get(index));
        }

        private void ensureMetricsIsMutable() {
            Internal.ProtobufList<String> tmp = this.metrics_;
            if (!tmp.isModifiable()) {
                this.metrics_ = GeneratedMessageLite.mutableCopy(tmp);
            }
        }

        /* access modifiers changed from: private */
        public void setMetrics(int index, String value) {
            value.getClass();
            ensureMetricsIsMutable();
            this.metrics_.set(index, value);
        }

        /* access modifiers changed from: private */
        public void addMetrics(String value) {
            value.getClass();
            ensureMetricsIsMutable();
            this.metrics_.add(value);
        }

        /* access modifiers changed from: private */
        public void addAllMetrics(Iterable<String> values) {
            ensureMetricsIsMutable();
            AbstractMessageLite.addAll(values, this.metrics_);
        }

        /* access modifiers changed from: private */
        public void clearMetrics() {
            this.metrics_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void addMetricsBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            ensureMetricsIsMutable();
            this.metrics_.add(value.toStringUtf8());
        }

        public static MonitoringDestination parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (MonitoringDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MonitoringDestination parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MonitoringDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MonitoringDestination parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (MonitoringDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MonitoringDestination parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MonitoringDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MonitoringDestination parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (MonitoringDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MonitoringDestination parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MonitoringDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MonitoringDestination parseFrom(InputStream input) throws IOException {
            return (MonitoringDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static MonitoringDestination parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MonitoringDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static MonitoringDestination parseDelimitedFrom(InputStream input) throws IOException {
            return (MonitoringDestination) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static MonitoringDestination parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MonitoringDestination) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static MonitoringDestination parseFrom(CodedInputStream input) throws IOException {
            return (MonitoringDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static MonitoringDestination parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MonitoringDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(MonitoringDestination prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<MonitoringDestination, Builder> implements MonitoringDestinationOrBuilder {
            /* synthetic */ Builder(C00371 x0) {
                this();
            }

            private Builder() {
                super(MonitoringDestination.DEFAULT_INSTANCE);
            }

            public String getMonitoredResource() {
                return ((MonitoringDestination) this.instance).getMonitoredResource();
            }

            public ByteString getMonitoredResourceBytes() {
                return ((MonitoringDestination) this.instance).getMonitoredResourceBytes();
            }

            public Builder setMonitoredResource(String value) {
                copyOnWrite();
                ((MonitoringDestination) this.instance).setMonitoredResource(value);
                return this;
            }

            public Builder clearMonitoredResource() {
                copyOnWrite();
                ((MonitoringDestination) this.instance).clearMonitoredResource();
                return this;
            }

            public Builder setMonitoredResourceBytes(ByteString value) {
                copyOnWrite();
                ((MonitoringDestination) this.instance).setMonitoredResourceBytes(value);
                return this;
            }

            public List<String> getMetricsList() {
                return Collections.unmodifiableList(((MonitoringDestination) this.instance).getMetricsList());
            }

            public int getMetricsCount() {
                return ((MonitoringDestination) this.instance).getMetricsCount();
            }

            public String getMetrics(int index) {
                return ((MonitoringDestination) this.instance).getMetrics(index);
            }

            public ByteString getMetricsBytes(int index) {
                return ((MonitoringDestination) this.instance).getMetricsBytes(index);
            }

            public Builder setMetrics(int index, String value) {
                copyOnWrite();
                ((MonitoringDestination) this.instance).setMetrics(index, value);
                return this;
            }

            public Builder addMetrics(String value) {
                copyOnWrite();
                ((MonitoringDestination) this.instance).addMetrics(value);
                return this;
            }

            public Builder addAllMetrics(Iterable<String> values) {
                copyOnWrite();
                ((MonitoringDestination) this.instance).addAllMetrics(values);
                return this;
            }

            public Builder clearMetrics() {
                copyOnWrite();
                ((MonitoringDestination) this.instance).clearMetrics();
                return this;
            }

            public Builder addMetricsBytes(ByteString value) {
                copyOnWrite();
                ((MonitoringDestination) this.instance).addMetricsBytes(value);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C00371.f31xa1df5c61[method.ordinal()]) {
                case 1:
                    return new MonitoringDestination();
                case 2:
                    return new Builder((C00371) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002Ț", new Object[]{"monitoredResource_", "metrics_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<MonitoringDestination> parser = PARSER;
                    if (parser == null) {
                        synchronized (MonitoringDestination.class) {
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
            MonitoringDestination defaultInstance = new MonitoringDestination();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(MonitoringDestination.class, defaultInstance);
        }

        public static MonitoringDestination getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<MonitoringDestination> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.api.Monitoring$1 */
    static /* synthetic */ class C00371 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f31xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f31xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f31xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f31xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f31xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f31xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f31xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f31xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public List<MonitoringDestination> getProducerDestinationsList() {
        return this.producerDestinations_;
    }

    public List<? extends MonitoringDestinationOrBuilder> getProducerDestinationsOrBuilderList() {
        return this.producerDestinations_;
    }

    public int getProducerDestinationsCount() {
        return this.producerDestinations_.size();
    }

    public MonitoringDestination getProducerDestinations(int index) {
        return (MonitoringDestination) this.producerDestinations_.get(index);
    }

    public MonitoringDestinationOrBuilder getProducerDestinationsOrBuilder(int index) {
        return (MonitoringDestinationOrBuilder) this.producerDestinations_.get(index);
    }

    private void ensureProducerDestinationsIsMutable() {
        Internal.ProtobufList<MonitoringDestination> tmp = this.producerDestinations_;
        if (!tmp.isModifiable()) {
            this.producerDestinations_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setProducerDestinations(int index, MonitoringDestination value) {
        value.getClass();
        ensureProducerDestinationsIsMutable();
        this.producerDestinations_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addProducerDestinations(MonitoringDestination value) {
        value.getClass();
        ensureProducerDestinationsIsMutable();
        this.producerDestinations_.add(value);
    }

    /* access modifiers changed from: private */
    public void addProducerDestinations(int index, MonitoringDestination value) {
        value.getClass();
        ensureProducerDestinationsIsMutable();
        this.producerDestinations_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllProducerDestinations(Iterable<? extends MonitoringDestination> values) {
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

    public List<MonitoringDestination> getConsumerDestinationsList() {
        return this.consumerDestinations_;
    }

    public List<? extends MonitoringDestinationOrBuilder> getConsumerDestinationsOrBuilderList() {
        return this.consumerDestinations_;
    }

    public int getConsumerDestinationsCount() {
        return this.consumerDestinations_.size();
    }

    public MonitoringDestination getConsumerDestinations(int index) {
        return (MonitoringDestination) this.consumerDestinations_.get(index);
    }

    public MonitoringDestinationOrBuilder getConsumerDestinationsOrBuilder(int index) {
        return (MonitoringDestinationOrBuilder) this.consumerDestinations_.get(index);
    }

    private void ensureConsumerDestinationsIsMutable() {
        Internal.ProtobufList<MonitoringDestination> tmp = this.consumerDestinations_;
        if (!tmp.isModifiable()) {
            this.consumerDestinations_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setConsumerDestinations(int index, MonitoringDestination value) {
        value.getClass();
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addConsumerDestinations(MonitoringDestination value) {
        value.getClass();
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.add(value);
    }

    /* access modifiers changed from: private */
    public void addConsumerDestinations(int index, MonitoringDestination value) {
        value.getClass();
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllConsumerDestinations(Iterable<? extends MonitoringDestination> values) {
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

    public static Monitoring parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Monitoring) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Monitoring parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Monitoring) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Monitoring parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Monitoring) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Monitoring parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Monitoring) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Monitoring parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Monitoring) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Monitoring parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Monitoring) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Monitoring parseFrom(InputStream input) throws IOException {
        return (Monitoring) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Monitoring parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Monitoring) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Monitoring parseDelimitedFrom(InputStream input) throws IOException {
        return (Monitoring) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Monitoring parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Monitoring) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Monitoring parseFrom(CodedInputStream input) throws IOException {
        return (Monitoring) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Monitoring parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Monitoring) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Monitoring prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Monitoring, Builder> implements MonitoringOrBuilder {
        /* synthetic */ Builder(C00371 x0) {
            this();
        }

        private Builder() {
            super(Monitoring.DEFAULT_INSTANCE);
        }

        public List<MonitoringDestination> getProducerDestinationsList() {
            return Collections.unmodifiableList(((Monitoring) this.instance).getProducerDestinationsList());
        }

        public int getProducerDestinationsCount() {
            return ((Monitoring) this.instance).getProducerDestinationsCount();
        }

        public MonitoringDestination getProducerDestinations(int index) {
            return ((Monitoring) this.instance).getProducerDestinations(index);
        }

        public Builder setProducerDestinations(int index, MonitoringDestination value) {
            copyOnWrite();
            ((Monitoring) this.instance).setProducerDestinations(index, value);
            return this;
        }

        public Builder setProducerDestinations(int index, MonitoringDestination.Builder builderForValue) {
            copyOnWrite();
            ((Monitoring) this.instance).setProducerDestinations(index, (MonitoringDestination) builderForValue.build());
            return this;
        }

        public Builder addProducerDestinations(MonitoringDestination value) {
            copyOnWrite();
            ((Monitoring) this.instance).addProducerDestinations(value);
            return this;
        }

        public Builder addProducerDestinations(int index, MonitoringDestination value) {
            copyOnWrite();
            ((Monitoring) this.instance).addProducerDestinations(index, value);
            return this;
        }

        public Builder addProducerDestinations(MonitoringDestination.Builder builderForValue) {
            copyOnWrite();
            ((Monitoring) this.instance).addProducerDestinations((MonitoringDestination) builderForValue.build());
            return this;
        }

        public Builder addProducerDestinations(int index, MonitoringDestination.Builder builderForValue) {
            copyOnWrite();
            ((Monitoring) this.instance).addProducerDestinations(index, (MonitoringDestination) builderForValue.build());
            return this;
        }

        public Builder addAllProducerDestinations(Iterable<? extends MonitoringDestination> values) {
            copyOnWrite();
            ((Monitoring) this.instance).addAllProducerDestinations(values);
            return this;
        }

        public Builder clearProducerDestinations() {
            copyOnWrite();
            ((Monitoring) this.instance).clearProducerDestinations();
            return this;
        }

        public Builder removeProducerDestinations(int index) {
            copyOnWrite();
            ((Monitoring) this.instance).removeProducerDestinations(index);
            return this;
        }

        public List<MonitoringDestination> getConsumerDestinationsList() {
            return Collections.unmodifiableList(((Monitoring) this.instance).getConsumerDestinationsList());
        }

        public int getConsumerDestinationsCount() {
            return ((Monitoring) this.instance).getConsumerDestinationsCount();
        }

        public MonitoringDestination getConsumerDestinations(int index) {
            return ((Monitoring) this.instance).getConsumerDestinations(index);
        }

        public Builder setConsumerDestinations(int index, MonitoringDestination value) {
            copyOnWrite();
            ((Monitoring) this.instance).setConsumerDestinations(index, value);
            return this;
        }

        public Builder setConsumerDestinations(int index, MonitoringDestination.Builder builderForValue) {
            copyOnWrite();
            ((Monitoring) this.instance).setConsumerDestinations(index, (MonitoringDestination) builderForValue.build());
            return this;
        }

        public Builder addConsumerDestinations(MonitoringDestination value) {
            copyOnWrite();
            ((Monitoring) this.instance).addConsumerDestinations(value);
            return this;
        }

        public Builder addConsumerDestinations(int index, MonitoringDestination value) {
            copyOnWrite();
            ((Monitoring) this.instance).addConsumerDestinations(index, value);
            return this;
        }

        public Builder addConsumerDestinations(MonitoringDestination.Builder builderForValue) {
            copyOnWrite();
            ((Monitoring) this.instance).addConsumerDestinations((MonitoringDestination) builderForValue.build());
            return this;
        }

        public Builder addConsumerDestinations(int index, MonitoringDestination.Builder builderForValue) {
            copyOnWrite();
            ((Monitoring) this.instance).addConsumerDestinations(index, (MonitoringDestination) builderForValue.build());
            return this;
        }

        public Builder addAllConsumerDestinations(Iterable<? extends MonitoringDestination> values) {
            copyOnWrite();
            ((Monitoring) this.instance).addAllConsumerDestinations(values);
            return this;
        }

        public Builder clearConsumerDestinations() {
            copyOnWrite();
            ((Monitoring) this.instance).clearConsumerDestinations();
            return this;
        }

        public Builder removeConsumerDestinations(int index) {
            copyOnWrite();
            ((Monitoring) this.instance).removeConsumerDestinations(index);
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00371.f31xa1df5c61[method.ordinal()]) {
            case 1:
                return new Monitoring();
            case 2:
                return new Builder((C00371) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0001\u001b\u0002\u001b", new Object[]{"producerDestinations_", MonitoringDestination.class, "consumerDestinations_", MonitoringDestination.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Monitoring> parser = PARSER;
                if (parser == null) {
                    synchronized (Monitoring.class) {
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
        Monitoring defaultInstance = new Monitoring();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Monitoring.class, defaultInstance);
    }

    public static Monitoring getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Monitoring> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
