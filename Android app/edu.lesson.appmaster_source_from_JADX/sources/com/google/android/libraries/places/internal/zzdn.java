package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzdn extends zzdw {
    private AutocompleteActivityMode zza;
    private zzge<Place.Field> zzb;
    private zzdv zzc;
    private String zzd;
    private String zze;
    private LocationBias zzf;
    private LocationRestriction zzg;
    private zzge<String> zzh;
    private TypeFilter zzi;
    private Integer zzj;
    private Integer zzk;

    zzdn() {
    }

    /* synthetic */ zzdn(zzdx zzdx, zzdm zzdm) {
        this.zza = zzdx.zzh();
        this.zzb = zzdx.zzj();
        this.zzc = zzdx.zzf();
        this.zzd = zzdx.zzl();
        this.zze = zzdx.zzk();
        this.zzf = zzdx.zzc();
        this.zzg = zzdx.zzd();
        this.zzh = zzdx.zzi();
        this.zzi = zzdx.zze();
        this.zzj = Integer.valueOf(zzdx.zza());
        this.zzk = Integer.valueOf(zzdx.zzb());
    }

    public final zzdw zza(List<String> list) {
        this.zzh = zzge.zzk(list);
        return this;
    }

    public final zzdw zzb(String str) {
        this.zze = str;
        return this;
    }

    public final zzdw zzc(String str) {
        this.zzd = str;
        return this;
    }

    public final zzdw zzd(LocationBias locationBias) {
        this.zzf = locationBias;
        return this;
    }

    public final zzdw zze(LocationRestriction locationRestriction) {
        this.zzg = locationRestriction;
        return this;
    }

    public final zzdw zzf(AutocompleteActivityMode autocompleteActivityMode) {
        if (autocompleteActivityMode != null) {
            this.zza = autocompleteActivityMode;
            return this;
        }
        throw new NullPointerException("Null mode");
    }

    public final zzdw zzg(zzdv zzdv) {
        if (zzdv != null) {
            this.zzc = zzdv;
            return this;
        }
        throw new NullPointerException("Null origin");
    }

    public final zzdw zzh(List<Place.Field> list) {
        this.zzb = zzge.zzk(list);
        return this;
    }

    public final zzdw zzi(int i) {
        this.zzj = Integer.valueOf(i);
        return this;
    }

    public final zzdw zzj(int i) {
        this.zzk = Integer.valueOf(i);
        return this;
    }

    public final zzdw zzk(TypeFilter typeFilter) {
        this.zzi = typeFilter;
        return this;
    }

    public final zzdx zzl() {
        zzge<Place.Field> zzge;
        zzdv zzdv;
        zzge<String> zzge2;
        Integer num;
        AutocompleteActivityMode autocompleteActivityMode = this.zza;
        if (autocompleteActivityMode != null && (zzge = this.zzb) != null && (zzdv = this.zzc) != null && (zzge2 = this.zzh) != null && (num = this.zzj) != null && this.zzk != null) {
            return new zzdq(autocompleteActivityMode, zzge, zzdv, this.zzd, this.zze, this.zzf, this.zzg, zzge2, this.zzi, num.intValue(), this.zzk.intValue());
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" mode");
        }
        if (this.zzb == null) {
            sb.append(" placeFields");
        }
        if (this.zzc == null) {
            sb.append(" origin");
        }
        if (this.zzh == null) {
            sb.append(" countries");
        }
        if (this.zzj == null) {
            sb.append(" primaryColor");
        }
        if (this.zzk == null) {
            sb.append(" primaryColorDark");
        }
        String valueOf = String.valueOf(sb);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 28);
        sb2.append("Missing required properties:");
        sb2.append(valueOf);
        throw new IllegalStateException(sb2.toString());
    }
}
