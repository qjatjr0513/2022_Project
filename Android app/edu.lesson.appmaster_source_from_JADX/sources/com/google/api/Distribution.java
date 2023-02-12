package com.google.api;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class Distribution extends GeneratedMessageLite<Distribution, Builder> implements DistributionOrBuilder {
    public static final int BUCKET_COUNTS_FIELD_NUMBER = 7;
    public static final int BUCKET_OPTIONS_FIELD_NUMBER = 6;
    public static final int COUNT_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final Distribution DEFAULT_INSTANCE;
    public static final int EXEMPLARS_FIELD_NUMBER = 10;
    public static final int MEAN_FIELD_NUMBER = 2;
    private static volatile Parser<Distribution> PARSER = null;
    public static final int RANGE_FIELD_NUMBER = 4;
    public static final int SUM_OF_SQUARED_DEVIATION_FIELD_NUMBER = 3;
    private int bucketCountsMemoizedSerializedSize = -1;
    private Internal.LongList bucketCounts_ = emptyLongList();
    private BucketOptions bucketOptions_;
    private long count_;
    private Internal.ProtobufList<Exemplar> exemplars_ = emptyProtobufList();
    private double mean_;
    private Range range_;
    private double sumOfSquaredDeviation_;

    public interface BucketOptionsOrBuilder extends MessageLiteOrBuilder {
        BucketOptions.Explicit getExplicitBuckets();

        BucketOptions.Exponential getExponentialBuckets();

        BucketOptions.Linear getLinearBuckets();

        BucketOptions.OptionsCase getOptionsCase();

        boolean hasExplicitBuckets();

        boolean hasExponentialBuckets();

        boolean hasLinearBuckets();
    }

    public interface ExemplarOrBuilder extends MessageLiteOrBuilder {
        Any getAttachments(int i);

        int getAttachmentsCount();

        List<Any> getAttachmentsList();

        Timestamp getTimestamp();

        double getValue();

        boolean hasTimestamp();
    }

    public interface RangeOrBuilder extends MessageLiteOrBuilder {
        double getMax();

        double getMin();
    }

    private Distribution() {
    }

    public static final class Range extends GeneratedMessageLite<Range, Builder> implements RangeOrBuilder {
        /* access modifiers changed from: private */
        public static final Range DEFAULT_INSTANCE;
        public static final int MAX_FIELD_NUMBER = 2;
        public static final int MIN_FIELD_NUMBER = 1;
        private static volatile Parser<Range> PARSER;
        private double max_;
        private double min_;

        private Range() {
        }

        public double getMin() {
            return this.min_;
        }

        /* access modifiers changed from: private */
        public void setMin(double value) {
            this.min_ = value;
        }

        /* access modifiers changed from: private */
        public void clearMin() {
            this.min_ = 0.0d;
        }

        public double getMax() {
            return this.max_;
        }

        /* access modifiers changed from: private */
        public void setMax(double value) {
            this.max_ = value;
        }

        /* access modifiers changed from: private */
        public void clearMax() {
            this.max_ = 0.0d;
        }

        public static Range parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Range) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Range parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Range) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Range parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Range) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Range parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Range) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Range parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Range) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Range parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Range) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Range parseFrom(InputStream input) throws IOException {
            return (Range) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Range parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Range) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Range parseDelimitedFrom(InputStream input) throws IOException {
            return (Range) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Range parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Range) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Range parseFrom(CodedInputStream input) throws IOException {
            return (Range) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Range parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Range) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Range prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Range, Builder> implements RangeOrBuilder {
            /* synthetic */ Builder(C00151 x0) {
                this();
            }

            private Builder() {
                super(Range.DEFAULT_INSTANCE);
            }

            public double getMin() {
                return ((Range) this.instance).getMin();
            }

            public Builder setMin(double value) {
                copyOnWrite();
                ((Range) this.instance).setMin(value);
                return this;
            }

            public Builder clearMin() {
                copyOnWrite();
                ((Range) this.instance).clearMin();
                return this;
            }

            public double getMax() {
                return ((Range) this.instance).getMax();
            }

            public Builder setMax(double value) {
                copyOnWrite();
                ((Range) this.instance).setMax(value);
                return this;
            }

            public Builder clearMax() {
                copyOnWrite();
                ((Range) this.instance).clearMax();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C00151.f13xa1df5c61[method.ordinal()]) {
                case 1:
                    return new Range();
                case 2:
                    return new Builder((C00151) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0000\u0002\u0000", new Object[]{"min_", "max_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Range> parser = PARSER;
                    if (parser == null) {
                        synchronized (Range.class) {
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
            Range defaultInstance = new Range();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Range.class, defaultInstance);
        }

        public static Range getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Range> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.api.Distribution$1 */
    static /* synthetic */ class C00151 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f13xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f13xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f13xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f13xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f13xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f13xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f13xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f13xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public static final class BucketOptions extends GeneratedMessageLite<BucketOptions, Builder> implements BucketOptionsOrBuilder {
        /* access modifiers changed from: private */
        public static final BucketOptions DEFAULT_INSTANCE;
        public static final int EXPLICIT_BUCKETS_FIELD_NUMBER = 3;
        public static final int EXPONENTIAL_BUCKETS_FIELD_NUMBER = 2;
        public static final int LINEAR_BUCKETS_FIELD_NUMBER = 1;
        private static volatile Parser<BucketOptions> PARSER;
        private int optionsCase_ = 0;
        private Object options_;

        public interface ExplicitOrBuilder extends MessageLiteOrBuilder {
            double getBounds(int i);

            int getBoundsCount();

            List<Double> getBoundsList();
        }

        public interface ExponentialOrBuilder extends MessageLiteOrBuilder {
            double getGrowthFactor();

            int getNumFiniteBuckets();

            double getScale();
        }

        public interface LinearOrBuilder extends MessageLiteOrBuilder {
            int getNumFiniteBuckets();

            double getOffset();

            double getWidth();
        }

        private BucketOptions() {
        }

        public static final class Linear extends GeneratedMessageLite<Linear, Builder> implements LinearOrBuilder {
            /* access modifiers changed from: private */
            public static final Linear DEFAULT_INSTANCE;
            public static final int NUM_FINITE_BUCKETS_FIELD_NUMBER = 1;
            public static final int OFFSET_FIELD_NUMBER = 3;
            private static volatile Parser<Linear> PARSER = null;
            public static final int WIDTH_FIELD_NUMBER = 2;
            private int numFiniteBuckets_;
            private double offset_;
            private double width_;

            private Linear() {
            }

            public int getNumFiniteBuckets() {
                return this.numFiniteBuckets_;
            }

            /* access modifiers changed from: private */
            public void setNumFiniteBuckets(int value) {
                this.numFiniteBuckets_ = value;
            }

            /* access modifiers changed from: private */
            public void clearNumFiniteBuckets() {
                this.numFiniteBuckets_ = 0;
            }

            public double getWidth() {
                return this.width_;
            }

            /* access modifiers changed from: private */
            public void setWidth(double value) {
                this.width_ = value;
            }

            /* access modifiers changed from: private */
            public void clearWidth() {
                this.width_ = 0.0d;
            }

            public double getOffset() {
                return this.offset_;
            }

            /* access modifiers changed from: private */
            public void setOffset(double value) {
                this.offset_ = value;
            }

            /* access modifiers changed from: private */
            public void clearOffset() {
                this.offset_ = 0.0d;
            }

            public static Linear parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (Linear) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Linear parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Linear) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Linear parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Linear) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Linear parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Linear) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Linear parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Linear) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Linear parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Linear) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Linear parseFrom(InputStream input) throws IOException {
                return (Linear) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Linear parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Linear) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Linear parseDelimitedFrom(InputStream input) throws IOException {
                return (Linear) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Linear parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Linear) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Linear parseFrom(CodedInputStream input) throws IOException {
                return (Linear) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Linear parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Linear) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(Linear prototype) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Linear, Builder> implements LinearOrBuilder {
                /* synthetic */ Builder(C00151 x0) {
                    this();
                }

                private Builder() {
                    super(Linear.DEFAULT_INSTANCE);
                }

                public int getNumFiniteBuckets() {
                    return ((Linear) this.instance).getNumFiniteBuckets();
                }

                public Builder setNumFiniteBuckets(int value) {
                    copyOnWrite();
                    ((Linear) this.instance).setNumFiniteBuckets(value);
                    return this;
                }

                public Builder clearNumFiniteBuckets() {
                    copyOnWrite();
                    ((Linear) this.instance).clearNumFiniteBuckets();
                    return this;
                }

                public double getWidth() {
                    return ((Linear) this.instance).getWidth();
                }

                public Builder setWidth(double value) {
                    copyOnWrite();
                    ((Linear) this.instance).setWidth(value);
                    return this;
                }

                public Builder clearWidth() {
                    copyOnWrite();
                    ((Linear) this.instance).clearWidth();
                    return this;
                }

                public double getOffset() {
                    return ((Linear) this.instance).getOffset();
                }

                public Builder setOffset(double value) {
                    copyOnWrite();
                    ((Linear) this.instance).setOffset(value);
                    return this;
                }

                public Builder clearOffset() {
                    copyOnWrite();
                    ((Linear) this.instance).clearOffset();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (C00151.f13xa1df5c61[method.ordinal()]) {
                    case 1:
                        return new Linear();
                    case 2:
                        return new Builder((C00151) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0002\u0000\u0003\u0000", new Object[]{"numFiniteBuckets_", "width_", "offset_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<Linear> parser = PARSER;
                        if (parser == null) {
                            synchronized (Linear.class) {
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
                Linear defaultInstance = new Linear();
                DEFAULT_INSTANCE = defaultInstance;
                GeneratedMessageLite.registerDefaultInstance(Linear.class, defaultInstance);
            }

            public static Linear getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Linear> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class Exponential extends GeneratedMessageLite<Exponential, Builder> implements ExponentialOrBuilder {
            /* access modifiers changed from: private */
            public static final Exponential DEFAULT_INSTANCE;
            public static final int GROWTH_FACTOR_FIELD_NUMBER = 2;
            public static final int NUM_FINITE_BUCKETS_FIELD_NUMBER = 1;
            private static volatile Parser<Exponential> PARSER = null;
            public static final int SCALE_FIELD_NUMBER = 3;
            private double growthFactor_;
            private int numFiniteBuckets_;
            private double scale_;

            private Exponential() {
            }

            public int getNumFiniteBuckets() {
                return this.numFiniteBuckets_;
            }

            /* access modifiers changed from: private */
            public void setNumFiniteBuckets(int value) {
                this.numFiniteBuckets_ = value;
            }

            /* access modifiers changed from: private */
            public void clearNumFiniteBuckets() {
                this.numFiniteBuckets_ = 0;
            }

            public double getGrowthFactor() {
                return this.growthFactor_;
            }

            /* access modifiers changed from: private */
            public void setGrowthFactor(double value) {
                this.growthFactor_ = value;
            }

            /* access modifiers changed from: private */
            public void clearGrowthFactor() {
                this.growthFactor_ = 0.0d;
            }

            public double getScale() {
                return this.scale_;
            }

            /* access modifiers changed from: private */
            public void setScale(double value) {
                this.scale_ = value;
            }

            /* access modifiers changed from: private */
            public void clearScale() {
                this.scale_ = 0.0d;
            }

            public static Exponential parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (Exponential) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Exponential parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Exponential) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Exponential parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Exponential) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Exponential parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Exponential) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Exponential parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Exponential) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Exponential parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Exponential) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Exponential parseFrom(InputStream input) throws IOException {
                return (Exponential) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Exponential parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Exponential) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Exponential parseDelimitedFrom(InputStream input) throws IOException {
                return (Exponential) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Exponential parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Exponential) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Exponential parseFrom(CodedInputStream input) throws IOException {
                return (Exponential) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Exponential parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Exponential) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(Exponential prototype) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Exponential, Builder> implements ExponentialOrBuilder {
                /* synthetic */ Builder(C00151 x0) {
                    this();
                }

                private Builder() {
                    super(Exponential.DEFAULT_INSTANCE);
                }

                public int getNumFiniteBuckets() {
                    return ((Exponential) this.instance).getNumFiniteBuckets();
                }

                public Builder setNumFiniteBuckets(int value) {
                    copyOnWrite();
                    ((Exponential) this.instance).setNumFiniteBuckets(value);
                    return this;
                }

                public Builder clearNumFiniteBuckets() {
                    copyOnWrite();
                    ((Exponential) this.instance).clearNumFiniteBuckets();
                    return this;
                }

                public double getGrowthFactor() {
                    return ((Exponential) this.instance).getGrowthFactor();
                }

                public Builder setGrowthFactor(double value) {
                    copyOnWrite();
                    ((Exponential) this.instance).setGrowthFactor(value);
                    return this;
                }

                public Builder clearGrowthFactor() {
                    copyOnWrite();
                    ((Exponential) this.instance).clearGrowthFactor();
                    return this;
                }

                public double getScale() {
                    return ((Exponential) this.instance).getScale();
                }

                public Builder setScale(double value) {
                    copyOnWrite();
                    ((Exponential) this.instance).setScale(value);
                    return this;
                }

                public Builder clearScale() {
                    copyOnWrite();
                    ((Exponential) this.instance).clearScale();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (C00151.f13xa1df5c61[method.ordinal()]) {
                    case 1:
                        return new Exponential();
                    case 2:
                        return new Builder((C00151) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0002\u0000\u0003\u0000", new Object[]{"numFiniteBuckets_", "growthFactor_", "scale_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<Exponential> parser = PARSER;
                        if (parser == null) {
                            synchronized (Exponential.class) {
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
                Exponential defaultInstance = new Exponential();
                DEFAULT_INSTANCE = defaultInstance;
                GeneratedMessageLite.registerDefaultInstance(Exponential.class, defaultInstance);
            }

            public static Exponential getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Exponential> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class Explicit extends GeneratedMessageLite<Explicit, Builder> implements ExplicitOrBuilder {
            public static final int BOUNDS_FIELD_NUMBER = 1;
            /* access modifiers changed from: private */
            public static final Explicit DEFAULT_INSTANCE;
            private static volatile Parser<Explicit> PARSER;
            private int boundsMemoizedSerializedSize = -1;
            private Internal.DoubleList bounds_ = emptyDoubleList();

            private Explicit() {
            }

            public List<Double> getBoundsList() {
                return this.bounds_;
            }

            public int getBoundsCount() {
                return this.bounds_.size();
            }

            public double getBounds(int index) {
                return this.bounds_.getDouble(index);
            }

            private void ensureBoundsIsMutable() {
                Internal.DoubleList tmp = this.bounds_;
                if (!tmp.isModifiable()) {
                    this.bounds_ = GeneratedMessageLite.mutableCopy(tmp);
                }
            }

            /* access modifiers changed from: private */
            public void setBounds(int index, double value) {
                ensureBoundsIsMutable();
                this.bounds_.setDouble(index, value);
            }

            /* access modifiers changed from: private */
            public void addBounds(double value) {
                ensureBoundsIsMutable();
                this.bounds_.addDouble(value);
            }

            /* access modifiers changed from: private */
            public void addAllBounds(Iterable<? extends Double> values) {
                ensureBoundsIsMutable();
                AbstractMessageLite.addAll(values, this.bounds_);
            }

            /* access modifiers changed from: private */
            public void clearBounds() {
                this.bounds_ = emptyDoubleList();
            }

            public static Explicit parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (Explicit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Explicit parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Explicit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Explicit parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Explicit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Explicit parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Explicit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Explicit parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Explicit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Explicit parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Explicit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Explicit parseFrom(InputStream input) throws IOException {
                return (Explicit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Explicit parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Explicit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Explicit parseDelimitedFrom(InputStream input) throws IOException {
                return (Explicit) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Explicit parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Explicit) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Explicit parseFrom(CodedInputStream input) throws IOException {
                return (Explicit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Explicit parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Explicit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(Explicit prototype) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Explicit, Builder> implements ExplicitOrBuilder {
                /* synthetic */ Builder(C00151 x0) {
                    this();
                }

                private Builder() {
                    super(Explicit.DEFAULT_INSTANCE);
                }

                public List<Double> getBoundsList() {
                    return Collections.unmodifiableList(((Explicit) this.instance).getBoundsList());
                }

                public int getBoundsCount() {
                    return ((Explicit) this.instance).getBoundsCount();
                }

                public double getBounds(int index) {
                    return ((Explicit) this.instance).getBounds(index);
                }

                public Builder setBounds(int index, double value) {
                    copyOnWrite();
                    ((Explicit) this.instance).setBounds(index, value);
                    return this;
                }

                public Builder addBounds(double value) {
                    copyOnWrite();
                    ((Explicit) this.instance).addBounds(value);
                    return this;
                }

                public Builder addAllBounds(Iterable<? extends Double> values) {
                    copyOnWrite();
                    ((Explicit) this.instance).addAllBounds(values);
                    return this;
                }

                public Builder clearBounds() {
                    copyOnWrite();
                    ((Explicit) this.instance).clearBounds();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (C00151.f13xa1df5c61[method.ordinal()]) {
                    case 1:
                        return new Explicit();
                    case 2:
                        return new Builder((C00151) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001#", new Object[]{"bounds_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<Explicit> parser = PARSER;
                        if (parser == null) {
                            synchronized (Explicit.class) {
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
                Explicit defaultInstance = new Explicit();
                DEFAULT_INSTANCE = defaultInstance;
                GeneratedMessageLite.registerDefaultInstance(Explicit.class, defaultInstance);
            }

            public static Explicit getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Explicit> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public enum OptionsCase {
            LINEAR_BUCKETS(1),
            EXPONENTIAL_BUCKETS(2),
            EXPLICIT_BUCKETS(3),
            OPTIONS_NOT_SET(0);
            
            private final int value;

            private OptionsCase(int value2) {
                this.value = value2;
            }

            @Deprecated
            public static OptionsCase valueOf(int value2) {
                return forNumber(value2);
            }

            public static OptionsCase forNumber(int value2) {
                switch (value2) {
                    case 0:
                        return OPTIONS_NOT_SET;
                    case 1:
                        return LINEAR_BUCKETS;
                    case 2:
                        return EXPONENTIAL_BUCKETS;
                    case 3:
                        return EXPLICIT_BUCKETS;
                    default:
                        return null;
                }
            }

            public int getNumber() {
                return this.value;
            }
        }

        public OptionsCase getOptionsCase() {
            return OptionsCase.forNumber(this.optionsCase_);
        }

        /* access modifiers changed from: private */
        public void clearOptions() {
            this.optionsCase_ = 0;
            this.options_ = null;
        }

        public boolean hasLinearBuckets() {
            return this.optionsCase_ == 1;
        }

        public Linear getLinearBuckets() {
            if (this.optionsCase_ == 1) {
                return (Linear) this.options_;
            }
            return Linear.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setLinearBuckets(Linear value) {
            value.getClass();
            this.options_ = value;
            this.optionsCase_ = 1;
        }

        /* access modifiers changed from: private */
        public void mergeLinearBuckets(Linear value) {
            value.getClass();
            if (this.optionsCase_ != 1 || this.options_ == Linear.getDefaultInstance()) {
                this.options_ = value;
            } else {
                this.options_ = ((Linear.Builder) Linear.newBuilder((Linear) this.options_).mergeFrom(value)).buildPartial();
            }
            this.optionsCase_ = 1;
        }

        /* access modifiers changed from: private */
        public void clearLinearBuckets() {
            if (this.optionsCase_ == 1) {
                this.optionsCase_ = 0;
                this.options_ = null;
            }
        }

        public boolean hasExponentialBuckets() {
            return this.optionsCase_ == 2;
        }

        public Exponential getExponentialBuckets() {
            if (this.optionsCase_ == 2) {
                return (Exponential) this.options_;
            }
            return Exponential.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setExponentialBuckets(Exponential value) {
            value.getClass();
            this.options_ = value;
            this.optionsCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void mergeExponentialBuckets(Exponential value) {
            value.getClass();
            if (this.optionsCase_ != 2 || this.options_ == Exponential.getDefaultInstance()) {
                this.options_ = value;
            } else {
                this.options_ = ((Exponential.Builder) Exponential.newBuilder((Exponential) this.options_).mergeFrom(value)).buildPartial();
            }
            this.optionsCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void clearExponentialBuckets() {
            if (this.optionsCase_ == 2) {
                this.optionsCase_ = 0;
                this.options_ = null;
            }
        }

        public boolean hasExplicitBuckets() {
            return this.optionsCase_ == 3;
        }

        public Explicit getExplicitBuckets() {
            if (this.optionsCase_ == 3) {
                return (Explicit) this.options_;
            }
            return Explicit.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setExplicitBuckets(Explicit value) {
            value.getClass();
            this.options_ = value;
            this.optionsCase_ = 3;
        }

        /* access modifiers changed from: private */
        public void mergeExplicitBuckets(Explicit value) {
            value.getClass();
            if (this.optionsCase_ != 3 || this.options_ == Explicit.getDefaultInstance()) {
                this.options_ = value;
            } else {
                this.options_ = ((Explicit.Builder) Explicit.newBuilder((Explicit) this.options_).mergeFrom(value)).buildPartial();
            }
            this.optionsCase_ = 3;
        }

        /* access modifiers changed from: private */
        public void clearExplicitBuckets() {
            if (this.optionsCase_ == 3) {
                this.optionsCase_ = 0;
                this.options_ = null;
            }
        }

        public static BucketOptions parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (BucketOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BucketOptions parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BucketOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BucketOptions parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (BucketOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BucketOptions parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BucketOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BucketOptions parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (BucketOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BucketOptions parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BucketOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BucketOptions parseFrom(InputStream input) throws IOException {
            return (BucketOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BucketOptions parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BucketOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BucketOptions parseDelimitedFrom(InputStream input) throws IOException {
            return (BucketOptions) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static BucketOptions parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BucketOptions) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BucketOptions parseFrom(CodedInputStream input) throws IOException {
            return (BucketOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BucketOptions parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BucketOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(BucketOptions prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BucketOptions, Builder> implements BucketOptionsOrBuilder {
            /* synthetic */ Builder(C00151 x0) {
                this();
            }

            private Builder() {
                super(BucketOptions.DEFAULT_INSTANCE);
            }

            public OptionsCase getOptionsCase() {
                return ((BucketOptions) this.instance).getOptionsCase();
            }

            public Builder clearOptions() {
                copyOnWrite();
                ((BucketOptions) this.instance).clearOptions();
                return this;
            }

            public boolean hasLinearBuckets() {
                return ((BucketOptions) this.instance).hasLinearBuckets();
            }

            public Linear getLinearBuckets() {
                return ((BucketOptions) this.instance).getLinearBuckets();
            }

            public Builder setLinearBuckets(Linear value) {
                copyOnWrite();
                ((BucketOptions) this.instance).setLinearBuckets(value);
                return this;
            }

            public Builder setLinearBuckets(Linear.Builder builderForValue) {
                copyOnWrite();
                ((BucketOptions) this.instance).setLinearBuckets((Linear) builderForValue.build());
                return this;
            }

            public Builder mergeLinearBuckets(Linear value) {
                copyOnWrite();
                ((BucketOptions) this.instance).mergeLinearBuckets(value);
                return this;
            }

            public Builder clearLinearBuckets() {
                copyOnWrite();
                ((BucketOptions) this.instance).clearLinearBuckets();
                return this;
            }

            public boolean hasExponentialBuckets() {
                return ((BucketOptions) this.instance).hasExponentialBuckets();
            }

            public Exponential getExponentialBuckets() {
                return ((BucketOptions) this.instance).getExponentialBuckets();
            }

            public Builder setExponentialBuckets(Exponential value) {
                copyOnWrite();
                ((BucketOptions) this.instance).setExponentialBuckets(value);
                return this;
            }

            public Builder setExponentialBuckets(Exponential.Builder builderForValue) {
                copyOnWrite();
                ((BucketOptions) this.instance).setExponentialBuckets((Exponential) builderForValue.build());
                return this;
            }

            public Builder mergeExponentialBuckets(Exponential value) {
                copyOnWrite();
                ((BucketOptions) this.instance).mergeExponentialBuckets(value);
                return this;
            }

            public Builder clearExponentialBuckets() {
                copyOnWrite();
                ((BucketOptions) this.instance).clearExponentialBuckets();
                return this;
            }

            public boolean hasExplicitBuckets() {
                return ((BucketOptions) this.instance).hasExplicitBuckets();
            }

            public Explicit getExplicitBuckets() {
                return ((BucketOptions) this.instance).getExplicitBuckets();
            }

            public Builder setExplicitBuckets(Explicit value) {
                copyOnWrite();
                ((BucketOptions) this.instance).setExplicitBuckets(value);
                return this;
            }

            public Builder setExplicitBuckets(Explicit.Builder builderForValue) {
                copyOnWrite();
                ((BucketOptions) this.instance).setExplicitBuckets((Explicit) builderForValue.build());
                return this;
            }

            public Builder mergeExplicitBuckets(Explicit value) {
                copyOnWrite();
                ((BucketOptions) this.instance).mergeExplicitBuckets(value);
                return this;
            }

            public Builder clearExplicitBuckets() {
                copyOnWrite();
                ((BucketOptions) this.instance).clearExplicitBuckets();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C00151.f13xa1df5c61[method.ordinal()]) {
                case 1:
                    return new BucketOptions();
                case 2:
                    return new Builder((C00151) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000", new Object[]{"options_", "optionsCase_", Linear.class, Exponential.class, Explicit.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<BucketOptions> parser = PARSER;
                    if (parser == null) {
                        synchronized (BucketOptions.class) {
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
            BucketOptions defaultInstance = new BucketOptions();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(BucketOptions.class, defaultInstance);
        }

        public static BucketOptions getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BucketOptions> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Exemplar extends GeneratedMessageLite<Exemplar, Builder> implements ExemplarOrBuilder {
        public static final int ATTACHMENTS_FIELD_NUMBER = 3;
        /* access modifiers changed from: private */
        public static final Exemplar DEFAULT_INSTANCE;
        private static volatile Parser<Exemplar> PARSER = null;
        public static final int TIMESTAMP_FIELD_NUMBER = 2;
        public static final int VALUE_FIELD_NUMBER = 1;
        private Internal.ProtobufList<Any> attachments_ = emptyProtobufList();
        private Timestamp timestamp_;
        private double value_;

        private Exemplar() {
        }

        public double getValue() {
            return this.value_;
        }

        /* access modifiers changed from: private */
        public void setValue(double value) {
            this.value_ = value;
        }

        /* access modifiers changed from: private */
        public void clearValue() {
            this.value_ = 0.0d;
        }

        public boolean hasTimestamp() {
            return this.timestamp_ != null;
        }

        public Timestamp getTimestamp() {
            Timestamp timestamp = this.timestamp_;
            return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
        }

        /* access modifiers changed from: private */
        public void setTimestamp(Timestamp value) {
            value.getClass();
            this.timestamp_ = value;
        }

        /* access modifiers changed from: private */
        public void mergeTimestamp(Timestamp value) {
            value.getClass();
            Timestamp timestamp = this.timestamp_;
            if (timestamp == null || timestamp == Timestamp.getDefaultInstance()) {
                this.timestamp_ = value;
            } else {
                this.timestamp_ = (Timestamp) ((Timestamp.Builder) Timestamp.newBuilder(this.timestamp_).mergeFrom(value)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearTimestamp() {
            this.timestamp_ = null;
        }

        public List<Any> getAttachmentsList() {
            return this.attachments_;
        }

        public List<? extends AnyOrBuilder> getAttachmentsOrBuilderList() {
            return this.attachments_;
        }

        public int getAttachmentsCount() {
            return this.attachments_.size();
        }

        public Any getAttachments(int index) {
            return (Any) this.attachments_.get(index);
        }

        public AnyOrBuilder getAttachmentsOrBuilder(int index) {
            return (AnyOrBuilder) this.attachments_.get(index);
        }

        private void ensureAttachmentsIsMutable() {
            Internal.ProtobufList<Any> tmp = this.attachments_;
            if (!tmp.isModifiable()) {
                this.attachments_ = GeneratedMessageLite.mutableCopy(tmp);
            }
        }

        /* access modifiers changed from: private */
        public void setAttachments(int index, Any value) {
            value.getClass();
            ensureAttachmentsIsMutable();
            this.attachments_.set(index, value);
        }

        /* access modifiers changed from: private */
        public void addAttachments(Any value) {
            value.getClass();
            ensureAttachmentsIsMutable();
            this.attachments_.add(value);
        }

        /* access modifiers changed from: private */
        public void addAttachments(int index, Any value) {
            value.getClass();
            ensureAttachmentsIsMutable();
            this.attachments_.add(index, value);
        }

        /* access modifiers changed from: private */
        public void addAllAttachments(Iterable<? extends Any> values) {
            ensureAttachmentsIsMutable();
            AbstractMessageLite.addAll(values, this.attachments_);
        }

        /* access modifiers changed from: private */
        public void clearAttachments() {
            this.attachments_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeAttachments(int index) {
            ensureAttachmentsIsMutable();
            this.attachments_.remove(index);
        }

        public static Exemplar parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Exemplar) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Exemplar parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Exemplar) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Exemplar parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Exemplar) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Exemplar parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Exemplar) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Exemplar parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Exemplar) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Exemplar parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Exemplar) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Exemplar parseFrom(InputStream input) throws IOException {
            return (Exemplar) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Exemplar parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Exemplar) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Exemplar parseDelimitedFrom(InputStream input) throws IOException {
            return (Exemplar) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Exemplar parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Exemplar) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Exemplar parseFrom(CodedInputStream input) throws IOException {
            return (Exemplar) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Exemplar parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Exemplar) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Exemplar prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Exemplar, Builder> implements ExemplarOrBuilder {
            /* synthetic */ Builder(C00151 x0) {
                this();
            }

            private Builder() {
                super(Exemplar.DEFAULT_INSTANCE);
            }

            public double getValue() {
                return ((Exemplar) this.instance).getValue();
            }

            public Builder setValue(double value) {
                copyOnWrite();
                ((Exemplar) this.instance).setValue(value);
                return this;
            }

            public Builder clearValue() {
                copyOnWrite();
                ((Exemplar) this.instance).clearValue();
                return this;
            }

            public boolean hasTimestamp() {
                return ((Exemplar) this.instance).hasTimestamp();
            }

            public Timestamp getTimestamp() {
                return ((Exemplar) this.instance).getTimestamp();
            }

            public Builder setTimestamp(Timestamp value) {
                copyOnWrite();
                ((Exemplar) this.instance).setTimestamp(value);
                return this;
            }

            public Builder setTimestamp(Timestamp.Builder builderForValue) {
                copyOnWrite();
                ((Exemplar) this.instance).setTimestamp((Timestamp) builderForValue.build());
                return this;
            }

            public Builder mergeTimestamp(Timestamp value) {
                copyOnWrite();
                ((Exemplar) this.instance).mergeTimestamp(value);
                return this;
            }

            public Builder clearTimestamp() {
                copyOnWrite();
                ((Exemplar) this.instance).clearTimestamp();
                return this;
            }

            public List<Any> getAttachmentsList() {
                return Collections.unmodifiableList(((Exemplar) this.instance).getAttachmentsList());
            }

            public int getAttachmentsCount() {
                return ((Exemplar) this.instance).getAttachmentsCount();
            }

            public Any getAttachments(int index) {
                return ((Exemplar) this.instance).getAttachments(index);
            }

            public Builder setAttachments(int index, Any value) {
                copyOnWrite();
                ((Exemplar) this.instance).setAttachments(index, value);
                return this;
            }

            public Builder setAttachments(int index, Any.Builder builderForValue) {
                copyOnWrite();
                ((Exemplar) this.instance).setAttachments(index, (Any) builderForValue.build());
                return this;
            }

            public Builder addAttachments(Any value) {
                copyOnWrite();
                ((Exemplar) this.instance).addAttachments(value);
                return this;
            }

            public Builder addAttachments(int index, Any value) {
                copyOnWrite();
                ((Exemplar) this.instance).addAttachments(index, value);
                return this;
            }

            public Builder addAttachments(Any.Builder builderForValue) {
                copyOnWrite();
                ((Exemplar) this.instance).addAttachments((Any) builderForValue.build());
                return this;
            }

            public Builder addAttachments(int index, Any.Builder builderForValue) {
                copyOnWrite();
                ((Exemplar) this.instance).addAttachments(index, (Any) builderForValue.build());
                return this;
            }

            public Builder addAllAttachments(Iterable<? extends Any> values) {
                copyOnWrite();
                ((Exemplar) this.instance).addAllAttachments(values);
                return this;
            }

            public Builder clearAttachments() {
                copyOnWrite();
                ((Exemplar) this.instance).clearAttachments();
                return this;
            }

            public Builder removeAttachments(int index) {
                copyOnWrite();
                ((Exemplar) this.instance).removeAttachments(index);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C00151.f13xa1df5c61[method.ordinal()]) {
                case 1:
                    return new Exemplar();
                case 2:
                    return new Builder((C00151) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001\u0000\u0002\t\u0003\u001b", new Object[]{"value_", "timestamp_", "attachments_", Any.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Exemplar> parser = PARSER;
                    if (parser == null) {
                        synchronized (Exemplar.class) {
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
            Exemplar defaultInstance = new Exemplar();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Exemplar.class, defaultInstance);
        }

        public static Exemplar getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Exemplar> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public long getCount() {
        return this.count_;
    }

    /* access modifiers changed from: private */
    public void setCount(long value) {
        this.count_ = value;
    }

    /* access modifiers changed from: private */
    public void clearCount() {
        this.count_ = 0;
    }

    public double getMean() {
        return this.mean_;
    }

    /* access modifiers changed from: private */
    public void setMean(double value) {
        this.mean_ = value;
    }

    /* access modifiers changed from: private */
    public void clearMean() {
        this.mean_ = 0.0d;
    }

    public double getSumOfSquaredDeviation() {
        return this.sumOfSquaredDeviation_;
    }

    /* access modifiers changed from: private */
    public void setSumOfSquaredDeviation(double value) {
        this.sumOfSquaredDeviation_ = value;
    }

    /* access modifiers changed from: private */
    public void clearSumOfSquaredDeviation() {
        this.sumOfSquaredDeviation_ = 0.0d;
    }

    public boolean hasRange() {
        return this.range_ != null;
    }

    public Range getRange() {
        Range range = this.range_;
        return range == null ? Range.getDefaultInstance() : range;
    }

    /* access modifiers changed from: private */
    public void setRange(Range value) {
        value.getClass();
        this.range_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeRange(Range value) {
        value.getClass();
        Range range = this.range_;
        if (range == null || range == Range.getDefaultInstance()) {
            this.range_ = value;
        } else {
            this.range_ = (Range) ((Range.Builder) Range.newBuilder(this.range_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearRange() {
        this.range_ = null;
    }

    public boolean hasBucketOptions() {
        return this.bucketOptions_ != null;
    }

    public BucketOptions getBucketOptions() {
        BucketOptions bucketOptions = this.bucketOptions_;
        return bucketOptions == null ? BucketOptions.getDefaultInstance() : bucketOptions;
    }

    /* access modifiers changed from: private */
    public void setBucketOptions(BucketOptions value) {
        value.getClass();
        this.bucketOptions_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeBucketOptions(BucketOptions value) {
        value.getClass();
        BucketOptions bucketOptions = this.bucketOptions_;
        if (bucketOptions == null || bucketOptions == BucketOptions.getDefaultInstance()) {
            this.bucketOptions_ = value;
        } else {
            this.bucketOptions_ = (BucketOptions) ((BucketOptions.Builder) BucketOptions.newBuilder(this.bucketOptions_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearBucketOptions() {
        this.bucketOptions_ = null;
    }

    public List<Long> getBucketCountsList() {
        return this.bucketCounts_;
    }

    public int getBucketCountsCount() {
        return this.bucketCounts_.size();
    }

    public long getBucketCounts(int index) {
        return this.bucketCounts_.getLong(index);
    }

    private void ensureBucketCountsIsMutable() {
        Internal.LongList tmp = this.bucketCounts_;
        if (!tmp.isModifiable()) {
            this.bucketCounts_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setBucketCounts(int index, long value) {
        ensureBucketCountsIsMutable();
        this.bucketCounts_.setLong(index, value);
    }

    /* access modifiers changed from: private */
    public void addBucketCounts(long value) {
        ensureBucketCountsIsMutable();
        this.bucketCounts_.addLong(value);
    }

    /* access modifiers changed from: private */
    public void addAllBucketCounts(Iterable<? extends Long> values) {
        ensureBucketCountsIsMutable();
        AbstractMessageLite.addAll(values, this.bucketCounts_);
    }

    /* access modifiers changed from: private */
    public void clearBucketCounts() {
        this.bucketCounts_ = emptyLongList();
    }

    public List<Exemplar> getExemplarsList() {
        return this.exemplars_;
    }

    public List<? extends ExemplarOrBuilder> getExemplarsOrBuilderList() {
        return this.exemplars_;
    }

    public int getExemplarsCount() {
        return this.exemplars_.size();
    }

    public Exemplar getExemplars(int index) {
        return (Exemplar) this.exemplars_.get(index);
    }

    public ExemplarOrBuilder getExemplarsOrBuilder(int index) {
        return (ExemplarOrBuilder) this.exemplars_.get(index);
    }

    private void ensureExemplarsIsMutable() {
        Internal.ProtobufList<Exemplar> tmp = this.exemplars_;
        if (!tmp.isModifiable()) {
            this.exemplars_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setExemplars(int index, Exemplar value) {
        value.getClass();
        ensureExemplarsIsMutable();
        this.exemplars_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addExemplars(Exemplar value) {
        value.getClass();
        ensureExemplarsIsMutable();
        this.exemplars_.add(value);
    }

    /* access modifiers changed from: private */
    public void addExemplars(int index, Exemplar value) {
        value.getClass();
        ensureExemplarsIsMutable();
        this.exemplars_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllExemplars(Iterable<? extends Exemplar> values) {
        ensureExemplarsIsMutable();
        AbstractMessageLite.addAll(values, this.exemplars_);
    }

    /* access modifiers changed from: private */
    public void clearExemplars() {
        this.exemplars_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeExemplars(int index) {
        ensureExemplarsIsMutable();
        this.exemplars_.remove(index);
    }

    public static Distribution parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Distribution) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Distribution parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Distribution) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Distribution parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Distribution) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Distribution parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Distribution) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Distribution parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Distribution) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Distribution parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Distribution) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Distribution parseFrom(InputStream input) throws IOException {
        return (Distribution) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Distribution parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Distribution) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Distribution parseDelimitedFrom(InputStream input) throws IOException {
        return (Distribution) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Distribution parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Distribution) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Distribution parseFrom(CodedInputStream input) throws IOException {
        return (Distribution) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Distribution parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Distribution) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Distribution prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Distribution, Builder> implements DistributionOrBuilder {
        /* synthetic */ Builder(C00151 x0) {
            this();
        }

        private Builder() {
            super(Distribution.DEFAULT_INSTANCE);
        }

        public long getCount() {
            return ((Distribution) this.instance).getCount();
        }

        public Builder setCount(long value) {
            copyOnWrite();
            ((Distribution) this.instance).setCount(value);
            return this;
        }

        public Builder clearCount() {
            copyOnWrite();
            ((Distribution) this.instance).clearCount();
            return this;
        }

        public double getMean() {
            return ((Distribution) this.instance).getMean();
        }

        public Builder setMean(double value) {
            copyOnWrite();
            ((Distribution) this.instance).setMean(value);
            return this;
        }

        public Builder clearMean() {
            copyOnWrite();
            ((Distribution) this.instance).clearMean();
            return this;
        }

        public double getSumOfSquaredDeviation() {
            return ((Distribution) this.instance).getSumOfSquaredDeviation();
        }

        public Builder setSumOfSquaredDeviation(double value) {
            copyOnWrite();
            ((Distribution) this.instance).setSumOfSquaredDeviation(value);
            return this;
        }

        public Builder clearSumOfSquaredDeviation() {
            copyOnWrite();
            ((Distribution) this.instance).clearSumOfSquaredDeviation();
            return this;
        }

        public boolean hasRange() {
            return ((Distribution) this.instance).hasRange();
        }

        public Range getRange() {
            return ((Distribution) this.instance).getRange();
        }

        public Builder setRange(Range value) {
            copyOnWrite();
            ((Distribution) this.instance).setRange(value);
            return this;
        }

        public Builder setRange(Range.Builder builderForValue) {
            copyOnWrite();
            ((Distribution) this.instance).setRange((Range) builderForValue.build());
            return this;
        }

        public Builder mergeRange(Range value) {
            copyOnWrite();
            ((Distribution) this.instance).mergeRange(value);
            return this;
        }

        public Builder clearRange() {
            copyOnWrite();
            ((Distribution) this.instance).clearRange();
            return this;
        }

        public boolean hasBucketOptions() {
            return ((Distribution) this.instance).hasBucketOptions();
        }

        public BucketOptions getBucketOptions() {
            return ((Distribution) this.instance).getBucketOptions();
        }

        public Builder setBucketOptions(BucketOptions value) {
            copyOnWrite();
            ((Distribution) this.instance).setBucketOptions(value);
            return this;
        }

        public Builder setBucketOptions(BucketOptions.Builder builderForValue) {
            copyOnWrite();
            ((Distribution) this.instance).setBucketOptions((BucketOptions) builderForValue.build());
            return this;
        }

        public Builder mergeBucketOptions(BucketOptions value) {
            copyOnWrite();
            ((Distribution) this.instance).mergeBucketOptions(value);
            return this;
        }

        public Builder clearBucketOptions() {
            copyOnWrite();
            ((Distribution) this.instance).clearBucketOptions();
            return this;
        }

        public List<Long> getBucketCountsList() {
            return Collections.unmodifiableList(((Distribution) this.instance).getBucketCountsList());
        }

        public int getBucketCountsCount() {
            return ((Distribution) this.instance).getBucketCountsCount();
        }

        public long getBucketCounts(int index) {
            return ((Distribution) this.instance).getBucketCounts(index);
        }

        public Builder setBucketCounts(int index, long value) {
            copyOnWrite();
            ((Distribution) this.instance).setBucketCounts(index, value);
            return this;
        }

        public Builder addBucketCounts(long value) {
            copyOnWrite();
            ((Distribution) this.instance).addBucketCounts(value);
            return this;
        }

        public Builder addAllBucketCounts(Iterable<? extends Long> values) {
            copyOnWrite();
            ((Distribution) this.instance).addAllBucketCounts(values);
            return this;
        }

        public Builder clearBucketCounts() {
            copyOnWrite();
            ((Distribution) this.instance).clearBucketCounts();
            return this;
        }

        public List<Exemplar> getExemplarsList() {
            return Collections.unmodifiableList(((Distribution) this.instance).getExemplarsList());
        }

        public int getExemplarsCount() {
            return ((Distribution) this.instance).getExemplarsCount();
        }

        public Exemplar getExemplars(int index) {
            return ((Distribution) this.instance).getExemplars(index);
        }

        public Builder setExemplars(int index, Exemplar value) {
            copyOnWrite();
            ((Distribution) this.instance).setExemplars(index, value);
            return this;
        }

        public Builder setExemplars(int index, Exemplar.Builder builderForValue) {
            copyOnWrite();
            ((Distribution) this.instance).setExemplars(index, (Exemplar) builderForValue.build());
            return this;
        }

        public Builder addExemplars(Exemplar value) {
            copyOnWrite();
            ((Distribution) this.instance).addExemplars(value);
            return this;
        }

        public Builder addExemplars(int index, Exemplar value) {
            copyOnWrite();
            ((Distribution) this.instance).addExemplars(index, value);
            return this;
        }

        public Builder addExemplars(Exemplar.Builder builderForValue) {
            copyOnWrite();
            ((Distribution) this.instance).addExemplars((Exemplar) builderForValue.build());
            return this;
        }

        public Builder addExemplars(int index, Exemplar.Builder builderForValue) {
            copyOnWrite();
            ((Distribution) this.instance).addExemplars(index, (Exemplar) builderForValue.build());
            return this;
        }

        public Builder addAllExemplars(Iterable<? extends Exemplar> values) {
            copyOnWrite();
            ((Distribution) this.instance).addAllExemplars(values);
            return this;
        }

        public Builder clearExemplars() {
            copyOnWrite();
            ((Distribution) this.instance).clearExemplars();
            return this;
        }

        public Builder removeExemplars(int index) {
            copyOnWrite();
            ((Distribution) this.instance).removeExemplars(index);
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00151.f13xa1df5c61[method.ordinal()]) {
            case 1:
                return new Distribution();
            case 2:
                return new Builder((C00151) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0007\u0000\u0000\u0001\n\u0007\u0000\u0002\u0000\u0001\u0002\u0002\u0000\u0003\u0000\u0004\t\u0006\t\u0007%\n\u001b", new Object[]{"count_", "mean_", "sumOfSquaredDeviation_", "range_", "bucketOptions_", "bucketCounts_", "exemplars_", Exemplar.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Distribution> parser = PARSER;
                if (parser == null) {
                    synchronized (Distribution.class) {
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
        Distribution defaultInstance = new Distribution();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Distribution.class, defaultInstance);
    }

    public static Distribution getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Distribution> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
