package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

@Deprecated
public class PlacePhotoMetadataResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<PlacePhotoMetadataResult> CREATOR = new zzk();
    private final DataHolder zzaf;
    private final PlacePhotoMetadataBuffer zzag;
    private final Status zzp;

    public PlacePhotoMetadataResult(Status status, DataHolder dataHolder) {
        this.zzp = status;
        this.zzaf = dataHolder;
        if (dataHolder == null) {
            this.zzag = null;
        } else {
            this.zzag = new PlacePhotoMetadataBuffer(dataHolder);
        }
    }

    public Status getStatus() {
        return this.zzp;
    }

    public PlacePhotoMetadataBuffer getPhotoMetadata() {
        return this.zzag;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzaf, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
