package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.remote.Datastore;
import com.google.firebase.firestore.remote.RemoteStore;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.ExponentialBackoff;
import com.google.firebase.firestore.util.Function;

public class TransactionRunner<TResult> {
    public static final int DEFAULT_MAX_ATTEMPTS_COUNT = 5;
    private AsyncQueue asyncQueue;
    private int attemptsRemaining;
    private ExponentialBackoff backoff;
    private RemoteStore remoteStore;
    private TaskCompletionSource<TResult> taskSource = new TaskCompletionSource<>();
    private Function<Transaction, Task<TResult>> updateFunction;

    public TransactionRunner(AsyncQueue asyncQueue2, RemoteStore remoteStore2, Function<Transaction, Task<TResult>> updateFunction2) {
        this.asyncQueue = asyncQueue2;
        this.remoteStore = remoteStore2;
        this.updateFunction = updateFunction2;
        this.attemptsRemaining = 5;
        this.backoff = new ExponentialBackoff(asyncQueue2, AsyncQueue.TimerId.RETRY_TRANSACTION);
    }

    public Task<TResult> run() {
        runWithBackoff();
        return this.taskSource.getTask();
    }

    private void runWithBackoff() {
        this.attemptsRemaining--;
        this.backoff.backoffAndRun(new TransactionRunner$$ExternalSyntheticLambda2(this));
    }

    /* renamed from: lambda$runWithBackoff$2$com-google-firebase-firestore-core-TransactionRunner */
    public /* synthetic */ void mo8983xd5705922() {
        Transaction transaction = this.remoteStore.createTransaction();
        this.updateFunction.apply(transaction).addOnCompleteListener(this.asyncQueue.getExecutor(), new TransactionRunner$$ExternalSyntheticLambda1(this, transaction));
    }

    /* renamed from: lambda$runWithBackoff$1$com-google-firebase-firestore-core-TransactionRunner */
    public /* synthetic */ void mo8982xbb54da83(Transaction transaction, Task userTask) {
        if (!userTask.isSuccessful()) {
            handleTransactionError(userTask);
        } else {
            transaction.commit().addOnCompleteListener(this.asyncQueue.getExecutor(), new TransactionRunner$$ExternalSyntheticLambda0(this, userTask));
        }
    }

    /* renamed from: lambda$runWithBackoff$0$com-google-firebase-firestore-core-TransactionRunner */
    public /* synthetic */ void mo8981xa1395be4(Task userTask, Task commitTask) {
        if (commitTask.isSuccessful()) {
            this.taskSource.setResult(userTask.getResult());
        } else {
            handleTransactionError(commitTask);
        }
    }

    private void handleTransactionError(Task task) {
        if (this.attemptsRemaining <= 0 || !isRetryableTransactionError(task.getException())) {
            this.taskSource.setException(task.getException());
        } else {
            runWithBackoff();
        }
    }

    private static boolean isRetryableTransactionError(Exception e) {
        if (!(e instanceof FirebaseFirestoreException)) {
            return false;
        }
        FirebaseFirestoreException.Code code = ((FirebaseFirestoreException) e).getCode();
        if (code == FirebaseFirestoreException.Code.ABORTED || code == FirebaseFirestoreException.Code.FAILED_PRECONDITION || !Datastore.isPermanentError(((FirebaseFirestoreException) e).getCode())) {
            return true;
        }
        return false;
    }
}
