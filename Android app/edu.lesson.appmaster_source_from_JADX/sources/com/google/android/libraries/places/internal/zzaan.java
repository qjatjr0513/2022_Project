package com.google.android.libraries.places.internal;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzaan extends zzaaq {
    final /* synthetic */ zzaax zza;
    private int zzb = 0;
    private final int zzc;

    zzaan(zzaax zzaax) {
        this.zza = zzaax;
        this.zzc = zzaax.zzd();
    }

    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    public final byte zza() {
        int i = this.zzb;
        if (i < this.zzc) {
            this.zzb = i + 1;
            return this.zza.zzb(i);
        }
        throw new NoSuchElementException();
    }
}
