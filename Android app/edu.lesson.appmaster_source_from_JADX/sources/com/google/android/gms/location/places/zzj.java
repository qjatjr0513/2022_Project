package com.google.android.gms.location.places;

import com.google.android.gms.location.places.internal.zzak;
import java.util.Comparator;

final class zzj implements Comparator<zzak> {
    zzj() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        return -Float.compare(((zzak) obj).getLikelihood(), ((zzak) obj2).getLikelihood());
    }
}
