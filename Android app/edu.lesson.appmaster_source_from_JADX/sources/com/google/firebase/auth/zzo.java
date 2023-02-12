package com.google.firebase.auth;

import android.app.Activity;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.internal.zze;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzo implements OnCompleteListener<zze> {
    final /* synthetic */ String zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ TimeUnit zzc;
    final /* synthetic */ PhoneAuthProvider.OnVerificationStateChangedCallbacks zzd;
    final /* synthetic */ Activity zze;
    final /* synthetic */ Executor zzf;
    final /* synthetic */ boolean zzg;
    final /* synthetic */ FirebaseAuth zzh;

    zzo(FirebaseAuth firebaseAuth, String str, long j, TimeUnit timeUnit, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor, boolean z) {
        this.zzh = firebaseAuth;
        this.zza = str;
        this.zzb = j;
        this.zzc = timeUnit;
        this.zzd = onVerificationStateChangedCallbacks;
        this.zze = activity;
        this.zzf = executor;
        this.zzg = z;
    }

    public final void onComplete(Task<zze> task) {
        String str;
        String str2;
        if (!task.isSuccessful()) {
            String valueOf = String.valueOf(task.getException() != null ? task.getException().getMessage() : "");
            Log.e("FirebaseAuth", valueOf.length() != 0 ? "Error while validating application identity: ".concat(valueOf) : new String("Error while validating application identity: "));
            Log.e("FirebaseAuth", "Proceeding without any application identifier.");
            str2 = null;
            str = null;
        } else {
            String zzb2 = task.getResult().zzb();
            str2 = task.getResult().zza();
            str = zzb2;
        }
        this.zzh.zzI(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, str2, str);
    }
}
