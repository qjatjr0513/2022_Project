package com.google.android.gms.internal.places;

import java.util.Iterator;
import java.util.Map;

final class zzcg implements zzcd {
    zzcg() {
    }

    public final Map<?, ?> zzg(Object obj) {
        return (zzce) obj;
    }

    public final zzcb<?, ?> zzl(Object obj) {
        throw new NoSuchMethodError();
    }

    public final Map<?, ?> zzh(Object obj) {
        return (zzce) obj;
    }

    public final boolean zzi(Object obj) {
        return !((zzce) obj).isMutable();
    }

    public final Object zzj(Object obj) {
        ((zzce) obj).zzab();
        return obj;
    }

    public final Object zzk(Object obj) {
        return zzce.zzcd().zzce();
    }

    public final Object zzc(Object obj, Object obj2) {
        zzce zzce = (zzce) obj;
        zzce zzce2 = (zzce) obj2;
        if (!zzce2.isEmpty()) {
            if (!zzce.isMutable()) {
                zzce = zzce.zzce();
            }
            zzce.zzb(zzce2);
        }
        return zzce;
    }

    public final int zzc(int i, Object obj, Object obj2) {
        zzce zzce = (zzce) obj;
        if (zzce.isEmpty()) {
            return 0;
        }
        Iterator it = zzce.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}
