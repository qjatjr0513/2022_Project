package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfn;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfp;
import com.google.android.gms.internal.measurement.zzfq;
import com.google.android.gms.internal.measurement.zzfr;
import com.google.android.gms.internal.measurement.zzfs;
import com.google.android.gms.internal.measurement.zzfv;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzfz;
import com.google.android.gms.internal.measurement.zzgb;
import com.google.android.gms.internal.measurement.zzgg;
import com.google.android.gms.internal.measurement.zzgh;
import com.google.android.gms.internal.measurement.zzom;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzgi implements Callable<byte[]> {
    final /* synthetic */ zzat zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzgn zzc;

    zzgi(zzgn zzgn, zzat zzat, String str) {
        this.zzc = zzgn;
        this.zza = zzat;
        this.zzb = str;
    }

    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        byte[] bArr;
        zzks zzks;
        zzkx zzkx;
        Bundle bundle;
        zzfv zzfv;
        String str;
        zzfx zzfx;
        zzg zzg;
        Object obj;
        long j;
        zzap zzap;
        this.zzc.zza.zzA();
        zzif zzr = this.zzc.zza.zzr();
        zzat zzat = this.zza;
        String str2 = this.zzb;
        zzr.zzg();
        zzfv.zzO();
        Preconditions.checkNotNull(zzat);
        Preconditions.checkNotEmpty(str2);
        if (!zzr.zzs.zzf().zzs(str2, zzdy.zzU)) {
            zzr.zzs.zzay().zzc().zzb("Generating ScionPayload disabled. packageName", str2);
            return new byte[0];
        } else if ("_iap".equals(zzat.zza) || "_iapx".equals(zzat.zza)) {
            zzfv zza2 = zzfw.zza();
            zzr.zzf.zzi().zzw();
            zzg zzj = zzr.zzf.zzi().zzj(str2);
            if (zzj == null) {
                zzr.zzs.zzay().zzc().zzb("Log and bundle not available. package_name", str2);
                bArr = new byte[0];
                zzks = zzr.zzf;
            } else if (!zzj.zzaj()) {
                zzr.zzs.zzay().zzc().zzb("Log and bundle disabled. package_name", str2);
                bArr = new byte[0];
                zzks = zzr.zzf;
            } else {
                zzfx zzu = zzfy.zzu();
                zzu.zzaa(1);
                zzu.zzW("android");
                if (!TextUtils.isEmpty(zzj.zzt())) {
                    zzu.zzA(zzj.zzt());
                }
                if (!TextUtils.isEmpty(zzj.zzv())) {
                    zzu.zzC((String) Preconditions.checkNotNull(zzj.zzv()));
                }
                if (!TextUtils.isEmpty(zzj.zzw())) {
                    zzu.zzD((String) Preconditions.checkNotNull(zzj.zzw()));
                }
                if (zzj.zzb() != -2147483648L) {
                    zzu.zzE((int) zzj.zzb());
                }
                zzu.zzS(zzj.zzm());
                zzu.zzM(zzj.zzk());
                String zzz = zzj.zzz();
                String zzr2 = zzj.zzr();
                zzom.zzc();
                if (zzr.zzs.zzf().zzs(zzj.zzt(), zzdy.zzac)) {
                    String zzy = zzj.zzy();
                    if (!TextUtils.isEmpty(zzz)) {
                        zzu.zzR(zzz);
                    } else if (!TextUtils.isEmpty(zzy)) {
                        zzu.zzQ(zzy);
                    } else if (!TextUtils.isEmpty(zzr2)) {
                        zzu.zzy(zzr2);
                    }
                } else if (!TextUtils.isEmpty(zzz)) {
                    zzu.zzR(zzz);
                } else if (!TextUtils.isEmpty(zzr2)) {
                    zzu.zzy(zzr2);
                }
                zzag zzh = zzr.zzf.zzh(str2);
                zzu.zzJ(zzj.zzj());
                if (zzr.zzs.zzJ() && zzr.zzs.zzf().zzt(zzu.zzal()) && zzh.zzj() && !TextUtils.isEmpty((CharSequence) null)) {
                    zzu.zzL((String) null);
                }
                zzu.zzI(zzh.zzi());
                if (zzh.zzj()) {
                    Pair<String, Boolean> zzd = zzr.zzf.zzs().zzd(zzj.zzt(), zzh);
                    if (zzj.zzai() && !TextUtils.isEmpty((CharSequence) zzd.first)) {
                        try {
                            zzu.zzab(zzif.zza((String) zzd.first, Long.toString(zzat.zzd)));
                            if (zzd.second != null) {
                                zzu.zzU(((Boolean) zzd.second).booleanValue());
                            }
                        } catch (SecurityException e) {
                            zzr.zzs.zzay().zzc().zzb("Resettable device id encryption failed", e.getMessage());
                            bArr = new byte[0];
                            zzks = zzr.zzf;
                        } catch (Throwable th) {
                            zzr.zzf.zzi().zzy();
                            throw th;
                        }
                    }
                }
                zzr.zzs.zzg().zzu();
                zzu.zzK(Build.MODEL);
                zzr.zzs.zzg().zzu();
                zzu.zzV(Build.VERSION.RELEASE);
                zzu.zzaf((int) zzr.zzs.zzg().zzb());
                zzu.zzaj(zzr.zzs.zzg().zzc());
                try {
                    if (zzh.zzk() && zzj.zzu() != null) {
                        zzu.zzB(zzif.zza((String) Preconditions.checkNotNull(zzj.zzu()), Long.toString(zzat.zzd)));
                    }
                    if (!TextUtils.isEmpty(zzj.zzx())) {
                        zzu.zzP((String) Preconditions.checkNotNull(zzj.zzx()));
                    }
                    String zzt = zzj.zzt();
                    List<zzkx> zzu2 = zzr.zzf.zzi().zzu(zzt);
                    Iterator<zzkx> it = zzu2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            zzkx = null;
                            break;
                        }
                        zzkx = it.next();
                        if ("_lte".equals(zzkx.zzc)) {
                            break;
                        }
                    }
                    if (zzkx == null || zzkx.zze == null) {
                        zzkx zzkx2 = new zzkx(zzt, "auto", "_lte", zzr.zzs.zzav().currentTimeMillis(), 0L);
                        zzu2.add(zzkx2);
                        zzr.zzf.zzi().zzN(zzkx2);
                    }
                    zzku zzu3 = zzr.zzf.zzu();
                    zzu3.zzs.zzay().zzj().zza("Checking account type status for ad personalization signals");
                    if (zzu3.zzs.zzg().zze()) {
                        String zzt2 = zzj.zzt();
                        Preconditions.checkNotNull(zzt2);
                        if (zzj.zzai() && zzu3.zzf.zzo().zzk(zzt2)) {
                            zzu3.zzs.zzay().zzc().zza("Turning off ad personalization due to account type");
                            Iterator<zzkx> it2 = zzu2.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                } else if ("_npa".equals(it2.next().zzc)) {
                                    it2.remove();
                                    break;
                                }
                            }
                            zzu2.add(new zzkx(zzt2, "auto", "_npa", zzu3.zzs.zzav().currentTimeMillis(), 1L));
                        }
                    }
                    zzgh[] zzghArr = new zzgh[zzu2.size()];
                    for (int i = 0; i < zzu2.size(); i++) {
                        zzgg zzd2 = zzgh.zzd();
                        zzd2.zzf(zzu2.get(i).zzc);
                        zzd2.zzg(zzu2.get(i).zzd);
                        zzr.zzf.zzu().zzv(zzd2, zzu2.get(i).zze);
                        zzghArr[i] = (zzgh) zzd2.zzaA();
                    }
                    zzu.zzi(Arrays.asList(zzghArr));
                    zzem zzb2 = zzem.zzb(zzat);
                    zzr.zzs.zzv().zzK(zzb2.zzd, zzr.zzf.zzi().zzi(str2));
                    zzr.zzs.zzv().zzL(zzb2, zzr.zzs.zzf().zzd(str2));
                    Bundle bundle2 = zzb2.zzd;
                    bundle2.putLong("_c", 1);
                    zzr.zzs.zzay().zzc().zza("Marking in-app purchase as real-time");
                    bundle2.putLong("_r", 1);
                    bundle2.putString("_o", zzat.zzc);
                    if (zzr.zzs.zzv().zzad(zzu.zzal())) {
                        zzr.zzs.zzv().zzN(bundle2, "_dbg", 1L);
                        zzr.zzs.zzv().zzN(bundle2, "_r", 1L);
                    }
                    zzap zzn = zzr.zzf.zzi().zzn(str2, zzat.zza);
                    if (zzn == null) {
                        zzfx = zzu;
                        zzg = zzj;
                        zzfv = zza2;
                        str = str2;
                        bundle = bundle2;
                        obj = null;
                        zzap = new zzap(str2, zzat.zza, 0, 0, 0, zzat.zzd, 0, (Long) null, (Long) null, (Long) null, (Boolean) null);
                        j = 0;
                    } else {
                        zzg = zzj;
                        zzfv = zza2;
                        str = str2;
                        bundle = bundle2;
                        zzfx = zzu;
                        obj = null;
                        long j2 = zzn.zzf;
                        zzap = zzn.zzc(zzat.zzd);
                        j = j2;
                    }
                    zzr.zzf.zzi().zzF(zzap);
                    zzao zzao = new zzao(zzr.zzs, zzat.zzc, str, zzat.zza, zzat.zzd, j, bundle);
                    zzfn zze = zzfo.zze();
                    zze.zzm(zzao.zzd);
                    zze.zzi(zzao.zzb);
                    zze.zzl(zzao.zze);
                    zzaq zzaq = new zzaq(zzao.zzf);
                    while (zzaq.hasNext()) {
                        String zza3 = zzaq.next();
                        zzfr zze2 = zzfs.zze();
                        zze2.zzj(zza3);
                        Object zzf = zzao.zzf.zzf(zza3);
                        if (zzf != null) {
                            zzr.zzf.zzu().zzu(zze2, zzf);
                            zze.zze(zze2);
                        }
                    }
                    zzfx zzfx2 = zzfx;
                    zzfx2.zzj(zze);
                    zzfz zza4 = zzgb.zza();
                    zzfp zza5 = zzfq.zza();
                    zza5.zza(zzap.zzc);
                    zza5.zzb(zzat.zza);
                    zza4.zza(zza5);
                    zzfx2.zzX(zza4);
                    zzfx2.zzf(zzr.zzf.zzf().zza(zzg.zzt(), Collections.emptyList(), zzfx2.zzap(), Long.valueOf(zze.zzc()), Long.valueOf(zze.zzc())));
                    if (zze.zzq()) {
                        zzfx2.zzae(zze.zzc());
                        zzfx2.zzN(zze.zzc());
                    }
                    long zzn2 = zzg.zzn();
                    int i2 = (zzn2 > 0 ? 1 : (zzn2 == 0 ? 0 : -1));
                    if (i2 != 0) {
                        zzfx2.zzY(zzn2);
                    }
                    long zzp = zzg.zzp();
                    if (zzp != 0) {
                        zzfx2.zzZ(zzp);
                    } else if (i2 != 0) {
                        zzfx2.zzZ(zzn2);
                    }
                    zzg.zzE();
                    zzfx2.zzF((int) zzg.zzo());
                    zzr.zzs.zzf().zzh();
                    zzfx2.zzah(46000);
                    zzfx2.zzag(zzr.zzs.zzav().currentTimeMillis());
                    zzfx2.zzad(Boolean.TRUE.booleanValue());
                    zzfv zzfv2 = zzfv;
                    zzfv2.zza(zzfx2);
                    zzg zzg2 = zzg;
                    zzg2.zzad(zzfx2.zzd());
                    zzg2.zzab(zzfx2.zzc());
                    zzr.zzf.zzi().zzE(zzg2);
                    zzr.zzf.zzi().zzD();
                    zzr.zzf.zzi().zzy();
                    try {
                        return zzr.zzf.zzu().zzz(((zzfw) zzfv2.zzaA()).zzbs());
                    } catch (IOException e2) {
                        zzr.zzs.zzay().zzd().zzc("Data loss. Failed to bundle and serialize. appId", zzel.zzn(str), e2);
                        return obj;
                    }
                } catch (SecurityException e3) {
                    zzr.zzs.zzay().zzc().zzb("app instance id encryption failed", e3.getMessage());
                    bArr = new byte[0];
                    zzks = zzr.zzf;
                }
            }
            zzks.zzi().zzy();
            return bArr;
        } else {
            zzr.zzs.zzay().zzc().zzc("Generating a payload for this event is not available. package_name, event_name", str2, zzat.zza);
            return null;
        }
    }
}
