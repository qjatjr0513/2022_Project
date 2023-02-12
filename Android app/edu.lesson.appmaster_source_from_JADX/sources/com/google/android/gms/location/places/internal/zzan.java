package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Collections;
import java.util.List;

public final class zzan extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzan> CREATOR = new zzf();
    private final int endYear;
    private final int startYear;
    private final int zzcn;
    private final int zzco;
    private final int zzcp;
    private final int zzcq;
    private final List<zzao> zzcr;

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.startYear);
        SafeParcelWriter.writeInt(parcel, 2, this.zzcn);
        SafeParcelWriter.writeInt(parcel, 3, this.zzco);
        SafeParcelWriter.writeInt(parcel, 4, this.endYear);
        SafeParcelWriter.writeInt(parcel, 5, this.zzcp);
        SafeParcelWriter.writeInt(parcel, 6, this.zzcq);
        SafeParcelWriter.writeTypedList(parcel, 7, this.zzcr, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    zzan(int i, int i2, int i3, int i4, int i5, int i6, List<zzao> list) {
        this.startYear = i;
        this.zzcn = i2;
        this.zzco = i3;
        this.endYear = i4;
        this.zzcp = i5;
        this.zzcq = i6;
        this.zzcr = Collections.unmodifiableList(list);
    }
}
