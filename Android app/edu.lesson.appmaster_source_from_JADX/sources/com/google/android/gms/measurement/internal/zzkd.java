package com.google.android.gms.measurement.internal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.internal.measurement.zzby;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zzkd extends zzf {
    protected final zzkc zza = new zzkc(this);
    protected final zzkb zzb = new zzkb(this);
    protected final zzjz zzc = new zzjz(this);
    /* access modifiers changed from: private */
    public Handler zzd;

    zzkd(zzfv zzfv) {
        super(zzfv);
    }

    static /* bridge */ /* synthetic */ void zzj(zzkd zzkd, long j) {
        zzkd.zzg();
        zzkd.zzm();
        zzkd.zzs.zzay().zzj().zzb("Activity paused, time", Long.valueOf(j));
        zzkd.zzc.zza(j);
        if (zzkd.zzs.zzf().zzu()) {
            zzkd.zzb.zzb(j);
        }
    }

    static /* bridge */ /* synthetic */ void zzl(zzkd zzkd, long j) {
        zzkd.zzg();
        zzkd.zzm();
        zzkd.zzs.zzay().zzj().zzb("Activity resumed, time", Long.valueOf(j));
        if (zzkd.zzs.zzf().zzu() || zzkd.zzs.zzm().zzl.zzb()) {
            zzkd.zzb.zzc(j);
        }
        zzkd.zzc.zzb();
        zzkc zzkc = zzkd.zza;
        zzkc.zza.zzg();
        if (zzkc.zza.zzs.zzJ()) {
            zzkc.zzb(zzkc.zza.zzs.zzav().currentTimeMillis(), false);
        }
    }

    /* access modifiers changed from: private */
    public final void zzm() {
        zzg();
        if (this.zzd == null) {
            this.zzd = new zzby(Looper.getMainLooper());
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zzf() {
        return false;
    }
}
