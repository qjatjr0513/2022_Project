package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zzdx<V> {
    private static final Object zza = new Object();
    private final String zzb;
    private final zzdu<V> zzc;
    private final V zzd;
    private final V zze;
    private final Object zzf = new Object();
    private volatile V zzg = null;
    private volatile V zzh = null;

    /* synthetic */ zzdx(String str, Object obj, Object obj2, zzdu zzdu, zzdw zzdw) {
        this.zzb = str;
        this.zzd = obj;
        this.zze = obj2;
        this.zzc = zzdu;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x001e, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r4 = com.google.android.gms.measurement.internal.zzdy.zzb().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x002c, code lost:
        if (r4.hasNext() == false) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x002e, code lost:
        r0 = (com.google.android.gms.measurement.internal.zzdx) r4.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0038, code lost:
        if (com.google.android.gms.measurement.internal.zzaa.zza() != false) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x003a, code lost:
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r2 = r0.zzc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x003d, code lost:
        if (r2 == null) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x003f, code lost:
        r1 = r2.zza();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0057, code lost:
        throw new java.lang.IllegalStateException("Refreshing flag cache must be done on a worker thread.");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final V zza(V r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.zzf
            monitor-enter(r0)
            monitor-exit(r0)     // Catch:{ all -> 0x006f }
            if (r4 == 0) goto L_0x0007
            return r4
        L_0x0007:
            com.google.android.gms.measurement.internal.zzaa r4 = com.google.android.gms.measurement.internal.zzdv.zza
            if (r4 == 0) goto L_0x006c
            java.lang.Object r4 = zza
            monitor-enter(r4)
            boolean r0 = com.google.android.gms.measurement.internal.zzaa.zza()     // Catch:{ all -> 0x0069 }
            if (r0 == 0) goto L_0x001f
            V r0 = r3.zzh     // Catch:{ all -> 0x0069 }
            if (r0 != 0) goto L_0x001b
            V r0 = r3.zzd     // Catch:{ all -> 0x0069 }
            goto L_0x001d
        L_0x001b:
            V r0 = r3.zzh     // Catch:{ all -> 0x0069 }
        L_0x001d:
            monitor-exit(r4)     // Catch:{ all -> 0x0069 }
            return r0
        L_0x001f:
            monitor-exit(r4)     // Catch:{ all -> 0x0069 }
            java.util.List r4 = com.google.android.gms.measurement.internal.zzdy.zzaD     // Catch:{ SecurityException -> 0x0058 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ SecurityException -> 0x0058 }
        L_0x0028:
            boolean r0 = r4.hasNext()     // Catch:{ SecurityException -> 0x0058 }
            if (r0 == 0) goto L_0x0059
            java.lang.Object r0 = r4.next()     // Catch:{ SecurityException -> 0x0058 }
            com.google.android.gms.measurement.internal.zzdx r0 = (com.google.android.gms.measurement.internal.zzdx) r0     // Catch:{ SecurityException -> 0x0058 }
            boolean r1 = com.google.android.gms.measurement.internal.zzaa.zza()     // Catch:{ SecurityException -> 0x0058 }
            if (r1 != 0) goto L_0x0050
            r1 = 0
            com.google.android.gms.measurement.internal.zzdu<V> r2 = r0.zzc     // Catch:{ IllegalStateException -> 0x0045 }
            if (r2 == 0) goto L_0x0044
            java.lang.Object r1 = r2.zza()     // Catch:{ IllegalStateException -> 0x0045 }
            goto L_0x0046
        L_0x0044:
            goto L_0x0046
        L_0x0045:
            r2 = move-exception
        L_0x0046:
            java.lang.Object r2 = zza     // Catch:{ SecurityException -> 0x0058 }
            monitor-enter(r2)     // Catch:{ SecurityException -> 0x0058 }
            r0.zzh = r1     // Catch:{ all -> 0x004d }
            monitor-exit(r2)     // Catch:{ all -> 0x004d }
            goto L_0x0028
        L_0x004d:
            r4 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x004d }
            throw r4     // Catch:{ SecurityException -> 0x0058 }
        L_0x0050:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ SecurityException -> 0x0058 }
            java.lang.String r0 = "Refreshing flag cache must be done on a worker thread."
            r4.<init>(r0)     // Catch:{ SecurityException -> 0x0058 }
            throw r4     // Catch:{ SecurityException -> 0x0058 }
        L_0x0058:
            r4 = move-exception
        L_0x0059:
            com.google.android.gms.measurement.internal.zzdu<V> r4 = r3.zzc
            if (r4 != 0) goto L_0x0060
        L_0x005d:
            V r4 = r3.zzd
            return r4
        L_0x0060:
            java.lang.Object r4 = r4.zza()     // Catch:{ SecurityException -> 0x0067, IllegalStateException -> 0x0065 }
            return r4
        L_0x0065:
            r4 = move-exception
            goto L_0x005d
        L_0x0067:
            r4 = move-exception
            goto L_0x005d
        L_0x0069:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0069 }
            throw r0
        L_0x006c:
            V r4 = r3.zzd
            return r4
        L_0x006f:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x006f }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzdx.zza(java.lang.Object):java.lang.Object");
    }

    public final String zzb() {
        return this.zzb;
    }
}
