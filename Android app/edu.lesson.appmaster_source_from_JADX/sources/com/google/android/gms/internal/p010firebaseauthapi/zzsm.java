package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsm */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final /* synthetic */ class zzsm implements RemoteCall {
    public final /* synthetic */ zzsn zza;

    public /* synthetic */ zzsm(zzsn zzsn) {
        this.zza = zzsn;
    }

    public final void accept(Object obj, Object obj2) {
        this.zza.zzd((zztm) obj, (TaskCompletionSource) obj2);
    }
}
