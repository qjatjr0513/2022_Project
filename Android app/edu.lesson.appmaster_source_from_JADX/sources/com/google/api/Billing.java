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

public final class Billing extends GeneratedMessageLite<Billing, Builder> implements BillingOrBuilder {
    public static final int CONSUMER_DESTINATIONS_FIELD_NUMBER = 8;
    /* access modifiers changed from: private */
    public static final Billing DEFAULT_INSTANCE;
    private static volatile Parser<Billing> PARSER;
    private Internal.ProtobufList<BillingDestination> consumerDestinations_ = emptyProtobufList();

    public interface BillingDestinationOrBuilder extends MessageLiteOrBuilder {
        String getMetrics(int i);

        ByteString getMetricsBytes(int i);

        int getMetricsCount();

        List<String> getMetricsList();

        String getMonitoredResource();

        ByteString getMonitoredResourceBytes();
    }

    private Billing() {
    }

    public static final class BillingDestination extends GeneratedMessageLite<BillingDestination, Builder> implements BillingDestinationOrBuilder {
        /* access modifiers changed from: private */
        public static final BillingDestination DEFAULT_INSTANCE;
        public static final int METRICS_FIELD_NUMBER = 2;
        public static final int MONITORED_RESOURCE_FIELD_NUMBER = 1;
        private static volatile Parser<BillingDestination> PARSER;
        private Internal.ProtobufList<String> metrics_ = GeneratedMessageLite.emptyProtobufList();
        private String monitoredResource_ = "";

