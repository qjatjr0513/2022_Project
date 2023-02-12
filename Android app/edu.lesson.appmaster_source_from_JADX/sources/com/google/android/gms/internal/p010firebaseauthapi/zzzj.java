package com.google.android.gms.internal.p010firebaseauthapi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzj */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzzj {
    static final zzzj zza = new zzzj(true);
    private static volatile boolean zzb = false;
    private static volatile zzzj zzc;
    private final Map<zzzi, zzzu<?, ?>> zzd;

    zzzj() {
        this.zzd = new HashMap();
    }

    public static zzzj zza() {
        zzzj zzzj = zzc;
        if (zzzj == null) {
            synchronized (zzzj.class) {
                zzzj = zzc;
                if (zzzj == null) {
                    zzzj = zza;
                    zzc = zzzj;
                }
            }
        }
        return zzzj;
    }

    public final <ContainingType extends zzaaz> zzzu<ContainingType, ?> zzb(ContainingType containingtype, int i) {
        return this.zzd.get(new zzzi(containingtype, i));
    }

    zzzj(boolean z) {
        this.zzd = Collections.emptyMap();
    }
}
