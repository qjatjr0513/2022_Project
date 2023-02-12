package com.google.api;

import com.google.api.Advice;
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

public final class ConfigChange extends GeneratedMessageLite<ConfigChange, Builder> implements ConfigChangeOrBuilder {
    public static final int ADVICES_FIELD_NUMBER = 5;
    public static final int CHANGE_TYPE_FIELD_NUMBER = 4;
    /* access modifiers changed from: private */
    public static final ConfigChange DEFAULT_INSTANCE;
    public static final int ELEMENT_FIELD_NUMBER = 1;
    public static final int NEW_VALUE_FIELD_NUMBER = 3;
    public static final int OLD_VALUE_FIELD_NUMBER = 2;
    private static volatile Parser<ConfigChange> PARSER;
    private Internal.ProtobufList<Advice> advices_ = emptyProtobufList();
    private int changeType_;
    private String element_ = "";
    private String newValue_ = "";
    private String oldValue_ = "";

    private ConfigChange() {
    }

    public String getElement() {
        return this.element_;
    }

    public ByteString getElementBytes() {
        return ByteString.copyFromUtf8(this.element_);
    }

    /* access modifiers changed from: private */
    public void setElement(String value) {
        value.getClass();
        this.element_ = value;
    }

    /* access modifiers changed from: private */
    public void clearElement() {
        this.element_ = getDefaultInstance().getElement();
    }

