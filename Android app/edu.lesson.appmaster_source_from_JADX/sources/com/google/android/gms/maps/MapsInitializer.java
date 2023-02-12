package com.google.android.gms.maps;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public final class MapsInitializer {
    private static final String zza = MapsInitializer.class.getSimpleName();
    private static boolean zzb = false;
    private static Renderer zzc = Renderer.LEGACY;

    /* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
    public enum Renderer {
        LEGACY,
        LATEST
    }

    private MapsInitializer() {
    }

    public static synchronized int initialize(Context context) {
        int initialize;
        synchronized (MapsInitializer.class) {
            initialize = initialize(context, (Renderer) null, (OnMapsSdkInitializedCallback) null);
        }
        return initialize;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0094, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0030, code lost:
        return 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized int initialize(android.content.Context r5, com.google.android.gms.maps.MapsInitializer.Renderer r6, com.google.android.gms.maps.OnMapsSdkInitializedCallback r7) {
        /*
            java.lang.Class<com.google.android.gms.maps.MapsInitializer> r0 = com.google.android.gms.maps.MapsInitializer.class
            monitor-enter(r0)
            java.lang.String r1 = "Context is null"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5, r1)     // Catch:{ all -> 0x00a1 }
            java.lang.String r1 = zza     // Catch:{ all -> 0x00a1 }
            java.lang.String r2 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x00a1 }
            java.lang.String r3 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x00a1 }
            r3.length()     // Catch:{ all -> 0x00a1 }
            java.lang.String r3 = "preferredRenderer: "
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x00a1 }
            java.lang.String r2 = r3.concat(r2)     // Catch:{ all -> 0x00a1 }
            android.util.Log.d(r1, r2)     // Catch:{ all -> 0x00a1 }
            boolean r1 = zzb     // Catch:{ all -> 0x00a1 }
            r2 = 0
            if (r1 == 0) goto L_0x0031
            if (r7 == 0) goto L_0x002f
            com.google.android.gms.maps.MapsInitializer$Renderer r5 = zzc     // Catch:{ all -> 0x00a1 }
            r7.onMapsSdkInitialized(r5)     // Catch:{ all -> 0x00a1 }
        L_0x002f:
            monitor-exit(r0)
            return r2
        L_0x0031:
            com.google.android.gms.maps.internal.zzf r1 = com.google.android.gms.maps.internal.zzca.zza(r5, r6)     // Catch:{ GooglePlayServicesNotAvailableException -> 0x009c }
            com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate r3 = r1.zze()     // Catch:{ RemoteException -> 0x0095 }
            com.google.android.gms.maps.CameraUpdateFactory.zza(r3)     // Catch:{ RemoteException -> 0x0095 }
            com.google.android.gms.internal.maps.zzi r3 = r1.zzj()     // Catch:{ RemoteException -> 0x0095 }
            com.google.android.gms.maps.model.BitmapDescriptorFactory.zza(r3)     // Catch:{ RemoteException -> 0x0095 }
            r3 = 1
            zzb = r3     // Catch:{ all -> 0x00a1 }
            r4 = 2
            if (r6 == 0) goto L_0x0055
            int r6 = r6.ordinal()     // Catch:{ all -> 0x00a1 }
            switch(r6) {
                case 0: goto L_0x0054;
                case 1: goto L_0x0052;
                default: goto L_0x0050;
            }
        L_0x0050:
            r3 = r2
            goto L_0x0056
        L_0x0052:
            r3 = r4
            goto L_0x0056
        L_0x0054:
            goto L_0x0056
        L_0x0055:
            r3 = r2
        L_0x0056:
            int r6 = r1.zzd()     // Catch:{ RemoteException -> 0x0068 }
            if (r6 != r4) goto L_0x0060
            com.google.android.gms.maps.MapsInitializer$Renderer r6 = com.google.android.gms.maps.MapsInitializer.Renderer.LATEST     // Catch:{ RemoteException -> 0x0068 }
            zzc = r6     // Catch:{ RemoteException -> 0x0068 }
        L_0x0060:
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r5)     // Catch:{ RemoteException -> 0x0068 }
            r1.zzl(r5, r3)     // Catch:{ RemoteException -> 0x0068 }
            goto L_0x0070
        L_0x0068:
            r5 = move-exception
            java.lang.String r6 = zza     // Catch:{ all -> 0x00a1 }
            java.lang.String r1 = "Failed to retrieve renderer type or log initialization."
            android.util.Log.e(r6, r1, r5)     // Catch:{ all -> 0x00a1 }
        L_0x0070:
            java.lang.String r5 = zza     // Catch:{ all -> 0x00a1 }
            com.google.android.gms.maps.MapsInitializer$Renderer r6 = zzc     // Catch:{ all -> 0x00a1 }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x00a1 }
            java.lang.String r1 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x00a1 }
            r1.length()     // Catch:{ all -> 0x00a1 }
            java.lang.String r1 = "loadedRenderer: "
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x00a1 }
            java.lang.String r6 = r1.concat(r6)     // Catch:{ all -> 0x00a1 }
            android.util.Log.d(r5, r6)     // Catch:{ all -> 0x00a1 }
            if (r7 == 0) goto L_0x0093
            com.google.android.gms.maps.MapsInitializer$Renderer r5 = zzc     // Catch:{ all -> 0x00a1 }
            r7.onMapsSdkInitialized(r5)     // Catch:{ all -> 0x00a1 }
        L_0x0093:
            monitor-exit(r0)
            return r2
        L_0x0095:
            r5 = move-exception
            com.google.android.gms.maps.model.RuntimeRemoteException r6 = new com.google.android.gms.maps.model.RuntimeRemoteException     // Catch:{ all -> 0x00a1 }
            r6.<init>(r5)     // Catch:{ all -> 0x00a1 }
            throw r6     // Catch:{ all -> 0x00a1 }
        L_0x009c:
            r5 = move-exception
            int r5 = r5.errorCode     // Catch:{ all -> 0x00a1 }
            monitor-exit(r0)
            return r5
        L_0x00a1:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.MapsInitializer.initialize(android.content.Context, com.google.android.gms.maps.MapsInitializer$Renderer, com.google.android.gms.maps.OnMapsSdkInitializedCallback):int");
    }
}
