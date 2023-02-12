package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.zzbj;
import com.google.android.gms.maps.internal.zzbl;
import com.google.android.gms.maps.internal.zzbn;
import com.google.android.gms.maps.internal.zzbp;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public class StreetViewPanorama {
    private final IStreetViewPanoramaDelegate zza;

    /* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
    public interface OnStreetViewPanoramaCameraChangeListener {
        void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera streetViewPanoramaCamera);
    }

    /* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
    public interface OnStreetViewPanoramaChangeListener {
        void onStreetViewPanoramaChange(StreetViewPanoramaLocation streetViewPanoramaLocation);
    }

    /* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
    public interface OnStreetViewPanoramaClickListener {
        void onStreetViewPanoramaClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation);
    }

    /* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
    public interface OnStreetViewPanoramaLongClickListener {
        void onStreetViewPanoramaLongClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation);
    }

    public StreetViewPanorama(IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate) {
        this.zza = (IStreetViewPanoramaDelegate) Preconditions.checkNotNull(iStreetViewPanoramaDelegate, "delegate");
    }

    public void animateTo(StreetViewPanoramaCamera camera, long duration) {
        Preconditions.checkNotNull(camera);
        try {
            this.zza.animateTo(camera, duration);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public StreetViewPanoramaLocation getLocation() {
        try {
            return this.zza.getStreetViewPanoramaLocation();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public StreetViewPanoramaCamera getPanoramaCamera() {
        try {
            return this.zza.getPanoramaCamera();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isPanningGesturesEnabled() {
        try {
            return this.zza.isPanningGesturesEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isStreetNamesEnabled() {
        try {
            return this.zza.isStreetNamesEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isUserNavigationEnabled() {
        try {
            return this.zza.isUserNavigationEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isZoomGesturesEnabled() {
        try {
            return this.zza.isZoomGesturesEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public Point orientationToPoint(StreetViewPanoramaOrientation orientation) {
        try {
            IObjectWrapper orientationToPoint = this.zza.orientationToPoint(orientation);
            if (orientationToPoint == null) {
                return null;
            }
            return (Point) ObjectWrapper.unwrap(orientationToPoint);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public StreetViewPanoramaOrientation pointToOrientation(Point point) {
        try {
            return this.zza.pointToOrientation(ObjectWrapper.wrap(point));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnStreetViewPanoramaCameraChangeListener(OnStreetViewPanoramaCameraChangeListener listener) {
        if (listener == null) {
            try {
                this.zza.setOnStreetViewPanoramaCameraChangeListener((zzbj) null);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        } else {
            this.zza.setOnStreetViewPanoramaCameraChangeListener(new zzaj(this, listener));
        }
    }

    public final void setOnStreetViewPanoramaChangeListener(OnStreetViewPanoramaChangeListener listener) {
        if (listener == null) {
            try {
                this.zza.setOnStreetViewPanoramaChangeListener((zzbl) null);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        } else {
            this.zza.setOnStreetViewPanoramaChangeListener(new zzai(this, listener));
        }
    }

    public final void setOnStreetViewPanoramaClickListener(OnStreetViewPanoramaClickListener listener) {
        if (listener == null) {
            try {
                this.zza.setOnStreetViewPanoramaClickListener((zzbn) null);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        } else {
            this.zza.setOnStreetViewPanoramaClickListener(new zzak(this, listener));
        }
    }

    public final void setOnStreetViewPanoramaLongClickListener(OnStreetViewPanoramaLongClickListener listener) {
        if (listener == null) {
            try {
                this.zza.setOnStreetViewPanoramaLongClickListener((zzbp) null);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        } else {
            this.zza.setOnStreetViewPanoramaLongClickListener(new zzal(this, listener));
        }
    }

    public void setPanningGesturesEnabled(boolean enablePanning) {
        try {
            this.zza.enablePanning(enablePanning);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPosition(LatLng position) {
        try {
            this.zza.setPosition(position);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setStreetNamesEnabled(boolean enableStreetNames) {
        try {
            this.zza.enableStreetNames(enableStreetNames);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setUserNavigationEnabled(boolean enableUserNavigation) {
        try {
            this.zza.enableUserNavigation(enableUserNavigation);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZoomGesturesEnabled(boolean enableZoom) {
        try {
            this.zza.enableZoom(enableZoom);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPosition(LatLng position, int radius) {
        try {
            this.zza.setPositionWithRadius(position, radius);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPosition(LatLng position, int radius, StreetViewSource source) {
        try {
            this.zza.setPositionWithRadiusAndSource(position, radius, source);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPosition(LatLng position, StreetViewSource source) {
        try {
            this.zza.setPositionWithSource(position, source);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPosition(String panoId) {
        try {
            this.zza.setPositionWithID(panoId);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
