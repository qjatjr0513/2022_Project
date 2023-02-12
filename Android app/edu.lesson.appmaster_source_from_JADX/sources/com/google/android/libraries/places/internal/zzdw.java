package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class zzdw {
    public abstract zzdw zza(List<String> list);

    public abstract zzdw zzb(String str);

    public abstract zzdw zzc(String str);

    public abstract zzdw zzd(LocationBias locationBias);

    public abstract zzdw zze(LocationRestriction locationRestriction);

    public abstract zzdw zzf(AutocompleteActivityMode autocompleteActivityMode);

    public abstract zzdw zzg(zzdv zzdv);

    public abstract zzdw zzh(List<Place.Field> list);

    public abstract zzdw zzi(int i);

    public abstract zzdw zzj(int i);

    public abstract zzdw zzk(TypeFilter typeFilter);

    public abstract zzdx zzl();

    public final zzdw zzm(String str) {
        return zza(str == null ? zzge.zzm() : zzge.zzn(str));
    }
}
