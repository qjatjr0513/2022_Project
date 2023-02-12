package com.google.android.gms.internal.p010firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoh */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzoh implements zzuz<zzxh> {
    final /* synthetic */ zzxg zza;
    final /* synthetic */ zzwj zzb;
    final /* synthetic */ zztl zzc;
    final /* synthetic */ zzwq zzd;
    final /* synthetic */ zzuy zze;
    final /* synthetic */ zzpt zzf;

    zzoh(zzpt zzpt, zzxg zzxg, zzwj zzwj, zztl zztl, zzwq zzwq, zzuy zzuy) {
        this.zzf = zzpt;
        this.zza = zzxg;
        this.zzb = zzwj;
        this.zzc = zztl;
        this.zzd = zzwq;
        this.zze = zzuy;
    }

    public final void zza(String str) {
        this.zze.zza(str);
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzxh zzxh = (zzxh) obj;
        if (this.zza.zzn("EMAIL")) {
            this.zzb.zzg((String) null);
        } else if (this.zza.zzk() != null) {
            this.zzb.zzg(this.zza.zzk());
        }
        if (this.zza.zzn("DISPLAY_NAME")) {
            this.zzb.zzf((String) null);
        } else if (this.zza.zzj() != null) {
            this.zzb.zzf(this.zza.zzj());
        }
        if (this.zza.zzn("PHOTO_URL")) {
            this.zzb.zzj((String) null);
        } else if (this.zza.zzm() != null) {
            this.zzb.zzj(this.zza.zzm());
        }
        if (!TextUtils.isEmpty(this.zza.zzl())) {
            this.zzb.zzi(Base64Utils.encode("redacted".getBytes()));
        }
        List zzf2 = zzxh.zzf();
        if (zzf2 == null) {
            zzf2 = new ArrayList();
        }
        this.zzb.zzk(zzf2);
        zztl zztl = this.zzc;
        zzwq zzwq = this.zzd;
        Preconditions.checkNotNull(zzwq);
        Preconditions.checkNotNull(zzxh);
        String zzd2 = zzxh.zzd();
        String zze2 = zzxh.zze();
        if (!TextUtils.isEmpty(zzd2) && !TextUtils.isEmpty(zze2)) {
            zzwq = new zzwq(zze2, zzd2, Long.valueOf(zzxh.zzb()), zzwq.zzg());
        }
        zztl.zzi(zzwq, this.zzb);
    }
}
