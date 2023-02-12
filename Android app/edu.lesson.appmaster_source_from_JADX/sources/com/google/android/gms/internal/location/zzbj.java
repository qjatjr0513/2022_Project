package com.google.android.gms.internal.location;

import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
public final class zzbj {
    public static Looper zza(Looper looper) {
        return looper != null ? looper : zzb();
    }

    public static Looper zzb() {
        boolean z;
        if (Looper.myLooper() != null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "Can't create handler inside thread that has not called Looper.prepare()");
        return Looper.myLooper();
    }
}
