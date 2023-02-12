package com.google.android.libraries.places.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzer extends ViewModel {
    private final zzee zza;
    private final zzeu zzb;
    private final zzev zzc;
    private final Handler zzd = new Handler(Looper.getMainLooper());
    private Runnable zze;
    private final MutableLiveData<zzdz> zzf = new MutableLiveData<>();

    /* synthetic */ zzer(zzee zzee, zzeu zzeu, zzev zzev, zzeq zzeq) {
        this.zza = zzee;
        this.zzb = zzeu;
        this.zzc = zzev;
    }

    private static Status zzn(Exception exc) {
        if (exc instanceof ApiException) {
            return ((ApiException) exc).getStatus();
        }
        return new Status(13, exc.getMessage());
    }

    private final void zzo(zzdz zzdz) {
        if (!zzdz.equals(this.zzf.getValue())) {
            this.zzf.setValue(zzdz);
        }
    }

    private static boolean zzp(Status status) {
        return status.isCanceled() || status.getStatusCode() == 9012 || status.getStatusCode() == 9011;
    }

    public final LiveData<zzdz> zza() {
        return this.zzf;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(String str, Task task) {
        if (!task.isCanceled()) {
            Exception exception = task.getException();
            if (exception == null) {
                this.zzb.zzp();
                List<AutocompletePrediction> autocompletePredictions = ((FindAutocompletePredictionsResponse) task.getResult()).getAutocompletePredictions();
                if (autocompletePredictions.isEmpty()) {
                    zzo(zzdz.zzh(str));
                } else {
                    zzo(zzdz.zzj(autocompletePredictions));
                }
            } else {
                this.zzb.zzr();
                Status zzn = zzn(exception);
                if (zzp(zzn)) {
                    zzo(zzdz.zzq(zzn));
                } else {
                    zzo(zzdz.zzi(str, zzn));
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(AutocompletePrediction autocompletePrediction, Task task) {
        if (!task.isCanceled()) {
            Exception exception = task.getException();
            if (exception == null) {
                this.zzb.zzq();
                zzo(zzdz.zzn(((FetchPlaceResponse) task.getResult()).getPlace()));
                return;
            }
            this.zzb.zzs();
            Status zzn = zzn(exception);
            if (zzp(zzn)) {
                zzo(zzdz.zzq(zzn));
            } else {
                zzo(zzdz.zzm(autocompletePrediction, zzn));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(String str) {
        this.zza.zzb(str).addOnCompleteListener(new zzen(this, str));
    }

    public final void zze(Bundle bundle) {
        if (bundle == null) {
            this.zzf.setValue(zzdz.zzo());
        }
    }

    public final void zzf(AutocompletePrediction autocompletePrediction, int i) {
        this.zzb.zzu(i);
        Task<FetchPlaceResponse> zza2 = this.zza.zza(autocompletePrediction);
        if (!zza2.isComplete()) {
            zzo(zzdz.zzg());
        }
        zza2.addOnCompleteListener(new zzem(this, autocompletePrediction));
    }

    public final void zzg() {
        this.zzb.zzv();
    }

    public final void zzh() {
        this.zzb.zzl();
    }

    public final void zzi() {
        this.zzb.zzm();
    }

    public final void zzj() {
        this.zzb.zzn();
        zzo(zzdz.zzl());
    }

    public final void zzk() {
        this.zzb.zzw();
        zzm("");
    }

    public final void zzl(String str) {
        this.zza.zzc();
        zzm(str);
        zzo(zzdz.zzp());
    }

    public final void zzm(String str) {
        this.zzb.zzt(str);
        this.zzd.removeCallbacks(this.zze);
        if (str.isEmpty()) {
            this.zza.zzc();
            zzo(zzdz.zzk());
            return;
        }
        zzeo zzeo = new zzeo(this, str);
        this.zze = zzeo;
        this.zzd.postDelayed(zzeo, 100);
        zzo(zzdz.zzg());
    }

    /* access modifiers changed from: protected */
    public final void onCleared() {
        try {
            this.zza.zzc();
            this.zzd.removeCallbacks(this.zze);
            this.zzb.zzo();
            this.zzc.zza(this.zzb);
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }
}
