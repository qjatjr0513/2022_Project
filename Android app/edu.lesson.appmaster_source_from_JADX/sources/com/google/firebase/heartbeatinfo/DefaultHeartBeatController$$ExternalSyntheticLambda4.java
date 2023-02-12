package com.google.firebase.heartbeatinfo;

import java.util.concurrent.ThreadFactory;

public final /* synthetic */ class DefaultHeartBeatController$$ExternalSyntheticLambda4 implements ThreadFactory {
    public static final /* synthetic */ DefaultHeartBeatController$$ExternalSyntheticLambda4 INSTANCE = new DefaultHeartBeatController$$ExternalSyntheticLambda4();

    private /* synthetic */ DefaultHeartBeatController$$ExternalSyntheticLambda4() {
    }

    public final Thread newThread(Runnable runnable) {
        return DefaultHeartBeatController.lambda$static$0(runnable);
    }
}
