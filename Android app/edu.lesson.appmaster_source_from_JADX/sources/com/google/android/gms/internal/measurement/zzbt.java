package com.google.android.gms.internal.measurement;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.os.Build;
import android.os.UserHandle;
import android.util.Log;
import java.lang.reflect.Method;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzbt {
    private static final Method zza;
    private static final Method zzb;

    static {
        Method method;
        Method method2 = null;
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                method = JobScheduler.class.getDeclaredMethod("scheduleAsPackage", new Class[]{JobInfo.class, String.class, Integer.TYPE, String.class});
            } catch (NoSuchMethodException e) {
                if (Log.isLoggable("JobSchedulerCompat", 6)) {
                    Log.e("JobSchedulerCompat", "No scheduleAsPackage method available, falling back to schedule");
                    method = null;
                } else {
                    method = null;
                }
            }
        } else {
            method = null;
        }
        zza = method;
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                method2 = UserHandle.class.getDeclaredMethod("myUserId", new Class[0]);
            } catch (NoSuchMethodException e2) {
                if (Log.isLoggable("JobSchedulerCompat", 6)) {
                    Log.e("JobSchedulerCompat", "No myUserId method available");
                }
            }
        }
        zzb = method2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x004b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int zza(android.content.Context r5, android.app.job.JobInfo r6, java.lang.String r7, java.lang.String r8) {
        /*
            java.lang.String r7 = "jobscheduler"
            java.lang.Object r7 = r5.getSystemService(r7)
            android.app.job.JobScheduler r7 = (android.app.job.JobScheduler) r7
            if (r7 == 0) goto L_0x007c
            java.lang.reflect.Method r8 = zza
            if (r8 == 0) goto L_0x0077
            java.lang.String r8 = "android.permission.UPDATE_DEVICE_STATS"
            int r5 = r5.checkSelfPermission(r8)
            if (r5 == 0) goto L_0x0017
            goto L_0x0077
        L_0x0017:
            java.lang.reflect.Method r5 = zzb
            r8 = 0
            if (r5 == 0) goto L_0x0042
            java.lang.Class<android.os.UserHandle> r0 = android.os.UserHandle.class
            java.lang.Object[] r1 = new java.lang.Object[r8]     // Catch:{ IllegalAccessException -> 0x002f, InvocationTargetException -> 0x002d }
            java.lang.Object r5 = r5.invoke(r0, r1)     // Catch:{ IllegalAccessException -> 0x002f, InvocationTargetException -> 0x002d }
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ IllegalAccessException -> 0x002f, InvocationTargetException -> 0x002d }
            if (r5 == 0) goto L_0x0042
            int r5 = r5.intValue()     // Catch:{ IllegalAccessException -> 0x002f, InvocationTargetException -> 0x002d }
            goto L_0x0043
        L_0x002d:
            r5 = move-exception
            goto L_0x0030
        L_0x002f:
            r5 = move-exception
        L_0x0030:
            r0 = 6
            java.lang.String r1 = "JobSchedulerCompat"
            boolean r0 = android.util.Log.isLoggable(r1, r0)
            if (r0 == 0) goto L_0x0040
            java.lang.String r0 = "myUserId invocation illegal"
            android.util.Log.e(r1, r0, r5)
            r5 = r8
            goto L_0x0043
        L_0x0040:
            r5 = r8
            goto L_0x0043
        L_0x0042:
            r5 = r8
        L_0x0043:
            java.lang.String r0 = "com.google.android.gms"
            java.lang.String r1 = "UploadAlarm"
            java.lang.reflect.Method r2 = zza
            if (r2 == 0) goto L_0x0072
            r3 = 4
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ IllegalAccessException -> 0x006c, InvocationTargetException -> 0x006a }
            r3[r8] = r6     // Catch:{ IllegalAccessException -> 0x006c, InvocationTargetException -> 0x006a }
            r4 = 1
            r3[r4] = r0     // Catch:{ IllegalAccessException -> 0x006c, InvocationTargetException -> 0x006a }
            r0 = 2
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ IllegalAccessException -> 0x006c, InvocationTargetException -> 0x006a }
            r3[r0] = r5     // Catch:{ IllegalAccessException -> 0x006c, InvocationTargetException -> 0x006a }
            r5 = 3
            r3[r5] = r1     // Catch:{ IllegalAccessException -> 0x006c, InvocationTargetException -> 0x006a }
            java.lang.Object r5 = r2.invoke(r7, r3)     // Catch:{ IllegalAccessException -> 0x006c, InvocationTargetException -> 0x006a }
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ IllegalAccessException -> 0x006c, InvocationTargetException -> 0x006a }
            if (r5 == 0) goto L_0x0069
            int r8 = r5.intValue()     // Catch:{ IllegalAccessException -> 0x006c, InvocationTargetException -> 0x006a }
        L_0x0069:
            goto L_0x0076
        L_0x006a:
            r5 = move-exception
            goto L_0x006d
        L_0x006c:
            r5 = move-exception
        L_0x006d:
            java.lang.String r8 = "error calling scheduleAsPackage"
            android.util.Log.e(r1, r8, r5)
        L_0x0072:
            int r8 = r7.schedule(r6)
        L_0x0076:
            return r8
        L_0x0077:
            int r5 = r7.schedule(r6)
            return r5
        L_0x007c:
            r5 = 0
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbt.zza(android.content.Context, android.app.job.JobInfo, java.lang.String, java.lang.String):int");
    }
}
