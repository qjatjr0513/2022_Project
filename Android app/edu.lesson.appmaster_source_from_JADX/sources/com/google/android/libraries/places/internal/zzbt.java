package com.google.android.libraries.places.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.net.FetchPhotoResponse;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzbt implements Continuation {
    public final /* synthetic */ zzcb zza;

    public /* synthetic */ zzbt(zzcb zzcb) {
        this.zza = zzcb;
    }

    public final Object then(Task task) {
        return FetchPhotoResponse.newInstance(((zzbb) task.getResult()).zza);
    }
}
