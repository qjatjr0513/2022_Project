package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzi */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzzi {
    private final Object zza;
    private final int zzb;

    zzzi(Object obj, int i) {
        this.zza = obj;
        this.zzb = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzzi)) {
            return false;
        }
        zzzi zzzi = (zzzi) obj;
        if (this.zza == zzzi.zza && this.zzb == zzzi.zzb) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zza) * 65535) + this.zzb;
    }
}
