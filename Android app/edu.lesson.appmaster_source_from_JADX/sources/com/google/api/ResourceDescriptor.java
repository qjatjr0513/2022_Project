package com.google.api;

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

public final class ResourceDescriptor extends GeneratedMessageLite<ResourceDescriptor, Builder> implements ResourceDescriptorOrBuilder {
    /* access modifiers changed from: private */
    public static final ResourceDescriptor DEFAULT_INSTANCE;
    public static final int HISTORY_FIELD_NUMBER = 4;
    public static final int NAME_FIELD_FIELD_NUMBER = 3;
    private static volatile Parser<ResourceDescriptor> PARSER = null;
    public static final int PATTERN_FIELD_NUMBER = 2;
    public static final int PLURAL_FIELD_NUMBER = 5;
    public static final int SINGULAR_FIELD_NUMBER = 6;
    public static final int TYPE_FIELD_NUMBER = 1;
    private int history_;
    private String nameField_ = "";
    private Internal.ProtobufList<String> pattern_ = GeneratedMessageLite.emptyProtobufList();
    private String plural_ = "";
    private String singular_ = "";
    private String type_ = "";

    private ResourceDescriptor() {
    }

    public enum History implements Internal.EnumLite {
        HISTORY_UNSPECIFIED(0),
        ORIGINALLY_SINGLE_PATTERN(1),
        FUTURE_MULTI_PATTERN(2),
        UNRECOGNIZED(-1);
        
