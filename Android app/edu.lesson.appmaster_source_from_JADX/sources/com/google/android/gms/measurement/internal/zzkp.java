package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzkp implements zzky {
    final /* synthetic */ zzks zza;

    zzkp(zzks zzks) {
        this.zza = zzks;
    }

    public final void zza(String str, String str2, Bundle bundle) {
        if (!TextUtils.isEmpty(str)) {
            this.zza.zzaz().zzp(new zzko(this, str, "_err", bundle));
        } else if (this.zza.zzn != null) {
            this.zza.zzn.zzay().zzd().zzb("AppId not known when logging event", "_err");
        }
    }
}
