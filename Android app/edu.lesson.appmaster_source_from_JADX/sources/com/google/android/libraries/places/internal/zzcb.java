package com.google.android.libraries.places.internal;

import android.location.Location;
import android.text.TextUtils;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPhotoResponse;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;
import java.util.ArrayList;
import java.util.Locale;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzcb implements zzz {
    private final zzdl zza;
    private final zzae zzb;
    private final zzak zzc;
    private final zzcx zzd;
    private final zza zze;
    private final zzbc zzf;
    private final zzbg zzg;
    private final zzbk zzh;
    private final zzbo zzi;
    private final zzcy zzj;

    zzcb(zzcy zzcy, zzdl zzdl, zzae zzae, zzak zzak, zzcx zzcx, zza zza2, zzbc zzbc, zzbg zzbg, zzbk zzbk, zzbo zzbo, byte[] bArr) {
        this.zzj = zzcy;
        this.zza = zzdl;
        this.zzb = zzae;
        this.zzc = zzak;
        this.zzd = zzcx;
        this.zze = zza2;
        this.zzf = zzbc;
        this.zzg = zzbg;
        this.zzh = zzbk;
        this.zzi = zzbo;
    }

    static final /* synthetic */ FetchPlaceResponse zzi(Task task) throws Exception {
        zzge zzge;
        zzbf zzbf = (zzbf) task.getResult();
        int zza2 = zzci.zza(zzbf.status);
        if (!PlacesStatusCodes.isError(zza2)) {
            zzch zzch = zzbf.result;
            String[] strArr = zzbf.htmlAttributions;
            if (strArr != null) {
                zzge = zzge.zzl(strArr);
            } else {
                zzge = null;
            }
            return FetchPlaceResponse.newInstance(zzce.zzc(zzch, zzge));
        }
        throw new ApiException(new Status(zza2, zzci.zzb(zzbf.status, zzbf.errorMessage)));
    }

    static final /* synthetic */ FindCurrentPlaceResponse zzj(Task task) throws Exception {
        zzge zzge;
        zzbn zzbn = (zzbn) task.getResult();
        int zza2 = zzci.zza(zzbn.status);
        if (!PlacesStatusCodes.isError(zza2)) {
            ArrayList arrayList = new ArrayList();
            zzcg[] zzcgArr = zzbn.predictions;
            if (zzcgArr != null) {
                int length = zzcgArr.length;
                int i = 0;
                while (i < length) {
                    zzcg zzcg = zzcgArr[i];
                    if (zzcg.zza() == null) {
                        throw new ApiException(new Status(8, "Unexpected server error: PlaceLikelihood returned without a Place value"));
                    } else if (zzcg.zzb() != null) {
                        zzch zza3 = zzcg.zza();
                        String[] strArr = zzbn.htmlAttributions;
                        if (strArr != null) {
                            zzge = zzge.zzl(strArr);
                        } else {
                            zzge = null;
                        }
                        arrayList.add(PlaceLikelihood.newInstance(zzce.zzc(zza3, zzge), zzcg.zzb().doubleValue()));
                        i++;
                    } else {
                        throw new ApiException(new Status(8, "Unexpected server error: PlaceLikelihood returned without a likelihood value"));
                    }
                }
            }
            return FindCurrentPlaceResponse.newInstance(arrayList);
        }
        throw new ApiException(new Status(zza2, zzci.zzb(zzbn.status, zzbn.errorMessage)));
    }

    public final Task<FetchPhotoResponse> zza(FetchPhotoRequest fetchPhotoRequest) {
        Integer maxWidth = fetchPhotoRequest.getMaxWidth();
        Integer maxHeight = fetchPhotoRequest.getMaxHeight();
        if (maxWidth == null && maxHeight == null) {
            return Tasks.forException(new ApiException(new Status((int) PlacesStatusCodes.INVALID_REQUEST, "Must include max width or max height in request.")));
        }
        if (maxWidth != null && maxWidth.intValue() <= 0) {
            return Tasks.forException(new ApiException(new Status((int) PlacesStatusCodes.INVALID_REQUEST, String.format("Max Width must not be < 1, but was: %d.", new Object[]{maxWidth}))));
        } else if (maxHeight == null || maxHeight.intValue() > 0) {
            String zza2 = this.zzj.zza();
            this.zzj.zze();
            return this.zzc.zzb(new zzay(fetchPhotoRequest, zza2, false, this.zza), new zzaz()).continueWith(new zzbt(this)).continueWith(new zzbx(this, this.zze.zza()));
        } else {
            return Tasks.forException(new ApiException(new Status((int) PlacesStatusCodes.INVALID_REQUEST, String.format("Max Height must not be < 1, but was: %d.", new Object[]{maxHeight}))));
        }
    }

    public final Task<FetchPlaceResponse> zzb(FetchPlaceRequest fetchPlaceRequest) {
        if (TextUtils.isEmpty(fetchPlaceRequest.getPlaceId())) {
            return Tasks.forException(new ApiException(new Status((int) PlacesStatusCodes.INVALID_REQUEST, "Place ID must not be empty.")));
        }
        if (fetchPlaceRequest.getPlaceFields().isEmpty()) {
            return Tasks.forException(new ApiException(new Status((int) PlacesStatusCodes.INVALID_REQUEST, "Place Fields must not be empty.")));
        }
        Locale zzb2 = this.zzj.zzb();
        String zza2 = this.zzj.zza();
        this.zzj.zze();
        return this.zzb.zza(new zzbe(fetchPlaceRequest, zzb2, zza2, false, this.zza), zzbf.class).continueWith(new zzbu(this)).continueWith(new zzby(this, this.zze.zza()));
    }

    public final Task<FindAutocompletePredictionsResponse> zzc(FindAutocompletePredictionsRequest findAutocompletePredictionsRequest) {
        String query = findAutocompletePredictionsRequest.getQuery();
        if (query == null || TextUtils.isEmpty(query.trim())) {
            return Tasks.forResult(FindAutocompletePredictionsResponse.newInstance(zzge.zzm()));
        }
        Locale zzb2 = this.zzj.zzb();
        String zza2 = this.zzj.zza();
        this.zzj.zze();
        return this.zzb.zza(new zzbi(findAutocompletePredictionsRequest, zzb2, zza2, false, this.zza), zzbj.class).continueWith(new zzbv(this)).continueWith(new zzbz(this, this.zze.zza()));
    }

    public final Task<FindCurrentPlaceResponse> zzd(FindCurrentPlaceRequest findCurrentPlaceRequest, Location location, zzge<zzs> zzge) {
        if (findCurrentPlaceRequest.getPlaceFields().isEmpty()) {
            return Tasks.forException(new ApiException(new Status((int) PlacesStatusCodes.INVALID_REQUEST, "Place Fields must not be empty.")));
        }
        Locale zzb2 = this.zzj.zzb();
        String zza2 = this.zzj.zza();
        this.zzj.zze();
        return this.zzb.zza(new zzbm(findCurrentPlaceRequest, location, zzge, zzb2, zza2, false, this.zza), zzbn.class).continueWith(new zzbw(this)).continueWith(new zzca(this, this.zze.zza()));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ FetchPhotoResponse zze(long j, Task task) throws Exception {
        this.zzd.zzb(task, j, this.zze.zza());
        return (FetchPhotoResponse) task.getResult();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ FetchPlaceResponse zzf(long j, Task task) throws Exception {
        this.zzd.zzd(task, j, this.zze.zza());
        return (FetchPlaceResponse) task.getResult();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ FindAutocompletePredictionsResponse zzg(long j, Task task) throws Exception {
        this.zzd.zzf(task, j, this.zze.zza());
        return (FindAutocompletePredictionsResponse) task.getResult();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ FindCurrentPlaceResponse zzh(long j, Task task) throws Exception {
        this.zzd.zzh(task, j, this.zze.zza());
        return (FindCurrentPlaceResponse) task.getResult();
    }
}
