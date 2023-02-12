package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.internal.measurement.zzha;
import com.google.android.gms.internal.measurement.zzhk;
import com.google.firebase.firestore.util.ExponentialBackoff;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zzdy {
    public static final zzdx<Long> zzA = zza("measurement.upload.retry_time", 1800000L, 1800000L, zzbf.zza);
    public static final zzdx<Integer> zzB = zza("measurement.upload.retry_count", 6, 6, zzbh.zza);
    public static final zzdx<Long> zzC = zza("measurement.upload.max_queue_time", 2419200000L, 2419200000L, zzbi.zza);
    public static final zzdx<Integer> zzD = zza("measurement.lifetimevalue.max_currency_tracked", 4, 4, zzbj.zza);
    public static final zzdx<Integer> zzE = zza("measurement.audience.filter_result_max_count", 200, 200, zzbl.zza);
    public static final zzdx<Integer> zzF = zza("measurement.upload.max_public_user_properties", 25, 25, (zzdu) null);
    public static final zzdx<Integer> zzG = zza("measurement.upload.max_event_name_cardinality", 500, 500, (zzdu) null);
    public static final zzdx<Integer> zzH = zza("measurement.upload.max_public_event_params", 25, 25, (zzdu) null);
    public static final zzdx<Long> zzI = zza("measurement.service_client.idle_disconnect_millis", 5000L, 5000L, zzbm.zza);
    public static final zzdx<Boolean> zzJ = zza("measurement.test.boolean_flag", false, false, zzbn.zza);
    public static final zzdx<String> zzK = zza("measurement.test.string_flag", "---", "---", zzbo.zza);
    public static final zzdx<Long> zzL = zza("measurement.test.long_flag", -1L, -1L, zzbp.zza);
    public static final zzdx<Integer> zzM = zza("measurement.test.int_flag", -2, -2, zzbq.zza);
    public static final zzdx<Double> zzN;
    public static final zzdx<Integer> zzO = zza("measurement.experiment.max_ids", 50, 50, zzbt.zza);
    public static final zzdx<Integer> zzP = zza("measurement.max_bundles_per_iteration", 100, 100, zzbu.zza);
    public static final zzdx<Long> zzQ = zza("measurement.sdk.attribution.cache.ttl", 604800000L, 604800000L, zzbv.zza);
    public static final zzdx<Boolean> zzR = zza("measurement.validation.internal_limits_internal_event_params", false, false, zzbx.zza);
    public static final zzdx<Boolean> zzS = zza("measurement.collection.firebase_global_collection_flag_enabled", true, true, zzby.zza);
    public static final zzdx<Boolean> zzT = zza("measurement.collection.redundant_engagement_removal_enabled", false, false, zzbz.zza);
    public static final zzdx<Boolean> zzU = zza("measurement.collection.log_event_and_bundle_v2", true, true, zzca.zza);
    public static final zzdx<Boolean> zzV = zza("measurement.quality.checksum", false, false, (zzdu) null);
    public static final zzdx<Boolean> zzW = zza("measurement.audience.use_bundle_end_timestamp_for_non_sequence_property_filters", false, false, zzcb.zza);
    public static final zzdx<Boolean> zzX = zza("measurement.audience.refresh_event_count_filters_timestamp", false, false, zzcd.zza);
    public static final zzdx<Boolean> zzY = zza("measurement.audience.use_bundle_timestamp_for_event_count_filters", false, false, zzce.zza);
    public static final zzdx<Boolean> zzZ = zza("measurement.sdk.collection.retrieve_deeplink_from_bow_2", true, true, zzcf.zza);
    public static final zzdx<Long> zza = zza("measurement.ad_id_cache_time", 10000L, 10000L, zzav.zza);
    public static final zzdx<Boolean> zzaA = zza("measurement.service.refactor.package_side_screen", true, true, zzdm.zza);
    public static final zzdx<Boolean> zzaB = zza("measurement.config.fix_feature_flags_from_config", true, true, zzdn.zza);
    public static final zzdx<Boolean> zzaC = zza("measurement.service.event_config_fix", true, true, zzdo.zza);
    /* access modifiers changed from: private */
    public static final List<zzdx<?>> zzaD = Collections.synchronizedList(new ArrayList());
    private static final Set<zzdx<?>> zzaE = Collections.synchronizedSet(new HashSet());
    public static final zzdx<Boolean> zzaa = zza("measurement.sdk.collection.last_deep_link_referrer_campaign2", false, false, zzcg.zza);
    public static final zzdx<Boolean> zzab = zza("measurement.sdk.collection.enable_extend_user_property_size", true, true, zzch.zza);
    public static final zzdx<Boolean> zzac = zza("measurement.ga.ga_app_id", false, false, zzcj.zza);
    public static final zzdx<Boolean> zzad = zza("measurement.lifecycle.app_in_background_parameter", false, false, zzck.zza);
    public static final zzdx<Boolean> zzae = zza("measurement.integration.disable_firebase_instance_id", false, false, zzcl.zza);
    public static final zzdx<Boolean> zzaf = zza("measurement.lifecycle.app_backgrounded_engagement", false, false, zzcm.zza);
    public static final zzdx<Boolean> zzag = zza("measurement.collection.service.update_with_analytics_fix", false, false, zzco.zza);
    public static final zzdx<Boolean> zzah = zza("measurement.client.firebase_feature_rollout.v1.enable", true, true, zzcp.zza);
    public static final zzdx<Boolean> zzai = zza("measurement.client.sessions.check_on_reset_and_enable2", true, true, zzcq.zza);
    public static final zzdx<Boolean> zzaj = zza("measurement.scheduler.task_thread.cleanup_on_exit", false, false, zzcr.zza);
    public static final zzdx<Boolean> zzak = zza("measurement.upload.file_truncate_fix", false, false, zzcs.zza);
    public static final zzdx<Boolean> zzal = zza("measurement.collection.synthetic_data_mitigation", false, false, zzct.zza);
    public static final zzdx<Boolean> zzam = zza("measurement.androidId.delete_feature", true, true, zzcv.zza);
    public static final zzdx<Integer> zzan = zza("measurement.service.storage_consent_support_version", 203600, 203600, zzcw.zza);
    public static final zzdx<Boolean> zzao = zza("measurement.client.click_identifier_control.dev", false, false, zzcx.zza);
    public static final zzdx<Boolean> zzap = zza("measurement.service.click_identifier_control", false, false, zzcz.zza);
    public static final zzdx<Boolean> zzaq = zza("measurement.config.persist_last_modified", true, true, zzda.zza);
    public static final zzdx<Boolean> zzar = zza("measurement.client.consent.suppress_1p_in_ga4f_install", true, true, zzdb.zza);
    public static final zzdx<Boolean> zzas = zza("measurement.client.consent.gmpappid_worker_thread_fix", true, true, zzdc.zza);
    public static final zzdx<Boolean> zzat = zza("measurement.module.pixie.ees", true, true, zzdd.zza);
    public static final zzdx<Boolean> zzau = zza("measurement.module.pixie.fix_array", true, true, zzde.zza);
    public static final zzdx<Boolean> zzav = zza("measurement.adid_zero.service", false, false, zzdf.zza);
    public static final zzdx<Boolean> zzaw = zza("measurement.adid_zero.remove_lair_if_adidzero_false", true, true, zzdh.zza);
    public static final zzdx<Boolean> zzax = zza("measurement.adid_zero.remove_lair_if_userid_cleared", true, true, zzdi.zza);
    public static final zzdx<Boolean> zzay = zza("measurement.adid_zero.adid_uid", false, false, zzdk.zza);
    public static final zzdx<Boolean> zzaz = zza("measurement.adid_zero.app_instance_id_fix", true, true, zzdl.zza);
    public static final zzdx<Long> zzb = zza("measurement.monitoring.sample_period_millis", 86400000L, 86400000L, zzbg.zza);
    public static final zzdx<Long> zzc = zza("measurement.config.cache_time", 86400000L, 3600000L, zzay.zza);
    public static final zzdx<String> zzd = zza("measurement.config.url_scheme", "https", "https", zzbk.zza);
    public static final zzdx<String> zze = zza("measurement.config.url_authority", "app-measurement.com", "app-measurement.com", zzbw.zza);
    public static final zzdx<Integer> zzf = zza("measurement.upload.max_bundles", 100, 100, zzci.zza);
    public static final zzdx<Integer> zzg = zza("measurement.upload.max_batch_size", 65536, 65536, zzcu.zza);
    public static final zzdx<Integer> zzh = zza("measurement.upload.max_bundle_size", 65536, 65536, zzdg.zza);
    public static final zzdx<Integer> zzi = zza("measurement.upload.max_events_per_bundle", 1000, 1000, zzdp.zza);
    public static final zzdx<Integer> zzj = zza("measurement.upload.max_events_per_day", 100000, 100000, zzdq.zza);
    public static final zzdx<Integer> zzk = zza("measurement.upload.max_error_events_per_day", 1000, 1000, zzbr.zza);
    public static final zzdx<Integer> zzl = zza("measurement.upload.max_public_events_per_day", 50000, 50000, zzcc.zza);
    public static final zzdx<Integer> zzm = zza("measurement.upload.max_conversions_per_day", 10000, 10000, zzcn.zza);
    public static final zzdx<Integer> zzn = zza("measurement.upload.max_realtime_events_per_day", 10, 10, zzcy.zza);
    public static final zzdx<Integer> zzo = zza("measurement.store.max_stored_events_per_app", 100000, 100000, zzdj.zza);
    public static final zzdx<String> zzp = zza("measurement.upload.url", "https://app-measurement.com/a", "https://app-measurement.com/a", zzdr.zza);
    public static final zzdx<Long> zzq = zza("measurement.upload.backoff_period", 43200000L, 43200000L, zzds.zza);
    public static final zzdx<Long> zzr = zza("measurement.upload.window_interval", 3600000L, 3600000L, zzdt.zza);
    public static final zzdx<Long> zzs = zza("measurement.upload.interval", 3600000L, 3600000L, zzaw.zza);
    public static final zzdx<Long> zzt = zza("measurement.upload.realtime_upload_interval", 10000L, 10000L, zzax.zza);
    public static final zzdx<Long> zzu = zza("measurement.upload.debug_upload_interval", 1000L, 1000L, zzaz.zza);
    public static final zzdx<Long> zzv = zza("measurement.upload.minimum_delay", 500L, 500L, zzba.zza);
    public static final zzdx<Long> zzw;
    public static final zzdx<Long> zzx = zza("measurement.upload.stale_data_deletion_interval", 86400000L, 86400000L, zzbc.zza);
    public static final zzdx<Long> zzy = zza("measurement.upload.refresh_blacklisted_config_interval", 604800000L, 604800000L, zzbd.zza);
    public static final zzdx<Long> zzz = zza("measurement.upload.initial_upload_delay_time", 15000L, 15000L, zzbe.zza);

    static {
        Long valueOf = Long.valueOf(ExponentialBackoff.DEFAULT_BACKOFF_MAX_DELAY_MS);
        zzw = zza("measurement.alarm_manager.minimum_interval", valueOf, valueOf, zzbb.zza);
        Double valueOf2 = Double.valueOf(-3.0d);
        zzN = zza("measurement.test.double_flag", valueOf2, valueOf2, zzbs.zza);
    }

    static <V> zzdx<V> zza(String str, V v, V v2, zzdu<V> zzdu) {
        zzdx zzdx = new zzdx(str, v, v2, zzdu, (zzdw) null);
        zzaD.add(zzdx);
        return zzdx;
    }

    public static Map<String, String> zzc(Context context) {
        zzha zza2 = zzha.zza(context.getContentResolver(), zzhk.zza("com.google.android.gms.measurement"));
        return zza2 == null ? Collections.emptyMap() : zza2.zzc();
    }
}
