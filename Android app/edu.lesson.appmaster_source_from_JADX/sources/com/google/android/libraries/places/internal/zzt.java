package com.google.android.libraries.places.internal;

import android.net.wifi.ScanResult;
import java.util.Comparator;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzt implements Comparator {
    public static final /* synthetic */ zzt zza = new zzt();

    private /* synthetic */ zzt() {
    }

    public final int compare(Object obj, Object obj2) {
        int i = zzu.zza;
        return ((ScanResult) obj2).level - ((ScanResult) obj).level;
    }
}
