package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.zzm;

final class zzab extends zzm.zzf<zzae> {
    private final /* synthetic */ PlaceReport zzbu;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzab(zzz zzz, Api api, GoogleApiClient googleApiClient, PlaceReport placeReport) {
        super(api, googleApiClient);
        this.zzbu = placeReport;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzae) anyClient).zzb(new zzm((zzm.zzf) this), this.zzbu);
    }
}
