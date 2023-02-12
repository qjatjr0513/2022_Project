package com.google.android.gms.internal.p010firebaseauthapi;

import androidx.collection.ArrayMap;
import com.google.firebase.FirebaseApp;
import java.lang.ref.WeakReference;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvr */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzvr {
    private static final Map<String, zzvp> zza = new ArrayMap();
    private static final Map<String, WeakReference<zzvq>> zzb = new ArrayMap();

    public static String zza(String str) {
        zzvp zzvp;
        Map<String, zzvp> map = zza;
        synchronized (map) {
            zzvp = map.get(str);
        }
        if (zzvp != null) {
            return String.valueOf(zzh(zzvp.zzb(), zzvp.zza(), zzvp.zzb().contains(":"))).concat("emulator/auth/handler");
        }
        throw new IllegalStateException("Tried to get the emulator widget endpoint, but no emulator endpoint overrides found.");
    }

    public static String zzb(String str) {
        zzvp zzvp;
        String str2;
        Map<String, zzvp> map = zza;
        synchronized (map) {
            zzvp = map.get(str);
        }
        if (zzvp != null) {
            String valueOf = String.valueOf(zzh(zzvp.zzb(), zzvp.zza(), zzvp.zzb().contains(":")));
            str2 = valueOf.length() != 0 ? "".concat(valueOf) : new String("");
        } else {
            str2 = "https://";
        }
        return String.valueOf(str2).concat("www.googleapis.com/identitytoolkit/v3/relyingparty");
    }

    public static String zzc(String str) {
        zzvp zzvp;
        String str2;
        Map<String, zzvp> map = zza;
        synchronized (map) {
            zzvp = map.get(str);
        }
        if (zzvp != null) {
            String valueOf = String.valueOf(zzh(zzvp.zzb(), zzvp.zza(), zzvp.zzb().contains(":")));
            str2 = valueOf.length() != 0 ? "".concat(valueOf) : new String("");
        } else {
            str2 = "https://";
        }
        return String.valueOf(str2).concat("identitytoolkit.googleapis.com/v2/accounts");
    }

    public static String zzd(String str) {
        zzvp zzvp;
        String str2;
        Map<String, zzvp> map = zza;
        synchronized (map) {
            zzvp = map.get(str);
        }
        if (zzvp != null) {
            String valueOf = String.valueOf(zzh(zzvp.zzb(), zzvp.zza(), zzvp.zzb().contains(":")));
            str2 = valueOf.length() != 0 ? "".concat(valueOf) : new String("");
        } else {
            str2 = "https://";
        }
        return String.valueOf(str2).concat("securetoken.googleapis.com/v1");
    }

    public static void zze(String str, zzvq zzvq) {
        Map<String, WeakReference<zzvq>> map = zzb;
        synchronized (map) {
            map.put(str, new WeakReference(zzvq));
        }
    }

    public static void zzf(FirebaseApp firebaseApp, String str, int i) {
        String apiKey = firebaseApp.getOptions().getApiKey();
        Map<String, zzvp> map = zza;
        synchronized (map) {
            map.put(apiKey, new zzvp(str, i));
        }
        Map<String, WeakReference<zzvq>> map2 = zzb;
        synchronized (map2) {
            if (map2.containsKey(apiKey)) {
                zzvq zzvq = (zzvq) map2.get(apiKey).get();
                if (zzvq != null) {
                    zzvq.zzi();
                } else {
                    map.remove(apiKey);
                }
            }
        }
    }

    public static boolean zzg(FirebaseApp firebaseApp) {
        return zza.containsKey(firebaseApp.getOptions().getApiKey());
    }

    private static String zzh(String str, int i, boolean z) {
        if (z) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 22);
            sb.append("http://[");
            sb.append(str);
            sb.append("]:");
            sb.append(i);
            sb.append("/");
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 20);
        sb2.append("http://");
        sb2.append(str);
        sb2.append(":");
        sb2.append(i);
        sb2.append("/");
        return sb2.toString();
    }
}
