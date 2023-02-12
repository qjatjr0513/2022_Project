package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzw;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public abstract class zzau extends zzb implements zzav {
    public zzau() {
        super("com.google.android.gms.maps.internal.IOnMarkerDragListener");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zzd(zzw.zzb(parcel.readStrongBinder()));
                break;
            case 2:
                zzb(zzw.zzb(parcel.readStrongBinder()));
                break;
            case 3:
                zzc(zzw.zzb(parcel.readStrongBinder()));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
