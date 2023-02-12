package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzm extends zzai {
    final /* synthetic */ zzo zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzm(zzn zzn, String str, zzo zzo) {
        super("getValue");
        this.zza = zzo;
    }

    public final zzap zza(zzg zzg, List<zzap> list) {
        zzh.zzh("getValue", 2, list);
        zzap zzb = zzg.zzb(list.get(0));
        zzap zzb2 = zzg.zzb(list.get(1));
        String zza2 = this.zza.zza(zzb.zzi());
        return zza2 != null ? new zzat(zza2) : zzb2;
    }
}
