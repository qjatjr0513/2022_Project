package com.google.android.libraries.places.internal;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzgu<K> extends zzgh<K> {
    private final transient zzgg<K, ?> zza;
    private final transient zzge<K> zzb;

    zzgu(zzgg<K, ?> zzgg, zzge<K> zzge) {
        this.zza = zzgg;
        this.zzb = zzge;
    }

    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.get(obj) != null;
    }

    public final /* bridge */ /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    public final int size() {
        return this.zza.size();
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        return this.zzb.zza(objArr, 0);
    }

    public final zzge<K> zzd() {
        return this.zzb;
    }

    public final zzha<K> zze() {
        return this.zzb.listIterator(0);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf() {
        throw null;
    }
}
