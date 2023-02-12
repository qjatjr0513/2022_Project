package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public final class zze implements Parcelable.Creator<zzc> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzc[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        ArrayList<Integer> arrayList = null;
        String str2 = null;
        ArrayList<zzb> arrayList2 = null;
        String str3 = null;
        ArrayList<zzb> arrayList3 = null;
        String str4 = null;
        ArrayList<zzb> arrayList4 = null;
        int i = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    arrayList = SafeParcelReader.createIntegerList(parcel, readHeader);
                    break;
                case 4:
                    arrayList2 = SafeParcelReader.createTypedList(parcel, readHeader, zzb.CREATOR);
                    break;
                case 5:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 6:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 7:
                    arrayList3 = SafeParcelReader.createTypedList(parcel, readHeader, zzb.CREATOR);
                    break;
                case 8:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 9:
                    arrayList4 = SafeParcelReader.createTypedList(parcel, readHeader, zzb.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzc(str, arrayList, i, str2, arrayList2, str3, arrayList3, str4, arrayList4);
    }
}
