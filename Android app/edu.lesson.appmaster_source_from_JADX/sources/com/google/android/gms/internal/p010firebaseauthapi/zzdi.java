package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdi */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzdi extends zzav<zzhg> {
    public zzdi() {
        super(zzhg.class, new zzdh(zzam.class));
    }

    public final zzid zzb() {
        return zzid.ASYMMETRIC_PUBLIC;
    }

    public final /* bridge */ /* synthetic */ zzaaz zzc(zzyu zzyu) throws zzaae {
        return zzhg.zzf(zzyu, zzzj.zza());
    }

    public final String zzg() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey";
    }

    public final /* bridge */ /* synthetic */ void zzi(zzaaz zzaaz) throws GeneralSecurityException {
        zzhg zzhg = (zzhg) zzaaz;
        zzli.zzc(zzhg.zza(), 0);
        zzdp.zzb(zzhg.zzb());
    }
}
