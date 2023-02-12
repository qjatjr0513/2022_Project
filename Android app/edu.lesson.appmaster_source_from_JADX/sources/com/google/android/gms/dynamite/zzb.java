package com.google.android.gms.dynamite;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
public final class zzb {
    private static volatile ClassLoader zza = null;
    private static volatile Thread zzb = null;

    public static synchronized ClassLoader zza() {
        ClassLoader classLoader;
        synchronized (zzb.class) {
            if (zza == null) {
                zza = zzb();
            }
            classLoader = zza;
        }
        return classLoader;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0010, code lost:
        if (zzb != null) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized java.lang.ClassLoader zzb() {
        /*
            java.lang.Class<com.google.android.gms.dynamite.zzb> r0 = com.google.android.gms.dynamite.zzb.class
            monitor-enter(r0)
            java.lang.Thread r1 = zzb     // Catch:{ all -> 0x0045 }
            r2 = 0
            if (r1 != 0) goto L_0x0015
            java.lang.Thread r1 = zzc()     // Catch:{ all -> 0x0045 }
            zzb = r1     // Catch:{ all -> 0x0045 }
            java.lang.Thread r1 = zzb     // Catch:{ all -> 0x0045 }
            if (r1 == 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            monitor-exit(r0)
            return r2
        L_0x0015:
            java.lang.Thread r1 = zzb     // Catch:{ all -> 0x0045 }
            monitor-enter(r1)     // Catch:{ all -> 0x0045 }
            java.lang.Thread r3 = zzb     // Catch:{ SecurityException -> 0x0021 }
            java.lang.ClassLoader r2 = r3.getContextClassLoader()     // Catch:{ SecurityException -> 0x0021 }
            goto L_0x0041
        L_0x001f:
            r2 = move-exception
            goto L_0x0043
        L_0x0021:
            r3 = move-exception
            java.lang.String r4 = "DynamiteLoaderV2CL"
            java.lang.String r5 = "Failed to get thread context classloader "
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x001f }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x001f }
            int r6 = r3.length()     // Catch:{ all -> 0x001f }
            if (r6 == 0) goto L_0x0039
            java.lang.String r3 = r5.concat(r3)     // Catch:{ all -> 0x001f }
            goto L_0x003e
        L_0x0039:
            java.lang.String r3 = new java.lang.String     // Catch:{ all -> 0x001f }
            r3.<init>(r5)     // Catch:{ all -> 0x001f }
        L_0x003e:
            android.util.Log.w(r4, r3)     // Catch:{ all -> 0x001f }
        L_0x0041:
            monitor-exit(r1)     // Catch:{ all -> 0x001f }
            goto L_0x0013
        L_0x0043:
            monitor-exit(r1)     // Catch:{ all -> 0x001f }
            throw r2     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.zzb.zzb():java.lang.ClassLoader");
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0093  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized java.lang.Thread zzc() {
        /*
            java.lang.Class<com.google.android.gms.dynamite.zzb> r0 = com.google.android.gms.dynamite.zzb.class
            monitor-enter(r0)
            android.os.Looper r1 = android.os.Looper.getMainLooper()     // Catch:{ all -> 0x00a1 }
            java.lang.Thread r1 = r1.getThread()     // Catch:{ all -> 0x00a1 }
            java.lang.ThreadGroup r1 = r1.getThreadGroup()     // Catch:{ all -> 0x00a1 }
            r2 = 0
            if (r1 != 0) goto L_0x0014
            monitor-exit(r0)
            return r2
        L_0x0014:
            java.lang.Class<java.lang.Void> r3 = java.lang.Void.class
            monitor-enter(r3)     // Catch:{ all -> 0x00a1 }
            int r4 = r1.activeGroupCount()     // Catch:{ SecurityException -> 0x0078 }
            java.lang.ThreadGroup[] r5 = new java.lang.ThreadGroup[r4]     // Catch:{ SecurityException -> 0x0078 }
            r1.enumerate(r5)     // Catch:{ SecurityException -> 0x0078 }
            r6 = 0
            r7 = r6
        L_0x0022:
            if (r7 >= r4) goto L_0x0036
            r8 = r5[r7]     // Catch:{ SecurityException -> 0x0078 }
            java.lang.String r9 = "dynamiteLoader"
            java.lang.String r10 = r8.getName()     // Catch:{ SecurityException -> 0x0078 }
            boolean r9 = r9.equals(r10)     // Catch:{ SecurityException -> 0x0078 }
            if (r9 == 0) goto L_0x0033
            goto L_0x0037
        L_0x0033:
            int r7 = r7 + 1
            goto L_0x0022
        L_0x0036:
            r8 = r2
        L_0x0037:
            if (r8 != 0) goto L_0x0040
            java.lang.ThreadGroup r8 = new java.lang.ThreadGroup     // Catch:{ SecurityException -> 0x0078 }
            java.lang.String r4 = "dynamiteLoader"
            r8.<init>(r1, r4)     // Catch:{ SecurityException -> 0x0078 }
        L_0x0040:
            int r1 = r8.activeCount()     // Catch:{ SecurityException -> 0x0078 }
            java.lang.Thread[] r4 = new java.lang.Thread[r1]     // Catch:{ SecurityException -> 0x0078 }
            r8.enumerate(r4)     // Catch:{ SecurityException -> 0x0078 }
        L_0x0049:
            if (r6 >= r1) goto L_0x005d
            r5 = r4[r6]     // Catch:{ SecurityException -> 0x0078 }
            java.lang.String r7 = "GmsDynamite"
            java.lang.String r9 = r5.getName()     // Catch:{ SecurityException -> 0x0078 }
            boolean r7 = r7.equals(r9)     // Catch:{ SecurityException -> 0x0078 }
            if (r7 == 0) goto L_0x005a
            goto L_0x005e
        L_0x005a:
            int r6 = r6 + 1
            goto L_0x0049
        L_0x005d:
            r5 = r2
        L_0x005e:
            if (r5 != 0) goto L_0x0075
            com.google.android.gms.dynamite.zza r1 = new com.google.android.gms.dynamite.zza     // Catch:{ SecurityException -> 0x0071 }
            java.lang.String r4 = "GmsDynamite"
            r1.<init>(r8, r4)     // Catch:{ SecurityException -> 0x0071 }
            r1.setContextClassLoader(r2)     // Catch:{ SecurityException -> 0x006f }
            r1.start()     // Catch:{ SecurityException -> 0x006f }
            r5 = r1
            goto L_0x009c
        L_0x006f:
            r2 = move-exception
            goto L_0x007c
        L_0x0071:
            r1 = move-exception
            r2 = r1
            r1 = r5
            goto L_0x007c
        L_0x0075:
            goto L_0x009c
        L_0x0076:
            r1 = move-exception
            goto L_0x009f
        L_0x0078:
            r1 = move-exception
            r11 = r2
            r2 = r1
            r1 = r11
        L_0x007c:
            java.lang.String r4 = "DynamiteLoaderV2CL"
            java.lang.String r5 = "Failed to enumerate thread/threadgroup "
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x0076 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x0076 }
            int r6 = r2.length()     // Catch:{ all -> 0x0076 }
            if (r6 == 0) goto L_0x0093
            java.lang.String r2 = r5.concat(r2)     // Catch:{ all -> 0x0076 }
            goto L_0x0098
        L_0x0093:
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x0076 }
            r2.<init>(r5)     // Catch:{ all -> 0x0076 }
        L_0x0098:
            android.util.Log.w(r4, r2)     // Catch:{ all -> 0x0076 }
            r5 = r1
        L_0x009c:
            monitor-exit(r3)     // Catch:{ all -> 0x0076 }
            monitor-exit(r0)
            return r5
        L_0x009f:
            monitor-exit(r3)     // Catch:{ all -> 0x0076 }
            throw r1     // Catch:{ all -> 0x00a1 }
        L_0x00a1:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.zzb.zzc():java.lang.Thread");
    }
}
