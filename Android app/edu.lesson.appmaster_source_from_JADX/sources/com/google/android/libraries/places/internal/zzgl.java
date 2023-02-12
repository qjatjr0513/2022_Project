package com.google.android.libraries.places.internal;

import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzgl<F, T> extends AbstractSequentialList<T> implements Serializable {
    final List<F> zza;
    final zzi zzb;

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.android.libraries.places.internal.zzi, java.util.List<F>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    zzgl(java.util.List r1, java.util.List<F> r2, com.google.android.libraries.places.internal.zzi r3) {
        /*
            r0 = this;
            r0.<init>()
            if (r1 == 0) goto L_0x000a
            r0.zza = r1
            r0.zzb = r2
            return
        L_0x000a:
            r1 = 0
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzgl.<init>(java.util.List, com.google.android.libraries.places.internal.zzi, byte[]):void");
    }

    public final void clear() {
        this.zza.clear();
    }

    public final ListIterator<T> listIterator(int i) {
        return new zzgk(this, this.zza.listIterator(i));
    }

    public final int size() {
        return this.zza.size();
    }
}
