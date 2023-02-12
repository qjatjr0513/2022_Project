package com.google.android.libraries.places.internal;

import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.tasks.OnTokenCanceledListener;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzac implements OnTokenCanceledListener {
    public final /* synthetic */ JsonObjectRequest zza;

    public /* synthetic */ zzac(JsonObjectRequest jsonObjectRequest) {
        this.zza = jsonObjectRequest;
    }

    public final void onCanceled() {
        this.zza.cancel();
    }
}
