package com.google.android.libraries.places.internal;

import android.text.TextUtils;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.zzba;
import com.google.android.libraries.places.api.model.zzbb;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;
import com.google.android.libraries.places.internal.zzax;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzbk {
    zzbk() {
    }

    public static final FindAutocompletePredictionsResponse zza(zzbj zzbj) throws ApiException {
        int zza = zzci.zza(zzbj.status);
        if (!PlacesStatusCodes.isError(zza)) {
            ArrayList arrayList = new ArrayList();
            zzax[] zzaxArr = zzbj.predictions;
            if (zzaxArr != null) {
                for (zzax zzax : zzaxArr) {
                    if (zzax == null || TextUtils.isEmpty(zzax.zzf())) {
                        throw new ApiException(new Status(8, "Unexpected server error: Place ID not provided for an autocomplete prediction result"));
                    }
                    AutocompletePrediction.Builder builder = AutocompletePrediction.builder(zzax.zzf());
                    builder.setDistanceMeters(zzax.zzd());
                    builder.setPlaceTypes(zzce.zza(zzce.zzb(zzax.zzc())));
                    builder.setFullText(zzfr.zzb(zzax.zze()));
                    builder.zza(zzb(zzax.zzb()));
                    zzax.zza zza2 = zzax.zza();
                    if (zza2 != null) {
                        builder.setPrimaryText(zzfr.zzb(zza2.zzc()));
                        builder.zzc(zzb(zza2.zza()));
                        builder.setSecondaryText(zzfr.zzb(zza2.zzd()));
                        builder.zzd(zzb(zza2.zzb()));
                    }
                    arrayList.add(builder.build());
                }
            }
            return FindAutocompletePredictionsResponse.newInstance(arrayList);
        }
        throw new ApiException(new Status(zza, zzci.zzb(zzbj.status, zzbj.errorMessage)));
    }

    private static List<zzbb> zzb(List<zzax.zzb> list) throws ApiException {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        zzhb zzq = ((zzge) list).listIterator(0);
        while (zzq.hasNext()) {
            zzax.zzb zzb = (zzax.zzb) zzq.next();
            if (zzb == null || zzb.offset == null || zzb.length == null) {
                throw new ApiException(new Status(8, "Unexpected server error: Place ID not provided for an autocomplete prediction result"));
            }
            zzba zzc = zzbb.zzc();
            zzc.zzb(zzb.offset.intValue());
            zzc.zza(zzb.length.intValue());
            arrayList.add(zzc.zzc());
        }
        return arrayList;
    }
}
