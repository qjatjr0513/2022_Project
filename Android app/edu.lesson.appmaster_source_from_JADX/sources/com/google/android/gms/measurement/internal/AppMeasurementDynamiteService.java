package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzcb;
import com.google.android.gms.internal.measurement.zzcf;
import com.google.android.gms.internal.measurement.zzci;
import com.google.android.gms.internal.measurement.zzck;
import com.google.android.gms.internal.measurement.zzcl;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* compiled from: com.google.android.gms:play-services-measurement-sdk@@20.0.0 */
public class AppMeasurementDynamiteService extends zzcb {
    zzfv zza = null;
    private final Map<Integer, zzgw> zzb = new ArrayMap();

    @EnsuresNonNull({"scion"})
    private final void zzb() {
        if (this.zza == null) {
            throw new IllegalStateException("Attempting to perform action before initialize.");
        }
    }

    private final void zzc(zzcf zzcf, String str) {
        zzb();
        this.zza.zzv().zzU(zzcf, str);
    }

    public void beginAdUnitExposure(String adUnitId, long timestamp) throws RemoteException {
        zzb();
        this.zza.zzd().zzd(adUnitId, timestamp);
    }

    public void clearConditionalUserProperty(String userPropertyName, String clearEventName, Bundle clearEventParams) throws RemoteException {
        zzb();
        this.zza.zzq().zzz(userPropertyName, clearEventName, clearEventParams);
    }

    public void clearMeasurementEnabled(long j) throws RemoteException {
        zzb();
        this.zza.zzq().zzV((Boolean) null);
    }

    public void endAdUnitExposure(String adUnitId, long timestamp) throws RemoteException {
        zzb();
        this.zza.zzd().zze(adUnitId, timestamp);
    }

    public void generateEventId(zzcf receiver) throws RemoteException {
        zzb();
        long zzq = this.zza.zzv().zzq();
        zzb();
        this.zza.zzv().zzT(receiver, zzq);
    }

    public void getAppInstanceId(zzcf receiver) throws RemoteException {
        zzb();
        this.zza.zzaz().zzp(new zzh(this, receiver));
    }

    public void getCachedAppInstanceId(zzcf receiver) throws RemoteException {
        zzb();
        zzc(receiver, this.zza.zzq().zzo());
    }

    public void getConditionalUserProperties(String origin, String propertyNamePrefix, zzcf receiver) throws RemoteException {
        zzb();
        this.zza.zzaz().zzp(new zzl(this, receiver, origin, propertyNamePrefix));
    }

    public void getCurrentScreenClass(zzcf receiver) throws RemoteException {
        zzb();
        zzc(receiver, this.zza.zzq().zzp());
    }

    public void getCurrentScreenName(zzcf receiver) throws RemoteException {
        zzb();
        zzc(receiver, this.zza.zzq().zzq());
    }

    public void getGmpAppId(zzcf receiver) throws RemoteException {
        String str;
        zzb();
        zzia zzq = this.zza.zzq();
        if (zzq.zzs.zzw() != null) {
            str = zzq.zzs.zzw();
        } else {
            try {
                str = zzig.zzc(zzq.zzs.zzau(), "google_app_id", zzq.zzs.zzz());
            } catch (IllegalStateException e) {
                zzq.zzs.zzay().zzd().zzb("getGoogleAppId failed with exception", e);
                str = null;
            }
        }
        zzc(receiver, str);
    }

    public void getMaxUserProperties(String origin, zzcf receiver) throws RemoteException {
        zzb();
        this.zza.zzq().zzh(origin);
        zzb();
        this.zza.zzv().zzS(receiver, 25);
    }

    public void getTestFlag(zzcf receiver, int type) throws RemoteException {
        zzb();
        switch (type) {
            case 0:
                this.zza.zzv().zzU(receiver, this.zza.zzq().zzr());
                return;
            case 1:
                this.zza.zzv().zzT(receiver, this.zza.zzq().zzm().longValue());
                return;
            case 2:
                zzkz zzv = this.zza.zzv();
                double doubleValue = this.zza.zzq().zzj().doubleValue();
                Bundle bundle = new Bundle();
                bundle.putDouble("r", doubleValue);
                try {
                    receiver.zzd(bundle);
                    return;
                } catch (RemoteException e) {
                    zzv.zzs.zzay().zzk().zzb("Error returning double value to wrapper", e);
                    return;
                }
            case 3:
                this.zza.zzv().zzS(receiver, this.zza.zzq().zzl().intValue());
                return;
            case 4:
                this.zza.zzv().zzO(receiver, this.zza.zzq().zzi().booleanValue());
                return;
            default:
                return;
        }
    }

