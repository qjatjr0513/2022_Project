package com.google.android.gms.internal.p010firebaseauthapi;

import android.content.Context;
import com.google.android.gms.common.api.Api;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzug */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzug {
    public static final Api.ClientKey<zztm> zza;
    public static final Api<zzuf> zzb;
    private static final Api.AbstractClientBuilder<zztm, zzuf> zzc;

    static {
        Api.ClientKey<zztm> clientKey = new Api.ClientKey<>();
        zza = clientKey;
        zzud zzud = new zzud();
        zzc = zzud;
        zzb = new Api<>("InternalFirebaseAuth.FIREBASE_AUTH_API", zzud, clientKey);
    }

    public static zzti zza(Context context, zzuf zzuf) {
        return new zzti(context, zzuf);
    }
}
