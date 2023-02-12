package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public final class Tile extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Tile> CREATOR = new zzs();
    public final byte[] data;
    public final int height;
    public final int width;

    public Tile(int width2, int height2, byte[] data2) {
        this.width = width2;
        this.height = height2;
        this.data = data2;
    }

    public void writeToParcel(Parcel out, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(out);
        SafeParcelWriter.writeInt(out, 2, this.width);
        SafeParcelWriter.writeInt(out, 3, this.height);
        SafeParcelWriter.writeByteArray(out, 4, this.data, false);
        SafeParcelWriter.finishObjectHeader(out, beginObjectHeader);
    }
}
