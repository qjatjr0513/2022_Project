package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.PhoneAuthProvider;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzus */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzus implements zzuv {
    final /* synthetic */ Status zza;

    zzus(zzuu zzuu, Status status) {
        this.zza = status;
    }

    public final void zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onVerificationFailed(zzto.zza(this.zza));
    }
}
