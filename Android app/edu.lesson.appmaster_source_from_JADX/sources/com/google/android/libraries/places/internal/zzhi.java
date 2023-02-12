package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public class zzhi<T> {
    private final String zza;
    private final Class<? extends T> zzb;
    private final boolean zzc;

    protected zzhi(String str, Class<? extends T> cls, boolean z) {
        zzje.zzb(str);
        this.zza = str;
        this.zzb = cls;
        this.zzc = z;
        System.identityHashCode(this);
        for (int i = 0; i < 5; i++) {
        }
    }

    public static <T> zzhi<T> zza(String str, Class<? extends T> cls) {
        return new zzhi<>(str, cls, false);
    }

    public final String toString() {
        String name = getClass().getName();
        String str = this.zza;
        String name2 = this.zzb.getName();
        int length = String.valueOf(name).length();
        StringBuilder sb = new StringBuilder(length + 3 + str.length() + String.valueOf(name2).length());
        sb.append(name);
        sb.append("/");
        sb.append(str);
        sb.append("[");
        sb.append(name2);
        sb.append("]");
        return sb.toString();
    }

    public final boolean zzb() {
        return this.zzc;
    }
}
