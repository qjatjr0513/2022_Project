package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzr;
import com.google.firebase.auth.internal.zzx;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqz */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzqz extends zzux<AuthResult, zzg> {
    private final EmailAuthCredential zza;

    public zzqz(EmailAuthCredential emailAuthCredential) {
        super(2);
        this.zza = (EmailAuthCredential) Preconditions.checkNotNull(emailAuthCredential, "credential cannot be null");
    }

    public final TaskApiCall<zztm, AuthResult> zza() {
        return TaskApiCall.builder().run(new zzqy(this)).build();
    }

    public final String zzb() {
        return "linkEmailAuthCredential";
    }

    public final void zzc() {
        zzx zzR = zzti.zzR(this.zzd, this.zzk);
        ((zzg) this.zzf).zza(this.zzj, zzR);
        zzm(new zzr(zzR));
    }

    public final /* synthetic */ void zzd(zztm zztm, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzuw(this, taskCompletionSource);
        EmailAuthCredential emailAuthCredential = this.zza;
        emailAuthCredential.zzb(this.zze);
        zztm.zzq().zzy(new zzng(emailAuthCredential), this.zzc);
    }
}
