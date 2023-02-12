package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzop */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzop implements zzuz<zzxz> {
    final /* synthetic */ zztl zza;
    final /* synthetic */ zzpt zzb;

    zzop(zzpt zzpt, zztl zztl) {
        this.zzb = zzpt;
        this.zza = zztl;
    }

    public final void zza(String str) {
        this.zza.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzxz zzxz = (zzxz) obj;
        this.zzb.zzO(new zzwq(zzxz.zze(), zzxz.zzc(), Long.valueOf(zzxz.zzb()), "Bearer"), (String) null, (String) null, Boolean.valueOf(zzxz.zzg()), (zze) null, this.zza, this);
    }
}
