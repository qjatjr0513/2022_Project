package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zzw;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public abstract class zzh extends zzb implements zzi {
    public zzh() {
        super("com.google.android.gms.maps.internal.IInfoWindowAdapter");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                IObjectWrapper zzc = zzc(zzw.zzb(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzc.zzf(parcel2, zzc);
                return true;
            case 2:
                IObjectWrapper zzb = zzb(zzw.zzb(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzc.zzf(parcel2, zzb);
                return true;
            default:
                return false;
        }
    }
}
