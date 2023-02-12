package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
final class zzjm {
    private static final zzjk<?> zza = new zzjl();
    private static final zzjk<?> zzb;

    static {
        zzjk<?> zzjk;
        try {
            zzjk = (zzjk) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            zzjk = null;
        }
        zzb = zzjk;
    }

    static zzjk<?> zza() {
        zzjk<?> zzjk = zzb;
        if (zzjk != null) {
            return zzjk;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    static zzjk<?> zzb() {
        return zza;
    }
}
