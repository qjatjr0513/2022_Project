package com.google.android.gms.internal.p010firebaseauthapi;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECPrivateKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzde */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzde extends zzau<zzal, zzhd> {
    zzde(Class cls) {
        super(cls);
    }

    public final /* bridge */ /* synthetic */ Object zzb(Object obj) throws GeneralSecurityException {
        zzhd zzhd = (zzhd) obj;
        zzha zzb = zzhd.zze().zzb();
        zzhj zzf = zzb.zzf();
        int zzc = zzdp.zzc(zzf.zzd());
        byte[] zzs = zzhd.zzf().zzs();
        ECPrivateKeySpec eCPrivateKeySpec = new ECPrivateKeySpec(new BigInteger(1, zzs), zzkn.zzf(zzc));
        return new zzkj((ECPrivateKey) zzkp.zzg.zza("EC").generatePrivate(eCPrivateKeySpec), zzf.zzf().zzs(), zzdp.zza(zzf.zze()), zzdp.zzd(zzb.zza()), new zzdq(zzb.zzb().zzd()));
    }
}
