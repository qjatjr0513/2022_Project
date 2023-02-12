package com.google.android.gms.location.places;

import android.os.RemoteException;
import com.google.android.gms.location.places.internal.zzy;

public final class zzf extends zzy {
    private final zzg zzn;
    private final zze zzo;

    public zzf(zzg zzg) {
        this.zzn = zzg;
        this.zzo = null;
    }

    public zzf(zze zze) {
        this.zzn = null;
        this.zzo = zze;
    }

    public final void zzb(PlacePhotoMetadataResult placePhotoMetadataResult) throws RemoteException {
        this.zzn.setResult(placePhotoMetadataResult);
    }

    public final void zzb(PlacePhotoResult placePhotoResult) throws RemoteException {
        this.zzo.setResult(placePhotoResult);
    }
}
