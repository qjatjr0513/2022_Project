package com.google.android.libraries.places.internal;

import java.util.Iterator;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzaef implements Iterator<String> {
    final Iterator<String> zza;
    final /* synthetic */ zzaeg zzb;

    zzaef(zzaeg zzaeg) {
        this.zzb = zzaeg;
        this.zza = zzaeg.zza.iterator();
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
