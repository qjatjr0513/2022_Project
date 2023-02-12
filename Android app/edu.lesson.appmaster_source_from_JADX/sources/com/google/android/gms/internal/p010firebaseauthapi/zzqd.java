package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.ActionCodeResult;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzo;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqd */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzqd extends zzux<ActionCodeResult, zzg> {
    private final zzls zza;

    public zzqd(String str, String str2) {
        super(4);
        Preconditions.checkNotEmpty(str, "code cannot be null or empty");
        this.zza = new zzls(str, str2);
    }

    public final TaskApiCall<zztm, ActionCodeResult> zza() {
        return TaskApiCall.builder().run(new zzqc(this)).build();
    }

    public final String zzb() {
        return "checkActionCode";
    }

    public final void zzc() {
        zzm(new zzo(this.zzm));
    }

    public final /* synthetic */ void zzd(zztm zztm, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzuw(this, taskCompletionSource);
        zztm.zzq().zze(this.zza, this.zzc);
    }
}
