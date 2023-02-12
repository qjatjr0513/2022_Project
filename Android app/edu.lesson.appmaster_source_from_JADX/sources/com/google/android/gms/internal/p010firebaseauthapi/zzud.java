package com.google.android.gms.internal.p010firebaseauthapi;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzud */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzud extends Api.AbstractClientBuilder<zztm, zzuf> {
    zzud() {
    }

    public final /* bridge */ /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        return new zztn(context, looper, clientSettings, (zzuf) obj, connectionCallbacks, onConnectionFailedListener);
    }
}
