package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzhi implements Runnable {
    final /* synthetic */ long zza;
    final /* synthetic */ zzia zzb;

    zzhi(zzia zzia, long j) {
        this.zzb = zzia;
        this.zza = j;
    }

    public final void run() {
        this.zzb.zzL(this.zza, true);
        this.zzb.zzs.zzt().zzu(new AtomicReference());
    }
}
