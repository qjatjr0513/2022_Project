package com.google.android.gms.common.internal;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
public final class RootTelemetryConfigManager {
    private static RootTelemetryConfigManager zza = null;
    private static final RootTelemetryConfiguration zzb = new RootTelemetryConfiguration(0, false, false, 0, 0);
    private RootTelemetryConfiguration zzc;

    private RootTelemetryConfigManager() {
    }

    public static synchronized RootTelemetryConfigManager getInstance() {
        RootTelemetryConfigManager rootTelemetryConfigManager;
        synchronized (RootTelemetryConfigManager.class) {
            if (zza == null) {
                zza = new RootTelemetryConfigManager();
            }
            rootTelemetryConfigManager = zza;
        }
        return rootTelemetryConfigManager;
    }

    public RootTelemetryConfiguration getConfig() {
        return this.zzc;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        if (r0.getVersion() >= r3.getVersion()) goto L_0x0007;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zza(com.google.android.gms.common.internal.RootTelemetryConfiguration r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r3 != 0) goto L_0x0009
            com.google.android.gms.common.internal.RootTelemetryConfiguration r3 = zzb     // Catch:{ all -> 0x0018 }
        L_0x0005:
            r2.zzc = r3     // Catch:{ all -> 0x0018 }
        L_0x0007:
            monitor-exit(r2)
            return
        L_0x0009:
            com.google.android.gms.common.internal.RootTelemetryConfiguration r0 = r2.zzc     // Catch:{ all -> 0x0018 }
            if (r0 == 0) goto L_0x0017
            int r0 = r0.getVersion()     // Catch:{ all -> 0x0018 }
            int r1 = r3.getVersion()     // Catch:{ all -> 0x0018 }
            if (r0 >= r1) goto L_0x0007
        L_0x0017:
            goto L_0x0005
        L_0x0018:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.RootTelemetryConfigManager.zza(com.google.android.gms.common.internal.RootTelemetryConfiguration):void");
    }
}
