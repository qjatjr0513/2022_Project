package com.google.android.libraries.places.internal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzadh {
    private static final zzadh zza = new zzadh();
    private final zzadl zzb = new zzacr();
    private final ConcurrentMap<Class<?>, zzadk<?>> zzc = new ConcurrentHashMap();

    private zzadh() {
    }

    public static zzadh zza() {
        return zza;
    }

    public final <T> zzadk<T> zzb(Class<T> cls) {
        zzaca.zzf(cls, "messageType");
        zzadk<T> zzadk = (zzadk) this.zzc.get(cls);
        if (zzadk == null) {
            zzadk = this.zzb.zza(cls);
            zzaca.zzf(cls, "messageType");
            zzaca.zzf(zzadk, "schema");
            zzadk<T> putIfAbsent = this.zzc.putIfAbsent(cls, zzadk);
            return putIfAbsent == null ? zzadk : putIfAbsent;
        }
    }
}
