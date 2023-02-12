package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzcl;
import com.google.android.gms.internal.measurement.zzhu;
import com.google.android.gms.internal.measurement.zznu;
import com.google.android.gms.internal.measurement.zznx;
import com.google.firebase.database.core.ServerValues;
import com.google.firebase.messaging.Constants;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zzfv implements zzgq {
    private static volatile zzfv zzd;
    private zzec zzA;
    private boolean zzB = false;
    private Boolean zzC;
    private long zzD;
    private volatile Boolean zzE;
    private volatile boolean zzF;
    private int zzG;
    private final AtomicInteger zzH = new AtomicInteger(0);
    protected Boolean zza;
    protected Boolean zzb;
    final long zzc;
    private final Context zze;
    private final String zzf;
    private final String zzg;
    private final String zzh;
    private final boolean zzi;
    private final zzaa zzj;
    private final zzaf zzk;
    private final zzfa zzl;
    private final zzel zzm;
    private final zzfs zzn;
    private final zzkd zzo;
    private final zzkz zzp;
    private final zzeg zzq;
    private final Clock zzr;
    private final zzio zzs;
    private final zzia zzt;
    private final zzd zzu;
    private final zzie zzv;
    private final String zzw;
    private zzee zzx;
    private zzjo zzy;
    private zzan zzz;

    zzfv(zzgy zzgy) {
        long j;
        Bundle bundle;
        boolean z = false;
        Preconditions.checkNotNull(zzgy);
        zzaa zzaa = new zzaa(zzgy.zza);
        this.zzj = zzaa;
        zzdv.zza = zzaa;
        Context context = zzgy.zza;
        this.zze = context;
        this.zzf = zzgy.zzb;
        this.zzg = zzgy.zzc;
        this.zzh = zzgy.zzd;
        this.zzi = zzgy.zzh;
        this.zzE = zzgy.zze;
        this.zzw = zzgy.zzj;
        this.zzF = true;
        zzcl zzcl = zzgy.zzg;
        if (!(zzcl == null || (bundle = zzcl.zzg) == null)) {
            Object obj = bundle.get("measurementEnabled");
            if (obj instanceof Boolean) {
                this.zza = (Boolean) obj;
            }
            Object obj2 = zzcl.zzg.get("measurementDeactivated");
            if (obj2 instanceof Boolean) {
                this.zzb = (Boolean) obj2;
            }
        }
        zzhu.zzd(context);
        Clock instance = DefaultClock.getInstance();
        this.zzr = instance;
        Long l = zzgy.zzi;
        if (l != null) {
            j = l.longValue();
        } else {
            j = instance.currentTimeMillis();
        }
        this.zzc = j;
        this.zzk = new zzaf(this);
        zzfa zzfa = new zzfa(this);
        zzfa.zzv();
        this.zzl = zzfa;
        zzel zzel = new zzel(this);
        zzel.zzv();
        this.zzm = zzel;
        zzkz zzkz = new zzkz(this);
        zzkz.zzv();
        this.zzp = zzkz;
        this.zzq = new zzeg(new zzgx(zzgy, this));
        this.zzu = new zzd(this);
        zzio zzio = new zzio(this);
        zzio.zzb();
        this.zzs = zzio;
        zzia zzia = new zzia(this);
        zzia.zzb();
        this.zzt = zzia;
        zzkd zzkd = new zzkd(this);
        zzkd.zzb();
        this.zzo = zzkd;
        zzie zzie = new zzie(this);
        zzie.zzv();
        this.zzv = zzie;
        zzfs zzfs = new zzfs(this);
        zzfs.zzv();
        this.zzn = zzfs;
        zzcl zzcl2 = zzgy.zzg;
        z = (zzcl2 == null || zzcl2.zzb == 0) ? true : z;
        if (context.getApplicationContext() instanceof Application) {
            zzia zzq2 = zzq();
            if (zzq2.zzs.zze.getApplicationContext() instanceof Application) {
                Application application = (Application) zzq2.zzs.zze.getApplicationContext();
                if (zzq2.zza == null) {
                    zzq2.zza = new zzhz(zzq2, (zzhy) null);
                }
                if (z) {
                    application.unregisterActivityLifecycleCallbacks(zzq2.zza);
                    application.registerActivityLifecycleCallbacks(zzq2.zza);
                    zzq2.zzs.zzay().zzj().zza("Registered activity lifecycle callback");
                }
            }
        } else {
            zzay().zzk().zza("Application context is not an Application");
        }
        zzfs.zzp(new zzfu(this, zzgy));
    }

    static /* bridge */ /* synthetic */ void zzA(zzfv zzfv, zzgy zzgy) {
        zzfv.zzaz().zzg();
        zzfv.zzk.zzn();
        zzan zzan = new zzan(zzfv);
        zzan.zzv();
        zzfv.zzz = zzan;
        zzec zzec = new zzec(zzfv, zzgy.zzf);
        zzec.zzb();
        zzfv.zzA = zzec;
        zzee zzee = new zzee(zzfv);
        zzee.zzb();
        zzfv.zzx = zzee;
        zzjo zzjo = new zzjo(zzfv);
        zzjo.zzb();
        zzfv.zzy = zzjo;
        zzfv.zzp.zzw();
        zzfv.zzl.zzw();
        zzfv.zzA.zzc();
        zzej zzi2 = zzfv.zzay().zzi();
        zzfv.zzk.zzh();
        zzi2.zzb("App measurement initialized, version", 46000L);
        zzfv.zzay().zzi().zza("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        String zzl2 = zzec.zzl();
        if (TextUtils.isEmpty(zzfv.zzf)) {
            if (zzfv.zzv().zzad(zzl2)) {
                zzfv.zzay().zzi().zza("Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.");
            } else {
                zzej zzi3 = zzfv.zzay().zzi();
                String valueOf = String.valueOf(zzl2);
                zzi3.zza(valueOf.length() != 0 ? "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(valueOf) : new String("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app "));
            }
        }
        zzfv.zzay().zzc().zza("Debug-level message logging enabled");
        if (zzfv.zzG != zzfv.zzH.get()) {
            zzfv.zzay().zzd().zzc("Not all components initialized", Integer.valueOf(zzfv.zzG), Integer.valueOf(zzfv.zzH.get()));
        }
        zzfv.zzB = true;
    }

    static final void zzO() {
        throw new IllegalStateException("Unexpected call on client side");
    }

    private static final void zzP(zzgo zzgo) {
        if (zzgo == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private static final void zzQ(zzf zzf2) {
        if (zzf2 == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzf2.zze()) {
            String valueOf = String.valueOf(zzf2.getClass());
            String.valueOf(valueOf).length();
            throw new IllegalStateException("Component not initialized: ".concat(String.valueOf(valueOf)));
        }
    }

    private static final void zzR(zzgp zzgp) {
        if (zzgp == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzgp.zzx()) {
            String valueOf = String.valueOf(zzgp.getClass());
            String.valueOf(valueOf).length();
            throw new IllegalStateException("Component not initialized: ".concat(String.valueOf(valueOf)));
        }
    }

    public static zzfv zzp(Context context, zzcl zzcl, Long l) {
        Bundle bundle;
        if (zzcl != null && (zzcl.zze == null || zzcl.zzf == null)) {
            zzcl = new zzcl(zzcl.zza, zzcl.zzb, zzcl.zzc, zzcl.zzd, (String) null, (String) null, zzcl.zzg, (String) null);
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzd == null) {
            synchronized (zzfv.class) {
                if (zzd == null) {
                    zzd = new zzfv(new zzgy(context, zzcl, l));
                }
            }
        } else if (!(zzcl == null || (bundle = zzcl.zzg) == null || !bundle.containsKey("dataCollectionDefaultEnabled"))) {
            Preconditions.checkNotNull(zzd);
            zzd.zzE = Boolean.valueOf(zzcl.zzg.getBoolean("dataCollectionDefaultEnabled"));
        }
        Preconditions.checkNotNull(zzd);
        return zzd;
    }

    /* access modifiers changed from: package-private */
    public final void zzB() {
        this.zzH.incrementAndGet();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzC(String str, int i, Throwable th, byte[] bArr, Map map) {
        if (!(i == 200 || i == 204)) {
            if (i == 304) {
                i = 304;
            }
            zzay().zzk().zzc("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i), th);
        }
        if (th == null) {
            zzm().zzm.zza(true);
            if (bArr == null || bArr.length == 0) {
                zzay().zzc().zza("Deferred Deep Link response empty.");
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(new String(bArr));
                String optString = jSONObject.optString("deeplink", "");
                String optString2 = jSONObject.optString("gclid", "");
                double optDouble = jSONObject.optDouble(ServerValues.NAME_OP_TIMESTAMP, 0.0d);
                if (TextUtils.isEmpty(optString)) {
                    zzay().zzc().zza("Deferred Deep Link is empty.");
                    return;
                }
                zzkz zzv2 = zzv();
                zzfv zzfv = zzv2.zzs;
                if (!TextUtils.isEmpty(optString)) {
                    List<ResolveInfo> queryIntentActivities = zzv2.zzs.zze.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(optString)), 0);
                    if (queryIntentActivities != null && !queryIntentActivities.isEmpty()) {
                        Bundle bundle = new Bundle();
                        bundle.putString("gclid", optString2);
                        bundle.putString("_cis", "ddp");
                        this.zzt.zzG("auto", Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, bundle);
                        zzkz zzv3 = zzv();
                        if (!TextUtils.isEmpty(optString)) {
                            try {
                                SharedPreferences.Editor edit = zzv3.zzs.zze.getSharedPreferences("google.analytics.deferred.deeplink.prefs", 0).edit();
                                edit.putString("deeplink", optString);
                                edit.putLong(ServerValues.NAME_OP_TIMESTAMP, Double.doubleToRawLongBits(optDouble));
                                if (edit.commit()) {
                                    zzv3.zzs.zze.sendBroadcast(new Intent("android.google.analytics.action.DEEPLINK_ACTION"));
                                    return;
                                }
                                return;
                            } catch (RuntimeException e) {
                                zzv3.zzs.zzay().zzd().zzb("Failed to persist Deferred Deep Link. exception", e);
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                }
                zzay().zzk().zzc("Deferred Deep Link validation failed. gclid, deep link", optString2, optString);
                return;
            } catch (JSONException e2) {
                zzay().zzd().zzb("Failed to parse the Deferred Deep Link response. exception", e2);
                return;
            }
        }
        zzay().zzk().zzc("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i), th);
    }

    /* access modifiers changed from: package-private */
    public final void zzD() {
        this.zzG++;
    }

    public final void zzE() {
        zzaz().zzg();
        zzR(zzr());
        String zzl2 = zzh().zzl();
        Pair<String, Boolean> zzb2 = zzm().zzb(zzl2);
        if (!this.zzk.zzr() || ((Boolean) zzb2.second).booleanValue() || TextUtils.isEmpty((CharSequence) zzb2.first)) {
            zzay().zzc().zza("ADID unavailable to retrieve Deferred Deep Link. Skipping");
            return;
        }
        zzie zzr2 = zzr();
        zzr2.zzu();
        ConnectivityManager connectivityManager = (ConnectivityManager) zzr2.zzs.zze.getSystemService("connectivity");
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            try {
                networkInfo = connectivityManager.getActiveNetworkInfo();
            } catch (SecurityException e) {
            }
        }
        if (networkInfo == null || !networkInfo.isConnected()) {
            zzay().zzk().zza("Network is not available for Deferred Deep Link request. Skipping");
            return;
        }
        zzkz zzv2 = zzv();
        zzh().zzs.zzk.zzh();
        URL zzD2 = zzv2.zzD(46000, zzl2, (String) zzb2.first, zzm().zzn.zza() - 1);
        if (zzD2 != null) {
            zzie zzr3 = zzr();
            zzft zzft = new zzft(this);
            zzr3.zzg();
            zzr3.zzu();
            Preconditions.checkNotNull(zzD2);
            Preconditions.checkNotNull(zzft);
            zzr3.zzs.zzaz().zzo(new zzid(zzr3, zzl2, zzD2, (byte[]) null, (Map) null, zzft, (byte[]) null));
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzF(boolean z) {
        this.zzE = Boolean.valueOf(z);
    }

    public final void zzG(boolean z) {
        zzaz().zzg();
        this.zzF = z;
    }

    /* access modifiers changed from: protected */
    public final void zzH(zzcl zzcl) {
        zzag zzag;
        zzaz().zzg();
        zzag zzc2 = zzm().zzc();
        zzfa zzm2 = zzm();
        zzfv zzfv = zzm2.zzs;
        zzm2.zzg();
        int i = 100;
        int i2 = zzm2.zza().getInt("consent_source", 100);
        zzaf zzaf = this.zzk;
        zzfv zzfv2 = zzaf.zzs;
        Boolean zzk2 = zzaf.zzk("google_analytics_default_allow_ad_storage");
        zzaf zzaf2 = this.zzk;
        zzfv zzfv3 = zzaf2.zzs;
        Boolean zzk3 = zzaf2.zzk("google_analytics_default_allow_analytics_storage");
        if (!(zzk2 == null && zzk3 == null) && zzm().zzl(-10)) {
            zzag = new zzag(zzk2, zzk3);
            i = -10;
        } else if (TextUtils.isEmpty(zzh().zzn()) || !(i2 == 0 || i2 == 30 || i2 == 10 || i2 == 30 || i2 == 30 || i2 == 40)) {
            zznx.zzc();
            if ((!this.zzk.zzs((String) null, zzdy.zzar) || TextUtils.isEmpty(zzh().zzn())) && zzcl != null && zzcl.zzg != null && zzm().zzl(30)) {
                zzag = zzag.zza(zzcl.zzg);
                if (!zzag.equals(zzag.zza)) {
                    i = 30;
                }
            }
            zzag = null;
        } else {
            zzq().zzS(zzag.zza, -10, this.zzc);
            zzag = null;
        }
        if (zzag != null) {
            zzq().zzS(zzag, i, this.zzc);
            zzc2 = zzag;
        }
        zzq().zzW(zzc2);
        if (zzm().zzc.zza() == 0) {
            zzay().zzj().zzb("Persisting first open", Long.valueOf(this.zzc));
            zzm().zzc.zzb(this.zzc);
        }
        zzq().zzb.zzc();
        if (zzM()) {
            if (!TextUtils.isEmpty(zzh().zzn()) || !TextUtils.isEmpty(zzh().zzk())) {
                zzkz zzv2 = zzv();
                String zzn2 = zzh().zzn();
                zzfa zzm3 = zzm();
                zzm3.zzg();
                String string = zzm3.zza().getString("gmp_app_id", (String) null);
                String zzk4 = zzh().zzk();
                zzfa zzm4 = zzm();
                zzm4.zzg();
                if (zzv2.zzam(zzn2, string, zzk4, zzm4.zza().getString("admob_app_id", (String) null))) {
                    zzay().zzi().zza("Rechecking which service to use due to a GMP App Id change");
                    zzfa zzm5 = zzm();
                    zzm5.zzg();
                    Boolean zzd2 = zzm5.zzd();
                    SharedPreferences.Editor edit = zzm5.zza().edit();
                    edit.clear();
                    edit.apply();
                    if (zzd2 != null) {
                        zzm5.zzh(zzd2);
                    }
                    zzi().zzj();
                    this.zzy.zzs();
                    this.zzy.zzr();
                    zzm().zzc.zzb(this.zzc);
                    zzm().zze.zzb((String) null);
                }
                zzfa zzm6 = zzm();
                String zzn3 = zzh().zzn();
                zzm6.zzg();
                SharedPreferences.Editor edit2 = zzm6.zza().edit();
                edit2.putString("gmp_app_id", zzn3);
                edit2.apply();
                zzfa zzm7 = zzm();
                String zzk5 = zzh().zzk();
                zzm7.zzg();
                SharedPreferences.Editor edit3 = zzm7.zza().edit();
                edit3.putString("admob_app_id", zzk5);
                edit3.apply();
            }
            if (!zzm().zzc().zzk()) {
                zzm().zze.zzb((String) null);
            }
            zzq().zzO(zzm().zze.zza());
            zznu.zzc();
            if (this.zzk.zzs((String) null, zzdy.zzah)) {
                try {
                    zzv().zzs.zze.getClassLoader().loadClass("com.google.firebase.remoteconfig.FirebaseRemoteConfig");
                } catch (ClassNotFoundException e) {
                    if (!TextUtils.isEmpty(zzm().zzo.zza())) {
                        zzay().zzk().zza("Remote config removed with active feature rollouts");
                        zzm().zzo.zzb((String) null);
                    }
                }
            }
            if (!TextUtils.isEmpty(zzh().zzn()) || !TextUtils.isEmpty(zzh().zzk())) {
                boolean zzJ = zzJ();
                if (!zzm().zzj() && !this.zzk.zzv()) {
                    zzm().zzi(!zzJ);
                }
                if (zzJ) {
                    zzq().zzy();
                }
                zzu().zza.zza();
                zzt().zzu(new AtomicReference());
                zzt().zzH(zzm().zzr.zza());
            }
        } else if (zzJ()) {
            if (!zzv().zzac("android.permission.INTERNET")) {
                zzay().zzd().zza("App is missing INTERNET permission");
            }
            if (!zzv().zzac("android.permission.ACCESS_NETWORK_STATE")) {
                zzay().zzd().zza("App is missing ACCESS_NETWORK_STATE permission");
            }
            if (!Wrappers.packageManager(this.zze).isCallerInstantApp() && !this.zzk.zzx()) {
                if (!zzkz.zzai(this.zze)) {
                    zzay().zzd().zza("AppMeasurementReceiver not registered/enabled");
                }
                if (!zzkz.zzaj(this.zze, false)) {
                    zzay().zzd().zza("AppMeasurementService not registered/enabled");
                }
            }
            zzay().zzd().zza("Uploading is not possible. App measurement disabled");
        }
        zzm().zzi.zza(true);
    }

    public final boolean zzI() {
        return this.zzE != null && this.zzE.booleanValue();
    }

    public final boolean zzJ() {
        return zza() == 0;
    }

    public final boolean zzK() {
        zzaz().zzg();
        return this.zzF;
    }

    @Pure
    public final boolean zzL() {
        return TextUtils.isEmpty(this.zzf);
    }

    /* access modifiers changed from: protected */
    public final boolean zzM() {
        boolean z;
        if (this.zzB) {
            zzaz().zzg();
            Boolean bool = this.zzC;
            if (bool == null || this.zzD == 0 || (!bool.booleanValue() && Math.abs(this.zzr.elapsedRealtime() - this.zzD) > 1000)) {
                this.zzD = this.zzr.elapsedRealtime();
                boolean z2 = true;
                if (zzv().zzac("android.permission.INTERNET")) {
                    if (zzv().zzac("android.permission.ACCESS_NETWORK_STATE")) {
                        if (Wrappers.packageManager(this.zze).isCallerInstantApp() || this.zzk.zzx()) {
                            z = true;
                        } else if (zzkz.zzai(this.zze) && zzkz.zzaj(this.zze, false)) {
                            z = true;
                        }
                    }
                    z = false;
                } else {
                    z = false;
                }
                Boolean valueOf = Boolean.valueOf(z);
                this.zzC = valueOf;
                if (valueOf.booleanValue()) {
                    if (!zzv().zzW(zzh().zzn(), zzh().zzk(), zzh().zzm()) && TextUtils.isEmpty(zzh().zzk())) {
                        z2 = false;
                    }
                    this.zzC = Boolean.valueOf(z2);
                }
            }
            return this.zzC.booleanValue();
        }
        throw new IllegalStateException("AppMeasurement is not initialized");
    }

    @Pure
    public final boolean zzN() {
        return this.zzi;
    }

    public final int zza() {
        zzaz().zzg();
        if (this.zzk.zzv()) {
            return 1;
        }
        Boolean bool = this.zzb;
        if (bool != null && bool.booleanValue()) {
            return 2;
        }
        zzaz().zzg();
        if (!this.zzF) {
            return 8;
        }
        Boolean zzd2 = zzm().zzd();
        if (zzd2 == null) {
            zzaf zzaf = this.zzk;
            zzaa zzaa = zzaf.zzs.zzj;
            Boolean zzk2 = zzaf.zzk("firebase_analytics_collection_enabled");
            if (zzk2 == null) {
                Boolean bool2 = this.zza;
                if (bool2 != null) {
                    if (bool2.booleanValue()) {
                        return 0;
                    }
                    return 5;
                } else if (!this.zzk.zzs((String) null, zzdy.zzS) || this.zzE == null || this.zzE.booleanValue()) {
                    return 0;
                } else {
                    return 7;
                }
            } else if (zzk2.booleanValue()) {
                return 0;
            } else {
                return 4;
            }
        } else if (zzd2.booleanValue()) {
            return 0;
        } else {
            return 3;
        }
    }

    @Pure
    public final Context zzau() {
        return this.zze;
    }

    @Pure
    public final Clock zzav() {
        return this.zzr;
    }

    @Pure
    public final zzaa zzaw() {
        return this.zzj;
    }

    @Pure
    public final zzel zzay() {
        zzR(this.zzm);
        return this.zzm;
    }

    @Pure
    public final zzfs zzaz() {
        zzR(this.zzn);
        return this.zzn;
    }

    @Pure
    public final zzd zzd() {
        zzd zzd2 = this.zzu;
        if (zzd2 != null) {
            return zzd2;
        }
        throw new IllegalStateException("Component not created");
    }

    @Pure
    public final zzaf zzf() {
        return this.zzk;
    }

    @Pure
    public final zzan zzg() {
        zzR(this.zzz);
        return this.zzz;
    }

    @Pure
    public final zzec zzh() {
        zzQ(this.zzA);
        return this.zzA;
    }

    @Pure
    public final zzee zzi() {
        zzQ(this.zzx);
        return this.zzx;
    }

    @Pure
    public final zzeg zzj() {
        return this.zzq;
    }

    public final zzel zzl() {
        zzel zzel = this.zzm;
        if (zzel == null || !zzel.zzx()) {
            return null;
        }
        return this.zzm;
    }

    @Pure
    public final zzfa zzm() {
        zzP(this.zzl);
        return this.zzl;
    }

    /* access modifiers changed from: package-private */
    @SideEffectFree
    public final zzfs zzo() {
        return this.zzn;
    }

    @Pure
    public final zzia zzq() {
        zzQ(this.zzt);
        return this.zzt;
    }

    @Pure
    public final zzie zzr() {
        zzR(this.zzv);
        return this.zzv;
    }

    @Pure
    public final zzio zzs() {
        zzQ(this.zzs);
        return this.zzs;
    }

    @Pure
    public final zzjo zzt() {
        zzQ(this.zzy);
        return this.zzy;
    }

    @Pure
    public final zzkd zzu() {
        zzQ(this.zzo);
        return this.zzo;
    }

    @Pure
    public final zzkz zzv() {
        zzP(this.zzp);
        return this.zzp;
    }

    @Pure
    public final String zzw() {
        return this.zzf;
    }

    @Pure
    public final String zzx() {
        return this.zzg;
    }

    @Pure
    public final String zzy() {
        return this.zzh;
    }

    @Pure
    public final String zzz() {
        return this.zzw;
    }
}
