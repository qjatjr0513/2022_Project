package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdb */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzdb {
    public static final String zza = "type.googleapis.com/google.crypto.tink.AesSivKey";
    @Deprecated
    public static final zzjn zzb = zzjn.zzb();
    @Deprecated
    public static final zzjn zzc = zzjn.zzb();

    static {
        new zzda();
        try {
            zzbn.zzn(new zzdd());
            zzbn.zzm(new zzda(), true);
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
