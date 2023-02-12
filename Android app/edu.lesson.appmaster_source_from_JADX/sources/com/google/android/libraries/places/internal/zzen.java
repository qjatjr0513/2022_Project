package com.google.android.libraries.places.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzen implements OnCompleteListener {
    public final /* synthetic */ zzer zza;
    public final /* synthetic */ String zzb;

    public /* synthetic */ zzen(zzer zzer, String str) {
        this.zza = zzer;
        this.zzb = str;
    }

    public final void onComplete(Task task) {
        this.zza.zzb(this.zzb, task);
    }
}
