package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.places.zzb;
import com.google.android.gms.internal.places.zze;

public abstract class zzaa extends zzb implements zzx {
    public zzaa() {
        super("com.google.android.gms.location.places.internal.IPlacesCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zzb((DataHolder) zze.zzb(parcel, DataHolder.CREATOR));
                return true;
            case 2:
                zzc((DataHolder) zze.zzb(parcel, DataHolder.CREATOR));
                return true;
            case 3:
                zzd((DataHolder) zze.zzb(parcel, DataHolder.CREATOR));
                return true;
            case 4:
                zzb((Status) zze.zzb(parcel, Status.CREATOR));
                return true;
            case 5:
                zze((DataHolder) zze.zzb(parcel, DataHolder.CREATOR));
                return true;
            default:
                return false;
        }
    }
}
