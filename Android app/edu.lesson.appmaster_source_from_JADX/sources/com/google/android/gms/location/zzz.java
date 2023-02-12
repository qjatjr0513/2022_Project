package com.google.android.gms.location;

import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
final /* synthetic */ class zzz implements zzan {
    private final TaskCompletionSource zza;

    zzz(TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void zza() {
        TaskCompletionSource taskCompletionSource = this.zza;
        String str = FusedLocationProviderClient.KEY_MOCK_LOCATION;
        taskCompletionSource.trySetResult(null);
    }
}
