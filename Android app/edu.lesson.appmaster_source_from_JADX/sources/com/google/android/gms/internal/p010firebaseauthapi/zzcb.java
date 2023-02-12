package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcb */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzcb extends zzat<zzfo, zzfl> {
    final /* synthetic */ zzcc zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcb(zzcc zzcc, Class cls) {
        super(cls);
        this.zza = zzcc;
    }

    public final /* bridge */ /* synthetic */ zzaaz zza(zzyu zzyu) throws zzaae {
        return zzfo.zzd(zzyu, zzzj.zza());
    }

    public final /* bridge */ /* synthetic */ Object zzc(zzaaz zzaaz) throws GeneralSecurityException {
        zzfo zzfo = (zzfo) zzaaz;
        zzfk zzb = zzfl.zzb();
        zzb.zza(zzyu.zzn(zzlg.zza(zzfo.zza())));
        zzb.zzb(zzfo.zze());
        zzb.zzc(0);
        return (zzfl) zzb.zzk();
    }

    public final Map<String, zzas<zzfo>> zzd() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_EAX", zzcc.zzj(16, 16, 1));
        hashMap.put("AES128_EAX_RAW", zzcc.zzj(16, 16, 3));
        hashMap.put("AES256_EAX", zzcc.zzj(32, 16, 1));
        hashMap.put("AES256_EAX_RAW", zzcc.zzj(32, 16, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zze(zzaaz zzaaz) throws GeneralSecurityException {
        zzfo zzfo = (zzfo) zzaaz;
        zzli.zzb(zzfo.zza());
        if (zzfo.zze().zza() != 12 && zzfo.zze().zza() != 16) {
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
    }
}
