package com.google.android.gms.internal.p010firebaseauthapi;

import java.util.NoSuchElementException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyl */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzyl extends zzyo {
    final /* synthetic */ zzyu zza;
    private int zzb = 0;
    private final int zzc;

    zzyl(zzyu zzyu) {
        this.zza = zzyu;
        this.zzc = zzyu.zzd();
    }

    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    public final byte zza() {
        int i = this.zzb;
        if (i < this.zzc) {
            this.zzb = i + 1;
            return this.zza.zzb(i);
        }
        throw new NoSuchElementException();
    }
}
