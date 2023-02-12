package com.google.android.gms.internal.p010firebaseauthapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaal */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzaal extends zzaan {
    private static final Class<?> zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzaal() {
        super((zzaak) null);
    }

    /* synthetic */ zzaal(zzaak zzaak) {
        super((zzaak) null);
    }

    private static <L> List<L> zzf(Object obj, long j, int i) {
        List<L> list;
        List<L> list2 = (List) zzacj.zzf(obj, j);
        if (list2.isEmpty()) {
            if (list2 instanceof zzaaj) {
                list = new zzaai(i);
            } else if (!(list2 instanceof zzabg) || !(list2 instanceof zzaab)) {
                list = new ArrayList<>(i);
            } else {
                list = ((zzaab) list2).zzd(i);
            }
            zzacj.zzs(obj, j, list);
            return list;
        } else if (zza.isAssignableFrom(list2.getClass())) {
            ArrayList arrayList = new ArrayList(list2.size() + i);
            arrayList.addAll(list2);
            zzacj.zzs(obj, j, arrayList);
            return arrayList;
        } else if (list2 instanceof zzace) {
            zzaai zzaai = new zzaai(list2.size() + i);
            zzaai.addAll(zzaai.size(), (zzace) list2);
            zzacj.zzs(obj, j, zzaai);
            return zzaai;
        } else if (!(list2 instanceof zzabg) || !(list2 instanceof zzaab)) {
            return list2;
        } else {
            zzaab zzaab = (zzaab) list2;
            if (zzaab.zzc()) {
                return list2;
            }
            zzaab zzd = zzaab.zzd(list2.size() + i);
            zzacj.zzs(obj, j, zzd);
            return zzd;
        }
    }

    /* access modifiers changed from: package-private */
    public final <L> List<L> zza(Object obj, long j) {
        return zzf(obj, j, 10);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, long j) {
        Object obj2;
        List list = (List) zzacj.zzf(obj, j);
        if (list instanceof zzaaj) {
            obj2 = ((zzaaj) list).zze();
        } else if (!zza.isAssignableFrom(list.getClass())) {
            if (!(list instanceof zzabg) || !(list instanceof zzaab)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                zzaab zzaab = (zzaab) list;
                if (zzaab.zzc()) {
                    zzaab.zzb();
                    return;
                }
                return;
            }
        } else {
            return;
        }
        zzacj.zzs(obj, j, obj2);
    }

    /* access modifiers changed from: package-private */
    public final <E> void zzc(Object obj, Object obj2, long j) {
        List list = (List) zzacj.zzf(obj2, j);
        List zzf = zzf(obj, j, list.size());
        int size = zzf.size();
        int size2 = list.size();
        if (size > 0 && size2 > 0) {
            zzf.addAll(list);
        }
        if (size > 0) {
            list = zzf;
        }
        zzacj.zzs(obj, j, list);
    }
}
