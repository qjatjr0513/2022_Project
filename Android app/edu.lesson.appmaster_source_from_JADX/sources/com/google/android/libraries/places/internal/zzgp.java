package com.google.android.libraries.places.internal;

import java.io.Serializable;
import java.lang.Comparable;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzgp<C extends Comparable> extends zzgq implements Serializable {
    private static final zzgp<Comparable> zzc = new zzgp<>(zzfx.zzb, zzfv.zzb);
    final zzfz<C> zza;
    final zzfz<C> zzb;

    private zzgp(zzfz<C> zzfz, zzfz<C> zzfz2) {
        this.zza = zzfz;
        this.zzb = zzfz2;
        if (zzfz.compareTo(zzfz2) > 0 || zzfz == zzfv.zzb || zzfz2 == zzfx.zzb) {
            String valueOf = String.valueOf(zzf(zzfz, zzfz2));
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Invalid range: ".concat(valueOf) : new String("Invalid range: "));
        }
    }

    static int zza(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    public static <C extends Comparable<?>> zzgp<C> zzb(C c) {
        return new zzgp<>(new zzfy(c), zzfv.zzb);
    }

    public static <C extends Comparable<?>> zzgp<C> zzc(C c, C c2) {
        return new zzgp<>(new zzfy(c), new zzfw(c2));
    }

    public static <C extends Comparable<?>> zzgp<C> zzd(C c, C c2) {
        return new zzgp<>(new zzfy(c), new zzfy(c2));
    }

    private static String zzf(zzfz<?> zzfz, zzfz<?> zzfz2) {
        StringBuilder sb = new StringBuilder(16);
        zzfz.zzc(sb);
        sb.append("..");
        zzfz2.zzd(sb);
        return sb.toString();
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzgp) {
            zzgp zzgp = (zzgp) obj;
            if (!this.zza.equals(zzgp.zza) || !this.zzb.equals(zzgp.zzb)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (this.zza.hashCode() * 31) + this.zzb.hashCode();
    }

    public final String toString() {
        return zzf(this.zza, this.zzb);
    }

    public final boolean zze(C c) {
        if (c == null) {
            throw null;
        } else if (!this.zza.zze(c) || this.zzb.zze(c)) {
            return false;
        } else {
            return true;
        }
    }
}
