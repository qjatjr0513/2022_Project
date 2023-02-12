package com.google.firebase.firestore.local;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.proto.MaybeDocument;
import com.google.firebase.firestore.proto.NoDocument;
import com.google.firebase.firestore.proto.Target;
import com.google.firebase.firestore.proto.UnknownDocument;
import com.google.firebase.firestore.proto.WriteBatch;
import com.google.firebase.firestore.remote.RemoteSerializer;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.admin.p001v1.Index;
import com.google.firestore.bundle.BundledQuery;
import com.google.firestore.p002v1.Document;
import com.google.firestore.p002v1.DocumentTransform;
import com.google.firestore.p002v1.Target;
import com.google.firestore.p002v1.Write;
import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.List;

public final class LocalSerializer {
    private final RemoteSerializer rpcSerializer;

    public LocalSerializer(RemoteSerializer rpcSerializer2) {
        this.rpcSerializer = rpcSerializer2;
    }

    /* access modifiers changed from: package-private */
    public MaybeDocument encodeMaybeDocument(Document document) {
        MaybeDocument.Builder builder = MaybeDocument.newBuilder();
        if (document.isNoDocument()) {
            builder.setNoDocument(encodeNoDocument(document));
        } else if (document.isFoundDocument()) {
            builder.setDocument(encodeDocument(document));
        } else if (document.isUnknownDocument()) {
            builder.setUnknownDocument(encodeUnknownDocument(document));
        } else {
            throw Assert.fail("Cannot encode invalid document %s", document);
        }
        builder.setHasCommittedMutations(document.hasCommittedMutations());
        return (MaybeDocument) builder.build();
    }

    /* access modifiers changed from: package-private */
    public MutableDocument decodeMaybeDocument(MaybeDocument proto) {
        switch (C07681.f158xe45654f0[proto.getDocumentTypeCase().ordinal()]) {
            case 1:
                return decodeDocument(proto.getDocument(), proto.getHasCommittedMutations());
            case 2:
                return decodeNoDocument(proto.getNoDocument(), proto.getHasCommittedMutations());
            case 3:
                return decodeUnknownDocument(proto.getUnknownDocument());
            default:
                throw Assert.fail("Unknown MaybeDocument %s", proto);
        }
    }

    private com.google.firestore.p002v1.Document encodeDocument(Document document) {
        Document.Builder builder = com.google.firestore.p002v1.Document.newBuilder();
        builder.setName(this.rpcSerializer.encodeKey(document.getKey()));
        builder.putAllFields(document.getData().getFieldsMap());
        builder.setUpdateTime(this.rpcSerializer.encodeTimestamp(document.getVersion().getTimestamp()));
        return (com.google.firestore.p002v1.Document) builder.build();
    }

    private MutableDocument decodeDocument(com.google.firestore.p002v1.Document document, boolean hasCommittedMutations) {
        MutableDocument result = MutableDocument.newFoundDocument(this.rpcSerializer.decodeKey(document.getName()), this.rpcSerializer.decodeVersion(document.getUpdateTime()), ObjectValue.fromMap(document.getFieldsMap()));
        return hasCommittedMutations ? result.setHasCommittedMutations() : result;
    }

    private NoDocument encodeNoDocument(com.google.firebase.firestore.model.Document document) {
        NoDocument.Builder builder = NoDocument.newBuilder();
        builder.setName(this.rpcSerializer.encodeKey(document.getKey()));
        builder.setReadTime(this.rpcSerializer.encodeTimestamp(document.getVersion().getTimestamp()));
        return (NoDocument) builder.build();
    }

    private MutableDocument decodeNoDocument(NoDocument proto, boolean hasCommittedMutations) {
        MutableDocument result = MutableDocument.newNoDocument(this.rpcSerializer.decodeKey(proto.getName()), this.rpcSerializer.decodeVersion(proto.getReadTime()));
        return hasCommittedMutations ? result.setHasCommittedMutations() : result;
    }

    private UnknownDocument encodeUnknownDocument(com.google.firebase.firestore.model.Document document) {
        UnknownDocument.Builder builder = UnknownDocument.newBuilder();
        builder.setName(this.rpcSerializer.encodeKey(document.getKey()));
        builder.setVersion(this.rpcSerializer.encodeTimestamp(document.getVersion().getTimestamp()));
        return (UnknownDocument) builder.build();
    }

    private MutableDocument decodeUnknownDocument(UnknownDocument proto) {
        return MutableDocument.newUnknownDocument(this.rpcSerializer.decodeKey(proto.getName()), this.rpcSerializer.decodeVersion(proto.getVersion()));
    }

