package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcr */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzcr extends zzat<zzji, zzjf> {
    final /* synthetic */ zzcs zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcr(zzcs zzcs, Class cls) {
        super(cls);
        this.zza = zzcs;
    }

    public final /* bridge */ /* synthetic */ zzaaz zza(zzyu zzyu) throws zzaae {
        return zzji.zzd(zzyu, zzzj.zza());
    }

    public final /* bridge */ /* synthetic */ Object zzc(zzaaz zzaaz) throws GeneralSecurityException {
        zzje zzb = zzjf.zzb();
        zzb.zza((zzji) zzaaz);
        zzb.zzb(0);
        return (zzjf) zzb.zzk();
    }

    public final /* bridge */ /* synthetic */ void zze(zzaaz zzaaz) throws GeneralSecurityException {
        zzji zzji = (zzji) zzaaz;
        if (zzji.zze().isEmpty() || !zzji.zzf()) {
            throw new GeneralSecurityException("invalid key format: missing KEK URI or DEK template");
        }
    }
}
