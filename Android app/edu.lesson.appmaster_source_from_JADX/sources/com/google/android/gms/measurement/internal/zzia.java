package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zznx;
import com.google.android.gms.internal.measurement.zzod;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zzia extends zzf {
    protected zzhz zza;
    final zzr zzb;
    protected boolean zzc = true;
    private zzgv zzd;
    private final Set<zzgw> zze = new CopyOnWriteArraySet();
    private boolean zzf;
    private final AtomicReference<String> zzg = new AtomicReference<>();
    private final Object zzh = new Object();
    private zzag zzi = new zzag((Boolean) null, (Boolean) null);
    private int zzj = 100;
    private final AtomicLong zzk = new AtomicLong(0);
    private long zzl = -1;
    private int zzm = 100;
    private final zzky zzn = new zzho(this);

    protected zzia(zzfv zzfv) {
        super(zzfv);
        this.zzb = new zzr(zzfv);
    }

    /* access modifiers changed from: private */
    /* renamed from: zzab */
    public final void zzB(Bundle bundle, long j) {
        zznx.zzc();
        if (!this.zzs.zzf().zzs((String) null, zzdy.zzar) || TextUtils.isEmpty(this.zzs.zzh().zzn())) {
            zzR(bundle, 0, j);
        } else {
            this.zzs.zzay().zzl().zza("Using developer consent only; google app id found");
        }
    }

    /* access modifiers changed from: private */
    public final void zzac(Boolean bool, boolean z) {
        zzg();
        zza();
        this.zzs.zzay().zzc().zzb("Setting app measurement enabled (FE)", bool);
        this.zzs.zzm().zzh(bool);
        if (z) {
            zzfa zzm2 = this.zzs.zzm();
            zzfv zzfv = zzm2.zzs;
            zzm2.zzg();
            SharedPreferences.Editor edit = zzm2.zza().edit();
            if (bool != null) {
                edit.putBoolean("measurement_enabled_from_api", bool.booleanValue());
            } else {
                edit.remove("measurement_enabled_from_api");
            }
            edit.apply();
        }
        if (this.zzs.zzK() || (bool != null && !bool.booleanValue())) {
            zzad();
        }
    }

    /* access modifiers changed from: private */
    public final void zzad() {
        long j;
        zzg();
        String zza2 = this.zzs.zzm().zzh.zza();
        if (zza2 != null) {
            if ("unset".equals(zza2)) {
                zzZ("app", "_npa", (Object) null, this.zzs.zzav().currentTimeMillis());
            } else {
                if (true != "true".equals(zza2)) {
                    j = 0;
                } else {
                    j = 1;
                }
                zzZ("app", "_npa", Long.valueOf(j), this.zzs.zzav().currentTimeMillis());
            }
        }
        if (!this.zzs.zzJ() || !this.zzc) {
            this.zzs.zzay().zzc().zza("Updating Scion state (FE)");
            this.zzs.zzt().zzI();
            return;
        }
        this.zzs.zzay().zzc().zza("Recording app launch after enabling measurement for the first time (FE)");
        zzy();
        zzod.zzc();
        if (this.zzs.zzf().zzs((String) null, zzdy.zzai)) {
            this.zzs.zzu().zza.zza();
        }
        this.zzs.zzaz().zzp(new zzhd(this));
    }

    static /* synthetic */ void zzv(zzia zzia, zzag zzag, int i, long j, boolean z, boolean z2) {
        zzia.zzg();
        zzia.zza();
        if (j > zzia.zzl || !zzag.zzl(zzia.zzm, i)) {
            zzfa zzm2 = zzia.zzs.zzm();
            zzfv zzfv = zzm2.zzs;
            zzm2.zzg();
            if (zzm2.zzl(i)) {
                SharedPreferences.Editor edit = zzm2.zza().edit();
                edit.putString("consent_settings", zzag.zzi());
                edit.putInt("consent_source", i);
                edit.apply();
                zzia.zzl = j;
                zzia.zzm = i;
                zzia.zzs.zzt().zzF(z);
                if (z2) {
                    zzia.zzs.zzt().zzu(new AtomicReference());
                    return;
                }
                return;
            }
            zzia.zzs.zzay().zzi().zzb("Lower precedence consent source ignored, proposed source", Integer.valueOf(i));
            return;
        }
        zzia.zzs.zzay().zzi().zzb("Dropped out-of-date consent setting, proposed settings", zzag);
    }

    public final void zzA() {
        if ((this.zzs.zzau().getApplicationContext() instanceof Application) && this.zza != null) {
            ((Application) this.zzs.zzau().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.zza);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzC(Bundle bundle) {
        if (bundle == null) {
            this.zzs.zzm().zzr.zzb(new Bundle());
            return;
        }
        Bundle zza2 = this.zzs.zzm().zzr.zza();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj != null && !(obj instanceof String) && !(obj instanceof Long) && !(obj instanceof Double)) {
                if (this.zzs.zzv().zzae(obj)) {
                    this.zzs.zzv().zzM(this.zzn, (String) null, 27, (String) null, (String) null, 0);
                }
                this.zzs.zzay().zzl().zzc("Invalid default event parameter type. Name, value", str, obj);
            } else if (zzkz.zzag(str)) {
                this.zzs.zzay().zzl().zzb("Invalid default event parameter name. Name", str);
            } else if (obj == null) {
                zza2.remove(str);
            } else {
                zzkz zzv = this.zzs.zzv();
                this.zzs.zzf();
                if (zzv.zzZ("param", str, 100, obj)) {
                    this.zzs.zzv().zzN(zza2, str, obj);
                }
            }
        }
        this.zzs.zzv();
        int zzc2 = this.zzs.zzf().zzc();
        if (zza2.size() > zzc2) {
            int i = 0;
            for (String str2 : new TreeSet(zza2.keySet())) {
                i++;
                if (i > zzc2) {
                    zza2.remove(str2);
                }
            }
            this.zzs.zzv().zzM(this.zzn, (String) null, 26, (String) null, (String) null, 0);
            this.zzs.zzay().zzl().zza("Too many default event parameters set. Discarding beyond event parameter limit");
        }
        this.zzs.zzm().zzr.zzb(zza2);
        this.zzs.zzt().zzH(zza2);
    }

    public final void zzD(String str, String str2, Bundle bundle) {
        zzE(str, str2, bundle, true, true, this.zzs.zzav().currentTimeMillis());
    }

    public final void zzE(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) {
        boolean z3;
        String str3 = str == null ? "app" : str;
        Bundle bundle2 = bundle == null ? new Bundle() : bundle;
        String str4 = str2;
        if (zzkz.zzak(str2, FirebaseAnalytics.Event.SCREEN_VIEW)) {
            this.zzs.zzs().zzx(bundle2, j);
            return;
        }
        long j2 = j;
        boolean z4 = true;
        if (z2 && this.zzd != null) {
            if (zzkz.zzag(str2)) {
                z3 = true;
                zzM(str3, str2, j, bundle2, z2, z3, z, (String) null);
            }
            z4 = false;
        }
        z3 = z4;
        zzM(str3, str2, j, bundle2, z2, z3, z, (String) null);
    }

    public final void zzF(String str, String str2, Bundle bundle, String str3) {
        zzfv.zzO();
        zzM("auto", str2, this.zzs.zzav().currentTimeMillis(), bundle, false, true, true, str3);
    }

    /* access modifiers changed from: package-private */
    public final void zzG(String str, String str2, Bundle bundle) {
        zzg();
        zzH(str, str2, this.zzs.zzav().currentTimeMillis(), bundle);
    }

    /* access modifiers changed from: package-private */
    public final void zzH(String str, String str2, long j, Bundle bundle) {
        boolean z;
        zzg();
        if (this.zzd != null) {
            z = zzkz.zzag(str2);
        } else {
            z = true;
        }
        zzI(str, str2, j, bundle, true, z, true, (String) null);
    }

    /* JADX WARNING: type inference failed for: r5v9, types: [java.lang.Object[]] */
    /* JADX WARNING: type inference failed for: r5v11, types: [java.lang.Object[]] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzI(java.lang.String r20, java.lang.String r21, long r22, android.os.Bundle r24, boolean r25, boolean r26, boolean r27, java.lang.String r28) {
        /*
            r19 = this;
            r7 = r19
            r8 = r20
            r9 = r21
            r10 = r22
            r12 = r24
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r20)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r24)
            r19.zzg()
            r19.zza()
            com.google.android.gms.measurement.internal.zzfv r0 = r7.zzs
            boolean r0 = r0.zzJ()
            if (r0 == 0) goto L_0x0526
            com.google.android.gms.measurement.internal.zzfv r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzec r0 = r0.zzh()
            java.util.List r0 = r0.zzo()
            if (r0 == 0) goto L_0x0041
            boolean r0 = r0.contains(r9)
            if (r0 == 0) goto L_0x0031
            goto L_0x0041
        L_0x0031:
            com.google.android.gms.measurement.internal.zzfv r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzc()
            java.lang.String r1 = "Dropping non-safelisted event. event name, origin"
            r0.zzc(r1, r9, r8)
            return
        L_0x0041:
            boolean r0 = r7.zzf
            r13 = 0
            r14 = 0
            r15 = 1
            if (r0 != 0) goto L_0x00a3
            r7.zzf = r15
            com.google.android.gms.measurement.internal.zzfv r0 = r7.zzs     // Catch:{ ClassNotFoundException -> 0x0093 }
            boolean r0 = r0.zzN()     // Catch:{ ClassNotFoundException -> 0x0093 }
            java.lang.String r1 = "com.google.android.gms.tagmanager.TagManagerService"
            if (r0 != 0) goto L_0x0063
            com.google.android.gms.measurement.internal.zzfv r0 = r7.zzs     // Catch:{ ClassNotFoundException -> 0x0093 }
            android.content.Context r0 = r0.zzau()     // Catch:{ ClassNotFoundException -> 0x0093 }
            java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x0093 }
            java.lang.Class r0 = java.lang.Class.forName(r1, r15, r0)     // Catch:{ ClassNotFoundException -> 0x0093 }
            goto L_0x0068
        L_0x0063:
            java.lang.Class r0 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x0093 }
        L_0x0068:
            java.lang.Class[] r1 = new java.lang.Class[r15]     // Catch:{ Exception -> 0x0082 }
            java.lang.Class<android.content.Context> r2 = android.content.Context.class
            r1[r14] = r2     // Catch:{ Exception -> 0x0082 }
            java.lang.String r2 = "initialize"
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r2, r1)     // Catch:{ Exception -> 0x0082 }
            java.lang.Object[] r1 = new java.lang.Object[r15]     // Catch:{ Exception -> 0x0082 }
            com.google.android.gms.measurement.internal.zzfv r2 = r7.zzs     // Catch:{ Exception -> 0x0082 }
            android.content.Context r2 = r2.zzau()     // Catch:{ Exception -> 0x0082 }
            r1[r14] = r2     // Catch:{ Exception -> 0x0082 }
            r0.invoke(r13, r1)     // Catch:{ Exception -> 0x0082 }
            goto L_0x00a3
        L_0x0082:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs     // Catch:{ ClassNotFoundException -> 0x0093 }
            com.google.android.gms.measurement.internal.zzel r1 = r1.zzay()     // Catch:{ ClassNotFoundException -> 0x0093 }
            com.google.android.gms.measurement.internal.zzej r1 = r1.zzk()     // Catch:{ ClassNotFoundException -> 0x0093 }
            java.lang.String r2 = "Failed to invoke Tag Manager's initialize() method"
            r1.zzb(r2, r0)     // Catch:{ ClassNotFoundException -> 0x0093 }
            goto L_0x00a3
        L_0x0093:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfv r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzi()
            java.lang.String r1 = "Tag Manager is not found and thus will not be used"
            r0.zza(r1)
        L_0x00a3:
            java.lang.String r0 = "_cmp"
            boolean r0 = r0.equals(r9)
            if (r0 == 0) goto L_0x00d0
            java.lang.String r0 = "gclid"
            boolean r1 = r12.containsKey(r0)
            if (r1 == 0) goto L_0x00d0
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            r1.zzaw()
            java.lang.String r4 = r12.getString(r0)
            com.google.android.gms.measurement.internal.zzfv r0 = r7.zzs
            com.google.android.gms.common.util.Clock r0 = r0.zzav()
            long r5 = r0.currentTimeMillis()
            java.lang.String r2 = "auto"
            java.lang.String r3 = "_lgclid"
            r1 = r19
            r1.zzZ(r2, r3, r4, r5)
        L_0x00d0:
            com.google.android.gms.measurement.internal.zzfv r0 = r7.zzs
            r0.zzaw()
            if (r25 == 0) goto L_0x00f2
            boolean r0 = com.google.android.gms.measurement.internal.zzkz.zzal(r21)
            if (r0 == 0) goto L_0x00f2
            com.google.android.gms.measurement.internal.zzfv r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzkz r0 = r0.zzv()
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzfa r1 = r1.zzm()
            com.google.android.gms.measurement.internal.zzev r1 = r1.zzr
            android.os.Bundle r1 = r1.zza()
            r0.zzK(r12, r1)
        L_0x00f2:
            r0 = 40
            if (r27 != 0) goto L_0x017d
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            r1.zzaw()
            java.lang.String r1 = "_iap"
            boolean r1 = r1.equals(r9)
            if (r1 != 0) goto L_0x017d
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzkz r1 = r1.zzv()
            java.lang.String r2 = "event"
            boolean r3 = r1.zzab(r2, r9)
            r4 = 2
            if (r3 != 0) goto L_0x0113
        L_0x0112:
            goto L_0x012e
        L_0x0113:
            java.lang.String[] r3 = com.google.android.gms.measurement.internal.zzgs.zza
            java.lang.String[] r5 = com.google.android.gms.measurement.internal.zzgs.zzb
            boolean r3 = r1.zzY(r2, r3, r5, r9)
            if (r3 != 0) goto L_0x0121
            r4 = 13
            goto L_0x012e
        L_0x0121:
            com.google.android.gms.measurement.internal.zzfv r3 = r1.zzs
            r3.zzf()
            boolean r1 = r1.zzX(r2, r0, r9)
            if (r1 != 0) goto L_0x012d
            goto L_0x0112
        L_0x012d:
            r4 = r14
        L_0x012e:
            if (r4 == 0) goto L_0x017d
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzel r1 = r1.zzay()
            com.google.android.gms.measurement.internal.zzej r1 = r1.zze()
            com.google.android.gms.measurement.internal.zzfv r2 = r7.zzs
            com.google.android.gms.measurement.internal.zzeg r2 = r2.zzj()
            java.lang.String r2 = r2.zzd(r9)
            java.lang.String r3 = "Invalid public event name. Event will not be logged (FE)"
            r1.zzb(r3, r2)
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzkz r1 = r1.zzv()
            com.google.android.gms.measurement.internal.zzfv r2 = r7.zzs
            r2.zzf()
            java.lang.String r0 = r1.zzC(r9, r0, r15)
            if (r9 == 0) goto L_0x015f
            int r14 = r21.length()
            goto L_0x0160
        L_0x015f:
        L_0x0160:
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzkz r1 = r1.zzv()
            com.google.android.gms.measurement.internal.zzky r2 = r7.zzn
            r3 = 0
            java.lang.String r5 = "_ev"
            r20 = r1
            r21 = r2
            r22 = r3
            r23 = r4
            r24 = r5
            r25 = r0
            r26 = r14
            r20.zzM(r21, r22, r23, r24, r25, r26)
            return
        L_0x017d:
            com.google.android.gms.internal.measurement.zzpn.zzc()
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzaf r1 = r1.zzf()
            com.google.android.gms.measurement.internal.zzdx<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzdy.zzaA
            boolean r1 = r1.zzs(r13, r2)
            java.lang.String r2 = "_sc"
            if (r1 == 0) goto L_0x01b4
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            r1.zzaw()
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzio r1 = r1.zzs()
            com.google.android.gms.measurement.internal.zzih r1 = r1.zzj(r14)
            if (r1 == 0) goto L_0x01a9
            boolean r3 = r12.containsKey(r2)
            if (r3 != 0) goto L_0x01a9
            r1.zzd = r15
        L_0x01a9:
            if (r25 == 0) goto L_0x01af
            if (r27 != 0) goto L_0x01af
            r3 = r15
            goto L_0x01b0
        L_0x01af:
            r3 = r14
        L_0x01b0:
            com.google.android.gms.measurement.internal.zzkz.zzJ(r1, r12, r3)
            goto L_0x01d7
        L_0x01b4:
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            r1.zzaw()
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzio r1 = r1.zzs()
            com.google.android.gms.measurement.internal.zzih r1 = r1.zzj(r14)
            if (r1 == 0) goto L_0x01cd
            boolean r3 = r12.containsKey(r2)
            if (r3 != 0) goto L_0x01cd
            r1.zzd = r15
        L_0x01cd:
            if (r25 == 0) goto L_0x01d3
            if (r27 != 0) goto L_0x01d3
            r3 = r15
            goto L_0x01d4
        L_0x01d3:
            r3 = r14
        L_0x01d4:
            com.google.android.gms.measurement.internal.zzkz.zzJ(r1, r12, r3)
        L_0x01d7:
            java.lang.String r1 = "am"
            boolean r1 = r1.equals(r8)
            boolean r3 = com.google.android.gms.measurement.internal.zzkz.zzag(r21)
            if (r25 == 0) goto L_0x0225
            com.google.android.gms.measurement.internal.zzgv r4 = r7.zzd
            if (r4 == 0) goto L_0x0225
            if (r3 != 0) goto L_0x0225
            if (r1 == 0) goto L_0x01ef
            r16 = r15
            goto L_0x0228
        L_0x01ef:
            com.google.android.gms.measurement.internal.zzfv r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzc()
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzeg r1 = r1.zzj()
            java.lang.String r1 = r1.zzd(r9)
            com.google.android.gms.measurement.internal.zzfv r2 = r7.zzs
            com.google.android.gms.measurement.internal.zzeg r2 = r2.zzj()
            java.lang.String r2 = r2.zzb(r12)
            java.lang.String r3 = "Passing event to registered event handler (FE)"
            r0.zzc(r3, r1, r2)
            com.google.android.gms.measurement.internal.zzgv r0 = r7.zzd
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)
            com.google.android.gms.measurement.internal.zzgv r1 = r7.zzd
            r2 = r20
            r3 = r21
            r4 = r24
            r5 = r22
            r1.interceptEvent(r2, r3, r4, r5)
            return
        L_0x0225:
            r16 = r1
        L_0x0228:
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            boolean r1 = r1.zzM()
            if (r1 == 0) goto L_0x0525
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzkz r1 = r1.zzv()
            int r1 = r1.zzh(r9)
            if (r1 == 0) goto L_0x0288
            com.google.android.gms.measurement.internal.zzfv r2 = r7.zzs
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzej r2 = r2.zze()
            com.google.android.gms.measurement.internal.zzfv r3 = r7.zzs
            com.google.android.gms.measurement.internal.zzeg r3 = r3.zzj()
            java.lang.String r3 = r3.zzd(r9)
            java.lang.String r4 = "Invalid event name. Event will not be logged (FE)"
            r2.zzb(r4, r3)
            com.google.android.gms.measurement.internal.zzfv r2 = r7.zzs
            com.google.android.gms.measurement.internal.zzkz r2 = r2.zzv()
            com.google.android.gms.measurement.internal.zzfv r3 = r7.zzs
            r3.zzf()
            java.lang.String r0 = r2.zzC(r9, r0, r15)
            if (r9 == 0) goto L_0x026b
            int r14 = r21.length()
            goto L_0x026c
        L_0x026b:
        L_0x026c:
            com.google.android.gms.measurement.internal.zzfv r2 = r7.zzs
            com.google.android.gms.measurement.internal.zzkz r2 = r2.zzv()
            com.google.android.gms.measurement.internal.zzky r3 = r7.zzn
            java.lang.String r4 = "_ev"
            r20 = r2
            r21 = r3
            r22 = r28
            r23 = r1
            r24 = r4
            r25 = r0
            r26 = r14
            r20.zzM(r21, r22, r23, r24, r25, r26)
            return
        L_0x0288:
            java.lang.String r0 = "_o"
            java.lang.String r1 = "_sn"
            java.lang.String r3 = "_si"
            java.lang.String[] r1 = new java.lang.String[]{r0, r1, r2, r3}
            java.util.List r5 = com.google.android.gms.common.util.CollectionUtils.listOf((T[]) r1)
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzkz r1 = r1.zzv()
            r2 = r28
            r3 = r21
            r4 = r24
            r6 = r27
            android.os.Bundle r12 = r1.zzy(r2, r3, r4, r5, r6)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r12)
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            r1.zzaw()
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzio r1 = r1.zzs()
            com.google.android.gms.measurement.internal.zzih r1 = r1.zzj(r14)
            r5 = 0
            java.lang.String r4 = "_ae"
            if (r1 == 0) goto L_0x02ed
            boolean r1 = r4.equals(r9)
            if (r1 == 0) goto L_0x02ed
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzkd r1 = r1.zzu()
            com.google.android.gms.measurement.internal.zzkb r1 = r1.zzb
            com.google.android.gms.measurement.internal.zzkd r2 = r1.zzc
            com.google.android.gms.measurement.internal.zzfv r2 = r2.zzs
            com.google.android.gms.common.util.Clock r2 = r2.zzav()
            long r2 = r2.elapsedRealtime()
            long r14 = r1.zzb
            long r14 = r2 - r14
            r1.zzb = r2
            int r1 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1))
            if (r1 <= 0) goto L_0x02ed
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzkz r1 = r1.zzv()
            r1.zzH(r12, r14)
        L_0x02ed:
            com.google.android.gms.internal.measurement.zznu.zzc()
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzaf r1 = r1.zzf()
            com.google.android.gms.measurement.internal.zzdx<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzdy.zzah
            boolean r1 = r1.zzs(r13, r2)
            if (r1 == 0) goto L_0x0378
            java.lang.String r1 = "auto"
            boolean r1 = r1.equals(r8)
            java.lang.String r2 = "_ffr"
            if (r1 != 0) goto L_0x0356
            java.lang.String r1 = "_ssr"
            boolean r1 = r1.equals(r9)
            if (r1 == 0) goto L_0x0356
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzkz r1 = r1.zzv()
            java.lang.String r2 = r12.getString(r2)
            boolean r3 = com.google.android.gms.common.util.Strings.isEmptyOrWhitespace(r2)
            if (r3 == 0) goto L_0x0322
            r2 = r13
            goto L_0x0328
        L_0x0322:
            if (r2 == 0) goto L_0x0328
            java.lang.String r2 = r2.trim()
        L_0x0328:
            com.google.android.gms.measurement.internal.zzfv r3 = r1.zzs
            com.google.android.gms.measurement.internal.zzfa r3 = r3.zzm()
            com.google.android.gms.measurement.internal.zzez r3 = r3.zzo
            java.lang.String r3 = r3.zza()
            boolean r3 = com.google.android.gms.measurement.internal.zzkz.zzak(r2, r3)
            if (r3 != 0) goto L_0x0346
            com.google.android.gms.measurement.internal.zzfv r1 = r1.zzs
            com.google.android.gms.measurement.internal.zzfa r1 = r1.zzm()
            com.google.android.gms.measurement.internal.zzez r1 = r1.zzo
            r1.zzb(r2)
            goto L_0x0378
        L_0x0346:
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzs
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzc()
            java.lang.String r1 = "Not logging duplicate session_start_with_rollout event"
            r0.zza(r1)
            return
        L_0x0356:
            boolean r1 = r4.equals(r9)
            if (r1 == 0) goto L_0x0378
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzkz r1 = r1.zzv()
            com.google.android.gms.measurement.internal.zzfv r1 = r1.zzs
            com.google.android.gms.measurement.internal.zzfa r1 = r1.zzm()
            com.google.android.gms.measurement.internal.zzez r1 = r1.zzo
            java.lang.String r1 = r1.zza()
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 != 0) goto L_0x0378
            r12.putString(r2, r1)
        L_0x0378:
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            r14.add(r12)
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzfa r1 = r1.zzm()
            com.google.android.gms.measurement.internal.zzew r1 = r1.zzj
            long r1 = r1.zza()
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 <= 0) goto L_0x0400
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzfa r1 = r1.zzm()
            boolean r1 = r1.zzk(r10)
            if (r1 == 0) goto L_0x03fd
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzfa r1 = r1.zzm()
            com.google.android.gms.measurement.internal.zzeu r1 = r1.zzl
            boolean r1 = r1.zzb()
            if (r1 == 0) goto L_0x03fa
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzel r1 = r1.zzay()
            com.google.android.gms.measurement.internal.zzej r1 = r1.zzj()
            java.lang.String r2 = "Current session is expired, remove the session number, ID, and engagement time"
            r1.zza(r2)
            r15 = 0
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.common.util.Clock r1 = r1.zzav()
            long r17 = r1.currentTimeMillis()
            java.lang.String r2 = "auto"
            java.lang.String r3 = "_sid"
            r1 = r19
            r13 = r4
            r4 = r15
            r8 = r5
            r5 = r17
            r1.zzZ(r2, r3, r4, r5)
            r4 = 0
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.common.util.Clock r1 = r1.zzav()
            long r5 = r1.currentTimeMillis()
            java.lang.String r2 = "auto"
            java.lang.String r3 = "_sno"
            r1 = r19
            r1.zzZ(r2, r3, r4, r5)
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.common.util.Clock r1 = r1.zzav()
            long r5 = r1.currentTimeMillis()
            java.lang.String r2 = "auto"
            java.lang.String r3 = "_se"
            r1 = r19
            r1.zzZ(r2, r3, r4, r5)
            goto L_0x0402
        L_0x03fa:
            r13 = r4
            r8 = r5
            goto L_0x0402
        L_0x03fd:
            r13 = r4
            r8 = r5
            goto L_0x0402
        L_0x0400:
            r13 = r4
            r8 = r5
        L_0x0402:
            java.lang.String r1 = "extend_session"
            long r1 = r12.getLong(r1, r8)
            r3 = 1
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x042a
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzel r1 = r1.zzay()
            com.google.android.gms.measurement.internal.zzej r1 = r1.zzj()
            java.lang.String r2 = "EXTEND_SESSION param attached: initiate a new session or extend the current active session"
            r1.zza(r2)
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzkd r1 = r1.zzu()
            com.google.android.gms.measurement.internal.zzkc r1 = r1.zza
            r2 = 1
            r1.zzb(r10, r2)
        L_0x042a:
            java.util.ArrayList r1 = new java.util.ArrayList
            java.util.Set r2 = r12.keySet()
            r1.<init>(r2)
            java.util.Collections.sort(r1)
            int r2 = r1.size()
            r3 = 0
        L_0x043b:
            if (r3 >= r2) goto L_0x0489
            java.lang.Object r4 = r1.get(r3)
            java.lang.String r4 = (java.lang.String) r4
            if (r4 == 0) goto L_0x0486
            com.google.android.gms.measurement.internal.zzfv r5 = r7.zzs
            r5.zzv()
            java.lang.Object r5 = r12.get(r4)
            boolean r6 = r5 instanceof android.os.Bundle
            if (r6 == 0) goto L_0x045b
            r6 = 1
            android.os.Bundle[] r8 = new android.os.Bundle[r6]
            android.os.Bundle r5 = (android.os.Bundle) r5
            r6 = 0
            r8[r6] = r5
            goto L_0x0481
        L_0x045b:
            boolean r6 = r5 instanceof android.os.Parcelable[]
            if (r6 == 0) goto L_0x046c
            android.os.Parcelable[] r5 = (android.os.Parcelable[]) r5
            int r6 = r5.length
            java.lang.Class<android.os.Bundle[]> r8 = android.os.Bundle[].class
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r5, r6, r8)
            r8 = r5
            android.os.Bundle[] r8 = (android.os.Bundle[]) r8
            goto L_0x0481
        L_0x046c:
            boolean r6 = r5 instanceof java.util.ArrayList
            if (r6 == 0) goto L_0x0480
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            int r6 = r5.size()
            android.os.Bundle[] r6 = new android.os.Bundle[r6]
            java.lang.Object[] r5 = r5.toArray(r6)
            r8 = r5
            android.os.Bundle[] r8 = (android.os.Bundle[]) r8
            goto L_0x0481
        L_0x0480:
            r8 = 0
        L_0x0481:
            if (r8 == 0) goto L_0x0486
            r12.putParcelableArray(r4, r8)
        L_0x0486:
            int r3 = r3 + 1
            goto L_0x043b
        L_0x0489:
            r8 = 0
        L_0x048a:
            int r1 = r14.size()
            if (r8 >= r1) goto L_0x04f4
            java.lang.Object r1 = r14.get(r8)
            android.os.Bundle r1 = (android.os.Bundle) r1
            if (r8 == 0) goto L_0x049b
            java.lang.String r2 = "_ep"
            goto L_0x049d
        L_0x049b:
            r2 = r21
        L_0x049d:
            r9 = r20
            r1.putString(r0, r9)
            if (r26 == 0) goto L_0x04b1
            com.google.android.gms.measurement.internal.zzfv r3 = r7.zzs
            com.google.android.gms.measurement.internal.zzkz r3 = r3.zzv()
            android.os.Bundle r1 = r3.zzt(r1)
            r12 = r1
            goto L_0x04b2
        L_0x04b1:
            r12 = r1
        L_0x04b2:
            com.google.android.gms.measurement.internal.zzat r15 = new com.google.android.gms.measurement.internal.zzat
            com.google.android.gms.measurement.internal.zzar r3 = new com.google.android.gms.measurement.internal.zzar
            r3.<init>(r12)
            r1 = r15
            r4 = r20
            r5 = r22
            r1.<init>(r2, r3, r4, r5)
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzjo r1 = r1.zzt()
            r5 = r28
            r1.zzA(r15, r5)
            if (r16 != 0) goto L_0x04f1
            java.util.Set<com.google.android.gms.measurement.internal.zzgw> r1 = r7.zze
            java.util.Iterator r15 = r1.iterator()
        L_0x04d4:
            boolean r1 = r15.hasNext()
            if (r1 == 0) goto L_0x04f1
            java.lang.Object r1 = r15.next()
            com.google.android.gms.measurement.internal.zzgw r1 = (com.google.android.gms.measurement.internal.zzgw) r1
            android.os.Bundle r4 = new android.os.Bundle
            r4.<init>(r12)
            r2 = r20
            r3 = r21
            r5 = r22
            r1.onEvent(r2, r3, r4, r5)
            r5 = r28
            goto L_0x04d4
        L_0x04f1:
            int r8 = r8 + 1
            goto L_0x048a
        L_0x04f4:
            com.google.android.gms.measurement.internal.zzfv r0 = r7.zzs
            r0.zzaw()
            com.google.android.gms.measurement.internal.zzfv r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzio r0 = r0.zzs()
            r1 = 0
            com.google.android.gms.measurement.internal.zzih r0 = r0.zzj(r1)
            if (r0 == 0) goto L_0x0524
            r1 = r21
            boolean r0 = r13.equals(r1)
            if (r0 == 0) goto L_0x0524
            com.google.android.gms.measurement.internal.zzfv r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzkd r0 = r0.zzu()
            com.google.android.gms.measurement.internal.zzfv r1 = r7.zzs
            com.google.android.gms.common.util.Clock r1 = r1.zzav()
            long r1 = r1.elapsedRealtime()
            com.google.android.gms.measurement.internal.zzkb r0 = r0.zzb
            r3 = 1
            r0.zzd(r3, r3, r1)
        L_0x0524:
            return
        L_0x0525:
            return
        L_0x0526:
            com.google.android.gms.measurement.internal.zzfv r0 = r7.zzs
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzc()
            java.lang.String r1 = "Event not sent since app measurement is disabled"
            r0.zza(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzia.zzI(java.lang.String, java.lang.String, long, android.os.Bundle, boolean, boolean, boolean, java.lang.String):void");
    }

    public final void zzJ(zzgw zzgw) {
        zza();
        Preconditions.checkNotNull(zzgw);
        if (!this.zze.add(zzgw)) {
            this.zzs.zzay().zzk().zza("OnEventListener already registered");
        }
    }

    public final void zzK(long j) {
        this.zzg.set((Object) null);
        this.zzs.zzaz().zzp(new zzhi(this, j));
    }

    /* access modifiers changed from: package-private */
    public final void zzL(long j, boolean z) {
        zzg();
        zza();
        this.zzs.zzay().zzc().zza("Resetting analytics data (FE)");
        zzkd zzu = this.zzs.zzu();
        zzu.zzg();
        zzkc zzkc = zzu.zza;
        zzu.zzb.zza();
        boolean zzJ = this.zzs.zzJ();
        zzfa zzm2 = this.zzs.zzm();
        zzm2.zzc.zzb(j);
        if (!TextUtils.isEmpty(zzm2.zzs.zzm().zzo.zza())) {
            zzm2.zzo.zzb((String) null);
        }
        zzod.zzc();
        if (zzm2.zzs.zzf().zzs((String) null, zzdy.zzai)) {
            zzm2.zzj.zzb(0);
        }
        if (!zzm2.zzs.zzf().zzv()) {
            zzm2.zzi(!zzJ);
        }
        zzm2.zzp.zzb((String) null);
        zzm2.zzq.zzb(0);
        zzm2.zzr.zzb((Bundle) null);
        if (z) {
            this.zzs.zzt().zzC();
        }
        zzod.zzc();
        if (this.zzs.zzf().zzs((String) null, zzdy.zzai)) {
            this.zzs.zzu().zza.zza();
        }
        this.zzc = !zzJ;
    }

    /* access modifiers changed from: protected */
    public final void zzM(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        Bundle bundle2 = new Bundle(bundle);
        for (String str4 : bundle2.keySet()) {
            Object obj = bundle2.get(str4);
            if (obj instanceof Bundle) {
                bundle2.putBundle(str4, new Bundle((Bundle) obj));
            } else {
                int i = 0;
                if (obj instanceof Parcelable[]) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    while (i < parcelableArr.length) {
                        Parcelable parcelable = parcelableArr[i];
                        if (parcelable instanceof Bundle) {
                            parcelableArr[i] = new Bundle((Bundle) parcelable);
                        }
                        i++;
                    }
                } else if (obj instanceof List) {
                    List list = (List) obj;
                    while (i < list.size()) {
                        Object obj2 = list.get(i);
                        if (obj2 instanceof Bundle) {
                            list.set(i, new Bundle((Bundle) obj2));
                        }
                        i++;
                    }
                }
            }
        }
        this.zzs.zzaz().zzp(new zzhf(this, str, str2, j, bundle2, z, z2, z3, str3));
    }

    /* access modifiers changed from: package-private */
    public final void zzN(String str, String str2, long j, Object obj) {
        this.zzs.zzaz().zzp(new zzhg(this, str, str2, obj, j));
    }

    /* access modifiers changed from: package-private */
    public final void zzO(String str) {
        this.zzg.set(str);
    }

    public final void zzP(Bundle bundle) {
        zzQ(bundle, this.zzs.zzav().currentTimeMillis());
    }

    public final void zzQ(Bundle bundle, long j) {
        Preconditions.checkNotNull(bundle);
        Bundle bundle2 = new Bundle(bundle);
        if (!TextUtils.isEmpty(bundle2.getString("app_id"))) {
            this.zzs.zzay().zzk().zza("Package name should be null when calling setConditionalUserProperty");
        }
        bundle2.remove("app_id");
        Preconditions.checkNotNull(bundle2);
        zzgr.zza(bundle2, "app_id", String.class, null);
        zzgr.zza(bundle2, "origin", String.class, null);
        zzgr.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.NAME, String.class, null);
        zzgr.zza(bundle2, "value", Object.class, null);
        zzgr.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, String.class, null);
        zzgr.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.class, 0L);
        zzgr.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, String.class, null);
        zzgr.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, Bundle.class, null);
        zzgr.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, String.class, null);
        zzgr.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, Bundle.class, null);
        zzgr.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.class, 0L);
        zzgr.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, String.class, null);
        zzgr.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, Bundle.class, null);
        Preconditions.checkNotEmpty(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        Preconditions.checkNotEmpty(bundle2.getString("origin"));
        Preconditions.checkNotNull(bundle2.get("value"));
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, j);
        String string = bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
        Object obj = bundle2.get("value");
        if (this.zzs.zzv().zzl(string) != 0) {
            this.zzs.zzay().zzd().zzb("Invalid conditional user property name", this.zzs.zzj().zzf(string));
        } else if (this.zzs.zzv().zzd(string, obj) == 0) {
            Object zzB = this.zzs.zzv().zzB(string, obj);
            if (zzB == null) {
                this.zzs.zzay().zzd().zzc("Unable to normalize conditional user property value", this.zzs.zzj().zzf(string), obj);
                return;
            }
            zzgr.zzb(bundle2, zzB);
            long j2 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT);
            if (!TextUtils.isEmpty(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME))) {
                this.zzs.zzf();
                if (j2 > 15552000000L || j2 < 1) {
                    this.zzs.zzay().zzd().zzc("Invalid conditional user property timeout", this.zzs.zzj().zzf(string), Long.valueOf(j2));
                    return;
                }
            }
            long j3 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE);
            this.zzs.zzf();
            if (j3 > 15552000000L || j3 < 1) {
                this.zzs.zzay().zzd().zzc("Invalid conditional user property time to live", this.zzs.zzj().zzf(string), Long.valueOf(j3));
            } else {
                this.zzs.zzaz().zzp(new zzhj(this, bundle2));
            }
        } else {
            this.zzs.zzay().zzd().zzc("Invalid conditional user property value", this.zzs.zzj().zzf(string), obj);
        }
    }

    public final void zzR(Bundle bundle, int i, long j) {
        zza();
        String zzh2 = zzag.zzh(bundle);
        if (zzh2 != null) {
            this.zzs.zzay().zzl().zzb("Ignoring invalid consent setting", zzh2);
            this.zzs.zzay().zzl().zza("Valid consent values are 'granted', 'denied'");
        }
        zzS(zzag.zza(bundle), i, j);
    }

    public final void zzS(zzag zzag, int i, long j) {
        boolean z;
        boolean z2;
        zzag zzag2;
        boolean z3;
        zzag zzag3 = zzag;
        int i2 = i;
        zza();
        if (i2 != -10 && zzag.zze() == null && zzag.zzf() == null) {
            this.zzs.zzay().zzl().zza("Discarding empty consent settings");
            return;
        }
        synchronized (this.zzh) {
            z = true;
            z2 = false;
            if (zzag.zzl(i2, this.zzj)) {
                boolean zzm2 = zzag.zzm(this.zzi);
                if (zzag.zzk() && !this.zzi.zzk()) {
                    z2 = true;
                }
                zzag zzd2 = zzag.zzd(this.zzi);
                this.zzi = zzd2;
                this.zzj = i2;
                boolean z4 = zzm2;
                zzag2 = zzd2;
                z3 = z2;
                z2 = z4;
            } else {
                zzag2 = zzag3;
                z3 = false;
                z = false;
            }
        }
        if (!z) {
            this.zzs.zzay().zzi().zzb("Ignoring lower-priority consent settings, proposed settings", zzag2);
            return;
        }
        long andIncrement = this.zzk.getAndIncrement();
        if (z2) {
            this.zzg.set((Object) null);
            this.zzs.zzaz().zzq(new zzhu(this, zzag2, j, i, andIncrement, z3));
        } else if (i2 == 30 || i2 == -10) {
            this.zzs.zzaz().zzq(new zzhv(this, zzag2, i, andIncrement, z3));
        } else {
            this.zzs.zzaz().zzp(new zzhw(this, zzag2, i, andIncrement, z3));
        }
    }

    public final void zzT(Bundle bundle, long j) {
        zznx.zzc();
        if (this.zzs.zzf().zzs((String) null, zzdy.zzas)) {
            this.zzs.zzaz().zzq(new zzhb(this, bundle, j));
        } else {
            zzB(bundle, j);
        }
    }

    public final void zzU(zzgv zzgv) {
        zzgv zzgv2;
        boolean z;
        zzg();
        zza();
        if (!(zzgv == null || zzgv == (zzgv2 = this.zzd))) {
            if (zzgv2 == null) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkState(z, "EventInterceptor already set.");
        }
        this.zzd = zzgv;
    }

    public final void zzV(Boolean bool) {
        zza();
        this.zzs.zzaz().zzp(new zzht(this, bool));
    }

    /* access modifiers changed from: package-private */
    public final void zzW(zzag zzag) {
        boolean z;
        Boolean bool;
        zzg();
        if (!zzag.zzk() || !zzag.zzj()) {
            z = this.zzs.zzt().zzM();
        } else {
            z = true;
        }
        if (z != this.zzs.zzK()) {
            this.zzs.zzG(z);
            zzfa zzm2 = this.zzs.zzm();
            zzfv zzfv = zzm2.zzs;
            zzm2.zzg();
            if (zzm2.zza().contains("measurement_enabled_from_api")) {
                bool = Boolean.valueOf(zzm2.zza().getBoolean("measurement_enabled_from_api", true));
            } else {
                bool = null;
            }
            if (!z || bool == null || bool.booleanValue()) {
                zzac(Boolean.valueOf(z), false);
            }
        }
    }

    public final void zzX(String str, String str2, Object obj, boolean z) {
        zzY("auto", "_ldl", obj, true, this.zzs.zzav().currentTimeMillis());
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0083  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzZ(java.lang.String r9, java.lang.String r10, java.lang.Object r11, long r12) {
        /*
            r8 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r10)
            r8.zzg()
            r8.zza()
            java.lang.String r0 = "allow_personalized_ads"
            boolean r0 = r0.equals(r10)
            java.lang.String r1 = "_npa"
            if (r0 == 0) goto L_0x0068
            boolean r0 = r11 instanceof java.lang.String
            if (r0 == 0) goto L_0x0056
            r0 = r11
            java.lang.String r0 = (java.lang.String) r0
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L_0x0056
            r10 = 1
            java.util.Locale r11 = java.util.Locale.ENGLISH
            java.lang.String r11 = r0.toLowerCase(r11)
            java.lang.String r0 = "false"
            boolean r11 = r0.equals(r11)
            r2 = 1
            if (r10 == r11) goto L_0x0037
            r10 = 0
            goto L_0x0038
        L_0x0037:
            r10 = r2
        L_0x0038:
            java.lang.Long r11 = java.lang.Long.valueOf(r10)
            com.google.android.gms.measurement.internal.zzfv r10 = r8.zzs
            com.google.android.gms.measurement.internal.zzfa r10 = r10.zzm()
            com.google.android.gms.measurement.internal.zzez r10 = r10.zzh
            long r4 = r11.longValue()
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 != 0) goto L_0x004f
            java.lang.String r0 = "true"
            goto L_0x0050
        L_0x004f:
        L_0x0050:
            r10.zzb(r0)
            r6 = r11
            r3 = r1
            goto L_0x006b
        L_0x0056:
            if (r11 != 0) goto L_0x0068
            com.google.android.gms.measurement.internal.zzfv r10 = r8.zzs
            com.google.android.gms.measurement.internal.zzfa r10 = r10.zzm()
            com.google.android.gms.measurement.internal.zzez r10 = r10.zzh
            java.lang.String r0 = "unset"
            r10.zzb(r0)
            r6 = r11
            r3 = r1
            goto L_0x006b
        L_0x0068:
            r3 = r10
            r6 = r11
        L_0x006b:
            com.google.android.gms.measurement.internal.zzfv r10 = r8.zzs
            boolean r10 = r10.zzJ()
            if (r10 != 0) goto L_0x0083
            com.google.android.gms.measurement.internal.zzfv r9 = r8.zzs
            com.google.android.gms.measurement.internal.zzel r9 = r9.zzay()
            com.google.android.gms.measurement.internal.zzej r9 = r9.zzj()
            java.lang.String r10 = "User property not set since app measurement is disabled"
            r9.zza(r10)
            return
        L_0x0083:
            com.google.android.gms.measurement.internal.zzfv r10 = r8.zzs
            boolean r10 = r10.zzM()
            if (r10 != 0) goto L_0x008c
            return
        L_0x008c:
            com.google.android.gms.measurement.internal.zzkv r10 = new com.google.android.gms.measurement.internal.zzkv
            r2 = r10
            r4 = r12
            r7 = r9
            r2.<init>(r3, r4, r6, r7)
            com.google.android.gms.measurement.internal.zzfv r9 = r8.zzs
            com.google.android.gms.measurement.internal.zzjo r9 = r9.zzt()
            r9.zzK(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzia.zzZ(java.lang.String, java.lang.String, java.lang.Object, long):void");
    }

    public final void zzaa(zzgw zzgw) {
        zza();
        Preconditions.checkNotNull(zzgw);
        if (!this.zze.remove(zzgw)) {
            this.zzs.zzay().zzk().zza("OnEventListener had not been registered");
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zzf() {
        return false;
    }

    public final int zzh(String str) {
        Preconditions.checkNotEmpty(str);
        this.zzs.zzf();
        return 25;
    }

    public final Boolean zzi() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) this.zzs.zzaz().zzd(atomicReference, 15000, "boolean test flag value", new zzhl(this, atomicReference));
    }

    public final Double zzj() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) this.zzs.zzaz().zzd(atomicReference, 15000, "double test flag value", new zzhs(this, atomicReference));
    }

    public final Integer zzl() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) this.zzs.zzaz().zzd(atomicReference, 15000, "int test flag value", new zzhr(this, atomicReference));
    }

    public final Long zzm() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) this.zzs.zzaz().zzd(atomicReference, 15000, "long test flag value", new zzhq(this, atomicReference));
    }

    public final String zzo() {
        return this.zzg.get();
    }

    public final String zzp() {
        zzih zzi2 = this.zzs.zzs().zzi();
        if (zzi2 != null) {
            return zzi2.zzb;
        }
        return null;
    }

    public final String zzq() {
        zzih zzi2 = this.zzs.zzs().zzi();
        if (zzi2 != null) {
            return zzi2.zza;
        }
        return null;
    }

    public final String zzr() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) this.zzs.zzaz().zzd(atomicReference, 15000, "String test flag value", new zzhp(this, atomicReference));
    }

    public final ArrayList<Bundle> zzs(String str, String str2) {
        if (this.zzs.zzaz().zzs()) {
            this.zzs.zzay().zzd().zza("Cannot get conditional user properties from analytics worker thread");
            return new ArrayList<>(0);
        }
        this.zzs.zzaw();
        if (zzaa.zza()) {
            this.zzs.zzay().zzd().zza("Cannot get conditional user properties from main thread");
            return new ArrayList<>(0);
        }
        AtomicReference atomicReference = new AtomicReference();
        this.zzs.zzaz().zzd(atomicReference, 5000, "get conditional user properties", new zzhm(this, atomicReference, (String) null, str, str2));
        List list = (List) atomicReference.get();
        if (list != null) {
            return zzkz.zzG(list);
        }
        this.zzs.zzay().zzd().zzb("Timed out waiting for get conditional user properties", (Object) null);
        return new ArrayList<>();
    }

    public final List<zzkv> zzt(boolean z) {
        zza();
        this.zzs.zzay().zzj().zza("Getting user properties (FE)");
        if (!this.zzs.zzaz().zzs()) {
            this.zzs.zzaw();
            if (zzaa.zza()) {
                this.zzs.zzay().zzd().zza("Cannot get all user properties from main thread");
                return Collections.emptyList();
            }
            AtomicReference atomicReference = new AtomicReference();
            this.zzs.zzaz().zzd(atomicReference, 5000, "get user properties", new zzhh(this, atomicReference, z));
            List<zzkv> list = (List) atomicReference.get();
            if (list != null) {
                return list;
            }
            this.zzs.zzay().zzd().zzb("Timed out waiting for get user properties, includeInternal", Boolean.valueOf(z));
            return Collections.emptyList();
        }
        this.zzs.zzay().zzd().zza("Cannot get all user properties from analytics worker thread");
        return Collections.emptyList();
    }

    public final Map<String, Object> zzu(String str, String str2, boolean z) {
        if (this.zzs.zzaz().zzs()) {
            this.zzs.zzay().zzd().zza("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        }
        this.zzs.zzaw();
        if (zzaa.zza()) {
            this.zzs.zzay().zzd().zza("Cannot get user properties from main thread");
            return Collections.emptyMap();
        }
        AtomicReference atomicReference = new AtomicReference();
        this.zzs.zzaz().zzd(atomicReference, 5000, "get user properties", new zzhn(this, atomicReference, (String) null, str, str2, z));
        List<zzkv> list = (List) atomicReference.get();
        if (list == null) {
            this.zzs.zzay().zzd().zzb("Timed out waiting for handle get user properties, includeInternal", Boolean.valueOf(z));
            return Collections.emptyMap();
        }
        ArrayMap arrayMap = new ArrayMap(list.size());
        for (zzkv zzkv : list) {
            Object zza2 = zzkv.zza();
            if (zza2 != null) {
                arrayMap.put(zzkv.zzb, zza2);
            }
        }
        return arrayMap;
    }

    public final void zzy() {
        zzg();
        zza();
        if (this.zzs.zzM()) {
            if (this.zzs.zzf().zzs((String) null, zzdy.zzZ)) {
                zzaf zzf2 = this.zzs.zzf();
                zzf2.zzs.zzaw();
                Boolean zzk2 = zzf2.zzk("google_analytics_deferred_deep_link_enabled");
                if (zzk2 != null && zzk2.booleanValue()) {
                    this.zzs.zzay().zzc().zza("Deferred Deep Link feature enabled.");
                    this.zzs.zzaz().zzp(new zzgz(this));
                }
            }
            this.zzs.zzt().zzq();
            this.zzc = false;
            zzfa zzm2 = this.zzs.zzm();
            zzm2.zzg();
            String string = zzm2.zza().getString("previous_os_version", (String) null);
            zzm2.zzs.zzg().zzu();
            String str = Build.VERSION.RELEASE;
            if (!TextUtils.isEmpty(str) && !str.equals(string)) {
                SharedPreferences.Editor edit = zzm2.zza().edit();
                edit.putString("previous_os_version", str);
                edit.apply();
            }
            if (!TextUtils.isEmpty(string)) {
                this.zzs.zzg().zzu();
                if (!string.equals(Build.VERSION.RELEASE)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("_po", string);
                    zzG("auto", "_ou", bundle);
                }
            }
        }
    }

    public final void zzz(String str, String str2, Bundle bundle) {
        long currentTimeMillis = this.zzs.zzav().currentTimeMillis();
        Preconditions.checkNotEmpty(str);
        Bundle bundle2 = new Bundle();
        bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, str);
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, currentTimeMillis);
        if (str2 != null) {
            bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str2);
            bundle2.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle);
        }
        this.zzs.zzaz().zzp(new zzhk(this, bundle2));
    }

    public final void zzY(String str, String str2, Object obj, boolean z, long j) {
        int i;
        int i2;
        int i3;
        String str3 = str2;
        Object obj2 = obj;
        String str4 = str == null ? "app" : str;
        int i4 = 6;
        if (z) {
            i = this.zzs.zzv().zzl(str3);
        } else {
            zzkz zzv = this.zzs.zzv();
            if (zzv.zzab("user property", str3)) {
                if (!zzv.zzY("user property", zzgu.zza, (String[]) null, str3)) {
                    i4 = 15;
                } else {
                    zzv.zzs.zzf();
                    if (zzv.zzX("user property", 24, str3)) {
                        i = 0;
                    }
                }
            }
            i = i4;
        }
        if (i != 0) {
            zzkz zzv2 = this.zzs.zzv();
            this.zzs.zzf();
            String zzC = zzv2.zzC(str3, 24, true);
            if (str3 != null) {
                i3 = str2.length();
            } else {
                i3 = 0;
            }
            this.zzs.zzv().zzM(this.zzn, (String) null, i, "_ev", zzC, i3);
        } else if (obj2 != null) {
            int zzd2 = this.zzs.zzv().zzd(str3, obj2);
            if (zzd2 != 0) {
                zzkz zzv3 = this.zzs.zzv();
                this.zzs.zzf();
                String zzC2 = zzv3.zzC(str3, 24, true);
                if ((obj2 instanceof String) || (obj2 instanceof CharSequence)) {
                    i2 = obj.toString().length();
                } else {
                    i2 = 0;
                }
                this.zzs.zzv().zzM(this.zzn, (String) null, zzd2, "_ev", zzC2, i2);
                return;
            }
            Object zzB = this.zzs.zzv().zzB(str3, obj2);
            if (zzB != null) {
                zzN(str4, str2, j, zzB);
            }
        } else {
            zzN(str4, str2, j, (Object) null);
        }
    }
}
