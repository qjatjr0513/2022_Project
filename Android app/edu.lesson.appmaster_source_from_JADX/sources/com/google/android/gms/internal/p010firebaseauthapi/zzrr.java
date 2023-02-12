package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.firebase.auth.internal.zzg;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrr */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzrr extends zzux<Void, zzg> {
    public zzrr() {
        super(2);
    }

    public final TaskApiCall<zztm, Void> zza() {
        return TaskApiCall.builder().run(new zzrq(this)).build();
    }

    public final String zzb() {
        return "reload";
    }

    public final void zzc() {
        ((zzg) this.zzf).zza(this.zzj, zzti.zzR(this.zzd, this.zzk));
        zzm(null);
    }
}
