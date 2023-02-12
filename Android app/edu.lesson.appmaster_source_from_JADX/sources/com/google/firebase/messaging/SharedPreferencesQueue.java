package com.google.firebase.messaging;

import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-messaging@@23.0.0 */
final class SharedPreferencesQueue {
    private boolean bulkOperation = false;
    private final ArrayDeque<String> internalQueue = new ArrayDeque<>();
    private final String itemSeparator;
    private final String queueName;
    private final SharedPreferences sharedPreferences;
    private final Executor syncExecutor;

    private SharedPreferencesQueue(SharedPreferences sharedPreferences2, String str, String str2, Executor executor) {
        this.sharedPreferences = sharedPreferences2;
        this.queueName = "topic_operation_queue";
        this.itemSeparator = ",";
        this.syncExecutor = executor;
    }

    private boolean checkAndSyncState(boolean z) {
        if (!z || this.bulkOperation) {
            return z;
        }
        syncStateAsync();
        return true;
    }

    static SharedPreferencesQueue createInstance(SharedPreferences sharedPreferences2, String str, String str2, Executor executor) {
        SharedPreferencesQueue sharedPreferencesQueue = new SharedPreferencesQueue(sharedPreferences2, "topic_operation_queue", ",", executor);
        sharedPreferencesQueue.initQueue();
        return sharedPreferencesQueue;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initQueue() {
        /*
            r6 = this;
            java.util.ArrayDeque<java.lang.String> r0 = r6.internalQueue
            monitor-enter(r0)
            java.util.ArrayDeque<java.lang.String> r1 = r6.internalQueue     // Catch:{ all -> 0x004b }
            r1.clear()     // Catch:{ all -> 0x004b }
            android.content.SharedPreferences r1 = r6.sharedPreferences     // Catch:{ all -> 0x004b }
            java.lang.String r2 = r6.queueName     // Catch:{ all -> 0x004b }
            java.lang.String r3 = ""
            java.lang.String r1 = r1.getString(r2, r3)     // Catch:{ all -> 0x004b }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x004b }
            if (r2 != 0) goto L_0x0049
            java.lang.String r2 = r6.itemSeparator     // Catch:{ all -> 0x004b }
            boolean r2 = r1.contains(r2)     // Catch:{ all -> 0x004b }
            if (r2 != 0) goto L_0x0021
            goto L_0x0049
        L_0x0021:
            java.lang.String r2 = r6.itemSeparator     // Catch:{ all -> 0x004b }
            r3 = -1
            java.lang.String[] r1 = r1.split(r2, r3)     // Catch:{ all -> 0x004b }
            int r2 = r1.length     // Catch:{ all -> 0x004b }
            r3 = 0
            if (r2 != 0) goto L_0x0034
            java.lang.String r4 = "FirebaseMessaging"
            java.lang.String r5 = "Corrupted queue. Please check the queue contents and item separator provided"
            android.util.Log.e(r4, r5)     // Catch:{ all -> 0x004b }
            goto L_0x0035
        L_0x0034:
        L_0x0035:
            if (r3 >= r2) goto L_0x0047
            r4 = r1[r3]     // Catch:{ all -> 0x004b }
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x004b }
            if (r5 != 0) goto L_0x0044
            java.util.ArrayDeque<java.lang.String> r5 = r6.internalQueue     // Catch:{ all -> 0x004b }
            r5.add(r4)     // Catch:{ all -> 0x004b }
        L_0x0044:
            int r3 = r3 + 1
            goto L_0x0035
        L_0x0047:
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            return
        L_0x0049:
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            return
        L_0x004b:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.SharedPreferencesQueue.initQueue():void");
    }

    /* access modifiers changed from: private */
    public void syncState() {
        synchronized (this.internalQueue) {
            this.sharedPreferences.edit().putString(this.queueName, serialize()).commit();
        }
    }

    private void syncStateAsync() {
        this.syncExecutor.execute(new SharedPreferencesQueue$$ExternalSyntheticLambda0(this));
    }

    public boolean add(String str) {
        boolean add;
        if (TextUtils.isEmpty(str) || str.contains(this.itemSeparator)) {
            return false;
        }
        synchronized (this.internalQueue) {
            add = this.internalQueue.add(str);
            checkAndSyncState(add);
        }
        return add;
    }

    public String peek() {
        String peek;
        synchronized (this.internalQueue) {
            peek = this.internalQueue.peek();
        }
        return peek;
    }

    public boolean remove(Object obj) {
        boolean remove;
        synchronized (this.internalQueue) {
            remove = this.internalQueue.remove(obj);
            checkAndSyncState(remove);
        }
        return remove;
    }

    public String serialize() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.internalQueue.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(this.itemSeparator);
        }
        return sb.toString();
    }
}
