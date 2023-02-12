package com.google.android.gms.internal.p010firebaseauthapi;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwp */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzwp implements zzuj<zzwp> {
    private static final String zza = zzwp.class.getSimpleName();
    private List<String> zzb;

    public final /* bridge */ /* synthetic */ zzuj zza(String str) throws zzpz {
        zzb(str);
        return this;
    }

    public final zzwp zzb(String str) throws zzpz {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("authorizedDomains");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    this.zzb.add(optJSONArray.getString(i));
                }
            }
            return this;
        } catch (JSONException e) {
            throw zzyc.zza(e, zza, str);
        }
    }

    public final List<String> zzc() {
        return this.zzb;
    }
}
