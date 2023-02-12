package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzch */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzch extends zzat<zzgd, zzga> {
    final /* synthetic */ zzci zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzch(zzci zzci, Class cls) {
        super(cls);
        this.zza = zzci;
    }

    public final /* bridge */ /* synthetic */ zzaaz zza(zzyu zzyu) throws zzaae {
        return zzgd.zzd(zzyu, zzzj.zza());
    }

    public final /* bridge */ /* synthetic */ Object zzc(zzaaz zzaaz) throws GeneralSecurityException {
        zzfz zzb = zzga.zzb();
        zzb.zza(zzyu.zzn(zzlg.zza(((zzgd) zzaaz).zza())));
        zzb.zzb(0);
        return (zzga) zzb.zzk();
    }

    public final Map<String, zzas<zzgd>> zzd() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_GCM_SIV", zzci.zzk(16, 1));
        hashMap.put("AES128_GCM_SIV_RAW", zzci.zzk(16, 3));
        hashMap.put("AES256_GCM_SIV", zzci.zzk(32, 1));
        hashMap.put("AES256_GCM_SIV_RAW", zzci.zzk(32, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zze(zzaaz zzaaz) throws GeneralSecurityException {
        zzli.zzb(((zzgd) zzaaz).zza());
    }
}