    /* access modifiers changed from: package-private */
    public WriteBatch encodeMutationBatch(MutationBatch batch) {
        WriteBatch.Builder result = WriteBatch.newBuilder();
        result.setBatchId(batch.getBatchId());
        result.setLocalWriteTime(this.rpcSerializer.encodeTimestamp(batch.getLocalWriteTime()));
        for (Mutation mutation : batch.getBaseMutations()) {
            result.addBaseWrites(this.rpcSerializer.encodeMutation(mutation));
        }
        for (Mutation mutation2 : batch.getMutations()) {
            result.addWrites(this.rpcSerializer.encodeMutation(mutation2));
        }
        return (WriteBatch) result.build();
    }

    /* access modifiers changed from: package-private */
    public MutationBatch decodeMutationBatch(WriteBatch batch) {
        int batchId = batch.getBatchId();
        Timestamp localWriteTime = this.rpcSerializer.decodeTimestamp(batch.getLocalWriteTime());
        int baseMutationsCount = batch.getBaseWritesCount();
        List<Mutation> baseMutations = new ArrayList<>(baseMutationsCount);
        for (int i = 0; i < baseMutationsCount; i++) {
            baseMutations.add(this.rpcSerializer.decodeMutation(batch.getBaseWrites(i)));
        }
        List<Mutation> mutations = new ArrayList<>(batch.getWritesCount());
        int i2 = 0;
        while (i2 < batch.getWritesCount()) {
            Write currentMutation = batch.getWrites(i2);
            if (i2 + 1 < batch.getWritesCount() && batch.getWrites(i2 + 1).hasTransform()) {
                Assert.hardAssert(batch.getWrites(i2).hasUpdate(), "TransformMutation should be preceded by a patch or set mutation", new Object[0]);
                Write.Builder newMutationBuilder = Write.newBuilder(currentMutation);
                for (DocumentTransform.FieldTransform fieldTransform : batch.getWrites(i2 + 1).getTransform().getFieldTransformsList()) {
                    newMutationBuilder.addUpdateTransforms(fieldTransform);
                }
                mutations.add(this.rpcSerializer.decodeMutation((Write) newMutationBuilder.build()));
                i2++;
            } else {
                mutations.add(this.rpcSerializer.decodeMutation(currentMutation));
            }
            i2++;
        }
        return new MutationBatch(batchId, localWriteTime, baseMutations, mutations);
    }

    /* access modifiers changed from: package-private */
    public Target encodeTargetData(TargetData targetData) {
        Assert.hardAssert(QueryPurpose.LISTEN.equals(targetData.getPurpose()), "Only queries with purpose %s may be stored, got %s", QueryPurpose.LISTEN, targetData.getPurpose());
        Target.Builder result = Target.newBuilder();
        result.setTargetId(targetData.getTargetId()).setLastListenSequenceNumber(targetData.getSequenceNumber()).setLastLimboFreeSnapshotVersion(this.rpcSerializer.encodeVersion(targetData.getLastLimboFreeSnapshotVersion())).setSnapshotVersion(this.rpcSerializer.encodeVersion(targetData.getSnapshotVersion())).setResumeToken(targetData.getResumeToken());
        com.google.firebase.firestore.core.Target target = targetData.getTarget();
        if (target.isDocumentQuery()) {
            result.setDocuments(this.rpcSerializer.encodeDocumentsTarget(target));
        } else {
            result.setQuery(this.rpcSerializer.encodeQueryTarget(target));
        }
        return (Target) result.build();
    }

    /* access modifiers changed from: package-private */
    public TargetData decodeTargetData(Target targetProto) {
        com.google.firebase.firestore.core.Target target;
        int targetId = targetProto.getTargetId();
        SnapshotVersion version = this.rpcSerializer.decodeVersion(targetProto.getSnapshotVersion());
        SnapshotVersion lastLimboFreeSnapshotVersion = this.rpcSerializer.decodeVersion(targetProto.getLastLimboFreeSnapshotVersion());
        ByteString resumeToken = targetProto.getResumeToken();
        long sequenceNumber = targetProto.getLastListenSequenceNumber();
        switch (C07681.f159x5167ea64[targetProto.getTargetTypeCase().ordinal()]) {
            case 1:
                target = this.rpcSerializer.decodeDocumentsTarget(targetProto.getDocuments());
                break;
            case 2:
                target = this.rpcSerializer.decodeQueryTarget(targetProto.getQuery());
                break;
            default:
                throw Assert.fail("Unknown targetType %d", targetProto.getTargetTypeCase());
        }
        return new TargetData(target, targetId, sequenceNumber, QueryPurpose.LISTEN, version, lastLimboFreeSnapshotVersion, resumeToken);
    }

