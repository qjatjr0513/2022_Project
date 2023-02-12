package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrq */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final /* synthetic */ class zzrq implements RemoteCall {
    public final /* synthetic */ zzrr zza;

    public /* synthetic */ zzrq(zzrr zzrr) {
        this.zza = zzrr;
    }

    public final void accept(Object obj, Object obj2) {
        zzrr zzrr = this.zza;
        zzrr.zzv = new zzuw(zzrr, (TaskCompletionSource) obj2);
        ((zztm) obj).zzq().zzp(new zzmo(zzrr.zze.zzf()), zzrr.zzc);
    }
}
