package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpn */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzpn implements zzuz<zzxj> {
    final /* synthetic */ zztl zza;
    final /* synthetic */ zzpt zzb;

    zzpn(zzpt zzpt, zztl zztl) {
        this.zzb = zzpt;
        this.zza = zztl;
    }

    public final void zza(String str) {
        this.zza.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzxj zzxj = (zzxj) obj;
        this.zzb.zzO(new zzwq(zzxj.zzd(), zzxj.zzc(), Long.valueOf(zzxj.zzb()), "Bearer"), (String) null, (String) null, true, (zze) null, this.zza, this);
    }
}
