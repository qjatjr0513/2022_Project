package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.os.Binder;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.internal.measurement.zzaa;
import com.google.android.gms.internal.measurement.zzc;
import com.google.android.gms.internal.measurement.zzd;
import com.google.android.gms.internal.measurement.zzpe;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzgn extends zzea {
    /* access modifiers changed from: private */
    public final zzks zza;
    private Boolean zzb;
    private String zzc = null;

    public zzgn(zzks zzks, String str) {
        Preconditions.checkNotNull(zzks);
        this.zza = zzks;
    }

    private final void zzA(String str, boolean z) {
        boolean z2;
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                try {
                    if (this.zzb == null) {
                        if ("com.google.android.gms".equals(this.zzc)) {
                            z2 = true;
                        } else if (!UidVerifier.isGooglePlayServicesUid(this.zza.zzau(), Binder.getCallingUid())) {
                            z2 = GoogleSignatureVerifier.getInstance(this.zza.zzau()).isUidGoogleSigned(Binder.getCallingUid());
                        } else {
                            z2 = true;
                        }
                        this.zzb = Boolean.valueOf(z2);
                    }
                    if (this.zzb.booleanValue()) {
                        return;
                    }
                } catch (SecurityException e) {
                    this.zza.zzay().zzd().zzb("Measurement Service called with invalid calling package. appId", zzel.zzn(str));
                    throw e;
                }
            }
            if (this.zzc == null && GooglePlayServicesUtilLight.uidHasPackageName(this.zza.zzau(), Binder.getCallingUid(), str)) {
                this.zzc = str;
            }
            if (!str.equals(this.zzc)) {
                throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[]{str}));
            }
            return;
        }
        this.zza.zzay().zzd().zza("Measurement Service called without app package");
        throw new SecurityException("Measurement Service called without app package");
    }

    /* access modifiers changed from: private */
    public final void zzB(zzat zzat, zzp zzp) {
        this.zza.zzA();
        this.zza.zzD(zzat, zzp);
    }

    private final void zzz(zzp zzp, boolean z) {
        Preconditions.checkNotNull(zzp);
        Preconditions.checkNotEmpty(zzp.zza);
        zzA(zzp.zza, false);
        this.zza.zzv().zzW(zzp.zzb, zzp.zzq, zzp.zzu);
    }

    /* access modifiers changed from: package-private */
    public final zzat zzb(zzat zzat, zzp zzp) {
        zzar zzar;
        if (!(!Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN.equals(zzat.zza) || (zzar = zzat.zzb) == null || zzar.zza() == 0)) {
            String zzg = zzat.zzb.zzg("_cis");
            if ("referrer broadcast".equals(zzg) || "referrer API".equals(zzg)) {
                this.zza.zzay().zzi().zzb("Event has been filtered ", zzat.toString());
                return new zzat("_cmpx", zzat.zzb, zzat.zzc, zzat.zzd);
            }
        }
        return zzat;
    }

    public final String zzd(zzp zzp) {
        zzz(zzp, false);
        return this.zza.zzx(zzp);
    }

    public final List<zzkv> zze(zzp zzp, boolean z) {
        zzz(zzp, false);
        String str = zzp.zza;
        Preconditions.checkNotNull(str);
        try {
            List<zzkx> list = (List) this.zza.zzaz().zzh(new zzgk(this, str)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzkx zzkx : list) {
                if (z || !zzkz.zzag(zzkx.zzc)) {
                    arrayList.add(new zzkv(zzkx));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzay().zzd().zzc("Failed to get user properties. appId", zzel.zzn(zzp.zza), e);
            return null;
        }
    }

    public final List<zzab> zzf(String str, String str2, zzp zzp) {
        zzz(zzp, false);
        String str3 = zzp.zza;
        Preconditions.checkNotNull(str3);
        try {
            return (List) this.zza.zzaz().zzh(new zzgb(this, str3, str, str2)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzay().zzd().zzb("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    public final List<zzab> zzg(String str, String str2, String str3) {
        zzA(str, true);
        try {
            return (List) this.zza.zzaz().zzh(new zzgc(this, str, str2, str3)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzay().zzd().zzb("Failed to get conditional user properties as", e);
            return Collections.emptyList();
        }
    }

    public final List<zzkv> zzh(String str, String str2, boolean z, zzp zzp) {
        zzz(zzp, false);
        String str3 = zzp.zza;
        Preconditions.checkNotNull(str3);
        try {
            List<zzkx> list = (List) this.zza.zzaz().zzh(new zzfz(this, str3, str, str2)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzkx zzkx : list) {
                if (z || !zzkz.zzag(zzkx.zzc)) {
                    arrayList.add(new zzkv(zzkx));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzay().zzd().zzc("Failed to query user properties. appId", zzel.zzn(zzp.zza), e);
            return Collections.emptyList();
        }
    }

    public final List<zzkv> zzi(String str, String str2, String str3, boolean z) {
        zzA(str, true);
        try {
            List<zzkx> list = (List) this.zza.zzaz().zzh(new zzga(this, str, str2, str3)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzkx zzkx : list) {
                if (z || !zzkz.zzag(zzkx.zzc)) {
                    arrayList.add(new zzkv(zzkx));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzay().zzd().zzc("Failed to get user properties as. appId", zzel.zzn(str), e);
            return Collections.emptyList();
        }
    }

    public final void zzj(zzp zzp) {
        zzz(zzp, false);
        zzy(new zzgl(this, zzp));
    }

    public final void zzk(zzat zzat, zzp zzp) {
        Preconditions.checkNotNull(zzat);
        zzz(zzp, false);
        zzy(new zzgg(this, zzat, zzp));
    }

    public final void zzl(zzat zzat, String str, String str2) {
        Preconditions.checkNotNull(zzat);
        Preconditions.checkNotEmpty(str);
        zzA(str, true);
        zzy(new zzgh(this, zzat, str));
    }

    public final void zzm(zzp zzp) {
        Preconditions.checkNotEmpty(zzp.zza);
        zzA(zzp.zza, false);
        zzy(new zzgd(this, zzp));
    }

    public final void zzn(zzab zzab, zzp zzp) {
        Preconditions.checkNotNull(zzab);
        Preconditions.checkNotNull(zzab.zzc);
        zzz(zzp, false);
        zzab zzab2 = new zzab(zzab);
        zzab2.zza = zzp.zza;
        zzy(new zzfx(this, zzab2, zzp));
    }

    public final void zzo(zzab zzab) {
        Preconditions.checkNotNull(zzab);
        Preconditions.checkNotNull(zzab.zzc);
        Preconditions.checkNotEmpty(zzab.zza);
        zzA(zzab.zza, true);
        zzy(new zzfy(this, new zzab(zzab)));
    }

    public final void zzp(zzp zzp) {
        Preconditions.checkNotEmpty(zzp.zza);
        Preconditions.checkNotNull(zzp.zzv);
        zzgf zzgf = new zzgf(this, zzp);
        Preconditions.checkNotNull(zzgf);
        if (this.zza.zzaz().zzs()) {
            zzgf.run();
        } else {
            this.zza.zzaz().zzq(zzgf);
        }
    }

    public final void zzq(long j, String str, String str2, String str3) {
        zzy(new zzgm(this, str2, str3, str, j));
    }

    public final void zzr(Bundle bundle, zzp zzp) {
        zzz(zzp, false);
        String str = zzp.zza;
        Preconditions.checkNotNull(str);
        zzy(new zzfw(this, str, bundle));
    }

    public final void zzs(zzp zzp) {
        zzz(zzp, false);
        zzy(new zzge(this, zzp));
    }

    public final void zzt(zzkv zzkv, zzp zzp) {
        Preconditions.checkNotNull(zzkv);
        zzz(zzp, false);
        zzy(new zzgj(this, zzkv, zzp));
    }

    public final byte[] zzu(zzat zzat, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzat);
        zzA(str, true);
        this.zza.zzay().zzc().zzb("Log and bundle. event", this.zza.zzj().zzd(zzat.zza));
        long nanoTime = this.zza.zzav().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zza.zzaz().zzi(new zzgi(this, zzat, str)).get();
            if (bArr == null) {
                this.zza.zzay().zzd().zzb("Log and bundle returned null. appId", zzel.zzn(str));
                bArr = new byte[0];
            }
            this.zza.zzay().zzc().zzd("Log and bundle processed. event, size, time_ms", this.zza.zzj().zzd(zzat.zza), Integer.valueOf(bArr.length), Long.valueOf((this.zza.zzav().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzay().zzd().zzd("Failed to log and bundle. appId, event, error", zzel.zzn(str), this.zza.zzj().zzd(zzat.zza), e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzw(zzat zzat, zzp zzp) {
        if (!this.zza.zzo().zzl(zzp.zza)) {
            zzB(zzat, zzp);
            return;
        }
        this.zza.zzay().zzj().zzb("EES config found for", zzp.zza);
        zzfm zzo = this.zza.zzo();
        String str = zzp.zza;
        zzpe.zzc();
        zzc zzc2 = null;
        if (zzo.zzs.zzf().zzs((String) null, zzdy.zzat) && !TextUtils.isEmpty(str)) {
            zzc2 = zzo.zzc.get(str);
        }
        if (zzc2 != null) {
            try {
                Map<String, Object> zzt = this.zza.zzu().zzt(zzat.zzb.zzc(), true);
                String zza2 = zzgs.zza(zzat.zza);
                if (zza2 == null) {
                    zza2 = zzat.zza;
                }
                if (zzc2.zze(new zzaa(zza2, zzat.zzd, zzt))) {
                    if (zzc2.zzg()) {
                        this.zza.zzay().zzj().zzb("EES edited event", zzat.zza);
                        zzB(this.zza.zzu().zzi(zzc2.zza().zzb()), zzp);
                    } else {
                        zzB(zzat, zzp);
                    }
                    if (zzc2.zzf()) {
                        for (zzaa next : zzc2.zza().zzc()) {
                            this.zza.zzay().zzj().zzb("EES logging created event", next.zzd());
                            zzB(this.zza.zzu().zzi(next), zzp);
                        }
                        return;
                    }
                    return;
                }
            } catch (zzd e) {
                this.zza.zzay().zzd().zzc("EES error. appId, eventName", zzp.zzb, zzat.zza);
            }
            this.zza.zzay().zzj().zzb("EES was not applied to event", zzat.zza);
            zzB(zzat, zzp);
            return;
        }
        this.zza.zzay().zzj().zzb("EES not loaded for", zzp.zza);
        zzB(zzat, zzp);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzx(String str, Bundle bundle) {
        zzaj zzi = this.zza.zzi();
        zzi.zzg();
        zzi.zzY();
        byte[] zzbs = zzi.zzf.zzu().zzj(new zzao(zzi.zzs, "", str, "dep", 0, 0, bundle)).zzbs();
        zzi.zzs.zzay().zzj().zzc("Saving default event parameters, appId, data size", zzi.zzs.zzj().zzd(str), Integer.valueOf(zzbs.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("parameters", zzbs);
        try {
            if (zzi.zzh().insertWithOnConflict("default_event_params", (String) null, contentValues, 5) == -1) {
                zzi.zzs.zzay().zzd().zzb("Failed to insert default event parameters (got -1). appId", zzel.zzn(str));
            }
        } catch (SQLiteException e) {
            zzi.zzs.zzay().zzd().zzc("Error storing default event parameters. appId", zzel.zzn(str), e);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzy(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (this.zza.zzaz().zzs()) {
            runnable.run();
        } else {
            this.zza.zzaz().zzp(runnable);
        }
    }
}
