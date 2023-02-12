package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzm */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzzm {
    private static final zzzk<?> zza = new zzzl();
    private static final zzzk<?> zzb;

    static {
        zzzk<?> zzzk;
        try {
            zzzk = (zzzk) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            zzzk = null;
        }
        zzb = zzzk;
    }

    static zzzk<?> zza() {
        zzzk<?> zzzk = zzb;
        if (zzzk != null) {
            return zzzk;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    static zzzk<?> zzb() {
        return zza;
    }
}
