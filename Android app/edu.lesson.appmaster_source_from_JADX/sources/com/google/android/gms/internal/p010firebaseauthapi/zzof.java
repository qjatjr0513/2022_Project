package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzof */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzof implements zzuz<zzvz> {
    final /* synthetic */ zztl zza;
    final /* synthetic */ zzpt zzb;

    zzof(zzpt zzpt, zztl zztl) {
        this.zzb = zzpt;
        this.zza = zztl;
    }

    public final void zza(String str) {
        this.zza.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzvz zzvz = (zzvz) obj;
        if (zzvz.zzg()) {
            this.zza.zzf(new zzoa(zzvz.zzd(), zzvz.zzf(), (zze) null));
            return;
        }
        this.zzb.zzO(new zzwq(zzvz.zze(), zzvz.zzc(), Long.valueOf(zzvz.zzb()), "Bearer"), (String) null, (String) null, Boolean.valueOf(zzvz.zzh()), (zze) null, this.zza, this);
    }
}
