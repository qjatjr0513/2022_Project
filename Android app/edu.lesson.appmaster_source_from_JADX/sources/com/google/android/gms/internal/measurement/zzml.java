package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
final class zzml {
    static final long zza = ((long) zzz(byte[].class));
    static final boolean zzb;
    private static final Unsafe zzc;
    private static final Class<?> zzd = zzij.zza();
    private static final boolean zze;
    private static final boolean zzf;
    private static final zzmk zzg;
    private static final boolean zzh;
    private static final boolean zzi;

    static {
        boolean z;
        boolean z2;
        zzmk zzmk;
        Class<double[]> cls = double[].class;
        Class<float[]> cls2 = float[].class;
        Class<long[]> cls3 = long[].class;
        Class<int[]> cls4 = int[].class;
        Class<boolean[]> cls5 = boolean[].class;
        Unsafe zzg2 = zzg();
        zzc = zzg2;
        boolean zzv = zzv(Long.TYPE);
        zze = zzv;
        boolean zzv2 = zzv(Integer.TYPE);
        zzf = zzv2;
        zzmk zzmk2 = null;
        if (zzg2 != null) {
            if (zzv) {
                zzmk2 = new zzmj(zzg2);
            } else if (zzv2) {
                zzmk2 = new zzmi(zzg2);
            }
        }
        zzg = zzmk2;
        boolean z3 = true;
        if (zzmk2 == null) {
            z = false;
        } else {
            Unsafe unsafe = zzmk2.zza;
            if (unsafe == null) {
                z = false;
            } else {
                try {
                    Class<?> cls6 = unsafe.getClass();
                    cls6.getMethod("objectFieldOffset", new Class[]{Field.class});
                    cls6.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
                    z = zzB() != null;
                } catch (Throwable th) {
                    Logger.getLogger(zzml.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(th.toString()));
                    z = false;
                }
            }
        }
        zzh = z;
        zzmk zzmk3 = zzg;
        if (zzmk3 == null) {
            z2 = false;
        } else {
            Unsafe unsafe2 = zzmk3.zza;
            if (unsafe2 == null) {
                z2 = false;
            } else {
                try {
                    Class<?> cls7 = unsafe2.getClass();
                    cls7.getMethod("objectFieldOffset", new Class[]{Field.class});
                    cls7.getMethod("arrayBaseOffset", new Class[]{Class.class});
                    cls7.getMethod("arrayIndexScale", new Class[]{Class.class});
                    cls7.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
                    cls7.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
                    cls7.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
                    cls7.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
                    cls7.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
                    cls7.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
                    z2 = true;
                } catch (Throwable th2) {
                    Logger.getLogger(zzml.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(th2.toString()));
                    z2 = false;
                }
            }
        }
        zzi = z2;
        zzz(cls5);
        zzA(cls5);
        zzz(cls4);
        zzA(cls4);
        zzz(cls3);
        zzA(cls3);
        zzz(cls2);
        zzA(cls2);
        zzz(cls);
        zzA(cls);
        zzz(Object[].class);
        zzA(Object[].class);
        Field zzB = zzB();
        if (!(zzB == null || (zzmk = zzg) == null)) {
            zzmk.zzl(zzB);
        }
        if (ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN) {
            z3 = false;
        }
        zzb = z3;
    }

    private zzml() {
    }

    private static int zzA(Class<?> cls) {
        if (zzi) {
            return zzg.zzi(cls);
        }
        return -1;
    }

    private static Field zzB() {
        int i = zzij.zza;
        Field zzC = zzC(Buffer.class, "effectiveDirectAddress");
        if (zzC != null) {
            return zzC;
        }
        Field zzC2 = zzC(Buffer.class, "address");
        if (zzC2 == null || zzC2.getType() != Long.TYPE) {
            return null;
        }
        return zzC2;
    }

    private static Field zzC(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable th) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static void zzD(Object obj, long j, byte b) {
        long j2 = -4 & j;
        zzmk zzmk = zzg;
        int zzj = zzmk.zzj(obj, j2);
        int i = ((~((int) j)) & 3) << 3;
        zzmk.zzn(obj, j2, ((255 & b) << i) | (zzj & (~(255 << i))));
    }

    /* access modifiers changed from: private */
    public static void zzE(Object obj, long j, byte b) {
        long j2 = -4 & j;
        zzmk zzmk = zzg;
        int i = (((int) j) & 3) << 3;
        zzmk.zzn(obj, j2, ((255 & b) << i) | (zzmk.zzj(obj, j2) & (~(255 << i))));
    }

    static double zza(Object obj, long j) {
        return zzg.zza(obj, j);
    }

    static float zzb(Object obj, long j) {
        return zzg.zzb(obj, j);
    }

    static int zzc(Object obj, long j) {
        return zzg.zzj(obj, j);
    }

    static long zzd(Object obj, long j) {
        return zzg.zzk(obj, j);
    }

    static <T> T zze(Class<T> cls) {
        try {
            return zzc.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    static Object zzf(Object obj, long j) {
        return zzg.zzm(obj, j);
    }

    static Unsafe zzg() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzmh());
        } catch (Throwable th) {
            return null;
        }
    }

    static void zzm(Object obj, long j, boolean z) {
        zzg.zzc(obj, j, z);
    }

    static void zzn(byte[] bArr, long j, byte b) {
        zzg.zzd(bArr, zza + j, b);
    }

    static void zzo(Object obj, long j, double d) {
        zzg.zze(obj, j, d);
    }

    static void zzp(Object obj, long j, float f) {
        zzg.zzf(obj, j, f);
    }

    static void zzq(Object obj, long j, int i) {
        zzg.zzn(obj, j, i);
    }

    static void zzr(Object obj, long j, long j2) {
        zzg.zzo(obj, j, j2);
    }

    static void zzs(Object obj, long j, Object obj2) {
        zzg.zzp(obj, j, obj2);
    }

    static /* synthetic */ boolean zzt(Object obj, long j) {
        return ((byte) ((zzg.zzj(obj, -4 & j) >>> ((int) (((~j) & 3) << 3))) & 255)) != 0;
    }

    static /* synthetic */ boolean zzu(Object obj, long j) {
        return ((byte) ((zzg.zzj(obj, -4 & j) >>> ((int) ((j & 3) << 3))) & 255)) != 0;
    }

    static boolean zzv(Class<?> cls) {
        Class<byte[]> cls2 = byte[].class;
        int i = zzij.zza;
        try {
            Class<?> cls3 = zzd;
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

    static boolean zzw(Object obj, long j) {
        return zzg.zzg(obj, j);
    }

    static boolean zzx() {
        return zzi;
    }

    static boolean zzy() {
        return zzh;
    }

    private static int zzz(Class<?> cls) {
        if (zzi) {
            return zzg.zzh(cls);
        }
        return -1;
    }
}
