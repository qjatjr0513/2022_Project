package com.google.android.libraries.places.internal;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzio extends zzid {
    private static final Set<zzhi<?>> zza;
    private static final zzhv zzb;
    private final String zzc;
    private final Level zzd;

    static {
        Set<zzhi<?>> unmodifiableSet = Collections.unmodifiableSet(new HashSet(Arrays.asList(new zzhi[]{zzhe.zza, zzhk.zza})));
        zza = unmodifiableSet;
        zzb = zzhy.zza(unmodifiableSet).zzd();
    }

    /* synthetic */ zzio(String str, String str2, boolean z, boolean z2, Level level, zzin zzin) {
        super(str2);
        if (str2.length() > 23) {
            int i = -1;
            int length = str2.length() - 1;
            while (true) {
                if (length < 0) {
                    break;
                }
                char charAt = str2.charAt(length);
                if (charAt == '.' || charAt == '$') {
                    i = length;
                } else {
                    length--;
                }
            }
            i = length;
            str2 = str2.substring(i + 1);
        }
        String valueOf = String.valueOf(str2);
        String concat = valueOf.length() != 0 ? "".concat(valueOf) : new String("");
        this.zzc = concat.substring(0, Math.min(concat.length(), 23));
        this.zzd = level;
    }
}
