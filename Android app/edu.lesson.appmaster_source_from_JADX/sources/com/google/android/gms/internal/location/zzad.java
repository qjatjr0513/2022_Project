package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.zzbq;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
final class zzad extends zzae {
    final /* synthetic */ zzbq zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzad(zzaf zzaf, GoogleApiClient googleApiClient, zzbq zzbq) {
        super(googleApiClient);
        this.zza = zzbq;
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzaz) anyClient).zzw(this.zza, this);
    }
}
