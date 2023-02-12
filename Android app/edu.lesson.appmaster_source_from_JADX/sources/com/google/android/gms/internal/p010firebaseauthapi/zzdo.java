package com.google.android.gms.internal.p010firebaseauthapi;

@Deprecated
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdo */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzdo {
    public static final zzij zza;
    public static final zzij zzb;
    public static final zzij zzc;
    private static final byte[] zzd;

    static {
        byte[] bArr = new byte[0];
        zzd = bArr;
        byte[] bArr2 = bArr;
        zza = zza(zzhl.NIST_P256, zzhq.SHA256, zzgr.UNCOMPRESSED, zzbq.zza, zzjk.TINK, bArr2);
        zzb = zza(zzhl.NIST_P256, zzhq.SHA256, zzgr.COMPRESSED, zzbq.zza, zzjk.RAW, bArr2);
        zzc = zza(zzhl.NIST_P256, zzhq.SHA256, zzgr.UNCOMPRESSED, zzbq.zze, zzjk.TINK, bArr2);
    }

    public static zzij zza(zzhl zzhl, zzhq zzhq, zzgr zzgr, zzij zzij, zzjk zzjk, byte[] bArr) {
        zzgw zza2 = zzgx.zza();
        zzhi zza3 = zzhj.zza();
        zza3.zza(zzhl);
        zza3.zzb(zzhq);
        zza3.zzc(zzyu.zzn(bArr));
        zzgt zza4 = zzgu.zza();
        zza4.zza(zzij);
        zzgz zzc2 = zzha.zzc();
        zzc2.zzc((zzhj) zza3.zzk());
        zzc2.zza((zzgu) zza4.zzk());
        zzc2.zzb(zzgr);
        zza2.zza((zzha) zzc2.zzk());
        zzii zza5 = zzij.zza();
        new zzdg();
        zza5.zzb("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey");
        zza5.zza(zzjk);
        zza5.zzc(((zzgx) zza2.zzk()).zzo());
        return (zzij) zza5.zzk();
    }
}
