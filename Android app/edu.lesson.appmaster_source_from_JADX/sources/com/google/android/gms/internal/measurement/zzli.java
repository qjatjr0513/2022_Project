package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
final class zzli {
    private static final zzlh zza;
    private static final zzlh zzb = new zzlh();

    static {
        zzlh zzlh;
        try {
            zzlh = (zzlh) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            zzlh = null;
        }
        zza = zzlh;
    }

    static zzlh zza() {
        return zza;
    }

    static zzlh zzb() {
        return zzb;
    }
}
