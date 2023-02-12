package com.google.android.libraries.places.internal;

import java.util.Arrays;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzgf<K, V> {
    Object[] zza = new Object[8];
    int zzb = 0;

    public final zzgf<K, V> zza(K k, V v) {
        int i = this.zzb + 1;
        int i2 = i + i;
        Object[] objArr = this.zza;
        int length = objArr.length;
        if (i2 > length) {
            int i3 = length + (length >> 1) + 1;
            if (i3 < i2) {
                int highestOneBit = Integer.highestOneBit(i2 - 1);
                i3 = highestOneBit + highestOneBit;
            }
            if (i3 < 0) {
                i3 = Integer.MAX_VALUE;
            }
            this.zza = Arrays.copyOf(objArr, i3);
        }
        zzft.zza(k, v);
        Object[] objArr2 = this.zza;
        int i4 = this.zzb;
        int i5 = i4 + i4;
        objArr2[i5] = k;
        objArr2[i5 + 1] = v;
        this.zzb = i4 + 1;
        return this;
    }

    public final zzgg<K, V> zzb() {
        return zzgw.zzf(this.zzb, this.zza);
    }
}
