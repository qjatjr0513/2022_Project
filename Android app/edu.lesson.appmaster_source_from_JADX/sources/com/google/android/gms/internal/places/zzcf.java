package com.google.android.gms.internal.places;

final class zzcf {
    private static final zzcd zzko = zzci();
    private static final zzcd zzkp = new zzcg();

    static zzcd zzcg() {
        return zzko;
    }

    static zzcd zzch() {
        return zzkp;
    }

    private static zzcd zzci() {
        try {
            return (zzcd) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }
}
