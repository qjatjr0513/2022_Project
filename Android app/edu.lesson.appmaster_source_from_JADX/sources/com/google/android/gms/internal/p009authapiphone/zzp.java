package com.google.android.gms.internal.p009authapiphone;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api-phone.zzp */
/* compiled from: com.google.android.gms:play-services-auth-api-phone@@17.4.0 */
final /* synthetic */ class zzp implements RemoteCall {
    private final zzn zza;

    zzp(zzn zzn) {
        this.zza = zzn;
    }

    public final void accept(Object obj, Object obj2) {
        ((zzj) ((zzv) obj).getService()).zza((zzf) new zzt(this.zza, (TaskCompletionSource) obj2));
    }
}
