package com.google.android.gms.location.places.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResult;

public interface zzv extends IInterface {
    void zzb(PlacePhotoMetadataResult placePhotoMetadataResult) throws RemoteException;

    void zzb(PlacePhotoResult placePhotoResult) throws RemoteException;
}
