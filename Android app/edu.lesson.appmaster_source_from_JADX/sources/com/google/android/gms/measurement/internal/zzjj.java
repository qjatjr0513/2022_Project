package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzjj implements Runnable {
    final /* synthetic */ ComponentName zza;
    final /* synthetic */ zzjn zzb;

    zzjj(zzjn zzjn, ComponentName componentName) {
        this.zzb = zzjn;
        this.zza = componentName;
    }

    public final void run() {
        zzjo.zzo(this.zzb.zza, this.zza);
    }
}
