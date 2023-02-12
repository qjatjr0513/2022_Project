package com.google.android.gms.location.places.internal;

import android.text.style.CharacterStyle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.places.AutocompletePrediction;
import java.util.Collections;
import java.util.List;

public final class zzd extends zzaw implements AutocompletePrediction {
    public zzd(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    public final String getPlaceId() {
        return zzb("ap_place_id", (String) null);
    }

    public final List<Integer> getPlaceTypes() {
        return zzb("ap_place_types", (List<Integer>) Collections.emptyList());
    }

    public final CharSequence getFullText(CharacterStyle characterStyle) {
        return zzi.zzb(zzd(), zzg(), characterStyle);
    }

    public final CharSequence getPrimaryText(CharacterStyle characterStyle) {
        return zzi.zzb(zze(), zzh(), characterStyle);
    }

    public final CharSequence getSecondaryText(CharacterStyle characterStyle) {
        return zzi.zzb(zzf(), zzi(), characterStyle);
    }

    private final String zzd() {
        return zzb("ap_description", "");
    }

    private final String zze() {
        return zzb("ap_primary_text", "");
    }

    private final String zzf() {
        return zzb("ap_secondary_text", "");
    }

    private final List<zzb> zzg() {
        return zzb("ap_matched_subscriptions", zzb.CREATOR, Collections.emptyList());
    }

    private final List<zzb> zzh() {
        return zzb("ap_primary_text_matched", zzb.CREATOR, Collections.emptyList());
    }

    private final List<zzb> zzi() {
        return zzb("ap_secondary_text_matched", zzb.CREATOR, Collections.emptyList());
    }

    public final /* synthetic */ Object freeze() {
        String placeId = getPlaceId();
        List<Integer> placeTypes = getPlaceTypes();
        int zzc = zzc("ap_personalization_type", 6);
        String zzd = zzd();
        return new zzc(placeId, placeTypes, zzc, (String) Preconditions.checkNotNull(zzd), zzg(), zze(), zzh(), zzf(), zzi());
    }
}
