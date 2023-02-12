package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.core.CompositeFilter;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.local.QueryPurpose;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.model.mutation.ArrayTransformOperation;
import com.google.firebase.firestore.model.mutation.DeleteMutation;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.model.mutation.FieldTransform;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationResult;
import com.google.firebase.firestore.model.mutation.NumericIncrementTransformOperation;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.model.mutation.ServerTimestampOperation;
import com.google.firebase.firestore.model.mutation.SetMutation;
import com.google.firebase.firestore.model.mutation.TransformOperation;
import com.google.firebase.firestore.model.mutation.VerifyMutation;
import com.google.firebase.firestore.remote.WatchChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.p002v1.ArrayValue;
import com.google.firestore.p002v1.BatchGetDocumentsResponse;
import com.google.firestore.p002v1.Cursor;
import com.google.firestore.p002v1.Document;
import com.google.firestore.p002v1.DocumentChange;
import com.google.firestore.p002v1.DocumentDelete;
import com.google.firestore.p002v1.DocumentMask;
import com.google.firestore.p002v1.DocumentRemove;
import com.google.firestore.p002v1.DocumentTransform;
import com.google.firestore.p002v1.ExistenceFilter;
import com.google.firestore.p002v1.ListenResponse;
import com.google.firestore.p002v1.Precondition;
import com.google.firestore.p002v1.StructuredQuery;
import com.google.firestore.p002v1.Target;
import com.google.firestore.p002v1.TargetChange;
import com.google.firestore.p002v1.Value;
import com.google.firestore.p002v1.Write;
import com.google.firestore.p002v1.WriteResult;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import p004io.grpc.Status;

public final class RemoteSerializer {
    private final DatabaseId databaseId;
    private final String databaseName;

    public RemoteSerializer(DatabaseId databaseId2) {
        this.databaseId = databaseId2;
        this.databaseName = encodedDatabaseId(databaseId2).canonicalString();
    }

    public Timestamp encodeTimestamp(com.google.firebase.Timestamp timestamp) {
        Timestamp.Builder builder = Timestamp.newBuilder();
        builder.setSeconds(timestamp.getSeconds());
        builder.setNanos(timestamp.getNanoseconds());
        return (Timestamp) builder.build();
    }

    public com.google.firebase.Timestamp decodeTimestamp(Timestamp proto) {
        return new com.google.firebase.Timestamp(proto.getSeconds(), proto.getNanos());
    }

    public Timestamp encodeVersion(SnapshotVersion version) {
        return encodeTimestamp(version.getTimestamp());
    }

    public SnapshotVersion decodeVersion(Timestamp proto) {
        if (proto.getSeconds() == 0 && proto.getNanos() == 0) {
            return SnapshotVersion.NONE;
        }
        return new SnapshotVersion(decodeTimestamp(proto));
    }

    public String encodeKey(DocumentKey key) {
        return encodeResourceName(this.databaseId, key.getPath());
    }

    public DocumentKey decodeKey(String name) {
        ResourcePath resource = decodeResourceName(name);
        Assert.hardAssert(resource.getSegment(1).equals(this.databaseId.getProjectId()), "Tried to deserialize key from different project.", new Object[0]);
        Assert.hardAssert(resource.getSegment(3).equals(this.databaseId.getDatabaseId()), "Tried to deserialize key from different database.", new Object[0]);
        return DocumentKey.fromPath(extractLocalPathFromResourceName(resource));
    }

    private String encodeQueryPath(ResourcePath path) {
        return encodeResourceName(this.databaseId, path);
    }

    private ResourcePath decodeQueryPath(String name) {
        ResourcePath resource = decodeResourceName(name);
        if (resource.length() == 4) {
            return ResourcePath.EMPTY;
        }
        return extractLocalPathFromResourceName(resource);
    }

    private String encodeResourceName(DatabaseId databaseId2, ResourcePath path) {
        return ((ResourcePath) ((ResourcePath) encodedDatabaseId(databaseId2).append("documents")).append(path)).canonicalString();
    }

    private ResourcePath decodeResourceName(String encoded) {
        ResourcePath resource = ResourcePath.fromString(encoded);
        Assert.hardAssert(isValidResourceName(resource), "Tried to deserialize invalid key %s", resource);
        return resource;
    }

    private static ResourcePath encodedDatabaseId(DatabaseId databaseId2) {
        return ResourcePath.fromSegments(Arrays.asList(new String[]{"projects", databaseId2.getProjectId(), "databases", databaseId2.getDatabaseId()}));
    }

    private static ResourcePath extractLocalPathFromResourceName(ResourcePath resourceName) {
        Assert.hardAssert(resourceName.length() > 4 && resourceName.getSegment(4).equals("documents"), "Tried to deserialize invalid key %s", resourceName);
        return (ResourcePath) resourceName.popFirst(5);
    }

    private static boolean isValidResourceName(ResourcePath path) {
        if (path.length() < 4 || !path.getSegment(0).equals("projects") || !path.getSegment(2).equals("databases")) {
            return false;
        }
        return true;
    }

    public boolean isLocalResourceName(ResourcePath path) {
        if (!isValidResourceName(path) || !path.getSegment(1).equals(this.databaseId.getProjectId()) || !path.getSegment(3).equals(this.databaseId.getDatabaseId())) {
            return false;
        }
        return true;
    }

    public String databaseName() {
        return this.databaseName;
    }

    public Document encodeDocument(DocumentKey key, ObjectValue value) {
        Document.Builder builder = Document.newBuilder();
        builder.setName(encodeKey(key));
        builder.putAllFields(value.getFieldsMap());
        return (Document) builder.build();
    }

    public MutableDocument decodeMaybeDocument(BatchGetDocumentsResponse response) {
        if (response.getResultCase().equals(BatchGetDocumentsResponse.ResultCase.FOUND)) {
            return decodeFoundDocument(response);
        }
        if (response.getResultCase().equals(BatchGetDocumentsResponse.ResultCase.MISSING)) {
            return decodeMissingDocument(response);
        }
        throw new IllegalArgumentException("Unknown result case: " + response.getResultCase());
    }

