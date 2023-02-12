package com.google.android.gms.internal.p009authapiphone;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api-phone.zzx */
/* compiled from: com.google.android.gms:play-services-auth-api-phone@@17.4.0 */
final /* synthetic */ class zzx implements RemoteCall {
    private final zzu zza;

    zzx(zzu zzu) {
        this.zza = zzu;
    }

    public final void accept(Object obj, Object obj2) {
        ((zzj) ((zzv) obj).getService()).zza((zzl) new zzz(this.zza, (TaskCompletionSource) obj2));
    }
}
