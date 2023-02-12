package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import androidx.collection.ArrayMap;
import androidx.collection.LruCache;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzc;
import com.google.android.gms.internal.measurement.zzd;
import com.google.android.gms.internal.measurement.zzez;
import com.google.android.gms.internal.measurement.zzfb;
import com.google.android.gms.internal.measurement.zzfc;
import com.google.android.gms.internal.measurement.zzfe;
import com.google.android.gms.internal.measurement.zzgm;
import com.google.android.gms.internal.measurement.zzgo;
import com.google.android.gms.internal.measurement.zzkh;
import com.google.android.gms.internal.measurement.zznl;
import com.google.android.gms.internal.measurement.zzoy;
import com.google.android.gms.internal.measurement.zzpe;
import com.google.android.gms.internal.measurement.zzr;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzfm extends zzki implements zzae {
    final Map<String, Map<String, Boolean>> zza = new ArrayMap();
    final Map<String, Map<String, Boolean>> zzb = new ArrayMap();
    final LruCache<String, zzc> zzc = new zzfj(this, 20);
    final zzr zzd = new zzfk(this);
    /* access modifiers changed from: private */
    public final Map<String, Map<String, String>> zze = new ArrayMap();
    private final Map<String, zzfc> zzg = new ArrayMap();
    private final Map<String, Map<String, Integer>> zzh = new ArrayMap();
    private final Map<String, String> zzi = new ArrayMap();

    zzfm(zzks zzks) {
        super(zzks);
    }

    static /* bridge */ /* synthetic */ zzc zzd(zzfm zzfm, String str) {
        zzfm.zzY();
        Preconditions.checkNotEmpty(str);
        zzpe.zzc();
        if (!zzfm.zzs.zzf().zzs((String) null, zzdy.zzat) || !zzfm.zzl(str)) {
            return null;
        }
        if (!zzfm.zzg.containsKey(str) || zzfm.zzg.get(str) == null) {
            zzfm.zzt(str);
        } else {
            zzfm.zzu(str, zzfm.zzg.get(str));
        }
        return zzfm.zzc.snapshot().get(str);
    }

    private final zzfc zzr(String str, byte[] bArr) {
        Long l;
        if (bArr == null) {
            return zzfc.zzg();
        }
        try {
            zzfc zzfc = (zzfc) ((zzfb) zzku.zzl(zzfc.zze(), bArr)).zzaA();
            zzej zzj = this.zzs.zzay().zzj();
            String str2 = null;
            if (zzfc.zzq()) {
                l = Long.valueOf(zzfc.zzc());
            } else {
                l = null;
            }
            if (zzfc.zzp()) {
                str2 = zzfc.zzh();
            }
            zzj.zzc("Parsed config. version, gmp_app_id", l, str2);
            return zzfc;
        } catch (zzkh e) {
            this.zzs.zzay().zzk().zzc("Unable to merge remote config. appId", zzel.zzn(str), e);
            return zzfc.zzg();
        } catch (RuntimeException e2) {
            this.zzs.zzay().zzk().zzc("Unable to merge remote config. appId", zzel.zzn(str), e2);
            return zzfc.zzg();
        }
    }

    private final void zzs(String str, zzfb zzfb) {
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        if (zzfb != null) {
            for (int i = 0; i < zzfb.zza(); i++) {
                zzez zzez = (zzez) zzfb.zzb(i).zzbv();
                if (TextUtils.isEmpty(zzez.zzc())) {
                    this.zzs.zzay().zzk().zza("EventConfig contained null event name");
                } else {
                    String zzc2 = zzez.zzc();
                    String zzb2 = zzgs.zzb(zzez.zzc());
                    if (!TextUtils.isEmpty(zzb2)) {
                        zzez.zzb(zzb2);
                        zzfb.zzd(i, zzez);
                    }
                    zznl.zzc();
                    if (!this.zzs.zzf().zzs((String) null, zzdy.zzaC)) {
                        arrayMap.put(zzc2, Boolean.valueOf(zzez.zzd()));
                    } else if (zzez.zzf() && zzez.zzd()) {
                        arrayMap.put(zzc2, true);
                    }
                    zznl.zzc();
                    if (!this.zzs.zzf().zzs((String) null, zzdy.zzaC)) {
                        arrayMap2.put(zzez.zzc(), Boolean.valueOf(zzez.zze()));
                    } else if (zzez.zzg() && zzez.zze()) {
                        arrayMap2.put(zzez.zzc(), true);
                    }
                    if (zzez.zzh()) {
                        if (zzez.zza() < 2 || zzez.zza() > 65535) {
                            this.zzs.zzay().zzk().zzc("Invalid sampling rate. Event name, sample rate", zzez.zzc(), Integer.valueOf(zzez.zza()));
                        } else {
                            arrayMap3.put(zzez.zzc(), Integer.valueOf(zzez.zza()));
                        }
                    }
                }
            }
        }
        this.zza.put(str, arrayMap);
        this.zzb.put(str, arrayMap2);
        this.zzh.put(str, arrayMap3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x012b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzt(java.lang.String r13) {
        /*
            r12 = this;
            r12.zzY()
            r12.zzg()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            java.util.Map<java.lang.String, com.google.android.gms.internal.measurement.zzfc> r0 = r12.zzg
            java.lang.Object r0 = r0.get(r13)
            if (r0 != 0) goto L_0x012f
            com.google.android.gms.measurement.internal.zzks r0 = r12.zzf
            com.google.android.gms.measurement.internal.zzaj r0 = r0.zzi()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            r0.zzg()
            r0.zzY()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r0.zzh()     // Catch:{ SQLiteException -> 0x0082, all -> 0x007f }
            java.lang.String r3 = "remote_config"
            java.lang.String r4 = "config_last_modified_time"
            java.lang.String[] r4 = new java.lang.String[]{r3, r4}     // Catch:{ SQLiteException -> 0x0082, all -> 0x007f }
            r10 = 1
            java.lang.String[] r6 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x0082, all -> 0x007f }
            r11 = 0
            r6[r11] = r13     // Catch:{ SQLiteException -> 0x0082, all -> 0x007f }
            java.lang.String r3 = "apps"
            java.lang.String r5 = "app_id=?"
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x0082, all -> 0x007f }
            boolean r3 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x007d }
            if (r3 != 0) goto L_0x0049
            if (r2 == 0) goto L_0x0047
            goto L_0x009a
        L_0x0047:
            r5 = r1
            goto L_0x00a0
        L_0x0049:
            byte[] r3 = r2.getBlob(r11)     // Catch:{ SQLiteException -> 0x007d }
            java.lang.String r4 = r2.getString(r10)     // Catch:{ SQLiteException -> 0x007d }
            boolean r5 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x007d }
            if (r5 == 0) goto L_0x006b
            com.google.android.gms.measurement.internal.zzfv r5 = r0.zzs     // Catch:{ SQLiteException -> 0x007d }
            com.google.android.gms.measurement.internal.zzel r5 = r5.zzay()     // Catch:{ SQLiteException -> 0x007d }
            com.google.android.gms.measurement.internal.zzej r5 = r5.zzd()     // Catch:{ SQLiteException -> 0x007d }
            java.lang.String r6 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzel.zzn(r13)     // Catch:{ SQLiteException -> 0x007d }
            r5.zzb(r6, r7)     // Catch:{ SQLiteException -> 0x007d }
        L_0x006b:
            if (r3 != 0) goto L_0x0071
            if (r2 == 0) goto L_0x0070
            goto L_0x009a
        L_0x0070:
            goto L_0x009d
        L_0x0071:
            android.util.Pair r5 = new android.util.Pair     // Catch:{ SQLiteException -> 0x007d }
            r5.<init>(r3, r4)     // Catch:{ SQLiteException -> 0x007d }
            if (r2 == 0) goto L_0x007c
            r2.close()
            goto L_0x00a0
        L_0x007c:
            goto L_0x00a0
        L_0x007d:
            r3 = move-exception
            goto L_0x0085
        L_0x007f:
            r13 = move-exception
            goto L_0x0129
        L_0x0082:
            r2 = move-exception
            r3 = r2
            r2 = r1
        L_0x0085:
            com.google.android.gms.measurement.internal.zzfv r0 = r0.zzs     // Catch:{ all -> 0x0127 }
            com.google.android.gms.measurement.internal.zzel r0 = r0.zzay()     // Catch:{ all -> 0x0127 }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzd()     // Catch:{ all -> 0x0127 }
            java.lang.String r4 = "Error querying remote config. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzel.zzn(r13)     // Catch:{ all -> 0x0127 }
            r0.zzc(r4, r5, r3)     // Catch:{ all -> 0x0127 }
            if (r2 == 0) goto L_0x009f
        L_0x009a:
            r2.close()
        L_0x009d:
            r5 = r1
            goto L_0x00a0
        L_0x009f:
            r5 = r1
        L_0x00a0:
            if (r5 != 0) goto L_0x00c1
            java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>> r0 = r12.zze
            r0.put(r13, r1)
            java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Boolean>> r0 = r12.zza
            r0.put(r13, r1)
            java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Boolean>> r0 = r12.zzb
            r0.put(r13, r1)
            java.util.Map<java.lang.String, com.google.android.gms.internal.measurement.zzfc> r0 = r12.zzg
            r0.put(r13, r1)
            java.util.Map<java.lang.String, java.lang.String> r0 = r12.zzi
            r0.put(r13, r1)
            java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Integer>> r0 = r12.zzh
            r0.put(r13, r1)
            return
        L_0x00c1:
            java.lang.Object r0 = r5.first
            byte[] r0 = (byte[]) r0
            com.google.android.gms.internal.measurement.zzfc r0 = r12.zzr(r13, r0)
            com.google.android.gms.internal.measurement.zzjt r0 = r0.zzbv()
            com.google.android.gms.internal.measurement.zzfb r0 = (com.google.android.gms.internal.measurement.zzfb) r0
            r12.zzs(r13, r0)
            java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>> r2 = r12.zze
            com.google.android.gms.internal.measurement.zzjx r3 = r0.zzaA()
            com.google.android.gms.internal.measurement.zzfc r3 = (com.google.android.gms.internal.measurement.zzfc) r3
            java.util.Map r3 = zzv(r3)
            r2.put(r13, r3)
            java.util.Map<java.lang.String, com.google.android.gms.internal.measurement.zzfc> r2 = r12.zzg
            com.google.android.gms.internal.measurement.zzjx r3 = r0.zzaA()
            com.google.android.gms.internal.measurement.zzfc r3 = (com.google.android.gms.internal.measurement.zzfc) r3
            r2.put(r13, r3)
            com.google.android.gms.internal.measurement.zzpe.zzc()
            com.google.android.gms.measurement.internal.zzfv r2 = r12.zzs
            com.google.android.gms.measurement.internal.zzaf r2 = r2.zzf()
            com.google.android.gms.measurement.internal.zzdx<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzdy.zzat
            boolean r2 = r2.zzs(r1, r3)
            if (r2 == 0) goto L_0x0106
            com.google.android.gms.internal.measurement.zzjx r0 = r0.zzaA()
            com.google.android.gms.internal.measurement.zzfc r0 = (com.google.android.gms.internal.measurement.zzfc) r0
            r12.zzu(r13, r0)
        L_0x0106:
            com.google.android.gms.internal.measurement.zzoy.zzc()
            com.google.android.gms.measurement.internal.zzfv r0 = r12.zzs
            com.google.android.gms.measurement.internal.zzaf r0 = r0.zzf()
            com.google.android.gms.measurement.internal.zzdx<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzdy.zzaq
            boolean r0 = r0.zzs(r1, r2)
            if (r0 == 0) goto L_0x0121
            java.util.Map<java.lang.String, java.lang.String> r0 = r12.zzi
            java.lang.Object r1 = r5.second
            java.lang.String r1 = (java.lang.String) r1
            r0.put(r13, r1)
            return
        L_0x0121:
            java.util.Map<java.lang.String, java.lang.String> r0 = r12.zzi
            r0.put(r13, r1)
            return
        L_0x0127:
            r13 = move-exception
            r1 = r2
        L_0x0129:
            if (r1 == 0) goto L_0x012e
            r1.close()
        L_0x012e:
            throw r13
        L_0x012f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfm.zzt(java.lang.String):void");
    }

    private final void zzu(String str, zzfc zzfc) {
        if (zzfc.zza() != 0) {
            this.zzs.zzay().zzj().zzb("EES programs found", Integer.valueOf(zzfc.zza()));
            zzgo zzgo = zzfc.zzj().get(0);
            try {
                zzc zzc2 = new zzc();
                zzc2.zzd("internal.remoteConfig", new zzfg(this, str));
                zzc2.zzd("internal.appMetadata", new zzfi(this, str));
                zzc2.zzd("internal.logger", new zzff(this));
                zzc2.zzc(zzgo);
                this.zzc.put(str, zzc2);
                this.zzs.zzay().zzj().zzc("EES program loaded for appId, activities", str, Integer.valueOf(zzgo.zza().zza()));
                for (zzgm zzb2 : zzgo.zza().zzd()) {
                    this.zzs.zzay().zzj().zzb("EES program activity", zzb2.zzb());
                }
            } catch (zzd e) {
                this.zzs.zzay().zzd().zzb("Failed to load EES program. appId", str);
            }
        } else {
            this.zzc.remove(str);
        }
    }

    private static final Map<String, String> zzv(zzfc zzfc) {
        ArrayMap arrayMap = new ArrayMap();
        if (zzfc != null) {
            for (zzfe next : zzfc.zzk()) {
                arrayMap.put(next.zzb(), next.zzc());
            }
        }
        return arrayMap;
    }

    public final String zza(String str, String str2) {
        zzg();
        zzt(str);
        Map map = this.zze.get(str);
        if (map != null) {
            return (String) map.get(str2);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final boolean zzb() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final int zzc(String str, String str2) {
        Integer num;
        zzg();
        zzt(str);
        Map map = this.zzh.get(str);
        if (map == null || (num = (Integer) map.get(str2)) == null) {
            return 1;
        }
        return num.intValue();
    }

    /* access modifiers changed from: protected */
    public final zzfc zze(String str) {
        zzY();
        zzg();
        Preconditions.checkNotEmpty(str);
        zzt(str);
        return this.zzg.get(str);
    }

    /* access modifiers changed from: protected */
    public final String zzf(String str) {
        zzg();
        return this.zzi.get(str);
    }

    /* access modifiers changed from: protected */
    public final void zzi(String str) {
        zzg();
        this.zzi.put(str, (Object) null);
    }

    /* access modifiers changed from: package-private */
    public final void zzj(String str) {
        zzg();
        this.zzg.remove(str);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzk(String str) {
        zzg();
        zzfc zze2 = zze(str);
        if (zze2 == null) {
            return false;
        }
        return zze2.zzo();
    }

    public final boolean zzl(String str) {
        zzfc zzfc;
        zzpe.zzc();
        if (!this.zzs.zzf().zzs((String) null, zzdy.zzat) || TextUtils.isEmpty(str) || (zzfc = this.zzg.get(str)) == null || zzfc.zza() == 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzm(String str) {
        return "1".equals(zza(str, "measurement.upload.blacklist_internal"));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzn(String str, String str2) {
        Boolean bool;
        zzg();
        zzt(str);
        if (FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(str2) || FirebaseAnalytics.Event.PURCHASE.equals(str2) || FirebaseAnalytics.Event.REFUND.equals(str2)) {
            return true;
        }
        Map map = this.zzb.get(str);
        if (map == null || (bool = (Boolean) map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzo(String str, String str2) {
        Boolean bool;
        zzg();
        zzt(str);
        if (zzm(str) && zzkz.zzag(str2)) {
            return true;
        }
        if (zzp(str) && zzkz.zzah(str2)) {
            return true;
        }
        Map map = this.zza.get(str);
        if (map == null || (bool = (Boolean) map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzp(String str) {
        return "1".equals(zza(str, "measurement.upload.blacklist_public"));
    }

    /* access modifiers changed from: protected */
    public final boolean zzq(String str, byte[] bArr, String str2) {
        zzY();
        zzg();
        Preconditions.checkNotEmpty(str);
        zzfb zzfb = (zzfb) zzr(str, bArr).zzbv();
        if (zzfb == null) {
            return false;
        }
        zzs(str, zzfb);
        zzpe.zzc();
        if (this.zzs.zzf().zzs((String) null, zzdy.zzat)) {
            zzu(str, (zzfc) zzfb.zzaA());
        }
        this.zzg.put(str, (zzfc) zzfb.zzaA());
        this.zzi.put(str, str2);
        this.zze.put(str, zzv((zzfc) zzfb.zzaA()));
        this.zzf.zzi().zzC(str, new ArrayList(zzfb.zze()));
        try {
            zzfb.zzc();
            bArr = ((zzfc) zzfb.zzaA()).zzbs();
        } catch (RuntimeException e) {
            this.zzs.zzay().zzk().zzc("Unable to serialize reduced-size config. Storing full config instead. appId", zzel.zzn(str), e);
        }
        zzoy.zzc();
        if (this.zzs.zzf().zzs((String) null, zzdy.zzaq)) {
            this.zzf.zzi().zzG(str, bArr, str2);
        } else {
            this.zzf.zzi().zzG(str, bArr, (String) null);
        }
        this.zzg.put(str, (zzfc) zzfb.zzaA());
        return true;
    }
}
