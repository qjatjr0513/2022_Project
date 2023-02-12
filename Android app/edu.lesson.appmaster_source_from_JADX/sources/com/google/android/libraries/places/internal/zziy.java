package com.google.android.libraries.places.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zziy implements Iterator {
    final /* synthetic */ zziz zza;
    private int zzb = 0;

    zziy(zziz zziz) {
        this.zza = zziz;
    }

    public final boolean hasNext() {
        int i = this.zzb;
        zziz zziz = this.zza;
        return i < zziz.zza() - zziz.zzb();
    }

    public final Object next() {
        int i = this.zzb;
        zziz zziz = this.zza;
        if (i < zziz.zza() - zziz.zzb()) {
            Object obj = this.zza.zzb.zzb[this.zza.zzb() + i];
            this.zzb = i + 1;
            return obj;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
