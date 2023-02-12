package com.google.android.gms.internal.measurement;

import android.net.Uri;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zzgv {
    public static final Uri zza = Uri.parse("content://com.google.android.gsf.gservices");
    public static final Uri zzb = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    public static final Pattern zzc = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern zzd = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    static HashMap<String, String> zze;
    static final HashMap<String, Boolean> zzf = new HashMap<>();
    static final HashMap<String, Integer> zzg = new HashMap<>();
    static final HashMap<String, Long> zzh = new HashMap<>();
    static final HashMap<String, Float> zzi = new HashMap<>();
    static final String[] zzj = new String[0];
    /* access modifiers changed from: private */
    public static final AtomicBoolean zzk = new AtomicBoolean();
    private static Object zzl;
    private static boolean zzm;

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x006b, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0070, code lost:
        r11 = r11.query(zza, (java.lang.String[]) null, (java.lang.String) null, new java.lang.String[]{r12}, (java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x007e, code lost:
        if (r11 != null) goto L_0x0081;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0080, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0085, code lost:
        if (r11.moveToFirst() != false) goto L_0x008f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0087, code lost:
        zzc(r0, r12, (java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008d, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r13 = r11.getString(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0093, code lost:
        if (r13 == null) goto L_0x009c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0099, code lost:
        if (r13.equals((java.lang.Object) null) == false) goto L_0x009c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x009b, code lost:
        r13 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x009c, code lost:
        zzc(r0, r12, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009f, code lost:
        if (r13 != null) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a2, code lost:
        r3 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a4, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a5, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a8, code lost:
        throw r12;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String zza(android.content.ContentResolver r11, java.lang.String r12, java.lang.String r13) {
        /*
            java.lang.Class<com.google.android.gms.internal.measurement.zzgv> r13 = com.google.android.gms.internal.measurement.zzgv.class
            monitor-enter(r13)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = zze     // Catch:{ all -> 0x00a9 }
            r1 = 1
            r2 = 0
            r3 = 0
            if (r0 != 0) goto L_0x002a
            java.util.concurrent.atomic.AtomicBoolean r0 = zzk     // Catch:{ all -> 0x00a9 }
            r0.set(r2)     // Catch:{ all -> 0x00a9 }
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ all -> 0x00a9 }
            r0.<init>()     // Catch:{ all -> 0x00a9 }
            zze = r0     // Catch:{ all -> 0x00a9 }
            java.lang.Object r0 = new java.lang.Object     // Catch:{ all -> 0x00a9 }
            r0.<init>()     // Catch:{ all -> 0x00a9 }
            zzl = r0     // Catch:{ all -> 0x00a9 }
            zzm = r2     // Catch:{ all -> 0x00a9 }
            android.net.Uri r0 = zza     // Catch:{ all -> 0x00a9 }
            com.google.android.gms.internal.measurement.zzgu r4 = new com.google.android.gms.internal.measurement.zzgu     // Catch:{ all -> 0x00a9 }
            r4.<init>(r3)     // Catch:{ all -> 0x00a9 }
            r11.registerContentObserver(r0, r1, r4)     // Catch:{ all -> 0x00a9 }
            goto L_0x0054
        L_0x002a:
            java.util.concurrent.atomic.AtomicBoolean r0 = zzk     // Catch:{ all -> 0x00a9 }
            boolean r0 = r0.getAndSet(r2)     // Catch:{ all -> 0x00a9 }
            if (r0 == 0) goto L_0x0054
            java.util.HashMap<java.lang.String, java.lang.String> r0 = zze     // Catch:{ all -> 0x00a9 }
            r0.clear()     // Catch:{ all -> 0x00a9 }
            java.util.HashMap<java.lang.String, java.lang.Boolean> r0 = zzf     // Catch:{ all -> 0x00a9 }
            r0.clear()     // Catch:{ all -> 0x00a9 }
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = zzg     // Catch:{ all -> 0x00a9 }
            r0.clear()     // Catch:{ all -> 0x00a9 }
            java.util.HashMap<java.lang.String, java.lang.Long> r0 = zzh     // Catch:{ all -> 0x00a9 }
            r0.clear()     // Catch:{ all -> 0x00a9 }
            java.util.HashMap<java.lang.String, java.lang.Float> r0 = zzi     // Catch:{ all -> 0x00a9 }
            r0.clear()     // Catch:{ all -> 0x00a9 }
            java.lang.Object r0 = new java.lang.Object     // Catch:{ all -> 0x00a9 }
            r0.<init>()     // Catch:{ all -> 0x00a9 }
            zzl = r0     // Catch:{ all -> 0x00a9 }
            zzm = r2     // Catch:{ all -> 0x00a9 }
        L_0x0054:
            java.lang.Object r0 = zzl     // Catch:{ all -> 0x00a9 }
            java.util.HashMap<java.lang.String, java.lang.String> r4 = zze     // Catch:{ all -> 0x00a9 }
            boolean r4 = r4.containsKey(r12)     // Catch:{ all -> 0x00a9 }
            if (r4 == 0) goto L_0x006c
            java.util.HashMap<java.lang.String, java.lang.String> r11 = zze     // Catch:{ all -> 0x00a9 }
            java.lang.Object r11 = r11.get(r12)     // Catch:{ all -> 0x00a9 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x00a9 }
            if (r11 != 0) goto L_0x0069
            goto L_0x006a
        L_0x0069:
            r3 = r11
        L_0x006a:
            monitor-exit(r13)     // Catch:{ all -> 0x00a9 }
            return r3
        L_0x006c:
            java.lang.String[] r4 = zzj     // Catch:{ all -> 0x00a9 }
            int r4 = r4.length     // Catch:{ all -> 0x00a9 }
            monitor-exit(r13)     // Catch:{ all -> 0x00a9 }
            android.net.Uri r6 = zza
            java.lang.String[] r9 = new java.lang.String[r1]
            r9[r2] = r12
            r7 = 0
            r8 = 0
            r10 = 0
            r5 = r11
            android.database.Cursor r11 = r5.query(r6, r7, r8, r9, r10)
            if (r11 != 0) goto L_0x0081
            return r3
        L_0x0081:
            boolean r13 = r11.moveToFirst()     // Catch:{ all -> 0x00a4 }
            if (r13 != 0) goto L_0x008e
            zzc(r0, r12, r3)     // Catch:{ all -> 0x00a4 }
        L_0x008a:
            r11.close()
            return r3
        L_0x008e:
            java.lang.String r13 = r11.getString(r1)     // Catch:{ all -> 0x00a4 }
            if (r13 == 0) goto L_0x009c
            boolean r1 = r13.equals(r3)     // Catch:{ all -> 0x00a4 }
            if (r1 == 0) goto L_0x009c
            r13 = r3
        L_0x009c:
            zzc(r0, r12, r13)     // Catch:{ all -> 0x00a4 }
            if (r13 != 0) goto L_0x00a2
            goto L_0x008a
        L_0x00a2:
            r3 = r13
            goto L_0x008a
        L_0x00a4:
            r12 = move-exception
            r11.close()
            throw r12
        L_0x00a9:
            r11 = move-exception
            monitor-exit(r13)     // Catch:{ all -> 0x00a9 }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgv.zza(android.content.ContentResolver, java.lang.String, java.lang.String):java.lang.String");
    }

    private static void zzc(Object obj, String str, String str2) {
        synchronized (zzgv.class) {
            if (obj == zzl) {
                zze.put(str, str2);
            }
        }
    }
}
