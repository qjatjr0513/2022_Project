package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzgc<E> extends zzfs<E> {
    private final zzge<E> zza;

    zzgc(zzge<E> zzge, int i) {
        super(zzge.size(), i);
        this.zza = zzge;
    }

    /* access modifiers changed from: protected */
    public final E zza(int i) {
        return this.zza.get(i);
    }
}
