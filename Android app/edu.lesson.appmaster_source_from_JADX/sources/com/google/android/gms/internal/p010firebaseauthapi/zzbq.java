package com.google.android.gms.internal.p010firebaseauthapi;

@Deprecated
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbq */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzbq {
    public static final zzij zza = zzc(16);
    public static final zzij zzb = zzc(32);
    public static final zzij zzc = zzb(16, 16);
    public static final zzij zzd = zzb(32, 16);
    public static final zzij zze = zza(16, 16, 32, 16, zzhq.SHA256);
    public static final zzij zzf = zza(32, 16, 32, 32, zzhq.SHA256);
    public static final zzij zzg;
    public static final zzij zzh;

    static {
        zzii zza2 = zzij.zza();
        new zzcl();
        zza2.zzb("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key");
        zza2.zza(zzjk.TINK);
        zzg = (zzij) zza2.zzk();
        zzii zza3 = zzij.zza();
        new zzcv();
        zza3.zzb("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");
        zza3.zza(zzjk.TINK);
        zzh = (zzij) zza3.zzk();
    }

    public static zzij zza(int i, int i2, int i3, int i4, zzhq zzhq) {
        zzfe zzb2 = zzff.zzb();
        zzfh zzb3 = zzfi.zzb();
        zzb3.zza(16);
        zzb2.zzb((zzfi) zzb3.zzk());
        zzb2.zza(i);
        zzhv zzb4 = zzhw.zzb();
        zzhy zzc2 = zzhz.zzc();
        zzc2.zza(zzhq);
        zzc2.zzb(i4);
        zzb4.zzb((zzhz) zzc2.zzk());
        zzb4.zza(32);
        zzey zza2 = zzez.zza();
        zza2.zza((zzff) zzb2.zzk());
        zza2.zzb((zzhw) zzb4.zzk());
        zzii zza3 = zzij.zza();
        zza3.zzc(((zzez) zza2.zzk()).zzo());
        new zzbw();
        zza3.zzb("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey");
        zza3.zza(zzjk.TINK);
        return (zzij) zza3.zzk();
    }

    public static zzij zzb(int i, int i2) {
        zzfn zzb2 = zzfo.zzb();
        zzb2.zza(i);
        zzfq zzb3 = zzfr.zzb();
        zzb3.zza(16);
        zzb2.zzb((zzfr) zzb3.zzk());
        zzii zza2 = zzij.zza();
        zza2.zzc(((zzfo) zzb2.zzk()).zzo());
        new zzcc();
        zza2.zzb("type.googleapis.com/google.crypto.tink.AesEaxKey");
        zza2.zza(zzjk.TINK);
        return (zzij) zza2.zzk();
    }

    public static zzij zzc(int i) {
        zzfw zzb2 = zzfx.zzb();
        zzb2.zza(i);
        zzii zza2 = zzij.zza();
        zza2.zzc(((zzfx) zzb2.zzk()).zzo());
        new zzcf();
        zza2.zzb("type.googleapis.com/google.crypto.tink.AesGcmKey");
        zza2.zza(zzjk.TINK);
        return (zzij) zza2.zzk();
    }
}
