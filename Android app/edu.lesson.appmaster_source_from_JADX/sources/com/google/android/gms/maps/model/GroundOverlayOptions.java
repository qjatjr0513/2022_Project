package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public final class GroundOverlayOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GroundOverlayOptions> CREATOR = new zzd();
    public static final float NO_DIMENSION = -1.0f;
    private BitmapDescriptor zza;
    private LatLng zzb;
    private float zzc;
    private float zzd;
    private LatLngBounds zze;
    private float zzf;
    private float zzg;
    private boolean zzh = true;
    private float zzi = 0.0f;
    private float zzj = 0.5f;
    private float zzk = 0.5f;
    private boolean zzl = false;

    public GroundOverlayOptions() {
    }

    private final GroundOverlayOptions zza(LatLng latLng, float f, float f2) {
        this.zzb = latLng;
        this.zzc = f;
        this.zzd = f2;
        return this;
    }

    public GroundOverlayOptions anchor(float f, float f2) {
        this.zzj = f;
        this.zzk = f2;
        return this;
    }

    public GroundOverlayOptions bearing(float f) {
        this.zzf = ((f % 360.0f) + 360.0f) % 360.0f;
        return this;
    }

    public GroundOverlayOptions clickable(boolean z) {
        this.zzl = z;
        return this;
    }

    public float getAnchorU() {
        return this.zzj;
    }

    public float getAnchorV() {
        return this.zzk;
    }

    public float getBearing() {
        return this.zzf;
    }

    public LatLngBounds getBounds() {
        return this.zze;
    }

    public float getHeight() {
        return this.zzd;
    }

    public BitmapDescriptor getImage() {
        return this.zza;
    }

    public LatLng getLocation() {
        return this.zzb;
    }

    public float getTransparency() {
        return this.zzi;
    }

    public float getWidth() {
        return this.zzc;
    }

    public float getZIndex() {
        return this.zzg;
    }

    public GroundOverlayOptions image(BitmapDescriptor imageDescriptor) {
        Preconditions.checkNotNull(imageDescriptor, "imageDescriptor must not be null");
        this.zza = imageDescriptor;
        return this;
    }

    public boolean isClickable() {
        return this.zzl;
    }

    public boolean isVisible() {
        return this.zzh;
    }

    public GroundOverlayOptions position(LatLng location, float width) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        if (this.zze == null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "Position has already been set using positionFromBounds");
        if (location != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "Location must be specified");
        if (width < 0.0f) {
            z3 = false;
        }
        Preconditions.checkArgument(z3, "Width must be non-negative");
        zza(location, width, -1.0f);
        return this;
    }

    public GroundOverlayOptions positionFromBounds(LatLngBounds bounds) {
        LatLng latLng = this.zzb;
        boolean z = latLng == null;
        String valueOf = String.valueOf(latLng);
        String.valueOf(valueOf).length();
        Preconditions.checkState(z, "Position has already been set using position: ".concat(String.valueOf(valueOf)));
        this.zze = bounds;
        return this;
    }

    public GroundOverlayOptions transparency(float transparency) {
        boolean z = false;
        if (transparency >= 0.0f && transparency <= 1.0f) {
            z = true;
        }
        Preconditions.checkArgument(z, "Transparency must be in the range [0..1]");
        this.zzi = transparency;
        return this;
    }

    public GroundOverlayOptions visible(boolean z) {
        this.zzh = z;
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(out);
        SafeParcelWriter.writeIBinder(out, 2, this.zza.zza().asBinder(), false);
        SafeParcelWriter.writeParcelable(out, 3, getLocation(), flags, false);
        SafeParcelWriter.writeFloat(out, 4, getWidth());
        SafeParcelWriter.writeFloat(out, 5, getHeight());
        SafeParcelWriter.writeParcelable(out, 6, getBounds(), flags, false);
        SafeParcelWriter.writeFloat(out, 7, getBearing());
        SafeParcelWriter.writeFloat(out, 8, getZIndex());
        SafeParcelWriter.writeBoolean(out, 9, isVisible());
        SafeParcelWriter.writeFloat(out, 10, getTransparency());
        SafeParcelWriter.writeFloat(out, 11, getAnchorU());
        SafeParcelWriter.writeFloat(out, 12, getAnchorV());
        SafeParcelWriter.writeBoolean(out, 13, isClickable());
        SafeParcelWriter.finishObjectHeader(out, beginObjectHeader);
    }

    public GroundOverlayOptions zIndex(float f) {
        this.zzg = f;
        return this;
    }

    GroundOverlayOptions(IBinder iBinder, LatLng latLng, float f, float f2, LatLngBounds latLngBounds, float f3, float f4, boolean z, float f5, float f6, float f7, boolean z2) {
        this.zza = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder));
        this.zzb = latLng;
        this.zzc = f;
        this.zzd = f2;
        this.zze = latLngBounds;
        this.zzf = f3;
        this.zzg = f4;
        this.zzh = z;
        this.zzi = f5;
        this.zzj = f6;
        this.zzk = f7;
        this.zzl = z2;
    }

    public GroundOverlayOptions position(LatLng location, float width, float height) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = true;
        if (this.zze == null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "Position has already been set using positionFromBounds");
        if (location != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "Location must be specified");
        if (width >= 0.0f) {
            z3 = true;
        } else {
            z3 = false;
        }
        Preconditions.checkArgument(z3, "Width must be non-negative");
        if (height < 0.0f) {
            z4 = false;
        }
        Preconditions.checkArgument(z4, "Height must be non-negative");
        zza(location, width, height);
        return this;
    }
}