    private MutableDocument decodeFoundDocument(BatchGetDocumentsResponse response) {
        Assert.hardAssert(response.getResultCase().equals(BatchGetDocumentsResponse.ResultCase.FOUND), "Tried to deserialize a found document from a missing document.", new Object[0]);
        DocumentKey key = decodeKey(response.getFound().getName());
        ObjectValue value = ObjectValue.fromMap(response.getFound().getFieldsMap());
        SnapshotVersion version = decodeVersion(response.getFound().getUpdateTime());
        Assert.hardAssert(!version.equals(SnapshotVersion.NONE), "Got a document response with no snapshot version", new Object[0]);
        return MutableDocument.newFoundDocument(key, version, value);
    }

    private MutableDocument decodeMissingDocument(BatchGetDocumentsResponse response) {
        Assert.hardAssert(response.getResultCase().equals(BatchGetDocumentsResponse.ResultCase.MISSING), "Tried to deserialize a missing document from a found document.", new Object[0]);
        DocumentKey key = decodeKey(response.getMissing());
        SnapshotVersion version = decodeVersion(response.getReadTime());
        Assert.hardAssert(!version.equals(SnapshotVersion.NONE), "Got a no document response with no snapshot version", new Object[0]);
        return MutableDocument.newNoDocument(key, version);
    }

    public Write encodeMutation(Mutation mutation) {
        Write.Builder builder = Write.newBuilder();
        if (mutation instanceof SetMutation) {
            builder.setUpdate(encodeDocument(mutation.getKey(), ((SetMutation) mutation).getValue()));
        } else if (mutation instanceof PatchMutation) {
            builder.setUpdate(encodeDocument(mutation.getKey(), ((PatchMutation) mutation).getValue()));
            builder.setUpdateMask(encodeDocumentMask(((PatchMutation) mutation).getMask()));
        } else if (mutation instanceof DeleteMutation) {
            builder.setDelete(encodeKey(mutation.getKey()));
        } else if (mutation instanceof VerifyMutation) {
            builder.setVerify(encodeKey(mutation.getKey()));
        } else {
            throw Assert.fail("unknown mutation type %s", mutation.getClass());
        }
        for (FieldTransform fieldTransform : mutation.getFieldTransforms()) {
            builder.addUpdateTransforms(encodeFieldTransform(fieldTransform));
        }
        if (!mutation.getPrecondition().isNone()) {
            builder.setCurrentDocument(encodePrecondition(mutation.getPrecondition()));
        }
        return (Write) builder.build();
    }

    public Mutation decodeMutation(Write mutation) {
        Precondition precondition;
        if (mutation.hasCurrentDocument()) {
            precondition = decodePrecondition(mutation.getCurrentDocument());
        } else {
            precondition = Precondition.NONE;
        }
        List<FieldTransform> fieldTransforms = new ArrayList<>();
        for (DocumentTransform.FieldTransform fieldTransform : mutation.getUpdateTransformsList()) {
            fieldTransforms.add(decodeFieldTransform(fieldTransform));
        }
        switch (C07911.$SwitchMap$com$google$firestore$v1$Write$OperationCase[mutation.getOperationCase().ordinal()]) {
            case 1:
                if (mutation.hasUpdateMask()) {
                    return new PatchMutation(decodeKey(mutation.getUpdate().getName()), ObjectValue.fromMap(mutation.getUpdate().getFieldsMap()), decodeDocumentMask(mutation.getUpdateMask()), precondition, fieldTransforms);
                }
                return new SetMutation(decodeKey(mutation.getUpdate().getName()), ObjectValue.fromMap(mutation.getUpdate().getFieldsMap()), precondition, fieldTransforms);
            case 2:
                return new DeleteMutation(decodeKey(mutation.getDelete()), precondition);
            case 3:
                return new VerifyMutation(decodeKey(mutation.getVerify()), precondition);
            default:
                throw Assert.fail("Unknown mutation operation: %d", mutation.getOperationCase());
        }
    }

    private com.google.firestore.p002v1.Precondition encodePrecondition(Precondition precondition) {
        Assert.hardAssert(!precondition.isNone(), "Can't serialize an empty precondition", new Object[0]);
        Precondition.Builder builder = com.google.firestore.p002v1.Precondition.newBuilder();
        if (precondition.getUpdateTime() != null) {
            return (com.google.firestore.p002v1.Precondition) builder.setUpdateTime(encodeVersion(precondition.getUpdateTime())).build();
        }
        if (precondition.getExists() != null) {
            return (com.google.firestore.p002v1.Precondition) builder.setExists(precondition.getExists().booleanValue()).build();
        }
        throw Assert.fail("Unknown Precondition", new Object[0]);
    }

    private com.google.firebase.firestore.model.mutation.Precondition decodePrecondition(com.google.firestore.p002v1.Precondition precondition) {
        switch (C07911.f183x8f18ca41[precondition.getConditionTypeCase().ordinal()]) {
            case 1:
                return com.google.firebase.firestore.model.mutation.Precondition.updateTime(decodeVersion(precondition.getUpdateTime()));
            case 2:
                return com.google.firebase.firestore.model.mutation.Precondition.exists(precondition.getExists());
            case 3:
                return com.google.firebase.firestore.model.mutation.Precondition.NONE;
            default:
                throw Assert.fail("Unknown precondition", new Object[0]);
        }
    }

    private DocumentMask encodeDocumentMask(FieldMask mask) {
        DocumentMask.Builder builder = DocumentMask.newBuilder();
        for (FieldPath path : mask.getMask()) {
            builder.addFieldPaths(path.canonicalString());
        }
        return (DocumentMask) builder.build();
    }

    private FieldMask decodeDocumentMask(DocumentMask mask) {
        int count = mask.getFieldPathsCount();
        Set<FieldPath> paths = new HashSet<>(count);
        for (int i = 0; i < count; i++) {
            paths.add(FieldPath.fromServerFormat(mask.getFieldPaths(i)));
        }
        return FieldMask.fromSet(paths);
    }

