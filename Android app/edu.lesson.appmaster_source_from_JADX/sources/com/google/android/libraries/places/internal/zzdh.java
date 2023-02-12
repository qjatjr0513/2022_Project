package com.google.android.libraries.places.internal;

import android.content.Context;
import android.os.DropBoxManager;
import java.util.LinkedHashMap;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzdh {
    private static DropBoxManager zza;
    private static final LinkedHashMap<Long, Integer> zzb = new zzdg(16, 0.75f, true);
    private static String zzc;

    public static synchronized void zza(Context context, boolean z) {
        synchronized (zzdh.class) {
            if (zza == null) {
                zza = (DropBoxManager) context.getApplicationContext().getSystemService("dropbox");
                zzc = "com.google.android.libraries.places";
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0025, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void zzb(java.lang.Throwable r17) {
        /*
            java.lang.Class<com.google.android.libraries.places.internal.zzdh> r1 = com.google.android.libraries.places.internal.zzdh.class
            monitor-enter(r1)
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x00bf }
            long r2 = r0.getId()     // Catch:{ all -> 0x00bf }
            int r4 = r17.hashCode()     // Catch:{ all -> 0x00bf }
            java.util.LinkedHashMap<java.lang.Long, java.lang.Integer> r0 = zzb     // Catch:{ all -> 0x00bf }
            java.lang.Long r5 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x00bf }
            java.lang.Object r0 = r0.get(r5)     // Catch:{ all -> 0x00bf }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ all -> 0x00bf }
            if (r0 == 0) goto L_0x0026
            int r0 = r0.intValue()     // Catch:{ all -> 0x00bf }
            if (r0 == r4) goto L_0x0024
            goto L_0x0026
        L_0x0024:
            monitor-exit(r1)
            return
        L_0x0026:
            android.os.DropBoxManager r0 = zza     // Catch:{ all -> 0x00bf }
            if (r0 == 0) goto L_0x0024
            java.lang.String r5 = "system_app_crash"
            boolean r0 = r0.isTagEnabled(r5)     // Catch:{ all -> 0x00bf }
            if (r0 == 0) goto L_0x0024
            android.os.DropBoxManager r5 = zza     // Catch:{ all -> 0x00bf }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bf }
            r6.<init>()     // Catch:{ all -> 0x00bf }
            r0 = 3
            java.lang.Object[] r7 = new java.lang.Object[r0]     // Catch:{ all -> 0x00bf }
            java.lang.String r8 = zzc     // Catch:{ all -> 0x00bf }
            r9 = 0
            r7[r9] = r8     // Catch:{ all -> 0x00bf }
            r8 = 46
            com.google.android.libraries.places.internal.zzfq r8 = com.google.android.libraries.places.internal.zzfq.zzb(r8)     // Catch:{ all -> 0x00bf }
            java.lang.String r10 = "2.5.0"
            java.util.List r8 = r8.zzc(r10)     // Catch:{ all -> 0x00bf }
            int r10 = r8.size()     // Catch:{ all -> 0x00bf }
            if (r10 == r0) goto L_0x0056
            r11 = -1
            goto L_0x0076
        L_0x0056:
            r13 = 0
            r0 = r9
        L_0x0059:
            int r10 = r8.size()     // Catch:{ NumberFormatException -> 0x0073 }
            if (r0 >= r10) goto L_0x0071
            r15 = 100
            long r13 = r13 * r15
            java.lang.Object r10 = r8.get(r0)     // Catch:{ NumberFormatException -> 0x0073 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ NumberFormatException -> 0x0073 }
            int r10 = java.lang.Integer.parseInt(r10)     // Catch:{ NumberFormatException -> 0x0073 }
            long r11 = (long) r10
            long r13 = r13 + r11
            int r0 = r0 + 1
            goto L_0x0059
        L_0x0071:
            r11 = r13
            goto L_0x0076
        L_0x0073:
            r0 = move-exception
            r11 = -1
        L_0x0076:
            java.lang.Long r0 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x00bf }
            r8 = 1
            r7[r8] = r0     // Catch:{ all -> 0x00bf }
            r0 = 2
            java.lang.String r10 = "2.5.0"
            r7[r0] = r10     // Catch:{ all -> 0x00bf }
            java.lang.String r0 = "Package: %s v%d (%s)\n"
            java.lang.String r0 = java.lang.String.format(r0, r7)     // Catch:{ all -> 0x00bf }
            r6.append(r0)     // Catch:{ all -> 0x00bf }
            java.lang.Object[] r0 = new java.lang.Object[r8]     // Catch:{ all -> 0x00bf }
            java.lang.String r7 = android.os.Build.FINGERPRINT     // Catch:{ all -> 0x00bf }
            r0[r9] = r7     // Catch:{ all -> 0x00bf }
            java.lang.String r7 = "Build: %s\n"
            java.lang.String r0 = java.lang.String.format(r7, r0)     // Catch:{ all -> 0x00bf }
            r6.append(r0)     // Catch:{ all -> 0x00bf }
            java.lang.String r0 = "\n"
            r6.append(r0)     // Catch:{ all -> 0x00bf }
            java.lang.String r0 = android.util.Log.getStackTraceString(r17)     // Catch:{ all -> 0x00bf }
            r6.append(r0)     // Catch:{ all -> 0x00bf }
            java.lang.String r0 = "system_app_crash"
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00bf }
            r5.addText(r0, r6)     // Catch:{ all -> 0x00bf }
            java.util.LinkedHashMap<java.lang.Long, java.lang.Integer> r0 = zzb     // Catch:{ all -> 0x00bf }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x00bf }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x00bf }
            r0.put(r2, r3)     // Catch:{ all -> 0x00bf }
            monitor-exit(r1)
            return
        L_0x00bf:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzdh.zzb(java.lang.Throwable):void");
    }
}
