package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
final class zzy extends zzah {
    private final BaseImplementation.ResultHolder<Status> zza;

    public zzy(BaseImplementation.ResultHolder<Status> resultHolder) {
        this.zza = resultHolder;
    }

    public final void zzb(zzaa zzaa) {
        this.zza.setResult(zzaa.getStatus());
    }

    public final void zzc() {
    }
}
