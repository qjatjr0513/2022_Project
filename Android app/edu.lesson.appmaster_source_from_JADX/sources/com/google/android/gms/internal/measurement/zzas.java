package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzas implements Iterator<zzap> {
    final /* synthetic */ zzat zza;
    private int zzb = 0;

    zzas(zzat zzat) {
        this.zza = zzat;
    }

    public final boolean hasNext() {
        return this.zzb < this.zza.zza.length();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        if (this.zzb < this.zza.zza.length()) {
            String zzb2 = this.zza.zza;
            int i = this.zzb;
            this.zzb = i + 1;
            return new zzat(String.valueOf(zzb2.charAt(i)));
        }
        throw new NoSuchElementException();
    }
}
