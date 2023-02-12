package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzgu extends ContentObserver {
    zzgu(Handler handler) {
        super((Handler) null);
    }

    public final void onChange(boolean z) {
        zzgv.zzk.set(true);
    }
}
