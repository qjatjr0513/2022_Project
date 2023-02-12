package com.google.android.gms.common.logging;

import android.util.Log;
import com.google.android.gms.common.internal.GmsLogger;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
public class Logger {
    private final String zza;
    private final String zzb;
    private final GmsLogger zzc;
    private final int zzd;

    public Logger(String tag, String... categories) {
        String str;
        if (r0 == 0) {
            str = "";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (String str2 : categories) {
                if (sb.length() > 1) {
                    sb.append(",");
                }
                sb.append(str2);
            }
            sb.append("] ");
            str = sb.toString();
        }
        this.zzb = str;
        this.zza = tag;
        this.zzc = new GmsLogger(tag);
        int i = 2;
        while (i <= 7 && !Log.isLoggable(this.zza, i)) {
            i++;
        }
        this.zzd = i;
    }

    /* renamed from: d */
    public void mo31544d(String msg, Object... optionalFormatArgs) {
        if (isLoggable(3)) {
            Log.d(this.zza, format(msg, optionalFormatArgs));
        }
    }

    /* renamed from: e */
    public void mo31545e(String msg, Throwable tr, Object... optionalFormatArgs) {
        Log.e(this.zza, format(msg, optionalFormatArgs), tr);
    }

    /* access modifiers changed from: protected */
    public String format(String message, Object... optionalFormatArgs) {
        if (optionalFormatArgs != null && optionalFormatArgs.length > 0) {
            message = String.format(Locale.US, message, optionalFormatArgs);
        }
        return this.zzb.concat(message);
    }

    public String getTag() {
        return this.zza;
    }

    /* renamed from: i */
    public void mo31549i(String msg, Object... optionalFormatArgs) {
        Log.i(this.zza, format(msg, optionalFormatArgs));
    }

    public boolean isLoggable(int i) {
        return this.zzd <= i;
    }

    /* renamed from: v */
    public void mo31551v(String msg, Throwable tr, Object... optionalFormatArgs) {
        if (isLoggable(2)) {
            Log.v(this.zza, format(msg, optionalFormatArgs), tr);
        }
    }

    /* renamed from: w */
    public void mo31553w(String msg, Object... optionalFormatArgs) {
        Log.w(this.zza, format(msg, optionalFormatArgs));
    }

    public void wtf(String msg, Throwable tr, Object... optionalFormatArgs) {
        Log.wtf(this.zza, format(msg, optionalFormatArgs), tr);
    }

    /* renamed from: e */
    public void mo31546e(String msg, Object... optionalFormatArgs) {
        Log.e(this.zza, format(msg, optionalFormatArgs));
    }

    public void wtf(Throwable tr) {
        Log.wtf(this.zza, tr);
    }

    /* renamed from: v */
    public void mo31552v(String msg, Object... optionalFormatArgs) {
        if (isLoggable(2)) {
            Log.v(this.zza, format(msg, optionalFormatArgs));
        }
    }
}
