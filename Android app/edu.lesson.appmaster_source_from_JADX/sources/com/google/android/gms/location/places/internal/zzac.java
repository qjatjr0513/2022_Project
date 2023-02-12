package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.zzm;

final class zzac extends zzm.zzd<zzae> {
    private final /* synthetic */ PlaceFilter zzbv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzac(zzz zzz, Api api, GoogleApiClient googleApiClient, PlaceFilter placeFilter) {
        super(api, googleApiClient);
        this.zzbv = placeFilter;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzae) anyClient).zzb(new zzm((zzm.zzd) this), this.zzbv);
    }
}
