package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzr;
import com.google.firebase.auth.internal.zzx;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrz */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzrz extends zzux<AuthResult, zzg> {
    private final zzmy zza;

    public zzrz(String str) {
        super(2);
        this.zza = new zzmy(str);
    }

    public final TaskApiCall<zztm, AuthResult> zza() {
        return TaskApiCall.builder().run(new zzry(this)).build();
    }

    public final String zzb() {
        return "signInAnonymously";
    }

    public final void zzc() {
        zzx zzR = zzti.zzR(this.zzd, this.zzk);
        ((zzg) this.zzf).zza(this.zzj, zzR);
        zzm(new zzr(zzR));
    }

    public final /* synthetic */ void zzd(zztm zztm, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzuw(this, taskCompletionSource);
        zztm.zzq().zzu(this.zza, this.zzc);
    }
}
