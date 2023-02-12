package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzkl implements zzen {
    final /* synthetic */ String zza;
    final /* synthetic */ zzks zzb;

    zzkl(zzks zzks, String str) {
        this.zzb = zzks;
        this.zza = str;
    }

    public final void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        this.zzb.zzJ(i, th, bArr, this.zza);
    }
}
