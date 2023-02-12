package com.google.android.gms.internal.p010firebaseauthapi;

import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzog */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzog implements zzuz<zzwh> {
    final /* synthetic */ zzuy zza;
    final /* synthetic */ zztl zzb;
    final /* synthetic */ zzwq zzc;
    final /* synthetic */ zzxg zzd;
    final /* synthetic */ zzpt zze;

    zzog(zzpt zzpt, zzuy zzuy, zztl zztl, zzwq zzwq, zzxg zzxg) {
        this.zze = zzpt;
        this.zza = zzuy;
        this.zzb = zztl;
        this.zzc = zzwq;
        this.zzd = zzxg;
    }

    public final void zza(String str) {
        this.zza.zza(str);
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        List<zzwj> zzb2 = ((zzwh) obj).zzb();
        if (zzb2 == null || zzb2.isEmpty()) {
            this.zza.zza("No users");
        } else {
            zzpt.zzf(this.zze, this.zzb, this.zzc, zzb2.get(0), this.zzd, this.zza);
        }
    }
}
