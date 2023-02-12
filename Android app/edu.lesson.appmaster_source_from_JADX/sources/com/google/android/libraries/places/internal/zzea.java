package com.google.android.libraries.places.internal;

import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzea {
    public static Status zza(Intent intent) {
        boolean z;
        try {
            zzfm.zzc(intent, "Intent must not be null.");
            Status status = (Status) intent.getParcelableExtra("places/status");
            if (status != null) {
                z = true;
            } else {
                z = false;
            }
            zzfm.zze(z, "Intent expected to contain a Status, but doesn't.");
            return status;
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    public static Place zzb(Intent intent) {
        boolean z;
        try {
            zzfm.zzc(intent, "Intent must not be null.");
            Place place = (Place) intent.getParcelableExtra("places/selected_place");
            if (place != null) {
                z = true;
            } else {
                z = false;
            }
            zzfm.zze(z, "Intent expected to contain a Place, but doesn't.");
            return place;
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }
}
