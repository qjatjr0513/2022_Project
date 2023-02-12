package com.google.android.libraries.places.internal;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class zzib {
    private static String zza = "com.google.android.libraries.places.internal.zzig";
    private static String zzb = "com.google.common.flogger.backend.google.GooglePlatform";
    private static String zzc = "com.google.common.flogger.backend.system.DefaultPlatform";
    /* access modifiers changed from: private */
    public static final String[] zzd = {"com.google.android.libraries.places.internal.zzig", "com.google.common.flogger.backend.google.GooglePlatform", "com.google.common.flogger.backend.system.DefaultPlatform"};

    public static int zza() {
        return zzjg.zza();
    }

    public static long zzb() {
        return zzhz.zza.zzc();
    }

    public static zzhl zzd(String className) {
        return zzhz.zza.zze(className);
    }

    public static zzhn zzf() {
        return zzi().zza();
    }

    public static zzia zzg() {
        return zzhz.zza.zzh();
    }

    public static zzip zzi() {
        return zzhz.zza.zzj();
    }

    public static zzjc zzk() {
        return zzi().zzc();
    }

    public static String zzl() {
        return zzhz.zza.zzm();
    }

    public static boolean zzn(String loggerName, Level level, boolean isEnabled) {
        zzi().zzd(loggerName, level, isEnabled);
        return false;
    }

    /* access modifiers changed from: protected */
    public long zzc() {
        return TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
    }

    /* access modifiers changed from: protected */
    public abstract zzhl zze(String str);

    /* access modifiers changed from: protected */
    public abstract zzia zzh();

    /* access modifiers changed from: protected */
    public zzip zzj() {
        return zzip.zze();
    }

    /* access modifiers changed from: protected */
    public abstract String zzm();
}
