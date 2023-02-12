package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzg {
    public final zzg zza;
    final zzax zzb;
    final Map<String, zzap> zzc = new HashMap();
    final Map<String, Boolean> zzd = new HashMap();

    public zzg(zzg zzg, zzax zzax) {
        this.zza = zzg;
        this.zzb = zzax;
    }

    public final zzg zza() {
        return new zzg(this, this.zzb);
    }

    public final zzap zzb(zzap zzap) {
        return this.zzb.zza(this, zzap);
    }

    public final zzap zzc(zzae zzae) {
        zzap zzap = zzap.zzf;
        Iterator<Integer> zzk = zzae.zzk();
        while (zzk.hasNext()) {
            zzap = this.zzb.zza(this, zzae.zze(zzk.next().intValue()));
            if (zzap instanceof zzag) {
                break;
            }
        }
        return zzap;
    }

    public final zzap zzd(String str) {
        if (this.zzc.containsKey(str)) {
            return this.zzc.get(str);
        }
        zzg zzg = this.zza;
        if (zzg != null) {
            return zzg.zzd(str);
        }
        throw new IllegalArgumentException(String.format("%s is not defined", new Object[]{str}));
    }

    public final void zze(String str, zzap zzap) {
        if (!this.zzd.containsKey(str)) {
            if (zzap == null) {
                this.zzc.remove(str);
            } else {
                this.zzc.put(str, zzap);
            }
        }
    }

    public final void zzf(String str, zzap zzap) {
        zze(str, zzap);
        this.zzd.put(str, true);
    }

    public final void zzg(String str, zzap zzap) {
        zzg zzg;
        if (!this.zzc.containsKey(str) && (zzg = this.zza) != null && zzg.zzh(str)) {
            this.zza.zzg(str, zzap);
        } else if (!this.zzd.containsKey(str)) {
            if (zzap == null) {
                this.zzc.remove(str);
            } else {
                this.zzc.put(str, zzap);
            }
        }
    }

    public final boolean zzh(String str) {
        if (this.zzc.containsKey(str)) {
            return true;
        }
        zzg zzg = this.zza;
        if (zzg != null) {
            return zzg.zzh(str);
        }
        return false;
    }
}
