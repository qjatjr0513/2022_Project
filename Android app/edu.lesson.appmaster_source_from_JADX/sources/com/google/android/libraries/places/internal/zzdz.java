package com.google.android.libraries.places.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class zzdz {
    public static zzdz zzg() {
        return zzr(3).zzf();
    }

    public static zzdz zzk() {
        return zzr(2).zzf();
    }

    public static zzdz zzl() {
        zzdy zzr = zzr(10);
        zzr.zze(new Status(16));
        return zzr.zzf();
    }

    public static zzdz zzo() {
        return zzr(1).zzf();
    }

    public static zzdz zzp() {
        return zzr(4).zzf();
    }

    private static zzdy zzr(int i) {
        zzdr zzdr = new zzdr();
        zzdr.zzg(i);
        return zzdr;
    }

    public abstract Status zza();

    public abstract AutocompletePrediction zzb();

    public abstract Place zzc();

    public abstract zzge<AutocompletePrediction> zzd();

    public abstract String zze();

    public abstract int zzf();

    public static zzdz zzh(String str) {
        if (str != null) {
            zzdy zzr = zzr(6);
            zzr.zzd(str);
            return zzr.zzf();
        }
        throw null;
    }

    public static zzdz zzi(String str, Status status) {
        if (str == null) {
            throw null;
        } else if (status != null) {
            zzdy zzr = zzr(7);
            zzr.zzd(str);
            zzr.zze(status);
            return zzr.zzf();
        } else {
            throw null;
        }
    }

    public static zzdz zzj(List<AutocompletePrediction> list) {
        if (list != null) {
            zzdy zzr = zzr(5);
            zzr.zzc(list);
            return zzr.zzf();
        }
        throw null;
    }

    public static zzdz zzm(AutocompletePrediction autocompletePrediction, Status status) {
        if (autocompletePrediction == null) {
            throw null;
        } else if (status != null) {
            zzdy zzr = zzr(9);
            zzr.zzb(autocompletePrediction);
            zzr.zze(status);
            return zzr.zzf();
        } else {
            throw null;
        }
    }

    public static zzdz zzn(Place place) {
        if (place != null) {
            zzdy zzr = zzr(8);
            zzr.zza(place);
            return zzr.zzf();
        }
        throw null;
    }

    public static zzdz zzq(Status status) {
        if (status != null) {
            zzdy zzr = zzr(10);
            zzr.zze(status);
            return zzr.zzf();
        }
        throw null;
    }
}
