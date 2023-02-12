package com.google.apphosting.datastore.testing;

import com.google.firestore.p002v1.BatchGetDocumentsRequest;
import com.google.firestore.p002v1.BatchGetDocumentsResponse;
import com.google.firestore.p002v1.BatchGetDocumentsResponseOrBuilder;
import com.google.firestore.p002v1.BeginTransactionRequest;
import com.google.firestore.p002v1.BeginTransactionResponse;
import com.google.firestore.p002v1.CommitRequest;
import com.google.firestore.p002v1.CommitResponse;
import com.google.firestore.p002v1.CreateDocumentRequest;
import com.google.firestore.p002v1.DeleteDocumentRequest;
import com.google.firestore.p002v1.Document;
import com.google.firestore.p002v1.GetDocumentRequest;
import com.google.firestore.p002v1.ListCollectionIdsRequest;
import com.google.firestore.p002v1.ListCollectionIdsResponse;
import com.google.firestore.p002v1.ListDocumentsRequest;
import com.google.firestore.p002v1.ListDocumentsResponse;
import com.google.firestore.p002v1.ListenRequest;
import com.google.firestore.p002v1.ListenResponse;
import com.google.firestore.p002v1.RollbackRequest;
import com.google.firestore.p002v1.RunQueryRequest;
import com.google.firestore.p002v1.RunQueryResponse;
import com.google.firestore.p002v1.RunQueryResponseOrBuilder;
import com.google.firestore.p002v1.UpdateDocumentRequest;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Empty;
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

public final class DatastoreTestTrace {

    public interface DatastoreActionOrBuilder extends MessageLiteOrBuilder {
        DatastoreAction.ActionCase getActionCase();

        int getActionId();

        FirestoreV1Action getFirestoreV1Action();

        ValidationRule getValidationRule();

        boolean hasFirestoreV1Action();

        boolean hasValidationRule();
    }

    public interface FirestoreV1ActionOrBuilder extends MessageLiteOrBuilder {
        FirestoreV1Action.ActionCase getActionCase();

        FirestoreV1Action.BatchGetDocuments getBatchGetDocuments();

        FirestoreV1Action.BeginTransaction getBeginTransaction();

        FirestoreV1Action.Commit getCommit();

        FirestoreV1Action.CreateDocument getCreateDocument();

        FirestoreV1Action.RunQuery getDatabaseContentsBeforeAction();

        FirestoreV1Action.DeleteDocument getDeleteDocument();

        FirestoreV1Action.GetDocument getGetDocument();

        FirestoreV1Action.ListCollectionIds getListCollectionIds();

        FirestoreV1Action.ListDocuments getListDocuments();

        FirestoreV1Action.Listen getListen();

        FirestoreV1Action.MatchingDocuments getMatchingDocuments(int i);

        int getMatchingDocumentsCount();

        List<FirestoreV1Action.MatchingDocuments> getMatchingDocumentsList();

        FirestoreV1Action.RemoveListen getRemoveListen();

        FirestoreV1Action.Rollback getRollback();

        FirestoreV1Action.RunQuery getRunQuery();

        StatusProto getStatus();

        FirestoreV1Action.UpdateDocument getUpdateDocument();

        boolean hasBatchGetDocuments();

        boolean hasBeginTransaction();

        boolean hasCommit();

        boolean hasCreateDocument();

        boolean hasDatabaseContentsBeforeAction();

        boolean hasDeleteDocument();

        boolean hasGetDocument();

        boolean hasListCollectionIds();

        boolean hasListDocuments();

        boolean hasListen();

        boolean hasRemoveListen();

        boolean hasRollback();

        boolean hasRunQuery();

        boolean hasStatus();

        boolean hasUpdateDocument();
    }

    public interface ParallelTestTraceOrBuilder extends MessageLiteOrBuilder {
        TestTrace getTestTrace();

        boolean hasTestTrace();
    }

    public interface StatusProtoOrBuilder extends MessageLiteOrBuilder {
        int getCanonicalCode();

        int getCode();

        String getMessage();

        ByteString getMessageBytes();

        String getSpace();

        ByteString getSpaceBytes();
    }

    public interface TestTraceOrBuilder extends MessageLiteOrBuilder {
        DatastoreAction getAction(int i);

        int getActionCount();

        List<DatastoreAction> getActionList();

        String getTraceDescription();

        ByteString getTraceDescriptionBytes();

        String getTraceId();

        ByteString getTraceIdBytes();
    }

    public interface TimelineTestTraceOrBuilder extends MessageLiteOrBuilder {
        TestTrace getTestTrace();

        boolean hasTestTrace();
    }

    public interface ValidationRuleOrBuilder extends MessageLiteOrBuilder {
        boolean getValidateQueryIndexes();

        boolean getValidateQueryResultOrder();
    }

    private DatastoreTestTrace() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static final class StatusProto extends GeneratedMessageLite<StatusProto, Builder> implements StatusProtoOrBuilder {
        public static final int CANONICAL_CODE_FIELD_NUMBER = 6;
        public static final int CODE_FIELD_NUMBER = 1;
        /* access modifiers changed from: private */
        public static final StatusProto DEFAULT_INSTANCE;
        public static final int MESSAGE_FIELD_NUMBER = 3;
        private static volatile Parser<StatusProto> PARSER = null;
        public static final int SPACE_FIELD_NUMBER = 2;
        private int canonicalCode_;
        private int code_;
        private String message_ = "";
        private String space_ = "";

        private StatusProto() {
        }

        public int getCode() {
            return this.code_;
        }

        /* access modifiers changed from: private */
        public void setCode(int value) {
            this.code_ = value;
        }

        /* access modifiers changed from: private */
        public void clearCode() {
            this.code_ = 0;
        }

        public String getSpace() {
            return this.space_;
        }

        public ByteString getSpaceBytes() {
            return ByteString.copyFromUtf8(this.space_);
        }

        /* access modifiers changed from: private */
        public void setSpace(String value) {
            value.getClass();
            this.space_ = value;
        }

        /* access modifiers changed from: private */
        public void clearSpace() {
            this.space_ = getDefaultInstance().getSpace();
        }

        /* access modifiers changed from: private */
        public void setSpaceBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.space_ = value.toStringUtf8();
        }

        public String getMessage() {
            return this.message_;
        }

        public ByteString getMessageBytes() {
            return ByteString.copyFromUtf8(this.message_);
        }

        /* access modifiers changed from: private */
        public void setMessage(String value) {
            value.getClass();
            this.message_ = value;
        }

        /* access modifiers changed from: private */
        public void clearMessage() {
            this.message_ = getDefaultInstance().getMessage();
        }

