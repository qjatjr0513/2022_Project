package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqi */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final /* synthetic */ class zzqi implements RemoteCall {
    public final /* synthetic */ zzqj zza;

    public /* synthetic */ zzqi(zzqj zzqj) {
        this.zza = zzqj;
    }

    public final void accept(Object obj, Object obj2) {
        zzqj zzqj = this.zza;
        zzqj.zzv = new zzuw(zzqj, (TaskCompletionSource) obj2);
        ((zztm) obj).zzq().zzh(new zzly(zzqj.zze.zzf()), zzqj.zzc);
    }
}
