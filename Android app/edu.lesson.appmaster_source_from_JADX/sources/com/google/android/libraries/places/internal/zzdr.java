package com.google.android.libraries.places.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzdr extends zzdy {
    private String zza;
    private zzge<AutocompletePrediction> zzb;
    private Place zzc;
    private AutocompletePrediction zzd;
    private Status zze;
    private int zzf;

    zzdr() {
    }

    public final zzdy zza(Place place) {
        this.zzc = place;
        return this;
    }

    public final zzdy zzb(AutocompletePrediction autocompletePrediction) {
        this.zzd = autocompletePrediction;
        return this;
    }

    public final zzdy zzc(List<AutocompletePrediction> list) {
        this.zzb = zzge.zzk(list);
        return this;
    }

    public final zzdy zzd(String str) {
        this.zza = str;
        return this;
    }

    public final zzdy zze(Status status) {
        this.zze = status;
        return this;
    }

    public final zzdz zzf() {
        int i = this.zzf;
        if (i != 0) {
            return new zzdt(i, this.zza, this.zzb, this.zzc, this.zzd, this.zze, (zzds) null);
        }
        throw new IllegalStateException("Missing required properties: type");
    }

    public final zzdy zzg(int i) {
        this.zzf = i;
        return this;
    }
}
