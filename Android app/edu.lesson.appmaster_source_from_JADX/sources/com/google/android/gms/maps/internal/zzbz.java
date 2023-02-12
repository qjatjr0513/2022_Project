package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public final class zzbz {
    private zzbz() {
    }

    public static <T extends Parcelable> T zza(Bundle bundle, String str) {
        ClassLoader zzd = zzd();
        bundle.setClassLoader(zzd);
        Bundle bundle2 = bundle.getBundle("map_state");
        if (bundle2 == null) {
            return null;
        }
        bundle2.setClassLoader(zzd);
        return bundle2.getParcelable(str);
    }

    public static void zzb(Bundle bundle, Bundle bundle2) {
        if (bundle != null && bundle2 != null) {
            Parcelable zza = zza(bundle, "MapOptions");
            if (zza != null) {
                zzc(bundle2, "MapOptions", zza);
            }
            Parcelable zza2 = zza(bundle, "StreetViewPanoramaOptions");
            if (zza2 != null) {
                zzc(bundle2, "StreetViewPanoramaOptions", zza2);
            }
            Parcelable zza3 = zza(bundle, "camera");
            if (zza3 != null) {
                zzc(bundle2, "camera", zza3);
            }
            if (bundle.containsKey("position")) {
                bundle2.putString("position", bundle.getString("position"));
            }
            if (bundle.containsKey("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT")) {
                bundle2.putBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", bundle.getBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", false));
            }
        }
    }

    public static void zzc(Bundle bundle, String str, Parcelable parcelable) {
        ClassLoader zzd = zzd();
        bundle.setClassLoader(zzd);
        Bundle bundle2 = bundle.getBundle("map_state");
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        bundle2.setClassLoader(zzd);
        bundle2.putParcelable(str, parcelable);
        bundle.putBundle("map_state", bundle2);
    }

    private static ClassLoader zzd() {
        return (ClassLoader) Preconditions.checkNotNull(zzbz.class.getClassLoader());
    }
}
