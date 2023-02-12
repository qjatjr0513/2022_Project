package com.google.firebase.auth;

import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p010firebaseauthapi.zzti;
import com.google.android.gms.internal.p010firebaseauthapi.zztk;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.internal.zzag;
import com.google.firebase.auth.internal.zze;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzp implements OnCompleteListener<zze> {
    final /* synthetic */ PhoneAuthOptions zza;
    final /* synthetic */ FirebaseAuth zzb;

    zzp(FirebaseAuth firebaseAuth, PhoneAuthOptions phoneAuthOptions) {
        this.zzb = firebaseAuth;
        this.zza = phoneAuthOptions;
    }

    public final void onComplete(Task<zze> task) {
        String str;
        String str2;
        boolean z;
        boolean z2;
        if (!task.isSuccessful()) {
            String str3 = "Error while validating application identity: ";
            if (task.getException() != null) {
                String valueOf = String.valueOf(task.getException().getMessage());
                if (valueOf.length() != 0) {
                    str3 = str3.concat(valueOf);
                } else {
                    str3 = new String(str3);
                }
            }
            Log.e("FirebaseAuth", str3);
            Log.e("FirebaseAuth", "Proceeding without any application identifier.");
            str2 = null;
            str = null;
        } else {
            String zzb2 = task.getResult().zzb();
            str = task.getResult().zza();
            str2 = zzb2;
        }
        long longValue = this.zza.zzg().longValue();
        PhoneAuthProvider.OnVerificationStateChangedCallbacks zzt = this.zzb.zzJ(this.zza.zzh(), this.zza.zze());
        zzag zzag = (zzag) Preconditions.checkNotNull(this.zza.zzc());
        if (zzag.zze()) {
            zzti zzu = this.zzb.zze;
            String str4 = (String) Preconditions.checkNotNull(this.zza.zzh());
            String zzy = this.zzb.zzi;
            if (this.zza.zzd() != null) {
                z2 = true;
            } else {
                z2 = false;
            }
            zzu.zzH(zzag, str4, zzy, longValue, z2, this.zza.zzj(), str2, str, zztk.zzb(), zzt, this.zza.zzi(), this.zza.zza());
            return;
        }
        zzti zzu2 = this.zzb.zze;
        PhoneMultiFactorInfo phoneMultiFactorInfo = (PhoneMultiFactorInfo) Preconditions.checkNotNull(this.zza.zzf());
        String zzy2 = this.zzb.zzi;
        if (this.zza.zzd() != null) {
            z = true;
        } else {
            z = false;
        }
        zzu2.zzI(zzag, phoneMultiFactorInfo, zzy2, longValue, z, this.zza.zzj(), str2, str, zztk.zzb(), zzt, this.zza.zzi(), this.zza.zza());
    }
}
