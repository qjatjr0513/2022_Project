package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzar */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzar {
    public static zzaq zza(String str) throws GeneralSecurityException {
        if (zzbn.zzk().containsKey(str)) {
            return zzbn.zzk().get(str);
        }
        throw new GeneralSecurityException(str.length() != 0 ? "cannot find key template: ".concat(str) : new String("cannot find key template: "));
    }
}
