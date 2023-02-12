package com.google.android.gms.internal.places;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzdd implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzlz;
    private final /* synthetic */ zzdb zzma;

    private zzdd(zzdb zzdb) {
        this.zzma = zzdb;
        this.pos = zzdb.zzlq.size();
    }

    public final boolean hasNext() {
        int i = this.pos;
        return (i > 0 && i <= this.zzma.zzlq.size()) || zzde().hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator<Map.Entry<K, V>> zzde() {
        if (this.zzlz == null) {
            this.zzlz = this.zzma.zzlt.entrySet().iterator();
        }
        return this.zzlz;
    }

    public final /* synthetic */ Object next() {
        if (zzde().hasNext()) {
            return (Map.Entry) zzde().next();
        }
        List zzc = this.zzma.zzlq;
        int i = this.pos - 1;
        this.pos = i;
        return (Map.Entry) zzc.get(i);
    }

    /* synthetic */ zzdd(zzdb zzdb, zzde zzde) {
        this(zzdb);
    }
}
