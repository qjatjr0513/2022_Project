package com.google.android.gms.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
final /* synthetic */ class zzc implements RemoteCall {
    private final long zza;
    private final PendingIntent zzb;

    zzc(long j, PendingIntent pendingIntent) {
        this.zza = j;
        this.zzb = pendingIntent;
    }

    public final void accept(Object obj, Object obj2) {
        long j = this.zza;
        PendingIntent pendingIntent = this.zzb;
        int i = ActivityRecognitionClient.zza;
        ((zzaz) obj).zzq(j, pendingIntent);
        ((TaskCompletionSource) obj2).setResult(null);
    }
}
