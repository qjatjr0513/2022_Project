package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.google.android.gms.measurement.internal.zzfn;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.0.0 */
final class zzcx extends zzdt {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ Context zzc;
    final /* synthetic */ Bundle zzd;
    final /* synthetic */ zzee zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcx(zzee zzee, String str, String str2, Context context, Bundle bundle) {
        super(zzee, true);
        this.zze = zzee;
        this.zza = str;
        this.zzb = str2;
        this.zzc = context;
        this.zzd = bundle;
    }

    public final void zza() {
        String str;
        String str2;
        String str3;
        boolean z;
        try {
            if (zzee.zzV(this.zza, this.zzb)) {
                String str4 = this.zzb;
                str2 = this.zza;
                str = str4;
                str3 = this.zze.zzd;
            } else {
                str3 = null;
                str2 = null;
                str = null;
            }
            Preconditions.checkNotNull(this.zzc);
            zzee zzee = this.zze;
            zzee.zzj = zzee.zzf(this.zzc, true);
            if (this.zze.zzj == null) {
                Log.w(this.zze.zzd, "Failed to connect to measurement client.");
                return;
            }
            int localVersion = DynamiteModule.getLocalVersion(this.zzc, ModuleDescriptor.MODULE_ID);
            int remoteVersion = DynamiteModule.getRemoteVersion(this.zzc, ModuleDescriptor.MODULE_ID);
            int max = Math.max(localVersion, remoteVersion);
            if (remoteVersion < localVersion) {
                z = true;
            } else {
                z = false;
            }
            ((zzcc) Preconditions.checkNotNull(this.zze.zzj)).initialize(ObjectWrapper.wrap(this.zzc), new zzcl(46000, (long) max, z, str3, str2, str, this.zzd, zzfn.zza(this.zzc)), this.zzh);
        } catch (Exception e) {
            this.zze.zzS(e, true, false);
        }
    }
}
