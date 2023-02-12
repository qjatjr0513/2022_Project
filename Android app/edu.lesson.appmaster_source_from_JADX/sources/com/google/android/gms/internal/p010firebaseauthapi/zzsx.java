package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzg;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsx */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzsx extends zzux<Void, zzg> {
    private final String zza;

    public zzsx(String str) {
        super(2);
        this.zza = Preconditions.checkNotEmpty(str, "password cannot be null or empty");
    }

    public final TaskApiCall<zztm, Void> zza() {
        return TaskApiCall.builder().run(new zzsw(this)).build();
    }

    public final String zzb() {
        return "updatePassword";
    }

    public final void zzc() {
        ((zzg) this.zzf).zza(this.zzj, zzti.zzR(this.zzd, this.zzk));
        zzm(null);
    }

    public final /* synthetic */ void zzd(zztm zztm, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzuw(this, taskCompletionSource);
        zztm.zzq().zzd(new zzlq(this.zze.zzf(), this.zza), this.zzc);
    }
}
