package com.google.android.gms.internal.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

@Deprecated
public final class zzi extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzi> CREATOR = new zzj();
    private final String placeId;
    private final String zzav;
    private final List<zzg> zzdd;

    zzi(String str, String str2, List<zzg> list) {
        this.zzav = str;
        this.placeId = str2;
        this.zzdd = list;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzav, this.placeId, this.zzdd);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("accountName", this.zzav).add("placeId", this.placeId).add("placeAliases", this.zzdd).toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzi)) {
            return false;
        }
        zzi zzi = (zzi) obj;
        if (!this.zzav.equals(zzi.zzav) || !this.placeId.equals(zzi.placeId) || !this.zzdd.equals(zzi.zzdd)) {
            return false;
        }
        return true;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzav, false);
        SafeParcelWriter.writeString(parcel, 2, this.placeId, false);
        SafeParcelWriter.writeTypedList(parcel, 6, this.zzdd, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
