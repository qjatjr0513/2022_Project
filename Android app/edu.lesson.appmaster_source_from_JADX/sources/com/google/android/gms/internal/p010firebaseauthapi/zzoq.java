package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoq */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzoq implements zzuz<zzwq> {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zztl zzc;
    final /* synthetic */ zzpt zzd;

    zzoq(zzpt zzpt, String str, String str2, zztl zztl) {
        this.zzd = zzpt;
        this.zza = str;
        this.zzb = str2;
        this.zzc = zztl;
    }

    public final void zza(String str) {
        this.zzc.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzwq zzwq = (zzwq) obj;
        zzxg zzxg = new zzxg();
        zzxg.zze(zzwq.zze());
        zzxg.zzd(this.zza);
        zzxg.zzg(this.zzb);
        zzpt.zzd(this.zzd, this.zzc, zzwq, zzxg, this);
    }
}
