package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import android.text.SpannableString;
import android.text.style.CharacterStyle;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.internal.zzge;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class AutocompletePrediction implements Parcelable {

    /* compiled from: com.google.android.libraries.places:places@@2.5.0 */
    public static abstract class Builder {
        public AutocompletePrediction build() {
            AutocompletePrediction zze = zze();
            setPlaceTypes(zzge.zzk(zze.getPlaceTypes()));
            List<zzbb> zzd = zze.zzd();
            if (zzd != null) {
                zza(zzge.zzk(zzd));
            }
            List<zzbb> zze2 = zze.zze();
            if (zze2 != null) {
                zzc(zzge.zzk(zze2));
            }
            List<zzbb> zzf = zze.zzf();
            if (zzf != null) {
                zzd(zzge.zzk(zzf));
            }
            return zze();
        }

        public abstract Integer getDistanceMeters();

        public abstract String getFullText();

        public abstract List<Place.Type> getPlaceTypes();

        public abstract String getPrimaryText();

        public abstract String getSecondaryText();

        public abstract Builder setDistanceMeters(Integer num);

        public abstract Builder setFullText(String str);

        public abstract Builder setPlaceTypes(List<Place.Type> list);

        public abstract Builder setPrimaryText(String str);

        public abstract Builder setSecondaryText(String str);

        public abstract Builder zza(List<zzbb> list);

        public abstract Builder zzc(List<zzbb> list);

        public abstract Builder zzd(List<zzbb> list);

        /* access modifiers changed from: package-private */
        public abstract AutocompletePrediction zze();
    }

    public static Builder builder(String placeId) {
        zzd zzd = new zzd();
        zzd.zzb(placeId);
        zzd.setPlaceTypes(new ArrayList());
        zzd.setFullText("");
        zzd.setPrimaryText("");
        zzd.setSecondaryText("");
        return zzd;
    }

    private static final SpannableString zzg(String str, List<zzbb> list, CharacterStyle characterStyle) {
        SpannableString spannableString = new SpannableString(str);
        if (str.length() == 0 || characterStyle == null || list == null) {
            return spannableString;
        }
        for (zzbb next : list) {
            spannableString.setSpan(CharacterStyle.wrap(characterStyle), next.zzb(), next.zzb() + next.zza(), 0);
        }
        return spannableString;
    }

    public abstract Integer getDistanceMeters();

    public SpannableString getFullText(CharacterStyle matchStyle) {
        return zzg(zza(), zzd(), matchStyle);
    }

    public abstract String getPlaceId();

    public abstract List<Place.Type> getPlaceTypes();

    public SpannableString getPrimaryText(CharacterStyle matchStyle) {
        return zzg(zzb(), zze(), matchStyle);
    }

    public SpannableString getSecondaryText(CharacterStyle matchStyle) {
        return zzg(zzc(), zzf(), matchStyle);
    }

    /* access modifiers changed from: package-private */
    public abstract String zza();

    /* access modifiers changed from: package-private */
    public abstract String zzb();

    /* access modifiers changed from: package-private */
    public abstract String zzc();

    /* access modifiers changed from: package-private */
    public abstract List<zzbb> zzd();

    /* access modifiers changed from: package-private */
    public abstract List<zzbb> zze();

    /* access modifiers changed from: package-private */
    public abstract List<zzbb> zzf();
}
