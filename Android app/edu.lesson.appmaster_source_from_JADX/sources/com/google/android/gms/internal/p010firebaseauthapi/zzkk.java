package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkk */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzkk implements zzam {
    private final zzkm zza;
    private final String zzb;
    private final byte[] zzc;
    private final zzki zzd;

    public zzkk(ECPublicKey eCPublicKey, byte[] bArr, String str, int i, zzki zzki) throws GeneralSecurityException {
        zzkn.zzd(eCPublicKey.getW(), eCPublicKey.getParams().getCurve());
        this.zza = new zzkm(eCPublicKey);
        this.zzc = bArr;
        this.zzb = str;
        this.zzd = zzki;
    }
}
