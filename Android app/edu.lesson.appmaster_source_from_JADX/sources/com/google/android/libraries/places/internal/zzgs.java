package com.google.android.libraries.places.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.AbstractMap;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzgs extends zzge<Map.Entry> {
    final /* synthetic */ zzgt zza;

    zzgs(zzgt zzgt) {
        this.zza = zzgt;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzfm.zza(i, this.zza.zzc, FirebaseAnalytics.Param.INDEX);
        int i2 = i + i;
        Object obj = this.zza.zzb[i2];
        obj.getClass();
        Object obj2 = this.zza.zzb[i2 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    public final int size() {
        return this.zza.zzc;
    }

    public final boolean zzf() {
        return true;
    }
}
