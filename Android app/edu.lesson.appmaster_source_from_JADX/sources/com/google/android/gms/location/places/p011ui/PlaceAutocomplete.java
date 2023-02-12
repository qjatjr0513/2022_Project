package com.google.android.gms.location.places.p011ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLngBounds;

@Deprecated
/* renamed from: com.google.android.gms.location.places.ui.PlaceAutocomplete */
public class PlaceAutocomplete extends zzb {
    public static final int MODE_FULLSCREEN = 1;
    public static final int MODE_OVERLAY = 2;
    public static final int RESULT_ERROR = 2;

    private PlaceAutocomplete() {
    }

    public static Place getPlace(Context context, Intent intent) {
        return zzb.getPlace(context, intent);
    }

    public static Status getStatus(Context context, Intent intent) {
        return zzb.getStatus(context, intent);
    }

    /* renamed from: com.google.android.gms.location.places.ui.PlaceAutocomplete$IntentBuilder */
    public static class IntentBuilder extends zzc {
        public IntentBuilder(int i) {
            super("com.google.android.gms.location.places.ui.AUTOCOMPLETE");
            this.intent.putExtra("gmscore_client_jar_version", GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE);
            this.intent.putExtra("mode", i);
            this.intent.putExtra("origin", 2);
        }

        public IntentBuilder setBoundsBias(LatLngBounds latLngBounds) {
            if (latLngBounds != null) {
                this.intent.putExtra("bounds", latLngBounds);
            } else {
                this.intent.removeExtra("bounds");
            }
            return this;
        }

        public IntentBuilder setFilter(AutocompleteFilter autocompleteFilter) {
            if (autocompleteFilter != null) {
                this.intent.putExtra("filter", autocompleteFilter);
            } else {
                this.intent.removeExtra("filter");
            }
            return this;
        }

        public final IntentBuilder zzd(int i) {
            this.intent.putExtra("origin", 1);
            return this;
        }

        public final IntentBuilder zzg(String str) {
            if (str != null) {
                this.intent.putExtra("initial_query", str);
            } else {
                this.intent.removeExtra("initial_query");
            }
            return this;
        }

        public Intent build(Activity activity) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
            return super.build(activity);
        }
    }
}
