package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpm */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzpm implements zzuz<zzxs> {
    final /* synthetic */ zztl zza;
    final /* synthetic */ zzpt zzb;

    zzpm(zzpt zzpt, zztl zztl) {
        this.zzb = zzpt;
        this.zza = zztl;
    }

    public final void zza(String str) {
        this.zza.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzxs zzxs = (zzxs) obj;
        if (!zzxs.zzm()) {
            zzpt.zzc(this.zzb, zzxs, this.zza, this);
        } else {
            this.zza.zzf(new zzoa(zzxs.zzg(), zzxs.zzl(), zzxs.zzc()));
        }
    }
}
