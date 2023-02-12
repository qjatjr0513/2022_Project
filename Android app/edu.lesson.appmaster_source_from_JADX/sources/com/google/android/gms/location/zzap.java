package com.google.android.gms.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
abstract class zzap implements RemoteCall<zzaz, TaskCompletionSource<Boolean>> {
    private boolean zza = true;

    protected zzap() {
    }

    /* access modifiers changed from: protected */
    public final boolean zza() {
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(boolean z) {
        this.zza = false;
    }
}
