package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbw */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzbw extends zzav<zzew> {
    zzbw() {
        super(zzew.class, new zzbu(zzag.class));
    }

    static /* synthetic */ zzas zzj(int i, int i2, int i3, int i4, zzhq zzhq, int i5) {
        zzfe zzb = zzff.zzb();
        zzfh zzb2 = zzfi.zzb();
        zzb2.zza(16);
        zzb.zzb((zzfi) zzb2.zzk());
        zzb.zza(i);
        zzhv zzb3 = zzhw.zzb();
        zzhy zzc = zzhz.zzc();
        zzc.zza(zzhq);
        zzc.zzb(i4);
        zzb3.zzb((zzhz) zzc.zzk());
        zzb3.zza(32);
        zzey zza = zzez.zza();
        zza.zza((zzff) zzb.zzk());
        zza.zzb((zzhw) zzb3.zzk());
        return new zzas((zzez) zza.zzk(), i5);
    }

    public final zzat<zzez, zzew> zza() {
        return new zzbv(this, zzez.class);
    }

    public final zzid zzb() {
        return zzid.SYMMETRIC;
    }

    public final /* bridge */ /* synthetic */ zzaaz zzc(zzyu zzyu) throws zzaae {
        return zzew.zzd(zzyu, zzzj.zza());
    }

    public final String zzg() {
        return "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
    }

    public final /* bridge */ /* synthetic */ void zzi(zzaaz zzaaz) throws GeneralSecurityException {
        zzew zzew = (zzew) zzaaz;
        zzli.zzc(zzew.zza(), 0);
        new zzbz();
        zzbz.zzk(zzew.zze());
        new zzef();
        zzef.zzl(zzew.zzf());
    }
}
