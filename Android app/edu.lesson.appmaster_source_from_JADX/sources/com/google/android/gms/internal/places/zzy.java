package com.google.android.gms.internal.places;

import java.util.Comparator;

final class zzy implements Comparator<zzw> {
    zzy() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzw zzw = (zzw) obj;
        zzw zzw2 = (zzw) obj2;
        zzab zzab = (zzab) zzw.iterator();
        zzab zzab2 = (zzab) zzw2.iterator();
        while (zzab.hasNext() && zzab2.hasNext()) {
            int compare = Integer.compare(zzw.zzb(zzab.nextByte()), zzw.zzb(zzab2.nextByte()));
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzw.size(), zzw2.size());
    }
}
