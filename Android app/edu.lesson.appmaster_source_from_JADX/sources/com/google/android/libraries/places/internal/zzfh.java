package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public class zzfh {
    /* access modifiers changed from: private */
    public final String zza;

    /* synthetic */ zzfh(zzfh zzfh, zzfg zzfg) {
        this.zza = zzfh.zza;
    }

    private zzfh(String str) {
        this.zza = str;
    }

    public static zzfh zzb(String str) {
        return new zzfh(str);
    }

    static final CharSequence zzf(@CheckForNull Object obj) {
        obj.getClass();
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }

    public <A extends Appendable> A zza(A a, Iterator it) throws IOException {
        if (it.hasNext()) {
            a.append(zzf(it.next()));
            while (it.hasNext()) {
                a.append(this.zza);
                a.append(zzf(it.next()));
            }
        }
        return a;
    }

    public final zzfh zzc() {
        return new zzfe(this, this);
    }

    public final String zze(Iterable<? extends Object> iterable) {
        Iterator<? extends Object> it = iterable.iterator();
        StringBuilder sb = new StringBuilder();
        try {
            zza(sb, it);
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
