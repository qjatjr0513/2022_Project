package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
public abstract class zzje extends zzin {
    private static final Logger zzb = Logger.getLogger(zzje.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzc = zzml.zzx();
    zzjf zza;

    private zzje() {
    }

    /* synthetic */ zzje(zzjb zzjb) {
    }

    public static int zzA(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (i & -268435456) == 0 ? 4 : 5;
    }

    public static int zzB(long j) {
        int i;
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if ((-34359738368L & j) != 0) {
            j >>>= 28;
            i = 6;
        } else {
            i = 2;
        }
        if ((-2097152 & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & -16384) != 0 ? i + 1 : i;
    }

    public static zzje zzC(byte[] bArr) {
        return new zzjc(bArr, 0, bArr.length);
    }

    public static int zzt(zzix zzix) {
        int zzd = zzix.zzd();
        return zzA(zzd) + zzd;
    }

    @Deprecated
    static int zzu(int i, zzlc zzlc, zzln zzln) {
        int zzA = zzA(i << 3);
        int i2 = zzA + zzA;
        zzih zzih = (zzih) zzlc;
        int zzbo = zzih.zzbo();
        if (zzbo == -1) {
            zzbo = zzln.zza(zzih);
            zzih.zzbr(zzbo);
        }
        return i2 + zzbo;
    }

    public static int zzv(int i) {
        if (i >= 0) {
            return zzA(i);
        }
        return 10;
    }

    public static int zzw(zzkk zzkk) {
        int zza2 = zzkk.zza();
        return zzA(zza2) + zza2;
    }

    static int zzx(zzlc zzlc, zzln zzln) {
        zzih zzih = (zzih) zzlc;
        int zzbo = zzih.zzbo();
        if (zzbo == -1) {
            zzbo = zzln.zza(zzih);
            zzih.zzbr(zzbo);
        }
        return zzA(zzbo) + zzbo;
    }

    public static int zzy(String str) {
        int i;
        try {
            i = zzmq.zzc(str);
        } catch (zzmp e) {
            i = str.getBytes(zzkf.zzb).length;
        }
        return zzA(i) + i;
    }

    public static int zzz(int i) {
        return zzA(i << 3);
    }

    public final void zzD() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzE(String str, zzmp zzmp) throws IOException {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzmp);
        byte[] bytes = str.getBytes(zzkf.zzb);
        try {
            int length = bytes.length;
            zzq(length);
            zzl(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzjd(e);
        } catch (zzjd e2) {
            throw e2;
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzd(int i, boolean z) throws IOException;

    public abstract void zze(int i, zzix zzix) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzg(int i) throws IOException;

    public abstract void zzh(int i, long j) throws IOException;

    public abstract void zzi(long j) throws IOException;

    public abstract void zzj(int i, int i2) throws IOException;

    public abstract void zzk(int i) throws IOException;

    public abstract void zzl(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzm(int i, String str) throws IOException;

    public abstract void zzo(int i, int i2) throws IOException;

    public abstract void zzp(int i, int i2) throws IOException;

    public abstract void zzq(int i) throws IOException;

    public abstract void zzr(int i, long j) throws IOException;

    public abstract void zzs(long j) throws IOException;
}
