package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzfv extends zzfz<Comparable<?>> {
    /* access modifiers changed from: private */
    public static final zzfv zzb = new zzfv();

    private zzfv() {
        super("");
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo((zzfz) obj);
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    public final String toString() {
        return "+∞";
    }

    public final int zza(zzfz<Comparable<?>> zzfz) {
        return zzfz == this ? 0 : 1;
    }

    /* access modifiers changed from: package-private */
    public final void zzc(StringBuilder sb) {
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    public final void zzd(StringBuilder sb) {
        sb.append("+∞)");
    }

    /* access modifiers changed from: package-private */
    public final boolean zze(Comparable<?> comparable) {
        return false;
    }
}
