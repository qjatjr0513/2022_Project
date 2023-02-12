package com.google.android.libraries.places.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPhotoResponse;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import java.util.Locale;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzw implements zzcx {
    private final zzdf zza;
    private final zzdj zzb;
    private final zzcy zzc;

    zzw(zzdj zzdj, zzdf zzdf, zzcy zzcy, byte[] bArr) {
        this.zzb = zzdj;
        this.zza = zzdf;
        this.zzc = zzcy;
    }

    static final <ResponseT> Object zzi(Task<ResponseT> task) {
        ApiException apiException;
        if (task.isSuccessful()) {
            return 2;
        }
        Exception exception = task.getException();
        if (exception instanceof ApiException) {
            apiException = (ApiException) exception;
        } else {
            apiException = new ApiException(new Status(13, exception.getMessage()));
        }
        switch (apiException.getStatusCode()) {
            case 7:
                return 4;
            case 15:
                return 3;
            default:
                return 1;
        }
    }

    private final zzxr zzj() {
        Locale zzb2 = this.zzc.zzb();
        Locale locale = Locale.getDefault();
        zzxr zza2 = zzxt.zza();
        zza2.zzd(zzb2.toString());
        if (!zzb2.equals(locale)) {
            zza2.zzb(locale.toString());
        }
        return zza2;
    }

    private final void zzk(zzva zzva) {
        zzwc zzb2 = zzdk.zzb(this.zza);
        zzb2.zzl(16);
        zzb2.zze(zzva);
        zzb2.zza(this.zzc.zza());
        zzl((zzwh) zzb2.zzt());
    }

    private final void zzl(zzwh zzwh) {
        this.zzb.zza(zzdk.zza(zzwh));
    }

    public final void zza(FetchPhotoRequest fetchPhotoRequest) {
        zzxj zza2 = zzxl.zza();
        zza2.zza(2);
        zzwc zzb2 = zzdk.zzb(this.zza);
        zzb2.zzl(5);
        zzb2.zzg((zzxl) zza2.zzt());
        zzb2.zza(this.zzc.zza());
        zzl((zzwh) zzb2.zzt());
    }

    public final void zzb(Task<FetchPhotoResponse> task, long j, long j2) {
        zzuv zza2 = zzva.zza();
        zza2.zzf(15);
        zza2.zze(zzi(task));
        zza2.zzd((int) (j2 - j));
        zzk((zzva) zza2.zzt());
    }

    public final void zzc(FetchPlaceRequest fetchPlaceRequest) {
        zzwn zza2 = zzwo.zza();
        zza2.zza(1);
        zzxm zza3 = zzxn.zza();
        zza3.zza(zzck.zzb(fetchPlaceRequest.getPlaceFields()));
        zza2.zzb((zzxn) zza3.zzt());
        zzxr zzj = zzj();
        zzj.zze(5);
        zzj.zzc((zzwo) zza2.zzt());
        zzwc zzb2 = zzdk.zzb(this.zza);
        zzb2.zzl(1);
        zzb2.zzh((zzxt) zzj.zzt());
        zzb2.zza(this.zzc.zza());
        if (fetchPlaceRequest.getSessionToken() != null) {
            zzb2.zzj(fetchPlaceRequest.getSessionToken().toString());
        }
        zzl((zzwh) zzb2.zzt());
    }

    public final void zzd(Task<FetchPlaceResponse> task, long j, long j2) {
        boolean isSuccessful = task.isSuccessful();
        zzun zza2 = zzuo.zza();
        zza2.zza(1);
        zza2.zzb(isSuccessful ? 1 : 0);
        zzuv zza3 = zzva.zza();
        zza3.zzf(8);
        zza3.zzc((zzuo) zza2.zzt());
        zza3.zze(zzi(task));
        zza3.zzd((int) (j2 - j));
        zzk((zzva) zza3.zzt());
    }

    public final void zze(FindAutocompletePredictionsRequest findAutocompletePredictionsRequest) {
        zzve zza2 = zzvf.zza();
        if (findAutocompletePredictionsRequest.getTypeFilter() != null) {
            zza2.zza(zzcl.zza(findAutocompletePredictionsRequest.getTypeFilter()));
        }
        zzvf zzvf = (zzvf) zza2.zzt();
        zzvq zza3 = zzvr.zza();
        if (zzvf != null) {
            zza3.zza(zzvf);
        }
        zzxr zzj = zzj();
        zzj.zze(6);
        zzj.zza((zzvr) zza3.zzt());
        zzwc zzb2 = zzdk.zzb(this.zza);
        zzb2.zzl(1);
        zzb2.zzh((zzxt) zzj.zzt());
        zzb2.zza(this.zzc.zza());
        if (findAutocompletePredictionsRequest.getSessionToken() != null) {
            zzb2.zzj(findAutocompletePredictionsRequest.getSessionToken().toString());
        }
        zzl((zzwh) zzb2.zzt());
    }

    public final void zzf(Task<FindAutocompletePredictionsResponse> task, long j, long j2) {
        int i;
        if (task.isSuccessful()) {
            i = task.getResult().getAutocompletePredictions().size();
        } else {
            i = 0;
        }
        zzui zza2 = zzuj.zza();
        zza2.zza(i);
        zzuv zza3 = zzva.zza();
        zza3.zzf(6);
        zza3.zzb((zzuj) zza2.zzt());
        zza3.zze(zzi(task));
        zza3.zzd((int) (j2 - j));
        zzk((zzva) zza3.zzt());
    }

    public final void zzg(FindCurrentPlaceRequest findCurrentPlaceRequest, Task<FindCurrentPlaceResponse> task, long j, long j2) {
        int i = 1;
        if (true == task.isSuccessful()) {
            i = 2;
        }
        zzwt zza2 = zzwv.zza();
        zzxm zza3 = zzxn.zza();
        zza3.zza(zzck.zzb(findCurrentPlaceRequest.getPlaceFields()));
        zza2.zzb((zzxn) zza3.zzt());
        zza2.zza((int) (j2 - j));
        zza2.zzc(i);
        zzwc zzb2 = zzdk.zzb(this.zza);
        zzb2.zzl(6);
        zzb2.zzd((zzwv) zza2.zzt());
        zzb2.zza(this.zzc.zza());
        zzl((zzwh) zzb2.zzt());
    }

    public final void zzh(Task<FindCurrentPlaceResponse> task, long j, long j2) {
        int i;
        if (task.isSuccessful()) {
            i = task.getResult().getPlaceLikelihoods().size();
        } else {
            i = 0;
        }
        zzub zza2 = zzuc.zza();
        zza2.zza(i);
        zzuv zza3 = zzva.zza();
        zza3.zzf(4);
        zza3.zza((zzuc) zza2.zzt());
        zza3.zze(zzi(task));
        zza3.zzd((int) (j2 - j));
        zzk((zzva) zza3.zzt());
    }
}
