package com.google.android.gms.dynamite;

import android.os.Process;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
final class zza extends Thread {
    zza(ThreadGroup threadGroup, String str) {
        super(threadGroup, "GmsDynamite");
    }

    public final void run() {
        Process.setThreadPriority(19);
        synchronized (this) {
            while (true) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }
}
