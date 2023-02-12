package com.google.android.gms.internal.p010firebaseauthapi;

import java.io.IOException;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdu */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzdu {
    public static final /* synthetic */ int zza = 0;
    /* access modifiers changed from: private */
    public static final String zzb = zzdu.class.getSimpleName();
    private final zzay zzc;
    private final zzag zzd;
    private final zzax zze;

    /* synthetic */ zzdu(zzdt zzdt, zzds zzds) throws GeneralSecurityException, IOException {
        this.zzc = zzdt.zza;
        this.zzd = zzdt.zzc;
        this.zze = zzdt.zze;
    }

    public final synchronized zzaw zza() throws GeneralSecurityException {
        return this.zze.zzb();
    }
}
