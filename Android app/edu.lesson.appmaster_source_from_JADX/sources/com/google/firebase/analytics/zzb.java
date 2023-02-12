package com.google.firebase.analytics;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement-api@@20.0.0 */
final class zzb implements Callable<String> {
    final /* synthetic */ FirebaseAnalytics zza;

    zzb(FirebaseAnalytics firebaseAnalytics) {
        this.zza = firebaseAnalytics;
    }

    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        return this.zza.zzb.zzk();
    }
}
