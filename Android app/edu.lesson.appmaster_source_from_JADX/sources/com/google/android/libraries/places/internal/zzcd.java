package com.google.android.libraries.places.internal;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzcd {
    private final String zza;
    private final String zzb;
    private Locale zzc = null;
    private Map<String, String> zzd = new HashMap();

    zzcd(String str, String str2) {
        this.zza = str;
        zzfm.zze(!TextUtils.isEmpty(str2), "API key cannot be empty.");
        this.zzb = str2;
    }

    /* access modifiers changed from: package-private */
    public final zzcd zza(Locale locale) {
        this.zzc = locale;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final zzcd zzb(Map<String, String> map) {
        this.zzd = new HashMap(map);
        return this;
    }

    public final String zzc() {
        String str;
        Uri.Builder buildUpon = Uri.parse("https://maps.googleapis.com/").buildUpon();
        buildUpon.appendEncodedPath("maps/api/place/");
        buildUpon.appendEncodedPath(this.zza);
        buildUpon.appendQueryParameter("key", this.zzb);
        Locale locale = this.zzc;
        if (locale != null) {
            if (Build.VERSION.SDK_INT < 21) {
                StringBuilder sb = new StringBuilder();
                String language = locale.getLanguage();
                if (!TextUtils.isEmpty(language)) {
                    sb.append(language);
                    String country = locale.getCountry();
                    if (!TextUtils.isEmpty(country)) {
                        sb.append("-");
                        sb.append(country);
                    }
                }
                str = sb.toString();
            } else {
                str = locale.toLanguageTag();
            }
            if (!TextUtils.isEmpty(str)) {
                buildUpon.appendQueryParameter("language", str);
            }
        }
        Map<String, String> map = this.zzd;
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                buildUpon.appendQueryParameter((String) next.getKey(), (String) next.getValue());
            }
        }
        return buildUpon.build().toString();
    }
}
