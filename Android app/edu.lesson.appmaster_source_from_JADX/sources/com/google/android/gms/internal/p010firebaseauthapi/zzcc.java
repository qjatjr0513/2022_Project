package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcc */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzcc extends zzav<zzfl> {
    zzcc() {
        super(zzfl.class, new zzca(zzag.class));
    }

    static /* synthetic */ zzas zzj(int i, int i2, int i3) {
        zzfn zzb = zzfo.zzb();
        zzb.zza(i);
        zzfq zzb2 = zzfr.zzb();
        zzb2.zza(16);
        zzb.zzb((zzfr) zzb2.zzk());
        return new zzas((zzfo) zzb.zzk(), i3);
    }

    public final zzat<zzfo, zzfl> zza() {
        return new zzcb(this, zzfo.class);
    }

    public final zzid zzb() {
        return zzid.SYMMETRIC;
    }

    public final /* bridge */ /* synthetic */ zzaaz zzc(zzyu zzyu) throws zzaae {
        return zzfl.zzd(zzyu, zzzj.zza());
    }

    public final String zzg() {
        return "type.googleapis.com/google.crypto.tink.AesEaxKey";
    }

    public final /* bridge */ /* synthetic */ void zzi(zzaaz zzaaz) throws GeneralSecurityException {
        zzfl zzfl = (zzfl) zzaaz;
        zzli.zzc(zzfl.zza(), 0);
        zzli.zzb(zzfl.zzf().zzd());
        if (zzfl.zze().zza() != 12 && zzfl.zze().zza() != 16) {
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
    }
}
