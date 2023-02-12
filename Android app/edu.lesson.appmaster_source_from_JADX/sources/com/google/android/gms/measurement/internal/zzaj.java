package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzom;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzaj extends zzki {
    /* access modifiers changed from: private */
    public static final String[] zza = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzb = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zzc = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;", "config_last_modified_time", "ALTER TABLE apps ADD COLUMN config_last_modified_time TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zzd = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zze = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzg = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    /* access modifiers changed from: private */
    public static final String[] zzh = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    /* access modifiers changed from: private */
    public static final String[] zzi = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzai zzj;
    /* access modifiers changed from: private */
    public final zzke zzk = new zzke(this.zzs.zzav());

    zzaj(zzks zzks) {
        super(zzks);
        this.zzs.zzf();
        this.zzj = new zzai(this, this.zzs.zzau(), "google_app_measurement.db");
    }

    static final void zzX(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty("value");
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put("value", (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put("value", (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put("value", (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long zzab(java.lang.String r4, java.lang.String[] r5) {
        /*
            r3 = this;
            android.database.sqlite.SQLiteDatabase r0 = r3.zzh()
            r1 = 0
            android.database.Cursor r1 = r0.rawQuery(r4, r5)     // Catch:{ SQLiteException -> 0x0028, all -> 0x0026 }
            boolean r5 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            if (r5 == 0) goto L_0x001a
            r5 = 0
            long r4 = r1.getLong(r5)     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            if (r1 == 0) goto L_0x0019
            r1.close()
        L_0x0019:
            return r4
        L_0x001a:
            android.database.sqlite.SQLiteException r5 = new android.database.sqlite.SQLiteException     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            java.lang.String r0 = "Database returned empty set"
            r5.<init>(r0)     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            throw r5     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
        L_0x0022:
            r4 = move-exception
            goto L_0x003a
        L_0x0024:
            r5 = move-exception
            goto L_0x0029
        L_0x0026:
            r4 = move-exception
            goto L_0x003a
        L_0x0028:
            r5 = move-exception
        L_0x0029:
            com.google.android.gms.measurement.internal.zzfv r0 = r3.zzs     // Catch:{ all -> 0x0039 }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ all -> 0x0039 }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzd()     // Catch:{ all -> 0x0039 }
            java.lang.String r2 = "Database error"
            r0.zzc(r2, r4, r5)     // Catch:{ all -> 0x0039 }
            throw r5     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r4 = move-exception
        L_0x003a:
            if (r1 == 0) goto L_0x003f
            r1.close()
        L_0x003f:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaj.zzab(java.lang.String, java.lang.String[]):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0039  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long zzac(java.lang.String r3, java.lang.String[] r4, long r5) {
        /*
            r2 = this;
            android.database.sqlite.SQLiteDatabase r0 = r2.zzh()
            r1 = 0
            android.database.Cursor r1 = r0.rawQuery(r3, r4)     // Catch:{ SQLiteException -> 0x0026, all -> 0x0024 }
            boolean r4 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0022 }
            if (r4 == 0) goto L_0x001a
            r4 = 0
            long r3 = r1.getLong(r4)     // Catch:{ SQLiteException -> 0x0022 }
            if (r1 == 0) goto L_0x0019
            r1.close()
        L_0x0019:
            return r3
        L_0x001a:
            if (r1 == 0) goto L_0x001f
            r1.close()
        L_0x001f:
            return r5
        L_0x0020:
            r3 = move-exception
            goto L_0x0037
        L_0x0022:
            r4 = move-exception
            goto L_0x0027
        L_0x0024:
            r3 = move-exception
            goto L_0x0037
        L_0x0026:
            r4 = move-exception
        L_0x0027:
            com.google.android.gms.measurement.internal.zzfv r5 = r2.zzs     // Catch:{ all -> 0x0020 }
            com.google.android.gms.measurement.internal.zzel r5 = r5.zzay()     // Catch:{ all -> 0x0020 }
            com.google.android.gms.measurement.internal.zzej r5 = r5.zzd()     // Catch:{ all -> 0x0020 }
            java.lang.String r6 = "Database error"
            r5.zzc(r6, r3, r4)     // Catch:{ all -> 0x0020 }
            throw r4     // Catch:{ all -> 0x0020 }
        L_0x0037:
            if (r1 == 0) goto L_0x003c
            r1.close()
        L_0x003c:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaj.zzac(java.lang.String, java.lang.String[], long):long");
    }

    /* access modifiers changed from: package-private */
    public final void zzA() {
        zzg();
        zzY();
        if (zzK()) {
            long zza2 = this.zzf.zzs().zza.zza();
            long elapsedRealtime = this.zzs.zzav().elapsedRealtime();
            long abs = Math.abs(elapsedRealtime - zza2);
            this.zzs.zzf();
            if (abs > zzdy.zzx.zza(null).longValue()) {
                this.zzf.zzs().zza.zzb(elapsedRealtime);
                zzg();
                zzY();
                if (zzK()) {
                    SQLiteDatabase zzh2 = zzh();
                    this.zzs.zzf();
                    int delete = zzh2.delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(this.zzs.zzav().currentTimeMillis()), String.valueOf(zzaf.zzA())});
                    if (delete > 0) {
                        this.zzs.zzay().zzj().zzb("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                    }
                }
            }
        }
    }

    public final void zzB(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzY();
        try {
            zzh().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            this.zzs.zzay().zzd().zzd("Error deleting user property. appId", zzel.zzn(str), this.zzs.zzj().zzf(str2), e);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x032d, code lost:
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x032e, code lost:
        r11.put("filter_id", r12);
        r22 = r0;
        r11.put("property_name", r3.zze());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0340, code lost:
        if (r3.zzk() == false) goto L_0x034b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0342, code lost:
        r0 = java.lang.Boolean.valueOf(r3.zzi());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x034b, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x034c, code lost:
        r11.put("session_scoped", r0);
        r11.put(com.google.firebase.messaging.Constants.ScionAnalytics.MessageType.DATA_MESSAGE, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0360, code lost:
        if (zzh().insertWithOnConflict("property_filters", (java.lang.String) null, r11, 5) != -1) goto L_0x0376;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0362, code lost:
        r9.zzs.zzay().zzd().zzb("Failed to insert property filter (got -1). appId", com.google.android.gms.measurement.internal.zzel.zzn(r24));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0376, code lost:
        r0 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x037a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:?, code lost:
        r9.zzs.zzay().zzd().zzc("Error storing property filter. appId", com.google.android.gms.measurement.internal.zzel.zzn(r24), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x03c5, code lost:
        r3 = r17;
        r4 = r21;
        r3 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x018d, code lost:
        r11 = r0.zzh().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0199, code lost:
        if (r11.hasNext() == false) goto L_0x01c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x01a5, code lost:
        if (r11.next().zzj() != false) goto L_0x0195;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x01a7, code lost:
        r9.zzs.zzay().zzk().zzc("Property filter with no ID. Audience definition ignored. appId, audienceId", com.google.android.gms.measurement.internal.zzel.zzn(r24), java.lang.Integer.valueOf(r10));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x01c0, code lost:
        r11 = r0.zzg().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x01d6, code lost:
        if (r11.hasNext() == false) goto L_0x02b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        r12 = r11.next();
        zzY();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x01f2, code lost:
        if (android.text.TextUtils.isEmpty(r12.zzg()) == false) goto L_0x0226;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x01f4, code lost:
        r0 = r9.zzs.zzay().zzk();
        r8 = com.google.android.gms.measurement.internal.zzel.zzn(r24);
        r11 = java.lang.Integer.valueOf(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x020c, code lost:
        if (r12.zzp() == false) goto L_0x0219;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x020e, code lost:
        r20 = java.lang.Integer.valueOf(r12.zzb());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0219, code lost:
        r20 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x021b, code lost:
        r0.zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", r8, r11, java.lang.String.valueOf(r20));
        r21 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0226, code lost:
        r3 = r12.zzbs();
        r21 = r4;
        r4 = new android.content.ContentValues();
        r4.put("app_id", r2);
        r4.put("audience_id", java.lang.Integer.valueOf(r10));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x023f, code lost:
        if (r12.zzp() == false) goto L_0x024a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0241, code lost:
        r8 = java.lang.Integer.valueOf(r12.zzb());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x024a, code lost:
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x024b, code lost:
        r4.put("filter_id", r8);
        r4.put("event_name", r12.zzg());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x025b, code lost:
        if (r12.zzq() == false) goto L_0x0266;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x025d, code lost:
        r8 = java.lang.Boolean.valueOf(r12.zzn());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0266, code lost:
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0267, code lost:
        r4.put("session_scoped", r8);
        r4.put(com.google.firebase.messaging.Constants.ScionAnalytics.MessageType.DATA_MESSAGE, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x027b, code lost:
        if (zzh().insertWithOnConflict("event_filters", (java.lang.String) null, r4, 5) != -1) goto L_0x0296;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x027d, code lost:
        r9.zzs.zzay().zzd().zzb("Failed to insert event filter (got -1). appId", com.google.android.gms.measurement.internal.zzel.zzn(r24));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0290, code lost:
        r3 = r25;
        r4 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0296, code lost:
        r3 = r25;
        r4 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x02b2, code lost:
        r21 = r4;
        r0 = r0.zzh().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x02c0, code lost:
        if (r0.hasNext() == false) goto L_0x03c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x02c2, code lost:
        r3 = r0.next();
        zzY();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x02dc, code lost:
        if (android.text.TextUtils.isEmpty(r3.zze()) == false) goto L_0x030b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x02de, code lost:
        r0 = r9.zzs.zzay().zzk();
        r7 = com.google.android.gms.measurement.internal.zzel.zzn(r24);
        r8 = java.lang.Integer.valueOf(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x02f6, code lost:
        if (r3.zzj() == false) goto L_0x0301;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x02f8, code lost:
        r3 = java.lang.Integer.valueOf(r3.zza());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0301, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0302, code lost:
        r0.zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", r7, r8, java.lang.String.valueOf(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x030b, code lost:
        r4 = r3.zzbs();
        r11 = new android.content.ContentValues();
        r11.put("app_id", r2);
        r11.put("audience_id", java.lang.Integer.valueOf(r10));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0322, code lost:
        if (r3.zzj() == false) goto L_0x032d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0324, code lost:
        r12 = java.lang.Integer.valueOf(r3.zza());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzC(java.lang.String r24, java.util.List<com.google.android.gms.internal.measurement.zzeh> r25) {
        /*
            r23 = this;
            r1 = r23
            r2 = r24
            r3 = r25
            java.lang.String r4 = "app_id=? and audience_id=?"
            java.lang.String r0 = "app_id=?"
            java.lang.String r5 = "event_filters"
            java.lang.String r6 = "property_filters"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r25)
            r8 = 0
        L_0x0012:
            int r9 = r25.size()
            if (r8 >= r9) goto L_0x00e9
            java.lang.Object r9 = r3.get(r8)
            com.google.android.gms.internal.measurement.zzeh r9 = (com.google.android.gms.internal.measurement.zzeh) r9
            com.google.android.gms.internal.measurement.zzjt r9 = r9.zzbv()
            com.google.android.gms.internal.measurement.zzeg r9 = (com.google.android.gms.internal.measurement.zzeg) r9
            int r11 = r9.zza()
            if (r11 == 0) goto L_0x00a6
            r12 = r9
            r11 = 0
        L_0x002c:
            int r13 = r12.zza()
            if (r11 >= r13) goto L_0x00a3
            com.google.android.gms.internal.measurement.zzej r13 = r12.zze(r11)
            com.google.android.gms.internal.measurement.zzjt r13 = r13.zzbv()
            com.google.android.gms.internal.measurement.zzei r13 = (com.google.android.gms.internal.measurement.zzei) r13
            com.google.android.gms.internal.measurement.zzjt r14 = r13.zzaq()
            com.google.android.gms.internal.measurement.zzei r14 = (com.google.android.gms.internal.measurement.zzei) r14
            java.lang.String r15 = r13.zze()
            java.lang.String r15 = com.google.android.gms.measurement.internal.zzgs.zzb(r15)
            if (r15 == 0) goto L_0x0051
            r14.zzb(r15)
            r15 = 1
            goto L_0x0052
        L_0x0051:
            r15 = 0
        L_0x0052:
            r7 = 0
        L_0x0053:
            int r10 = r13.zza()
            if (r7 >= r10) goto L_0x008b
            com.google.android.gms.internal.measurement.zzel r10 = r13.zzd(r7)
            r16 = r13
            java.lang.String r13 = r10.zze()
            r17 = r4
            java.lang.String[] r4 = com.google.android.gms.measurement.internal.zzgt.zza
            java.lang.String[] r1 = com.google.android.gms.measurement.internal.zzgt.zzb
            java.lang.String r1 = com.google.android.gms.measurement.internal.zzig.zzb(r13, r4, r1)
            if (r1 == 0) goto L_0x0082
            com.google.android.gms.internal.measurement.zzjt r4 = r10.zzbv()
            com.google.android.gms.internal.measurement.zzek r4 = (com.google.android.gms.internal.measurement.zzek) r4
            r4.zza(r1)
            com.google.android.gms.internal.measurement.zzjx r1 = r4.zzaA()
            com.google.android.gms.internal.measurement.zzel r1 = (com.google.android.gms.internal.measurement.zzel) r1
            r14.zzc(r7, r1)
            r15 = 1
        L_0x0082:
            int r7 = r7 + 1
            r1 = r23
            r13 = r16
            r4 = r17
            goto L_0x0053
        L_0x008b:
            r17 = r4
            if (r15 == 0) goto L_0x009c
            r12.zzc(r11, r14)
            com.google.android.gms.internal.measurement.zzjx r1 = r9.zzaA()
            com.google.android.gms.internal.measurement.zzeh r1 = (com.google.android.gms.internal.measurement.zzeh) r1
            r3.set(r8, r1)
            r12 = r9
        L_0x009c:
            int r11 = r11 + 1
            r1 = r23
            r4 = r17
            goto L_0x002c
        L_0x00a3:
            r17 = r4
            goto L_0x00a9
        L_0x00a6:
            r17 = r4
            r12 = r9
        L_0x00a9:
            int r1 = r12.zzb()
            if (r1 == 0) goto L_0x00e1
            r1 = 0
        L_0x00b0:
            int r4 = r12.zzb()
            if (r1 >= r4) goto L_0x00e1
            com.google.android.gms.internal.measurement.zzes r4 = r12.zzf(r1)
            java.lang.String r7 = r4.zze()
            java.lang.String[] r10 = com.google.android.gms.measurement.internal.zzgu.zza
            java.lang.String[] r11 = com.google.android.gms.measurement.internal.zzgu.zzb
            java.lang.String r7 = com.google.android.gms.measurement.internal.zzig.zzb(r7, r10, r11)
            if (r7 == 0) goto L_0x00de
            com.google.android.gms.internal.measurement.zzjt r4 = r4.zzbv()
            com.google.android.gms.internal.measurement.zzer r4 = (com.google.android.gms.internal.measurement.zzer) r4
            r4.zza(r7)
            r12.zzd(r1, r4)
            com.google.android.gms.internal.measurement.zzjx r4 = r9.zzaA()
            com.google.android.gms.internal.measurement.zzeh r4 = (com.google.android.gms.internal.measurement.zzeh) r4
            r3.set(r8, r4)
            r12 = r9
        L_0x00de:
            int r1 = r1 + 1
            goto L_0x00b0
        L_0x00e1:
            int r8 = r8 + 1
            r1 = r23
            r4 = r17
            goto L_0x0012
        L_0x00e9:
            r17 = r4
            r23.zzY()
            r23.zzg()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r25)
            android.database.sqlite.SQLiteDatabase r1 = r23.zzh()
            r1.beginTransaction()
            r23.zzY()     // Catch:{ all -> 0x04c4 }
            r23.zzg()     // Catch:{ all -> 0x04c4 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)     // Catch:{ all -> 0x04c4 }
            android.database.sqlite.SQLiteDatabase r4 = r23.zzh()     // Catch:{ all -> 0x04c4 }
            r7 = 1
            java.lang.String[] r8 = new java.lang.String[r7]     // Catch:{ all -> 0x04c4 }
            r7 = 0
            r8[r7] = r2     // Catch:{ all -> 0x04c4 }
            r4.delete(r6, r0, r8)     // Catch:{ all -> 0x04c4 }
            r7 = 1
            java.lang.String[] r8 = new java.lang.String[r7]     // Catch:{ all -> 0x04c4 }
            r7 = 0
            r8[r7] = r2     // Catch:{ all -> 0x04c4 }
            r4.delete(r5, r0, r8)     // Catch:{ all -> 0x04c4 }
            java.util.Iterator r4 = r25.iterator()     // Catch:{ all -> 0x04c4 }
        L_0x0121:
            boolean r0 = r4.hasNext()     // Catch:{ all -> 0x04c4 }
            if (r0 == 0) goto L_0x03cd
            java.lang.Object r0 = r4.next()     // Catch:{ all -> 0x04c4 }
            com.google.android.gms.internal.measurement.zzeh r0 = (com.google.android.gms.internal.measurement.zzeh) r0     // Catch:{ all -> 0x04c4 }
            r23.zzY()     // Catch:{ all -> 0x04c4 }
            r23.zzg()     // Catch:{ all -> 0x04c4 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)     // Catch:{ all -> 0x04c4 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch:{ all -> 0x04c4 }
            boolean r9 = r0.zzk()     // Catch:{ all -> 0x04c4 }
            if (r9 != 0) goto L_0x0155
            r9 = r23
            com.google.android.gms.measurement.internal.zzfv r0 = r9.zzs     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzk()     // Catch:{ all -> 0x04c2 }
            java.lang.String r7 = "Audience with no ID. appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzel.zzn(r24)     // Catch:{ all -> 0x04c2 }
            r0.zzb(r7, r8)     // Catch:{ all -> 0x04c2 }
            goto L_0x0121
        L_0x0155:
            r9 = r23
            int r10 = r0.zza()     // Catch:{ all -> 0x04c2 }
            java.util.List r11 = r0.zzg()     // Catch:{ all -> 0x04c2 }
            java.util.Iterator r11 = r11.iterator()     // Catch:{ all -> 0x04c2 }
        L_0x0163:
            boolean r12 = r11.hasNext()     // Catch:{ all -> 0x04c2 }
            if (r12 == 0) goto L_0x018d
            java.lang.Object r12 = r11.next()     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.internal.measurement.zzej r12 = (com.google.android.gms.internal.measurement.zzej) r12     // Catch:{ all -> 0x04c2 }
            boolean r12 = r12.zzp()     // Catch:{ all -> 0x04c2 }
            if (r12 != 0) goto L_0x0163
            com.google.android.gms.measurement.internal.zzfv r0 = r9.zzs     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzk()     // Catch:{ all -> 0x04c2 }
            java.lang.String r7 = "Event filter with no ID. Audience definition ignored. appId, audienceId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzel.zzn(r24)     // Catch:{ all -> 0x04c2 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x04c2 }
            r0.zzc(r7, r8, r10)     // Catch:{ all -> 0x04c2 }
            goto L_0x0121
        L_0x018d:
            java.util.List r11 = r0.zzh()     // Catch:{ all -> 0x04c2 }
            java.util.Iterator r11 = r11.iterator()     // Catch:{ all -> 0x04c2 }
        L_0x0195:
            boolean r12 = r11.hasNext()     // Catch:{ all -> 0x04c2 }
            if (r12 == 0) goto L_0x01c0
            java.lang.Object r12 = r11.next()     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.internal.measurement.zzes r12 = (com.google.android.gms.internal.measurement.zzes) r12     // Catch:{ all -> 0x04c2 }
            boolean r12 = r12.zzj()     // Catch:{ all -> 0x04c2 }
            if (r12 != 0) goto L_0x0195
            com.google.android.gms.measurement.internal.zzfv r0 = r9.zzs     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzk()     // Catch:{ all -> 0x04c2 }
            java.lang.String r7 = "Property filter with no ID. Audience definition ignored. appId, audienceId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzel.zzn(r24)     // Catch:{ all -> 0x04c2 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x04c2 }
            r0.zzc(r7, r8, r10)     // Catch:{ all -> 0x04c2 }
            goto L_0x0121
        L_0x01c0:
            java.util.List r11 = r0.zzg()     // Catch:{ all -> 0x04c2 }
            java.util.Iterator r11 = r11.iterator()     // Catch:{ all -> 0x04c2 }
        L_0x01c8:
            boolean r12 = r11.hasNext()     // Catch:{ all -> 0x04c2 }
            java.lang.String r7 = "data"
            java.lang.String r13 = "session_scoped"
            java.lang.String r14 = "filter_id"
            java.lang.String r8 = "audience_id"
            java.lang.String r15 = "app_id"
            if (r12 == 0) goto L_0x02b2
            java.lang.Object r12 = r11.next()     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.internal.measurement.zzej r12 = (com.google.android.gms.internal.measurement.zzej) r12     // Catch:{ all -> 0x04c2 }
            r23.zzY()     // Catch:{ all -> 0x04c2 }
            r23.zzg()     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r12)     // Catch:{ all -> 0x04c2 }
            java.lang.String r21 = r12.zzg()     // Catch:{ all -> 0x04c2 }
            boolean r21 = android.text.TextUtils.isEmpty(r21)     // Catch:{ all -> 0x04c2 }
            if (r21 == 0) goto L_0x0226
            com.google.android.gms.measurement.internal.zzfv r0 = r9.zzs     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzk()     // Catch:{ all -> 0x04c2 }
            java.lang.String r7 = "Event filter had no event name. Audience definition ignored. appId, audienceId, filterId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzel.zzn(r24)     // Catch:{ all -> 0x04c2 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x04c2 }
            boolean r13 = r12.zzp()     // Catch:{ all -> 0x04c2 }
            if (r13 == 0) goto L_0x0219
            int r12 = r12.zzb()     // Catch:{ all -> 0x04c2 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ all -> 0x04c2 }
            r20 = r12
            goto L_0x021b
        L_0x0219:
            r20 = 0
        L_0x021b:
            java.lang.String r12 = java.lang.String.valueOf(r20)     // Catch:{ all -> 0x04c2 }
            r0.zzd(r7, r8, r11, r12)     // Catch:{ all -> 0x04c2 }
            r21 = r4
            goto L_0x038e
        L_0x0226:
            byte[] r3 = r12.zzbs()     // Catch:{ all -> 0x04c2 }
            r21 = r4
            android.content.ContentValues r4 = new android.content.ContentValues     // Catch:{ all -> 0x04c2 }
            r4.<init>()     // Catch:{ all -> 0x04c2 }
            r4.put(r15, r2)     // Catch:{ all -> 0x04c2 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x04c2 }
            r4.put(r8, r15)     // Catch:{ all -> 0x04c2 }
            boolean r8 = r12.zzp()     // Catch:{ all -> 0x04c2 }
            if (r8 == 0) goto L_0x024a
            int r8 = r12.zzb()     // Catch:{ all -> 0x04c2 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x04c2 }
            goto L_0x024b
        L_0x024a:
            r8 = 0
        L_0x024b:
            r4.put(r14, r8)     // Catch:{ all -> 0x04c2 }
            java.lang.String r8 = "event_name"
            java.lang.String r14 = r12.zzg()     // Catch:{ all -> 0x04c2 }
            r4.put(r8, r14)     // Catch:{ all -> 0x04c2 }
            boolean r8 = r12.zzq()     // Catch:{ all -> 0x04c2 }
            if (r8 == 0) goto L_0x0266
            boolean r8 = r12.zzn()     // Catch:{ all -> 0x04c2 }
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)     // Catch:{ all -> 0x04c2 }
            goto L_0x0267
        L_0x0266:
            r8 = 0
        L_0x0267:
            r4.put(r13, r8)     // Catch:{ all -> 0x04c2 }
            r4.put(r7, r3)     // Catch:{ all -> 0x04c2 }
            android.database.sqlite.SQLiteDatabase r3 = r23.zzh()     // Catch:{ SQLiteException -> 0x029c }
            r7 = 5
            r8 = 0
            long r3 = r3.insertWithOnConflict(r5, r8, r4, r7)     // Catch:{ SQLiteException -> 0x029c }
            r7 = -1
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r3 != 0) goto L_0x0296
            com.google.android.gms.measurement.internal.zzfv r3 = r9.zzs     // Catch:{ SQLiteException -> 0x029c }
            com.google.android.gms.measurement.internal.zzel r3 = r3.zzay()     // Catch:{ SQLiteException -> 0x029c }
            com.google.android.gms.measurement.internal.zzej r3 = r3.zzd()     // Catch:{ SQLiteException -> 0x029c }
            java.lang.String r4 = "Failed to insert event filter (got -1). appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzel.zzn(r24)     // Catch:{ SQLiteException -> 0x029c }
            r3.zzb(r4, r7)     // Catch:{ SQLiteException -> 0x029c }
            r3 = r25
            r4 = r21
            goto L_0x01c8
        L_0x0296:
            r3 = r25
            r4 = r21
            goto L_0x01c8
        L_0x029c:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfv r3 = r9.zzs     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.measurement.internal.zzel r3 = r3.zzay()     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.measurement.internal.zzej r3 = r3.zzd()     // Catch:{ all -> 0x04c2 }
            java.lang.String r4 = "Error storing event filter. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzel.zzn(r24)     // Catch:{ all -> 0x04c2 }
            r3.zzc(r4, r7, r0)     // Catch:{ all -> 0x04c2 }
            goto L_0x038e
        L_0x02b2:
            r21 = r4
            java.util.List r0 = r0.zzh()     // Catch:{ all -> 0x04c2 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x04c2 }
        L_0x02bc:
            boolean r3 = r0.hasNext()     // Catch:{ all -> 0x04c2 }
            if (r3 == 0) goto L_0x03c5
            java.lang.Object r3 = r0.next()     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.internal.measurement.zzes r3 = (com.google.android.gms.internal.measurement.zzes) r3     // Catch:{ all -> 0x04c2 }
            r23.zzY()     // Catch:{ all -> 0x04c2 }
            r23.zzg()     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)     // Catch:{ all -> 0x04c2 }
            java.lang.String r4 = r3.zze()     // Catch:{ all -> 0x04c2 }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x04c2 }
            if (r4 == 0) goto L_0x030b
            com.google.android.gms.measurement.internal.zzfv r0 = r9.zzs     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzk()     // Catch:{ all -> 0x04c2 }
            java.lang.String r4 = "Property filter had no property name. Audience definition ignored. appId, audienceId, filterId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzel.zzn(r24)     // Catch:{ all -> 0x04c2 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x04c2 }
            boolean r11 = r3.zzj()     // Catch:{ all -> 0x04c2 }
            if (r11 == 0) goto L_0x0301
            int r3 = r3.zza()     // Catch:{ all -> 0x04c2 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x04c2 }
            goto L_0x0302
        L_0x0301:
            r3 = 0
        L_0x0302:
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x04c2 }
            r0.zzd(r4, r7, r8, r3)     // Catch:{ all -> 0x04c2 }
            goto L_0x038e
        L_0x030b:
            byte[] r4 = r3.zzbs()     // Catch:{ all -> 0x04c2 }
            android.content.ContentValues r11 = new android.content.ContentValues     // Catch:{ all -> 0x04c2 }
            r11.<init>()     // Catch:{ all -> 0x04c2 }
            r11.put(r15, r2)     // Catch:{ all -> 0x04c2 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x04c2 }
            r11.put(r8, r12)     // Catch:{ all -> 0x04c2 }
            boolean r12 = r3.zzj()     // Catch:{ all -> 0x04c2 }
            if (r12 == 0) goto L_0x032d
            int r12 = r3.zza()     // Catch:{ all -> 0x04c2 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ all -> 0x04c2 }
            goto L_0x032e
        L_0x032d:
            r12 = 0
        L_0x032e:
            r11.put(r14, r12)     // Catch:{ all -> 0x04c2 }
            java.lang.String r12 = "property_name"
            r22 = r0
            java.lang.String r0 = r3.zze()     // Catch:{ all -> 0x04c2 }
            r11.put(r12, r0)     // Catch:{ all -> 0x04c2 }
            boolean r0 = r3.zzk()     // Catch:{ all -> 0x04c2 }
            if (r0 == 0) goto L_0x034b
            boolean r0 = r3.zzi()     // Catch:{ all -> 0x04c2 }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x04c2 }
            goto L_0x034c
        L_0x034b:
            r0 = 0
        L_0x034c:
            r11.put(r13, r0)     // Catch:{ all -> 0x04c2 }
            r11.put(r7, r4)     // Catch:{ all -> 0x04c2 }
            android.database.sqlite.SQLiteDatabase r0 = r23.zzh()     // Catch:{ SQLiteException -> 0x037a }
            r3 = 0
            r4 = 5
            long r11 = r0.insertWithOnConflict(r6, r3, r11, r4)     // Catch:{ SQLiteException -> 0x037a }
            r18 = -1
            int r0 = (r11 > r18 ? 1 : (r11 == r18 ? 0 : -1))
            if (r0 != 0) goto L_0x0376
            com.google.android.gms.measurement.internal.zzfv r0 = r9.zzs     // Catch:{ SQLiteException -> 0x037a }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ SQLiteException -> 0x037a }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzd()     // Catch:{ SQLiteException -> 0x037a }
            java.lang.String r3 = "Failed to insert property filter (got -1). appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzel.zzn(r24)     // Catch:{ SQLiteException -> 0x037a }
            r0.zzb(r3, r4)     // Catch:{ SQLiteException -> 0x037a }
            goto L_0x038e
        L_0x0376:
            r0 = r22
            goto L_0x02bc
        L_0x037a:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfv r3 = r9.zzs     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.measurement.internal.zzel r3 = r3.zzay()     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.measurement.internal.zzej r3 = r3.zzd()     // Catch:{ all -> 0x04c2 }
            java.lang.String r4 = "Error storing property filter. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzel.zzn(r24)     // Catch:{ all -> 0x04c2 }
            r3.zzc(r4, r7, r0)     // Catch:{ all -> 0x04c2 }
        L_0x038e:
            r23.zzY()     // Catch:{ all -> 0x04c2 }
            r23.zzg()     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)     // Catch:{ all -> 0x04c2 }
            android.database.sqlite.SQLiteDatabase r0 = r23.zzh()     // Catch:{ all -> 0x04c2 }
            r3 = 2
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ all -> 0x04c2 }
            r3 = 0
            r4[r3] = r2     // Catch:{ all -> 0x04c2 }
            java.lang.String r3 = java.lang.String.valueOf(r10)     // Catch:{ all -> 0x04c2 }
            r7 = 1
            r4[r7] = r3     // Catch:{ all -> 0x04c2 }
            r3 = r17
            r0.delete(r6, r3, r4)     // Catch:{ all -> 0x04c2 }
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ all -> 0x04c2 }
            r7 = 0
            r4[r7] = r2     // Catch:{ all -> 0x04c2 }
            java.lang.String r7 = java.lang.String.valueOf(r10)     // Catch:{ all -> 0x04c2 }
            r8 = 1
            r4[r8] = r7     // Catch:{ all -> 0x04c2 }
            r0.delete(r5, r3, r4)     // Catch:{ all -> 0x04c2 }
            r17 = r3
            r4 = r21
            r3 = r25
            goto L_0x0121
        L_0x03c5:
            r3 = r17
            r4 = r21
            r3 = r25
            goto L_0x0121
        L_0x03cd:
            r3 = 0
            r9 = r23
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x04c2 }
            r0.<init>()     // Catch:{ all -> 0x04c2 }
            java.util.Iterator r4 = r25.iterator()     // Catch:{ all -> 0x04c2 }
        L_0x03d9:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x04c2 }
            if (r5 == 0) goto L_0x03f9
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.internal.measurement.zzeh r5 = (com.google.android.gms.internal.measurement.zzeh) r5     // Catch:{ all -> 0x04c2 }
            boolean r6 = r5.zzk()     // Catch:{ all -> 0x04c2 }
            if (r6 == 0) goto L_0x03f4
            int r5 = r5.zza()     // Catch:{ all -> 0x04c2 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x04c2 }
            goto L_0x03f5
        L_0x03f4:
            r8 = r3
        L_0x03f5:
            r0.add(r8)     // Catch:{ all -> 0x04c2 }
            goto L_0x03d9
        L_0x03f9:
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)     // Catch:{ all -> 0x04c2 }
            r23.zzY()     // Catch:{ all -> 0x04c2 }
            r23.zzg()     // Catch:{ all -> 0x04c2 }
            android.database.sqlite.SQLiteDatabase r3 = r23.zzh()     // Catch:{ all -> 0x04c2 }
            r4 = 1
            java.lang.String[] r5 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x04a7 }
            r4 = 0
            r5[r4] = r2     // Catch:{ SQLiteException -> 0x04a7 }
            java.lang.String r4 = "select count(1) from audience_filter_values where app_id=?"
            long r4 = r9.zzab(r4, r5)     // Catch:{ SQLiteException -> 0x04a7 }
            com.google.android.gms.measurement.internal.zzfv r6 = r9.zzs     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.measurement.internal.zzaf r6 = r6.zzf()     // Catch:{ all -> 0x04c2 }
            r7 = 2000(0x7d0, float:2.803E-42)
            com.google.android.gms.measurement.internal.zzdx<java.lang.Integer> r8 = com.google.android.gms.measurement.internal.zzdy.zzE     // Catch:{ all -> 0x04c2 }
            int r6 = r6.zze(r2, r8)     // Catch:{ all -> 0x04c2 }
            int r6 = java.lang.Math.min(r7, r6)     // Catch:{ all -> 0x04c2 }
            r7 = 0
            int r6 = java.lang.Math.max(r7, r6)     // Catch:{ all -> 0x04c2 }
            long r7 = (long) r6     // Catch:{ all -> 0x04c2 }
            int r4 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r4 > 0) goto L_0x0430
            goto L_0x04bb
        L_0x0430:
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x04c2 }
            r4.<init>()     // Catch:{ all -> 0x04c2 }
            r7 = 0
        L_0x0436:
            int r5 = r0.size()     // Catch:{ all -> 0x04c2 }
            if (r7 >= r5) goto L_0x0452
            java.lang.Object r5 = r0.get(r7)     // Catch:{ all -> 0x04c2 }
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ all -> 0x04c2 }
            if (r5 == 0) goto L_0x04bb
            int r5 = r5.intValue()     // Catch:{ all -> 0x04c2 }
            java.lang.String r5 = java.lang.Integer.toString(r5)     // Catch:{ all -> 0x04c2 }
            r4.add(r5)     // Catch:{ all -> 0x04c2 }
            int r7 = r7 + 1
            goto L_0x0436
        L_0x0452:
            java.lang.String r0 = ","
            java.lang.String r0 = android.text.TextUtils.join(r0, r4)     // Catch:{ all -> 0x04c2 }
            java.lang.String r4 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x04c2 }
            int r4 = r4.length()     // Catch:{ all -> 0x04c2 }
            r5 = 2
            int r4 = r4 + r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x04c2 }
            r5.<init>(r4)     // Catch:{ all -> 0x04c2 }
            java.lang.String r4 = "("
            r5.append(r4)     // Catch:{ all -> 0x04c2 }
            r5.append(r0)     // Catch:{ all -> 0x04c2 }
            java.lang.String r0 = ")"
            r5.append(r0)     // Catch:{ all -> 0x04c2 }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x04c2 }
            java.lang.String r4 = "audience_filter_values"
            int r5 = r0.length()     // Catch:{ all -> 0x04c2 }
            int r5 = r5 + 140
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x04c2 }
            r7.<init>(r5)     // Catch:{ all -> 0x04c2 }
            java.lang.String r5 = "audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in "
            r7.append(r5)     // Catch:{ all -> 0x04c2 }
            r7.append(r0)     // Catch:{ all -> 0x04c2 }
            java.lang.String r0 = " order by rowid desc limit -1 offset ?)"
            r7.append(r0)     // Catch:{ all -> 0x04c2 }
            r5 = 2
            java.lang.String[] r0 = new java.lang.String[r5]     // Catch:{ all -> 0x04c2 }
            r5 = 0
            r0[r5] = r2     // Catch:{ all -> 0x04c2 }
            java.lang.String r2 = java.lang.Integer.toString(r6)     // Catch:{ all -> 0x04c2 }
            r5 = 1
            r0[r5] = r2     // Catch:{ all -> 0x04c2 }
            java.lang.String r2 = r7.toString()     // Catch:{ all -> 0x04c2 }
            r3.delete(r4, r2, r0)     // Catch:{ all -> 0x04c2 }
            goto L_0x04bb
        L_0x04a7:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfv r3 = r9.zzs     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.measurement.internal.zzel r3 = r3.zzay()     // Catch:{ all -> 0x04c2 }
            com.google.android.gms.measurement.internal.zzej r3 = r3.zzd()     // Catch:{ all -> 0x04c2 }
            java.lang.String r4 = "Database error querying filters. appId"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzel.zzn(r24)     // Catch:{ all -> 0x04c2 }
            r3.zzc(r4, r2, r0)     // Catch:{ all -> 0x04c2 }
        L_0x04bb:
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x04c2 }
            r1.endTransaction()
            return
        L_0x04c2:
            r0 = move-exception
            goto L_0x04c7
        L_0x04c4:
            r0 = move-exception
            r9 = r23
        L_0x04c7:
            r1.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaj.zzC(java.lang.String, java.util.List):void");
    }

    public final void zzD() {
        zzY();
        zzh().setTransactionSuccessful();
    }

    public final void zzE(zzg zzg2) {
        Preconditions.checkNotNull(zzg2);
        zzg();
        zzY();
        String zzt = zzg2.zzt();
        Preconditions.checkNotNull(zzt);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzt);
        contentValues.put("app_instance_id", zzg2.zzu());
        contentValues.put("gmp_app_id", zzg2.zzz());
        contentValues.put("resettable_device_id_hash", zzg2.zzB());
        contentValues.put("last_bundle_index", Long.valueOf(zzg2.zzo()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzg2.zzp()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzg2.zzn()));
        contentValues.put("app_version", zzg2.zzw());
        contentValues.put("app_store", zzg2.zzv());
        contentValues.put("gmp_version", Long.valueOf(zzg2.zzm()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzg2.zzj()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzg2.zzaj()));
        contentValues.put("day", Long.valueOf(zzg2.zzi()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzg2.zzg()));
        contentValues.put("daily_events_count", Long.valueOf(zzg2.zzf()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzg2.zzd()));
        contentValues.put("config_fetched_time", Long.valueOf(zzg2.zzc()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzg2.zzl()));
        contentValues.put("app_version_int", Long.valueOf(zzg2.zzb()));
        contentValues.put("firebase_instance_id", zzg2.zzx());
        contentValues.put("daily_error_events_count", Long.valueOf(zzg2.zze()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzg2.zzh()));
        contentValues.put("health_monitor_sample", zzg2.zzA());
        contentValues.put("android_id", Long.valueOf(zzg2.zza()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzg2.zzai()));
        contentValues.put("admob_app_id", zzg2.zzr());
        contentValues.put("dynamite_version", Long.valueOf(zzg2.zzk()));
        List<String> zzC = zzg2.zzC();
        if (zzC != null) {
            if (zzC.size() == 0) {
                this.zzs.zzay().zzk().zzb("Safelisted events should not be an empty list. appId", zzt);
            } else {
                contentValues.put("safelisted_events", TextUtils.join(",", zzC));
            }
        }
        zzom.zzc();
        if (this.zzs.zzf().zzs(zzt, zzdy.zzac)) {
            contentValues.put("ga_app_id", zzg2.zzy());
        }
        try {
            SQLiteDatabase zzh2 = zzh();
            if (((long) zzh2.update("apps", contentValues, "app_id = ?", new String[]{zzt})) == 0 && zzh2.insertWithOnConflict("apps", (String) null, contentValues, 5) == -1) {
                this.zzs.zzay().zzd().zzb("Failed to insert/update app (got -1). appId", zzel.zzn(zzt));
            }
        } catch (SQLiteException e) {
            this.zzs.zzay().zzd().zzc("Error storing app. appId", zzel.zzn(zzt), e);
        }
    }

    public final void zzF(zzap zzap) {
        long j;
        Preconditions.checkNotNull(zzap);
        zzg();
        zzY();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzap.zza);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzap.zzb);
        contentValues.put("lifetime_count", Long.valueOf(zzap.zzc));
        contentValues.put("current_bundle_count", Long.valueOf(zzap.zzd));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzap.zzf));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzap.zzg));
        contentValues.put("last_bundled_day", zzap.zzh);
        contentValues.put("last_sampled_complex_event_id", zzap.zzi);
        contentValues.put("last_sampling_rate", zzap.zzj);
        contentValues.put("current_session_count", Long.valueOf(zzap.zze));
        Boolean bool = zzap.zzk;
        if (bool == null || !bool.booleanValue()) {
            j = null;
        } else {
            j = 1L;
        }
        contentValues.put("last_exempt_from_sampling", j);
        try {
            if (zzh().insertWithOnConflict("events", (String) null, contentValues, 5) == -1) {
                this.zzs.zzay().zzd().zzb("Failed to insert/update event aggregates (got -1). appId", zzel.zzn(zzap.zza));
            }
        } catch (SQLiteException e) {
            this.zzs.zzay().zzd().zzc("Error storing event aggregates. appId", zzel.zzn(zzap.zza), e);
        }
    }

    public final void zzG(String str, byte[] bArr, String str2) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzY();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        contentValues.put("config_last_modified_time", str2);
        try {
            if (((long) zzh().update("apps", contentValues, "app_id = ?", new String[]{str})) == 0) {
                this.zzs.zzay().zzd().zzb("Failed to update remote config (got 0). appId", zzel.zzn(str));
            }
        } catch (SQLiteException e) {
            this.zzs.zzay().zzd().zzc("Error storing remote config. appId", zzel.zzn(str), e);
        }
    }

    public final boolean zzH() {
        return zzab("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public final boolean zzI() {
        return zzab("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    public final boolean zzJ() {
        return zzab("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    /* access modifiers changed from: protected */
    public final boolean zzK() {
        Context zzau = this.zzs.zzau();
        this.zzs.zzf();
        return zzau.getDatabasePath("google_app_measurement.db").exists();
    }

    public final boolean zzL(String str, Long l, long j, zzfo zzfo) {
        zzg();
        zzY();
        Preconditions.checkNotNull(zzfo);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        byte[] zzbs = zzfo.zzbs();
        this.zzs.zzay().zzj().zzc("Saving complex main event, appId, data size", this.zzs.zzj().zzd(str), Integer.valueOf(zzbs.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l);
        contentValues.put("children_to_process", Long.valueOf(j));
        contentValues.put("main_event", zzbs);
        try {
            if (zzh().insertWithOnConflict("main_event_params", (String) null, contentValues, 5) != -1) {
                return true;
            }
            this.zzs.zzay().zzd().zzb("Failed to insert complex main event (got -1). appId", zzel.zzn(str));
            return false;
        } catch (SQLiteException e) {
            this.zzs.zzay().zzd().zzc("Error storing complex main event. appId", zzel.zzn(str), e);
            return false;
        }
    }

    public final boolean zzM(zzab zzab) {
        Preconditions.checkNotNull(zzab);
        zzg();
        zzY();
        String str = zzab.zza;
        Preconditions.checkNotNull(str);
        if (zzp(str, zzab.zzc.zzb) == null) {
            long zzab2 = zzab("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{str});
            this.zzs.zzf();
            if (zzab2 >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("origin", zzab.zzb);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzab.zzc.zzb);
        zzX(contentValues, "value", Preconditions.checkNotNull(zzab.zzc.zza()));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.valueOf(zzab.zze));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, zzab.zzf);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzab.zzh));
        contentValues.put("timed_out_event", this.zzs.zzv().zzan(zzab.zzg));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzab.zzd));
        contentValues.put("triggered_event", this.zzs.zzv().zzan(zzab.zzi));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzab.zzc.zzc));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzab.zzj));
        contentValues.put("expired_event", this.zzs.zzv().zzan(zzab.zzk));
        try {
            if (zzh().insertWithOnConflict("conditional_properties", (String) null, contentValues, 5) == -1) {
                this.zzs.zzay().zzd().zzb("Failed to insert/update conditional user property (got -1)", zzel.zzn(str));
            }
        } catch (SQLiteException e) {
            this.zzs.zzay().zzd().zzc("Error storing conditional user property", zzel.zzn(str), e);
        }
        return true;
    }

    public final boolean zzN(zzkx zzkx) {
        Preconditions.checkNotNull(zzkx);
        zzg();
        zzY();
        if (zzp(zzkx.zza, zzkx.zzc) == null) {
            if (zzkz.zzah(zzkx.zzc)) {
                if (zzab("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzkx.zza}) >= ((long) this.zzs.zzf().zzf(zzkx.zza, zzdy.zzF, 25, 100))) {
                    return false;
                }
            } else if (!"_npa".equals(zzkx.zzc)) {
                long zzab = zzab("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzkx.zza, zzkx.zzb});
                this.zzs.zzf();
                if (zzab >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzkx.zza);
        contentValues.put("origin", zzkx.zzb);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzkx.zzc);
        contentValues.put("set_timestamp", Long.valueOf(zzkx.zzd));
        zzX(contentValues, "value", zzkx.zze);
        try {
            if (zzh().insertWithOnConflict("user_attributes", (String) null, contentValues, 5) == -1) {
                this.zzs.zzay().zzd().zzb("Failed to insert/update user property (got -1). appId", zzel.zzn(zzkx.zza));
            }
        } catch (SQLiteException e) {
            this.zzs.zzay().zzd().zzc("Error storing user property. appId", zzel.zzn(zzkx.zza), e);
        }
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v14, resolved type: java.lang.String[]} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: type inference failed for: r3v8 */
    /* JADX WARNING: type inference failed for: r3v9 */
    /* JADX WARNING: type inference failed for: r3v10 */
    /* JADX WARNING: type inference failed for: r3v12 */
    /* JADX WARNING: type inference failed for: r3v13 */
    /* JADX WARNING: type inference failed for: r3v14 */
    /* JADX WARNING: type inference failed for: r3v15 */
    /* JADX WARNING: type inference failed for: r3v16 */
    /* JADX WARNING: type inference failed for: r3v17 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0254  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x025c  */
    /* JADX WARNING: Removed duplicated region for block: B:141:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:12:0x0040=Splitter:B:12:0x0040, B:32:0x009d=Splitter:B:32:0x009d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzW(java.lang.String r21, long r22, long r24, com.google.android.gms.measurement.internal.zzkr r26) {
        /*
            r20 = this;
            r1 = r20
            r2 = r26
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r26)
            r20.zzg()
            r20.zzY()
            r3 = 0
            android.database.sqlite.SQLiteDatabase r0 = r20.zzh()     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            java.lang.String r5 = ""
            r13 = -1
            r15 = 2
            r12 = 1
            r11 = 0
            if (r4 == 0) goto L_0x0084
            int r4 = (r24 > r13 ? 1 : (r24 == r13 ? 0 : -1))
            if (r4 == 0) goto L_0x0032
            java.lang.String[] r6 = new java.lang.String[r15]     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            java.lang.String r7 = java.lang.String.valueOf(r24)     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            r6[r11] = r7     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            java.lang.String r7 = java.lang.String.valueOf(r22)     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            r6[r12] = r7     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            goto L_0x003a
        L_0x0032:
            java.lang.String[] r6 = new java.lang.String[r12]     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            java.lang.String r7 = java.lang.String.valueOf(r22)     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            r6[r11] = r7     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
        L_0x003a:
            if (r4 == 0) goto L_0x003f
            java.lang.String r5 = "rowid <= ? and "
            goto L_0x0040
        L_0x003f:
        L_0x0040:
            int r4 = r5.length()     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            int r4 = r4 + 148
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            r7.<init>(r4)     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            java.lang.String r4 = "select app_id, metadata_fingerprint from raw_events where "
            r7.append(r4)     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            r7.append(r5)     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            java.lang.String r4 = "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;"
            r7.append(r4)     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            java.lang.String r4 = r7.toString()     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            android.database.Cursor r4 = r0.rawQuery(r4, r6)     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            boolean r5 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x0081, all -> 0x007d }
            if (r5 != 0) goto L_0x006c
            if (r4 == 0) goto L_0x006b
            r4.close()
        L_0x006b:
            return
        L_0x006c:
            java.lang.String r3 = r4.getString(r11)     // Catch:{ SQLiteException -> 0x0081, all -> 0x007d }
            java.lang.String r5 = r4.getString(r12)     // Catch:{ SQLiteException -> 0x0081, all -> 0x007d }
            r4.close()     // Catch:{ SQLiteException -> 0x0081, all -> 0x007d }
            r16 = r4
            r17 = r5
            goto L_0x00d5
        L_0x007d:
            r0 = move-exception
            r3 = r4
            goto L_0x025a
        L_0x0081:
            r0 = move-exception
            goto L_0x023f
        L_0x0084:
            int r4 = (r24 > r13 ? 1 : (r24 == r13 ? 0 : -1))
            if (r4 == 0) goto L_0x0093
            java.lang.String[] r6 = new java.lang.String[r15]     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            r6[r11] = r3     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            java.lang.String r7 = java.lang.String.valueOf(r24)     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            r6[r12] = r7     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            goto L_0x0097
        L_0x0093:
            java.lang.String[] r6 = new java.lang.String[]{r3}     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
        L_0x0097:
            if (r4 == 0) goto L_0x009c
            java.lang.String r5 = " and rowid <= ?"
            goto L_0x009d
        L_0x009c:
        L_0x009d:
            int r4 = r5.length()     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            int r4 = r4 + 84
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            r7.<init>(r4)     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            java.lang.String r4 = "select metadata_fingerprint from raw_events where app_id = ?"
            r7.append(r4)     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            r7.append(r5)     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            java.lang.String r4 = " order by rowid limit 1;"
            r7.append(r4)     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            java.lang.String r4 = r7.toString()     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            android.database.Cursor r4 = r0.rawQuery(r4, r6)     // Catch:{ SQLiteException -> 0x023d, all -> 0x023b }
            boolean r5 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x0239 }
            if (r5 != 0) goto L_0x00c9
            if (r4 == 0) goto L_0x00c8
            r4.close()
        L_0x00c8:
            return
        L_0x00c9:
            java.lang.String r5 = r4.getString(r11)     // Catch:{ SQLiteException -> 0x0239 }
            r4.close()     // Catch:{ SQLiteException -> 0x0239 }
            r16 = r4
            r17 = r5
        L_0x00d5:
            java.lang.String r4 = "metadata"
            java.lang.String[] r6 = new java.lang.String[]{r4}     // Catch:{ SQLiteException -> 0x0235, all -> 0x0231 }
            java.lang.String[] r8 = new java.lang.String[r15]     // Catch:{ SQLiteException -> 0x0235, all -> 0x0231 }
            r8[r11] = r3     // Catch:{ SQLiteException -> 0x0235, all -> 0x0231 }
            r8[r12] = r17     // Catch:{ SQLiteException -> 0x0235, all -> 0x0231 }
            java.lang.String r5 = "raw_events_metadata"
            java.lang.String r7 = "app_id = ? and metadata_fingerprint = ?"
            r9 = 0
            r10 = 0
            java.lang.String r18 = "rowid"
            java.lang.String r19 = "2"
            r4 = r0
            r15 = r11
            r11 = r18
            r12 = r19
            android.database.Cursor r12 = r4.query(r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ SQLiteException -> 0x0235, all -> 0x0231 }
            boolean r4 = r12.moveToFirst()     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            if (r4 != 0) goto L_0x0114
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzs     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzd()     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            java.lang.String r2 = "Raw event metadata record is missing. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzel.zzn(r3)     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            r0.zzb(r2, r4)     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            if (r12 == 0) goto L_0x0113
            r12.close()
        L_0x0113:
            return
        L_0x0114:
            byte[] r4 = r12.getBlob(r15)     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            com.google.android.gms.internal.measurement.zzfx r5 = com.google.android.gms.internal.measurement.zzfy.zzu()     // Catch:{ IOException -> 0x0204 }
            com.google.android.gms.internal.measurement.zzlb r4 = com.google.android.gms.measurement.internal.zzku.zzl(r5, r4)     // Catch:{ IOException -> 0x0204 }
            com.google.android.gms.internal.measurement.zzfx r4 = (com.google.android.gms.internal.measurement.zzfx) r4     // Catch:{ IOException -> 0x0204 }
            com.google.android.gms.internal.measurement.zzjx r4 = r4.zzaA()     // Catch:{ IOException -> 0x0204 }
            com.google.android.gms.internal.measurement.zzfy r4 = (com.google.android.gms.internal.measurement.zzfy) r4     // Catch:{ IOException -> 0x0204 }
            boolean r5 = r12.moveToNext()     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            if (r5 == 0) goto L_0x0142
            com.google.android.gms.measurement.internal.zzfv r5 = r1.zzs     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            com.google.android.gms.measurement.internal.zzel r5 = r5.zzay()     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            com.google.android.gms.measurement.internal.zzej r5 = r5.zzk()     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            java.lang.String r6 = "Get multiple raw event metadata records, expected one. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzel.zzn(r3)     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            r5.zzb(r6, r7)     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
        L_0x0142:
            r12.close()     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            r2.zza = r4     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            int r4 = (r24 > r13 ? 1 : (r24 == r13 ? 0 : -1))
            r13 = 3
            if (r4 == 0) goto L_0x0162
            java.lang.String r4 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?"
            java.lang.String[] r5 = new java.lang.String[r13]     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            r5[r15] = r3     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            r14 = 1
            r5[r14] = r17     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            java.lang.String r6 = java.lang.String.valueOf(r24)     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            r7 = 2
            r5[r7] = r6     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            r7 = r4
            r8 = r5
            goto L_0x016e
        L_0x0162:
            r14 = 1
            java.lang.String r4 = "app_id = ? and metadata_fingerprint = ?"
            r5 = 2
            java.lang.String[] r6 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            r6[r15] = r3     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            r6[r14] = r17     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            r7 = r4
            r8 = r6
        L_0x016e:
            java.lang.String r4 = "rowid"
            java.lang.String r5 = "name"
            java.lang.String r6 = "timestamp"
            java.lang.String r9 = "data"
            java.lang.String[] r6 = new java.lang.String[]{r4, r5, r6, r9}     // Catch:{ SQLiteException -> 0x022b, all -> 0x0225 }
            java.lang.String r5 = "raw_events"
            r9 = 0
            r10 = 0
            java.lang.String r11 = "rowid"
            r16 = 0
            r4 = r0
            r17 = r12
            r12 = r16
            android.database.Cursor r4 = r4.query(r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ SQLiteException -> 0x0223, all -> 0x0221 }
            boolean r0 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
            if (r0 == 0) goto L_0x01e6
        L_0x0191:
            long r5 = r4.getLong(r15)     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
            byte[] r0 = r4.getBlob(r13)     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
            com.google.android.gms.internal.measurement.zzfn r7 = com.google.android.gms.internal.measurement.zzfo.zze()     // Catch:{ IOException -> 0x01c5 }
            com.google.android.gms.internal.measurement.zzlb r0 = com.google.android.gms.measurement.internal.zzku.zzl(r7, r0)     // Catch:{ IOException -> 0x01c5 }
            com.google.android.gms.internal.measurement.zzfn r0 = (com.google.android.gms.internal.measurement.zzfn) r0     // Catch:{ IOException -> 0x01c5 }
            java.lang.String r7 = r4.getString(r14)     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
            r0.zzi(r7)     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
            r7 = 2
            long r8 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
            r0.zzm(r8)     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
            com.google.android.gms.internal.measurement.zzjx r0 = r0.zzaA()     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
            com.google.android.gms.internal.measurement.zzfo r0 = (com.google.android.gms.internal.measurement.zzfo) r0     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
            boolean r0 = r2.zza(r5, r0)     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
            if (r0 != 0) goto L_0x01da
            if (r4 == 0) goto L_0x01c4
            r4.close()
        L_0x01c4:
            return
        L_0x01c5:
            r0 = move-exception
            r7 = 2
            com.google.android.gms.measurement.internal.zzfv r5 = r1.zzs     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
            com.google.android.gms.measurement.internal.zzel r5 = r5.zzay()     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
            com.google.android.gms.measurement.internal.zzej r5 = r5.zzd()     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
            java.lang.String r6 = "Data loss. Failed to merge raw event. appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzel.zzn(r3)     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
            r5.zzc(r6, r8, r0)     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
        L_0x01da:
            boolean r0 = r4.moveToNext()     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
            if (r0 != 0) goto L_0x0191
            if (r4 == 0) goto L_0x0257
            r4.close()
            return
        L_0x01e6:
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzs     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzk()     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
            java.lang.String r2 = "Raw event data disappeared while in transaction. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzel.zzn(r3)     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
            r0.zzb(r2, r5)     // Catch:{ SQLiteException -> 0x0202, all -> 0x01ff }
            if (r4 == 0) goto L_0x01fe
            r4.close()
        L_0x01fe:
            return
        L_0x01ff:
            r0 = move-exception
            r3 = r4
            goto L_0x025a
        L_0x0202:
            r0 = move-exception
            goto L_0x023f
        L_0x0204:
            r0 = move-exception
            r17 = r12
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzs     // Catch:{ SQLiteException -> 0x0223, all -> 0x0221 }
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()     // Catch:{ SQLiteException -> 0x0223, all -> 0x0221 }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()     // Catch:{ SQLiteException -> 0x0223, all -> 0x0221 }
            java.lang.String r4 = "Data loss. Failed to merge raw event metadata. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzel.zzn(r3)     // Catch:{ SQLiteException -> 0x0223, all -> 0x0221 }
            r2.zzc(r4, r5, r0)     // Catch:{ SQLiteException -> 0x0223, all -> 0x0221 }
            if (r17 == 0) goto L_0x0220
            r17.close()
            return
        L_0x0220:
            return
        L_0x0221:
            r0 = move-exception
            goto L_0x0228
        L_0x0223:
            r0 = move-exception
            goto L_0x022e
        L_0x0225:
            r0 = move-exception
            r17 = r12
        L_0x0228:
            r3 = r17
            goto L_0x025a
        L_0x022b:
            r0 = move-exception
            r17 = r12
        L_0x022e:
            r4 = r17
            goto L_0x023f
        L_0x0231:
            r0 = move-exception
            r3 = r16
            goto L_0x025a
        L_0x0235:
            r0 = move-exception
            r4 = r16
            goto L_0x023f
        L_0x0239:
            r0 = move-exception
            goto L_0x023f
        L_0x023b:
            r0 = move-exception
            goto L_0x025a
        L_0x023d:
            r0 = move-exception
            r4 = r3
        L_0x023f:
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzs     // Catch:{ all -> 0x0258 }
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()     // Catch:{ all -> 0x0258 }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()     // Catch:{ all -> 0x0258 }
            java.lang.String r5 = "Data loss. Error selecting raw event. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzel.zzn(r3)     // Catch:{ all -> 0x0258 }
            r2.zzc(r5, r3, r0)     // Catch:{ all -> 0x0258 }
            if (r4 == 0) goto L_0x0257
            r4.close()
        L_0x0257:
            return
        L_0x0258:
            r0 = move-exception
            r3 = r4
        L_0x025a:
            if (r3 == 0) goto L_0x025f
            r3.close()
        L_0x025f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaj.zzW(java.lang.String, long, long, com.google.android.gms.measurement.internal.zzkr):void");
    }

    public final int zza(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzY();
        try {
            return zzh().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            this.zzs.zzay().zzd().zzd("Error deleting conditional property", zzel.zzn(str), this.zzs.zzj().zzf(str2), e);
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zzb() {
        return false;
    }

    /* access modifiers changed from: protected */
    public final long zzc(String str, String str2) {
        long j;
        SQLiteException e;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty("first_open_count");
        zzg();
        zzY();
        SQLiteDatabase zzh2 = zzh();
        zzh2.beginTransaction();
        try {
            StringBuilder sb = new StringBuilder(48);
            sb.append("select ");
            sb.append("first_open_count");
            sb.append(" from app2 where app_id=?");
            j = zzac(sb.toString(), new String[]{str}, -1);
            if (j == -1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", 0);
                contentValues.put("previous_install_count", 0);
                if (zzh2.insertWithOnConflict("app2", (String) null, contentValues, 5) == -1) {
                    this.zzs.zzay().zzd().zzc("Failed to insert column (got -1). appId", zzel.zzn(str), "first_open_count");
                    zzh2.endTransaction();
                    return -1;
                }
                j = 0;
            }
            try {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("app_id", str);
                contentValues2.put("first_open_count", Long.valueOf(1 + j));
                if (((long) zzh2.update("app2", contentValues2, "app_id = ?", new String[]{str})) == 0) {
                    this.zzs.zzay().zzd().zzc("Failed to update column (got 0). appId", zzel.zzn(str), "first_open_count");
                    zzh2.endTransaction();
                    return -1;
                }
                zzh2.setTransactionSuccessful();
                zzh2.endTransaction();
                return j;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    this.zzs.zzay().zzd().zzd("Error inserting column. appId", zzel.zzn(str), "first_open_count", e);
                    zzh2.endTransaction();
                    return j;
                } catch (Throwable th) {
                    zzh2.endTransaction();
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            j = 0;
            e = e3;
            this.zzs.zzay().zzd().zzd("Error inserting column. appId", zzel.zzn(str), "first_open_count", e);
            zzh2.endTransaction();
            return j;
        }
    }

    public final long zzd() {
        return zzac("select max(bundle_end_timestamp) from queue", (String[]) null, 0);
    }

    public final long zze() {
        return zzac("select max(timestamp) from raw_events", (String[]) null, 0);
    }

    public final long zzf(String str) {
        Preconditions.checkNotEmpty(str);
        return zzac("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    /* access modifiers changed from: package-private */
    public final SQLiteDatabase zzh() {
        zzg();
        try {
            return this.zzj.getWritableDatabase();
        } catch (SQLiteException e) {
            this.zzs.zzay().zzk().zzb("Error opening database", e);
            throw e;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00e3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Bundle zzi(java.lang.String r8) {
        /*
            r7 = this;
            r7.zzg()
            r7.zzY()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r7.zzh()     // Catch:{ SQLiteException -> 0x00c8, all -> 0x00c6 }
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x00c8, all -> 0x00c6 }
            r3 = 0
            r2[r3] = r8     // Catch:{ SQLiteException -> 0x00c8, all -> 0x00c6 }
            java.lang.String r4 = "select parameters from default_event_params where app_id=?"
            android.database.Cursor r1 = r1.rawQuery(r4, r2)     // Catch:{ SQLiteException -> 0x00c8, all -> 0x00c6 }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            if (r2 != 0) goto L_0x0032
            com.google.android.gms.measurement.internal.zzfv r8 = r7.zzs     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            com.google.android.gms.measurement.internal.zzel r8 = r8.zzay()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            com.google.android.gms.measurement.internal.zzej r8 = r8.zzj()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            java.lang.String r2 = "Default event parameters not found"
            r8.zza(r2)     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            if (r1 == 0) goto L_0x0031
            r1.close()
        L_0x0031:
            return r0
        L_0x0032:
            byte[] r2 = r1.getBlob(r3)     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            com.google.android.gms.internal.measurement.zzfn r3 = com.google.android.gms.internal.measurement.zzfo.zze()     // Catch:{ IOException -> 0x00a7 }
            com.google.android.gms.internal.measurement.zzlb r2 = com.google.android.gms.measurement.internal.zzku.zzl(r3, r2)     // Catch:{ IOException -> 0x00a7 }
            com.google.android.gms.internal.measurement.zzfn r2 = (com.google.android.gms.internal.measurement.zzfn) r2     // Catch:{ IOException -> 0x00a7 }
            com.google.android.gms.internal.measurement.zzjx r2 = r2.zzaA()     // Catch:{ IOException -> 0x00a7 }
            com.google.android.gms.internal.measurement.zzfo r2 = (com.google.android.gms.internal.measurement.zzfo) r2     // Catch:{ IOException -> 0x00a7 }
            com.google.android.gms.measurement.internal.zzks r8 = r7.zzf     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            r8.zzu()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            java.util.List r8 = r2.zzi()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            android.os.Bundle r2 = new android.os.Bundle     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            r2.<init>()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
        L_0x0059:
            boolean r3 = r8.hasNext()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            if (r3 == 0) goto L_0x00a1
            java.lang.Object r3 = r8.next()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            com.google.android.gms.internal.measurement.zzfs r3 = (com.google.android.gms.internal.measurement.zzfs) r3     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            java.lang.String r4 = r3.zzg()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            boolean r5 = r3.zzu()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            if (r5 == 0) goto L_0x0077
            double r5 = r3.zza()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            r2.putDouble(r4, r5)     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            goto L_0x0059
        L_0x0077:
            boolean r5 = r3.zzv()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            if (r5 == 0) goto L_0x0085
            float r3 = r3.zzb()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            r2.putFloat(r4, r3)     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            goto L_0x0059
        L_0x0085:
            boolean r5 = r3.zzy()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            if (r5 == 0) goto L_0x0093
            java.lang.String r3 = r3.zzh()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            r2.putString(r4, r3)     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            goto L_0x0059
        L_0x0093:
            boolean r5 = r3.zzw()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            if (r5 == 0) goto L_0x0059
            long r5 = r3.zzd()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            r2.putLong(r4, r5)     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            goto L_0x0059
        L_0x00a1:
            if (r1 == 0) goto L_0x00a6
            r1.close()
        L_0x00a6:
            return r2
        L_0x00a7:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzfv r3 = r7.zzs     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            com.google.android.gms.measurement.internal.zzel r3 = r3.zzay()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            com.google.android.gms.measurement.internal.zzej r3 = r3.zzd()     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            java.lang.String r4 = "Failed to retrieve default event parameters. appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzel.zzn(r8)     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            r3.zzc(r4, r8, r2)     // Catch:{ SQLiteException -> 0x00c4, all -> 0x00c1 }
            if (r1 == 0) goto L_0x00c0
            r1.close()
        L_0x00c0:
            return r0
        L_0x00c1:
            r8 = move-exception
            r0 = r1
            goto L_0x00e1
        L_0x00c4:
            r8 = move-exception
            goto L_0x00ca
        L_0x00c6:
            r8 = move-exception
            goto L_0x00e1
        L_0x00c8:
            r8 = move-exception
            r1 = r0
        L_0x00ca:
            com.google.android.gms.measurement.internal.zzfv r2 = r7.zzs     // Catch:{ all -> 0x00df }
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()     // Catch:{ all -> 0x00df }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()     // Catch:{ all -> 0x00df }
            java.lang.String r3 = "Error selecting default event parameters"
            r2.zzb(r3, r8)     // Catch:{ all -> 0x00df }
            if (r1 == 0) goto L_0x00de
            r1.close()
        L_0x00de:
            return r0
        L_0x00df:
            r8 = move-exception
            r0 = r1
        L_0x00e1:
            if (r0 == 0) goto L_0x00e6
            r0.close()
        L_0x00e6:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaj.zzi(java.lang.String):android.os.Bundle");
    }

    /* JADX WARNING: Removed duplicated region for block: B:57:0x020f  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0217  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzg zzj(java.lang.String r34) {
        /*
            r33 = this;
            r1 = r33
            r2 = r34
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r34)
            r33.zzg()
            r33.zzY()
            r3 = 0
            android.database.sqlite.SQLiteDatabase r4 = r33.zzh()     // Catch:{ SQLiteException -> 0x01f8, all -> 0x01f6 }
            java.lang.String r5 = "app_instance_id"
            java.lang.String r6 = "gmp_app_id"
            java.lang.String r7 = "resettable_device_id_hash"
            java.lang.String r8 = "last_bundle_index"
            java.lang.String r9 = "last_bundle_start_timestamp"
            java.lang.String r10 = "last_bundle_end_timestamp"
            java.lang.String r11 = "app_version"
            java.lang.String r12 = "app_store"
            java.lang.String r13 = "gmp_version"
            java.lang.String r14 = "dev_cert_hash"
            java.lang.String r15 = "measurement_enabled"
            java.lang.String r16 = "day"
            java.lang.String r17 = "daily_public_events_count"
            java.lang.String r18 = "daily_events_count"
            java.lang.String r19 = "daily_conversions_count"
            java.lang.String r20 = "config_fetched_time"
            java.lang.String r21 = "failed_config_fetch_time"
            java.lang.String r22 = "app_version_int"
            java.lang.String r23 = "firebase_instance_id"
            java.lang.String r24 = "daily_error_events_count"
            java.lang.String r25 = "daily_realtime_events_count"
            java.lang.String r26 = "health_monitor_sample"
            java.lang.String r27 = "android_id"
            java.lang.String r28 = "adid_reporting_enabled"
            java.lang.String r29 = "admob_app_id"
            java.lang.String r30 = "dynamite_version"
            java.lang.String r31 = "safelisted_events"
            java.lang.String r32 = "ga_app_id"
            java.lang.String[] r6 = new java.lang.String[]{r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32}     // Catch:{ SQLiteException -> 0x01f8, all -> 0x01f6 }
            r0 = 1
            java.lang.String[] r8 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x01f8, all -> 0x01f6 }
            r12 = 0
            r8[r12] = r2     // Catch:{ SQLiteException -> 0x01f8, all -> 0x01f6 }
            java.lang.String r5 = "apps"
            java.lang.String r7 = "app_id=?"
            r9 = 0
            r10 = 0
            r11 = 0
            android.database.Cursor r4 = r4.query(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x01f8, all -> 0x01f6 }
            boolean r5 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x01f4 }
            if (r5 != 0) goto L_0x006b
            if (r4 == 0) goto L_0x006a
            r4.close()
        L_0x006a:
            return r3
        L_0x006b:
            com.google.android.gms.measurement.internal.zzg r5 = new com.google.android.gms.measurement.internal.zzg     // Catch:{ SQLiteException -> 0x01f4 }
            com.google.android.gms.measurement.internal.zzks r6 = r1.zzf     // Catch:{ SQLiteException -> 0x01f4 }
            com.google.android.gms.measurement.internal.zzfv r6 = r6.zzq()     // Catch:{ SQLiteException -> 0x01f4 }
            r5.<init>(r6, r2)     // Catch:{ SQLiteException -> 0x01f4 }
            java.lang.String r6 = r4.getString(r12)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzI(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            java.lang.String r6 = r4.getString(r0)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzY(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r6 = 2
            java.lang.String r6 = r4.getString(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzag(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r6 = 3
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzac(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r6 = 4
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzad(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r6 = 5
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzab(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r6 = 6
            java.lang.String r6 = r4.getString(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzK(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r6 = 7
            java.lang.String r6 = r4.getString(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzJ(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r6 = 8
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzZ(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r6 = 9
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzT(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r6 = 10
            boolean r7 = r4.isNull(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            if (r7 != 0) goto L_0x00d8
            int r6 = r4.getInt(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            if (r6 == 0) goto L_0x00d6
            r6 = r0
            goto L_0x00d9
        L_0x00d6:
            r6 = r12
            goto L_0x00d9
        L_0x00d8:
            r6 = r0
        L_0x00d9:
            r5.zzae(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r6 = 11
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzS(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r6 = 12
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzQ(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r6 = 13
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzP(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r6 = 14
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzN(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r6 = 15
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzM(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r6 = 16
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzV(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r6 = 17
            boolean r7 = r4.isNull(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            if (r7 == 0) goto L_0x011e
            r6 = -2147483648(0xffffffff80000000, double:NaN)
            goto L_0x0124
        L_0x011e:
            int r6 = r4.getInt(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            long r6 = (long) r6     // Catch:{ SQLiteException -> 0x01f4 }
        L_0x0124:
            r5.zzL(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r6 = 18
            java.lang.String r6 = r4.getString(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzW(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r6 = 19
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzO(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r6 = 20
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzR(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r6 = 21
            java.lang.String r6 = r4.getString(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzaa(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            com.google.android.gms.measurement.internal.zzfv r6 = r1.zzs     // Catch:{ SQLiteException -> 0x01f4 }
            com.google.android.gms.measurement.internal.zzaf r6 = r6.zzf()     // Catch:{ SQLiteException -> 0x01f4 }
            com.google.android.gms.measurement.internal.zzdx<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzdy.zzam     // Catch:{ SQLiteException -> 0x01f4 }
            boolean r6 = r6.zzs(r3, r7)     // Catch:{ SQLiteException -> 0x01f4 }
            r7 = 0
            if (r6 != 0) goto L_0x016d
            r6 = 22
            boolean r9 = r4.isNull(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            if (r9 == 0) goto L_0x0165
            r9 = r7
            goto L_0x016a
        L_0x0165:
            long r9 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x01f4 }
        L_0x016a:
            r5.zzH(r9)     // Catch:{ SQLiteException -> 0x01f4 }
        L_0x016d:
            r6 = 23
            boolean r9 = r4.isNull(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            if (r9 != 0) goto L_0x017f
            int r6 = r4.getInt(r6)     // Catch:{ SQLiteException -> 0x01f4 }
            if (r6 == 0) goto L_0x017d
            goto L_0x0180
        L_0x017d:
            r0 = r12
            goto L_0x0180
        L_0x017f:
        L_0x0180:
            r5.zzG(r0)     // Catch:{ SQLiteException -> 0x01f4 }
            r0 = 24
            java.lang.String r0 = r4.getString(r0)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzF(r0)     // Catch:{ SQLiteException -> 0x01f4 }
            r0 = 25
            boolean r6 = r4.isNull(r0)     // Catch:{ SQLiteException -> 0x01f4 }
            if (r6 == 0) goto L_0x0195
        L_0x0194:
            goto L_0x019b
        L_0x0195:
            long r7 = r4.getLong(r0)     // Catch:{ SQLiteException -> 0x01f4 }
            goto L_0x0194
        L_0x019b:
            r5.zzU(r7)     // Catch:{ SQLiteException -> 0x01f4 }
            r0 = 26
            boolean r6 = r4.isNull(r0)     // Catch:{ SQLiteException -> 0x01f4 }
            if (r6 != 0) goto L_0x01b8
            java.lang.String r0 = r4.getString(r0)     // Catch:{ SQLiteException -> 0x01f4 }
            java.lang.String r6 = ","
            r7 = -1
            java.lang.String[] r0 = r0.split(r6, r7)     // Catch:{ SQLiteException -> 0x01f4 }
            java.util.List r0 = java.util.Arrays.asList(r0)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzah(r0)     // Catch:{ SQLiteException -> 0x01f4 }
        L_0x01b8:
            com.google.android.gms.internal.measurement.zzom.zzc()     // Catch:{ SQLiteException -> 0x01f4 }
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzs     // Catch:{ SQLiteException -> 0x01f4 }
            com.google.android.gms.measurement.internal.zzaf r0 = r0.zzf()     // Catch:{ SQLiteException -> 0x01f4 }
            com.google.android.gms.measurement.internal.zzdx<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzdy.zzac     // Catch:{ SQLiteException -> 0x01f4 }
            boolean r0 = r0.zzs(r2, r6)     // Catch:{ SQLiteException -> 0x01f4 }
            if (r0 == 0) goto L_0x01d2
            r0 = 27
            java.lang.String r0 = r4.getString(r0)     // Catch:{ SQLiteException -> 0x01f4 }
            r5.zzX(r0)     // Catch:{ SQLiteException -> 0x01f4 }
        L_0x01d2:
            r5.zzD()     // Catch:{ SQLiteException -> 0x01f4 }
            boolean r0 = r4.moveToNext()     // Catch:{ SQLiteException -> 0x01f4 }
            if (r0 == 0) goto L_0x01ee
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzs     // Catch:{ SQLiteException -> 0x01f4 }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ SQLiteException -> 0x01f4 }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzd()     // Catch:{ SQLiteException -> 0x01f4 }
            java.lang.String r6 = "Got multiple records for app, expected one. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzel.zzn(r34)     // Catch:{ SQLiteException -> 0x01f4 }
            r0.zzb(r6, r7)     // Catch:{ SQLiteException -> 0x01f4 }
        L_0x01ee:
            if (r4 == 0) goto L_0x01f3
            r4.close()
        L_0x01f3:
            return r5
        L_0x01f4:
            r0 = move-exception
            goto L_0x01fa
        L_0x01f6:
            r0 = move-exception
            goto L_0x0215
        L_0x01f8:
            r0 = move-exception
            r4 = r3
        L_0x01fa:
            com.google.android.gms.measurement.internal.zzfv r5 = r1.zzs     // Catch:{ all -> 0x0213 }
            com.google.android.gms.measurement.internal.zzel r5 = r5.zzay()     // Catch:{ all -> 0x0213 }
            com.google.android.gms.measurement.internal.zzej r5 = r5.zzd()     // Catch:{ all -> 0x0213 }
            java.lang.String r6 = "Error querying app. appId"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzel.zzn(r34)     // Catch:{ all -> 0x0213 }
            r5.zzc(r6, r2, r0)     // Catch:{ all -> 0x0213 }
            if (r4 == 0) goto L_0x0212
            r4.close()
        L_0x0212:
            return r3
        L_0x0213:
            r0 = move-exception
            r3 = r4
        L_0x0215:
            if (r3 == 0) goto L_0x021a
            r3.close()
        L_0x021a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaj.zzj(java.lang.String):com.google.android.gms.measurement.internal.zzg");
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x012f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzab zzk(java.lang.String r31, java.lang.String r32) {
        /*
            r30 = this;
            r1 = r30
            r8 = r32
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r31)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r32)
            r30.zzg()
            r30.zzY()
            r9 = 0
            android.database.sqlite.SQLiteDatabase r10 = r30.zzh()     // Catch:{ SQLiteException -> 0x0106, all -> 0x0104 }
            java.lang.String r11 = "origin"
            java.lang.String r12 = "value"
            java.lang.String r13 = "active"
            java.lang.String r14 = "trigger_event_name"
            java.lang.String r15 = "trigger_timeout"
            java.lang.String r16 = "timed_out_event"
            java.lang.String r17 = "creation_timestamp"
            java.lang.String r18 = "triggered_event"
            java.lang.String r19 = "triggered_timestamp"
            java.lang.String r20 = "time_to_live"
            java.lang.String r21 = "expired_event"
            java.lang.String[] r12 = new java.lang.String[]{r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21}     // Catch:{ SQLiteException -> 0x0106, all -> 0x0104 }
            r0 = 2
            java.lang.String[] r14 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0106, all -> 0x0104 }
            r2 = 0
            r14[r2] = r31     // Catch:{ SQLiteException -> 0x0106, all -> 0x0104 }
            r3 = 1
            r14[r3] = r8     // Catch:{ SQLiteException -> 0x0106, all -> 0x0104 }
            java.lang.String r11 = "conditional_properties"
            java.lang.String r13 = "app_id=? and name=?"
            r15 = 0
            r16 = 0
            r17 = 0
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16, r17)     // Catch:{ SQLiteException -> 0x0106, all -> 0x0104 }
            boolean r4 = r10.moveToFirst()     // Catch:{ SQLiteException -> 0x0102 }
            if (r4 != 0) goto L_0x0051
            if (r10 == 0) goto L_0x0050
            r10.close()
        L_0x0050:
            return r9
        L_0x0051:
            java.lang.String r4 = r10.getString(r2)     // Catch:{ SQLiteException -> 0x0102 }
            if (r4 != 0) goto L_0x005a
            java.lang.String r4 = ""
        L_0x005a:
            r17 = r4
            java.lang.Object r6 = r1.zzq(r10, r3)     // Catch:{ SQLiteException -> 0x0102 }
            int r0 = r10.getInt(r0)     // Catch:{ SQLiteException -> 0x0102 }
            if (r0 == 0) goto L_0x0069
            r21 = r3
            goto L_0x006b
        L_0x0069:
            r21 = r2
        L_0x006b:
            r0 = 3
            java.lang.String r22 = r10.getString(r0)     // Catch:{ SQLiteException -> 0x0102 }
            r0 = 4
            long r24 = r10.getLong(r0)     // Catch:{ SQLiteException -> 0x0102 }
            com.google.android.gms.measurement.internal.zzks r0 = r1.zzf     // Catch:{ SQLiteException -> 0x0102 }
            com.google.android.gms.measurement.internal.zzku r0 = r0.zzu()     // Catch:{ SQLiteException -> 0x0102 }
            r2 = 5
            byte[] r2 = r10.getBlob(r2)     // Catch:{ SQLiteException -> 0x0102 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzat> r3 = com.google.android.gms.measurement.internal.zzat.CREATOR     // Catch:{ SQLiteException -> 0x0102 }
            android.os.Parcelable r0 = r0.zzh(r2, r3)     // Catch:{ SQLiteException -> 0x0102 }
            r23 = r0
            com.google.android.gms.measurement.internal.zzat r23 = (com.google.android.gms.measurement.internal.zzat) r23     // Catch:{ SQLiteException -> 0x0102 }
            r0 = 6
            long r19 = r10.getLong(r0)     // Catch:{ SQLiteException -> 0x0102 }
            com.google.android.gms.measurement.internal.zzks r0 = r1.zzf     // Catch:{ SQLiteException -> 0x0102 }
            com.google.android.gms.measurement.internal.zzku r0 = r0.zzu()     // Catch:{ SQLiteException -> 0x0102 }
            r2 = 7
            byte[] r2 = r10.getBlob(r2)     // Catch:{ SQLiteException -> 0x0102 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzat> r3 = com.google.android.gms.measurement.internal.zzat.CREATOR     // Catch:{ SQLiteException -> 0x0102 }
            android.os.Parcelable r0 = r0.zzh(r2, r3)     // Catch:{ SQLiteException -> 0x0102 }
            r26 = r0
            com.google.android.gms.measurement.internal.zzat r26 = (com.google.android.gms.measurement.internal.zzat) r26     // Catch:{ SQLiteException -> 0x0102 }
            r0 = 8
            long r4 = r10.getLong(r0)     // Catch:{ SQLiteException -> 0x0102 }
            r0 = 9
            long r27 = r10.getLong(r0)     // Catch:{ SQLiteException -> 0x0102 }
            com.google.android.gms.measurement.internal.zzks r0 = r1.zzf     // Catch:{ SQLiteException -> 0x0102 }
            com.google.android.gms.measurement.internal.zzku r0 = r0.zzu()     // Catch:{ SQLiteException -> 0x0102 }
            r2 = 10
            byte[] r2 = r10.getBlob(r2)     // Catch:{ SQLiteException -> 0x0102 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzat> r3 = com.google.android.gms.measurement.internal.zzat.CREATOR     // Catch:{ SQLiteException -> 0x0102 }
            android.os.Parcelable r0 = r0.zzh(r2, r3)     // Catch:{ SQLiteException -> 0x0102 }
            r29 = r0
            com.google.android.gms.measurement.internal.zzat r29 = (com.google.android.gms.measurement.internal.zzat) r29     // Catch:{ SQLiteException -> 0x0102 }
            com.google.android.gms.measurement.internal.zzkv r18 = new com.google.android.gms.measurement.internal.zzkv     // Catch:{ SQLiteException -> 0x0102 }
            r2 = r18
            r3 = r32
            r7 = r17
            r2.<init>(r3, r4, r6, r7)     // Catch:{ SQLiteException -> 0x0102 }
            com.google.android.gms.measurement.internal.zzab r0 = new com.google.android.gms.measurement.internal.zzab     // Catch:{ SQLiteException -> 0x0102 }
            r15 = r0
            r16 = r31
            r15.<init>(r16, r17, r18, r19, r21, r22, r23, r24, r26, r27, r29)     // Catch:{ SQLiteException -> 0x0102 }
            boolean r2 = r10.moveToNext()     // Catch:{ SQLiteException -> 0x0102 }
            if (r2 == 0) goto L_0x00fc
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzs     // Catch:{ SQLiteException -> 0x0102 }
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()     // Catch:{ SQLiteException -> 0x0102 }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()     // Catch:{ SQLiteException -> 0x0102 }
            java.lang.String r3 = "Got multiple records for conditional property, expected one"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzel.zzn(r31)     // Catch:{ SQLiteException -> 0x0102 }
            com.google.android.gms.measurement.internal.zzfv r5 = r1.zzs     // Catch:{ SQLiteException -> 0x0102 }
            com.google.android.gms.measurement.internal.zzeg r5 = r5.zzj()     // Catch:{ SQLiteException -> 0x0102 }
            java.lang.String r5 = r5.zzf(r8)     // Catch:{ SQLiteException -> 0x0102 }
            r2.zzc(r3, r4, r5)     // Catch:{ SQLiteException -> 0x0102 }
        L_0x00fc:
            if (r10 == 0) goto L_0x0101
            r10.close()
        L_0x0101:
            return r0
        L_0x0102:
            r0 = move-exception
            goto L_0x0108
        L_0x0104:
            r0 = move-exception
            goto L_0x012d
        L_0x0106:
            r0 = move-exception
            r10 = r9
        L_0x0108:
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzs     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()     // Catch:{ all -> 0x012b }
            java.lang.String r3 = "Error querying conditional property"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzel.zzn(r31)     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zzfv r5 = r1.zzs     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zzeg r5 = r5.zzj()     // Catch:{ all -> 0x012b }
            java.lang.String r5 = r5.zzf(r8)     // Catch:{ all -> 0x012b }
            r2.zzd(r3, r4, r5, r0)     // Catch:{ all -> 0x012b }
            if (r10 == 0) goto L_0x012a
            r10.close()
        L_0x012a:
            return r9
        L_0x012b:
            r0 = move-exception
            r9 = r10
        L_0x012d:
            if (r9 == 0) goto L_0x0132
            r9.close()
        L_0x0132:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaj.zzk(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzab");
    }

    public final zzah zzl(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        return zzm(j, str, 1, false, false, z3, false, z5);
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x011f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzah zzm(long r16, java.lang.String r18, long r19, boolean r21, boolean r22, boolean r23, boolean r24, boolean r25) {
        /*
            r15 = this;
            r1 = r15
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r18)
            r15.zzg()
            r15.zzY()
            r0 = 1
            java.lang.String[] r2 = new java.lang.String[r0]
            r3 = 0
            r2[r3] = r18
            com.google.android.gms.measurement.internal.zzah r4 = new com.google.android.gms.measurement.internal.zzah
            r4.<init>()
            r5 = 0
            android.database.sqlite.SQLiteDatabase r14 = r15.zzh()     // Catch:{ SQLiteException -> 0x0102, all -> 0x0100 }
            java.lang.String r6 = "day"
            java.lang.String r7 = "daily_events_count"
            java.lang.String r8 = "daily_public_events_count"
            java.lang.String r9 = "daily_conversions_count"
            java.lang.String r10 = "daily_error_events_count"
            java.lang.String r11 = "daily_realtime_events_count"
            java.lang.String[] r8 = new java.lang.String[]{r6, r7, r8, r9, r10, r11}     // Catch:{ SQLiteException -> 0x0102, all -> 0x0100 }
            java.lang.String[] r10 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0102, all -> 0x0100 }
            r10[r3] = r18     // Catch:{ SQLiteException -> 0x0102, all -> 0x0100 }
            java.lang.String r7 = "apps"
            java.lang.String r9 = "app_id=?"
            r11 = 0
            r12 = 0
            r13 = 0
            r6 = r14
            android.database.Cursor r5 = r6.query(r7, r8, r9, r10, r11, r12, r13)     // Catch:{ SQLiteException -> 0x0102, all -> 0x0100 }
            boolean r6 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x00fe }
            if (r6 != 0) goto L_0x0059
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzs     // Catch:{ SQLiteException -> 0x00fe }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ SQLiteException -> 0x00fe }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzk()     // Catch:{ SQLiteException -> 0x00fe }
            java.lang.String r2 = "Not updating daily counts, app is not known. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzel.zzn(r18)     // Catch:{ SQLiteException -> 0x00fe }
            r0.zzb(r2, r3)     // Catch:{ SQLiteException -> 0x00fe }
            if (r5 == 0) goto L_0x0058
            r5.close()
        L_0x0058:
            return r4
        L_0x0059:
            long r6 = r5.getLong(r3)     // Catch:{ SQLiteException -> 0x00fe }
            int r3 = (r6 > r16 ? 1 : (r6 == r16 ? 0 : -1))
            if (r3 != 0) goto L_0x0084
            long r6 = r5.getLong(r0)     // Catch:{ SQLiteException -> 0x00fe }
            r4.zzb = r6     // Catch:{ SQLiteException -> 0x00fe }
            r0 = 2
            long r6 = r5.getLong(r0)     // Catch:{ SQLiteException -> 0x00fe }
            r4.zza = r6     // Catch:{ SQLiteException -> 0x00fe }
            r0 = 3
            long r6 = r5.getLong(r0)     // Catch:{ SQLiteException -> 0x00fe }
            r4.zzc = r6     // Catch:{ SQLiteException -> 0x00fe }
            r0 = 4
            long r6 = r5.getLong(r0)     // Catch:{ SQLiteException -> 0x00fe }
            r4.zzd = r6     // Catch:{ SQLiteException -> 0x00fe }
            r0 = 5
            long r6 = r5.getLong(r0)     // Catch:{ SQLiteException -> 0x00fe }
            r4.zze = r6     // Catch:{ SQLiteException -> 0x00fe }
        L_0x0084:
            if (r21 == 0) goto L_0x008c
            long r6 = r4.zzb     // Catch:{ SQLiteException -> 0x00fe }
            long r6 = r6 + r19
            r4.zzb = r6     // Catch:{ SQLiteException -> 0x00fe }
        L_0x008c:
            if (r22 == 0) goto L_0x0094
            long r6 = r4.zza     // Catch:{ SQLiteException -> 0x00fe }
            long r6 = r6 + r19
            r4.zza = r6     // Catch:{ SQLiteException -> 0x00fe }
        L_0x0094:
            if (r23 == 0) goto L_0x009c
            long r6 = r4.zzc     // Catch:{ SQLiteException -> 0x00fe }
            long r6 = r6 + r19
            r4.zzc = r6     // Catch:{ SQLiteException -> 0x00fe }
        L_0x009c:
            if (r24 == 0) goto L_0x00a4
            long r6 = r4.zzd     // Catch:{ SQLiteException -> 0x00fe }
            long r6 = r6 + r19
            r4.zzd = r6     // Catch:{ SQLiteException -> 0x00fe }
        L_0x00a4:
            if (r25 == 0) goto L_0x00ac
            long r6 = r4.zze     // Catch:{ SQLiteException -> 0x00fe }
            long r6 = r6 + r19
            r4.zze = r6     // Catch:{ SQLiteException -> 0x00fe }
        L_0x00ac:
            android.content.ContentValues r0 = new android.content.ContentValues     // Catch:{ SQLiteException -> 0x00fe }
            r0.<init>()     // Catch:{ SQLiteException -> 0x00fe }
            java.lang.String r3 = "day"
            java.lang.Long r6 = java.lang.Long.valueOf(r16)     // Catch:{ SQLiteException -> 0x00fe }
            r0.put(r3, r6)     // Catch:{ SQLiteException -> 0x00fe }
            java.lang.String r3 = "daily_public_events_count"
            long r6 = r4.zza     // Catch:{ SQLiteException -> 0x00fe }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ SQLiteException -> 0x00fe }
            r0.put(r3, r6)     // Catch:{ SQLiteException -> 0x00fe }
            java.lang.String r3 = "daily_events_count"
            long r6 = r4.zzb     // Catch:{ SQLiteException -> 0x00fe }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ SQLiteException -> 0x00fe }
            r0.put(r3, r6)     // Catch:{ SQLiteException -> 0x00fe }
            java.lang.String r3 = "daily_conversions_count"
            long r6 = r4.zzc     // Catch:{ SQLiteException -> 0x00fe }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ SQLiteException -> 0x00fe }
            r0.put(r3, r6)     // Catch:{ SQLiteException -> 0x00fe }
            java.lang.String r3 = "daily_error_events_count"
            long r6 = r4.zzd     // Catch:{ SQLiteException -> 0x00fe }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ SQLiteException -> 0x00fe }
            r0.put(r3, r6)     // Catch:{ SQLiteException -> 0x00fe }
            java.lang.String r3 = "daily_realtime_events_count"
            long r6 = r4.zze     // Catch:{ SQLiteException -> 0x00fe }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ SQLiteException -> 0x00fe }
            r0.put(r3, r6)     // Catch:{ SQLiteException -> 0x00fe }
            java.lang.String r3 = "apps"
            java.lang.String r6 = "app_id=?"
            r14.update(r3, r0, r6, r2)     // Catch:{ SQLiteException -> 0x00fe }
            if (r5 == 0) goto L_0x00fd
            r5.close()
        L_0x00fd:
            return r4
        L_0x00fe:
            r0 = move-exception
            goto L_0x0103
        L_0x0100:
            r0 = move-exception
            goto L_0x011d
        L_0x0102:
            r0 = move-exception
        L_0x0103:
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzs     // Catch:{ all -> 0x011c }
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()     // Catch:{ all -> 0x011c }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()     // Catch:{ all -> 0x011c }
            java.lang.String r3 = "Error updating daily counts. appId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzel.zzn(r18)     // Catch:{ all -> 0x011c }
            r2.zzc(r3, r6, r0)     // Catch:{ all -> 0x011c }
            if (r5 == 0) goto L_0x011b
            r5.close()
        L_0x011b:
            return r4
        L_0x011c:
            r0 = move-exception
        L_0x011d:
            if (r5 == 0) goto L_0x0122
            r5.close()
        L_0x0122:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaj.zzm(long, java.lang.String, long, boolean, boolean, boolean, boolean, boolean):com.google.android.gms.measurement.internal.zzah");
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x0154  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x015d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzap zzn(java.lang.String r28, java.lang.String r29) {
        /*
            r27 = this;
            r1 = r27
            r15 = r29
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r28)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r29)
            r27.zzg()
            r27.zzY()
            java.util.ArrayList r0 = new java.util.ArrayList
            java.lang.String r2 = "lifetime_count"
            java.lang.String r3 = "current_bundle_count"
            java.lang.String r4 = "last_fire_timestamp"
            java.lang.String r5 = "last_bundled_timestamp"
            java.lang.String r6 = "last_bundled_day"
            java.lang.String r7 = "last_sampled_complex_event_id"
            java.lang.String r8 = "last_sampling_rate"
            java.lang.String r9 = "last_exempt_from_sampling"
            java.lang.String r10 = "current_session_count"
            java.lang.String[] r2 = new java.lang.String[]{r2, r3, r4, r5, r6, r7, r8, r9, r10}
            java.util.List r2 = java.util.Arrays.asList(r2)
            r0.<init>(r2)
            r19 = 0
            android.database.sqlite.SQLiteDatabase r2 = r27.zzh()     // Catch:{ SQLiteException -> 0x0130, all -> 0x012e }
            r10 = 0
            java.lang.String[] r3 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x0130, all -> 0x012e }
            java.lang.Object[] r0 = r0.toArray(r3)     // Catch:{ SQLiteException -> 0x0130, all -> 0x012e }
            r4 = r0
            java.lang.String[] r4 = (java.lang.String[]) r4     // Catch:{ SQLiteException -> 0x0130, all -> 0x012e }
            r0 = 2
            java.lang.String[] r6 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0130, all -> 0x012e }
            r6[r10] = r28     // Catch:{ SQLiteException -> 0x0130, all -> 0x012e }
            r11 = 1
            r6[r11] = r15     // Catch:{ SQLiteException -> 0x0130, all -> 0x012e }
            java.lang.String r3 = "events"
            java.lang.String r5 = "app_id=? and name=?"
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r13 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x0130, all -> 0x012e }
            boolean r2 = r13.moveToFirst()     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
            if (r2 != 0) goto L_0x005e
            if (r13 == 0) goto L_0x005d
            r13.close()
        L_0x005d:
            return r19
        L_0x005e:
            long r5 = r13.getLong(r10)     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
            long r7 = r13.getLong(r11)     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
            long r16 = r13.getLong(r0)     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
            r0 = 3
            boolean r2 = r13.isNull(r0)     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
            r3 = 0
            if (r2 == 0) goto L_0x0077
            r20 = r3
            goto L_0x007c
        L_0x0077:
            long r20 = r13.getLong(r0)     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
        L_0x007c:
            r0 = 4
            boolean r2 = r13.isNull(r0)     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
            if (r2 == 0) goto L_0x0086
            r0 = r19
            goto L_0x008f
        L_0x0086:
            long r22 = r13.getLong(r0)     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
            java.lang.Long r0 = java.lang.Long.valueOf(r22)     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
        L_0x008f:
            r2 = 5
            boolean r9 = r13.isNull(r2)     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
            if (r9 == 0) goto L_0x0099
            r18 = r19
            goto L_0x00a4
        L_0x0099:
            long r22 = r13.getLong(r2)     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
            java.lang.Long r2 = java.lang.Long.valueOf(r22)     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
            r18 = r2
        L_0x00a4:
            r2 = 6
            boolean r9 = r13.isNull(r2)     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
            if (r9 == 0) goto L_0x00ae
            r22 = r19
            goto L_0x00b9
        L_0x00ae:
            long r22 = r13.getLong(r2)     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
            java.lang.Long r2 = java.lang.Long.valueOf(r22)     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
            r22 = r2
        L_0x00b9:
            r2 = 7
            boolean r9 = r13.isNull(r2)     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
            if (r9 != 0) goto L_0x00d4
            long r23 = r13.getLong(r2)     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
            r25 = 1
            int r2 = (r23 > r25 ? 1 : (r23 == r25 ? 0 : -1))
            if (r2 != 0) goto L_0x00cc
            r10 = r11
            goto L_0x00cd
        L_0x00cc:
        L_0x00cd:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r10)     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
            r23 = r2
            goto L_0x00d6
        L_0x00d4:
            r23 = r19
        L_0x00d6:
            r2 = 8
            boolean r9 = r13.isNull(r2)     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
            if (r9 == 0) goto L_0x00e1
            r9 = r3
            goto L_0x00e7
        L_0x00e1:
            long r2 = r13.getLong(r2)     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
            r9 = r2
        L_0x00e7:
            com.google.android.gms.measurement.internal.zzap r24 = new com.google.android.gms.measurement.internal.zzap     // Catch:{ SQLiteException -> 0x0128, all -> 0x0122 }
            r2 = r24
            r3 = r28
            r4 = r29
            r11 = r16
            r25 = r13
            r13 = r20
            r15 = r0
            r16 = r18
            r17 = r22
            r18 = r23
            r2.<init>(r3, r4, r5, r7, r9, r11, r13, r15, r16, r17, r18)     // Catch:{ SQLiteException -> 0x0120, all -> 0x011e }
            boolean r0 = r25.moveToNext()     // Catch:{ SQLiteException -> 0x0120, all -> 0x011e }
            if (r0 == 0) goto L_0x0118
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzs     // Catch:{ SQLiteException -> 0x0120, all -> 0x011e }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ SQLiteException -> 0x0120, all -> 0x011e }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzd()     // Catch:{ SQLiteException -> 0x0120, all -> 0x011e }
            java.lang.String r2 = "Got multiple records for event aggregates, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzel.zzn(r28)     // Catch:{ SQLiteException -> 0x0120, all -> 0x011e }
            r0.zzb(r2, r3)     // Catch:{ SQLiteException -> 0x0120, all -> 0x011e }
        L_0x0118:
            if (r25 == 0) goto L_0x011d
            r25.close()
        L_0x011d:
            return r24
        L_0x011e:
            r0 = move-exception
            goto L_0x0125
        L_0x0120:
            r0 = move-exception
            goto L_0x012b
        L_0x0122:
            r0 = move-exception
            r25 = r13
        L_0x0125:
            r19 = r25
            goto L_0x015b
        L_0x0128:
            r0 = move-exception
            r25 = r13
        L_0x012b:
            r13 = r25
            goto L_0x0133
        L_0x012e:
            r0 = move-exception
            goto L_0x015b
        L_0x0130:
            r0 = move-exception
            r13 = r19
        L_0x0133:
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzs     // Catch:{ all -> 0x0158 }
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()     // Catch:{ all -> 0x0158 }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()     // Catch:{ all -> 0x0158 }
            java.lang.String r3 = "Error querying events. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzel.zzn(r28)     // Catch:{ all -> 0x0158 }
            com.google.android.gms.measurement.internal.zzfv r5 = r1.zzs     // Catch:{ all -> 0x0158 }
            com.google.android.gms.measurement.internal.zzeg r5 = r5.zzj()     // Catch:{ all -> 0x0158 }
            r6 = r29
            java.lang.String r5 = r5.zzd(r6)     // Catch:{ all -> 0x0158 }
            r2.zzd(r3, r4, r5, r0)     // Catch:{ all -> 0x0158 }
            if (r13 == 0) goto L_0x0157
            r13.close()
        L_0x0157:
            return r19
        L_0x0158:
            r0 = move-exception
            r19 = r13
        L_0x015b:
            if (r19 == 0) goto L_0x0160
            r19.close()
        L_0x0160:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaj.zzn(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzap");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ae  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzkx zzp(java.lang.String r20, java.lang.String r21) {
        /*
            r19 = this;
            r1 = r19
            r9 = r21
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r20)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r21)
            r19.zzg()
            r19.zzY()
            r10 = 0
            android.database.sqlite.SQLiteDatabase r11 = r19.zzh()     // Catch:{ SQLiteException -> 0x0085, all -> 0x0083 }
            java.lang.String r0 = "set_timestamp"
            java.lang.String r2 = "value"
            java.lang.String r3 = "origin"
            java.lang.String[] r13 = new java.lang.String[]{r0, r2, r3}     // Catch:{ SQLiteException -> 0x0085, all -> 0x0083 }
            r0 = 2
            java.lang.String[] r15 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0085, all -> 0x0083 }
            r2 = 0
            r15[r2] = r20     // Catch:{ SQLiteException -> 0x0085, all -> 0x0083 }
            r3 = 1
            r15[r3] = r9     // Catch:{ SQLiteException -> 0x0085, all -> 0x0083 }
            java.lang.String r12 = "user_attributes"
            java.lang.String r14 = "app_id=? and name=?"
            r16 = 0
            r17 = 0
            r18 = 0
            android.database.Cursor r11 = r11.query(r12, r13, r14, r15, r16, r17, r18)     // Catch:{ SQLiteException -> 0x0085, all -> 0x0083 }
            boolean r4 = r11.moveToFirst()     // Catch:{ SQLiteException -> 0x0081 }
            if (r4 != 0) goto L_0x0042
            if (r11 == 0) goto L_0x0041
            r11.close()
        L_0x0041:
            return r10
        L_0x0042:
            long r6 = r11.getLong(r2)     // Catch:{ SQLiteException -> 0x0081 }
            java.lang.Object r8 = r1.zzq(r11, r3)     // Catch:{ SQLiteException -> 0x0081 }
            if (r8 != 0) goto L_0x0053
            if (r11 == 0) goto L_0x0052
            r11.close()
        L_0x0052:
            return r10
        L_0x0053:
            java.lang.String r4 = r11.getString(r0)     // Catch:{ SQLiteException -> 0x0081 }
            com.google.android.gms.measurement.internal.zzkx r0 = new com.google.android.gms.measurement.internal.zzkx     // Catch:{ SQLiteException -> 0x0081 }
            r2 = r0
            r3 = r20
            r5 = r21
            r2.<init>(r3, r4, r5, r6, r8)     // Catch:{ SQLiteException -> 0x0081 }
            boolean r2 = r11.moveToNext()     // Catch:{ SQLiteException -> 0x0081 }
            if (r2 == 0) goto L_0x007b
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzs     // Catch:{ SQLiteException -> 0x0081 }
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()     // Catch:{ SQLiteException -> 0x0081 }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()     // Catch:{ SQLiteException -> 0x0081 }
            java.lang.String r3 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzel.zzn(r20)     // Catch:{ SQLiteException -> 0x0081 }
            r2.zzb(r3, r4)     // Catch:{ SQLiteException -> 0x0081 }
        L_0x007b:
            if (r11 == 0) goto L_0x0080
            r11.close()
        L_0x0080:
            return r0
        L_0x0081:
            r0 = move-exception
            goto L_0x0087
        L_0x0083:
            r0 = move-exception
            goto L_0x00ac
        L_0x0085:
            r0 = move-exception
            r11 = r10
        L_0x0087:
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzs     // Catch:{ all -> 0x00aa }
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()     // Catch:{ all -> 0x00aa }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()     // Catch:{ all -> 0x00aa }
            java.lang.String r3 = "Error querying user property. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzel.zzn(r20)     // Catch:{ all -> 0x00aa }
            com.google.android.gms.measurement.internal.zzfv r5 = r1.zzs     // Catch:{ all -> 0x00aa }
            com.google.android.gms.measurement.internal.zzeg r5 = r5.zzj()     // Catch:{ all -> 0x00aa }
            java.lang.String r5 = r5.zzf(r9)     // Catch:{ all -> 0x00aa }
            r2.zzd(r3, r4, r5, r0)     // Catch:{ all -> 0x00aa }
            if (r11 == 0) goto L_0x00a9
            r11.close()
        L_0x00a9:
            return r10
        L_0x00aa:
            r0 = move-exception
            r10 = r11
        L_0x00ac:
            if (r10 == 0) goto L_0x00b1
            r10.close()
        L_0x00b1:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaj.zzp(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzkx");
    }

    /* access modifiers changed from: package-private */
    public final Object zzq(Cursor cursor, int i) {
        int type = cursor.getType(i);
        switch (type) {
            case 0:
                this.zzs.zzay().zzd().zza("Loaded invalid null value from database");
                return null;
            case 1:
                return Long.valueOf(cursor.getLong(i));
            case 2:
                return Double.valueOf(cursor.getDouble(i));
            case 3:
                return cursor.getString(i);
            case 4:
                this.zzs.zzay().zzd().zza("Loaded invalid blob type value, ignoring it");
                return null;
            default:
                this.zzs.zzay().zzd().zzb("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzr() {
        /*
            r6 = this;
            android.database.sqlite.SQLiteDatabase r0 = r6.zzh()
            r1 = 0
            java.lang.String r2 = "select app_id from queue order by has_realtime desc, rowid asc limit 1;"
            android.database.Cursor r0 = r0.rawQuery(r2, r1)     // Catch:{ SQLiteException -> 0x0026, all -> 0x0024 }
            boolean r2 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x0022 }
            if (r2 == 0) goto L_0x001c
            r2 = 0
            java.lang.String r1 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x0022 }
            if (r0 == 0) goto L_0x001b
            r0.close()
        L_0x001b:
            return r1
        L_0x001c:
            if (r0 == 0) goto L_0x0021
            r0.close()
        L_0x0021:
            return r1
        L_0x0022:
            r2 = move-exception
            goto L_0x0029
        L_0x0024:
            r0 = move-exception
            goto L_0x0042
        L_0x0026:
            r0 = move-exception
            r2 = r0
            r0 = r1
        L_0x0029:
            com.google.android.gms.measurement.internal.zzfv r3 = r6.zzs     // Catch:{ all -> 0x003e }
            com.google.android.gms.measurement.internal.zzel r3 = r3.zzay()     // Catch:{ all -> 0x003e }
            com.google.android.gms.measurement.internal.zzej r3 = r3.zzd()     // Catch:{ all -> 0x003e }
            java.lang.String r4 = "Database error getting next bundle app id"
            r3.zzb(r4, r2)     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x003d
            r0.close()
        L_0x003d:
            return r1
        L_0x003e:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L_0x0042:
            if (r1 == 0) goto L_0x0047
            r1.close()
        L_0x0047:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaj.zzr():java.lang.String");
    }

    public final List<zzab> zzs(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzY();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zzt(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0133  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.measurement.internal.zzab> zzt(java.lang.String r28, java.lang.String[] r29) {
        /*
            r27 = this;
            r1 = r27
            r27.zzg()
            r27.zzY()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r10 = "1001"
            r11 = 0
            android.database.sqlite.SQLiteDatabase r2 = r27.zzh()     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            java.lang.String r3 = "conditional_properties"
            java.lang.String r12 = "app_id"
            java.lang.String r13 = "origin"
            java.lang.String r14 = "name"
            java.lang.String r15 = "value"
            java.lang.String r16 = "active"
            java.lang.String r17 = "trigger_event_name"
            java.lang.String r18 = "trigger_timeout"
            java.lang.String r19 = "timed_out_event"
            java.lang.String r20 = "creation_timestamp"
            java.lang.String r21 = "triggered_event"
            java.lang.String r22 = "triggered_timestamp"
            java.lang.String r23 = "time_to_live"
            java.lang.String r24 = "expired_event"
            java.lang.String[] r4 = new java.lang.String[]{r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24}     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            java.lang.String r9 = "rowid"
            com.google.android.gms.measurement.internal.zzfv r5 = r1.zzs     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r5.zzf()     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r7 = 0
            r8 = 0
            r5 = r28
            r6 = r29
            android.database.Cursor r11 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            boolean r2 = r11.moveToFirst()     // Catch:{ SQLiteException -> 0x0112 }
            if (r2 == 0) goto L_0x010c
        L_0x004b:
            int r2 = r0.size()     // Catch:{ SQLiteException -> 0x0112 }
            com.google.android.gms.measurement.internal.zzfv r3 = r1.zzs     // Catch:{ SQLiteException -> 0x0112 }
            r3.zzf()     // Catch:{ SQLiteException -> 0x0112 }
            r3 = 1000(0x3e8, float:1.401E-42)
            if (r2 < r3) goto L_0x0072
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzs     // Catch:{ SQLiteException -> 0x0112 }
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()     // Catch:{ SQLiteException -> 0x0112 }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()     // Catch:{ SQLiteException -> 0x0112 }
            java.lang.String r4 = "Read more than the max allowed conditional properties, ignoring extra"
            com.google.android.gms.measurement.internal.zzfv r5 = r1.zzs     // Catch:{ SQLiteException -> 0x0112 }
            r5.zzf()     // Catch:{ SQLiteException -> 0x0112 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x0112 }
            r2.zzb(r4, r3)     // Catch:{ SQLiteException -> 0x0112 }
            goto L_0x0106
        L_0x0072:
            r2 = 0
            java.lang.String r13 = r11.getString(r2)     // Catch:{ SQLiteException -> 0x0112 }
            r3 = 1
            java.lang.String r14 = r11.getString(r3)     // Catch:{ SQLiteException -> 0x0112 }
            r4 = 2
            java.lang.String r5 = r11.getString(r4)     // Catch:{ SQLiteException -> 0x0112 }
            r4 = 3
            java.lang.Object r8 = r1.zzq(r11, r4)     // Catch:{ SQLiteException -> 0x0112 }
            r4 = 4
            int r4 = r11.getInt(r4)     // Catch:{ SQLiteException -> 0x0112 }
            if (r4 == 0) goto L_0x0091
            r18 = r3
            goto L_0x0093
        L_0x0091:
            r18 = r2
        L_0x0093:
            r2 = 5
            java.lang.String r19 = r11.getString(r2)     // Catch:{ SQLiteException -> 0x0112 }
            r2 = 6
            long r21 = r11.getLong(r2)     // Catch:{ SQLiteException -> 0x0112 }
            com.google.android.gms.measurement.internal.zzks r2 = r1.zzf     // Catch:{ SQLiteException -> 0x0112 }
            com.google.android.gms.measurement.internal.zzku r2 = r2.zzu()     // Catch:{ SQLiteException -> 0x0112 }
            r3 = 7
            byte[] r3 = r11.getBlob(r3)     // Catch:{ SQLiteException -> 0x0112 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzat> r4 = com.google.android.gms.measurement.internal.zzat.CREATOR     // Catch:{ SQLiteException -> 0x0112 }
            android.os.Parcelable r2 = r2.zzh(r3, r4)     // Catch:{ SQLiteException -> 0x0112 }
            r20 = r2
            com.google.android.gms.measurement.internal.zzat r20 = (com.google.android.gms.measurement.internal.zzat) r20     // Catch:{ SQLiteException -> 0x0112 }
            r2 = 8
            long r16 = r11.getLong(r2)     // Catch:{ SQLiteException -> 0x0112 }
            com.google.android.gms.measurement.internal.zzks r2 = r1.zzf     // Catch:{ SQLiteException -> 0x0112 }
            com.google.android.gms.measurement.internal.zzku r2 = r2.zzu()     // Catch:{ SQLiteException -> 0x0112 }
            r3 = 9
            byte[] r3 = r11.getBlob(r3)     // Catch:{ SQLiteException -> 0x0112 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzat> r4 = com.google.android.gms.measurement.internal.zzat.CREATOR     // Catch:{ SQLiteException -> 0x0112 }
            android.os.Parcelable r2 = r2.zzh(r3, r4)     // Catch:{ SQLiteException -> 0x0112 }
            r23 = r2
            com.google.android.gms.measurement.internal.zzat r23 = (com.google.android.gms.measurement.internal.zzat) r23     // Catch:{ SQLiteException -> 0x0112 }
            r2 = 10
            long r6 = r11.getLong(r2)     // Catch:{ SQLiteException -> 0x0112 }
            r2 = 11
            long r24 = r11.getLong(r2)     // Catch:{ SQLiteException -> 0x0112 }
            com.google.android.gms.measurement.internal.zzks r2 = r1.zzf     // Catch:{ SQLiteException -> 0x0112 }
            com.google.android.gms.measurement.internal.zzku r2 = r2.zzu()     // Catch:{ SQLiteException -> 0x0112 }
            r3 = 12
            byte[] r3 = r11.getBlob(r3)     // Catch:{ SQLiteException -> 0x0112 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzat> r4 = com.google.android.gms.measurement.internal.zzat.CREATOR     // Catch:{ SQLiteException -> 0x0112 }
            android.os.Parcelable r2 = r2.zzh(r3, r4)     // Catch:{ SQLiteException -> 0x0112 }
            r26 = r2
            com.google.android.gms.measurement.internal.zzat r26 = (com.google.android.gms.measurement.internal.zzat) r26     // Catch:{ SQLiteException -> 0x0112 }
            com.google.android.gms.measurement.internal.zzkv r15 = new com.google.android.gms.measurement.internal.zzkv     // Catch:{ SQLiteException -> 0x0112 }
            r4 = r15
            r9 = r14
            r4.<init>(r5, r6, r8, r9)     // Catch:{ SQLiteException -> 0x0112 }
            com.google.android.gms.measurement.internal.zzab r2 = new com.google.android.gms.measurement.internal.zzab     // Catch:{ SQLiteException -> 0x0112 }
            r12 = r2
            r12.<init>(r13, r14, r15, r16, r18, r19, r20, r21, r23, r24, r26)     // Catch:{ SQLiteException -> 0x0112 }
            r0.add(r2)     // Catch:{ SQLiteException -> 0x0112 }
            boolean r2 = r11.moveToNext()     // Catch:{ SQLiteException -> 0x0112 }
            if (r2 != 0) goto L_0x004b
        L_0x0106:
            if (r11 == 0) goto L_0x010b
            r11.close()
        L_0x010b:
            return r0
        L_0x010c:
            if (r11 == 0) goto L_0x0111
            r11.close()
        L_0x0111:
            return r0
        L_0x0112:
            r0 = move-exception
            goto L_0x0117
        L_0x0114:
            r0 = move-exception
            goto L_0x0131
        L_0x0116:
            r0 = move-exception
        L_0x0117:
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzs     // Catch:{ all -> 0x0130 }
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()     // Catch:{ all -> 0x0130 }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()     // Catch:{ all -> 0x0130 }
            java.lang.String r3 = "Error querying conditional user property value"
            r2.zzb(r3, r0)     // Catch:{ all -> 0x0130 }
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0130 }
            if (r11 == 0) goto L_0x012f
            r11.close()
        L_0x012f:
            return r0
        L_0x0130:
            r0 = move-exception
        L_0x0131:
            if (r11 == 0) goto L_0x0136
            r11.close()
        L_0x0136:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaj.zzt(java.lang.String, java.lang.String[]):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ad  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.measurement.internal.zzkx> zzu(java.lang.String r14) {
        /*
            r13 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            r13.zzg()
            r13.zzY()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r9 = "1000"
            r10 = 0
            android.database.sqlite.SQLiteDatabase r1 = r13.zzh()     // Catch:{ SQLiteException -> 0x008c, all -> 0x008a }
            java.lang.String r2 = "user_attributes"
            java.lang.String r3 = "name"
            java.lang.String r4 = "origin"
            java.lang.String r5 = "set_timestamp"
            java.lang.String r6 = "value"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4, r5, r6}     // Catch:{ SQLiteException -> 0x008c, all -> 0x008a }
            java.lang.String r4 = "app_id=?"
            r11 = 1
            java.lang.String[] r5 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x008c, all -> 0x008a }
            r12 = 0
            r5[r12] = r14     // Catch:{ SQLiteException -> 0x008c, all -> 0x008a }
            java.lang.String r8 = "rowid"
            com.google.android.gms.measurement.internal.zzfv r6 = r13.zzs     // Catch:{ SQLiteException -> 0x008c, all -> 0x008a }
            r6.zzf()     // Catch:{ SQLiteException -> 0x008c, all -> 0x008a }
            r6 = 0
            r7 = 0
            android.database.Cursor r10 = r1.query(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x008c, all -> 0x008a }
            boolean r1 = r10.moveToFirst()     // Catch:{ SQLiteException -> 0x0088 }
            if (r1 == 0) goto L_0x0082
        L_0x003e:
            java.lang.String r5 = r10.getString(r12)     // Catch:{ SQLiteException -> 0x0088 }
            java.lang.String r1 = r10.getString(r11)     // Catch:{ SQLiteException -> 0x0088 }
            if (r1 != 0) goto L_0x004b
            java.lang.String r1 = ""
        L_0x004b:
            r4 = r1
            r1 = 2
            long r6 = r10.getLong(r1)     // Catch:{ SQLiteException -> 0x0088 }
            r1 = 3
            java.lang.Object r8 = r13.zzq(r10, r1)     // Catch:{ SQLiteException -> 0x0088 }
            if (r8 != 0) goto L_0x006c
            com.google.android.gms.measurement.internal.zzfv r1 = r13.zzs     // Catch:{ SQLiteException -> 0x0088 }
            com.google.android.gms.measurement.internal.zzel r1 = r1.zzay()     // Catch:{ SQLiteException -> 0x0088 }
            com.google.android.gms.measurement.internal.zzej r1 = r1.zzd()     // Catch:{ SQLiteException -> 0x0088 }
            java.lang.String r2 = "Read invalid user property value, ignoring it. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzel.zzn(r14)     // Catch:{ SQLiteException -> 0x0088 }
            r1.zzb(r2, r3)     // Catch:{ SQLiteException -> 0x0088 }
            goto L_0x0076
        L_0x006c:
            com.google.android.gms.measurement.internal.zzkx r1 = new com.google.android.gms.measurement.internal.zzkx     // Catch:{ SQLiteException -> 0x0088 }
            r2 = r1
            r3 = r14
            r2.<init>(r3, r4, r5, r6, r8)     // Catch:{ SQLiteException -> 0x0088 }
            r0.add(r1)     // Catch:{ SQLiteException -> 0x0088 }
        L_0x0076:
            boolean r1 = r10.moveToNext()     // Catch:{ SQLiteException -> 0x0088 }
            if (r1 != 0) goto L_0x003e
            if (r10 == 0) goto L_0x0081
            r10.close()
        L_0x0081:
            return r0
        L_0x0082:
            if (r10 == 0) goto L_0x0087
            r10.close()
        L_0x0087:
            return r0
        L_0x0088:
            r0 = move-exception
            goto L_0x008d
        L_0x008a:
            r14 = move-exception
            goto L_0x00ab
        L_0x008c:
            r0 = move-exception
        L_0x008d:
            com.google.android.gms.measurement.internal.zzfv r1 = r13.zzs     // Catch:{ all -> 0x00aa }
            com.google.android.gms.measurement.internal.zzel r1 = r1.zzay()     // Catch:{ all -> 0x00aa }
            com.google.android.gms.measurement.internal.zzej r1 = r1.zzd()     // Catch:{ all -> 0x00aa }
            java.lang.String r2 = "Error querying user properties. appId"
            java.lang.Object r14 = com.google.android.gms.measurement.internal.zzel.zzn(r14)     // Catch:{ all -> 0x00aa }
            r1.zzc(r2, r14, r0)     // Catch:{ all -> 0x00aa }
            java.util.List r14 = java.util.Collections.emptyList()     // Catch:{ all -> 0x00aa }
            if (r10 == 0) goto L_0x00a9
            r10.close()
        L_0x00a9:
            return r14
        L_0x00aa:
            r14 = move-exception
        L_0x00ab:
            if (r10 == 0) goto L_0x00b0
            r10.close()
        L_0x00b0:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaj.zzu(java.lang.String):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0101, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0103, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0105, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0106, code lost:
        r13 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0108, code lost:
        r14 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0123, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x012a, code lost:
        r11.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0103 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x0013] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x012a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.measurement.internal.zzkx> zzv(java.lang.String r17, java.lang.String r18, java.lang.String r19) {
        /*
            r16 = this;
            r1 = r16
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r17)
            r16.zzg()
            r16.zzY()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r10 = "1001"
            r11 = 0
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0105, all -> 0x0103 }
            r12 = 3
            r2.<init>(r12)     // Catch:{ SQLiteException -> 0x0105, all -> 0x0103 }
            r13 = r17
            r2.add(r13)     // Catch:{ SQLiteException -> 0x0101, all -> 0x0103 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0101, all -> 0x0103 }
            java.lang.String r4 = "app_id=?"
            r3.<init>(r4)     // Catch:{ SQLiteException -> 0x0101, all -> 0x0103 }
            boolean r4 = android.text.TextUtils.isEmpty(r18)     // Catch:{ SQLiteException -> 0x0101, all -> 0x0103 }
            if (r4 != 0) goto L_0x0036
            r14 = r18
            r2.add(r14)     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
            java.lang.String r4 = " and origin=?"
            r3.append(r4)     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
            goto L_0x0038
        L_0x0036:
            r14 = r18
        L_0x0038:
            boolean r4 = android.text.TextUtils.isEmpty(r19)     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
            if (r4 != 0) goto L_0x0050
            java.lang.String r4 = java.lang.String.valueOf(r19)     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
            java.lang.String r5 = "*"
            java.lang.String r4 = r4.concat(r5)     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
            r2.add(r4)     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
            java.lang.String r4 = " and name glob ?"
            r3.append(r4)     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
        L_0x0050:
            int r4 = r2.size()     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
            java.lang.Object[] r2 = r2.toArray(r4)     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
            r6 = r2
            java.lang.String[] r6 = (java.lang.String[]) r6     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
            android.database.sqlite.SQLiteDatabase r2 = r16.zzh()     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
            java.lang.String r4 = "user_attributes"
            java.lang.String r5 = "name"
            java.lang.String r7 = "set_timestamp"
            java.lang.String r8 = "value"
            java.lang.String r9 = "origin"
            java.lang.String[] r5 = new java.lang.String[]{r5, r7, r8, r9}     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
            java.lang.String r7 = r3.toString()     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
            java.lang.String r9 = "rowid"
            com.google.android.gms.measurement.internal.zzfv r3 = r1.zzs     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
            r3.zzf()     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
            r8 = 0
            r15 = 0
            r3 = r4
            r4 = r5
            r5 = r7
            r7 = r8
            r8 = r15
            android.database.Cursor r11 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x00ff, all -> 0x0103 }
            boolean r2 = r11.moveToFirst()     // Catch:{ SQLiteException -> 0x00fd }
            if (r2 != 0) goto L_0x0091
            if (r11 == 0) goto L_0x0090
            r11.close()
        L_0x0090:
            return r0
        L_0x0091:
            int r2 = r0.size()     // Catch:{ SQLiteException -> 0x00fd }
            com.google.android.gms.measurement.internal.zzfv r3 = r1.zzs     // Catch:{ SQLiteException -> 0x00fd }
            r3.zzf()     // Catch:{ SQLiteException -> 0x00fd }
            r3 = 1000(0x3e8, float:1.401E-42)
            if (r2 < r3) goto L_0x00b7
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzs     // Catch:{ SQLiteException -> 0x00fd }
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()     // Catch:{ SQLiteException -> 0x00fd }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()     // Catch:{ SQLiteException -> 0x00fd }
            java.lang.String r4 = "Read more than the max allowed user properties, ignoring excess"
            com.google.android.gms.measurement.internal.zzfv r5 = r1.zzs     // Catch:{ SQLiteException -> 0x00fd }
            r5.zzf()     // Catch:{ SQLiteException -> 0x00fd }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x00fd }
            r2.zzb(r4, r3)     // Catch:{ SQLiteException -> 0x00fd }
            goto L_0x00f7
        L_0x00b7:
            r2 = 0
            java.lang.String r6 = r11.getString(r2)     // Catch:{ SQLiteException -> 0x00fd }
            r2 = 1
            long r7 = r11.getLong(r2)     // Catch:{ SQLiteException -> 0x00fd }
            r2 = 2
            java.lang.Object r9 = r1.zzq(r11, r2)     // Catch:{ SQLiteException -> 0x00fd }
            java.lang.String r14 = r11.getString(r12)     // Catch:{ SQLiteException -> 0x00fd }
            if (r9 != 0) goto L_0x00e2
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzs     // Catch:{ SQLiteException -> 0x00fd }
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()     // Catch:{ SQLiteException -> 0x00fd }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()     // Catch:{ SQLiteException -> 0x00fd }
            java.lang.String r3 = "(2)Read invalid user property value, ignoring it"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzel.zzn(r17)     // Catch:{ SQLiteException -> 0x00fd }
            r10 = r19
            r2.zzd(r3, r4, r14, r10)     // Catch:{ SQLiteException -> 0x00fd }
            goto L_0x00f0
        L_0x00e2:
            r10 = r19
            com.google.android.gms.measurement.internal.zzkx r2 = new com.google.android.gms.measurement.internal.zzkx     // Catch:{ SQLiteException -> 0x00fd }
            r3 = r2
            r4 = r17
            r5 = r14
            r3.<init>(r4, r5, r6, r7, r9)     // Catch:{ SQLiteException -> 0x00fd }
            r0.add(r2)     // Catch:{ SQLiteException -> 0x00fd }
        L_0x00f0:
            boolean r2 = r11.moveToNext()     // Catch:{ SQLiteException -> 0x00fd }
            if (r2 == 0) goto L_0x00f7
            goto L_0x0091
        L_0x00f7:
            if (r11 == 0) goto L_0x00fc
            r11.close()
        L_0x00fc:
            return r0
        L_0x00fd:
            r0 = move-exception
            goto L_0x010a
        L_0x00ff:
            r0 = move-exception
            goto L_0x010a
        L_0x0101:
            r0 = move-exception
            goto L_0x0108
        L_0x0103:
            r0 = move-exception
            goto L_0x0128
        L_0x0105:
            r0 = move-exception
            r13 = r17
        L_0x0108:
            r14 = r18
        L_0x010a:
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzs     // Catch:{ all -> 0x0127 }
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()     // Catch:{ all -> 0x0127 }
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()     // Catch:{ all -> 0x0127 }
            java.lang.String r3 = "(2)Error querying user properties"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzel.zzn(r17)     // Catch:{ all -> 0x0127 }
            r2.zzd(r3, r4, r14, r0)     // Catch:{ all -> 0x0127 }
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0127 }
            if (r11 == 0) goto L_0x0126
            r11.close()
        L_0x0126:
            return r0
        L_0x0127:
            r0 = move-exception
        L_0x0128:
            if (r11 == 0) goto L_0x012d
            r11.close()
        L_0x012d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaj.zzv(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    public final void zzw() {
        zzY();
        zzh().beginTransaction();
    }

    public final void zzx(List<Long> list) {
        Preconditions.checkNotNull(list);
        zzg();
        zzY();
        StringBuilder sb = new StringBuilder("rowid in (");
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(list.get(i).longValue());
        }
        sb.append(")");
        int delete = zzh().delete("raw_events", sb.toString(), (String[]) null);
        if (delete != list.size()) {
            this.zzs.zzay().zzd().zzc("Deleted fewer rows from raw events table than expected", Integer.valueOf(delete), Integer.valueOf(list.size()));
        }
    }

    public final void zzy() {
        zzY();
        zzh().endTransaction();
    }

    /* access modifiers changed from: package-private */
    public final void zzz(List<Long> list) {
        zzg();
        zzY();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzK()) {
            String join = TextUtils.join(",", list);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append("(");
            sb.append(join);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(sb2.length() + 80);
            sb3.append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
            sb3.append(sb2);
            sb3.append(" AND retry_count =  2147483647 LIMIT 1");
            if (zzab(sb3.toString(), (String[]) null) > 0) {
                this.zzs.zzay().zzk().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                SQLiteDatabase zzh2 = zzh();
                StringBuilder sb4 = new StringBuilder(sb2.length() + 127);
                sb4.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");
                sb4.append(sb2);
                sb4.append(" AND (retry_count IS NULL OR retry_count < ");
                sb4.append(Integer.MAX_VALUE);
                sb4.append(")");
                zzh2.execSQL(sb4.toString());
            } catch (SQLiteException e) {
                this.zzs.zzay().zzd().zzb("Error incrementing retry count. error", e);
            }
        }
    }
}
