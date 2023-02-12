package com.google.api;

import com.google.api.MetricRule;
import com.google.api.QuotaLimit;
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

public final class Quota extends GeneratedMessageLite<Quota, Builder> implements QuotaOrBuilder {
    /* access modifiers changed from: private */
    public static final Quota DEFAULT_INSTANCE;
    public static final int LIMITS_FIELD_NUMBER = 3;
    public static final int METRIC_RULES_FIELD_NUMBER = 4;
    private static volatile Parser<Quota> PARSER;
    private Internal.ProtobufList<QuotaLimit> limits_ = emptyProtobufList();
    private Internal.ProtobufList<MetricRule> metricRules_ = emptyProtobufList();

    private Quota() {
    }

    public List<QuotaLimit> getLimitsList() {
        return this.limits_;
    }

    public List<? extends QuotaLimitOrBuilder> getLimitsOrBuilderList() {
        return this.limits_;
    }

    public int getLimitsCount() {
        return this.limits_.size();
    }

    public QuotaLimit getLimits(int index) {
        return (QuotaLimit) this.limits_.get(index);
    }

    public QuotaLimitOrBuilder getLimitsOrBuilder(int index) {
        return (QuotaLimitOrBuilder) this.limits_.get(index);
    }

    private void ensureLimitsIsMutable() {
        Internal.ProtobufList<QuotaLimit> tmp = this.limits_;
        if (!tmp.isModifiable()) {
            this.limits_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setLimits(int index, QuotaLimit value) {
        value.getClass();
        ensureLimitsIsMutable();
        this.limits_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addLimits(QuotaLimit value) {
        value.getClass();
        ensureLimitsIsMutable();
        this.limits_.add(value);
    }

    /* access modifiers changed from: private */
    public void addLimits(int index, QuotaLimit value) {
        value.getClass();
        ensureLimitsIsMutable();
        this.limits_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllLimits(Iterable<? extends QuotaLimit> values) {
        ensureLimitsIsMutable();
        AbstractMessageLite.addAll(values, this.limits_);
    }

    /* access modifiers changed from: private */
    public void clearLimits() {
        this.limits_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeLimits(int index) {
        ensureLimitsIsMutable();
        this.limits_.remove(index);
    }

    public List<MetricRule> getMetricRulesList() {
        return this.metricRules_;
    }

    public List<? extends MetricRuleOrBuilder> getMetricRulesOrBuilderList() {
        return this.metricRules_;
    }

    public int getMetricRulesCount() {
        return this.metricRules_.size();
    }

    public MetricRule getMetricRules(int index) {
        return (MetricRule) this.metricRules_.get(index);
    }

    public MetricRuleOrBuilder getMetricRulesOrBuilder(int index) {
        return (MetricRuleOrBuilder) this.metricRules_.get(index);
    }

    private void ensureMetricRulesIsMutable() {
        Internal.ProtobufList<MetricRule> tmp = this.metricRules_;
        if (!tmp.isModifiable()) {
            this.metricRules_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setMetricRules(int index, MetricRule value) {
        value.getClass();
        ensureMetricRulesIsMutable();
        this.metricRules_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addMetricRules(MetricRule value) {
        value.getClass();
        ensureMetricRulesIsMutable();
        this.metricRules_.add(value);
    }

    /* access modifiers changed from: private */
    public void addMetricRules(int index, MetricRule value) {
        value.getClass();
        ensureMetricRulesIsMutable();
        this.metricRules_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllMetricRules(Iterable<? extends MetricRule> values) {
        ensureMetricRulesIsMutable();
        AbstractMessageLite.addAll(values, this.metricRules_);
    }

    /* access modifiers changed from: private */
    public void clearMetricRules() {
        this.metricRules_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeMetricRules(int index) {
        ensureMetricRulesIsMutable();
        this.metricRules_.remove(index);
    }

    public static Quota parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Quota) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Quota parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Quota) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Quota parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Quota) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Quota parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Quota) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Quota parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Quota) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Quota parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Quota) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Quota parseFrom(InputStream input) throws IOException {
        return (Quota) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Quota parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Quota) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Quota parseDelimitedFrom(InputStream input) throws IOException {
        return (Quota) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Quota parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Quota) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Quota parseFrom(CodedInputStream input) throws IOException {
        return (Quota) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Quota parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Quota) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Quota prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Quota, Builder> implements QuotaOrBuilder {
        /* synthetic */ Builder(C00431 x0) {
            this();
        }

        private Builder() {
            super(Quota.DEFAULT_INSTANCE);
        }

        public List<QuotaLimit> getLimitsList() {
            return Collections.unmodifiableList(((Quota) this.instance).getLimitsList());
        }

        public int getLimitsCount() {
            return ((Quota) this.instance).getLimitsCount();
        }

        public QuotaLimit getLimits(int index) {
            return ((Quota) this.instance).getLimits(index);
        }

        public Builder setLimits(int index, QuotaLimit value) {
            copyOnWrite();
            ((Quota) this.instance).setLimits(index, value);
            return this;
        }

        public Builder setLimits(int index, QuotaLimit.Builder builderForValue) {
            copyOnWrite();
            ((Quota) this.instance).setLimits(index, (QuotaLimit) builderForValue.build());
            return this;
        }

        public Builder addLimits(QuotaLimit value) {
            copyOnWrite();
            ((Quota) this.instance).addLimits(value);
            return this;
        }

        public Builder addLimits(int index, QuotaLimit value) {
            copyOnWrite();
            ((Quota) this.instance).addLimits(index, value);
            return this;
        }

        public Builder addLimits(QuotaLimit.Builder builderForValue) {
            copyOnWrite();
            ((Quota) this.instance).addLimits((QuotaLimit) builderForValue.build());
            return this;
        }

        public Builder addLimits(int index, QuotaLimit.Builder builderForValue) {
            copyOnWrite();
            ((Quota) this.instance).addLimits(index, (QuotaLimit) builderForValue.build());
            return this;
        }

        public Builder addAllLimits(Iterable<? extends QuotaLimit> values) {
            copyOnWrite();
            ((Quota) this.instance).addAllLimits(values);
            return this;
        }

        public Builder clearLimits() {
            copyOnWrite();
            ((Quota) this.instance).clearLimits();
            return this;
        }

        public Builder removeLimits(int index) {
            copyOnWrite();
            ((Quota) this.instance).removeLimits(index);
            return this;
        }

        public List<MetricRule> getMetricRulesList() {
            return Collections.unmodifiableList(((Quota) this.instance).getMetricRulesList());
        }

        public int getMetricRulesCount() {
            return ((Quota) this.instance).getMetricRulesCount();
        }

        public MetricRule getMetricRules(int index) {
            return ((Quota) this.instance).getMetricRules(index);
        }

        public Builder setMetricRules(int index, MetricRule value) {
            copyOnWrite();
            ((Quota) this.instance).setMetricRules(index, value);
            return this;
        }

        public Builder setMetricRules(int index, MetricRule.Builder builderForValue) {
            copyOnWrite();
            ((Quota) this.instance).setMetricRules(index, (MetricRule) builderForValue.build());
            return this;
        }

        public Builder addMetricRules(MetricRule value) {
            copyOnWrite();
            ((Quota) this.instance).addMetricRules(value);
            return this;
        }

        public Builder addMetricRules(int index, MetricRule value) {
            copyOnWrite();
            ((Quota) this.instance).addMetricRules(index, value);
            return this;
        }

        public Builder addMetricRules(MetricRule.Builder builderForValue) {
            copyOnWrite();
            ((Quota) this.instance).addMetricRules((MetricRule) builderForValue.build());
            return this;
        }

        public Builder addMetricRules(int index, MetricRule.Builder builderForValue) {
            copyOnWrite();
            ((Quota) this.instance).addMetricRules(index, (MetricRule) builderForValue.build());
            return this;
        }

        public Builder addAllMetricRules(Iterable<? extends MetricRule> values) {
            copyOnWrite();
            ((Quota) this.instance).addAllMetricRules(values);
            return this;
        }

        public Builder clearMetricRules() {
            copyOnWrite();
            ((Quota) this.instance).clearMetricRules();
            return this;
        }

        public Builder removeMetricRules(int index) {
            copyOnWrite();
            ((Quota) this.instance).removeMetricRules(index);
            return this;
        }
    }

    /* renamed from: com.google.api.Quota$1 */
    static /* synthetic */ class C00431 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f36xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f36xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f36xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f36xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f36xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f36xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f36xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f36xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00431.f36xa1df5c61[method.ordinal()]) {
            case 1:
                return new Quota();
            case 2:
                return new Builder((C00431) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0003\u0004\u0002\u0000\u0002\u0000\u0003\u001b\u0004\u001b", new Object[]{"limits_", QuotaLimit.class, "metricRules_", MetricRule.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Quota> parser = PARSER;
                if (parser == null) {
                    synchronized (Quota.class) {
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
        Quota defaultInstance = new Quota();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Quota.class, defaultInstance);
    }

    public static Quota getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Quota> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
