package com.google.android.libraries.places.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzcn implements Continuation {
    public final /* synthetic */ zzcq zza;
    public final /* synthetic */ TaskCompletionSource zzb;

    public /* synthetic */ zzcn(zzcq zzcq, TaskCompletionSource taskCompletionSource) {
        this.zza = zzcq;
        this.zzb = taskCompletionSource;
    }

    public final Object then(Task task) {
        TaskCompletionSource taskCompletionSource = this.zzb;
        if (task.isSuccessful()) {
            taskCompletionSource.setResult(task.getResult());
        } else if (!task.isCanceled() && task.getException() != null) {
            taskCompletionSource.setException(task.getException());
        }
        return taskCompletionSource.getTask();
    }
}
