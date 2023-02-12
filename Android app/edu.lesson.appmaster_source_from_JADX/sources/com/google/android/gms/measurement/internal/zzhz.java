package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzhz implements Application.ActivityLifecycleCallbacks {
    final /* synthetic */ zzia zza;

    /* synthetic */ zzhz(zzia zzia, zzhy zzhy) {
        this.zza = zzia;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zzfv zzfv;
        try {
            this.zza.zzs.zzay().zzj().zza("onActivityCreated");
            Intent intent = activity.getIntent();
            if (intent == null) {
                zzfv = this.zza.zzs;
            } else {
                Uri data = intent.getData();
                if (data != null) {
                    if (data.isHierarchical()) {
                        this.zza.zzs.zzv();
                        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
                        boolean z = true;
                        String str = true != (("android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra)) ? true : "android-app://com.google.appcrawler".equals(stringExtra)) ? "auto" : "gs";
                        String queryParameter = data.getQueryParameter("referrer");
                        if (bundle != null) {
                            z = false;
                        }
                        this.zza.zzs.zzaz().zzp(new zzhx(this, z, data, str, queryParameter));
                        zzfv = this.zza.zzs;
                    }
                }
                zzfv = this.zza.zzs;
            }
        } catch (RuntimeException e) {
            this.zza.zzs.zzay().zzd().zzb("Throwable caught in onActivityCreated", e);
            zzfv = this.zza.zzs;
        } catch (Throwable th) {
            this.zza.zzs.zzs().zzr(activity, bundle);
            throw th;
        }
        zzfv.zzs().zzr(activity, bundle);
    }

    public final void onActivityDestroyed(Activity activity) {
        this.zza.zzs.zzs().zzs(activity);
    }

    public final void onActivityPaused(Activity activity) {
        this.zza.zzs.zzs().zzt(activity);
        zzkd zzu = this.zza.zzs.zzu();
        zzu.zzs.zzaz().zzp(new zzjw(zzu, zzu.zzs.zzav().elapsedRealtime()));
    }

    public final void onActivityResumed(Activity activity) {
        zzkd zzu = this.zza.zzs.zzu();
        zzu.zzs.zzaz().zzp(new zzjv(zzu, zzu.zzs.zzav().elapsedRealtime()));
        this.zza.zzs.zzs().zzu(activity);
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.zza.zzs.zzs().zzv(activity, bundle);
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }
}
