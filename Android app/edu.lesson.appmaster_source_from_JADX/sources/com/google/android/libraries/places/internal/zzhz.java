package com.google.android.libraries.places.internal;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzhz {
    /* access modifiers changed from: private */
    public static final zzib zza = zzb(zzib.zzd);

    private static zzib zzb(String[] strArr) {
        zzib zzib;
        try {
            zzib = zzic.zza();
        } catch (NoClassDefFoundError e) {
            zzib = null;
        }
        if (zzib != null) {
            return zzib;
        }
        StringBuilder sb = new StringBuilder();
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            String str = strArr[i];
            try {
                return (zzib) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Throwable th) {
                th = th;
                if (th instanceof InvocationTargetException) {
                    th = th.getCause();
                }
                sb.append(10);
                sb.append(str);
                sb.append(": ");
                sb.append(th);
                i++;
            }
        }
        throw new IllegalStateException(sb.insert(0, "No logging platforms found:").toString());
    }
}
