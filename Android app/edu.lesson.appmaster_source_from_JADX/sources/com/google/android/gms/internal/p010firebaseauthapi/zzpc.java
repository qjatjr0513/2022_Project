package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpc */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzpc implements zzuz<zzwb> {
    final /* synthetic */ zzpd zza;

    zzpc(zzpd zzpd) {
        this.zza = zzpd;
    }

    public final void zza(String str) {
        this.zza.zzb.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzwb zzwb = (zzwb) obj;
        this.zza.zzc.zzO(new zzwq(zzwb.zzc(), zzwb.zzb(), Long.valueOf(zzws.zza(zzwb.zzb())), "Bearer"), (String) null, (String) null, false, (zze) null, this.zza.zzb, this);
    }
}