    private DocumentTransform.FieldTransform encodeFieldTransform(FieldTransform fieldTransform) {
        TransformOperation transform = fieldTransform.getOperation();
        if (transform instanceof ServerTimestampOperation) {
            return (DocumentTransform.FieldTransform) DocumentTransform.FieldTransform.newBuilder().setFieldPath(fieldTransform.getFieldPath().canonicalString()).setSetToServerValue(DocumentTransform.FieldTransform.ServerValue.REQUEST_TIME).build();
        }
        if (transform instanceof ArrayTransformOperation.Union) {
            return (DocumentTransform.FieldTransform) DocumentTransform.FieldTransform.newBuilder().setFieldPath(fieldTransform.getFieldPath().canonicalString()).setAppendMissingElements(ArrayValue.newBuilder().addAllValues(((ArrayTransformOperation.Union) transform).getElements())).build();
        }
        if (transform instanceof ArrayTransformOperation.Remove) {
            return (DocumentTransform.FieldTransform) DocumentTransform.FieldTransform.newBuilder().setFieldPath(fieldTransform.getFieldPath().canonicalString()).setRemoveAllFromArray(ArrayValue.newBuilder().addAllValues(((ArrayTransformOperation.Remove) transform).getElements())).build();
        }
        if (transform instanceof NumericIncrementTransformOperation) {
            return (DocumentTransform.FieldTransform) DocumentTransform.FieldTransform.newBuilder().setFieldPath(fieldTransform.getFieldPath().canonicalString()).setIncrement(((NumericIncrementTransformOperation) transform).getOperand()).build();
        }
        throw Assert.fail("Unknown transform: %s", transform);
    }

    private FieldTransform decodeFieldTransform(DocumentTransform.FieldTransform fieldTransform) {
        switch (C07911.f181xdd498c9f[fieldTransform.getTransformTypeCase().ordinal()]) {
            case 1:
                Assert.hardAssert(fieldTransform.getSetToServerValue() == DocumentTransform.FieldTransform.ServerValue.REQUEST_TIME, "Unknown transform setToServerValue: %s", fieldTransform.getSetToServerValue());
                return new FieldTransform(FieldPath.fromServerFormat(fieldTransform.getFieldPath()), ServerTimestampOperation.getInstance());
            case 2:
                return new FieldTransform(FieldPath.fromServerFormat(fieldTransform.getFieldPath()), new ArrayTransformOperation.Union(fieldTransform.getAppendMissingElements().getValuesList()));
            case 3:
                return new FieldTransform(FieldPath.fromServerFormat(fieldTransform.getFieldPath()), new ArrayTransformOperation.Remove(fieldTransform.getRemoveAllFromArray().getValuesList()));
            case 4:
                return new FieldTransform(FieldPath.fromServerFormat(fieldTransform.getFieldPath()), new NumericIncrementTransformOperation(fieldTransform.getIncrement()));
            default:
                throw Assert.fail("Unknown FieldTransform proto: %s", fieldTransform);
        }
    }

    public MutationResult decodeMutationResult(WriteResult proto, SnapshotVersion commitVersion) {
        SnapshotVersion version = decodeVersion(proto.getUpdateTime());
        if (SnapshotVersion.NONE.equals(version)) {
            version = commitVersion;
        }
        int transformResultsCount = proto.getTransformResultsCount();
        List<Value> transformResults = new ArrayList<>(transformResultsCount);
        for (int i = 0; i < transformResultsCount; i++) {
            transformResults.add(proto.getTransformResults(i));
        }
        return new MutationResult(version, transformResults);
    }

    public Map<String, String> encodeListenRequestLabels(TargetData targetData) {
        String value = encodeLabel(targetData.getPurpose());
        if (value == null) {
            return null;
        }
        Map<String, String> result = new HashMap<>(1);
        result.put("goog-listen-tags", value);
        return result;
    }

    private String encodeLabel(QueryPurpose purpose) {
        switch (C07911.$SwitchMap$com$google$firebase$firestore$local$QueryPurpose[purpose.ordinal()]) {
            case 1:
                return null;
            case 2:
                return "existence-filter-mismatch";
            case 3:
                return "limbo-document";
            default:
                throw Assert.fail("Unrecognized query purpose: %s", purpose);
        }
    }

    public Target encodeTarget(TargetData targetData) {
        Target.Builder builder = Target.newBuilder();
        com.google.firebase.firestore.core.Target target = targetData.getTarget();
        if (target.isDocumentQuery()) {
            builder.setDocuments(encodeDocumentsTarget(target));
        } else {
            builder.setQuery(encodeQueryTarget(target));
        }
        builder.setTargetId(targetData.getTargetId());
        if (!targetData.getResumeToken().isEmpty() || targetData.getSnapshotVersion().compareTo(SnapshotVersion.NONE) <= 0) {
            builder.setResumeToken(targetData.getResumeToken());
        } else {
            builder.setReadTime(encodeTimestamp(targetData.getSnapshotVersion().getTimestamp()));
        }
        return (Target) builder.build();
    }

    public Target.DocumentsTarget encodeDocumentsTarget(com.google.firebase.firestore.core.Target target) {
        Target.DocumentsTarget.Builder builder = Target.DocumentsTarget.newBuilder();
        builder.addDocuments(encodeQueryPath(target.getPath()));
        return (Target.DocumentsTarget) builder.build();
    }

    public com.google.firebase.firestore.core.Target decodeDocumentsTarget(Target.DocumentsTarget target) {
        int count = target.getDocumentsCount();
        Assert.hardAssert(count == 1, "DocumentsTarget contained other than 1 document %d", Integer.valueOf(count));
        return Query.atPath(decodeQueryPath(target.getDocuments(0))).toTarget();
    }

