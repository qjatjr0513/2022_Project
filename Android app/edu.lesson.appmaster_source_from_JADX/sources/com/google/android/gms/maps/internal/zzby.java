package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public final class zzby extends zza implements IUiSettingsDelegate {
    zzby(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IUiSettingsDelegate");
    }

    public final boolean isCompassEnabled() throws RemoteException {
        Parcel zzH = zzH(10, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final boolean isIndoorLevelPickerEnabled() throws RemoteException {
        Parcel zzH = zzH(17, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final boolean isMapToolbarEnabled() throws RemoteException {
        Parcel zzH = zzH(19, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final boolean isMyLocationButtonEnabled() throws RemoteException {
        Parcel zzH = zzH(11, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final boolean isRotateGesturesEnabled() throws RemoteException {
        Parcel zzH = zzH(15, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final boolean isScrollGesturesEnabled() throws RemoteException {
        Parcel zzH = zzH(12, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final boolean isScrollGesturesEnabledDuringRotateOrZoom() throws RemoteException {
        Parcel zzH = zzH(21, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final boolean isTiltGesturesEnabled() throws RemoteException {
        Parcel zzH = zzH(14, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final boolean isZoomControlsEnabled() throws RemoteException {
        Parcel zzH = zzH(9, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final boolean isZoomGesturesEnabled() throws RemoteException {
        Parcel zzH = zzH(13, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final void setAllGesturesEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(8, zza);
    }

    public final void setCompassEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(2, zza);
    }

    public final void setIndoorLevelPickerEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(16, zza);
    }

    public final void setMapToolbarEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(18, zza);
    }

    public final void setMyLocationButtonEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(3, zza);
    }

    public final void setRotateGesturesEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(7, zza);
    }

    public final void setScrollGesturesEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(4, zza);
    }

    public final void setScrollGesturesEnabledDuringRotateOrZoom(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(20, zza);
    }

    public final void setTiltGesturesEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(6, zza);
    }

    public final void setZoomControlsEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(1, zza);
    }

    public final void setZoomGesturesEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(5, zza);
    }
}
