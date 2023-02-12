package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.measurement.zzcf;
import com.google.firebase.firestore.util.ExponentialBackoff;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zzjo extends zzf {
    /* access modifiers changed from: private */
    public final zzjn zza;
    /* access modifiers changed from: private */
    public zzeb zzb;
    private volatile Boolean zzc;
    private final zzam zzd;
    private final zzke zze;
    private final List<Runnable> zzf = new ArrayList();
    private final zzam zzg;

    protected zzjo(zzfv zzfv) {
        super(zzfv);
        this.zze = new zzke(zzfv.zzav());
        this.zza = new zzjn(this);
        this.zzd = new zziy(this, zzfv);
        this.zzg = new zzja(this, zzfv);
    }

    private final zzp zzO(boolean z) {
        Pair<String, Long> zza2;
        this.zzs.zzaw();
        zzec zzh = this.zzs.zzh();
        String str = null;
        if (z) {
            zzel zzay = this.zzs.zzay();
            if (!(zzay.zzs.zzm().zzb == null || (zza2 = zzay.zzs.zzm().zzb.zza()) == null || zza2 == zzfa.zza)) {
                String valueOf = String.valueOf(zza2.second);
                String str2 = (String) zza2.first;
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str2).length());
                sb.append(valueOf);
                sb.append(":");
                sb.append(str2);
                str = sb.toString();
            }
        }
        return zzh.zzj(str);
    }

    /* access modifiers changed from: private */
    public final void zzP() {
        zzg();
        this.zzs.zzay().zzj().zzb("Processing queued up service tasks", Integer.valueOf(this.zzf.size()));
        for (Runnable run : this.zzf) {
            try {
                run.run();
            } catch (RuntimeException e) {
                this.zzs.zzay().zzd().zzb("Task exception while flushing queue", e);
            }
        }
        this.zzf.clear();
        this.zzg.zzb();
    }

    /* access modifiers changed from: private */
    public final void zzQ() {
        zzg();
        this.zze.zzb();
        zzam zzam = this.zzd;
        this.zzs.zzf();
        zzam.zzd(zzdy.zzI.zza(null).longValue());
    }

    private final void zzR(Runnable runnable) throws IllegalStateException {
        zzg();
        if (zzL()) {
            runnable.run();
            return;
        }
        int size = this.zzf.size();
        this.zzs.zzf();
        if (((long) size) >= 1000) {
            this.zzs.zzay().zzd().zza("Discarding data. Max runnable queue size reached");
            return;
        }
        this.zzf.add(runnable);
        this.zzg.zzd(ExponentialBackoff.DEFAULT_BACKOFF_MAX_DELAY_MS);
        zzr();
    }

    private final boolean zzS() {
        this.zzs.zzaw();
        return true;
    }

    static /* bridge */ /* synthetic */ void zzo(zzjo zzjo, ComponentName componentName) {
        zzjo.zzg();
        if (zzjo.zzb != null) {
            zzjo.zzb = null;
            zzjo.zzs.zzay().zzj().zzb("Disconnected from device MeasurementService", componentName);
            zzjo.zzg();
            zzjo.zzr();
        }
    }

    /* access modifiers changed from: protected */
    public final void zzA(zzat zzat, String str) {
        Preconditions.checkNotNull(zzat);
        zzg();
        zza();
        zzS();
        zzR(new zzjd(this, true, zzO(true), this.zzs.zzi().zzo(zzat), zzat, str));
    }

    public final void zzB(zzcf zzcf, zzat zzat, String str) {
        zzg();
        zza();
        if (this.zzs.zzv().zzo(12451000) != 0) {
            this.zzs.zzay().zzk().zza("Not bundling data. Service unavailable or out of date");
            this.zzs.zzv().zzR(zzcf, new byte[0]);
            return;
        }
        zzR(new zziz(this, zzat, str, zzcf));
    }

    /* access modifiers changed from: protected */
    public final void zzC() {
        zzg();
        zza();
        zzp zzO = zzO(false);
        zzS();
        this.zzs.zzi().zzj();
        zzR(new zzis(this, zzO));
    }

    /* access modifiers changed from: package-private */
    public final void zzD(zzeb zzeb, AbstractSafeParcelable abstractSafeParcelable, zzp zzp) {
        int i;
        zzg();
        zza();
        zzS();
        this.zzs.zzf();
        int i2 = 0;
        int i3 = 100;
        while (i2 < 1001 && i3 == 100) {
            ArrayList arrayList = new ArrayList();
            List<AbstractSafeParcelable> zzi = this.zzs.zzi().zzi(100);
            if (zzi != null) {
                arrayList.addAll(zzi);
                i = zzi.size();
            } else {
                i = 0;
            }
            if (abstractSafeParcelable != null && i < 100) {
                arrayList.add(abstractSafeParcelable);
            }
            int size = arrayList.size();
            for (int i4 = 0; i4 < size; i4++) {
                AbstractSafeParcelable abstractSafeParcelable2 = (AbstractSafeParcelable) arrayList.get(i4);
                if (abstractSafeParcelable2 instanceof zzat) {
                    try {
                        zzeb.zzk((zzat) abstractSafeParcelable2, zzp);
                    } catch (RemoteException e) {
                        this.zzs.zzay().zzd().zzb("Failed to send event to the service", e);
                    }
                } else if (abstractSafeParcelable2 instanceof zzkv) {
                    try {
                        zzeb.zzt((zzkv) abstractSafeParcelable2, zzp);
                    } catch (RemoteException e2) {
                        this.zzs.zzay().zzd().zzb("Failed to send user property to the service", e2);
                    }
                } else if (abstractSafeParcelable2 instanceof zzab) {
                    try {
                        zzeb.zzn((zzab) abstractSafeParcelable2, zzp);
                    } catch (RemoteException e3) {
                        this.zzs.zzay().zzd().zzb("Failed to send conditional user property to the service", e3);
                    }
                } else {
                    this.zzs.zzay().zzd().zza("Discarding data. Unrecognized parcel type.");
                }
            }
            i2++;
            i3 = i;
        }
    }

    /* access modifiers changed from: protected */
    public final void zzE(zzab zzab) {
        Preconditions.checkNotNull(zzab);
        zzg();
        zza();
        this.zzs.zzaw();
        zzR(new zzje(this, true, zzO(true), this.zzs.zzi().zzn(zzab), new zzab(zzab), zzab));
    }

    /* access modifiers changed from: protected */
    public final void zzF(boolean z) {
        zzg();
        zza();
        if (z) {
            zzS();
            this.zzs.zzi().zzj();
        }
        if (zzM()) {
            zzR(new zzjc(this, zzO(false)));
        }
    }

    /* access modifiers changed from: protected */
    public final void zzG(zzih zzih) {
        zzg();
        zza();
        zzR(new zziw(this, zzih));
    }

    public final void zzH(Bundle bundle) {
        zzg();
        zza();
        zzR(new zzix(this, zzO(false), bundle));
    }

    /* access modifiers changed from: protected */
    public final void zzI() {
        zzg();
        zza();
        zzR(new zzjb(this, zzO(true)));
    }

    /* access modifiers changed from: protected */
    public final void zzJ(zzeb zzeb) {
        zzg();
        Preconditions.checkNotNull(zzeb);
        this.zzb = zzeb;
        zzQ();
        zzP();
    }

    /* access modifiers changed from: protected */
    public final void zzK(zzkv zzkv) {
        zzg();
        zza();
        zzS();
        zzR(new zziq(this, zzO(true), this.zzs.zzi().zzp(zzkv), zzkv));
    }

    public final boolean zzL() {
        zzg();
        zza();
        return this.zzb != null;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzM() {
        zzg();
        zza();
        if (!zzN() || this.zzs.zzv().zzm() >= zzdy.zzan.zza(null).intValue()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzN() {
        Boolean bool;
        zzg();
        zza();
        if (this.zzc == null) {
            zzg();
            zza();
            zzfa zzm = this.zzs.zzm();
            zzm.zzg();
            boolean z = false;
            if (!zzm.zza().contains("use_service")) {
                bool = null;
            } else {
                bool = Boolean.valueOf(zzm.zza().getBoolean("use_service", false));
            }
            boolean z2 = true;
            if (bool == null || !bool.booleanValue()) {
                this.zzs.zzaw();
                if (this.zzs.zzh().zzh() != 1) {
                    this.zzs.zzay().zzj().zza("Checking service availability");
                    int zzo = this.zzs.zzv().zzo(12451000);
                    switch (zzo) {
                        case 0:
                            this.zzs.zzay().zzj().zza("Service available");
                            z = true;
                            break;
                        case 1:
                            this.zzs.zzay().zzj().zza("Service missing");
                            break;
                        case 2:
                            this.zzs.zzay().zzc().zza("Service container out of date");
                            if (this.zzs.zzv().zzm() >= 17443) {
                                if (bool != null) {
                                    z2 = false;
                                }
                                z = z2;
                                z2 = false;
                                break;
                            }
                            break;
                        case 3:
                            this.zzs.zzay().zzk().zza("Service disabled");
                            z2 = false;
                            break;
                        case 9:
                            this.zzs.zzay().zzk().zza("Service invalid");
                            z2 = false;
                            break;
                        case 18:
                            this.zzs.zzay().zzk().zza("Service updating");
                            z = true;
                            break;
                        default:
                            this.zzs.zzay().zzk().zzb("Unexpected service status", Integer.valueOf(zzo));
                            z2 = false;
                            break;
                    }
                } else {
                    z = true;
                }
                if (!z && this.zzs.zzf().zzx()) {
                    this.zzs.zzay().zzd().zza("No way to upload. Consider using the full version of Analytics");
                } else if (z2) {
                    zzfa zzm2 = this.zzs.zzm();
                    zzm2.zzg();
                    SharedPreferences.Editor edit = zzm2.zza().edit();
                    edit.putBoolean("use_service", z);
                    edit.apply();
                }
                z2 = z;
            }
            this.zzc = Boolean.valueOf(z2);
        }
        return this.zzc.booleanValue();
    }

    /* access modifiers changed from: protected */
    public final boolean zzf() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzj() {
        return this.zzc;
    }

    /* access modifiers changed from: protected */
    public final void zzq() {
        zzg();
        zza();
        zzp zzO = zzO(true);
        this.zzs.zzi().zzk();
        zzR(new zziv(this, zzO));
    }

    /* access modifiers changed from: package-private */
    public final void zzr() {
        zzg();
        zza();
        if (!zzL()) {
            if (zzN()) {
                this.zza.zzc();
            } else if (!this.zzs.zzf().zzx()) {
                this.zzs.zzaw();
                List<ResolveInfo> queryIntentServices = this.zzs.zzau().getPackageManager().queryIntentServices(new Intent().setClassName(this.zzs.zzau(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
                if (queryIntentServices == null || queryIntentServices.size() <= 0) {
                    this.zzs.zzay().zzd().zza("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
                    return;
                }
                Intent intent = new Intent("com.google.android.gms.measurement.START");
                Context zzau = this.zzs.zzau();
                this.zzs.zzaw();
                intent.setComponent(new ComponentName(zzau, "com.google.android.gms.measurement.AppMeasurementService"));
                this.zza.zzb(intent);
            }
        }
    }

    public final void zzs() {
        zzg();
        zza();
        this.zza.zzd();
        try {
            ConnectionTracker.getInstance().unbindService(this.zzs.zzau(), this.zza);
        } catch (IllegalArgumentException | IllegalStateException e) {
        }
        this.zzb = null;
    }

    public final void zzt(zzcf zzcf) {
        zzg();
        zza();
        zzR(new zziu(this, zzO(false), zzcf));
    }

    public final void zzu(AtomicReference<String> atomicReference) {
        zzg();
        zza();
        zzR(new zzit(this, atomicReference, zzO(false)));
    }

    /* access modifiers changed from: protected */
    public final void zzv(zzcf zzcf, String str, String str2) {
        zzg();
        zza();
        zzR(new zzjg(this, str, str2, zzO(false), zzcf));
    }

    /* access modifiers changed from: protected */
    public final void zzw(AtomicReference<List<zzab>> atomicReference, String str, String str2, String str3) {
        zzg();
        zza();
        zzR(new zzjf(this, atomicReference, (String) null, str2, str3, zzO(false)));
    }

    /* access modifiers changed from: protected */
    public final void zzx(AtomicReference<List<zzkv>> atomicReference, boolean z) {
        zzg();
        zza();
        zzR(new zzir(this, atomicReference, zzO(false), z));
    }

    /* access modifiers changed from: protected */
    public final void zzy(zzcf zzcf, String str, String str2, boolean z) {
        zzg();
        zza();
        zzR(new zzip(this, str, str2, zzO(false), z, zzcf));
    }

    /* access modifiers changed from: protected */
    public final void zzz(AtomicReference<List<zzkv>> atomicReference, String str, String str2, String str3, boolean z) {
        zzg();
        zza();
        zzR(new zzjh(this, atomicReference, (String) null, str2, str3, zzO(false), z));
    }
}
