package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzes implements Runnable {
    final /* synthetic */ boolean zza;
    final /* synthetic */ zzet zzb;

    zzes(zzet zzet, boolean z) {
        this.zzb = zzet;
        this.zza = z;
    }

    public final void run() {
        this.zzb.zzb.zzI(this.zza);
    }
}
