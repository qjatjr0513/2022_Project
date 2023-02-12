package com.google.android.gms.internal.p009authapiphone;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api-phone.zzt */
/* compiled from: com.google.android.gms:play-services-auth-api-phone@@17.4.0 */
final class zzt extends zze {
    private final /* synthetic */ TaskCompletionSource zza;

    zzt(zzn zzn, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void zza(Status status, int i) {
        TaskUtil.setResultOrApiException(status, Integer.valueOf(i), this.zza);
    }
}
