package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzs;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
final class zzv extends zzs {
    final /* synthetic */ GoogleMap.OnCameraMoveListener zza;

    zzv(GoogleMap googleMap, GoogleMap.OnCameraMoveListener onCameraMoveListener) {
        this.zza = onCameraMoveListener;
    }

    public final void zzb() {
        this.zza.onCameraMove();
    }
}
