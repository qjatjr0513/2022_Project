package com.google.android.libraries.places.internal;

import java.io.Serializable;
import java.util.Comparator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzfu<T> extends zzgo<T> implements Serializable {
    final Comparator<T> zza;

    zzfu(Comparator<T> comparator) {
        this.zza = comparator;
    }

    public final int compare(T t, T t2) {
        return this.zza.compare(t, t2);
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfu) {
            return this.zza.equals(((zzfu) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        return this.zza.toString();
    }
}
