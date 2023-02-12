package com.google.firestore.p002v1;

import com.google.firestore.p002v1.ArrayValue;
import com.google.firestore.p002v1.Value;
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

/* renamed from: com.google.firestore.v1.DocumentTransform */
public final class DocumentTransform extends GeneratedMessageLite<DocumentTransform, Builder> implements DocumentTransformOrBuilder {
    /* access modifiers changed from: private */
    public static final DocumentTransform DEFAULT_INSTANCE;
    public static final int DOCUMENT_FIELD_NUMBER = 1;
    public static final int FIELD_TRANSFORMS_FIELD_NUMBER = 2;
    private static volatile Parser<DocumentTransform> PARSER;
    private String document_ = "";
    private Internal.ProtobufList<FieldTransform> fieldTransforms_ = emptyProtobufList();

    /* renamed from: com.google.firestore.v1.DocumentTransform$FieldTransformOrBuilder */
    public interface FieldTransformOrBuilder extends MessageLiteOrBuilder {
        ArrayValue getAppendMissingElements();

        String getFieldPath();

        ByteString getFieldPathBytes();

        Value getIncrement();

        Value getMaximum();

        Value getMinimum();

        ArrayValue getRemoveAllFromArray();

        FieldTransform.ServerValue getSetToServerValue();

        int getSetToServerValueValue();

        FieldTransform.TransformTypeCase getTransformTypeCase();

        boolean hasAppendMissingElements();

        boolean hasIncrement();

        boolean hasMaximum();

        boolean hasMinimum();

        boolean hasRemoveAllFromArray();
    }

    private DocumentTransform() {
    }

    /* renamed from: com.google.firestore.v1.DocumentTransform$FieldTransform */
    public static final class FieldTransform extends GeneratedMessageLite<FieldTransform, Builder> implements FieldTransformOrBuilder {
        public static final int APPEND_MISSING_ELEMENTS_FIELD_NUMBER = 6;
        /* access modifiers changed from: private */
        public static final FieldTransform DEFAULT_INSTANCE;
        public static final int FIELD_PATH_FIELD_NUMBER = 1;
        public static final int INCREMENT_FIELD_NUMBER = 3;
        public static final int MAXIMUM_FIELD_NUMBER = 4;
        public static final int MINIMUM_FIELD_NUMBER = 5;
        private static volatile Parser<FieldTransform> PARSER = null;
        public static final int REMOVE_ALL_FROM_ARRAY_FIELD_NUMBER = 7;
        public static final int SET_TO_SERVER_VALUE_FIELD_NUMBER = 2;
        private String fieldPath_ = "";
        private int transformTypeCase_ = 0;
        private Object transformType_;

        private FieldTransform() {
        }

        /* renamed from: com.google.firestore.v1.DocumentTransform$FieldTransform$ServerValue */
        public enum ServerValue implements Internal.EnumLite {
            SERVER_VALUE_UNSPECIFIED(0),
            REQUEST_TIME(1),
            UNRECOGNIZED(-1);
            
            public static final int REQUEST_TIME_VALUE = 1;
            public static final int SERVER_VALUE_UNSPECIFIED_VALUE = 0;
            private static final Internal.EnumLiteMap<ServerValue> internalValueMap = null;
            private final int value;

