package com.google.android.gms.internal.places;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzcv {
    private static final zzcv zzll = new zzcv();
    private final zzcz zzlm = new zzbx();
    private final ConcurrentMap<Class<?>, zzda<?>> zzln = new ConcurrentHashMap();

    public static zzcv zzcq() {
        return zzll;
    }

    public final <T> zzda<T> zzf(Class<T> cls) {
        zzbd.zzb(cls, "messageType");
        zzda<T> zzda = (zzda) this.zzln.get(cls);
        if (zzda != null) {
            return zzda;
        }
        zzda<T> zze = this.zzlm.zze(cls);
        zzbd.zzb(cls, "messageType");
        zzbd.zzb(zze, "schema");
        zzda<T> putIfAbsent = this.zzln.putIfAbsent(cls, zze);
        if (putIfAbsent != null) {
            return putIfAbsent;
        }
        return zze;
    }

    public final <T> zzda<T> zzq(T t) {
        return zzf(t.getClass());
    }

    private zzcv() {
    }
}
