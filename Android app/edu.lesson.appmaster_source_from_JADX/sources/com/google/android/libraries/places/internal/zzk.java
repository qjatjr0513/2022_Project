package com.google.android.libraries.places.internal;

import android.os.SystemClock;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzk {
    private static final zza zza = new zze();
    private static final zzk zzb = new zzk(SystemClock.elapsedRealtime());

    private zzk() {
        SystemClock.elapsedRealtime();
    }

    private zzk(long j) {
    }

    public static zzk zza() {
        return new zzk(SystemClock.elapsedRealtime());
    }
}
