package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzx;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzau;
import com.google.android.gms.maps.model.Marker;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
final class zzb extends zzau {
    final /* synthetic */ GoogleMap.OnMarkerDragListener zza;

    zzb(GoogleMap googleMap, GoogleMap.OnMarkerDragListener onMarkerDragListener) {
        this.zza = onMarkerDragListener;
    }

    public final void zzb(zzx zzx) {
        this.zza.onMarkerDrag(new Marker(zzx));
    }

    public final void zzc(zzx zzx) {
        this.zza.onMarkerDragEnd(new Marker(zzx));
    }

    public final void zzd(zzx zzx) {
        this.zza.onMarkerDragStart(new Marker(zzx));
    }
}
