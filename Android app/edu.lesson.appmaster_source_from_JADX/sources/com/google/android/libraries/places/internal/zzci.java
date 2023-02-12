package com.google.android.libraries.places.internal;

import android.text.TextUtils;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzci {
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int zza(java.lang.String r3) {
        /*
            r0 = 13
            if (r3 != 0) goto L_0x0005
            return r0
        L_0x0005:
            int r1 = r3.hashCode()
            r2 = 0
            switch(r1) {
                case -1698126997: goto L_0x0040;
                case -1125000185: goto L_0x0036;
                case -813482689: goto L_0x002c;
                case 2524: goto L_0x0022;
                case 1023286998: goto L_0x0018;
                case 1831775833: goto L_0x000e;
                default: goto L_0x000d;
            }
        L_0x000d:
            goto L_0x004a
        L_0x000e:
            java.lang.String r1 = "OVER_QUERY_LIMIT"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x004a
            r3 = 2
            goto L_0x004b
        L_0x0018:
            java.lang.String r1 = "NOT_FOUND"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x004a
            r3 = 5
            goto L_0x004b
        L_0x0022:
            java.lang.String r1 = "OK"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x004a
            r3 = r2
            goto L_0x004b
        L_0x002c:
            java.lang.String r1 = "ZERO_RESULTS"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x004a
            r3 = 1
            goto L_0x004b
        L_0x0036:
            java.lang.String r1 = "INVALID_REQUEST"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x004a
            r3 = 4
            goto L_0x004b
        L_0x0040:
            java.lang.String r1 = "REQUEST_DENIED"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x004a
            r3 = 3
            goto L_0x004b
        L_0x004a:
            r3 = -1
        L_0x004b:
            switch(r3) {
                case 0: goto L_0x005b;
                case 1: goto L_0x005b;
                case 2: goto L_0x0058;
                case 3: goto L_0x0055;
                case 4: goto L_0x0052;
                case 5: goto L_0x004f;
                default: goto L_0x004e;
            }
        L_0x004e:
            return r0
        L_0x004f:
            r3 = 9013(0x2335, float:1.263E-41)
            return r3
        L_0x0052:
            r3 = 9012(0x2334, float:1.2629E-41)
            return r3
        L_0x0055:
            r3 = 9011(0x2333, float:1.2627E-41)
            return r3
        L_0x0058:
            r3 = 9010(0x2332, float:1.2626E-41)
            return r3
        L_0x005b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzci.zza(java.lang.String):int");
    }

    public static String zzb(String str, String str2) {
        return TextUtils.isEmpty(str2) ? str : str2;
    }
}
