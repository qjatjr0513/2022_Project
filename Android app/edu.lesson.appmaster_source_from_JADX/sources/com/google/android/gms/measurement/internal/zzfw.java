package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final /* synthetic */ class zzfw implements Runnable {
    public final /* synthetic */ zzgn zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ Bundle zzc;

    public /* synthetic */ zzfw(zzgn zzgn, String str, Bundle bundle) {
        this.zza = zzgn;
        this.zzb = str;
        this.zzc = bundle;
    }

    public final void run() {
        this.zza.zzx(this.zzb, this.zzc);
    }
}
