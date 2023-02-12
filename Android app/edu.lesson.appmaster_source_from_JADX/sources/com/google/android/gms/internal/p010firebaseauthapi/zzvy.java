package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.firebase.auth.ActionCodeUrl;
import com.google.firebase.auth.EmailAuthCredential;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvy */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzvy implements zzui {
    private static final String zza;
    private static final Logger zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;

    static {
        String simpleName = zzvy.class.getSimpleName();
        zza = simpleName;
        zzb = new Logger(simpleName, new String[0]);
    }

    public zzvy(EmailAuthCredential emailAuthCredential, String str) {
        this.zzc = Preconditions.checkNotEmpty(emailAuthCredential.zzd());
        this.zzd = Preconditions.checkNotEmpty(emailAuthCredential.zzf());
        this.zze = str;
    }

    public final String zza() throws JSONException {
        String str;
        ActionCodeUrl parseLink = ActionCodeUrl.parseLink(this.zzd);
        String str2 = null;
        if (parseLink != null) {
            str = parseLink.getCode();
        } else {
            str = null;
        }
        if (parseLink != null) {
            str2 = parseLink.zza();
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("email", this.zzc);
        if (str != null) {
            jSONObject.put("oobCode", str);
        }
        if (str2 != null) {
            jSONObject.put("tenantId", str2);
        }
        String str3 = this.zze;
        if (str3 != null) {
            jSONObject.put("idToken", str3);
        }
        return jSONObject.toString();
    }
}
