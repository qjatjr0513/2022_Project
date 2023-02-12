package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzaj;
import com.google.android.gms.maps.internal.zzj;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
final class zzs extends zzj {
    final /* synthetic */ LocationSource zza;

    zzs(GoogleMap googleMap, LocationSource locationSource) {
        this.zza = locationSource;
    }

    public final void activate(zzaj zzaj) {
        this.zza.activate(new zzl(this, zzaj));
    }

    public final void deactivate() {
        this.zza.deactivate();
    }
}
