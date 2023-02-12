package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.internal.zzg;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsz */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzsz extends zzux<Void, zzg> {
    private final PhoneAuthCredential zza;

    public zzsz(PhoneAuthCredential phoneAuthCredential) {
        super(2);
        this.zza = (PhoneAuthCredential) Preconditions.checkNotNull(phoneAuthCredential);
    }

    public final TaskApiCall<zztm, Void> zza() {
        return TaskApiCall.builder().run(new zzsy(this)).build();
    }

    public final String zzb() {
        return "updatePhoneNumber";
    }

    public final void zzc() {
        ((zzg) this.zzf).zza(this.zzj, zzti.zzR(this.zzd, this.zzk));
        zzm(null);
    }

    public final /* synthetic */ void zzd(zztm zztm, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzuw(this, taskCompletionSource);
        zztm.zzq().zzo(new zzmm(this.zze.zzf(), this.zza), this.zzc);
    }
}
