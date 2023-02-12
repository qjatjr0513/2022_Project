package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzh;
import com.google.firebase.auth.internal.zzx;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrb */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzrb extends zzux<Void, zzg> {
    private final zzna zza;

    public zzrb(AuthCredential authCredential, String str) {
        super(2);
        Preconditions.checkNotNull(authCredential, "credential cannot be null");
        zzxq zza2 = zzh.zza(authCredential, str);
        zza2.zzb(false);
        this.zza = new zzna(zza2);
    }

    public final TaskApiCall<zztm, Void> zza() {
        return TaskApiCall.builder().run(new zzra(this)).build();
    }

    public final String zzb() {
        return "reauthenticateWithCredential";
    }

    public final void zzc() {
        zzx zzR = zzti.zzR(this.zzd, this.zzk);
        if (this.zze.getUid().equalsIgnoreCase(zzR.getUid())) {
            ((zzg) this.zzf).zza(this.zzj, zzR);
            zzm(null);
            return;
        }
        zzl(new Status(FirebaseError.ERROR_USER_MISMATCH));
    }

    public final /* synthetic */ void zzd(zztm zztm, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzuw(this, taskCompletionSource);
        zztm.zzq().zzv(this.zza, this.zzc);
    }
}
