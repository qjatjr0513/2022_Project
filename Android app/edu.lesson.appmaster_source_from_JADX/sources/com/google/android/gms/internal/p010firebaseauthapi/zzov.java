package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzov */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzov implements zzuz<zzwq> {
    final /* synthetic */ zztl zza;
    final /* synthetic */ zzpt zzb;

    zzov(zzpt zzpt, zztl zztl) {
        this.zzb = zzpt;
        this.zza = zztl;
    }

    public final void zza(String str) {
        this.zza.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzwq zzwq = (zzwq) obj;
        zzxg zzxg = new zzxg();
        zzxg.zze(zzwq.zze());
        zzxg.zzd((String) null);
        zzxg.zzg((String) null);
        zzpt.zzd(this.zzb, this.zza, zzwq, zzxg, this);
    }
}
