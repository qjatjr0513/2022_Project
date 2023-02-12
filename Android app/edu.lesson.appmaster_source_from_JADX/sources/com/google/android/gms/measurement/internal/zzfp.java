package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzfp implements Thread.UncaughtExceptionHandler {
    final /* synthetic */ zzfs zza;
    private final String zzb;

    public zzfp(zzfs zzfs, String str) {
        this.zza = zzfs;
        Preconditions.checkNotNull(str);
        this.zzb = str;
    }

    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.zza.zzs.zzay().zzd().zzb(this.zzb, th);
    }
}
