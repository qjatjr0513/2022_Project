package com.google.firestore.p002v1;

import com.google.firestore.p002v1.Document;
import com.google.firestore.p002v1.DocumentMask;
import com.google.firestore.p002v1.DocumentTransform;
import com.google.firestore.p002v1.Precondition;
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

/* renamed from: com.google.firestore.v1.Write */
public final class Write extends GeneratedMessageLite<Write, Builder> implements WriteOrBuilder {
    public static final int CURRENT_DOCUMENT_FIELD_NUMBER = 4;
    /* access modifiers changed from: private */
    public static final Write DEFAULT_INSTANCE;
    public static final int DELETE_FIELD_NUMBER = 2;
    private static volatile Parser<Write> PARSER = null;
    public static final int TRANSFORM_FIELD_NUMBER = 6;
    public static final int UPDATE_FIELD_NUMBER = 1;
    public static final int UPDATE_MASK_FIELD_NUMBER = 3;
    public static final int UPDATE_TRANSFORMS_FIELD_NUMBER = 7;
    public static final int VERIFY_FIELD_NUMBER = 5;
    private Precondition currentDocument_;
    private int operationCase_ = 0;
    private Object operation_;
    private DocumentMask updateMask_;
    private Internal.ProtobufList<DocumentTransform.FieldTransform> updateTransforms_ = emptyProtobufList();

    private Write() {
    }

    /* renamed from: com.google.firestore.v1.Write$OperationCase */
    public enum OperationCase {
        UPDATE(1),
        DELETE(2),
        VERIFY(5),
        TRANSFORM(6),
        OPERATION_NOT_SET(0);
        
        private final int value;

        private OperationCase(int value2) {
            this.value = value2;
        }

        @Deprecated
        public static OperationCase valueOf(int value2) {
            return forNumber(value2);
        }