    /* renamed from: com.google.firebase.firestore.local.LocalSerializer$1 */
    static /* synthetic */ class C07681 {

        /* renamed from: $SwitchMap$com$google$firebase$firestore$proto$MaybeDocument$DocumentTypeCase */
        static final /* synthetic */ int[] f158xe45654f0;

        /* renamed from: $SwitchMap$com$google$firebase$firestore$proto$Target$TargetTypeCase */
        static final /* synthetic */ int[] f159x5167ea64;

        static {
            int[] iArr = new int[Target.TargetTypeCase.values().length];
            f159x5167ea64 = iArr;
            try {
                iArr[Target.TargetTypeCase.DOCUMENTS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f159x5167ea64[Target.TargetTypeCase.QUERY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            int[] iArr2 = new int[MaybeDocument.DocumentTypeCase.values().length];
            f158xe45654f0 = iArr2;
            try {
                iArr2[MaybeDocument.DocumentTypeCase.DOCUMENT.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f158xe45654f0[MaybeDocument.DocumentTypeCase.NO_DOCUMENT.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f158xe45654f0[MaybeDocument.DocumentTypeCase.UNKNOWN_DOCUMENT.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public BundledQuery encodeBundledQuery(com.google.firebase.firestore.bundle.BundledQuery bundledQuery) {
        BundledQuery.LimitType limitType;
        Target.QueryTarget queryTarget = this.rpcSerializer.encodeQueryTarget(bundledQuery.getTarget());
        BundledQuery.Builder result = BundledQuery.newBuilder();
        if (bundledQuery.getLimitType().equals(Query.LimitType.LIMIT_TO_FIRST)) {
            limitType = BundledQuery.LimitType.FIRST;
        } else {
            limitType = BundledQuery.LimitType.LAST;
        }
        result.setLimitType(limitType);
        result.setParent(queryTarget.getParent());
        result.setStructuredQuery(queryTarget.getStructuredQuery());
        return (BundledQuery) result.build();
    }

    public com.google.firebase.firestore.bundle.BundledQuery decodeBundledQuery(BundledQuery bundledQuery) {
        Query.LimitType limitType;
        if (bundledQuery.getLimitType().equals(BundledQuery.LimitType.FIRST)) {
            limitType = Query.LimitType.LIMIT_TO_FIRST;
        } else {
            limitType = Query.LimitType.LIMIT_TO_LAST;
        }
        return new com.google.firebase.firestore.bundle.BundledQuery(this.rpcSerializer.decodeQueryTarget(bundledQuery.getParent(), bundledQuery.getStructuredQuery()), limitType);
    }

    public Index encodeFieldIndexSegments(List<FieldIndex.Segment> segments) {
        Index.Builder index = Index.newBuilder();
        index.setQueryScope(Index.QueryScope.COLLECTION_GROUP);
        for (FieldIndex.Segment segment : segments) {
            Index.IndexField.Builder indexField = Index.IndexField.newBuilder();
            indexField.setFieldPath(segment.getFieldPath().canonicalString());
            if (segment.getKind() == FieldIndex.Segment.Kind.CONTAINS) {
                indexField.setArrayConfig(Index.IndexField.ArrayConfig.CONTAINS);
            } else if (segment.getKind() == FieldIndex.Segment.Kind.ASCENDING) {
                indexField.setOrder(Index.IndexField.Order.ASCENDING);
            } else {
                indexField.setOrder(Index.IndexField.Order.DESCENDING);
            }
            index.addFields(indexField);
        }
        return (Index) index.build();
    }

    public List<FieldIndex.Segment> decodeFieldIndexSegments(Index index) {
        FieldIndex.Segment.Kind kind;
        List<FieldIndex.Segment> result = new ArrayList<>();
        for (Index.IndexField field : index.getFieldsList()) {
            FieldPath fieldPath = FieldPath.fromServerFormat(field.getFieldPath());
            if (field.getValueModeCase().equals(Index.IndexField.ValueModeCase.ARRAY_CONFIG)) {
                kind = FieldIndex.Segment.Kind.CONTAINS;
            } else if (field.getOrder().equals(Index.IndexField.Order.ASCENDING)) {
                kind = FieldIndex.Segment.Kind.ASCENDING;
            } else {
                kind = FieldIndex.Segment.Kind.DESCENDING;
            }
            result.add(FieldIndex.Segment.create(fieldPath, kind));
        }
        return result;
    }

    public Mutation decodeMutation(Write mutation) {
        return this.rpcSerializer.decodeMutation(mutation);
    }

    public Write encodeMutation(Mutation mutation) {
        return this.rpcSerializer.encodeMutation(mutation);
    }
}
