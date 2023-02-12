package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzjk implements Runnable {
    final /* synthetic */ zzeb zza;
    final /* synthetic */ zzjn zzb;

    zzjk(zzjn zzjn, zzeb zzeb) {
        this.zzb = zzjn;
        this.zza = zzeb;
    }

    public final void run() {
        synchronized (this.zzb) {
            this.zzb.zzb = false;
            if (!this.zzb.zza.zzL()) {
                this.zzb.zza.zzs.zzay().zzc().zza("Connected to remote service");
                this.zzb.zza.zzJ(this.zza);
            }
        }
    }
}
