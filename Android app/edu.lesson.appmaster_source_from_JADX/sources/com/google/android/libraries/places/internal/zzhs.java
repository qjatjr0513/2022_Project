package com.google.android.libraries.places.internal;

import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzhs<C> extends zzhv<C> {
    private final Map<zzhi<?>, zzhu<?, ? super C>> zza;
    private final Map<zzhi<?>, zzht<?, ? super C>> zzb;
    private final zzhu<Object, ? super C> zzc;
    private final zzht<Object, ? super C> zzd;

    /* synthetic */ zzhs(zzhr zzhr, zzho zzho) {
        HashMap hashMap = new HashMap();
        this.zza = hashMap;
        HashMap hashMap2 = new HashMap();
        this.zzb = hashMap2;
        hashMap.putAll(zzhr.zzc);
        hashMap2.putAll(zzhr.zzd);
        this.zzc = zzhr.zze;
        this.zzd = zzhr.zzf;
    }
}
