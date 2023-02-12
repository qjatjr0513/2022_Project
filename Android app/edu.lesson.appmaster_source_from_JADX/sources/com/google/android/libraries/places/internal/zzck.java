package com.google.android.libraries.places.internal;

import android.text.TextUtils;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.libraries.places.api.model.Place;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzck {
    private static final zzgg<Place.Field, String> zza;

    static {
        zzgf zzgf = new zzgf();
        zzgf.zza(Place.Field.ADDRESS, "formatted_address");
        zzgf.zza(Place.Field.ADDRESS_COMPONENTS, "address_components");
        zzgf.zza(Place.Field.BUSINESS_STATUS, "business_status");
        zzgf.zza(Place.Field.ID, "place_id");
        zzgf.zza(Place.Field.LAT_LNG, "geometry/location");
        zzgf.zza(Place.Field.NAME, AppMeasurementSdk.ConditionalUserProperty.NAME);
        zzgf.zza(Place.Field.OPENING_HOURS, "opening_hours");
        zzgf.zza(Place.Field.PHONE_NUMBER, "international_phone_number");
        zzgf.zza(Place.Field.PHOTO_METADATAS, "photos");
        zzgf.zza(Place.Field.PLUS_CODE, "plus_code");
        zzgf.zza(Place.Field.PRICE_LEVEL, "price_level");
        zzgf.zza(Place.Field.RATING, "rating");
        zzgf.zza(Place.Field.TYPES, "types");
        zzgf.zza(Place.Field.USER_RATINGS_TOTAL, "user_ratings_total");
        zzgf.zza(Place.Field.UTC_OFFSET, "utc_offset");
        zzgf.zza(Place.Field.VIEWPORT, "geometry/viewport");
        zzgf.zza(Place.Field.WEBSITE_URI, "website");
        zzgf.zza(Place.Field.ICON_URL, "icon_mask_base_uri");
        zzgf.zza(Place.Field.ICON_BACKGROUND_COLOR, "icon_background_color");
        zza = zzgf.zzb();
    }

    public static String zza(List<Place.Field> list) {
        StringBuilder sb = new StringBuilder();
        for (Place.Field field : list) {
            String str = zza.get(field);
            if (!TextUtils.isEmpty(str)) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static List<String> zzb(List<Place.Field> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Place.Field field : list) {
            arrayList.add(zza.get(field));
        }
        return arrayList;
    }
}
