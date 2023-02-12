package com.google.android.libraries.places.internal;

import android.content.Context;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzdl {
    private final Context zza;

    public zzdl(Context context) {
        zzfm.zzc(context, "Context must not be null.");
        this.zza = context;
    }

    public final zzgg<String, String> zza() {
        String packageName = this.zza.getPackageName();
        String zza2 = zzda.zza(this.zza.getPackageManager(), packageName);
        zzgf zzgf = new zzgf();
        if (packageName != null) {
            zzgf.zza("X-Android-Package", packageName);
        }
        if (zza2 != null) {
            zzgf.zza("X-Android-Cert", zza2);
        }
        return zzgf.zzb();
    }
}
