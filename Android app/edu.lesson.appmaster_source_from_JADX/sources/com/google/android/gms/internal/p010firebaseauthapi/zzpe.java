package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpe */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzpe implements zzuz<zzwd> {
    final /* synthetic */ zztl zza;
    final /* synthetic */ zzpt zzb;

    zzpe(zzpt zzpt, zztl zztl) {
        this.zzb = zzpt;
        this.zza = zztl;
    }

    public final void zza(String str) {
        this.zza.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzwd zzwd = (zzwd) obj;
        this.zzb.zzO(new zzwq(zzwd.zzc(), zzwd.zzb(), Long.valueOf(zzws.zza(zzwd.zzb())), "Bearer"), (String) null, (String) null, false, (zze) null, this.zza, this);
    }
}
