package com.google.android.libraries.places.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzcp implements Runnable {
    public final /* synthetic */ TaskCompletionSource zza;

    public /* synthetic */ zzcp(TaskCompletionSource taskCompletionSource, String str) {
        this.zza = taskCompletionSource;
    }

    public final void run() {
        this.zza.trySetException(new ApiException(new Status(15, "Location timeout.")));
    }
}
