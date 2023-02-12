package com.google.android.libraries.places.internal;

import android.location.Location;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPhotoResponse;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzav implements PlacesClient {
    private final zzz zza;
    private final zzp zzb;
    private final zzu zzc;
    private final zzcx zzd;
    private final zza zze;

    zzav(zzz zzz, zzp zzp, zzu zzu, zzcx zzcx, zza zza2) {
        this.zza = zzz;
        this.zzb = zzp;
        this.zzc = zzu;
        this.zzd = zzcx;
        this.zze = zza2;
    }

    private static void zzf(zzj zzj, zzk zzk) {
        zzj.zza(zzj, zzj.zzb("Duration"));
        zzg.zza();
        zzg.zza();
        zzj.zza(zzj, zzj.zzb("Battery"));
        zzg.zza();
    }

    public final Task<FetchPhotoResponse> fetchPhoto(FetchPhotoRequest fetchPhotoRequest) {
        try {
            zzfm.zzc(fetchPhotoRequest, "Request must not be null.");
            zzg.zza();
            return this.zza.zza(fetchPhotoRequest).continueWith(new zzap(this, fetchPhotoRequest, zzk.zza())).continueWithTask(zzat.zza);
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    public final Task<FetchPlaceResponse> fetchPlace(FetchPlaceRequest fetchPlaceRequest) {
        try {
            zzfm.zzc(fetchPlaceRequest, "Request must not be null.");
            zzg.zza();
            return this.zza.zzb(fetchPlaceRequest).continueWith(new zzaq(this, fetchPlaceRequest, zzk.zza())).continueWithTask(zzat.zza);
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    public final Task<FindAutocompletePredictionsResponse> findAutocompletePredictions(FindAutocompletePredictionsRequest findAutocompletePredictionsRequest) {
        try {
            zzfm.zzc(findAutocompletePredictionsRequest, "Request must not be null.");
            zzg.zza();
            return this.zza.zzc(findAutocompletePredictionsRequest).continueWith(new zzar(this, findAutocompletePredictionsRequest, zzk.zza())).continueWithTask(zzat.zza);
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    public final Task<FindCurrentPlaceResponse> findCurrentPlace(FindCurrentPlaceRequest findCurrentPlaceRequest) {
        try {
            zzfm.zzc(findCurrentPlaceRequest, "Request must not be null.");
            long zza2 = this.zze.zza();
            zzg.zza();
            return this.zzb.zza(findCurrentPlaceRequest.getCancellationToken()).onSuccessTask(new zzau(this, findCurrentPlaceRequest)).continueWith(new zzas(this, findCurrentPlaceRequest, zza2, zzk.zza())).continueWithTask(zzat.zza);
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Task zza(FindCurrentPlaceRequest findCurrentPlaceRequest, Location location) throws Exception {
        return this.zza.zzd(findCurrentPlaceRequest, location, this.zzc.zza());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ FetchPhotoResponse zzb(FetchPhotoRequest fetchPhotoRequest, zzk zzk, Task task) throws Exception {
        this.zzd.zza(fetchPhotoRequest);
        zzf(zzj.zzb("FetchPhoto"), zzk);
        return (FetchPhotoResponse) task.getResult();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ FetchPlaceResponse zzc(FetchPlaceRequest fetchPlaceRequest, zzk zzk, Task task) throws Exception {
        this.zzd.zzc(fetchPlaceRequest);
        zzf(zzj.zzb("FetchPlace"), zzk);
        return (FetchPlaceResponse) task.getResult();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ FindAutocompletePredictionsResponse zzd(FindAutocompletePredictionsRequest findAutocompletePredictionsRequest, zzk zzk, Task task) throws Exception {
        this.zzd.zze(findAutocompletePredictionsRequest);
        zzf(zzj.zzb("FindAutocompletePredictions"), zzk);
        return (FindAutocompletePredictionsResponse) task.getResult();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ FindCurrentPlaceResponse zze(FindCurrentPlaceRequest findCurrentPlaceRequest, long j, zzk zzk, Task task) throws Exception {
        this.zzd.zzg(findCurrentPlaceRequest, task, j, this.zze.zza());
        zzf(zzj.zzb("FindCurrentPlace"), zzk);
        return (FindCurrentPlaceResponse) task.getResult();
    }
}
