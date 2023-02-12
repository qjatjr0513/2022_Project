package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzaa;
import com.google.android.gms.internal.maps.zzac;
import com.google.android.gms.internal.maps.zzad;
import com.google.android.gms.internal.maps.zzaf;
import com.google.android.gms.internal.maps.zzag;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zzk;
import com.google.android.gms.internal.maps.zzl;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.internal.maps.zzo;
import com.google.android.gms.internal.maps.zzq;
import com.google.android.gms.internal.maps.zzr;
import com.google.android.gms.internal.maps.zzw;
import com.google.android.gms.internal.maps.zzx;
import com.google.android.gms.internal.maps.zzz;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public final class zzg extends zza implements IGoogleMapDelegate {
    zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IGoogleMapDelegate");
    }

    public final zzl addCircle(CircleOptions circleOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, circleOptions);
        Parcel zzH = zzH(35, zza);
        zzl zzb = zzk.zzb(zzH.readStrongBinder());
        zzH.recycle();
        return zzb;
    }

    public final zzo addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, groundOverlayOptions);
        Parcel zzH = zzH(12, zza);
        zzo zzb = zzn.zzb(zzH.readStrongBinder());
        zzH.recycle();
        return zzb;
    }

    public final zzx addMarker(MarkerOptions markerOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, markerOptions);
        Parcel zzH = zzH(11, zza);
        zzx zzb = zzw.zzb(zzH.readStrongBinder());
        zzH.recycle();
        return zzb;
    }

    public final zzaa addPolygon(PolygonOptions polygonOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, polygonOptions);
        Parcel zzH = zzH(10, zza);
        zzaa zzb = zzz.zzb(zzH.readStrongBinder());
        zzH.recycle();
        return zzb;
    }

    public final zzad addPolyline(PolylineOptions polylineOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, polylineOptions);
        Parcel zzH = zzH(9, zza);
        zzad zzb = zzac.zzb(zzH.readStrongBinder());
        zzH.recycle();
        return zzb;
    }

    public final zzag addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, tileOverlayOptions);
        Parcel zzH = zzH(13, zza);
        zzag zzb = zzaf.zzb(zzH.readStrongBinder());
        zzH.recycle();
        return zzb;
    }

    public final void animateCamera(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, iObjectWrapper);
        zzc(5, zza);
    }

    public final void animateCameraWithCallback(IObjectWrapper iObjectWrapper, zzd zzd) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, iObjectWrapper);
        zzc.zzf(zza, zzd);
        zzc(6, zza);
    }

    public final void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i, zzd zzd) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, iObjectWrapper);
        zza.writeInt(i);
        zzc.zzf(zza, zzd);
        zzc(7, zza);
    }

    public final void clear() throws RemoteException {
        zzc(14, zza());
    }

    public final CameraPosition getCameraPosition() throws RemoteException {
        Parcel zzH = zzH(1, zza());
        CameraPosition cameraPosition = (CameraPosition) zzc.zza(zzH, CameraPosition.CREATOR);
        zzH.recycle();
        return cameraPosition;
    }

    public final zzr getFocusedBuilding() throws RemoteException {
        Parcel zzH = zzH(44, zza());
        zzr zzb = zzq.zzb(zzH.readStrongBinder());
        zzH.recycle();
        return zzb;
    }

    public final void getMapAsync(zzar zzar) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzar);
        zzc(53, zza);
    }

    public final int getMapType() throws RemoteException {
        Parcel zzH = zzH(15, zza());
        int readInt = zzH.readInt();
        zzH.recycle();
        return readInt;
    }

    public final float getMaxZoomLevel() throws RemoteException {
        Parcel zzH = zzH(2, zza());
        float readFloat = zzH.readFloat();
        zzH.recycle();
        return readFloat;
    }

    public final float getMinZoomLevel() throws RemoteException {
        Parcel zzH = zzH(3, zza());
        float readFloat = zzH.readFloat();
        zzH.recycle();
        return readFloat;
    }

    public final Location getMyLocation() throws RemoteException {
        Parcel zzH = zzH(23, zza());
        Location location = (Location) zzc.zza(zzH, Location.CREATOR);
        zzH.recycle();
        return location;
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.IProjectionDelegate getProjection() throws android.os.RemoteException {
        /*
            r4 = this;
            android.os.Parcel r0 = r4.zza()
            r1 = 26
            android.os.Parcel r0 = r4.zzH(r1, r0)
            android.os.IBinder r1 = r0.readStrongBinder()
            if (r1 != 0) goto L_0x0012
            r1 = 0
            goto L_0x0027
        L_0x0012:
            java.lang.String r2 = "com.google.android.gms.maps.internal.IProjectionDelegate"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.maps.internal.IProjectionDelegate
            if (r3 == 0) goto L_0x0021
            r1 = r2
            com.google.android.gms.maps.internal.IProjectionDelegate r1 = (com.google.android.gms.maps.internal.IProjectionDelegate) r1
            goto L_0x0027
        L_0x0021:
            com.google.android.gms.maps.internal.zzbs r2 = new com.google.android.gms.maps.internal.zzbs
            r2.<init>(r1)
            r1 = r2
        L_0x0027:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzg.getProjection():com.google.android.gms.maps.internal.IProjectionDelegate");
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.IUiSettingsDelegate getUiSettings() throws android.os.RemoteException {
        /*
            r4 = this;
            android.os.Parcel r0 = r4.zza()
            r1 = 25
            android.os.Parcel r0 = r4.zzH(r1, r0)
            android.os.IBinder r1 = r0.readStrongBinder()
            if (r1 != 0) goto L_0x0012
            r1 = 0
            goto L_0x0027
        L_0x0012:
            java.lang.String r2 = "com.google.android.gms.maps.internal.IUiSettingsDelegate"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.maps.internal.IUiSettingsDelegate
            if (r3 == 0) goto L_0x0021
            r1 = r2
            com.google.android.gms.maps.internal.IUiSettingsDelegate r1 = (com.google.android.gms.maps.internal.IUiSettingsDelegate) r1
            goto L_0x0027
        L_0x0021:
            com.google.android.gms.maps.internal.zzby r2 = new com.google.android.gms.maps.internal.zzby
            r2.<init>(r1)
            r1 = r2
        L_0x0027:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzg.getUiSettings():com.google.android.gms.maps.internal.IUiSettingsDelegate");
    }

    public final boolean isBuildingsEnabled() throws RemoteException {
        Parcel zzH = zzH(40, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final boolean isIndoorEnabled() throws RemoteException {
        Parcel zzH = zzH(19, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final boolean isMyLocationEnabled() throws RemoteException {
        Parcel zzH = zzH(21, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final boolean isTrafficEnabled() throws RemoteException {
        Parcel zzH = zzH(17, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final void moveCamera(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, iObjectWrapper);
        zzc(4, zza);
    }

    public final void onCreate(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, bundle);
        zzc(54, zza);
    }

    public final void onDestroy() throws RemoteException {
        zzc(57, zza());
    }

    public final void onEnterAmbient(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, bundle);
        zzc(81, zza);
    }

    public final void onExitAmbient() throws RemoteException {
        zzc(82, zza());
    }

    public final void onLowMemory() throws RemoteException {
        zzc(58, zza());
    }

    public final void onPause() throws RemoteException {
        zzc(56, zza());
    }

    public final void onResume() throws RemoteException {
        zzc(55, zza());
    }

    public final void onSaveInstanceState(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, bundle);
        Parcel zzH = zzH(60, zza);
        if (zzH.readInt() != 0) {
            bundle.readFromParcel(zzH);
        }
        zzH.recycle();
    }

    public final void onStart() throws RemoteException {
        zzc(101, zza());
    }

    public final void onStop() throws RemoteException {
        zzc(102, zza());
    }

    public final void resetMinMaxZoomPreference() throws RemoteException {
        zzc(94, zza());
    }

    public final void setBuildingsEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(41, zza);
    }

    public final void setContentDescription(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzc(61, zza);
    }

    public final boolean setIndoorEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        Parcel zzH = zzH(20, zza);
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final void setInfoWindowAdapter(zzi zzi) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzi);
        zzc(33, zza);
    }

    public final void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, latLngBounds);
        zzc(95, zza);
    }

    public final void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, iLocationSourceDelegate);
        zzc(24, zza);
    }

    public final boolean setMapStyle(MapStyleOptions mapStyleOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, mapStyleOptions);
        Parcel zzH = zzH(91, zza);
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final void setMapType(int i) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i);
        zzc(16, zza);
    }

    public final void setMaxZoomPreference(float f) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzc(93, zza);
    }

    public final void setMinZoomPreference(float f) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzc(92, zza);
    }

    public final void setMyLocationEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(22, zza);
    }

    public final void setOnCameraChangeListener(zzn zzn) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzn);
        zzc(27, zza);
    }

    public final void setOnCameraIdleListener(zzp zzp) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzp);
        zzc(99, zza);
    }

    public final void setOnCameraMoveCanceledListener(zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzr);
        zzc(98, zza);
    }

    public final void setOnCameraMoveListener(zzt zzt) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzt);
        zzc(97, zza);
    }

    public final void setOnCameraMoveStartedListener(zzv zzv) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzv);
        zzc(96, zza);
    }

    public final void setOnCircleClickListener(zzx zzx) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzx);
        zzc(89, zza);
    }

    public final void setOnGroundOverlayClickListener(zzz zzz) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzz);
        zzc(83, zza);
    }

    public final void setOnIndoorStateChangeListener(zzab zzab) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzab);
        zzc(45, zza);
    }

    public final void setOnInfoWindowClickListener(zzad zzad) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzad);
        zzc(32, zza);
    }

    public final void setOnInfoWindowCloseListener(zzaf zzaf) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzaf);
        zzc(86, zza);
    }

    public final void setOnInfoWindowLongClickListener(zzah zzah) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzah);
        zzc(84, zza);
    }

    public final void setOnMapClickListener(zzal zzal) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzal);
        zzc(28, zza);
    }

    public final void setOnMapLoadedCallback(zzan zzan) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzan);
        zzc(42, zza);
    }

    public final void setOnMapLongClickListener(zzap zzap) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzap);
        zzc(29, zza);
    }

    public final void setOnMarkerClickListener(zzat zzat) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzat);
        zzc(30, zza);
    }

    public final void setOnMarkerDragListener(zzav zzav) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzav);
        zzc(31, zza);
    }

    public final void setOnMyLocationButtonClickListener(zzax zzax) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzax);
        zzc(37, zza);
    }

    public final void setOnMyLocationChangeListener(zzaz zzaz) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzaz);
        zzc(36, zza);
    }

    public final void setOnMyLocationClickListener(zzbb zzbb) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzbb);
        zzc(107, zza);
    }

    public final void setOnPoiClickListener(zzbd zzbd) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzbd);
        zzc(80, zza);
    }

    public final void setOnPolygonClickListener(zzbf zzbf) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzbf);
        zzc(85, zza);
    }

    public final void setOnPolylineClickListener(zzbh zzbh) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzbh);
        zzc(87, zza);
    }

    public final void setPadding(int i, int i2, int i3, int i4) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i);
        zza.writeInt(i2);
        zza.writeInt(i3);
        zza.writeInt(i4);
        zzc(39, zza);
    }

    public final void setTrafficEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(18, zza);
    }

    public final void setWatermarkEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(51, zza);
    }

    public final void snapshot(zzbu zzbu, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzbu);
        zzc.zzf(zza, iObjectWrapper);
        zzc(38, zza);
    }

    public final void snapshotForTest(zzbu zzbu) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzbu);
        zzc(71, zza);
    }

    public final void stopAnimation() throws RemoteException {
        zzc(8, zza());
    }

    public final boolean useViewLifecycleWhenInFragment() throws RemoteException {
        Parcel zzH = zzH(59, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }
}
