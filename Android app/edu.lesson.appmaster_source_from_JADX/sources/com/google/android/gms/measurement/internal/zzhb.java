package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final /* synthetic */ class zzhb implements Runnable {
    public final /* synthetic */ zzia zza;
    public final /* synthetic */ Bundle zzb;
    public final /* synthetic */ long zzc;

    public /* synthetic */ zzhb(zzia zzia, Bundle bundle, long j) {
        this.zza = zzia;
        this.zzb = bundle;
        this.zzc = j;
    }

    public final void run() {
        this.zza.zzB(this.zzb, this.zzc);
    }
}
