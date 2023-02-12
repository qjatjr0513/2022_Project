package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzgm implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ zzgn zze;

    zzgm(zzgn zzgn, String str, String str2, String str3, long j) {
        this.zze = zzgn;
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = j;
    }

    public final void run() {
        String str = this.zza;
        if (str == null) {
            this.zze.zza.zzq().zzs().zzy(this.zzb, (zzih) null);
            return;
        }
        this.zze.zza.zzq().zzs().zzy(this.zzb, new zzih(this.zzc, str, this.zzd));
    }
}
