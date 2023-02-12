package com.google.firebase.database.core;

import com.google.firebase.database.connection.ConnectionTokenProvider;

public final /* synthetic */ class Context$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ConnectionTokenProvider.GetTokenCallback f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ Context$1$$ExternalSyntheticLambda0(ConnectionTokenProvider.GetTokenCallback getTokenCallback, String str) {
        this.f$0 = getTokenCallback;
        this.f$1 = str;
    }

    public final void run() {
        this.f$0.onError(this.f$1);
    }
}
