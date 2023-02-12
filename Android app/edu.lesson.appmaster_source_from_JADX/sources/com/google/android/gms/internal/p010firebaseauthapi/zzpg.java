package com.google.android.gms.internal.p010firebaseauthapi;

import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpg */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzpg implements zzuz<zzwh> {
    final /* synthetic */ zzuz zza;
    final /* synthetic */ zzwq zzb;
    final /* synthetic */ zzph zzc;

    zzpg(zzph zzph, zzuz zzuz, zzwq zzwq) {
        this.zzc = zzph;
        this.zza = zzuz;
        this.zzb = zzwq;
    }

    public final void zza(String str) {
        this.zza.zza(str);
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        List<zzwj> zzb2 = ((zzwh) obj).zzb();
        if (zzb2 == null || zzb2.isEmpty()) {
            this.zza.zza("No users");
        } else {
            this.zzc.zza.zzi(this.zzb, zzb2.get(0));
        }
    }
}