        public static final int FUTURE_MULTI_PATTERN_VALUE = 2;
        public static final int HISTORY_UNSPECIFIED_VALUE = 0;
        public static final int ORIGINALLY_SINGLE_PATTERN_VALUE = 1;
        private static final Internal.EnumLiteMap<History> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<History>() {
                public History findValueByNumber(int number) {
                    return History.forNumber(number);
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
        public static History valueOf(int value2) {
            return forNumber(value2);
        }

        public static History forNumber(int value2) {
            switch (value2) {
                case 0:
                    return HISTORY_UNSPECIFIED;
                case 1:
                    return ORIGINALLY_SINGLE_PATTERN;
                case 2:
                    return FUTURE_MULTI_PATTERN;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<History> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return HistoryVerifier.INSTANCE;
        }

        private static final class HistoryVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = null;

            private HistoryVerifier() {
            }

            static {
                INSTANCE = new HistoryVerifier();
            }

            public boolean isInRange(int number) {
                return History.forNumber(number) != null;
            }
        }

        private History(int value2) {
            this.value = value2;
        }
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

    public List<String> getPatternList() {
        return this.pattern_;
    }

    public int getPatternCount() {
        return this.pattern_.size();
    }

    public String getPattern(int index) {
        return (String) this.pattern_.get(index);
    }

    public ByteString getPatternBytes(int index) {
        return ByteString.copyFromUtf8((String) this.pattern_.get(index));
    }

    private void ensurePatternIsMutable() {
        Internal.ProtobufList<String> tmp = this.pattern_;
        if (!tmp.isModifiable()) {
            this.pattern_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setPattern(int index, String value) {
        value.getClass();
        ensurePatternIsMutable();
        this.pattern_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addPattern(String value) {
        value.getClass();
        ensurePatternIsMutable();
        this.pattern_.add(value);
    }

    /* access modifiers changed from: private */
    public void addAllPattern(Iterable<String> values) {
        ensurePatternIsMutable();
        AbstractMessageLite.addAll(values, this.pattern_);
    }

    /* access modifiers changed from: private */
    public void clearPattern() {
        this.pattern_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void addPatternBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        ensurePatternIsMutable();
        this.pattern_.add(value.toStringUtf8());
    }

    public String getNameField() {
        return this.nameField_;
    }

    public ByteString getNameFieldBytes() {
        return ByteString.copyFromUtf8(this.nameField_);
    }

    /* access modifiers changed from: private */
    public void setNameField(String value) {
        value.getClass();
        this.nameField_ = value;
    }

    /* access modifiers changed from: private */
    public void clearNameField() {
        this.nameField_ = getDefaultInstance().getNameField();
    }

    /* access modifiers changed from: private */
    public void setNameFieldBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.nameField_ = value.toStringUtf8();
    }

    public int getHistoryValue() {
        return this.history_;
    }

    public History getHistory() {
        History result = History.forNumber(this.history_);
        return result == null ? History.UNRECOGNIZED : result;
    }

    /* access modifiers changed from: private */
    public void setHistoryValue(int value) {
        this.history_ = value;
    }

    /* access modifiers changed from: private */
    public void setHistory(History value) {
        this.history_ = value.getNumber();
    }

    /* access modifiers changed from: private */
    public void clearHistory() {
        this.history_ = 0;
    }

    public String getPlural() {
        return this.plural_;
    }

    public ByteString getPluralBytes() {
        return ByteString.copyFromUtf8(this.plural_);
    }

    /* access modifiers changed from: private */
    public void setPlural(String value) {
        value.getClass();
        this.plural_ = value;
    }

    /* access modifiers changed from: private */
    public void clearPlural() {
        this.plural_ = getDefaultInstance().getPlural();
    }

    /* access modifiers changed from: private */
    public void setPluralBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.plural_ = value.toStringUtf8();
    }

    public String getSingular() {
        return this.singular_;
    }

    public ByteString getSingularBytes() {
        return ByteString.copyFromUtf8(this.singular_);
    }

    /* access modifiers changed from: private */
    public void setSingular(String value) {
        value.getClass();
        this.singular_ = value;
    }

    /* access modifiers changed from: private */
    public void clearSingular() {
        this.singular_ = getDefaultInstance().getSingular();
    }

    /* access modifiers changed from: private */
    public void setSingularBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.singular_ = value.toStringUtf8();
    }

    public static ResourceDescriptor parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (ResourceDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ResourceDescriptor parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ResourceDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ResourceDescriptor parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ResourceDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ResourceDescriptor parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ResourceDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ResourceDescriptor parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ResourceDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ResourceDescriptor parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ResourceDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ResourceDescriptor parseFrom(InputStream input) throws IOException {
        return (ResourceDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ResourceDescriptor parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ResourceDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ResourceDescriptor parseDelimitedFrom(InputStream input) throws IOException {
        return (ResourceDescriptor) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ResourceDescriptor parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ResourceDescriptor) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ResourceDescriptor parseFrom(CodedInputStream input) throws IOException {
        return (ResourceDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ResourceDescriptor parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ResourceDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ResourceDescriptor prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ResourceDescriptor, Builder> implements ResourceDescriptorOrBuilder {
        /* synthetic */ Builder(C00451 x0) {
            this();
        }

        private Builder() {
            super(ResourceDescriptor.DEFAULT_INSTANCE);
        }

        public String getType() {
            return ((ResourceDescriptor) this.instance).getType();
        }

        public ByteString getTypeBytes() {
            return ((ResourceDescriptor) this.instance).getTypeBytes();
        }

        public Builder setType(String value) {
            copyOnWrite();
            ((ResourceDescriptor) this.instance).setType(value);
            return this;
        }

        public Builder clearType() {
            copyOnWrite();
            ((ResourceDescriptor) this.instance).clearType();
            return this;
        }

        public Builder setTypeBytes(ByteString value) {
            copyOnWrite();
            ((ResourceDescriptor) this.instance).setTypeBytes(value);
            return this;
        }

        public List<String> getPatternList() {
            return Collections.unmodifiableList(((ResourceDescriptor) this.instance).getPatternList());
        }

        public int getPatternCount() {
            return ((ResourceDescriptor) this.instance).getPatternCount();
        }

        public String getPattern(int index) {
            return ((ResourceDescriptor) this.instance).getPattern(index);
        }

        public ByteString getPatternBytes(int index) {
            return ((ResourceDescriptor) this.instance).getPatternBytes(index);
        }

        public Builder setPattern(int index, String value) {
            copyOnWrite();
            ((ResourceDescriptor) this.instance).setPattern(index, value);
            return this;
        }

        public Builder addPattern(String value) {
            copyOnWrite();
            ((ResourceDescriptor) this.instance).addPattern(value);
            return this;
        }

        public Builder addAllPattern(Iterable<String> values) {
            copyOnWrite();
            ((ResourceDescriptor) this.instance).addAllPattern(values);
            return this;
        }

        public Builder clearPattern() {
            copyOnWrite();
            ((ResourceDescriptor) this.instance).clearPattern();
            return this;
        }

        public Builder addPatternBytes(ByteString value) {
            copyOnWrite();
            ((ResourceDescriptor) this.instance).addPatternBytes(value);
            return this;
        }

        public String getNameField() {
            return ((ResourceDescriptor) this.instance).getNameField();
        }

        public ByteString getNameFieldBytes() {
            return ((ResourceDescriptor) this.instance).getNameFieldBytes();
        }

        public Builder setNameField(String value) {
            copyOnWrite();
            ((ResourceDescriptor) this.instance).setNameField(value);
            return this;
        }

        public Builder clearNameField() {
            copyOnWrite();
            ((ResourceDescriptor) this.instance).clearNameField();
            return this;
        }

        public Builder setNameFieldBytes(ByteString value) {
            copyOnWrite();
            ((ResourceDescriptor) this.instance).setNameFieldBytes(value);
            return this;
        }

        public int getHistoryValue() {
            return ((ResourceDescriptor) this.instance).getHistoryValue();
        }

        public Builder setHistoryValue(int value) {
            copyOnWrite();
            ((ResourceDescriptor) this.instance).setHistoryValue(value);
            return this;
        }

        public History getHistory() {
            return ((ResourceDescriptor) this.instance).getHistory();
        }

        public Builder setHistory(History value) {
            copyOnWrite();
            ((ResourceDescriptor) this.instance).setHistory(value);
            return this;
        }

        public Builder clearHistory() {
            copyOnWrite();
            ((ResourceDescriptor) this.instance).clearHistory();
            return this;
        }

        public String getPlural() {
            return ((ResourceDescriptor) this.instance).getPlural();
        }

        public ByteString getPluralBytes() {
            return ((ResourceDescriptor) this.instance).getPluralBytes();
        }

        public Builder setPlural(String value) {
            copyOnWrite();
            ((ResourceDescriptor) this.instance).setPlural(value);
            return this;
        }

        public Builder clearPlural() {
            copyOnWrite();
            ((ResourceDescriptor) this.instance).clearPlural();
            return this;
        }

        public Builder setPluralBytes(ByteString value) {
            copyOnWrite();
            ((ResourceDescriptor) this.instance).setPluralBytes(value);
            return this;
        }

        public String getSingular() {
            return ((ResourceDescriptor) this.instance).getSingular();
        }

        public ByteString getSingularBytes() {
            return ((ResourceDescriptor) this.instance).getSingularBytes();
        }

        public Builder setSingular(String value) {
            copyOnWrite();
            ((ResourceDescriptor) this.instance).setSingular(value);
            return this;
        }

        public Builder clearSingular() {
            copyOnWrite();
            ((ResourceDescriptor) this.instance).clearSingular();
            return this;
        }

        public Builder setSingularBytes(ByteString value) {
            copyOnWrite();
            ((ResourceDescriptor) this.instance).setSingularBytes(value);
            return this;
        }
    }

    /* renamed from: com.google.api.ResourceDescriptor$1 */
    static /* synthetic */ class C00451 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f38xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f38xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f38xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f38xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f38xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f38xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f38xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f38xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00451.f38xa1df5c61[method.ordinal()]) {
            case 1:
                return new ResourceDescriptor();
            case 2:
                return new Builder((C00451) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0006\u0000\u0000\u0001\u0006\u0006\u0000\u0001\u0000\u0001Ȉ\u0002Ț\u0003Ȉ\u0004\f\u0005Ȉ\u0006Ȉ", new Object[]{"type_", "pattern_", "nameField_", "history_", "plural_", "singular_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ResourceDescriptor> parser = PARSER;
                if (parser == null) {
                    synchronized (ResourceDescriptor.class) {
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
        ResourceDescriptor defaultInstance = new ResourceDescriptor();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(ResourceDescriptor.class, defaultInstance);
    }

    public static ResourceDescriptor getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ResourceDescriptor> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
