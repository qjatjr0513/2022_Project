package com.google.android.gms.internal.p010firebaseauthapi;

import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbd */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzbd<P> {
    private final P zza;
    private final byte[] zzb;
    private final zzig zzc;
    private final zzjk zzd;

    zzbd(P p, byte[] bArr, zzig zzig, zzjk zzjk, int i) {
        this.zza = p;
        this.zzb = Arrays.copyOf(bArr, bArr.length);
        this.zzc = zzig;
        this.zzd = zzjk;
    }

    public final zzig zza() {
        return this.zzc;
    }

    public final zzjk zzb() {
        return this.zzd;
    }

    public final P zzc() {
        return this.zza;
    }

    public final byte[] zzd() {
        byte[] bArr = this.zzb;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }
}
