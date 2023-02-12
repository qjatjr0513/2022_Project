package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfy;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzkr {
    zzfy zza;
    List<Long> zzb;
    List<zzfo> zzc;
    long zzd;
    final /* synthetic */ zzks zze;

    /* synthetic */ zzkr(zzks zzks, zzkq zzkq) {
        this.zze = zzks;
    }

    private static final long zzb(zzfo zzfo) {
        return ((zzfo.zzd() / 1000) / 60) / 60;
    }

    public final boolean zza(long j, zzfo zzfo) {
        Preconditions.checkNotNull(zzfo);
        if (this.zzc == null) {
            this.zzc = new ArrayList();
        }
        if (this.zzb == null) {
            this.zzb = new ArrayList();
        }
        if (this.zzc.size() > 0 && zzb(this.zzc.get(0)) != zzb(zzfo)) {
            return false;
        }
        long zzbt = this.zzd + ((long) zzfo.zzbt());
        this.zze.zzg();
        if (zzbt >= ((long) Math.max(0, zzdy.zzh.zza(null).intValue()))) {
            return false;
        }
        this.zzd = zzbt;
        this.zzc.add(zzfo);
        this.zzb.add(Long.valueOf(j));
        int size = this.zzc.size();
        this.zze.zzg();
        if (size >= Math.max(1, zzdy.zzi.zza(null).intValue())) {
            return false;
        }
        return true;
    }
}
