package com.google.android.gms.location.places;

import android.text.style.CharacterStyle;
import com.google.android.gms.common.data.Freezable;
import java.util.List;

@Deprecated
public interface AutocompletePrediction extends Freezable<AutocompletePrediction> {
    CharSequence getFullText(CharacterStyle characterStyle);

    String getPlaceId();

    List<Integer> getPlaceTypes();

    CharSequence getPrimaryText(CharacterStyle characterStyle);

    CharSequence getSecondaryText(CharacterStyle characterStyle);
}
