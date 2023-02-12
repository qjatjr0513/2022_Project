package com.google.android.libraries.places.internal;

import android.content.Context;
import android.content.pm.PackageManager;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class zzdf {
    public static zzde zzd(Context context) {
        String packageName = context.getPackageName();
        int i = 0;
        try {
            i = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
        }
        zzdb zzdb = new zzdb();
        zzdb.zza(packageName);
        zzdb.zzb(i);
        zzdb.zzd(1);
        return zzdb;
    }

    public abstract int zza();

    public abstract String zzb();

    public abstract int zzc();
}
