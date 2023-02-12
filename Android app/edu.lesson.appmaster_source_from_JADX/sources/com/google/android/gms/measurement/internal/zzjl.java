package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzjl implements Runnable {
    final /* synthetic */ zzjn zza;

    zzjl(zzjn zzjn) {
        this.zza = zzjn;
    }

    public final void run() {
        zzjo zzjo = this.zza.zza;
        Context zzau = zzjo.zzs.zzau();
        this.zza.zza.zzs.zzaw();
        zzjo.zzo(zzjo, new ComponentName(zzau, "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
