package com.google.android.libraries.places.internal;

import android.content.Context;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzcs implements zzcv {
    private Context zza;
    private zzcy zzb;
    private zzdf zzc;

    private zzcs() {
    }

    /* synthetic */ zzcs(zzcr zzcr) {
    }

    public final /* bridge */ /* synthetic */ zzcv zza(zzcy zzcy) {
        this.zzb = zzcy;
        return this;
    }

    public final /* bridge */ /* synthetic */ zzcv zzb(zzdf zzdf) {
        this.zzc = zzdf;
        return this;
    }

    public final /* bridge */ /* synthetic */ zzcv zzc(Context context) {
        if (context != null) {
            this.zza = context;
            return this;
        }
        throw null;
    }

    public final zzcw zzd() {
        zzaes.zzb(this.zza, Context.class);
        zzaes.zzb(this.zzb, zzcy.class);
        zzaes.zzb(this.zzc, zzdf.class);
        return new zzcu(this.zza, this.zzb, this.zzc, (zzct) null);
    }
}
