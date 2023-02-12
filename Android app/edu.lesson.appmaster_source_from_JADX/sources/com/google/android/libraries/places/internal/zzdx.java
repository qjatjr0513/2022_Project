package com.google.android.libraries.places.internal;

import android.os.Parcelable;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class zzdx implements Parcelable {
    public static zzdw zzm(AutocompleteActivityMode autocompleteActivityMode, List<Place.Field> list, zzdv zzdv) {
        zzdn zzdn = new zzdn();
        zzdn.zza(new ArrayList());
        zzdn.zzf(autocompleteActivityMode);
        zzdn.zzh(list);
        zzdn.zzg(zzdv);
        zzdn.zzi(0);
        zzdn.zzj(0);
        return zzdn;
    }

    public abstract int zza();

    public abstract int zzb();

    public abstract LocationBias zzc();

    public abstract LocationRestriction zzd();

    public abstract TypeFilter zze();

    public abstract zzdv zzf();

    public abstract zzdw zzg();

    public abstract AutocompleteActivityMode zzh();

    public abstract zzge<String> zzi();

    public abstract zzge<Place.Field> zzj();

    public abstract String zzk();

    public abstract String zzl();
}
