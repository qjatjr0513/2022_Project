package com.google.android.gms.measurement.internal;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.firebase.messaging.Constants;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzhx implements Runnable {
    final /* synthetic */ boolean zza;
    final /* synthetic */ Uri zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ String zzd;
    final /* synthetic */ zzhz zze;

    zzhx(zzhz zzhz, boolean z, Uri uri, String str, String str2) {
        this.zze = zzhz;
        this.zza = z;
        this.zzb = uri;
        this.zzc = str;
        this.zzd = str2;
    }

    public final void run() {
        Bundle bundle;
        zzhz zzhz = this.zze;
        boolean z = this.zza;
        Uri uri = this.zzb;
        String str = this.zzc;
        String str2 = this.zzd;
        zzhz.zza.zzg();
        try {
            zzkz zzv = zzhz.zza.zzs.zzv();
            if (TextUtils.isEmpty(str2)) {
                bundle = null;
            } else if (str2.contains("gclid") || str2.contains("utm_campaign") || str2.contains("utm_source") || str2.contains("utm_medium")) {
                String valueOf = String.valueOf(str2);
                bundle = zzv.zzs(Uri.parse(valueOf.length() != 0 ? "https://google.com/search?".concat(valueOf) : new String("https://google.com/search?")));
                if (bundle != null) {
                    bundle.putString("_cis", "referrer");
                }
            } else {
                zzv.zzs.zzay().zzc().zza("Activity created with data 'referrer' without required params");
                bundle = null;
            }
            if (z) {
                Bundle zzs = zzhz.zza.zzs.zzv().zzs(uri);
                if (zzs != null) {
                    zzs.putString("_cis", "intent");
                    if (!zzs.containsKey("gclid") && bundle != null && bundle.containsKey("gclid")) {
                        zzs.putString("_cer", String.format("gclid=%s", new Object[]{bundle.getString("gclid")}));
                    }
                    zzhz.zza.zzG(str, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, zzs);
                    zzhz.zza.zzb.zza(str, zzs);
                }
            }
            if (!TextUtils.isEmpty(str2)) {
                zzhz.zza.zzs.zzay().zzc().zzb("Activity created with referrer", str2);
                if (zzhz.zza.zzs.zzf().zzs((String) null, zzdy.zzaa)) {
                    if (bundle != null) {
                        zzhz.zza.zzG(str, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, bundle);
                        zzhz.zza.zzb.zza(str, bundle);
                    } else {
                        zzhz.zza.zzs.zzay().zzc().zzb("Referrer does not contain valid parameters", str2);
                    }
                    zzhz.zza.zzX("auto", "_ldl", (Object) null, true);
                } else if (!str2.contains("gclid") || (!str2.contains("utm_campaign") && !str2.contains("utm_source") && !str2.contains("utm_medium") && !str2.contains("utm_term") && !str2.contains("utm_content"))) {
                    zzhz.zza.zzs.zzay().zzc().zza("Activity created with data 'referrer' without required params");
                } else if (!TextUtils.isEmpty(str2)) {
                    zzhz.zza.zzX("auto", "_ldl", str2, true);
                }
            }
        } catch (RuntimeException e) {
            zzhz.zza.zzs.zzay().zzd().zzb("Throwable caught in handleReferrerForOnActivityCreated", e);
        }
    }
}
