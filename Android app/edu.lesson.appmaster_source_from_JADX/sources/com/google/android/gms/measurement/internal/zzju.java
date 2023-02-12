package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcl;
import com.google.android.gms.measurement.internal.zzjt;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzju<T extends Context & zzjt> {
    private final T zza;

    public zzju(T t) {
        Preconditions.checkNotNull(t);
        this.zza = t;
    }

    private final zzel zzk() {
        return zzfv.zzp(this.zza, (zzcl) null, (Long) null).zzay();
    }

    public final int zza(Intent intent, int i, int i2) {
        zzfv zzp = zzfv.zzp(this.zza, (zzcl) null, (Long) null);
        zzel zzay = zzp.zzay();
        if (intent == null) {
            zzay.zzk().zza("AppMeasurementService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        zzp.zzaw();
        zzay.zzj().zzc("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            zzh(new zzjq(this, i2, zzay, intent));
        }
        return 2;
    }

    public final IBinder zzb(Intent intent) {
        if (intent == null) {
            zzk().zzd().zza("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzgn(zzks.zzt(this.zza), (String) null);
        }
        zzk().zzk().zzb("onBind received unknown action", action);
        return null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(int i, zzel zzel, Intent intent) {
        if (((zzjt) this.zza).zzc(i)) {
            zzel.zzj().zzb("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i));
            zzk().zzj().zza("Completed wakeful intent.");
            ((zzjt) this.zza).zza(intent);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zzel zzel, JobParameters jobParameters) {
        zzel.zzj().zza("AppMeasurementJobService processed last upload request.");
        ((zzjt) this.zza).zzb(jobParameters, false);
    }

    public final void zze() {
        zzfv zzp = zzfv.zzp(this.zza, (zzcl) null, (Long) null);
        zzel zzay = zzp.zzay();
        zzp.zzaw();
        zzay.zzj().zza("Local AppMeasurementService is starting up");
    }

    public final void zzf() {
        zzfv zzp = zzfv.zzp(this.zza, (zzcl) null, (Long) null);
        zzel zzay = zzp.zzay();
        zzp.zzaw();
        zzay.zzj().zza("Local AppMeasurementService is shutting down");
    }

    public final void zzg(Intent intent) {
        if (intent == null) {
            zzk().zzd().zza("onRebind called with null intent");
            return;
        }
        zzk().zzj().zzb("onRebind called. action", intent.getAction());
    }

    public final void zzh(Runnable runnable) {
        zzks zzt = zzks.zzt(this.zza);
        zzt.zzaz().zzp(new zzjs(this, zzt, runnable));
    }

    public final boolean zzi(JobParameters jobParameters) {
        zzfv zzp = zzfv.zzp(this.zza, (zzcl) null, (Long) null);
        zzel zzay = zzp.zzay();
        String string = jobParameters.getExtras().getString("action");
        zzp.zzaw();
        zzay.zzj().zzb("Local AppMeasurementJobService called. action", string);
        if (!"com.google.android.gms.measurement.UPLOAD".equals(string)) {
            return true;
        }
        zzh(new zzjr(this, zzay, jobParameters));
        return true;
    }

    public final boolean zzj(Intent intent) {
        if (intent == null) {
            zzk().zzd().zza("onUnbind called with null intent");
            return true;
        }
        zzk().zzj().zzb("onUnbind called for intent. action", intent.getAction());
        return true;
    }
}
