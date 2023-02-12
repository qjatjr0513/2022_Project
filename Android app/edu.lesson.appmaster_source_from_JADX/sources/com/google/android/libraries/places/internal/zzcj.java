package com.google.android.libraries.places.internal;

import android.location.Location;
import android.text.TextUtils;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.RectangularBounds;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzcj {
    private static final zzgg<zzr, String> zza;

    static {
        zzgf zzgf = new zzgf();
        zzgf.zza(zzr.NONE, "NONE");
        zzgf.zza(zzr.PSK, "WPA_PSK");
        zzgf.zza(zzr.EAP, "WPA_EAP");
        zzgf.zza(zzr.OTHER, "SECURED_NONE");
        zza = zzgf.zzb();
    }

    public static Integer zza(Location location) {
        if (location == null) {
            return null;
        }
        float accuracy = location.getAccuracy();
        if (!location.hasAccuracy() || accuracy <= 0.0f) {
            return null;
        }
        return Integer.valueOf(Math.round(accuracy * 100.0f));
    }

    public static String zzb(List<String> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = list.iterator();
        while (true) {
            String str = null;
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            if (!TextUtils.isEmpty(next)) {
                String valueOf = String.valueOf(next.toLowerCase(Locale.US));
                str = valueOf.length() != 0 ? "country:".concat(valueOf) : new String("country:");
            }
            if (str != null) {
                if (sb.length() != 0) {
                    sb.append('|');
                }
                sb.append(str);
            }
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    public static String zzc(Location location) {
        if (location == null) {
            return null;
        }
        return zzh(location.getLatitude(), location.getLongitude());
    }

    public static String zzd(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return zzh(latLng.latitude, latLng.longitude);
    }

    public static String zze(LocationBias locationBias) {
        if (locationBias == null) {
            return null;
        }
        if (locationBias instanceof RectangularBounds) {
            return zzi((RectangularBounds) locationBias);
        }
        throw new AssertionError("Unknown LocationBias type.");
    }

    public static String zzf(LocationRestriction locationRestriction) {
        if (locationRestriction == null) {
            return null;
        }
        if (locationRestriction instanceof RectangularBounds) {
            return zzi((RectangularBounds) locationRestriction);
        }
        throw new AssertionError("Unknown LocationRestriction type.");
    }

    public static String zzg(zzge<zzs> zzge, int i) {
        StringBuilder sb = new StringBuilder();
        int size = zzge.size();
        int i2 = 0;
        while (i2 < size) {
            zzs zzs = zzge.get(i2);
            String str = sb.length() > 0 ? "|" : "";
            zzgf zzgf = new zzgf();
            zzgf.zza("mac", zzs.zzd());
            zzgf.zza("strength_dbm", Integer.valueOf(zzs.zzb()));
            zzgf.zza("wifi_auth_type", zza.get(zzs.zzc()));
            zzgf.zza("is_connected", Boolean.valueOf(zzs.zze()));
            zzgf.zza("frequency_mhz", Integer.valueOf(zzs.zza()));
            zzgg zzb = zzgf.zzb();
            zzfh zzb2 = zzfh.zzb(",");
            Iterator it = zzb.entrySet().iterator();
            StringBuilder sb2 = new StringBuilder();
            try {
                zzff.zza(sb2, it, zzb2, "=");
                String valueOf = String.valueOf(sb2.toString());
                String concat = valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
                if (sb.length() + concat.length() > 4000) {
                    break;
                }
                sb.append(concat);
                i2++;
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }
        return sb.toString();
    }

    private static String zzh(double d, double d2) {
        return String.format(Locale.US, "%.15f,%.15f", new Object[]{Double.valueOf(d), Double.valueOf(d2)});
    }

    private static String zzi(RectangularBounds rectangularBounds) {
        LatLng southwest = rectangularBounds.getSouthwest();
        double d = southwest.latitude;
        double d2 = southwest.longitude;
        LatLng northeast = rectangularBounds.getNortheast();
        double d3 = northeast.latitude;
        double d4 = northeast.longitude;
        return String.format(Locale.US, "rectangle:%.15f,%.15f|%.15f,%.15f", new Object[]{Double.valueOf(d), Double.valueOf(d2), Double.valueOf(d3), Double.valueOf(d4)});
    }
}
