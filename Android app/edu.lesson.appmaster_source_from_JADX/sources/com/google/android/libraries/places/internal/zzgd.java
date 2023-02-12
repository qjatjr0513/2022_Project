package com.google.android.libraries.places.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzgd extends zzge {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzge zzc;

    zzgd(zzge zzge, int i, int i2) {
        this.zzc = zzge;
        this.zza = i;
        this.zzb = i2;
    }

    public final Object get(int i) {
        zzfm.zza(i, this.zzb, FirebaseAnalytics.Param.INDEX);
        return this.zzc.get(i + this.zza);
    }

    public final int size() {
        return this.zzb;
    }

    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    /* access modifiers changed from: package-private */
    public final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf() {
        return true;
    }

    /* access modifiers changed from: package-private */
    @CheckForNull
    public final Object[] zzg() {
        return this.zzc.zzg();
    }

    public final zzge zzh(int i, int i2) {
        zzfm.zzg(i, i2, this.zzb);
        zzge zzge = this.zzc;
        int i3 = this.zza;
        return zzge.subList(i + i3, i2 + i3);
    }
}
