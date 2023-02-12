package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.text.TextUtils;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.Bound;
import com.google.firebase.firestore.core.CompositeFilter;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.index.FirestoreIndexValueWriter;
import com.google.firebase.firestore.index.IndexByteEncoder;
import com.google.firebase.firestore.index.IndexEntry;
import com.google.firebase.firestore.local.MemoryIndexManager;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.TargetIndexMatcher;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.LogicUtils;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.admin.p001v1.Index;
import com.google.firestore.p002v1.StructuredQuery;
import com.google.firestore.p002v1.Value;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;

final class SQLiteIndexManager implements IndexManager {
    private static final byte[] EMPTY_BYTES_VALUE = new byte[0];
    private static final String TAG = SQLiteIndexManager.class.getSimpleName();
    private final MemoryIndexManager.MemoryCollectionParentIndex collectionParentsCache = new MemoryIndexManager.MemoryCollectionParentIndex();

    /* renamed from: db */
    private final SQLitePersistence f163db;
    private final Map<String, Map<Integer, FieldIndex>> memoizedIndexes = new HashMap();
    private int memoizedMaxIndexId = -1;
    private long memoizedMaxSequenceNumber = -1;
    private final Queue<FieldIndex> nextIndexToUpdate = new PriorityQueue(10, SQLiteIndexManager$$ExternalSyntheticLambda8.INSTANCE);
    private final LocalSerializer serializer;
    private boolean started = false;
    private final Map<Target, List<Target>> targetToDnfSubTargets = new HashMap();
    private final String uid;

    static /* synthetic */ int lambda$new$0(FieldIndex l, FieldIndex r) {
        int sequenceCmp = Long.compare(l.getIndexState().getSequenceNumber(), r.getIndexState().getSequenceNumber());
        if (sequenceCmp == 0) {
            return l.getCollectionGroup().compareTo(r.getCollectionGroup());
        }
        return sequenceCmp;
    }

    SQLiteIndexManager(SQLitePersistence persistence, LocalSerializer serializer2, User user) {
        this.f163db = persistence;
        this.serializer = serializer2;
        this.uid = user.isAuthenticated() ? user.getUid() : "";
    }

    public void start() {
        Map<Integer, FieldIndex.IndexState> indexStates = new HashMap<>();
        this.f163db.query("SELECT index_id, sequence_number, read_time_seconds, read_time_nanos, document_key, largest_batch_id FROM index_state WHERE uid = ?").binding(this.uid).forEach(new SQLiteIndexManager$$ExternalSyntheticLambda5(indexStates));
        this.f163db.query("SELECT index_id, collection_group, index_proto FROM index_configuration").forEach(new SQLiteIndexManager$$ExternalSyntheticLambda2(this, indexStates));
        this.started = true;
    }

    /* renamed from: lambda$start$2$com-google-firebase-firestore-local-SQLiteIndexManager */
    public /* synthetic */ void mo9307x30c0807a(Map indexStates, Cursor row) {
        FieldIndex.IndexState indexState;
        try {
            int indexId = row.getInt(0);
            String collectionGroup = row.getString(1);
            List<FieldIndex.Segment> segments = this.serializer.decodeFieldIndexSegments(Index.parseFrom(row.getBlob(2)));
            if (indexStates.containsKey(Integer.valueOf(indexId))) {
                indexState = (FieldIndex.IndexState) indexStates.get(Integer.valueOf(indexId));
            } else {
                indexState = FieldIndex.INITIAL_STATE;
            }
            memoizeIndex(FieldIndex.create(indexId, collectionGroup, segments, indexState));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("Failed to decode index: " + e, new Object[0]);
        }
    }

