package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.SecureRandom;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlf */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzlf extends ThreadLocal<SecureRandom> {
    zzlf() {
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object initialValue() {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextLong();
        return secureRandom;
    }
}
