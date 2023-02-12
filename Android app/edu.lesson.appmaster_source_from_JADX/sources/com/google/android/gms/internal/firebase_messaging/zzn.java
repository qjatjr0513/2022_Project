package com.google.android.gms.internal.firebase_messaging;

import java.lang.annotation.Annotation;

/* compiled from: com.google.firebase:firebase-messaging@@23.0.0 */
final class zzn implements zzs {
    private final int zza;
    private final zzr zzb;

    zzn(int i, zzr zzr) {
        this.zza = i;
        this.zzb = zzr;
    }

    public final Class<? extends Annotation> annotationType() {
        return zzs.class;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzs)) {
            return false;
        }
        zzs zzs = (zzs) obj;
        return this.zza == zzs.zza() && this.zzb.equals(zzs.zzb());
    }

    public final int hashCode() {
        return (this.zza ^ 14552422) + (this.zzb.hashCode() ^ 2041407134);
    }

    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf" + "(tag=" + this.zza + "intEncoding=" + this.zzb + ')';
    }

    public final int zza() {
        return this.zza;
    }

    public final zzr zzb() {
        return this.zzb;
    }
}
