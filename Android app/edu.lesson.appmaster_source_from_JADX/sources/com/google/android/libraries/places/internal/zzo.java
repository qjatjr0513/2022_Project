package com.google.android.libraries.places.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzo extends LocationCallback {
    final /* synthetic */ TaskCompletionSource zza;

    zzo(zzp zzp, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void onLocationAvailability(LocationAvailability locationAvailability) {
        try {
            super.onLocationAvailability(locationAvailability);
            if (!locationAvailability.isLocationAvailable()) {
                this.zza.trySetException(new ApiException(new Status(8, "Location unavailable.")));
            }
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    public final void onLocationResult(LocationResult locationResult) {
        try {
            super.onLocationResult(locationResult);
            this.zza.trySetResult(locationResult.getLastLocation());
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }
}
