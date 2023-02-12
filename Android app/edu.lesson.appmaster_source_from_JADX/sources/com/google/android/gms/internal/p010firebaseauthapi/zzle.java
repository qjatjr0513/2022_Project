package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzle */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzle implements zzbb {
    private final zzek zza;
    private final int zzb;

    public zzle(zzek zzek, int i) throws GeneralSecurityException {
        this.zza = zzek;
        this.zzb = i;
        if (i >= 10) {
            zzek.zza(new byte[0], i);
            return;
        }
        throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
    }

    public final void zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (!zzkd.zzb(this.zza.zza(bArr2, this.zzb), bArr)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }
}
