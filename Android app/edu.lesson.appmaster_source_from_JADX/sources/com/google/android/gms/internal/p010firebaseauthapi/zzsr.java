package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzr;
import com.google.firebase.auth.internal.zzx;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsr */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzsr extends zzux<AuthResult, zzg> {
    public zzsr() {
        super(2);
    }

    public final TaskApiCall<zztm, AuthResult> zza() {
        return TaskApiCall.builder().run(new zzsq(this)).build();
    }

    public final String zzb() {
        return "unlinkEmailCredential";
    }

    public final void zzc() {
        zzx zzR = zzti.zzR(this.zzd, this.zzk);
        ((zzg) this.zzf).zza(this.zzj, zzR);
        zzm(new zzr(zzR));
    }
}
