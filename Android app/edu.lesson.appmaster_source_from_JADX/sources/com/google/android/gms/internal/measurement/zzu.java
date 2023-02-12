package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzu extends zzai {
    private final Callable<Object> zza;

    public zzu(String str, Callable<Object> callable) {
        super("internal.appMetadata");
        this.zza = callable;
    }

    public final zzap zza(zzg zzg, List<zzap> list) {
        try {
            return zzi.zzb(this.zza.call());
        } catch (Exception e) {
            return zzap.zzf;
        }
    }
}
