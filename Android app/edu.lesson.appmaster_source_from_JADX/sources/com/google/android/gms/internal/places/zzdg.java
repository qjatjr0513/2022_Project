package com.google.android.gms.internal.places;

import java.util.Iterator;
import java.util.Map;

final class zzdg extends zzdm {
    private final /* synthetic */ zzdb zzma;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzdg(zzdb zzdb) {
        super(zzdb, (zzde) null);
        this.zzma = zzdb;
    }

    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzdd(this.zzma, (zzde) null);
    }

    /* synthetic */ zzdg(zzdb zzdb, zzde zzde) {
        this(zzdb);
    }
}
