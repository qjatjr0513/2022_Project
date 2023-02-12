package com.google.android.libraries.places.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzacj extends zzacn {
    private static final Class<?> zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzacj() {
        super((zzacm) null);
    }

    /* synthetic */ zzacj(zzaci zzaci) {
        super((zzacm) null);
    }

    /* access modifiers changed from: package-private */
    public final void zza(Object obj, long j) {
        Object obj2;
        List list = (List) zzael.zzf(obj, j);
        if (list instanceof zzach) {
            obj2 = ((zzach) list).zzd();
        } else if (!zza.isAssignableFrom(list.getClass())) {
            if (!(list instanceof zzadg) || !(list instanceof zzabz)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                zzabz zzabz = (zzabz) list;
                if (zzabz.zzc()) {
                    zzabz.zzb();
                    return;
                }
                return;
            }
        } else {
            return;
        }
        zzael.zzs(obj, j, obj2);
    }

    /* access modifiers changed from: package-private */
    public final <E> void zzb(Object obj, Object obj2, long j) {
        List list = (List) zzael.zzf(obj2, j);
        int size = list.size();
        List list2 = (List) zzael.zzf(obj, j);
        if (list2.isEmpty()) {
            if (list2 instanceof zzach) {
                list2 = new zzacg(size);
            } else if (!(list2 instanceof zzadg) || !(list2 instanceof zzabz)) {
                list2 = new ArrayList(size);
            } else {
                list2 = ((zzabz) list2).zzf(size);
            }
            zzael.zzs(obj, j, list2);
        } else if (zza.isAssignableFrom(list2.getClass())) {
            ArrayList arrayList = new ArrayList(list2.size() + size);
            arrayList.addAll(list2);
            zzael.zzs(obj, j, arrayList);
            list2 = arrayList;
        } else if (list2 instanceof zzaeg) {
            zzacg zzacg = new zzacg(list2.size() + size);
            zzacg.addAll(zzacg.size(), (zzaeg) list2);
            zzael.zzs(obj, j, zzacg);
            list2 = zzacg;
        } else if ((list2 instanceof zzadg) && (list2 instanceof zzabz)) {
            zzabz zzabz = (zzabz) list2;
            if (!zzabz.zzc()) {
                list2 = zzabz.zzf(list2.size() + size);
                zzael.zzs(obj, j, list2);
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
        zzael.zzs(obj, j, list);
    }
}
