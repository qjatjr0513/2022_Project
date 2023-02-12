package com.google.firestore.p002v1;

import com.google.firestore.p002v1.DocumentChange;
import com.google.firestore.p002v1.DocumentDelete;
import com.google.firestore.p002v1.DocumentRemove;
import com.google.firestore.p002v1.ExistenceFilter;
import com.google.firestore.p002v1.TargetChange;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* renamed from: com.google.firestore.v1.ListenResponse */
public final class ListenResponse extends GeneratedMessageLite<ListenResponse, Builder> implements ListenResponseOrBuilder {
    /* access modifiers changed from: private */
    public static final ListenResponse DEFAULT_INSTANCE;
    public static final int DOCUMENT_CHANGE_FIELD_NUMBER = 3;
    public static final int DOCUMENT_DELETE_FIELD_NUMBER = 4;
    public static final int DOCUMENT_REMOVE_FIELD_NUMBER = 6;
    public static final int FILTER_FIELD_NUMBER = 5;
    private static volatile Parser<ListenResponse> PARSER = null;
    public static final int TARGET_CHANGE_FIELD_NUMBER = 2;
    private int responseTypeCase_ = 0;
    private Object responseType_;

    private ListenResponse() {
    }

    /* renamed from: com.google.firestore.v1.ListenResponse$ResponseTypeCase */
    public enum ResponseTypeCase {
        TARGET_CHANGE(2),
        DOCUMENT_CHANGE(3),
        DOCUMENT_DELETE(4),
        DOCUMENT_REMOVE(6),
        FILTER(5),
        RESPONSETYPE_NOT_SET(0);
        
        private final int value;

        private ResponseTypeCase(int value2) {
            this.value = value2;
        }

        @Deprecated
        public static ResponseTypeCase valueOf(int value2) {
            return forNumber(value2);
        }

