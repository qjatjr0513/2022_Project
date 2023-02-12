package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzax {
    final Map<String, zzaw> zza = new HashMap();
    final zzbj zzb = new zzbj();

    public zzax() {
        zzb(new zzav());
        zzb(new zzay());
        zzb(new zzaz());
        zzb(new zzbc());
        zzb(new zzbh());
        zzb(new zzbi());
        zzb(new zzbk());
    }

    public final zzap zza(zzg zzg, zzap zzap) {
        zzaw zzaw;
        zzh.zzc(zzg);
        if (!(zzap instanceof zzaq)) {
            return zzap;
        }
        zzaq zzaq = (zzaq) zzap;
        ArrayList<zzap> zzc = zzaq.zzc();
        String zzb2 = zzaq.zzb();
        if (this.zza.containsKey(zzb2)) {
            zzaw = this.zza.get(zzb2);
        } else {
            zzaw = this.zzb;
        }
        return zzaw.zza(zzb2, zzg, zzc);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzaw zzaw) {
        for (zzbl zzb2 : zzaw.zza) {
            this.zza.put(zzb2.zzb().toString(), zzaw);
        }
    }
}
