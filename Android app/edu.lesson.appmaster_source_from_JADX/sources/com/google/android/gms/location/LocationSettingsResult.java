package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
public final class LocationSettingsResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<LocationSettingsResult> CREATOR = new zzbm();
    private final Status zza;
    private final LocationSettingsStates zzb;

    public LocationSettingsResult(Status status, LocationSettingsStates locationSettingsStates) {
        this.zza = status;
        this.zzb = locationSettingsStates;
    }

    public LocationSettingsStates getLocationSettingsStates() {
        return this.zzb;
    }

    public Status getStatus() {
        return this.zza;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getLocationSettingsStates(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
