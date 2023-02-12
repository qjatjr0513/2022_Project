package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.zzm;

final class zzk extends zzm.zze<zzq> {
    private final /* synthetic */ AddPlaceRequest zzbk;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzk(zzh zzh, Api api, GoogleApiClient googleApiClient, AddPlaceRequest addPlaceRequest) {
        super(api, googleApiClient);
        this.zzbk = addPlaceRequest;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzq) anyClient).zzb(new zzm((zzm.zze) this), this.zzbk);
    }
}
