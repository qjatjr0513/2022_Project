package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzau */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public abstract class zzau<PrimitiveT, KeyT> {
    private final Class<PrimitiveT> zza;

    public zzau(Class<PrimitiveT> cls) {
        this.zza = cls;
    }

    /* access modifiers changed from: package-private */
    public final Class<PrimitiveT> zza() {
        return this.zza;
    }

    public abstract PrimitiveT zzb(KeyT keyt) throws GeneralSecurityException;
}
