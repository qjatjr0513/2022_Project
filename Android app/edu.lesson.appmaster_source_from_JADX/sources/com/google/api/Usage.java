package com.google.api;

import com.google.api.UsageRule;
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

public final class Usage extends GeneratedMessageLite<Usage, Builder> implements UsageOrBuilder {
    /* access modifiers changed from: private */
    public static final Usage DEFAULT_INSTANCE;
    private static volatile Parser<Usage> PARSER = null;
    public static final int PRODUCER_NOTIFICATION_CHANNEL_FIELD_NUMBER = 7;
    public static final int REQUIREMENTS_FIELD_NUMBER = 1;
    public static final int RULES_FIELD_NUMBER = 6;
    private String producerNotificationChannel_ = "";
    private Internal.ProtobufList<String> requirements_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList<UsageRule> rules_ = emptyProtobufList();

    private Usage() {
    }

    public List<String> getRequirementsList() {
        return this.requirements_;
    }

    public int getRequirementsCount() {
        return this.requirements_.size();
    }

    public String getRequirements(int index) {
        return (String) this.requirements_.get(index);
    }

    public ByteString getRequirementsBytes(int index) {
        return ByteString.copyFromUtf8((String) this.requirements_.get(index));
    }

    private void ensureRequirementsIsMutable() {
        Internal.ProtobufList<String> tmp = this.requirements_;
        if (!tmp.isModifiable()) {
            this.requirements_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setRequirements(int index, String value) {
        value.getClass();
        ensureRequirementsIsMutable();
        this.requirements_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addRequirements(String value) {
        value.getClass();
        ensureRequirementsIsMutable();
        this.requirements_.add(value);
    }

    /* access modifiers changed from: private */
    public void addAllRequirements(Iterable<String> values) {
        ensureRequirementsIsMutable();
        AbstractMessageLite.addAll(values, this.requirements_);
    }

    /* access modifiers changed from: private */
    public void clearRequirements() {
        this.requirements_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void addRequirementsBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        ensureRequirementsIsMutable();
        this.requirements_.add(value.toStringUtf8());
    }

    public List<UsageRule> getRulesList() {
        return this.rules_;
    }

    public List<? extends UsageRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    public int getRulesCount() {
        return this.rules_.size();
    }

    public UsageRule getRules(int index) {
        return (UsageRule) this.rules_.get(index);
    }

    public UsageRuleOrBuilder getRulesOrBuilder(int index) {
        return (UsageRuleOrBuilder) this.rules_.get(index);
    }

    private void ensureRulesIsMutable() {
        Internal.ProtobufList<UsageRule> tmp = this.rules_;
        if (!tmp.isModifiable()) {
            this.rules_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setRules(int index, UsageRule value) {
        value.getClass();
        ensureRulesIsMutable();
        this.rules_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addRules(UsageRule value) {
        value.getClass();
        ensureRulesIsMutable();
        this.rules_.add(value);
    }

    /* access modifiers changed from: private */
    public void addRules(int index, UsageRule value) {
        value.getClass();
        ensureRulesIsMutable();
        this.rules_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllRules(Iterable<? extends UsageRule> values) {
        ensureRulesIsMutable();
        AbstractMessageLite.addAll(values, this.rules_);
    }

    /* access modifiers changed from: private */
    public void clearRules() {
        this.rules_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeRules(int index) {
        ensureRulesIsMutable();
        this.rules_.remove(index);
    }

    public String getProducerNotificationChannel() {
        return this.producerNotificationChannel_;
    }

    public ByteString getProducerNotificationChannelBytes() {
        return ByteString.copyFromUtf8(this.producerNotificationChannel_);
    }

    /* access modifiers changed from: private */
    public void setProducerNotificationChannel(String value) {
        value.getClass();
        this.producerNotificationChannel_ = value;
    }

    /* access modifiers changed from: private */
    public void clearProducerNotificationChannel() {
        this.producerNotificationChannel_ = getDefaultInstance().getProducerNotificationChannel();
    }

    /* access modifiers changed from: private */
    public void setProducerNotificationChannelBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.producerNotificationChannel_ = value.toStringUtf8();
    }

    public static Usage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Usage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Usage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Usage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Usage parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Usage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Usage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Usage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Usage parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Usage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Usage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Usage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Usage parseFrom(InputStream input) throws IOException {
        return (Usage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Usage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Usage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Usage parseDelimitedFrom(InputStream input) throws IOException {
        return (Usage) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Usage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Usage) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Usage parseFrom(CodedInputStream input) throws IOException {
        return (Usage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Usage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Usage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Usage prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Usage, Builder> implements UsageOrBuilder {
        /* synthetic */ Builder(C00531 x0) {
            this();
        }

        private Builder() {
            super(Usage.DEFAULT_INSTANCE);
        }

        public List<String> getRequirementsList() {
            return Collections.unmodifiableList(((Usage) this.instance).getRequirementsList());
        }

        public int getRequirementsCount() {
            return ((Usage) this.instance).getRequirementsCount();
        }

        public String getRequirements(int index) {
            return ((Usage) this.instance).getRequirements(index);
        }

        public ByteString getRequirementsBytes(int index) {
            return ((Usage) this.instance).getRequirementsBytes(index);
        }

        public Builder setRequirements(int index, String value) {
            copyOnWrite();
            ((Usage) this.instance).setRequirements(index, value);
            return this;
        }

        public Builder addRequirements(String value) {
            copyOnWrite();
            ((Usage) this.instance).addRequirements(value);
            return this;
        }

        public Builder addAllRequirements(Iterable<String> values) {
            copyOnWrite();
            ((Usage) this.instance).addAllRequirements(values);
            return this;
        }

        public Builder clearRequirements() {
            copyOnWrite();
            ((Usage) this.instance).clearRequirements();
            return this;
        }

        public Builder addRequirementsBytes(ByteString value) {
            copyOnWrite();
            ((Usage) this.instance).addRequirementsBytes(value);
            return this;
        }

        public List<UsageRule> getRulesList() {
            return Collections.unmodifiableList(((Usage) this.instance).getRulesList());
        }

        public int getRulesCount() {
            return ((Usage) this.instance).getRulesCount();
        }

        public UsageRule getRules(int index) {
            return ((Usage) this.instance).getRules(index);
        }

        public Builder setRules(int index, UsageRule value) {
            copyOnWrite();
            ((Usage) this.instance).setRules(index, value);
            return this;
        }

        public Builder setRules(int index, UsageRule.Builder builderForValue) {
            copyOnWrite();
            ((Usage) this.instance).setRules(index, (UsageRule) builderForValue.build());
            return this;
        }

        public Builder addRules(UsageRule value) {
            copyOnWrite();
            ((Usage) this.instance).addRules(value);
            return this;
        }

        public Builder addRules(int index, UsageRule value) {
            copyOnWrite();
            ((Usage) this.instance).addRules(index, value);
            return this;
        }

        public Builder addRules(UsageRule.Builder builderForValue) {
            copyOnWrite();
            ((Usage) this.instance).addRules((UsageRule) builderForValue.build());
            return this;
        }

        public Builder addRules(int index, UsageRule.Builder builderForValue) {
            copyOnWrite();
            ((Usage) this.instance).addRules(index, (UsageRule) builderForValue.build());
            return this;
        }

        public Builder addAllRules(Iterable<? extends UsageRule> values) {
            copyOnWrite();
            ((Usage) this.instance).addAllRules(values);
            return this;
        }

        public Builder clearRules() {
            copyOnWrite();
            ((Usage) this.instance).clearRules();
            return this;
        }

        public Builder removeRules(int index) {
            copyOnWrite();
            ((Usage) this.instance).removeRules(index);
            return this;
        }

        public String getProducerNotificationChannel() {
            return ((Usage) this.instance).getProducerNotificationChannel();
        }

        public ByteString getProducerNotificationChannelBytes() {
            return ((Usage) this.instance).getProducerNotificationChannelBytes();
        }

        public Builder setProducerNotificationChannel(String value) {
            copyOnWrite();
            ((Usage) this.instance).setProducerNotificationChannel(value);
            return this;
        }

        public Builder clearProducerNotificationChannel() {
            copyOnWrite();
            ((Usage) this.instance).clearProducerNotificationChannel();
            return this;
        }

        public Builder setProducerNotificationChannelBytes(ByteString value) {
            copyOnWrite();
            ((Usage) this.instance).setProducerNotificationChannelBytes(value);
            return this;
        }
    }

    /* renamed from: com.google.api.Usage$1 */
    static /* synthetic */ class C00531 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f45xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f45xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f45xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f45xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f45xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f45xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f45xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f45xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00531.f45xa1df5c61[method.ordinal()]) {
            case 1:
                return new Usage();
            case 2:
                return new Builder((C00531) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0007\u0003\u0000\u0002\u0000\u0001Ț\u0006\u001b\u0007Ȉ", new Object[]{"requirements_", "rules_", UsageRule.class, "producerNotificationChannel_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Usage> parser = PARSER;
                if (parser == null) {
                    synchronized (Usage.class) {
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
        Usage defaultInstance = new Usage();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Usage.class, defaultInstance);
    }

    public static Usage getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Usage> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
