package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.res.Resources;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.C2416R;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
public final class zzfn {
    public static String zza(Context context) {
        try {
            return context.getResources().getResourcePackageName(C2416R.string.common_google_play_services_unknown_issue);
        } catch (Resources.NotFoundException e) {
            return context.getPackageName();
        }
    }

    public static final String zzb(String str, Resources resources, String str2) {
        int identifier = resources.getIdentifier(str, TypedValues.Custom.S_STRING, str2);
        if (identifier == 0) {
            return null;
        }
        try {
            return resources.getString(identifier);
        } catch (Resources.NotFoundException e) {
            return null;
        }
    }
}
