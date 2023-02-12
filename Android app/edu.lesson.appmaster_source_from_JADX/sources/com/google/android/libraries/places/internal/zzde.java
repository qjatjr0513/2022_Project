package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class zzde {
    /* access modifiers changed from: package-private */
    public abstract zzde zzb(int i);

    /* access modifiers changed from: package-private */
    public abstract zzdf zzc();

    public abstract zzde zzd(int i);

    public final zzdf zze() {
        zzdf zzc = zzc();
        zzfm.zzi(!zzc.zzb().isEmpty(), "Package name must not be empty.");
        return zzc;
    }
}
