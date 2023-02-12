package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.p010firebaseauthapi.zzpv;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Future;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpy */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public abstract class zzpy<T extends zzpv> {
    private zzpu<T> zza;

    public final <ResultT, A extends Api.AnyClient> Task<ResultT> zza(zzpx<A, ResultT> zzpx) {
        return zzc().zza.doRead(zzpx.zza());
    }

    public final <ResultT, A extends Api.AnyClient> Task<ResultT> zzb(zzpx<A, ResultT> zzpx) {
        return zzc().zza.doWrite(zzpx.zza());
    }

    public final zzpu<T> zzc() {
        zzpu<T> zzpu;
        synchronized (this) {
            if (this.zza == null) {
                try {
                    this.zza = (zzpu) zzd().get();
                } catch (Exception e) {
                    String valueOf = String.valueOf(e.getMessage());
                    throw new RuntimeException(valueOf.length() != 0 ? "There was an error while initializing the connection to the GoogleApi: ".concat(valueOf) : new String("There was an error while initializing the connection to the GoogleApi: "));
                }
            }
            zzpu = this.zza;
        }
        return zzpu;
    }

    /* access modifiers changed from: package-private */
    public abstract Future<zzpu<T>> zzd();
}
