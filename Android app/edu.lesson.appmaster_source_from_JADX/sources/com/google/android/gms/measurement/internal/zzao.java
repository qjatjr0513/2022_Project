package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zzao {
    final String zza;
    final String zzb;
    final String zzc;
    final long zzd;
    final long zze;
    final zzar zzf;

    zzao(zzfv zzfv, String str, String str2, String str3, long j, long j2, Bundle bundle) {
        zzar zzar;
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        this.zza = str2;
        this.zzb = str3;
        this.zzc = true == TextUtils.isEmpty(str) ? null : str;
        this.zzd = j;
        this.zze = j2;
        if (j2 != 0 && j2 > j) {
            zzfv.zzay().zzk().zzb("Event created with reverse previous/current timestamps. appId", zzel.zzn(str2));
        }
        if (bundle == null || bundle.isEmpty()) {
            zzar = new zzar(new Bundle());
        } else {
            Bundle bundle2 = new Bundle(bundle);
            Iterator it = bundle2.keySet().iterator();
            while (it.hasNext()) {
                String str4 = (String) it.next();
                if (str4 == null) {
                    zzfv.zzay().zzd().zza("Param name can't be null");
                    it.remove();
                } else {
                    Object zzA = zzfv.zzv().zzA(str4, bundle2.get(str4));
                    if (zzA == null) {
                        zzfv.zzay().zzk().zzb("Param value can't be null", zzfv.zzj().zze(str4));
                        it.remove();
                    } else {
                        zzfv.zzv().zzN(bundle2, str4, zzA);
                    }
                }
            }
            zzar = new zzar(bundle2);
        }
        this.zzf = zzar;
    }

    public final String toString() {
        String str = this.zza;
        String str2 = this.zzb;
        String obj = this.zzf.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 33 + String.valueOf(str2).length() + obj.length());
        sb.append("Event{appId='");
        sb.append(str);
        sb.append("', name='");
        sb.append(str2);
        sb.append("', params=");
        sb.append(obj);
        sb.append('}');
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final zzao zza(zzfv zzfv, long j) {
        return new zzao(zzfv, this.zzc, this.zza, this.zzb, this.zzd, j, this.zzf);
    }

    private zzao(zzfv zzfv, String str, String str2, String str3, long j, long j2, zzar zzar) {
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzar);
        this.zza = str2;
        this.zzb = str3;
        this.zzc = true == TextUtils.isEmpty(str) ? null : str;
        this.zzd = j;
        this.zze = j2;
        if (j2 != 0 && j2 > j) {
            zzfv.zzay().zzk().zzc("Event created with reverse previous/current timestamps. appId, name", zzel.zzn(str2), zzel.zzn(str3));
        }
        this.zzf = zzar;
    }
}
