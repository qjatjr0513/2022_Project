package com.google.android.libraries.places.internal;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzaa implements Response.ErrorListener {
    public final /* synthetic */ TaskCompletionSource zza;

    public /* synthetic */ zzaa(TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void onErrorResponse(VolleyError volleyError) {
        zzae.zzc(this.zza, volleyError);
    }
}
