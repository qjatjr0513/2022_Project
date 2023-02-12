package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzed */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzed extends zzau<zzbb, zzht> {
    zzed(Class cls) {
        super(cls);
    }

    public final /* bridge */ /* synthetic */ Object zzb(Object obj) throws GeneralSecurityException {
        zzht zzht = (zzht) obj;
        zzhq zzb = zzht.zzf().zzb();
        SecretKeySpec secretKeySpec = new SecretKeySpec(zzht.zzg().zzs(), "HMAC");
        int zza = zzht.zzf().zza();
        zzhq zzhq = zzhq.UNKNOWN_HASH;
        switch (zzb.ordinal()) {
            case 1:
                return new zzle(new zzld("HMACSHA1", secretKeySpec), zza);
            case 2:
                return new zzle(new zzld("HMACSHA384", secretKeySpec), zza);
            case 3:
                return new zzle(new zzld("HMACSHA256", secretKeySpec), zza);
            case 4:
                return new zzle(new zzld("HMACSHA512", secretKeySpec), zza);
            case 5:
                return new zzle(new zzld("HMACSHA224", secretKeySpec), zza);
            default:
                throw new GeneralSecurityException("unknown hash");
        }
    }
}
