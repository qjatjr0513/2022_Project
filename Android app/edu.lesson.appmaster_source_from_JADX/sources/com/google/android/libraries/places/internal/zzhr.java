package com.google.android.libraries.places.internal;

import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzhr<C> {
    private static final zzhu<Object, Object> zza = new zzhp();
    private static final zzht<Object, Object> zzb = new zzhq();
    /* access modifiers changed from: private */
    public final Map<zzhi<?>, zzhu<?, ? super C>> zzc = new HashMap();
    /* access modifiers changed from: private */
    public final Map<zzhi<?>, zzht<?, ? super C>> zzd = new HashMap();
    /* access modifiers changed from: private */
    public final zzhu<Object, ? super C> zze;
    /* access modifiers changed from: private */
    public zzht<Object, ? super C> zzf = null;

    /* synthetic */ zzhr(zzhu zzhu, zzho zzho) {
        this.zze = zzhu;
    }

    public final zzhr<C> zza(zzht<Object, ? super C> zzht) {
        this.zzf = zzht;
        return this;
    }

    public final zzhv<C> zzd() {
        return new zzhs(this, (zzho) null);
    }

    /* access modifiers changed from: package-private */
    public final <T> void zzg(zzhi<T> zzhi) {
        zzje.zza(zzhi, "key");
        if (zzhi.zzb()) {
            zzht<Object, Object> zzht = zzb;
            zzje.zza(zzhi, "key");
            if (zzhi.zzb()) {
                this.zzc.remove(zzhi);
                this.zzd.put(zzhi, zzht);
                return;
            }
            throw new IllegalArgumentException("key must be repeating");
        }
        zzhu<Object, Object> zzhu = zza;
        zzje.zza(zzhi, "key");
        this.zzd.remove(zzhi);
        this.zzc.put(zzhi, zzhu);
    }
}
