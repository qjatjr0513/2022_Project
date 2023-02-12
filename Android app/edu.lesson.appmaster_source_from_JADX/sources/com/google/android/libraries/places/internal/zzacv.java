package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzacv {
    private static final zzacu zza;
    private static final zzacu zzb = new zzacu();

    static {
        zzacu zzacu;
        try {
            zzacu = (zzacu) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            zzacu = null;
        }
        zza = zzacu;
    }

    static zzacu zza() {
        return zza;
    }

    static zzacu zzb() {
        return zzb;
    }
}
