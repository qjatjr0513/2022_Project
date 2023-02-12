package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaq */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzaq {
    private final zzij zza;

    private zzaq(zzij zzij) {
        this.zza = zzij;
    }

    public static zzaq zze(String str, byte[] bArr, int i) {
        zzjk zzjk;
        zzii zza2 = zzij.zza();
        zza2.zzb(str);
        zza2.zzc(zzyu.zzn(bArr));
        zzjk zzjk2 = zzjk.UNKNOWN_PREFIX;
        switch (i - 1) {
            case 0:
                zzjk = zzjk.TINK;
                break;
            case 1:
                zzjk = zzjk.LEGACY;
                break;
            case 2:
                zzjk = zzjk.RAW;
                break;
            default:
                zzjk = zzjk.CRUNCHY;
                break;
        }
        zza2.zza(zzjk);
        return new zzaq((zzij) zza2.zzk());
    }

    /* access modifiers changed from: package-private */
    public final zzij zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zza.zzf();
    }

    public final byte[] zzc() {
        return this.zza.zze().zzs();
    }

    public final int zzd() {
        zzjk zzd = this.zza.zzd();
        zzjk zzjk = zzjk.UNKNOWN_PREFIX;
        switch (zzd.ordinal()) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            default:
                throw new IllegalArgumentException("Unknown output prefix type");
        }
    }
}
