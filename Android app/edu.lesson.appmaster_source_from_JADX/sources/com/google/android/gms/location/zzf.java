package com.google.android.gms.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
final /* synthetic */ class zzf implements RemoteCall {
    private final ActivityTransitionRequest zza;
    private final PendingIntent zzb;

    zzf(ActivityTransitionRequest activityTransitionRequest, PendingIntent pendingIntent) {
        this.zza = activityTransitionRequest;
        this.zzb = pendingIntent;
    }

    public final void accept(Object obj, Object obj2) {
        ActivityTransitionRequest activityTransitionRequest = this.zza;
        PendingIntent pendingIntent = this.zzb;
        int i = ActivityRecognitionClient.zza;
        ((zzaz) obj).zzr(activityTransitionRequest, pendingIntent, new zzj((TaskCompletionSource) obj2));
    }
}
