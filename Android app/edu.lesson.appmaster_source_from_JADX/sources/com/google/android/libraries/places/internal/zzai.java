package com.google.android.libraries.places.internal;

import com.android.volley.toolbox.ImageRequest;
import com.google.android.gms.tasks.OnTokenCanceledListener;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzai implements OnTokenCanceledListener {
    public final /* synthetic */ ImageRequest zza;

    public /* synthetic */ zzai(ImageRequest imageRequest) {
        this.zza = imageRequest;
    }

    public final void onCanceled() {
        this.zza.cancel();
    }
}