        public static ResponseTypeCase forNumber(int value2) {
            switch (value2) {
                case 0:
                    return RESPONSETYPE_NOT_SET;
                case 2:
                    return TARGET_CHANGE;
                case 3:
                    return DOCUMENT_CHANGE;
                case 4:
                    return DOCUMENT_DELETE;
                case 5:
                    return FILTER;
                case 6:
                    return DOCUMENT_REMOVE;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    public ResponseTypeCase getResponseTypeCase() {
        return ResponseTypeCase.forNumber(this.responseTypeCase_);
    }

    /* access modifiers changed from: private */
    public void clearResponseType() {
        this.responseTypeCase_ = 0;
        this.responseType_ = null;
    }

    public boolean hasTargetChange() {
        return this.responseTypeCase_ == 2;
    }

    public TargetChange getTargetChange() {
        if (this.responseTypeCase_ == 2) {
            return (TargetChange) this.responseType_;
        }
        return TargetChange.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setTargetChange(TargetChange value) {
        value.getClass();
        this.responseType_ = value;
        this.responseTypeCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void mergeTargetChange(TargetChange value) {
        value.getClass();
        if (this.responseTypeCase_ != 2 || this.responseType_ == TargetChange.getDefaultInstance()) {
            this.responseType_ = value;
        } else {
            this.responseType_ = ((TargetChange.Builder) TargetChange.newBuilder((TargetChange) this.responseType_).mergeFrom(value)).buildPartial();
        }
        this.responseTypeCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void clearTargetChange() {
        if (this.responseTypeCase_ == 2) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    public boolean hasDocumentChange() {
        return this.responseTypeCase_ == 3;
    }

    public DocumentChange getDocumentChange() {
        if (this.responseTypeCase_ == 3) {
            return (DocumentChange) this.responseType_;
        }
        return DocumentChange.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setDocumentChange(DocumentChange value) {
        value.getClass();
        this.responseType_ = value;
        this.responseTypeCase_ = 3;
    }

    /* access modifiers changed from: private */
    public void mergeDocumentChange(DocumentChange value) {
        value.getClass();
        if (this.responseTypeCase_ != 3 || this.responseType_ == DocumentChange.getDefaultInstance()) {
            this.responseType_ = value;
        } else {
            this.responseType_ = ((DocumentChange.Builder) DocumentChange.newBuilder((DocumentChange) this.responseType_).mergeFrom(value)).buildPartial();
        }
        this.responseTypeCase_ = 3;
    }

    /* access modifiers changed from: private */
    public void clearDocumentChange() {
        if (this.responseTypeCase_ == 3) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    public boolean hasDocumentDelete() {
        return this.responseTypeCase_ == 4;
    }

    public DocumentDelete getDocumentDelete() {
        if (this.responseTypeCase_ == 4) {
            return (DocumentDelete) this.responseType_;
        }
        return DocumentDelete.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setDocumentDelete(DocumentDelete value) {
        value.getClass();
        this.responseType_ = value;
        this.responseTypeCase_ = 4;
    }

    /* access modifiers changed from: private */
    public void mergeDocumentDelete(DocumentDelete value) {
        value.getClass();
        if (this.responseTypeCase_ != 4 || this.responseType_ == DocumentDelete.getDefaultInstance()) {
            this.responseType_ = value;
        } else {
            this.responseType_ = ((DocumentDelete.Builder) DocumentDelete.newBuilder((DocumentDelete) this.responseType_).mergeFrom(value)).buildPartial();
        }
        this.responseTypeCase_ = 4;
    }

    /* access modifiers changed from: private */
    public void clearDocumentDelete() {
        if (this.responseTypeCase_ == 4) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    public boolean hasDocumentRemove() {
        return this.responseTypeCase_ == 6;
    }

    public DocumentRemove getDocumentRemove() {
        if (this.responseTypeCase_ == 6) {
            return (DocumentRemove) this.responseType_;
        }
        return DocumentRemove.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setDocumentRemove(DocumentRemove value) {
        value.getClass();
        this.responseType_ = value;
        this.responseTypeCase_ = 6;
    }

    /* access modifiers changed from: private */
    public void mergeDocumentRemove(DocumentRemove value) {
        value.getClass();
        if (this.responseTypeCase_ != 6 || this.responseType_ == DocumentRemove.getDefaultInstance()) {
            this.responseType_ = value;
        } else {
            this.responseType_ = ((DocumentRemove.Builder) DocumentRemove.newBuilder((DocumentRemove) this.responseType_).mergeFrom(value)).buildPartial();
        }
        this.responseTypeCase_ = 6;
    }

    /* access modifiers changed from: private */
    public void clearDocumentRemove() {
        if (this.responseTypeCase_ == 6) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    public boolean hasFilter() {
        return this.responseTypeCase_ == 5;
    }

    public ExistenceFilter getFilter() {
        if (this.responseTypeCase_ == 5) {
            return (ExistenceFilter) this.responseType_;
        }
        return ExistenceFilter.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setFilter(ExistenceFilter value) {
        value.getClass();
        this.responseType_ = value;
        this.responseTypeCase_ = 5;
    }

    /* access modifiers changed from: private */
    public void mergeFilter(ExistenceFilter value) {
        value.getClass();
        if (this.responseTypeCase_ != 5 || this.responseType_ == ExistenceFilter.getDefaultInstance()) {
            this.responseType_ = value;
        } else {
            this.responseType_ = ((ExistenceFilter.Builder) ExistenceFilter.newBuilder((ExistenceFilter) this.responseType_).mergeFrom(value)).buildPartial();
        }
        this.responseTypeCase_ = 5;
    }

    /* access modifiers changed from: private */
    public void clearFilter() {
        if (this.responseTypeCase_ == 5) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    public static ListenResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ListenResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ListenResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ListenResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ListenResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ListenResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ListenResponse parseFrom(InputStream input) throws IOException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ListenResponse parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ListenResponse parseDelimitedFrom(InputStream input) throws IOException {
        return (ListenResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ListenResponse parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ListenResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ListenResponse parseFrom(CodedInputStream input) throws IOException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ListenResponse parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ListenResponse prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /* renamed from: com.google.firestore.v1.ListenResponse$Builder */
    public static final class Builder extends GeneratedMessageLite.Builder<ListenResponse, Builder> implements ListenResponseOrBuilder {
        /* synthetic */ Builder(C08511 x0) {
            this();
        }

        private Builder() {
            super(ListenResponse.DEFAULT_INSTANCE);
        }

        public ResponseTypeCase getResponseTypeCase() {
            return ((ListenResponse) this.instance).getResponseTypeCase();
        }

        public Builder clearResponseType() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearResponseType();
            return this;
        }

        public boolean hasTargetChange() {
            return ((ListenResponse) this.instance).hasTargetChange();
        }

        public TargetChange getTargetChange() {
            return ((ListenResponse) this.instance).getTargetChange();
        }

        public Builder setTargetChange(TargetChange value) {
            copyOnWrite();
            ((ListenResponse) this.instance).setTargetChange(value);
            return this;
        }

        public Builder setTargetChange(TargetChange.Builder builderForValue) {
            copyOnWrite();
            ((ListenResponse) this.instance).setTargetChange((TargetChange) builderForValue.build());
            return this;
        }

        public Builder mergeTargetChange(TargetChange value) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeTargetChange(value);
            return this;
        }

        public Builder clearTargetChange() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearTargetChange();
            return this;
        }

        public boolean hasDocumentChange() {
            return ((ListenResponse) this.instance).hasDocumentChange();
        }

        public DocumentChange getDocumentChange() {
            return ((ListenResponse) this.instance).getDocumentChange();
        }

        public Builder setDocumentChange(DocumentChange value) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentChange(value);
            return this;
        }

        public Builder setDocumentChange(DocumentChange.Builder builderForValue) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentChange((DocumentChange) builderForValue.build());
            return this;
        }

        public Builder mergeDocumentChange(DocumentChange value) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeDocumentChange(value);
            return this;
        }

        public Builder clearDocumentChange() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearDocumentChange();
            return this;
        }

        public boolean hasDocumentDelete() {
            return ((ListenResponse) this.instance).hasDocumentDelete();
        }

        public DocumentDelete getDocumentDelete() {
            return ((ListenResponse) this.instance).getDocumentDelete();
        }

        public Builder setDocumentDelete(DocumentDelete value) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentDelete(value);
            return this;
        }

        public Builder setDocumentDelete(DocumentDelete.Builder builderForValue) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentDelete((DocumentDelete) builderForValue.build());
            return this;
        }

        public Builder mergeDocumentDelete(DocumentDelete value) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeDocumentDelete(value);
            return this;
        }

        public Builder clearDocumentDelete() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearDocumentDelete();
            return this;
        }

        public boolean hasDocumentRemove() {
            return ((ListenResponse) this.instance).hasDocumentRemove();
        }

        public DocumentRemove getDocumentRemove() {
            return ((ListenResponse) this.instance).getDocumentRemove();
        }

        public Builder setDocumentRemove(DocumentRemove value) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentRemove(value);
            return this;
        }

        public Builder setDocumentRemove(DocumentRemove.Builder builderForValue) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentRemove((DocumentRemove) builderForValue.build());
            return this;
        }

        public Builder mergeDocumentRemove(DocumentRemove value) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeDocumentRemove(value);
            return this;
        }

        public Builder clearDocumentRemove() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearDocumentRemove();
            return this;
        }

        public boolean hasFilter() {
            return ((ListenResponse) this.instance).hasFilter();
        }

        public ExistenceFilter getFilter() {
            return ((ListenResponse) this.instance).getFilter();
        }

        public Builder setFilter(ExistenceFilter value) {
            copyOnWrite();
            ((ListenResponse) this.instance).setFilter(value);
            return this;
        }

        public Builder setFilter(ExistenceFilter.Builder builderForValue) {
            copyOnWrite();
            ((ListenResponse) this.instance).setFilter((ExistenceFilter) builderForValue.build());
            return this;
        }

        public Builder mergeFilter(ExistenceFilter value) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeFilter(value);
            return this;
        }

        public Builder clearFilter() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearFilter();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.ListenResponse$1 */
    static /* synthetic */ class C08511 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f224xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f224xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f224xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f224xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f224xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f224xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f224xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f224xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C08511.f224xa1df5c61[method.ordinal()]) {
            case 1:
                return new ListenResponse();
            case 2:
                return new Builder((C08511) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0001\u0000\u0002\u0006\u0005\u0000\u0000\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000\u0006<\u0000", new Object[]{"responseType_", "responseTypeCase_", TargetChange.class, DocumentChange.class, DocumentDelete.class, ExistenceFilter.class, DocumentRemove.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ListenResponse> parser = PARSER;
                if (parser == null) {
                    synchronized (ListenResponse.class) {
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
        ListenResponse defaultInstance = new ListenResponse();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(ListenResponse.class, defaultInstance);
    }

    public static ListenResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListenResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
