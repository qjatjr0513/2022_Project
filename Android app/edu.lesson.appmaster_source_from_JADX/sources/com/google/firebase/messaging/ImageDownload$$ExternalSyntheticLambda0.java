package com.google.firebase.messaging;

import java.util.concurrent.Callable;

/* compiled from: com.google.firebase:firebase-messaging@@23.0.0 */
public final /* synthetic */ class ImageDownload$$ExternalSyntheticLambda0 implements Callable {
    public /* synthetic */ ImageDownload f$0;

    public /* synthetic */ ImageDownload$$ExternalSyntheticLambda0(ImageDownload imageDownload) {
        this.f$0 = imageDownload;
    }

    public final Object call() {
        return this.f$0.blockingDownload();
    }
}
