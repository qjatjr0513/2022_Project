package com.google.android.libraries.places.internal;

import android.os.Handler;
import android.os.HandlerThread;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzcq {
    private final zzcm zza;
    private final Map<TaskCompletionSource<?>, HandlerThread> zzb = new HashMap();

    public zzcq(zzcm zzcm) {
        this.zza = zzcm;
    }

    public final <T> boolean zza(TaskCompletionSource<T> taskCompletionSource, long j, String str) {
        if (this.zzb.containsKey(taskCompletionSource)) {
            return false;
        }
        HandlerThread handlerThread = new HandlerThread("timeoutHandlerThread");
        handlerThread.start();
        this.zzb.put(taskCompletionSource, handlerThread);
        return new Handler(handlerThread.getLooper()).postDelayed(new zzcp(taskCompletionSource, "Location timeout."), j);
    }

    public final boolean zzb(TaskCompletionSource<?> taskCompletionSource) {
        HandlerThread remove = this.zzb.remove(taskCompletionSource);
        if (remove == null) {
            return false;
        }
        return remove.quit();
    }
}
