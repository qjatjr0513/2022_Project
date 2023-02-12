package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpq */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzpq implements zzuz<zzwq> {
    final /* synthetic */ String zza;
    final /* synthetic */ zztl zzb;
    final /* synthetic */ zzpt zzc;

    zzpq(zzpt zzpt, String str, zztl zztl) {
        this.zzc = zzpt;
        this.zza = str;
        this.zzb = zztl;
    }

    public final void zza(String str) {
        this.zzb.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzwq zzwq = (zzwq) obj;
        String zze = zzwq.zze();
        zzxg zzxg = new zzxg();
        zzxg.zze(zze);
        zzxg.zzg(this.zza);
        zzpt.zzd(this.zzc, this.zzb, zzwq, zzxg, this);
    }
}
