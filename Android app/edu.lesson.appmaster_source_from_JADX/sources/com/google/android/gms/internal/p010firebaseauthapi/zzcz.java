package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcz */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzcz extends zzat<zzgj, zzgg> {
    final /* synthetic */ zzda zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcz(zzda zzda, Class cls) {
        super(cls);
        this.zza = zzda;
    }

    public final /* bridge */ /* synthetic */ zzaaz zza(zzyu zzyu) throws zzaae {
        return zzgj.zzd(zzyu, zzzj.zza());
    }

    public final /* bridge */ /* synthetic */ Object zzc(zzaaz zzaaz) throws GeneralSecurityException {
        zzgf zzb = zzgg.zzb();
        zzb.zza(zzyu.zzn(zzlg.zza(((zzgj) zzaaz).zza())));
        zzb.zzb(0);
        return (zzgg) zzb.zzk();
    }

    public final Map<String, zzas<zzgj>> zzd() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        zzgi zzb = zzgj.zzb();
        zzb.zza(64);
        hashMap.put("AES256_SIV", new zzas((zzgj) zzb.zzk(), 1));
        zzgi zzb2 = zzgj.zzb();
        zzb2.zza(64);
        hashMap.put("AES256_SIV_RAW", new zzas((zzgj) zzb2.zzk(), 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zze(zzaaz zzaaz) throws GeneralSecurityException {
        zzgj zzgj = (zzgj) zzaaz;
        if (zzgj.zza() != 64) {
            int zza2 = zzgj.zza();
            StringBuilder sb = new StringBuilder(61);
            sb.append("invalid key size: ");
            sb.append(zza2);
            sb.append(". Valid keys must have 64 bytes.");
            throw new InvalidAlgorithmParameterException(sb.toString());
        }
    }
}
