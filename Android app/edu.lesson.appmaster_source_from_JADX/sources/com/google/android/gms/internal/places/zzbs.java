package com.google.android.gms.internal.places;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzbs extends zzq<String> implements zzbr, RandomAccess {
    private static final zzbs zzjy;
    private static final zzbr zzjz;
    private final List<Object> zzka;

    public zzbs() {
        this(10);
    }

    public zzbs(int i) {
        this((ArrayList<Object>) new ArrayList(i));
    }

    private zzbs(ArrayList<Object> arrayList) {
        this.zzka = arrayList;
    }

    public final int size() {
        return this.zzka.size();
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzac();
        if (collection instanceof zzbr) {
            collection = ((zzbr) collection).zzby();
        }
        boolean addAll = this.zzka.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final void clear() {
        zzac();
        this.zzka.clear();
        this.modCount++;
    }

    public final Object zzae(int i) {
        return this.zzka.get(i);
    }

    private static String zzf(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzw) {
            return ((zzw) obj).zzad();
        }
        return zzbd.zzf((byte[]) obj);
    }

    public final List<?> zzby() {
        return Collections.unmodifiableList(this.zzka);
    }

    public final zzbr zzbz() {
        if (zzaa()) {
            return new zzdt(this);
        }
        return this;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        zzac();
        return zzf(this.zzka.set(i, (String) obj));
    }

    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    public final /* synthetic */ Object remove(int i) {
        zzac();
        Object remove = this.zzka.remove(i);
        this.modCount++;
        return zzf(remove);
    }

    public final /* bridge */ /* synthetic */ boolean zzaa() {
        return super.zzaa();
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzac();
        this.zzka.add(i, (String) obj);
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final /* synthetic */ zzbh zzh(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzka);
            return new zzbs((ArrayList<Object>) arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        Object obj = this.zzka.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzw) {
            zzw zzw = (zzw) obj;
            String zzad = zzw.zzad();
            if (zzw.zzae()) {
                this.zzka.set(i, zzad);
            }
            return zzad;
        }
        byte[] bArr = (byte[]) obj;
        String zzf = zzbd.zzf(bArr);
        if (zzbd.zze(bArr)) {
            this.zzka.set(i, zzf);
        }
        return zzf;
    }

    static {
        zzbs zzbs = new zzbs();
        zzjy = zzbs;
        zzbs.zzab();
        zzjz = zzbs;
    }
}
