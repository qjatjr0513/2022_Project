package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.measurement.internal.zzgv;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.0.0 */
final class zzdu extends zzch {
    private final zzgv zza;

    zzdu(zzgv zzgv) {
        this.zza = zzgv;
    }

    public final int zzd() {
        return System.identityHashCode(this.zza);
    }

    public final void zze(String str, String str2, Bundle bundle, long j) {
        this.zza.interceptEvent(str, str2, bundle, j);
    }
}
