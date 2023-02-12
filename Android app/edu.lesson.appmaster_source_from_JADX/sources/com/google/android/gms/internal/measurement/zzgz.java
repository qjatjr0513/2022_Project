package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzgz extends ContentObserver {
    final /* synthetic */ zzha zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzgz(zzha zzha, Handler handler) {
        super((Handler) null);
        this.zza = zzha;
    }

    public final void onChange(boolean z) {
        this.zza.zzf();
    }
}
