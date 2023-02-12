package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public interface ILocationSourceDelegate extends IInterface {
    void activate(zzaj zzaj) throws RemoteException;

    void deactivate() throws RemoteException;
}
