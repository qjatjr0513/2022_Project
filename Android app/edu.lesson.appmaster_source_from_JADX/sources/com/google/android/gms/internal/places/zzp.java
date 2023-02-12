package com.google.android.gms.internal.places;

final class zzp {
    private static final Class<?> zzdw = zzh("libcore.io.Memory");
    private static final boolean zzdx = (zzh("org.robolectric.Robolectric") != null);

    static boolean zzy() {
        return zzdw != null && !zzdx;
    }

    static Class<?> zzz() {
        return zzdw;
    }

    private static <T> Class<T> zzh(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable th) {
            return null;
        }
    }
}
