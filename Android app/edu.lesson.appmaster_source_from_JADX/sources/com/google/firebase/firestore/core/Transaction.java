package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.core.UserData;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.DeleteMutation;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.model.mutation.VerifyMutation;
import com.google.firebase.firestore.remote.Datastore;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Executors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Transaction {
    private static final Executor defaultExecutor = createDefaultExecutor();
    private boolean committed;
    private final Datastore datastore;
    private FirebaseFirestoreException lastWriteError;
    private final ArrayList<Mutation> mutations = new ArrayList<>();
    private final HashMap<DocumentKey, SnapshotVersion> readVersions = new HashMap<>();
    private Set<DocumentKey> writtenDocs = new HashSet();

    public Transaction(Datastore d) {
        this.datastore = d;
    }

    public Task<List<MutableDocument>> lookup(List<DocumentKey> keys) {
        ensureCommitNotCalled();
        if (this.mutations.size() != 0) {
            return Tasks.forException(new FirebaseFirestoreException("Firestore transactions require all reads to be executed before all writes.", FirebaseFirestoreException.Code.INVALID_ARGUMENT));
        }
        return this.datastore.lookup(keys).continueWithTask(Executors.DIRECT_EXECUTOR, new Transaction$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$lookup$0$com-google-firebase-firestore-core-Transaction  reason: not valid java name */
    public /* synthetic */ Task m449lambda$lookup$0$comgooglefirebasefirestorecoreTransaction(Task task) throws Exception {
        if (task.isSuccessful()) {
            for (MutableDocument doc : (List) task.getResult()) {
                recordVersion(doc);
            }
        }
        return task;
    }

    public void set(DocumentKey key, UserData.ParsedSetData data) {
        write(Collections.singletonList(data.toMutation(key, precondition(key))));
        this.writtenDocs.add(key);
    }

    public void update(DocumentKey key, UserData.ParsedUpdateData data) {
        try {
            write(Collections.singletonList(data.toMutation(key, preconditionForUpdate(key))));
        } catch (FirebaseFirestoreException e) {
            this.lastWriteError = e;
        }
        this.writtenDocs.add(key);
    }

    public void delete(DocumentKey key) {
        write(Collections.singletonList(new DeleteMutation(key, precondition(key))));
        this.writtenDocs.add(key);
    }

    public Task<Void> commit() {
        ensureCommitNotCalled();
        FirebaseFirestoreException firebaseFirestoreException = this.lastWriteError;
        if (firebaseFirestoreException != null) {
            return Tasks.forException(firebaseFirestoreException);
        }
        HashSet<DocumentKey> unwritten = new HashSet<>(this.readVersions.keySet());
        Iterator<Mutation> it = this.mutations.iterator();
        while (it.hasNext()) {
            unwritten.remove(it.next().getKey());
        }
        Iterator<DocumentKey> it2 = unwritten.iterator();
        while (it2.hasNext()) {
            DocumentKey key = it2.next();
            this.mutations.add(new VerifyMutation(key, precondition(key)));
        }
        this.committed = true;
        return this.datastore.commit(this.mutations).continueWithTask(Executors.DIRECT_EXECUTOR, Transaction$$ExternalSyntheticLambda1.INSTANCE);
    }

    static /* synthetic */ Task lambda$commit$1(Task task) throws Exception {
        if (task.isSuccessful()) {
            return Tasks.forResult(null);
        }
        return Tasks.forException(task.getException());
    }

    private static Executor createDefaultExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, (long) 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        executor.allowCoreThreadTimeOut(true);
        return executor;
    }

    private void recordVersion(MutableDocument doc) throws FirebaseFirestoreException {
        SnapshotVersion docVersion;
        if (doc.isFoundDocument()) {
            docVersion = doc.getVersion();
        } else if (doc.isNoDocument()) {
            docVersion = SnapshotVersion.NONE;
        } else {
            throw Assert.fail("Unexpected document type in transaction: " + doc, new Object[0]);
        }
        if (!this.readVersions.containsKey(doc.getKey())) {
            this.readVersions.put(doc.getKey(), docVersion);
        } else if (!this.readVersions.get(doc.getKey()).equals(doc.getVersion())) {
            throw new FirebaseFirestoreException("Document version changed between two reads.", FirebaseFirestoreException.Code.ABORTED);
        }
    }

    private Precondition precondition(DocumentKey key) {
        SnapshotVersion version = this.readVersions.get(key);
        if (this.writtenDocs.contains(key) || version == null) {
            return Precondition.NONE;
        }
        return Precondition.updateTime(version);
    }

    private Precondition preconditionForUpdate(DocumentKey key) throws FirebaseFirestoreException {
        SnapshotVersion version = this.readVersions.get(key);
        if (this.writtenDocs.contains(key) || version == null) {
            return Precondition.exists(true);
        }
        if (version == null || !version.equals(SnapshotVersion.NONE)) {
            return Precondition.updateTime(version);
        }
        throw new FirebaseFirestoreException("Can't update a document that doesn't exist.", FirebaseFirestoreException.Code.INVALID_ARGUMENT);
    }

    private void write(List<Mutation> mutations2) {
        ensureCommitNotCalled();
        this.mutations.addAll(mutations2);
    }

    private void ensureCommitNotCalled() {
        Assert.hardAssert(!this.committed, "A transaction object cannot be used after its update callback has been invoked.", new Object[0]);
    }

    public static Executor getDefaultExecutor() {
        return defaultExecutor;
    }
}
