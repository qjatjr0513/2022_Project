package com.google.android.gms.internal.p010firebaseauthapi;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;
import com.google.firebase.FirebaseExceptionMapper;
import java.util.concurrent.Callable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztj */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zztj implements Callable<zzpu<zzuf>> {
    private final zzuf zza;
    private final Context zzb;

    public zztj(zzuf zzuf, Context context) {
        this.zza = zzuf;
        this.zzb = context;
    }

    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        boolean z;
        int isGooglePlayServicesAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this.zzb, 12451000);
        if (isGooglePlayServicesAvailable != 0) {
            z = isGooglePlayServicesAvailable == 2;
        } else {
            z = true;
        }
        boolean unused = zztk.zza = z;
        Context context = this.zzb;
        zzuf zzb2 = this.zza.zza();
        zzb2.zza = true;
        return new zzpu(new zzpw(context, zzug.zzb, zzb2, new GoogleApi.Settings.Builder().setMapper(new FirebaseExceptionMapper()).build()));
    }
}
