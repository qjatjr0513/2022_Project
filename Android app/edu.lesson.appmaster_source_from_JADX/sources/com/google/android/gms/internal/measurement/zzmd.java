package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
final class zzmd extends zzmb<zzmc, zzmc> {
    zzmd() {
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ int zza(Object obj) {
        return ((zzmc) obj).zza();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ int zzb(Object obj) {
        return ((zzmc) obj).zzb();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzc(Object obj) {
        return ((zzjx) obj).zzc;
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ Object zzd(Object obj, Object obj2) {
        zzmc zzmc = (zzmc) obj2;
        if (zzmc.equals(zzmc.zzc())) {
            return obj;
        }
        return zzmc.zzd((zzmc) obj, zzmc);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zze() {
        return zzmc.zze();
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ void zzf(Object obj, int i, long j) {
        ((zzmc) obj).zzh(i << 3, Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public final void zzg(Object obj) {
        ((zzjx) obj).zzc.zzf();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzh(Object obj, Object obj2) {
        ((zzjx) obj).zzc = (zzmc) obj2;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzi(Object obj, zzjf zzjf) throws IOException {
        ((zzmc) obj).zzi(zzjf);
    }
}
