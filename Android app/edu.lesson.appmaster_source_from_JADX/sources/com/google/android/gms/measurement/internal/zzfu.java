package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzfu implements Runnable {
    final /* synthetic */ zzgy zza;
    final /* synthetic */ zzfv zzb;

    zzfu(zzfv zzfv, zzgy zzgy) {
        this.zzb = zzfv;
        this.zza = zzgy;
    }

    public final void run() {
        zzfv.zzA(this.zzb, this.zza);
        this.zzb.zzH(this.zza.zzg);
    }
}
