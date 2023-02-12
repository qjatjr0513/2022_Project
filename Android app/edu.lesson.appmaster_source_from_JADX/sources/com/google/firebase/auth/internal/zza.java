package com.google.firebase.auth.internal;

import android.app.Activity;
import android.util.Log;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zza implements OnFailureListener {
    final /* synthetic */ FirebaseAuth zza;
    final /* synthetic */ zzbm zzb;
    final /* synthetic */ Activity zzc;
    final /* synthetic */ TaskCompletionSource zzd;
    final /* synthetic */ zzf zze;

    zza(zzf zzf, FirebaseAuth firebaseAuth, zzbm zzbm, Activity activity, TaskCompletionSource taskCompletionSource) {
        this.zze = zzf;
        this.zza = firebaseAuth;
        this.zzb = zzbm;
        this.zzc = activity;
        this.zzd = taskCompletionSource;
    }

    public final void onFailure(Exception exc) {
        String zzc2 = zzf.zza;
        String valueOf = String.valueOf(exc.getMessage());
        Log.e(zzc2, valueOf.length() != 0 ? "Problem retrieving SafetyNet Token: ".concat(valueOf) : new String("Problem retrieving SafetyNet Token: "));
        this.zze.zze(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
