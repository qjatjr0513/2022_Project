package com.google.android.libraries.places.internal;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzacu {
    zzacu() {
    }

    public static final int zza(int i, Object obj, Object obj2) {
        zzact zzact = (zzact) obj;
        zzacs zzacs = (zzacs) obj2;
        if (zzact.isEmpty()) {
            return 0;
        }
        Iterator it = zzact.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw null;
    }
}
