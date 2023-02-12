package com.google.android.gms.measurement.internal;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzaq implements Iterator<String> {
    final Iterator<String> zza;
    final /* synthetic */ zzar zzb;

    zzaq(zzar zzar) {
        this.zzb = zzar;
        this.zza = zzar.zza.keySet().iterator();
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }

    /* renamed from: zza */
    public final String next() {
        return this.zza.next();
    }
}
