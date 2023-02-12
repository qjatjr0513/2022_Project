package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.zzm;
import com.google.android.gms.maps.model.LatLngBounds;

final class zzo extends zzm.zzc<zzq> {
    private final /* synthetic */ String val$query;
    private final /* synthetic */ LatLngBounds zzbq;
    private final /* synthetic */ AutocompleteFilter zzbr;
    private final /* synthetic */ int zzbs;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzo(zzh zzh, Api api, GoogleApiClient googleApiClient, String str, LatLngBounds latLngBounds, int i, AutocompleteFilter autocompleteFilter) {
        super(api, googleApiClient);
        this.val$query = str;
        this.zzbq = latLngBounds;
        this.zzbs = i;
        this.zzbr = autocompleteFilter;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzq) anyClient).zzb(new zzm((zzm.zzc) this), this.val$query, this.zzbq, this.zzbs, this.zzbr);
    }
}
