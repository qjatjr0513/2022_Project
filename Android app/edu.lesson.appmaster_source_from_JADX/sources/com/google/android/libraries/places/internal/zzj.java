package com.google.android.libraries.places.internal;

import java.util.Arrays;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzj {
    private final String zza;

    private zzj(String str) {
        this.zza = str;
    }

    public static zzj zza(zzj zzj, zzj... zzjArr) {
        String valueOf = String.valueOf(zzj.zza);
        String valueOf2 = String.valueOf(zzfh.zzb("").zze(zzgm.zza(Arrays.asList(zzjArr), zzi.zza)));
        return new zzj(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    public static zzj zzb(String str) {
        return new zzj(str);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzj) {
            return this.zza.equals(((zzj) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        return this.zza;
    }
}
