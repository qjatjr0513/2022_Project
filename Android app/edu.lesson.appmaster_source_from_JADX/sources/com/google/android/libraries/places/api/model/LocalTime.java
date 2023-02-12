package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import com.google.android.libraries.places.internal.zzfm;
import com.google.android.libraries.places.internal.zzgp;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class LocalTime implements Parcelable, Comparable<LocalTime> {
    public static LocalTime newInstance(int hours, int minutes) {
        try {
            zzi zzi = new zzi();
            zzi.zza(hours);
            zzi.zzb(minutes);
            LocalTime zzc = zzi.zzc();
            int hours2 = zzc.getHours();
            zzfm.zzj(zzgp.zzc(0, 23).zze(Integer.valueOf(hours2)), "Hours must not be out-of-range: 0 to 23, but was: %s.", hours2);
            int minutes2 = zzc.getMinutes();
            zzfm.zzj(zzgp.zzc(0, 59).zze(Integer.valueOf(minutes2)), "Minutes must not be out-of-range: 0 to 59, but was: %s.", minutes2);
            return zzc;
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public int compareTo(LocalTime compare) {
        zzfm.zzc(compare, "compare must not be null.");
        if (this == compare) {
            return 0;
        }
        if (getHours() == compare.getHours()) {
            return getMinutes() - compare.getMinutes();
        }
        return getHours() - compare.getHours();
    }

    public abstract int getHours();

    public abstract int getMinutes();
}
