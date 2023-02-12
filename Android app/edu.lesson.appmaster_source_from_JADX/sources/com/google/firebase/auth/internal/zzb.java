package com.google.firebase.auth.internal;

import android.app.Activity;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzb implements OnSuccessListener<SafetyNetApi.AttestationResponse> {
    final /* synthetic */ TaskCompletionSource zza;
    final /* synthetic */ FirebaseAuth zzb;
    final /* synthetic */ zzbm zzc;
    final /* synthetic */ Activity zzd;
    final /* synthetic */ zzf zze;

    zzb(zzf zzf, TaskCompletionSource taskCompletionSource, FirebaseAuth firebaseAuth, zzbm zzbm, Activity activity) {
        this.zze = zzf;
        this.zza = taskCompletionSource;
        this.zzb = firebaseAuth;
        this.zzc = zzbm;
        this.zzd = activity;
    }

    public final /* bridge */ /* synthetic */ void onSuccess(Object obj) {
        SafetyNetApi.AttestationResponse attestationResponse = (SafetyNetApi.AttestationResponse) obj;
        if (zzbf.zza(attestationResponse)) {
            this.zza.setResult(new zze(attestationResponse.getJwsResult(), (String) null));
        } else {
            this.zze.zze(this.zzb, this.zzc, this.zzd, this.zza);
        }
    }
}
