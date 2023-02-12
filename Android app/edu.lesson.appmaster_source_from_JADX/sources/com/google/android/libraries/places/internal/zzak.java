package com.google.android.libraries.places.internal;

import android.graphics.Bitmap;
import android.widget.ImageView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzak {
    private final RequestQueue zza;

    zzak(RequestQueue requestQueue) {
        this.zza = requestQueue;
    }

    static /* synthetic */ void zza(TaskCompletionSource taskCompletionSource, VolleyError volleyError) {
        ApiException apiException;
        try {
            if (volleyError.networkResponse != null) {
                switch (volleyError.networkResponse.statusCode) {
                    case WARNING_VALUE:
                        apiException = new ApiException(new Status((int) PlacesStatusCodes.INVALID_REQUEST, "The provided parameters are invalid (did you include a max width or height?)."));
                        break;
                    case TypedValues.CycleType.TYPE_ALPHA:
                        apiException = new ApiException(new Status((int) PlacesStatusCodes.REQUEST_DENIED, "The provided API key is invalid."));
                        break;
                }
            }
            apiException = zzy.zza(volleyError);
            taskCompletionSource.trySetException(apiException);
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    static /* synthetic */ void zzc(zzaz zzaz, TaskCompletionSource taskCompletionSource, Bitmap bitmap) {
        try {
            zzaz.zzb(bitmap);
            taskCompletionSource.trySetResult(zzaz.zza());
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    public final <HttpPhotoResponseT extends zzan> Task<HttpPhotoResponseT> zzb(zzam zzam, zzaz zzaz) {
        TaskCompletionSource taskCompletionSource;
        String zzc = zzam.zzc();
        Map<String, String> zzd = zzam.zzd();
        CancellationToken zza2 = zzam.zza();
        if (zza2 != null) {
            taskCompletionSource = new TaskCompletionSource(zza2);
        } else {
            taskCompletionSource = new TaskCompletionSource();
        }
        zzaj zzaj = new zzaj(this, zzc, new zzah(zzaz, taskCompletionSource, (byte[]) null), 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888, new zzag(taskCompletionSource), zzd);
        if (zza2 != null) {
            zza2.onCanceledRequested(new zzai(zzaj));
        }
        this.zza.add(zzaj);
        return taskCompletionSource.getTask();
    }
}
