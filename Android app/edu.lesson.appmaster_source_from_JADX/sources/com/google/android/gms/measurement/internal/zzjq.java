package com.google.android.gms.measurement.internal;

import android.content.Intent;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final /* synthetic */ class zzjq implements Runnable {
    public final /* synthetic */ zzju zza;
    public final /* synthetic */ int zzb;
    public final /* synthetic */ zzel zzc;
    public final /* synthetic */ Intent zzd;

    public /* synthetic */ zzjq(zzju zzju, int i, zzel zzel, Intent intent) {
        this.zza = zzju;
        this.zzb = i;
        this.zzc = zzel;
        this.zzd = intent;
    }

    public final void run() {
        this.zza.zzc(this.zzb, this.zzc, this.zzd);
    }
}
