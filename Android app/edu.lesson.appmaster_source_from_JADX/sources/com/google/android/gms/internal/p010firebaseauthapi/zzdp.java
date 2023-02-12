package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdp */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzdp {
    public static String zza(zzhq zzhq) throws NoSuchAlgorithmException {
        zzgr zzgr = zzgr.UNKNOWN_FORMAT;
        zzhl zzhl = zzhl.UNKNOWN_CURVE;
        zzhq zzhq2 = zzhq.UNKNOWN_HASH;
        switch (zzhq.ordinal()) {
            case 1:
                return "HmacSha1";
            case 2:
                return "HmacSha384";
            case 3:
                return "HmacSha256";
            case 4:
                return "HmacSha512";
            case 5:
                return "HmacSha224";
            default:
                String valueOf = String.valueOf(zzhq);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
                sb.append("hash unsupported for HMAC: ");
                sb.append(valueOf);
                throw new NoSuchAlgorithmException(sb.toString());
        }
    }

    public static void zzb(zzha zzha) throws GeneralSecurityException {
        zzkn.zzf(zzc(zzha.zzf().zzd()));
        zza(zzha.zzf().zze());
        if (zzha.zza() != zzgr.UNKNOWN_FORMAT) {
            zzbn.zzc(zzha.zzb().zzd());
            return;
        }
        throw new GeneralSecurityException("unknown EC point format");
    }

    public static int zzc(zzhl zzhl) throws GeneralSecurityException {
        zzgr zzgr = zzgr.UNKNOWN_FORMAT;
        zzhl zzhl2 = zzhl.UNKNOWN_CURVE;
        zzhq zzhq = zzhq.UNKNOWN_HASH;
        switch (zzhl.ordinal()) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            default:
                String valueOf = String.valueOf(zzhl);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
                sb.append("unknown curve type: ");
                sb.append(valueOf);
                throw new GeneralSecurityException(sb.toString());
        }
    }

    public static int zzd(zzgr zzgr) throws GeneralSecurityException {
        zzgr zzgr2 = zzgr.UNKNOWN_FORMAT;
        zzhl zzhl = zzhl.UNKNOWN_CURVE;
        zzhq zzhq = zzhq.UNKNOWN_HASH;
        switch (zzgr.ordinal()) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            default:
                String valueOf = String.valueOf(zzgr);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
                sb.append("unknown point format: ");
                sb.append(valueOf);
                throw new GeneralSecurityException(sb.toString());
        }
    }
}
