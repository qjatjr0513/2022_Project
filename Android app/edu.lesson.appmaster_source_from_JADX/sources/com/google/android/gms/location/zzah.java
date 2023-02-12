package com.google.android.gms.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
final /* synthetic */ class zzah implements RemoteCall {
    private final boolean zza;

    zzah(boolean z) {
        this.zza = z;
    }

    public final void accept(Object obj, Object obj2) {
        boolean z = this.zza;
        String str = FusedLocationProviderClient.KEY_MOCK_LOCATION;
        ((zzaz) obj).zzI(z);
        ((TaskCompletionSource) obj2).setResult(null);
    }
}
