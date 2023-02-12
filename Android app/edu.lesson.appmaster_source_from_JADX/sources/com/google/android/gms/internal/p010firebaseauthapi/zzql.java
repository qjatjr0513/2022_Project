package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.internal.zzaj;
import com.google.firebase.auth.internal.zzg;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzql */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzql extends zzux<SignInMethodQueryResult, zzg> {
    private final zzmg zza;

    public zzql(String str, String str2) {
        super(3);
        Preconditions.checkNotEmpty(str, "email cannot be null or empty");
        this.zza = new zzmg(str, str2);
    }

    public final TaskApiCall<zztm, SignInMethodQueryResult> zza() {
        return TaskApiCall.builder().run(new zzqk(this)).build();
    }

    public final String zzb() {
        return "fetchSignInMethodsForEmail";
    }

    public final void zzc() {
        zzm(new zzaj(this.zzl.zzb()));
    }

    public final /* synthetic */ void zzd(zztm zztm, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzuw(this, taskCompletionSource);
        zztm.zzq().zzl(this.zza, this.zzc);
    }
}
