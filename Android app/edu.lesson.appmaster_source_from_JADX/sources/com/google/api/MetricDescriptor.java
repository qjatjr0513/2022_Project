package com.google.api;

import com.google.api.LabelDescriptor;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Duration;
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

public final class MetricDescriptor extends GeneratedMessageLite<MetricDescriptor, Builder> implements MetricDescriptorOrBuilder {
    /* access modifiers changed from: private */
    public static final MetricDescriptor DEFAULT_INSTANCE;
    public static final int DESCRIPTION_FIELD_NUMBER = 6;
    public static final int DISPLAY_NAME_FIELD_NUMBER = 7;
    public static final int LABELS_FIELD_NUMBER = 2;
    public static final int LAUNCH_STAGE_FIELD_NUMBER = 12;
    public static final int METADATA_FIELD_NUMBER = 10;
    public static final int METRIC_KIND_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<MetricDescriptor> PARSER = null;
    public static final int TYPE_FIELD_NUMBER = 8;
    public static final int UNIT_FIELD_NUMBER = 5;
    public static final int VALUE_TYPE_FIELD_NUMBER = 4;
    private String description_ = "";
    private String displayName_ = "";
    private Internal.ProtobufList<LabelDescriptor> labels_ = emptyProtobufList();
    private int launchStage_;
    private MetricDescriptorMetadata metadata_;
    private int metricKind_;
    private String name_ = "";
    private String type_ = "";
    private String unit_ = "";
    private int valueType_;

    public interface MetricDescriptorMetadataOrBuilder extends MessageLiteOrBuilder {
        Duration getIngestDelay();

        @Deprecated
        LaunchStage getLaunchStage();

        @Deprecated
        int getLaunchStageValue();

        Duration getSamplePeriod();

        boolean hasIngestDelay();

        boolean hasSamplePeriod();
    }

    private MetricDescriptor() {
    }

    public enum MetricKind implements Internal.EnumLite {
        METRIC_KIND_UNSPECIFIED(0),
        GAUGE(1),
        DELTA(2),
        CUMULATIVE(3),
        UNRECOGNIZED(-1);
        
