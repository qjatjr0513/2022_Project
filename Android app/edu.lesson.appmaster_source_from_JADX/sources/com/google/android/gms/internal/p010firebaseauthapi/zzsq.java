package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsq */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final /* synthetic */ class zzsq implements RemoteCall {
    public final /* synthetic */ zzsr zza;

    public /* synthetic */ zzsq(zzsr zzsr) {
        this.zza = zzsr;
    }

    public final void accept(Object obj, Object obj2) {
        zzsr zzsr = this.zza;
        zzsr.zzv = new zzuw(zzsr, (TaskCompletionSource) obj2);
        ((zztm) obj).zzq().zzD(new zznq(zzsr.zze.zzf()), zzsr.zzc);
    }
}
