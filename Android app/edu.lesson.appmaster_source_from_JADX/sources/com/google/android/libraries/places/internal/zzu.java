package com.google.android.libraries.places.internal;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzu {
    public static final /* synthetic */ int zza = 0;
    private static final long zzb = TimeUnit.MINUTES.toMicros(1);
    private final WifiManager zzc;
    private final zza zzd;

    zzu(WifiManager wifiManager, zza zza2) {
        this.zzc = wifiManager;
        this.zzd = zza2;
    }

    public final zzge<zzs> zza() {
        if (Build.VERSION.SDK_INT < 17) {
            return zzge.zzm();
        }
        WifiManager wifiManager = this.zzc;
        if (wifiManager == null || !wifiManager.isWifiEnabled()) {
            return zzge.zzm();
        }
        List<ScanResult> scanResults = this.zzc.getScanResults();
        if (scanResults == null || scanResults.isEmpty()) {
            return zzge.zzm();
        }
        zzge<ScanResult> zzp = zzge.zzp(zzgo.zza(zzt.zza), scanResults);
        ArrayList arrayList = new ArrayList();
        WifiInfo connectionInfo = this.zzc.getConnectionInfo();
        int size = zzp.size();
        for (int i = 0; i < size; i++) {
            ScanResult scanResult = zzp.get(i);
            if (Build.VERSION.SDK_INT >= 17 && scanResult != null && !TextUtils.isEmpty(scanResult.SSID)) {
                long zza2 = (this.zzd.zza() * 1000) - scanResult.timestamp;
                long j = zzb;
                String str = scanResult.SSID;
                if (str != null) {
                    boolean z = true;
                    if (str.indexOf(95) < 0) {
                        z = false;
                    } else {
                        String lowerCase = str.toLowerCase(Locale.ENGLISH);
                        if (!lowerCase.contains("_nomap") && !lowerCase.contains("_optout")) {
                            z = false;
                        }
                    }
                    if (zza2 <= j && !z) {
                        arrayList.add(new zzs(connectionInfo, scanResult));
                    }
                } else {
                    throw new IllegalArgumentException("Null SSID.");
                }
            }
        }
        return zzge.zzk(arrayList);
    }
}
