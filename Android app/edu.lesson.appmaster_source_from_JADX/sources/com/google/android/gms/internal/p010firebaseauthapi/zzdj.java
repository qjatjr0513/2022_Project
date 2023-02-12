package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdj */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzdj {
    @Deprecated
    public static final zzjn zza = zzjn.zzb();
    @Deprecated
    public static final zzjn zzb = zzjn.zzb();
    @Deprecated
    public static final zzjn zzc = zzjn.zzb();

    static {
        new zzdi();
        new zzdg();
        try {
            zza();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zzbn.zzn(new zzdl());
        zzbn.zzn(new zzdn());
        zzbp.zza();
        zzbn.zzl(new zzdg(), new zzdi(), true);
    }
}
