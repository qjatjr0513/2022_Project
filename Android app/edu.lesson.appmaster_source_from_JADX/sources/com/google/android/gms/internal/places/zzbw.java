package com.google.android.gms.internal.places;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class zzbw extends zzbu {
    private static final Class<?> zzkd = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzbw() {
        super();
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, long j) {
        Object obj2;
        List list = (List) zzdy.zzp(obj, j);
        if (list instanceof zzbr) {
            obj2 = ((zzbr) list).zzbz();
        } else if (!zzkd.isAssignableFrom(list.getClass())) {
            if (!(list instanceof zzcw) || !(list instanceof zzbh)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                zzbh zzbh = (zzbh) list;
                if (zzbh.zzaa()) {
                    zzbh.zzab();
                    return;
                }
                return;
            }
        } else {
            return;
        }
        zzdy.zzb(obj, j, obj2);
    }

    /* access modifiers changed from: package-private */
    public final <E> void zzb(Object obj, Object obj2, long j) {
        List zzd = zzd(obj2, j);
        int size = zzd.size();
        List zzd2 = zzd(obj, j);
        if (zzd2.isEmpty()) {
            if (zzd2 instanceof zzbr) {
                zzd2 = new zzbs(size);
            } else if (!(zzd2 instanceof zzcw) || !(zzd2 instanceof zzbh)) {
                zzd2 = new ArrayList(size);
            } else {
                zzd2 = ((zzbh) zzd2).zzh(size);
            }
            zzdy.zzb(obj, j, (Object) zzd2);
        } else if (zzkd.isAssignableFrom(zzd2.getClass())) {
            ArrayList arrayList = new ArrayList(zzd2.size() + size);
            arrayList.addAll(zzd2);
            zzdy.zzb(obj, j, (Object) arrayList);
            zzd2 = arrayList;
        } else if (zzd2 instanceof zzdt) {
            zzbs zzbs = new zzbs(zzd2.size() + size);
            zzbs.addAll((zzdt) zzd2);
            zzdy.zzb(obj, j, (Object) zzbs);
            zzd2 = zzbs;
        } else if ((zzd2 instanceof zzcw) && (zzd2 instanceof zzbh)) {
            zzbh zzbh = (zzbh) zzd2;
            if (!zzbh.zzaa()) {
                zzd2 = zzbh.zzh(zzd2.size() + size);
                zzdy.zzb(obj, j, (Object) zzd2);
            }
        }
        int size2 = zzd2.size();
        int size3 = zzd.size();
        if (size2 > 0 && size3 > 0) {
            zzd2.addAll(zzd);
        }
        if (size2 > 0) {
            zzd = zzd2;
        }
        zzdy.zzb(obj, j, (Object) zzd);
    }

    private static <E> List<E> zzd(Object obj, long j) {
        return (List) zzdy.zzp(obj, j);
    }
}
