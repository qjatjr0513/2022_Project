package com.google.android.libraries.places.internal;

import android.graphics.Bitmap;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzaz {
    private Bitmap zza;

    public final zzbb zza() {
        boolean z;
        if (this.zza != null) {
            z = true;
        } else {
            z = false;
        }
        zzfm.zzi(z, "Photo must be set to non-null value.");
        return new zzbb(this.zza, (zzba) null);
    }

    public final zzaz zzb(Bitmap bitmap) {
        this.zza = bitmap;
        return this;
    }
}
