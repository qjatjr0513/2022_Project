package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.zze;
import com.google.android.gms.location.places.zzf;

final class zzm extends zze<zzq> {
    private final /* synthetic */ String zzbm;
    private final /* synthetic */ int zzbn;
    private final /* synthetic */ int zzbo;
    private final /* synthetic */ int zzbp;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzm(zzh zzh, Api api, GoogleApiClient googleApiClient, String str, int i, int i2, int i3) {
        super(api, googleApiClient);
        this.zzbm = str;
        this.zzbn = i;
        this.zzbo = i2;
        this.zzbp = i3;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzq) anyClient).zzb(new zzf((zze) this), this.zzbm, this.zzbn, this.zzbo, this.zzbp);
    }
}
