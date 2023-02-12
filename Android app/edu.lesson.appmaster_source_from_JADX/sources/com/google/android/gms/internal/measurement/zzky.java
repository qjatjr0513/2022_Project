package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
final class zzky {
    private static final zzkx zza;
    private static final zzkx zzb = new zzkx();

    static {
        zzkx zzkx;
        try {
            zzkx = (zzkx) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            zzkx = null;
        }
        zza = zzkx;
    }

    static zzkx zza() {
        return zza;
    }

    static zzkx zzb() {
        return zzb;
    }
}
