package com.google.firebase.database.core.persistence;

import com.google.firebase.database.core.CompoundWrite;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.UserWriteRecord;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.view.CacheNode;
import com.google.firebase.database.core.view.QuerySpec;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.IndexedNode;
import com.google.firebase.database.snapshot.Node;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public class NoopPersistenceManager implements PersistenceManager {
    private static final String TAG = "NoopPersistenceManager";
    private boolean insideTransaction = false;

    public void saveUserOverwrite(Path path, Node node, long writeId) {
        verifyInsideTransaction();
    }

    public void saveUserMerge(Path path, CompoundWrite children, long writeId) {
        verifyInsideTransaction();
    }

    public void removeUserWrite(long writeId) {
        verifyInsideTransaction();
    }

    public void removeAllUserWrites() {
        verifyInsideTransaction();
    }

    public void applyUserWriteToServerCache(Path path, Node node) {
        verifyInsideTransaction();
    }

    public void applyUserWriteToServerCache(Path path, CompoundWrite merge) {
        verifyInsideTransaction();
    }

    public List<UserWriteRecord> loadUserWrites() {
        return Collections.emptyList();
    }

    public CacheNode serverCache(QuerySpec query) {
        return new CacheNode(IndexedNode.from(EmptyNode.Empty(), query.getIndex()), false, false);
    }

    public void updateServerCache(QuerySpec query, Node node) {
        verifyInsideTransaction();
    }

    public void updateServerCache(Path path, CompoundWrite children) {
        verifyInsideTransaction();
    }

    public void setQueryActive(QuerySpec query) {
        verifyInsideTransaction();
    }

    public void setQueryInactive(QuerySpec query) {
        verifyInsideTransaction();
    }

    public void setQueryComplete(QuerySpec query) {
        verifyInsideTransaction();
    }

    public void setTrackedQueryKeys(QuerySpec query, Set<ChildKey> set) {
        verifyInsideTransaction();
    }

    public void updateTrackedQueryKeys(QuerySpec query, Set<ChildKey> set, Set<ChildKey> set2) {
        verifyInsideTransaction();
    }

    public <T> T runInTransaction(Callable<T> callable) {
        Utilities.hardAssert(!this.insideTransaction, "runInTransaction called when an existing transaction is already in progress.");
        this.insideTransaction = true;
        try {
            T call = callable.call();
            this.insideTransaction = false;
            return call;
        } catch (Throwable e) {
            this.insideTransaction = false;
            throw e;
        }
    }

    private void verifyInsideTransaction() {
        Utilities.hardAssert(this.insideTransaction, "Transaction expected to already be in progress.");
    }
}
