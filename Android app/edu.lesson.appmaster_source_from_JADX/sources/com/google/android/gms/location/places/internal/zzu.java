package com.google.android.gms.location.places.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.places.zzc;
import com.google.android.gms.internal.places.zze;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;

public final class zzu extends zzc implements zzr {
    zzu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
    }

    public final void zzb(PlaceFilter placeFilter, zzau zzau, zzx zzx) throws RemoteException {
        Parcel zzb = zzb();
        zze.zzb(zzb, (Parcelable) placeFilter);
        zze.zzb(zzb, (Parcelable) zzau);
        zze.zzb(zzb, (IInterface) zzx);
        zzb(6, zzb);
    }

    public final void zzb(PlaceReport placeReport, zzau zzau, zzx zzx) throws RemoteException {
        Parcel zzb = zzb();
        zze.zzb(zzb, (Parcelable) placeReport);
        zze.zzb(zzb, (Parcelable) zzau);
        zze.zzb(zzb, (IInterface) zzx);
        zzb(7, zzb);
    }
}
