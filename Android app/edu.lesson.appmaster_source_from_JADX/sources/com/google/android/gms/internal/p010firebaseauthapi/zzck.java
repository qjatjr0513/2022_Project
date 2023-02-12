package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzck */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzck extends zzat<zzgp, zzgm> {
    final /* synthetic */ zzcl zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzck(zzcl zzcl, Class cls) {
        super(cls);
        this.zza = zzcl;
    }

    public final /* bridge */ /* synthetic */ zzaaz zza(zzyu zzyu) throws zzaae {
        return zzgp.zzc(zzyu, zzzj.zza());
    }

    public final /* bridge */ /* synthetic */ Object zzc(zzaaz zzaaz) throws GeneralSecurityException {
        zzgp zzgp = (zzgp) zzaaz;
        zzgl zzb = zzgm.zzb();
        zzb.zzb(0);
        zzb.zza(zzyu.zzn(zzlg.zza(32)));
        return (zzgm) zzb.zzk();
    }

    public final Map<String, zzas<zzgp>> zzd() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("CHACHA20_POLY1305", new zzas(zzgp.zzb(), 1));
        hashMap.put("CHACHA20_POLY1305_RAW", new zzas(zzgp.zzb(), 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zze(zzaaz zzaaz) throws GeneralSecurityException {
        zzgp zzgp = (zzgp) zzaaz;
    }
}
