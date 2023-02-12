package com.google.android.gms.maps.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.maps.GoogleMapOptions;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public final class LatLngBounds extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<LatLngBounds> CREATOR = new zzf();
    public final LatLng northeast;
    public final LatLng southwest;

    /* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
    public static final class Builder {
        private double zza = Double.POSITIVE_INFINITY;
        private double zzb = Double.NEGATIVE_INFINITY;
        private double zzc = Double.NaN;
        private double zzd = Double.NaN;

        public LatLngBounds build() {
            Preconditions.checkState(!Double.isNaN(this.zzc), "no included points");
            return new LatLngBounds(new LatLng(this.zza, this.zzc), new LatLng(this.zzb, this.zzd));
        }

        public Builder include(LatLng point) {
            Preconditions.checkNotNull(point, "point must not be null");
            this.zza = Math.min(this.zza, point.latitude);
            this.zzb = Math.max(this.zzb, point.latitude);
            double d = point.longitude;
            if (Double.isNaN(this.zzc)) {
                this.zzc = d;
                this.zzd = d;
            } else {
                double d2 = this.zzc;
                double d3 = this.zzd;
                if (d2 > d3 ? !(d2 <= d || d <= d3) : !(d2 <= d && d <= d3)) {
                    Parcelable.Creator<LatLngBounds> creator = LatLngBounds.CREATOR;
                    if (((d2 - d) + 360.0d) % 360.0d < ((d - d3) + 360.0d) % 360.0d) {
                        this.zzc = d;
                    } else {
                        this.zzd = d;
                    }
                }
            }
            return this;
        }
    }

    public LatLngBounds(LatLng southwest2, LatLng northeast2) {
        Preconditions.checkNotNull(southwest2, "southwest must not be null.");
        Preconditions.checkNotNull(northeast2, "northeast must not be null.");
        double d = northeast2.latitude;
        double d2 = southwest2.latitude;
        Preconditions.checkArgument(d >= d2, "southern latitude exceeds northern latitude (%s > %s)", Double.valueOf(d2), Double.valueOf(northeast2.latitude));
        this.southwest = southwest2;
        this.northeast = northeast2;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static LatLngBounds createFromAttributes(Context context, AttributeSet attrs) {
        return GoogleMapOptions.zzb(context, attrs);
    }

    private final boolean zza(double d) {
        double d2 = this.southwest.longitude;
        double d3 = this.northeast.longitude;
        int i = (d2 > d3 ? 1 : (d2 == d3 ? 0 : -1));
        int i2 = (d2 > d ? 1 : (d2 == d ? 0 : -1));
        return i <= 0 ? i2 <= 0 && d <= d3 : i2 <= 0 || d <= d3;
    }

    public boolean contains(LatLng point) {
        LatLng latLng = (LatLng) Preconditions.checkNotNull(point, "point must not be null.");
        double d = latLng.latitude;
        return this.southwest.latitude <= d && d <= this.northeast.latitude && zza(latLng.longitude);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) o;
        return this.southwest.equals(latLngBounds.southwest) && this.northeast.equals(latLngBounds.northeast);
    }

    public LatLng getCenter() {
        double d;
        LatLng latLng = this.southwest;
        double d2 = latLng.latitude;
        LatLng latLng2 = this.northeast;
        double d3 = (d2 + latLng2.latitude) / 2.0d;
        double d4 = latLng2.longitude;
        double d5 = latLng.longitude;
        if (d5 <= d4) {
            d = (d4 + d5) / 2.0d;
        } else {
            d = ((d4 + 360.0d) + d5) / 2.0d;
        }
        return new LatLng(d3, d);
    }

    public int hashCode() {
        return Objects.hashCode(this.southwest, this.northeast);
    }

    public LatLngBounds including(LatLng point) {
        LatLng latLng = (LatLng) Preconditions.checkNotNull(point, "point must not be null.");
        double min = Math.min(this.southwest.latitude, latLng.latitude);
        double max = Math.max(this.northeast.latitude, latLng.latitude);
        double d = this.northeast.longitude;
        double d2 = this.southwest.longitude;
        double d3 = latLng.longitude;
        if (!zza(d3)) {
            if (((d2 - d3) + 360.0d) % 360.0d < ((d3 - d) + 360.0d) % 360.0d) {
                d2 = d3;
            } else {
                d = d3;
            }
        }
        return new LatLngBounds(new LatLng(min, d2), new LatLng(max, d));
    }

    public String toString() {
        return Objects.toStringHelper(this).add("southwest", this.southwest).add("northeast", this.northeast).toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(out);
        SafeParcelWriter.writeParcelable(out, 2, this.southwest, flags, false);
        SafeParcelWriter.writeParcelable(out, 3, this.northeast, flags, false);
        SafeParcelWriter.finishObjectHeader(out, beginObjectHeader);
    }
}
