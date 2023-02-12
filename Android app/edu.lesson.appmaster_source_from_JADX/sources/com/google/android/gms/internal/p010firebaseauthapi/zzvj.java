package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.tasks.OnFailureListener;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvj */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzvj implements OnFailureListener {
    zzvj(zzvn zzvn) {
    }

    public final void onFailure(Exception exc) {
        Logger zza = zzvn.zza;
        String valueOf = String.valueOf(exc.getMessage());
        zza.mo31546e(valueOf.length() != 0 ? "SmsRetrieverClient failed to start: ".concat(valueOf) : new String("SmsRetrieverClient failed to start: "), new Object[0]);
    }
}
