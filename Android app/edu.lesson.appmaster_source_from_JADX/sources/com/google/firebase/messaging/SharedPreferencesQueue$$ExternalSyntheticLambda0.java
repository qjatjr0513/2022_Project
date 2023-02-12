package com.google.firebase.messaging;

/* compiled from: com.google.firebase:firebase-messaging@@23.0.0 */
public final /* synthetic */ class SharedPreferencesQueue$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ SharedPreferencesQueue f$0;

    public /* synthetic */ SharedPreferencesQueue$$ExternalSyntheticLambda0(SharedPreferencesQueue sharedPreferencesQueue) {
        this.f$0 = sharedPreferencesQueue;
    }

    public final void run() {
        this.f$0.syncState();
    }
}
