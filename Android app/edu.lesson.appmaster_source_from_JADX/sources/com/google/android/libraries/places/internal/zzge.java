package com.google.android.libraries.places.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class zzge<E> extends zzgb<E> implements List<E>, RandomAccess {
    private static final zzhb<Object> zza = new zzgc(zzgr.zza, 0);

    zzge() {
    }

    static <E> zzge<E> zzi(Object[] objArr) {
        return zzj(objArr, objArr.length);
    }

    static <E> zzge<E> zzj(Object[] objArr, int i) {
        if (i == 0) {
            return zzgr.zza;
        }
        return new zzgr(objArr, i);
    }

    public static <E> zzge<E> zzk(Collection<? extends E> collection) {
        if (collection instanceof zzgb) {
            zzge<E> zzd = ((zzgb) collection).zzd();
            if (!zzd.zzf()) {
                return zzd;
            }
            Object[] array = zzd.toArray();
            return zzj(array, array.length);
        }
        Object[] array2 = collection.toArray();
        int length = array2.length;
        zzgn.zza(array2, length);
        return zzj(array2, length);
    }

    public static <E> zzge<E> zzl(E[] eArr) {
        if (eArr.length == 0) {
            return zzgr.zza;
        }
        Object[] objArr = (Object[]) eArr.clone();
        int length = objArr.length;
        zzgn.zza(objArr, length);
        return zzj(objArr, length);
    }

    public static <E> zzge<E> zzm() {
        return zzgr.zza;
    }

    public static <E> zzge<E> zzn(E e) {
        Object[] objArr = {e};
        zzgn.zza(objArr, 1);
        return zzj(objArr, 1);
    }

    public static <E> zzge<E> zzo(E e, E e2) {
        Object[] objArr = {e, e2};
        zzgn.zza(objArr, 2);
        return zzj(objArr, 2);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.util.Collection, java.lang.Iterable<? extends E>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <E> com.google.android.libraries.places.internal.zzge<E> zzp(java.util.Comparator<? super E> r1, java.lang.Iterable<? extends E> r2) {
        /*
            java.lang.Object[] r2 = r2.toArray()
            int r0 = r2.length
            com.google.android.libraries.places.internal.zzgn.zza(r2, r0)
            java.util.Arrays.sort(r2, r1)
            com.google.android.libraries.places.internal.zzge r1 = zzj(r2, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzge.zzp(java.util.Comparator, java.lang.Iterable):com.google.android.libraries.places.internal.zzge");
    }

    @Deprecated
    public final void add(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    public final boolean contains(@CheckForNull Object obj) {
        return indexOf(obj) >= 0;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        List list = (List) obj;
        int size = size();
        if (size != list.size()) {
            return false;
        }
        if (list instanceof RandomAccess) {
            for (int i = 0; i < size; i++) {
                if (!zzfi.zza(get(i), list.get(i))) {
                    return false;
                }
            }
            return true;
        }
        Iterator it = list.iterator();
        for (Object zza2 : this) {
            if (!it.hasNext()) {
                return false;
            }
            if (!zzfi.zza(zza2, it.next())) {
                return false;
            }
        }
        return !it.hasNext();
    }

    public final int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    public final int indexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (obj.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    public final /* bridge */ /* synthetic */ Iterator iterator() {
        return listIterator(0);
    }

    public final int lastIndexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    public final /* bridge */ /* synthetic */ ListIterator listIterator() {
        return listIterator(0);
    }

    @Deprecated
    public final E remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final E set(int i, E e) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public int zza(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i2] = get(i2);
        }
        return size;
    }

    @Deprecated
    public final zzge<E> zzd() {
        return this;
    }

    public final zzha<E> zze() {
        return listIterator(0);
    }

    /* renamed from: zzh */
    public zzge<E> subList(int i, int i2) {
        zzfm.zzg(i, i2, size());
        int i3 = i2 - i;
        if (i3 == size()) {
            return this;
        }
        if (i3 == 0) {
            return zzgr.zza;
        }
        return new zzgd(this, i, i3);
    }

    /* renamed from: zzq */
    public final zzhb<E> listIterator(int i) {
        zzfm.zzb(i, size(), FirebaseAnalytics.Param.INDEX);
        if (isEmpty()) {
            return zza;
        }
        return new zzgc(this, i);
    }
}
