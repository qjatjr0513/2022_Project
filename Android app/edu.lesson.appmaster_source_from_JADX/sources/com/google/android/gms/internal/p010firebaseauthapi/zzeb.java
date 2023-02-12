package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzeb */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzeb extends zzat<zzeq, zzen> {
    zzeb(zzec zzec, Class cls) {
        super(cls);
    }

    public final /* bridge */ /* synthetic */ zzaaz zza(zzyu zzyu) throws zzaae {
        return zzeq.zzd(zzyu, zzzj.zza());
    }

    public final /* bridge */ /* synthetic */ Object zzc(zzaaz zzaaz) throws GeneralSecurityException {
        zzeq zzeq = (zzeq) zzaaz;
        zzem zzb = zzen.zzb();
        zzb.zzc(0);
        zzb.zza(zzyu.zzn(zzlg.zza(zzeq.zza())));
        zzb.zzb(zzeq.zze());
        return (zzen) zzb.zzk();
    }

    public final Map<String, zzas<zzeq>> zzd() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        zzep zzb = zzeq.zzb();
        zzb.zza(32);
        zzes zzb2 = zzet.zzb();
        zzb2.zza(16);
        zzb.zzb((zzet) zzb2.zzk());
        hashMap.put("AES_CMAC", new zzas((zzeq) zzb.zzk(), 1));
        zzep zzb3 = zzeq.zzb();
        zzb3.zza(32);
        zzes zzb4 = zzet.zzb();
        zzb4.zza(16);
        zzb3.zzb((zzet) zzb4.zzk());
        hashMap.put("AES256_CMAC", new zzas((zzeq) zzb3.zzk(), 1));
        zzep zzb5 = zzeq.zzb();
        zzb5.zza(32);
        zzes zzb6 = zzet.zzb();
        zzb6.zza(16);
        zzb5.zzb((zzet) zzb6.zzk());
        hashMap.put("AES256_CMAC_RAW", new zzas((zzeq) zzb5.zzk(), 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zze(zzaaz zzaaz) throws GeneralSecurityException {
        zzeq zzeq = (zzeq) zzaaz;
        zzec.zzl(zzeq.zze());
        zzec.zzm(zzeq.zza());
    }
}
