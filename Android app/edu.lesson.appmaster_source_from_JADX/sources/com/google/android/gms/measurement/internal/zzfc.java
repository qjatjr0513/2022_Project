package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.internal.measurement.zzbq;
import com.google.android.gms.internal.measurement.zzbr;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzfc implements ServiceConnection {
    final /* synthetic */ zzfd zza;
    /* access modifiers changed from: private */
    public final String zzb;

    zzfc(zzfd zzfd, String str) {
        this.zza = zzfd;
        this.zzb = str;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder != null) {
            try {
                zzbr zzb2 = zzbq.zzb(iBinder);
                if (zzb2 == null) {
                    this.zza.zza.zzay().zzk().zza("Install Referrer Service implementation was not found");
                    return;
                }
                this.zza.zza.zzay().zzj().zza("Install Referrer Service connected");
                this.zza.zza.zzaz().zzp(new zzfb(this, zzb2, this));
            } catch (RuntimeException e) {
                this.zza.zza.zzay().zzk().zzb("Exception occurred while calling Install Referrer API", e);
            }
        } else {
            this.zza.zza.zzay().zzk().zza("Install Referrer connection returned with null binder");
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.zza.zza.zzay().zzj().zza("Install Referrer Service disconnected");
    }
}
