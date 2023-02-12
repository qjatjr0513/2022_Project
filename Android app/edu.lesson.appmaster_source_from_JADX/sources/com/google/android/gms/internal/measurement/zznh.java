package com.google.android.gms.internal.measurement;

import android.support.p005v4.media.session.PlaybackStateCompat;
import com.google.firebase.firestore.util.ExponentialBackoff;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zznh implements zzng {
    public static final zzhu<Long> zzA;
    public static final zzhu<Long> zzB;
    public static final zzhu<Long> zzC;
    public static final zzhu<Long> zzD;
    public static final zzhu<Long> zzE;
    public static final zzhu<Long> zzF;
    public static final zzhu<Long> zzG;
    public static final zzhu<Long> zzH;
    public static final zzhu<Long> zzI;
    public static final zzhu<Long> zzJ;
    public static final zzhu<String> zzK;
    public static final zzhu<Long> zzL;
    public static final zzhu<Long> zza;
    public static final zzhu<Long> zzb;
    public static final zzhu<Long> zzc;
    public static final zzhu<String> zzd;
    public static final zzhu<String> zze;
    public static final zzhu<String> zzf;
    public static final zzhu<Long> zzg;
    public static final zzhu<Long> zzh;
    public static final zzhu<Long> zzi;
    public static final zzhu<Long> zzj;
    public static final zzhu<Long> zzk;
    public static final zzhu<Long> zzl;
    public static final zzhu<Long> zzm;
    public static final zzhu<Long> zzn;
    public static final zzhu<Long> zzo;
    public static final zzhu<Long> zzp;
    public static final zzhu<Long> zzq;
    public static final zzhu<Long> zzr;
    public static final zzhu<String> zzs;
    public static final zzhu<Long> zzt;
    public static final zzhu<Long> zzu;
    public static final zzhu<Long> zzv;
    public static final zzhu<Long> zzw;
    public static final zzhu<Long> zzx;
    public static final zzhu<Long> zzy;
    public static final zzhu<Long> zzz;

    static {
        zzhr zzhr = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhr.zzc("measurement.ad_id_cache_time", 10000);
        zzb = zzhr.zzc("measurement.max_bundles_per_iteration", 100);
        zzc = zzhr.zzc("measurement.config.cache_time", 86400000);
        zzd = zzhr.zzd("measurement.log_tag", "FA");
        zze = zzhr.zzd("measurement.config.url_authority", "app-measurement.com");
        zzf = zzhr.zzd("measurement.config.url_scheme", "https");
        zzg = zzhr.zzc("measurement.upload.debug_upload_interval", 1000);
        zzh = zzhr.zzc("measurement.lifetimevalue.max_currency_tracked", 4);
        zzi = zzhr.zzc("measurement.store.max_stored_events_per_app", 100000);
        zzj = zzhr.zzc("measurement.experiment.max_ids", 50);
        zzk = zzhr.zzc("measurement.audience.filter_result_max_count", 200);
        zzl = zzhr.zzc("measurement.alarm_manager.minimum_interval", ExponentialBackoff.DEFAULT_BACKOFF_MAX_DELAY_MS);
        zzm = zzhr.zzc("measurement.upload.minimum_delay", 500);
        zzn = zzhr.zzc("measurement.monitoring.sample_period_millis", 86400000);
        zzo = zzhr.zzc("measurement.upload.realtime_upload_interval", 10000);
        zzp = zzhr.zzc("measurement.upload.refresh_blacklisted_config_interval", 604800000);
        zzq = zzhr.zzc("measurement.config.cache_time.service", 3600000);
        zzr = zzhr.zzc("measurement.service_client.idle_disconnect_millis", 5000);
        zzs = zzhr.zzd("measurement.log_tag.service", "FA-SVC");
        zzt = zzhr.zzc("measurement.upload.stale_data_deletion_interval", 86400000);
        zzu = zzhr.zzc("measurement.sdk.attribution.cache.ttl", 604800000);
        zzv = zzhr.zzc("measurement.upload.backoff_period", 43200000);
        zzw = zzhr.zzc("measurement.upload.initial_upload_delay_time", 15000);
        zzx = zzhr.zzc("measurement.upload.interval", 3600000);
        zzy = zzhr.zzc("measurement.upload.max_bundle_size", PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH);
        zzz = zzhr.zzc("measurement.upload.max_bundles", 100);
        zzA = zzhr.zzc("measurement.upload.max_conversions_per_day", 500);
        zzB = zzhr.zzc("measurement.upload.max_error_events_per_day", 1000);
        zzC = zzhr.zzc("measurement.upload.max_events_per_bundle", 1000);
        zzD = zzhr.zzc("measurement.upload.max_events_per_day", 100000);
        zzE = zzhr.zzc("measurement.upload.max_public_events_per_day", 50000);
        zzF = zzhr.zzc("measurement.upload.max_queue_time", 2419200000L);
        zzG = zzhr.zzc("measurement.upload.max_realtime_events_per_day", 10);
        zzH = zzhr.zzc("measurement.upload.max_batch_size", PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH);
        zzI = zzhr.zzc("measurement.upload.retry_count", 6);
        zzJ = zzhr.zzc("measurement.upload.retry_time", 1800000);
        zzK = zzhr.zzd("measurement.upload.url", "https://app-measurement.com/a");
        zzL = zzhr.zzc("measurement.upload.window_interval", 3600000);
    }

    public final long zzA() {
        return zzF.zzb().longValue();
    }

    public final long zzB() {
        return zzG.zzb().longValue();
    }

    public final long zzC() {
        return zzH.zzb().longValue();
    }

    public final long zzD() {
        return zzI.zzb().longValue();
    }

    public final long zzE() {
        return zzJ.zzb().longValue();
    }

    public final long zzF() {
        return zzL.zzb().longValue();
    }

    public final String zzG() {
        return zze.zzb();
    }

    public final String zzH() {
        return zzf.zzb();
    }

    public final String zzI() {
        return zzK.zzb();
    }

    public final long zza() {
        return zza.zzb().longValue();
    }

    public final long zzb() {
        return zzb.zzb().longValue();
    }

    public final long zzc() {
        return zzc.zzb().longValue();
    }

    public final long zzd() {
        return zzg.zzb().longValue();
    }

    public final long zze() {
        return zzh.zzb().longValue();
    }

    public final long zzf() {
        return zzi.zzb().longValue();
    }

    public final long zzg() {
        return zzj.zzb().longValue();
    }

    public final long zzh() {
        return zzk.zzb().longValue();
    }

    public final long zzi() {
        return zzl.zzb().longValue();
    }

    public final long zzj() {
        return zzm.zzb().longValue();
    }

    public final long zzk() {
        return zzn.zzb().longValue();
    }

    public final long zzl() {
        return zzo.zzb().longValue();
    }

    public final long zzm() {
        return zzp.zzb().longValue();
    }

    public final long zzn() {
        return zzr.zzb().longValue();
    }

    public final long zzo() {
        return zzt.zzb().longValue();
    }

    public final long zzp() {
        return zzu.zzb().longValue();
    }

    public final long zzq() {
        return zzv.zzb().longValue();
    }

    public final long zzr() {
        return zzw.zzb().longValue();
    }

    public final long zzs() {
        return zzx.zzb().longValue();
    }

    public final long zzt() {
        return zzy.zzb().longValue();
    }

    public final long zzu() {
        return zzz.zzb().longValue();
    }

    public final long zzv() {
        return zzA.zzb().longValue();
    }

    public final long zzw() {
        return zzB.zzb().longValue();
    }

    public final long zzx() {
        return zzC.zzb().longValue();
    }

    public final long zzy() {
        return zzD.zzb().longValue();
    }

    public final long zzz() {
        return zzE.zzb().longValue();
    }
}
