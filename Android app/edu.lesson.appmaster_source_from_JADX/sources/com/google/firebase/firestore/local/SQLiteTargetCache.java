package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.util.SparseArray;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.proto.Target;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.Iterator;

final class SQLiteTargetCache implements TargetCache {

    /* renamed from: db */
    private final SQLitePersistence f171db;
    private int highestTargetId;
    private long lastListenSequenceNumber;
    private SnapshotVersion lastRemoteSnapshotVersion = SnapshotVersion.NONE;
    private final LocalSerializer localSerializer;
    private long targetCount;

    SQLiteTargetCache(SQLitePersistence db, LocalSerializer localSerializer2) {
        this.f171db = db;
        this.localSerializer = localSerializer2;
    }

    /* access modifiers changed from: package-private */
    public void start() {
        boolean z = true;
        if (this.f171db.query("SELECT highest_target_id, highest_listen_sequence_number, last_remote_snapshot_version_seconds, last_remote_snapshot_version_nanos, target_count FROM target_globals LIMIT 1").first(new SQLiteTargetCache$$ExternalSyntheticLambda1(this)) != 1) {
            z = false;
        }
        Assert.hardAssert(z, "Missing target_globals entry", new Object[0]);
    }

    /* renamed from: lambda$start$0$com-google-firebase-firestore-local-SQLiteTargetCache */
    public /* synthetic */ void mo9383x4f660810(Cursor row) {
        this.highestTargetId = row.getInt(0);
        this.lastListenSequenceNumber = (long) row.getInt(1);
        this.lastRemoteSnapshotVersion = new SnapshotVersion(new Timestamp(row.getLong(2), row.getInt(3)));
        this.targetCount = row.getLong(4);
    }

    public int getHighestTargetId() {
        return this.highestTargetId;
    }

    public long getHighestListenSequenceNumber() {
        return this.lastListenSequenceNumber;
    }

    public long getTargetCount() {
        return this.targetCount;
    }

    public void forEachTarget(Consumer<TargetData> consumer) {
        this.f171db.query("SELECT target_proto FROM targets").forEach(new SQLiteTargetCache$$ExternalSyntheticLambda4(this, consumer));
    }

    /* renamed from: lambda$forEachTarget$1$com-google-firebase-firestore-local-SQLiteTargetCache */
    public /* synthetic */ void mo9380x1515438a(Consumer consumer, Cursor row) {
        consumer.accept(decodeTargetData(row.getBlob(0)));
    }

    public SnapshotVersion getLastRemoteSnapshotVersion() {
        return this.lastRemoteSnapshotVersion;
    }

    public void setLastRemoteSnapshotVersion(SnapshotVersion snapshotVersion) {
        this.lastRemoteSnapshotVersion = snapshotVersion;
        writeMetadata();
    }

    private void saveTargetData(TargetData targetData) {
        int targetId = targetData.getTargetId();
        String canonicalId = targetData.getTarget().getCanonicalId();
        Timestamp version = targetData.getSnapshotVersion().getTimestamp();
        Target targetProto = this.localSerializer.encodeTargetData(targetData);
        this.f171db.execute("INSERT OR REPLACE INTO targets (target_id, canonical_id, snapshot_version_seconds, snapshot_version_nanos, resume_token, last_listen_sequence_number, target_proto) VALUES (?, ?, ?, ?, ?, ?, ?)", Integer.valueOf(targetId), canonicalId, Long.valueOf(version.getSeconds()), Integer.valueOf(version.getNanoseconds()), targetData.getResumeToken().toByteArray(), Long.valueOf(targetData.getSequenceNumber()), targetProto.toByteArray());
    }

    private boolean updateMetadata(TargetData targetData) {
        boolean wasUpdated = false;
        if (targetData.getTargetId() > this.highestTargetId) {
            this.highestTargetId = targetData.getTargetId();
            wasUpdated = true;
        }
        if (targetData.getSequenceNumber() <= this.lastListenSequenceNumber) {
            return wasUpdated;
        }
        this.lastListenSequenceNumber = targetData.getSequenceNumber();
        return true;
    }

    public void addTargetData(TargetData targetData) {
        saveTargetData(targetData);
        updateMetadata(targetData);
        this.targetCount++;
        writeMetadata();
    }

    public void updateTargetData(TargetData targetData) {
        saveTargetData(targetData);
        if (updateMetadata(targetData)) {
            writeMetadata();
        }
    }

    private void writeMetadata() {
        this.f171db.execute("UPDATE target_globals SET highest_target_id = ?, highest_listen_sequence_number = ?, last_remote_snapshot_version_seconds = ?, last_remote_snapshot_version_nanos = ?, target_count = ?", Integer.valueOf(this.highestTargetId), Long.valueOf(this.lastListenSequenceNumber), Long.valueOf(this.lastRemoteSnapshotVersion.getTimestamp().getSeconds()), Integer.valueOf(this.lastRemoteSnapshotVersion.getTimestamp().getNanoseconds()), Long.valueOf(this.targetCount));
    }

