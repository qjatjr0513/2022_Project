package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.zzf;
import com.google.android.gms.location.places.zzg;

final class zzj extends zzg<zzq> {
    private final /* synthetic */ String zzbj;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzj(zzh zzh, Api api, GoogleApiClient googleApiClient, String str) {
        super(api, googleApiClient);
        this.zzbj = str;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzq) anyClient).zzb(new zzf((zzg) this), this.zzbj);
    }
}