    public Target.QueryTarget encodeQueryTarget(com.google.firebase.firestore.core.Target target) {
        Target.QueryTarget.Builder builder = Target.QueryTarget.newBuilder();
        StructuredQuery.Builder structuredQueryBuilder = StructuredQuery.newBuilder();
        ResourcePath path = target.getPath();
        if (target.getCollectionGroup() != null) {
            Assert.hardAssert(path.length() % 2 == 0, "Collection Group queries should be within a document path or root.", new Object[0]);
            builder.setParent(encodeQueryPath(path));
            StructuredQuery.CollectionSelector.Builder from = StructuredQuery.CollectionSelector.newBuilder();
            from.setCollectionId(target.getCollectionGroup());
            from.setAllDescendants(true);
            structuredQueryBuilder.addFrom(from);
        } else {
            Assert.hardAssert(path.length() % 2 != 0, "Document queries with filters are not supported.", new Object[0]);
            builder.setParent(encodeQueryPath((ResourcePath) path.popLast()));
            StructuredQuery.CollectionSelector.Builder from2 = StructuredQuery.CollectionSelector.newBuilder();
            from2.setCollectionId(path.getLastSegment());
            structuredQueryBuilder.addFrom(from2);
        }
        if (target.getFilters().size() > 0) {
            structuredQueryBuilder.setWhere(encodeFilters(target.getFilters()));
        }
        for (OrderBy orderBy : target.getOrderBy()) {
            structuredQueryBuilder.addOrderBy(encodeOrderBy(orderBy));
        }
        if (target.hasLimit()) {
            structuredQueryBuilder.setLimit(Int32Value.newBuilder().setValue((int) target.getLimit()));
        }
        if (target.getStartAt() != null) {
            Cursor.Builder cursor = Cursor.newBuilder();
            cursor.addAllValues(target.getStartAt().getPosition());
            cursor.setBefore(target.getStartAt().isInclusive());
            structuredQueryBuilder.setStartAt(cursor);
        }
        if (target.getEndAt() != null) {
            Cursor.Builder cursor2 = Cursor.newBuilder();
            cursor2.addAllValues(target.getEndAt().getPosition());
            cursor2.setBefore(true ^ target.getEndAt().isInclusive());
            structuredQueryBuilder.setEndAt(cursor2);
        }
        builder.setStructuredQuery(structuredQueryBuilder);
        return (Target.QueryTarget) builder.build();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v2, resolved type: java.util.ArrayList} */
    /* JADX WARNING: type inference failed for: r7v12, types: [com.google.firebase.firestore.model.BasePath] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.firebase.firestore.core.Target decodeQueryTarget(java.lang.String r23, com.google.firestore.p002v1.StructuredQuery r24) {
        /*
            r22 = this;
            r0 = r22
            r1 = r24
            com.google.firebase.firestore.model.ResourcePath r2 = r22.decodeQueryPath(r23)
            r3 = 0
            int r4 = r24.getFromCount()
            r5 = 1
            if (r4 <= 0) goto L_0x0037
            r6 = 0
            if (r4 != r5) goto L_0x0015
            r7 = r5
            goto L_0x0016
        L_0x0015:
            r7 = r6
        L_0x0016:
            java.lang.Object[] r8 = new java.lang.Object[r6]
            java.lang.String r9 = "StructuredQuery.from with more than one collection is not supported."
            com.google.firebase.firestore.util.Assert.hardAssert(r7, r9, r8)
            com.google.firestore.v1.StructuredQuery$CollectionSelector r6 = r1.getFrom(r6)
            boolean r7 = r6.getAllDescendants()
            if (r7 == 0) goto L_0x002c
            java.lang.String r3 = r6.getCollectionId()
            goto L_0x0037
        L_0x002c:
            java.lang.String r7 = r6.getCollectionId()
            com.google.firebase.firestore.model.BasePath r7 = r2.append((java.lang.String) r7)
            r2 = r7
            com.google.firebase.firestore.model.ResourcePath r2 = (com.google.firebase.firestore.model.ResourcePath) r2
        L_0x0037:
            boolean r6 = r24.hasWhere()
            if (r6 == 0) goto L_0x0046
            com.google.firestore.v1.StructuredQuery$Filter r6 = r24.getWhere()
            java.util.List r6 = r0.decodeFilters(r6)
            goto L_0x004a
        L_0x0046:
            java.util.List r6 = java.util.Collections.emptyList()
        L_0x004a:
            int r15 = r24.getOrderByCount()
            if (r15 <= 0) goto L_0x0069
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>(r15)
            r8 = 0
        L_0x0056:
            if (r8 >= r15) goto L_0x0066
            com.google.firestore.v1.StructuredQuery$Order r9 = r1.getOrderBy(r8)
            com.google.firebase.firestore.core.OrderBy r9 = r0.decodeOrderBy(r9)
            r7.add(r9)
            int r8 = r8 + 1
            goto L_0x0056
        L_0x0066:
            r16 = r7
            goto L_0x006f
        L_0x0069:
            java.util.List r7 = java.util.Collections.emptyList()
            r16 = r7
        L_0x006f:
            r7 = -1
            boolean r9 = r24.hasLimit()
            if (r9 == 0) goto L_0x0083
            com.google.protobuf.Int32Value r9 = r24.getLimit()
            int r9 = r9.getValue()
            long r7 = (long) r9
            r17 = r7
            goto L_0x0085
        L_0x0083:
            r17 = r7
        L_0x0085:
            r7 = 0
            boolean r8 = r24.hasStartAt()
            if (r8 == 0) goto L_0x00a5
            com.google.firebase.firestore.core.Bound r8 = new com.google.firebase.firestore.core.Bound
            com.google.firestore.v1.Cursor r9 = r24.getStartAt()
            java.util.List r9 = r9.getValuesList()
            com.google.firestore.v1.Cursor r10 = r24.getStartAt()
            boolean r10 = r10.getBefore()
            r8.<init>(r9, r10)
            r7 = r8
            r19 = r7
            goto L_0x00a7
        L_0x00a5:
            r19 = r7
        L_0x00a7:
            r7 = 0
            boolean r8 = r24.hasEndAt()
            if (r8 == 0) goto L_0x00c7
            com.google.firebase.firestore.core.Bound r8 = new com.google.firebase.firestore.core.Bound
            com.google.firestore.v1.Cursor r9 = r24.getEndAt()
            java.util.List r9 = r9.getValuesList()
            com.google.firestore.v1.Cursor r10 = r24.getEndAt()
            boolean r10 = r10.getBefore()
            r5 = r5 ^ r10
            r8.<init>(r9, r5)
            r7 = r8
            r5 = r7
            goto L_0x00c8
        L_0x00c7:
            r5 = r7
        L_0x00c8:
            com.google.firebase.firestore.core.Target r20 = new com.google.firebase.firestore.core.Target
            r7 = r20
            r8 = r2
            r9 = r3
            r10 = r6
            r11 = r16
            r12 = r17
            r14 = r19
            r21 = r15
            r15 = r5
            r7.<init>(r8, r9, r10, r11, r12, r14, r15)
            return r20
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.remote.RemoteSerializer.decodeQueryTarget(java.lang.String, com.google.firestore.v1.StructuredQuery):com.google.firebase.firestore.core.Target");
    }

    public com.google.firebase.firestore.core.Target decodeQueryTarget(Target.QueryTarget target) {
        return decodeQueryTarget(target.getParent(), target.getStructuredQuery());
    }

    private StructuredQuery.Filter encodeFilters(List<Filter> filters) {
        return encodeFilter(new CompositeFilter(filters, StructuredQuery.CompositeFilter.Operator.AND));
    }

    private List<Filter> decodeFilters(StructuredQuery.Filter proto) {
        Filter result = decodeFilter(proto);
        if (result instanceof CompositeFilter) {
            CompositeFilter compositeFilter = (CompositeFilter) result;
            if (compositeFilter.isFlatConjunction()) {
                return compositeFilter.getFilters();
            }
        }
        return Collections.singletonList(result);
    }

    /* access modifiers changed from: package-private */
    public StructuredQuery.Filter encodeFilter(Filter filter) {
        if (filter instanceof FieldFilter) {
            return encodeUnaryOrFieldFilter((FieldFilter) filter);
        }
        if (filter instanceof CompositeFilter) {
            return encodeCompositeFilter((CompositeFilter) filter);
        }
        throw Assert.fail("Unrecognized filter type %s", filter.toString());
    }

