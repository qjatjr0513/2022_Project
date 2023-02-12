package com.google.android.libraries.places.internal;

import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzik extends zzip {
    private static final zzik zza = new zzik(zzip.zze());
    private final AtomicReference<zzip> zzb;

    zzik(zzip zzip) {
        this.zzb = new AtomicReference<>(zzip);
    }

    public static final zzik zzb() {
        return zza;
    }

    public final zzhn zza() {
        return this.zzb.get().zza();
    }

    public final zzjc zzc() {
        return this.zzb.get().zzc();
    }

    public final boolean zzd(String str, Level level, boolean z) {
        this.zzb.get().zzd(str, level, z);
        return false;
    }
}
