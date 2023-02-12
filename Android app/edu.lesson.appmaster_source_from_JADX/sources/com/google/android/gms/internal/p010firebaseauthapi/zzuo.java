package com.google.android.gms.internal.p010firebaseauthapi;

import android.app.Activity;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuo */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzuo extends LifecycleCallback {
    private final List<PhoneAuthProvider.OnVerificationStateChangedCallbacks> zza;

    private zzuo(LifecycleFragment lifecycleFragment, List<PhoneAuthProvider.OnVerificationStateChangedCallbacks> list) {
        super(lifecycleFragment);
        this.mLifecycleFragment.addCallback("PhoneAuthActivityStopCallback", this);
        this.zza = list;
    }

    public static void zza(Activity activity, List<PhoneAuthProvider.OnVerificationStateChangedCallbacks> list) {
        LifecycleFragment fragment = getFragment(activity);
        if (((zzuo) fragment.getCallbackOrNull("PhoneAuthActivityStopCallback", zzuo.class)) == null) {
            new zzuo(fragment, list);
        }
    }

    public final void onStop() {
        synchronized (this.zza) {
            this.zza.clear();
        }
    }
}
