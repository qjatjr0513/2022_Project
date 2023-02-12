package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzew implements zzev {
    private final zzdf zza;
    private final zzdj zzb;

    public zzew(zzdj zzdj, zzdf zzdf, byte[] bArr) {
        this.zzb = zzdj;
        this.zza = zzdf;
    }

    public final void zza(zzeu zzeu) {
        zzvt zza2 = zzvw.zza();
        zza2.zzg(zzeu.zzz());
        zza2.zzd(zzeu.zzx());
        zza2.zze(zzeu.zzy());
        zza2.zzj(zzeu.zzd());
        zza2.zzc(zzeu.zzb());
        zza2.zzb(zzeu.zza());
        zza2.zzk(zzeu.zze());
        zza2.zzh(zzeu.zzk().length());
        zza2.zzl(zzeu.zzg());
        zza2.zzf(zzeu.zzc());
        zza2.zzi(zzeu.zzA());
        zza2.zza(zzeu.zzf());
        if (zzeu.zzi() == zzdv.FRAGMENT) {
            zza2.zzn(2);
        } else if (zzeu.zzi() == zzdv.INTENT) {
            zza2.zzn(3);
        } else {
            zza2.zzn(1);
        }
        if (zzeu.zzj() == AutocompleteActivityMode.FULLSCREEN) {
            zza2.zzm(2);
        } else if (zzeu.zzj() == AutocompleteActivityMode.OVERLAY) {
            zza2.zzm(1);
        }
        zzwc zzb2 = zzdk.zzb(this.zza);
        zzb2.zzl(10);
        zzb2.zzc((zzvw) zza2.zzt());
        this.zzb.zza(zzdk.zza((zzwh) zzb2.zzt()));
    }
}
