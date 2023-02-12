package com.google.firebase.firestore;

import android.app.Activity;
import com.google.android.gms.common.api.internal.ActivityLifecycleObserver;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.firestore.LoadBundleTaskProgress;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

public class LoadBundleTask extends Task<LoadBundleTaskProgress> {
    private final TaskCompletionSource<LoadBundleTaskProgress> completionSource;
    private final Task<LoadBundleTaskProgress> delegate;
    private final Object lock = new Object();
    private final Queue<ManagedListener> progressListeners;
    private LoadBundleTaskProgress snapshot = LoadBundleTaskProgress.INITIAL;

    public LoadBundleTask() {
        TaskCompletionSource<LoadBundleTaskProgress> taskCompletionSource = new TaskCompletionSource<>();
        this.completionSource = taskCompletionSource;
        this.delegate = taskCompletionSource.getTask();
        this.progressListeners = new ArrayDeque();
    }

    public boolean isComplete() {
        return this.delegate.isComplete();
    }

    public boolean isSuccessful() {
        return this.delegate.isSuccessful();
    }

    public boolean isCanceled() {
        return this.delegate.isCanceled();
    }

    public LoadBundleTaskProgress getResult() {
        return this.delegate.getResult();
    }

    public <X extends Throwable> LoadBundleTaskProgress getResult(Class<X> exceptionType) throws Throwable {
        return this.delegate.getResult(exceptionType);
    }

    public Exception getException() {
        return this.delegate.getException();
    }

    public Task<LoadBundleTaskProgress> addOnSuccessListener(OnSuccessListener<? super LoadBundleTaskProgress> onSuccessListener) {
        return this.delegate.addOnSuccessListener(onSuccessListener);
    }

    public Task<LoadBundleTaskProgress> addOnSuccessListener(Executor executor, OnSuccessListener<? super LoadBundleTaskProgress> onSuccessListener) {
        return this.delegate.addOnSuccessListener(executor, onSuccessListener);
    }

    public Task<LoadBundleTaskProgress> addOnSuccessListener(Activity activity, OnSuccessListener<? super LoadBundleTaskProgress> onSuccessListener) {
        return this.delegate.addOnSuccessListener(activity, onSuccessListener);
    }

    public Task<LoadBundleTaskProgress> addOnFailureListener(OnFailureListener onFailureListener) {
        return this.delegate.addOnFailureListener(onFailureListener);
    }

    public Task<LoadBundleTaskProgress> addOnFailureListener(Executor executor, OnFailureListener onFailureListener) {
        return this.delegate.addOnFailureListener(executor, onFailureListener);
    }

    public Task<LoadBundleTaskProgress> addOnFailureListener(Activity activity, OnFailureListener onFailureListener) {
        return this.delegate.addOnFailureListener(activity, onFailureListener);
    }

    public Task<LoadBundleTaskProgress> addOnCompleteListener(OnCompleteListener<LoadBundleTaskProgress> onCompleteListener) {
        return this.delegate.addOnCompleteListener(onCompleteListener);
    }

    public Task<LoadBundleTaskProgress> addOnCompleteListener(Executor executor, OnCompleteListener<LoadBundleTaskProgress> onCompleteListener) {
        return this.delegate.addOnCompleteListener(executor, onCompleteListener);
    }

    public Task<LoadBundleTaskProgress> addOnCompleteListener(Activity activity, OnCompleteListener<LoadBundleTaskProgress> onCompleteListener) {
        return this.delegate.addOnCompleteListener(activity, onCompleteListener);
    }

    public Task<LoadBundleTaskProgress> addOnCanceledListener(OnCanceledListener onCanceledListener) {
        return this.delegate.addOnCanceledListener(onCanceledListener);
    }

    public Task<LoadBundleTaskProgress> addOnCanceledListener(Executor executor, OnCanceledListener onCanceledListener) {
        return this.delegate.addOnCanceledListener(executor, onCanceledListener);
    }

