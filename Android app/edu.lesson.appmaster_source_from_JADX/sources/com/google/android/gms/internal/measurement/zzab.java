package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzab {
    private zzaa zza;
    private zzaa zzb;
    private final List<zzaa> zzc;

    public zzab() {
        this.zza = new zzaa("", 0, (Map<String, Object>) null);
        this.zzb = new zzaa("", 0, (Map<String, Object>) null);
        this.zzc = new ArrayList();
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzab zzab = new zzab(this.zza.clone());
        for (zzaa zzb2 : this.zzc) {
            zzab.zzc.add(zzb2.clone());
        }
        return zzab;
    }

    public final zzaa zza() {
        return this.zza;
    }

    public final zzaa zzb() {
        return this.zzb;
    }

    public final List<zzaa> zzc() {
        return this.zzc;
    }

    public final void zzd(zzaa zzaa) {
        this.zza = zzaa;
        this.zzb = zzaa.clone();
        this.zzc.clear();
    }

    public final void zze(String str, long j, Map<String, Object> map) {
        this.zzc.add(new zzaa(str, j, map));
    }

    public final void zzf(zzaa zzaa) {
        this.zzb = zzaa;
    }

    public zzab(zzaa zzaa) {
        this.zza = zzaa;
        this.zzb = zzaa.clone();
        this.zzc = new ArrayList();
    }
}
