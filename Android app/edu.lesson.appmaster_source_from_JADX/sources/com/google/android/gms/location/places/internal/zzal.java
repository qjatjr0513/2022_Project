package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Collections;
import java.util.List;

public final class zzal extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzal> CREATOR = new zzaq();
    private final List<zzao> zzcl;
    private final List<zzan> zzcm;

    zzal(List<zzao> list, List<zzan> list2) {
        this.zzcl = Collections.unmodifiableList(list);
        this.zzcm = Collections.unmodifiableList(list2);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zzcl, false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzcm, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
