package com.google.android.gms.internal.places;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzaj extends zzt {
    private static final Logger logger = Logger.getLogger(zzaj.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzer = zzdy.zzdl();
    zzam zzes;

    public static zzaj zzc(byte[] bArr) {
        return new zzb(bArr, 0, bArr.length);
    }

    public abstract int zzak();

    public abstract void zzb(int i, long j) throws IOException;

    public abstract void zzb(int i, zzck zzck) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zzb(int i, zzck zzck, zzda zzda) throws IOException;

    public abstract void zzb(int i, zzw zzw) throws IOException;

    public abstract void zzb(int i, String str) throws IOException;

    public abstract void zzb(zzw zzw) throws IOException;

    public abstract void zzc(int i, int i2) throws IOException;

    public abstract void zzc(int i, zzw zzw) throws IOException;

    public abstract void zzc(int i, boolean z) throws IOException;

    public abstract void zzc(long j) throws IOException;

    public abstract void zzc(zzck zzck) throws IOException;

    public abstract void zzd(byte b) throws IOException;

    public abstract void zzd(int i, int i2) throws IOException;

    public abstract void zzd(int i, long j) throws IOException;

    public abstract void zze(int i, int i2) throws IOException;

    public abstract void zze(long j) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zze(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzg(int i, int i2) throws IOException;

    public abstract void zzj(String str) throws IOException;

    public abstract void zzn(int i) throws IOException;

    public abstract void zzo(int i) throws IOException;

    public abstract void zzq(int i) throws IOException;

    public static class zzc extends IOException {
        zzc() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzc(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        zzc(java.lang.String r3, java.lang.Throwable r4) {
            /*
                r2 = this;
                java.lang.String r0 = "CodedOutputStream was writing to a flat byte array and ran out of space.: "
                java.lang.String r0 = java.lang.String.valueOf(r0)
                java.lang.String r3 = java.lang.String.valueOf(r3)
                int r1 = r3.length()
                if (r1 == 0) goto L_0x0015
                java.lang.String r3 = r0.concat(r3)
                goto L_0x001a
            L_0x0015:
                java.lang.String r3 = new java.lang.String
                r3.<init>(r0)
            L_0x001a:
                r2.<init>(r3, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.places.zzaj.zzc.<init>(java.lang.String, java.lang.Throwable):void");
        }
    }

    private zzaj() {
    }

    public final void zzf(int i, int i2) throws IOException {
        zze(i, zzy(i2));
    }

    public final void zzc(int i, long j) throws IOException {
        zzb(i, zzk(j));
    }

    public final void zzb(int i, float f) throws IOException {
        zzg(i, Float.floatToRawIntBits(f));
    }

    public final void zzb(int i, double d) throws IOException {
        zzd(i, Double.doubleToRawLongBits(d));
    }

    static class zzb extends zzaj {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zzb(byte[] bArr, int i, int i2) {
            super();
            if (bArr != null) {
                int i3 = i2 + 0;
                if ((i2 | 0 | (bArr.length - i3)) >= 0) {
                    this.buffer = bArr;
                    this.offset = 0;
                    this.position = 0;
                    this.limit = i3;
                    return;
                }
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), 0, Integer.valueOf(i2)}));
            }
            throw new NullPointerException("buffer");
        }

        public final void zzc(int i, int i2) throws IOException {
            zzo((i << 3) | i2);
        }

        public final void zzd(int i, int i2) throws IOException {
            zzc(i, 0);
            zzn(i2);
        }

        public final void zze(int i, int i2) throws IOException {
            zzc(i, 0);
            zzo(i2);
        }

        public final void zzg(int i, int i2) throws IOException {
            zzc(i, 5);
            zzq(i2);
        }

        public final void zzb(int i, long j) throws IOException {
            zzc(i, 0);
            zzc(j);
        }

        public final void zzd(int i, long j) throws IOException {
            zzc(i, 1);
            zze(j);
        }

        public final void zzc(int i, boolean z) throws IOException {
            zzc(i, 0);
            zzd(z ? (byte) 1 : 0);
        }

        public final void zzb(int i, String str) throws IOException {
            zzc(i, 2);
            zzj(str);
        }

        public final void zzb(int i, zzw zzw) throws IOException {
            zzc(i, 2);
            zzb(zzw);
        }

        public final void zzb(zzw zzw) throws IOException {
            zzo(zzw.size());
            zzw.zzb((zzt) this);
        }

        public final void zze(byte[] bArr, int i, int i2) throws IOException {
            zzo(i2);
            write(bArr, 0, i2);
        }

        /* access modifiers changed from: package-private */
        public final void zzb(int i, zzck zzck, zzda zzda) throws IOException {
            zzc(i, 2);
            zzm zzm = (zzm) zzck;
            int zzw = zzm.zzw();
            if (zzw == -1) {
                zzw = zzda.zzn(zzm);
                zzm.zze(zzw);
            }
            zzo(zzw);
            zzda.zzb(zzck, this.zzes);
        }

        public final void zzb(int i, zzck zzck) throws IOException {
            zzc(1, 3);
            zze(2, i);
            zzc(3, 2);
            zzc(zzck);
            zzc(1, 4);
        }

        public final void zzc(int i, zzw zzw) throws IOException {
            zzc(1, 3);
            zze(2, i);
            zzb(3, zzw);
            zzc(1, 4);
        }

        public final void zzc(zzck zzck) throws IOException {
            zzo(zzck.zzbh());
            zzck.zzc(this);
        }

        public final void zzd(byte b) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
            }
        }

        public final void zzn(int i) throws IOException {
            if (i >= 0) {
                zzo(i);
            } else {
                zzc((long) i);
            }
        }

        public final void zzo(int i) throws IOException {
            if (!zzaj.zzer || zzp.zzy() || zzak() < 5) {
                while ((i & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    bArr[i2] = (byte) ((i & 127) | 128);
                    i >>>= 7;
                }
                try {
                    byte[] bArr2 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    bArr2[i3] = (byte) i;
                } catch (IndexOutOfBoundsException e) {
                    throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
                }
            } else if ((i & -128) == 0) {
                byte[] bArr3 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                zzdy.zzb(bArr3, (long) i4, (byte) i);
            } else {
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                zzdy.zzb(bArr4, (long) i5, (byte) (i | 128));
                int i6 = i >>> 7;
                if ((i6 & -128) == 0) {
                    byte[] bArr5 = this.buffer;
                    int i7 = this.position;
                    this.position = i7 + 1;
                    zzdy.zzb(bArr5, (long) i7, (byte) i6);
                    return;
                }
                byte[] bArr6 = this.buffer;
                int i8 = this.position;
                this.position = i8 + 1;
                zzdy.zzb(bArr6, (long) i8, (byte) (i6 | 128));
                int i9 = i6 >>> 7;
                if ((i9 & -128) == 0) {
                    byte[] bArr7 = this.buffer;
                    int i10 = this.position;
                    this.position = i10 + 1;
                    zzdy.zzb(bArr7, (long) i10, (byte) i9);
                    return;
                }
                byte[] bArr8 = this.buffer;
                int i11 = this.position;
                this.position = i11 + 1;
                zzdy.zzb(bArr8, (long) i11, (byte) (i9 | 128));
                int i12 = i9 >>> 7;
                if ((i12 & -128) == 0) {
                    byte[] bArr9 = this.buffer;
                    int i13 = this.position;
                    this.position = i13 + 1;
                    zzdy.zzb(bArr9, (long) i13, (byte) i12);
                    return;
                }
                byte[] bArr10 = this.buffer;
                int i14 = this.position;
                this.position = i14 + 1;
                zzdy.zzb(bArr10, (long) i14, (byte) (i12 | 128));
                byte[] bArr11 = this.buffer;
                int i15 = this.position;
                this.position = i15 + 1;
                zzdy.zzb(bArr11, (long) i15, (byte) (i12 >>> 7));
            }
        }

        public final void zzq(int i) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                int i3 = i2 + 1;
                this.position = i3;
                bArr[i2] = (byte) i;
                int i4 = i3 + 1;
                this.position = i4;
                bArr[i3] = (byte) (i >> 8);
                int i5 = i4 + 1;
                this.position = i5;
                bArr[i4] = (byte) (i >> 16);
                this.position = i5 + 1;
                bArr[i5] = (byte) (i >>> 24);
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
            }
        }

        public final void zzc(long j) throws IOException {
            if (!zzaj.zzer || zzak() < 10) {
                while ((j & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i = this.position;
                    this.position = i + 1;
                    bArr[i] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                }
                try {
                    byte[] bArr2 = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    bArr2[i2] = (byte) ((int) j);
                } catch (IndexOutOfBoundsException e) {
                    throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
                }
            } else {
                while ((j & -128) != 0) {
                    byte[] bArr3 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    zzdy.zzb(bArr3, (long) i3, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                zzdy.zzb(bArr4, (long) i4, (byte) ((int) j));
            }
        }

        public final void zze(long j) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                int i2 = i + 1;
                this.position = i2;
                bArr[i] = (byte) ((int) j);
                int i3 = i2 + 1;
                this.position = i3;
                bArr[i2] = (byte) ((int) (j >> 8));
                int i4 = i3 + 1;
                this.position = i4;
                bArr[i3] = (byte) ((int) (j >> 16));
                int i5 = i4 + 1;
                this.position = i5;
                bArr[i4] = (byte) ((int) (j >> 24));
                int i6 = i5 + 1;
                this.position = i6;
                bArr[i5] = (byte) ((int) (j >> 32));
                int i7 = i6 + 1;
                this.position = i7;
                bArr[i6] = (byte) ((int) (j >> 40));
                int i8 = i7 + 1;
                this.position = i8;
                bArr[i7] = (byte) ((int) (j >> 48));
                this.position = i8 + 1;
                bArr[i8] = (byte) ((int) (j >> 56));
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
            }
        }

        private final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)}), e);
            }
        }

        public final void zzb(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final void zzj(String str) throws IOException {
            int i = this.position;
            try {
                int zzt = zzt(str.length() * 3);
                int zzt2 = zzt(str.length());
                if (zzt2 == zzt) {
                    int i2 = i + zzt2;
                    this.position = i2;
                    int zzb = zzea.zzb(str, this.buffer, i2, zzak());
                    this.position = i;
                    zzo((zzb - i) - zzt2);
                    this.position = zzb;
                    return;
                }
                zzo(zzea.zzb(str));
                this.position = zzea.zzb(str, this.buffer, this.position, zzak());
            } catch (zzee e) {
                this.position = i;
                zzb(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzc(e2);
            }
        }

        public final int zzak() {
            return this.limit - this.position;
        }
    }

    public final void zzp(int i) throws IOException {
        zzo(zzy(i));
    }

    public final void zzd(long j) throws IOException {
        zzc(zzk(j));
    }

    public final void zzd(float f) throws IOException {
        zzq(Float.floatToRawIntBits(f));
    }

    public final void zzb(double d) throws IOException {
        zze(Double.doubleToRawLongBits(d));
    }

    public final void zzc(boolean z) throws IOException {
        zzd(z ? (byte) 1 : 0);
    }

    public static int zzh(int i, int i2) {
        return zzr(i) + zzs(i2);
    }

    public static int zzi(int i, int i2) {
        return zzr(i) + zzt(i2);
    }

    public static int zzj(int i, int i2) {
        return zzr(i) + zzt(zzy(i2));
    }

    public static int zzk(int i, int i2) {
        return zzr(i) + 4;
    }

    public static int zzl(int i, int i2) {
        return zzr(i) + 4;
    }

    public static int zze(int i, long j) {
        return zzr(i) + zzg(j);
    }

    public static int zzf(int i, long j) {
        return zzr(i) + zzg(j);
    }

    public static int zzg(int i, long j) {
        return zzr(i) + zzg(zzk(j));
    }

    public static int zzh(int i, long j) {
        return zzr(i) + 8;
    }

    public static int zzi(int i, long j) {
        return zzr(i) + 8;
    }

    public static int zzc(int i, float f) {
        return zzr(i) + 4;
    }

    public static int zzc(int i, double d) {
        return zzr(i) + 8;
    }

    public static int zzd(int i, boolean z) {
        return zzr(i) + 1;
    }

    public static int zzm(int i, int i2) {
        return zzr(i) + zzs(i2);
    }

    public static int zzc(int i, String str) {
        return zzr(i) + zzk(str);
    }

    public static int zzd(int i, zzw zzw) {
        int zzr = zzr(i);
        int size = zzw.size();
        return zzr + zzt(size) + size;
    }

    public static int zzb(int i, zzbp zzbp) {
        int zzr = zzr(i);
        int zzbh = zzbp.zzbh();
        return zzr + zzt(zzbh) + zzbh;
    }

    static int zzc(int i, zzck zzck, zzda zzda) {
        return zzr(i) + zzb(zzck, zzda);
    }

    public static int zzc(int i, zzck zzck) {
        return (zzr(1) << 1) + zzi(2, i) + zzr(3) + zzd(zzck);
    }

    public static int zze(int i, zzw zzw) {
        return (zzr(1) << 1) + zzi(2, i) + zzd(3, zzw);
    }

    public static int zzc(int i, zzbp zzbp) {
        return (zzr(1) << 1) + zzi(2, i) + zzb(3, zzbp);
    }

    public static int zzr(int i) {
        return zzt(i << 3);
    }

    public static int zzs(int i) {
        if (i >= 0) {
            return zzt(i);
        }
        return 10;
    }

    public static int zzt(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        if ((i & -268435456) == 0) {
            return 4;
        }
        return 5;
    }

    public static int zzu(int i) {
        return zzt(zzy(i));
    }

    public static int zzv(int i) {
        return 4;
    }

    public static int zzw(int i) {
        return 4;
    }

    public static int zzf(long j) {
        return zzg(j);
    }

    public static int zzg(long j) {
        int i;
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if ((-34359738368L & j) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if ((-2097152 & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        if ((j & -16384) != 0) {
            return i + 1;
        }
        return i;
    }

    public static int zzh(long j) {
        return zzg(zzk(j));
    }

    public static int zzi(long j) {
        return 8;
    }

    public static int zzj(long j) {
        return 8;
    }

    public static int zze(float f) {
        return 4;
    }

    public static int zzc(double d) {
        return 8;
    }

    public static int zzd(boolean z) {
        return 1;
    }

    public static int zzx(int i) {
        return zzs(i);
    }

    public static int zzk(String str) {
        int i;
        try {
            i = zzea.zzb(str);
        } catch (zzee e) {
            i = str.getBytes(zzbd.UTF_8).length;
        }
        return zzt(i) + i;
    }

    public static int zzb(zzbp zzbp) {
        int zzbh = zzbp.zzbh();
        return zzt(zzbh) + zzbh;
    }

    public static int zzc(zzw zzw) {
        int size = zzw.size();
        return zzt(size) + size;
    }

    public static int zzd(byte[] bArr) {
        int length = bArr.length;
        return zzt(length) + length;
    }

    public static int zzd(zzck zzck) {
        int zzbh = zzck.zzbh();
        return zzt(zzbh) + zzbh;
    }

    static int zzb(zzck zzck, zzda zzda) {
        zzm zzm = (zzm) zzck;
        int zzw = zzm.zzw();
        if (zzw == -1) {
            zzw = zzda.zzn(zzm);
            zzm.zze(zzw);
        }
        return zzt(zzw) + zzw;
    }

    private static int zzy(int i) {
        return (i >> 31) ^ (i << 1);
    }

    private static long zzk(long j) {
        return (j >> 63) ^ (j << 1);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(String str, zzee zzee) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzee);
        byte[] bytes = str.getBytes(zzbd.UTF_8);
        try {
            zzo(bytes.length);
            zzb(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzc(e);
        } catch (zzc e2) {
            throw e2;
        }
    }

    @Deprecated
    static int zzd(int i, zzck zzck, zzda zzda) {
        int zzr = zzr(i) << 1;
        zzm zzm = (zzm) zzck;
        int zzw = zzm.zzw();
        if (zzw == -1) {
            zzw = zzda.zzn(zzm);
            zzm.zze(zzw);
        }
        return zzr + zzw;
    }

    @Deprecated
    public static int zze(zzck zzck) {
        return zzck.zzbh();
    }

    @Deprecated
    public static int zzz(int i) {
        return zzt(i);
    }
}