        public static final int CUMULATIVE_VALUE = 3;
        public static final int DELTA_VALUE = 2;
        public static final int GAUGE_VALUE = 1;
        public static final int METRIC_KIND_UNSPECIFIED_VALUE = 0;
        private static final Internal.EnumLiteMap<MetricKind> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<MetricKind>() {
                public MetricKind findValueByNumber(int number) {
                    return MetricKind.forNumber(number);
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
        public static MetricKind valueOf(int value2) {
            return forNumber(value2);
        }

        public static MetricKind forNumber(int value2) {
            switch (value2) {
                case 0:
                    return METRIC_KIND_UNSPECIFIED;
                case 1:
                    return GAUGE;
                case 2:
                    return DELTA;
                case 3:
                    return CUMULATIVE;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<MetricKind> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return MetricKindVerifier.INSTANCE;
        }

        private static final class MetricKindVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = null;

            private MetricKindVerifier() {
            }

            static {
                INSTANCE = new MetricKindVerifier();
            }

            public boolean isInRange(int number) {
                return MetricKind.forNumber(number) != null;
            }
        }

        private MetricKind(int value2) {
            this.value = value2;
        }
    }

    public enum ValueType implements Internal.EnumLite {
        VALUE_TYPE_UNSPECIFIED(0),
        BOOL(1),
        INT64(2),
        DOUBLE(3),
        STRING(4),
        DISTRIBUTION(5),
        MONEY(6),
        UNRECOGNIZED(-1);
        
        public static final int BOOL_VALUE = 1;
        public static final int DISTRIBUTION_VALUE = 5;
        public static final int DOUBLE_VALUE = 3;
        public static final int INT64_VALUE = 2;
        public static final int MONEY_VALUE = 6;
        public static final int STRING_VALUE = 4;
        public static final int VALUE_TYPE_UNSPECIFIED_VALUE = 0;
        private static final Internal.EnumLiteMap<ValueType> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<ValueType>() {
                public ValueType findValueByNumber(int number) {
                    return ValueType.forNumber(number);
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
        public static ValueType valueOf(int value2) {
            return forNumber(value2);
        }

        public static ValueType forNumber(int value2) {
            switch (value2) {
                case 0:
                    return VALUE_TYPE_UNSPECIFIED;
                case 1:
                    return BOOL;
                case 2:
                    return INT64;
                case 3:
                    return DOUBLE;
                case 4:
                    return STRING;
                case 5:
                    return DISTRIBUTION;
                case 6:
                    return MONEY;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<ValueType> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return ValueTypeVerifier.INSTANCE;
        }

        private static final class ValueTypeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = null;

            private ValueTypeVerifier() {
            }

            static {
                INSTANCE = new ValueTypeVerifier();
            }

            public boolean isInRange(int number) {
                return ValueType.forNumber(number) != null;
            }
        }

        private ValueType(int value2) {
            this.value = value2;
        }
    }

    public static final class MetricDescriptorMetadata extends GeneratedMessageLite<MetricDescriptorMetadata, Builder> implements MetricDescriptorMetadataOrBuilder {
        /* access modifiers changed from: private */
        public static final MetricDescriptorMetadata DEFAULT_INSTANCE;
        public static final int INGEST_DELAY_FIELD_NUMBER = 3;
        public static final int LAUNCH_STAGE_FIELD_NUMBER = 1;
        private static volatile Parser<MetricDescriptorMetadata> PARSER = null;
        public static final int SAMPLE_PERIOD_FIELD_NUMBER = 2;
        private Duration ingestDelay_;
        private int launchStage_;
        private Duration samplePeriod_;

        private MetricDescriptorMetadata() {
        }

        @Deprecated
        public int getLaunchStageValue() {
            return this.launchStage_;
        }

        @Deprecated
        public LaunchStage getLaunchStage() {
            LaunchStage result = LaunchStage.forNumber(this.launchStage_);
            return result == null ? LaunchStage.UNRECOGNIZED : result;
        }

        /* access modifiers changed from: private */
        public void setLaunchStageValue(int value) {
            this.launchStage_ = value;
        }

        /* access modifiers changed from: private */
        public void setLaunchStage(LaunchStage value) {
            this.launchStage_ = value.getNumber();
        }

        /* access modifiers changed from: private */
        public void clearLaunchStage() {
            this.launchStage_ = 0;
        }

        public boolean hasSamplePeriod() {
            return this.samplePeriod_ != null;
        }

        public Duration getSamplePeriod() {
            Duration duration = this.samplePeriod_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        /* access modifiers changed from: private */
        public void setSamplePeriod(Duration value) {
            value.getClass();
            this.samplePeriod_ = value;
        }

        /* access modifiers changed from: private */
        public void mergeSamplePeriod(Duration value) {
            value.getClass();
            Duration duration = this.samplePeriod_;
            if (duration == null || duration == Duration.getDefaultInstance()) {
                this.samplePeriod_ = value;
            } else {
                this.samplePeriod_ = (Duration) ((Duration.Builder) Duration.newBuilder(this.samplePeriod_).mergeFrom(value)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearSamplePeriod() {
            this.samplePeriod_ = null;
        }

        public boolean hasIngestDelay() {
            return this.ingestDelay_ != null;
        }

        public Duration getIngestDelay() {
            Duration duration = this.ingestDelay_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        /* access modifiers changed from: private */
        public void setIngestDelay(Duration value) {
            value.getClass();
            this.ingestDelay_ = value;
        }

        /* access modifiers changed from: private */
        public void mergeIngestDelay(Duration value) {
            value.getClass();
            Duration duration = this.ingestDelay_;
            if (duration == null || duration == Duration.getDefaultInstance()) {
                this.ingestDelay_ = value;
            } else {
                this.ingestDelay_ = (Duration) ((Duration.Builder) Duration.newBuilder(this.ingestDelay_).mergeFrom(value)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearIngestDelay() {
            this.ingestDelay_ = null;
        }

        public static MetricDescriptorMetadata parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (MetricDescriptorMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MetricDescriptorMetadata parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MetricDescriptorMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MetricDescriptorMetadata parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (MetricDescriptorMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MetricDescriptorMetadata parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MetricDescriptorMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MetricDescriptorMetadata parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (MetricDescriptorMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MetricDescriptorMetadata parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MetricDescriptorMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MetricDescriptorMetadata parseFrom(InputStream input) throws IOException {
            return (MetricDescriptorMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static MetricDescriptorMetadata parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MetricDescriptorMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static MetricDescriptorMetadata parseDelimitedFrom(InputStream input) throws IOException {
            return (MetricDescriptorMetadata) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static MetricDescriptorMetadata parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MetricDescriptorMetadata) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static MetricDescriptorMetadata parseFrom(CodedInputStream input) throws IOException {
            return (MetricDescriptorMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static MetricDescriptorMetadata parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MetricDescriptorMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(MetricDescriptorMetadata prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<MetricDescriptorMetadata, Builder> implements MetricDescriptorMetadataOrBuilder {
            /* synthetic */ Builder(C00301 x0) {
                this();
            }

            private Builder() {
                super(MetricDescriptorMetadata.DEFAULT_INSTANCE);
            }

            @Deprecated
            public int getLaunchStageValue() {
                return ((MetricDescriptorMetadata) this.instance).getLaunchStageValue();
            }

            @Deprecated
            public Builder setLaunchStageValue(int value) {
                copyOnWrite();
                ((MetricDescriptorMetadata) this.instance).setLaunchStageValue(value);
                return this;
            }

            @Deprecated
            public LaunchStage getLaunchStage() {
                return ((MetricDescriptorMetadata) this.instance).getLaunchStage();
            }

            @Deprecated
            public Builder setLaunchStage(LaunchStage value) {
                copyOnWrite();
                ((MetricDescriptorMetadata) this.instance).setLaunchStage(value);
                return this;
            }

            @Deprecated
            public Builder clearLaunchStage() {
                copyOnWrite();
                ((MetricDescriptorMetadata) this.instance).clearLaunchStage();
                return this;
            }

            public boolean hasSamplePeriod() {
                return ((MetricDescriptorMetadata) this.instance).hasSamplePeriod();
            }

            public Duration getSamplePeriod() {
                return ((MetricDescriptorMetadata) this.instance).getSamplePeriod();
            }

            public Builder setSamplePeriod(Duration value) {
                copyOnWrite();
                ((MetricDescriptorMetadata) this.instance).setSamplePeriod(value);
                return this;
            }

            public Builder setSamplePeriod(Duration.Builder builderForValue) {
                copyOnWrite();
                ((MetricDescriptorMetadata) this.instance).setSamplePeriod((Duration) builderForValue.build());
                return this;
            }

            public Builder mergeSamplePeriod(Duration value) {
                copyOnWrite();
                ((MetricDescriptorMetadata) this.instance).mergeSamplePeriod(value);
                return this;
            }

            public Builder clearSamplePeriod() {
                copyOnWrite();
                ((MetricDescriptorMetadata) this.instance).clearSamplePeriod();
                return this;
            }

            public boolean hasIngestDelay() {
                return ((MetricDescriptorMetadata) this.instance).hasIngestDelay();
            }

            public Duration getIngestDelay() {
                return ((MetricDescriptorMetadata) this.instance).getIngestDelay();
            }

            public Builder setIngestDelay(Duration value) {
                copyOnWrite();
                ((MetricDescriptorMetadata) this.instance).setIngestDelay(value);
                return this;
            }

            public Builder setIngestDelay(Duration.Builder builderForValue) {
                copyOnWrite();
                ((MetricDescriptorMetadata) this.instance).setIngestDelay((Duration) builderForValue.build());
                return this;
            }

            public Builder mergeIngestDelay(Duration value) {
                copyOnWrite();
                ((MetricDescriptorMetadata) this.instance).mergeIngestDelay(value);
                return this;
            }

            public Builder clearIngestDelay() {
                copyOnWrite();
                ((MetricDescriptorMetadata) this.instance).clearIngestDelay();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C00301.f26xa1df5c61[method.ordinal()]) {
                case 1:
                    return new MetricDescriptorMetadata();
                case 2:
                    return new Builder((C00301) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0002\t\u0003\t", new Object[]{"launchStage_", "samplePeriod_", "ingestDelay_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<MetricDescriptorMetadata> parser = PARSER;
                    if (parser == null) {
                        synchronized (MetricDescriptorMetadata.class) {
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
            MetricDescriptorMetadata defaultInstance = new MetricDescriptorMetadata();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(MetricDescriptorMetadata.class, defaultInstance);
        }

        public static MetricDescriptorMetadata getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<MetricDescriptorMetadata> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.api.MetricDescriptor$1 */
    static /* synthetic */ class C00301 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f26xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f26xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f26xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f26xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f26xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f26xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f26xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f26xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
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

    public String getType() {
        return this.type_;
    }

    public ByteString getTypeBytes() {
        return ByteString.copyFromUtf8(this.type_);
    }

    /* access modifiers changed from: private */
    public void setType(String value) {
        value.getClass();
        this.type_ = value;
    }

    /* access modifiers changed from: private */
    public void clearType() {
        this.type_ = getDefaultInstance().getType();
    }

    /* access modifiers changed from: private */
    public void setTypeBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.type_ = value.toStringUtf8();
    }

    public List<LabelDescriptor> getLabelsList() {
        return this.labels_;
    }

    public List<? extends LabelDescriptorOrBuilder> getLabelsOrBuilderList() {
        return this.labels_;
    }

    public int getLabelsCount() {
        return this.labels_.size();
    }

    public LabelDescriptor getLabels(int index) {
        return (LabelDescriptor) this.labels_.get(index);
    }

    public LabelDescriptorOrBuilder getLabelsOrBuilder(int index) {
        return (LabelDescriptorOrBuilder) this.labels_.get(index);
    }

    private void ensureLabelsIsMutable() {
        Internal.ProtobufList<LabelDescriptor> tmp = this.labels_;
        if (!tmp.isModifiable()) {
            this.labels_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setLabels(int index, LabelDescriptor value) {
        value.getClass();
        ensureLabelsIsMutable();
        this.labels_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addLabels(LabelDescriptor value) {
        value.getClass();
        ensureLabelsIsMutable();
        this.labels_.add(value);
    }

    /* access modifiers changed from: private */
    public void addLabels(int index, LabelDescriptor value) {
        value.getClass();
        ensureLabelsIsMutable();
        this.labels_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllLabels(Iterable<? extends LabelDescriptor> values) {
        ensureLabelsIsMutable();
        AbstractMessageLite.addAll(values, this.labels_);
    }

    /* access modifiers changed from: private */
    public void clearLabels() {
        this.labels_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeLabels(int index) {
        ensureLabelsIsMutable();
        this.labels_.remove(index);
    }

    public int getMetricKindValue() {
        return this.metricKind_;
    }

    public MetricKind getMetricKind() {
        MetricKind result = MetricKind.forNumber(this.metricKind_);
        return result == null ? MetricKind.UNRECOGNIZED : result;
    }

    /* access modifiers changed from: private */
    public void setMetricKindValue(int value) {
        this.metricKind_ = value;
    }

    /* access modifiers changed from: private */
    public void setMetricKind(MetricKind value) {
        this.metricKind_ = value.getNumber();
    }

    /* access modifiers changed from: private */
    public void clearMetricKind() {
        this.metricKind_ = 0;
    }

    public int getValueTypeValue() {
        return this.valueType_;
    }

    public ValueType getValueType() {
        ValueType result = ValueType.forNumber(this.valueType_);
        return result == null ? ValueType.UNRECOGNIZED : result;
    }

    /* access modifiers changed from: private */
    public void setValueTypeValue(int value) {
        this.valueType_ = value;
    }

    /* access modifiers changed from: private */
    public void setValueType(ValueType value) {
        this.valueType_ = value.getNumber();
    }

    /* access modifiers changed from: private */
    public void clearValueType() {
        this.valueType_ = 0;
    }

    public String getUnit() {
        return this.unit_;
    }

    public ByteString getUnitBytes() {
        return ByteString.copyFromUtf8(this.unit_);
    }

    /* access modifiers changed from: private */
    public void setUnit(String value) {
        value.getClass();
        this.unit_ = value;
    }

    /* access modifiers changed from: private */
    public void clearUnit() {
        this.unit_ = getDefaultInstance().getUnit();
    }

    /* access modifiers changed from: private */
    public void setUnitBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.unit_ = value.toStringUtf8();
    }

    public String getDescription() {
        return this.description_;
    }

    public ByteString getDescriptionBytes() {
        return ByteString.copyFromUtf8(this.description_);
    }

    /* access modifiers changed from: private */
    public void setDescription(String value) {
        value.getClass();
        this.description_ = value;
    }

    /* access modifiers changed from: private */
    public void clearDescription() {
        this.description_ = getDefaultInstance().getDescription();
    }

    /* access modifiers changed from: private */
    public void setDescriptionBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.description_ = value.toStringUtf8();
    }

    public String getDisplayName() {
        return this.displayName_;
    }

    public ByteString getDisplayNameBytes() {
        return ByteString.copyFromUtf8(this.displayName_);
    }

    /* access modifiers changed from: private */
    public void setDisplayName(String value) {
        value.getClass();
        this.displayName_ = value;
    }

    /* access modifiers changed from: private */
    public void clearDisplayName() {
        this.displayName_ = getDefaultInstance().getDisplayName();
    }

    /* access modifiers changed from: private */
    public void setDisplayNameBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.displayName_ = value.toStringUtf8();
    }

    public boolean hasMetadata() {
        return this.metadata_ != null;
    }

    public MetricDescriptorMetadata getMetadata() {
        MetricDescriptorMetadata metricDescriptorMetadata = this.metadata_;
        return metricDescriptorMetadata == null ? MetricDescriptorMetadata.getDefaultInstance() : metricDescriptorMetadata;
    }

    /* access modifiers changed from: private */
    public void setMetadata(MetricDescriptorMetadata value) {
        value.getClass();
        this.metadata_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeMetadata(MetricDescriptorMetadata value) {
        value.getClass();
        MetricDescriptorMetadata metricDescriptorMetadata = this.metadata_;
        if (metricDescriptorMetadata == null || metricDescriptorMetadata == MetricDescriptorMetadata.getDefaultInstance()) {
            this.metadata_ = value;
        } else {
            this.metadata_ = (MetricDescriptorMetadata) ((MetricDescriptorMetadata.Builder) MetricDescriptorMetadata.newBuilder(this.metadata_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearMetadata() {
        this.metadata_ = null;
    }

    public int getLaunchStageValue() {
        return this.launchStage_;
    }

    public LaunchStage getLaunchStage() {
        LaunchStage result = LaunchStage.forNumber(this.launchStage_);
        return result == null ? LaunchStage.UNRECOGNIZED : result;
    }

    /* access modifiers changed from: private */
    public void setLaunchStageValue(int value) {
        this.launchStage_ = value;
    }

    /* access modifiers changed from: private */
    public void setLaunchStage(LaunchStage value) {
        this.launchStage_ = value.getNumber();
    }

    /* access modifiers changed from: private */
    public void clearLaunchStage() {
        this.launchStage_ = 0;
    }

    public static MetricDescriptor parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static MetricDescriptor parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static MetricDescriptor parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static MetricDescriptor parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static MetricDescriptor parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static MetricDescriptor parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static MetricDescriptor parseFrom(InputStream input) throws IOException {
        return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static MetricDescriptor parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static MetricDescriptor parseDelimitedFrom(InputStream input) throws IOException {
        return (MetricDescriptor) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static MetricDescriptor parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MetricDescriptor) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static MetricDescriptor parseFrom(CodedInputStream input) throws IOException {
        return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static MetricDescriptor parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(MetricDescriptor prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<MetricDescriptor, Builder> implements MetricDescriptorOrBuilder {
        /* synthetic */ Builder(C00301 x0) {
            this();
        }

        private Builder() {
            super(MetricDescriptor.DEFAULT_INSTANCE);
        }

        public String getName() {
            return ((MetricDescriptor) this.instance).getName();
        }

        public ByteString getNameBytes() {
            return ((MetricDescriptor) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((MetricDescriptor) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setNameBytes(value);
            return this;
        }

        public String getType() {
            return ((MetricDescriptor) this.instance).getType();
        }

        public ByteString getTypeBytes() {
            return ((MetricDescriptor) this.instance).getTypeBytes();
        }

        public Builder setType(String value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setType(value);
            return this;
        }

        public Builder clearType() {
            copyOnWrite();
            ((MetricDescriptor) this.instance).clearType();
            return this;
        }

        public Builder setTypeBytes(ByteString value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setTypeBytes(value);
            return this;
        }

        public List<LabelDescriptor> getLabelsList() {
            return Collections.unmodifiableList(((MetricDescriptor) this.instance).getLabelsList());
        }

        public int getLabelsCount() {
            return ((MetricDescriptor) this.instance).getLabelsCount();
        }

        public LabelDescriptor getLabels(int index) {
            return ((MetricDescriptor) this.instance).getLabels(index);
        }

        public Builder setLabels(int index, LabelDescriptor value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setLabels(index, value);
            return this;
        }

        public Builder setLabels(int index, LabelDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setLabels(index, (LabelDescriptor) builderForValue.build());
            return this;
        }

        public Builder addLabels(LabelDescriptor value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).addLabels(value);
            return this;
        }

        public Builder addLabels(int index, LabelDescriptor value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).addLabels(index, value);
            return this;
        }

        public Builder addLabels(LabelDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).addLabels((LabelDescriptor) builderForValue.build());
            return this;
        }

        public Builder addLabels(int index, LabelDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).addLabels(index, (LabelDescriptor) builderForValue.build());
            return this;
        }

        public Builder addAllLabels(Iterable<? extends LabelDescriptor> values) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).addAllLabels(values);
            return this;
        }

        public Builder clearLabels() {
            copyOnWrite();
            ((MetricDescriptor) this.instance).clearLabels();
            return this;
        }

        public Builder removeLabels(int index) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).removeLabels(index);
            return this;
        }

        public int getMetricKindValue() {
            return ((MetricDescriptor) this.instance).getMetricKindValue();
        }

        public Builder setMetricKindValue(int value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setMetricKindValue(value);
            return this;
        }

        public MetricKind getMetricKind() {
            return ((MetricDescriptor) this.instance).getMetricKind();
        }

        public Builder setMetricKind(MetricKind value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setMetricKind(value);
            return this;
        }

        public Builder clearMetricKind() {
            copyOnWrite();
            ((MetricDescriptor) this.instance).clearMetricKind();
            return this;
        }

        public int getValueTypeValue() {
            return ((MetricDescriptor) this.instance).getValueTypeValue();
        }

        public Builder setValueTypeValue(int value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setValueTypeValue(value);
            return this;
        }

        public ValueType getValueType() {
            return ((MetricDescriptor) this.instance).getValueType();
        }

        public Builder setValueType(ValueType value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setValueType(value);
            return this;
        }

        public Builder clearValueType() {
            copyOnWrite();
            ((MetricDescriptor) this.instance).clearValueType();
            return this;
        }

        public String getUnit() {
            return ((MetricDescriptor) this.instance).getUnit();
        }

        public ByteString getUnitBytes() {
            return ((MetricDescriptor) this.instance).getUnitBytes();
        }

        public Builder setUnit(String value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setUnit(value);
            return this;
        }

        public Builder clearUnit() {
            copyOnWrite();
            ((MetricDescriptor) this.instance).clearUnit();
            return this;
        }

        public Builder setUnitBytes(ByteString value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setUnitBytes(value);
            return this;
        }

        public String getDescription() {
            return ((MetricDescriptor) this.instance).getDescription();
        }

        public ByteString getDescriptionBytes() {
            return ((MetricDescriptor) this.instance).getDescriptionBytes();
        }

        public Builder setDescription(String value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setDescription(value);
            return this;
        }

        public Builder clearDescription() {
            copyOnWrite();
            ((MetricDescriptor) this.instance).clearDescription();
            return this;
        }

        public Builder setDescriptionBytes(ByteString value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setDescriptionBytes(value);
            return this;
        }

        public String getDisplayName() {
            return ((MetricDescriptor) this.instance).getDisplayName();
        }

        public ByteString getDisplayNameBytes() {
            return ((MetricDescriptor) this.instance).getDisplayNameBytes();
        }

        public Builder setDisplayName(String value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setDisplayName(value);
            return this;
        }

        public Builder clearDisplayName() {
            copyOnWrite();
            ((MetricDescriptor) this.instance).clearDisplayName();
            return this;
        }

        public Builder setDisplayNameBytes(ByteString value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setDisplayNameBytes(value);
            return this;
        }

        public boolean hasMetadata() {
            return ((MetricDescriptor) this.instance).hasMetadata();
        }

        public MetricDescriptorMetadata getMetadata() {
            return ((MetricDescriptor) this.instance).getMetadata();
        }

        public Builder setMetadata(MetricDescriptorMetadata value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setMetadata(value);
            return this;
        }

        public Builder setMetadata(MetricDescriptorMetadata.Builder builderForValue) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setMetadata((MetricDescriptorMetadata) builderForValue.build());
            return this;
        }

        public Builder mergeMetadata(MetricDescriptorMetadata value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).mergeMetadata(value);
            return this;
        }

        public Builder clearMetadata() {
            copyOnWrite();
            ((MetricDescriptor) this.instance).clearMetadata();
            return this;
        }

        public int getLaunchStageValue() {
            return ((MetricDescriptor) this.instance).getLaunchStageValue();
        }

        public Builder setLaunchStageValue(int value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setLaunchStageValue(value);
            return this;
        }

        public LaunchStage getLaunchStage() {
            return ((MetricDescriptor) this.instance).getLaunchStage();
        }

        public Builder setLaunchStage(LaunchStage value) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setLaunchStage(value);
            return this;
        }

        public Builder clearLaunchStage() {
            copyOnWrite();
            ((MetricDescriptor) this.instance).clearLaunchStage();
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00301.f26xa1df5c61[method.ordinal()]) {
            case 1:
                return new MetricDescriptor();
            case 2:
                return new Builder((C00301) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\n\u0000\u0000\u0001\f\n\u0000\u0001\u0000\u0001Ȉ\u0002\u001b\u0003\f\u0004\f\u0005Ȉ\u0006Ȉ\u0007Ȉ\bȈ\n\t\f\f", new Object[]{"name_", "labels_", LabelDescriptor.class, "metricKind_", "valueType_", "unit_", "description_", "displayName_", "type_", "metadata_", "launchStage_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<MetricDescriptor> parser = PARSER;
                if (parser == null) {
                    synchronized (MetricDescriptor.class) {
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
        MetricDescriptor defaultInstance = new MetricDescriptor();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(MetricDescriptor.class, defaultInstance);
    }

    public static MetricDescriptor getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MetricDescriptor> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
