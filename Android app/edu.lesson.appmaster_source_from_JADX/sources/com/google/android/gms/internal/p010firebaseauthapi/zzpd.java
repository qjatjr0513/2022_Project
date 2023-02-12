package com.google.android.gms.internal.p010firebaseauthapi;

import android.content.Context;
import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpd */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzpd implements zzuz<zzwq> {
    final /* synthetic */ zzwa zza;
    final /* synthetic */ zztl zzb;
    final /* synthetic */ zzpt zzc;

    zzpd(zzpt zzpt, zzwa zzwa, Context context, zztl zztl) {
        this.zzc = zzpt;
        this.zza = zzwa;
        this.zzb = zztl;
    }

    public final void zza(String str) {
        this.zzb.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        this.zza.zzc(((zzwq) obj).zze());
        this.zzc.zza.zzd((Context) null, this.zza, new zzpc(this));
    }
}
