package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public interface IStreetViewPanoramaDelegate extends IInterface {
    void animateTo(StreetViewPanoramaCamera streetViewPanoramaCamera, long j) throws RemoteException;

    void enablePanning(boolean z) throws RemoteException;

    void enableStreetNames(boolean z) throws RemoteException;

    void enableUserNavigation(boolean z) throws RemoteException;

    void enableZoom(boolean z) throws RemoteException;

    StreetViewPanoramaCamera getPanoramaCamera() throws RemoteException;

    StreetViewPanoramaLocation getStreetViewPanoramaLocation() throws RemoteException;

    boolean isPanningGesturesEnabled() throws RemoteException;

    boolean isStreetNamesEnabled() throws RemoteException;

    boolean isUserNavigationEnabled() throws RemoteException;

    boolean isZoomGesturesEnabled() throws RemoteException;

    IObjectWrapper orientationToPoint(StreetViewPanoramaOrientation streetViewPanoramaOrientation) throws RemoteException;

    StreetViewPanoramaOrientation pointToOrientation(IObjectWrapper iObjectWrapper) throws RemoteException;

    void setOnStreetViewPanoramaCameraChangeListener(@Nullable zzbj zzbj) throws RemoteException;

    void setOnStreetViewPanoramaChangeListener(@Nullable zzbl zzbl) throws RemoteException;

    void setOnStreetViewPanoramaClickListener(@Nullable zzbn zzbn) throws RemoteException;

    void setOnStreetViewPanoramaLongClickListener(@Nullable zzbp zzbp) throws RemoteException;

    void setPosition(LatLng latLng) throws RemoteException;

    void setPositionWithID(String str) throws RemoteException;

    void setPositionWithRadius(LatLng latLng, int i) throws RemoteException;

    void setPositionWithRadiusAndSource(LatLng latLng, int i, @Nullable StreetViewSource streetViewSource) throws RemoteException;

    void setPositionWithSource(LatLng latLng, @Nullable StreetViewSource streetViewSource) throws RemoteException;
}
