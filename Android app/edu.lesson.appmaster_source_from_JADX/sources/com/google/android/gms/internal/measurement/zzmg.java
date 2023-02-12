package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
public final class zzmg extends AbstractList<String> implements RandomAccess, zzkm {
    /* access modifiers changed from: private */
    public final zzkm zza;

    public zzmg(zzkm zzkm) {
        this.zza = zzkm;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        return ((zzkl) this.zza).get(i);
    }

    public final Iterator<String> iterator() {
        return new zzmf(this);
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzme(this, i);
    }

    public final int size() {
        return this.zza.size();
    }

    public final zzkm zze() {
        return this;
    }

    public final Object zzf(int i) {
        return this.zza.zzf(i);
    }

    public final List<?> zzh() {
        return this.zza.zzh();
    }

    public final void zzi(zzix zzix) {
        throw new UnsupportedOperationException();
    }
}
