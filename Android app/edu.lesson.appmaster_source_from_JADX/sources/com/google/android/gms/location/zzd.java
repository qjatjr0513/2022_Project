package com.google.android.gms.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzam;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
final /* synthetic */ class zzd implements RemoteCall {
    private final ActivityRecognitionClient zza;
    private final PendingIntent zzb;
    private final SleepSegmentRequest zzc;

    zzd(ActivityRecognitionClient activityRecognitionClient, PendingIntent pendingIntent, SleepSegmentRequest sleepSegmentRequest) {
        this.zza = activityRecognitionClient;
        this.zzb = pendingIntent;
        this.zzc = sleepSegmentRequest;
    }

    public final void accept(Object obj, Object obj2) {
        ActivityRecognitionClient activityRecognitionClient = this.zza;
        ((zzam) ((zzaz) obj).getService()).zzv(this.zzb, this.zzc, new zzi(activityRecognitionClient, (TaskCompletionSource) obj2));
    }
}
