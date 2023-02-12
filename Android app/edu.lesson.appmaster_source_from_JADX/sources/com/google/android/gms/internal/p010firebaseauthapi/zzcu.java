package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcu */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzcu extends zzat<zzjt, zzjq> {
    final /* synthetic */ zzcv zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcu(zzcv zzcv, Class cls) {
        super(cls);
        this.zza = zzcv;
    }

    public final /* bridge */ /* synthetic */ zzaaz zza(zzyu zzyu) throws zzaae {
        return zzjt.zzc(zzyu, zzzj.zza());
    }

    public final /* bridge */ /* synthetic */ Object zzc(zzaaz zzaaz) throws GeneralSecurityException {
        zzjt zzjt = (zzjt) zzaaz;
        zzjp zzb = zzjq.zzb();
        zzb.zzb(0);
        zzb.zza(zzyu.zzn(zzlg.zza(32)));
        return (zzjq) zzb.zzk();
    }

    public final Map<String, zzas<zzjt>> zzd() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("XCHACHA20_POLY1305", new zzas(zzjt.zzb(), 1));
        hashMap.put("XCHACHA20_POLY1305_RAW", new zzas(zzjt.zzb(), 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zze(zzaaz zzaaz) throws GeneralSecurityException {
        zzjt zzjt = (zzjt) zzaaz;
    }
}
