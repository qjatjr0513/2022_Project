package com.google.android.gms.internal.p010firebaseauthapi;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbo */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzbo {
    public static final Charset zza = Charset.forName("UTF-8");

    public static zziw zza(zzir zzir) {
        zzit zza2 = zziw.zza();
        zza2.zzb(zzir.zzb());
        for (zziq next : zzir.zzg()) {
            zziu zzb = zziv.zzb();
            zzb.zzd(next.zzb().zzf());
            zzb.zzc(next.zzc());
            zzb.zzb(next.zzf());
            zzb.zza(next.zza());
            zza2.zza((zziv) zzb.zzk());
        }
        return (zziw) zza2.zzk();
    }

    public static void zzb(zzir zzir) throws GeneralSecurityException {
        int zzb = zzir.zzb();
        int i = 0;
        boolean z = false;
        boolean z2 = true;
        for (zziq next : zzir.zzg()) {
            if (next.zzc() == zzig.ENABLED) {
                if (!next.zzl()) {
                    throw new GeneralSecurityException(String.format("key %d has no key data", new Object[]{Integer.valueOf(next.zza())}));
                } else if (next.zzf() == zzjk.UNKNOWN_PREFIX) {
                    throw new GeneralSecurityException(String.format("key %d has unknown prefix", new Object[]{Integer.valueOf(next.zza())}));
                } else if (next.zzc() != zzig.UNKNOWN_STATUS) {
                    if (next.zza() == zzb) {
                        if (!z) {
                            z = true;
                        } else {
                            throw new GeneralSecurityException("keyset contains multiple primary keys");
                        }
                    }
                    z2 &= next.zzb().zzb() == zzid.ASYMMETRIC_PUBLIC;
                    i++;
                } else {
                    throw new GeneralSecurityException(String.format("key %d has unknown status", new Object[]{Integer.valueOf(next.zza())}));
                }
            }
        }
        if (i == 0) {
            throw new GeneralSecurityException("keyset must contain at least one ENABLED key");
        } else if (!z && !z2) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }
}
