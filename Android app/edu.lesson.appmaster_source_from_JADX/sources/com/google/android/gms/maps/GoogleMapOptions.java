package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public final class GoogleMapOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<GoogleMapOptions> CREATOR = new zzab();
    private Boolean zza;
    private Boolean zzb;
    private int zzc = -1;
    private CameraPosition zzd;
    private Boolean zze;
    private Boolean zzf;
    private Boolean zzg;
    private Boolean zzh;
    private Boolean zzi;
    private Boolean zzj;
    private Boolean zzk;
    private Boolean zzl;
    private Boolean zzm;
    private Float zzn = null;
    private Float zzo = null;
    private LatLngBounds zzp = null;
    private Boolean zzq;
    private Integer zzr = null;
    private String zzs = null;

    public GoogleMapOptions() {
    }

    public static GoogleMapOptions createFromAttributes(Context context, AttributeSet attrs) {
        String string;
        if (context == null || attrs == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attrs, C2422R.styleable.MapAttrs);
        GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_mapType)) {
            googleMapOptions.mapType(obtainAttributes.getInt(C2422R.styleable.MapAttrs_mapType, -1));
        }
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_zOrderOnTop)) {
            googleMapOptions.zOrderOnTop(obtainAttributes.getBoolean(C2422R.styleable.MapAttrs_zOrderOnTop, false));
        }
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_useViewLifecycle)) {
            googleMapOptions.useViewLifecycleInFragment(obtainAttributes.getBoolean(C2422R.styleable.MapAttrs_useViewLifecycle, false));
        }
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_uiCompass)) {
            googleMapOptions.compassEnabled(obtainAttributes.getBoolean(C2422R.styleable.MapAttrs_uiCompass, true));
        }
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_uiRotateGestures)) {
            googleMapOptions.rotateGesturesEnabled(obtainAttributes.getBoolean(C2422R.styleable.MapAttrs_uiRotateGestures, true));
        }
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_uiScrollGesturesDuringRotateOrZoom)) {
            googleMapOptions.scrollGesturesEnabledDuringRotateOrZoom(obtainAttributes.getBoolean(C2422R.styleable.MapAttrs_uiScrollGesturesDuringRotateOrZoom, true));
        }
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_uiScrollGestures)) {
            googleMapOptions.scrollGesturesEnabled(obtainAttributes.getBoolean(C2422R.styleable.MapAttrs_uiScrollGestures, true));
        }
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_uiTiltGestures)) {
            googleMapOptions.tiltGesturesEnabled(obtainAttributes.getBoolean(C2422R.styleable.MapAttrs_uiTiltGestures, true));
        }
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_uiZoomGestures)) {
            googleMapOptions.zoomGesturesEnabled(obtainAttributes.getBoolean(C2422R.styleable.MapAttrs_uiZoomGestures, true));
        }
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_uiZoomControls)) {
            googleMapOptions.zoomControlsEnabled(obtainAttributes.getBoolean(C2422R.styleable.MapAttrs_uiZoomControls, true));
        }
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_liteMode)) {
            googleMapOptions.liteMode(obtainAttributes.getBoolean(C2422R.styleable.MapAttrs_liteMode, false));
        }
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_uiMapToolbar)) {
            googleMapOptions.mapToolbarEnabled(obtainAttributes.getBoolean(C2422R.styleable.MapAttrs_uiMapToolbar, true));
        }
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_ambientEnabled)) {
            googleMapOptions.ambientEnabled(obtainAttributes.getBoolean(C2422R.styleable.MapAttrs_ambientEnabled, false));
        }
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_cameraMinZoomPreference)) {
            googleMapOptions.minZoomPreference(obtainAttributes.getFloat(C2422R.styleable.MapAttrs_cameraMinZoomPreference, Float.NEGATIVE_INFINITY));
        }
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_cameraMinZoomPreference)) {
            googleMapOptions.maxZoomPreference(obtainAttributes.getFloat(C2422R.styleable.MapAttrs_cameraMaxZoomPreference, Float.POSITIVE_INFINITY));
        }
        TypedArray obtainAttributes2 = context.getResources().obtainAttributes(attrs, new int[]{zzc(context, "backgroundColor"), zzc(context, "mapId")});
        if (obtainAttributes2.hasValue(0)) {
            googleMapOptions.backgroundColor(Integer.valueOf(obtainAttributes2.getColor(0, 0)));
        }
        if (obtainAttributes2.hasValue(1) && (string = obtainAttributes2.getString(1)) != null && !string.isEmpty()) {
            googleMapOptions.mapId(string);
        }
        obtainAttributes2.recycle();
        googleMapOptions.latLngBoundsForCameraTarget(zzb(context, attrs));
        googleMapOptions.camera(zza(context, attrs));
        obtainAttributes.recycle();
        return googleMapOptions;
    }

    public static CameraPosition zza(Context context, AttributeSet attributeSet) {
        float f;
        float f2;
        if (context == null || attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, C2422R.styleable.MapAttrs);
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_cameraTargetLat)) {
            f = obtainAttributes.getFloat(C2422R.styleable.MapAttrs_cameraTargetLat, 0.0f);
        } else {
            f = 0.0f;
        }
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_cameraTargetLng)) {
            f2 = obtainAttributes.getFloat(C2422R.styleable.MapAttrs_cameraTargetLng, 0.0f);
        } else {
            f2 = 0.0f;
        }
        LatLng latLng = new LatLng((double) f, (double) f2);
        CameraPosition.Builder builder = CameraPosition.builder();
        builder.target(latLng);
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_cameraZoom)) {
            builder.zoom(obtainAttributes.getFloat(C2422R.styleable.MapAttrs_cameraZoom, 0.0f));
        }
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_cameraBearing)) {
            builder.bearing(obtainAttributes.getFloat(C2422R.styleable.MapAttrs_cameraBearing, 0.0f));
        }
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_cameraTilt)) {
            builder.tilt(obtainAttributes.getFloat(C2422R.styleable.MapAttrs_cameraTilt, 0.0f));
        }
        obtainAttributes.recycle();
        return builder.build();
    }

    public static LatLngBounds zzb(Context context, AttributeSet attributeSet) {
        Float f;
        Float f2;
        Float f3;
        Float f4;
        if (context == null || attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, C2422R.styleable.MapAttrs);
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_latLngBoundsSouthWestLatitude)) {
            f = Float.valueOf(obtainAttributes.getFloat(C2422R.styleable.MapAttrs_latLngBoundsSouthWestLatitude, 0.0f));
        } else {
            f = null;
        }
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_latLngBoundsSouthWestLongitude)) {
            f2 = Float.valueOf(obtainAttributes.getFloat(C2422R.styleable.MapAttrs_latLngBoundsSouthWestLongitude, 0.0f));
        } else {
            f2 = null;
        }
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_latLngBoundsNorthEastLatitude)) {
            f3 = Float.valueOf(obtainAttributes.getFloat(C2422R.styleable.MapAttrs_latLngBoundsNorthEastLatitude, 0.0f));
        } else {
            f3 = null;
        }
        if (obtainAttributes.hasValue(C2422R.styleable.MapAttrs_latLngBoundsNorthEastLongitude)) {
            f4 = Float.valueOf(obtainAttributes.getFloat(C2422R.styleable.MapAttrs_latLngBoundsNorthEastLongitude, 0.0f));
        } else {
            f4 = null;
        }
        obtainAttributes.recycle();
        if (f == null || f2 == null || f3 == null || f4 == null) {
            return null;
        }
        return new LatLngBounds(new LatLng((double) f.floatValue(), (double) f2.floatValue()), new LatLng((double) f3.floatValue(), (double) f4.floatValue()));
    }

    private static int zzc(Context context, String str) {
        return context.getResources().getIdentifier(str, "attr", context.getPackageName());
    }

    public GoogleMapOptions ambientEnabled(boolean enabled) {
        this.zzm = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions backgroundColor(Integer num) {
        this.zzr = num;
        return this;
    }

    public GoogleMapOptions camera(CameraPosition cameraPosition) {
        this.zzd = cameraPosition;
        return this;
    }

    public GoogleMapOptions compassEnabled(boolean enabled) {
        this.zzf = Boolean.valueOf(enabled);
        return this;
    }

    public Boolean getAmbientEnabled() {
        return this.zzm;
    }

    public Integer getBackgroundColor() {
        return this.zzr;
    }

    public CameraPosition getCamera() {
        return this.zzd;
    }

    public Boolean getCompassEnabled() {
        return this.zzf;
    }

    public LatLngBounds getLatLngBoundsForCameraTarget() {
        return this.zzp;
    }

    public Boolean getLiteMode() {
        return this.zzk;
    }

    public String getMapId() {
        return this.zzs;
    }

    public Boolean getMapToolbarEnabled() {
        return this.zzl;
    }

    public int getMapType() {
        return this.zzc;
    }

    public Float getMaxZoomPreference() {
        return this.zzo;
    }

    public Float getMinZoomPreference() {
        return this.zzn;
    }

    public Boolean getRotateGesturesEnabled() {
        return this.zzj;
    }

    public Boolean getScrollGesturesEnabled() {
        return this.zzg;
    }

    public Boolean getScrollGesturesEnabledDuringRotateOrZoom() {
        return this.zzq;
    }

    public Boolean getTiltGesturesEnabled() {
        return this.zzi;
    }

    public Boolean getUseViewLifecycleInFragment() {
        return this.zzb;
    }

    public Boolean getZOrderOnTop() {
        return this.zza;
    }

    public Boolean getZoomControlsEnabled() {
        return this.zze;
    }

    public Boolean getZoomGesturesEnabled() {
        return this.zzh;
    }

    public GoogleMapOptions latLngBoundsForCameraTarget(LatLngBounds latLngBounds) {
        this.zzp = latLngBounds;
        return this;
    }

    public GoogleMapOptions liteMode(boolean enabled) {
        this.zzk = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions mapId(String str) {
        this.zzs = str;
        return this;
    }

    public GoogleMapOptions mapToolbarEnabled(boolean enabled) {
        this.zzl = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions mapType(int i) {
        this.zzc = i;
        return this;
    }

    public GoogleMapOptions maxZoomPreference(float maxZoomPreference) {
        this.zzo = Float.valueOf(maxZoomPreference);
        return this;
    }

    public GoogleMapOptions minZoomPreference(float minZoomPreference) {
        this.zzn = Float.valueOf(minZoomPreference);
        return this;
    }

    public GoogleMapOptions rotateGesturesEnabled(boolean enabled) {
        this.zzj = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions scrollGesturesEnabled(boolean enabled) {
        this.zzg = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions scrollGesturesEnabledDuringRotateOrZoom(boolean enabled) {
        this.zzq = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions tiltGesturesEnabled(boolean enabled) {
        this.zzi = Boolean.valueOf(enabled);
        return this;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("MapType", Integer.valueOf(this.zzc)).add("LiteMode", this.zzk).add("Camera", this.zzd).add("CompassEnabled", this.zzf).add("ZoomControlsEnabled", this.zze).add("ScrollGesturesEnabled", this.zzg).add("ZoomGesturesEnabled", this.zzh).add("TiltGesturesEnabled", this.zzi).add("RotateGesturesEnabled", this.zzj).add("ScrollGesturesEnabledDuringRotateOrZoom", this.zzq).add("MapToolbarEnabled", this.zzl).add("AmbientEnabled", this.zzm).add("MinZoomPreference", this.zzn).add("MaxZoomPreference", this.zzo).add("BackgroundColor", this.zzr).add("LatLngBoundsForCameraTarget", this.zzp).add("ZOrderOnTop", this.zza).add("UseViewLifecycleInFragment", this.zzb).toString();
    }

    public GoogleMapOptions useViewLifecycleInFragment(boolean useViewLifecycleInFragment) {
        this.zzb = Boolean.valueOf(useViewLifecycleInFragment);
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(out);
        SafeParcelWriter.writeByte(out, 2, zza.zza(this.zza));
        SafeParcelWriter.writeByte(out, 3, zza.zza(this.zzb));
        SafeParcelWriter.writeInt(out, 4, getMapType());
        SafeParcelWriter.writeParcelable(out, 5, getCamera(), flags, false);
        SafeParcelWriter.writeByte(out, 6, zza.zza(this.zze));
        SafeParcelWriter.writeByte(out, 7, zza.zza(this.zzf));
        SafeParcelWriter.writeByte(out, 8, zza.zza(this.zzg));
        SafeParcelWriter.writeByte(out, 9, zza.zza(this.zzh));
        SafeParcelWriter.writeByte(out, 10, zza.zza(this.zzi));
        SafeParcelWriter.writeByte(out, 11, zza.zza(this.zzj));
        SafeParcelWriter.writeByte(out, 12, zza.zza(this.zzk));
        SafeParcelWriter.writeByte(out, 14, zza.zza(this.zzl));
        SafeParcelWriter.writeByte(out, 15, zza.zza(this.zzm));
        SafeParcelWriter.writeFloatObject(out, 16, getMinZoomPreference(), false);
        SafeParcelWriter.writeFloatObject(out, 17, getMaxZoomPreference(), false);
        SafeParcelWriter.writeParcelable(out, 18, getLatLngBoundsForCameraTarget(), flags, false);
        SafeParcelWriter.writeByte(out, 19, zza.zza(this.zzq));
        SafeParcelWriter.writeIntegerObject(out, 20, getBackgroundColor(), false);
        SafeParcelWriter.writeString(out, 21, getMapId(), false);
        SafeParcelWriter.finishObjectHeader(out, beginObjectHeader);
    }

    public GoogleMapOptions zOrderOnTop(boolean zOrderOnTop) {
        this.zza = Boolean.valueOf(zOrderOnTop);
        return this;
    }

    public GoogleMapOptions zoomControlsEnabled(boolean enabled) {
        this.zze = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions zoomGesturesEnabled(boolean enabled) {
        this.zzh = Boolean.valueOf(enabled);
        return this;
    }

    GoogleMapOptions(byte b, byte b2, int i, CameraPosition cameraPosition, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, byte b9, byte b10, byte b11, Float f, Float f2, LatLngBounds latLngBounds, byte b12, Integer num, String str) {
        this.zza = zza.zzb(b);
        this.zzb = zza.zzb(b2);
        this.zzc = i;
        this.zzd = cameraPosition;
        this.zze = zza.zzb(b3);
        this.zzf = zza.zzb(b4);
        this.zzg = zza.zzb(b5);
        this.zzh = zza.zzb(b6);
        this.zzi = zza.zzb(b7);
        this.zzj = zza.zzb(b8);
        this.zzk = zza.zzb(b9);
        this.zzl = zza.zzb(b10);
        this.zzm = zza.zzb(b11);
        this.zzn = f;
        this.zzo = f2;
        this.zzp = latLngBounds;
        this.zzq = zza.zzb(b12);
        this.zzr = num;
        this.zzs = str;
    }
}
