package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzo;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
final class zzx extends zzo {
    final /* synthetic */ GoogleMap.OnCameraIdleListener zza;

    zzx(GoogleMap googleMap, GoogleMap.OnCameraIdleListener onCameraIdleListener) {
        this.zza = onCameraIdleListener;
    }

    public final void zzb() {
        this.zza.onCameraIdle();
    }
}
