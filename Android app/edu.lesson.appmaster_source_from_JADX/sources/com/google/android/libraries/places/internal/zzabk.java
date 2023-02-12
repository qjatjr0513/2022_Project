package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzabk {
    private static final zzabi<?> zza = new zzabj();
    private static final zzabi<?> zzb;

    static {
        zzabi<?> zzabi;
        try {
            zzabi = (zzabi) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            zzabi = null;
        }
        zzb = zzabi;
    }

    static zzabi<?> zza() {
        zzabi<?> zzabi = zzb;
        if (zzabi != null) {
            return zzabi;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    static zzabi<?> zzb() {
        return zza;
    }
}
