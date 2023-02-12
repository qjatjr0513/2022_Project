package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzkk implements Runnable {
    final /* synthetic */ zzkt zza;
    final /* synthetic */ zzks zzb;

    zzkk(zzks zzks, zzkt zzkt) {
        this.zzb = zzks;
        this.zza = zzkt;
    }

    public final void run() {
        zzks.zzy(this.zzb, this.zza);
        this.zzb.zzQ();
    }
}
