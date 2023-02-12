package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.common.util.Strings;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxf */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzxf implements zzuj<zzxf> {
    private static final String zza = zzxf.class.getSimpleName();
    private String zzb;

    public final /* bridge */ /* synthetic */ zzuj zza(String str) throws zzpz {
        try {
            this.zzb = Strings.emptyToNull(new JSONObject(str).optString("sessionInfo", (String) null));
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzyc.zza(e, zza, str);
        }
    }

    public final String zzb() {
        return this.zzb;
    }
}
