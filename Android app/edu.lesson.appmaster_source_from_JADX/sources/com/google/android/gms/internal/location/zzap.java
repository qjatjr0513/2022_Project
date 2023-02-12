package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
final class zzap implements ListenerHolder.Notifier<LocationCallback> {
    final /* synthetic */ LocationResult zza;

    zzap(zzar zzar, LocationResult locationResult) {
        this.zza = locationResult;
    }

    public final /* bridge */ /* synthetic */ void notifyListener(Object obj) {
        ((LocationCallback) obj).onLocationResult(this.zza);
    }

    public final void onNotifyListenerFailed() {
    }
}
