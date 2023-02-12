package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

public final class PlaceEntity extends AbstractSafeParcelable implements ReflectedParcelable, Place {
    public static final Parcelable.Creator<PlaceEntity> CREATOR = new zzag();
    private final LatLng latLng;
    private Locale locale;
    private final String name;
    private final String zzbw;
    private final float zzbx;
    private final LatLngBounds zzby;
    private final String zzbz;
    private final boolean zzca;
    private final float zzcb;
    private final int zzcc;
    private final List<String> zzcd;
    private final zzal zzce;
    private final zzai zzcf;
    private final String zzcg;
    private final String zzf;
    private final List<Integer> zzg;
    private final String zzh;
    private final Uri zzi;

    PlaceEntity(String str, List<Integer> list, String str2, String str3, String str4, List<String> list2, LatLng latLng2, float f, LatLngBounds latLngBounds, String str5, Uri uri, boolean z, float f2, int i, zzal zzal, zzai zzai, String str6) {
        this.zzbw = str;
        this.zzg = Collections.unmodifiableList(list);
        this.name = str2;
        this.zzf = str3;
        this.zzh = str4;
        this.zzcd = list2 != null ? list2 : Collections.emptyList();
        this.latLng = latLng2;
        this.zzbx = f;
        this.zzby = latLngBounds;
        this.zzbz = str5 != null ? str5 : "UTC";
        this.zzi = uri;
        this.zzca = z;
        this.zzcb = f2;
        this.zzcc = i;
        this.locale = null;
        this.zzce = zzal;
        this.zzcf = zzai;
        this.zzcg = str6;
    }

    public static class zzb {
        private LatLng latLng;
        private String name;
        private String zzbw;
        private float zzbx;
        private LatLngBounds zzby;
        private boolean zzca;
        private float zzcb = -1.0f;
        private int zzcc = -1;
        private List<String> zzcd;
        private zzal zzce;
        private String zzcg;
        private List<Integer> zzch;
        private zzai zzci;
        private String zzf;
        private String zzh;
        private Uri zzi;

        public final zzb zzb(String str) {
            this.zzbw = str;
            return this;
        }

        public final zzb zzc(String str) {
            this.name = str;
            return this;
        }

        public final zzb zzb(LatLng latLng2) {
            this.latLng = latLng2;
            return this;
        }

        public final zzb zzb(float f) {
            this.zzbx = f;
            return this;
        }

        public final zzb zzb(LatLngBounds latLngBounds) {
            this.zzby = latLngBounds;
            return this;
        }

        public final zzb zzb(Uri uri) {
            this.zzi = uri;
            return this;
        }

        public final zzb zzb(boolean z) {
            this.zzca = z;
            return this;
        }

        public final zzb zzc(float f) {
            this.zzcb = f;
            return this;
        }

        public final zzb zzc(int i) {
            this.zzcc = i;
            return this;
        }

        public final zzb zzc(List<Integer> list) {
            this.zzch = list;
            return this;
        }

        public final zzb zzd(String str) {
            this.zzf = str;
            return this;
        }

        public final zzb zze(String str) {
            this.zzh = str;
            return this;
        }

        public final zzb zzd(List<String> list) {
            this.zzcd = list;
            return this;
        }

        public final zzb zzb(zzal zzal) {
            this.zzce = zzal;
            return this;
        }

        public final zzb zzb(zzai zzai) {
            this.zzci = zzai;
            return this;
        }

        public final zzb zzf(String str) {
            this.zzcg = str;
            return this;
        }

        public final PlaceEntity zzj() {
            return new PlaceEntity(this.zzbw, this.zzch, this.name, this.zzf, this.zzh, this.zzcd, this.latLng, this.zzbx, this.zzby, (String) null, this.zzi, this.zzca, this.zzcb, this.zzcc, this.zzce, this.zzci, this.zzcg);
        }
    }

    public final String getId() {
        return this.zzbw;
    }

    public final List<Integer> getPlaceTypes() {
        return this.zzg;
    }

    public final Locale getLocale() {
        return this.locale;
    }

    public final void setLocale(Locale locale2) {
        this.locale = locale2;
    }

    public final LatLng getLatLng() {
        return this.latLng;
    }

    public final LatLngBounds getViewport() {
        return this.zzby;
    }

    public final Uri getWebsiteUri() {
        return this.zzi;
    }

    @Nullable
    public final CharSequence getAttributions() {
        return zzi.zzc(this.zzcd);
    }

    public final float getRating() {
        return this.zzcb;
    }

    public final int getPriceLevel() {
        return this.zzcc;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("id", this.zzbw).add("placeTypes", this.zzg).add("locale", this.locale).add(AppMeasurementSdk.ConditionalUserProperty.NAME, this.name).add("address", this.zzf).add("phoneNumber", this.zzh).add("latlng", this.latLng).add("viewport", this.zzby).add("websiteUri", this.zzi).add("isPermanentlyClosed", Boolean.valueOf(this.zzca)).add("priceLevel", Integer.valueOf(this.zzcc)).toString();
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzbw, this.locale);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceEntity)) {
            return false;
        }
        PlaceEntity placeEntity = (PlaceEntity) obj;
        if (!this.zzbw.equals(placeEntity.zzbw) || !Objects.equal(this.locale, placeEntity.locale)) {
            return false;
        }
        return true;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getId(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, getLatLng(), i, false);
        SafeParcelWriter.writeFloat(parcel, 5, this.zzbx);
        SafeParcelWriter.writeParcelable(parcel, 6, getViewport(), i, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzbz, false);
        SafeParcelWriter.writeParcelable(parcel, 8, getWebsiteUri(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzca);
        SafeParcelWriter.writeFloat(parcel, 10, getRating());
        SafeParcelWriter.writeInt(parcel, 11, getPriceLevel());
        SafeParcelWriter.writeString(parcel, 14, (String) getAddress(), false);
        SafeParcelWriter.writeString(parcel, 15, (String) getPhoneNumber(), false);
        SafeParcelWriter.writeStringList(parcel, 17, this.zzcd, false);
        SafeParcelWriter.writeString(parcel, 19, (String) getName(), false);
        SafeParcelWriter.writeIntegerList(parcel, 20, getPlaceTypes(), false);
        SafeParcelWriter.writeParcelable(parcel, 21, this.zzce, i, false);
        SafeParcelWriter.writeParcelable(parcel, 22, this.zzcf, i, false);
        SafeParcelWriter.writeString(parcel, 23, this.zzcg, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean isDataValid() {
        return true;
    }

    public final /* synthetic */ CharSequence getPhoneNumber() {
        return this.zzh;
    }

    public final /* synthetic */ CharSequence getName() {
        return this.name;
    }

    public final /* synthetic */ CharSequence getAddress() {
        return this.zzf;
    }

    public final /* bridge */ /* synthetic */ Object freeze() {
        return this;
    }
}
