package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzeg */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzeg {
    @Deprecated
    public static final zzjn zza;
    @Deprecated
    public static final zzjn zzb;
    @Deprecated
    public static final zzjn zzc;

    static {
        new zzef();
        zzjn zzb2 = zzjn.zzb();
        zza = zzb2;
        zzb = zzb2;
        zzc = zzb2;
        try {
            zza();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zzbn.zzn(new zzej());
        zzbn.zzm(new zzef(), true);
        zzbn.zzm(new zzec(), true);
    }
}
