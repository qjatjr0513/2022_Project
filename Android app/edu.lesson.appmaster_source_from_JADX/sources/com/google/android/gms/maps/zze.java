package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzx;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzae;
import com.google.android.gms.maps.model.Marker;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
final class zze extends zzae {
    final /* synthetic */ GoogleMap.OnInfoWindowCloseListener zza;

    zze(GoogleMap googleMap, GoogleMap.OnInfoWindowCloseListener onInfoWindowCloseListener) {
        this.zza = onInfoWindowCloseListener;
    }

    public final void zzb(zzx zzx) {
        this.zza.onInfoWindowClose(new Marker(zzx));
    }
}
