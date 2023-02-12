package com.google.android.libraries.places.internal;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzae {
    private final RequestQueue zza;
    private final zzbq zzb;

    zzae(RequestQueue requestQueue, zzbq zzbq, byte[] bArr) {
        this.zza = requestQueue;
        this.zzb = zzbq;
    }

    public final <HttpJsonResponseT extends zzan> Task<HttpJsonResponseT> zza(zzam<Object, ? extends zzcz> zzam, Class<HttpJsonResponseT> cls) {
        TaskCompletionSource taskCompletionSource;
        String zzc = zzam.zzc();
        Map<String, String> zzd = zzam.zzd();
        CancellationToken zza2 = zzam.zza();
        if (zza2 != null) {
            taskCompletionSource = new TaskCompletionSource(zza2);
        } else {
            taskCompletionSource = new TaskCompletionSource();
        }
        zzad zzad = new zzad(this, 0, zzc, (JSONObject) null, new zzab(this, cls, taskCompletionSource), new zzaa(taskCompletionSource), zzd);
        if (zza2 != null) {
            zza2.onCanceledRequested(new zzac(zzad));
        }
        this.zza.add(zzad);
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Class cls, TaskCompletionSource taskCompletionSource, JSONObject jSONObject) {
        try {
            taskCompletionSource.trySetResult((zzan) this.zzb.zza(jSONObject.toString(), cls));
        } catch (zzao e) {
            try {
                taskCompletionSource.trySetException(new ApiException(new Status(8, e.getMessage())));
            } catch (Error | RuntimeException e2) {
                zzdh.zzb(e2);
                throw e2;
            }
        }
    }

    static /* synthetic */ void zzc(TaskCompletionSource taskCompletionSource, VolleyError volleyError) {
        try {
            taskCompletionSource.trySetException(zzy.zza(volleyError));
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }
}
