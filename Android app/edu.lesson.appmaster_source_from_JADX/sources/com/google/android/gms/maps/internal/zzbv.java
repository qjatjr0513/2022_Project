package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public final class zzbv extends zza implements IStreetViewPanoramaDelegate {
    zzbv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
    }

    public final void animateTo(StreetViewPanoramaCamera streetViewPanoramaCamera, long j) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, streetViewPanoramaCamera);
        zza.writeLong(j);
        zzc(9, zza);
    }

    public final void enablePanning(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(2, zza);
    }

    public final void enableStreetNames(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(4, zza);
    }

    public final void enableUserNavigation(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(3, zza);
    }

    public final void enableZoom(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(1, zza);
    }

    public final StreetViewPanoramaCamera getPanoramaCamera() throws RemoteException {
        Parcel zzH = zzH(10, zza());
        StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera) zzc.zza(zzH, StreetViewPanoramaCamera.CREATOR);
        zzH.recycle();
        return streetViewPanoramaCamera;
    }

    public final StreetViewPanoramaLocation getStreetViewPanoramaLocation() throws RemoteException {
        Parcel zzH = zzH(14, zza());
        StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation) zzc.zza(zzH, StreetViewPanoramaLocation.CREATOR);
        zzH.recycle();
        return streetViewPanoramaLocation;
    }

    public final boolean isPanningGesturesEnabled() throws RemoteException {
        Parcel zzH = zzH(6, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final boolean isStreetNamesEnabled() throws RemoteException {
        Parcel zzH = zzH(8, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final boolean isUserNavigationEnabled() throws RemoteException {
        Parcel zzH = zzH(7, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final boolean isZoomGesturesEnabled() throws RemoteException {
        Parcel zzH = zzH(5, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final IObjectWrapper orientationToPoint(StreetViewPanoramaOrientation streetViewPanoramaOrientation) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, streetViewPanoramaOrientation);
        Parcel zzH = zzH(19, zza);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zzH.readStrongBinder());
        zzH.recycle();
        return asInterface;
    }

    public final StreetViewPanoramaOrientation pointToOrientation(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, iObjectWrapper);
        Parcel zzH = zzH(18, zza);
        StreetViewPanoramaOrientation streetViewPanoramaOrientation = (StreetViewPanoramaOrientation) zzc.zza(zzH, StreetViewPanoramaOrientation.CREATOR);
        zzH.recycle();
        return streetViewPanoramaOrientation;
    }

    public final void setOnStreetViewPanoramaCameraChangeListener(zzbj zzbj) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzbj);
        zzc(16, zza);
    }

    public final void setOnStreetViewPanoramaChangeListener(zzbl zzbl) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzbl);
        zzc(15, zza);
    }

    public final void setOnStreetViewPanoramaClickListener(zzbn zzbn) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzbn);
        zzc(17, zza);
    }

    public final void setOnStreetViewPanoramaLongClickListener(zzbp zzbp) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzbp);
        zzc(20, zza);
    }

    public final void setPosition(LatLng latLng) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, latLng);
        zzc(12, zza);
    }

    public final void setPositionWithID(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzc(11, zza);
    }

    public final void setPositionWithRadius(LatLng latLng, int i) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, latLng);
        zza.writeInt(i);
        zzc(13, zza);
    }

    public final void setPositionWithRadiusAndSource(LatLng latLng, int i, StreetViewSource streetViewSource) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, latLng);
        zza.writeInt(i);
        zzc.zzd(zza, streetViewSource);
        zzc(22, zza);
    }

    public final void setPositionWithSource(LatLng latLng, StreetViewSource streetViewSource) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, latLng);
        zzc.zzd(zza, streetViewSource);
        zzc(21, zza);
    }
}
