package com.google.android.libraries.places.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzjd {
    private static final String[] zza;
    private static final zzjh zzb;

    static {
        zzjh zzji;
        String[] strArr = {"com.google.common.flogger.util.StackWalkerStackGetter", "com.google.common.flogger.util.JavaLangAccessStackGetter"};
        zza = strArr;
        int i = 0;
        while (true) {
            if (i >= 2) {
                zzji = new zzji();
                break;
            }
            try {
                zzji = (zzjh) Class.forName(strArr[i]).asSubclass(zzjh.class).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Throwable th) {
                zzji = null;
            }
            if (zzji != null) {
                break;
            }
            i++;
        }
        zzb = zzji;
    }

    @NullableDecl
    public static StackTraceElement zza(Class<?> cls, int i) {
        zzje.zza(cls, TypedValues.AttributesType.S_TARGET);
        return zzb.zza(cls, 2);
    }
}
