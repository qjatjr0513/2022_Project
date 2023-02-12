package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.LocationSettingsResult;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
final class zzay extends zzan {
    private BaseImplementation.ResultHolder<LocationSettingsResult> zza;

    public zzay(BaseImplementation.ResultHolder<LocationSettingsResult> resultHolder) {
        boolean z;
        if (resultHolder != null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "listener can't be null.");
        this.zza = resultHolder;
    }

    public final void zzb(LocationSettingsResult locationSettingsResult) throws RemoteException {
        this.zza.setResult(locationSettingsResult);
        this.zza = null;
    }
}
