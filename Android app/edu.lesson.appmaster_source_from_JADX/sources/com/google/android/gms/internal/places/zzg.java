package com.google.android.gms.internal.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

@Deprecated
public final class zzg extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzg> CREATOR = new zzf();
    private static final zzg zzda = new zzg("Home");
    private static final zzg zzdb = new zzg("Work");
    private final String zzdc;

    zzg(String str) {
        this.zzdc = str;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzdc);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzg)) {
            return false;
        }
        return Objects.equal(this.zzdc, ((zzg) obj).zzdc);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("alias", this.zzdc).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzdc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
