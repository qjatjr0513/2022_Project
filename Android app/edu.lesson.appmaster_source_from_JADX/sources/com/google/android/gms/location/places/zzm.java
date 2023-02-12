package com.google.android.gms.location.places;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.places.zzh;
import com.google.android.gms.location.places.internal.zzaa;

public class zzm extends zzaa {
    private static final String TAG = zzm.class.getSimpleName();
    private final zzd zzal;
    private final zzc zzam;
    private final zzg zzan;
    private final zzf zzao;
    private final zze zzap;

    public static abstract class zzb<R extends Result, A extends Api.Client> extends BaseImplementation.ApiMethodImpl<R, A> {
        public zzb(Api api, GoogleApiClient googleApiClient) {
            super((Api<?>) api, googleApiClient);
        }
    }

    @Deprecated
    public static abstract class zzg<A extends Api.Client> extends zzb<zzh, A> {
    }

    public zzm(zzd zzd2) {
        this.zzal = zzd2;
        this.zzam = null;
        this.zzan = null;
        this.zzao = null;
        this.zzap = null;
    }

    public static abstract class zzc<A extends Api.Client> extends zzb<AutocompletePredictionBuffer, A> {
        public zzc(Api api, GoogleApiClient googleApiClient) {
            super(api, googleApiClient);
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ Result createFailedResult(Status status) {
            return new AutocompletePredictionBuffer(DataHolder.empty(status.getStatusCode()));
        }
    }

    public static abstract class zzd<A extends Api.Client> extends zzb<PlaceLikelihoodBuffer, A> {
        public zzd(Api api, GoogleApiClient googleApiClient) {
            super(api, googleApiClient);
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ Result createFailedResult(Status status) {
            return new PlaceLikelihoodBuffer(DataHolder.empty(status.getStatusCode()), 100);
        }
    }

    public static abstract class zze<A extends Api.Client> extends zzb<PlaceBuffer, A> {
        public zze(Api api, GoogleApiClient googleApiClient) {
            super(api, googleApiClient);
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ Result createFailedResult(Status status) {
            return new PlaceBuffer(DataHolder.empty(status.getStatusCode()));
        }
    }

    public static abstract class zzf<A extends Api.Client> extends zzb<Status, A> {
        public zzf(Api api, GoogleApiClient googleApiClient) {
            super(api, googleApiClient);
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ Result createFailedResult(Status status) {
            return status;
        }
    }

    public zzm(zzc zzc2) {
        this.zzal = null;
        this.zzam = zzc2;
        this.zzan = null;
        this.zzao = null;
        this.zzap = null;
    }

    public zzm(zzf zzf2) {
        this.zzal = null;
        this.zzam = null;
        this.zzan = null;
        this.zzao = zzf2;
        this.zzap = null;
    }

    public zzm(zze zze2) {
        this.zzal = null;
        this.zzam = null;
        this.zzan = null;
        this.zzao = null;
        this.zzap = zze2;
    }

    public final void zzb(DataHolder dataHolder) throws RemoteException {
        Preconditions.checkState(this.zzal != null, "placeEstimator cannot be null");
        if (dataHolder == null) {
            String str = TAG;
            if (Log.isLoggable(str, 6)) {
                Log.e(str, "onPlaceEstimated received null DataHolder", new Throwable());
            }
            this.zzal.setFailedResult(Status.RESULT_INTERNAL_ERROR);
            return;
        }
        Bundle metadata = dataHolder.getMetadata();
        this.zzal.setResult(new PlaceLikelihoodBuffer(dataHolder, metadata == null ? 100 : PlaceLikelihoodBuffer.zzb(metadata)));
    }

    public final void zzc(DataHolder dataHolder) throws RemoteException {
        if (dataHolder == null) {
            String str = TAG;
            if (Log.isLoggable(str, 6)) {
                Log.e(str, "onAutocompletePrediction received null DataHolder", new Throwable());
            }
            this.zzam.setFailedResult(Status.RESULT_INTERNAL_ERROR);
            return;
        }
        this.zzam.setResult(new AutocompletePredictionBuffer(dataHolder));
    }

    public final void zzd(DataHolder dataHolder) throws RemoteException {
        zzg zzg2 = null;
        if (dataHolder == null) {
            String str = TAG;
            if (Log.isLoggable(str, 6)) {
                Log.e(str, "onPlaceUserDataFetched received null DataHolder", new Throwable());
            }
            zzg2.setFailedResult(Status.RESULT_INTERNAL_ERROR);
            return;
        }
        zzg2.setResult(new zzh(dataHolder));
    }

    public final void zzb(Status status) throws RemoteException {
        this.zzao.setResult(status);
    }

    public final void zze(DataHolder dataHolder) throws RemoteException {
        this.zzap.setResult(new PlaceBuffer(dataHolder));
    }
}
