package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final /* synthetic */ class zzha implements Runnable {
    public final /* synthetic */ zzia zza;
    public final /* synthetic */ Bundle zzb;

    public /* synthetic */ zzha(zzia zzia, Bundle bundle) {
        this.zza = zzia;
        this.zzb = bundle;
    }

    public final void run() {
        this.zza.zzC(this.zzb);
    }
}
