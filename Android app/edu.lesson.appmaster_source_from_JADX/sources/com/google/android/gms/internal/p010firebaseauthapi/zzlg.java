package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.SecureRandom;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlg */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzlg {
    private static final ThreadLocal<SecureRandom> zza = new zzlf();

    public static byte[] zza(int i) {
        byte[] bArr = new byte[i];
        zza.get().nextBytes(bArr);
        return bArr;
    }
}
