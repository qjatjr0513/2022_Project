package com.google.android.gms.internal.places;

import java.util.ArrayList;
import java.util.List;

final class zzcy<E> extends zzq<E> {
    private static final zzcy<Object> zzlo;
    private final List<E> zzka;

    public static <E> zzcy<E> zzct() {
        return zzlo;
    }

    zzcy() {
        this(new ArrayList(10));
    }

    private zzcy(List<E> list) {
        this.zzka = list;
    }

    public final void add(int i, E e) {
        zzac();
        this.zzka.add(i, e);
        this.modCount++;
    }

    public final E get(int i) {
        return this.zzka.get(i);
    }

    public final E remove(int i) {
        zzac();
        E remove = this.zzka.remove(i);
        this.modCount++;
        return remove;
    }

    public final E set(int i, E e) {
        zzac();
        E e2 = this.zzka.set(i, e);
        this.modCount++;
        return e2;
    }

    public final int size() {
        return this.zzka.size();
    }

    public final /* synthetic */ zzbh zzh(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzka);
            return new zzcy(arrayList);
        }
        throw new IllegalArgumentException();
    }

    static {
        zzcy<Object> zzcy = new zzcy<>(new ArrayList(0));
        zzlo = zzcy;
        zzcy.zzab();
    }
}
