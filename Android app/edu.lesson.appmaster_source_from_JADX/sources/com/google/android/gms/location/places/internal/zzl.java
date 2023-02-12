package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.zzm;
import java.util.Arrays;
import java.util.List;

final class zzl extends zzm.zze<zzq> {
    private final /* synthetic */ String[] zzbl;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzl(zzh zzh, Api api, GoogleApiClient googleApiClient, String[] strArr) {
        super(api, googleApiClient);
        this.zzbl = strArr;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzq) anyClient).zzb(new zzm((zzm.zze) this), (List<String>) Arrays.asList(this.zzbl));
    }
}
