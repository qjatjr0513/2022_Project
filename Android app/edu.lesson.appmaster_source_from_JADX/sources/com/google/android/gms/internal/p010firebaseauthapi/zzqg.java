package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqg */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final /* synthetic */ class zzqg implements RemoteCall {
    public final /* synthetic */ zzqh zza;

    public /* synthetic */ zzqg(zzqh zzqh) {
        this.zza = zzqh;
    }

    public final void accept(Object obj, Object obj2) {
        zzqh zzqh = this.zza;
        zzqh.zzv = new zzuw(zzqh, (TaskCompletionSource) obj2);
        ((zztm) obj).zzq().zzg(zzqh.zza, zzqh.zzc);
    }
}
