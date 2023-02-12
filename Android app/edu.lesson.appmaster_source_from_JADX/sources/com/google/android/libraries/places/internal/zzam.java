package com.google.android.libraries.places.internal;

import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.internal.zzcz;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class zzam<TypeT, RequestT extends zzcz> {
    private final RequestT zza;

    protected zzam(RequestT requestt) {
        this.zza = requestt;
    }

    /* access modifiers changed from: protected */
    public final CancellationToken zza() {
        return this.zza.getCancellationToken();
    }

    /* access modifiers changed from: protected */
    public final RequestT zzb() {
        return this.zza;
    }

    /* access modifiers changed from: protected */
    public abstract String zzc();

    /* access modifiers changed from: protected */
    public abstract Map<String, String> zzd();
}
