package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.api.AppMeasurementSdk;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzhk implements Runnable {
    final /* synthetic */ Bundle zza;
    final /* synthetic */ zzia zzb;

    zzhk(zzia zzia, Bundle bundle) {
        this.zzb = zzia;
        this.zza = bundle;
    }

    public final void run() {
        zzia zzia = this.zzb;
        Bundle bundle = this.zza;
        zzia.zzg();
        zzia.zza();
        Preconditions.checkNotNull(bundle);
        String checkNotEmpty = Preconditions.checkNotEmpty(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        if (!zzia.zzs.zzJ()) {
            zzia.zzs.zzay().zzj().zza("Conditional property not cleared since app measurement is disabled");
            return;
        }
        zzkv zzkv = new zzkv(checkNotEmpty, 0, (Object) null, "");
        try {
            zzab zzab = r4;
            zzab zzab2 = new zzab(bundle.getString("app_id"), "", zzkv, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), bundle.getBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), (zzat) null, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), (zzat) null, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zzia.zzs.zzv().zzz(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), "", bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), true, true));
            zzia.zzs.zzt().zzE(zzab);
        } catch (IllegalArgumentException e) {
        }
    }
}