        /* access modifiers changed from: private */
        public void setMessageBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.message_ = value.toStringUtf8();
        }

        public int getCanonicalCode() {
            return this.canonicalCode_;
        }

        /* access modifiers changed from: private */
        public void setCanonicalCode(int value) {
            this.canonicalCode_ = value;
        }

        /* access modifiers changed from: private */
        public void clearCanonicalCode() {
            this.canonicalCode_ = 0;
        }

        public static StatusProto parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StatusProto parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StatusProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StatusProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StatusProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StatusProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StatusProto parseFrom(InputStream input) throws IOException {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StatusProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StatusProto parseDelimitedFrom(InputStream input) throws IOException {
            return (StatusProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static StatusProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StatusProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StatusProto parseFrom(CodedInputStream input) throws IOException {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StatusProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(StatusProto prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<StatusProto, Builder> implements StatusProtoOrBuilder {
            /* synthetic */ Builder(C00551 x0) {
                this();
            }

            private Builder() {
                super(StatusProto.DEFAULT_INSTANCE);
            }

            public int getCode() {
                return ((StatusProto) this.instance).getCode();
            }

            public Builder setCode(int value) {
                copyOnWrite();
                ((StatusProto) this.instance).setCode(value);
                return this;
            }

            public Builder clearCode() {
                copyOnWrite();
                ((StatusProto) this.instance).clearCode();
                return this;
            }

            public String getSpace() {
                return ((StatusProto) this.instance).getSpace();
            }

            public ByteString getSpaceBytes() {
                return ((StatusProto) this.instance).getSpaceBytes();
            }

            public Builder setSpace(String value) {
                copyOnWrite();
                ((StatusProto) this.instance).setSpace(value);
                return this;
            }

            public Builder clearSpace() {
                copyOnWrite();
                ((StatusProto) this.instance).clearSpace();
                return this;
            }

            public Builder setSpaceBytes(ByteString value) {
                copyOnWrite();
                ((StatusProto) this.instance).setSpaceBytes(value);
                return this;
            }

            public String getMessage() {
                return ((StatusProto) this.instance).getMessage();
            }

            public ByteString getMessageBytes() {
                return ((StatusProto) this.instance).getMessageBytes();
            }

            public Builder setMessage(String value) {
                copyOnWrite();
                ((StatusProto) this.instance).setMessage(value);
                return this;
            }

            public Builder clearMessage() {
                copyOnWrite();
                ((StatusProto) this.instance).clearMessage();
                return this;
            }

            public Builder setMessageBytes(ByteString value) {
                copyOnWrite();
                ((StatusProto) this.instance).setMessageBytes(value);
                return this;
            }

            public int getCanonicalCode() {
                return ((StatusProto) this.instance).getCanonicalCode();
            }

            public Builder setCanonicalCode(int value) {
                copyOnWrite();
                ((StatusProto) this.instance).setCanonicalCode(value);
                return this;
            }

            public Builder clearCanonicalCode() {
                copyOnWrite();
                ((StatusProto) this.instance).clearCanonicalCode();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C00551.f47xa1df5c61[method.ordinal()]) {
                case 1:
                    return new StatusProto();
                case 2:
                    return new Builder((C00551) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0006\u0004\u0000\u0000\u0000\u0001\u0004\u0002Ȉ\u0003Ȉ\u0006\u0004", new Object[]{"code_", "space_", "message_", "canonicalCode_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<StatusProto> parser = PARSER;
                    if (parser == null) {
                        synchronized (StatusProto.class) {
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
            StatusProto defaultInstance = new StatusProto();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(StatusProto.class, defaultInstance);
        }

        public static StatusProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<StatusProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.apphosting.datastore.testing.DatastoreTestTrace$1 */
    static /* synthetic */ class C00551 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f47xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f47xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f47xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f47xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f47xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f47xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f47xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f47xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public static final class ValidationRule extends GeneratedMessageLite<ValidationRule, Builder> implements ValidationRuleOrBuilder {
        /* access modifiers changed from: private */
        public static final ValidationRule DEFAULT_INSTANCE;
        private static volatile Parser<ValidationRule> PARSER = null;
        public static final int VALIDATE_QUERY_INDEXES_FIELD_NUMBER = 2;
        public static final int VALIDATE_QUERY_RESULT_ORDER_FIELD_NUMBER = 1;
        private boolean validateQueryIndexes_;
        private boolean validateQueryResultOrder_;

        private ValidationRule() {
        }

        public boolean getValidateQueryResultOrder() {
            return this.validateQueryResultOrder_;
        }

        /* access modifiers changed from: private */
        public void setValidateQueryResultOrder(boolean value) {
            this.validateQueryResultOrder_ = value;
        }

        /* access modifiers changed from: private */
        public void clearValidateQueryResultOrder() {
            this.validateQueryResultOrder_ = false;
        }

        public boolean getValidateQueryIndexes() {
            return this.validateQueryIndexes_;
        }

        /* access modifiers changed from: private */
        public void setValidateQueryIndexes(boolean value) {
            this.validateQueryIndexes_ = value;
        }

        /* access modifiers changed from: private */
        public void clearValidateQueryIndexes() {
            this.validateQueryIndexes_ = false;
        }

        public static ValidationRule parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ValidationRule parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ValidationRule parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ValidationRule parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ValidationRule parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ValidationRule parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ValidationRule parseFrom(InputStream input) throws IOException {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ValidationRule parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ValidationRule parseDelimitedFrom(InputStream input) throws IOException {
            return (ValidationRule) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ValidationRule parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ValidationRule) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ValidationRule parseFrom(CodedInputStream input) throws IOException {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ValidationRule parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ValidationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(ValidationRule prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ValidationRule, Builder> implements ValidationRuleOrBuilder {
            /* synthetic */ Builder(C00551 x0) {
                this();
            }

            private Builder() {
                super(ValidationRule.DEFAULT_INSTANCE);
            }

            public boolean getValidateQueryResultOrder() {
                return ((ValidationRule) this.instance).getValidateQueryResultOrder();
            }

            public Builder setValidateQueryResultOrder(boolean value) {
                copyOnWrite();
                ((ValidationRule) this.instance).setValidateQueryResultOrder(value);
                return this;
            }

            public Builder clearValidateQueryResultOrder() {
                copyOnWrite();
                ((ValidationRule) this.instance).clearValidateQueryResultOrder();
                return this;
            }

            public boolean getValidateQueryIndexes() {
                return ((ValidationRule) this.instance).getValidateQueryIndexes();
            }

            public Builder setValidateQueryIndexes(boolean value) {
                copyOnWrite();
                ((ValidationRule) this.instance).setValidateQueryIndexes(value);
                return this;
            }

            public Builder clearValidateQueryIndexes() {
                copyOnWrite();
                ((ValidationRule) this.instance).clearValidateQueryIndexes();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C00551.f47xa1df5c61[method.ordinal()]) {
                case 1:
                    return new ValidationRule();
                case 2:
                    return new Builder((C00551) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0007\u0002\u0007", new Object[]{"validateQueryResultOrder_", "validateQueryIndexes_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<ValidationRule> parser = PARSER;
                    if (parser == null) {
                        synchronized (ValidationRule.class) {
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
            ValidationRule defaultInstance = new ValidationRule();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(ValidationRule.class, defaultInstance);
        }

        public static ValidationRule getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ValidationRule> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class FirestoreV1Action extends GeneratedMessageLite<FirestoreV1Action, Builder> implements FirestoreV1ActionOrBuilder {
        public static final int BATCH_GET_DOCUMENTS_FIELD_NUMBER = 10;
        public static final int BEGIN_TRANSACTION_FIELD_NUMBER = 6;
        public static final int COMMIT_FIELD_NUMBER = 7;
        public static final int CREATE_DOCUMENT_FIELD_NUMBER = 3;
        public static final int DATABASE_CONTENTS_BEFORE_ACTION_FIELD_NUMBER = 202;
        /* access modifiers changed from: private */
        public static final FirestoreV1Action DEFAULT_INSTANCE;
        public static final int DELETE_DOCUMENT_FIELD_NUMBER = 5;
        public static final int GET_DOCUMENT_FIELD_NUMBER = 1;
        public static final int LISTEN_FIELD_NUMBER = 12;
        public static final int LIST_COLLECTION_IDS_FIELD_NUMBER = 9;
        public static final int LIST_DOCUMENTS_FIELD_NUMBER = 2;
        public static final int MATCHING_DOCUMENTS_FIELD_NUMBER = 203;
        private static volatile Parser<FirestoreV1Action> PARSER = null;
        public static final int REMOVE_LISTEN_FIELD_NUMBER = 13;
        public static final int ROLLBACK_FIELD_NUMBER = 8;
        public static final int RUN_QUERY_FIELD_NUMBER = 11;
        public static final int STATUS_FIELD_NUMBER = 201;
        public static final int UPDATE_DOCUMENT_FIELD_NUMBER = 4;
        private int actionCase_ = 0;
        private Object action_;
        private RunQuery databaseContentsBeforeAction_;
        private Internal.ProtobufList<MatchingDocuments> matchingDocuments_ = emptyProtobufList();
        private StatusProto status_;

        public interface BatchGetDocumentsOrBuilder extends MessageLiteOrBuilder {
            BatchGetDocumentsRequest getRequest();

            BatchGetDocumentsResponse getResponse(int i);

            int getResponseCount();

            List<BatchGetDocumentsResponse> getResponseList();

            boolean hasRequest();
        }

        public interface BeginTransactionOrBuilder extends MessageLiteOrBuilder {
            BeginTransactionRequest getRequest();

            BeginTransactionResponse getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public interface CommitOrBuilder extends MessageLiteOrBuilder {
            CommitRequest getRequest();

            CommitResponse getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public interface CreateDocumentOrBuilder extends MessageLiteOrBuilder {
            CreateDocumentRequest getRequest();

            Document getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public interface DeleteDocumentOrBuilder extends MessageLiteOrBuilder {
            DeleteDocumentRequest getRequest();

            Empty getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public interface GetDocumentOrBuilder extends MessageLiteOrBuilder {
            GetDocumentRequest getRequest();

            Document getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public interface ListCollectionIdsOrBuilder extends MessageLiteOrBuilder {
            ListCollectionIdsRequest getRequest();

            ListCollectionIdsResponse getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public interface ListDocumentsOrBuilder extends MessageLiteOrBuilder {
            ListDocumentsRequest getRequest();

            ListDocumentsResponse getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public interface ListenOrBuilder extends MessageLiteOrBuilder {
            ListenRequest getRequest();

            ListenResponse getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public interface MatchingDocumentsOrBuilder extends MessageLiteOrBuilder {
            ListenResponse getListenResponse();

            RunQueryResponse getMatchingDocuments();

            boolean hasListenResponse();

            boolean hasMatchingDocuments();
        }

        public interface RemoveListenOrBuilder extends MessageLiteOrBuilder {
            int getTargetId();
        }

        public interface RollbackOrBuilder extends MessageLiteOrBuilder {
            RollbackRequest getRequest();

            Empty getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        public interface RunQueryOrBuilder extends MessageLiteOrBuilder {
            RunQueryRequest getRequest();

            RunQueryResponse getResponse(int i);

            int getResponseCount();

            List<RunQueryResponse> getResponseList();

            boolean hasRequest();
        }

        public interface UpdateDocumentOrBuilder extends MessageLiteOrBuilder {
            UpdateDocumentRequest getRequest();

            Document getResponse();

            boolean hasRequest();

            boolean hasResponse();
        }

        private FirestoreV1Action() {
        }

        public static final class GetDocument extends GeneratedMessageLite<GetDocument, Builder> implements GetDocumentOrBuilder {
            /* access modifiers changed from: private */
            public static final GetDocument DEFAULT_INSTANCE;
            private static volatile Parser<GetDocument> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private GetDocumentRequest request_;
            private Document response_;

            private GetDocument() {
            }

            public boolean hasRequest() {
                return this.request_ != null;
            }

            public GetDocumentRequest getRequest() {
                GetDocumentRequest getDocumentRequest = this.request_;
                return getDocumentRequest == null ? GetDocumentRequest.getDefaultInstance() : getDocumentRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(GetDocumentRequest value) {
                value.getClass();
                this.request_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(GetDocumentRequest value) {
                value.getClass();
                GetDocumentRequest getDocumentRequest = this.request_;
                if (getDocumentRequest == null || getDocumentRequest == GetDocumentRequest.getDefaultInstance()) {
                    this.request_ = value;
                } else {
                    this.request_ = (GetDocumentRequest) ((GetDocumentRequest.Builder) GetDocumentRequest.newBuilder(this.request_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
            }

            public boolean hasResponse() {
                return this.response_ != null;
            }

            public Document getResponse() {
                Document document = this.response_;
                return document == null ? Document.getDefaultInstance() : document;
            }

            /* access modifiers changed from: private */
            public void setResponse(Document value) {
                value.getClass();
                this.response_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeResponse(Document value) {
                value.getClass();
                Document document = this.response_;
                if (document == null || document == Document.getDefaultInstance()) {
                    this.response_ = value;
                } else {
                    this.response_ = (Document) ((Document.Builder) Document.newBuilder(this.response_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = null;
            }

            public static GetDocument parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static GetDocument parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static GetDocument parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static GetDocument parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static GetDocument parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static GetDocument parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static GetDocument parseFrom(InputStream input) throws IOException {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static GetDocument parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static GetDocument parseDelimitedFrom(InputStream input) throws IOException {
                return (GetDocument) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static GetDocument parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (GetDocument) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static GetDocument parseFrom(CodedInputStream input) throws IOException {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static GetDocument parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (GetDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(GetDocument prototype) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<GetDocument, Builder> implements GetDocumentOrBuilder {
                /* synthetic */ Builder(C00551 x0) {
                    this();
                }

                private Builder() {
                    super(GetDocument.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((GetDocument) this.instance).hasRequest();
                }

                public GetDocumentRequest getRequest() {
                    return ((GetDocument) this.instance).getRequest();
                }

                public Builder setRequest(GetDocumentRequest value) {
                    copyOnWrite();
                    ((GetDocument) this.instance).setRequest(value);
                    return this;
                }

                public Builder setRequest(GetDocumentRequest.Builder builderForValue) {
                    copyOnWrite();
                    ((GetDocument) this.instance).setRequest((GetDocumentRequest) builderForValue.build());
                    return this;
                }

                public Builder mergeRequest(GetDocumentRequest value) {
                    copyOnWrite();
                    ((GetDocument) this.instance).mergeRequest(value);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((GetDocument) this.instance).clearRequest();
                    return this;
                }

                public boolean hasResponse() {
                    return ((GetDocument) this.instance).hasResponse();
                }

                public Document getResponse() {
                    return ((GetDocument) this.instance).getResponse();
                }

                public Builder setResponse(Document value) {
                    copyOnWrite();
                    ((GetDocument) this.instance).setResponse(value);
                    return this;
                }

                public Builder setResponse(Document.Builder builderForValue) {
                    copyOnWrite();
                    ((GetDocument) this.instance).setResponse((Document) builderForValue.build());
                    return this;
                }

                public Builder mergeResponse(Document value) {
                    copyOnWrite();
                    ((GetDocument) this.instance).mergeResponse(value);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((GetDocument) this.instance).clearResponse();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (C00551.f47xa1df5c61[method.ordinal()]) {
                    case 1:
                        return new GetDocument();
                    case 2:
                        return new Builder((C00551) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"request_", "response_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<GetDocument> parser = PARSER;
                        if (parser == null) {
                            synchronized (GetDocument.class) {
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
                GetDocument defaultInstance = new GetDocument();
                DEFAULT_INSTANCE = defaultInstance;
                GeneratedMessageLite.registerDefaultInstance(GetDocument.class, defaultInstance);
            }

            public static GetDocument getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<GetDocument> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class ListDocuments extends GeneratedMessageLite<ListDocuments, Builder> implements ListDocumentsOrBuilder {
            /* access modifiers changed from: private */
            public static final ListDocuments DEFAULT_INSTANCE;
            private static volatile Parser<ListDocuments> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private ListDocumentsRequest request_;
            private ListDocumentsResponse response_;

            private ListDocuments() {
            }

            public boolean hasRequest() {
                return this.request_ != null;
            }

            public ListDocumentsRequest getRequest() {
                ListDocumentsRequest listDocumentsRequest = this.request_;
                return listDocumentsRequest == null ? ListDocumentsRequest.getDefaultInstance() : listDocumentsRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(ListDocumentsRequest value) {
                value.getClass();
                this.request_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(ListDocumentsRequest value) {
                value.getClass();
                ListDocumentsRequest listDocumentsRequest = this.request_;
                if (listDocumentsRequest == null || listDocumentsRequest == ListDocumentsRequest.getDefaultInstance()) {
                    this.request_ = value;
                } else {
                    this.request_ = (ListDocumentsRequest) ((ListDocumentsRequest.Builder) ListDocumentsRequest.newBuilder(this.request_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
            }

            public boolean hasResponse() {
                return this.response_ != null;
            }

            public ListDocumentsResponse getResponse() {
                ListDocumentsResponse listDocumentsResponse = this.response_;
                return listDocumentsResponse == null ? ListDocumentsResponse.getDefaultInstance() : listDocumentsResponse;
            }

            /* access modifiers changed from: private */
            public void setResponse(ListDocumentsResponse value) {
                value.getClass();
                this.response_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeResponse(ListDocumentsResponse value) {
                value.getClass();
                ListDocumentsResponse listDocumentsResponse = this.response_;
                if (listDocumentsResponse == null || listDocumentsResponse == ListDocumentsResponse.getDefaultInstance()) {
                    this.response_ = value;
                } else {
                    this.response_ = (ListDocumentsResponse) ((ListDocumentsResponse.Builder) ListDocumentsResponse.newBuilder(this.response_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = null;
            }

            public static ListDocuments parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static ListDocuments parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static ListDocuments parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static ListDocuments parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static ListDocuments parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static ListDocuments parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static ListDocuments parseFrom(InputStream input) throws IOException {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static ListDocuments parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static ListDocuments parseDelimitedFrom(InputStream input) throws IOException {
                return (ListDocuments) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static ListDocuments parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ListDocuments) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static ListDocuments parseFrom(CodedInputStream input) throws IOException {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static ListDocuments parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ListDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(ListDocuments prototype) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<ListDocuments, Builder> implements ListDocumentsOrBuilder {
                /* synthetic */ Builder(C00551 x0) {
                    this();
                }

                private Builder() {
                    super(ListDocuments.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((ListDocuments) this.instance).hasRequest();
                }

                public ListDocumentsRequest getRequest() {
                    return ((ListDocuments) this.instance).getRequest();
                }

                public Builder setRequest(ListDocumentsRequest value) {
                    copyOnWrite();
                    ((ListDocuments) this.instance).setRequest(value);
                    return this;
                }

                public Builder setRequest(ListDocumentsRequest.Builder builderForValue) {
                    copyOnWrite();
                    ((ListDocuments) this.instance).setRequest((ListDocumentsRequest) builderForValue.build());
                    return this;
                }

                public Builder mergeRequest(ListDocumentsRequest value) {
                    copyOnWrite();
                    ((ListDocuments) this.instance).mergeRequest(value);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((ListDocuments) this.instance).clearRequest();
                    return this;
                }

                public boolean hasResponse() {
                    return ((ListDocuments) this.instance).hasResponse();
                }

                public ListDocumentsResponse getResponse() {
                    return ((ListDocuments) this.instance).getResponse();
                }

                public Builder setResponse(ListDocumentsResponse value) {
                    copyOnWrite();
                    ((ListDocuments) this.instance).setResponse(value);
                    return this;
                }

                public Builder setResponse(ListDocumentsResponse.Builder builderForValue) {
                    copyOnWrite();
                    ((ListDocuments) this.instance).setResponse((ListDocumentsResponse) builderForValue.build());
                    return this;
                }

                public Builder mergeResponse(ListDocumentsResponse value) {
                    copyOnWrite();
                    ((ListDocuments) this.instance).mergeResponse(value);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((ListDocuments) this.instance).clearResponse();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (C00551.f47xa1df5c61[method.ordinal()]) {
                    case 1:
                        return new ListDocuments();
                    case 2:
                        return new Builder((C00551) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"request_", "response_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<ListDocuments> parser = PARSER;
                        if (parser == null) {
                            synchronized (ListDocuments.class) {
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
                ListDocuments defaultInstance = new ListDocuments();
                DEFAULT_INSTANCE = defaultInstance;
                GeneratedMessageLite.registerDefaultInstance(ListDocuments.class, defaultInstance);
            }

            public static ListDocuments getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<ListDocuments> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class CreateDocument extends GeneratedMessageLite<CreateDocument, Builder> implements CreateDocumentOrBuilder {
            /* access modifiers changed from: private */
            public static final CreateDocument DEFAULT_INSTANCE;
            private static volatile Parser<CreateDocument> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private CreateDocumentRequest request_;
            private Document response_;

            private CreateDocument() {
            }

            public boolean hasRequest() {
                return this.request_ != null;
            }

            public CreateDocumentRequest getRequest() {
                CreateDocumentRequest createDocumentRequest = this.request_;
                return createDocumentRequest == null ? CreateDocumentRequest.getDefaultInstance() : createDocumentRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(CreateDocumentRequest value) {
                value.getClass();
                this.request_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(CreateDocumentRequest value) {
                value.getClass();
                CreateDocumentRequest createDocumentRequest = this.request_;
                if (createDocumentRequest == null || createDocumentRequest == CreateDocumentRequest.getDefaultInstance()) {
                    this.request_ = value;
                } else {
                    this.request_ = (CreateDocumentRequest) ((CreateDocumentRequest.Builder) CreateDocumentRequest.newBuilder(this.request_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
            }

            public boolean hasResponse() {
                return this.response_ != null;
            }

            public Document getResponse() {
                Document document = this.response_;
                return document == null ? Document.getDefaultInstance() : document;
            }

            /* access modifiers changed from: private */
            public void setResponse(Document value) {
                value.getClass();
                this.response_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeResponse(Document value) {
                value.getClass();
                Document document = this.response_;
                if (document == null || document == Document.getDefaultInstance()) {
                    this.response_ = value;
                } else {
                    this.response_ = (Document) ((Document.Builder) Document.newBuilder(this.response_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = null;
            }

            public static CreateDocument parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static CreateDocument parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static CreateDocument parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static CreateDocument parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static CreateDocument parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static CreateDocument parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static CreateDocument parseFrom(InputStream input) throws IOException {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static CreateDocument parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static CreateDocument parseDelimitedFrom(InputStream input) throws IOException {
                return (CreateDocument) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static CreateDocument parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (CreateDocument) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static CreateDocument parseFrom(CodedInputStream input) throws IOException {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static CreateDocument parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (CreateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(CreateDocument prototype) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<CreateDocument, Builder> implements CreateDocumentOrBuilder {
                /* synthetic */ Builder(C00551 x0) {
                    this();
                }

                private Builder() {
                    super(CreateDocument.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((CreateDocument) this.instance).hasRequest();
                }

                public CreateDocumentRequest getRequest() {
                    return ((CreateDocument) this.instance).getRequest();
                }

                public Builder setRequest(CreateDocumentRequest value) {
                    copyOnWrite();
                    ((CreateDocument) this.instance).setRequest(value);
                    return this;
                }

                public Builder setRequest(CreateDocumentRequest.Builder builderForValue) {
                    copyOnWrite();
                    ((CreateDocument) this.instance).setRequest((CreateDocumentRequest) builderForValue.build());
                    return this;
                }

                public Builder mergeRequest(CreateDocumentRequest value) {
                    copyOnWrite();
                    ((CreateDocument) this.instance).mergeRequest(value);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((CreateDocument) this.instance).clearRequest();
                    return this;
                }

                public boolean hasResponse() {
                    return ((CreateDocument) this.instance).hasResponse();
                }

                public Document getResponse() {
                    return ((CreateDocument) this.instance).getResponse();
                }

                public Builder setResponse(Document value) {
                    copyOnWrite();
                    ((CreateDocument) this.instance).setResponse(value);
                    return this;
                }

                public Builder setResponse(Document.Builder builderForValue) {
                    copyOnWrite();
                    ((CreateDocument) this.instance).setResponse((Document) builderForValue.build());
                    return this;
                }

                public Builder mergeResponse(Document value) {
                    copyOnWrite();
                    ((CreateDocument) this.instance).mergeResponse(value);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((CreateDocument) this.instance).clearResponse();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (C00551.f47xa1df5c61[method.ordinal()]) {
                    case 1:
                        return new CreateDocument();
                    case 2:
                        return new Builder((C00551) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"request_", "response_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<CreateDocument> parser = PARSER;
                        if (parser == null) {
                            synchronized (CreateDocument.class) {
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
                CreateDocument defaultInstance = new CreateDocument();
                DEFAULT_INSTANCE = defaultInstance;
                GeneratedMessageLite.registerDefaultInstance(CreateDocument.class, defaultInstance);
            }

            public static CreateDocument getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<CreateDocument> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class UpdateDocument extends GeneratedMessageLite<UpdateDocument, Builder> implements UpdateDocumentOrBuilder {
            /* access modifiers changed from: private */
            public static final UpdateDocument DEFAULT_INSTANCE;
            private static volatile Parser<UpdateDocument> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private UpdateDocumentRequest request_;
            private Document response_;

            private UpdateDocument() {
            }

            public boolean hasRequest() {
                return this.request_ != null;
            }

            public UpdateDocumentRequest getRequest() {
                UpdateDocumentRequest updateDocumentRequest = this.request_;
                return updateDocumentRequest == null ? UpdateDocumentRequest.getDefaultInstance() : updateDocumentRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(UpdateDocumentRequest value) {
                value.getClass();
                this.request_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(UpdateDocumentRequest value) {
                value.getClass();
                UpdateDocumentRequest updateDocumentRequest = this.request_;
                if (updateDocumentRequest == null || updateDocumentRequest == UpdateDocumentRequest.getDefaultInstance()) {
                    this.request_ = value;
                } else {
                    this.request_ = (UpdateDocumentRequest) ((UpdateDocumentRequest.Builder) UpdateDocumentRequest.newBuilder(this.request_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
            }

            public boolean hasResponse() {
                return this.response_ != null;
            }

            public Document getResponse() {
                Document document = this.response_;
                return document == null ? Document.getDefaultInstance() : document;
            }

            /* access modifiers changed from: private */
            public void setResponse(Document value) {
                value.getClass();
                this.response_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeResponse(Document value) {
                value.getClass();
                Document document = this.response_;
                if (document == null || document == Document.getDefaultInstance()) {
                    this.response_ = value;
                } else {
                    this.response_ = (Document) ((Document.Builder) Document.newBuilder(this.response_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = null;
            }

            public static UpdateDocument parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static UpdateDocument parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static UpdateDocument parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static UpdateDocument parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static UpdateDocument parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static UpdateDocument parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static UpdateDocument parseFrom(InputStream input) throws IOException {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static UpdateDocument parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static UpdateDocument parseDelimitedFrom(InputStream input) throws IOException {
                return (UpdateDocument) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static UpdateDocument parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (UpdateDocument) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static UpdateDocument parseFrom(CodedInputStream input) throws IOException {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static UpdateDocument parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (UpdateDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(UpdateDocument prototype) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<UpdateDocument, Builder> implements UpdateDocumentOrBuilder {
                /* synthetic */ Builder(C00551 x0) {
                    this();
                }

                private Builder() {
                    super(UpdateDocument.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((UpdateDocument) this.instance).hasRequest();
                }

                public UpdateDocumentRequest getRequest() {
                    return ((UpdateDocument) this.instance).getRequest();
                }

                public Builder setRequest(UpdateDocumentRequest value) {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).setRequest(value);
                    return this;
                }

                public Builder setRequest(UpdateDocumentRequest.Builder builderForValue) {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).setRequest((UpdateDocumentRequest) builderForValue.build());
                    return this;
                }

                public Builder mergeRequest(UpdateDocumentRequest value) {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).mergeRequest(value);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).clearRequest();
                    return this;
                }

                public boolean hasResponse() {
                    return ((UpdateDocument) this.instance).hasResponse();
                }

                public Document getResponse() {
                    return ((UpdateDocument) this.instance).getResponse();
                }

                public Builder setResponse(Document value) {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).setResponse(value);
                    return this;
                }

                public Builder setResponse(Document.Builder builderForValue) {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).setResponse((Document) builderForValue.build());
                    return this;
                }

                public Builder mergeResponse(Document value) {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).mergeResponse(value);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((UpdateDocument) this.instance).clearResponse();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (C00551.f47xa1df5c61[method.ordinal()]) {
                    case 1:
                        return new UpdateDocument();
                    case 2:
                        return new Builder((C00551) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"request_", "response_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<UpdateDocument> parser = PARSER;
                        if (parser == null) {
                            synchronized (UpdateDocument.class) {
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
                UpdateDocument defaultInstance = new UpdateDocument();
                DEFAULT_INSTANCE = defaultInstance;
                GeneratedMessageLite.registerDefaultInstance(UpdateDocument.class, defaultInstance);
            }

            public static UpdateDocument getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<UpdateDocument> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class DeleteDocument extends GeneratedMessageLite<DeleteDocument, Builder> implements DeleteDocumentOrBuilder {
            /* access modifiers changed from: private */
            public static final DeleteDocument DEFAULT_INSTANCE;
            private static volatile Parser<DeleteDocument> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private DeleteDocumentRequest request_;
            private Empty response_;

            private DeleteDocument() {
            }

            public boolean hasRequest() {
                return this.request_ != null;
            }

            public DeleteDocumentRequest getRequest() {
                DeleteDocumentRequest deleteDocumentRequest = this.request_;
                return deleteDocumentRequest == null ? DeleteDocumentRequest.getDefaultInstance() : deleteDocumentRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(DeleteDocumentRequest value) {
                value.getClass();
                this.request_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(DeleteDocumentRequest value) {
                value.getClass();
                DeleteDocumentRequest deleteDocumentRequest = this.request_;
                if (deleteDocumentRequest == null || deleteDocumentRequest == DeleteDocumentRequest.getDefaultInstance()) {
                    this.request_ = value;
                } else {
                    this.request_ = (DeleteDocumentRequest) ((DeleteDocumentRequest.Builder) DeleteDocumentRequest.newBuilder(this.request_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
            }

            public boolean hasResponse() {
                return this.response_ != null;
            }

            public Empty getResponse() {
                Empty empty = this.response_;
                return empty == null ? Empty.getDefaultInstance() : empty;
            }

            /* access modifiers changed from: private */
            public void setResponse(Empty value) {
                value.getClass();
                this.response_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeResponse(Empty value) {
                value.getClass();
                Empty empty = this.response_;
                if (empty == null || empty == Empty.getDefaultInstance()) {
                    this.response_ = value;
                } else {
                    this.response_ = (Empty) ((Empty.Builder) Empty.newBuilder(this.response_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = null;
            }

            public static DeleteDocument parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static DeleteDocument parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static DeleteDocument parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static DeleteDocument parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static DeleteDocument parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static DeleteDocument parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static DeleteDocument parseFrom(InputStream input) throws IOException {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static DeleteDocument parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static DeleteDocument parseDelimitedFrom(InputStream input) throws IOException {
                return (DeleteDocument) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static DeleteDocument parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (DeleteDocument) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static DeleteDocument parseFrom(CodedInputStream input) throws IOException {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static DeleteDocument parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (DeleteDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(DeleteDocument prototype) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<DeleteDocument, Builder> implements DeleteDocumentOrBuilder {
                /* synthetic */ Builder(C00551 x0) {
                    this();
                }

                private Builder() {
                    super(DeleteDocument.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((DeleteDocument) this.instance).hasRequest();
                }

                public DeleteDocumentRequest getRequest() {
                    return ((DeleteDocument) this.instance).getRequest();
                }

                public Builder setRequest(DeleteDocumentRequest value) {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).setRequest(value);
                    return this;
                }

                public Builder setRequest(DeleteDocumentRequest.Builder builderForValue) {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).setRequest((DeleteDocumentRequest) builderForValue.build());
                    return this;
                }

                public Builder mergeRequest(DeleteDocumentRequest value) {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).mergeRequest(value);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).clearRequest();
                    return this;
                }

                public boolean hasResponse() {
                    return ((DeleteDocument) this.instance).hasResponse();
                }

                public Empty getResponse() {
                    return ((DeleteDocument) this.instance).getResponse();
                }

                public Builder setResponse(Empty value) {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).setResponse(value);
                    return this;
                }

                public Builder setResponse(Empty.Builder builderForValue) {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).setResponse((Empty) builderForValue.build());
                    return this;
                }

                public Builder mergeResponse(Empty value) {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).mergeResponse(value);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((DeleteDocument) this.instance).clearResponse();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (C00551.f47xa1df5c61[method.ordinal()]) {
                    case 1:
                        return new DeleteDocument();
                    case 2:
                        return new Builder((C00551) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"request_", "response_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<DeleteDocument> parser = PARSER;
                        if (parser == null) {
                            synchronized (DeleteDocument.class) {
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
                DeleteDocument defaultInstance = new DeleteDocument();
                DEFAULT_INSTANCE = defaultInstance;
                GeneratedMessageLite.registerDefaultInstance(DeleteDocument.class, defaultInstance);
            }

            public static DeleteDocument getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<DeleteDocument> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class BeginTransaction extends GeneratedMessageLite<BeginTransaction, Builder> implements BeginTransactionOrBuilder {
            /* access modifiers changed from: private */
            public static final BeginTransaction DEFAULT_INSTANCE;
            private static volatile Parser<BeginTransaction> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private BeginTransactionRequest request_;
            private BeginTransactionResponse response_;

            private BeginTransaction() {
            }

            public boolean hasRequest() {
                return this.request_ != null;
            }

            public BeginTransactionRequest getRequest() {
                BeginTransactionRequest beginTransactionRequest = this.request_;
                return beginTransactionRequest == null ? BeginTransactionRequest.getDefaultInstance() : beginTransactionRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(BeginTransactionRequest value) {
                value.getClass();
                this.request_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(BeginTransactionRequest value) {
                value.getClass();
                BeginTransactionRequest beginTransactionRequest = this.request_;
                if (beginTransactionRequest == null || beginTransactionRequest == BeginTransactionRequest.getDefaultInstance()) {
                    this.request_ = value;
                } else {
                    this.request_ = (BeginTransactionRequest) ((BeginTransactionRequest.Builder) BeginTransactionRequest.newBuilder(this.request_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
            }

            public boolean hasResponse() {
                return this.response_ != null;
            }

            public BeginTransactionResponse getResponse() {
                BeginTransactionResponse beginTransactionResponse = this.response_;
                return beginTransactionResponse == null ? BeginTransactionResponse.getDefaultInstance() : beginTransactionResponse;
            }

            /* access modifiers changed from: private */
            public void setResponse(BeginTransactionResponse value) {
                value.getClass();
                this.response_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeResponse(BeginTransactionResponse value) {
                value.getClass();
                BeginTransactionResponse beginTransactionResponse = this.response_;
                if (beginTransactionResponse == null || beginTransactionResponse == BeginTransactionResponse.getDefaultInstance()) {
                    this.response_ = value;
                } else {
                    this.response_ = (BeginTransactionResponse) ((BeginTransactionResponse.Builder) BeginTransactionResponse.newBuilder(this.response_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = null;
            }

            public static BeginTransaction parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static BeginTransaction parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static BeginTransaction parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static BeginTransaction parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static BeginTransaction parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static BeginTransaction parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static BeginTransaction parseFrom(InputStream input) throws IOException {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static BeginTransaction parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static BeginTransaction parseDelimitedFrom(InputStream input) throws IOException {
                return (BeginTransaction) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static BeginTransaction parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (BeginTransaction) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static BeginTransaction parseFrom(CodedInputStream input) throws IOException {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static BeginTransaction parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (BeginTransaction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(BeginTransaction prototype) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<BeginTransaction, Builder> implements BeginTransactionOrBuilder {
                /* synthetic */ Builder(C00551 x0) {
                    this();
                }

                private Builder() {
                    super(BeginTransaction.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((BeginTransaction) this.instance).hasRequest();
                }

                public BeginTransactionRequest getRequest() {
                    return ((BeginTransaction) this.instance).getRequest();
                }

                public Builder setRequest(BeginTransactionRequest value) {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).setRequest(value);
                    return this;
                }

                public Builder setRequest(BeginTransactionRequest.Builder builderForValue) {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).setRequest((BeginTransactionRequest) builderForValue.build());
                    return this;
                }

                public Builder mergeRequest(BeginTransactionRequest value) {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).mergeRequest(value);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).clearRequest();
                    return this;
                }

                public boolean hasResponse() {
                    return ((BeginTransaction) this.instance).hasResponse();
                }

                public BeginTransactionResponse getResponse() {
                    return ((BeginTransaction) this.instance).getResponse();
                }

                public Builder setResponse(BeginTransactionResponse value) {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).setResponse(value);
                    return this;
                }

                public Builder setResponse(BeginTransactionResponse.Builder builderForValue) {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).setResponse((BeginTransactionResponse) builderForValue.build());
                    return this;
                }

                public Builder mergeResponse(BeginTransactionResponse value) {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).mergeResponse(value);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((BeginTransaction) this.instance).clearResponse();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (C00551.f47xa1df5c61[method.ordinal()]) {
                    case 1:
                        return new BeginTransaction();
                    case 2:
                        return new Builder((C00551) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"request_", "response_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<BeginTransaction> parser = PARSER;
                        if (parser == null) {
                            synchronized (BeginTransaction.class) {
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
                BeginTransaction defaultInstance = new BeginTransaction();
                DEFAULT_INSTANCE = defaultInstance;
                GeneratedMessageLite.registerDefaultInstance(BeginTransaction.class, defaultInstance);
            }

            public static BeginTransaction getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<BeginTransaction> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class Commit extends GeneratedMessageLite<Commit, Builder> implements CommitOrBuilder {
            /* access modifiers changed from: private */
            public static final Commit DEFAULT_INSTANCE;
            private static volatile Parser<Commit> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private CommitRequest request_;
            private CommitResponse response_;

            private Commit() {
            }

            public boolean hasRequest() {
                return this.request_ != null;
            }

            public CommitRequest getRequest() {
                CommitRequest commitRequest = this.request_;
                return commitRequest == null ? CommitRequest.getDefaultInstance() : commitRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(CommitRequest value) {
                value.getClass();
                this.request_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(CommitRequest value) {
                value.getClass();
                CommitRequest commitRequest = this.request_;
                if (commitRequest == null || commitRequest == CommitRequest.getDefaultInstance()) {
                    this.request_ = value;
                } else {
                    this.request_ = (CommitRequest) ((CommitRequest.Builder) CommitRequest.newBuilder(this.request_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
            }

            public boolean hasResponse() {
                return this.response_ != null;
            }

            public CommitResponse getResponse() {
                CommitResponse commitResponse = this.response_;
                return commitResponse == null ? CommitResponse.getDefaultInstance() : commitResponse;
            }

            /* access modifiers changed from: private */
            public void setResponse(CommitResponse value) {
                value.getClass();
                this.response_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeResponse(CommitResponse value) {
                value.getClass();
                CommitResponse commitResponse = this.response_;
                if (commitResponse == null || commitResponse == CommitResponse.getDefaultInstance()) {
                    this.response_ = value;
                } else {
                    this.response_ = (CommitResponse) ((CommitResponse.Builder) CommitResponse.newBuilder(this.response_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = null;
            }

            public static Commit parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Commit parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Commit parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Commit parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Commit parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Commit parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Commit parseFrom(InputStream input) throws IOException {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Commit parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Commit parseDelimitedFrom(InputStream input) throws IOException {
                return (Commit) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Commit parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Commit) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Commit parseFrom(CodedInputStream input) throws IOException {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Commit parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Commit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(Commit prototype) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Commit, Builder> implements CommitOrBuilder {
                /* synthetic */ Builder(C00551 x0) {
                    this();
                }

                private Builder() {
                    super(Commit.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((Commit) this.instance).hasRequest();
                }

                public CommitRequest getRequest() {
                    return ((Commit) this.instance).getRequest();
                }

                public Builder setRequest(CommitRequest value) {
                    copyOnWrite();
                    ((Commit) this.instance).setRequest(value);
                    return this;
                }

                public Builder setRequest(CommitRequest.Builder builderForValue) {
                    copyOnWrite();
                    ((Commit) this.instance).setRequest((CommitRequest) builderForValue.build());
                    return this;
                }

                public Builder mergeRequest(CommitRequest value) {
                    copyOnWrite();
                    ((Commit) this.instance).mergeRequest(value);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((Commit) this.instance).clearRequest();
                    return this;
                }

                public boolean hasResponse() {
                    return ((Commit) this.instance).hasResponse();
                }

                public CommitResponse getResponse() {
                    return ((Commit) this.instance).getResponse();
                }

                public Builder setResponse(CommitResponse value) {
                    copyOnWrite();
                    ((Commit) this.instance).setResponse(value);
                    return this;
                }

                public Builder setResponse(CommitResponse.Builder builderForValue) {
                    copyOnWrite();
                    ((Commit) this.instance).setResponse((CommitResponse) builderForValue.build());
                    return this;
                }

                public Builder mergeResponse(CommitResponse value) {
                    copyOnWrite();
                    ((Commit) this.instance).mergeResponse(value);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((Commit) this.instance).clearResponse();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (C00551.f47xa1df5c61[method.ordinal()]) {
                    case 1:
                        return new Commit();
                    case 2:
                        return new Builder((C00551) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"request_", "response_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<Commit> parser = PARSER;
                        if (parser == null) {
                            synchronized (Commit.class) {
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
                Commit defaultInstance = new Commit();
                DEFAULT_INSTANCE = defaultInstance;
                GeneratedMessageLite.registerDefaultInstance(Commit.class, defaultInstance);
            }

            public static Commit getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Commit> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class Rollback extends GeneratedMessageLite<Rollback, Builder> implements RollbackOrBuilder {
            /* access modifiers changed from: private */
            public static final Rollback DEFAULT_INSTANCE;
            private static volatile Parser<Rollback> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private RollbackRequest request_;
            private Empty response_;

            private Rollback() {
            }

            public boolean hasRequest() {
                return this.request_ != null;
            }

            public RollbackRequest getRequest() {
                RollbackRequest rollbackRequest = this.request_;
                return rollbackRequest == null ? RollbackRequest.getDefaultInstance() : rollbackRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(RollbackRequest value) {
                value.getClass();
                this.request_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(RollbackRequest value) {
                value.getClass();
                RollbackRequest rollbackRequest = this.request_;
                if (rollbackRequest == null || rollbackRequest == RollbackRequest.getDefaultInstance()) {
                    this.request_ = value;
                } else {
                    this.request_ = (RollbackRequest) ((RollbackRequest.Builder) RollbackRequest.newBuilder(this.request_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
            }

            public boolean hasResponse() {
                return this.response_ != null;
            }

            public Empty getResponse() {
                Empty empty = this.response_;
                return empty == null ? Empty.getDefaultInstance() : empty;
            }

            /* access modifiers changed from: private */
            public void setResponse(Empty value) {
                value.getClass();
                this.response_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeResponse(Empty value) {
                value.getClass();
                Empty empty = this.response_;
                if (empty == null || empty == Empty.getDefaultInstance()) {
                    this.response_ = value;
                } else {
                    this.response_ = (Empty) ((Empty.Builder) Empty.newBuilder(this.response_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = null;
            }

            public static Rollback parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Rollback parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Rollback parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Rollback parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Rollback parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Rollback parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Rollback parseFrom(InputStream input) throws IOException {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Rollback parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Rollback parseDelimitedFrom(InputStream input) throws IOException {
                return (Rollback) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Rollback parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Rollback) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Rollback parseFrom(CodedInputStream input) throws IOException {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Rollback parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Rollback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(Rollback prototype) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Rollback, Builder> implements RollbackOrBuilder {
                /* synthetic */ Builder(C00551 x0) {
                    this();
                }

                private Builder() {
                    super(Rollback.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((Rollback) this.instance).hasRequest();
                }

                public RollbackRequest getRequest() {
                    return ((Rollback) this.instance).getRequest();
                }

                public Builder setRequest(RollbackRequest value) {
                    copyOnWrite();
                    ((Rollback) this.instance).setRequest(value);
                    return this;
                }

                public Builder setRequest(RollbackRequest.Builder builderForValue) {
                    copyOnWrite();
                    ((Rollback) this.instance).setRequest((RollbackRequest) builderForValue.build());
                    return this;
                }

                public Builder mergeRequest(RollbackRequest value) {
                    copyOnWrite();
                    ((Rollback) this.instance).mergeRequest(value);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((Rollback) this.instance).clearRequest();
                    return this;
                }

                public boolean hasResponse() {
                    return ((Rollback) this.instance).hasResponse();
                }

                public Empty getResponse() {
                    return ((Rollback) this.instance).getResponse();
                }

                public Builder setResponse(Empty value) {
                    copyOnWrite();
                    ((Rollback) this.instance).setResponse(value);
                    return this;
                }

                public Builder setResponse(Empty.Builder builderForValue) {
                    copyOnWrite();
                    ((Rollback) this.instance).setResponse((Empty) builderForValue.build());
                    return this;
                }

                public Builder mergeResponse(Empty value) {
                    copyOnWrite();
                    ((Rollback) this.instance).mergeResponse(value);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((Rollback) this.instance).clearResponse();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (C00551.f47xa1df5c61[method.ordinal()]) {
                    case 1:
                        return new Rollback();
                    case 2:
                        return new Builder((C00551) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"request_", "response_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<Rollback> parser = PARSER;
                        if (parser == null) {
                            synchronized (Rollback.class) {
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
                Rollback defaultInstance = new Rollback();
                DEFAULT_INSTANCE = defaultInstance;
                GeneratedMessageLite.registerDefaultInstance(Rollback.class, defaultInstance);
            }

            public static Rollback getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Rollback> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class ListCollectionIds extends GeneratedMessageLite<ListCollectionIds, Builder> implements ListCollectionIdsOrBuilder {
            /* access modifiers changed from: private */
            public static final ListCollectionIds DEFAULT_INSTANCE;
            private static volatile Parser<ListCollectionIds> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private ListCollectionIdsRequest request_;
            private ListCollectionIdsResponse response_;

            private ListCollectionIds() {
            }

            public boolean hasRequest() {
                return this.request_ != null;
            }

            public ListCollectionIdsRequest getRequest() {
                ListCollectionIdsRequest listCollectionIdsRequest = this.request_;
                return listCollectionIdsRequest == null ? ListCollectionIdsRequest.getDefaultInstance() : listCollectionIdsRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(ListCollectionIdsRequest value) {
                value.getClass();
                this.request_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(ListCollectionIdsRequest value) {
                value.getClass();
                ListCollectionIdsRequest listCollectionIdsRequest = this.request_;
                if (listCollectionIdsRequest == null || listCollectionIdsRequest == ListCollectionIdsRequest.getDefaultInstance()) {
                    this.request_ = value;
                } else {
                    this.request_ = (ListCollectionIdsRequest) ((ListCollectionIdsRequest.Builder) ListCollectionIdsRequest.newBuilder(this.request_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
            }

            public boolean hasResponse() {
                return this.response_ != null;
            }

            public ListCollectionIdsResponse getResponse() {
                ListCollectionIdsResponse listCollectionIdsResponse = this.response_;
                return listCollectionIdsResponse == null ? ListCollectionIdsResponse.getDefaultInstance() : listCollectionIdsResponse;
            }

            /* access modifiers changed from: private */
            public void setResponse(ListCollectionIdsResponse value) {
                value.getClass();
                this.response_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeResponse(ListCollectionIdsResponse value) {
                value.getClass();
                ListCollectionIdsResponse listCollectionIdsResponse = this.response_;
                if (listCollectionIdsResponse == null || listCollectionIdsResponse == ListCollectionIdsResponse.getDefaultInstance()) {
                    this.response_ = value;
                } else {
                    this.response_ = (ListCollectionIdsResponse) ((ListCollectionIdsResponse.Builder) ListCollectionIdsResponse.newBuilder(this.response_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = null;
            }

            public static ListCollectionIds parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static ListCollectionIds parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static ListCollectionIds parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static ListCollectionIds parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static ListCollectionIds parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static ListCollectionIds parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static ListCollectionIds parseFrom(InputStream input) throws IOException {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static ListCollectionIds parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static ListCollectionIds parseDelimitedFrom(InputStream input) throws IOException {
                return (ListCollectionIds) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static ListCollectionIds parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ListCollectionIds) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static ListCollectionIds parseFrom(CodedInputStream input) throws IOException {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static ListCollectionIds parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ListCollectionIds) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(ListCollectionIds prototype) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<ListCollectionIds, Builder> implements ListCollectionIdsOrBuilder {
                /* synthetic */ Builder(C00551 x0) {
                    this();
                }

                private Builder() {
                    super(ListCollectionIds.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((ListCollectionIds) this.instance).hasRequest();
                }

                public ListCollectionIdsRequest getRequest() {
                    return ((ListCollectionIds) this.instance).getRequest();
                }

                public Builder setRequest(ListCollectionIdsRequest value) {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).setRequest(value);
                    return this;
                }

                public Builder setRequest(ListCollectionIdsRequest.Builder builderForValue) {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).setRequest((ListCollectionIdsRequest) builderForValue.build());
                    return this;
                }

                public Builder mergeRequest(ListCollectionIdsRequest value) {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).mergeRequest(value);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).clearRequest();
                    return this;
                }

                public boolean hasResponse() {
                    return ((ListCollectionIds) this.instance).hasResponse();
                }

                public ListCollectionIdsResponse getResponse() {
                    return ((ListCollectionIds) this.instance).getResponse();
                }

                public Builder setResponse(ListCollectionIdsResponse value) {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).setResponse(value);
                    return this;
                }

                public Builder setResponse(ListCollectionIdsResponse.Builder builderForValue) {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).setResponse((ListCollectionIdsResponse) builderForValue.build());
                    return this;
                }

                public Builder mergeResponse(ListCollectionIdsResponse value) {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).mergeResponse(value);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((ListCollectionIds) this.instance).clearResponse();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (C00551.f47xa1df5c61[method.ordinal()]) {
                    case 1:
                        return new ListCollectionIds();
                    case 2:
                        return new Builder((C00551) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"request_", "response_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<ListCollectionIds> parser = PARSER;
                        if (parser == null) {
                            synchronized (ListCollectionIds.class) {
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
                ListCollectionIds defaultInstance = new ListCollectionIds();
                DEFAULT_INSTANCE = defaultInstance;
                GeneratedMessageLite.registerDefaultInstance(ListCollectionIds.class, defaultInstance);
            }

            public static ListCollectionIds getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<ListCollectionIds> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class BatchGetDocuments extends GeneratedMessageLite<BatchGetDocuments, Builder> implements BatchGetDocumentsOrBuilder {
            /* access modifiers changed from: private */
            public static final BatchGetDocuments DEFAULT_INSTANCE;
            private static volatile Parser<BatchGetDocuments> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private BatchGetDocumentsRequest request_;
            private Internal.ProtobufList<BatchGetDocumentsResponse> response_ = emptyProtobufList();

            private BatchGetDocuments() {
            }

            public boolean hasRequest() {
                return this.request_ != null;
            }

            public BatchGetDocumentsRequest getRequest() {
                BatchGetDocumentsRequest batchGetDocumentsRequest = this.request_;
                return batchGetDocumentsRequest == null ? BatchGetDocumentsRequest.getDefaultInstance() : batchGetDocumentsRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(BatchGetDocumentsRequest value) {
                value.getClass();
                this.request_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(BatchGetDocumentsRequest value) {
                value.getClass();
                BatchGetDocumentsRequest batchGetDocumentsRequest = this.request_;
                if (batchGetDocumentsRequest == null || batchGetDocumentsRequest == BatchGetDocumentsRequest.getDefaultInstance()) {
                    this.request_ = value;
                } else {
                    this.request_ = (BatchGetDocumentsRequest) ((BatchGetDocumentsRequest.Builder) BatchGetDocumentsRequest.newBuilder(this.request_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
            }

            public List<BatchGetDocumentsResponse> getResponseList() {
                return this.response_;
            }

            public List<? extends BatchGetDocumentsResponseOrBuilder> getResponseOrBuilderList() {
                return this.response_;
            }

            public int getResponseCount() {
                return this.response_.size();
            }

            public BatchGetDocumentsResponse getResponse(int index) {
                return (BatchGetDocumentsResponse) this.response_.get(index);
            }

            public BatchGetDocumentsResponseOrBuilder getResponseOrBuilder(int index) {
                return (BatchGetDocumentsResponseOrBuilder) this.response_.get(index);
            }

            private void ensureResponseIsMutable() {
                Internal.ProtobufList<BatchGetDocumentsResponse> tmp = this.response_;
                if (!tmp.isModifiable()) {
                    this.response_ = GeneratedMessageLite.mutableCopy(tmp);
                }
            }

            /* access modifiers changed from: private */
            public void setResponse(int index, BatchGetDocumentsResponse value) {
                value.getClass();
                ensureResponseIsMutable();
                this.response_.set(index, value);
            }

            /* access modifiers changed from: private */
            public void addResponse(BatchGetDocumentsResponse value) {
                value.getClass();
                ensureResponseIsMutable();
                this.response_.add(value);
            }

            /* access modifiers changed from: private */
            public void addResponse(int index, BatchGetDocumentsResponse value) {
                value.getClass();
                ensureResponseIsMutable();
                this.response_.add(index, value);
            }

            /* access modifiers changed from: private */
            public void addAllResponse(Iterable<? extends BatchGetDocumentsResponse> values) {
                ensureResponseIsMutable();
                AbstractMessageLite.addAll(values, this.response_);
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = emptyProtobufList();
            }

            /* access modifiers changed from: private */
            public void removeResponse(int index) {
                ensureResponseIsMutable();
                this.response_.remove(index);
            }

            public static BatchGetDocuments parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static BatchGetDocuments parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static BatchGetDocuments parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static BatchGetDocuments parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static BatchGetDocuments parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static BatchGetDocuments parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static BatchGetDocuments parseFrom(InputStream input) throws IOException {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static BatchGetDocuments parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static BatchGetDocuments parseDelimitedFrom(InputStream input) throws IOException {
                return (BatchGetDocuments) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static BatchGetDocuments parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (BatchGetDocuments) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static BatchGetDocuments parseFrom(CodedInputStream input) throws IOException {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static BatchGetDocuments parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (BatchGetDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(BatchGetDocuments prototype) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<BatchGetDocuments, Builder> implements BatchGetDocumentsOrBuilder {
                /* synthetic */ Builder(C00551 x0) {
                    this();
                }

                private Builder() {
                    super(BatchGetDocuments.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((BatchGetDocuments) this.instance).hasRequest();
                }

                public BatchGetDocumentsRequest getRequest() {
                    return ((BatchGetDocuments) this.instance).getRequest();
                }

                public Builder setRequest(BatchGetDocumentsRequest value) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).setRequest(value);
                    return this;
                }

                public Builder setRequest(BatchGetDocumentsRequest.Builder builderForValue) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).setRequest((BatchGetDocumentsRequest) builderForValue.build());
                    return this;
                }

                public Builder mergeRequest(BatchGetDocumentsRequest value) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).mergeRequest(value);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).clearRequest();
                    return this;
                }

                public List<BatchGetDocumentsResponse> getResponseList() {
                    return Collections.unmodifiableList(((BatchGetDocuments) this.instance).getResponseList());
                }

                public int getResponseCount() {
                    return ((BatchGetDocuments) this.instance).getResponseCount();
                }

                public BatchGetDocumentsResponse getResponse(int index) {
                    return ((BatchGetDocuments) this.instance).getResponse(index);
                }

                public Builder setResponse(int index, BatchGetDocumentsResponse value) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).setResponse(index, value);
                    return this;
                }

                public Builder setResponse(int index, BatchGetDocumentsResponse.Builder builderForValue) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).setResponse(index, (BatchGetDocumentsResponse) builderForValue.build());
                    return this;
                }

                public Builder addResponse(BatchGetDocumentsResponse value) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).addResponse(value);
                    return this;
                }

                public Builder addResponse(int index, BatchGetDocumentsResponse value) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).addResponse(index, value);
                    return this;
                }

                public Builder addResponse(BatchGetDocumentsResponse.Builder builderForValue) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).addResponse((BatchGetDocumentsResponse) builderForValue.build());
                    return this;
                }

                public Builder addResponse(int index, BatchGetDocumentsResponse.Builder builderForValue) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).addResponse(index, (BatchGetDocumentsResponse) builderForValue.build());
                    return this;
                }

                public Builder addAllResponse(Iterable<? extends BatchGetDocumentsResponse> values) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).addAllResponse(values);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).clearResponse();
                    return this;
                }

                public Builder removeResponse(int index) {
                    copyOnWrite();
                    ((BatchGetDocuments) this.instance).removeResponse(index);
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (C00551.f47xa1df5c61[method.ordinal()]) {
                    case 1:
                        return new BatchGetDocuments();
                    case 2:
                        return new Builder((C00551) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\t\u0002\u001b", new Object[]{"request_", "response_", BatchGetDocumentsResponse.class});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<BatchGetDocuments> parser = PARSER;
                        if (parser == null) {
                            synchronized (BatchGetDocuments.class) {
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
                BatchGetDocuments defaultInstance = new BatchGetDocuments();
                DEFAULT_INSTANCE = defaultInstance;
                GeneratedMessageLite.registerDefaultInstance(BatchGetDocuments.class, defaultInstance);
            }

            public static BatchGetDocuments getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<BatchGetDocuments> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class RunQuery extends GeneratedMessageLite<RunQuery, Builder> implements RunQueryOrBuilder {
            /* access modifiers changed from: private */
            public static final RunQuery DEFAULT_INSTANCE;
            private static volatile Parser<RunQuery> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private RunQueryRequest request_;
            private Internal.ProtobufList<RunQueryResponse> response_ = emptyProtobufList();

            private RunQuery() {
            }

            public boolean hasRequest() {
                return this.request_ != null;
            }

            public RunQueryRequest getRequest() {
                RunQueryRequest runQueryRequest = this.request_;
                return runQueryRequest == null ? RunQueryRequest.getDefaultInstance() : runQueryRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(RunQueryRequest value) {
                value.getClass();
                this.request_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(RunQueryRequest value) {
                value.getClass();
                RunQueryRequest runQueryRequest = this.request_;
                if (runQueryRequest == null || runQueryRequest == RunQueryRequest.getDefaultInstance()) {
                    this.request_ = value;
                } else {
                    this.request_ = (RunQueryRequest) ((RunQueryRequest.Builder) RunQueryRequest.newBuilder(this.request_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
            }

            public List<RunQueryResponse> getResponseList() {
                return this.response_;
            }

            public List<? extends RunQueryResponseOrBuilder> getResponseOrBuilderList() {
                return this.response_;
            }

            public int getResponseCount() {
                return this.response_.size();
            }

            public RunQueryResponse getResponse(int index) {
                return (RunQueryResponse) this.response_.get(index);
            }

            public RunQueryResponseOrBuilder getResponseOrBuilder(int index) {
                return (RunQueryResponseOrBuilder) this.response_.get(index);
            }

            private void ensureResponseIsMutable() {
                Internal.ProtobufList<RunQueryResponse> tmp = this.response_;
                if (!tmp.isModifiable()) {
                    this.response_ = GeneratedMessageLite.mutableCopy(tmp);
                }
            }

            /* access modifiers changed from: private */
            public void setResponse(int index, RunQueryResponse value) {
                value.getClass();
                ensureResponseIsMutable();
                this.response_.set(index, value);
            }

            /* access modifiers changed from: private */
            public void addResponse(RunQueryResponse value) {
                value.getClass();
                ensureResponseIsMutable();
                this.response_.add(value);
            }

            /* access modifiers changed from: private */
            public void addResponse(int index, RunQueryResponse value) {
                value.getClass();
                ensureResponseIsMutable();
                this.response_.add(index, value);
            }

            /* access modifiers changed from: private */
            public void addAllResponse(Iterable<? extends RunQueryResponse> values) {
                ensureResponseIsMutable();
                AbstractMessageLite.addAll(values, this.response_);
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = emptyProtobufList();
            }

            /* access modifiers changed from: private */
            public void removeResponse(int index) {
                ensureResponseIsMutable();
                this.response_.remove(index);
            }

            public static RunQuery parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static RunQuery parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static RunQuery parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static RunQuery parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static RunQuery parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static RunQuery parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static RunQuery parseFrom(InputStream input) throws IOException {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static RunQuery parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static RunQuery parseDelimitedFrom(InputStream input) throws IOException {
                return (RunQuery) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static RunQuery parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (RunQuery) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static RunQuery parseFrom(CodedInputStream input) throws IOException {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static RunQuery parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (RunQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(RunQuery prototype) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<RunQuery, Builder> implements RunQueryOrBuilder {
                /* synthetic */ Builder(C00551 x0) {
                    this();
                }

                private Builder() {
                    super(RunQuery.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((RunQuery) this.instance).hasRequest();
                }

                public RunQueryRequest getRequest() {
                    return ((RunQuery) this.instance).getRequest();
                }

                public Builder setRequest(RunQueryRequest value) {
                    copyOnWrite();
                    ((RunQuery) this.instance).setRequest(value);
                    return this;
                }

                public Builder setRequest(RunQueryRequest.Builder builderForValue) {
                    copyOnWrite();
                    ((RunQuery) this.instance).setRequest((RunQueryRequest) builderForValue.build());
                    return this;
                }

                public Builder mergeRequest(RunQueryRequest value) {
                    copyOnWrite();
                    ((RunQuery) this.instance).mergeRequest(value);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((RunQuery) this.instance).clearRequest();
                    return this;
                }

                public List<RunQueryResponse> getResponseList() {
                    return Collections.unmodifiableList(((RunQuery) this.instance).getResponseList());
                }

                public int getResponseCount() {
                    return ((RunQuery) this.instance).getResponseCount();
                }

                public RunQueryResponse getResponse(int index) {
                    return ((RunQuery) this.instance).getResponse(index);
                }

                public Builder setResponse(int index, RunQueryResponse value) {
                    copyOnWrite();
                    ((RunQuery) this.instance).setResponse(index, value);
                    return this;
                }

                public Builder setResponse(int index, RunQueryResponse.Builder builderForValue) {
                    copyOnWrite();
                    ((RunQuery) this.instance).setResponse(index, (RunQueryResponse) builderForValue.build());
                    return this;
                }

                public Builder addResponse(RunQueryResponse value) {
                    copyOnWrite();
                    ((RunQuery) this.instance).addResponse(value);
                    return this;
                }

                public Builder addResponse(int index, RunQueryResponse value) {
                    copyOnWrite();
                    ((RunQuery) this.instance).addResponse(index, value);
                    return this;
                }

                public Builder addResponse(RunQueryResponse.Builder builderForValue) {
                    copyOnWrite();
                    ((RunQuery) this.instance).addResponse((RunQueryResponse) builderForValue.build());
                    return this;
                }

                public Builder addResponse(int index, RunQueryResponse.Builder builderForValue) {
                    copyOnWrite();
                    ((RunQuery) this.instance).addResponse(index, (RunQueryResponse) builderForValue.build());
                    return this;
                }

                public Builder addAllResponse(Iterable<? extends RunQueryResponse> values) {
                    copyOnWrite();
                    ((RunQuery) this.instance).addAllResponse(values);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((RunQuery) this.instance).clearResponse();
                    return this;
                }

                public Builder removeResponse(int index) {
                    copyOnWrite();
                    ((RunQuery) this.instance).removeResponse(index);
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (C00551.f47xa1df5c61[method.ordinal()]) {
                    case 1:
                        return new RunQuery();
                    case 2:
                        return new Builder((C00551) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\t\u0002\u001b", new Object[]{"request_", "response_", RunQueryResponse.class});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<RunQuery> parser = PARSER;
                        if (parser == null) {
                            synchronized (RunQuery.class) {
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
                RunQuery defaultInstance = new RunQuery();
                DEFAULT_INSTANCE = defaultInstance;
                GeneratedMessageLite.registerDefaultInstance(RunQuery.class, defaultInstance);
            }

            public static RunQuery getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<RunQuery> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class Listen extends GeneratedMessageLite<Listen, Builder> implements ListenOrBuilder {
            /* access modifiers changed from: private */
            public static final Listen DEFAULT_INSTANCE;
            private static volatile Parser<Listen> PARSER = null;
            public static final int REQUEST_FIELD_NUMBER = 1;
            public static final int RESPONSE_FIELD_NUMBER = 2;
            private ListenRequest request_;
            private ListenResponse response_;

            private Listen() {
            }

            public boolean hasRequest() {
                return this.request_ != null;
            }

            public ListenRequest getRequest() {
                ListenRequest listenRequest = this.request_;
                return listenRequest == null ? ListenRequest.getDefaultInstance() : listenRequest;
            }

            /* access modifiers changed from: private */
            public void setRequest(ListenRequest value) {
                value.getClass();
                this.request_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeRequest(ListenRequest value) {
                value.getClass();
                ListenRequest listenRequest = this.request_;
                if (listenRequest == null || listenRequest == ListenRequest.getDefaultInstance()) {
                    this.request_ = value;
                } else {
                    this.request_ = (ListenRequest) ((ListenRequest.Builder) ListenRequest.newBuilder(this.request_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearRequest() {
                this.request_ = null;
            }

            public boolean hasResponse() {
                return this.response_ != null;
            }

            public ListenResponse getResponse() {
                ListenResponse listenResponse = this.response_;
                return listenResponse == null ? ListenResponse.getDefaultInstance() : listenResponse;
            }

            /* access modifiers changed from: private */
            public void setResponse(ListenResponse value) {
                value.getClass();
                this.response_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeResponse(ListenResponse value) {
                value.getClass();
                ListenResponse listenResponse = this.response_;
                if (listenResponse == null || listenResponse == ListenResponse.getDefaultInstance()) {
                    this.response_ = value;
                } else {
                    this.response_ = (ListenResponse) ((ListenResponse.Builder) ListenResponse.newBuilder(this.response_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearResponse() {
                this.response_ = null;
            }

            public static Listen parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Listen parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Listen parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Listen parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Listen parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Listen parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Listen parseFrom(InputStream input) throws IOException {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Listen parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Listen parseDelimitedFrom(InputStream input) throws IOException {
                return (Listen) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Listen parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Listen) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Listen parseFrom(CodedInputStream input) throws IOException {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Listen parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Listen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(Listen prototype) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Listen, Builder> implements ListenOrBuilder {
                /* synthetic */ Builder(C00551 x0) {
                    this();
                }

                private Builder() {
                    super(Listen.DEFAULT_INSTANCE);
                }

                public boolean hasRequest() {
                    return ((Listen) this.instance).hasRequest();
                }

                public ListenRequest getRequest() {
                    return ((Listen) this.instance).getRequest();
                }

                public Builder setRequest(ListenRequest value) {
                    copyOnWrite();
                    ((Listen) this.instance).setRequest(value);
                    return this;
                }

                public Builder setRequest(ListenRequest.Builder builderForValue) {
                    copyOnWrite();
                    ((Listen) this.instance).setRequest((ListenRequest) builderForValue.build());
                    return this;
                }

                public Builder mergeRequest(ListenRequest value) {
                    copyOnWrite();
                    ((Listen) this.instance).mergeRequest(value);
                    return this;
                }

                public Builder clearRequest() {
                    copyOnWrite();
                    ((Listen) this.instance).clearRequest();
                    return this;
                }

                public boolean hasResponse() {
                    return ((Listen) this.instance).hasResponse();
                }

                public ListenResponse getResponse() {
                    return ((Listen) this.instance).getResponse();
                }

                public Builder setResponse(ListenResponse value) {
                    copyOnWrite();
                    ((Listen) this.instance).setResponse(value);
                    return this;
                }

                public Builder setResponse(ListenResponse.Builder builderForValue) {
                    copyOnWrite();
                    ((Listen) this.instance).setResponse((ListenResponse) builderForValue.build());
                    return this;
                }

                public Builder mergeResponse(ListenResponse value) {
                    copyOnWrite();
                    ((Listen) this.instance).mergeResponse(value);
                    return this;
                }

                public Builder clearResponse() {
                    copyOnWrite();
                    ((Listen) this.instance).clearResponse();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (C00551.f47xa1df5c61[method.ordinal()]) {
                    case 1:
                        return new Listen();
                    case 2:
                        return new Builder((C00551) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"request_", "response_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<Listen> parser = PARSER;
                        if (parser == null) {
                            synchronized (Listen.class) {
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
                Listen defaultInstance = new Listen();
                DEFAULT_INSTANCE = defaultInstance;
                GeneratedMessageLite.registerDefaultInstance(Listen.class, defaultInstance);
            }

            public static Listen getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Listen> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class RemoveListen extends GeneratedMessageLite<RemoveListen, Builder> implements RemoveListenOrBuilder {
            /* access modifiers changed from: private */
            public static final RemoveListen DEFAULT_INSTANCE;
            private static volatile Parser<RemoveListen> PARSER = null;
            public static final int TARGET_ID_FIELD_NUMBER = 1;
            private int targetId_;

            private RemoveListen() {
            }

            public int getTargetId() {
                return this.targetId_;
            }

            /* access modifiers changed from: private */
            public void setTargetId(int value) {
                this.targetId_ = value;
            }

            /* access modifiers changed from: private */
            public void clearTargetId() {
                this.targetId_ = 0;
            }

            public static RemoveListen parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static RemoveListen parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static RemoveListen parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static RemoveListen parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static RemoveListen parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static RemoveListen parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static RemoveListen parseFrom(InputStream input) throws IOException {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static RemoveListen parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static RemoveListen parseDelimitedFrom(InputStream input) throws IOException {
                return (RemoveListen) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static RemoveListen parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (RemoveListen) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static RemoveListen parseFrom(CodedInputStream input) throws IOException {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static RemoveListen parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (RemoveListen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(RemoveListen prototype) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<RemoveListen, Builder> implements RemoveListenOrBuilder {
                /* synthetic */ Builder(C00551 x0) {
                    this();
                }

                private Builder() {
                    super(RemoveListen.DEFAULT_INSTANCE);
                }

                public int getTargetId() {
                    return ((RemoveListen) this.instance).getTargetId();
                }

                public Builder setTargetId(int value) {
                    copyOnWrite();
                    ((RemoveListen) this.instance).setTargetId(value);
                    return this;
                }

                public Builder clearTargetId() {
                    copyOnWrite();
                    ((RemoveListen) this.instance).clearTargetId();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (C00551.f47xa1df5c61[method.ordinal()]) {
                    case 1:
                        return new RemoveListen();
                    case 2:
                        return new Builder((C00551) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0004", new Object[]{"targetId_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<RemoveListen> parser = PARSER;
                        if (parser == null) {
                            synchronized (RemoveListen.class) {
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
                RemoveListen defaultInstance = new RemoveListen();
                DEFAULT_INSTANCE = defaultInstance;
                GeneratedMessageLite.registerDefaultInstance(RemoveListen.class, defaultInstance);
            }

            public static RemoveListen getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<RemoveListen> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class MatchingDocuments extends GeneratedMessageLite<MatchingDocuments, Builder> implements MatchingDocumentsOrBuilder {
            /* access modifiers changed from: private */
            public static final MatchingDocuments DEFAULT_INSTANCE;
            public static final int LISTEN_RESPONSE_FIELD_NUMBER = 1;
            public static final int MATCHING_DOCUMENTS_FIELD_NUMBER = 2;
            private static volatile Parser<MatchingDocuments> PARSER;
            private ListenResponse listenResponse_;
            private RunQueryResponse matchingDocuments_;

            private MatchingDocuments() {
            }

            public boolean hasListenResponse() {
                return this.listenResponse_ != null;
            }

            public ListenResponse getListenResponse() {
                ListenResponse listenResponse = this.listenResponse_;
                return listenResponse == null ? ListenResponse.getDefaultInstance() : listenResponse;
            }

            /* access modifiers changed from: private */
            public void setListenResponse(ListenResponse value) {
                value.getClass();
                this.listenResponse_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeListenResponse(ListenResponse value) {
                value.getClass();
                ListenResponse listenResponse = this.listenResponse_;
                if (listenResponse == null || listenResponse == ListenResponse.getDefaultInstance()) {
                    this.listenResponse_ = value;
                } else {
                    this.listenResponse_ = (ListenResponse) ((ListenResponse.Builder) ListenResponse.newBuilder(this.listenResponse_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearListenResponse() {
                this.listenResponse_ = null;
            }

            public boolean hasMatchingDocuments() {
                return this.matchingDocuments_ != null;
            }

            public RunQueryResponse getMatchingDocuments() {
                RunQueryResponse runQueryResponse = this.matchingDocuments_;
                return runQueryResponse == null ? RunQueryResponse.getDefaultInstance() : runQueryResponse;
            }

            /* access modifiers changed from: private */
            public void setMatchingDocuments(RunQueryResponse value) {
                value.getClass();
                this.matchingDocuments_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeMatchingDocuments(RunQueryResponse value) {
                value.getClass();
                RunQueryResponse runQueryResponse = this.matchingDocuments_;
                if (runQueryResponse == null || runQueryResponse == RunQueryResponse.getDefaultInstance()) {
                    this.matchingDocuments_ = value;
                } else {
                    this.matchingDocuments_ = (RunQueryResponse) ((RunQueryResponse.Builder) RunQueryResponse.newBuilder(this.matchingDocuments_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearMatchingDocuments() {
                this.matchingDocuments_ = null;
            }

            public static MatchingDocuments parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static MatchingDocuments parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static MatchingDocuments parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static MatchingDocuments parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static MatchingDocuments parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static MatchingDocuments parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static MatchingDocuments parseFrom(InputStream input) throws IOException {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static MatchingDocuments parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static MatchingDocuments parseDelimitedFrom(InputStream input) throws IOException {
                return (MatchingDocuments) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static MatchingDocuments parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (MatchingDocuments) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static MatchingDocuments parseFrom(CodedInputStream input) throws IOException {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static MatchingDocuments parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (MatchingDocuments) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(MatchingDocuments prototype) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<MatchingDocuments, Builder> implements MatchingDocumentsOrBuilder {
                /* synthetic */ Builder(C00551 x0) {
                    this();
                }

                private Builder() {
                    super(MatchingDocuments.DEFAULT_INSTANCE);
                }

                public boolean hasListenResponse() {
                    return ((MatchingDocuments) this.instance).hasListenResponse();
                }

                public ListenResponse getListenResponse() {
                    return ((MatchingDocuments) this.instance).getListenResponse();
                }

                public Builder setListenResponse(ListenResponse value) {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).setListenResponse(value);
                    return this;
                }

                public Builder setListenResponse(ListenResponse.Builder builderForValue) {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).setListenResponse((ListenResponse) builderForValue.build());
                    return this;
                }

                public Builder mergeListenResponse(ListenResponse value) {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).mergeListenResponse(value);
                    return this;
                }

                public Builder clearListenResponse() {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).clearListenResponse();
                    return this;
                }

                public boolean hasMatchingDocuments() {
                    return ((MatchingDocuments) this.instance).hasMatchingDocuments();
                }

                public RunQueryResponse getMatchingDocuments() {
                    return ((MatchingDocuments) this.instance).getMatchingDocuments();
                }

                public Builder setMatchingDocuments(RunQueryResponse value) {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).setMatchingDocuments(value);
                    return this;
                }

                public Builder setMatchingDocuments(RunQueryResponse.Builder builderForValue) {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).setMatchingDocuments((RunQueryResponse) builderForValue.build());
                    return this;
                }

                public Builder mergeMatchingDocuments(RunQueryResponse value) {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).mergeMatchingDocuments(value);
                    return this;
                }

                public Builder clearMatchingDocuments() {
                    copyOnWrite();
                    ((MatchingDocuments) this.instance).clearMatchingDocuments();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (C00551.f47xa1df5c61[method.ordinal()]) {
                    case 1:
                        return new MatchingDocuments();
                    case 2:
                        return new Builder((C00551) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"listenResponse_", "matchingDocuments_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<MatchingDocuments> parser = PARSER;
                        if (parser == null) {
                            synchronized (MatchingDocuments.class) {
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
                MatchingDocuments defaultInstance = new MatchingDocuments();
                DEFAULT_INSTANCE = defaultInstance;
                GeneratedMessageLite.registerDefaultInstance(MatchingDocuments.class, defaultInstance);
            }

            public static MatchingDocuments getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<MatchingDocuments> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public enum ActionCase {
            GET_DOCUMENT(1),
            LIST_DOCUMENTS(2),
            CREATE_DOCUMENT(3),
            UPDATE_DOCUMENT(4),
            DELETE_DOCUMENT(5),
            BEGIN_TRANSACTION(6),
            COMMIT(7),
            ROLLBACK(8),
            LIST_COLLECTION_IDS(9),
            BATCH_GET_DOCUMENTS(10),
            RUN_QUERY(11),
            LISTEN(12),
            REMOVE_LISTEN(13),
            ACTION_NOT_SET(0);
            
            private final int value;

            private ActionCase(int value2) {
                this.value = value2;
            }

            @Deprecated
            public static ActionCase valueOf(int value2) {
                return forNumber(value2);
            }

            public static ActionCase forNumber(int value2) {
                switch (value2) {
                    case 0:
                        return ACTION_NOT_SET;
                    case 1:
                        return GET_DOCUMENT;
                    case 2:
                        return LIST_DOCUMENTS;
                    case 3:
                        return CREATE_DOCUMENT;
                    case 4:
                        return UPDATE_DOCUMENT;
                    case 5:
                        return DELETE_DOCUMENT;
                    case 6:
                        return BEGIN_TRANSACTION;
                    case 7:
                        return COMMIT;
                    case 8:
                        return ROLLBACK;
                    case 9:
                        return LIST_COLLECTION_IDS;
                    case 10:
                        return BATCH_GET_DOCUMENTS;
                    case 11:
                        return RUN_QUERY;
                    case 12:
                        return LISTEN;
                    case 13:
                        return REMOVE_LISTEN;
                    default:
                        return null;
                }
            }

            public int getNumber() {
                return this.value;
            }
        }

        public ActionCase getActionCase() {
            return ActionCase.forNumber(this.actionCase_);
        }

        /* access modifiers changed from: private */
        public void clearAction() {
            this.actionCase_ = 0;
            this.action_ = null;
        }

        public boolean hasGetDocument() {
            return this.actionCase_ == 1;
        }

        public GetDocument getGetDocument() {
            if (this.actionCase_ == 1) {
                return (GetDocument) this.action_;
            }
            return GetDocument.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setGetDocument(GetDocument value) {
            value.getClass();
            this.action_ = value;
            this.actionCase_ = 1;
        }

        /* access modifiers changed from: private */
        public void mergeGetDocument(GetDocument value) {
            value.getClass();
            if (this.actionCase_ != 1 || this.action_ == GetDocument.getDefaultInstance()) {
                this.action_ = value;
            } else {
                this.action_ = ((GetDocument.Builder) GetDocument.newBuilder((GetDocument) this.action_).mergeFrom(value)).buildPartial();
            }
            this.actionCase_ = 1;
        }

        /* access modifiers changed from: private */
        public void clearGetDocument() {
            if (this.actionCase_ == 1) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasListDocuments() {
            return this.actionCase_ == 2;
        }

        public ListDocuments getListDocuments() {
            if (this.actionCase_ == 2) {
                return (ListDocuments) this.action_;
            }
            return ListDocuments.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setListDocuments(ListDocuments value) {
            value.getClass();
            this.action_ = value;
            this.actionCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void mergeListDocuments(ListDocuments value) {
            value.getClass();
            if (this.actionCase_ != 2 || this.action_ == ListDocuments.getDefaultInstance()) {
                this.action_ = value;
            } else {
                this.action_ = ((ListDocuments.Builder) ListDocuments.newBuilder((ListDocuments) this.action_).mergeFrom(value)).buildPartial();
            }
            this.actionCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void clearListDocuments() {
            if (this.actionCase_ == 2) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasCreateDocument() {
            return this.actionCase_ == 3;
        }

        public CreateDocument getCreateDocument() {
            if (this.actionCase_ == 3) {
                return (CreateDocument) this.action_;
            }
            return CreateDocument.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setCreateDocument(CreateDocument value) {
            value.getClass();
            this.action_ = value;
            this.actionCase_ = 3;
        }

        /* access modifiers changed from: private */
        public void mergeCreateDocument(CreateDocument value) {
            value.getClass();
            if (this.actionCase_ != 3 || this.action_ == CreateDocument.getDefaultInstance()) {
                this.action_ = value;
            } else {
                this.action_ = ((CreateDocument.Builder) CreateDocument.newBuilder((CreateDocument) this.action_).mergeFrom(value)).buildPartial();
            }
            this.actionCase_ = 3;
        }

        /* access modifiers changed from: private */
        public void clearCreateDocument() {
            if (this.actionCase_ == 3) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasUpdateDocument() {
            return this.actionCase_ == 4;
        }

        public UpdateDocument getUpdateDocument() {
            if (this.actionCase_ == 4) {
                return (UpdateDocument) this.action_;
            }
            return UpdateDocument.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setUpdateDocument(UpdateDocument value) {
            value.getClass();
            this.action_ = value;
            this.actionCase_ = 4;
        }

        /* access modifiers changed from: private */
        public void mergeUpdateDocument(UpdateDocument value) {
            value.getClass();
            if (this.actionCase_ != 4 || this.action_ == UpdateDocument.getDefaultInstance()) {
                this.action_ = value;
            } else {
                this.action_ = ((UpdateDocument.Builder) UpdateDocument.newBuilder((UpdateDocument) this.action_).mergeFrom(value)).buildPartial();
            }
            this.actionCase_ = 4;
        }

        /* access modifiers changed from: private */
        public void clearUpdateDocument() {
            if (this.actionCase_ == 4) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasDeleteDocument() {
            return this.actionCase_ == 5;
        }

        public DeleteDocument getDeleteDocument() {
            if (this.actionCase_ == 5) {
                return (DeleteDocument) this.action_;
            }
            return DeleteDocument.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setDeleteDocument(DeleteDocument value) {
            value.getClass();
            this.action_ = value;
            this.actionCase_ = 5;
        }

        /* access modifiers changed from: private */
        public void mergeDeleteDocument(DeleteDocument value) {
            value.getClass();
            if (this.actionCase_ != 5 || this.action_ == DeleteDocument.getDefaultInstance()) {
                this.action_ = value;
            } else {
                this.action_ = ((DeleteDocument.Builder) DeleteDocument.newBuilder((DeleteDocument) this.action_).mergeFrom(value)).buildPartial();
            }
            this.actionCase_ = 5;
        }

        /* access modifiers changed from: private */
        public void clearDeleteDocument() {
            if (this.actionCase_ == 5) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasBeginTransaction() {
            return this.actionCase_ == 6;
        }

        public BeginTransaction getBeginTransaction() {
            if (this.actionCase_ == 6) {
                return (BeginTransaction) this.action_;
            }
            return BeginTransaction.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setBeginTransaction(BeginTransaction value) {
            value.getClass();
            this.action_ = value;
            this.actionCase_ = 6;
        }

        /* access modifiers changed from: private */
        public void mergeBeginTransaction(BeginTransaction value) {
            value.getClass();
            if (this.actionCase_ != 6 || this.action_ == BeginTransaction.getDefaultInstance()) {
                this.action_ = value;
            } else {
                this.action_ = ((BeginTransaction.Builder) BeginTransaction.newBuilder((BeginTransaction) this.action_).mergeFrom(value)).buildPartial();
            }
            this.actionCase_ = 6;
        }

        /* access modifiers changed from: private */
        public void clearBeginTransaction() {
            if (this.actionCase_ == 6) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasCommit() {
            return this.actionCase_ == 7;
        }

        public Commit getCommit() {
            if (this.actionCase_ == 7) {
                return (Commit) this.action_;
            }
            return Commit.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setCommit(Commit value) {
            value.getClass();
            this.action_ = value;
            this.actionCase_ = 7;
        }

        /* access modifiers changed from: private */
        public void mergeCommit(Commit value) {
            value.getClass();
            if (this.actionCase_ != 7 || this.action_ == Commit.getDefaultInstance()) {
                this.action_ = value;
            } else {
                this.action_ = ((Commit.Builder) Commit.newBuilder((Commit) this.action_).mergeFrom(value)).buildPartial();
            }
            this.actionCase_ = 7;
        }

        /* access modifiers changed from: private */
        public void clearCommit() {
            if (this.actionCase_ == 7) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasRollback() {
            return this.actionCase_ == 8;
        }

        public Rollback getRollback() {
            if (this.actionCase_ == 8) {
                return (Rollback) this.action_;
            }
            return Rollback.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setRollback(Rollback value) {
            value.getClass();
            this.action_ = value;
            this.actionCase_ = 8;
        }

        /* access modifiers changed from: private */
        public void mergeRollback(Rollback value) {
            value.getClass();
            if (this.actionCase_ != 8 || this.action_ == Rollback.getDefaultInstance()) {
                this.action_ = value;
            } else {
                this.action_ = ((Rollback.Builder) Rollback.newBuilder((Rollback) this.action_).mergeFrom(value)).buildPartial();
            }
            this.actionCase_ = 8;
        }

        /* access modifiers changed from: private */
        public void clearRollback() {
            if (this.actionCase_ == 8) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasListCollectionIds() {
            return this.actionCase_ == 9;
        }

        public ListCollectionIds getListCollectionIds() {
            if (this.actionCase_ == 9) {
                return (ListCollectionIds) this.action_;
            }
            return ListCollectionIds.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setListCollectionIds(ListCollectionIds value) {
            value.getClass();
            this.action_ = value;
            this.actionCase_ = 9;
        }

        /* access modifiers changed from: private */
        public void mergeListCollectionIds(ListCollectionIds value) {
            value.getClass();
            if (this.actionCase_ != 9 || this.action_ == ListCollectionIds.getDefaultInstance()) {
                this.action_ = value;
            } else {
                this.action_ = ((ListCollectionIds.Builder) ListCollectionIds.newBuilder((ListCollectionIds) this.action_).mergeFrom(value)).buildPartial();
            }
            this.actionCase_ = 9;
        }

        /* access modifiers changed from: private */
        public void clearListCollectionIds() {
            if (this.actionCase_ == 9) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasBatchGetDocuments() {
            return this.actionCase_ == 10;
        }

        public BatchGetDocuments getBatchGetDocuments() {
            if (this.actionCase_ == 10) {
                return (BatchGetDocuments) this.action_;
            }
            return BatchGetDocuments.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setBatchGetDocuments(BatchGetDocuments value) {
            value.getClass();
            this.action_ = value;
            this.actionCase_ = 10;
        }

        /* access modifiers changed from: private */
        public void mergeBatchGetDocuments(BatchGetDocuments value) {
            value.getClass();
            if (this.actionCase_ != 10 || this.action_ == BatchGetDocuments.getDefaultInstance()) {
                this.action_ = value;
            } else {
                this.action_ = ((BatchGetDocuments.Builder) BatchGetDocuments.newBuilder((BatchGetDocuments) this.action_).mergeFrom(value)).buildPartial();
            }
            this.actionCase_ = 10;
        }

        /* access modifiers changed from: private */
        public void clearBatchGetDocuments() {
            if (this.actionCase_ == 10) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasRunQuery() {
            return this.actionCase_ == 11;
        }

        public RunQuery getRunQuery() {
            if (this.actionCase_ == 11) {
                return (RunQuery) this.action_;
            }
            return RunQuery.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setRunQuery(RunQuery value) {
            value.getClass();
            this.action_ = value;
            this.actionCase_ = 11;
        }

        /* access modifiers changed from: private */
        public void mergeRunQuery(RunQuery value) {
            value.getClass();
            if (this.actionCase_ != 11 || this.action_ == RunQuery.getDefaultInstance()) {
                this.action_ = value;
            } else {
                this.action_ = ((RunQuery.Builder) RunQuery.newBuilder((RunQuery) this.action_).mergeFrom(value)).buildPartial();
            }
            this.actionCase_ = 11;
        }

        /* access modifiers changed from: private */
        public void clearRunQuery() {
            if (this.actionCase_ == 11) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasListen() {
            return this.actionCase_ == 12;
        }

        public Listen getListen() {
            if (this.actionCase_ == 12) {
                return (Listen) this.action_;
            }
            return Listen.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setListen(Listen value) {
            value.getClass();
            this.action_ = value;
            this.actionCase_ = 12;
        }

        /* access modifiers changed from: private */
        public void mergeListen(Listen value) {
            value.getClass();
            if (this.actionCase_ != 12 || this.action_ == Listen.getDefaultInstance()) {
                this.action_ = value;
            } else {
                this.action_ = ((Listen.Builder) Listen.newBuilder((Listen) this.action_).mergeFrom(value)).buildPartial();
            }
            this.actionCase_ = 12;
        }

        /* access modifiers changed from: private */
        public void clearListen() {
            if (this.actionCase_ == 12) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasRemoveListen() {
            return this.actionCase_ == 13;
        }

        public RemoveListen getRemoveListen() {
            if (this.actionCase_ == 13) {
                return (RemoveListen) this.action_;
            }
            return RemoveListen.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setRemoveListen(RemoveListen value) {
            value.getClass();
            this.action_ = value;
            this.actionCase_ = 13;
        }

        /* access modifiers changed from: private */
        public void mergeRemoveListen(RemoveListen value) {
            value.getClass();
            if (this.actionCase_ != 13 || this.action_ == RemoveListen.getDefaultInstance()) {
                this.action_ = value;
            } else {
                this.action_ = ((RemoveListen.Builder) RemoveListen.newBuilder((RemoveListen) this.action_).mergeFrom(value)).buildPartial();
            }
            this.actionCase_ = 13;
        }

        /* access modifiers changed from: private */
        public void clearRemoveListen() {
            if (this.actionCase_ == 13) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public boolean hasStatus() {
            return this.status_ != null;
        }

        public StatusProto getStatus() {
            StatusProto statusProto = this.status_;
            return statusProto == null ? StatusProto.getDefaultInstance() : statusProto;
        }

        /* access modifiers changed from: private */
        public void setStatus(StatusProto value) {
            value.getClass();
            this.status_ = value;
        }

        /* access modifiers changed from: private */
        public void mergeStatus(StatusProto value) {
            value.getClass();
            StatusProto statusProto = this.status_;
            if (statusProto == null || statusProto == StatusProto.getDefaultInstance()) {
                this.status_ = value;
            } else {
                this.status_ = (StatusProto) ((StatusProto.Builder) StatusProto.newBuilder(this.status_).mergeFrom(value)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearStatus() {
            this.status_ = null;
        }

        public boolean hasDatabaseContentsBeforeAction() {
            return this.databaseContentsBeforeAction_ != null;
        }

        public RunQuery getDatabaseContentsBeforeAction() {
            RunQuery runQuery = this.databaseContentsBeforeAction_;
            return runQuery == null ? RunQuery.getDefaultInstance() : runQuery;
        }

        /* access modifiers changed from: private */
        public void setDatabaseContentsBeforeAction(RunQuery value) {
            value.getClass();
            this.databaseContentsBeforeAction_ = value;
        }

        /* access modifiers changed from: private */
        public void mergeDatabaseContentsBeforeAction(RunQuery value) {
            value.getClass();
            RunQuery runQuery = this.databaseContentsBeforeAction_;
            if (runQuery == null || runQuery == RunQuery.getDefaultInstance()) {
                this.databaseContentsBeforeAction_ = value;
            } else {
                this.databaseContentsBeforeAction_ = (RunQuery) ((RunQuery.Builder) RunQuery.newBuilder(this.databaseContentsBeforeAction_).mergeFrom(value)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearDatabaseContentsBeforeAction() {
            this.databaseContentsBeforeAction_ = null;
        }

        public List<MatchingDocuments> getMatchingDocumentsList() {
            return this.matchingDocuments_;
        }

        public List<? extends MatchingDocumentsOrBuilder> getMatchingDocumentsOrBuilderList() {
            return this.matchingDocuments_;
        }

        public int getMatchingDocumentsCount() {
            return this.matchingDocuments_.size();
        }

        public MatchingDocuments getMatchingDocuments(int index) {
            return (MatchingDocuments) this.matchingDocuments_.get(index);
        }

        public MatchingDocumentsOrBuilder getMatchingDocumentsOrBuilder(int index) {
            return (MatchingDocumentsOrBuilder) this.matchingDocuments_.get(index);
        }

        private void ensureMatchingDocumentsIsMutable() {
            Internal.ProtobufList<MatchingDocuments> tmp = this.matchingDocuments_;
            if (!tmp.isModifiable()) {
                this.matchingDocuments_ = GeneratedMessageLite.mutableCopy(tmp);
            }
        }

        /* access modifiers changed from: private */
        public void setMatchingDocuments(int index, MatchingDocuments value) {
            value.getClass();
            ensureMatchingDocumentsIsMutable();
            this.matchingDocuments_.set(index, value);
        }

        /* access modifiers changed from: private */
        public void addMatchingDocuments(MatchingDocuments value) {
            value.getClass();
            ensureMatchingDocumentsIsMutable();
            this.matchingDocuments_.add(value);
        }

        /* access modifiers changed from: private */
        public void addMatchingDocuments(int index, MatchingDocuments value) {
            value.getClass();
            ensureMatchingDocumentsIsMutable();
            this.matchingDocuments_.add(index, value);
        }

        /* access modifiers changed from: private */
        public void addAllMatchingDocuments(Iterable<? extends MatchingDocuments> values) {
            ensureMatchingDocumentsIsMutable();
            AbstractMessageLite.addAll(values, this.matchingDocuments_);
        }

        /* access modifiers changed from: private */
        public void clearMatchingDocuments() {
            this.matchingDocuments_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeMatchingDocuments(int index) {
            ensureMatchingDocumentsIsMutable();
            this.matchingDocuments_.remove(index);
        }

        public static FirestoreV1Action parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FirestoreV1Action parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FirestoreV1Action parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FirestoreV1Action parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FirestoreV1Action parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FirestoreV1Action parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FirestoreV1Action parseFrom(InputStream input) throws IOException {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FirestoreV1Action parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FirestoreV1Action parseDelimitedFrom(InputStream input) throws IOException {
            return (FirestoreV1Action) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static FirestoreV1Action parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FirestoreV1Action) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FirestoreV1Action parseFrom(CodedInputStream input) throws IOException {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FirestoreV1Action parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FirestoreV1Action) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(FirestoreV1Action prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<FirestoreV1Action, Builder> implements FirestoreV1ActionOrBuilder {
            /* synthetic */ Builder(C00551 x0) {
                this();
            }

            private Builder() {
                super(FirestoreV1Action.DEFAULT_INSTANCE);
            }

            public ActionCase getActionCase() {
                return ((FirestoreV1Action) this.instance).getActionCase();
            }

            public Builder clearAction() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearAction();
                return this;
            }

            public boolean hasGetDocument() {
                return ((FirestoreV1Action) this.instance).hasGetDocument();
            }

            public GetDocument getGetDocument() {
                return ((FirestoreV1Action) this.instance).getGetDocument();
            }

            public Builder setGetDocument(GetDocument value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setGetDocument(value);
                return this;
            }

            public Builder setGetDocument(GetDocument.Builder builderForValue) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setGetDocument((GetDocument) builderForValue.build());
                return this;
            }

            public Builder mergeGetDocument(GetDocument value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeGetDocument(value);
                return this;
            }

            public Builder clearGetDocument() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearGetDocument();
                return this;
            }

            public boolean hasListDocuments() {
                return ((FirestoreV1Action) this.instance).hasListDocuments();
            }

            public ListDocuments getListDocuments() {
                return ((FirestoreV1Action) this.instance).getListDocuments();
            }

            public Builder setListDocuments(ListDocuments value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setListDocuments(value);
                return this;
            }

            public Builder setListDocuments(ListDocuments.Builder builderForValue) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setListDocuments((ListDocuments) builderForValue.build());
                return this;
            }

            public Builder mergeListDocuments(ListDocuments value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeListDocuments(value);
                return this;
            }

            public Builder clearListDocuments() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearListDocuments();
                return this;
            }

            public boolean hasCreateDocument() {
                return ((FirestoreV1Action) this.instance).hasCreateDocument();
            }

            public CreateDocument getCreateDocument() {
                return ((FirestoreV1Action) this.instance).getCreateDocument();
            }

            public Builder setCreateDocument(CreateDocument value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setCreateDocument(value);
                return this;
            }

            public Builder setCreateDocument(CreateDocument.Builder builderForValue) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setCreateDocument((CreateDocument) builderForValue.build());
                return this;
            }

            public Builder mergeCreateDocument(CreateDocument value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeCreateDocument(value);
                return this;
            }

            public Builder clearCreateDocument() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearCreateDocument();
                return this;
            }

            public boolean hasUpdateDocument() {
                return ((FirestoreV1Action) this.instance).hasUpdateDocument();
            }

            public UpdateDocument getUpdateDocument() {
                return ((FirestoreV1Action) this.instance).getUpdateDocument();
            }

            public Builder setUpdateDocument(UpdateDocument value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setUpdateDocument(value);
                return this;
            }

            public Builder setUpdateDocument(UpdateDocument.Builder builderForValue) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setUpdateDocument((UpdateDocument) builderForValue.build());
                return this;
            }

            public Builder mergeUpdateDocument(UpdateDocument value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeUpdateDocument(value);
                return this;
            }

            public Builder clearUpdateDocument() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearUpdateDocument();
                return this;
            }

            public boolean hasDeleteDocument() {
                return ((FirestoreV1Action) this.instance).hasDeleteDocument();
            }

            public DeleteDocument getDeleteDocument() {
                return ((FirestoreV1Action) this.instance).getDeleteDocument();
            }

            public Builder setDeleteDocument(DeleteDocument value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setDeleteDocument(value);
                return this;
            }

            public Builder setDeleteDocument(DeleteDocument.Builder builderForValue) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setDeleteDocument((DeleteDocument) builderForValue.build());
                return this;
            }

            public Builder mergeDeleteDocument(DeleteDocument value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeDeleteDocument(value);
                return this;
            }

            public Builder clearDeleteDocument() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearDeleteDocument();
                return this;
            }

            public boolean hasBeginTransaction() {
                return ((FirestoreV1Action) this.instance).hasBeginTransaction();
            }

            public BeginTransaction getBeginTransaction() {
                return ((FirestoreV1Action) this.instance).getBeginTransaction();
            }

            public Builder setBeginTransaction(BeginTransaction value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setBeginTransaction(value);
                return this;
            }

            public Builder setBeginTransaction(BeginTransaction.Builder builderForValue) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setBeginTransaction((BeginTransaction) builderForValue.build());
                return this;
            }

            public Builder mergeBeginTransaction(BeginTransaction value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeBeginTransaction(value);
                return this;
            }

            public Builder clearBeginTransaction() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearBeginTransaction();
                return this;
            }

            public boolean hasCommit() {
                return ((FirestoreV1Action) this.instance).hasCommit();
            }

            public Commit getCommit() {
                return ((FirestoreV1Action) this.instance).getCommit();
            }

            public Builder setCommit(Commit value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setCommit(value);
                return this;
            }

            public Builder setCommit(Commit.Builder builderForValue) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setCommit((Commit) builderForValue.build());
                return this;
            }

            public Builder mergeCommit(Commit value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeCommit(value);
                return this;
            }

            public Builder clearCommit() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearCommit();
                return this;
            }

            public boolean hasRollback() {
                return ((FirestoreV1Action) this.instance).hasRollback();
            }

            public Rollback getRollback() {
                return ((FirestoreV1Action) this.instance).getRollback();
            }

            public Builder setRollback(Rollback value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setRollback(value);
                return this;
            }

            public Builder setRollback(Rollback.Builder builderForValue) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setRollback((Rollback) builderForValue.build());
                return this;
            }

            public Builder mergeRollback(Rollback value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeRollback(value);
                return this;
            }

            public Builder clearRollback() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearRollback();
                return this;
            }

            public boolean hasListCollectionIds() {
                return ((FirestoreV1Action) this.instance).hasListCollectionIds();
            }

            public ListCollectionIds getListCollectionIds() {
                return ((FirestoreV1Action) this.instance).getListCollectionIds();
            }

            public Builder setListCollectionIds(ListCollectionIds value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setListCollectionIds(value);
                return this;
            }

            public Builder setListCollectionIds(ListCollectionIds.Builder builderForValue) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setListCollectionIds((ListCollectionIds) builderForValue.build());
                return this;
            }

            public Builder mergeListCollectionIds(ListCollectionIds value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeListCollectionIds(value);
                return this;
            }

            public Builder clearListCollectionIds() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearListCollectionIds();
                return this;
            }

            public boolean hasBatchGetDocuments() {
                return ((FirestoreV1Action) this.instance).hasBatchGetDocuments();
            }

            public BatchGetDocuments getBatchGetDocuments() {
                return ((FirestoreV1Action) this.instance).getBatchGetDocuments();
            }

            public Builder setBatchGetDocuments(BatchGetDocuments value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setBatchGetDocuments(value);
                return this;
            }

            public Builder setBatchGetDocuments(BatchGetDocuments.Builder builderForValue) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setBatchGetDocuments((BatchGetDocuments) builderForValue.build());
                return this;
            }

            public Builder mergeBatchGetDocuments(BatchGetDocuments value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeBatchGetDocuments(value);
                return this;
            }

            public Builder clearBatchGetDocuments() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearBatchGetDocuments();
                return this;
            }

            public boolean hasRunQuery() {
                return ((FirestoreV1Action) this.instance).hasRunQuery();
            }

            public RunQuery getRunQuery() {
                return ((FirestoreV1Action) this.instance).getRunQuery();
            }

            public Builder setRunQuery(RunQuery value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setRunQuery(value);
                return this;
            }

            public Builder setRunQuery(RunQuery.Builder builderForValue) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setRunQuery((RunQuery) builderForValue.build());
                return this;
            }

            public Builder mergeRunQuery(RunQuery value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeRunQuery(value);
                return this;
            }

            public Builder clearRunQuery() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearRunQuery();
                return this;
            }

            public boolean hasListen() {
                return ((FirestoreV1Action) this.instance).hasListen();
            }

            public Listen getListen() {
                return ((FirestoreV1Action) this.instance).getListen();
            }

            public Builder setListen(Listen value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setListen(value);
                return this;
            }

            public Builder setListen(Listen.Builder builderForValue) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setListen((Listen) builderForValue.build());
                return this;
            }

            public Builder mergeListen(Listen value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeListen(value);
                return this;
            }

            public Builder clearListen() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearListen();
                return this;
            }

            public boolean hasRemoveListen() {
                return ((FirestoreV1Action) this.instance).hasRemoveListen();
            }

            public RemoveListen getRemoveListen() {
                return ((FirestoreV1Action) this.instance).getRemoveListen();
            }

            public Builder setRemoveListen(RemoveListen value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setRemoveListen(value);
                return this;
            }

            public Builder setRemoveListen(RemoveListen.Builder builderForValue) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setRemoveListen((RemoveListen) builderForValue.build());
                return this;
            }

            public Builder mergeRemoveListen(RemoveListen value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeRemoveListen(value);
                return this;
            }

            public Builder clearRemoveListen() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearRemoveListen();
                return this;
            }

            public boolean hasStatus() {
                return ((FirestoreV1Action) this.instance).hasStatus();
            }

            public StatusProto getStatus() {
                return ((FirestoreV1Action) this.instance).getStatus();
            }

            public Builder setStatus(StatusProto value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setStatus(value);
                return this;
            }

            public Builder setStatus(StatusProto.Builder builderForValue) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setStatus((StatusProto) builderForValue.build());
                return this;
            }

            public Builder mergeStatus(StatusProto value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeStatus(value);
                return this;
            }

            public Builder clearStatus() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearStatus();
                return this;
            }

            public boolean hasDatabaseContentsBeforeAction() {
                return ((FirestoreV1Action) this.instance).hasDatabaseContentsBeforeAction();
            }

            public RunQuery getDatabaseContentsBeforeAction() {
                return ((FirestoreV1Action) this.instance).getDatabaseContentsBeforeAction();
            }

            public Builder setDatabaseContentsBeforeAction(RunQuery value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setDatabaseContentsBeforeAction(value);
                return this;
            }

            public Builder setDatabaseContentsBeforeAction(RunQuery.Builder builderForValue) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setDatabaseContentsBeforeAction((RunQuery) builderForValue.build());
                return this;
            }

            public Builder mergeDatabaseContentsBeforeAction(RunQuery value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).mergeDatabaseContentsBeforeAction(value);
                return this;
            }

            public Builder clearDatabaseContentsBeforeAction() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearDatabaseContentsBeforeAction();
                return this;
            }

            public List<MatchingDocuments> getMatchingDocumentsList() {
                return Collections.unmodifiableList(((FirestoreV1Action) this.instance).getMatchingDocumentsList());
            }

            public int getMatchingDocumentsCount() {
                return ((FirestoreV1Action) this.instance).getMatchingDocumentsCount();
            }

            public MatchingDocuments getMatchingDocuments(int index) {
                return ((FirestoreV1Action) this.instance).getMatchingDocuments(index);
            }

            public Builder setMatchingDocuments(int index, MatchingDocuments value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setMatchingDocuments(index, value);
                return this;
            }

            public Builder setMatchingDocuments(int index, MatchingDocuments.Builder builderForValue) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).setMatchingDocuments(index, (MatchingDocuments) builderForValue.build());
                return this;
            }

            public Builder addMatchingDocuments(MatchingDocuments value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).addMatchingDocuments(value);
                return this;
            }

            public Builder addMatchingDocuments(int index, MatchingDocuments value) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).addMatchingDocuments(index, value);
                return this;
            }

            public Builder addMatchingDocuments(MatchingDocuments.Builder builderForValue) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).addMatchingDocuments((MatchingDocuments) builderForValue.build());
                return this;
            }

            public Builder addMatchingDocuments(int index, MatchingDocuments.Builder builderForValue) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).addMatchingDocuments(index, (MatchingDocuments) builderForValue.build());
                return this;
            }

            public Builder addAllMatchingDocuments(Iterable<? extends MatchingDocuments> values) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).addAllMatchingDocuments(values);
                return this;
            }

            public Builder clearMatchingDocuments() {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).clearMatchingDocuments();
                return this;
            }

            public Builder removeMatchingDocuments(int index) {
                copyOnWrite();
                ((FirestoreV1Action) this.instance).removeMatchingDocuments(index);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C00551.f47xa1df5c61[method.ordinal()]) {
                case 1:
                    return new FirestoreV1Action();
                case 2:
                    return new Builder((C00551) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0010\u0001\u0000\u0001Ë\u0010\u0000\u0001\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000\u0006<\u0000\u0007<\u0000\b<\u0000\t<\u0000\n<\u0000\u000b<\u0000\f<\u0000\r<\u0000É\tÊ\tË\u001b", new Object[]{"action_", "actionCase_", GetDocument.class, ListDocuments.class, CreateDocument.class, UpdateDocument.class, DeleteDocument.class, BeginTransaction.class, Commit.class, Rollback.class, ListCollectionIds.class, BatchGetDocuments.class, RunQuery.class, Listen.class, RemoveListen.class, "status_", "databaseContentsBeforeAction_", "matchingDocuments_", MatchingDocuments.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<FirestoreV1Action> parser = PARSER;
                    if (parser == null) {
                        synchronized (FirestoreV1Action.class) {
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
            FirestoreV1Action defaultInstance = new FirestoreV1Action();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(FirestoreV1Action.class, defaultInstance);
        }

        public static FirestoreV1Action getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FirestoreV1Action> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class DatastoreAction extends GeneratedMessageLite<DatastoreAction, Builder> implements DatastoreActionOrBuilder {
        public static final int ACTION_ID_FIELD_NUMBER = 200;
        /* access modifiers changed from: private */
        public static final DatastoreAction DEFAULT_INSTANCE;
        public static final int FIRESTORE_V1_ACTION_FIELD_NUMBER = 3;
        private static volatile Parser<DatastoreAction> PARSER = null;
        public static final int VALIDATION_RULE_FIELD_NUMBER = 201;
        private int actionCase_ = 0;
        private int actionId_;
        private Object action_;
        private ValidationRule validationRule_;

        private DatastoreAction() {
        }

        public enum ActionCase {
            FIRESTORE_V1_ACTION(3),
            ACTION_NOT_SET(0);
            
            private final int value;

            private ActionCase(int value2) {
                this.value = value2;
            }

            @Deprecated
            public static ActionCase valueOf(int value2) {
                return forNumber(value2);
            }

            public static ActionCase forNumber(int value2) {
                switch (value2) {
                    case 0:
                        return ACTION_NOT_SET;
                    case 3:
                        return FIRESTORE_V1_ACTION;
                    default:
                        return null;
                }
            }

            public int getNumber() {
                return this.value;
            }
        }

        public ActionCase getActionCase() {
            return ActionCase.forNumber(this.actionCase_);
        }

        /* access modifiers changed from: private */
        public void clearAction() {
            this.actionCase_ = 0;
            this.action_ = null;
        }

        public boolean hasFirestoreV1Action() {
            return this.actionCase_ == 3;
        }

        public FirestoreV1Action getFirestoreV1Action() {
            if (this.actionCase_ == 3) {
                return (FirestoreV1Action) this.action_;
            }
            return FirestoreV1Action.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setFirestoreV1Action(FirestoreV1Action value) {
            value.getClass();
            this.action_ = value;
            this.actionCase_ = 3;
        }

        /* access modifiers changed from: private */
        public void mergeFirestoreV1Action(FirestoreV1Action value) {
            value.getClass();
            if (this.actionCase_ != 3 || this.action_ == FirestoreV1Action.getDefaultInstance()) {
                this.action_ = value;
            } else {
                this.action_ = ((FirestoreV1Action.Builder) FirestoreV1Action.newBuilder((FirestoreV1Action) this.action_).mergeFrom(value)).buildPartial();
            }
            this.actionCase_ = 3;
        }

        /* access modifiers changed from: private */
        public void clearFirestoreV1Action() {
            if (this.actionCase_ == 3) {
                this.actionCase_ = 0;
                this.action_ = null;
            }
        }

        public int getActionId() {
            return this.actionId_;
        }

        /* access modifiers changed from: private */
        public void setActionId(int value) {
            this.actionId_ = value;
        }

        /* access modifiers changed from: private */
        public void clearActionId() {
            this.actionId_ = 0;
        }

        public boolean hasValidationRule() {
            return this.validationRule_ != null;
        }

        public ValidationRule getValidationRule() {
            ValidationRule validationRule = this.validationRule_;
            return validationRule == null ? ValidationRule.getDefaultInstance() : validationRule;
        }

        /* access modifiers changed from: private */
        public void setValidationRule(ValidationRule value) {
            value.getClass();
            this.validationRule_ = value;
        }

        /* access modifiers changed from: private */
        public void mergeValidationRule(ValidationRule value) {
            value.getClass();
            ValidationRule validationRule = this.validationRule_;
            if (validationRule == null || validationRule == ValidationRule.getDefaultInstance()) {
                this.validationRule_ = value;
            } else {
                this.validationRule_ = (ValidationRule) ((ValidationRule.Builder) ValidationRule.newBuilder(this.validationRule_).mergeFrom(value)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearValidationRule() {
            this.validationRule_ = null;
        }

        public static DatastoreAction parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DatastoreAction parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DatastoreAction parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DatastoreAction parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DatastoreAction parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DatastoreAction parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DatastoreAction parseFrom(InputStream input) throws IOException {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DatastoreAction parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DatastoreAction parseDelimitedFrom(InputStream input) throws IOException {
            return (DatastoreAction) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static DatastoreAction parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DatastoreAction) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DatastoreAction parseFrom(CodedInputStream input) throws IOException {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DatastoreAction parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DatastoreAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(DatastoreAction prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<DatastoreAction, Builder> implements DatastoreActionOrBuilder {
            /* synthetic */ Builder(C00551 x0) {
                this();
            }

            private Builder() {
                super(DatastoreAction.DEFAULT_INSTANCE);
            }

            public ActionCase getActionCase() {
                return ((DatastoreAction) this.instance).getActionCase();
            }

            public Builder clearAction() {
                copyOnWrite();
                ((DatastoreAction) this.instance).clearAction();
                return this;
            }

            public boolean hasFirestoreV1Action() {
                return ((DatastoreAction) this.instance).hasFirestoreV1Action();
            }

            public FirestoreV1Action getFirestoreV1Action() {
                return ((DatastoreAction) this.instance).getFirestoreV1Action();
            }

            public Builder setFirestoreV1Action(FirestoreV1Action value) {
                copyOnWrite();
                ((DatastoreAction) this.instance).setFirestoreV1Action(value);
                return this;
            }

            public Builder setFirestoreV1Action(FirestoreV1Action.Builder builderForValue) {
                copyOnWrite();
                ((DatastoreAction) this.instance).setFirestoreV1Action((FirestoreV1Action) builderForValue.build());
                return this;
            }

            public Builder mergeFirestoreV1Action(FirestoreV1Action value) {
                copyOnWrite();
                ((DatastoreAction) this.instance).mergeFirestoreV1Action(value);
                return this;
            }

            public Builder clearFirestoreV1Action() {
                copyOnWrite();
                ((DatastoreAction) this.instance).clearFirestoreV1Action();
                return this;
            }

            public int getActionId() {
                return ((DatastoreAction) this.instance).getActionId();
            }

            public Builder setActionId(int value) {
                copyOnWrite();
                ((DatastoreAction) this.instance).setActionId(value);
                return this;
            }

            public Builder clearActionId() {
                copyOnWrite();
                ((DatastoreAction) this.instance).clearActionId();
                return this;
            }

            public boolean hasValidationRule() {
                return ((DatastoreAction) this.instance).hasValidationRule();
            }

            public ValidationRule getValidationRule() {
                return ((DatastoreAction) this.instance).getValidationRule();
            }

            public Builder setValidationRule(ValidationRule value) {
                copyOnWrite();
                ((DatastoreAction) this.instance).setValidationRule(value);
                return this;
            }

            public Builder setValidationRule(ValidationRule.Builder builderForValue) {
                copyOnWrite();
                ((DatastoreAction) this.instance).setValidationRule((ValidationRule) builderForValue.build());
                return this;
            }

            public Builder mergeValidationRule(ValidationRule value) {
                copyOnWrite();
                ((DatastoreAction) this.instance).mergeValidationRule(value);
                return this;
            }

            public Builder clearValidationRule() {
                copyOnWrite();
                ((DatastoreAction) this.instance).clearValidationRule();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C00551.f47xa1df5c61[method.ordinal()]) {
                case 1:
                    return new DatastoreAction();
                case 2:
                    return new Builder((C00551) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0001\u0000\u0003É\u0003\u0000\u0000\u0000\u0003<\u0000È\u0004É\t", new Object[]{"action_", "actionCase_", FirestoreV1Action.class, "actionId_", "validationRule_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<DatastoreAction> parser = PARSER;
                    if (parser == null) {
                        synchronized (DatastoreAction.class) {
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
            DatastoreAction defaultInstance = new DatastoreAction();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(DatastoreAction.class, defaultInstance);
        }

        public static DatastoreAction getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DatastoreAction> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class TestTrace extends GeneratedMessageLite<TestTrace, Builder> implements TestTraceOrBuilder {
        public static final int ACTION_FIELD_NUMBER = 2;
        /* access modifiers changed from: private */
        public static final TestTrace DEFAULT_INSTANCE;
        private static volatile Parser<TestTrace> PARSER = null;
        public static final int TRACE_DESCRIPTION_FIELD_NUMBER = 3;
        public static final int TRACE_ID_FIELD_NUMBER = 1;
        private Internal.ProtobufList<DatastoreAction> action_ = emptyProtobufList();
        private String traceDescription_ = "";
        private String traceId_ = "";

        private TestTrace() {
        }

        public String getTraceId() {
            return this.traceId_;
        }

        public ByteString getTraceIdBytes() {
            return ByteString.copyFromUtf8(this.traceId_);
        }

        /* access modifiers changed from: private */
        public void setTraceId(String value) {
            value.getClass();
            this.traceId_ = value;
        }

        /* access modifiers changed from: private */
        public void clearTraceId() {
            this.traceId_ = getDefaultInstance().getTraceId();
        }

        /* access modifiers changed from: private */
        public void setTraceIdBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.traceId_ = value.toStringUtf8();
        }

        public List<DatastoreAction> getActionList() {
            return this.action_;
        }

        public List<? extends DatastoreActionOrBuilder> getActionOrBuilderList() {
            return this.action_;
        }

        public int getActionCount() {
            return this.action_.size();
        }

        public DatastoreAction getAction(int index) {
            return (DatastoreAction) this.action_.get(index);
        }

        public DatastoreActionOrBuilder getActionOrBuilder(int index) {
            return (DatastoreActionOrBuilder) this.action_.get(index);
        }

        private void ensureActionIsMutable() {
            Internal.ProtobufList<DatastoreAction> tmp = this.action_;
            if (!tmp.isModifiable()) {
                this.action_ = GeneratedMessageLite.mutableCopy(tmp);
            }
        }

        /* access modifiers changed from: private */
        public void setAction(int index, DatastoreAction value) {
            value.getClass();
            ensureActionIsMutable();
            this.action_.set(index, value);
        }

        /* access modifiers changed from: private */
        public void addAction(DatastoreAction value) {
            value.getClass();
            ensureActionIsMutable();
            this.action_.add(value);
        }

        /* access modifiers changed from: private */
        public void addAction(int index, DatastoreAction value) {
            value.getClass();
            ensureActionIsMutable();
            this.action_.add(index, value);
        }

        /* access modifiers changed from: private */
        public void addAllAction(Iterable<? extends DatastoreAction> values) {
            ensureActionIsMutable();
            AbstractMessageLite.addAll(values, this.action_);
        }

        /* access modifiers changed from: private */
        public void clearAction() {
            this.action_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeAction(int index) {
            ensureActionIsMutable();
            this.action_.remove(index);
        }

        public String getTraceDescription() {
            return this.traceDescription_;
        }

        public ByteString getTraceDescriptionBytes() {
            return ByteString.copyFromUtf8(this.traceDescription_);
        }

        /* access modifiers changed from: private */
        public void setTraceDescription(String value) {
            value.getClass();
            this.traceDescription_ = value;
        }

        /* access modifiers changed from: private */
        public void clearTraceDescription() {
            this.traceDescription_ = getDefaultInstance().getTraceDescription();
        }

        /* access modifiers changed from: private */
        public void setTraceDescriptionBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.traceDescription_ = value.toStringUtf8();
        }

        public static TestTrace parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static TestTrace parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static TestTrace parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static TestTrace parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static TestTrace parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static TestTrace parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static TestTrace parseFrom(InputStream input) throws IOException {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static TestTrace parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static TestTrace parseDelimitedFrom(InputStream input) throws IOException {
            return (TestTrace) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static TestTrace parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TestTrace) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static TestTrace parseFrom(CodedInputStream input) throws IOException {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static TestTrace parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(TestTrace prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<TestTrace, Builder> implements TestTraceOrBuilder {
            /* synthetic */ Builder(C00551 x0) {
                this();
            }

            private Builder() {
                super(TestTrace.DEFAULT_INSTANCE);
            }

            public String getTraceId() {
                return ((TestTrace) this.instance).getTraceId();
            }

            public ByteString getTraceIdBytes() {
                return ((TestTrace) this.instance).getTraceIdBytes();
            }

            public Builder setTraceId(String value) {
                copyOnWrite();
                ((TestTrace) this.instance).setTraceId(value);
                return this;
            }

            public Builder clearTraceId() {
                copyOnWrite();
                ((TestTrace) this.instance).clearTraceId();
                return this;
            }

            public Builder setTraceIdBytes(ByteString value) {
                copyOnWrite();
                ((TestTrace) this.instance).setTraceIdBytes(value);
                return this;
            }

            public List<DatastoreAction> getActionList() {
                return Collections.unmodifiableList(((TestTrace) this.instance).getActionList());
            }

            public int getActionCount() {
                return ((TestTrace) this.instance).getActionCount();
            }

            public DatastoreAction getAction(int index) {
                return ((TestTrace) this.instance).getAction(index);
            }

            public Builder setAction(int index, DatastoreAction value) {
                copyOnWrite();
                ((TestTrace) this.instance).setAction(index, value);
                return this;
            }

            public Builder setAction(int index, DatastoreAction.Builder builderForValue) {
                copyOnWrite();
                ((TestTrace) this.instance).setAction(index, (DatastoreAction) builderForValue.build());
                return this;
            }

            public Builder addAction(DatastoreAction value) {
                copyOnWrite();
                ((TestTrace) this.instance).addAction(value);
                return this;
            }

            public Builder addAction(int index, DatastoreAction value) {
                copyOnWrite();
                ((TestTrace) this.instance).addAction(index, value);
                return this;
            }

            public Builder addAction(DatastoreAction.Builder builderForValue) {
                copyOnWrite();
                ((TestTrace) this.instance).addAction((DatastoreAction) builderForValue.build());
                return this;
            }

            public Builder addAction(int index, DatastoreAction.Builder builderForValue) {
                copyOnWrite();
                ((TestTrace) this.instance).addAction(index, (DatastoreAction) builderForValue.build());
                return this;
            }

            public Builder addAllAction(Iterable<? extends DatastoreAction> values) {
                copyOnWrite();
                ((TestTrace) this.instance).addAllAction(values);
                return this;
            }

            public Builder clearAction() {
                copyOnWrite();
                ((TestTrace) this.instance).clearAction();
                return this;
            }

            public Builder removeAction(int index) {
                copyOnWrite();
                ((TestTrace) this.instance).removeAction(index);
                return this;
            }

            public String getTraceDescription() {
                return ((TestTrace) this.instance).getTraceDescription();
            }

            public ByteString getTraceDescriptionBytes() {
                return ((TestTrace) this.instance).getTraceDescriptionBytes();
            }

            public Builder setTraceDescription(String value) {
                copyOnWrite();
                ((TestTrace) this.instance).setTraceDescription(value);
                return this;
            }

            public Builder clearTraceDescription() {
                copyOnWrite();
                ((TestTrace) this.instance).clearTraceDescription();
                return this;
            }

            public Builder setTraceDescriptionBytes(ByteString value) {
                copyOnWrite();
                ((TestTrace) this.instance).setTraceDescriptionBytes(value);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C00551.f47xa1df5c61[method.ordinal()]) {
                case 1:
                    return new TestTrace();
                case 2:
                    return new Builder((C00551) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001Ȉ\u0002\u001b\u0003Ȉ", new Object[]{"traceId_", "action_", DatastoreAction.class, "traceDescription_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<TestTrace> parser = PARSER;
                    if (parser == null) {
                        synchronized (TestTrace.class) {
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
            TestTrace defaultInstance = new TestTrace();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(TestTrace.class, defaultInstance);
        }

        public static TestTrace getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<TestTrace> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ParallelTestTrace extends GeneratedMessageLite<ParallelTestTrace, Builder> implements ParallelTestTraceOrBuilder {
        /* access modifiers changed from: private */
        public static final ParallelTestTrace DEFAULT_INSTANCE;
        private static volatile Parser<ParallelTestTrace> PARSER = null;
        public static final int TEST_TRACE_FIELD_NUMBER = 1;
        private TestTrace testTrace_;

        private ParallelTestTrace() {
        }

        public boolean hasTestTrace() {
            return this.testTrace_ != null;
        }

        public TestTrace getTestTrace() {
            TestTrace testTrace = this.testTrace_;
            return testTrace == null ? TestTrace.getDefaultInstance() : testTrace;
        }

        /* access modifiers changed from: private */
        public void setTestTrace(TestTrace value) {
            value.getClass();
            this.testTrace_ = value;
        }

        /* access modifiers changed from: private */
        public void mergeTestTrace(TestTrace value) {
            value.getClass();
            TestTrace testTrace = this.testTrace_;
            if (testTrace == null || testTrace == TestTrace.getDefaultInstance()) {
                this.testTrace_ = value;
            } else {
                this.testTrace_ = (TestTrace) ((TestTrace.Builder) TestTrace.newBuilder(this.testTrace_).mergeFrom(value)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearTestTrace() {
            this.testTrace_ = null;
        }

        public static ParallelTestTrace parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ParallelTestTrace parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ParallelTestTrace parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ParallelTestTrace parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ParallelTestTrace parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ParallelTestTrace parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ParallelTestTrace parseFrom(InputStream input) throws IOException {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ParallelTestTrace parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ParallelTestTrace parseDelimitedFrom(InputStream input) throws IOException {
            return (ParallelTestTrace) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ParallelTestTrace parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ParallelTestTrace) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ParallelTestTrace parseFrom(CodedInputStream input) throws IOException {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ParallelTestTrace parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ParallelTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(ParallelTestTrace prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ParallelTestTrace, Builder> implements ParallelTestTraceOrBuilder {
            /* synthetic */ Builder(C00551 x0) {
                this();
            }

            private Builder() {
                super(ParallelTestTrace.DEFAULT_INSTANCE);
            }

            public boolean hasTestTrace() {
                return ((ParallelTestTrace) this.instance).hasTestTrace();
            }

            public TestTrace getTestTrace() {
                return ((ParallelTestTrace) this.instance).getTestTrace();
            }

            public Builder setTestTrace(TestTrace value) {
                copyOnWrite();
                ((ParallelTestTrace) this.instance).setTestTrace(value);
                return this;
            }

            public Builder setTestTrace(TestTrace.Builder builderForValue) {
                copyOnWrite();
                ((ParallelTestTrace) this.instance).setTestTrace((TestTrace) builderForValue.build());
                return this;
            }

            public Builder mergeTestTrace(TestTrace value) {
                copyOnWrite();
                ((ParallelTestTrace) this.instance).mergeTestTrace(value);
                return this;
            }

            public Builder clearTestTrace() {
                copyOnWrite();
                ((ParallelTestTrace) this.instance).clearTestTrace();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C00551.f47xa1df5c61[method.ordinal()]) {
                case 1:
                    return new ParallelTestTrace();
                case 2:
                    return new Builder((C00551) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"testTrace_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<ParallelTestTrace> parser = PARSER;
                    if (parser == null) {
                        synchronized (ParallelTestTrace.class) {
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
            ParallelTestTrace defaultInstance = new ParallelTestTrace();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(ParallelTestTrace.class, defaultInstance);
        }

        public static ParallelTestTrace getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ParallelTestTrace> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class TimelineTestTrace extends GeneratedMessageLite<TimelineTestTrace, Builder> implements TimelineTestTraceOrBuilder {
        /* access modifiers changed from: private */
        public static final TimelineTestTrace DEFAULT_INSTANCE;
        private static volatile Parser<TimelineTestTrace> PARSER = null;
        public static final int TEST_TRACE_FIELD_NUMBER = 1;
        private TestTrace testTrace_;

        private TimelineTestTrace() {
        }

        public boolean hasTestTrace() {
            return this.testTrace_ != null;
        }

        public TestTrace getTestTrace() {
            TestTrace testTrace = this.testTrace_;
            return testTrace == null ? TestTrace.getDefaultInstance() : testTrace;
        }

        /* access modifiers changed from: private */
        public void setTestTrace(TestTrace value) {
            value.getClass();
            this.testTrace_ = value;
        }

        /* access modifiers changed from: private */
        public void mergeTestTrace(TestTrace value) {
            value.getClass();
            TestTrace testTrace = this.testTrace_;
            if (testTrace == null || testTrace == TestTrace.getDefaultInstance()) {
                this.testTrace_ = value;
            } else {
                this.testTrace_ = (TestTrace) ((TestTrace.Builder) TestTrace.newBuilder(this.testTrace_).mergeFrom(value)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearTestTrace() {
            this.testTrace_ = null;
        }

        public static TimelineTestTrace parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static TimelineTestTrace parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static TimelineTestTrace parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static TimelineTestTrace parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static TimelineTestTrace parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static TimelineTestTrace parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static TimelineTestTrace parseFrom(InputStream input) throws IOException {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static TimelineTestTrace parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static TimelineTestTrace parseDelimitedFrom(InputStream input) throws IOException {
            return (TimelineTestTrace) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static TimelineTestTrace parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TimelineTestTrace) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static TimelineTestTrace parseFrom(CodedInputStream input) throws IOException {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static TimelineTestTrace parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TimelineTestTrace) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(TimelineTestTrace prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<TimelineTestTrace, Builder> implements TimelineTestTraceOrBuilder {
            /* synthetic */ Builder(C00551 x0) {
                this();
            }

            private Builder() {
                super(TimelineTestTrace.DEFAULT_INSTANCE);
            }

            public boolean hasTestTrace() {
                return ((TimelineTestTrace) this.instance).hasTestTrace();
            }

            public TestTrace getTestTrace() {
                return ((TimelineTestTrace) this.instance).getTestTrace();
            }

            public Builder setTestTrace(TestTrace value) {
                copyOnWrite();
                ((TimelineTestTrace) this.instance).setTestTrace(value);
                return this;
            }

            public Builder setTestTrace(TestTrace.Builder builderForValue) {
                copyOnWrite();
                ((TimelineTestTrace) this.instance).setTestTrace((TestTrace) builderForValue.build());
                return this;
            }

            public Builder mergeTestTrace(TestTrace value) {
                copyOnWrite();
                ((TimelineTestTrace) this.instance).mergeTestTrace(value);
                return this;
            }

            public Builder clearTestTrace() {
                copyOnWrite();
                ((TimelineTestTrace) this.instance).clearTestTrace();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C00551.f47xa1df5c61[method.ordinal()]) {
                case 1:
                    return new TimelineTestTrace();
                case 2:
                    return new Builder((C00551) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"testTrace_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<TimelineTestTrace> parser = PARSER;
                    if (parser == null) {
                        synchronized (TimelineTestTrace.class) {
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
            TimelineTestTrace defaultInstance = new TimelineTestTrace();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(TimelineTestTrace.class, defaultInstance);
        }

        public static TimelineTestTrace getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<TimelineTestTrace> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
