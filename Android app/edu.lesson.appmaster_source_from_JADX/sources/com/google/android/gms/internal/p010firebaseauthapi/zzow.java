package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzow */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzow implements zzuz<zzwh> {
    final /* synthetic */ zzuz zza;
    final /* synthetic */ zzwq zzb;
    final /* synthetic */ zzox zzc;

    zzow(zzox zzox, zzuz zzuz, zzwq zzwq) {
        this.zzc = zzox;
        this.zza = zzuz;
        this.zzb = zzwq;
    }

    public final void zza(String str) {
        this.zzc.zzb.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        List<zzwj> zzb2 = ((zzwh) obj).zzb();
        if (zzb2 == null || zzb2.isEmpty()) {
            this.zza.zza("No users.");
            return;
        }
        zzxg zzxg = new zzxg();
        zzxg.zze(this.zzb.zze());
        zzxg.zzb(this.zzc.zza);
        zzox zzox = this.zzc;
        zzpt.zzf(zzox.zzc, zzox.zzb, this.zzb, zzb2.get(0), zzxg, this.zza);
    }
}
