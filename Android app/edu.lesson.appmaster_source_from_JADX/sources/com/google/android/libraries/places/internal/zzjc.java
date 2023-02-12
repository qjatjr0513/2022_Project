package com.google.android.libraries.places.internal;

import java.util.Collections;
import java.util.Comparator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzjc {
    /* access modifiers changed from: private */
    public static final Comparator<Object> zza = new zziu();
    private static final Comparator<zziw> zzb = new zziv();
    private static final zzjc zzc = new zzjc(new zzja(Collections.emptyList()));
    private final zzja zzd;

    private zzjc(zzja zzja) {
        this.zzd = zzja;
    }

    public static zzjc zza() {
        return zzc;
    }

    public final boolean equals(@NullableDecl Object obj) {
        return (obj instanceof zzjc) && ((zzjc) obj).zzd.equals(this.zzd);
    }

    public final int hashCode() {
        return ~this.zzd.hashCode();
    }

    public final String toString() {
        return this.zzd.toString();
    }
}
