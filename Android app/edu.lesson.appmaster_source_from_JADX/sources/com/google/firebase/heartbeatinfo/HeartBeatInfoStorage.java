package com.google.firebase.heartbeatinfo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

class HeartBeatInfoStorage {
    private static final String GLOBAL = "fire-global";
    private static final String HEARTBEAT_PREFERENCES_NAME = "FirebaseHeartBeat";
    private static final int HEART_BEAT_COUNT_LIMIT = 30;
    private static final String HEART_BEAT_COUNT_TAG = "fire-count";
    private static final String LAST_STORED_DATE = "last-used-date";
    private static final String PREFERENCES_NAME = "FirebaseAppHeartBeat";
    private static HeartBeatInfoStorage instance = null;
    private final SharedPreferences firebaseSharedPreferences;

    public HeartBeatInfoStorage(Context applicationContext, String persistenceKey) {
        this.firebaseSharedPreferences = applicationContext.getSharedPreferences(HEARTBEAT_PREFERENCES_NAME + persistenceKey, 0);
    }

    HeartBeatInfoStorage(SharedPreferences firebaseSharedPreferences2) {
        this.firebaseSharedPreferences = firebaseSharedPreferences2;
    }

    /* access modifiers changed from: package-private */
    public int getHeartBeatCount() {
        return (int) this.firebaseSharedPreferences.getLong(HEART_BEAT_COUNT_TAG, 0);
    }

    /* access modifiers changed from: package-private */
    public synchronized void deleteAllHeartBeats() {
        SharedPreferences.Editor editor = this.firebaseSharedPreferences.edit();
        for (Map.Entry<String, ?> entry : this.firebaseSharedPreferences.getAll().entrySet()) {
            if (entry.getValue() instanceof Set) {
                editor.remove(entry.getKey());
            }
        }
        editor.remove(HEART_BEAT_COUNT_TAG);
        editor.commit();
    }

    /* access modifiers changed from: package-private */
    public synchronized List<HeartBeatResult> getAllHeartBeats() {
        ArrayList<HeartBeatResult> heartBeatResults;
        heartBeatResults = new ArrayList<>();
        for (Map.Entry<String, ?> entry : this.firebaseSharedPreferences.getAll().entrySet()) {
            if (entry.getValue() instanceof Set) {
                heartBeatResults.add(HeartBeatResult.create(entry.getKey(), new ArrayList((Set) entry.getValue())));
            }
        }
        updateGlobalHeartBeat(System.currentTimeMillis());
        return heartBeatResults;
    }

