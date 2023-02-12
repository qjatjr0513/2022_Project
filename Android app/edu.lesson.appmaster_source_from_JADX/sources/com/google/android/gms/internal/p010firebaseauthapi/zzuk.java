package com.google.android.gms.internal.p010firebaseauthapi;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.LibraryVersion;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuk */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzuk {
    private final int zza;

    public zzuk(String str) {
        int i = -1;
        try {
            List<String> zzd = zzae.zzc("[.-]").zzd(str);
            if (zzd.size() == 1) {
                i = Integer.parseInt(str);
            } else if (zzd.size() >= 3) {
                i = (Integer.parseInt(zzd.get(0)) * 1000000) + (Integer.parseInt(zzd.get(1)) * 1000) + Integer.parseInt(zzd.get(2));
            }
        } catch (IllegalArgumentException e) {
            if (Log.isLoggable("LibraryVersionContainer", 3)) {
                Log.d("LibraryVersionContainer", String.format("Version code parsing failed for: %s with exception %s.", new Object[]{str, e}));
            }
        }
        this.zza = i;
    }

    public static zzuk zza() {
        return new zzuk(zzc());
    }

    static String zzc() {
        String version = LibraryVersion.getInstance().getVersion("firebase-auth");
        return (TextUtils.isEmpty(version) || version.equals("UNKNOWN")) ? "-1" : version;
    }

    public final String zzb() {
        return String.format("X%s", new Object[]{Integer.toString(this.zza)});
    }
}
