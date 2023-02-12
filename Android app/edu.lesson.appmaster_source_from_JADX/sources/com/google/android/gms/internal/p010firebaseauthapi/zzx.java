package com.google.android.gms.internal.p010firebaseauthapi;

import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzx */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzx {
    private static final Logger zza = Logger.getLogger(zzx.class.getName());
    private static final zzw zzb = new zzw((zzv) null);

    private zzx() {
    }

    static zzs zza(String str) {
        return new zzu(Pattern.compile("[.-]"));
    }

    static String zzb(@NullableDecl String str) {
        return str == null ? "" : str;
    }

    static boolean zzc(@NullableDecl String str) {
        return str == null || str.isEmpty();
    }
}
