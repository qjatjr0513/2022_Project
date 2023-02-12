package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzef */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzef extends zzav<zzht> {
    public zzef() {
        super(zzht.class, new zzed(zzbb.class));
    }

    static /* synthetic */ zzas zzk(int i, int i2, zzhq zzhq, int i3) {
        zzhv zzb = zzhw.zzb();
        zzhy zzc = zzhz.zzc();
        zzc.zza(zzhq);
        zzc.zzb(i2);
        zzb.zzb((zzhz) zzc.zzk());
        zzb.zza(i);
        return new zzas((zzhw) zzb.zzk(), i3);
    }

    public static final void zzl(zzht zzht) throws GeneralSecurityException {
        zzli.zzc(zzht.zza(), 0);
        if (zzht.zzg().zzd() >= 16) {
            zzm(zzht.zzf());
            return;
        }
        throw new GeneralSecurityException("key too short");
    }

    /* access modifiers changed from: private */
    public static void zzm(zzhz zzhz) throws GeneralSecurityException {
        if (zzhz.zza() >= 10) {
            zzhq zzhq = zzhq.UNKNOWN_HASH;
            switch (zzhz.zzb().ordinal()) {
                case 1:
                    if (zzhz.zza() > 20) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                case 2:
                    if (zzhz.zza() > 48) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                case 3:
                    if (zzhz.zza() > 32) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                case 4:
                    if (zzhz.zza() > 64) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                case 5:
                    if (zzhz.zza() > 28) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                default:
                    throw new GeneralSecurityException("unknown hash type");
            }
        } else {
            throw new GeneralSecurityException("tag size too small");
        }
    }

    public final zzat<zzhw, zzht> zza() {
        return new zzee(this, zzhw.class);
    }

    public final zzid zzb() {
        return zzid.SYMMETRIC;
    }

    public final /* bridge */ /* synthetic */ zzaaz zzc(zzyu zzyu) throws zzaae {
        return zzht.zze(zzyu, zzzj.zza());
    }

    public final String zzg() {
        return "type.googleapis.com/google.crypto.tink.HmacKey";
    }

    public final /* bridge */ /* synthetic */ void zzi(zzaaz zzaaz) throws GeneralSecurityException {
        zzl((zzht) zzaaz);
    }
}
