package com.google.android.gms.common.internal;

import android.util.Log;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
public final class GmsLogger {
    private final String zza;
    private final String zzb;

    public GmsLogger(String logTag) {
        this(logTag, (String) null);
    }

    private final String zza(String str) {
        String str2 = this.zzb;
        return str2 == null ? str : str2.concat(str);
    }

    private final String zzb(String str, Object... objArr) {
        String format = String.format(str, objArr);
        String str2 = this.zzb;
        if (str2 == null) {
            return format;
        }
        return str2.concat(format);
    }

    public boolean canLog(int level) {
        return Log.isLoggable(this.zza, level);
    }

    public boolean canLogPii() {
        return false;
    }

    /* renamed from: d */
    public void mo31384d(String tag, String message) {
        if (canLog(3)) {
            Log.d(tag, zza(message));
        }
    }

    /* renamed from: e */
    public void mo31386e(String tag, String message) {
        if (canLog(6)) {
            Log.e(tag, zza(message));
        }
    }

    public void efmt(String tag, String messageFormatString, Object... messageParams) {
        if (canLog(6)) {
            Log.e(tag, zzb(messageFormatString, messageParams));
        }
    }

    /* renamed from: i */
    public void mo31389i(String tag, String message) {
        if (canLog(4)) {
            Log.i(tag, zza(message));
        }
    }

    public void pii(String str, String str2) {
    }

    public void pii(String str, String str2, Throwable th) {
    }

    /* renamed from: v */
    public void mo31393v(String tag, String message) {
        if (canLog(2)) {
            Log.v(tag, zza(message));
        }
    }

    /* renamed from: w */
    public void mo31395w(String tag, String message) {
        if (canLog(5)) {
            Log.w(tag, zza(message));
        }
    }

    public void wfmt(String str, String messageFormatString, Object... messageParams) {
        if (canLog(5)) {
            Log.w(this.zza, zzb(messageFormatString, messageParams));
        }
    }

    public void wtf(String tag, String message, Throwable thr) {
        if (canLog(7)) {
            Log.e(tag, zza(message), thr);
            Log.wtf(tag, zza(message), thr);
        }
    }

    public GmsLogger(String logTag, String messagePrefix) {
        boolean z;
        Preconditions.checkNotNull(logTag, "log tag cannot be null");
        if (logTag.length() <= 23) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "tag \"%s\" is longer than the %d character maximum", logTag, 23);
        this.zza = logTag;
        if (messagePrefix == null || messagePrefix.length() <= 0) {
            this.zzb = null;
        } else {
            this.zzb = messagePrefix;
        }
    }

    /* renamed from: d */
    public void mo31385d(String tag, String message, Throwable thr) {
        if (canLog(3)) {
            Log.d(tag, zza(message), thr);
        }
    }

    /* renamed from: e */
    public void mo31387e(String tag, String message, Throwable thr) {
        if (canLog(6)) {
            Log.e(tag, zza(message), thr);
        }
    }

    /* renamed from: i */
    public void mo31390i(String tag, String message, Throwable thr) {
        if (canLog(4)) {
            Log.i(tag, zza(message), thr);
        }
    }

    /* renamed from: v */
    public void mo31394v(String tag, String message, Throwable thr) {
        if (canLog(2)) {
            Log.v(tag, zza(message), thr);
        }
    }

    /* renamed from: w */
    public void mo31396w(String tag, String message, Throwable thr) {
        if (canLog(5)) {
            Log.w(tag, zza(message), thr);
        }
    }
}
