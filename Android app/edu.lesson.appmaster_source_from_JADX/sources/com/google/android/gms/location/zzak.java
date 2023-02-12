package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
final class zzak extends zzap {
    final /* synthetic */ ListenerHolder zza;
    final /* synthetic */ FusedLocationProviderClient zzb;

    zzak(FusedLocationProviderClient fusedLocationProviderClient, ListenerHolder listenerHolder) {
        this.zzb = fusedLocationProviderClient;
        this.zza = listenerHolder;
    }

    public final /* bridge */ /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
        zzaz zzaz = (zzaz) obj;
        TaskCompletionSource taskCompletionSource = (TaskCompletionSource) obj2;
        if (zza()) {
            zzal zzal = new zzal(this.zzb, taskCompletionSource);
            try {
                ListenerHolder.ListenerKey listenerKey = this.zza.getListenerKey();
                if (listenerKey != null) {
                    zzaz.zzH(listenerKey, zzal);
                }
            } catch (RuntimeException e) {
                taskCompletionSource.trySetException(e);
            }
        }
    }
}
