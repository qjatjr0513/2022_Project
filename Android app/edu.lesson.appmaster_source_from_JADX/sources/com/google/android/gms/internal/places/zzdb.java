package com.google.android.gms.internal.places;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class zzdb<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzfk;
    private final int zzlp;
    /* access modifiers changed from: private */
    public List<zzdk> zzlq;
    /* access modifiers changed from: private */
    public Map<K, V> zzlr;
    private volatile zzdm zzls;
    /* access modifiers changed from: private */
    public Map<K, V> zzlt;
    private volatile zzdg zzlu;

    static <FieldDescriptorType extends zzax<FieldDescriptorType>> zzdb<FieldDescriptorType, Object> zzal(int i) {
        return new zzde(i);
    }

    private zzdb(int i) {
        this.zzlp = i;
        this.zzlq = Collections.emptyList();
        this.zzlr = Collections.emptyMap();
        this.zzlt = Collections.emptyMap();
    }

    public void zzab() {
        Map<K, V> map;
        Map<K, V> map2;
        if (!this.zzfk) {
            if (this.zzlr.isEmpty()) {
                map = Collections.emptyMap();
            } else {
                map = Collections.unmodifiableMap(this.zzlr);
            }
            this.zzlr = map;
            if (this.zzlt.isEmpty()) {
                map2 = Collections.emptyMap();
            } else {
                map2 = Collections.unmodifiableMap(this.zzlt);
            }
            this.zzlt = map2;
            this.zzfk = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzfk;
    }

    public final int zzcu() {
        return this.zzlq.size();
    }

    public final Map.Entry<K, V> zzam(int i) {
        return this.zzlq.get(i);
    }

    public final Iterable<Map.Entry<K, V>> zzcv() {
        if (this.zzlr.isEmpty()) {
            return zzdf.zzdf();
        }
        return this.zzlr.entrySet();
    }

    public int size() {
        return this.zzlq.size() + this.zzlr.size();
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zzb(comparable) >= 0 || this.zzlr.containsKey(comparable);
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zzb = zzb(comparable);
        if (zzb >= 0) {
            return this.zzlq.get(zzb).getValue();
        }
        return this.zzlr.get(comparable);
    }

    /* renamed from: zzb */
    public final V put(K k, V v) {
        zzcx();
        int zzb = zzb(k);
        if (zzb >= 0) {
            return this.zzlq.get(zzb).setValue(v);
        }
        zzcx();
        if (this.zzlq.isEmpty() && !(this.zzlq instanceof ArrayList)) {
            this.zzlq = new ArrayList(this.zzlp);
        }
        int i = -(zzb + 1);
        if (i >= this.zzlp) {
            return zzcy().put(k, v);
        }
        int size = this.zzlq.size();
        int i2 = this.zzlp;
        if (size == i2) {
            zzdk remove = this.zzlq.remove(i2 - 1);
            zzcy().put((Comparable) remove.getKey(), remove.getValue());
        }
        this.zzlq.add(i, new zzdk(this, k, v));
        return null;
    }

    public void clear() {
        zzcx();
        if (!this.zzlq.isEmpty()) {
            this.zzlq.clear();
        }
        if (!this.zzlr.isEmpty()) {
            this.zzlr.clear();
        }
    }

    public V remove(Object obj) {
        zzcx();
        Comparable comparable = (Comparable) obj;
        int zzb = zzb(comparable);
        if (zzb >= 0) {
            return zzan(zzb);
        }
        if (this.zzlr.isEmpty()) {
            return null;
        }
        return this.zzlr.remove(comparable);
    }

    /* access modifiers changed from: private */
    public final V zzan(int i) {
        zzcx();
        V value = this.zzlq.remove(i).getValue();
        if (!this.zzlr.isEmpty()) {
            Iterator it = zzcy().entrySet().iterator();
            this.zzlq.add(new zzdk(this, (Map.Entry) it.next()));
            it.remove();
        }
        return value;
    }

    private final int zzb(K k) {
        int size = this.zzlq.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo((Comparable) this.zzlq.get(size).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) / 2;
            int compareTo2 = k.compareTo((Comparable) this.zzlq.get(i2).getKey());
            if (compareTo2 < 0) {
                size = i2 - 1;
            } else if (compareTo2 <= 0) {
                return i2;
            } else {
                i = i2 + 1;
            }
        }
        return -(i + 1);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        if (this.zzls == null) {
            this.zzls = new zzdm(this, (zzde) null);
        }
        return this.zzls;
    }

    /* access modifiers changed from: package-private */
    public final Set<Map.Entry<K, V>> zzcw() {
        if (this.zzlu == null) {
            this.zzlu = new zzdg(this, (zzde) null);
        }
        return this.zzlu;
    }

    /* access modifiers changed from: private */
    public final void zzcx() {
        if (this.zzfk) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzcy() {
        zzcx();
        if (this.zzlr.isEmpty() && !(this.zzlr instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zzlr = treeMap;
            this.zzlt = treeMap.descendingMap();
        }
        return (SortedMap) this.zzlr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdb)) {
            return super.equals(obj);
        }
        zzdb zzdb = (zzdb) obj;
        int size = size();
        if (size != zzdb.size()) {
            return false;
        }
        int zzcu = zzcu();
        if (zzcu != zzdb.zzcu()) {
            return entrySet().equals(zzdb.entrySet());
        }
        for (int i = 0; i < zzcu; i++) {
            if (!zzam(i).equals(zzdb.zzam(i))) {
                return false;
            }
        }
        if (zzcu != size) {
            return this.zzlr.equals(zzdb.zzlr);
        }
        return true;
    }

    public int hashCode() {
        int zzcu = zzcu();
        int i = 0;
        for (int i2 = 0; i2 < zzcu; i2++) {
            i += this.zzlq.get(i2).hashCode();
        }
        if (this.zzlr.size() > 0) {
            return i + this.zzlr.hashCode();
        }
        return i;
    }

    /* synthetic */ zzdb(int i, zzde zzde) {
        this(i);
    }
}
