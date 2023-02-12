package com.google.android.libraries.places.internal;

import android.text.TextUtils;
import com.google.android.libraries.places.internal.zzcz;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
abstract class zzbr<TypeT, RequestT extends zzcz> extends zzam<TypeT, RequestT> {
    private final Locale zza;
    private final String zzb;
    private final zzdl zzc;

    protected zzbr(RequestT requestt, Locale locale, String str, boolean z, zzdl zzdl) {
        super(requestt);
        this.zza = locale;
        this.zzb = str;
        this.zzc = zzdl;
    }

    protected static void zzg(Map<String, String> map, String str, Object obj, Object obj2) {
        String str2;
        if (obj != null) {
            str2 = obj.toString();
        } else {
            str2 = null;
        }
        if (!TextUtils.isEmpty(str2)) {
            map.put(str, str2);
        }
    }

    /* access modifiers changed from: protected */
    public final String zzc() {
        zzcd zzcd = new zzcd(zze(), this.zzb);
        zzcd.zza(this.zza);
        zzcd.zzb(zzf());
        return zzcd.zzc();
    }

    /* access modifiers changed from: protected */
    public final Map<String, String> zzd() {
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.zzc.zza());
        hashMap.put("X-Places-Android-Sdk", new String("2.5.0"));
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public abstract String zze();

    /* access modifiers changed from: protected */
    public abstract Map<String, String> zzf();
}
