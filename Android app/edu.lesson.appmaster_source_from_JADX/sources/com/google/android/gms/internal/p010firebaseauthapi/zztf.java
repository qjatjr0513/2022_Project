package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzo;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztf */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zztf extends zzux<String, zzg> {
    private final zzls zza;

    public zztf(String str, String str2) {
        super(4);
        Preconditions.checkNotEmpty(str, "code cannot be null or empty");
        this.zza = new zzls(str, str2);
    }

    public final TaskApiCall<zztm, String> zza() {
        return TaskApiCall.builder().run(new zzte(this)).build();
    }

    public final String zzb() {
        return "verifyPasswordResetCode";
    }

    public final void zzc() {
        if (new zzo(this.zzm).getOperation() != 0) {
            zzl(new Status(FirebaseError.ERROR_INTERNAL_ERROR));
        } else {
            zzm(this.zzm.zzc());
        }
    }

    public final /* synthetic */ void zzd(zztm zztm, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzuw(this, taskCompletionSource);
        zztm.zzq().zze(this.zza, this.zzc);
    }
}
