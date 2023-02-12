package com.google.android.gms.internal.places;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

class zzdm extends AbstractSet<Map.Entry<K, V>> {
    private final /* synthetic */ zzdb zzma;

    private zzdm(zzdb zzdb) {
        this.zzma = zzdb;
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        return new zzdj(this.zzma, (zzde) null);
    }

    public int size() {
        return this.zzma.size();
    }

    public boolean contains(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.zzma.get(entry.getKey());
        Object value = entry.getValue();
        if (obj2 != value) {
            return obj2 != null && obj2.equals(value);
        }
        return true;
    }

    public boolean remove(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zzma.remove(entry.getKey());
        return true;
    }

    public void clear() {
        this.zzma.clear();
    }

    public /* synthetic */ boolean add(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zzma.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    /* synthetic */ zzdm(zzdb zzdb, zzde zzde) {
        this(zzdb);
    }
}
