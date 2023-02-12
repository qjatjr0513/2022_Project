package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcv */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzcv extends zzav<zzjq> {
    zzcv() {
        super(zzjq.class, new zzct(zzag.class));
    }

    public final zzat<zzjt, zzjq> zza() {
        return new zzcu(this, zzjt.class);
    }

    public final zzid zzb() {
        return zzid.SYMMETRIC;
    }

    public final /* bridge */ /* synthetic */ zzaaz zzc(zzyu zzyu) throws zzaae {
        return zzjq.zzd(zzyu, zzzj.zza());
    }

    public final String zzg() {
        return "type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key";
    }

    public final /* bridge */ /* synthetic */ void zzi(zzaaz zzaaz) throws GeneralSecurityException {
        zzjq zzjq = (zzjq) zzaaz;
        zzli.zzc(zzjq.zza(), 0);
        if (zzjq.zze().zzd() != 32) {
            throw new GeneralSecurityException("invalid XChaCha20Poly1305Key: incorrect key length");
        }
    }
}
