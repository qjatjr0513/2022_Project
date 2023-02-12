package com.google.android.libraries.places.internal;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzag implements Response.ErrorListener {
    public final /* synthetic */ TaskCompletionSource zza;

    public /* synthetic */ zzag(TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void onErrorResponse(VolleyError volleyError) {
        zzak.zza(this.zza, volleyError);
    }
}
