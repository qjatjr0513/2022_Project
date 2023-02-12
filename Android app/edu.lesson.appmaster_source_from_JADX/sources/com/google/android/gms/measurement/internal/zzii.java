package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzii implements Runnable {
    final /* synthetic */ Bundle zza;
    final /* synthetic */ zzih zzb;
    final /* synthetic */ zzih zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ zzio zze;

    zzii(zzio zzio, Bundle bundle, zzih zzih, zzih zzih2, long j) {
        this.zze = zzio;
        this.zza = bundle;
        this.zzb = zzih;
        this.zzc = zzih2;
        this.zzd = j;
    }

    public final void run() {
        zzio.zzp(this.zze, this.zza, this.zzb, this.zzc, this.zzd);
    }
}
