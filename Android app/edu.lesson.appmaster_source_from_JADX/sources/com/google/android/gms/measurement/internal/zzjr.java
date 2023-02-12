package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final /* synthetic */ class zzjr implements Runnable {
    public final /* synthetic */ zzju zza;
    public final /* synthetic */ zzel zzb;
    public final /* synthetic */ JobParameters zzc;

    public /* synthetic */ zzjr(zzju zzju, zzel zzel, JobParameters jobParameters) {
        this.zza = zzju;
        this.zzb = zzel;
        this.zzc = jobParameters;
    }

    public final void run() {
        this.zza.zzd(this.zzb, this.zzc);
    }
}