    private void removeTarget(int targetId) {
        removeMatchingKeysForTargetId(targetId);
        this.f171db.execute("DELETE FROM targets WHERE target_id = ?", Integer.valueOf(targetId));
        this.targetCount--;
    }

    public void removeTargetData(TargetData targetData) {
        removeTarget(targetData.getTargetId());
        writeMetadata();
    }

    /* access modifiers changed from: package-private */
    public int removeQueries(long upperBound, SparseArray<?> activeTargetIds) {
        int[] count = new int[1];
        this.f171db.query("SELECT target_id FROM targets WHERE last_listen_sequence_number <= ?").binding(Long.valueOf(upperBound)).forEach(new SQLiteTargetCache$$ExternalSyntheticLambda2(this, activeTargetIds, count));
        writeMetadata();
        return count[0];
    }

    /* renamed from: lambda$removeQueries$2$com-google-firebase-firestore-local-SQLiteTargetCache */
    public /* synthetic */ void mo9382x1041d572(SparseArray activeTargetIds, int[] count, Cursor row) {
        int targetId = row.getInt(0);
        if (activeTargetIds.get(targetId) == null) {
            removeTarget(targetId);
            count[0] = count[0] + 1;
        }
    }

    public TargetData getTargetData(com.google.firebase.firestore.core.Target target) {
        String canonicalId = target.getCanonicalId();
        TargetDataHolder result = new TargetDataHolder();
        this.f171db.query("SELECT target_proto FROM targets WHERE canonical_id = ?").binding(canonicalId).forEach(new SQLiteTargetCache$$ExternalSyntheticLambda3(this, target, result));
        return result.targetData;
    }

    /* renamed from: lambda$getTargetData$3$com-google-firebase-firestore-local-SQLiteTargetCache */
    public /* synthetic */ void mo9381x4f05f442(com.google.firebase.firestore.core.Target target, TargetDataHolder result, Cursor row) {
        TargetData found = decodeTargetData(row.getBlob(0));
        if (target.equals(found.getTarget())) {
            result.targetData = found;
        }
    }

    private static class TargetDataHolder {
        TargetData targetData;

        private TargetDataHolder() {
        }
    }

    private TargetData decodeTargetData(byte[] bytes) {
        try {
            return this.localSerializer.decodeTargetData(Target.parseFrom(bytes));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("TargetData failed to parse: %s", e);
        }
    }

    public void addMatchingKeys(ImmutableSortedSet<DocumentKey> keys, int targetId) {
        SQLiteStatement inserter = this.f171db.prepare("INSERT OR IGNORE INTO target_documents (target_id, path) VALUES (?, ?)");
        ReferenceDelegate delegate = this.f171db.getReferenceDelegate();
        Iterator<DocumentKey> it = keys.iterator();
        while (it.hasNext()) {
            DocumentKey key = it.next();
            String path = EncodedPath.encode(key.getPath());
            this.f171db.execute(inserter, Integer.valueOf(targetId), path);
            delegate.addReference(key);
        }
    }

    public void removeMatchingKeys(ImmutableSortedSet<DocumentKey> keys, int targetId) {
        SQLiteStatement deleter = this.f171db.prepare("DELETE FROM target_documents WHERE target_id = ? AND path = ?");
        ReferenceDelegate delegate = this.f171db.getReferenceDelegate();
        Iterator<DocumentKey> it = keys.iterator();
        while (it.hasNext()) {
            DocumentKey key = it.next();
            String path = EncodedPath.encode(key.getPath());
            this.f171db.execute(deleter, Integer.valueOf(targetId), path);
            delegate.removeReference(key);
        }
    }

    public void removeMatchingKeysForTargetId(int targetId) {
        this.f171db.execute("DELETE FROM target_documents WHERE target_id = ?", Integer.valueOf(targetId));
    }

    public ImmutableSortedSet<DocumentKey> getMatchingKeysForTargetId(int targetId) {
        DocumentKeysHolder holder = new DocumentKeysHolder();
        this.f171db.query("SELECT path FROM target_documents WHERE target_id = ?").binding(Integer.valueOf(targetId)).forEach(new SQLiteTargetCache$$ExternalSyntheticLambda0(holder));
        return holder.keys;
    }

    private static class DocumentKeysHolder {
        /* access modifiers changed from: package-private */
        public ImmutableSortedSet<DocumentKey> keys;

        private DocumentKeysHolder() {
            this.keys = DocumentKey.emptyKeySet();
        }
    }

    public boolean containsKey(DocumentKey key) {
        String path = EncodedPath.encode(key.getPath());
        return !this.f171db.query("SELECT target_id FROM target_documents WHERE path = ? AND target_id != 0 LIMIT 1").binding(path).isEmpty();
    }
}
