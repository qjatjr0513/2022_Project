package com.google.android.gms.internal.p010firebaseauthapi;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzd */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzzd extends zzza {
    private final OutputStream zzf;

    zzzd(OutputStream outputStream, int i) {
        super(i);
        this.zzf = outputStream;
    }

    private final void zzL() throws IOException {
        this.zzf.write(this.zza, 0, this.zzc);
        this.zzc = 0;
    }

    private final void zzM(int i) throws IOException {
        if (this.zzb - this.zzc < i) {
            zzL();
        }
    }

    public final void zzN() throws IOException {
        if (this.zzc > 0) {
            zzL();
        }
    }

    public final void zzO(byte b) throws IOException {
        if (this.zzc == this.zzb) {
            zzL();
        }
        zzc(b);
    }

    public final void zzP(int i, boolean z) throws IOException {
        zzM(11);
        zzf(i << 3);
        zzc(z ? (byte) 1 : 0);
    }

    public final void zzQ(int i, zzyu zzyu) throws IOException {
        zzs((i << 3) | 2);
        zzs(zzyu.zzd());
        zzyu.zzj(this);
    }

    public final void zza(byte[] bArr, int i, int i2) throws IOException {
        zzp(bArr, 0, i2);
    }

    public final void zzh(int i, int i2) throws IOException {
        zzM(14);
        zzf((i << 3) | 5);
        zzd(i2);
    }

    public final void zzi(int i) throws IOException {
        zzM(4);
        zzd(i);
    }

    public final void zzj(int i, long j) throws IOException {
        zzM(18);
        zzf((i << 3) | 1);
        zze(j);
    }

    public final void zzk(long j) throws IOException {
        zzM(8);
        zze(j);
    }

    public final void zzl(int i, int i2) throws IOException {
        zzM(20);
        zzf(i << 3);
        if (i2 >= 0) {
            zzf(i2);
        } else {
            zzg((long) i2);
        }
    }

    public final void zzm(int i) throws IOException {
        if (i >= 0) {
            zzs(i);
        } else {
            zzu((long) i);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzn(int i, zzaaz zzaaz, zzabl zzabl) throws IOException {
        zzs((i << 3) | 2);
        zzye zzye = (zzye) zzaaz;
        int zzn = zzye.zzn();
        if (zzn == -1) {
            zzn = zzabl.zza(zzye);
            zzye.zzp(zzn);
        }
        zzs(zzn);
        zzabl.zzn(zzaaz, this.zze);
    }

    public final void zzo(int i, String str) throws IOException {
        zzs((i << 3) | 2);
        zzv(str);
    }

    public final void zzp(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.zzb;
        int i4 = this.zzc;
        int i5 = i3 - i4;
        if (i5 >= i2) {
            System.arraycopy(bArr, 0, this.zza, i4, i2);
            this.zzc += i2;
            this.zzd += i2;
            return;
        }
        System.arraycopy(bArr, 0, this.zza, i4, i5);
        int i6 = i2 - i5;
        this.zzc = this.zzb;
        this.zzd += i5;
        zzL();
        if (i6 <= this.zzb) {
            System.arraycopy(bArr, i5, this.zza, 0, i6);
            this.zzc = i6;
        } else {
            this.zzf.write(bArr, i5, i6);
        }
        this.zzd += i6;
    }

    public final void zzq(int i, int i2) throws IOException {
        zzs((i << 3) | i2);
    }

    public final void zzr(int i, int i2) throws IOException {
        zzM(20);
        zzf(i << 3);
        zzf(i2);
    }

    public final void zzs(int i) throws IOException {
        zzM(5);
        zzf(i);
    }

    public final void zzt(int i, long j) throws IOException {
        zzM(20);
        zzf(i << 3);
        zzg(j);
    }

    public final void zzu(long j) throws IOException {
        zzM(10);
        zzg(j);
    }

    public final void zzv(String str) throws IOException {
        int i;
        int i2;
        try {
            int length = str.length() * 3;
            int zzE = zzE(length);
            int i3 = zzE + length;
            int i4 = this.zzb;
            if (i3 > i4) {
                byte[] bArr = new byte[length];
                int zzb = zzaco.zzb(str, bArr, 0, length);
                zzs(zzb);
                zzp(bArr, 0, zzb);
                return;
            }
            if (i3 > i4 - this.zzc) {
                zzL();
            }
            int zzE2 = zzE(str.length());
            i = this.zzc;
            if (zzE2 == zzE) {
                int i5 = i + zzE2;
                this.zzc = i5;
                int zzb2 = zzaco.zzb(str, this.zza, i5, this.zzb - i5);
                this.zzc = i;
                i2 = (zzb2 - i) - zzE2;
                zzf(i2);
                this.zzc = zzb2;
            } else {
                i2 = zzaco.zzc(str);
                zzf(i2);
                this.zzc = zzaco.zzb(str, this.zza, this.zzc, i2);
            }
            this.zzd += i2;
        } catch (zzacn e) {
            this.zzd -= this.zzc - i;
            this.zzc = i;
            throw e;
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new zzzc(e2);
        } catch (zzacn e3) {
            zzJ(str, e3);
        }
    }
}
