package com.google.android.libraries.places.internal;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.libraries.places.api.net.PlacesClient;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzcu implements zzcw {
    private final zzcy zza;
    private final Context zzb;
    private final zzdf zzc;
    private final zzcu zzd = this;
    private final zzaeu<zza> zze;

    /* synthetic */ zzcu(Context context, zzcy zzcy, zzdf zzdf, zzct zzct) {
        this.zza = zzcy;
        this.zzb = context;
        this.zzc = zzdf;
        this.zze = zzaet.zza(zzc.zza());
    }

    public static zzcv zza() {
        return new zzcs((zzcr) null);
    }

    private final zzw zzc() {
        return zzx.zza(new zzdj(this.zzb), this.zzc, this.zza);
    }

    public final PlacesClient zzb() {
        zzcy zzcy = this.zza;
        zzdl zzdl = new zzdl(this.zzb);
        Context applicationContext = this.zzb.getApplicationContext();
        zzaes.zza(applicationContext);
        RequestQueue newRequestQueue = Volley.newRequestQueue(applicationContext);
        zzaes.zza(newRequestQueue);
        zzae zza2 = zzaf.zza(newRequestQueue, new zzbq());
        Context applicationContext2 = this.zzb.getApplicationContext();
        zzaes.zza(applicationContext2);
        RequestQueue newRequestQueue2 = Volley.newRequestQueue(applicationContext2);
        zzaes.zza(newRequestQueue2);
        zzcb zza3 = zzcc.zza(zzcy, zzdl, zza2, zzal.zza(newRequestQueue2), zzc(), this.zze.zzb(), zzbd.zza(), zzbh.zza(zzcf.zza()), zzbl.zza(), zzbp.zza(zzcf.zza()));
        Context applicationContext3 = this.zzb.getApplicationContext();
        zzaes.zza(applicationContext3);
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(applicationContext3);
        zzaes.zza(fusedLocationProviderClient);
        zzp zza4 = zzq.zza(this.zze.zzb(), fusedLocationProviderClient, new zzcq(new zzcm()));
        Context applicationContext4 = this.zzb.getApplicationContext();
        zzaes.zza(applicationContext4);
        return zzaw.zza(zza3, zza4, zzv.zza((WifiManager) applicationContext4.getSystemService("wifi"), this.zze.zzb()), zzc(), this.zze.zzb());
    }
}
