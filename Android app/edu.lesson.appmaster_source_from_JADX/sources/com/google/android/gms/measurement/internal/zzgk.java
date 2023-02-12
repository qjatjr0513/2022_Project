package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzgk implements Callable<List<zzkx>> {
    final /* synthetic */ String zza;
    final /* synthetic */ zzgn zzb;

    zzgk(zzgn zzgn, String str) {
        this.zzb = zzgn;
        this.zza = str;
    }

    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        this.zzb.zza.zzA();
        return this.zzb.zza.zzi().zzu(this.zza);
    }
}
