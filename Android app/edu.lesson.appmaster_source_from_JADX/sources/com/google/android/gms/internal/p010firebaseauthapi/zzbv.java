package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbv */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzbv extends zzat<zzez, zzew> {
    final /* synthetic */ zzbw zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbv(zzbw zzbw, Class cls) {
        super(cls);
        this.zza = zzbw;
    }

    public final /* bridge */ /* synthetic */ zzaaz zza(zzyu zzyu) throws zzaae {
        return zzez.zzc(zzyu, zzzj.zza());
    }

    public final /* bridge */ /* synthetic */ Object zzc(zzaaz zzaaz) throws GeneralSecurityException {
        zzez zzez = (zzez) zzaaz;
        new zzbz();
        zzfc zzg = zzby.zzg(zzez.zzd());
        Object zzc = new zzef().zza().zzc(zzez.zze());
        zzev zzb = zzew.zzb();
        zzb.zza(zzg);
        zzb.zzb((zzht) zzc);
        zzb.zzc(0);
        return (zzew) zzb.zzk();
    }

    public final Map<String, zzas<zzez>> zzd() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_CTR_HMAC_SHA256", zzbw.zzj(16, 16, 32, 16, zzhq.SHA256, 1));
        hashMap.put("AES128_CTR_HMAC_SHA256_RAW", zzbw.zzj(16, 16, 32, 16, zzhq.SHA256, 3));
        hashMap.put("AES256_CTR_HMAC_SHA256", zzbw.zzj(32, 16, 32, 32, zzhq.SHA256, 1));
        hashMap.put("AES256_CTR_HMAC_SHA256_RAW", zzbw.zzj(32, 16, 32, 32, zzhq.SHA256, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zze(zzaaz zzaaz) throws GeneralSecurityException {
        zzez zzez = (zzez) zzaaz;
        ((zzby) new zzbz().zza()).zze(zzez.zzd());
        new zzef().zza().zze(zzez.zze());
        zzli.zzb(zzez.zzd().zza());
    }
}
