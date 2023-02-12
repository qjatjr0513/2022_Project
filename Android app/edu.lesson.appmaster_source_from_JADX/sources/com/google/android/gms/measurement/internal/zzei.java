package com.google.android.gms.measurement.internal;

import android.util.Log;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzei implements Runnable {
    final /* synthetic */ int zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ Object zzc;
    final /* synthetic */ Object zzd;
    final /* synthetic */ Object zze;
    final /* synthetic */ zzel zzf;

    zzei(zzel zzel, int i, String str, Object obj, Object obj2, Object obj3) {
        this.zzf = zzel;
        this.zza = i;
        this.zzb = str;
        this.zzc = obj;
        this.zzd = obj2;
        this.zze = obj3;
    }

    public final void run() {
        zzfa zzm = this.zzf.zzs.zzm();
        if (zzm.zzx()) {
            if (this.zzf.zza == 0) {
                if (this.zzf.zzs.zzf().zzy()) {
                    zzel zzel = this.zzf;
                    zzel.zzs.zzaw();
                    zzel.zza = 'C';
                } else {
                    zzel zzel2 = this.zzf;
                    zzel2.zzs.zzaw();
                    zzel2.zza = 'c';
                }
            }
            if (this.zzf.zzb < 0) {
                zzel zzel3 = this.zzf;
                zzel3.zzs.zzf().zzh();
                zzel3.zzb = 46000;
            }
            char charAt = "01VDIWEA?".charAt(this.zza);
            char zza2 = this.zzf.zza;
            long zzb2 = this.zzf.zzb;
            String zzo = zzel.zzo(true, this.zzb, this.zzc, this.zzd, this.zze);
            StringBuilder sb = new StringBuilder(zzo.length() + 24);
            sb.append("2");
            sb.append(charAt);
            sb.append(zza2);
            sb.append(zzb2);
            sb.append(":");
            sb.append(zzo);
            String sb2 = sb.toString();
            if (sb2.length() > 1024) {
                sb2 = this.zzb.substring(0, 1024);
            }
            zzey zzey = zzm.zzb;
            if (zzey != null) {
                zzey.zzb(sb2, 1);
                return;
            }
            return;
        }
        Log.println(6, this.zzf.zzq(), "Persisted config not initialized. Not logging error/warn");
    }
}
