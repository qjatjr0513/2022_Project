package com.google.android.gms.measurement.api;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzee;
import com.google.android.gms.measurement.internal.zzgv;
import com.google.android.gms.measurement.internal.zzgw;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.0.0 */
public class AppMeasurementSdk {
    private final zzee zza;

    /* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.0.0 */
    public static final class ConditionalUserProperty {
        public static final String ACTIVE = "active";
        public static final String CREATION_TIMESTAMP = "creation_timestamp";
        public static final String EXPIRED_EVENT_NAME = "expired_event_name";
        public static final String EXPIRED_EVENT_PARAMS = "expired_event_params";
        public static final String NAME = "name";
        public static final String ORIGIN = "origin";
        public static final String TIMED_OUT_EVENT_NAME = "timed_out_event_name";
        public static final String TIMED_OUT_EVENT_PARAMS = "timed_out_event_params";
        public static final String TIME_TO_LIVE = "time_to_live";
        public static final String TRIGGERED_EVENT_NAME = "triggered_event_name";
        public static final String TRIGGERED_EVENT_PARAMS = "triggered_event_params";
        public static final String TRIGGERED_TIMESTAMP = "triggered_timestamp";
        public static final String TRIGGER_EVENT_NAME = "trigger_event_name";
        public static final String TRIGGER_TIMEOUT = "trigger_timeout";
        public static final String VALUE = "value";

        private ConditionalUserProperty() {
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.0.0 */
    public interface EventInterceptor extends zzgv {
        void interceptEvent(String str, String str2, Bundle bundle, long j);
    }

    /* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.0.0 */
    public interface OnEventListener extends zzgw {
        void onEvent(String str, String str2, Bundle bundle, long j);
    }

    public AppMeasurementSdk(zzee zzee) {
        this.zza = zzee;
    }

    public static AppMeasurementSdk getInstance(Context context) {
        return zzee.zzg(context, (String) null, (String) null, (String) null, (Bundle) null).zzd();
    }

    public void beginAdUnitExposure(String adUnitId) {
        this.zza.zzu(adUnitId);
    }

    public void clearConditionalUserProperty(String userPropertyName, String clearEventName, Bundle clearEventParams) {
        this.zza.zzv(userPropertyName, clearEventName, clearEventParams);
    }

    public void endAdUnitExposure(String adUnitId) {
        this.zza.zzw(adUnitId);
    }

    public long generateEventId() {
        return this.zza.zzb();
    }

    public String getAppIdOrigin() {
        return this.zza.zzj();
    }

    public String getAppInstanceId() {
        return this.zza.zzl();
    }

    public List<Bundle> getConditionalUserProperties(String origin, String propertyNamePrefix) {
        return this.zza.zzp(origin, propertyNamePrefix);
    }

    public String getCurrentScreenClass() {
        return this.zza.zzm();
    }

    public String getCurrentScreenName() {
        return this.zza.zzn();
    }

    public String getGmpAppId() {
        return this.zza.zzo();
    }

    public int getMaxUserProperties(String origin) {
        return this.zza.zza(origin);
    }

    public Map<String, Object> getUserProperties(String origin, String propertyNamePrefix, boolean includeInternal) {
        return this.zza.zzq(origin, propertyNamePrefix, includeInternal);
    }

    public void logEvent(String origin, String name, Bundle params) {
        this.zza.zzy(origin, name, params);
    }

    public void logEventNoInterceptor(String origin, String name, Bundle params, long timestampInMillis) {
        this.zza.zzz(origin, name, params, timestampInMillis);
    }

    public void performAction(Bundle bundle) {
        this.zza.zzc(bundle, false);
    }

    public Bundle performActionWithResponse(Bundle bundle) {
        return this.zza.zzc(bundle, true);
    }

    public void registerOnMeasurementEventListener(OnEventListener listener) {
        this.zza.zzB(listener);
    }

    public void setConditionalUserProperty(Bundle conditionalUserProperty) {
        this.zza.zzD(conditionalUserProperty);
    }

    public void setConsent(Bundle consentMap) {
        this.zza.zzE(consentMap);
    }

    public void setCurrentScreen(Activity activity, String screenName, String screenClassOverride) {
        this.zza.zzG(activity, screenName, screenClassOverride);
    }

    public void setEventInterceptor(EventInterceptor interceptor) {
        this.zza.zzJ(interceptor);
    }

    public void setMeasurementEnabled(Boolean enabled) {
        this.zza.zzK(enabled);
    }

    public void setUserProperty(String origin, String name, Object value) {
        this.zza.zzN(origin, name, value, true);
    }

    public void unregisterOnMeasurementEventListener(OnEventListener listener) {
        this.zza.zzO(listener);
    }

    public final void zza(boolean z) {
        this.zza.zzH(z);
    }

    public void setMeasurementEnabled(boolean enabled) {
        this.zza.zzK(Boolean.valueOf(enabled));
    }

    public static AppMeasurementSdk getInstance(Context context, String logTag, String origin, String customAppId, Bundle extraParameters) {
        return zzee.zzg(context, logTag, origin, customAppId, extraParameters).zzd();
    }
}