    public void addToCollectionParentIndex(ResourcePath collectionPath) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        Assert.hardAssert(collectionPath.length() % 2 == 1, "Expected a collection path.", new Object[0]);
        if (this.collectionParentsCache.add(collectionPath)) {
            this.f163db.execute("INSERT OR REPLACE INTO collection_parents (collection_id, parent) VALUES (?, ?)", collectionPath.getLastSegment(), EncodedPath.encode((ResourcePath) collectionPath.popLast()));
        }
    }

    public List<ResourcePath> getCollectionParents(String collectionId) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        ArrayList<ResourcePath> parentPaths = new ArrayList<>();
        this.f163db.query("SELECT parent FROM collection_parents WHERE collection_id = ?").binding(collectionId).forEach(new SQLiteIndexManager$$ExternalSyntheticLambda3(parentPaths));
        return parentPaths;
    }

    public void addFieldIndex(FieldIndex index) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        int nextIndexId = this.memoizedMaxIndexId + 1;
        FieldIndex index2 = FieldIndex.create(nextIndexId, index.getCollectionGroup(), index.getSegments(), index.getIndexState());
        this.f163db.execute("INSERT INTO index_configuration (index_id, collection_group, index_proto) VALUES(?, ?, ?)", Integer.valueOf(nextIndexId), index2.getCollectionGroup(), encodeSegments(index2));
        memoizeIndex(index2);
    }

    public void deleteFieldIndex(FieldIndex index) {
        this.f163db.execute("DELETE FROM index_configuration WHERE index_id = ?", Integer.valueOf(index.getIndexId()));
        this.f163db.execute("DELETE FROM index_entries WHERE index_id = ?", Integer.valueOf(index.getIndexId()));
        this.f163db.execute("DELETE FROM index_state WHERE index_id = ?", Integer.valueOf(index.getIndexId()));
        this.nextIndexToUpdate.remove(index);
        Map<Integer, FieldIndex> collectionIndices = this.memoizedIndexes.get(index.getCollectionGroup());
        if (collectionIndices != null) {
            collectionIndices.remove(Integer.valueOf(index.getIndexId()));
        }
    }

    public String getNextCollectionGroupToUpdate() {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        FieldIndex nextIndex = this.nextIndexToUpdate.peek();
        if (nextIndex != null) {
            return nextIndex.getCollectionGroup();
        }
        return null;
    }

    public void updateIndexEntries(ImmutableSortedMap<DocumentKey, Document> documents) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        Iterator<Map.Entry<DocumentKey, Document>> it = documents.iterator();
        while (it.hasNext()) {
            Map.Entry<DocumentKey, Document> entry = it.next();
            for (FieldIndex fieldIndex : getFieldIndexes(entry.getKey().getCollectionGroup())) {
                SortedSet<IndexEntry> existingEntries = getExistingIndexEntries(entry.getKey(), fieldIndex);
                SortedSet<IndexEntry> newEntries = computeIndexEntries(entry.getValue(), fieldIndex);
                if (!existingEntries.equals(newEntries)) {
                    updateEntries(entry.getValue(), existingEntries, newEntries);
                }
            }
        }
    }

    private void updateEntries(Document document, SortedSet<IndexEntry> existingEntries, SortedSet<IndexEntry> newEntries) {
        Logger.debug(TAG, "Updating index entries for document '%s'", document.getKey());
        Util.diffCollections(existingEntries, newEntries, new SQLiteIndexManager$$ExternalSyntheticLambda0(this, document), new SQLiteIndexManager$$ExternalSyntheticLambda1(this, document));
    }

    public Collection<FieldIndex> getFieldIndexes(String collectionGroup) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        Map<Integer, FieldIndex> indexes = this.memoizedIndexes.get(collectionGroup);
        return indexes == null ? Collections.emptyList() : indexes.values();
    }

    public Collection<FieldIndex> getFieldIndexes() {
        List<FieldIndex> allIndices = new ArrayList<>();
        for (Map<Integer, FieldIndex> indices : this.memoizedIndexes.values()) {
            allIndices.addAll(indices.values());
        }
        return allIndices;
    }

    private FieldIndex.IndexOffset getMinOffset(Collection<FieldIndex> fieldIndexes) {
        Assert.hardAssert(!fieldIndexes.isEmpty(), "Found empty index group when looking for least recent index offset.", new Object[0]);
        Iterator<FieldIndex> it = fieldIndexes.iterator();
        FieldIndex.IndexOffset minOffset = it.next().getIndexState().getOffset();
        int minBatchId = minOffset.getLargestBatchId();
        while (it.hasNext()) {
            FieldIndex.IndexOffset newOffset = it.next().getIndexState().getOffset();
            if (newOffset.compareTo(minOffset) < 0) {
                minOffset = newOffset;
            }
            minBatchId = Math.max(newOffset.getLargestBatchId(), minBatchId);
        }
        return FieldIndex.IndexOffset.create(minOffset.getReadTime(), minOffset.getDocumentKey(), minBatchId);
    }

    public FieldIndex.IndexOffset getMinOffset(String collectionGroup) {
        Collection<FieldIndex> fieldIndexes = getFieldIndexes(collectionGroup);
        Assert.hardAssert(!fieldIndexes.isEmpty(), "minOffset was called for collection without indexes", new Object[0]);
        return getMinOffset(fieldIndexes);
    }

    public FieldIndex.IndexOffset getMinOffset(Target target) {
        List<FieldIndex> fieldIndexes = new ArrayList<>();
        for (Target subTarget : getSubTargets(target)) {
            fieldIndexes.add(getFieldIndex(subTarget));
        }
        return getMinOffset((Collection<FieldIndex>) fieldIndexes);
    }

    private List<Target> getSubTargets(Target target) {
        if (this.targetToDnfSubTargets.containsKey(target)) {
            return this.targetToDnfSubTargets.get(target);
        }
        List<Target> subTargets = new ArrayList<>();
        if (target.getFilters().isEmpty()) {
            subTargets.add(target);
        } else {
            for (Filter term : LogicUtils.DnfTransform(new CompositeFilter(target.getFilters(), StructuredQuery.CompositeFilter.Operator.AND))) {
                subTargets.add(new Target(target.getPath(), target.getCollectionGroup(), term.getFilters(), target.getOrderBy(), target.getLimit(), target.getStartAt(), target.getEndAt()));
            }
        }
        this.targetToDnfSubTargets.put(target, subTargets);
        return subTargets;
    }

    private void memoizeIndex(FieldIndex fieldIndex) {
        Map<Integer, FieldIndex> existingIndexes = this.memoizedIndexes.get(fieldIndex.getCollectionGroup());
        if (existingIndexes == null) {
            existingIndexes = new HashMap<>();
            this.memoizedIndexes.put(fieldIndex.getCollectionGroup(), existingIndexes);
        }
        FieldIndex existingIndex = existingIndexes.get(Integer.valueOf(fieldIndex.getIndexId()));
        if (existingIndex != null) {
            this.nextIndexToUpdate.remove(existingIndex);
        }
        existingIndexes.put(Integer.valueOf(fieldIndex.getIndexId()), fieldIndex);
        this.nextIndexToUpdate.add(fieldIndex);
        this.memoizedMaxIndexId = Math.max(this.memoizedMaxIndexId, fieldIndex.getIndexId());
        this.memoizedMaxSequenceNumber = Math.max(this.memoizedMaxSequenceNumber, fieldIndex.getIndexState().getSequenceNumber());
    }

    private SortedSet<IndexEntry> computeIndexEntries(Document document, FieldIndex fieldIndex) {
        SortedSet<IndexEntry> result = new TreeSet<>();
        byte[] directionalValue = encodeDirectionalElements(fieldIndex, document);
        if (directionalValue == null) {
            return result;
        }
        FieldIndex.Segment arraySegment = fieldIndex.getArraySegment();
        if (arraySegment != null) {
            Value value = document.getField(arraySegment.getFieldPath());
            if (Values.isArray(value)) {
                for (Value arrayValue : value.getArrayValue().getValuesList()) {
                    result.add(IndexEntry.create(fieldIndex.getIndexId(), document.getKey(), encodeSingleElement(arrayValue), directionalValue));
                }
            }
        } else {
            result.add(IndexEntry.create(fieldIndex.getIndexId(), document.getKey(), new byte[0], directionalValue));
        }
        return result;
    }

    /* access modifiers changed from: private */
    /* renamed from: addIndexEntry */
    public void mo9308x55deb5b3(Document document, IndexEntry indexEntry) {
        this.f163db.execute("INSERT INTO index_entries (index_id, uid, array_value, directional_value, document_key) VALUES(?, ?, ?, ?, ?)", Integer.valueOf(indexEntry.getIndexId()), this.uid, indexEntry.getArrayValue(), indexEntry.getDirectionalValue(), document.getKey().toString());
    }

    /* access modifiers changed from: private */
    /* renamed from: deleteIndexEntry */
    public void mo9309x57150892(Document document, IndexEntry indexEntry) {
        this.f163db.execute("DELETE FROM index_entries WHERE index_id = ? AND uid = ? AND array_value = ? AND directional_value = ? AND document_key = ?", Integer.valueOf(indexEntry.getIndexId()), this.uid, indexEntry.getArrayValue(), indexEntry.getDirectionalValue(), document.getKey().toString());
    }

    private SortedSet<IndexEntry> getExistingIndexEntries(DocumentKey documentKey, FieldIndex fieldIndex) {
        SortedSet<IndexEntry> results = new TreeSet<>();
        this.f163db.query("SELECT array_value, directional_value FROM index_entries WHERE index_id = ? AND document_key = ? AND uid = ?").binding(Integer.valueOf(fieldIndex.getIndexId()), documentKey.toString(), this.uid).forEach(new SQLiteIndexManager$$ExternalSyntheticLambda6(results, fieldIndex, documentKey));
        return results;
    }

    public List<DocumentKey> getDocumentsMatchingTarget(Target target) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        List<String> subQueries = new ArrayList<>();
        List<Object> bindings = new ArrayList<>();
        for (Target subTarget : getSubTargets(target)) {
            FieldIndex fieldIndex = getFieldIndex(subTarget);
            if (fieldIndex == null) {
                return null;
            }
            List<Value> arrayValues = subTarget.getArrayValues(fieldIndex);
            Collection<Value> notInValues = subTarget.getNotInValues(fieldIndex);
            Bound lowerBound = subTarget.getLowerBound(fieldIndex);
            Bound upperBound = subTarget.getUpperBound(fieldIndex);
            if (Logger.isDebugEnabled()) {
                Logger.debug(TAG, "Using index '%s' to execute '%s' (Arrays: %s, Lower bound: %s, Upper bound: %s)", fieldIndex, subTarget, arrayValues, lowerBound, upperBound);
            }
            Bound bound = upperBound;
            Bound bound2 = lowerBound;
            Collection<Value> collection = notInValues;
            FieldIndex fieldIndex2 = fieldIndex;
            Object[] subQueryAndBindings = generateQueryAndBindings(subTarget, fieldIndex.getIndexId(), arrayValues, encodeBound(fieldIndex, subTarget, lowerBound), (lowerBound == null || !lowerBound.isInclusive()) ? ">" : ">=", encodeBound(fieldIndex, subTarget, upperBound), (upperBound == null || !upperBound.isInclusive()) ? "<" : "<=", encodeValues(fieldIndex, subTarget, notInValues));
            subQueries.add(String.valueOf(subQueryAndBindings[0]));
            bindings.addAll(Arrays.asList(subQueryAndBindings).subList(1, subQueryAndBindings.length));
        }
        String queryString = "SELECT DISTINCT document_key FROM (" + TextUtils.join(" UNION ", subQueries) + ")";
        if (target.getLimit() != -1) {
            queryString = queryString + " LIMIT " + target.getLimit();
        }
        Assert.hardAssert(bindings.size() < 1000, "Cannot perform query with more than 999 bind elements", new Object[0]);
        SQLitePersistence.Query query = this.f163db.query(queryString).binding(bindings.toArray());
        List<DocumentKey> result = new ArrayList<>();
        query.forEach(new SQLiteIndexManager$$ExternalSyntheticLambda4(result));
        Logger.debug(TAG, "Index scan returned %s documents", Integer.valueOf(result.size()));
        return result;
    }

    private Object[] generateQueryAndBindings(Target target, int indexId, List<Value> arrayValues, Object[] lowerBounds, String lowerBoundOp, Object[] upperBounds, String upperBoundOp, Object[] notIn) {
        StringBuilder sql;
        Object[] objArr = lowerBounds;
        Object[] objArr2 = upperBounds;
        Object[] objArr3 = notIn;
        int i = 1;
        int size = arrayValues != null ? arrayValues.size() : 1;
        int length = objArr != null ? objArr.length : 1;
        if (objArr2 != null) {
            i = objArr2.length;
        }
        int statementCount = size * Math.max(length, i);
        StringBuilder statement = new StringBuilder();
        statement.append("SELECT document_key, directional_value FROM index_entries ");
        statement.append("WHERE index_id = ? AND uid = ? ");
        statement.append("AND array_value = ? ");
        if (objArr != null) {
            statement.append("AND directional_value ").append(lowerBoundOp).append(" ? ");
        } else {
            String str = lowerBoundOp;
        }
        if (objArr2 != null) {
            statement.append("AND directional_value ").append(upperBoundOp).append(" ? ");
        } else {
            String str2 = upperBoundOp;
        }
        StringBuilder sql2 = Util.repeatSequence(statement, statementCount, " UNION ");
        sql2.append("ORDER BY directional_value, document_key ");
        sql2.append(target.getKeyOrder().equals(OrderBy.Direction.ASCENDING) ? "asc " : "desc ");
        if (target.getLimit() != -1) {
            sql2.append("LIMIT ").append(target.getLimit()).append(" ");
        }
        if (objArr3 != null) {
            StringBuilder sql3 = new StringBuilder("SELECT document_key, directional_value FROM (").append(sql2);
            sql3.append(") WHERE directional_value NOT IN (");
            sql3.append(Util.repeatSequence("?", objArr3.length, ", "));
            sql3.append(")");
            sql = sql3;
        } else {
            sql = sql2;
        }
        Object[] bindArgs = fillBounds(statementCount, indexId, arrayValues, lowerBounds, upperBounds, notIn);
        List<Object> result = new ArrayList<>();
        result.add(sql.toString());
        result.addAll(Arrays.asList(bindArgs));
        return result.toArray();
    }

    private Object[] fillBounds(int statementCount, int indexId, List<Value> arrayValues, Object[] lowerBounds, Object[] upperBounds, Object[] notInValues) {
        byte[] bArr;
        int i = 0;
        int i2 = 1;
        int bindsPerStatement = (lowerBounds != null ? 1 : 0) + 3 + (upperBounds != null ? 1 : 0);
        if (arrayValues != null) {
            i2 = arrayValues.size();
        }
        int statementsPerArrayValue = statementCount / i2;
        Object[] bindArgs = new Object[((statementCount * bindsPerStatement) + (notInValues != null ? notInValues.length : 0))];
        int offset = 0;
        for (int i3 = 0; i3 < statementCount; i3++) {
            int offset2 = offset + 1;
            bindArgs[offset] = Integer.valueOf(indexId);
            int offset3 = offset2 + 1;
            bindArgs[offset2] = this.uid;
            int offset4 = offset3 + 1;
            if (arrayValues != null) {
                bArr = encodeSingleElement(arrayValues.get(i3 / statementsPerArrayValue));
            } else {
                bArr = EMPTY_BYTES_VALUE;
            }
            bindArgs[offset3] = bArr;
            if (lowerBounds != null) {
                bindArgs[offset4] = lowerBounds[i3 % statementsPerArrayValue];
                offset4++;
            }
            if (upperBounds != null) {
                offset = offset4 + 1;
                bindArgs[offset4] = upperBounds[i3 % statementsPerArrayValue];
            } else {
                offset = offset4;
            }
        }
        if (notInValues != null) {
            int length = notInValues.length;
            while (i < length) {
                bindArgs[offset] = notInValues[i];
                i++;
                offset++;
            }
        }
        return bindArgs;
    }

    public FieldIndex getFieldIndex(Target target) {
        String collectionGroup;
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        TargetIndexMatcher targetIndexMatcher = new TargetIndexMatcher(target);
        if (target.getCollectionGroup() != null) {
            collectionGroup = target.getCollectionGroup();
        } else {
            collectionGroup = target.getPath().getLastSegment();
        }
        Collection<FieldIndex> collectionIndexes = getFieldIndexes(collectionGroup);
        if (collectionIndexes.isEmpty()) {
            return null;
        }
        List<FieldIndex> matchingIndexes = new ArrayList<>();
        for (FieldIndex fieldIndex : collectionIndexes) {
            if (targetIndexMatcher.servedByIndex(fieldIndex)) {
                matchingIndexes.add(fieldIndex);
            }
        }
        if (matchingIndexes.isEmpty()) {
            return null;
        }
        return (FieldIndex) Collections.max(matchingIndexes, SQLiteIndexManager$$ExternalSyntheticLambda7.INSTANCE);
    }

    private byte[] encodeDirectionalElements(FieldIndex fieldIndex, Document document) {
        IndexByteEncoder encoder = new IndexByteEncoder();
        for (FieldIndex.Segment segment : fieldIndex.getDirectionalSegments()) {
            Value field = document.getField(segment.getFieldPath());
            if (field == null) {
                return null;
            }
            FirestoreIndexValueWriter.INSTANCE.writeIndexValue(field, encoder.forKind(segment.getKind()));
        }
        return encoder.getEncodedBytes();
    }

    private byte[] encodeSingleElement(Value value) {
        IndexByteEncoder encoder = new IndexByteEncoder();
        FirestoreIndexValueWriter.INSTANCE.writeIndexValue(value, encoder.forKind(FieldIndex.Segment.Kind.ASCENDING));
        return encoder.getEncodedBytes();
    }

    private Object[] encodeValues(FieldIndex fieldIndex, Target target, Collection<Value> values) {
        if (values == null) {
            return null;
        }
        List<IndexByteEncoder> encoders = new ArrayList<>();
        encoders.add(new IndexByteEncoder());
        Iterator<Value> position = values.iterator();
        for (FieldIndex.Segment segment : fieldIndex.getDirectionalSegments()) {
            Value value = position.next();
            for (IndexByteEncoder encoder : encoders) {
                if (!isInFilter(target, segment.getFieldPath()) || !Values.isArray(value)) {
                    FirestoreIndexValueWriter.INSTANCE.writeIndexValue(value, encoder.forKind(segment.getKind()));
                } else {
                    encoders = expandIndexValues(encoders, segment, value);
                }
            }
        }
        return getEncodedBytes(encoders);
    }

    private Object[] encodeBound(FieldIndex fieldIndex, Target target, Bound bound) {
        if (bound == null) {
            return null;
        }
        return encodeValues(fieldIndex, target, bound.getPosition());
    }

    private Object[] getEncodedBytes(List<IndexByteEncoder> encoders) {
        Object[] result = new Object[encoders.size()];
        for (int i = 0; i < encoders.size(); i++) {
            result[i] = encoders.get(i).getEncodedBytes();
        }
        return result;
    }

    private List<IndexByteEncoder> expandIndexValues(List<IndexByteEncoder> encoders, FieldIndex.Segment segment, Value value) {
        List<IndexByteEncoder> prefixes = new ArrayList<>(encoders);
        List<IndexByteEncoder> results = new ArrayList<>();
        for (Value arrayElement : value.getArrayValue().getValuesList()) {
            for (IndexByteEncoder prefix : prefixes) {
                IndexByteEncoder clonedEncoder = new IndexByteEncoder();
                clonedEncoder.seed(prefix.getEncodedBytes());
                FirestoreIndexValueWriter.INSTANCE.writeIndexValue(arrayElement, clonedEncoder.forKind(segment.getKind()));
                results.add(clonedEncoder);
            }
        }
        return results;
    }

    private boolean isInFilter(Target target, FieldPath fieldPath) {
        for (Filter filter : target.getFilters()) {
            if ((filter instanceof FieldFilter) && ((FieldFilter) filter).getField().equals(fieldPath)) {
                FieldFilter.Operator operator = ((FieldFilter) filter).getOperator();
                if (operator.equals(FieldFilter.Operator.IN) || operator.equals(FieldFilter.Operator.NOT_IN)) {
                    return true;
                }
            }
        }
        return false;
    }

    private byte[] encodeSegments(FieldIndex fieldIndex) {
        return this.serializer.encodeFieldIndexSegments(fieldIndex.getSegments()).toByteArray();
    }

    public void updateCollectionGroup(String collectionGroup, FieldIndex.IndexOffset offset) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        this.memoizedMaxSequenceNumber++;
        for (FieldIndex fieldIndex : getFieldIndexes(collectionGroup)) {
            FieldIndex updatedIndex = FieldIndex.create(fieldIndex.getIndexId(), fieldIndex.getCollectionGroup(), fieldIndex.getSegments(), FieldIndex.IndexState.create(this.memoizedMaxSequenceNumber, offset));
            this.f163db.execute("REPLACE INTO index_state (index_id, uid,  sequence_number, read_time_seconds, read_time_nanos, document_key, largest_batch_id) VALUES(?, ?, ?, ?, ?, ?, ?)", Integer.valueOf(fieldIndex.getIndexId()), this.uid, Long.valueOf(this.memoizedMaxSequenceNumber), Long.valueOf(offset.getReadTime().getTimestamp().getSeconds()), Integer.valueOf(offset.getReadTime().getTimestamp().getNanoseconds()), EncodedPath.encode(offset.getDocumentKey().getPath()), Integer.valueOf(offset.getLargestBatchId()));
            memoizeIndex(updatedIndex);
        }
    }
}
