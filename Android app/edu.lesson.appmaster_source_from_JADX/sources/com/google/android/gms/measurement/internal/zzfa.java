package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.internal.Preconditions;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzfa extends zzgp {
    static final Pair<String, Long> zza = new Pair<>("", 0L);
    public zzey zzb;
    public final zzew zzc = new zzew(this, "first_open_time", 0);
    public final zzew zzd = new zzew(this, "app_install_time", 0);
    public final zzez zze = new zzez(this, "app_instance_id", (String) null);
    public final zzew zzf = new zzew(this, "session_timeout", 1800000);
    public final zzeu zzg = new zzeu(this, "start_new_session", true);
    public final zzez zzh = new zzez(this, "non_personalized_ads", (String) null);
    public final zzeu zzi = new zzeu(this, "allow_remote_dynamite", false);
    public final zzew zzj = new zzew(this, "last_pause_time", 0);
    public boolean zzk;
    public final zzeu zzl = new zzeu(this, "app_backgrounded", false);
    public final zzeu zzm = new zzeu(this, "deep_link_retrieval_complete", false);
    public final zzew zzn = new zzew(this, "deep_link_retrieval_attempts", 0);
    public final zzez zzo = new zzez(this, "firebase_feature_rollouts", (String) null);
    public final zzez zzp = new zzez(this, "deferred_attribution_cache", (String) null);
    public final zzew zzq = new zzew(this, "deferred_attribution_cache_timestamp", 0);
    public final zzev zzr = new zzev(this, "default_event_parameters", (Bundle) null);
    private SharedPreferences zzt;
    private String zzu;
    private boolean zzv;
    private long zzw;

    zzfa(zzfv zzfv) {
        super(zzfv);
    }

    /* access modifiers changed from: protected */
    public final SharedPreferences zza() {
        zzg();
        zzu();
        Preconditions.checkNotNull(this.zzt);
        return this.zzt;
    }

    /* access modifiers changed from: protected */
    @EnsuresNonNull.List({@EnsuresNonNull({"this.preferences"}), @EnsuresNonNull({"this.monitoringSample"})})
    public final void zzaA() {
        SharedPreferences sharedPreferences = this.zzs.zzau().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzt = sharedPreferences;
        boolean z = sharedPreferences.getBoolean("has_been_opened", false);
        this.zzk = z;
        if (!z) {
            SharedPreferences.Editor edit = this.zzt.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
        this.zzs.zzf();
        this.zzb = new zzey(this, "health_monitor", Math.max(0, zzdy.zzb.zza(null).longValue()), (zzex) null);
    }

    /* access modifiers changed from: package-private */
    public final Pair<String, Boolean> zzb(String str) {
        zzg();
        long elapsedRealtime = this.zzs.zzav().elapsedRealtime();
        String str2 = this.zzu;
        if (str2 != null && elapsedRealtime < this.zzw) {
            return new Pair<>(str2, Boolean.valueOf(this.zzv));
        }
        this.zzw = elapsedRealtime + this.zzs.zzf().zzi(str, zzdy.zza);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.zzs.zzau());
            this.zzu = "";
            String id = advertisingIdInfo.getId();
            if (id != null) {
                this.zzu = id;
            }
            this.zzv = advertisingIdInfo.isLimitAdTrackingEnabled();
        } catch (Exception e) {
            this.zzs.zzay().zzc().zzb("Unable to get advertising id", e);
            this.zzu = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.zzu, Boolean.valueOf(this.zzv));
    }

    /* access modifiers changed from: package-private */
    public final zzag zzc() {
        zzg();
        return zzag.zzb(zza().getString("consent_settings", "G1"));
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzd() {
        zzg();
        if (zza().contains("measurement_enabled")) {
            return Boolean.valueOf(zza().getBoolean("measurement_enabled", true));
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final boolean zzf() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public final void zzh(Boolean bool) {
        zzg();
        SharedPreferences.Editor edit = zza().edit();
        if (bool != null) {
            edit.putBoolean("measurement_enabled", bool.booleanValue());
        } else {
            edit.remove("measurement_enabled");
        }
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final void zzi(boolean z) {
        zzg();
        this.zzs.zzay().zzj().zzb("App measurement setting deferred collection", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zza().edit();
        edit.putBoolean("deferred_analytics_collection", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzj() {
        SharedPreferences sharedPreferences = this.zzt;
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.contains("deferred_analytics_collection");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzk(long j) {
        return j - this.zzf.zza() > this.zzj.zza();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzl(int i) {
        return zzag.zzl(i, zza().getInt("consent_source", 100));
    }
}