        public static OperationCase forNumber(int value2) {
            switch (value2) {
                case 0:
                    return OPERATION_NOT_SET;
                case 1:
                    return UPDATE;
                case 2:
                    return DELETE;
                case 5:
                    return VERIFY;
                case 6:
                    return TRANSFORM;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    public OperationCase getOperationCase() {
        return OperationCase.forNumber(this.operationCase_);
    }

    /* access modifiers changed from: private */
    public void clearOperation() {
        this.operationCase_ = 0;
        this.operation_ = null;
    }

    public boolean hasUpdate() {
        return this.operationCase_ == 1;
    }

    public Document getUpdate() {
        if (this.operationCase_ == 1) {
            return (Document) this.operation_;
        }
        return Document.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setUpdate(Document value) {
        value.getClass();
        this.operation_ = value;
        this.operationCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void mergeUpdate(Document value) {
        value.getClass();
        if (this.operationCase_ != 1 || this.operation_ == Document.getDefaultInstance()) {
            this.operation_ = value;
        } else {
            this.operation_ = ((Document.Builder) Document.newBuilder((Document) this.operation_).mergeFrom(value)).buildPartial();
        }
        this.operationCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void clearUpdate() {
        if (this.operationCase_ == 1) {
            this.operationCase_ = 0;
            this.operation_ = null;
        }
    }

    public String getDelete() {
        if (this.operationCase_ == 2) {
            return (String) this.operation_;
        }
        return "";
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.protobuf.ByteString getDeleteBytes() {
        /*
            r3 = this;
            java.lang.String r0 = ""
            int r1 = r3.operationCase_
            r2 = 2
            if (r1 != r2) goto L_0x000c
            java.lang.Object r1 = r3.operation_
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0
        L_0x000c:
            com.google.protobuf.ByteString r1 = com.google.protobuf.ByteString.copyFromUtf8(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firestore.p002v1.Write.getDeleteBytes():com.google.protobuf.ByteString");
    }

    /* access modifiers changed from: private */
    public void setDelete(String value) {
        value.getClass();
        this.operationCase_ = 2;
        this.operation_ = value;
    }

    /* access modifiers changed from: private */
    public void clearDelete() {
        if (this.operationCase_ == 2) {
            this.operationCase_ = 0;
            this.operation_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setDeleteBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.operation_ = value.toStringUtf8();
        this.operationCase_ = 2;
    }

    public String getVerify() {
        if (this.operationCase_ == 5) {
            return (String) this.operation_;
        }
        return "";
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.protobuf.ByteString getVerifyBytes() {
        /*
            r3 = this;
            java.lang.String r0 = ""
            int r1 = r3.operationCase_
            r2 = 5
            if (r1 != r2) goto L_0x000c
            java.lang.Object r1 = r3.operation_
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0
        L_0x000c:
            com.google.protobuf.ByteString r1 = com.google.protobuf.ByteString.copyFromUtf8(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firestore.p002v1.Write.getVerifyBytes():com.google.protobuf.ByteString");
    }

    /* access modifiers changed from: private */
    public void setVerify(String value) {
        value.getClass();
        this.operationCase_ = 5;
        this.operation_ = value;
    }

    /* access modifiers changed from: private */
    public void clearVerify() {
        if (this.operationCase_ == 5) {
            this.operationCase_ = 0;
            this.operation_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setVerifyBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.operation_ = value.toStringUtf8();
        this.operationCase_ = 5;
    }

    public boolean hasTransform() {
        return this.operationCase_ == 6;
    }

    public DocumentTransform getTransform() {
        if (this.operationCase_ == 6) {
            return (DocumentTransform) this.operation_;
        }
        return DocumentTransform.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setTransform(DocumentTransform value) {
        value.getClass();
        this.operation_ = value;
        this.operationCase_ = 6;
    }

    /* access modifiers changed from: private */
    public void mergeTransform(DocumentTransform value) {
        value.getClass();
        if (this.operationCase_ != 6 || this.operation_ == DocumentTransform.getDefaultInstance()) {
            this.operation_ = value;
        } else {
            this.operation_ = ((DocumentTransform.Builder) DocumentTransform.newBuilder((DocumentTransform) this.operation_).mergeFrom(value)).buildPartial();
        }
        this.operationCase_ = 6;
    }

    /* access modifiers changed from: private */
    public void clearTransform() {
        if (this.operationCase_ == 6) {
            this.operationCase_ = 0;
            this.operation_ = null;
        }
    }

    public boolean hasUpdateMask() {
        return this.updateMask_ != null;
    }

    public DocumentMask getUpdateMask() {
        DocumentMask documentMask = this.updateMask_;
        return documentMask == null ? DocumentMask.getDefaultInstance() : documentMask;
    }

    /* access modifiers changed from: private */
    public void setUpdateMask(DocumentMask value) {
        value.getClass();
        this.updateMask_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeUpdateMask(DocumentMask value) {
        value.getClass();
        DocumentMask documentMask = this.updateMask_;
        if (documentMask == null || documentMask == DocumentMask.getDefaultInstance()) {
            this.updateMask_ = value;
        } else {
            this.updateMask_ = (DocumentMask) ((DocumentMask.Builder) DocumentMask.newBuilder(this.updateMask_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearUpdateMask() {
        this.updateMask_ = null;
    }

    public List<DocumentTransform.FieldTransform> getUpdateTransformsList() {
        return this.updateTransforms_;
    }

    public List<? extends DocumentTransform.FieldTransformOrBuilder> getUpdateTransformsOrBuilderList() {
        return this.updateTransforms_;
    }

    public int getUpdateTransformsCount() {
        return this.updateTransforms_.size();
    }

    public DocumentTransform.FieldTransform getUpdateTransforms(int index) {
        return (DocumentTransform.FieldTransform) this.updateTransforms_.get(index);
    }

    public DocumentTransform.FieldTransformOrBuilder getUpdateTransformsOrBuilder(int index) {
        return (DocumentTransform.FieldTransformOrBuilder) this.updateTransforms_.get(index);
    }

    private void ensureUpdateTransformsIsMutable() {
        Internal.ProtobufList<DocumentTransform.FieldTransform> tmp = this.updateTransforms_;
        if (!tmp.isModifiable()) {
            this.updateTransforms_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setUpdateTransforms(int index, DocumentTransform.FieldTransform value) {
        value.getClass();
        ensureUpdateTransformsIsMutable();
        this.updateTransforms_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addUpdateTransforms(DocumentTransform.FieldTransform value) {
        value.getClass();
        ensureUpdateTransformsIsMutable();
        this.updateTransforms_.add(value);
    }

    /* access modifiers changed from: private */
    public void addUpdateTransforms(int index, DocumentTransform.FieldTransform value) {
        value.getClass();
        ensureUpdateTransformsIsMutable();
        this.updateTransforms_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllUpdateTransforms(Iterable<? extends DocumentTransform.FieldTransform> values) {
        ensureUpdateTransformsIsMutable();
        AbstractMessageLite.addAll(values, this.updateTransforms_);
    }

    /* access modifiers changed from: private */
    public void clearUpdateTransforms() {
        this.updateTransforms_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeUpdateTransforms(int index) {
        ensureUpdateTransformsIsMutable();
        this.updateTransforms_.remove(index);
    }

    public boolean hasCurrentDocument() {
        return this.currentDocument_ != null;
    }

    public Precondition getCurrentDocument() {
        Precondition precondition = this.currentDocument_;
        return precondition == null ? Precondition.getDefaultInstance() : precondition;
    }

    /* access modifiers changed from: private */
    public void setCurrentDocument(Precondition value) {
        value.getClass();
        this.currentDocument_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeCurrentDocument(Precondition value) {
        value.getClass();
        Precondition precondition = this.currentDocument_;
        if (precondition == null || precondition == Precondition.getDefaultInstance()) {
            this.currentDocument_ = value;
        } else {
            this.currentDocument_ = (Precondition) ((Precondition.Builder) Precondition.newBuilder(this.currentDocument_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearCurrentDocument() {
        this.currentDocument_ = null;
    }

    public static Write parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Write parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Write parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Write parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Write parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Write parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Write parseFrom(InputStream input) throws IOException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Write parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Write parseDelimitedFrom(InputStream input) throws IOException {
        return (Write) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Write parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Write) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Write parseFrom(CodedInputStream input) throws IOException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Write parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Write prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /* renamed from: com.google.firestore.v1.Write$Builder */
    public static final class Builder extends GeneratedMessageLite.Builder<Write, Builder> implements WriteOrBuilder {
        /* synthetic */ Builder(C08681 x0) {
            this();
        }

        private Builder() {
            super(Write.DEFAULT_INSTANCE);
        }

        public OperationCase getOperationCase() {
            return ((Write) this.instance).getOperationCase();
        }

        public Builder clearOperation() {
            copyOnWrite();
            ((Write) this.instance).clearOperation();
            return this;
        }

        public boolean hasUpdate() {
            return ((Write) this.instance).hasUpdate();
        }

        public Document getUpdate() {
            return ((Write) this.instance).getUpdate();
        }

        public Builder setUpdate(Document value) {
            copyOnWrite();
            ((Write) this.instance).setUpdate(value);
            return this;
        }

        public Builder setUpdate(Document.Builder builderForValue) {
            copyOnWrite();
            ((Write) this.instance).setUpdate((Document) builderForValue.build());
            return this;
        }

        public Builder mergeUpdate(Document value) {
            copyOnWrite();
            ((Write) this.instance).mergeUpdate(value);
            return this;
        }

        public Builder clearUpdate() {
            copyOnWrite();
            ((Write) this.instance).clearUpdate();
            return this;
        }

        public String getDelete() {
            return ((Write) this.instance).getDelete();
        }

        public ByteString getDeleteBytes() {
            return ((Write) this.instance).getDeleteBytes();
        }

        public Builder setDelete(String value) {
            copyOnWrite();
            ((Write) this.instance).setDelete(value);
            return this;
        }

        public Builder clearDelete() {
            copyOnWrite();
            ((Write) this.instance).clearDelete();
            return this;
        }

        public Builder setDeleteBytes(ByteString value) {
            copyOnWrite();
            ((Write) this.instance).setDeleteBytes(value);
            return this;
        }

        public String getVerify() {
            return ((Write) this.instance).getVerify();
        }

        public ByteString getVerifyBytes() {
            return ((Write) this.instance).getVerifyBytes();
        }

        public Builder setVerify(String value) {
            copyOnWrite();
            ((Write) this.instance).setVerify(value);
            return this;
        }

        public Builder clearVerify() {
            copyOnWrite();
            ((Write) this.instance).clearVerify();
            return this;
        }

        public Builder setVerifyBytes(ByteString value) {
            copyOnWrite();
            ((Write) this.instance).setVerifyBytes(value);
            return this;
        }

        public boolean hasTransform() {
            return ((Write) this.instance).hasTransform();
        }

        public DocumentTransform getTransform() {
            return ((Write) this.instance).getTransform();
        }

        public Builder setTransform(DocumentTransform value) {
            copyOnWrite();
            ((Write) this.instance).setTransform(value);
            return this;
        }

        public Builder setTransform(DocumentTransform.Builder builderForValue) {
            copyOnWrite();
            ((Write) this.instance).setTransform((DocumentTransform) builderForValue.build());
            return this;
        }

        public Builder mergeTransform(DocumentTransform value) {
            copyOnWrite();
            ((Write) this.instance).mergeTransform(value);
            return this;
        }

        public Builder clearTransform() {
            copyOnWrite();
            ((Write) this.instance).clearTransform();
            return this;
        }

        public boolean hasUpdateMask() {
            return ((Write) this.instance).hasUpdateMask();
        }

        public DocumentMask getUpdateMask() {
            return ((Write) this.instance).getUpdateMask();
        }

        public Builder setUpdateMask(DocumentMask value) {
            copyOnWrite();
            ((Write) this.instance).setUpdateMask(value);
            return this;
        }

        public Builder setUpdateMask(DocumentMask.Builder builderForValue) {
            copyOnWrite();
            ((Write) this.instance).setUpdateMask((DocumentMask) builderForValue.build());
            return this;
        }

        public Builder mergeUpdateMask(DocumentMask value) {
            copyOnWrite();
            ((Write) this.instance).mergeUpdateMask(value);
            return this;
        }

        public Builder clearUpdateMask() {
            copyOnWrite();
            ((Write) this.instance).clearUpdateMask();
            return this;
        }

        public List<DocumentTransform.FieldTransform> getUpdateTransformsList() {
            return Collections.unmodifiableList(((Write) this.instance).getUpdateTransformsList());
        }

        public int getUpdateTransformsCount() {
            return ((Write) this.instance).getUpdateTransformsCount();
        }

        public DocumentTransform.FieldTransform getUpdateTransforms(int index) {
            return ((Write) this.instance).getUpdateTransforms(index);
        }

        public Builder setUpdateTransforms(int index, DocumentTransform.FieldTransform value) {
            copyOnWrite();
            ((Write) this.instance).setUpdateTransforms(index, value);
            return this;
        }

        public Builder setUpdateTransforms(int index, DocumentTransform.FieldTransform.Builder builderForValue) {
            copyOnWrite();
            ((Write) this.instance).setUpdateTransforms(index, (DocumentTransform.FieldTransform) builderForValue.build());
            return this;
        }

        public Builder addUpdateTransforms(DocumentTransform.FieldTransform value) {
            copyOnWrite();
            ((Write) this.instance).addUpdateTransforms(value);
            return this;
        }

        public Builder addUpdateTransforms(int index, DocumentTransform.FieldTransform value) {
            copyOnWrite();
            ((Write) this.instance).addUpdateTransforms(index, value);
            return this;
        }

        public Builder addUpdateTransforms(DocumentTransform.FieldTransform.Builder builderForValue) {
            copyOnWrite();
            ((Write) this.instance).addUpdateTransforms((DocumentTransform.FieldTransform) builderForValue.build());
            return this;
        }

        public Builder addUpdateTransforms(int index, DocumentTransform.FieldTransform.Builder builderForValue) {
            copyOnWrite();
            ((Write) this.instance).addUpdateTransforms(index, (DocumentTransform.FieldTransform) builderForValue.build());
            return this;
        }

        public Builder addAllUpdateTransforms(Iterable<? extends DocumentTransform.FieldTransform> values) {
            copyOnWrite();
            ((Write) this.instance).addAllUpdateTransforms(values);
            return this;
        }

        public Builder clearUpdateTransforms() {
            copyOnWrite();
            ((Write) this.instance).clearUpdateTransforms();
            return this;
        }

        public Builder removeUpdateTransforms(int index) {
            copyOnWrite();
            ((Write) this.instance).removeUpdateTransforms(index);
            return this;
        }

        public boolean hasCurrentDocument() {
            return ((Write) this.instance).hasCurrentDocument();
        }

        public Precondition getCurrentDocument() {
            return ((Write) this.instance).getCurrentDocument();
        }

        public Builder setCurrentDocument(Precondition value) {
            copyOnWrite();
            ((Write) this.instance).setCurrentDocument(value);
            return this;
        }

        public Builder setCurrentDocument(Precondition.Builder builderForValue) {
            copyOnWrite();
            ((Write) this.instance).setCurrentDocument((Precondition) builderForValue.build());
            return this;
        }

        public Builder mergeCurrentDocument(Precondition value) {
            copyOnWrite();
            ((Write) this.instance).mergeCurrentDocument(value);
            return this;
        }

        public Builder clearCurrentDocument() {
            copyOnWrite();
            ((Write) this.instance).clearCurrentDocument();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.Write$1 */
    static /* synthetic */ class C08681 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f237xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f237xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f237xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f237xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f237xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f237xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f237xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f237xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C08681.f237xa1df5c61[method.ordinal()]) {
            case 1:
                return new Write();
            case 2:
                return new Builder((C08681) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0007\u0001\u0000\u0001\u0007\u0007\u0000\u0001\u0000\u0001<\u0000\u0002Ȼ\u0000\u0003\t\u0004\t\u0005Ȼ\u0000\u0006<\u0000\u0007\u001b", new Object[]{"operation_", "operationCase_", Document.class, "updateMask_", "currentDocument_", DocumentTransform.class, "updateTransforms_", DocumentTransform.FieldTransform.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Write> parser = PARSER;
                if (parser == null) {
                    synchronized (Write.class) {
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
        Write defaultInstance = new Write();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Write.class, defaultInstance);
    }

    public static Write getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Write> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
