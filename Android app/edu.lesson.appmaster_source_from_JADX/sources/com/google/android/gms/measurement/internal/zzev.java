package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zzev {
    final /* synthetic */ zzfa zza;
    private final String zzb = "default_event_parameters";
    private final Bundle zzc = new Bundle();
    private Bundle zzd;

    public zzev(zzfa zzfa, String str, Bundle bundle) {
        this.zza = zzfa;
        Preconditions.checkNotEmpty("default_event_parameters");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Bundle zza() {
        /*
            r9 = this;
            android.os.Bundle r0 = r9.zzd
            if (r0 == 0) goto L_0x0006
            goto L_0x00cf
        L_0x0006:
            com.google.android.gms.measurement.internal.zzfa r0 = r9.zza
            android.content.SharedPreferences r0 = r0.zza()
            java.lang.String r1 = r9.zzb
            r2 = 0
            java.lang.String r0 = r0.getString(r1, r2)
            if (r0 == 0) goto L_0x00c7
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ JSONException -> 0x00b5 }
            r1.<init>()     // Catch:{ JSONException -> 0x00b5 }
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ JSONException -> 0x00b5 }
            r2.<init>(r0)     // Catch:{ JSONException -> 0x00b5 }
            r0 = 0
            r3 = r0
        L_0x0021:
            int r4 = r2.length()     // Catch:{ JSONException -> 0x00b5 }
            if (r3 >= r4) goto L_0x00b2
            org.json.JSONObject r4 = r2.getJSONObject(r3)     // Catch:{ JSONException -> 0x009c, NumberFormatException -> 0x009a }
            java.lang.String r5 = "n"
            java.lang.String r5 = r4.getString(r5)     // Catch:{ JSONException -> 0x009c, NumberFormatException -> 0x009a }
            java.lang.String r6 = "t"
            java.lang.String r6 = r4.getString(r6)     // Catch:{ JSONException -> 0x009c, NumberFormatException -> 0x009a }
            int r7 = r6.hashCode()     // Catch:{ JSONException -> 0x009c, NumberFormatException -> 0x009a }
            switch(r7) {
                case 100: goto L_0x0054;
                case 108: goto L_0x004a;
                case 115: goto L_0x003f;
                default: goto L_0x003e;
            }
        L_0x003e:
            goto L_0x005e
        L_0x003f:
            java.lang.String r7 = "s"
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L_0x003e
            r7 = r0
            goto L_0x005f
        L_0x004a:
            java.lang.String r7 = "l"
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L_0x003e
            r7 = 2
            goto L_0x005f
        L_0x0054:
            java.lang.String r7 = "d"
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L_0x003e
            r7 = 1
            goto L_0x005f
        L_0x005e:
            r7 = -1
        L_0x005f:
            java.lang.String r8 = "v"
            switch(r7) {
                case 0: goto L_0x0081;
                case 1: goto L_0x0074;
                case 2: goto L_0x0067;
                default: goto L_0x0064;
            }
        L_0x0064:
            com.google.android.gms.measurement.internal.zzfa r4 = r9.zza     // Catch:{ JSONException -> 0x009c, NumberFormatException -> 0x009a }
            goto L_0x008a
        L_0x0067:
            java.lang.String r4 = r4.getString(r8)     // Catch:{ JSONException -> 0x009c, NumberFormatException -> 0x009a }
            long r6 = java.lang.Long.parseLong(r4)     // Catch:{ JSONException -> 0x009c, NumberFormatException -> 0x009a }
            r1.putLong(r5, r6)     // Catch:{ JSONException -> 0x009c, NumberFormatException -> 0x009a }
            goto L_0x00ae
        L_0x0074:
            java.lang.String r4 = r4.getString(r8)     // Catch:{ JSONException -> 0x009c, NumberFormatException -> 0x009a }
            double r6 = java.lang.Double.parseDouble(r4)     // Catch:{ JSONException -> 0x009c, NumberFormatException -> 0x009a }
            r1.putDouble(r5, r6)     // Catch:{ JSONException -> 0x009c, NumberFormatException -> 0x009a }
            goto L_0x00ae
        L_0x0081:
            java.lang.String r4 = r4.getString(r8)     // Catch:{ JSONException -> 0x009c, NumberFormatException -> 0x009a }
            r1.putString(r5, r4)     // Catch:{ JSONException -> 0x009c, NumberFormatException -> 0x009a }
            goto L_0x00ae
        L_0x008a:
            com.google.android.gms.measurement.internal.zzfv r4 = r4.zzs     // Catch:{ JSONException -> 0x009c, NumberFormatException -> 0x009a }
            com.google.android.gms.measurement.internal.zzel r4 = r4.zzay()     // Catch:{ JSONException -> 0x009c, NumberFormatException -> 0x009a }
            com.google.android.gms.measurement.internal.zzej r4 = r4.zzd()     // Catch:{ JSONException -> 0x009c, NumberFormatException -> 0x009a }
            java.lang.String r5 = "Unrecognized persisted bundle type. Type"
            r4.zzb(r5, r6)     // Catch:{ JSONException -> 0x009c, NumberFormatException -> 0x009a }
            goto L_0x00ae
        L_0x009a:
            r4 = move-exception
            goto L_0x009d
        L_0x009c:
            r4 = move-exception
        L_0x009d:
            com.google.android.gms.measurement.internal.zzfa r4 = r9.zza     // Catch:{ JSONException -> 0x00b5 }
            com.google.android.gms.measurement.internal.zzfv r4 = r4.zzs     // Catch:{ JSONException -> 0x00b5 }
            com.google.android.gms.measurement.internal.zzel r4 = r4.zzay()     // Catch:{ JSONException -> 0x00b5 }
            com.google.android.gms.measurement.internal.zzej r4 = r4.zzd()     // Catch:{ JSONException -> 0x00b5 }
            java.lang.String r5 = "Error reading value from SharedPreferences. Value dropped"
            r4.zza(r5)     // Catch:{ JSONException -> 0x00b5 }
        L_0x00ae:
            int r3 = r3 + 1
            goto L_0x0021
        L_0x00b2:
            r9.zzd = r1     // Catch:{ JSONException -> 0x00b5 }
            goto L_0x00c7
        L_0x00b5:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfa r0 = r9.zza
            com.google.android.gms.measurement.internal.zzfv r0 = r0.zzs
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzd()
            java.lang.String r1 = "Error loading bundle from SharedPreferences. Values will be lost"
            r0.zza(r1)
        L_0x00c7:
            android.os.Bundle r0 = r9.zzd
            if (r0 != 0) goto L_0x00cf
            android.os.Bundle r0 = r9.zzc
            r9.zzd = r0
        L_0x00cf:
            android.os.Bundle r0 = r9.zzd
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzev.zza():android.os.Bundle");
    }

    public final void zzb(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        SharedPreferences.Editor edit = this.zza.zza().edit();
        if (bundle.size() == 0) {
            edit.remove(this.zzb);
        } else {
            String str = this.zzb;
            JSONArray jSONArray = new JSONArray();
            for (String str2 : bundle.keySet()) {
                Object obj = bundle.get(str2);
                if (obj != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("n", str2);
                        jSONObject.put("v", obj.toString());
                        if (obj instanceof String) {
                            jSONObject.put("t", "s");
                        } else if (obj instanceof Long) {
                            jSONObject.put("t", "l");
                        } else if (obj instanceof Double) {
                            jSONObject.put("t", "d");
                        } else {
                            this.zza.zzs.zzay().zzd().zzb("Cannot serialize bundle value to SharedPreferences. Type", obj.getClass());
                        }
                        jSONArray.put(jSONObject);
                    } catch (JSONException e) {
                        this.zza.zzs.zzay().zzd().zzb("Cannot serialize bundle value to SharedPreferences", e);
                    }
                }
            }
            edit.putString(str, jSONArray.toString());
        }
        edit.apply();
        this.zzd = bundle;
    }
}
