package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzfo;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzv {
    final /* synthetic */ zzz zza;
    private zzfo zzb;
    private Long zzc;
    private long zzd;

    /* synthetic */ zzv(zzz zzz, zzu zzu) {
        this.zza = zzz;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: java.lang.String} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00e9, code lost:
        if (r14 == null) goto L_0x010e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0107, code lost:
        if (r14 != null) goto L_0x0109;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0109, code lost:
        r14.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x010e, code lost:
        r0 = null;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01f3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzfo zza(java.lang.String r18, com.google.android.gms.internal.measurement.zzfo r19) {
        /*
            r17 = this;
            r1 = r17
            r3 = r18
            r8 = r19
            java.lang.String r0 = r19.zzh()
            java.util.List r9 = r19.zzi()
            com.google.android.gms.measurement.internal.zzz r2 = r1.zza
            com.google.android.gms.measurement.internal.zzks r2 = r2.zzf
            r2.zzu()
            java.lang.String r2 = "_eid"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzku.zzD(r8, r2)
            java.lang.Long r4 = (java.lang.Long) r4
            if (r4 == 0) goto L_0x0245
            java.lang.String r5 = "_ep"
            boolean r5 = r0.equals(r5)
            r6 = 0
            if (r5 == 0) goto L_0x01f7
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)
            com.google.android.gms.measurement.internal.zzz r0 = r1.zza
            com.google.android.gms.measurement.internal.zzks r0 = r0.zzf
            r0.zzu()
            java.lang.String r0 = "_en"
            java.lang.Object r0 = com.google.android.gms.measurement.internal.zzku.zzD(r8, r0)
            r10 = r0
            java.lang.String r10 = (java.lang.String) r10
            boolean r0 = android.text.TextUtils.isEmpty(r10)
            r5 = 0
            if (r0 == 0) goto L_0x0055
            com.google.android.gms.measurement.internal.zzz r0 = r1.zza
            com.google.android.gms.measurement.internal.zzfv r0 = r0.zzs
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzh()
            java.lang.String r2 = "Extra parameter without an event name. eventId"
            r0.zzb(r2, r4)
            return r5
        L_0x0055:
            com.google.android.gms.internal.measurement.zzfo r0 = r1.zzb
            r11 = 1
            r12 = 0
            if (r0 == 0) goto L_0x006d
            java.lang.Long r0 = r1.zzc
            if (r0 == 0) goto L_0x006d
            long r13 = r4.longValue()
            java.lang.Long r0 = r1.zzc
            long r15 = r0.longValue()
            int r0 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0138
        L_0x006d:
            com.google.android.gms.measurement.internal.zzz r0 = r1.zza
            com.google.android.gms.measurement.internal.zzks r0 = r0.zzf
            com.google.android.gms.measurement.internal.zzaj r13 = r0.zzi()
            r13.zzg()
            r13.zzY()
            android.database.sqlite.SQLiteDatabase r0 = r13.zzh()     // Catch:{ SQLiteException -> 0x00f6, all -> 0x00f2 }
            r14 = 2
            java.lang.String[] r14 = new java.lang.String[r14]     // Catch:{ SQLiteException -> 0x00f6, all -> 0x00f2 }
            r14[r12] = r3     // Catch:{ SQLiteException -> 0x00f6, all -> 0x00f2 }
            java.lang.String r15 = r4.toString()     // Catch:{ SQLiteException -> 0x00f6, all -> 0x00f2 }
            r14[r11] = r15     // Catch:{ SQLiteException -> 0x00f6, all -> 0x00f2 }
            java.lang.String r15 = "select main_event, children_to_process from main_event_params where app_id=? and event_id=?"
            android.database.Cursor r14 = r0.rawQuery(r15, r14)     // Catch:{ SQLiteException -> 0x00f6, all -> 0x00f2 }
            boolean r0 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            if (r0 != 0) goto L_0x00ae
            com.google.android.gms.measurement.internal.zzfv r0 = r13.zzs     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzj()     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            java.lang.String r15 = "Main event not found"
            r0.zza(r15)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            if (r14 == 0) goto L_0x00ac
            r14.close()
            r0 = r5
            goto L_0x010f
        L_0x00ac:
            r0 = r5
            goto L_0x010f
        L_0x00ae:
            byte[] r0 = r14.getBlob(r12)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            long r15 = r14.getLong(r11)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            java.lang.Long r15 = java.lang.Long.valueOf(r15)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            com.google.android.gms.internal.measurement.zzfn r5 = com.google.android.gms.internal.measurement.zzfo.zze()     // Catch:{ IOException -> 0x00d5 }
            com.google.android.gms.internal.measurement.zzlb r0 = com.google.android.gms.measurement.internal.zzku.zzl(r5, r0)     // Catch:{ IOException -> 0x00d5 }
            com.google.android.gms.internal.measurement.zzfn r0 = (com.google.android.gms.internal.measurement.zzfn) r0     // Catch:{ IOException -> 0x00d5 }
            com.google.android.gms.internal.measurement.zzjx r0 = r0.zzaA()     // Catch:{ IOException -> 0x00d5 }
            com.google.android.gms.internal.measurement.zzfo r0 = (com.google.android.gms.internal.measurement.zzfo) r0     // Catch:{ IOException -> 0x00d5 }
            android.util.Pair r0 = android.util.Pair.create(r0, r15)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            if (r14 == 0) goto L_0x010f
            r14.close()
            goto L_0x010f
        L_0x00d5:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfv r5 = r13.zzs     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            com.google.android.gms.measurement.internal.zzel r5 = r5.zzay()     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            com.google.android.gms.measurement.internal.zzej r5 = r5.zzd()     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            java.lang.String r15 = "Failed to merge main event. appId, eventId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzel.zzn(r18)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            r5.zzd(r15, r12, r4, r0)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            if (r14 == 0) goto L_0x010d
            goto L_0x0109
        L_0x00ec:
            r0 = move-exception
            r5 = r14
            goto L_0x01f1
        L_0x00f0:
            r0 = move-exception
            goto L_0x00f8
        L_0x00f2:
            r0 = move-exception
            r5 = 0
            goto L_0x01f1
        L_0x00f6:
            r0 = move-exception
            r14 = 0
        L_0x00f8:
            com.google.android.gms.measurement.internal.zzfv r5 = r13.zzs     // Catch:{ all -> 0x01ef }
            com.google.android.gms.measurement.internal.zzel r5 = r5.zzay()     // Catch:{ all -> 0x01ef }
            com.google.android.gms.measurement.internal.zzej r5 = r5.zzd()     // Catch:{ all -> 0x01ef }
            java.lang.String r12 = "Error selecting main event"
            r5.zzb(r12, r0)     // Catch:{ all -> 0x01ef }
            if (r14 == 0) goto L_0x010d
        L_0x0109:
            r14.close()
            goto L_0x010e
        L_0x010d:
        L_0x010e:
            r0 = 0
        L_0x010f:
            if (r0 == 0) goto L_0x01dc
            java.lang.Object r5 = r0.first
            if (r5 != 0) goto L_0x0117
            goto L_0x01dc
        L_0x0117:
            java.lang.Object r5 = r0.first
            com.google.android.gms.internal.measurement.zzfo r5 = (com.google.android.gms.internal.measurement.zzfo) r5
            r1.zzb = r5
            java.lang.Object r0 = r0.second
            java.lang.Long r0 = (java.lang.Long) r0
            long r12 = r0.longValue()
            r1.zzd = r12
            com.google.android.gms.measurement.internal.zzz r0 = r1.zza
            com.google.android.gms.measurement.internal.zzks r0 = r0.zzf
            r0.zzu()
            com.google.android.gms.internal.measurement.zzfo r0 = r1.zzb
            java.lang.Object r0 = com.google.android.gms.measurement.internal.zzku.zzD(r0, r2)
            java.lang.Long r0 = (java.lang.Long) r0
            r1.zzc = r0
        L_0x0138:
            long r12 = r1.zzd
            r14 = -1
            long r12 = r12 + r14
            r1.zzd = r12
            int r0 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r0 > 0) goto L_0x017d
            com.google.android.gms.measurement.internal.zzz r0 = r1.zza
            com.google.android.gms.measurement.internal.zzks r0 = r0.zzf
            com.google.android.gms.measurement.internal.zzaj r2 = r0.zzi()
            r2.zzg()
            com.google.android.gms.measurement.internal.zzfv r0 = r2.zzs
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzj()
            java.lang.String r4 = "Clearing complex main event info. appId"
            r0.zzb(r4, r3)
            android.database.sqlite.SQLiteDatabase r0 = r2.zzh()     // Catch:{ SQLiteException -> 0x016c }
            java.lang.String[] r4 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x016c }
            r5 = 0
            r4[r5] = r3     // Catch:{ SQLiteException -> 0x016c }
            java.lang.String r3 = "delete from main_event_params where app_id=?"
            r0.execSQL(r3, r4)     // Catch:{ SQLiteException -> 0x016c }
            goto L_0x018e
        L_0x016c:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfv r2 = r2.zzs
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()
            java.lang.String r3 = "Error clearing complex main event"
            r2.zzb(r3, r0)
            goto L_0x018e
        L_0x017d:
            com.google.android.gms.measurement.internal.zzz r0 = r1.zza
            com.google.android.gms.measurement.internal.zzks r0 = r0.zzf
            com.google.android.gms.measurement.internal.zzaj r2 = r0.zzi()
            long r5 = r1.zzd
            com.google.android.gms.internal.measurement.zzfo r7 = r1.zzb
            r3 = r18
            r2.zzL(r3, r4, r5, r7)
        L_0x018e:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.google.android.gms.internal.measurement.zzfo r2 = r1.zzb
            java.util.List r2 = r2.zzi()
            java.util.Iterator r2 = r2.iterator()
        L_0x019d:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x01be
            java.lang.Object r3 = r2.next()
            com.google.android.gms.internal.measurement.zzfs r3 = (com.google.android.gms.internal.measurement.zzfs) r3
            com.google.android.gms.measurement.internal.zzz r4 = r1.zza
            com.google.android.gms.measurement.internal.zzks r4 = r4.zzf
            r4.zzu()
            java.lang.String r4 = r3.zzg()
            com.google.android.gms.internal.measurement.zzfs r4 = com.google.android.gms.measurement.internal.zzku.zzC(r8, r4)
            if (r4 != 0) goto L_0x019d
            r0.add(r3)
            goto L_0x019d
        L_0x01be:
            boolean r2 = r0.isEmpty()
            if (r2 != 0) goto L_0x01c9
            r0.addAll(r9)
            r9 = r0
            goto L_0x01da
        L_0x01c9:
            com.google.android.gms.measurement.internal.zzz r0 = r1.zza
            com.google.android.gms.measurement.internal.zzfv r0 = r0.zzs
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzh()
            java.lang.String r2 = "No unique parameters in main event. eventName"
            r0.zzb(r2, r10)
        L_0x01da:
            r0 = r10
            goto L_0x0246
        L_0x01dc:
            com.google.android.gms.measurement.internal.zzz r0 = r1.zza
            com.google.android.gms.measurement.internal.zzfv r0 = r0.zzs
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzh()
            java.lang.String r2 = "Extra parameter without existing main event. eventName, eventId"
            r0.zzc(r2, r10, r4)
            r2 = 0
            return r2
        L_0x01ef:
            r0 = move-exception
            r5 = r14
        L_0x01f1:
            if (r5 == 0) goto L_0x01f6
            r5.close()
        L_0x01f6:
            throw r0
        L_0x01f7:
            r1.zzc = r4
            r1.zzb = r8
            com.google.android.gms.measurement.internal.zzz r2 = r1.zza
            com.google.android.gms.measurement.internal.zzks r2 = r2.zzf
            r2.zzu()
            java.lang.Long r2 = java.lang.Long.valueOf(r6)
            java.lang.String r5 = "_epc"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzku.zzD(r8, r5)
            if (r5 == 0) goto L_0x020f
            r2 = r5
        L_0x020f:
            java.lang.Long r2 = (java.lang.Long) r2
            long r10 = r2.longValue()
            r1.zzd = r10
            int r2 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r2 > 0) goto L_0x022d
            com.google.android.gms.measurement.internal.zzz r2 = r1.zza
            com.google.android.gms.measurement.internal.zzfv r2 = r2.zzs
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzh()
            java.lang.String r3 = "Complex event with zero extra param count. eventName"
            r2.zzb(r3, r0)
            goto L_0x0246
        L_0x022d:
            com.google.android.gms.measurement.internal.zzz r2 = r1.zza
            com.google.android.gms.measurement.internal.zzks r2 = r2.zzf
            com.google.android.gms.measurement.internal.zzaj r2 = r2.zzi()
            java.lang.Object r4 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)
            java.lang.Long r4 = (java.lang.Long) r4
            long r5 = r1.zzd
            r3 = r18
            r7 = r19
            r2.zzL(r3, r4, r5, r7)
            goto L_0x0246
        L_0x0245:
        L_0x0246:
            com.google.android.gms.internal.measurement.zzjt r2 = r19.zzbv()
            com.google.android.gms.internal.measurement.zzfn r2 = (com.google.android.gms.internal.measurement.zzfn) r2
            r2.zzi(r0)
            r2.zzg()
            r2.zzd(r9)
            com.google.android.gms.internal.measurement.zzjx r0 = r2.zzaA()
            com.google.android.gms.internal.measurement.zzfo r0 = (com.google.android.gms.internal.measurement.zzfo) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzv.zza(java.lang.String, com.google.android.gms.internal.measurement.zzfo):com.google.android.gms.internal.measurement.zzfo");
    }
}
