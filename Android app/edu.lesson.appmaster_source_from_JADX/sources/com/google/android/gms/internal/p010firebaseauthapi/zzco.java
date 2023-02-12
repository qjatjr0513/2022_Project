package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzco */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzco extends zzav<zziz> {
    zzco() {
        super(zziz.class, new zzcm(zzag.class));
    }

    public final zzat<zzjc, zziz> zza() {
        return new zzcn(this, zzjc.class);
    }

    public final zzid zzb() {
        return zzid.REMOTE;
    }

    public final /* bridge */ /* synthetic */ zzaaz zzc(zzyu zzyu) throws zzaae {
        return zziz.zzd(zzyu, zzzj.zza());
    }

    public final String zzg() {
        return "type.googleapis.com/google.crypto.tink.KmsAeadKey";
    }

    public final /* bridge */ /* synthetic */ void zzi(zzaaz zzaaz) throws GeneralSecurityException {
        zzli.zzc(((zziz) zzaaz).zza(), 0);
    }
}