    public void getUserProperties(String origin, String propertyNamePrefix, boolean getInternal, zzcf receiver) throws RemoteException {
        zzb();
        this.zza.zzaz().zzp(new zzj(this, receiver, origin, propertyNamePrefix, getInternal));
    }

    public void initForTests(Map map) throws RemoteException {
        zzb();
    }

    public void initialize(IObjectWrapper context, zzcl params, long timestamp) throws RemoteException {
        zzfv zzfv = this.zza;
        if (zzfv == null) {
            this.zza = zzfv.zzp((Context) Preconditions.checkNotNull((Context) ObjectWrapper.unwrap(context)), params, Long.valueOf(timestamp));
        } else {
            zzfv.zzay().zzk().zza("Attempting to initialize multiple times");
        }
    }

    public void isDataCollectionEnabled(zzcf receiver) throws RemoteException {
        zzb();
        this.zza.zzaz().zzp(new zzm(this, receiver));
    }

    /* Debug info: failed to restart local var, previous not found, register: 10 */
    public void logEvent(String origin, String name, Bundle params, boolean isInternal, boolean allowInterceptor, long timestamp) throws RemoteException {
        zzb();
        this.zza.zzq().zzE(origin, name, params, isInternal, allowInterceptor, timestamp);
    }

    public void logEventAndBundle(String packageName, String eventName, Bundle params, zzcf receiver, long timestamp) throws RemoteException {
        zzb();
        Preconditions.checkNotEmpty(eventName);
        (params != null ? new Bundle(params) : new Bundle()).putString("_o", "app");
        this.zza.zzaz().zzp(new zzi(this, receiver, new zzat(eventName, new zzar(params), "app", timestamp), packageName));
    }

    public void logHealthData(int priority, String key, IObjectWrapper context1, IObjectWrapper context2, IObjectWrapper context3) throws RemoteException {
        Object obj;
        Object obj2;
        Object obj3;
        zzb();
        if (context1 == null) {
            obj = null;
        } else {
            obj = ObjectWrapper.unwrap(context1);
        }
        if (context2 == null) {
            obj2 = null;
        } else {
            obj2 = ObjectWrapper.unwrap(context2);
        }
        if (context3 == null) {
            obj3 = null;
        } else {
            obj3 = ObjectWrapper.unwrap(context3);
        }
        this.zza.zzay().zzt(priority, true, false, key, obj, obj2, obj3);
    }

    public void onActivityCreated(IObjectWrapper activity, Bundle savedInstanceState, long j) throws RemoteException {
        zzb();
        zzhz zzhz = this.zza.zzq().zza;
        if (zzhz != null) {
            this.zza.zzq().zzA();
            zzhz.onActivityCreated((Activity) ObjectWrapper.unwrap(activity), savedInstanceState);
        }
    }

    public void onActivityDestroyed(IObjectWrapper activity, long j) throws RemoteException {
        zzb();
        zzhz zzhz = this.zza.zzq().zza;
        if (zzhz != null) {
            this.zza.zzq().zzA();
            zzhz.onActivityDestroyed((Activity) ObjectWrapper.unwrap(activity));
        }
    }

    public void onActivityPaused(IObjectWrapper activity, long j) throws RemoteException {
        zzb();
        zzhz zzhz = this.zza.zzq().zza;
        if (zzhz != null) {
            this.zza.zzq().zzA();
            zzhz.onActivityPaused((Activity) ObjectWrapper.unwrap(activity));
        }
    }

    public void onActivityResumed(IObjectWrapper activity, long j) throws RemoteException {
        zzb();
        zzhz zzhz = this.zza.zzq().zza;
        if (zzhz != null) {
            this.zza.zzq().zzA();
            zzhz.onActivityResumed((Activity) ObjectWrapper.unwrap(activity));
        }
    }

    public void onActivitySaveInstanceState(IObjectWrapper activity, zzcf receiver, long j) throws RemoteException {
        zzb();
        zzhz zzhz = this.zza.zzq().zza;
        Bundle bundle = new Bundle();
        if (zzhz != null) {
            this.zza.zzq().zzA();
            zzhz.onActivitySaveInstanceState((Activity) ObjectWrapper.unwrap(activity), bundle);
        }
        try {
            receiver.zzd(bundle);
        } catch (RemoteException e) {
            this.zza.zzay().zzk().zzb("Error returning bundle value to wrapper", e);
        }
    }

    public void onActivityStarted(IObjectWrapper activity, long j) throws RemoteException {
        zzb();
        if (this.zza.zzq().zza != null) {
            this.zza.zzq().zzA();
            Activity activity2 = (Activity) ObjectWrapper.unwrap(activity);
        }
    }

