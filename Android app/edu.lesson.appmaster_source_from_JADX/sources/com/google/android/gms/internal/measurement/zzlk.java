package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
final class zzlk {
    private static final zzlk zza = new zzlk();
    private final zzlo zzb = new zzku();
    private final ConcurrentMap<Class<?>, zzln<?>> zzc = new ConcurrentHashMap();

    private zzlk() {
    }

    public static zzlk zza() {
        return zza;
    }

    public final <T> zzln<T> zzb(Class<T> cls) {
        zzkf.zzf(cls, "messageType");
        zzln<T> zzln = (zzln) this.zzc.get(cls);
        if (zzln == null) {
            zzln = this.zzb.zza(cls);
            zzkf.zzf(cls, "messageType");
            zzkf.zzf(zzln, "schema");
            zzln<T> putIfAbsent = this.zzc.putIfAbsent(cls, zzln);
            return putIfAbsent == null ? zzln : putIfAbsent;
        }
    }
}
