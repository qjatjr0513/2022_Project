package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzt;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final /* synthetic */ class zzff implements Callable {
    public final /* synthetic */ zzfm zza;

    public /* synthetic */ zzff(zzfm zzfm) {
        this.zza = zzfm;
    }

    public final Object call() {
        return new zzt(this.zza.zzd);
    }
}
