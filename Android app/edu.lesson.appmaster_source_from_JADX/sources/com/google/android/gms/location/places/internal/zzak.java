package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;

public final class zzak extends AbstractSafeParcelable implements PlaceLikelihood {
    public static final Parcelable.Creator<zzak> CREATOR = new zzaj();
    private final PlaceEntity zzcj;
    private final float zzck;

    zzak(PlaceEntity placeEntity, float f) {
        this.zzcj = placeEntity;
        this.zzck = f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzak)) {
            return false;
        }
        zzak zzak = (zzak) obj;
        if (!this.zzcj.equals(zzak.zzcj) || this.zzck != zzak.zzck) {
            return false;
        }
        return true;
    }

    public final float getLikelihood() {
        return this.zzck;
    }

    public final Place getPlace() {
        return this.zzcj;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzcj, Float.valueOf(this.zzck));
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("place", this.zzcj).add("likelihood", Float.valueOf(this.zzck)).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzcj, i, false);
        SafeParcelWriter.writeFloat(parcel, 2, this.zzck);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final /* bridge */ /* synthetic */ Object freeze() {
        return this;
    }
}
