package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzaq;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
final class zzat extends zzaq {
    final /* synthetic */ OnMapReadyCallback zza;

    zzat(zzau zzau, OnMapReadyCallback onMapReadyCallback) {
        this.zza = onMapReadyCallback;
    }

    public final void zzb(IGoogleMapDelegate iGoogleMapDelegate) throws RemoteException {
        this.zza.onMapReady(new GoogleMap(iGoogleMapDelegate));
    }
}