    /* access modifiers changed from: package-private */
    public StructuredQuery.Filter encodeUnaryOrFieldFilter(FieldFilter filter) {
        StructuredQuery.UnaryFilter.Operator operator;
        StructuredQuery.UnaryFilter.Operator operator2;
        if (filter.getOperator() == FieldFilter.Operator.EQUAL || filter.getOperator() == FieldFilter.Operator.NOT_EQUAL) {
            StructuredQuery.UnaryFilter.Builder unaryProto = StructuredQuery.UnaryFilter.newBuilder();
            unaryProto.setField(encodeFieldPath(filter.getField()));
            if (Values.isNanValue(filter.getValue())) {
                if (filter.getOperator() == FieldFilter.Operator.EQUAL) {
                    operator2 = StructuredQuery.UnaryFilter.Operator.IS_NAN;
                } else {
                    operator2 = StructuredQuery.UnaryFilter.Operator.IS_NOT_NAN;
                }
                unaryProto.setOp(operator2);
                return (StructuredQuery.Filter) StructuredQuery.Filter.newBuilder().setUnaryFilter(unaryProto).build();
            } else if (Values.isNullValue(filter.getValue())) {
                if (filter.getOperator() == FieldFilter.Operator.EQUAL) {
                    operator = StructuredQuery.UnaryFilter.Operator.IS_NULL;
                } else {
                    operator = StructuredQuery.UnaryFilter.Operator.IS_NOT_NULL;
                }
                unaryProto.setOp(operator);
                return (StructuredQuery.Filter) StructuredQuery.Filter.newBuilder().setUnaryFilter(unaryProto).build();
            }
        }
        StructuredQuery.FieldFilter.Builder proto = StructuredQuery.FieldFilter.newBuilder();
        proto.setField(encodeFieldPath(filter.getField()));
        proto.setOp(encodeFieldFilterOperator(filter.getOperator()));
        proto.setValue(filter.getValue());
        return (StructuredQuery.Filter) StructuredQuery.Filter.newBuilder().setFieldFilter(proto).build();
    }

    /* access modifiers changed from: package-private */
    public StructuredQuery.Filter encodeCompositeFilter(CompositeFilter compositeFilter) {
        List<StructuredQuery.Filter> protos = new ArrayList<>(compositeFilter.getFilters().size());
        for (Filter filter : compositeFilter.getFilters()) {
            protos.add(encodeFilter(filter));
        }
        if (protos.size() == 1) {
            return protos.get(0);
        }
        StructuredQuery.CompositeFilter.Builder composite = StructuredQuery.CompositeFilter.newBuilder();
        composite.setOp(compositeFilter.getOperator());
        composite.addAllFilters(protos);
        return (StructuredQuery.Filter) StructuredQuery.Filter.newBuilder().setCompositeFilter(composite).build();
    }

    /* access modifiers changed from: package-private */
    public Filter decodeFilter(StructuredQuery.Filter proto) {
        switch (C07911.f185x9d2ee979[proto.getFilterTypeCase().ordinal()]) {
            case 1:
                return decodeCompositeFilter(proto.getCompositeFilter());
            case 2:
                return decodeFieldFilter(proto.getFieldFilter());
            case 3:
                return decodeUnaryFilter(proto.getUnaryFilter());
            default:
                throw Assert.fail("Unrecognized Filter.filterType %d", proto.getFilterTypeCase());
        }
    }

    /* access modifiers changed from: package-private */
    public FieldFilter decodeFieldFilter(StructuredQuery.FieldFilter proto) {
        return FieldFilter.create(FieldPath.fromServerFormat(proto.getField().getFieldPath()), decodeFieldFilterOperator(proto.getOp()), proto.getValue());
    }

    private Filter decodeUnaryFilter(StructuredQuery.UnaryFilter proto) {
        FieldPath fieldPath = FieldPath.fromServerFormat(proto.getField().getFieldPath());
        switch (C07911.f186xf473b456[proto.getOp().ordinal()]) {
            case 1:
                return FieldFilter.create(fieldPath, FieldFilter.Operator.EQUAL, Values.NAN_VALUE);
            case 2:
                return FieldFilter.create(fieldPath, FieldFilter.Operator.EQUAL, Values.NULL_VALUE);
            case 3:
                return FieldFilter.create(fieldPath, FieldFilter.Operator.NOT_EQUAL, Values.NAN_VALUE);
            case 4:
                return FieldFilter.create(fieldPath, FieldFilter.Operator.NOT_EQUAL, Values.NULL_VALUE);
            default:
                throw Assert.fail("Unrecognized UnaryFilter.operator %d", proto.getOp());
        }
    }

    /* access modifiers changed from: package-private */
    public CompositeFilter decodeCompositeFilter(StructuredQuery.CompositeFilter compositeFilter) {
        List<Filter> filters = new ArrayList<>();
        for (StructuredQuery.Filter filter : compositeFilter.getFiltersList()) {
            filters.add(decodeFilter(filter));
        }
        return new CompositeFilter(filters, compositeFilter.getOp());
    }

