package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzij implements Runnable {
    final /* synthetic */ zzih zza;
    final /* synthetic */ zzih zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ boolean zzd;
    final /* synthetic */ zzio zze;

    zzij(zzio zzio, zzih zzih, zzih zzih2, long j, boolean z) {
        this.zze = zzio;
        this.zza = zzih;
        this.zzb = zzih2;
        this.zzc = j;
        this.zzd = z;
    }

    public final void run() {
        this.zze.zzB(this.zza, this.zzb, this.zzc, this.zzd, (Bundle) null);
    }
}
