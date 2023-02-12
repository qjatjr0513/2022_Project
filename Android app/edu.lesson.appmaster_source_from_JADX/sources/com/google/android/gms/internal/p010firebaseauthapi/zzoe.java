package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoe */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzoe implements zzuz<zzwq> {
    final /* synthetic */ EmailAuthCredential zza;
    final /* synthetic */ zztl zzb;
    final /* synthetic */ zzpt zzc;

    zzoe(zzpt zzpt, EmailAuthCredential emailAuthCredential, zztl zztl) {
        this.zzc = zzpt;
        this.zza = emailAuthCredential;
        this.zzb = zztl;
    }

    public final void zza(String str) {
        this.zzb.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        this.zzc.zzN(new zzvy(this.zza, ((zzwq) obj).zze()), this.zzb);
    }
}
