package com.google.android.gms.location.places.p011ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLngBounds;

@Deprecated
/* renamed from: com.google.android.gms.location.places.ui.PlacePicker */
public class PlacePicker extends zzb {
    public static final int RESULT_ERROR = 2;

    private PlacePicker() {
    }

    @Deprecated
    public static Place getPlace(Intent intent, Context context) {
        return zzb.getPlace(context, intent);
    }

    /* renamed from: com.google.android.gms.location.places.ui.PlacePicker$IntentBuilder */
    public static class IntentBuilder extends zzc {
        public IntentBuilder() {
            super("com.google.android.gms.location.places.ui.PICK_PLACE");
            this.intent.putExtra("gmscore_client_jar_version", GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        }

        public IntentBuilder setLatLngBounds(LatLngBounds latLngBounds) {
            Preconditions.checkNotNull(latLngBounds);
            SafeParcelableSerializer.serializeToIntentExtra(latLngBounds, this.intent, "latlng_bounds");
            return this;
        }

        public Intent build(Activity activity) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
            return super.build(activity);
        }
    }

    public static Place getPlace(Context context, Intent intent) {
        return zzb.getPlace(context, intent);
    }

    @Deprecated
    public static String getAttributions(Intent intent) {
        return intent.getStringExtra("third_party_attributions");
    }

    public static LatLngBounds getLatLngBounds(Intent intent) {
        return (LatLngBounds) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "final_latlng_bounds", LatLngBounds.CREATOR);
    }
}
