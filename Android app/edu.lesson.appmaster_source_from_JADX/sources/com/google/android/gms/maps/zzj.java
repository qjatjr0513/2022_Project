package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzam;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
final class zzj extends zzam {
    final /* synthetic */ GoogleMap.OnMapLoadedCallback zza;

    zzj(GoogleMap googleMap, GoogleMap.OnMapLoadedCallback onMapLoadedCallback) {
        this.zza = onMapLoadedCallback;
    }

    public final void zzb() throws RemoteException {
        this.zza.onMapLoaded();
    }
}