            static {
                internalValueMap = new Internal.EnumLiteMap<ServerValue>() {
                    public ServerValue findValueByNumber(int number) {
                        return ServerValue.forNumber(number);
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
            public static ServerValue valueOf(int value2) {
                return forNumber(value2);
            }

            public static ServerValue forNumber(int value2) {
                switch (value2) {
                    case 0:
                        return SERVER_VALUE_UNSPECIFIED;
                    case 1:
                        return REQUEST_TIME;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<ServerValue> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return ServerValueVerifier.INSTANCE;
            }

            /* renamed from: com.google.firestore.v1.DocumentTransform$FieldTransform$ServerValue$ServerValueVerifier */
            private static final class ServerValueVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = null;

                private ServerValueVerifier() {
                }

                static {
                    INSTANCE = new ServerValueVerifier();
                }

                public boolean isInRange(int number) {
                    return ServerValue.forNumber(number) != null;
                }
            }

            private ServerValue(int value2) {
                this.value = value2;
            }
        }

        /* renamed from: com.google.firestore.v1.DocumentTransform$FieldTransform$TransformTypeCase */
        public enum TransformTypeCase {
            SET_TO_SERVER_VALUE(2),
            INCREMENT(3),
            MAXIMUM(4),
            MINIMUM(5),
            APPEND_MISSING_ELEMENTS(6),
            REMOVE_ALL_FROM_ARRAY(7),
            TRANSFORMTYPE_NOT_SET(0);
            
            private final int value;

            private TransformTypeCase(int value2) {
                this.value = value2;
            }

            @Deprecated
            public static TransformTypeCase valueOf(int value2) {
                return forNumber(value2);
            }

            public static TransformTypeCase forNumber(int value2) {
                switch (value2) {
                    case 0:
                        return TRANSFORMTYPE_NOT_SET;
                    case 2:
                        return SET_TO_SERVER_VALUE;
                    case 3:
                        return INCREMENT;
                    case 4:
                        return MAXIMUM;
                    case 5:
                        return MINIMUM;
                    case 6:
                        return APPEND_MISSING_ELEMENTS;
                    case 7:
                        return REMOVE_ALL_FROM_ARRAY;
                    default:
                        return null;
                }
            }

            public int getNumber() {
                return this.value;
            }
        }

        public TransformTypeCase getTransformTypeCase() {
            return TransformTypeCase.forNumber(this.transformTypeCase_);
        }

        /* access modifiers changed from: private */
        public void clearTransformType() {
            this.transformTypeCase_ = 0;
            this.transformType_ = null;
        }

        public String getFieldPath() {
            return this.fieldPath_;
        }

        public ByteString getFieldPathBytes() {
            return ByteString.copyFromUtf8(this.fieldPath_);
        }

        /* access modifiers changed from: private */
        public void setFieldPath(String value) {
            value.getClass();
            this.fieldPath_ = value;
        }

        /* access modifiers changed from: private */
        public void clearFieldPath() {
            this.fieldPath_ = getDefaultInstance().getFieldPath();
        }

        /* access modifiers changed from: private */
        public void setFieldPathBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.fieldPath_ = value.toStringUtf8();
        }

        public int getSetToServerValueValue() {
            if (this.transformTypeCase_ == 2) {
                return ((Integer) this.transformType_).intValue();
            }
            return 0;
        }

        public ServerValue getSetToServerValue() {
            if (this.transformTypeCase_ != 2) {
                return ServerValue.SERVER_VALUE_UNSPECIFIED;
            }
            ServerValue result = ServerValue.forNumber(((Integer) this.transformType_).intValue());
            return result == null ? ServerValue.UNRECOGNIZED : result;
        }

        /* access modifiers changed from: private */
        public void setSetToServerValueValue(int value) {
            this.transformTypeCase_ = 2;
            this.transformType_ = Integer.valueOf(value);
        }

        /* access modifiers changed from: private */
        public void setSetToServerValue(ServerValue value) {
            this.transformType_ = Integer.valueOf(value.getNumber());
            this.transformTypeCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void clearSetToServerValue() {
            if (this.transformTypeCase_ == 2) {
                this.transformTypeCase_ = 0;
                this.transformType_ = null;
            }
        }

        public boolean hasIncrement() {
            return this.transformTypeCase_ == 3;
        }

        public Value getIncrement() {
            if (this.transformTypeCase_ == 3) {
                return (Value) this.transformType_;
            }
            return Value.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setIncrement(Value value) {
            value.getClass();
            this.transformType_ = value;
            this.transformTypeCase_ = 3;
        }

        /* access modifiers changed from: private */
        public void mergeIncrement(Value value) {
            value.getClass();
            if (this.transformTypeCase_ != 3 || this.transformType_ == Value.getDefaultInstance()) {
                this.transformType_ = value;
            } else {
                this.transformType_ = ((Value.Builder) Value.newBuilder((Value) this.transformType_).mergeFrom(value)).buildPartial();
            }
            this.transformTypeCase_ = 3;
        }

        /* access modifiers changed from: private */
        public void clearIncrement() {
            if (this.transformTypeCase_ == 3) {
                this.transformTypeCase_ = 0;
                this.transformType_ = null;
            }
        }

        public boolean hasMaximum() {
            return this.transformTypeCase_ == 4;
        }

        public Value getMaximum() {
            if (this.transformTypeCase_ == 4) {
                return (Value) this.transformType_;
            }
            return Value.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setMaximum(Value value) {
            value.getClass();
            this.transformType_ = value;
            this.transformTypeCase_ = 4;
        }

        /* access modifiers changed from: private */
        public void mergeMaximum(Value value) {
            value.getClass();
            if (this.transformTypeCase_ != 4 || this.transformType_ == Value.getDefaultInstance()) {
                this.transformType_ = value;
            } else {
                this.transformType_ = ((Value.Builder) Value.newBuilder((Value) this.transformType_).mergeFrom(value)).buildPartial();
            }
            this.transformTypeCase_ = 4;
        }

        /* access modifiers changed from: private */
        public void clearMaximum() {
            if (this.transformTypeCase_ == 4) {
                this.transformTypeCase_ = 0;
                this.transformType_ = null;
            }
        }

        public boolean hasMinimum() {
            return this.transformTypeCase_ == 5;
        }

        public Value getMinimum() {
            if (this.transformTypeCase_ == 5) {
                return (Value) this.transformType_;
            }
            return Value.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setMinimum(Value value) {
            value.getClass();
            this.transformType_ = value;
            this.transformTypeCase_ = 5;
        }

        /* access modifiers changed from: private */
        public void mergeMinimum(Value value) {
            value.getClass();
            if (this.transformTypeCase_ != 5 || this.transformType_ == Value.getDefaultInstance()) {
                this.transformType_ = value;
            } else {
                this.transformType_ = ((Value.Builder) Value.newBuilder((Value) this.transformType_).mergeFrom(value)).buildPartial();
            }
            this.transformTypeCase_ = 5;
        }

        /* access modifiers changed from: private */
        public void clearMinimum() {
            if (this.transformTypeCase_ == 5) {
                this.transformTypeCase_ = 0;
                this.transformType_ = null;
            }
        }

        public boolean hasAppendMissingElements() {
            return this.transformTypeCase_ == 6;
        }

        public ArrayValue getAppendMissingElements() {
            if (this.transformTypeCase_ == 6) {
                return (ArrayValue) this.transformType_;
            }
            return ArrayValue.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setAppendMissingElements(ArrayValue value) {
            value.getClass();
            this.transformType_ = value;
            this.transformTypeCase_ = 6;
        }

        /* access modifiers changed from: private */
        public void mergeAppendMissingElements(ArrayValue value) {
            value.getClass();
            if (this.transformTypeCase_ != 6 || this.transformType_ == ArrayValue.getDefaultInstance()) {
                this.transformType_ = value;
            } else {
                this.transformType_ = ((ArrayValue.Builder) ArrayValue.newBuilder((ArrayValue) this.transformType_).mergeFrom(value)).buildPartial();
            }
            this.transformTypeCase_ = 6;
        }

        /* access modifiers changed from: private */
        public void clearAppendMissingElements() {
            if (this.transformTypeCase_ == 6) {
                this.transformTypeCase_ = 0;
                this.transformType_ = null;
            }
        }

        public boolean hasRemoveAllFromArray() {
            return this.transformTypeCase_ == 7;
        }

        public ArrayValue getRemoveAllFromArray() {
            if (this.transformTypeCase_ == 7) {
                return (ArrayValue) this.transformType_;
            }
            return ArrayValue.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setRemoveAllFromArray(ArrayValue value) {
            value.getClass();
            this.transformType_ = value;
            this.transformTypeCase_ = 7;
        }

        /* access modifiers changed from: private */
        public void mergeRemoveAllFromArray(ArrayValue value) {
            value.getClass();
            if (this.transformTypeCase_ != 7 || this.transformType_ == ArrayValue.getDefaultInstance()) {
                this.transformType_ = value;
            } else {
                this.transformType_ = ((ArrayValue.Builder) ArrayValue.newBuilder((ArrayValue) this.transformType_).mergeFrom(value)).buildPartial();
            }
            this.transformTypeCase_ = 7;
        }

        /* access modifiers changed from: private */
        public void clearRemoveAllFromArray() {
            if (this.transformTypeCase_ == 7) {
                this.transformTypeCase_ = 0;
                this.transformType_ = null;
            }
        }

        public static FieldTransform parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FieldTransform parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FieldTransform parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FieldTransform parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FieldTransform parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FieldTransform parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FieldTransform parseFrom(InputStream input) throws IOException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FieldTransform parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FieldTransform parseDelimitedFrom(InputStream input) throws IOException {
            return (FieldTransform) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static FieldTransform parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FieldTransform) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FieldTransform parseFrom(CodedInputStream input) throws IOException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FieldTransform parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(FieldTransform prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /* renamed from: com.google.firestore.v1.DocumentTransform$FieldTransform$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<FieldTransform, Builder> implements FieldTransformOrBuilder {
            /* synthetic */ Builder(C08391 x0) {
                this();
            }

            private Builder() {
                super(FieldTransform.DEFAULT_INSTANCE);
            }

            public TransformTypeCase getTransformTypeCase() {
                return ((FieldTransform) this.instance).getTransformTypeCase();
            }

            public Builder clearTransformType() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearTransformType();
                return this;
            }

            public String getFieldPath() {
                return ((FieldTransform) this.instance).getFieldPath();
            }

            public ByteString getFieldPathBytes() {
                return ((FieldTransform) this.instance).getFieldPathBytes();
            }

            public Builder setFieldPath(String value) {
                copyOnWrite();
                ((FieldTransform) this.instance).setFieldPath(value);
                return this;
            }

            public Builder clearFieldPath() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearFieldPath();
                return this;
            }

            public Builder setFieldPathBytes(ByteString value) {
                copyOnWrite();
                ((FieldTransform) this.instance).setFieldPathBytes(value);
                return this;
            }

            public int getSetToServerValueValue() {
                return ((FieldTransform) this.instance).getSetToServerValueValue();
            }

            public Builder setSetToServerValueValue(int value) {
                copyOnWrite();
                ((FieldTransform) this.instance).setSetToServerValueValue(value);
                return this;
            }

            public ServerValue getSetToServerValue() {
                return ((FieldTransform) this.instance).getSetToServerValue();
            }

            public Builder setSetToServerValue(ServerValue value) {
                copyOnWrite();
                ((FieldTransform) this.instance).setSetToServerValue(value);
                return this;
            }

            public Builder clearSetToServerValue() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearSetToServerValue();
                return this;
            }

            public boolean hasIncrement() {
                return ((FieldTransform) this.instance).hasIncrement();
            }

            public Value getIncrement() {
                return ((FieldTransform) this.instance).getIncrement();
            }

            public Builder setIncrement(Value value) {
                copyOnWrite();
                ((FieldTransform) this.instance).setIncrement(value);
                return this;
            }

            public Builder setIncrement(Value.Builder builderForValue) {
                copyOnWrite();
                ((FieldTransform) this.instance).setIncrement((Value) builderForValue.build());
                return this;
            }

            public Builder mergeIncrement(Value value) {
                copyOnWrite();
                ((FieldTransform) this.instance).mergeIncrement(value);
                return this;
            }

            public Builder clearIncrement() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearIncrement();
                return this;
            }

            public boolean hasMaximum() {
                return ((FieldTransform) this.instance).hasMaximum();
            }

            public Value getMaximum() {
                return ((FieldTransform) this.instance).getMaximum();
            }

            public Builder setMaximum(Value value) {
                copyOnWrite();
                ((FieldTransform) this.instance).setMaximum(value);
                return this;
            }

            public Builder setMaximum(Value.Builder builderForValue) {
                copyOnWrite();
                ((FieldTransform) this.instance).setMaximum((Value) builderForValue.build());
                return this;
            }

            public Builder mergeMaximum(Value value) {
                copyOnWrite();
                ((FieldTransform) this.instance).mergeMaximum(value);
                return this;
            }

            public Builder clearMaximum() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearMaximum();
                return this;
            }

            public boolean hasMinimum() {
                return ((FieldTransform) this.instance).hasMinimum();
            }

            public Value getMinimum() {
                return ((FieldTransform) this.instance).getMinimum();
            }

            public Builder setMinimum(Value value) {
                copyOnWrite();
                ((FieldTransform) this.instance).setMinimum(value);
                return this;
            }

            public Builder setMinimum(Value.Builder builderForValue) {
                copyOnWrite();
                ((FieldTransform) this.instance).setMinimum((Value) builderForValue.build());
                return this;
            }

            public Builder mergeMinimum(Value value) {
                copyOnWrite();
                ((FieldTransform) this.instance).mergeMinimum(value);
                return this;
            }

            public Builder clearMinimum() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearMinimum();
                return this;
            }

            public boolean hasAppendMissingElements() {
                return ((FieldTransform) this.instance).hasAppendMissingElements();
            }

            public ArrayValue getAppendMissingElements() {
                return ((FieldTransform) this.instance).getAppendMissingElements();
            }

            public Builder setAppendMissingElements(ArrayValue value) {
                copyOnWrite();
                ((FieldTransform) this.instance).setAppendMissingElements(value);
                return this;
            }

            public Builder setAppendMissingElements(ArrayValue.Builder builderForValue) {
                copyOnWrite();
                ((FieldTransform) this.instance).setAppendMissingElements((ArrayValue) builderForValue.build());
                return this;
            }

            public Builder mergeAppendMissingElements(ArrayValue value) {
                copyOnWrite();
                ((FieldTransform) this.instance).mergeAppendMissingElements(value);
                return this;
            }

            public Builder clearAppendMissingElements() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearAppendMissingElements();
                return this;
            }

            public boolean hasRemoveAllFromArray() {
                return ((FieldTransform) this.instance).hasRemoveAllFromArray();
            }

            public ArrayValue getRemoveAllFromArray() {
                return ((FieldTransform) this.instance).getRemoveAllFromArray();
            }

            public Builder setRemoveAllFromArray(ArrayValue value) {
                copyOnWrite();
                ((FieldTransform) this.instance).setRemoveAllFromArray(value);
                return this;
            }

            public Builder setRemoveAllFromArray(ArrayValue.Builder builderForValue) {
                copyOnWrite();
                ((FieldTransform) this.instance).setRemoveAllFromArray((ArrayValue) builderForValue.build());
                return this;
            }

            public Builder mergeRemoveAllFromArray(ArrayValue value) {
                copyOnWrite();
                ((FieldTransform) this.instance).mergeRemoveAllFromArray(value);
                return this;
            }

            public Builder clearRemoveAllFromArray() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearRemoveAllFromArray();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C08391.f216xa1df5c61[method.ordinal()]) {
                case 1:
                    return new FieldTransform();
                case 2:
                    return new Builder((C08391) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0007\u0001\u0000\u0001\u0007\u0007\u0000\u0000\u0000\u0001Ȉ\u0002?\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000\u0006<\u0000\u0007<\u0000", new Object[]{"transformType_", "transformTypeCase_", "fieldPath_", Value.class, Value.class, Value.class, ArrayValue.class, ArrayValue.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<FieldTransform> parser = PARSER;
                    if (parser == null) {
                        synchronized (FieldTransform.class) {
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
            FieldTransform defaultInstance = new FieldTransform();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(FieldTransform.class, defaultInstance);
        }

        public static FieldTransform getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FieldTransform> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.DocumentTransform$1 */
    static /* synthetic */ class C08391 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f216xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f216xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f216xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f216xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f216xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f216xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f216xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f216xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public String getDocument() {
        return this.document_;
    }

    public ByteString getDocumentBytes() {
        return ByteString.copyFromUtf8(this.document_);
    }

    /* access modifiers changed from: private */
    public void setDocument(String value) {
        value.getClass();
        this.document_ = value;
    }

    /* access modifiers changed from: private */
    public void clearDocument() {
        this.document_ = getDefaultInstance().getDocument();
    }

    /* access modifiers changed from: private */
    public void setDocumentBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.document_ = value.toStringUtf8();
    }

    public List<FieldTransform> getFieldTransformsList() {
        return this.fieldTransforms_;
    }

    public List<? extends FieldTransformOrBuilder> getFieldTransformsOrBuilderList() {
        return this.fieldTransforms_;
    }

    public int getFieldTransformsCount() {
        return this.fieldTransforms_.size();
    }

    public FieldTransform getFieldTransforms(int index) {
        return (FieldTransform) this.fieldTransforms_.get(index);
    }

    public FieldTransformOrBuilder getFieldTransformsOrBuilder(int index) {
        return (FieldTransformOrBuilder) this.fieldTransforms_.get(index);
    }

    private void ensureFieldTransformsIsMutable() {
        Internal.ProtobufList<FieldTransform> tmp = this.fieldTransforms_;
        if (!tmp.isModifiable()) {
            this.fieldTransforms_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setFieldTransforms(int index, FieldTransform value) {
        value.getClass();
        ensureFieldTransformsIsMutable();
        this.fieldTransforms_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addFieldTransforms(FieldTransform value) {
        value.getClass();
        ensureFieldTransformsIsMutable();
        this.fieldTransforms_.add(value);
    }

    /* access modifiers changed from: private */
    public void addFieldTransforms(int index, FieldTransform value) {
        value.getClass();
        ensureFieldTransformsIsMutable();
        this.fieldTransforms_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllFieldTransforms(Iterable<? extends FieldTransform> values) {
        ensureFieldTransformsIsMutable();
        AbstractMessageLite.addAll(values, this.fieldTransforms_);
    }

    /* access modifiers changed from: private */
    public void clearFieldTransforms() {
        this.fieldTransforms_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeFieldTransforms(int index) {
        ensureFieldTransformsIsMutable();
        this.fieldTransforms_.remove(index);
    }

    public static DocumentTransform parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DocumentTransform parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DocumentTransform parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DocumentTransform parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DocumentTransform parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DocumentTransform parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DocumentTransform parseFrom(InputStream input) throws IOException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DocumentTransform parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DocumentTransform parseDelimitedFrom(InputStream input) throws IOException {
        return (DocumentTransform) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static DocumentTransform parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DocumentTransform) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DocumentTransform parseFrom(CodedInputStream input) throws IOException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DocumentTransform parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(DocumentTransform prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /* renamed from: com.google.firestore.v1.DocumentTransform$Builder */
    public static final class Builder extends GeneratedMessageLite.Builder<DocumentTransform, Builder> implements DocumentTransformOrBuilder {
        /* synthetic */ Builder(C08391 x0) {
            this();
        }

        private Builder() {
            super(DocumentTransform.DEFAULT_INSTANCE);
        }

        public String getDocument() {
            return ((DocumentTransform) this.instance).getDocument();
        }

        public ByteString getDocumentBytes() {
            return ((DocumentTransform) this.instance).getDocumentBytes();
        }

        public Builder setDocument(String value) {
            copyOnWrite();
            ((DocumentTransform) this.instance).setDocument(value);
            return this;
        }

        public Builder clearDocument() {
            copyOnWrite();
            ((DocumentTransform) this.instance).clearDocument();
            return this;
        }

        public Builder setDocumentBytes(ByteString value) {
            copyOnWrite();
            ((DocumentTransform) this.instance).setDocumentBytes(value);
            return this;
        }

        public List<FieldTransform> getFieldTransformsList() {
            return Collections.unmodifiableList(((DocumentTransform) this.instance).getFieldTransformsList());
        }

        public int getFieldTransformsCount() {
            return ((DocumentTransform) this.instance).getFieldTransformsCount();
        }

        public FieldTransform getFieldTransforms(int index) {
            return ((DocumentTransform) this.instance).getFieldTransforms(index);
        }

        public Builder setFieldTransforms(int index, FieldTransform value) {
            copyOnWrite();
            ((DocumentTransform) this.instance).setFieldTransforms(index, value);
            return this;
        }

        public Builder setFieldTransforms(int index, FieldTransform.Builder builderForValue) {
            copyOnWrite();
            ((DocumentTransform) this.instance).setFieldTransforms(index, (FieldTransform) builderForValue.build());
            return this;
        }

        public Builder addFieldTransforms(FieldTransform value) {
            copyOnWrite();
            ((DocumentTransform) this.instance).addFieldTransforms(value);
            return this;
        }

        public Builder addFieldTransforms(int index, FieldTransform value) {
            copyOnWrite();
            ((DocumentTransform) this.instance).addFieldTransforms(index, value);
            return this;
        }

        public Builder addFieldTransforms(FieldTransform.Builder builderForValue) {
            copyOnWrite();
            ((DocumentTransform) this.instance).addFieldTransforms((FieldTransform) builderForValue.build());
            return this;
        }

        public Builder addFieldTransforms(int index, FieldTransform.Builder builderForValue) {
            copyOnWrite();
            ((DocumentTransform) this.instance).addFieldTransforms(index, (FieldTransform) builderForValue.build());
            return this;
        }

        public Builder addAllFieldTransforms(Iterable<? extends FieldTransform> values) {
            copyOnWrite();
            ((DocumentTransform) this.instance).addAllFieldTransforms(values);
            return this;
        }

        public Builder clearFieldTransforms() {
            copyOnWrite();
            ((DocumentTransform) this.instance).clearFieldTransforms();
            return this;
        }

        public Builder removeFieldTransforms(int index) {
            copyOnWrite();
            ((DocumentTransform) this.instance).removeFieldTransforms(index);
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C08391.f216xa1df5c61[method.ordinal()]) {
            case 1:
                return new DocumentTransform();
            case 2:
                return new Builder((C08391) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"document_", "fieldTransforms_", FieldTransform.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<DocumentTransform> parser = PARSER;
                if (parser == null) {
                    synchronized (DocumentTransform.class) {
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
        DocumentTransform defaultInstance = new DocumentTransform();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(DocumentTransform.class, defaultInstance);
    }

    public static DocumentTransform getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DocumentTransform> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