    public void onActivityStopped(IObjectWrapper activity, long j) throws RemoteException {
        zzb();
        if (this.zza.zzq().zza != null) {
            this.zza.zzq().zzA();
            Activity activity2 = (Activity) ObjectWrapper.unwrap(activity);
        }
    }

    public void performAction(Bundle bundle, zzcf receiver, long j) throws RemoteException {
        zzb();
        receiver.zzd((Bundle) null);
    }

    public void registerOnMeasurementEventListener(zzci listenerProxy) throws RemoteException {
        zzgw zzgw;
        zzb();
        synchronized (this.zzb) {
            zzgw = this.zzb.get(Integer.valueOf(listenerProxy.zzd()));
            if (zzgw == null) {
                zzgw = new zzo(this, listenerProxy);
                this.zzb.put(Integer.valueOf(listenerProxy.zzd()), zzgw);
            }
        }
        this.zza.zzq().zzJ(zzgw);
    }

    public void resetAnalyticsData(long timestamp) throws RemoteException {
        zzb();
        this.zza.zzq().zzK(timestamp);
    }

    public void setConditionalUserProperty(Bundle conditionalUserProperty, long timestamp) throws RemoteException {
        zzb();
        if (conditionalUserProperty == null) {
            this.zza.zzay().zzd().zza("Conditional user property must not be null");
        } else {
            this.zza.zzq().zzQ(conditionalUserProperty, timestamp);
        }
    }

    public void setConsent(Bundle consentMap, long timestamp) throws RemoteException {
        zzb();
        this.zza.zzq().zzT(consentMap, timestamp);
    }

    public void setConsentThirdParty(Bundle consentMap, long timestamp) throws RemoteException {
        zzb();
        this.zza.zzq().zzR(consentMap, -20, timestamp);
    }

    public void setCurrentScreen(IObjectWrapper activity, String screenName, String screenClassOverride, long j) throws RemoteException {
        zzb();
        this.zza.zzs().zzw((Activity) ObjectWrapper.unwrap(activity), screenName, screenClassOverride);
    }

    public void setDataCollectionEnabled(boolean enabled) throws RemoteException {
        zzb();
        zzia zzq = this.zza.zzq();
        zzq.zza();
        zzfv zzfv = zzq.zzs;
        zzq.zzs.zzaz().zzp(new zzhc(zzq, enabled));
    }

    public void setDefaultEventParameters(Bundle parameters) {
        Bundle bundle;
        zzb();
        zzia zzq = this.zza.zzq();
        if (parameters == null) {
            bundle = null;
        } else {
            bundle = new Bundle(parameters);
        }
        zzq.zzs.zzaz().zzp(new zzha(zzq, bundle));
    }

    public void setEventInterceptor(zzci interceptor) throws RemoteException {
        zzb();
        zzn zzn = new zzn(this, interceptor);
        if (this.zza.zzaz().zzs()) {
            this.zza.zzq().zzU(zzn);
        } else {
            this.zza.zzaz().zzp(new zzk(this, zzn));
        }
    }

    public void setInstanceIdProvider(zzck zzck) throws RemoteException {
        zzb();
    }

    public void setMeasurementEnabled(boolean enabled, long j) throws RemoteException {
        zzb();
        this.zza.zzq().zzV(Boolean.valueOf(enabled));
    }

    public void setMinimumSessionDuration(long j) throws RemoteException {
        zzb();
    }

    public void setSessionTimeoutDuration(long milliseconds) throws RemoteException {
        zzb();
        zzia zzq = this.zza.zzq();
        zzfv zzfv = zzq.zzs;
        zzq.zzs.zzaz().zzp(new zzhe(zzq, milliseconds));
    }

    public void setUserId(String id, long timestamp) throws RemoteException {
        zzb();
        if (id == null || id.length() != 0) {
            this.zza.zzq().zzY((String) null, "_id", id, true, timestamp);
        } else {
            this.zza.zzay().zzk().zza("User ID must be non-empty");
        }
    }

    public void setUserProperty(String origin, String name, IObjectWrapper value, boolean isInternal, long timestamp) throws RemoteException {
        zzb();
        this.zza.zzq().zzY(origin, name, ObjectWrapper.unwrap(value), isInternal, timestamp);
    }

    public void unregisterOnMeasurementEventListener(zzci listenerProxy) throws RemoteException {
        zzgw remove;
        zzb();
        synchronized (this.zzb) {
            remove = this.zzb.remove(Integer.valueOf(listenerProxy.zzd()));
        }
        if (remove == null) {
            remove = new zzo(this, listenerProxy);
        }
        this.zza.zzq().zzaa(remove);
    }
}
