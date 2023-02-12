package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzci */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzci extends zzav<zzga> {
    zzci() {
        super(zzga.class, new zzcg(zzag.class));
    }

    public static void zzj(boolean z) throws GeneralSecurityException {
        if (zzl()) {
            zzbn.zzm(new zzci(), true);
        }
    }

    static /* synthetic */ zzas zzk(int i, int i2) {
        zzgc zzb = zzgd.zzb();
        zzb.zza(i);
        return new zzas((zzgd) zzb.zzk(), i2);
    }

    private static boolean zzl() {
        try {
            Cipher.getInstance("AES/GCM-SIV/NoPadding");
            return true;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            return false;
        }
    }

    public final zzat<zzgd, zzga> zza() {
        return new zzch(this, zzgd.class);
    }

    public final zzid zzb() {
        return zzid.SYMMETRIC;
    }

    public final /* bridge */ /* synthetic */ zzaaz zzc(zzyu zzyu) throws zzaae {
        return zzga.zzd(zzyu, zzzj.zza());
    }

    public final String zzg() {
        return "type.googleapis.com/google.crypto.tink.AesGcmSivKey";
    }

    public final /* bridge */ /* synthetic */ void zzi(zzaaz zzaaz) throws GeneralSecurityException {
        zzga zzga = (zzga) zzaaz;
        zzli.zzc(zzga.zza(), 0);
        zzli.zzb(zzga.zze().zzd());
    }
}
