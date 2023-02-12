package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzg;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrx */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzrx extends zzux<Void, zzg> {
    private final zzmw zza;

    public zzrx(String str) {
        super(9);
        this.zza = new zzmw(str);
    }

    public final TaskApiCall<zztm, Void> zza() {
        return TaskApiCall.builder().run(new zzrw(this)).build();
    }

    public final String zzb() {
        return "setFirebaseUIVersion";
    }

    public final void zzc() {
        zzm(null);
    }

    public final /* synthetic */ void zzd(zztm zztm, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzuw(this, taskCompletionSource);
        zztm.zzq().zzt(this.zza, this.zzc);
    }
}
