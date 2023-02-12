package com.google.android.libraries.places.internal;

import java.util.logging.Level;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzil implements zzif {
    private final String zza;
    private final Level zzb;

    public zzil() {
        this("", true, false, Level.ALL, false);
    }

    private zzil(String str, boolean z, boolean z2, Level level, boolean z3) {
        this.zza = "";
        this.zzb = level;
    }

    public final zzhl zza(String str) {
        return new zzio(this.zza, str, true, false, this.zzb, (zzin) null);
    }

    public final zzil zzb(boolean z) {
        return new zzil(this.zza, true, false, Level.OFF, false);
    }
}
