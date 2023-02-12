package com.google.firebase.auth;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class FirebaseAuthUserCollisionException extends FirebaseAuthException {
    private AuthCredential zza;
    private String zzb;
    private String zzc;

    public FirebaseAuthUserCollisionException(String str, String str2) {
        super(str, str2);
    }

    public String getEmail() {
        return this.zzb;
    }

    public AuthCredential getUpdatedCredential() {
        return this.zza;
    }

    public final FirebaseAuthUserCollisionException zza(AuthCredential authCredential) {
        this.zza = authCredential;
        return this;
    }

    public final FirebaseAuthUserCollisionException zzb(String str) {
        this.zzb = str;
        return this;
    }

    public final FirebaseAuthUserCollisionException zzc(String str) {
        this.zzc = str;
        return this;
    }
}
