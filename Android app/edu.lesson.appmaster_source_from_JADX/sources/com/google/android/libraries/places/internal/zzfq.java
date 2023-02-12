package com.google.android.libraries.places.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzfq {
    /* access modifiers changed from: private */
    public final zzfc zza;
    private final zzfo zzb;

    private zzfq(zzfo zzfo, byte[] bArr) {
        zzfb zzfb = zzfb.zza;
        this.zzb = zzfo;
        this.zza = zzfb;
    }

    public static zzfq zzb(char c) {
        return new zzfq(new zzfo(new zzez('.')), (byte[]) null);
    }

    public final List<String> zzc(CharSequence charSequence) {
        zzfn zzfn = new zzfn(this.zzb, this, "2.5.0");
        ArrayList arrayList = new ArrayList();
        while (zzfn.hasNext()) {
            arrayList.add((String) zzfn.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
