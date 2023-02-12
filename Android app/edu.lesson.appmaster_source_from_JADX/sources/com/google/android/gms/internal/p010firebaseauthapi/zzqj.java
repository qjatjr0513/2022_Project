package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.firebase.auth.internal.zzan;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqj */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzqj extends zzux<Void, zzan> {
    public zzqj() {
        super(5);
    }

    public final TaskApiCall<zztm, Void> zza() {
        return TaskApiCall.builder().run(new zzqi(this)).build();
    }

    public final String zzb() {
        return "delete";
    }

    public final void zzc() {
        ((zzan) this.zzf).zza();
        zzm(null);
    }
}
