package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzht implements Runnable {
    final /* synthetic */ Boolean zza;
    final /* synthetic */ zzia zzb;

    zzht(zzia zzia, Boolean bool) {
        this.zzb = zzia;
        this.zza = bool;
    }

    public final void run() {
        this.zzb.zzac(this.zza, true);
    }
}
