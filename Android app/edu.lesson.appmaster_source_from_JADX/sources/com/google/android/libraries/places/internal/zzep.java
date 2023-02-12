package com.google.android.libraries.places.internal;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzep implements ViewModelProvider.Factory {
    private final zzee zza;
    private final zzeu zzb;
    private final zzev zzc;

    public zzep(zzee zzee, zzeu zzeu, zzev zzev) {
        this.zza = zzee;
        this.zzb = zzeu;
        this.zzc = zzev;
    }

    public final <T extends ViewModel> T create(Class<T> cls) {
        boolean z;
        if (cls == zzer.class) {
            z = true;
        } else {
            z = false;
        }
        zzfm.zze(z, "This factory can only be used to instantiate its enclosing class.");
        return new zzer(this.zza, this.zzb, this.zzc, (zzeq) null);
    }
}
