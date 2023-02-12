package com.google.android.gms.internal.p010firebaseauthapi;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.common.net.HttpHeaders;
import java.net.URLConnection;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzum */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzum {
    private final Context zza;
    private zzvc zzb;
    private final String zzc;
    private boolean zzd = false;
    private String zze;

    public zzum(Context context, String str) {
        Preconditions.checkNotNull(context);
        this.zza = context.getApplicationContext();
        this.zzc = String.format("Android/%s/%s", new Object[]{"Fallback", str});
    }

    public final void zza(URLConnection uRLConnection) {
        String str;
        if (this.zzd) {
            String str2 = this.zzc;
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 19);
            sb.append(str2);
            sb.append("/FirebaseUI-Android");
            str = sb.toString();
        } else {
            String str3 = this.zzc;
            StringBuilder sb2 = new StringBuilder(String.valueOf(str3).length() + 21);
            sb2.append(str3);
            sb2.append("/FirebaseCore-Android");
            str = sb2.toString();
        }
        if (this.zzb == null) {
            Context context = this.zza;
            this.zzb = new zzvc(context, context.getPackageName());
        }
        uRLConnection.setRequestProperty("X-Android-Package", this.zzb.zzb());
        uRLConnection.setRequestProperty("X-Android-Cert", this.zzb.zza());
        uRLConnection.setRequestProperty(HttpHeaders.ACCEPT_LANGUAGE, zzun.zza());
        uRLConnection.setRequestProperty("X-Client-Version", str);
        uRLConnection.setRequestProperty("X-Firebase-Locale", this.zze);
        this.zze = null;
    }

    public final void zzb(String str) {
        this.zzd = !TextUtils.isEmpty(str);
    }

    public final void zzc(String str) {
        this.zze = str;
    }
}
