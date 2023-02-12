package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdr */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzdr {
    private final zzag zza;
    private final zzak zzb;

    public zzdr(zzag zzag) {
        this.zza = zzag;
        this.zzb = null;
    }

    public zzdr(zzak zzak) {
        this.zza = null;
        this.zzb = zzak;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        zzag zzag = this.zza;
        if (zzag != null) {
            return zzag.zza(bArr, bArr2);
        }
        return this.zzb.zza(bArr, bArr2);
    }
}
