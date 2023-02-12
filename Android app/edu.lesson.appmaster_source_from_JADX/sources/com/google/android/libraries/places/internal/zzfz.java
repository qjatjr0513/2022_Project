package com.google.android.libraries.places.internal;

import java.io.Serializable;
import java.lang.Comparable;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
abstract class zzfz<C extends Comparable> implements Comparable<zzfz<C>>, Serializable {
    final C zza;

    zzfz(C c) {
        this.zza = c;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzfz) {
            try {
                if (compareTo((zzfz) obj) == 0) {
                    return true;
                }
                return false;
            } catch (ClassCastException e) {
            }
        }
        return false;
    }

    public abstract int hashCode();

    /* renamed from: zza */
    public int compareTo(zzfz<C> zzfz) {
        if (zzfz != zzfx.zzb) {
            if (zzfz == zzfv.zzb) {
                return -1;
            }
            int zza2 = zzgp.zza(this.zza, zzfz.zza);
            if (zza2 != 0) {
                return zza2;
            }
            boolean z = this instanceof zzfw;
            if (z == (zzfz instanceof zzfw)) {
                return 0;
            }
            if (!z) {
                return -1;
            }
        }
        return 1;
    }

    /* access modifiers changed from: package-private */
    public abstract void zzc(StringBuilder sb);

    /* access modifiers changed from: package-private */
    public abstract void zzd(StringBuilder sb);

    /* access modifiers changed from: package-private */
    public abstract boolean zze(C c);
}
