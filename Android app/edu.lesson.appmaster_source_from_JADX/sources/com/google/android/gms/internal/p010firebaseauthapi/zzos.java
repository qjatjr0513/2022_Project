package com.google.android.gms.internal.p010firebaseauthapi;

import android.content.Context;
import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzos */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzos implements zzuz<zzwq> {
    final /* synthetic */ zzxy zza;
    final /* synthetic */ zztl zzb;
    final /* synthetic */ zzpt zzc;

    zzos(zzpt zzpt, zzxy zzxy, Context context, zztl zztl) {
        this.zzc = zzpt;
        this.zza = zzxy;
        this.zzb = zztl;
    }

    public final void zza(String str) {
        this.zzb.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        this.zza.zzd(((zzwq) obj).zze());
        this.zzc.zza.zzt((Context) null, this.zza, new zzor(this, this));
    }
}
