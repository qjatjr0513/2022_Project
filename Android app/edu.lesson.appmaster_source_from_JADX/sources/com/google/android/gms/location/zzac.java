package com.google.android.gms.location;

import android.location.Location;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
final /* synthetic */ class zzac implements Continuation {
    private final TaskCompletionSource zza;

    zzac(TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final Object then(Task task) {
        TaskCompletionSource taskCompletionSource = this.zza;
        String str = FusedLocationProviderClient.KEY_MOCK_LOCATION;
        if (task.isSuccessful()) {
            taskCompletionSource.trySetResult((Location) task.getResult());
        } else {
            Exception exception = task.getException();
            if (exception != null) {
                taskCompletionSource.setException(exception);
            }
        }
        return taskCompletionSource.getTask();
    }
}
