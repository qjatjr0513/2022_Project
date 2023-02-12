package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzo;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzy;
import com.google.android.gms.maps.model.GroundOverlay;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
final class zzm extends zzy {
    final /* synthetic */ GoogleMap.OnGroundOverlayClickListener zza;

    zzm(GoogleMap googleMap, GoogleMap.OnGroundOverlayClickListener onGroundOverlayClickListener) {
        this.zza = onGroundOverlayClickListener;
    }

    public final void zzb(zzo zzo) {
        this.zza.onGroundOverlayClick(new GroundOverlay(zzo));
    }
}
