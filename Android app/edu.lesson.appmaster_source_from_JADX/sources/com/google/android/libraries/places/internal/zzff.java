package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzff {
    public static final <A extends Appendable> A zza(A a, Iterator<? extends Map.Entry> it, zzfh zzfh, String str) throws IOException {
        if (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            a.append(zzfh.zzf(entry.getKey()));
            a.append("=");
            a.append(zzfh.zzf(entry.getValue()));
            while (it.hasNext()) {
                a.append(zzfh.zza);
                Map.Entry entry2 = (Map.Entry) it.next();
                a.append(zzfh.zzf(entry2.getKey()));
                a.append("=");
                a.append(zzfh.zzf(entry2.getValue()));
            }
        }
        return a;
    }
}
