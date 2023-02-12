package com.google.android.gms.measurement;

import android.os.Bundle;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzfv;
import com.google.android.gms.measurement.internal.zzgv;
import com.google.android.gms.measurement.internal.zzgw;
import com.google.android.gms.measurement.internal.zzia;
import com.google.android.gms.measurement.internal.zzkv;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zza extends zzd {
    private final zzfv zza;
    private final zzia zzb;

    public zza(zzfv zzfv) {
        super((zzc) null);
        Preconditions.checkNotNull(zzfv);
        this.zza = zzfv;
        this.zzb = zzfv.zzq();
    }

    public final int zza(String str) {
        this.zzb.zzh(str);
        return 25;
    }

    public final long zzb() {
        return this.zza.zzv().zzq();
    }

    public final Boolean zzc() {
        return this.zzb.zzi();
    }

    public final Double zzd() {
        return this.zzb.zzj();
    }

    public final Integer zze() {
        return this.zzb.zzl();
    }

    public final Long zzf() {
        return this.zzb.zzm();
    }

    public final String zzh() {
        return this.zzb.zzo();
    }

    public final String zzi() {
        return this.zzb.zzp();
    }

    public final String zzj() {
        return this.zzb.zzq();
    }

    public final String zzk() {
        return this.zzb.zzo();
    }

    public final String zzl() {
        return this.zzb.zzr();
    }

    public final List<Bundle> zzm(String str, String str2) {
        return this.zzb.zzs(str, str2);
    }

    public final Map<String, Object> zzn(boolean z) {
        List<zzkv> zzt = this.zzb.zzt(z);
        ArrayMap arrayMap = new ArrayMap(zzt.size());
        for (zzkv next : zzt) {
            Object zza2 = next.zza();
            if (zza2 != null) {
                arrayMap.put(next.zzb, zza2);
            }
        }
        return arrayMap;
    }

    public final Map<String, Object> zzo(String str, String str2, boolean z) {
        return this.zzb.zzu(str, str2, z);
    }

    public final void zzp(String str) {
        this.zza.zzd().zzd(str, this.zza.zzav().elapsedRealtime());
    }

    public final void zzq(String str, String str2, Bundle bundle) {
        this.zza.zzq().zzz(str, str2, bundle);
    }

    public final void zzr(String str) {
        this.zza.zzd().zze(str, this.zza.zzav().elapsedRealtime());
    }

    public final void zzs(String str, String str2, Bundle bundle) {
        this.zzb.zzD(str, str2, bundle);
    }

    public final void zzt(String str, String str2, Bundle bundle, long j) {
        this.zzb.zzE(str, str2, bundle, true, false, j);
    }

    public final void zzu(zzgw zzgw) {
        this.zzb.zzJ(zzgw);
    }

    public final void zzv(Bundle bundle) {
        this.zzb.zzP(bundle);
    }

    public final void zzw(zzgv zzgv) {
        this.zzb.zzU(zzgv);
    }

    public final void zzx(zzgw zzgw) {
        this.zzb.zzaa(zzgw);
    }

    public final Object zzg(int i) {
        switch (i) {
            case 0:
                return this.zzb.zzr();
            case 1:
                return this.zzb.zzm();
            case 2:
                return this.zzb.zzj();
            case 3:
                return this.zzb.zzl();
            default:
                return this.zzb.zzi();
        }
    }
}
