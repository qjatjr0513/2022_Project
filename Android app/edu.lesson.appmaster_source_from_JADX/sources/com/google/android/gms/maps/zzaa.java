package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzc;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
final class zzaa extends zzc {
    private final GoogleMap.CancelableCallback zza;

    zzaa(GoogleMap.CancelableCallback cancelableCallback) {
        this.zza = cancelableCallback;
    }

    public final void zzb() {
        this.zza.onCancel();
    }

    public final void zzc() {
        this.zza.onFinish();
    }
}
