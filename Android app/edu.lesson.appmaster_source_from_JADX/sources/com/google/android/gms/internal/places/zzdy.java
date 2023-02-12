package com.google.android.gms.internal.places;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class zzdy {
    private static final Logger logger = Logger.getLogger(zzdy.class.getName());
    private static final Class<?> zzdw = zzp.zzz();
    private static final boolean zzer = zzdo();
    private static final Unsafe zzkr;
    private static final boolean zzmn;
    private static final boolean zzmo;
    private static final zzd zzmp;
    private static final boolean zzmq = zzdp();
    private static final long zzmr;
    private static final long zzms;
    private static final long zzmt;
    private static final long zzmu;
    private static final long zzmv;
    private static final long zzmw;
    private static final long zzmx;
    private static final long zzmy;
    private static final long zzmz;
    private static final long zzna;
    private static final long zznb;
    private static final long zznc = ((long) zzi(Object[].class));
    private static final long zznd = ((long) zzj(Object[].class));
    private static final long zzne;
    private static final int zznf;
    static final boolean zzng = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);

    private zzdy() {
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzy(Object obj, long j) {
            if (zzdy.zzng) {
                return zzdy.zzq(obj, j);
            }
            return zzdy.zzr(obj, j);
        }

        public final void zzf(Object obj, long j, byte b) {
            if (zzdy.zzng) {
                zzdy.zzb(obj, j, b);
            } else {
                zzdy.zzc(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            if (zzdy.zzng) {
                return zzdy.zzs(obj, j);
            }
            return zzdy.zzt(obj, j);
        }

        public final void zzb(Object obj, long j, boolean z) {
            if (zzdy.zzng) {
                zzdy.zzc(obj, j, z);
            } else {
                zzdy.zzd(obj, j, z);
            }
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final void zzb(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final void zzb(Object obj, long j, double d) {
            zzb(obj, j, Double.doubleToLongBits(d));
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzy(Object obj, long j) {
            if (zzdy.zzng) {
                return zzdy.zzq(obj, j);
            }
            return zzdy.zzr(obj, j);
        }

        public final void zzf(Object obj, long j, byte b) {
            if (zzdy.zzng) {
                zzdy.zzb(obj, j, b);
            } else {
                zzdy.zzc(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            if (zzdy.zzng) {
                return zzdy.zzs(obj, j);
            }
            return zzdy.zzt(obj, j);
        }

        public final void zzb(Object obj, long j, boolean z) {
            if (zzdy.zzng) {
                zzdy.zzc(obj, j, z);
            } else {
                zzdy.zzd(obj, j, z);
            }
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final void zzb(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final void zzb(Object obj, long j, double d) {
            zzb(obj, j, Double.doubleToLongBits(d));
        }
    }

    static final class zze extends zzd {
        zze(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzy(Object obj, long j) {
            return this.zznh.getByte(obj, j);
        }

        public final void zzf(Object obj, long j, byte b) {
            this.zznh.putByte(obj, j, b);
        }

        public final boolean zzm(Object obj, long j) {
            return this.zznh.getBoolean(obj, j);
        }

        public final void zzb(Object obj, long j, boolean z) {
            this.zznh.putBoolean(obj, j, z);
        }

        public final float zzn(Object obj, long j) {
            return this.zznh.getFloat(obj, j);
        }

        public final void zzb(Object obj, long j, float f) {
            this.zznh.putFloat(obj, j, f);
        }

        public final double zzo(Object obj, long j) {
            return this.zznh.getDouble(obj, j);
        }

        public final void zzb(Object obj, long j, double d) {
            this.zznh.putDouble(obj, j, d);
        }
    }

    static boolean zzdl() {
        return zzer;
    }

    static abstract class zzd {
        Unsafe zznh;

        zzd(Unsafe unsafe) {
            this.zznh = unsafe;
        }

        public abstract void zzb(Object obj, long j, double d);

        public abstract void zzb(Object obj, long j, float f);

        public abstract void zzb(Object obj, long j, boolean z);

        public abstract void zzf(Object obj, long j, byte b);

        public abstract boolean zzm(Object obj, long j);

        public abstract float zzn(Object obj, long j);

        public abstract double zzo(Object obj, long j);

        public abstract byte zzy(Object obj, long j);

        public final int zzk(Object obj, long j) {
            return this.zznh.getInt(obj, j);
        }

        public final void zzb(Object obj, long j, int i) {
            this.zznh.putInt(obj, j, i);
        }

        public final long zzl(Object obj, long j) {
            return this.zznh.getLong(obj, j);
        }

        public final void zzb(Object obj, long j, long j2) {
            this.zznh.putLong(obj, j, j2);
        }
    }

    static boolean zzdm() {
        return zzmq;
    }

    static <T> T zzh(Class<T> cls) {
        try {
            return zzkr.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static int zzi(Class<?> cls) {
        if (zzer) {
            return zzmp.zznh.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzj(Class<?> cls) {
        if (zzer) {
            return zzmp.zznh.arrayIndexScale(cls);
        }
        return -1;
    }

    static int zzk(Object obj, long j) {
        return zzmp.zzk(obj, j);
    }

    static void zzb(Object obj, long j, int i) {
        zzmp.zzb(obj, j, i);
    }

    static long zzl(Object obj, long j) {
        return zzmp.zzl(obj, j);
    }

    static void zzb(Object obj, long j, long j2) {
        zzmp.zzb(obj, j, j2);
    }

    static boolean zzm(Object obj, long j) {
        return zzmp.zzm(obj, j);
    }

    static void zzb(Object obj, long j, boolean z) {
        zzmp.zzb(obj, j, z);
    }

    static float zzn(Object obj, long j) {
        return zzmp.zzn(obj, j);
    }

    static void zzb(Object obj, long j, float f) {
        zzmp.zzb(obj, j, f);
    }

    static double zzo(Object obj, long j) {
        return zzmp.zzo(obj, j);
    }

    static void zzb(Object obj, long j, double d) {
        zzmp.zzb(obj, j, d);
    }

    static Object zzp(Object obj, long j) {
        return zzmp.zznh.getObject(obj, j);
    }

    static void zzb(Object obj, long j, Object obj2) {
        zzmp.zznh.putObject(obj, j, obj2);
    }

    static byte zzb(byte[] bArr, long j) {
        return zzmp.zzy(bArr, zzmr + j);
    }

    static void zzb(byte[] bArr, long j, byte b) {
        zzmp.zzf(bArr, zzmr + j, b);
    }

    static Unsafe zzdn() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzdx());
        } catch (Throwable th) {
            return null;
        }
    }

    private static boolean zzdo() {
        Unsafe unsafe = zzkr;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("arrayIndexScale", new Class[]{Class.class});
            cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
            cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
            if (zzp.zzy()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putByte", new Class[]{Object.class, Long.TYPE, Byte.TYPE});
            cls.getMethod("getBoolean", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putBoolean", new Class[]{Object.class, Long.TYPE, Boolean.TYPE});
            cls.getMethod("getFloat", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putFloat", new Class[]{Object.class, Long.TYPE, Float.TYPE});
            cls.getMethod("getDouble", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putDouble", new Class[]{Object.class, Long.TYPE, Double.TYPE});
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", new StringBuilder(String.valueOf(valueOf).length() + 71).append("platform method missing - proto runtime falling back to safer methods: ").append(valueOf).toString());
            return false;
        }
    }

    private static boolean zzdp() {
        Unsafe unsafe = zzkr;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzdq() == null) {
                return false;
            }
            if (zzp.zzy()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Long.TYPE});
            cls.getMethod("putByte", new Class[]{Long.TYPE, Byte.TYPE});
            cls.getMethod("getInt", new Class[]{Long.TYPE});
            cls.getMethod("putInt", new Class[]{Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Long.TYPE});
            cls.getMethod("putLong", new Class[]{Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Long.TYPE, Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE});
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", new StringBuilder(String.valueOf(valueOf).length() + 71).append("platform method missing - proto runtime falling back to safer methods: ").append(valueOf).toString());
            return false;
        }
    }

    private static boolean zzk(Class<?> cls) {
        Class<byte[]> cls2 = byte[].class;
        if (!zzp.zzy()) {
            return false;
        }
        try {
            Class<?> cls3 = zzdw;
            cls3.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls3.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls3.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls3.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls3.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls3.getMethod("peekByte", new Class[]{cls});
            cls3.getMethod("pokeByteArray", new Class[]{cls, cls2, Integer.TYPE, Integer.TYPE});
            cls3.getMethod("peekByteArray", new Class[]{cls, cls2, Integer.TYPE, Integer.TYPE});
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private static Field zzdq() {
        Field zzc2;
        if (zzp.zzy() && (zzc2 = zzc(Buffer.class, "effectiveDirectAddress")) != null) {
            return zzc2;
        }
        Field zzc3 = zzc(Buffer.class, "address");
        if (zzc3 == null || zzc3.getType() != Long.TYPE) {
            return null;
        }
        return zzc3;
    }

    private static Field zzc(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable th) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static byte zzq(Object obj, long j) {
        return (byte) (zzk(obj, -4 & j) >>> ((int) (((~j) & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static byte zzr(Object obj, long j) {
        return (byte) (zzk(obj, -4 & j) >>> ((int) ((j & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int zzk = zzk(obj, j2);
        int i = ((~((int) j)) & 3) << 3;
        zzb(obj, j2, ((255 & b) << i) | (zzk & (~(255 << i))));
    }

    /* access modifiers changed from: private */
    public static void zzc(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int i = (((int) j) & 3) << 3;
        zzb(obj, j2, ((255 & b) << i) | (zzk(obj, j2) & (~(255 << i))));
    }

    /* access modifiers changed from: private */
    public static boolean zzs(Object obj, long j) {
        return zzq(obj, j) != 0;
    }

    /* access modifiers changed from: private */
    public static boolean zzt(Object obj, long j) {
        return zzr(obj, j) != 0;
    }

    /* access modifiers changed from: private */
    public static void zzc(Object obj, long j, boolean z) {
        zzb(obj, j, z ? (byte) 1 : 0);
    }

    /* access modifiers changed from: private */
    public static void zzd(Object obj, long j, boolean z) {
        zzc(obj, j, z ? (byte) 1 : 0);
    }

    static {
        long j;
        Class<double[]> cls = double[].class;
        Class<float[]> cls2 = float[].class;
        Class<long[]> cls3 = long[].class;
        Class<int[]> cls4 = int[].class;
        Class<boolean[]> cls5 = boolean[].class;
        Unsafe zzdn = zzdn();
        zzkr = zzdn;
        boolean zzk = zzk(Long.TYPE);
        zzmn = zzk;
        boolean zzk2 = zzk(Integer.TYPE);
        zzmo = zzk2;
        zzd zzd2 = null;
        if (zzdn != null) {
            if (!zzp.zzy()) {
                zzd2 = new zze(zzdn);
            } else if (zzk) {
                zzd2 = new zzb(zzdn);
            } else if (zzk2) {
                zzd2 = new zzc(zzdn);
            }
        }
        zzmp = zzd2;
        long zzi = (long) zzi(byte[].class);
        zzmr = zzi;
        zzms = (long) zzi(cls5);
        zzmt = (long) zzj(cls5);
        zzmu = (long) zzi(cls4);
        zzmv = (long) zzj(cls4);
        zzmw = (long) zzi(cls3);
        zzmx = (long) zzj(cls3);
        zzmy = (long) zzi(cls2);
        zzmz = (long) zzj(cls2);
        zzna = (long) zzi(cls);
        zznb = (long) zzj(cls);
        Field zzdq = zzdq();
        if (zzdq == null || zzd2 == null) {
            j = -1;
        } else {
            j = zzd2.zznh.objectFieldOffset(zzdq);
        }
        zzne = j;
        zznf = (int) (7 & zzi);
    }
}
