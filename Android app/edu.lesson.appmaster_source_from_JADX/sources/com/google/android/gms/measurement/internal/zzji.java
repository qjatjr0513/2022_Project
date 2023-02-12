package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzji implements Runnable {
    final /* synthetic */ zzeb zza;
    final /* synthetic */ zzjn zzb;

    zzji(zzjn zzjn, zzeb zzeb) {
        this.zzb = zzjn;
        this.zza = zzeb;
    }

    public final void run() {
        synchronized (this.zzb) {
            this.zzb.zzb = false;
            if (!this.zzb.zza.zzL()) {
                this.zzb.zza.zzs.zzay().zzj().zza("Connected to service");
                this.zzb.zza.zzJ(this.zza);
            }
        }
    }
}
