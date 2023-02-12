package com.google.android.gms.internal.measurement;

import android.content.Context;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public abstract class zzhu<T> {
    public static final /* synthetic */ int zzc = 0;
    private static final Object zzd = new Object();
    @Nullable
    private static volatile zzhs zze = null;
    private static volatile boolean zzf = false;
    private static final AtomicReference<Collection<zzhu<?>>> zzg = new AtomicReference<>();
    private static final zzhw zzh = new zzhw(zzhl.zza, (byte[]) null);
    private static final AtomicInteger zzi = new AtomicInteger();
    final zzhr zza;
    final String zzb;
    private final T zzj;
    private volatile int zzk = -1;
    private volatile T zzl;
    private final boolean zzm;

    /* synthetic */ zzhu(zzhr zzhr, String str, Object obj, boolean z, zzht zzht) {
        if (zzhr.zzb != null) {
            this.zza = zzhr;
            this.zzb = str;
            this.zzj = obj;
            this.zzm = true;
            return;
        }
        throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
    }

    @Deprecated
    public static void zzd(Context context) {
        synchronized (zzd) {
            zzhs zzhs = zze;
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            if (zzhs == null || zzhs.zza() != context) {
                zzha.zze();
                zzhv.zzc();
                zzhh.zze();
                zze = new zzgx(context, zzif.zza(new zzhm(context)));
                zzi.incrementAndGet();
            }
        }
    }

    static void zze() {
        zzi.incrementAndGet();
    }

    /* access modifiers changed from: package-private */
    public abstract T zza(Object obj);

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ad, code lost:
        r2 = r2.zzb(zzc());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T zzb() {
        /*
            r7 = this;
            boolean r0 = r7.zzm
            if (r0 != 0) goto L_0x0011
            java.lang.String r0 = r7.zzb
            if (r0 == 0) goto L_0x0009
            goto L_0x0011
        L_0x0009:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "flagName must not be null"
            r0.<init>(r1)
            throw r0
        L_0x0011:
            java.util.concurrent.atomic.AtomicInteger r0 = zzi
            int r0 = r0.get()
            int r1 = r7.zzk
            if (r1 >= r0) goto L_0x012b
            monitor-enter(r7)
            int r1 = r7.zzk     // Catch:{ all -> 0x0128 }
            if (r1 >= r0) goto L_0x0126
            com.google.android.gms.internal.measurement.zzhs r1 = zze     // Catch:{ all -> 0x0128 }
            java.lang.String r2 = "Must call PhenotypeFlag.init() first"
            if (r1 == 0) goto L_0x0120
            com.google.android.gms.internal.measurement.zzhr r2 = r7.zza     // Catch:{ all -> 0x0128 }
            boolean r3 = r2.zzf     // Catch:{ all -> 0x0128 }
            boolean r2 = r2.zzg     // Catch:{ all -> 0x0128 }
            android.content.Context r2 = r1.zza()     // Catch:{ all -> 0x0128 }
            com.google.android.gms.internal.measurement.zzhh r2 = com.google.android.gms.internal.measurement.zzhh.zza(r2)     // Catch:{ all -> 0x0128 }
            java.lang.String r3 = "gms:phenotype:phenotype_flag:debug_bypass_phenotype"
            java.lang.String r2 = r2.zzb(r3)     // Catch:{ all -> 0x0128 }
            r3 = 0
            if (r2 == 0) goto L_0x0074
            java.util.regex.Pattern r4 = com.google.android.gms.internal.measurement.zzgv.zzc     // Catch:{ all -> 0x0128 }
            java.util.regex.Matcher r2 = r4.matcher(r2)     // Catch:{ all -> 0x0128 }
            boolean r2 = r2.matches()     // Catch:{ all -> 0x0128 }
            if (r2 == 0) goto L_0x0074
            java.lang.String r2 = "PhenotypeFlag"
            r4 = 3
            boolean r2 = android.util.Log.isLoggable(r2, r4)     // Catch:{ all -> 0x0128 }
            if (r2 == 0) goto L_0x0072
            java.lang.String r2 = "PhenotypeFlag"
            java.lang.String r4 = "Bypass reading Phenotype values for flag: "
            java.lang.String r5 = r7.zzc()     // Catch:{ all -> 0x0128 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0128 }
            int r6 = r5.length()     // Catch:{ all -> 0x0128 }
            if (r6 == 0) goto L_0x0069
            java.lang.String r4 = r4.concat(r5)     // Catch:{ all -> 0x0128 }
            goto L_0x006f
        L_0x0069:
            java.lang.String r5 = new java.lang.String     // Catch:{ all -> 0x0128 }
            r5.<init>(r4)     // Catch:{ all -> 0x0128 }
            r4 = r5
        L_0x006f:
            android.util.Log.d(r2, r4)     // Catch:{ all -> 0x0128 }
        L_0x0072:
            r2 = r3
            goto L_0x00bd
        L_0x0074:
            com.google.android.gms.internal.measurement.zzhr r2 = r7.zza     // Catch:{ all -> 0x0128 }
            android.net.Uri r2 = r2.zzb     // Catch:{ all -> 0x0128 }
            if (r2 == 0) goto L_0x009f
            android.content.Context r2 = r1.zza()     // Catch:{ all -> 0x0128 }
            com.google.android.gms.internal.measurement.zzhr r4 = r7.zza     // Catch:{ all -> 0x0128 }
            android.net.Uri r4 = r4.zzb     // Catch:{ all -> 0x0128 }
            boolean r2 = com.google.android.gms.internal.measurement.zzhj.zza(r2, r4)     // Catch:{ all -> 0x0128 }
            if (r2 == 0) goto L_0x009d
            com.google.android.gms.internal.measurement.zzhr r2 = r7.zza     // Catch:{ all -> 0x0128 }
            boolean r2 = r2.zzh     // Catch:{ all -> 0x0128 }
            android.content.Context r2 = r1.zza()     // Catch:{ all -> 0x0128 }
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ all -> 0x0128 }
            com.google.android.gms.internal.measurement.zzhr r4 = r7.zza     // Catch:{ all -> 0x0128 }
            android.net.Uri r4 = r4.zzb     // Catch:{ all -> 0x0128 }
            com.google.android.gms.internal.measurement.zzha r2 = com.google.android.gms.internal.measurement.zzha.zza(r2, r4)     // Catch:{ all -> 0x0128 }
            goto L_0x00ab
        L_0x009d:
            r2 = r3
            goto L_0x00ab
        L_0x009f:
            android.content.Context r2 = r1.zza()     // Catch:{ all -> 0x0128 }
            com.google.android.gms.internal.measurement.zzhr r4 = r7.zza     // Catch:{ all -> 0x0128 }
            java.lang.String r4 = r4.zza     // Catch:{ all -> 0x0128 }
            com.google.android.gms.internal.measurement.zzhv r2 = com.google.android.gms.internal.measurement.zzhv.zza(r2, r3)     // Catch:{ all -> 0x0128 }
        L_0x00ab:
            if (r2 == 0) goto L_0x00bc
            java.lang.String r4 = r7.zzc()     // Catch:{ all -> 0x0128 }
            java.lang.Object r2 = r2.zzb(r4)     // Catch:{ all -> 0x0128 }
            if (r2 == 0) goto L_0x00bc
            java.lang.Object r2 = r7.zza(r2)     // Catch:{ all -> 0x0128 }
            goto L_0x00bd
        L_0x00bc:
            r2 = r3
        L_0x00bd:
            if (r2 == 0) goto L_0x00c0
            goto L_0x00ee
        L_0x00c0:
            com.google.android.gms.internal.measurement.zzhr r2 = r7.zza     // Catch:{ all -> 0x0128 }
            boolean r4 = r2.zze     // Catch:{ all -> 0x0128 }
            if (r4 != 0) goto L_0x00e9
            com.google.android.gms.internal.measurement.zzhy<android.content.Context, java.lang.Boolean> r2 = r2.zzi     // Catch:{ all -> 0x0128 }
            android.content.Context r2 = r1.zza()     // Catch:{ all -> 0x0128 }
            com.google.android.gms.internal.measurement.zzhh r2 = com.google.android.gms.internal.measurement.zzhh.zza(r2)     // Catch:{ all -> 0x0128 }
            com.google.android.gms.internal.measurement.zzhr r4 = r7.zza     // Catch:{ all -> 0x0128 }
            boolean r5 = r4.zze     // Catch:{ all -> 0x0128 }
            if (r5 == 0) goto L_0x00d8
            r4 = r3
            goto L_0x00dc
        L_0x00d8:
            java.lang.String r4 = r4.zzc     // Catch:{ all -> 0x0128 }
            java.lang.String r4 = r7.zzb     // Catch:{ all -> 0x0128 }
        L_0x00dc:
            java.lang.String r2 = r2.zzb(r4)     // Catch:{ all -> 0x0128 }
            if (r2 == 0) goto L_0x00e7
            java.lang.Object r2 = r7.zza(r2)     // Catch:{ all -> 0x0128 }
            goto L_0x00ea
        L_0x00e7:
            r2 = r3
            goto L_0x00ea
        L_0x00e9:
            r2 = r3
        L_0x00ea:
            if (r2 != 0) goto L_0x00ee
            T r2 = r7.zzj     // Catch:{ all -> 0x0128 }
        L_0x00ee:
            com.google.android.gms.internal.measurement.zzib r1 = r1.zzb()     // Catch:{ all -> 0x0128 }
            java.lang.Object r1 = r1.zza()     // Catch:{ all -> 0x0128 }
            com.google.android.gms.internal.measurement.zzhz r1 = (com.google.android.gms.internal.measurement.zzhz) r1     // Catch:{ all -> 0x0128 }
            boolean r4 = r1.zzb()     // Catch:{ all -> 0x0128 }
            if (r4 == 0) goto L_0x011b
            java.lang.Object r1 = r1.zza()     // Catch:{ all -> 0x0128 }
            com.google.android.gms.internal.measurement.zzhi r1 = (com.google.android.gms.internal.measurement.zzhi) r1     // Catch:{ all -> 0x0128 }
            com.google.android.gms.internal.measurement.zzhr r2 = r7.zza     // Catch:{ all -> 0x0128 }
            android.net.Uri r4 = r2.zzb     // Catch:{ all -> 0x0128 }
            java.lang.String r5 = r2.zza     // Catch:{ all -> 0x0128 }
            java.lang.String r2 = r2.zzd     // Catch:{ all -> 0x0128 }
            java.lang.String r5 = r7.zzb     // Catch:{ all -> 0x0128 }
            java.lang.String r1 = r1.zza(r4, r3, r2, r5)     // Catch:{ all -> 0x0128 }
            if (r1 != 0) goto L_0x0117
            T r2 = r7.zzj     // Catch:{ all -> 0x0128 }
            goto L_0x011b
        L_0x0117:
            java.lang.Object r2 = r7.zza(r1)     // Catch:{ all -> 0x0128 }
        L_0x011b:
            r7.zzl = r2     // Catch:{ all -> 0x0128 }
            r7.zzk = r0     // Catch:{ all -> 0x0128 }
            goto L_0x0126
        L_0x0120:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0128 }
            r0.<init>(r2)     // Catch:{ all -> 0x0128 }
            throw r0     // Catch:{ all -> 0x0128 }
        L_0x0126:
            monitor-exit(r7)     // Catch:{ all -> 0x0128 }
            goto L_0x012b
        L_0x0128:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0128 }
            throw r0
        L_0x012b:
            T r0 = r7.zzl
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhu.zzb():java.lang.Object");
    }

    public final String zzc() {
        String str = this.zza.zzd;
        return this.zzb;
    }
}
