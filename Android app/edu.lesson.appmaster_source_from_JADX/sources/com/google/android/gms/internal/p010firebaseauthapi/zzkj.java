package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.EllipticCurve;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkj */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzkj implements zzal {
    private static final byte[] zza = new byte[0];
    private final ECPrivateKey zzb;
    private final zzkl zzc;
    private final String zzd;
    private final byte[] zze;
    private final zzki zzf;
    private final int zzg;

    public zzkj(ECPrivateKey eCPrivateKey, byte[] bArr, String str, int i, zzki zzki) throws GeneralSecurityException {
        this.zzb = eCPrivateKey;
        this.zzc = new zzkl(eCPrivateKey);
        this.zze = bArr;
        this.zzd = str;
        this.zzg = i;
        this.zzf = zzki;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int i;
        EllipticCurve curve = this.zzb.getParams().getCurve();
        int i2 = this.zzg;
        int zza2 = zzkn.zza(curve);
        switch (i2 - 1) {
            case 0:
                i = zza2 + zza2 + 1;
                break;
            case 2:
                i = zza2 + zza2;
                break;
            default:
                i = zza2 + 1;
                break;
        }
        int length = bArr.length;
        if (length >= i) {
            return this.zzf.zzb(this.zzc.zza(Arrays.copyOfRange(bArr, 0, i), this.zzd, this.zze, (byte[]) null, this.zzf.zza(), this.zzg)).zza(Arrays.copyOfRange(bArr, i, length), zza);
        }
        throw new GeneralSecurityException("ciphertext too short");
    }
}
