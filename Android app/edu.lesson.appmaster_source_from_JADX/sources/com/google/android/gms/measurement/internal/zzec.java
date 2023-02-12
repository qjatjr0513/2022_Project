package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zznx;
import com.google.android.gms.internal.measurement.zzom;
import com.google.android.gms.internal.measurement.zzpt;
import java.security.MessageDigest;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zzec extends zzf {
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private String zze;
    private long zzf;
    private final long zzg;
    private List<String> zzh;
    private int zzi;
    private String zzj;
    private String zzk;
    private String zzl;

    zzec(zzfv zzfv, long j) {
        super(zzfv);
        this.zzg = j;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0117  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0147  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0157  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0173  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0194 A[Catch:{ IllegalStateException -> 0x023a }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0196 A[Catch:{ IllegalStateException -> 0x023a }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x01ac A[SYNTHETIC, Splitter:B:56:0x01ac] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01ee A[Catch:{ IllegalStateException -> 0x023a }] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x021b A[Catch:{ IllegalStateException -> 0x023a }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0264  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x029d  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x02aa  */
    @org.checkerframework.checker.nullness.qual.EnsuresNonNull({"appId", "appStore", "appName", "gmpAppId", "gaAppId"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd() {
        /*
            r13 = this;
            com.google.android.gms.measurement.internal.zzfv r0 = r13.zzs
            android.content.Context r0 = r0.zzau()
            java.lang.String r0 = r0.getPackageName()
            com.google.android.gms.measurement.internal.zzfv r1 = r13.zzs
            android.content.Context r1 = r1.zzau()
            android.content.pm.PackageManager r1 = r1.getPackageManager()
            java.lang.String r2 = "Unknown"
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = 0
            java.lang.String r5 = ""
            java.lang.String r6 = "unknown"
            if (r1 != 0) goto L_0x0035
            com.google.android.gms.measurement.internal.zzfv r7 = r13.zzs
            com.google.android.gms.measurement.internal.zzel r7 = r7.zzay()
            com.google.android.gms.measurement.internal.zzej r7 = r7.zzd()
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzel.zzn(r0)
            java.lang.String r9 = "PackageManager is null, app identity information might be inaccurate. appId"
            r7.zzb(r9, r8)
            r8 = r2
            goto L_0x00a1
        L_0x0035:
            java.lang.String r6 = r1.getInstallerPackageName(r0)     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x004e
        L_0x003a:
            r7 = move-exception
            com.google.android.gms.measurement.internal.zzfv r7 = r13.zzs
            com.google.android.gms.measurement.internal.zzel r7 = r7.zzay()
            com.google.android.gms.measurement.internal.zzej r7 = r7.zzd()
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzel.zzn(r0)
            java.lang.String r9 = "Error retrieving app installer package name. appId"
            r7.zzb(r9, r8)
        L_0x004e:
            if (r6 != 0) goto L_0x0053
            java.lang.String r6 = "manual_install"
            goto L_0x005d
        L_0x0053:
            java.lang.String r7 = "com.android.vending"
            boolean r7 = r7.equals(r6)
            if (r7 == 0) goto L_0x005d
            r6 = r5
        L_0x005d:
            com.google.android.gms.measurement.internal.zzfv r7 = r13.zzs     // Catch:{ NameNotFoundException -> 0x008a }
            android.content.Context r7 = r7.zzau()     // Catch:{ NameNotFoundException -> 0x008a }
            java.lang.String r7 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x008a }
            android.content.pm.PackageInfo r7 = r1.getPackageInfo(r7, r4)     // Catch:{ NameNotFoundException -> 0x008a }
            if (r7 == 0) goto L_0x0088
            android.content.pm.ApplicationInfo r8 = r7.applicationInfo     // Catch:{ NameNotFoundException -> 0x008a }
            java.lang.CharSequence r8 = r1.getApplicationLabel(r8)     // Catch:{ NameNotFoundException -> 0x008a }
            boolean r9 = android.text.TextUtils.isEmpty(r8)     // Catch:{ NameNotFoundException -> 0x008a }
            if (r9 != 0) goto L_0x007e
            java.lang.String r8 = r8.toString()     // Catch:{ NameNotFoundException -> 0x008a }
            goto L_0x007f
        L_0x007e:
            r8 = r2
        L_0x007f:
            java.lang.String r2 = r7.versionName     // Catch:{ NameNotFoundException -> 0x0084 }
            int r3 = r7.versionCode     // Catch:{ NameNotFoundException -> 0x0084 }
            goto L_0x00a1
        L_0x0084:
            r7 = move-exception
            r7 = r2
            r2 = r8
            goto L_0x008c
        L_0x0088:
            r8 = r2
            goto L_0x00a1
        L_0x008a:
            r7 = move-exception
            r7 = r2
        L_0x008c:
            com.google.android.gms.measurement.internal.zzfv r8 = r13.zzs
            com.google.android.gms.measurement.internal.zzel r8 = r8.zzay()
            com.google.android.gms.measurement.internal.zzej r8 = r8.zzd()
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzel.zzn(r0)
            java.lang.String r10 = "Error retrieving package info. appId, appName"
            r8.zzc(r10, r9, r2)
            r8 = r2
            r2 = r7
        L_0x00a1:
            r13.zza = r0
            r13.zzd = r6
            r13.zzb = r2
            r13.zzc = r3
            r13.zze = r8
            r2 = 0
            r13.zzf = r2
            com.google.android.gms.measurement.internal.zzfv r2 = r13.zzs
            java.lang.String r2 = r2.zzw()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            r3 = 1
            if (r2 != 0) goto L_0x00cc
            com.google.android.gms.measurement.internal.zzfv r2 = r13.zzs
            java.lang.String r2 = r2.zzx()
            java.lang.String r6 = "am"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x00cc
            r2 = r3
            goto L_0x00cd
        L_0x00cc:
            r2 = r4
        L_0x00cd:
            com.google.android.gms.measurement.internal.zzfv r6 = r13.zzs
            int r6 = r6.zza()
            switch(r6) {
                case 0: goto L_0x0157;
                case 1: goto L_0x0147;
                case 2: goto L_0x0137;
                case 3: goto L_0x0127;
                case 4: goto L_0x0117;
                case 5: goto L_0x0107;
                case 6: goto L_0x00f7;
                case 7: goto L_0x00e7;
                default: goto L_0x00d6;
            }
        L_0x00d6:
            com.google.android.gms.measurement.internal.zzfv r7 = r13.zzs
            com.google.android.gms.measurement.internal.zzel r7 = r7.zzay()
            com.google.android.gms.measurement.internal.zzej r7 = r7.zzi()
            java.lang.String r8 = "App measurement disabled due to denied storage consent"
            r7.zza(r8)
            goto L_0x0166
        L_0x00e7:
            com.google.android.gms.measurement.internal.zzfv r7 = r13.zzs
            com.google.android.gms.measurement.internal.zzel r7 = r7.zzay()
            com.google.android.gms.measurement.internal.zzej r7 = r7.zzi()
            java.lang.String r8 = "App measurement disabled via the global data collection setting"
            r7.zza(r8)
            goto L_0x0166
        L_0x00f7:
            com.google.android.gms.measurement.internal.zzfv r7 = r13.zzs
            com.google.android.gms.measurement.internal.zzel r7 = r7.zzay()
            com.google.android.gms.measurement.internal.zzej r7 = r7.zzl()
            java.lang.String r8 = "App measurement deactivated via resources. This method is being deprecated. Please refer to https://firebase.google.com/support/guides/disable-analytics"
            r7.zza(r8)
            goto L_0x0166
        L_0x0107:
            com.google.android.gms.measurement.internal.zzfv r7 = r13.zzs
            com.google.android.gms.measurement.internal.zzel r7 = r7.zzay()
            com.google.android.gms.measurement.internal.zzej r7 = r7.zzj()
            java.lang.String r8 = "App measurement disabled via the init parameters"
            r7.zza(r8)
            goto L_0x0166
        L_0x0117:
            com.google.android.gms.measurement.internal.zzfv r7 = r13.zzs
            com.google.android.gms.measurement.internal.zzel r7 = r7.zzay()
            com.google.android.gms.measurement.internal.zzej r7 = r7.zzi()
            java.lang.String r8 = "App measurement disabled via the manifest"
            r7.zza(r8)
            goto L_0x0166
        L_0x0127:
            com.google.android.gms.measurement.internal.zzfv r7 = r13.zzs
            com.google.android.gms.measurement.internal.zzel r7 = r7.zzay()
            com.google.android.gms.measurement.internal.zzej r7 = r7.zzi()
            java.lang.String r8 = "App measurement disabled by setAnalyticsCollectionEnabled(false)"
            r7.zza(r8)
            goto L_0x0166
        L_0x0137:
            com.google.android.gms.measurement.internal.zzfv r7 = r13.zzs
            com.google.android.gms.measurement.internal.zzel r7 = r7.zzay()
            com.google.android.gms.measurement.internal.zzej r7 = r7.zzj()
            java.lang.String r8 = "App measurement deactivated via the init parameters"
            r7.zza(r8)
            goto L_0x0166
        L_0x0147:
            com.google.android.gms.measurement.internal.zzfv r7 = r13.zzs
            com.google.android.gms.measurement.internal.zzel r7 = r7.zzay()
            com.google.android.gms.measurement.internal.zzej r7 = r7.zzi()
            java.lang.String r8 = "App measurement deactivated via the manifest"
            r7.zza(r8)
            goto L_0x0166
        L_0x0157:
            com.google.android.gms.measurement.internal.zzfv r7 = r13.zzs
            com.google.android.gms.measurement.internal.zzel r7 = r7.zzay()
            com.google.android.gms.measurement.internal.zzej r7 = r7.zzj()
            java.lang.String r8 = "App measurement collection enabled"
            r7.zza(r8)
        L_0x0166:
            r13.zzj = r5
            r13.zzk = r5
            r13.zzl = r5
            com.google.android.gms.measurement.internal.zzfv r7 = r13.zzs
            r7.zzaw()
            if (r2 == 0) goto L_0x017b
            com.google.android.gms.measurement.internal.zzfv r2 = r13.zzs
            java.lang.String r2 = r2.zzw()
            r13.zzk = r2
        L_0x017b:
            r2 = 0
            com.google.android.gms.measurement.internal.zzfv r7 = r13.zzs     // Catch:{ IllegalStateException -> 0x023a }
            android.content.Context r7 = r7.zzau()     // Catch:{ IllegalStateException -> 0x023a }
            com.google.android.gms.measurement.internal.zzfv r8 = r13.zzs     // Catch:{ IllegalStateException -> 0x023a }
            java.lang.String r8 = r8.zzz()     // Catch:{ IllegalStateException -> 0x023a }
            java.lang.String r9 = "google_app_id"
            java.lang.String r7 = com.google.android.gms.measurement.internal.zzig.zzc(r7, r9, r8)     // Catch:{ IllegalStateException -> 0x023a }
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch:{ IllegalStateException -> 0x023a }
            if (r3 == r8) goto L_0x0196
            r8 = r7
            goto L_0x0197
        L_0x0196:
            r8 = r5
        L_0x0197:
            r13.zzj = r8     // Catch:{ IllegalStateException -> 0x023a }
            com.google.android.gms.internal.measurement.zzom.zzc()     // Catch:{ IllegalStateException -> 0x023a }
            com.google.android.gms.measurement.internal.zzfv r8 = r13.zzs     // Catch:{ IllegalStateException -> 0x023a }
            com.google.android.gms.measurement.internal.zzaf r8 = r8.zzf()     // Catch:{ IllegalStateException -> 0x023a }
            com.google.android.gms.measurement.internal.zzdx<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzdy.zzac     // Catch:{ IllegalStateException -> 0x023a }
            boolean r8 = r8.zzs(r2, r9)     // Catch:{ IllegalStateException -> 0x023a }
            java.lang.String r9 = "admob_app_id"
            if (r8 == 0) goto L_0x01ee
            com.google.android.gms.measurement.internal.zzfv r8 = r13.zzs     // Catch:{ IllegalStateException -> 0x023a }
            android.content.Context r8 = r8.zzau()     // Catch:{ IllegalStateException -> 0x023a }
            com.google.android.gms.measurement.internal.zzfv r10 = r13.zzs     // Catch:{ IllegalStateException -> 0x023a }
            java.lang.String r10 = r10.zzz()     // Catch:{ IllegalStateException -> 0x023a }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r8)     // Catch:{ IllegalStateException -> 0x023a }
            android.content.res.Resources r11 = r8.getResources()     // Catch:{ IllegalStateException -> 0x023a }
            boolean r12 = android.text.TextUtils.isEmpty(r10)     // Catch:{ IllegalStateException -> 0x023a }
            if (r12 != 0) goto L_0x01c6
            goto L_0x01ca
        L_0x01c6:
            java.lang.String r10 = com.google.android.gms.measurement.internal.zzfn.zza(r8)     // Catch:{ IllegalStateException -> 0x023a }
        L_0x01ca:
            java.lang.String r8 = "ga_app_id"
            java.lang.String r8 = com.google.android.gms.measurement.internal.zzfn.zzb(r8, r11, r10)     // Catch:{ IllegalStateException -> 0x023a }
            boolean r12 = android.text.TextUtils.isEmpty(r8)     // Catch:{ IllegalStateException -> 0x023a }
            if (r3 == r12) goto L_0x01d8
            r5 = r8
            goto L_0x01d9
        L_0x01d8:
        L_0x01d9:
            r13.zzl = r5     // Catch:{ IllegalStateException -> 0x023a }
            boolean r3 = android.text.TextUtils.isEmpty(r7)     // Catch:{ IllegalStateException -> 0x023a }
            if (r3 == 0) goto L_0x01e7
            boolean r3 = android.text.TextUtils.isEmpty(r8)     // Catch:{ IllegalStateException -> 0x023a }
            if (r3 != 0) goto L_0x0219
        L_0x01e7:
            java.lang.String r3 = com.google.android.gms.measurement.internal.zzfn.zzb(r9, r11, r10)     // Catch:{ IllegalStateException -> 0x023a }
            r13.zzk = r3     // Catch:{ IllegalStateException -> 0x023a }
            goto L_0x0219
        L_0x01ee:
            boolean r3 = android.text.TextUtils.isEmpty(r7)     // Catch:{ IllegalStateException -> 0x023a }
            if (r3 != 0) goto L_0x0219
            com.google.android.gms.measurement.internal.zzfv r3 = r13.zzs     // Catch:{ IllegalStateException -> 0x023a }
            android.content.Context r3 = r3.zzau()     // Catch:{ IllegalStateException -> 0x023a }
            com.google.android.gms.measurement.internal.zzfv r5 = r13.zzs     // Catch:{ IllegalStateException -> 0x023a }
            java.lang.String r5 = r5.zzz()     // Catch:{ IllegalStateException -> 0x023a }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)     // Catch:{ IllegalStateException -> 0x023a }
            android.content.res.Resources r7 = r3.getResources()     // Catch:{ IllegalStateException -> 0x023a }
            boolean r8 = android.text.TextUtils.isEmpty(r5)     // Catch:{ IllegalStateException -> 0x023a }
            if (r8 != 0) goto L_0x020e
            goto L_0x0212
        L_0x020e:
            java.lang.String r5 = com.google.android.gms.measurement.internal.zzfn.zza(r3)     // Catch:{ IllegalStateException -> 0x023a }
        L_0x0212:
            java.lang.String r3 = com.google.android.gms.measurement.internal.zzfn.zzb(r9, r7, r5)     // Catch:{ IllegalStateException -> 0x023a }
            r13.zzk = r3     // Catch:{ IllegalStateException -> 0x023a }
        L_0x0219:
            if (r6 != 0) goto L_0x024e
            com.google.android.gms.measurement.internal.zzfv r3 = r13.zzs     // Catch:{ IllegalStateException -> 0x023a }
            com.google.android.gms.measurement.internal.zzel r3 = r3.zzay()     // Catch:{ IllegalStateException -> 0x023a }
            com.google.android.gms.measurement.internal.zzej r3 = r3.zzj()     // Catch:{ IllegalStateException -> 0x023a }
            java.lang.String r5 = "App measurement enabled for app package, google app id"
            java.lang.String r6 = r13.zza     // Catch:{ IllegalStateException -> 0x023a }
            java.lang.String r7 = r13.zzj     // Catch:{ IllegalStateException -> 0x023a }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ IllegalStateException -> 0x023a }
            if (r7 == 0) goto L_0x0234
            java.lang.String r7 = r13.zzk     // Catch:{ IllegalStateException -> 0x023a }
            goto L_0x0236
        L_0x0234:
            java.lang.String r7 = r13.zzj     // Catch:{ IllegalStateException -> 0x023a }
        L_0x0236:
            r3.zzc(r5, r6, r7)     // Catch:{ IllegalStateException -> 0x023a }
            goto L_0x024e
        L_0x023a:
            r3 = move-exception
            com.google.android.gms.measurement.internal.zzfv r5 = r13.zzs
            com.google.android.gms.measurement.internal.zzel r5 = r5.zzay()
            com.google.android.gms.measurement.internal.zzej r5 = r5.zzd()
            java.lang.Object r0 = com.google.android.gms.measurement.internal.zzel.zzn(r0)
            java.lang.String r6 = "Fetching Google App Id failed with exception. appId"
            r5.zzc(r6, r0, r3)
        L_0x024e:
            r13.zzh = r2
            com.google.android.gms.measurement.internal.zzfv r0 = r13.zzs
            r0.zzaw()
            com.google.android.gms.measurement.internal.zzfv r0 = r13.zzs
            com.google.android.gms.measurement.internal.zzaf r0 = r0.zzf()
            java.lang.String r2 = "analytics.safelisted_events"
            java.util.List r0 = r0.zzp(r2)
            if (r0 != 0) goto L_0x0264
            goto L_0x0299
        L_0x0264:
            int r2 = r0.size()
            if (r2 != 0) goto L_0x027a
            com.google.android.gms.measurement.internal.zzfv r0 = r13.zzs
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzl()
            java.lang.String r2 = "Safelisted event list is empty. Ignoring"
            r0.zza(r2)
            goto L_0x029b
        L_0x027a:
            java.util.Iterator r2 = r0.iterator()
        L_0x027e:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0299
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            com.google.android.gms.measurement.internal.zzfv r5 = r13.zzs
            com.google.android.gms.measurement.internal.zzkz r5 = r5.zzv()
            java.lang.String r6 = "safelisted event"
            boolean r3 = r5.zzaa(r6, r3)
            if (r3 != 0) goto L_0x027e
            goto L_0x029b
        L_0x0299:
            r13.zzh = r0
        L_0x029b:
            if (r1 == 0) goto L_0x02aa
            com.google.android.gms.measurement.internal.zzfv r0 = r13.zzs
            android.content.Context r0 = r0.zzau()
            boolean r0 = com.google.android.gms.common.wrappers.InstantApps.isInstantApp(r0)
            r13.zzi = r0
            return
        L_0x02aa:
            r13.zzi = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzec.zzd():void");
    }

    /* access modifiers changed from: protected */
    public final boolean zzf() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public final int zzh() {
        zza();
        return this.zzi;
    }

    /* access modifiers changed from: package-private */
    public final int zzi() {
        zza();
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final zzp zzj(String str) {
        long j;
        String str2;
        long j2;
        String str3;
        Boolean bool;
        String str4;
        long j3;
        zzg();
        String zzl2 = zzl();
        String zzn = zzn();
        zza();
        String str5 = this.zzb;
        zza();
        long j4 = (long) this.zzc;
        zza();
        Preconditions.checkNotNull(this.zzd);
        String str6 = this.zzd;
        this.zzs.zzf().zzh();
        zza();
        zzg();
        long j5 = this.zzf;
        if (j5 == 0) {
            zzkz zzv = this.zzs.zzv();
            Context zzau = this.zzs.zzau();
            String packageName = this.zzs.zzau().getPackageName();
            zzv.zzg();
            Preconditions.checkNotNull(zzau);
            Preconditions.checkNotEmpty(packageName);
            PackageManager packageManager = zzau.getPackageManager();
            MessageDigest zzE = zzkz.zzE();
            long j6 = -1;
            if (zzE == null) {
                zzv.zzs.zzay().zzd().zza("Could not get MD5 instance");
                j3 = -1;
            } else if (packageManager != null) {
                try {
                    if (!zzv.zzaf(zzau, packageName)) {
                        PackageInfo packageInfo = Wrappers.packageManager(zzau).getPackageInfo(zzv.zzs.zzau().getPackageName(), 64);
                        if (packageInfo.signatures == null || packageInfo.signatures.length <= 0) {
                            zzv.zzs.zzay().zzk().zza("Could not get signatures");
                        } else {
                            j6 = zzkz.zzp(zzE.digest(packageInfo.signatures[0].toByteArray()));
                        }
                    } else {
                        j6 = 0;
                    }
                    j3 = j6;
                } catch (PackageManager.NameNotFoundException e) {
                    zzv.zzs.zzay().zzd().zzb("Package name not found", e);
                    j3 = 0;
                }
            } else {
                j3 = 0;
            }
            this.zzf = j3;
            j = j3;
        } else {
            j = j5;
        }
        boolean zzJ = this.zzs.zzJ();
        boolean z = !this.zzs.zzm().zzk;
        zzg();
        if (!this.zzs.zzJ()) {
            str2 = null;
        } else {
            zzpt.zzc();
            if (this.zzs.zzf().zzs((String) null, zzdy.zzae)) {
                this.zzs.zzay().zzj().zza("Disabled IID for tests.");
                str2 = null;
            } else {
                try {
                    Class<?> loadClass = this.zzs.zzau().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
                    if (loadClass == null) {
                        str2 = null;
                    } else {
                        try {
                            Object invoke = loadClass.getDeclaredMethod("getInstance", new Class[]{Context.class}).invoke((Object) null, new Object[]{this.zzs.zzau()});
                            if (invoke == null) {
                                str2 = null;
                            } else {
                                try {
                                    str2 = (String) loadClass.getDeclaredMethod("getFirebaseInstanceId", new Class[0]).invoke(invoke, new Object[0]);
                                } catch (Exception e2) {
                                    this.zzs.zzay().zzl().zza("Failed to retrieve Firebase Instance Id");
                                    str2 = null;
                                }
                            }
                        } catch (Exception e3) {
                            this.zzs.zzay().zzm().zza("Failed to obtain Firebase Analytics instance");
                            str2 = null;
                        }
                    }
                } catch (ClassNotFoundException e4) {
                    str2 = null;
                }
            }
        }
        zzfv zzfv = this.zzs;
        long zza2 = zzfv.zzm().zzc.zza();
        if (zza2 == 0) {
            str3 = zzl2;
            j2 = zzfv.zzc;
        } else {
            str3 = zzl2;
            j2 = Math.min(zzfv.zzc, zza2);
        }
        zza();
        int i = this.zzi;
        boolean zzr = this.zzs.zzf().zzr();
        zzfa zzm = this.zzs.zzm();
        zzm.zzg();
        boolean z2 = zzm.zza().getBoolean("deferred_analytics_collection", false);
        zza();
        String str7 = this.zzk;
        Boolean zzk2 = this.zzs.zzf().zzk("google_analytics_default_allow_ad_personalization_signals");
        if (zzk2 == null) {
            bool = null;
        } else {
            bool = Boolean.valueOf(!zzk2.booleanValue());
        }
        long j7 = this.zzg;
        List<String> list = this.zzh;
        zzom.zzc();
        long j8 = j7;
        if (this.zzs.zzf().zzs((String) null, zzdy.zzac)) {
            str4 = zzm();
        } else {
            str4 = null;
        }
        return new zzp(str3, zzn, str5, j4, str6, 46000, j, str, zzJ, z, str2, 0, j2, i, zzr, z2, str7, bool, j8, list, str4, this.zzs.zzm().zzc().zzi());
    }

    /* access modifiers changed from: package-private */
    public final String zzk() {
        zza();
        return this.zzk;
    }

    /* access modifiers changed from: package-private */
    public final String zzl() {
        zza();
        Preconditions.checkNotNull(this.zza);
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final String zzm() {
        zza();
        Preconditions.checkNotNull(this.zzl);
        return this.zzl;
    }

    /* access modifiers changed from: package-private */
    public final String zzn() {
        zznx.zzc();
        if (this.zzs.zzf().zzs((String) null, zzdy.zzas)) {
            zzg();
        }
        zza();
        Preconditions.checkNotNull(this.zzj);
        return this.zzj;
    }

    /* access modifiers changed from: package-private */
    public final List<String> zzo() {
        return this.zzh;
    }
}
