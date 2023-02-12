package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzr;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzfk implements zzr {
    final /* synthetic */ zzfm zza;

    zzfk(zzfm zzfm) {
        this.zza = zzfm;
    }

    public final void zza(int i, String str, List<String> list, boolean z, boolean z2) {
        zzej zzej;
        switch (i - 1) {
            case 0:
                zzej = this.zza.zzs.zzay().zzc();
                break;
            case 1:
                if (!z) {
                    if (z2) {
                        zzej = this.zza.zzs.zzay().zzd();
                        break;
                    } else {
                        zzej = this.zza.zzs.zzay().zze();
                        break;
                    }
                } else {
                    zzej = this.zza.zzs.zzay().zzh();
                    break;
                }
            case 3:
                zzej = this.zza.zzs.zzay().zzj();
                break;
            case 4:
                if (!z) {
                    if (z2) {
                        zzej = this.zza.zzs.zzay().zzk();
                        break;
                    } else {
                        zzej = this.zza.zzs.zzay().zzl();
                        break;
                    }
                } else {
                    zzej = this.zza.zzs.zzay().zzm();
                    break;
                }
            default:
                zzej = this.zza.zzs.zzay().zzi();
                break;
        }
        switch (list.size()) {
            case 1:
                zzej.zzb(str, list.get(0));
                return;
            case 2:
                zzej.zzc(str, list.get(0), list.get(1));
                return;
            case 3:
                zzej.zzd(str, list.get(0), list.get(1), list.get(2));
                return;
            default:
                zzej.zza(str);
                return;
        }
    }
}
