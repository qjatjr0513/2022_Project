package com.google.firebase.messaging;

import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-messaging@@23.0.0 */
class RequestDeduplicator {
    private final Executor executor;
    private final Map<String, Task<String>> getTokenRequests = new ArrayMap();

    /* compiled from: com.google.firebase:firebase-messaging@@23.0.0 */
    interface GetTokenRequest {
        Task<String> start();
    }

    RequestDeduplicator(Executor executor2) {
        this.executor = executor2;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0030, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.google.android.gms.tasks.Task<java.lang.String> getOrStartGetTokenRequest(java.lang.String r4, com.google.firebase.messaging.RequestDeduplicator.GetTokenRequest r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.util.Map<java.lang.String, com.google.android.gms.tasks.Task<java.lang.String>> r0 = r3.getTokenRequests     // Catch:{ all -> 0x006a }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x006a }
            com.google.android.gms.tasks.Task r0 = (com.google.android.gms.tasks.Task) r0     // Catch:{ all -> 0x006a }
            r1 = 3
            if (r0 == 0) goto L_0x0031
            java.lang.String r5 = "FirebaseMessaging"
            boolean r5 = android.util.Log.isLoggable(r5, r1)     // Catch:{ all -> 0x006a }
            if (r5 == 0) goto L_0x002f
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x006a }
            java.lang.String r5 = "Joining ongoing request for: "
            int r1 = r4.length()     // Catch:{ all -> 0x006a }
            if (r1 == 0) goto L_0x0025
            java.lang.String r4 = r5.concat(r4)     // Catch:{ all -> 0x006a }
            goto L_0x002a
        L_0x0025:
            java.lang.String r4 = new java.lang.String     // Catch:{ all -> 0x006a }
            r4.<init>(r5)     // Catch:{ all -> 0x006a }
        L_0x002a:
            java.lang.String r5 = "FirebaseMessaging"
            android.util.Log.d(r5, r4)     // Catch:{ all -> 0x006a }
        L_0x002f:
            monitor-exit(r3)
            return r0
        L_0x0031:
            java.lang.String r0 = "FirebaseMessaging"
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x006a }
            if (r0 == 0) goto L_0x0054
            java.lang.String r0 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x006a }
            java.lang.String r1 = "Making new request for: "
            int r2 = r0.length()     // Catch:{ all -> 0x006a }
            if (r2 == 0) goto L_0x004a
            java.lang.String r0 = r1.concat(r0)     // Catch:{ all -> 0x006a }
            goto L_0x004f
        L_0x004a:
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x006a }
            r0.<init>(r1)     // Catch:{ all -> 0x006a }
        L_0x004f:
            java.lang.String r1 = "FirebaseMessaging"
            android.util.Log.d(r1, r0)     // Catch:{ all -> 0x006a }
        L_0x0054:
            com.google.android.gms.tasks.Task r5 = r5.start()     // Catch:{ all -> 0x006a }
            java.util.concurrent.Executor r0 = r3.executor     // Catch:{ all -> 0x006a }
            com.google.firebase.messaging.RequestDeduplicator$$ExternalSyntheticLambda0 r1 = new com.google.firebase.messaging.RequestDeduplicator$$ExternalSyntheticLambda0     // Catch:{ all -> 0x006a }
            r1.<init>(r3, r4)     // Catch:{ all -> 0x006a }
            com.google.android.gms.tasks.Task r5 = r5.continueWithTask(r0, r1)     // Catch:{ all -> 0x006a }
            java.util.Map<java.lang.String, com.google.android.gms.tasks.Task<java.lang.String>> r0 = r3.getTokenRequests     // Catch:{ all -> 0x006a }
            r0.put(r4, r5)     // Catch:{ all -> 0x006a }
            monitor-exit(r3)
            return r5
        L_0x006a:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.RequestDeduplicator.getOrStartGetTokenRequest(java.lang.String, com.google.firebase.messaging.RequestDeduplicator$GetTokenRequest):com.google.android.gms.tasks.Task");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getOrStartGetTokenRequest$0$com-google-firebase-messaging-RequestDeduplicator */
    public /* synthetic */ Task mo10326x7161fc54(String str, Task task) throws Exception {
        synchronized (this) {
            this.getTokenRequests.remove(str);
        }
        return task;
    }
}
