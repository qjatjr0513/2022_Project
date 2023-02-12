package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzn;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final /* synthetic */ class zzfg implements Callable {
    public final /* synthetic */ zzfm zza;
    public final /* synthetic */ String zzb;

    public /* synthetic */ zzfg(zzfm zzfm, String str) {
        this.zza = zzfm;
        this.zzb = str;
    }

    public final Object call() {
        return new zzn("internal.remoteConfig", new zzfl(this.zza, this.zzb));
    }
}
