package com.google.android.gms.location.places.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.places.zzc;
import com.google.android.gms.internal.places.zze;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;

public final class zzw extends zzc implements zzt {
    zzw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.places.internal.IGooglePlacesService");
    }

    public final void zzb(List<String> list, zzau zzau, zzx zzx) throws RemoteException {
        Parcel zzb = zzb();
        zzb.writeStringList(list);
        zze.zzb(zzb, (Parcelable) zzau);
        zze.zzb(zzb, (IInterface) zzx);
        zzb(17, zzb);
    }

    public final void zzb(String str, LatLngBounds latLngBounds, int i, AutocompleteFilter autocompleteFilter, zzau zzau, zzx zzx) throws RemoteException {
        Parcel zzb = zzb();
        zzb.writeString(str);
        zze.zzb(zzb, (Parcelable) latLngBounds);
        zzb.writeInt(i);
        zze.zzb(zzb, (Parcelable) autocompleteFilter);
        zze.zzb(zzb, (Parcelable) zzau);
        zze.zzb(zzb, (IInterface) zzx);
        zzb(28, zzb);
    }

    public final void zzb(AddPlaceRequest addPlaceRequest, zzau zzau, zzx zzx) throws RemoteException {
        Parcel zzb = zzb();
        zze.zzb(zzb, (Parcelable) addPlaceRequest);
        zze.zzb(zzb, (Parcelable) zzau);
        zze.zzb(zzb, (IInterface) zzx);
        zzb(14, zzb);
    }

    public final void zzb(String str, zzau zzau, zzv zzv) throws RemoteException {
        Parcel zzb = zzb();
        zzb.writeString(str);
        zze.zzb(zzb, (Parcelable) zzau);
        zze.zzb(zzb, (IInterface) zzv);
        zzb(19, zzb);
    }

    public final void zzb(String str, int i, int i2, int i3, zzau zzau, zzv zzv) throws RemoteException {
        Parcel zzb = zzb();
        zzb.writeString(str);
        zzb.writeInt(i);
        zzb.writeInt(i2);
        zzb.writeInt(i3);
        zze.zzb(zzb, (Parcelable) zzau);
        zze.zzb(zzb, (IInterface) zzv);
        zzb(20, zzb);
    }
}