    private StructuredQuery.FieldReference encodeFieldPath(FieldPath field) {
        return (StructuredQuery.FieldReference) StructuredQuery.FieldReference.newBuilder().setFieldPath(field.canonicalString()).build();
    }

    private StructuredQuery.FieldFilter.Operator encodeFieldFilterOperator(FieldFilter.Operator operator) {
        switch (C07911.f180x833e630d[operator.ordinal()]) {
            case 1:
                return StructuredQuery.FieldFilter.Operator.LESS_THAN;
            case 2:
                return StructuredQuery.FieldFilter.Operator.LESS_THAN_OR_EQUAL;
            case 3:
                return StructuredQuery.FieldFilter.Operator.EQUAL;
            case 4:
                return StructuredQuery.FieldFilter.Operator.NOT_EQUAL;
            case 5:
                return StructuredQuery.FieldFilter.Operator.GREATER_THAN;
            case 6:
                return StructuredQuery.FieldFilter.Operator.GREATER_THAN_OR_EQUAL;
            case 7:
                return StructuredQuery.FieldFilter.Operator.ARRAY_CONTAINS;
            case 8:
                return StructuredQuery.FieldFilter.Operator.IN;
            case 9:
                return StructuredQuery.FieldFilter.Operator.ARRAY_CONTAINS_ANY;
            case 10:
                return StructuredQuery.FieldFilter.Operator.NOT_IN;
            default:
                throw Assert.fail("Unknown operator %d", operator);
        }
    }

    private FieldFilter.Operator decodeFieldFilterOperator(StructuredQuery.FieldFilter.Operator operator) {
        switch (C07911.f184xaf95d2b[operator.ordinal()]) {
            case 1:
                return FieldFilter.Operator.LESS_THAN;
            case 2:
                return FieldFilter.Operator.LESS_THAN_OR_EQUAL;
            case 3:
                return FieldFilter.Operator.EQUAL;
            case 4:
                return FieldFilter.Operator.NOT_EQUAL;
            case 5:
                return FieldFilter.Operator.GREATER_THAN_OR_EQUAL;
            case 6:
                return FieldFilter.Operator.GREATER_THAN;
            case 7:
                return FieldFilter.Operator.ARRAY_CONTAINS;
            case 8:
                return FieldFilter.Operator.IN;
            case 9:
                return FieldFilter.Operator.ARRAY_CONTAINS_ANY;
            case 10:
                return FieldFilter.Operator.NOT_IN;
            default:
                throw Assert.fail("Unhandled FieldFilter.operator %d", operator);
        }
    }

    private StructuredQuery.Order encodeOrderBy(OrderBy orderBy) {
        StructuredQuery.Order.Builder proto = StructuredQuery.Order.newBuilder();
        if (orderBy.getDirection().equals(OrderBy.Direction.ASCENDING)) {
            proto.setDirection(StructuredQuery.Direction.ASCENDING);
        } else {
            proto.setDirection(StructuredQuery.Direction.DESCENDING);
        }
        proto.setField(encodeFieldPath(orderBy.getField()));
        return (StructuredQuery.Order) proto.build();
    }

    private OrderBy decodeOrderBy(StructuredQuery.Order proto) {
        OrderBy.Direction direction;
        FieldPath fieldPath = FieldPath.fromServerFormat(proto.getField().getFieldPath());
        switch (C07911.$SwitchMap$com$google$firestore$v1$StructuredQuery$Direction[proto.getDirection().ordinal()]) {
            case 1:
                direction = OrderBy.Direction.ASCENDING;
                break;
            case 2:
                direction = OrderBy.Direction.DESCENDING;
                break;
            default:
                throw Assert.fail("Unrecognized direction %d", proto.getDirection());
        }
        return OrderBy.getInstance(direction, fieldPath);
    }

