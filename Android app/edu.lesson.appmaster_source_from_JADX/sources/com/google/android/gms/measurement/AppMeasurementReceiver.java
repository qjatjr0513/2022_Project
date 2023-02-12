package com.google.android.gms.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.measurement.internal.zzfe;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class AppMeasurementReceiver extends WakefulBroadcastReceiver implements zzfe.zza {
    private zzfe zza;

    public BroadcastReceiver.PendingResult doGoAsync() {
        return goAsync();
    }

    public void doStartService(Context context, Intent service) {
        startWakefulService(context, service);
    }

    public void onReceive(Context context, Intent intent) {
        if (this.zza == null) {
            this.zza = new zzfe(this);
        }
        this.zza.zza(context, intent);
    }
}
