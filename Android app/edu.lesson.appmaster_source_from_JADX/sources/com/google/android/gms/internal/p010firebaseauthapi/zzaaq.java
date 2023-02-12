package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaaq */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzaaq implements zzaax {
    private final zzaax[] zza;

    zzaaq(zzaax... zzaaxArr) {
        this.zza = zzaaxArr;
    }

    public final zzaaw zzb(Class<?> cls) {
        zzaax[] zzaaxArr = this.zza;
        for (int i = 0; i < 2; i++) {
            zzaax zzaax = zzaaxArr[i];
            if (zzaax.zzc(cls)) {
                return zzaax.zzb(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }

    public final boolean zzc(Class<?> cls) {
        zzaax[] zzaaxArr = this.zza;
        for (int i = 0; i < 2; i++) {
            if (zzaaxArr[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
