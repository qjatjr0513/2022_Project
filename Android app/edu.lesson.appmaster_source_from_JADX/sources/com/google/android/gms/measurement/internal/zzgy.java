package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcl;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zzgy {
    final Context zza;
    String zzb;
    String zzc;
    String zzd;
    Boolean zze;
    long zzf;
    zzcl zzg;
    boolean zzh = true;
    final Long zzi;
    String zzj;

    public zzgy(Context context, zzcl zzcl, Long l) {
        Preconditions.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zza = applicationContext;
        this.zzi = l;
        if (zzcl != null) {
            this.zzg = zzcl;
            this.zzb = zzcl.zzf;
            this.zzc = zzcl.zze;
            this.zzd = zzcl.zzd;
            this.zzh = zzcl.zzc;
            this.zzf = zzcl.zzb;
            this.zzj = zzcl.zzh;
            Bundle bundle = zzcl.zzg;
            if (bundle != null) {
                this.zze = Boolean.valueOf(bundle.getBoolean("dataCollectionDefaultEnabled", true));
            }
        }
    }
}
