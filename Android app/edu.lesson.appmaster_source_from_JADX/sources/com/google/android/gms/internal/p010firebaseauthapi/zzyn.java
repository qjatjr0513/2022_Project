package com.google.android.gms.internal.p010firebaseauthapi;

import java.util.Comparator;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyn */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzyn implements Comparator<zzyu> {
    zzyn() {
    }

    public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        zzyu zzyu = (zzyu) obj;
        zzyu zzyu2 = (zzyu) obj2;
        zzyl zzyl = new zzyl(zzyu);
        zzyl zzyl2 = new zzyl(zzyu2);
        while (zzyl.hasNext() && zzyl2.hasNext()) {
            int zza = zzym.zza(zzyl.zza() & 255, zzyl2.zza() & 255);
            if (zza != 0) {
                return zza;
            }
        }
        return zzym.zza(zzyu.zzd(), zzyu2.zzd());
    }
}
