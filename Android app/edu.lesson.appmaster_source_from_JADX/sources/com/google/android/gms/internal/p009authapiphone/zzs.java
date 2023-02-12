package com.google.android.gms.internal.p009authapiphone;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api-phone.zzs */
/* compiled from: com.google.android.gms:play-services-auth-api-phone@@17.4.0 */
final class zzs extends zzg {
    private final /* synthetic */ TaskCompletionSource zza;

    zzs(zzn zzn, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void zza(Status status, boolean z) {
        TaskUtil.setResultOrApiException(status, Boolean.valueOf(z), this.zza);
    }
}
