package com.google.android.libraries.places.internal;

import android.os.Build;
import android.util.Log;
import dalvik.system.VMStack;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzig extends zzib {
    /* access modifiers changed from: private */
    public static final boolean zza = zza.zza();
    /* access modifiers changed from: private */
    public static final boolean zzb;
    private static final zzia zzc = new zzia() {
        public zzhg zza(Class<?> cls, int i) {
            return zzhg.zza;
        }

        public String zzb(Class loggerClass) {
            StackTraceElement zza;
            if (zzig.zza) {
                try {
                    if (loggerClass.equals(zzig.zzp())) {
                        return VMStack.getStackClass2().getName();
                    }
                } catch (Throwable th) {
                }
            }
            if (!zzig.zzb || (zza = zzjd.zza(loggerClass, 1)) == null) {
                return null;
            }
            return zza.getClassName();
        }
    };

    /* compiled from: com.google.android.libraries.places:places@@2.5.0 */
    final class zza {
        zza() {
        }

        static boolean zza() {
            return zzig.zzt();
        }
    }

    static {
        boolean z = true;
        if (Build.FINGERPRINT != null && !"robolectric".equals(Build.FINGERPRINT)) {
            z = false;
        }
        zzb = z;
        Log.class.getName();
    }

    static Class<?> zzp() {
        return VMStack.getStackClass2();
    }

    static String zzq() {
        try {
            return VMStack.getStackClass2().getName();
        } catch (Throwable th) {
            return null;
        }
    }

    static boolean zzt() {
        try {
            Class.forName("dalvik.system.VMStack").getMethod("getStackClass2", new Class[0]);
            return zza.class.getName().equals(zzq());
        } catch (Throwable th) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public zzhl zze(String className) {
        return zzij.zzb(className);
    }

    /* access modifiers changed from: protected */
    public zzia zzh() {
        return zzc;
    }

    /* access modifiers changed from: protected */
    public zzip zzj() {
        return zzik.zzb();
    }

    /* access modifiers changed from: protected */
    public String zzm() {
        return "platform: Android";
    }
}
