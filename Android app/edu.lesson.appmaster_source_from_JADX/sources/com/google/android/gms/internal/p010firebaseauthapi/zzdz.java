package com.google.android.gms.internal.p010firebaseauthapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdz */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzdz implements zzay {
    private final SharedPreferences.Editor zza;
    private final String zzb = "GenericIdpKeyset";

    public zzdz(Context context, String str, String str2) {
        Context applicationContext = context.getApplicationContext();
        if (str2 == null) {
            this.zza = PreferenceManager.getDefaultSharedPreferences(applicationContext).edit();
        } else {
            this.zza = applicationContext.getSharedPreferences(str2, 0).edit();
        }
    }

    public final void zzb(zzho zzho) throws IOException {
        if (!this.zza.putString(this.zzb, zzky.zza(zzho.zzr())).commit()) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }

    public final void zzc(zzir zzir) throws IOException {
        if (!this.zza.putString(this.zzb, zzky.zza(zzir.zzr())).commit()) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }
}
