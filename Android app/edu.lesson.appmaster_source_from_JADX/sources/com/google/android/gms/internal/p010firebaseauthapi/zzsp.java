package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzg;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsp */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzsp extends zzux<Void, zzg> {
    private final zzno zza;

    public zzsp(String str, String str2) {
        super(2);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(str2);
        this.zza = new zzno(str, str2);
    }

    public final TaskApiCall<zztm, Void> zza() {
        return TaskApiCall.builder().run(new zzso(this)).build();
    }

    public final String zzb() {
        return "unenrollMfa";
    }

    public final void zzc() {
        ((zzg) this.zzf).zza(this.zzj, zzti.zzR(this.zzd, this.zzk));
        zzm(null);
    }

    public final /* synthetic */ void zzd(zztm zztm, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzuw(this, taskCompletionSource);
        zztm.zzq().zzC(this.zza, this.zzc);
    }
}
