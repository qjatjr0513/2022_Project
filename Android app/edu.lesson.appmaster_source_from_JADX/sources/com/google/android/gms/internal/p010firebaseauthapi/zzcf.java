package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcf */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzcf extends zzav<zzfu> {
    zzcf() {
        super(zzfu.class, new zzcd(zzag.class));
    }

    static /* synthetic */ zzas zzj(int i, int i2) {
        zzfw zzb = zzfx.zzb();
        zzb.zza(i);
        return new zzas((zzfx) zzb.zzk(), i2);
    }

    public final zzat<zzfx, zzfu> zza() {
        return new zzce(this, zzfx.class);
    }

    public final zzid zzb() {
        return zzid.SYMMETRIC;
    }

    public final /* bridge */ /* synthetic */ zzaaz zzc(zzyu zzyu) throws zzaae {
        return zzfu.zzd(zzyu, zzzj.zza());
    }

    public final String zzg() {
        return "type.googleapis.com/google.crypto.tink.AesGcmKey";
    }

    public final /* bridge */ /* synthetic */ void zzi(zzaaz zzaaz) throws GeneralSecurityException {
        zzfu zzfu = (zzfu) zzaaz;
        zzli.zzc(zzfu.zza(), 0);
        zzli.zzb(zzfu.zze().zzd());
    }
}
