package com.google.android.gms.internal.p010firebaseauthapi;

import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwd */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzwd implements zzuj<zzwd> {
    private static final String zza = zzwd.class.getSimpleName();
    private String zzb;
    private String zzc;

    public final /* bridge */ /* synthetic */ zzuj zza(String str) throws zzpz {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = jSONObject.optString("idToken", (String) null);
            this.zzc = jSONObject.optString("refreshToken", (String) null);
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzyc.zza(e, zza, str);
        }
    }

    public final String zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzc;
    }
}
