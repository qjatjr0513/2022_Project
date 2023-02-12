package com.google.android.gms.measurement.internal;

import android.database.sqlite.SQLiteDatabase;
import java.io.File;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zzak {
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0029, code lost:
        if (r0 == false) goto L_0x0045;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0085 A[Catch:{ all -> 0x00eb, SQLiteException -> 0x00f0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c0 A[Catch:{ all -> 0x00eb, SQLiteException -> 0x00f0 }, LOOP:1: B:34:0x00c0->B:39:0x00d2, LOOP_START, PHI: r12 
      PHI: (r12v1 int) = (r12v0 int), (r12v2 int) binds: [B:33:0x00be, B:39:0x00d2] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00db A[Catch:{ all -> 0x00eb, SQLiteException -> 0x00f0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:62:? A[Catch:{  }, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void zza(com.google.android.gms.measurement.internal.zzel r14, android.database.sqlite.SQLiteDatabase r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String[] r19) throws android.database.sqlite.SQLiteException {
        /*
            r9 = r15
            r10 = r16
            r11 = r19
            if (r14 == 0) goto L_0x0103
            r12 = 0
            r13 = 0
            java.lang.String r0 = "name"
            java.lang.String[] r3 = new java.lang.String[]{r0}     // Catch:{ SQLiteException -> 0x0035, all -> 0x0032 }
            r0 = 1
            java.lang.String[] r5 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0035, all -> 0x0032 }
            r5[r12] = r10     // Catch:{ SQLiteException -> 0x0035, all -> 0x0032 }
            java.lang.String r2 = "SQLITE_MASTER"
            java.lang.String r4 = "name=?"
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r15
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0035, all -> 0x0032 }
            boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0030, all -> 0x002c }
            if (r1 == 0) goto L_0x0029
            r1.close()
        L_0x0029:
            if (r0 != 0) goto L_0x004a
            goto L_0x0045
        L_0x002c:
            r0 = move-exception
            r13 = r1
            goto L_0x00fd
        L_0x0030:
            r0 = move-exception
            goto L_0x0037
        L_0x0032:
            r0 = move-exception
            goto L_0x00fd
        L_0x0035:
            r0 = move-exception
            r1 = r13
        L_0x0037:
            com.google.android.gms.measurement.internal.zzej r2 = r14.zzk()     // Catch:{ all -> 0x00fb }
            java.lang.String r3 = "Error querying for table"
            r2.zzc(r3, r10, r0)     // Catch:{ all -> 0x00fb }
            if (r1 == 0) goto L_0x0045
            r1.close()
        L_0x0045:
            r1 = r17
            r15.execSQL(r1)
        L_0x004a:
            java.util.HashSet r0 = new java.util.HashSet     // Catch:{ SQLiteException -> 0x00f0 }
            r0.<init>()     // Catch:{ SQLiteException -> 0x00f0 }
            int r1 = r16.length()     // Catch:{ SQLiteException -> 0x00f0 }
            int r1 = r1 + 22
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x00f0 }
            r2.<init>(r1)     // Catch:{ SQLiteException -> 0x00f0 }
            java.lang.String r1 = "SELECT * FROM "
            r2.append(r1)     // Catch:{ SQLiteException -> 0x00f0 }
            r2.append(r10)     // Catch:{ SQLiteException -> 0x00f0 }
            java.lang.String r1 = " LIMIT 0"
            r2.append(r1)     // Catch:{ SQLiteException -> 0x00f0 }
            java.lang.String r1 = r2.toString()     // Catch:{ SQLiteException -> 0x00f0 }
            android.database.Cursor r1 = r15.rawQuery(r1, r13)     // Catch:{ SQLiteException -> 0x00f0 }
            java.lang.String[] r2 = r1.getColumnNames()     // Catch:{ all -> 0x00eb }
            java.util.Collections.addAll(r0, r2)     // Catch:{ all -> 0x00eb }
            r1.close()     // Catch:{ SQLiteException -> 0x00f0 }
            java.lang.String r1 = ","
            r2 = r18
            java.lang.String[] r1 = r2.split(r1)     // Catch:{ SQLiteException -> 0x00f0 }
            int r2 = r1.length     // Catch:{ SQLiteException -> 0x00f0 }
            r3 = r12
        L_0x0083:
            if (r3 >= r2) goto L_0x00be
            r4 = r1[r3]     // Catch:{ SQLiteException -> 0x00f0 }
            boolean r5 = r0.remove(r4)     // Catch:{ SQLiteException -> 0x00f0 }
            if (r5 == 0) goto L_0x0090
            int r3 = r3 + 1
            goto L_0x0083
        L_0x0090:
            android.database.sqlite.SQLiteException r0 = new android.database.sqlite.SQLiteException     // Catch:{ SQLiteException -> 0x00f0 }
            int r1 = r16.length()     // Catch:{ SQLiteException -> 0x00f0 }
            int r1 = r1 + 35
            java.lang.String r2 = java.lang.String.valueOf(r4)     // Catch:{ SQLiteException -> 0x00f0 }
            int r2 = r2.length()     // Catch:{ SQLiteException -> 0x00f0 }
            int r1 = r1 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x00f0 }
            r2.<init>(r1)     // Catch:{ SQLiteException -> 0x00f0 }
            java.lang.String r1 = "Table "
            r2.append(r1)     // Catch:{ SQLiteException -> 0x00f0 }
            r2.append(r10)     // Catch:{ SQLiteException -> 0x00f0 }
            java.lang.String r1 = " is missing required column: "
            r2.append(r1)     // Catch:{ SQLiteException -> 0x00f0 }
            r2.append(r4)     // Catch:{ SQLiteException -> 0x00f0 }
            java.lang.String r1 = r2.toString()     // Catch:{ SQLiteException -> 0x00f0 }
            r0.<init>(r1)     // Catch:{ SQLiteException -> 0x00f0 }
            throw r0     // Catch:{ SQLiteException -> 0x00f0 }
        L_0x00be:
            if (r11 == 0) goto L_0x00d5
        L_0x00c0:
            int r1 = r11.length     // Catch:{ SQLiteException -> 0x00f0 }
            if (r12 >= r1) goto L_0x00d5
            r1 = r11[r12]     // Catch:{ SQLiteException -> 0x00f0 }
            boolean r1 = r0.remove(r1)     // Catch:{ SQLiteException -> 0x00f0 }
            if (r1 != 0) goto L_0x00d2
            int r1 = r12 + 1
            r1 = r11[r1]     // Catch:{ SQLiteException -> 0x00f0 }
            r15.execSQL(r1)     // Catch:{ SQLiteException -> 0x00f0 }
        L_0x00d2:
            int r12 = r12 + 2
            goto L_0x00c0
        L_0x00d5:
            boolean r1 = r0.isEmpty()     // Catch:{ SQLiteException -> 0x00f0 }
            if (r1 != 0) goto L_0x00ea
            com.google.android.gms.measurement.internal.zzej r1 = r14.zzk()     // Catch:{ SQLiteException -> 0x00f0 }
            java.lang.String r2 = "Table has extra columns. table, columns"
            java.lang.String r3 = ", "
            java.lang.String r0 = android.text.TextUtils.join(r3, r0)     // Catch:{ SQLiteException -> 0x00f0 }
            r1.zzc(r2, r10, r0)     // Catch:{ SQLiteException -> 0x00f0 }
        L_0x00ea:
            return
        L_0x00eb:
            r0 = move-exception
            r1.close()     // Catch:{ SQLiteException -> 0x00f0 }
            throw r0     // Catch:{ SQLiteException -> 0x00f0 }
        L_0x00f0:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzej r1 = r14.zzd()
            java.lang.String r2 = "Failed to verify columns on table that was just created"
            r1.zzb(r2, r10)
            throw r0
        L_0x00fb:
            r0 = move-exception
            r13 = r1
        L_0x00fd:
            if (r13 == 0) goto L_0x0102
            r13.close()
        L_0x0102:
            throw r0
        L_0x0103:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Monitor must not be null"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzak.zza(com.google.android.gms.measurement.internal.zzel, android.database.sqlite.SQLiteDatabase, java.lang.String, java.lang.String, java.lang.String, java.lang.String[]):void");
    }

    static void zzb(zzel zzel, SQLiteDatabase sQLiteDatabase) {
        if (zzel != null) {
            File file = new File(sQLiteDatabase.getPath());
            if (!file.setReadable(false, false)) {
                zzel.zzk().zza("Failed to turn off database read permission");
            }
            if (!file.setWritable(false, false)) {
                zzel.zzk().zza("Failed to turn off database write permission");
            }
            if (!file.setReadable(true, true)) {
                zzel.zzk().zza("Failed to turn on database read permission for owner");
            }
            if (!file.setWritable(true, true)) {
                zzel.zzk().zza("Failed to turn on database write permission for owner");
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Monitor must not be null");
    }
}
