package com.google.firebase.database.core;

import com.google.firebase.database.core.view.QuerySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ZombieEventManager implements EventRegistrationZombieListener {
    private static ZombieEventManager defaultInstance = new ZombieEventManager();
    final HashMap<EventRegistration, List<EventRegistration>> globalEventRegistrations = new HashMap<>();

    private ZombieEventManager() {
    }

    public static ZombieEventManager getInstance() {
        return defaultInstance;
    }

    public void recordEventRegistration(EventRegistration registration) {
        synchronized (this.globalEventRegistrations) {
            List<EventRegistration> registrationList = this.globalEventRegistrations.get(registration);
            if (registrationList == null) {
                registrationList = new ArrayList<>();
                this.globalEventRegistrations.put(registration, registrationList);
            }
            registrationList.add(registration);
            if (!registration.getQuerySpec().isDefault()) {
                EventRegistration defaultRegistration = registration.clone(QuerySpec.defaultQueryAtPath(registration.getQuerySpec().getPath()));
                List<EventRegistration> registrationList2 = this.globalEventRegistrations.get(defaultRegistration);
                if (registrationList2 == null) {
                    registrationList2 = new ArrayList<>();
                    this.globalEventRegistrations.put(defaultRegistration, registrationList2);
                }
                registrationList2.add(registration);
            }
            registration.setIsUserInitiated(true);
            registration.setOnZombied(this);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0077 A[EDGE_INSN: B:43:0x0077->B:33:0x0077 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void unRecordEventRegistration(com.google.firebase.database.core.EventRegistration r7) {
        /*
            r6 = this;
            java.util.HashMap<com.google.firebase.database.core.EventRegistration, java.util.List<com.google.firebase.database.core.EventRegistration>> r0 = r6.globalEventRegistrations
            monitor-enter(r0)
            r1 = 0
            java.util.HashMap<com.google.firebase.database.core.EventRegistration, java.util.List<com.google.firebase.database.core.EventRegistration>> r2 = r6.globalEventRegistrations     // Catch:{ all -> 0x0084 }
            java.lang.Object r2 = r2.get(r7)     // Catch:{ all -> 0x0084 }
            java.util.List r2 = (java.util.List) r2     // Catch:{ all -> 0x0084 }
            if (r2 == 0) goto L_0x002e
            r3 = 0
        L_0x000f:
            int r4 = r2.size()     // Catch:{ all -> 0x0084 }
            if (r3 >= r4) goto L_0x0023
            java.lang.Object r4 = r2.get(r3)     // Catch:{ all -> 0x0084 }
            if (r4 != r7) goto L_0x0020
            r1 = 1
            r2.remove(r3)     // Catch:{ all -> 0x0084 }
            goto L_0x0023
        L_0x0020:
            int r3 = r3 + 1
            goto L_0x000f
        L_0x0023:
            boolean r3 = r2.isEmpty()     // Catch:{ all -> 0x0084 }
            if (r3 == 0) goto L_0x002e
            java.util.HashMap<com.google.firebase.database.core.EventRegistration, java.util.List<com.google.firebase.database.core.EventRegistration>> r3 = r6.globalEventRegistrations     // Catch:{ all -> 0x0084 }
            r3.remove(r7)     // Catch:{ all -> 0x0084 }
        L_0x002e:
            if (r1 != 0) goto L_0x0039
            boolean r3 = r7.isUserInitiated()     // Catch:{ all -> 0x0084 }
            if (r3 != 0) goto L_0x0037
            goto L_0x0039
        L_0x0037:
            r3 = 0
            goto L_0x003a
        L_0x0039:
            r3 = 1
        L_0x003a:
            com.google.firebase.database.core.utilities.Utilities.hardAssert(r3)     // Catch:{ all -> 0x0084 }
            com.google.firebase.database.core.view.QuerySpec r3 = r7.getQuerySpec()     // Catch:{ all -> 0x0084 }
            boolean r3 = r3.isDefault()     // Catch:{ all -> 0x0084 }
            if (r3 != 0) goto L_0x0082
            com.google.firebase.database.core.view.QuerySpec r3 = r7.getQuerySpec()     // Catch:{ all -> 0x0084 }
            com.google.firebase.database.core.Path r3 = r3.getPath()     // Catch:{ all -> 0x0084 }
            com.google.firebase.database.core.view.QuerySpec r3 = com.google.firebase.database.core.view.QuerySpec.defaultQueryAtPath(r3)     // Catch:{ all -> 0x0084 }
            com.google.firebase.database.core.EventRegistration r3 = r7.clone(r3)     // Catch:{ all -> 0x0084 }
            java.util.HashMap<com.google.firebase.database.core.EventRegistration, java.util.List<com.google.firebase.database.core.EventRegistration>> r4 = r6.globalEventRegistrations     // Catch:{ all -> 0x0084 }
            java.lang.Object r4 = r4.get(r3)     // Catch:{ all -> 0x0084 }
            java.util.List r4 = (java.util.List) r4     // Catch:{ all -> 0x0084 }
            r2 = r4
            if (r2 == 0) goto L_0x0082
            r4 = 0
        L_0x0064:
            int r5 = r2.size()     // Catch:{ all -> 0x0084 }
            if (r4 >= r5) goto L_0x0077
            java.lang.Object r5 = r2.get(r4)     // Catch:{ all -> 0x0084 }
            if (r5 != r7) goto L_0x0074
            r2.remove(r4)     // Catch:{ all -> 0x0084 }
            goto L_0x0077
        L_0x0074:
            int r4 = r4 + 1
            goto L_0x0064
        L_0x0077:
            boolean r4 = r2.isEmpty()     // Catch:{ all -> 0x0084 }
            if (r4 == 0) goto L_0x0082
            java.util.HashMap<com.google.firebase.database.core.EventRegistration, java.util.List<com.google.firebase.database.core.EventRegistration>> r4 = r6.globalEventRegistrations     // Catch:{ all -> 0x0084 }
            r4.remove(r3)     // Catch:{ all -> 0x0084 }
        L_0x0082:
            monitor-exit(r0)     // Catch:{ all -> 0x0084 }
            return
        L_0x0084:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0084 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.core.ZombieEventManager.unRecordEventRegistration(com.google.firebase.database.core.EventRegistration):void");
    }

    public void zombifyForRemove(EventRegistration registration) {
        synchronized (this.globalEventRegistrations) {
            List<EventRegistration> registrationList = this.globalEventRegistrations.get(registration);
            if (registrationList != null && !registrationList.isEmpty()) {
                if (registration.getQuerySpec().isDefault()) {
                    HashSet<QuerySpec> zombiedQueries = new HashSet<>();
                    for (int i = registrationList.size() - 1; i >= 0; i--) {
                        EventRegistration currentRegistration = registrationList.get(i);
                        if (!zombiedQueries.contains(currentRegistration.getQuerySpec())) {
                            zombiedQueries.add(currentRegistration.getQuerySpec());
                            currentRegistration.zombify();
                        }
                    }
                } else {
                    registrationList.get(0).zombify();
                }
            }
        }
    }

    public void onZombied(EventRegistration zombiedInstance) {
        unRecordEventRegistration(zombiedInstance);
    }
}
