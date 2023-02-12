package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpo */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzpo implements zzuz<zzwq> {
    final /* synthetic */ UserProfileChangeRequest zza;
    final /* synthetic */ zztl zzb;
    final /* synthetic */ zzpt zzc;

    zzpo(zzpt zzpt, UserProfileChangeRequest userProfileChangeRequest, zztl zztl) {
        this.zzc = zzpt;
        this.zza = userProfileChangeRequest;
        this.zzb = zztl;
    }

    public final void zza(String str) {
        this.zzb.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzwq zzwq = (zzwq) obj;
        zzxg zzxg = new zzxg();
        zzxg.zze(zzwq.zze());
        if (this.zza.zzb() || this.zza.getDisplayName() != null) {
            zzxg.zzc(this.zza.getDisplayName());
        }
        if (this.zza.zzc() || this.zza.getPhotoUri() != null) {
            zzxg.zzh(this.zza.zza());
        }
        zzpt.zzd(this.zzc, this.zzb, zzwq, zzxg, this);
    }
}
