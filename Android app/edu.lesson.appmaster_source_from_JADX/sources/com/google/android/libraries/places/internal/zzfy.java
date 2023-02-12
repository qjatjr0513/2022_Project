package com.google.android.libraries.places.internal;

import java.lang.Comparable;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzfy<C extends Comparable> extends zzfz<C> {
    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 2);
        sb.append("\\");
        sb.append(valueOf);
        sb.append("/");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final void zzc(StringBuilder sb) {
        sb.append('[');
        sb.append(this.zza);
    }

    /* access modifiers changed from: package-private */
    public final void zzd(StringBuilder sb) {
        sb.append(this.zza);
        sb.append(')');
    }

    /* access modifiers changed from: package-private */
    public final boolean zze(C c) {
        return zzgp.zza(this.zza, c) <= 0;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfy(C c) {
        super(c);
        if (c != null) {
            return;
        }
        throw null;
    }
}
