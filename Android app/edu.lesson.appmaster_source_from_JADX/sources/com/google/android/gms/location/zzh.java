package com.google.android.gms.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
final /* synthetic */ class zzh implements RemoteCall {
    private final PendingIntent zza;

    zzh(PendingIntent pendingIntent) {
        this.zza = pendingIntent;
    }

    public final void accept(Object obj, Object obj2) {
        PendingIntent pendingIntent = this.zza;
        int i = ActivityRecognitionClient.zza;
        ((zzaz) obj).zzu(pendingIntent, new zzj((TaskCompletionSource) obj2));
    }
}
