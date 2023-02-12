package com.google.android.libraries.places.internal;

import android.os.Build;
import android.os.SystemClock;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzd {
    private static final boolean zza;

    static {
        boolean z = false;
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                SystemClock.elapsedRealtimeNanos();
                z = true;
            }
        } catch (Throwable th) {
        }
        zza = z;
    }

    static long zza() {
        if (zza) {
            return SystemClock.elapsedRealtimeNanos();
        }
        return SystemClock.elapsedRealtime() * 1000000;
    }
}
