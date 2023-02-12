package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.PhoneAuthProvider;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzth */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzth extends zzux<Void, PhoneAuthProvider.OnVerificationStateChangedCallbacks> {
    private final zzmu zza;

    public zzth(zzxd zzxd) {
        super(8);
        Preconditions.checkNotNull(zzxd);
        this.zza = new zzmu(zzxd);
    }

    public final TaskApiCall<zztm, Void> zza() {
        return TaskApiCall.builder().run(new zztg(this)).build();
    }

    public final String zzb() {
        return "verifyPhoneNumber";
    }

    public final void zzc() {
    }

    public final /* synthetic */ void zzd(zztm zztm, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzuw(this, taskCompletionSource);
        zztm.zzq().zzs(this.zza, this.zzc);
    }
}
