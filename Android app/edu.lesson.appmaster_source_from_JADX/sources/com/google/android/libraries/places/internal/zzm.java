package com.google.android.libraries.places.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzm implements Continuation {
    public final /* synthetic */ zzp zza;
    public final /* synthetic */ TaskCompletionSource zzb;

    public /* synthetic */ zzm(zzp zzp, TaskCompletionSource taskCompletionSource) {
        this.zza = zzp;
        this.zzb = taskCompletionSource;
    }

    public final Object then(Task task) {
        TaskCompletionSource taskCompletionSource = this.zzb;
        if (task.isComplete()) {
            if (task.isCanceled()) {
                taskCompletionSource.trySetException(new ApiException(new Status(16, "Location request was cancelled. Please try again.")));
            } else if (!task.isSuccessful()) {
                taskCompletionSource.trySetException(new ApiException(new Status(8, task.getException().getMessage())));
            }
        }
        return task;
    }
}
