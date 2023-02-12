package com.google.firebase.auth;

import android.net.Uri;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public class ActionCodeUrl {
    private static final Map<String, Integer> zza = new zzc();
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;

    private ActionCodeUrl(String str) {
        String zzb2 = zzb(str, "apiKey");
        String zzb3 = zzb(str, "oobCode");
        String zzb4 = zzb(str, "mode");
        if (zzb2 == null || zzb3 == null || zzb4 == null) {
            throw new IllegalArgumentException(String.format("%s, %s and %s are required in a valid action code URL", new Object[]{"apiKey", "oobCode", "mode"}));
        }
        this.zzb = Preconditions.checkNotEmpty(zzb2);
        this.zzc = Preconditions.checkNotEmpty(zzb3);
        this.zzd = Preconditions.checkNotEmpty(zzb4);
        this.zze = zzb(str, "continueUrl");
        this.zzf = zzb(str, "languageCode");
        this.zzg = zzb(str, "tenantId");
    }

    public static ActionCodeUrl parseLink(String link) {
        Preconditions.checkNotEmpty(link);
        try {
            return new ActionCodeUrl(link);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static String zzb(String str, String str2) {
        Uri parse = Uri.parse(str);
        try {
            Set<String> queryParameterNames = parse.getQueryParameterNames();
            if (queryParameterNames.contains(str2)) {
                return parse.getQueryParameter(str2);
            }
            if (queryParameterNames.contains("link")) {
                return Uri.parse(Preconditions.checkNotEmpty(parse.getQueryParameter("link"))).getQueryParameter(str2);
            }
            return null;
        } catch (NullPointerException | UnsupportedOperationException e) {
            return null;
        }
    }

    public String getApiKey() {
        return this.zzb;
    }

    public String getCode() {
        return this.zzc;
    }

    public String getContinueUrl() {
        return this.zze;
    }

    public String getLanguageCode() {
        return this.zzf;
    }

    public int getOperation() {
        Map<String, Integer> map = zza;
        if (map.containsKey(this.zzd)) {
            return map.get(this.zzd).intValue();
        }
        return 3;
    }

    public final String zza() {
        return this.zzg;
    }
}
