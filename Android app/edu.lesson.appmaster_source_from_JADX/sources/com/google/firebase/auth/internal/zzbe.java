package com.google.firebase.auth.internal;

import android.text.TextUtils;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzbe {
    private boolean zza;
    private String zzb;

    private zzbe() {
    }

    public static zzbe zza(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Map<String, Object> zzb2 = zzaz.zzb(str);
        try {
            zzbe zzbe = new zzbe();
            Object obj = zzb2.get("basicIntegrity");
            boolean z = false;
            if (obj != null && ((Boolean) obj).booleanValue()) {
                z = true;
            }
            zzbe.zza = z;
            String str2 = (String) zzb2.get("advice");
            if (str2 == null) {
                str2 = "";
            }
            zzbe.zzb = str2;
            return zzbe;
        } catch (ClassCastException e) {
            return null;
        }
    }

    public final String zzb() {
        return this.zzb;
    }

    public final boolean zzc() {
        return this.zza;
    }
}
