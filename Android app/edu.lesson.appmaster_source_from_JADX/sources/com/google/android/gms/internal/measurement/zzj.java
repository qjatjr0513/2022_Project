package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzj {
    final Map<String, Callable<? extends zzai>> zza = new HashMap();

    public final void zza(String str, Callable<? extends zzai> callable) {
        this.zza.put(str, callable);
    }
}
