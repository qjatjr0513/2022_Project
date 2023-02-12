package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzby */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzby extends zzat<zzff, zzfc> {
    final /* synthetic */ zzbz zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzby(zzbz zzbz, Class cls) {
        super(cls);
        this.zza = zzbz;
    }

    public static final zzfc zzg(zzff zzff) throws GeneralSecurityException {
        zzfb zzb = zzfc.zzb();
        zzb.zzb(zzff.zzf());
        zzb.zza(zzyu.zzn(zzlg.zza(zzff.zza())));
        zzb.zzc(0);
        return (zzfc) zzb.zzk();
    }

    public final /* bridge */ /* synthetic */ zzaaz zza(zzyu zzyu) throws zzaae {
        return zzff.zze(zzyu, zzzj.zza());
    }

    public final /* bridge */ /* synthetic */ Object zzc(zzaaz zzaaz) throws GeneralSecurityException {
        return zzg((zzff) zzaaz);
    }

    /* renamed from: zzf */
    public final void zze(zzff zzff) throws GeneralSecurityException {
        zzli.zzb(zzff.zza());
        zzbz.zzl(zzff.zzf());
    }
}
