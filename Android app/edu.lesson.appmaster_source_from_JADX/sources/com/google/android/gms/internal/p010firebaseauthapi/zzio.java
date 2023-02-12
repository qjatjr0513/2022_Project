package com.google.android.gms.internal.p010firebaseauthapi;

import java.util.Collections;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzio */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzio extends zzzs<zzir, zzio> implements zzaba {
    private zzio() {
        super(zzir.zzb);
    }

    public final int zza() {
        return ((zzir) this.zza).zza();
    }

    public final zzio zzb(zziq zziq) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzir.zzi((zzir) this.zza, zziq);
        return this;
    }

    public final zzio zzc(int i) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        ((zzir) this.zza).zze = i;
        return this;
    }

    public final zziq zzd(int i) {
        return ((zzir) this.zza).zzd(i);
    }

    public final List<zziq> zze() {
        return Collections.unmodifiableList(((zzir) this.zza).zzg());
    }

    /* synthetic */ zzio(zzin zzin) {
        super(zzir.zzb);
    }
}