    /* renamed from: com.google.firebase.firestore.remote.RemoteSerializer$1 */
    static /* synthetic */ class C07911 {

        /* renamed from: $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator */
        static final /* synthetic */ int[] f180x833e630d;
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$local$QueryPurpose;

        /* renamed from: $SwitchMap$com$google$firestore$v1$DocumentTransform$FieldTransform$TransformTypeCase */
        static final /* synthetic */ int[] f181xdd498c9f;

        /* renamed from: $SwitchMap$com$google$firestore$v1$ListenResponse$ResponseTypeCase */
        static final /* synthetic */ int[] f182x1837d9f;

        /* renamed from: $SwitchMap$com$google$firestore$v1$Precondition$ConditionTypeCase */
        static final /* synthetic */ int[] f183x8f18ca41;
        static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$StructuredQuery$Direction;

        /* renamed from: $SwitchMap$com$google$firestore$v1$StructuredQuery$FieldFilter$Operator */
        static final /* synthetic */ int[] f184xaf95d2b;

        /* renamed from: $SwitchMap$com$google$firestore$v1$StructuredQuery$Filter$FilterTypeCase */
        static final /* synthetic */ int[] f185x9d2ee979;

        /* renamed from: $SwitchMap$com$google$firestore$v1$StructuredQuery$UnaryFilter$Operator */
        static final /* synthetic */ int[] f186xf473b456;
        static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType;
        static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$Write$OperationCase;

        static {
            int[] iArr = new int[ListenResponse.ResponseTypeCase.values().length];
            f182x1837d9f = iArr;
            try {
                iArr[ListenResponse.ResponseTypeCase.TARGET_CHANGE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f182x1837d9f[ListenResponse.ResponseTypeCase.DOCUMENT_CHANGE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f182x1837d9f[ListenResponse.ResponseTypeCase.DOCUMENT_DELETE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f182x1837d9f[ListenResponse.ResponseTypeCase.DOCUMENT_REMOVE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f182x1837d9f[ListenResponse.ResponseTypeCase.FILTER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f182x1837d9f[ListenResponse.ResponseTypeCase.RESPONSETYPE_NOT_SET.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            int[] iArr2 = new int[TargetChange.TargetChangeType.values().length];
            $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType = iArr2;
            try {
                iArr2[TargetChange.TargetChangeType.NO_CHANGE.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType[TargetChange.TargetChangeType.ADD.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType[TargetChange.TargetChangeType.REMOVE.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType[TargetChange.TargetChangeType.CURRENT.ordinal()] = 4;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType[TargetChange.TargetChangeType.RESET.ordinal()] = 5;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType[TargetChange.TargetChangeType.UNRECOGNIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e12) {
            }
            int[] iArr3 = new int[StructuredQuery.Direction.values().length];
            $SwitchMap$com$google$firestore$v1$StructuredQuery$Direction = iArr3;
            try {
                iArr3[StructuredQuery.Direction.ASCENDING.ordinal()] = 1;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$StructuredQuery$Direction[StructuredQuery.Direction.DESCENDING.ordinal()] = 2;
            } catch (NoSuchFieldError e14) {
            }
            int[] iArr4 = new int[StructuredQuery.FieldFilter.Operator.values().length];
            f184xaf95d2b = iArr4;
            try {
                iArr4[StructuredQuery.FieldFilter.Operator.LESS_THAN.ordinal()] = 1;
            } catch (NoSuchFieldError e15) {
            }
            try {
                f184xaf95d2b[StructuredQuery.FieldFilter.Operator.LESS_THAN_OR_EQUAL.ordinal()] = 2;
            } catch (NoSuchFieldError e16) {
            }
            try {
                f184xaf95d2b[StructuredQuery.FieldFilter.Operator.EQUAL.ordinal()] = 3;
            } catch (NoSuchFieldError e17) {
            }
            try {
                f184xaf95d2b[StructuredQuery.FieldFilter.Operator.NOT_EQUAL.ordinal()] = 4;
            } catch (NoSuchFieldError e18) {
            }
            try {
                f184xaf95d2b[StructuredQuery.FieldFilter.Operator.GREATER_THAN_OR_EQUAL.ordinal()] = 5;
            } catch (NoSuchFieldError e19) {
            }
            try {
                f184xaf95d2b[StructuredQuery.FieldFilter.Operator.GREATER_THAN.ordinal()] = 6;
            } catch (NoSuchFieldError e20) {
            }
            try {
                f184xaf95d2b[StructuredQuery.FieldFilter.Operator.ARRAY_CONTAINS.ordinal()] = 7;
            } catch (NoSuchFieldError e21) {
            }
            try {
                f184xaf95d2b[StructuredQuery.FieldFilter.Operator.IN.ordinal()] = 8;
            } catch (NoSuchFieldError e22) {
            }
            try {
                f184xaf95d2b[StructuredQuery.FieldFilter.Operator.ARRAY_CONTAINS_ANY.ordinal()] = 9;
            } catch (NoSuchFieldError e23) {
            }
            try {
                f184xaf95d2b[StructuredQuery.FieldFilter.Operator.NOT_IN.ordinal()] = 10;
            } catch (NoSuchFieldError e24) {
            }
            int[] iArr5 = new int[FieldFilter.Operator.values().length];
            f180x833e630d = iArr5;
            try {
                iArr5[FieldFilter.Operator.LESS_THAN.ordinal()] = 1;
            } catch (NoSuchFieldError e25) {
            }
            try {
                f180x833e630d[FieldFilter.Operator.LESS_THAN_OR_EQUAL.ordinal()] = 2;
            } catch (NoSuchFieldError e26) {
            }
            try {
                f180x833e630d[FieldFilter.Operator.EQUAL.ordinal()] = 3;
            } catch (NoSuchFieldError e27) {
            }
            try {
                f180x833e630d[FieldFilter.Operator.NOT_EQUAL.ordinal()] = 4;
            } catch (NoSuchFieldError e28) {
            }
            try {
                f180x833e630d[FieldFilter.Operator.GREATER_THAN.ordinal()] = 5;
            } catch (NoSuchFieldError e29) {
            }
            try {
                f180x833e630d[FieldFilter.Operator.GREATER_THAN_OR_EQUAL.ordinal()] = 6;
            } catch (NoSuchFieldError e30) {
            }
            try {
                f180x833e630d[FieldFilter.Operator.ARRAY_CONTAINS.ordinal()] = 7;
            } catch (NoSuchFieldError e31) {
            }
            try {
                f180x833e630d[FieldFilter.Operator.IN.ordinal()] = 8;
            } catch (NoSuchFieldError e32) {
            }
            try {
                f180x833e630d[FieldFilter.Operator.ARRAY_CONTAINS_ANY.ordinal()] = 9;
            } catch (NoSuchFieldError e33) {
            }
            try {
                f180x833e630d[FieldFilter.Operator.NOT_IN.ordinal()] = 10;
            } catch (NoSuchFieldError e34) {
            }
            int[] iArr6 = new int[StructuredQuery.UnaryFilter.Operator.values().length];
            f186xf473b456 = iArr6;
            try {
                iArr6[StructuredQuery.UnaryFilter.Operator.IS_NAN.ordinal()] = 1;
            } catch (NoSuchFieldError e35) {
            }
            try {
                f186xf473b456[StructuredQuery.UnaryFilter.Operator.IS_NULL.ordinal()] = 2;
            } catch (NoSuchFieldError e36) {
            }
            try {
                f186xf473b456[StructuredQuery.UnaryFilter.Operator.IS_NOT_NAN.ordinal()] = 3;
            } catch (NoSuchFieldError e37) {
            }
            try {
                f186xf473b456[StructuredQuery.UnaryFilter.Operator.IS_NOT_NULL.ordinal()] = 4;
            } catch (NoSuchFieldError e38) {
            }
            int[] iArr7 = new int[StructuredQuery.Filter.FilterTypeCase.values().length];
            f185x9d2ee979 = iArr7;
            try {
                iArr7[StructuredQuery.Filter.FilterTypeCase.COMPOSITE_FILTER.ordinal()] = 1;
            } catch (NoSuchFieldError e39) {
            }
            try {
                f185x9d2ee979[StructuredQuery.Filter.FilterTypeCase.FIELD_FILTER.ordinal()] = 2;
            } catch (NoSuchFieldError e40) {
            }
            try {
                f185x9d2ee979[StructuredQuery.Filter.FilterTypeCase.UNARY_FILTER.ordinal()] = 3;
            } catch (NoSuchFieldError e41) {
            }
            int[] iArr8 = new int[QueryPurpose.values().length];
            $SwitchMap$com$google$firebase$firestore$local$QueryPurpose = iArr8;
            try {
                iArr8[QueryPurpose.LISTEN.ordinal()] = 1;
            } catch (NoSuchFieldError e42) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$local$QueryPurpose[QueryPurpose.EXISTENCE_FILTER_MISMATCH.ordinal()] = 2;
            } catch (NoSuchFieldError e43) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$local$QueryPurpose[QueryPurpose.LIMBO_RESOLUTION.ordinal()] = 3;
            } catch (NoSuchFieldError e44) {
            }
            int[] iArr9 = new int[DocumentTransform.FieldTransform.TransformTypeCase.values().length];
            f181xdd498c9f = iArr9;
            try {
                iArr9[DocumentTransform.FieldTransform.TransformTypeCase.SET_TO_SERVER_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError e45) {
            }
            try {
                f181xdd498c9f[DocumentTransform.FieldTransform.TransformTypeCase.APPEND_MISSING_ELEMENTS.ordinal()] = 2;
            } catch (NoSuchFieldError e46) {
            }
            try {
                f181xdd498c9f[DocumentTransform.FieldTransform.TransformTypeCase.REMOVE_ALL_FROM_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError e47) {
            }
            try {
                f181xdd498c9f[DocumentTransform.FieldTransform.TransformTypeCase.INCREMENT.ordinal()] = 4;
            } catch (NoSuchFieldError e48) {
            }
            int[] iArr10 = new int[Precondition.ConditionTypeCase.values().length];
            f183x8f18ca41 = iArr10;
            try {
                iArr10[Precondition.ConditionTypeCase.UPDATE_TIME.ordinal()] = 1;
            } catch (NoSuchFieldError e49) {
            }
            try {
                f183x8f18ca41[Precondition.ConditionTypeCase.EXISTS.ordinal()] = 2;
            } catch (NoSuchFieldError e50) {
            }
            try {
                f183x8f18ca41[Precondition.ConditionTypeCase.CONDITIONTYPE_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError e51) {
            }
            int[] iArr11 = new int[Write.OperationCase.values().length];
            $SwitchMap$com$google$firestore$v1$Write$OperationCase = iArr11;
            try {
                iArr11[Write.OperationCase.UPDATE.ordinal()] = 1;
            } catch (NoSuchFieldError e52) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Write$OperationCase[Write.OperationCase.DELETE.ordinal()] = 2;
            } catch (NoSuchFieldError e53) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Write$OperationCase[Write.OperationCase.VERIFY.ordinal()] = 3;
            } catch (NoSuchFieldError e54) {
            }
        }
    }

    public WatchChange decodeWatchChange(ListenResponse protoChange) {
        WatchChange.WatchTargetChangeType changeType;
        switch (C07911.f182x1837d9f[protoChange.getResponseTypeCase().ordinal()]) {
            case 1:
                TargetChange targetChange = protoChange.getTargetChange();
                Status cause = null;
                switch (C07911.$SwitchMap$com$google$firestore$v1$TargetChange$TargetChangeType[targetChange.getTargetChangeType().ordinal()]) {
                    case 1:
                        changeType = WatchChange.WatchTargetChangeType.NoChange;
                        break;
                    case 2:
                        changeType = WatchChange.WatchTargetChangeType.Added;
                        break;
                    case 3:
                        changeType = WatchChange.WatchTargetChangeType.Removed;
                        cause = fromStatus(targetChange.getCause());
                        break;
                    case 4:
                        changeType = WatchChange.WatchTargetChangeType.Current;
                        break;
                    case 5:
                        changeType = WatchChange.WatchTargetChangeType.Reset;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown target change type");
                }
                return new WatchChange.WatchTargetChange(changeType, targetChange.getTargetIdsList(), targetChange.getResumeToken(), cause);
            case 2:
                DocumentChange docChange = protoChange.getDocumentChange();
                List<Integer> added = docChange.getTargetIdsList();
                List<Integer> removed = docChange.getRemovedTargetIdsList();
                DocumentKey key = decodeKey(docChange.getDocument().getName());
                SnapshotVersion version = decodeVersion(docChange.getDocument().getUpdateTime());
                Assert.hardAssert(!version.equals(SnapshotVersion.NONE), "Got a document change without an update time", new Object[0]);
                MutableDocument document = MutableDocument.newFoundDocument(key, version, ObjectValue.fromMap(docChange.getDocument().getFieldsMap()));
                return new WatchChange.DocumentChange(added, removed, document.getKey(), document);
            case 3:
                DocumentDelete docDelete = protoChange.getDocumentDelete();
                List<Integer> removed2 = docDelete.getRemovedTargetIdsList();
                MutableDocument doc = MutableDocument.newNoDocument(decodeKey(docDelete.getDocument()), decodeVersion(docDelete.getReadTime()));
                return new WatchChange.DocumentChange(Collections.emptyList(), removed2, doc.getKey(), doc);
            case 4:
                DocumentRemove docRemove = protoChange.getDocumentRemove();
                return new WatchChange.DocumentChange(Collections.emptyList(), docRemove.getRemovedTargetIdsList(), decodeKey(docRemove.getDocument()), (MutableDocument) null);
            case 5:
                ExistenceFilter protoFilter = protoChange.getFilter();
                return new WatchChange.ExistenceFilterWatchChange(protoFilter.getTargetId(), new ExistenceFilter(protoFilter.getCount()));
            default:
                throw new IllegalArgumentException("Unknown change type set");
        }
    }

    public SnapshotVersion decodeVersionFromListenResponse(ListenResponse watchChange) {
        if (watchChange.getResponseTypeCase() != ListenResponse.ResponseTypeCase.TARGET_CHANGE) {
            return SnapshotVersion.NONE;
        }
        if (watchChange.getTargetChange().getTargetIdsCount() != 0) {
            return SnapshotVersion.NONE;
        }
        return decodeVersion(watchChange.getTargetChange().getReadTime());
    }

    private Status fromStatus(com.google.rpc.Status status) {
        return Status.fromCodeValue(status.getCode()).withDescription(status.getMessage());
    }
}
