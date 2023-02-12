package com.google.android.libraries.places.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzbv implements Continuation {
    public final /* synthetic */ zzcb zza;

    public /* synthetic */ zzbv(zzcb zzcb) {
        this.zza = zzcb;
    }

    public final Object then(Task task) {
        return zzbk.zza((zzbj) task.getResult());
    }
}
