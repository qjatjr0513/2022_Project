package com.google.android.gms.internal.p010firebaseauthapi;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.firebase.auth.ModuleDescriptor;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztn */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zztn extends GmsClient<zzua> implements zztm {
    private static final Logger zze = new Logger("FirebaseAuth", "FirebaseAuth:");
    private final Context zzf;
    private final zzuf zzg;

    public zztn(Context context, Looper looper, ClientSettings clientSettings, zzuf zzuf, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 112, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zzf = (Context) Preconditions.checkNotNull(context);
        this.zzg = zzuf;
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthService");
        if (queryLocalInterface instanceof zzua) {
            return (zzua) queryLocalInterface;
        }
        return new zzty(iBinder);
    }

    public final Feature[] getApiFeatures() {
        return zzd.zzd;
    }

    /* access modifiers changed from: protected */
    public final Bundle getGetServiceRequestExtraArgs() {
        Bundle getServiceRequestExtraArgs = super.getGetServiceRequestExtraArgs();
        if (getServiceRequestExtraArgs == null) {
            getServiceRequestExtraArgs = new Bundle();
        }
        zzuf zzuf = this.zzg;
        if (zzuf != null) {
            getServiceRequestExtraArgs.putString("com.google.firebase.auth.API_KEY", zzuf.zzc());
        }
        getServiceRequestExtraArgs.putString("com.google.firebase.auth.LIBRARY_VERSION", zzuk.zzc());
        return getServiceRequestExtraArgs;
    }

    public final int getMinApkVersion() {
        return 12451000;
    }

    /* access modifiers changed from: protected */
    public final String getServiceDescriptor() {
        return "com.google.firebase.auth.api.internal.IFirebaseAuthService";
    }

    /* access modifiers changed from: protected */
    public final String getStartServiceAction() {
        return "com.google.firebase.auth.api.gms.service.START";
    }

    /* access modifiers changed from: protected */
    public final String getStartServicePackage() {
        if (this.zzg.zza) {
            zze.mo31549i("Preparing to create service connection to fallback implementation", new Object[0]);
            return this.zzf.getPackageName();
        }
        zze.mo31549i("Preparing to create service connection to gms implementation", new Object[0]);
        return "com.google.android.gms";
    }

    public final boolean requiresGooglePlayServices() {
        return DynamiteModule.getLocalVersion(this.zzf, ModuleDescriptor.MODULE_ID) == 0;
    }

    public final /* bridge */ /* synthetic */ zzua zzq() throws DeadObjectException {
        return (zzua) super.getService();
    }
}
