package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzkn implements Callable<String> {
    final /* synthetic */ zzp zza;
    final /* synthetic */ zzks zzb;

    zzkn(zzks zzks, zzp zzp) {
        this.zzb = zzks;
        this.zza = zzp;
    }

    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        if (this.zzb.zzh((String) Preconditions.checkNotNull(this.zza.zza)).zzk() && zzag.zzb(this.zza.zzv).zzk()) {
            return this.zzb.zzd(this.zza).zzu();
        }
        this.zzb.zzay().zzj().zza("Analytics storage consent denied. Returning null app instance id");
        return null;
    }
}