    private synchronized String getStoredUserAgentString(String dateString) {
        for (Map.Entry<String, ?> entry : this.firebaseSharedPreferences.getAll().entrySet()) {
            if (entry.getValue() instanceof Set) {
                for (String date : (Set) entry.getValue()) {
                    if (dateString.equals(date)) {
                        return entry.getKey();
                    }
                }
                continue;
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void removeStoredDate(java.lang.String r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.String r0 = r4.getStoredUserAgentString(r5)     // Catch:{ all -> 0x003f }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r4)
            return
        L_0x0009:
            java.util.HashSet r1 = new java.util.HashSet     // Catch:{ all -> 0x003f }
            android.content.SharedPreferences r2 = r4.firebaseSharedPreferences     // Catch:{ all -> 0x003f }
            java.util.HashSet r3 = new java.util.HashSet     // Catch:{ all -> 0x003f }
            r3.<init>()     // Catch:{ all -> 0x003f }
            java.util.Set r2 = r2.getStringSet(r0, r3)     // Catch:{ all -> 0x003f }
            r1.<init>(r2)     // Catch:{ all -> 0x003f }
            r1.remove(r5)     // Catch:{ all -> 0x003f }
            boolean r2 = r1.isEmpty()     // Catch:{ all -> 0x003f }
            if (r2 == 0) goto L_0x0030
            android.content.SharedPreferences r2 = r4.firebaseSharedPreferences     // Catch:{ all -> 0x003f }
            android.content.SharedPreferences$Editor r2 = r2.edit()     // Catch:{ all -> 0x003f }
            android.content.SharedPreferences$Editor r2 = r2.remove(r0)     // Catch:{ all -> 0x003f }
            r2.commit()     // Catch:{ all -> 0x003f }
            goto L_0x003d
        L_0x0030:
            android.content.SharedPreferences r2 = r4.firebaseSharedPreferences     // Catch:{ all -> 0x003f }
            android.content.SharedPreferences$Editor r2 = r2.edit()     // Catch:{ all -> 0x003f }
            android.content.SharedPreferences$Editor r2 = r2.putStringSet(r0, r1)     // Catch:{ all -> 0x003f }
            r2.commit()     // Catch:{ all -> 0x003f }
        L_0x003d:
            monitor-exit(r4)
            return
        L_0x003f:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.heartbeatinfo.HeartBeatInfoStorage.removeStoredDate(java.lang.String):void");
    }

    /* access modifiers changed from: package-private */
    public synchronized void postHeartBeatCleanUp() {
        String dateString = getFormattedDate(System.currentTimeMillis());
        this.firebaseSharedPreferences.edit().putString(LAST_STORED_DATE, dateString).commit();
        removeStoredDate(dateString);
    }

    private synchronized String getFormattedDate(long millis) {
        if (Build.VERSION.SDK_INT >= 26) {
            return new Date(millis).toInstant().atOffset(ZoneOffset.UTC).toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
        return new SimpleDateFormat("yyyy-MM-dd", Locale.UK).format(new Date(millis));
    }

    /* access modifiers changed from: package-private */
    public synchronized void storeHeartBeat(long millis, String userAgentString) {
        String dateString = getFormattedDate(millis);
        if (!this.firebaseSharedPreferences.getString(LAST_STORED_DATE, "").equals(dateString)) {
            long heartBeatCount = this.firebaseSharedPreferences.getLong(HEART_BEAT_COUNT_TAG, 0);
            if (heartBeatCount + 1 == 30) {
                cleanUpStoredHeartBeats();
                heartBeatCount = this.firebaseSharedPreferences.getLong(HEART_BEAT_COUNT_TAG, 0);
            }
            Set<String> userAgentDateSet = new HashSet<>(this.firebaseSharedPreferences.getStringSet(userAgentString, new HashSet()));
            userAgentDateSet.add(dateString);
            this.firebaseSharedPreferences.edit().putStringSet(userAgentString, userAgentDateSet).putLong(HEART_BEAT_COUNT_TAG, heartBeatCount + 1).putString(LAST_STORED_DATE, dateString).commit();
        }
    }

    private synchronized void cleanUpStoredHeartBeats() {
        long heartBeatCount = this.firebaseSharedPreferences.getLong(HEART_BEAT_COUNT_TAG, 0);
        String lowestDate = null;
        String userAgentString = "";
        for (Map.Entry<String, ?> entry : this.firebaseSharedPreferences.getAll().entrySet()) {
            if (entry.getValue() instanceof Set) {
                for (String date : (Set) entry.getValue()) {
                    if (lowestDate == null || lowestDate.compareTo(date) > 0) {
                        lowestDate = date;
                        userAgentString = entry.getKey();
                    }
                }
            }
        }
        Set<String> userAgentDateSet = new HashSet<>(this.firebaseSharedPreferences.getStringSet(userAgentString, new HashSet()));
        userAgentDateSet.remove(lowestDate);
        this.firebaseSharedPreferences.edit().putStringSet(userAgentString, userAgentDateSet).putLong(HEART_BEAT_COUNT_TAG, heartBeatCount - 1).commit();
    }

    /* access modifiers changed from: package-private */
    public synchronized long getLastGlobalHeartBeat() {
        return this.firebaseSharedPreferences.getLong(GLOBAL, -1);
    }

    /* access modifiers changed from: package-private */
    public synchronized void updateGlobalHeartBeat(long millis) {
        this.firebaseSharedPreferences.edit().putLong(GLOBAL, millis).commit();
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean isSameDateUtc(long base, long target) {
        return getFormattedDate(base).equals(getFormattedDate(target));
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean shouldSendSdkHeartBeat(String heartBeatTag, long millis) {
        if (!this.firebaseSharedPreferences.contains(heartBeatTag)) {
            this.firebaseSharedPreferences.edit().putLong(heartBeatTag, millis).commit();
            return true;
        } else if (isSameDateUtc(this.firebaseSharedPreferences.getLong(heartBeatTag, -1), millis)) {
            return false;
        } else {
            this.firebaseSharedPreferences.edit().putLong(heartBeatTag, millis).commit();
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean shouldSendGlobalHeartBeat(long millis) {
        return shouldSendSdkHeartBeat(GLOBAL, millis);
    }
}
