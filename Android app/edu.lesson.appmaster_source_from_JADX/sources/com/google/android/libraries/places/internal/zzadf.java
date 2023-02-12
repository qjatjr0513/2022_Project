package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzadf {
    private static final zzade zza;
    private static final zzade zzb = new zzade();

    static {
        zzade zzade;
        try {
            zzade = (zzade) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            zzade = null;
        }
        zza = zzade;
    }

    static zzade zza() {
        return zza;
    }

    static zzade zzb() {
        return zzb;
    }
}
