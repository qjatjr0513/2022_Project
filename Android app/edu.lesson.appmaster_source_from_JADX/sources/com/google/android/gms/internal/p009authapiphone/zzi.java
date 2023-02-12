package com.google.android.gms.internal.p009authapiphone;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.IStatusCallback;

/* renamed from: com.google.android.gms.internal.auth-api-phone.zzi */
/* compiled from: com.google.android.gms:play-services-auth-api-phone@@17.4.0 */
public final class zzi extends zzb implements zzj {
    zzi(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.phone.internal.ISmsRetrieverApiService");
    }

    public final void zza(zzl zzl) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (IInterface) zzl);
        zza(1, zza);
    }

    public final void zza(String str, zzl zzl) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, (IInterface) zzl);
        zza(2, zza);
    }

    public final void zza(IStatusCallback iStatusCallback) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (IInterface) iStatusCallback);
        zza(3, zza);
    }

    public final void zza(zzf zzf) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (IInterface) zzf);
        zza(4, zza);
    }

    public final void zza(String str, zzh zzh) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, (IInterface) zzh);
        zza(5, zza);
    }
}
