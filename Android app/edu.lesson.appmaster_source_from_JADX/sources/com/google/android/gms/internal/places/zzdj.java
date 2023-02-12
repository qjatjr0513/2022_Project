package com.google.android.gms.internal.places;

import java.util.Iterator;
import java.util.Map;

final class zzdj implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzlz;
    private final /* synthetic */ zzdb zzma;
    private boolean zzmd;

    private zzdj(zzdb zzdb) {
        this.zzma = zzdb;
        this.pos = -1;
    }

    public final boolean hasNext() {
        if (this.pos + 1 < this.zzma.zzlq.size() || (!this.zzma.zzlr.isEmpty() && zzde().hasNext())) {
            return true;
        }
        return false;
    }

    public final void remove() {
        if (this.zzmd) {
            this.zzmd = false;
            this.zzma.zzcx();
            if (this.pos < this.zzma.zzlq.size()) {
                zzdb zzdb = this.zzma;
                int i = this.pos;
                this.pos = i - 1;
                Object unused = zzdb.zzan(i);
                return;
            }
            zzde().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }

    private final Iterator<Map.Entry<K, V>> zzde() {
        if (this.zzlz == null) {
            this.zzlz = this.zzma.zzlr.entrySet().iterator();
        }
        return this.zzlz;
    }

    public final /* synthetic */ Object next() {
        this.zzmd = true;
        int i = this.pos + 1;
        this.pos = i;
        if (i < this.zzma.zzlq.size()) {
            return (Map.Entry) this.zzma.zzlq.get(this.pos);
        }
        return (Map.Entry) zzde().next();
    }

    /* synthetic */ zzdj(zzdb zzdb, zzde zzde) {
        this(zzdb);
    }
}
