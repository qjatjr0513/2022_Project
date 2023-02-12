package com.google.android.gms.location;

import android.location.Location;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
final /* synthetic */ class zzai implements RemoteCall {
    private final Location zza;

    zzai(Location location) {
        this.zza = location;
    }

    public final void accept(Object obj, Object obj2) {
        Location location = this.zza;
        String str = FusedLocationProviderClient.KEY_MOCK_LOCATION;
        ((zzaz) obj).zzJ(location);
        ((TaskCompletionSource) obj2).setResult(null);
    }
}
