package com.google.android.gms.internal.p010firebaseauthapi;

import android.text.TextUtils;
import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoy */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzoy implements zzuz<zzyb> {
    final /* synthetic */ zzoz zza;

    zzoy(zzoz zzoz) {
        this.zza = zzoz;
    }

    public final void zza(String str) {
        this.zza.zzb.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzyb zzyb = (zzyb) obj;
        if (TextUtils.isEmpty(zzyb.zzb()) || TextUtils.isEmpty(zzyb.zzc())) {
            this.zza.zzb.zzh(zzai.zza("INTERNAL_SUCCESS_SIGN_OUT"));
            return;
        }
        this.zza.zzc.zzO(new zzwq(zzyb.zzc(), zzyb.zzb(), Long.valueOf(zzws.zza(zzyb.zzb())), "Bearer"), (String) null, (String) null, false, (zze) null, this.zza.zzb, this);
    }
}
