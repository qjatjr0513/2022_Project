package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.internal.location.zzg;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
public class ActivityRecognition {
    public static final Api<Api.ApiOptions.NoOptions> API;
    @Deprecated
    public static final ActivityRecognitionApi ActivityRecognitionApi = new zzg();
    public static final String CLIENT_NAME = "activity_recognition";
    private static final Api.ClientKey<zzaz> zza;
    private static final Api.AbstractClientBuilder<zzaz, Api.ApiOptions.NoOptions> zzb;

    static {
        Api.ClientKey<zzaz> clientKey = new Api.ClientKey<>();
        zza = clientKey;
        zza zza2 = new zza();
        zzb = zza2;
        API = new Api<>("ActivityRecognition.API", zza2, clientKey);
    }

    private ActivityRecognition() {
    }

    public static ActivityRecognitionClient getClient(Activity activity) {
        return new ActivityRecognitionClient(activity);
    }

    public static ActivityRecognitionClient getClient(Context context) {
        return new ActivityRecognitionClient(context);
    }
}