        private BillingDestination() {
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

        public static BillingDestination parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (BillingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BillingDestination parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BillingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BillingDestination parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (BillingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BillingDestination parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BillingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BillingDestination parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (BillingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BillingDestination parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BillingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BillingDestination parseFrom(InputStream input) throws IOException {
            return (BillingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BillingDestination parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BillingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BillingDestination parseDelimitedFrom(InputStream input) throws IOException {
            return (BillingDestination) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static BillingDestination parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BillingDestination) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BillingDestination parseFrom(CodedInputStream input) throws IOException {
            return (BillingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BillingDestination parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BillingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(BillingDestination prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BillingDestination, Builder> implements BillingDestinationOrBuilder {
            /* synthetic */ Builder(C00081 x0) {
                this();
            }

            private Builder() {
                super(BillingDestination.DEFAULT_INSTANCE);
            }

            public String getMonitoredResource() {
                return ((BillingDestination) this.instance).getMonitoredResource();
            }

            public ByteString getMonitoredResourceBytes() {
                return ((BillingDestination) this.instance).getMonitoredResourceBytes();
            }

            public Builder setMonitoredResource(String value) {
                copyOnWrite();
                ((BillingDestination) this.instance).setMonitoredResource(value);
                return this;
            }

            public Builder clearMonitoredResource() {
                copyOnWrite();
                ((BillingDestination) this.instance).clearMonitoredResource();
                return this;
            }

            public Builder setMonitoredResourceBytes(ByteString value) {
                copyOnWrite();
                ((BillingDestination) this.instance).setMonitoredResourceBytes(value);
                return this;
            }

            public List<String> getMetricsList() {
                return Collections.unmodifiableList(((BillingDestination) this.instance).getMetricsList());
            }

            public int getMetricsCount() {
                return ((BillingDestination) this.instance).getMetricsCount();
            }

            public String getMetrics(int index) {
                return ((BillingDestination) this.instance).getMetrics(index);
            }

            public ByteString getMetricsBytes(int index) {
                return ((BillingDestination) this.instance).getMetricsBytes(index);
            }

            public Builder setMetrics(int index, String value) {
                copyOnWrite();
                ((BillingDestination) this.instance).setMetrics(index, value);
                return this;
            }

            public Builder addMetrics(String value) {
                copyOnWrite();
                ((BillingDestination) this.instance).addMetrics(value);
                return this;
            }

            public Builder addAllMetrics(Iterable<String> values) {
                copyOnWrite();
                ((BillingDestination) this.instance).addAllMetrics(values);
                return this;
            }

            public Builder clearMetrics() {
                copyOnWrite();
                ((BillingDestination) this.instance).clearMetrics();
                return this;
            }

            public Builder addMetricsBytes(ByteString value) {
                copyOnWrite();
                ((BillingDestination) this.instance).addMetricsBytes(value);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C00081.f7xa1df5c61[method.ordinal()]) {
                case 1:
                    return new BillingDestination();
                case 2:
                    return new Builder((C00081) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002Ț", new Object[]{"monitoredResource_", "metrics_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<BillingDestination> parser = PARSER;
                    if (parser == null) {
                        synchronized (BillingDestination.class) {
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
            BillingDestination defaultInstance = new BillingDestination();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(BillingDestination.class, defaultInstance);
        }

        public static BillingDestination getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BillingDestination> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.api.Billing$1 */
    static /* synthetic */ class C00081 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f7xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f7xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f7xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f7xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f7xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f7xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f7xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f7xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public List<BillingDestination> getConsumerDestinationsList() {
        return this.consumerDestinations_;
    }

    public List<? extends BillingDestinationOrBuilder> getConsumerDestinationsOrBuilderList() {
        return this.consumerDestinations_;
    }

    public int getConsumerDestinationsCount() {
        return this.consumerDestinations_.size();
    }

    public BillingDestination getConsumerDestinations(int index) {
        return (BillingDestination) this.consumerDestinations_.get(index);
    }

    public BillingDestinationOrBuilder getConsumerDestinationsOrBuilder(int index) {
        return (BillingDestinationOrBuilder) this.consumerDestinations_.get(index);
    }

    private void ensureConsumerDestinationsIsMutable() {
        Internal.ProtobufList<BillingDestination> tmp = this.consumerDestinations_;
        if (!tmp.isModifiable()) {
            this.consumerDestinations_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setConsumerDestinations(int index, BillingDestination value) {
        value.getClass();
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addConsumerDestinations(BillingDestination value) {
        value.getClass();
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.add(value);
    }

    /* access modifiers changed from: private */
    public void addConsumerDestinations(int index, BillingDestination value) {
        value.getClass();
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllConsumerDestinations(Iterable<? extends BillingDestination> values) {
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

    public static Billing parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Billing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Billing parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Billing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Billing parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Billing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Billing parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Billing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Billing parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Billing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Billing parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Billing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Billing parseFrom(InputStream input) throws IOException {
        return (Billing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Billing parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Billing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Billing parseDelimitedFrom(InputStream input) throws IOException {
        return (Billing) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Billing parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Billing) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Billing parseFrom(CodedInputStream input) throws IOException {
        return (Billing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Billing parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Billing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Billing prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Billing, Builder> implements BillingOrBuilder {
        /* synthetic */ Builder(C00081 x0) {
            this();
        }

        private Builder() {
            super(Billing.DEFAULT_INSTANCE);
        }

        public List<BillingDestination> getConsumerDestinationsList() {
            return Collections.unmodifiableList(((Billing) this.instance).getConsumerDestinationsList());
        }

        public int getConsumerDestinationsCount() {
            return ((Billing) this.instance).getConsumerDestinationsCount();
        }

        public BillingDestination getConsumerDestinations(int index) {
            return ((Billing) this.instance).getConsumerDestinations(index);
        }

        public Builder setConsumerDestinations(int index, BillingDestination value) {
            copyOnWrite();
            ((Billing) this.instance).setConsumerDestinations(index, value);
            return this;
        }

        public Builder setConsumerDestinations(int index, BillingDestination.Builder builderForValue) {
            copyOnWrite();
            ((Billing) this.instance).setConsumerDestinations(index, (BillingDestination) builderForValue.build());
            return this;
        }

        public Builder addConsumerDestinations(BillingDestination value) {
            copyOnWrite();
            ((Billing) this.instance).addConsumerDestinations(value);
            return this;
        }

        public Builder addConsumerDestinations(int index, BillingDestination value) {
            copyOnWrite();
            ((Billing) this.instance).addConsumerDestinations(index, value);
            return this;
        }

        public Builder addConsumerDestinations(BillingDestination.Builder builderForValue) {
            copyOnWrite();
            ((Billing) this.instance).addConsumerDestinations((BillingDestination) builderForValue.build());
            return this;
        }

        public Builder addConsumerDestinations(int index, BillingDestination.Builder builderForValue) {
            copyOnWrite();
            ((Billing) this.instance).addConsumerDestinations(index, (BillingDestination) builderForValue.build());
            return this;
        }

        public Builder addAllConsumerDestinations(Iterable<? extends BillingDestination> values) {
            copyOnWrite();
            ((Billing) this.instance).addAllConsumerDestinations(values);
            return this;
        }

        public Builder clearConsumerDestinations() {
            copyOnWrite();
            ((Billing) this.instance).clearConsumerDestinations();
            return this;
        }

        public Builder removeConsumerDestinations(int index) {
            copyOnWrite();
            ((Billing) this.instance).removeConsumerDestinations(index);
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00081.f7xa1df5c61[method.ordinal()]) {
            case 1:
                return new Billing();
            case 2:
                return new Builder((C00081) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\b\b\u0001\u0000\u0001\u0000\b\u001b", new Object[]{"consumerDestinations_", BillingDestination.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Billing> parser = PARSER;
                if (parser == null) {
                    synchronized (Billing.class) {
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
        Billing defaultInstance = new Billing();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Billing.class, defaultInstance);
    }

    public static Billing getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Billing> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
