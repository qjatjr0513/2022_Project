package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
public final class zzig {
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0038 A[Catch:{ IOException -> 0x003e, ClassNotFoundException -> 0x003c }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0042 A[Catch:{ IOException -> 0x003e, ClassNotFoundException -> 0x003c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object zza(java.lang.Object r4) {
        /*
            r0 = 0
            if (r4 == 0) goto L_0x0047
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0032 }
            r1.<init>()     // Catch:{ all -> 0x0032 }
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch:{ all -> 0x0032 }
            r2.<init>(r1)     // Catch:{ all -> 0x0032 }
            r2.writeObject(r4)     // Catch:{ all -> 0x002e }
            r2.flush()     // Catch:{ all -> 0x002e }
            java.io.ObjectInputStream r4 = new java.io.ObjectInputStream     // Catch:{ all -> 0x002e }
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x002e }
            byte[] r1 = r1.toByteArray()     // Catch:{ all -> 0x002e }
            r3.<init>(r1)     // Catch:{ all -> 0x002e }
            r4.<init>(r3)     // Catch:{ all -> 0x002e }
            java.lang.Object r1 = r4.readObject()     // Catch:{ all -> 0x002c }
            r2.close()     // Catch:{ IOException -> 0x003e, ClassNotFoundException -> 0x003c }
            r4.close()     // Catch:{ IOException -> 0x003e, ClassNotFoundException -> 0x003c }
            return r1
        L_0x002c:
            r1 = move-exception
            goto L_0x0036
        L_0x002e:
            r4 = move-exception
            r1 = r4
            r4 = r0
            goto L_0x0036
        L_0x0032:
            r4 = move-exception
            r1 = r4
            r4 = r0
            r2 = r4
        L_0x0036:
            if (r2 == 0) goto L_0x0040
            r2.close()     // Catch:{ IOException -> 0x003e, ClassNotFoundException -> 0x003c }
            goto L_0x0040
        L_0x003c:
            r4 = move-exception
            goto L_0x0046
        L_0x003e:
            r4 = move-exception
            goto L_0x0046
        L_0x0040:
            if (r4 == 0) goto L_0x0045
            r4.close()     // Catch:{ IOException -> 0x003e, ClassNotFoundException -> 0x003c }
        L_0x0045:
            throw r1     // Catch:{ IOException -> 0x003e, ClassNotFoundException -> 0x003c }
        L_0x0046:
            return r0
        L_0x0047:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzig.zza(java.lang.Object):java.lang.Object");
    }

    public static String zzb(String str, String[] strArr, String[] strArr2) {
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        int min = Math.min(strArr.length, strArr2.length);
        for (int i = 0; i < min; i++) {
            String str2 = strArr[i];
            if ((str == null && str2 == null) || (str != null && str.equals(str2))) {
                return strArr2[i];
            }
        }
        return null;
    }

    public static String zzc(Context context, String str, String str2) {
        Preconditions.checkNotNull(context);
        Resources resources = context.getResources();
        if (TextUtils.isEmpty(str2)) {
            str2 = zzfn.zza(context);
        }
        return zzfn.zzb("google_app_id", resources, str2);
    }
}
