package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpb */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzpb implements zzuz<zzxu> {
    final /* synthetic */ zztl zza;
    final /* synthetic */ zzpt zzb;

    zzpb(zzpt zzpt, zztl zztl) {
        this.zzb = zzpt;
        this.zza = zztl;
    }

    public final void zza(String str) {
        this.zza.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzxu zzxu = (zzxu) obj;
        this.zzb.zzO(new zzwq(zzxu.zzd(), zzxu.zzc(), Long.valueOf(zzxu.zzb()), "Bearer"), (String) null, (String) null, Boolean.valueOf(zzxu.zze()), (zze) null, this.zza, this);
    }
}
