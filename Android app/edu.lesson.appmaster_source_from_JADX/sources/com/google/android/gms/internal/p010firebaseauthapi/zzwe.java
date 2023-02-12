package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwe */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public enum zzwe {
    REFRESH_TOKEN("refresh_token"),
    AUTHORIZATION_CODE("authorization_code");
    
    private final String zzd;

    private zzwe(String str) {
        this.zzd = str;
    }

    public final String toString() {
        return this.zzd;
    }
}
