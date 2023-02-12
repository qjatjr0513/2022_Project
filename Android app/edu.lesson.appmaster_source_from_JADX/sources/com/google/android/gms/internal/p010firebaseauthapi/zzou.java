package com.google.android.gms.internal.p010firebaseauthapi;

import android.content.Context;
import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzou */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzou implements zzuz<zzwq> {
    final /* synthetic */ zzxq zza;
    final /* synthetic */ zztl zzb;
    final /* synthetic */ zzpt zzc;

    zzou(zzpt zzpt, zzxq zzxq, zztl zztl) {
        this.zzc = zzpt;
        this.zza = zzxq;
        this.zzb = zztl;
    }

    public final void zza(String str) {
        this.zzb.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        this.zza.zzd(true);
        this.zza.zzc(((zzwq) obj).zze());
        this.zzc.zza.zzq((Context) null, this.zza, new zzot(this, this));
    }
}
