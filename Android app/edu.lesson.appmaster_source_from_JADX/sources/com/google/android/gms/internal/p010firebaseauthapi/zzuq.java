package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuq */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzuq implements zzuv {
    final /* synthetic */ PhoneAuthCredential zza;

    zzuq(zzuu zzuu, PhoneAuthCredential phoneAuthCredential) {
        this.zza = phoneAuthCredential;
    }

    public final void zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onVerificationCompleted(this.zza);
    }
}
