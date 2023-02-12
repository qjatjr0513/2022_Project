package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
final class zzko extends zzkq {
    private static final Class<?> zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzko() {
        super((zzkn) null);
    }

    /* synthetic */ zzko(zzkn zzkn) {
        super((zzkn) null);
    }

    /* access modifiers changed from: package-private */
    public final void zza(Object obj, long j) {
        Object obj2;
        List list = (List) zzml.zzf(obj, j);
        if (list instanceof zzkm) {
            obj2 = ((zzkm) list).zze();
        } else if (!zza.isAssignableFrom(list.getClass())) {
            if (!(list instanceof zzlj) || !(list instanceof zzke)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                zzke zzke = (zzke) list;
                if (zzke.zzc()) {
                    zzke.zzb();
                    return;
                }
                return;
            }
        } else {
            return;
        }
        zzml.zzs(obj, j, obj2);
    }

    /* access modifiers changed from: package-private */
    public final <E> void zzb(Object obj, Object obj2, long j) {
        List list = (List) zzml.zzf(obj2, j);
        int size = list.size();
        List list2 = (List) zzml.zzf(obj, j);
        if (list2.isEmpty()) {
            if (list2 instanceof zzkm) {
                list2 = new zzkl(size);
            } else if (!(list2 instanceof zzlj) || !(list2 instanceof zzke)) {
                list2 = new ArrayList(size);
            } else {
                list2 = ((zzke) list2).zzd(size);
            }
            zzml.zzs(obj, j, list2);
        } else if (zza.isAssignableFrom(list2.getClass())) {
            ArrayList arrayList = new ArrayList(list2.size() + size);
            arrayList.addAll(list2);
            zzml.zzs(obj, j, arrayList);
            list2 = arrayList;
        } else if (list2 instanceof zzmg) {
            zzkl zzkl = new zzkl(list2.size() + size);
            zzkl.addAll(zzkl.size(), (zzmg) list2);
            zzml.zzs(obj, j, zzkl);
            list2 = zzkl;
        } else if ((list2 instanceof zzlj) && (list2 instanceof zzke)) {
            zzke zzke = (zzke) list2;
            if (!zzke.zzc()) {
                list2 = zzke.zzd(list2.size() + size);
                zzml.zzs(obj, j, list2);
            }
        }
        int size2 = list2.size();
        int size3 = list.size();
        if (size2 > 0 && size3 > 0) {
            list2.addAll(list);
        }
        if (size2 > 0) {
            list = list2;
        }
        zzml.zzs(obj, j, list);
    }
}
