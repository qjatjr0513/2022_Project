package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcl */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzcl extends zzav<zzgm> {
    zzcl() {
        super(zzgm.class, new zzcj(zzag.class));
    }

    public final zzat<zzgp, zzgm> zza() {
        return new zzck(this, zzgp.class);
    }

    public final zzid zzb() {
        return zzid.SYMMETRIC;
    }

    public final /* bridge */ /* synthetic */ zzaaz zzc(zzyu zzyu) throws zzaae {
        return zzgm.zzd(zzyu, zzzj.zza());
    }

    public final String zzg() {
        return "type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key";
    }

    public final /* bridge */ /* synthetic */ void zzi(zzaaz zzaaz) throws GeneralSecurityException {
        zzgm zzgm = (zzgm) zzaaz;
        zzli.zzc(zzgm.zza(), 0);
        if (zzgm.zze().zzd() != 32) {
            throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
        }
    }
}
