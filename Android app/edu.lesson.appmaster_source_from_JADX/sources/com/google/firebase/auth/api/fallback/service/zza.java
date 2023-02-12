package com.google.firebase.auth.api.fallback.service;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.FallbackServiceBroker;
import com.google.android.gms.common.internal.GetServiceRequest;
import com.google.android.gms.common.internal.IGmsCallbacks;
import com.google.android.gms.internal.p010firebaseauthapi.zztp;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zza extends FallbackServiceBroker {
    final /* synthetic */ FirebaseAuthFallbackService zza;

    protected zza(FirebaseAuthFallbackService firebaseAuthFallbackService, Context context) {
        this.zza = firebaseAuthFallbackService;
    }

    public final void getService(IGmsCallbacks iGmsCallbacks, GetServiceRequest getServiceRequest) throws RemoteException {
        Bundle extraArgs = getServiceRequest.getExtraArgs();
        if (extraArgs != null) {
            String string = extraArgs.getString("com.google.firebase.auth.API_KEY");
            if (!TextUtils.isEmpty(string)) {
                iGmsCallbacks.onPostInitComplete(0, new zztp(this.zza, string), (Bundle) null);
                return;
            }
            throw new IllegalArgumentException("ApiKey must not be empty.");
        }
        throw new IllegalArgumentException("ExtraArgs is null.");
    }
}
