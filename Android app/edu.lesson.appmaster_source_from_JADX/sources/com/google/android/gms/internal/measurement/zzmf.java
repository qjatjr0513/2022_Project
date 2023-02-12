package com.google.android.gms.internal.measurement;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
final class zzmf implements Iterator<String> {
    final Iterator<String> zza;
    final /* synthetic */ zzmg zzb;

    zzmf(zzmg zzmg) {
        this.zzb = zzmg;
        this.zza = zzmg.zza.iterator();
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        return this.zza.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
