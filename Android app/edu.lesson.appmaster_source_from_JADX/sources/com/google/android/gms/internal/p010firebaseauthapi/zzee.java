package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzee */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzee extends zzat<zzhw, zzht> {
    final /* synthetic */ zzef zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzee(zzef zzef, Class cls) {
        super(cls);
        this.zza = zzef;
    }

    public final /* bridge */ /* synthetic */ zzaaz zza(zzyu zzyu) throws zzaae {
        return zzhw.zze(zzyu, zzzj.zza());
    }

    public final /* bridge */ /* synthetic */ Object zzc(zzaaz zzaaz) throws GeneralSecurityException {
        zzhw zzhw = (zzhw) zzaaz;
        zzhs zzb = zzht.zzb();
        zzb.zzc(0);
        zzb.zzb(zzhw.zzf());
        zzb.zza(zzyu.zzn(zzlg.zza(zzhw.zza())));
        return (zzht) zzb.zzk();
    }

    public final Map<String, zzas<zzhw>> zzd() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("HMAC_SHA256_128BITTAG", zzef.zzk(32, 16, zzhq.SHA256, 1));
        hashMap.put("HMAC_SHA256_128BITTAG_RAW", zzef.zzk(32, 16, zzhq.SHA256, 3));
        hashMap.put("HMAC_SHA256_256BITTAG", zzef.zzk(32, 32, zzhq.SHA256, 1));
        hashMap.put("HMAC_SHA256_256BITTAG_RAW", zzef.zzk(32, 32, zzhq.SHA256, 3));
        hashMap.put("HMAC_SHA512_128BITTAG", zzef.zzk(64, 16, zzhq.SHA512, 1));
        hashMap.put("HMAC_SHA512_128BITTAG_RAW", zzef.zzk(64, 16, zzhq.SHA512, 3));
        hashMap.put("HMAC_SHA512_256BITTAG", zzef.zzk(64, 32, zzhq.SHA512, 1));
        hashMap.put("HMAC_SHA512_256BITTAG_RAW", zzef.zzk(64, 32, zzhq.SHA512, 3));
        hashMap.put("HMAC_SHA512_512BITTAG", zzef.zzk(64, 64, zzhq.SHA512, 1));
        hashMap.put("HMAC_SHA512_512BITTAG_RAW", zzef.zzk(64, 64, zzhq.SHA512, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zze(zzaaz zzaaz) throws GeneralSecurityException {
        zzhw zzhw = (zzhw) zzaaz;
        if (zzhw.zza() >= 16) {
            zzef.zzm(zzhw.zzf());
            return;
        }
        throw new GeneralSecurityException("key too short");
    }
}
