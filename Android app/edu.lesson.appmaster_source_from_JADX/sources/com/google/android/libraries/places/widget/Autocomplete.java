package com.google.android.libraries.places.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.internal.zzdh;
import com.google.android.libraries.places.internal.zzdv;
import com.google.android.libraries.places.internal.zzdw;
import com.google.android.libraries.places.internal.zzdx;
import com.google.android.libraries.places.internal.zzea;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class Autocomplete {

    /* compiled from: com.google.android.libraries.places:places@@2.5.0 */
    public static class IntentBuilder {
        private final zzdw zza;

        public IntentBuilder(zzdx zzdx) {
            this.zza = zzdx.zzg();
        }

        public Intent build(Context context) {
            try {
                Intent intent = new Intent(context, AutocompleteActivity.class);
                zzdw zzdw = this.zza;
                Resources.Theme theme = context.getTheme();
                TypedValue typedValue = new TypedValue();
                if (theme.resolveAttribute(16843827, typedValue, true)) {
                    zzdw.zzi(typedValue.data);
                }
                TypedValue typedValue2 = new TypedValue();
                if (theme.resolveAttribute(16843828, typedValue2, true)) {
                    zzdw.zzj(typedValue2.data);
                }
                intent.putExtra("places/AutocompleteOptions", this.zza.zzl());
                return intent;
            } catch (Error | RuntimeException e) {
                zzdh.zzb(e);
                throw e;
            }
        }

        public IntentBuilder setCountries(List<String> countries) {
            this.zza.zza(countries);
            return this;
        }

        public IntentBuilder setCountry(String country) {
            this.zza.zzm(country);
            return this;
        }

        public IntentBuilder setHint(String hint) {
            this.zza.zzb(hint);
            return this;
        }

        public IntentBuilder setInitialQuery(String initialQuery) {
            this.zza.zzc(initialQuery);
            return this;
        }

        public IntentBuilder setLocationBias(LocationBias locationBias) {
            this.zza.zzd(locationBias);
            return this;
        }

        public IntentBuilder setLocationRestriction(LocationRestriction locationRestriction) {
            this.zza.zze(locationRestriction);
            return this;
        }

        public IntentBuilder setTypeFilter(TypeFilter typeFilter) {
            this.zza.zzk(typeFilter);
            return this;
        }

        public final IntentBuilder zza(zzdv zzdv) {
            this.zza.zzg(zzdv);
            return this;
        }

        public IntentBuilder(AutocompleteActivityMode mode, List<Place.Field> placeFields) {
            this.zza = zzdx.zzm(mode, placeFields, zzdv.INTENT);
        }
    }

    private Autocomplete() {
    }

    public static Place getPlaceFromIntent(Intent intent) {
        return zzea.zzb(intent);
    }

    public static Status getStatusFromIntent(Intent intent) {
        return zzea.zza(intent);
    }
}
