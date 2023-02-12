package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcs */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzcs extends zzav<zzjf> {
    zzcs() {
        super(zzjf.class, new zzcq(zzag.class));
    }

    public final zzat<zzji, zzjf> zza() {
        return new zzcr(this, zzji.class);
    }

    public final zzid zzb() {
        return zzid.REMOTE;
    }

    public final /* bridge */ /* synthetic */ zzaaz zzc(zzyu zzyu) throws zzaae {
        return zzjf.zzd(zzyu, zzzj.zza());
    }

    public final String zzg() {
        return "type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey";
    }

    public final /* bridge */ /* synthetic */ void zzi(zzaaz zzaaz) throws GeneralSecurityException {
        zzli.zzc(((zzjf) zzaaz).zza(), 0);
    }
}
