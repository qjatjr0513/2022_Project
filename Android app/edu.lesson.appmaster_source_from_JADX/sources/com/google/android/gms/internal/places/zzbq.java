package com.google.android.gms.internal.places;

import java.util.Iterator;
import java.util.Map;

final class zzbq<K> implements Iterator<Map.Entry<K, Object>> {
    private Iterator<Map.Entry<K, Object>> zzjx;

    public zzbq(Iterator<Map.Entry<K, Object>> it) {
        this.zzjx = it;
    }

    public final boolean hasNext() {
        return this.zzjx.hasNext();
    }

    public final void remove() {
        this.zzjx.remove();
    }

    public final /* synthetic */ Object next() {
        Map.Entry next = this.zzjx.next();
        if (next.getValue() instanceof zzbl) {
            return new zzbn(next);
        }
        return next;
    }
}
