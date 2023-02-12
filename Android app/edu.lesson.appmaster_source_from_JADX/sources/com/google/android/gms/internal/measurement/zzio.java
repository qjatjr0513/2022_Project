package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
final class zzio extends zzir {
    final /* synthetic */ zzix zza;
    private int zzb = 0;
    private final int zzc;

    zzio(zzix zzix) {
        this.zza = zzix;
        this.zzc = zzix.zzd();
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
