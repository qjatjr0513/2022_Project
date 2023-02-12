package com.google.android.libraries.places.internal;

import android.content.Context;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.runtime.TransportRuntime;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzdj {
    private final Transport<zzjr> zza = TransportRuntime.getInstance().newFactory("cct").getTransport("LE", zzjr.class, zzdi.zza);

    public zzdj(Context context) {
        TransportRuntime.initialize(context.getApplicationContext());
    }

    public final void zza(zzjr zzjr) {
        this.zza.send(Event.ofData(zzjr));
    }
}
