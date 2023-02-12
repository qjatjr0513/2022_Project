package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.firebase.auth.PhoneAuthProvider;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzur */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzur implements zzuv {
    final /* synthetic */ String zza;

    zzur(zzuu zzuu, String str) {
        this.zza = str;
    }

    public final void zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onCodeAutoRetrievalTimeOut(this.zza);
    }
}
