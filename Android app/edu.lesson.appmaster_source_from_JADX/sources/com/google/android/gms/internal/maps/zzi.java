package com.google.android.gms.internal.maps;

import android.graphics.Bitmap;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public interface zzi extends IInterface {
    IObjectWrapper zzd() throws RemoteException;

    IObjectWrapper zze(float f) throws RemoteException;

    IObjectWrapper zzf(String str) throws RemoteException;

    IObjectWrapper zzg(Bitmap bitmap) throws RemoteException;

    IObjectWrapper zzh(String str) throws RemoteException;

    IObjectWrapper zzi(String str) throws RemoteException;

    IObjectWrapper zzj(int i) throws RemoteException;
}
