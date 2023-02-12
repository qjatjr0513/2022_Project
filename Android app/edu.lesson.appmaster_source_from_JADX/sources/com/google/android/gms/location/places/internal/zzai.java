package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.location.places.Place;
import java.util.Collections;
import java.util.List;

public final class zzai extends AbstractSafeParcelable implements Place.ExtendedDetails {
    public static final Parcelable.Creator<zzai> CREATOR = new zzah();
    private final float zzcb;
    private final int zzcc;
    private final List<Integer> zzg;
    private final String zzh;
    private final Uri zzi;

    zzai(List<Integer> list, String str, Uri uri, float f, int i) {
        this.zzg = Collections.unmodifiableList(list);
        this.zzh = str;
        this.zzi = uri;
        this.zzcb = f;
        this.zzcc = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIntegerList(parcel, 1, this.zzg, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzh, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzi, i, false);
        SafeParcelWriter.writeFloat(parcel, 4, this.zzcb);
        SafeParcelWriter.writeInt(parcel, 5, this.zzcc);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
