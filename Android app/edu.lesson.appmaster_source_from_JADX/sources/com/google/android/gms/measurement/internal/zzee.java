package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcel;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zzee extends zzf {
    private final zzed zza;
    private boolean zzb;

    zzee(zzfv zzfv) {
        super(zzfv);
        Context zzau = this.zzs.zzau();
        this.zzs.zzf();
        this.zza = new zzed(this, zzau, "google_app_measurement_local.db");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v12, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [boolean, int] */
    /* JADX WARNING: type inference failed for: r8v0 */
    /* JADX WARNING: type inference failed for: r8v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r8v2 */
    /* JADX WARNING: type inference failed for: r8v3 */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r8v6, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r8v8 */
    /* JADX WARNING: type inference failed for: r8v9 */
    /* JADX WARNING: type inference failed for: r8v10 */
    /* JADX WARNING: type inference failed for: r8v13 */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00cd A[SYNTHETIC, Splitter:B:48:0x00cd] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0121 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0121 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0121 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzq(int r17, byte[] r18) {
        /*
            r16 = this;
            r1 = r16
            r16.zzg()
            boolean r0 = r1.zzb
            r2 = 0
            if (r0 == 0) goto L_0x000b
            return r2
        L_0x000b:
            android.content.ContentValues r3 = new android.content.ContentValues
            r3.<init>()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r17)
            java.lang.String r4 = "type"
            r3.put(r4, r0)
            java.lang.String r0 = "entry"
            r4 = r18
            r3.put(r0, r4)
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzs
            r0.zzf()
            r4 = 5
            r5 = r2
            r6 = r4
        L_0x0028:
            if (r5 >= r4) goto L_0x0135
            r7 = 1
            r8 = 0
            android.database.sqlite.SQLiteDatabase r9 = r16.zzh()     // Catch:{ SQLiteFullException -> 0x0103, SQLiteDatabaseLockedException -> 0x00f0, SQLiteException -> 0x00c9, all -> 0x00c6 }
            if (r9 != 0) goto L_0x0035
            r1.zzb = r7     // Catch:{ SQLiteFullException -> 0x00c2, SQLiteDatabaseLockedException -> 0x00c0, SQLiteException -> 0x00bc }
            return r2
        L_0x0035:
            r9.beginTransaction()     // Catch:{ SQLiteFullException -> 0x00c2, SQLiteDatabaseLockedException -> 0x00c0, SQLiteException -> 0x00bc }
            java.lang.String r0 = "select count(1) from messages"
            android.database.Cursor r10 = r9.rawQuery(r0, r8)     // Catch:{ SQLiteFullException -> 0x00c2, SQLiteDatabaseLockedException -> 0x00c0, SQLiteException -> 0x00bc }
            r11 = 0
            if (r10 == 0) goto L_0x0057
            boolean r0 = r10.moveToFirst()     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
            if (r0 == 0) goto L_0x0057
            long r11 = r10.getLong(r2)     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
            goto L_0x0058
        L_0x004d:
            r0 = move-exception
            goto L_0x0129
        L_0x0050:
            r0 = move-exception
            goto L_0x00be
        L_0x0052:
            r0 = move-exception
            goto L_0x00b7
        L_0x0054:
            r0 = move-exception
            goto L_0x00c4
        L_0x0057:
        L_0x0058:
            r13 = 100000(0x186a0, double:4.94066E-319)
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            java.lang.String r15 = "messages"
            if (r0 < 0) goto L_0x00a3
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzs     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzd()     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
            java.lang.String r4 = "Data loss, local db full"
            r0.zza(r4)     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
            long r13 = r13 - r11
            r11 = 1
            long r13 = r13 + r11
            java.lang.String[] r0 = new java.lang.String[r7]     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
            java.lang.String r4 = java.lang.Long.toString(r13)     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
            r0[r2] = r4     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
            java.lang.String r4 = "rowid in (select rowid from messages order by rowid asc limit ?)"
            int r0 = r9.delete(r15, r4, r0)     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
            long r11 = (long) r0     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x00a3
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzs     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzd()     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
            java.lang.String r4 = "Different delete count than expected in local db. expected, received, difference"
            java.lang.Long r2 = java.lang.Long.valueOf(r13)     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
            java.lang.Long r7 = java.lang.Long.valueOf(r11)     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
            long r13 = r13 - r11
            java.lang.Long r11 = java.lang.Long.valueOf(r13)     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
            r0.zzd(r4, r2, r7, r11)     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
        L_0x00a3:
            r9.insertOrThrow(r15, r8, r3)     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
            r9.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
            r9.endTransaction()     // Catch:{ SQLiteFullException -> 0x0054, SQLiteDatabaseLockedException -> 0x0052, SQLiteException -> 0x0050, all -> 0x004d }
            if (r10 == 0) goto L_0x00b2
            r10.close()
        L_0x00b2:
            r9.close()
            r2 = 1
            return r2
        L_0x00b7:
            r8 = r10
            goto L_0x00f2
        L_0x00b9:
            r0 = move-exception
            goto L_0x012a
        L_0x00bc:
            r0 = move-exception
            r10 = r8
        L_0x00be:
            r8 = r9
            goto L_0x00cb
        L_0x00c0:
            r0 = move-exception
            goto L_0x00f2
        L_0x00c2:
            r0 = move-exception
            r10 = r8
        L_0x00c4:
            r8 = r9
            goto L_0x0105
        L_0x00c6:
            r0 = move-exception
            r9 = r8
            goto L_0x012a
        L_0x00c9:
            r0 = move-exception
            r10 = r8
        L_0x00cb:
            if (r8 == 0) goto L_0x00d6
            boolean r2 = r8.inTransaction()     // Catch:{ all -> 0x0127 }
            if (r2 == 0) goto L_0x00d6
            r8.endTransaction()     // Catch:{ all -> 0x0127 }
        L_0x00d6:
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzs     // Catch:{ all -> 0x0127 }
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()     // Catch:{ all -> 0x0127 }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()     // Catch:{ all -> 0x0127 }
            java.lang.String r4 = "Error writing entry to local database"
            r2.zzb(r4, r0)     // Catch:{ all -> 0x0127 }
            r2 = 1
            r1.zzb = r2     // Catch:{ all -> 0x0127 }
            if (r10 == 0) goto L_0x00ed
            r10.close()
        L_0x00ed:
            if (r8 == 0) goto L_0x0121
            goto L_0x011e
        L_0x00f0:
            r0 = move-exception
            r9 = r8
        L_0x00f2:
            long r10 = (long) r6
            android.os.SystemClock.sleep(r10)     // Catch:{ all -> 0x00b9 }
            int r6 = r6 + 20
            if (r8 == 0) goto L_0x00fd
            r8.close()
        L_0x00fd:
            if (r9 == 0) goto L_0x0121
            r9.close()
            goto L_0x0121
        L_0x0103:
            r0 = move-exception
            r10 = r8
        L_0x0105:
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzs     // Catch:{ all -> 0x0127 }
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()     // Catch:{ all -> 0x0127 }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()     // Catch:{ all -> 0x0127 }
            java.lang.String r4 = "Error writing entry; local database full"
            r2.zzb(r4, r0)     // Catch:{ all -> 0x0127 }
            r2 = 1
            r1.zzb = r2     // Catch:{ all -> 0x0127 }
            if (r10 == 0) goto L_0x011c
            r10.close()
        L_0x011c:
            if (r8 == 0) goto L_0x0121
        L_0x011e:
            r8.close()
        L_0x0121:
            int r5 = r5 + 1
            r2 = 0
            r4 = 5
            goto L_0x0028
        L_0x0127:
            r0 = move-exception
            r9 = r8
        L_0x0129:
            r8 = r10
        L_0x012a:
            if (r8 == 0) goto L_0x012f
            r8.close()
        L_0x012f:
            if (r9 == 0) goto L_0x0134
            r9.close()
        L_0x0134:
            throw r0
        L_0x0135:
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzs
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzj()
            java.lang.String r2 = "Failed to write entry to local database"
            r0.zza(r2)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzee.zzq(int, byte[]):boolean");
    }

    /* access modifiers changed from: protected */
    public final boolean zzf() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final SQLiteDatabase zzh() throws SQLiteException {
        if (this.zzb) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zza.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzb = true;
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:138:0x01f0 A[SYNTHETIC, Splitter:B:138:0x01f0] */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0218 A[SYNTHETIC, Splitter:B:162:0x0218] */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x0232  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x0243  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x0260  */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x026e  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x0273  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x01f3 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x0266 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x0266 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x0266 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable> zzi(int r23) {
        /*
            r22 = this;
            r1 = r22
            java.lang.String r2 = "rowid"
            java.lang.String r3 = "Error reading entries from local database"
            r22.zzg()
            boolean r0 = r1.zzb
            r4 = 0
            if (r0 == 0) goto L_0x000f
            return r4
        L_0x000f:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            boolean r0 = r22.zzl()
            if (r0 == 0) goto L_0x0287
            r6 = 5
            r7 = 0
            r9 = r6
            r8 = r7
        L_0x001e:
            if (r8 >= r6) goto L_0x0277
            r10 = 1
            android.database.sqlite.SQLiteDatabase r15 = r22.zzh()     // Catch:{ SQLiteFullException -> 0x024c, SQLiteDatabaseLockedException -> 0x0238, SQLiteException -> 0x0213, all -> 0x0210 }
            if (r15 != 0) goto L_0x002a
            r1.zzb = r10     // Catch:{ SQLiteFullException -> 0x020b, SQLiteDatabaseLockedException -> 0x0206, SQLiteException -> 0x0201, all -> 0x01fc }
            return r4
        L_0x002a:
            r15.beginTransaction()     // Catch:{ SQLiteFullException -> 0x020b, SQLiteDatabaseLockedException -> 0x0206, SQLiteException -> 0x0201, all -> 0x01fc }
            java.lang.String r0 = "3"
            java.lang.String r12 = "messages"
            java.lang.String[] r13 = new java.lang.String[]{r2}     // Catch:{ all -> 0x01eb }
            java.lang.String r14 = "type=?"
            java.lang.String[] r0 = new java.lang.String[]{r0}     // Catch:{ all -> 0x01eb }
            r16 = 0
            r17 = 0
            java.lang.String r18 = "rowid desc"
            java.lang.String r19 = "1"
            r11 = r15
            r23 = r15
            r15 = r0
            android.database.Cursor r11 = r11.query(r12, r13, r14, r15, r16, r17, r18, r19)     // Catch:{ all -> 0x01e7 }
            boolean r0 = r11.moveToFirst()     // Catch:{ all -> 0x01e3 }
            r20 = -1
            if (r0 == 0) goto L_0x005e
            long r12 = r11.getLong(r7)     // Catch:{ all -> 0x01e3 }
            if (r11 == 0) goto L_0x005d
            r11.close()     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x006e, SQLiteException -> 0x0069, all -> 0x0064 }
            goto L_0x007a
        L_0x005d:
            goto L_0x007a
        L_0x005e:
            if (r11 == 0) goto L_0x0078
            r11.close()     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x006e, SQLiteException -> 0x0069, all -> 0x0064 }
            goto L_0x0078
        L_0x0064:
            r0 = move-exception
            r14 = r23
            goto L_0x01fe
        L_0x0069:
            r0 = move-exception
            r14 = r23
            goto L_0x0203
        L_0x006e:
            r0 = move-exception
            r14 = r23
            goto L_0x0208
        L_0x0073:
            r0 = move-exception
            r14 = r23
            goto L_0x020d
        L_0x0078:
            r12 = r20
        L_0x007a:
            int r0 = (r12 > r20 ? 1 : (r12 == r20 ? 0 : -1))
            if (r0 == 0) goto L_0x008b
            java.lang.String r0 = "rowid<?"
            java.lang.String[] r11 = new java.lang.String[r10]     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x006e, SQLiteException -> 0x0069, all -> 0x0064 }
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x006e, SQLiteException -> 0x0069, all -> 0x0064 }
            r11[r7] = r12     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x006e, SQLiteException -> 0x0069, all -> 0x0064 }
            r14 = r0
            r15 = r11
            goto L_0x008d
        L_0x008b:
            r14 = r4
            r15 = r14
        L_0x008d:
            java.lang.String r0 = "type"
            java.lang.String r11 = "entry"
            java.lang.String[] r13 = new java.lang.String[]{r2, r0, r11}     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x006e, SQLiteException -> 0x0069, all -> 0x0064 }
            java.lang.String r12 = "messages"
            r16 = 0
            r17 = 0
            java.lang.String r18 = "rowid asc"
            r0 = 100
            java.lang.String r19 = java.lang.Integer.toString(r0)     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x006e, SQLiteException -> 0x0069, all -> 0x0064 }
            r11 = r23
            android.database.Cursor r11 = r11.query(r12, r13, r14, r15, r16, r17, r18, r19)     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x006e, SQLiteException -> 0x0069, all -> 0x0064 }
        L_0x00a9:
            boolean r0 = r11.moveToNext()     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            if (r0 == 0) goto L_0x018c
            long r20 = r11.getLong(r7)     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            int r0 = r11.getInt(r10)     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            r12 = 2
            byte[] r13 = r11.getBlob(r12)     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            if (r0 != 0) goto L_0x00f4
            android.os.Parcel r12 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            int r0 = r13.length     // Catch:{ ParseException -> 0x00dc }
            r12.unmarshall(r13, r7, r0)     // Catch:{ ParseException -> 0x00dc }
            r12.setDataPosition(r7)     // Catch:{ ParseException -> 0x00dc }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzat> r0 = com.google.android.gms.measurement.internal.zzat.CREATOR     // Catch:{ ParseException -> 0x00dc }
            java.lang.Object r0 = r0.createFromParcel(r12)     // Catch:{ ParseException -> 0x00dc }
            com.google.android.gms.measurement.internal.zzat r0 = (com.google.android.gms.measurement.internal.zzat) r0     // Catch:{ ParseException -> 0x00dc }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            if (r0 == 0) goto L_0x00d9
            r5.add(r0)     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
        L_0x00d9:
            goto L_0x00a9
        L_0x00da:
            r0 = move-exception
            goto L_0x00f0
        L_0x00dc:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzs     // Catch:{ all -> 0x00da }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ all -> 0x00da }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzd()     // Catch:{ all -> 0x00da }
            java.lang.String r13 = "Failed to load event from local database"
            r0.zza(r13)     // Catch:{ all -> 0x00da }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            goto L_0x00a9
        L_0x00f0:
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            throw r0     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
        L_0x00f4:
            if (r0 != r10) goto L_0x012d
            android.os.Parcel r12 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            int r0 = r13.length     // Catch:{ ParseException -> 0x010f }
            r12.unmarshall(r13, r7, r0)     // Catch:{ ParseException -> 0x010f }
            r12.setDataPosition(r7)     // Catch:{ ParseException -> 0x010f }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzkv> r0 = com.google.android.gms.measurement.internal.zzkv.CREATOR     // Catch:{ ParseException -> 0x010f }
            java.lang.Object r0 = r0.createFromParcel(r12)     // Catch:{ ParseException -> 0x010f }
            com.google.android.gms.measurement.internal.zzkv r0 = (com.google.android.gms.measurement.internal.zzkv) r0     // Catch:{ ParseException -> 0x010f }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            goto L_0x0123
        L_0x010d:
            r0 = move-exception
            goto L_0x0129
        L_0x010f:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzs     // Catch:{ all -> 0x010d }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ all -> 0x010d }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzd()     // Catch:{ all -> 0x010d }
            java.lang.String r13 = "Failed to load user property from local database"
            r0.zza(r13)     // Catch:{ all -> 0x010d }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            r0 = r4
        L_0x0123:
            if (r0 == 0) goto L_0x0128
            r5.add(r0)     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
        L_0x0128:
            goto L_0x00a9
        L_0x0129:
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            throw r0     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
        L_0x012d:
            if (r0 != r12) goto L_0x0167
            android.os.Parcel r12 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            int r0 = r13.length     // Catch:{ ParseException -> 0x0148 }
            r12.unmarshall(r13, r7, r0)     // Catch:{ ParseException -> 0x0148 }
            r12.setDataPosition(r7)     // Catch:{ ParseException -> 0x0148 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzab> r0 = com.google.android.gms.measurement.internal.zzab.CREATOR     // Catch:{ ParseException -> 0x0148 }
            java.lang.Object r0 = r0.createFromParcel(r12)     // Catch:{ ParseException -> 0x0148 }
            com.google.android.gms.measurement.internal.zzab r0 = (com.google.android.gms.measurement.internal.zzab) r0     // Catch:{ ParseException -> 0x0148 }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            goto L_0x015c
        L_0x0146:
            r0 = move-exception
            goto L_0x0163
        L_0x0148:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzs     // Catch:{ all -> 0x0146 }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ all -> 0x0146 }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzd()     // Catch:{ all -> 0x0146 }
            java.lang.String r13 = "Failed to load conditional user property from local database"
            r0.zza(r13)     // Catch:{ all -> 0x0146 }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            r0 = r4
        L_0x015c:
            if (r0 == 0) goto L_0x0161
            r5.add(r0)     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
        L_0x0161:
            goto L_0x00a9
        L_0x0163:
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            throw r0     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
        L_0x0167:
            r12 = 3
            if (r0 != r12) goto L_0x017b
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzs     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzk()     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            java.lang.String r12 = "Skipping app launch break"
            r0.zza(r12)     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            goto L_0x00a9
        L_0x017b:
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzs     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzd()     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            java.lang.String r12 = "Unknown record type in local database"
            r0.zza(r12)     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            goto L_0x00a9
        L_0x018c:
            java.lang.String[] r0 = new java.lang.String[r10]     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            java.lang.String r12 = java.lang.Long.toString(r20)     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            r0[r7] = r12     // Catch:{ SQLiteFullException -> 0x01dd, SQLiteDatabaseLockedException -> 0x01d7, SQLiteException -> 0x01d1, all -> 0x01ca }
            java.lang.String r12 = "messages"
            java.lang.String r13 = "rowid <= ?"
            r14 = r23
            int r0 = r14.delete(r12, r13, r0)     // Catch:{ SQLiteFullException -> 0x01c8, SQLiteDatabaseLockedException -> 0x01c6, SQLiteException -> 0x01c4, all -> 0x01c2 }
            int r12 = r5.size()     // Catch:{ SQLiteFullException -> 0x01c8, SQLiteDatabaseLockedException -> 0x01c6, SQLiteException -> 0x01c4, all -> 0x01c2 }
            if (r0 >= r12) goto L_0x01b3
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzs     // Catch:{ SQLiteFullException -> 0x01c8, SQLiteDatabaseLockedException -> 0x01c6, SQLiteException -> 0x01c4, all -> 0x01c2 }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ SQLiteFullException -> 0x01c8, SQLiteDatabaseLockedException -> 0x01c6, SQLiteException -> 0x01c4, all -> 0x01c2 }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzd()     // Catch:{ SQLiteFullException -> 0x01c8, SQLiteDatabaseLockedException -> 0x01c6, SQLiteException -> 0x01c4, all -> 0x01c2 }
            java.lang.String r12 = "Fewer entries removed from local database than expected"
            r0.zza(r12)     // Catch:{ SQLiteFullException -> 0x01c8, SQLiteDatabaseLockedException -> 0x01c6, SQLiteException -> 0x01c4, all -> 0x01c2 }
        L_0x01b3:
            r14.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x01c8, SQLiteDatabaseLockedException -> 0x01c6, SQLiteException -> 0x01c4, all -> 0x01c2 }
            r14.endTransaction()     // Catch:{ SQLiteFullException -> 0x01c8, SQLiteDatabaseLockedException -> 0x01c6, SQLiteException -> 0x01c4, all -> 0x01c2 }
            if (r11 == 0) goto L_0x01be
            r11.close()
        L_0x01be:
            r14.close()
            return r5
        L_0x01c2:
            r0 = move-exception
            goto L_0x01cd
        L_0x01c4:
            r0 = move-exception
            goto L_0x01d4
        L_0x01c6:
            r0 = move-exception
            goto L_0x01da
        L_0x01c8:
            r0 = move-exception
            goto L_0x01e0
        L_0x01ca:
            r0 = move-exception
            r14 = r23
        L_0x01cd:
            r4 = r11
            r15 = r14
            goto L_0x026c
        L_0x01d1:
            r0 = move-exception
            r14 = r23
        L_0x01d4:
            r15 = r14
            goto L_0x0216
        L_0x01d7:
            r0 = move-exception
            r14 = r23
        L_0x01da:
            r15 = r14
            goto L_0x023b
        L_0x01dd:
            r0 = move-exception
            r14 = r23
        L_0x01e0:
            r15 = r14
            goto L_0x024f
        L_0x01e3:
            r0 = move-exception
            r14 = r23
            goto L_0x01ee
        L_0x01e7:
            r0 = move-exception
            r14 = r23
            goto L_0x01ed
        L_0x01eb:
            r0 = move-exception
            r14 = r15
        L_0x01ed:
            r11 = r4
        L_0x01ee:
            if (r11 == 0) goto L_0x01f3
            r11.close()     // Catch:{ SQLiteFullException -> 0x01fa, SQLiteDatabaseLockedException -> 0x01f8, SQLiteException -> 0x01f6, all -> 0x01f4 }
        L_0x01f3:
            throw r0     // Catch:{ SQLiteFullException -> 0x01fa, SQLiteDatabaseLockedException -> 0x01f8, SQLiteException -> 0x01f6, all -> 0x01f4 }
        L_0x01f4:
            r0 = move-exception
            goto L_0x01fe
        L_0x01f6:
            r0 = move-exception
            goto L_0x0203
        L_0x01f8:
            r0 = move-exception
            goto L_0x0208
        L_0x01fa:
            r0 = move-exception
            goto L_0x020d
        L_0x01fc:
            r0 = move-exception
            r14 = r15
        L_0x01fe:
            r15 = r14
            goto L_0x026c
        L_0x0201:
            r0 = move-exception
            r14 = r15
        L_0x0203:
            r11 = r4
            r15 = r14
            goto L_0x0216
        L_0x0206:
            r0 = move-exception
            r14 = r15
        L_0x0208:
            r11 = r4
            r15 = r14
            goto L_0x023b
        L_0x020b:
            r0 = move-exception
            r14 = r15
        L_0x020d:
            r11 = r4
            r15 = r14
            goto L_0x024f
        L_0x0210:
            r0 = move-exception
            r15 = r4
            goto L_0x026c
        L_0x0213:
            r0 = move-exception
            r11 = r4
            r15 = r11
        L_0x0216:
            if (r15 == 0) goto L_0x0221
            boolean r12 = r15.inTransaction()     // Catch:{ all -> 0x026a }
            if (r12 == 0) goto L_0x0221
            r15.endTransaction()     // Catch:{ all -> 0x026a }
        L_0x0221:
            com.google.android.gms.measurement.internal.zzfv r12 = r1.zzs     // Catch:{ all -> 0x026a }
            com.google.android.gms.measurement.internal.zzel r12 = r12.zzay()     // Catch:{ all -> 0x026a }
            com.google.android.gms.measurement.internal.zzej r12 = r12.zzd()     // Catch:{ all -> 0x026a }
            r12.zzb(r3, r0)     // Catch:{ all -> 0x026a }
            r1.zzb = r10     // Catch:{ all -> 0x026a }
            if (r11 == 0) goto L_0x0235
            r11.close()
        L_0x0235:
            if (r15 == 0) goto L_0x0266
            goto L_0x0248
        L_0x0238:
            r0 = move-exception
            r11 = r4
            r15 = r11
        L_0x023b:
            long r12 = (long) r9
            android.os.SystemClock.sleep(r12)     // Catch:{ all -> 0x026a }
            int r9 = r9 + 20
            if (r11 == 0) goto L_0x0246
            r11.close()
        L_0x0246:
            if (r15 == 0) goto L_0x0266
        L_0x0248:
            r15.close()
            goto L_0x0266
        L_0x024c:
            r0 = move-exception
            r11 = r4
            r15 = r11
        L_0x024f:
            com.google.android.gms.measurement.internal.zzfv r12 = r1.zzs     // Catch:{ all -> 0x026a }
            com.google.android.gms.measurement.internal.zzel r12 = r12.zzay()     // Catch:{ all -> 0x026a }
            com.google.android.gms.measurement.internal.zzej r12 = r12.zzd()     // Catch:{ all -> 0x026a }
            r12.zzb(r3, r0)     // Catch:{ all -> 0x026a }
            r1.zzb = r10     // Catch:{ all -> 0x026a }
            if (r11 == 0) goto L_0x0263
            r11.close()
        L_0x0263:
            if (r15 == 0) goto L_0x0266
            goto L_0x0248
        L_0x0266:
            int r8 = r8 + 1
            goto L_0x001e
        L_0x026a:
            r0 = move-exception
            r4 = r11
        L_0x026c:
            if (r4 == 0) goto L_0x0271
            r4.close()
        L_0x0271:
            if (r15 == 0) goto L_0x0276
            r15.close()
        L_0x0276:
            throw r0
        L_0x0277:
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzs
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzk()
            java.lang.String r2 = "Failed to read events from database in reasonable time"
            r0.zza(r2)
            return r4
        L_0x0287:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzee.zzi(int):java.util.List");
    }

    public final void zzj() {
        int delete;
        zzg();
        try {
            SQLiteDatabase zzh = zzh();
            if (zzh != null && (delete = zzh.delete("messages", (String) null, (String[]) null)) > 0) {
                this.zzs.zzay().zzj().zzb("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            this.zzs.zzay().zzd().zzb("Error resetting local analytics data. error", e);
        }
    }

    public final boolean zzk() {
        return zzq(3, new byte[0]);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzl() {
        Context zzau = this.zzs.zzau();
        this.zzs.zzf();
        return zzau.getDatabasePath("google_app_measurement_local.db").exists();
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x004b A[SYNTHETIC, Splitter:B:24:0x004b] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0065 A[Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0066, SQLiteException -> 0x0048, all -> 0x0046, all -> 0x003e }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0086 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0086 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0086 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzm() {
        /*
            r10 = this;
            java.lang.String r0 = "Error deleting app launch break from local database"
            r10.zzg()
            boolean r1 = r10.zzb
            r2 = 0
            if (r1 == 0) goto L_0x000b
            return r2
        L_0x000b:
            boolean r1 = r10.zzl()
            if (r1 == 0) goto L_0x009e
            r1 = 5
            r4 = r1
            r3 = r2
        L_0x0014:
            if (r3 >= r1) goto L_0x008f
            r5 = 0
            r6 = 1
            android.database.sqlite.SQLiteDatabase r5 = r10.zzh()     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0066, SQLiteException -> 0x0048, all -> 0x0046 }
            if (r5 != 0) goto L_0x0021
            r10.zzb = r6     // Catch:{ SQLiteFullException -> 0x0044, SQLiteDatabaseLockedException -> 0x0042, SQLiteException -> 0x0040 }
            return r2
        L_0x0021:
            r5.beginTransaction()     // Catch:{ SQLiteFullException -> 0x0044, SQLiteDatabaseLockedException -> 0x0042, SQLiteException -> 0x0040 }
            java.lang.String[] r7 = new java.lang.String[r6]     // Catch:{ SQLiteFullException -> 0x0044, SQLiteDatabaseLockedException -> 0x0042, SQLiteException -> 0x0040 }
            r8 = 3
            java.lang.String r8 = java.lang.Integer.toString(r8)     // Catch:{ SQLiteFullException -> 0x0044, SQLiteDatabaseLockedException -> 0x0042, SQLiteException -> 0x0040 }
            r7[r2] = r8     // Catch:{ SQLiteFullException -> 0x0044, SQLiteDatabaseLockedException -> 0x0042, SQLiteException -> 0x0040 }
            java.lang.String r8 = "messages"
            java.lang.String r9 = "type == ?"
            r5.delete(r8, r9, r7)     // Catch:{ SQLiteFullException -> 0x0044, SQLiteDatabaseLockedException -> 0x0042, SQLiteException -> 0x0040 }
            r5.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x0044, SQLiteDatabaseLockedException -> 0x0042, SQLiteException -> 0x0040 }
            r5.endTransaction()     // Catch:{ SQLiteFullException -> 0x0044, SQLiteDatabaseLockedException -> 0x0042, SQLiteException -> 0x0040 }
            r5.close()
            return r6
        L_0x003e:
            r0 = move-exception
            goto L_0x0089
        L_0x0040:
            r7 = move-exception
            goto L_0x0049
        L_0x0042:
            r6 = move-exception
            goto L_0x0067
        L_0x0044:
            r7 = move-exception
            goto L_0x0074
        L_0x0046:
            r0 = move-exception
            goto L_0x0089
        L_0x0048:
            r7 = move-exception
        L_0x0049:
            if (r5 == 0) goto L_0x0054
            boolean r8 = r5.inTransaction()     // Catch:{ all -> 0x003e }
            if (r8 == 0) goto L_0x0054
            r5.endTransaction()     // Catch:{ all -> 0x003e }
        L_0x0054:
            com.google.android.gms.measurement.internal.zzfv r8 = r10.zzs     // Catch:{ all -> 0x003e }
            com.google.android.gms.measurement.internal.zzel r8 = r8.zzay()     // Catch:{ all -> 0x003e }
            com.google.android.gms.measurement.internal.zzej r8 = r8.zzd()     // Catch:{ all -> 0x003e }
            r8.zzb(r0, r7)     // Catch:{ all -> 0x003e }
            r10.zzb = r6     // Catch:{ all -> 0x003e }
            if (r5 == 0) goto L_0x0086
            goto L_0x006f
        L_0x0066:
            r6 = move-exception
        L_0x0067:
            long r6 = (long) r4     // Catch:{ all -> 0x003e }
            android.os.SystemClock.sleep(r6)     // Catch:{ all -> 0x003e }
            int r4 = r4 + 20
            if (r5 == 0) goto L_0x0086
        L_0x006f:
            r5.close()
            goto L_0x0086
        L_0x0073:
            r7 = move-exception
        L_0x0074:
            com.google.android.gms.measurement.internal.zzfv r8 = r10.zzs     // Catch:{ all -> 0x003e }
            com.google.android.gms.measurement.internal.zzel r8 = r8.zzay()     // Catch:{ all -> 0x003e }
            com.google.android.gms.measurement.internal.zzej r8 = r8.zzd()     // Catch:{ all -> 0x003e }
            r8.zzb(r0, r7)     // Catch:{ all -> 0x003e }
            r10.zzb = r6     // Catch:{ all -> 0x003e }
            if (r5 == 0) goto L_0x0086
            goto L_0x006f
        L_0x0086:
            int r3 = r3 + 1
            goto L_0x0014
        L_0x0089:
            if (r5 == 0) goto L_0x008e
            r5.close()
        L_0x008e:
            throw r0
        L_0x008f:
            com.google.android.gms.measurement.internal.zzfv r0 = r10.zzs
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzk()
            java.lang.String r1 = "Error deleting app launch break from local database in reasonable time"
            r0.zza(r1)
        L_0x009e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzee.zzm():boolean");
    }

    public final boolean zzn(zzab zzab) {
        byte[] zzan = this.zzs.zzv().zzan(zzab);
        if (zzan.length <= 131072) {
            return zzq(2, zzan);
        }
        this.zzs.zzay().zzh().zza("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    public final boolean zzo(zzat zzat) {
        Parcel obtain = Parcel.obtain();
        zzau.zza(zzat, obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zzq(0, marshall);
        }
        this.zzs.zzay().zzh().zza("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zzp(zzkv zzkv) {
        Parcel obtain = Parcel.obtain();
        zzkw.zza(zzkv, obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zzq(1, marshall);
        }
        this.zzs.zzay().zzh().zza("User property too long for local database. Sending directly to service");
        return false;
    }
}
