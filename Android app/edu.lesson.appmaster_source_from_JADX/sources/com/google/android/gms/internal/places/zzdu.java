package com.google.android.gms.internal.places;

import java.io.IOException;

final class zzdu extends zzds<zzdr, zzdr> {
    zzdu() {
    }

    private static void zzb(Object obj, zzdr zzdr) {
        ((zzbc) obj).zzih = zzdr;
    }

    /* access modifiers changed from: package-private */
    public final void zzd(Object obj) {
        ((zzbc) obj).zzih.zzab();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ int zzn(Object obj) {
        return ((zzdr) obj).zzbh();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ int zzs(Object obj) {
        return ((zzdr) obj).zzdj();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzh(Object obj, Object obj2) {
        zzdr zzdr = (zzdr) obj;
        zzdr zzdr2 = (zzdr) obj2;
        if (zzdr2.equals(zzdr.zzdh())) {
            return zzdr;
        }
        return zzdr.zzb(zzdr, zzdr2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(Object obj, zzel zzel) throws IOException {
        ((zzdr) obj).zzb(zzel);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Object obj, zzel zzel) throws IOException {
        ((zzdr) obj).zzc(zzel);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzg(Object obj, Object obj2) {
        zzb(obj, (zzdr) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzr(Object obj) {
        return ((zzbc) obj).zzih;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(Object obj, Object obj2) {
        zzb(obj, (zzdr) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzdk() {
        return zzdr.zzdi();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Object obj, int i, zzw zzw) {
        ((zzdr) obj).zzc((i << 3) | 2, zzw);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Object obj, int i, long j) {
        ((zzdr) obj).zzc(i << 3, Long.valueOf(j));
    }
}
