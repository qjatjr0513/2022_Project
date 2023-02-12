package com.google.android.gms.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
final /* synthetic */ class zzas implements RemoteCall {
    private final List zza;

    zzas(List list) {
        this.zza = list;
    }

    public final void accept(Object obj, Object obj2) {
        List list = this.zza;
        int i = GeofencingClient.zza;
        ((zzaz) obj).zzy(list, new zzat((TaskCompletionSource) obj2));
    }
}
