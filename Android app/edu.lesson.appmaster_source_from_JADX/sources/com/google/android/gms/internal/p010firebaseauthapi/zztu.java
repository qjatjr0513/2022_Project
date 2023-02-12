package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztu */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public class zztu {
    final String zza;
    final zzum zzb;

    public zztu(String str, zzum zzum) {
        this.zza = str;
        this.zzb = zzum;
    }

    /* access modifiers changed from: package-private */
    public final String zza(String str, String str2) {
        String str3 = this.zza;
        int length = String.valueOf(str3).length();
        StringBuilder sb = new StringBuilder(length + 5 + str.length() + String.valueOf(str2).length());
        sb.append(str3);
        sb.append(str);
        sb.append("?key=");
        sb.append(str2);
        return sb.toString();
    }
}
