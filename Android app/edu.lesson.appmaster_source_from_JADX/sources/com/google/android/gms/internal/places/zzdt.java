package com.google.android.gms.internal.places;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zzdt extends AbstractList<String> implements zzbr, RandomAccess {
    /* access modifiers changed from: private */
    public final zzbr zzmj;

    public zzdt(zzbr zzbr) {
        this.zzmj = zzbr;
    }

    public final Object zzae(int i) {
        return this.zzmj.zzae(i);
    }

    public final int size() {
        return this.zzmj.size();
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzdw(this, i);
    }

    public final Iterator<String> iterator() {
        return new zzdv(this);
    }

    public final List<?> zzby() {
        return this.zzmj.zzby();
    }

    public final zzbr zzbz() {
        return this;
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.zzmj.get(i);
    }
}