    public Task<LoadBundleTaskProgress> addOnCanceledListener(Activity activity, OnCanceledListener onCanceledListener) {
        return this.delegate.addOnCanceledListener(activity, onCanceledListener);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<LoadBundleTaskProgress, TContinuationResult> continuation) {
        return this.delegate.continueWith(continuation);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Executor executor, Continuation<LoadBundleTaskProgress, TContinuationResult> continuation) {
        return this.delegate.continueWith(executor, continuation);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<LoadBundleTaskProgress, Task<TContinuationResult>> continuation) {
        return this.delegate.continueWithTask(continuation);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Executor executor, Continuation<LoadBundleTaskProgress, Task<TContinuationResult>> continuation) {
        return this.delegate.continueWithTask(executor, continuation);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(SuccessContinuation<LoadBundleTaskProgress, TContinuationResult> successContinuation) {
        return this.delegate.onSuccessTask(successContinuation);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(Executor executor, SuccessContinuation<LoadBundleTaskProgress, TContinuationResult> successContinuation) {
        return this.delegate.onSuccessTask(executor, successContinuation);
    }

    public LoadBundleTask addOnProgressListener(OnProgressListener<LoadBundleTaskProgress> listener) {
        ManagedListener managedListener = new ManagedListener((Executor) null, listener);
        synchronized (this.lock) {
            this.progressListeners.add(managedListener);
        }
        return this;
    }

    public LoadBundleTask addOnProgressListener(Executor executor, OnProgressListener<LoadBundleTaskProgress> listener) {
        ManagedListener managedListener = new ManagedListener(executor, listener);
        synchronized (this.lock) {
            this.progressListeners.add(managedListener);
        }
        return this;
    }

    public LoadBundleTask addOnProgressListener(Activity activity, OnProgressListener<LoadBundleTaskProgress> listener) {
        ManagedListener managedListener = new ManagedListener((Executor) null, listener);
        synchronized (this.lock) {
            this.progressListeners.add(managedListener);
        }
        ActivityLifecycleObserver.m417of(activity).onStopCallOnce(new LoadBundleTask$$ExternalSyntheticLambda0(this, listener));
        return this;
    }

    /* access modifiers changed from: private */
    /* renamed from: removeOnProgressListener */
    public void mo8579x9c983ee1(OnProgressListener<LoadBundleTaskProgress> listener) {
        synchronized (this.lock) {
            this.progressListeners.remove(new ManagedListener((Executor) null, listener));
        }
    }

    public void setResult(LoadBundleTaskProgress result) {
        Assert.hardAssert(result.getTaskState().equals(LoadBundleTaskProgress.TaskState.SUCCESS), "Expected success, but was " + result.getTaskState(), new Object[0]);
        synchronized (this.lock) {
            this.snapshot = result;
            for (ManagedListener listener : this.progressListeners) {
                listener.invokeAsync(this.snapshot);
            }
            this.progressListeners.clear();
        }
        this.completionSource.setResult(result);
    }

    public void setException(Exception exception) {
        synchronized (this.lock) {
            LoadBundleTaskProgress snapshot2 = new LoadBundleTaskProgress(this.snapshot.getDocumentsLoaded(), this.snapshot.getTotalDocuments(), this.snapshot.getBytesLoaded(), this.snapshot.getTotalBytes(), exception, LoadBundleTaskProgress.TaskState.ERROR);
            this.snapshot = snapshot2;
            for (ManagedListener listener : this.progressListeners) {
                listener.invokeAsync(snapshot2);
            }
            this.progressListeners.clear();
        }
        this.completionSource.setException(exception);
    }

    public void updateProgress(LoadBundleTaskProgress progressUpdate) {
        synchronized (this.lock) {
            this.snapshot = progressUpdate;
            for (ManagedListener listener : this.progressListeners) {
                listener.invokeAsync(progressUpdate);
            }
        }
    }

    private static class ManagedListener {
        Executor executor;
        OnProgressListener<LoadBundleTaskProgress> listener;

        ManagedListener(Executor executor2, OnProgressListener<LoadBundleTaskProgress> listener2) {
            this.executor = executor2 != null ? executor2 : TaskExecutors.MAIN_THREAD;
            this.listener = listener2;
        }

        public void invokeAsync(LoadBundleTaskProgress snapshot) {
            this.executor.execute(new LoadBundleTask$ManagedListener$$ExternalSyntheticLambda0(this, snapshot));
        }

        /* renamed from: lambda$invokeAsync$0$com-google-firebase-firestore-LoadBundleTask$ManagedListener */
        public /* synthetic */ void mo8588xdd93410d(LoadBundleTaskProgress snapshot) {
            this.listener.onProgress(snapshot);
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            return this.listener.equals(((ManagedListener) o).listener);
        }

        public int hashCode() {
            return this.listener.hashCode();
        }
    }
}
