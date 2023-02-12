package com.google.android.libraries.places.internal;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.text.TextUtils;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzs {
    private final String zza;
    private final int zzb;
    private final zzr zzc;
    private final boolean zzd;
    private final int zze;

    public zzs(WifiInfo wifiInfo, ScanResult scanResult) {
        zzr zzr;
        String str = scanResult.BSSID;
        String str2 = scanResult.capabilities;
        int i = scanResult.level;
        int i2 = scanResult.frequency;
        if (TextUtils.isEmpty(str2)) {
            zzr = zzr.OTHER;
        } else {
            String upperCase = str2.toUpperCase();
            if (upperCase.equals("[ESS]") || upperCase.equals("[IBSS]")) {
                zzr = zzr.NONE;
            } else if (upperCase.matches(".*WPA[0-9]*-PSK.*")) {
                zzr = zzr.PSK;
            } else if (upperCase.matches(".*WPA[0-9]*-EAP.*")) {
                zzr = zzr.EAP;
            } else {
                zzr = zzr.OTHER;
            }
        }
        boolean z = false;
        if (wifiInfo != null && !TextUtils.isEmpty(str) && str.equalsIgnoreCase(wifiInfo.getBSSID())) {
            z = true;
        }
        this.zza = str;
        this.zzb = i;
        this.zzc = zzr;
        this.zzd = z;
        this.zze = i2;
    }

    public final int zza() {
        return this.zze;
    }

    public final int zzb() {
        return this.zzb;
    }

    public final zzr zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zza;
    }

    public final boolean zze() {
        return this.zzd;
    }
}
