package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.internal.zzay;
import com.google.firebase.auth.internal.zzg;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqr */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzqr extends zzux<GetTokenResult, zzg> {
    private final zzme zza;

    public zzqr(String str) {
        super(1);
        Preconditions.checkNotEmpty(str, "refresh token cannot be null");
        this.zza = new zzme(str);
    }

    public final TaskApiCall<zztm, GetTokenResult> zza() {
        return TaskApiCall.builder().run(new zzqq(this)).build();
    }

    public final String zzb() {
        return "getAccessToken";
    }

    public final void zzc() {
        if (TextUtils.isEmpty(this.zzj.zzf())) {
            this.zzj.zzi(this.zza.zza());
        }
        ((zzg) this.zzf).zza(this.zzj, this.zze);
        zzm(zzay.zza(this.zzj.zze()));
    }

    public final /* synthetic */ void zzd(zztm zztm, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzuw(this, taskCompletionSource);
        zztm.zzq().zzk(this.zza, this.zzc);
    }
}
