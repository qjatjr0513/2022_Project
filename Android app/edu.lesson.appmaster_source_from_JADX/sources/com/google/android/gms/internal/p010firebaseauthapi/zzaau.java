package com.google.android.gms.internal.p010firebaseauthapi;

import java.util.Iterator;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaau */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzaau {
    zzaau() {
    }

    public static final int zza(int i, Object obj, Object obj2) {
        zzaat zzaat = (zzaat) obj;
        zzaas zzaas = (zzaas) obj2;
        if (zzaat.isEmpty()) {
            return 0;
        }
        Iterator it = zzaat.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw null;
    }

    public static final boolean zzb(Object obj) {
        return !((zzaat) obj).zze();
    }

    public static final Object zzc(Object obj, Object obj2) {
        zzaat zzaat = (zzaat) obj;
        zzaat zzaat2 = (zzaat) obj2;
        if (!zzaat2.isEmpty()) {
            if (!zzaat.zze()) {
                zzaat = zzaat.zzb();
            }
            zzaat.zzd(zzaat2);
        }
        return zzaat;
    }
}
