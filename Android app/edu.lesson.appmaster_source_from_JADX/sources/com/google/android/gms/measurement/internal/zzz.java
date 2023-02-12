package com.google.android.gms.measurement.internal;

import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzz extends zzki {
    private String zza;
    private Set<Integer> zzb;
    private Map<Integer, zzt> zzc;
    private Long zzd;
    private Long zze;

    zzz(zzks zzks) {
        super(zzks);
    }

    private final zzt zzd(Integer num) {
        if (this.zzc.containsKey(num)) {
            return this.zzc.get(num);
        }
        zzt zzt = new zzt(this, this.zza, (zzs) null);
        this.zzc.put(num, zzt);
        return zzt;
    }

    private final boolean zzf(int i, int i2) {
        zzt zzt = this.zzc.get(Integer.valueOf(i));
        if (zzt == null) {
            return false;
        }
        return zzt.zze.get(i2);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x02ed, code lost:
        if (r5 != null) goto L_0x02ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x02ef, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x02f8, code lost:
        if (r5 != null) goto L_0x02ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x031d, code lost:
        if (r5 != null) goto L_0x02ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0320, code lost:
        r1 = r13.keySet().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x032c, code lost:
        if (r1.hasNext() == false) goto L_0x03f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x032e, code lost:
        r4 = ((java.lang.Integer) r1.next()).intValue();
        r5 = java.lang.Integer.valueOf(r4);
        r6 = (com.google.android.gms.internal.measurement.zzgd) r13.get(r5);
        r7 = (java.util.List) r0.get(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0348, code lost:
        if (r7 == null) goto L_0x03e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x034e, code lost:
        if (r7.isEmpty() == false) goto L_0x0356;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0356, code lost:
        r17 = r0;
        r0 = r10.zzf.zzu().zzr(r6.zzk(), r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x036a, code lost:
        if (r0.isEmpty() != false) goto L_0x03df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x036c, code lost:
        r5 = (com.google.android.gms.internal.measurement.zzgc) r6.zzbv();
        r5.zze();
        r5.zzb(r0);
        r20 = r1;
        r0 = r10.zzf.zzu().zzr(r6.zzn(), r7);
        r5.zzf();
        r5.zzd(r0);
        r0 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0393, code lost:
        if (r0 >= r6.zza()) goto L_0x03ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x03a5, code lost:
        if (r7.contains(java.lang.Integer.valueOf(r6.zze(r0).zza())) == false) goto L_0x03aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x03a7, code lost:
        r5.zzg(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x03aa, code lost:
        r0 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x03ad, code lost:
        r0 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x03b2, code lost:
        if (r0 >= r6.zzc()) goto L_0x03cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x03c4, code lost:
        if (r7.contains(java.lang.Integer.valueOf(r6.zzi(r0).zzb())) == false) goto L_0x03c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x03c6, code lost:
        r5.zzh(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x03c9, code lost:
        r0 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x03cc, code lost:
        r3.put(java.lang.Integer.valueOf(r4), (com.google.android.gms.internal.measurement.zzgd) r5.zzaA());
        r0 = r17;
        r1 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x03df, code lost:
        r20 = r1;
        r0 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x03e9, code lost:
        r3.put(r5, r6);
        r0 = r0;
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:290:0x0755, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:295:0x0763, code lost:
        if (r11 != null) goto L_0x0765;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:296:0x0765, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:297:0x0769, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:298:0x076b, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:299:0x076c, code lost:
        r5 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:300:0x076e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:301:0x076f, code lost:
        r29 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:306:0x077c, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:307:0x077d, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:308:0x077f, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:309:0x0780, code lost:
        r29 = r2;
        r28 = r4;
        r25 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:313:0x079e, code lost:
        if (r11 != null) goto L_0x0765;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:318:0x07a9, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:379:0x094a, code lost:
        if (r13 != null) goto L_0x094c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:380:0x094c, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:382:0x0952, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:383:0x0953, code lost:
        r5 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:384:0x0955, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:385:0x0956, code lost:
        r67 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:392:0x0975, code lost:
        if (r13 == null) goto L_0x0978;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:397:0x0980, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:431:0x0aa7, code lost:
        if (r7 != false) goto L_0x0ab6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:433:0x0ab6, code lost:
        r0 = r68;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0155, code lost:
        if (r5 != null) goto L_0x0157;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0157, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0179, code lost:
        if (r5 != null) goto L_0x0157;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x022c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x022e, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x022f, code lost:
        r5 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0232, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0233, code lost:
        r18 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0235, code lost:
        r19 = r7;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x025b  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0266  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0270  */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x03f8  */
    /* JADX WARNING: Removed duplicated region for block: B:244:0x05d0  */
    /* JADX WARNING: Removed duplicated region for block: B:245:0x05d4  */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x076b A[ExcHandler: all (th java.lang.Throwable), Splitter:B:262:0x06dd] */
    /* JADX WARNING: Removed duplicated region for block: B:306:0x077c A[ExcHandler: all (th java.lang.Throwable), Splitter:B:257:0x06bc] */
    /* JADX WARNING: Removed duplicated region for block: B:318:0x07a9  */
    /* JADX WARNING: Removed duplicated region for block: B:343:0x087a  */
    /* JADX WARNING: Removed duplicated region for block: B:344:0x087e  */
    /* JADX WARNING: Removed duplicated region for block: B:382:0x0952 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:352:0x08de] */
    /* JADX WARNING: Removed duplicated region for block: B:397:0x0980  */
    /* JADX WARNING: Removed duplicated region for block: B:439:0x0ade  */
    /* JADX WARNING: Removed duplicated region for block: B:457:0x0b77  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x017f  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01b9 A[Catch:{ SQLiteException -> 0x0232, all -> 0x022e }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01c9 A[SYNTHETIC, Splitter:B:71:0x01c9] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x022e A[ExcHandler: all (th java.lang.Throwable), Splitter:B:64:0x01b3] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.measurement.zzfk> zza(java.lang.String r66, java.util.List<com.google.android.gms.internal.measurement.zzfo> r67, java.util.List<com.google.android.gms.internal.measurement.zzgh> r68, java.lang.Long r69, java.lang.Long r70) {
        /*
            r65 = this;
            r10 = r65
            java.lang.String r11 = "current_results"
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r66)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r67)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r68)
            r0 = r66
            r10.zza = r0
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            r10.zzb = r0
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            r10.zzc = r0
            r0 = r69
            r10.zzd = r0
            r0 = r70
            r10.zze = r0
            java.util.Iterator r0 = r67.iterator()
        L_0x002b:
            boolean r1 = r0.hasNext()
            r12 = 0
            r13 = 1
            if (r1 == 0) goto L_0x0047
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.measurement.zzfo r1 = (com.google.android.gms.internal.measurement.zzfo) r1
            java.lang.String r1 = r1.zzh()
            java.lang.String r2 = "_s"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x002b
            r1 = r13
            goto L_0x0048
        L_0x0047:
            r1 = r12
        L_0x0048:
            com.google.android.gms.internal.measurement.zzoa.zzc()
            com.google.android.gms.measurement.internal.zzfv r0 = r10.zzs
            com.google.android.gms.measurement.internal.zzaf r0 = r0.zzf()
            java.lang.String r2 = r10.zza
            com.google.android.gms.measurement.internal.zzdx<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzdy.zzY
            boolean r14 = r0.zzs(r2, r3)
            com.google.android.gms.internal.measurement.zzoa.zzc()
            com.google.android.gms.measurement.internal.zzfv r0 = r10.zzs
            com.google.android.gms.measurement.internal.zzaf r0 = r0.zzf()
            java.lang.String r2 = r10.zza
            com.google.android.gms.measurement.internal.zzdx<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzdy.zzX
            boolean r15 = r0.zzs(r2, r3)
            if (r1 == 0) goto L_0x00af
            com.google.android.gms.measurement.internal.zzks r0 = r10.zzf
            com.google.android.gms.measurement.internal.zzaj r2 = r0.zzi()
            java.lang.String r3 = r10.zza
            r2.zzY()
            r2.zzg()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)
            android.content.ContentValues r0 = new android.content.ContentValues
            r0.<init>()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r12)
            java.lang.String r5 = "current_session_count"
            r0.put(r5, r4)
            android.database.sqlite.SQLiteDatabase r4 = r2.zzh()     // Catch:{ SQLiteException -> 0x009b }
            java.lang.String[] r5 = new java.lang.String[r13]     // Catch:{ SQLiteException -> 0x009b }
            r5[r12] = r3     // Catch:{ SQLiteException -> 0x009b }
            java.lang.String r6 = "events"
            java.lang.String r7 = "app_id = ?"
            r4.update(r6, r0, r7, r5)     // Catch:{ SQLiteException -> 0x009b }
            goto L_0x00af
        L_0x009b:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfv r2 = r2.zzs
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzel.zzn(r3)
            java.lang.String r4 = "Error resetting session-scoped event counts. appId"
            r2.zzc(r4, r3, r0)
        L_0x00af:
            java.util.Map r0 = java.util.Collections.emptyMap()
            java.lang.String r9 = "Failed to merge filter. appId"
            java.lang.String r8 = "Database error querying filters. appId"
            java.lang.String r7 = "data"
            java.lang.String r6 = "audience_id"
            if (r15 == 0) goto L_0x0183
            if (r14 == 0) goto L_0x0183
            com.google.android.gms.measurement.internal.zzks r0 = r10.zzf
            com.google.android.gms.measurement.internal.zzaj r2 = r0.zzi()
            java.lang.String r3 = r10.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)
            androidx.collection.ArrayMap r4 = new androidx.collection.ArrayMap
            r4.<init>()
            android.database.sqlite.SQLiteDatabase r16 = r2.zzh()
            java.lang.String[] r18 = new java.lang.String[]{r6, r7}     // Catch:{ SQLiteException -> 0x0162, all -> 0x015f }
            java.lang.String[] r0 = new java.lang.String[r13]     // Catch:{ SQLiteException -> 0x0162, all -> 0x015f }
            r0[r12] = r3     // Catch:{ SQLiteException -> 0x0162, all -> 0x015f }
            java.lang.String r17 = "event_filters"
            java.lang.String r19 = "app_id=?"
            r21 = 0
            r22 = 0
            r23 = 0
            r20 = r0
            android.database.Cursor r5 = r16.query(r17, r18, r19, r20, r21, r22, r23)     // Catch:{ SQLiteException -> 0x0162, all -> 0x015f }
            boolean r0 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x015d, all -> 0x015b }
            if (r0 == 0) goto L_0x0151
        L_0x00f1:
            byte[] r0 = r5.getBlob(r13)     // Catch:{ SQLiteException -> 0x015d, all -> 0x015b }
            com.google.android.gms.internal.measurement.zzei r13 = com.google.android.gms.internal.measurement.zzej.zzc()     // Catch:{ IOException -> 0x012d }
            com.google.android.gms.internal.measurement.zzlb r0 = com.google.android.gms.measurement.internal.zzku.zzl(r13, r0)     // Catch:{ IOException -> 0x012d }
            com.google.android.gms.internal.measurement.zzei r0 = (com.google.android.gms.internal.measurement.zzei) r0     // Catch:{ IOException -> 0x012d }
            com.google.android.gms.internal.measurement.zzjx r0 = r0.zzaA()     // Catch:{ IOException -> 0x012d }
            com.google.android.gms.internal.measurement.zzej r0 = (com.google.android.gms.internal.measurement.zzej) r0     // Catch:{ IOException -> 0x012d }
            boolean r13 = r0.zzo()     // Catch:{ SQLiteException -> 0x015d, all -> 0x015b }
            if (r13 != 0) goto L_0x010d
            goto L_0x013f
        L_0x010d:
            int r13 = r5.getInt(r12)     // Catch:{ SQLiteException -> 0x015d, all -> 0x015b }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ SQLiteException -> 0x015d, all -> 0x015b }
            java.lang.Object r16 = r4.get(r13)     // Catch:{ SQLiteException -> 0x015d, all -> 0x015b }
            java.util.List r16 = (java.util.List) r16     // Catch:{ SQLiteException -> 0x015d, all -> 0x015b }
            if (r16 != 0) goto L_0x0127
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x015d, all -> 0x015b }
            r12.<init>()     // Catch:{ SQLiteException -> 0x015d, all -> 0x015b }
            r4.put(r13, r12)     // Catch:{ SQLiteException -> 0x015d, all -> 0x015b }
            goto L_0x0129
        L_0x0127:
            r12 = r16
        L_0x0129:
            r12.add(r0)     // Catch:{ SQLiteException -> 0x015d, all -> 0x015b }
            goto L_0x013f
        L_0x012d:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfv r12 = r2.zzs     // Catch:{ SQLiteException -> 0x015d, all -> 0x015b }
            com.google.android.gms.measurement.internal.zzel r12 = r12.zzay()     // Catch:{ SQLiteException -> 0x015d, all -> 0x015b }
            com.google.android.gms.measurement.internal.zzej r12 = r12.zzd()     // Catch:{ SQLiteException -> 0x015d, all -> 0x015b }
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzel.zzn(r3)     // Catch:{ SQLiteException -> 0x015d, all -> 0x015b }
            r12.zzc(r9, r13, r0)     // Catch:{ SQLiteException -> 0x015d, all -> 0x015b }
        L_0x013f:
            boolean r0 = r5.moveToNext()     // Catch:{ SQLiteException -> 0x015d, all -> 0x015b }
            if (r0 != 0) goto L_0x014e
            if (r5 == 0) goto L_0x014b
            r5.close()
            goto L_0x014c
        L_0x014b:
        L_0x014c:
            r12 = r4
            goto L_0x0184
        L_0x014e:
            r12 = 0
            r13 = 1
            goto L_0x00f1
        L_0x0151:
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x015d, all -> 0x015b }
            if (r5 == 0) goto L_0x0183
        L_0x0157:
            r5.close()
            goto L_0x0183
        L_0x015b:
            r0 = move-exception
            goto L_0x017d
        L_0x015d:
            r0 = move-exception
            goto L_0x0164
        L_0x015f:
            r0 = move-exception
            r5 = 0
            goto L_0x017d
        L_0x0162:
            r0 = move-exception
            r5 = 0
        L_0x0164:
            com.google.android.gms.measurement.internal.zzfv r2 = r2.zzs     // Catch:{ all -> 0x017c }
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()     // Catch:{ all -> 0x017c }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()     // Catch:{ all -> 0x017c }
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzel.zzn(r3)     // Catch:{ all -> 0x017c }
            r2.zzc(r8, r3, r0)     // Catch:{ all -> 0x017c }
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ all -> 0x017c }
            if (r5 == 0) goto L_0x0183
            goto L_0x0157
        L_0x017c:
            r0 = move-exception
        L_0x017d:
            if (r5 == 0) goto L_0x0182
            r5.close()
        L_0x0182:
            throw r0
        L_0x0183:
            r12 = r0
        L_0x0184:
            com.google.android.gms.measurement.internal.zzks r0 = r10.zzf
            com.google.android.gms.measurement.internal.zzaj r2 = r0.zzi()
            java.lang.String r3 = r10.zza
            r2.zzY()
            r2.zzg()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)
            android.database.sqlite.SQLiteDatabase r16 = r2.zzh()
            java.lang.String[] r18 = new java.lang.String[]{r6, r11}     // Catch:{ SQLiteException -> 0x023c, all -> 0x0238 }
            r4 = 1
            java.lang.String[] r0 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x023c, all -> 0x0238 }
            r4 = 0
            r0[r4] = r3     // Catch:{ SQLiteException -> 0x023c, all -> 0x0238 }
            java.lang.String r17 = "audience_filter_values"
            java.lang.String r19 = "app_id=?"
            r21 = 0
            r22 = 0
            r23 = 0
            r20 = r0
            android.database.Cursor r4 = r16.query(r17, r18, r19, r20, r21, r22, r23)     // Catch:{ SQLiteException -> 0x023c, all -> 0x0238 }
            boolean r0 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x0232, all -> 0x022e }
            if (r0 != 0) goto L_0x01c9
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0232, all -> 0x022e }
            if (r4 == 0) goto L_0x01c2
            r4.close()
        L_0x01c2:
            r13 = r0
            r18 = r6
            r19 = r7
            goto L_0x025f
        L_0x01c9:
            androidx.collection.ArrayMap r5 = new androidx.collection.ArrayMap     // Catch:{ SQLiteException -> 0x0232, all -> 0x022e }
            r5.<init>()     // Catch:{ SQLiteException -> 0x0232, all -> 0x022e }
        L_0x01ce:
            r13 = 0
            int r16 = r4.getInt(r13)     // Catch:{ SQLiteException -> 0x0232, all -> 0x022e }
            r13 = 1
            byte[] r0 = r4.getBlob(r13)     // Catch:{ SQLiteException -> 0x0232, all -> 0x022e }
            com.google.android.gms.internal.measurement.zzgc r13 = com.google.android.gms.internal.measurement.zzgd.zzf()     // Catch:{ IOException -> 0x01f7 }
            com.google.android.gms.internal.measurement.zzlb r0 = com.google.android.gms.measurement.internal.zzku.zzl(r13, r0)     // Catch:{ IOException -> 0x01f7 }
            com.google.android.gms.internal.measurement.zzgc r0 = (com.google.android.gms.internal.measurement.zzgc) r0     // Catch:{ IOException -> 0x01f7 }
            com.google.android.gms.internal.measurement.zzjx r0 = r0.zzaA()     // Catch:{ IOException -> 0x01f7 }
            com.google.android.gms.internal.measurement.zzgd r0 = (com.google.android.gms.internal.measurement.zzgd) r0     // Catch:{ IOException -> 0x01f7 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r16)     // Catch:{ SQLiteException -> 0x0232, all -> 0x022e }
            r5.put(r13, r0)     // Catch:{ SQLiteException -> 0x0232, all -> 0x022e }
            r17 = r5
            r18 = r6
            r19 = r7
            goto L_0x0215
        L_0x01f7:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfv r13 = r2.zzs     // Catch:{ SQLiteException -> 0x0232, all -> 0x022e }
            com.google.android.gms.measurement.internal.zzel r13 = r13.zzay()     // Catch:{ SQLiteException -> 0x0232, all -> 0x022e }
            com.google.android.gms.measurement.internal.zzej r13 = r13.zzd()     // Catch:{ SQLiteException -> 0x0232, all -> 0x022e }
            r17 = r5
            java.lang.String r5 = "Failed to merge filter results. appId, audienceId, error"
            r18 = r6
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzel.zzn(r3)     // Catch:{ SQLiteException -> 0x022c, all -> 0x022e }
            r19 = r7
            java.lang.Integer r7 = java.lang.Integer.valueOf(r16)     // Catch:{ SQLiteException -> 0x022a, all -> 0x022e }
            r13.zzd(r5, r6, r7, r0)     // Catch:{ SQLiteException -> 0x022a, all -> 0x022e }
        L_0x0215:
            boolean r0 = r4.moveToNext()     // Catch:{ SQLiteException -> 0x022a, all -> 0x022e }
            if (r0 != 0) goto L_0x0223
            if (r4 == 0) goto L_0x0220
            r4.close()
        L_0x0220:
            r13 = r17
            goto L_0x025f
        L_0x0223:
            r5 = r17
            r6 = r18
            r7 = r19
            goto L_0x01ce
        L_0x022a:
            r0 = move-exception
            goto L_0x0242
        L_0x022c:
            r0 = move-exception
            goto L_0x0235
        L_0x022e:
            r0 = move-exception
            r5 = r4
            goto L_0x0b75
        L_0x0232:
            r0 = move-exception
            r18 = r6
        L_0x0235:
            r19 = r7
            goto L_0x0242
        L_0x0238:
            r0 = move-exception
            r5 = 0
            goto L_0x0b75
        L_0x023c:
            r0 = move-exception
            r18 = r6
            r19 = r7
            r4 = 0
        L_0x0242:
            com.google.android.gms.measurement.internal.zzfv r2 = r2.zzs     // Catch:{ all -> 0x0b73 }
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()     // Catch:{ all -> 0x0b73 }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()     // Catch:{ all -> 0x0b73 }
            java.lang.String r5 = "Database error querying filter results. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzel.zzn(r3)     // Catch:{ all -> 0x0b73 }
            r2.zzc(r5, r3, r0)     // Catch:{ all -> 0x0b73 }
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ all -> 0x0b73 }
            if (r4 == 0) goto L_0x025e
            r4.close()
        L_0x025e:
            r13 = r0
        L_0x025f:
            boolean r0 = r13.isEmpty()
            r7 = 2
            if (r0 == 0) goto L_0x0270
            r12 = r8
            r30 = r9
            r28 = r18
            r29 = r19
            r13 = 0
            goto L_0x05c8
        L_0x0270:
            java.util.HashSet r2 = new java.util.HashSet
            java.util.Set r0 = r13.keySet()
            r2.<init>(r0)
            if (r1 == 0) goto L_0x03fc
            java.lang.String r1 = r10.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r1)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r13)
            androidx.collection.ArrayMap r3 = new androidx.collection.ArrayMap
            r3.<init>()
            boolean r0 = r13.isEmpty()
            if (r0 == 0) goto L_0x0290
            goto L_0x03f3
        L_0x0290:
            com.google.android.gms.measurement.internal.zzks r0 = r10.zzf
            com.google.android.gms.measurement.internal.zzaj r4 = r0.zzi()
            r4.zzY()
            r4.zzg()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r1)
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r5 = r4.zzh()
            java.lang.String[] r6 = new java.lang.String[r7]     // Catch:{ SQLiteException -> 0x0304, all -> 0x0300 }
            r16 = 0
            r6[r16] = r1     // Catch:{ SQLiteException -> 0x0304, all -> 0x0300 }
            r16 = 1
            r6[r16] = r1     // Catch:{ SQLiteException -> 0x0304, all -> 0x0300 }
            java.lang.String r7 = "select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;"
            android.database.Cursor r5 = r5.rawQuery(r7, r6)     // Catch:{ SQLiteException -> 0x0304, all -> 0x0300 }
            boolean r6 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x02fe, all -> 0x02fb }
            if (r6 == 0) goto L_0x02f4
        L_0x02be:
            r6 = 0
            int r7 = r5.getInt(r6)     // Catch:{ SQLiteException -> 0x02fe, all -> 0x02fb }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r7)     // Catch:{ SQLiteException -> 0x02fe, all -> 0x02fb }
            java.lang.Object r7 = r0.get(r6)     // Catch:{ SQLiteException -> 0x02fe, all -> 0x02fb }
            java.util.List r7 = (java.util.List) r7     // Catch:{ SQLiteException -> 0x02fe, all -> 0x02fb }
            if (r7 != 0) goto L_0x02d9
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x02fe, all -> 0x02fb }
            r7.<init>()     // Catch:{ SQLiteException -> 0x02fe, all -> 0x02fb }
            r0.put(r6, r7)     // Catch:{ SQLiteException -> 0x02fe, all -> 0x02fb }
            goto L_0x02da
        L_0x02d9:
        L_0x02da:
            r6 = 1
            int r17 = r5.getInt(r6)     // Catch:{ SQLiteException -> 0x02fe, all -> 0x02fb }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r17)     // Catch:{ SQLiteException -> 0x02fe, all -> 0x02fb }
            r7.add(r6)     // Catch:{ SQLiteException -> 0x02fe, all -> 0x02fb }
            boolean r6 = r5.moveToNext()     // Catch:{ SQLiteException -> 0x02fe, all -> 0x02fb }
            if (r6 != 0) goto L_0x02be
            if (r5 == 0) goto L_0x02f3
        L_0x02ef:
            r5.close()
            goto L_0x0320
        L_0x02f3:
            goto L_0x0320
        L_0x02f4:
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x02fe, all -> 0x02fb }
            if (r5 == 0) goto L_0x0320
            goto L_0x02ef
        L_0x02fb:
            r0 = move-exception
            goto L_0x03f6
        L_0x02fe:
            r0 = move-exception
            goto L_0x0306
        L_0x0300:
            r0 = move-exception
            r5 = 0
            goto L_0x03f6
        L_0x0304:
            r0 = move-exception
            r5 = 0
        L_0x0306:
            com.google.android.gms.measurement.internal.zzfv r4 = r4.zzs     // Catch:{ all -> 0x03f5 }
            com.google.android.gms.measurement.internal.zzel r4 = r4.zzay()     // Catch:{ all -> 0x03f5 }
            com.google.android.gms.measurement.internal.zzej r4 = r4.zzd()     // Catch:{ all -> 0x03f5 }
            java.lang.String r6 = "Database error querying scoped filters. appId"
            java.lang.Object r1 = com.google.android.gms.measurement.internal.zzel.zzn(r1)     // Catch:{ all -> 0x03f5 }
            r4.zzc(r6, r1, r0)     // Catch:{ all -> 0x03f5 }
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ all -> 0x03f5 }
            if (r5 == 0) goto L_0x0320
            goto L_0x02ef
        L_0x0320:
            java.util.Set r1 = r13.keySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0328:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x03f2
            java.lang.Object r4 = r1.next()
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r4)
            java.lang.Object r6 = r13.get(r5)
            com.google.android.gms.internal.measurement.zzgd r6 = (com.google.android.gms.internal.measurement.zzgd) r6
            java.lang.Object r7 = r0.get(r5)
            java.util.List r7 = (java.util.List) r7
            if (r7 == 0) goto L_0x03e5
            boolean r17 = r7.isEmpty()
            if (r17 == 0) goto L_0x0356
            r17 = r0
            r20 = r1
            goto L_0x03e9
        L_0x0356:
            com.google.android.gms.measurement.internal.zzks r5 = r10.zzf
            com.google.android.gms.measurement.internal.zzku r5 = r5.zzu()
            r17 = r0
            java.util.List r0 = r6.zzk()
            java.util.List r0 = r5.zzr(r0, r7)
            boolean r5 = r0.isEmpty()
            if (r5 != 0) goto L_0x03df
            com.google.android.gms.internal.measurement.zzjt r5 = r6.zzbv()
            com.google.android.gms.internal.measurement.zzgc r5 = (com.google.android.gms.internal.measurement.zzgc) r5
            r5.zze()
            r5.zzb(r0)
            com.google.android.gms.measurement.internal.zzks r0 = r10.zzf
            com.google.android.gms.measurement.internal.zzku r0 = r0.zzu()
            r20 = r1
            java.util.List r1 = r6.zzn()
            java.util.List r0 = r0.zzr(r1, r7)
            r5.zzf()
            r5.zzd(r0)
            r0 = 0
        L_0x038f:
            int r1 = r6.zza()
            if (r0 >= r1) goto L_0x03ad
            com.google.android.gms.internal.measurement.zzfm r1 = r6.zze(r0)
            int r1 = r1.zza()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            boolean r1 = r7.contains(r1)
            if (r1 == 0) goto L_0x03aa
            r5.zzg(r0)
        L_0x03aa:
            int r0 = r0 + 1
            goto L_0x038f
        L_0x03ad:
            r0 = 0
        L_0x03ae:
            int r1 = r6.zzc()
            if (r0 >= r1) goto L_0x03cc
            com.google.android.gms.internal.measurement.zzgf r1 = r6.zzi(r0)
            int r1 = r1.zzb()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            boolean r1 = r7.contains(r1)
            if (r1 == 0) goto L_0x03c9
            r5.zzh(r0)
        L_0x03c9:
            int r0 = r0 + 1
            goto L_0x03ae
        L_0x03cc:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r4)
            com.google.android.gms.internal.measurement.zzjx r1 = r5.zzaA()
            com.google.android.gms.internal.measurement.zzgd r1 = (com.google.android.gms.internal.measurement.zzgd) r1
            r3.put(r0, r1)
            r0 = r17
            r1 = r20
            goto L_0x0328
        L_0x03df:
            r20 = r1
            r0 = r17
            goto L_0x0328
        L_0x03e5:
            r17 = r0
            r20 = r1
        L_0x03e9:
            r3.put(r5, r6)
            r0 = r17
            r1 = r20
            goto L_0x0328
        L_0x03f2:
        L_0x03f3:
            r0 = r3
            goto L_0x03fd
        L_0x03f5:
            r0 = move-exception
        L_0x03f6:
            if (r5 == 0) goto L_0x03fb
            r5.close()
        L_0x03fb:
            throw r0
        L_0x03fc:
            r0 = r13
        L_0x03fd:
            java.util.Iterator r17 = r2.iterator()
        L_0x0401:
            boolean r1 = r17.hasNext()
            if (r1 == 0) goto L_0x05c0
            java.lang.Object r1 = r17.next()
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r20 = r1.intValue()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r20)
            java.lang.Object r1 = r0.get(r1)
            com.google.android.gms.internal.measurement.zzgd r1 = (com.google.android.gms.internal.measurement.zzgd) r1
            java.util.BitSet r5 = new java.util.BitSet
            r5.<init>()
            java.util.BitSet r6 = new java.util.BitSet
            r6.<init>()
            androidx.collection.ArrayMap r7 = new androidx.collection.ArrayMap
            r7.<init>()
            if (r1 == 0) goto L_0x0469
            int r2 = r1.zza()
            if (r2 != 0) goto L_0x0433
            goto L_0x0469
        L_0x0433:
            java.util.List r2 = r1.zzj()
            java.util.Iterator r2 = r2.iterator()
        L_0x043b:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0469
            java.lang.Object r3 = r2.next()
            com.google.android.gms.internal.measurement.zzfm r3 = (com.google.android.gms.internal.measurement.zzfm) r3
            boolean r4 = r3.zzh()
            if (r4 == 0) goto L_0x043b
            int r4 = r3.zza()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            boolean r21 = r3.zzg()
            if (r21 == 0) goto L_0x0464
            long r21 = r3.zzb()
            java.lang.Long r3 = java.lang.Long.valueOf(r21)
            goto L_0x0465
        L_0x0464:
            r3 = 0
        L_0x0465:
            r7.put(r4, r3)
            goto L_0x043b
        L_0x0469:
            androidx.collection.ArrayMap r4 = new androidx.collection.ArrayMap
            r4.<init>()
            if (r1 == 0) goto L_0x04c3
            int r2 = r1.zzc()
            if (r2 != 0) goto L_0x0479
            r22 = r0
            goto L_0x04c5
        L_0x0479:
            java.util.List r2 = r1.zzm()
            java.util.Iterator r2 = r2.iterator()
        L_0x0481:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x04c0
            java.lang.Object r3 = r2.next()
            com.google.android.gms.internal.measurement.zzgf r3 = (com.google.android.gms.internal.measurement.zzgf) r3
            boolean r21 = r3.zzi()
            if (r21 == 0) goto L_0x04bb
            int r21 = r3.zza()
            if (r21 <= 0) goto L_0x04bb
            int r21 = r3.zzb()
            r22 = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r21)
            int r21 = r3.zza()
            r23 = r2
            int r2 = r21 + -1
            long r2 = r3.zzc(r2)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r4.put(r0, r2)
            r0 = r22
            r2 = r23
            goto L_0x0481
        L_0x04bb:
            r22 = r0
            r23 = r2
            goto L_0x0481
        L_0x04c0:
            r22 = r0
            goto L_0x04c5
        L_0x04c3:
            r22 = r0
        L_0x04c5:
            if (r1 == 0) goto L_0x051d
            r0 = 0
        L_0x04c8:
            int r2 = r1.zzd()
            int r2 = r2 * 64
            if (r0 >= r2) goto L_0x0518
            java.util.List r2 = r1.zzn()
            boolean r2 = com.google.android.gms.measurement.internal.zzku.zzw(r2, r0)
            if (r2 == 0) goto L_0x0506
            com.google.android.gms.measurement.internal.zzfv r2 = r10.zzs
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzj()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r20)
            r21 = r8
            java.lang.Integer r8 = java.lang.Integer.valueOf(r0)
            r23 = r9
            java.lang.String r9 = "Filter already evaluated. audience ID, filter ID"
            r2.zzc(r9, r3, r8)
            r6.set(r0)
            java.util.List r2 = r1.zzk()
            boolean r2 = com.google.android.gms.measurement.internal.zzku.zzw(r2, r0)
            if (r2 == 0) goto L_0x050a
            r5.set(r0)
            goto L_0x0511
        L_0x0506:
            r21 = r8
            r23 = r9
        L_0x050a:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            r7.remove(r2)
        L_0x0511:
            int r0 = r0 + 1
            r8 = r21
            r9 = r23
            goto L_0x04c8
        L_0x0518:
            r21 = r8
            r23 = r9
            goto L_0x0521
        L_0x051d:
            r21 = r8
            r23 = r9
        L_0x0521:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r20)
            java.lang.Object r1 = r13.get(r0)
            r8 = r1
            com.google.android.gms.internal.measurement.zzgd r8 = (com.google.android.gms.internal.measurement.zzgd) r8
            if (r15 == 0) goto L_0x058e
            if (r14 == 0) goto L_0x058e
            java.lang.Object r0 = r12.get(r0)
            java.util.List r0 = (java.util.List) r0
            if (r0 == 0) goto L_0x058e
            java.lang.Long r1 = r10.zze
            if (r1 == 0) goto L_0x058e
            java.lang.Long r1 = r10.zzd
            if (r1 != 0) goto L_0x0541
            goto L_0x058e
        L_0x0541:
            java.util.Iterator r0 = r0.iterator()
        L_0x0545:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x058e
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.measurement.zzej r1 = (com.google.android.gms.internal.measurement.zzej) r1
            int r2 = r1.zzb()
            java.lang.Long r3 = r10.zze
            long r24 = r3.longValue()
            r26 = 1000(0x3e8, double:4.94E-321)
            long r24 = r24 / r26
            boolean r1 = r1.zzm()
            if (r1 == 0) goto L_0x056e
            java.lang.Long r1 = r10.zzd
            long r24 = r1.longValue()
            long r24 = r24 / r26
            goto L_0x056f
        L_0x056e:
        L_0x056f:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            boolean r2 = r7.containsKey(r1)
            if (r2 == 0) goto L_0x0580
            java.lang.Long r2 = java.lang.Long.valueOf(r24)
            r7.put(r1, r2)
        L_0x0580:
            boolean r2 = r4.containsKey(r1)
            if (r2 == 0) goto L_0x0545
            java.lang.Long r2 = java.lang.Long.valueOf(r24)
            r4.put(r1, r2)
            goto L_0x0545
        L_0x058e:
            com.google.android.gms.measurement.internal.zzt r0 = new com.google.android.gms.measurement.internal.zzt
            java.lang.String r3 = r10.zza
            r9 = 0
            r1 = r0
            r2 = r65
            r24 = r4
            r4 = r8
            r8 = 0
            r28 = r18
            r29 = r19
            r16 = r12
            r66 = r13
            r12 = r21
            r13 = r8
            r8 = r24
            r30 = r23
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            java.util.Map<java.lang.Integer, com.google.android.gms.measurement.internal.zzt> r1 = r10.zzc
            java.lang.Integer r2 = java.lang.Integer.valueOf(r20)
            r1.put(r2, r0)
            r13 = r66
            r8 = r12
            r12 = r16
            r0 = r22
            r9 = r30
            goto L_0x0401
        L_0x05c0:
            r12 = r8
            r30 = r9
            r28 = r18
            r29 = r19
            r13 = 0
        L_0x05c8:
            boolean r0 = r67.isEmpty()
            java.lang.String r1 = "Skipping failed audience ID"
            if (r0 == 0) goto L_0x05d4
            r25 = r11
            goto L_0x0874
        L_0x05d4:
            com.google.android.gms.measurement.internal.zzv r2 = new com.google.android.gms.measurement.internal.zzv
            r2.<init>(r10, r13)
            androidx.collection.ArrayMap r3 = new androidx.collection.ArrayMap
            r3.<init>()
            java.util.Iterator r4 = r67.iterator()
        L_0x05e2:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0872
            java.lang.Object r0 = r4.next()
            com.google.android.gms.internal.measurement.zzfo r0 = (com.google.android.gms.internal.measurement.zzfo) r0
            java.lang.String r5 = r10.zza
            com.google.android.gms.internal.measurement.zzfo r5 = r2.zza(r5, r0)
            if (r5 == 0) goto L_0x0867
            com.google.android.gms.measurement.internal.zzks r6 = r10.zzf
            com.google.android.gms.measurement.internal.zzaj r6 = r6.zzi()
            java.lang.String r7 = r10.zza
            java.lang.String r8 = r5.zzh()
            java.lang.String r9 = r0.zzh()
            com.google.android.gms.measurement.internal.zzap r9 = r6.zzn(r7, r9)
            if (r9 != 0) goto L_0x064b
            com.google.android.gms.measurement.internal.zzfv r9 = r6.zzs
            com.google.android.gms.measurement.internal.zzel r9 = r9.zzay()
            com.google.android.gms.measurement.internal.zzej r9 = r9.zzk()
            java.lang.Object r14 = com.google.android.gms.measurement.internal.zzel.zzn(r7)
            com.google.android.gms.measurement.internal.zzfv r6 = r6.zzs
            com.google.android.gms.measurement.internal.zzeg r6 = r6.zzj()
            java.lang.String r6 = r6.zzd(r8)
            java.lang.String r8 = "Event aggregate wasn't created during raw event logging. appId, event"
            r9.zzc(r8, r14, r6)
            com.google.android.gms.measurement.internal.zzap r6 = new com.google.android.gms.measurement.internal.zzap
            r31 = r6
            java.lang.String r33 = r0.zzh()
            r34 = 1
            r36 = 1
            r38 = 1
            long r40 = r0.zzd()
            r42 = 0
            r44 = 0
            r45 = 0
            r46 = 0
            r47 = 0
            r32 = r7
            r31.<init>(r32, r33, r34, r36, r38, r40, r42, r44, r45, r46, r47)
            goto L_0x0680
        L_0x064b:
            com.google.android.gms.measurement.internal.zzap r6 = new com.google.android.gms.measurement.internal.zzap
            r48 = r6
            java.lang.String r0 = r9.zza
            r49 = r0
            java.lang.String r0 = r9.zzb
            r50 = r0
            long r7 = r9.zzc
            r14 = 1
            long r51 = r7 + r14
            long r7 = r9.zzd
            long r53 = r7 + r14
            long r7 = r9.zze
            long r55 = r7 + r14
            long r7 = r9.zzf
            r57 = r7
            long r7 = r9.zzg
            r59 = r7
            java.lang.Long r0 = r9.zzh
            r61 = r0
            java.lang.Long r0 = r9.zzi
            r62 = r0
            java.lang.Long r0 = r9.zzj
            r63 = r0
            java.lang.Boolean r0 = r9.zzk
            r64 = r0
            r48.<init>(r49, r50, r51, r53, r55, r57, r59, r61, r62, r63, r64)
        L_0x0680:
            com.google.android.gms.measurement.internal.zzks r0 = r10.zzf
            com.google.android.gms.measurement.internal.zzaj r0 = r0.zzi()
            r0.zzF(r6)
            long r7 = r6.zzc
            java.lang.String r9 = r5.zzh()
            java.lang.Object r0 = r3.get(r9)
            java.util.Map r0 = (java.util.Map) r0
            if (r0 != 0) goto L_0x07ad
            com.google.android.gms.measurement.internal.zzks r0 = r10.zzf
            com.google.android.gms.measurement.internal.zzaj r14 = r0.zzi()
            java.lang.String r15 = r10.zza
            r14.zzY()
            r14.zzg()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r15)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9)
            androidx.collection.ArrayMap r13 = new androidx.collection.ArrayMap
            r13.<init>()
            android.database.sqlite.SQLiteDatabase r16 = r14.zzh()
            r24 = r2
            r67 = r4
            r4 = r28
            r2 = r29
            java.lang.String[] r18 = new java.lang.String[]{r4, r2}     // Catch:{ SQLiteException -> 0x077f, all -> 0x077c }
            r25 = r11
            r11 = 2
            java.lang.String[] r0 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x0776, all -> 0x077c }
            r17 = 0
            r0[r17] = r15     // Catch:{ SQLiteException -> 0x0776, all -> 0x077c }
            r17 = 1
            r0[r17] = r9     // Catch:{ SQLiteException -> 0x0776, all -> 0x077c }
            java.lang.String r17 = "event_filters"
            java.lang.String r19 = "app_id=? AND event_name=?"
            r21 = 0
            r22 = 0
            r23 = 0
            r20 = r0
            android.database.Cursor r11 = r16.query(r17, r18, r19, r20, r21, r22, r23)     // Catch:{ SQLiteException -> 0x0776, all -> 0x077c }
            boolean r0 = r11.moveToFirst()     // Catch:{ SQLiteException -> 0x076e, all -> 0x076b }
            if (r0 == 0) goto L_0x0759
        L_0x06e3:
            r29 = r2
            r2 = 1
            byte[] r0 = r11.getBlob(r2)     // Catch:{ SQLiteException -> 0x0757, all -> 0x076b }
            com.google.android.gms.internal.measurement.zzei r2 = com.google.android.gms.internal.measurement.zzej.zzc()     // Catch:{ IOException -> 0x0723 }
            com.google.android.gms.internal.measurement.zzlb r0 = com.google.android.gms.measurement.internal.zzku.zzl(r2, r0)     // Catch:{ IOException -> 0x0723 }
            com.google.android.gms.internal.measurement.zzei r0 = (com.google.android.gms.internal.measurement.zzei) r0     // Catch:{ IOException -> 0x0723 }
            com.google.android.gms.internal.measurement.zzjx r0 = r0.zzaA()     // Catch:{ IOException -> 0x0723 }
            com.google.android.gms.internal.measurement.zzej r0 = (com.google.android.gms.internal.measurement.zzej) r0     // Catch:{ IOException -> 0x0723 }
            r2 = 0
            int r16 = r11.getInt(r2)     // Catch:{ SQLiteException -> 0x0757, all -> 0x076b }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r16)     // Catch:{ SQLiteException -> 0x0757, all -> 0x076b }
            java.lang.Object r16 = r13.get(r2)     // Catch:{ SQLiteException -> 0x0757, all -> 0x076b }
            java.util.List r16 = (java.util.List) r16     // Catch:{ SQLiteException -> 0x0757, all -> 0x076b }
            if (r16 != 0) goto L_0x0717
            r28 = r4
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0755, all -> 0x076b }
            r4.<init>()     // Catch:{ SQLiteException -> 0x0755, all -> 0x076b }
            r13.put(r2, r4)     // Catch:{ SQLiteException -> 0x0755, all -> 0x076b }
            goto L_0x071b
        L_0x0717:
            r28 = r4
            r4 = r16
        L_0x071b:
            r4.add(r0)     // Catch:{ SQLiteException -> 0x0755, all -> 0x076b }
            r16 = r13
            r13 = r30
            goto L_0x073b
        L_0x0723:
            r0 = move-exception
            r28 = r4
            com.google.android.gms.measurement.internal.zzfv r2 = r14.zzs     // Catch:{ SQLiteException -> 0x0755, all -> 0x076b }
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()     // Catch:{ SQLiteException -> 0x0755, all -> 0x076b }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()     // Catch:{ SQLiteException -> 0x0755, all -> 0x076b }
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzel.zzn(r15)     // Catch:{ SQLiteException -> 0x0755, all -> 0x076b }
            r16 = r13
            r13 = r30
            r2.zzc(r13, r4, r0)     // Catch:{ SQLiteException -> 0x0769, all -> 0x076b }
        L_0x073b:
            boolean r0 = r11.moveToNext()     // Catch:{ SQLiteException -> 0x0769, all -> 0x076b }
            if (r0 != 0) goto L_0x074c
            if (r11 == 0) goto L_0x0747
            r11.close()
            goto L_0x0748
        L_0x0747:
        L_0x0748:
            r0 = r16
            goto L_0x07a1
        L_0x074c:
            r30 = r13
            r13 = r16
            r4 = r28
            r2 = r29
            goto L_0x06e3
        L_0x0755:
            r0 = move-exception
            goto L_0x0773
        L_0x0757:
            r0 = move-exception
            goto L_0x0771
        L_0x0759:
            r29 = r2
            r28 = r4
            r13 = r30
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0769, all -> 0x076b }
            if (r11 == 0) goto L_0x07a1
        L_0x0765:
            r11.close()
            goto L_0x07a1
        L_0x0769:
            r0 = move-exception
            goto L_0x0789
        L_0x076b:
            r0 = move-exception
            r5 = r11
            goto L_0x07a7
        L_0x076e:
            r0 = move-exception
            r29 = r2
        L_0x0771:
            r28 = r4
        L_0x0773:
            r13 = r30
            goto L_0x0789
        L_0x0776:
            r0 = move-exception
            r29 = r2
            r28 = r4
            goto L_0x0786
        L_0x077c:
            r0 = move-exception
            r5 = 0
            goto L_0x07a7
        L_0x077f:
            r0 = move-exception
            r29 = r2
            r28 = r4
            r25 = r11
        L_0x0786:
            r13 = r30
            r11 = 0
        L_0x0789:
            com.google.android.gms.measurement.internal.zzfv r2 = r14.zzs     // Catch:{ all -> 0x07a5 }
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()     // Catch:{ all -> 0x07a5 }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()     // Catch:{ all -> 0x07a5 }
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzel.zzn(r15)     // Catch:{ all -> 0x07a5 }
            r2.zzc(r12, r4, r0)     // Catch:{ all -> 0x07a5 }
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ all -> 0x07a5 }
            if (r11 == 0) goto L_0x07a1
            goto L_0x0765
        L_0x07a1:
            r3.put(r9, r0)
            goto L_0x07b5
        L_0x07a5:
            r0 = move-exception
            r5 = r11
        L_0x07a7:
            if (r5 == 0) goto L_0x07ac
            r5.close()
        L_0x07ac:
            throw r0
        L_0x07ad:
            r24 = r2
            r67 = r4
            r25 = r11
            r13 = r30
        L_0x07b5:
            java.util.Set r2 = r0.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x07bd:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x085c
            java.lang.Object r4 = r2.next()
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            java.util.Set<java.lang.Integer> r9 = r10.zzb
            java.lang.Integer r11 = java.lang.Integer.valueOf(r4)
            boolean r9 = r9.contains(r11)
            if (r9 == 0) goto L_0x07e7
            com.google.android.gms.measurement.internal.zzfv r4 = r10.zzs
            com.google.android.gms.measurement.internal.zzel r4 = r4.zzay()
            com.google.android.gms.measurement.internal.zzej r4 = r4.zzj()
            r4.zzb(r1, r11)
            goto L_0x07bd
        L_0x07e7:
            java.lang.Object r9 = r0.get(r11)
            java.util.List r9 = (java.util.List) r9
            java.util.Iterator r9 = r9.iterator()
            r11 = 1
        L_0x07f2:
            boolean r14 = r9.hasNext()
            if (r14 == 0) goto L_0x0841
            java.lang.Object r11 = r9.next()
            com.google.android.gms.internal.measurement.zzej r11 = (com.google.android.gms.internal.measurement.zzej) r11
            com.google.android.gms.measurement.internal.zzw r15 = new com.google.android.gms.measurement.internal.zzw
            java.lang.String r14 = r10.zza
            r15.<init>(r10, r14, r4, r11)
            java.lang.Long r14 = r10.zzd
            r22 = r0
            java.lang.Long r0 = r10.zze
            int r11 = r11.zzb()
            boolean r21 = r10.zzf(r4, r11)
            r11 = r14
            r14 = r15
            r23 = r2
            r2 = r15
            r15 = r11
            r16 = r0
            r17 = r5
            r18 = r7
            r20 = r6
            boolean r11 = r14.zzd(r15, r16, r17, r18, r20, r21)
            if (r11 == 0) goto L_0x0837
            java.lang.Integer r0 = java.lang.Integer.valueOf(r4)
            com.google.android.gms.measurement.internal.zzt r0 = r10.zzd(r0)
            r0.zzc(r2)
            r0 = r22
            r2 = r23
            goto L_0x07f2
        L_0x0837:
            java.util.Set<java.lang.Integer> r0 = r10.zzb
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            r0.add(r2)
            goto L_0x0845
        L_0x0841:
            r22 = r0
            r23 = r2
        L_0x0845:
            if (r11 != 0) goto L_0x0856
            java.util.Set<java.lang.Integer> r0 = r10.zzb
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            r0.add(r2)
            r0 = r22
            r2 = r23
            goto L_0x07bd
        L_0x0856:
            r0 = r22
            r2 = r23
            goto L_0x07bd
        L_0x085c:
            r4 = r67
            r30 = r13
            r2 = r24
            r11 = r25
            r13 = 0
            goto L_0x05e2
        L_0x0867:
            r24 = r2
            r67 = r4
            r25 = r11
            r13 = r30
            r13 = 0
            goto L_0x05e2
        L_0x0872:
            r25 = r11
        L_0x0874:
            boolean r0 = r68.isEmpty()
            if (r0 == 0) goto L_0x087e
            r11 = r28
            goto L_0x0ac4
        L_0x087e:
            androidx.collection.ArrayMap r2 = new androidx.collection.ArrayMap
            r2.<init>()
            java.util.Iterator r3 = r68.iterator()
        L_0x0887:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0ac2
            java.lang.Object r0 = r3.next()
            r4 = r0
            com.google.android.gms.internal.measurement.zzgh r4 = (com.google.android.gms.internal.measurement.zzgh) r4
            java.lang.String r5 = r4.zzf()
            java.lang.Object r0 = r2.get(r5)
            java.util.Map r0 = (java.util.Map) r0
            if (r0 != 0) goto L_0x0984
            com.google.android.gms.measurement.internal.zzks r0 = r10.zzf
            com.google.android.gms.measurement.internal.zzaj r6 = r0.zzi()
            java.lang.String r7 = r10.zza
            r6.zzY()
            r6.zzg()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r7)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5)
            androidx.collection.ArrayMap r8 = new androidx.collection.ArrayMap
            r8.<init>()
            android.database.sqlite.SQLiteDatabase r13 = r6.zzh()
            r11 = r28
            r9 = r29
            java.lang.String[] r15 = new java.lang.String[]{r11, r9}     // Catch:{ SQLiteException -> 0x095c, all -> 0x0959 }
            r14 = 2
            java.lang.String[] r0 = new java.lang.String[r14]     // Catch:{ SQLiteException -> 0x095c, all -> 0x0959 }
            r14 = 0
            r0[r14] = r7     // Catch:{ SQLiteException -> 0x095c, all -> 0x0959 }
            r14 = 1
            r0[r14] = r5     // Catch:{ SQLiteException -> 0x095c, all -> 0x0959 }
            java.lang.String r14 = "property_filters"
            java.lang.String r16 = "app_id=? AND property_name=?"
            r18 = 0
            r19 = 0
            r20 = 0
            r17 = r0
            android.database.Cursor r13 = r13.query(r14, r15, r16, r17, r18, r19, r20)     // Catch:{ SQLiteException -> 0x095c, all -> 0x0959 }
            boolean r0 = r13.moveToFirst()     // Catch:{ SQLiteException -> 0x0955, all -> 0x0952 }
            if (r0 == 0) goto L_0x0944
        L_0x08e4:
            r14 = 1
            byte[] r0 = r13.getBlob(r14)     // Catch:{ SQLiteException -> 0x0955, all -> 0x0952 }
            com.google.android.gms.internal.measurement.zzer r15 = com.google.android.gms.internal.measurement.zzes.zzc()     // Catch:{ IOException -> 0x091c }
            com.google.android.gms.internal.measurement.zzlb r0 = com.google.android.gms.measurement.internal.zzku.zzl(r15, r0)     // Catch:{ IOException -> 0x091c }
            com.google.android.gms.internal.measurement.zzer r0 = (com.google.android.gms.internal.measurement.zzer) r0     // Catch:{ IOException -> 0x091c }
            com.google.android.gms.internal.measurement.zzjx r0 = r0.zzaA()     // Catch:{ IOException -> 0x091c }
            com.google.android.gms.internal.measurement.zzes r0 = (com.google.android.gms.internal.measurement.zzes) r0     // Catch:{ IOException -> 0x091c }
            r15 = 0
            int r16 = r13.getInt(r15)     // Catch:{ SQLiteException -> 0x0955, all -> 0x0952 }
            java.lang.Integer r14 = java.lang.Integer.valueOf(r16)     // Catch:{ SQLiteException -> 0x0955, all -> 0x0952 }
            java.lang.Object r16 = r8.get(r14)     // Catch:{ SQLiteException -> 0x0955, all -> 0x0952 }
            java.util.List r16 = (java.util.List) r16     // Catch:{ SQLiteException -> 0x0955, all -> 0x0952 }
            if (r16 != 0) goto L_0x0914
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0955, all -> 0x0952 }
            r15.<init>()     // Catch:{ SQLiteException -> 0x0955, all -> 0x0952 }
            r8.put(r14, r15)     // Catch:{ SQLiteException -> 0x0955, all -> 0x0952 }
            goto L_0x0916
        L_0x0914:
            r15 = r16
        L_0x0916:
            r15.add(r0)     // Catch:{ SQLiteException -> 0x0955, all -> 0x0952 }
            r67 = r3
            goto L_0x0932
        L_0x091c:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfv r14 = r6.zzs     // Catch:{ SQLiteException -> 0x0955, all -> 0x0952 }
            com.google.android.gms.measurement.internal.zzel r14 = r14.zzay()     // Catch:{ SQLiteException -> 0x0955, all -> 0x0952 }
            com.google.android.gms.measurement.internal.zzej r14 = r14.zzd()     // Catch:{ SQLiteException -> 0x0955, all -> 0x0952 }
            java.lang.String r15 = "Failed to merge filter"
            r67 = r3
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzel.zzn(r7)     // Catch:{ SQLiteException -> 0x0950, all -> 0x0952 }
            r14.zzc(r15, r3, r0)     // Catch:{ SQLiteException -> 0x0950, all -> 0x0952 }
        L_0x0932:
            boolean r0 = r13.moveToNext()     // Catch:{ SQLiteException -> 0x0950, all -> 0x0952 }
            if (r0 != 0) goto L_0x0941
            if (r13 == 0) goto L_0x093e
            r13.close()
            goto L_0x093f
        L_0x093e:
        L_0x093f:
            r0 = r8
            goto L_0x0978
        L_0x0941:
            r3 = r67
            goto L_0x08e4
        L_0x0944:
            r67 = r3
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0950, all -> 0x0952 }
            if (r13 == 0) goto L_0x0978
        L_0x094c:
            r13.close()
            goto L_0x0978
        L_0x0950:
            r0 = move-exception
            goto L_0x0960
        L_0x0952:
            r0 = move-exception
            r5 = r13
            goto L_0x097e
        L_0x0955:
            r0 = move-exception
            r67 = r3
            goto L_0x0960
        L_0x0959:
            r0 = move-exception
            r5 = 0
            goto L_0x097e
        L_0x095c:
            r0 = move-exception
            r67 = r3
            r13 = 0
        L_0x0960:
            com.google.android.gms.measurement.internal.zzfv r3 = r6.zzs     // Catch:{ all -> 0x097c }
            com.google.android.gms.measurement.internal.zzel r3 = r3.zzay()     // Catch:{ all -> 0x097c }
            com.google.android.gms.measurement.internal.zzej r3 = r3.zzd()     // Catch:{ all -> 0x097c }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzel.zzn(r7)     // Catch:{ all -> 0x097c }
            r3.zzc(r12, r6, r0)     // Catch:{ all -> 0x097c }
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ all -> 0x097c }
            if (r13 == 0) goto L_0x0978
            goto L_0x094c
        L_0x0978:
            r2.put(r5, r0)
            goto L_0x098a
        L_0x097c:
            r0 = move-exception
            r5 = r13
        L_0x097e:
            if (r5 == 0) goto L_0x0983
            r5.close()
        L_0x0983:
            throw r0
        L_0x0984:
            r67 = r3
            r11 = r28
            r9 = r29
        L_0x098a:
            java.util.Set r3 = r0.keySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x0992:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x0aba
            java.lang.Object r5 = r3.next()
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            java.util.Set<java.lang.Integer> r6 = r10.zzb
            java.lang.Integer r7 = java.lang.Integer.valueOf(r5)
            boolean r6 = r6.contains(r7)
            if (r6 == 0) goto L_0x09c3
            com.google.android.gms.measurement.internal.zzfv r0 = r10.zzs
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzj()
            r0.zzb(r1, r7)
            r3 = r67
            r29 = r9
            r28 = r11
            goto L_0x0887
        L_0x09c3:
            java.lang.Object r6 = r0.get(r7)
            java.util.List r6 = (java.util.List) r6
            java.util.Iterator r6 = r6.iterator()
            r7 = 1
        L_0x09ce:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto L_0x0aa5
            java.lang.Object r7 = r6.next()
            com.google.android.gms.internal.measurement.zzes r7 = (com.google.android.gms.internal.measurement.zzes) r7
            com.google.android.gms.measurement.internal.zzfv r8 = r10.zzs
            com.google.android.gms.measurement.internal.zzel r8 = r8.zzay()
            java.lang.String r8 = r8.zzq()
            r13 = 2
            boolean r8 = android.util.Log.isLoggable(r8, r13)
            if (r8 == 0) goto L_0x0a38
            com.google.android.gms.measurement.internal.zzfv r8 = r10.zzs
            com.google.android.gms.measurement.internal.zzel r8 = r8.zzay()
            com.google.android.gms.measurement.internal.zzej r8 = r8.zzj()
            java.lang.Integer r14 = java.lang.Integer.valueOf(r5)
            boolean r15 = r7.zzj()
            if (r15 == 0) goto L_0x0a08
            int r15 = r7.zza()
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)
            goto L_0x0a09
        L_0x0a08:
            r15 = 0
        L_0x0a09:
            com.google.android.gms.measurement.internal.zzfv r13 = r10.zzs
            com.google.android.gms.measurement.internal.zzeg r13 = r13.zzj()
            r68 = r0
            java.lang.String r0 = r7.zze()
            java.lang.String r0 = r13.zzf(r0)
            java.lang.String r13 = "Evaluating filter. audience, filter, property"
            r8.zzd(r13, r14, r15, r0)
            com.google.android.gms.measurement.internal.zzfv r0 = r10.zzs
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzj()
            com.google.android.gms.measurement.internal.zzks r8 = r10.zzf
            com.google.android.gms.measurement.internal.zzku r8 = r8.zzu()
            java.lang.String r8 = r8.zzp(r7)
            java.lang.String r13 = "Filter definition"
            r0.zzb(r13, r8)
            goto L_0x0a3a
        L_0x0a38:
            r68 = r0
        L_0x0a3a:
            boolean r0 = r7.zzj()
            if (r0 == 0) goto L_0x0a7b
            int r0 = r7.zza()
            r8 = 256(0x100, float:3.59E-43)
            if (r0 <= r8) goto L_0x0a49
            goto L_0x0a7b
        L_0x0a49:
            com.google.android.gms.measurement.internal.zzy r0 = new com.google.android.gms.measurement.internal.zzy
            java.lang.String r8 = r10.zza
            r0.<init>(r10, r8, r5, r7)
            java.lang.Long r8 = r10.zzd
            java.lang.Long r13 = r10.zze
            int r7 = r7.zza()
            boolean r7 = r10.zzf(r5, r7)
            boolean r7 = r0.zzd(r8, r13, r4, r7)
            if (r7 == 0) goto L_0x0a71
            java.lang.Integer r8 = java.lang.Integer.valueOf(r5)
            com.google.android.gms.measurement.internal.zzt r8 = r10.zzd(r8)
            r8.zzc(r0)
            r0 = r68
            goto L_0x09ce
        L_0x0a71:
            java.util.Set<java.lang.Integer> r0 = r10.zzb
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)
            r0.add(r6)
            goto L_0x0aa7
        L_0x0a7b:
            com.google.android.gms.measurement.internal.zzfv r0 = r10.zzs
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzk()
            java.lang.String r6 = r10.zza
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzel.zzn(r6)
            boolean r8 = r7.zzj()
            if (r8 == 0) goto L_0x0a9a
            int r7 = r7.zza()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            goto L_0x0a9b
        L_0x0a9a:
            r7 = 0
        L_0x0a9b:
            java.lang.String r7 = java.lang.String.valueOf(r7)
            java.lang.String r8 = "Invalid property filter ID. appId, id"
            r0.zzc(r8, r6, r7)
            goto L_0x0aa9
        L_0x0aa5:
            r68 = r0
        L_0x0aa7:
            if (r7 != 0) goto L_0x0ab6
        L_0x0aa9:
            java.util.Set<java.lang.Integer> r0 = r10.zzb
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r0.add(r5)
            r0 = r68
            goto L_0x0992
        L_0x0ab6:
            r0 = r68
            goto L_0x0992
        L_0x0aba:
            r3 = r67
            r29 = r9
            r28 = r11
            goto L_0x0887
        L_0x0ac2:
            r11 = r28
        L_0x0ac4:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Map<java.lang.Integer, com.google.android.gms.measurement.internal.zzt> r0 = r10.zzc
            java.util.Set r0 = r0.keySet()
            java.util.Set<java.lang.Integer> r2 = r10.zzb
            r0.removeAll(r2)
            java.util.Iterator r2 = r0.iterator()
        L_0x0ad8:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0b72
            java.lang.Object r0 = r2.next()
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            java.util.Map<java.lang.Integer, com.google.android.gms.measurement.internal.zzt> r3 = r10.zzc
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)
            java.lang.Object r3 = r3.get(r4)
            com.google.android.gms.measurement.internal.zzt r3 = (com.google.android.gms.measurement.internal.zzt) r3
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)
            com.google.android.gms.internal.measurement.zzfk r0 = r3.zza(r0)
            r1.add(r0)
            com.google.android.gms.measurement.internal.zzks r3 = r10.zzf
            com.google.android.gms.measurement.internal.zzaj r3 = r3.zzi()
            java.lang.String r5 = r10.zza
            com.google.android.gms.internal.measurement.zzgd r0 = r0.zzd()
            r3.zzY()
            r3.zzg()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)
            byte[] r0 = r0.zzbs()
            android.content.ContentValues r6 = new android.content.ContentValues
            r6.<init>()
            java.lang.String r7 = "app_id"
            r6.put(r7, r5)
            r6.put(r11, r4)
            r4 = r25
            r6.put(r4, r0)
            android.database.sqlite.SQLiteDatabase r0 = r3.zzh()     // Catch:{ SQLiteException -> 0x0b59 }
            java.lang.String r7 = "audience_filter_values"
            r8 = 5
            r9 = 0
            long r6 = r0.insertWithOnConflict(r7, r9, r6, r8)     // Catch:{ SQLiteException -> 0x0b57 }
            r12 = -1
            int r0 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r0 != 0) goto L_0x0b54
            com.google.android.gms.measurement.internal.zzfv r0 = r3.zzs     // Catch:{ SQLiteException -> 0x0b57 }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ SQLiteException -> 0x0b57 }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzd()     // Catch:{ SQLiteException -> 0x0b57 }
            java.lang.String r6 = "Failed to insert filter results (got -1). appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzel.zzn(r5)     // Catch:{ SQLiteException -> 0x0b57 }
            r0.zzb(r6, r7)     // Catch:{ SQLiteException -> 0x0b57 }
            r25 = r4
            goto L_0x0ad8
        L_0x0b54:
            r25 = r4
            goto L_0x0ad8
        L_0x0b57:
            r0 = move-exception
            goto L_0x0b5b
        L_0x0b59:
            r0 = move-exception
            r9 = 0
        L_0x0b5b:
            com.google.android.gms.measurement.internal.zzfv r3 = r3.zzs
            com.google.android.gms.measurement.internal.zzel r3 = r3.zzay()
            com.google.android.gms.measurement.internal.zzej r3 = r3.zzd()
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzel.zzn(r5)
            java.lang.String r6 = "Error storing filter results. appId"
            r3.zzc(r6, r5, r0)
            r25 = r4
            goto L_0x0ad8
        L_0x0b72:
            return r1
        L_0x0b73:
            r0 = move-exception
            r5 = r4
        L_0x0b75:
            if (r5 == 0) goto L_0x0b7a
            r5.close()
        L_0x0b7a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzz.zza(java.lang.String, java.util.List, java.util.List, java.lang.Long, java.lang.Long):java.util.List");
    }

    /* access modifiers changed from: protected */
    public final boolean zzb() {
        return false;
    }
}
