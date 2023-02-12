package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzao;
import com.google.android.gms.maps.model.LatLng;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
final class zzz extends zzao {
    final /* synthetic */ GoogleMap.OnMapLongClickListener zza;

    zzz(GoogleMap googleMap, GoogleMap.OnMapLongClickListener onMapLongClickListener) {
        this.zza = onMapLongClickListener;
    }

    public final void zzb(LatLng latLng) {
        this.zza.onMapLongClick(latLng);
    }
}