    /* access modifiers changed from: private */
    public void setElementBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.element_ = value.toStringUtf8();
    }

    public String getOldValue() {
        return this.oldValue_;
    }

    public ByteString getOldValueBytes() {
        return ByteString.copyFromUtf8(this.oldValue_);
    }

    /* access modifiers changed from: private */
    public void setOldValue(String value) {
        value.getClass();
        this.oldValue_ = value;
    }

    /* access modifiers changed from: private */
    public void clearOldValue() {
        this.oldValue_ = getDefaultInstance().getOldValue();
    }

    /* access modifiers changed from: private */
    public void setOldValueBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.oldValue_ = value.toStringUtf8();
    }

    public String getNewValue() {
        return this.newValue_;
    }

    public ByteString getNewValueBytes() {
        return ByteString.copyFromUtf8(this.newValue_);
    }

    /* access modifiers changed from: private */
    public void setNewValue(String value) {
        value.getClass();
        this.newValue_ = value;
    }

    /* access modifiers changed from: private */
    public void clearNewValue() {
        this.newValue_ = getDefaultInstance().getNewValue();
    }

    /* access modifiers changed from: private */
    public void setNewValueBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.newValue_ = value.toStringUtf8();
    }

    public int getChangeTypeValue() {
        return this.changeType_;
    }

    public ChangeType getChangeType() {
        ChangeType result = ChangeType.forNumber(this.changeType_);
        return result == null ? ChangeType.UNRECOGNIZED : result;
    }

    /* access modifiers changed from: private */
    public void setChangeTypeValue(int value) {
        this.changeType_ = value;
    }

    /* access modifiers changed from: private */
    public void setChangeType(ChangeType value) {
        this.changeType_ = value.getNumber();
    }

    /* access modifiers changed from: private */
    public void clearChangeType() {
        this.changeType_ = 0;
    }

    public List<Advice> getAdvicesList() {
        return this.advices_;
    }

    public List<? extends AdviceOrBuilder> getAdvicesOrBuilderList() {
        return this.advices_;
    }

    public int getAdvicesCount() {
        return this.advices_.size();
    }

    public Advice getAdvices(int index) {
        return (Advice) this.advices_.get(index);
    }

    public AdviceOrBuilder getAdvicesOrBuilder(int index) {
        return (AdviceOrBuilder) this.advices_.get(index);
    }

    private void ensureAdvicesIsMutable() {
        Internal.ProtobufList<Advice> tmp = this.advices_;
        if (!tmp.isModifiable()) {
            this.advices_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setAdvices(int index, Advice value) {
        value.getClass();
        ensureAdvicesIsMutable();
        this.advices_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addAdvices(Advice value) {
        value.getClass();
        ensureAdvicesIsMutable();
        this.advices_.add(value);
    }

    /* access modifiers changed from: private */
    public void addAdvices(int index, Advice value) {
        value.getClass();
        ensureAdvicesIsMutable();
        this.advices_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllAdvices(Iterable<? extends Advice> values) {
        ensureAdvicesIsMutable();
        AbstractMessageLite.addAll(values, this.advices_);
    }

    /* access modifiers changed from: private */
    public void clearAdvices() {
        this.advices_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeAdvices(int index) {
        ensureAdvicesIsMutable();
        this.advices_.remove(index);
    }

    public static ConfigChange parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ConfigChange parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ConfigChange parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ConfigChange parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ConfigChange parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ConfigChange parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ConfigChange parseFrom(InputStream input) throws IOException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ConfigChange parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ConfigChange parseDelimitedFrom(InputStream input) throws IOException {
        return (ConfigChange) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ConfigChange parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConfigChange) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ConfigChange parseFrom(CodedInputStream input) throws IOException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ConfigChange parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ConfigChange prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ConfigChange, Builder> implements ConfigChangeOrBuilder {
        /* synthetic */ Builder(C00101 x0) {
            this();
        }

        private Builder() {
            super(ConfigChange.DEFAULT_INSTANCE);
        }

        public String getElement() {
            return ((ConfigChange) this.instance).getElement();
        }

        public ByteString getElementBytes() {
            return ((ConfigChange) this.instance).getElementBytes();
        }

        public Builder setElement(String value) {
            copyOnWrite();
            ((ConfigChange) this.instance).setElement(value);
            return this;
        }

        public Builder clearElement() {
            copyOnWrite();
            ((ConfigChange) this.instance).clearElement();
            return this;
        }

        public Builder setElementBytes(ByteString value) {
            copyOnWrite();
            ((ConfigChange) this.instance).setElementBytes(value);
            return this;
        }

        public String getOldValue() {
            return ((ConfigChange) this.instance).getOldValue();
        }

        public ByteString getOldValueBytes() {
            return ((ConfigChange) this.instance).getOldValueBytes();
        }

        public Builder setOldValue(String value) {
            copyOnWrite();
            ((ConfigChange) this.instance).setOldValue(value);
            return this;
        }

        public Builder clearOldValue() {
            copyOnWrite();
            ((ConfigChange) this.instance).clearOldValue();
            return this;
        }

        public Builder setOldValueBytes(ByteString value) {
            copyOnWrite();
            ((ConfigChange) this.instance).setOldValueBytes(value);
            return this;
        }

        public String getNewValue() {
            return ((ConfigChange) this.instance).getNewValue();
        }

        public ByteString getNewValueBytes() {
            return ((ConfigChange) this.instance).getNewValueBytes();
        }

        public Builder setNewValue(String value) {
            copyOnWrite();
            ((ConfigChange) this.instance).setNewValue(value);
            return this;
        }

        public Builder clearNewValue() {
            copyOnWrite();
            ((ConfigChange) this.instance).clearNewValue();
            return this;
        }

        public Builder setNewValueBytes(ByteString value) {
            copyOnWrite();
            ((ConfigChange) this.instance).setNewValueBytes(value);
            return this;
        }

        public int getChangeTypeValue() {
            return ((ConfigChange) this.instance).getChangeTypeValue();
        }

        public Builder setChangeTypeValue(int value) {
            copyOnWrite();
            ((ConfigChange) this.instance).setChangeTypeValue(value);
            return this;
        }

        public ChangeType getChangeType() {
            return ((ConfigChange) this.instance).getChangeType();
        }

        public Builder setChangeType(ChangeType value) {
            copyOnWrite();
            ((ConfigChange) this.instance).setChangeType(value);
            return this;
        }

        public Builder clearChangeType() {
            copyOnWrite();
            ((ConfigChange) this.instance).clearChangeType();
            return this;
        }

        public List<Advice> getAdvicesList() {
            return Collections.unmodifiableList(((ConfigChange) this.instance).getAdvicesList());
        }

        public int getAdvicesCount() {
            return ((ConfigChange) this.instance).getAdvicesCount();
        }

        public Advice getAdvices(int index) {
            return ((ConfigChange) this.instance).getAdvices(index);
        }

        public Builder setAdvices(int index, Advice value) {
            copyOnWrite();
            ((ConfigChange) this.instance).setAdvices(index, value);
            return this;
        }

        public Builder setAdvices(int index, Advice.Builder builderForValue) {
            copyOnWrite();
            ((ConfigChange) this.instance).setAdvices(index, (Advice) builderForValue.build());
            return this;
        }

        public Builder addAdvices(Advice value) {
            copyOnWrite();
            ((ConfigChange) this.instance).addAdvices(value);
            return this;
        }

        public Builder addAdvices(int index, Advice value) {
            copyOnWrite();
            ((ConfigChange) this.instance).addAdvices(index, value);
            return this;
        }

        public Builder addAdvices(Advice.Builder builderForValue) {
            copyOnWrite();
            ((ConfigChange) this.instance).addAdvices((Advice) builderForValue.build());
            return this;
        }

        public Builder addAdvices(int index, Advice.Builder builderForValue) {
            copyOnWrite();
            ((ConfigChange) this.instance).addAdvices(index, (Advice) builderForValue.build());
            return this;
        }

        public Builder addAllAdvices(Iterable<? extends Advice> values) {
            copyOnWrite();
            ((ConfigChange) this.instance).addAllAdvices(values);
            return this;
        }

        public Builder clearAdvices() {
            copyOnWrite();
            ((ConfigChange) this.instance).clearAdvices();
            return this;
        }

        public Builder removeAdvices(int index) {
            copyOnWrite();
            ((ConfigChange) this.instance).removeAdvices(index);
            return this;
        }
    }

    /* renamed from: com.google.api.ConfigChange$1 */
    static /* synthetic */ class C00101 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f8xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f8xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f8xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f8xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f8xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f8xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f8xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00101.f8xa1df5c61[method.ordinal()]) {
            case 1:
                return new ConfigChange();
            case 2:
                return new Builder((C00101) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0001\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004\f\u0005\u001b", new Object[]{"element_", "oldValue_", "newValue_", "changeType_", "advices_", Advice.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ConfigChange> parser = PARSER;
                if (parser == null) {
                    synchronized (ConfigChange.class) {
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
        ConfigChange defaultInstance = new ConfigChange();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(ConfigChange.class, defaultInstance);
    }

    public static ConfigChange getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ConfigChange> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
