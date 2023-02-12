package com.google.android.gms.internal.p010firebaseauthapi;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzze */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public abstract class zzze extends zzyk {
    private static final Logger zza = Logger.getLogger(zzze.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzb = zzacj.zzx();
    zzzf zze;

    private zzze() {
    }

    /* synthetic */ zzze(zzyz zzyz) {
    }

    static int zzA(zzaaz zzaaz, zzabl zzabl) {
        zzye zzye = (zzye) zzaaz;
        int zzn = zzye.zzn();
        if (zzn == -1) {
            zzn = zzabl.zza(zzye);
            zzye.zzp(zzn);
        }
        return zzE(zzn) + zzn;
    }

    static int zzB(int i) {
        if (i > 4096) {
            return 4096;
        }
        return i;
    }

    public static int zzC(String str) {
        int i;
        try {
            i = zzaco.zzc(str);
        } catch (zzacn e) {
            i = str.getBytes(zzaac.zza).length;
        }
        return zzE(i) + i;
    }

    public static int zzD(int i) {
        return zzE(i << 3);
    }

    public static int zzE(int i) {
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

    public static int zzF(long j) {
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

    public static zzze zzG(byte[] bArr) {
        return new zzzb(bArr, 0, bArr.length);
    }

    public static zzze zzH(OutputStream outputStream, int i) {
        return new zzzd(outputStream, i);
    }

    public static int zzw(zzyu zzyu) {
        int zzd = zzyu.zzd();
        return zzE(zzd) + zzd;
    }

    @Deprecated
    static int zzx(int i, zzaaz zzaaz, zzabl zzabl) {
        int zzE = zzE(i << 3);
        int i2 = zzE + zzE;
        zzye zzye = (zzye) zzaaz;
        int zzn = zzye.zzn();
        if (zzn == -1) {
            zzn = zzabl.zza(zzye);
            zzye.zzp(zzn);
        }
        return i2 + zzn;
    }

    public static int zzy(int i) {
        if (i >= 0) {
            return zzE(i);
        }
        return 10;
    }

    public static int zzz(zzaah zzaah) {
        int zza2 = zzaah.zza();
        return zzE(zza2) + zza2;
    }

    public final void zzI() {
        if (zzb() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzJ(String str, zzacn zzacn) throws IOException {
        zza.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzacn);
        byte[] bytes = str.getBytes(zzaac.zza);
        try {
            int length = bytes.length;
            zzs(length);
            zza(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzzc(e);
        } catch (zzzc e2) {
            throw e2;
        }
    }

    public abstract void zzN() throws IOException;

    public abstract void zzO(byte b) throws IOException;

    public abstract void zzP(int i, boolean z) throws IOException;

    public abstract void zzQ(int i, zzyu zzyu) throws IOException;

    public abstract void zza(byte[] bArr, int i, int i2) throws IOException;

    public abstract int zzb();

    public abstract void zzh(int i, int i2) throws IOException;

    public abstract void zzi(int i) throws IOException;

    public abstract void zzj(int i, long j) throws IOException;

    public abstract void zzk(long j) throws IOException;

    public abstract void zzl(int i, int i2) throws IOException;

    public abstract void zzm(int i) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zzn(int i, zzaaz zzaaz, zzabl zzabl) throws IOException;

    public abstract void zzo(int i, String str) throws IOException;

    public abstract void zzq(int i, int i2) throws IOException;

    public abstract void zzr(int i, int i2) throws IOException;

    public abstract void zzs(int i) throws IOException;

    public abstract void zzt(int i, long j) throws IOException;

    public abstract void zzu(long j) throws IOException;
}
