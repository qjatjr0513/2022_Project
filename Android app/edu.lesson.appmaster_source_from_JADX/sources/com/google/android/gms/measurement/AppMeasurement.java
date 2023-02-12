package com.google.android.gms.measurement;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzfv;
import com.google.android.gms.measurement.internal.zzgr;
import com.google.android.gms.measurement.internal.zzgv;
import com.google.android.gms.measurement.internal.zzgw;
import com.google.android.gms.measurement.internal.zzib;
import com.google.android.gms.measurement.internal.zzig;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Deprecated
/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public class AppMeasurement {
    public static final String CRASH_ORIGIN = "crash";
    public static final String FCM_ORIGIN = "fcm";
    public static final String FIAM_ORIGIN = "fiam";
    private static volatile AppMeasurement zza;
    private final zzd zzb;

    /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
    public interface EventInterceptor extends zzgv {
        void interceptEvent(String str, String str2, Bundle bundle, long j);
    }

    /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
    public interface OnEventListener extends zzgw {
        void onEvent(String str, String str2, Bundle bundle, long j);
    }

    public AppMeasurement(zzfv zzfv) {
        this.zzb = new zza(zzfv);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0038 A[SYNTHETIC, Splitter:B:17:0x0038] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0040  */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.measurement.AppMeasurement getInstance(android.content.Context r14) {
        /*
            com.google.android.gms.measurement.AppMeasurement r0 = zza
            if (r0 != 0) goto L_0x0060
            java.lang.Class<com.google.android.gms.measurement.AppMeasurement> r0 = com.google.android.gms.measurement.AppMeasurement.class
            monitor-enter(r0)
            com.google.android.gms.measurement.AppMeasurement r1 = zza     // Catch:{ all -> 0x005d }
            if (r1 != 0) goto L_0x005b
            r1 = 0
            java.lang.String r2 = "com.google.firebase.analytics.FirebaseAnalytics"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ ClassNotFoundException -> 0x0034 }
            r3 = 2
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ Exception -> 0x0032 }
            java.lang.Class<android.content.Context> r5 = android.content.Context.class
            r6 = 0
            r4[r6] = r5     // Catch:{ Exception -> 0x0032 }
            java.lang.Class<android.os.Bundle> r5 = android.os.Bundle.class
            r7 = 1
            r4[r7] = r5     // Catch:{ Exception -> 0x0032 }
            java.lang.String r5 = "getScionFrontendApiImplementation"
            java.lang.reflect.Method r2 = r2.getDeclaredMethod(r5, r4)     // Catch:{ Exception -> 0x0032 }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x0032 }
            r3[r6] = r14     // Catch:{ Exception -> 0x0032 }
            r3[r7] = r1     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r2 = r2.invoke(r1, r3)     // Catch:{ Exception -> 0x0032 }
            com.google.android.gms.measurement.internal.zzib r2 = (com.google.android.gms.measurement.internal.zzib) r2     // Catch:{ Exception -> 0x0032 }
            goto L_0x0036
        L_0x0032:
            r2 = move-exception
            goto L_0x0035
        L_0x0034:
            r2 = move-exception
        L_0x0035:
            r2 = r1
        L_0x0036:
            if (r2 == 0) goto L_0x0040
            com.google.android.gms.measurement.AppMeasurement r14 = new com.google.android.gms.measurement.AppMeasurement     // Catch:{ all -> 0x005d }
            r14.<init>((com.google.android.gms.measurement.internal.zzib) r2)     // Catch:{ all -> 0x005d }
            zza = r14     // Catch:{ all -> 0x005d }
            goto L_0x005b
        L_0x0040:
            com.google.android.gms.internal.measurement.zzcl r13 = new com.google.android.gms.internal.measurement.zzcl     // Catch:{ all -> 0x005d }
            r3 = 0
            r5 = 0
            r7 = 1
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r2 = r13
            r2.<init>(r3, r5, r7, r8, r9, r10, r11, r12)     // Catch:{ all -> 0x005d }
            com.google.android.gms.measurement.internal.zzfv r14 = com.google.android.gms.measurement.internal.zzfv.zzp(r14, r13, r1)     // Catch:{ all -> 0x005d }
            com.google.android.gms.measurement.AppMeasurement r1 = new com.google.android.gms.measurement.AppMeasurement     // Catch:{ all -> 0x005d }
            r1.<init>((com.google.android.gms.measurement.internal.zzfv) r14)     // Catch:{ all -> 0x005d }
            zza = r1     // Catch:{ all -> 0x005d }
        L_0x005b:
            monitor-exit(r0)     // Catch:{ all -> 0x005d }
            goto L_0x0060
        L_0x005d:
            r14 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005d }
            throw r14
        L_0x0060:
            com.google.android.gms.measurement.AppMeasurement r14 = zza
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.AppMeasurement.getInstance(android.content.Context):com.google.android.gms.measurement.AppMeasurement");
    }

    public void beginAdUnitExposure(String adUnitId) {
        this.zzb.zzp(adUnitId);
    }

    public void clearConditionalUserProperty(String userPropertyName, String clearEventName, Bundle clearEventParams) {
        this.zzb.zzq(userPropertyName, clearEventName, clearEventParams);
    }

    public void endAdUnitExposure(String adUnitId) {
        this.zzb.zzr(adUnitId);
    }

    public long generateEventId() {
        return this.zzb.zzb();
    }

    public String getAppInstanceId() {
        return this.zzb.zzh();
    }

    public Boolean getBoolean() {
        return this.zzb.zzc();
    }

    public List<ConditionalUserProperty> getConditionalUserProperties(String origin, String propertyNamePrefix) {
        int i;
        List<Bundle> zzm = this.zzb.zzm(origin, propertyNamePrefix);
        if (zzm == null) {
            i = 0;
        } else {
            i = zzm.size();
        }
        ArrayList arrayList = new ArrayList(i);
        for (Bundle conditionalUserProperty : zzm) {
            arrayList.add(new ConditionalUserProperty(conditionalUserProperty));
        }
        return arrayList;
    }

    public String getCurrentScreenClass() {
        return this.zzb.zzi();
    }

    public String getCurrentScreenName() {
        return this.zzb.zzj();
    }

    public Double getDouble() {
        return this.zzb.zzd();
    }

    public String getGmpAppId() {
        return this.zzb.zzk();
    }

    public Integer getInteger() {
        return this.zzb.zze();
    }

    public Long getLong() {
        return this.zzb.zzf();
    }

    public int getMaxUserProperties(String origin) {
        return this.zzb.zza(origin);
    }

    public String getString() {
        return this.zzb.zzl();
    }

    /* access modifiers changed from: protected */
    public Map<String, Object> getUserProperties(String origin, String propertyNamePrefix, boolean includeInternal) {
        return this.zzb.zzo(origin, propertyNamePrefix, includeInternal);
    }

    public void logEventInternal(String origin, String name, Bundle params) {
        this.zzb.zzs(origin, name, params);
    }

    public void logEventInternalNoInterceptor(String origin, String name, Bundle params, long timestampInMillis) {
        this.zzb.zzt(origin, name, params, timestampInMillis);
    }

    public void registerOnMeasurementEventListener(OnEventListener listener) {
        this.zzb.zzu(listener);
    }

    public void setConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        zzd zzd = this.zzb;
        Bundle bundle = new Bundle();
        String str = conditionalUserProperty.mAppId;
        if (str != null) {
            bundle.putString("app_id", str);
        }
        String str2 = conditionalUserProperty.mOrigin;
        if (str2 != null) {
            bundle.putString("origin", str2);
        }
        String str3 = conditionalUserProperty.mName;
        if (str3 != null) {
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, str3);
        }
        Object obj = conditionalUserProperty.mValue;
        if (obj != null) {
            zzgr.zzb(bundle, obj);
        }
        String str4 = conditionalUserProperty.mTriggerEventName;
        if (str4 != null) {
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, str4);
        }
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, conditionalUserProperty.mTriggerTimeout);
        String str5 = conditionalUserProperty.mTimedOutEventName;
        if (str5 != null) {
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, str5);
        }
        Bundle bundle2 = conditionalUserProperty.mTimedOutEventParams;
        if (bundle2 != null) {
            bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, bundle2);
        }
        String str6 = conditionalUserProperty.mTriggeredEventName;
        if (str6 != null) {
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, str6);
        }
        Bundle bundle3 = conditionalUserProperty.mTriggeredEventParams;
        if (bundle3 != null) {
            bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, bundle3);
        }
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, conditionalUserProperty.mTimeToLive);
        String str7 = conditionalUserProperty.mExpiredEventName;
        if (str7 != null) {
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str7);
        }
        Bundle bundle4 = conditionalUserProperty.mExpiredEventParams;
        if (bundle4 != null) {
            bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle4);
        }
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, conditionalUserProperty.mCreationTimestamp);
        bundle.putBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, conditionalUserProperty.mActive);
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, conditionalUserProperty.mTriggeredTimestamp);
        zzd.zzv(bundle);
    }

    public void setEventInterceptor(EventInterceptor interceptor) {
        this.zzb.zzw(interceptor);
    }

    public void unregisterOnMeasurementEventListener(OnEventListener listener) {
        this.zzb.zzx(listener);
    }

    public AppMeasurement(zzib zzib) {
        this.zzb = new zzb(zzib);
    }

    public Map<String, Object> getUserProperties(boolean includeInternal) {
        return this.zzb.zzn(includeInternal);
    }

    /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
    public static class ConditionalUserProperty {
        public boolean mActive;
        public String mAppId;
        public long mCreationTimestamp;
        public String mExpiredEventName;
        public Bundle mExpiredEventParams;
        public String mName;
        public String mOrigin;
        public long mTimeToLive;
        public String mTimedOutEventName;
        public Bundle mTimedOutEventParams;
        public String mTriggerEventName;
        public long mTriggerTimeout;
        public String mTriggeredEventName;
        public Bundle mTriggeredEventParams;
        public long mTriggeredTimestamp;
        public Object mValue;

        public ConditionalUserProperty() {
        }

        ConditionalUserProperty(Bundle bundle) {
            Preconditions.checkNotNull(bundle);
            this.mAppId = (String) zzgr.zza(bundle, "app_id", String.class, null);
            this.mOrigin = (String) zzgr.zza(bundle, "origin", String.class, null);
            this.mName = (String) zzgr.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.NAME, String.class, null);
            this.mValue = zzgr.zza(bundle, "value", Object.class, null);
            this.mTriggerEventName = (String) zzgr.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, String.class, null);
            this.mTriggerTimeout = ((Long) zzgr.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.class, 0L)).longValue();
            this.mTimedOutEventName = (String) zzgr.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, String.class, null);
            this.mTimedOutEventParams = (Bundle) zzgr.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, Bundle.class, null);
            this.mTriggeredEventName = (String) zzgr.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, String.class, null);
            this.mTriggeredEventParams = (Bundle) zzgr.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, Bundle.class, null);
            this.mTimeToLive = ((Long) zzgr.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.class, 0L)).longValue();
            this.mExpiredEventName = (String) zzgr.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, String.class, null);
            this.mExpiredEventParams = (Bundle) zzgr.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, Bundle.class, null);
            this.mActive = ((Boolean) zzgr.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.class, false)).booleanValue();
            this.mCreationTimestamp = ((Long) zzgr.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.class, 0L)).longValue();
            this.mTriggeredTimestamp = ((Long) zzgr.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.class, 0L)).longValue();
        }

        public ConditionalUserProperty(ConditionalUserProperty other) {
            Preconditions.checkNotNull(other);
            this.mAppId = other.mAppId;
            this.mOrigin = other.mOrigin;
            this.mCreationTimestamp = other.mCreationTimestamp;
            this.mName = other.mName;
            Object obj = other.mValue;
            if (obj != null) {
                Object zza = zzig.zza(obj);
                this.mValue = zza;
                if (zza == null) {
                    this.mValue = other.mValue;
                }
            }
            this.mActive = other.mActive;
            this.mTriggerEventName = other.mTriggerEventName;
            this.mTriggerTimeout = other.mTriggerTimeout;
            this.mTimedOutEventName = other.mTimedOutEventName;
            Bundle bundle = other.mTimedOutEventParams;
            if (bundle != null) {
                this.mTimedOutEventParams = new Bundle(bundle);
            }
            this.mTriggeredEventName = other.mTriggeredEventName;
            Bundle bundle2 = other.mTriggeredEventParams;
            if (bundle2 != null) {
                this.mTriggeredEventParams = new Bundle(bundle2);
            }
            this.mTriggeredTimestamp = other.mTriggeredTimestamp;
            this.mTimeToLive = other.mTimeToLive;
            this.mExpiredEventName = other.mExpiredEventName;
            Bundle bundle3 = other.mExpiredEventParams;
            if (bundle3 != null) {
                this.mExpiredEventParams = new Bundle(bundle3);
            }
        }
    }
}
