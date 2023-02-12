package com.google.firebase.auth.internal;

import com.google.firebase.auth.FirebaseAuthSettings;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzw extends FirebaseAuthSettings {
    private String zza;
    private String zzb;
    private boolean zzc = false;
    private boolean zzd = false;

    public final void forceRecaptchaFlowForTesting(boolean z) {
        this.zzd = z;
    }

    public final void setAppVerificationDisabledForTesting(boolean z) {
        this.zzc = z;
    }

    public final void setAutoRetrievedSmsCodeForPhoneNumber(String str, String str2) {
        this.zza = str;
        this.zzb = str2;
    }

    public final String zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzb;
    }

    public final boolean zzc() {
        return this.zzd;
    }

    public final boolean zzd() {
        return (this.zza == null || this.zzb == null) ? false : true;
    }

    public final boolean zze() {
        return this.zzc;
    }
}
