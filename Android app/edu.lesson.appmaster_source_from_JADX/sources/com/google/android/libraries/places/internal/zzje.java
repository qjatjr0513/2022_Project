package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzje {
    public static <T> T zza(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str.concat(" must not be null"));
    }

    public static String zzb(String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("identifier must not be empty");
        } else if (!zzc(str.charAt(0))) {
            throw new IllegalArgumentException(str.length() != 0 ? "identifier must start with an ASCII letter: ".concat(str) : new String("identifier must start with an ASCII letter: "));
        } else {
            int i = 1;
            while (i < str.length()) {
                char charAt = str.charAt(i);
                if (zzc(charAt) || ((charAt >= '0' && charAt <= '9') || charAt == '_')) {
                    i++;
                } else {
                    throw new IllegalArgumentException(str.length() != 0 ? "identifier must contain only ASCII letters, digits or underscore: ".concat(str) : new String("identifier must contain only ASCII letters, digits or underscore: "));
                }
            }
            return str;
        }
    }

    private static boolean zzc(char c) {
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        if (c >= 'A') {
            return c <= 'Z';
        }
        return false;
    }
}
