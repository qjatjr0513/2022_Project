package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzjs implements Runnable {
    final /* synthetic */ zzks zza;
    final /* synthetic */ Runnable zzb;

    zzjs(zzju zzju, zzks zzks, Runnable runnable) {
        this.zza = zzks;
        this.zzb = runnable;
    }

    public final void run() {
        this.zza.zzA();
        this.zza.zzz(this.zzb);
        this.zza.zzV();
    }
}
