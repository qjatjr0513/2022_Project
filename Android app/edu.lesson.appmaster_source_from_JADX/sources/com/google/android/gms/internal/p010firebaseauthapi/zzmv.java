package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmv */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzmv implements Parcelable.Creator<zzmu> {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzxd zzxd = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    zzxd = (zzxd) SafeParcelReader.createParcelable(parcel, readHeader, zzxd.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzmu(zzxd);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new zzmu[i];
    }
}
