package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzej;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzw extends zzx {
    final /* synthetic */ zzz zza;
    private final zzej zzh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzw(zzz zzz, String str, int i, zzej zzej) {
        super(str, i);
        this.zza = zzz;
        this.zzh = zzej;
    }

    /* access modifiers changed from: package-private */
    public final int zza() {
        return this.zzh.zzb();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzb() {
        return this.zzh.zzo();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzc() {
        return false;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [java.lang.Integer] */
    /* JADX WARNING: type inference failed for: r5v16, types: [java.lang.Integer] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0400  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0403  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x040b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x040c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzd(java.lang.Long r16, java.lang.Long r17, com.google.android.gms.internal.measurement.zzfo r18, long r19, com.google.android.gms.measurement.internal.zzap r21, boolean r22) {
        /*
            r15 = this;
            r0 = r15
            com.google.android.gms.internal.measurement.zzoa.zzc()
            com.google.android.gms.measurement.internal.zzz r1 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r1 = r1.zzs
            com.google.android.gms.measurement.internal.zzaf r1 = r1.zzf()
            java.lang.String r2 = r0.zzb
            com.google.android.gms.measurement.internal.zzdx<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzdy.zzY
            boolean r1 = r1.zzs(r2, r3)
            com.google.android.gms.internal.measurement.zzej r2 = r0.zzh
            boolean r2 = r2.zzn()
            if (r2 == 0) goto L_0x0021
            r2 = r21
            long r2 = r2.zze
            goto L_0x0023
        L_0x0021:
            r2 = r19
        L_0x0023:
            com.google.android.gms.measurement.internal.zzz r4 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r4 = r4.zzs
            com.google.android.gms.measurement.internal.zzel r4 = r4.zzay()
            java.lang.String r4 = r4.zzq()
            r5 = 2
            boolean r4 = android.util.Log.isLoggable(r4, r5)
            r5 = 0
            if (r4 == 0) goto L_0x0093
            com.google.android.gms.measurement.internal.zzz r4 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r4 = r4.zzs
            com.google.android.gms.measurement.internal.zzel r4 = r4.zzay()
            com.google.android.gms.measurement.internal.zzej r4 = r4.zzj()
            int r6 = r0.zzc
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            com.google.android.gms.internal.measurement.zzej r7 = r0.zzh
            boolean r7 = r7.zzp()
            if (r7 == 0) goto L_0x005c
            com.google.android.gms.internal.measurement.zzej r7 = r0.zzh
            int r7 = r7.zzb()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            goto L_0x005d
        L_0x005c:
            r7 = r5
        L_0x005d:
            com.google.android.gms.measurement.internal.zzz r8 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r8 = r8.zzs
            com.google.android.gms.measurement.internal.zzeg r8 = r8.zzj()
            com.google.android.gms.internal.measurement.zzej r9 = r0.zzh
            java.lang.String r9 = r9.zzg()
            java.lang.String r8 = r8.zzd(r9)
            java.lang.String r9 = "Evaluating filter. audience, filter, event"
            r4.zzd(r9, r6, r7, r8)
            com.google.android.gms.measurement.internal.zzz r4 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r4 = r4.zzs
            com.google.android.gms.measurement.internal.zzel r4 = r4.zzay()
            com.google.android.gms.measurement.internal.zzej r4 = r4.zzj()
            com.google.android.gms.measurement.internal.zzz r6 = r0.zza
            com.google.android.gms.measurement.internal.zzks r6 = r6.zzf
            com.google.android.gms.measurement.internal.zzku r6 = r6.zzu()
            com.google.android.gms.internal.measurement.zzej r7 = r0.zzh
            java.lang.String r6 = r6.zzo(r7)
            java.lang.String r7 = "Filter definition"
            r4.zzb(r7, r6)
        L_0x0093:
            com.google.android.gms.internal.measurement.zzej r4 = r0.zzh
            boolean r4 = r4.zzp()
            r6 = 0
            if (r4 == 0) goto L_0x0456
            com.google.android.gms.internal.measurement.zzej r4 = r0.zzh
            int r4 = r4.zzb()
            r7 = 256(0x100, float:3.59E-43)
            if (r4 <= r7) goto L_0x00a8
            goto L_0x0456
        L_0x00a8:
            com.google.android.gms.internal.measurement.zzej r4 = r0.zzh
            boolean r4 = r4.zzk()
            com.google.android.gms.internal.measurement.zzej r7 = r0.zzh
            boolean r7 = r7.zzm()
            com.google.android.gms.internal.measurement.zzej r8 = r0.zzh
            boolean r8 = r8.zzn()
            r9 = 1
            if (r4 != 0) goto L_0x00c5
            if (r7 != 0) goto L_0x00c5
            if (r8 == 0) goto L_0x00c3
            r4 = r9
            goto L_0x00c6
        L_0x00c3:
            r4 = r6
            goto L_0x00c6
        L_0x00c5:
            r4 = r9
        L_0x00c6:
            if (r22 == 0) goto L_0x00f7
            if (r4 != 0) goto L_0x00f7
            com.google.android.gms.measurement.internal.zzz r1 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r1 = r1.zzs
            com.google.android.gms.measurement.internal.zzel r1 = r1.zzay()
            com.google.android.gms.measurement.internal.zzej r1 = r1.zzj()
            int r2 = r0.zzc
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            com.google.android.gms.internal.measurement.zzej r3 = r0.zzh
            boolean r3 = r3.zzp()
            if (r3 == 0) goto L_0x00ef
            com.google.android.gms.internal.measurement.zzej r3 = r0.zzh
            int r3 = r3.zzb()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)
            goto L_0x00f0
        L_0x00ef:
        L_0x00f0:
            java.lang.String r3 = "Event filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID"
            r1.zzc(r3, r2, r5)
            return r9
        L_0x00f7:
            com.google.android.gms.internal.measurement.zzej r7 = r0.zzh
            java.lang.String r8 = r18.zzh()
            boolean r10 = r7.zzo()
            if (r10 == 0) goto L_0x011b
            com.google.android.gms.internal.measurement.zzeq r10 = r7.zzf()
            java.lang.Boolean r2 = zzh(r2, r10)
            if (r2 != 0) goto L_0x010f
            goto L_0x03f2
        L_0x010f:
            boolean r2 = r2.booleanValue()
            if (r2 != 0) goto L_0x011b
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r6)
            goto L_0x03f2
        L_0x011b:
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.List r3 = r7.zzh()
            java.util.Iterator r3 = r3.iterator()
        L_0x0128:
            boolean r10 = r3.hasNext()
            if (r10 == 0) goto L_0x0165
            java.lang.Object r10 = r3.next()
            com.google.android.gms.internal.measurement.zzel r10 = (com.google.android.gms.internal.measurement.zzel) r10
            java.lang.String r11 = r10.zze()
            boolean r11 = r11.isEmpty()
            if (r11 == 0) goto L_0x015d
            com.google.android.gms.measurement.internal.zzz r2 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r2 = r2.zzs
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzk()
            com.google.android.gms.measurement.internal.zzz r3 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r3 = r3.zzs
            com.google.android.gms.measurement.internal.zzeg r3 = r3.zzj()
            java.lang.String r3 = r3.zzd(r8)
            java.lang.String r7 = "null or empty param name in filter. event"
            r2.zzb(r7, r3)
            goto L_0x03f2
        L_0x015d:
            java.lang.String r10 = r10.zze()
            r2.add(r10)
            goto L_0x0128
        L_0x0165:
            androidx.collection.ArrayMap r3 = new androidx.collection.ArrayMap
            r3.<init>()
            java.util.List r10 = r18.zzi()
            java.util.Iterator r10 = r10.iterator()
        L_0x0172:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x0205
            java.lang.Object r11 = r10.next()
            com.google.android.gms.internal.measurement.zzfs r11 = (com.google.android.gms.internal.measurement.zzfs) r11
            java.lang.String r12 = r11.zzg()
            boolean r12 = r2.contains(r12)
            if (r12 == 0) goto L_0x0172
            boolean r12 = r11.zzw()
            if (r12 == 0) goto L_0x01a6
            java.lang.String r12 = r11.zzg()
            boolean r13 = r11.zzw()
            if (r13 == 0) goto L_0x01a1
            long r13 = r11.zzd()
            java.lang.Long r11 = java.lang.Long.valueOf(r13)
            goto L_0x01a2
        L_0x01a1:
            r11 = r5
        L_0x01a2:
            r3.put(r12, r11)
            goto L_0x0172
        L_0x01a6:
            boolean r12 = r11.zzu()
            if (r12 == 0) goto L_0x01c4
            java.lang.String r12 = r11.zzg()
            boolean r13 = r11.zzu()
            if (r13 == 0) goto L_0x01bf
            double r13 = r11.zza()
            java.lang.Double r11 = java.lang.Double.valueOf(r13)
            goto L_0x01c0
        L_0x01bf:
            r11 = r5
        L_0x01c0:
            r3.put(r12, r11)
            goto L_0x0172
        L_0x01c4:
            boolean r12 = r11.zzy()
            if (r12 == 0) goto L_0x01d6
            java.lang.String r12 = r11.zzg()
            java.lang.String r11 = r11.zzh()
            r3.put(r12, r11)
            goto L_0x0172
        L_0x01d6:
            com.google.android.gms.measurement.internal.zzz r2 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r2 = r2.zzs
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzk()
            com.google.android.gms.measurement.internal.zzz r3 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r3 = r3.zzs
            com.google.android.gms.measurement.internal.zzeg r3 = r3.zzj()
            java.lang.String r3 = r3.zzd(r8)
            com.google.android.gms.measurement.internal.zzz r7 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r7 = r7.zzs
            com.google.android.gms.measurement.internal.zzeg r7 = r7.zzj()
            java.lang.String r8 = r11.zzg()
            java.lang.String r7 = r7.zze(r8)
            java.lang.String r8 = "Unknown value for param. event, param"
            r2.zzc(r8, r3, r7)
            goto L_0x03f2
        L_0x0205:
            java.util.List r2 = r7.zzh()
            java.util.Iterator r2 = r2.iterator()
        L_0x020d:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x03ed
            java.lang.Object r7 = r2.next()
            com.google.android.gms.internal.measurement.zzel r7 = (com.google.android.gms.internal.measurement.zzel) r7
            boolean r10 = r7.zzh()
            if (r10 == 0) goto L_0x0227
            boolean r10 = r7.zzg()
            if (r10 == 0) goto L_0x0227
            r10 = r9
            goto L_0x0228
        L_0x0227:
            r10 = r6
        L_0x0228:
            java.lang.String r11 = r7.zze()
            boolean r12 = r11.isEmpty()
            if (r12 == 0) goto L_0x0251
            com.google.android.gms.measurement.internal.zzz r2 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r2 = r2.zzs
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzk()
            com.google.android.gms.measurement.internal.zzz r3 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r3 = r3.zzs
            com.google.android.gms.measurement.internal.zzeg r3 = r3.zzj()
            java.lang.String r3 = r3.zzd(r8)
            java.lang.String r7 = "Event has empty param name. event"
            r2.zzb(r7, r3)
            goto L_0x03f2
        L_0x0251:
            java.lang.Object r12 = r3.get(r11)
            boolean r13 = r12 instanceof java.lang.Long
            if (r13 == 0) goto L_0x02a8
            boolean r13 = r7.zzi()
            if (r13 != 0) goto L_0x028a
            com.google.android.gms.measurement.internal.zzz r2 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r2 = r2.zzs
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzk()
            com.google.android.gms.measurement.internal.zzz r3 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r3 = r3.zzs
            com.google.android.gms.measurement.internal.zzeg r3 = r3.zzj()
            java.lang.String r3 = r3.zzd(r8)
            com.google.android.gms.measurement.internal.zzz r7 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r7 = r7.zzs
            com.google.android.gms.measurement.internal.zzeg r7 = r7.zzj()
            java.lang.String r7 = r7.zze(r11)
            java.lang.String r8 = "No number filter for long param. event, param"
            r2.zzc(r8, r3, r7)
            goto L_0x03f2
        L_0x028a:
            java.lang.Long r12 = (java.lang.Long) r12
            long r11 = r12.longValue()
            com.google.android.gms.internal.measurement.zzeq r7 = r7.zzc()
            java.lang.Boolean r7 = zzh(r11, r7)
            if (r7 != 0) goto L_0x029c
            goto L_0x03f2
        L_0x029c:
            boolean r7 = r7.booleanValue()
            if (r7 != r10) goto L_0x020d
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r6)
            goto L_0x03f2
        L_0x02a8:
            boolean r13 = r12 instanceof java.lang.Double
            if (r13 == 0) goto L_0x02fb
            boolean r13 = r7.zzi()
            if (r13 != 0) goto L_0x02dd
            com.google.android.gms.measurement.internal.zzz r2 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r2 = r2.zzs
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzk()
            com.google.android.gms.measurement.internal.zzz r3 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r3 = r3.zzs
            com.google.android.gms.measurement.internal.zzeg r3 = r3.zzj()
            java.lang.String r3 = r3.zzd(r8)
            com.google.android.gms.measurement.internal.zzz r7 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r7 = r7.zzs
            com.google.android.gms.measurement.internal.zzeg r7 = r7.zzj()
            java.lang.String r7 = r7.zze(r11)
            java.lang.String r8 = "No number filter for double param. event, param"
            r2.zzc(r8, r3, r7)
            goto L_0x03f2
        L_0x02dd:
            java.lang.Double r12 = (java.lang.Double) r12
            double r11 = r12.doubleValue()
            com.google.android.gms.internal.measurement.zzeq r7 = r7.zzc()
            java.lang.Boolean r7 = zzg(r11, r7)
            if (r7 != 0) goto L_0x02ef
            goto L_0x03f2
        L_0x02ef:
            boolean r7 = r7.booleanValue()
            if (r7 != r10) goto L_0x020d
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r6)
            goto L_0x03f2
        L_0x02fb:
            boolean r13 = r12 instanceof java.lang.String
            if (r13 == 0) goto L_0x0393
            boolean r13 = r7.zzk()
            if (r13 == 0) goto L_0x0318
            java.lang.String r12 = (java.lang.String) r12
            com.google.android.gms.internal.measurement.zzex r7 = r7.zzd()
            com.google.android.gms.measurement.internal.zzz r11 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r11 = r11.zzs
            com.google.android.gms.measurement.internal.zzel r11 = r11.zzay()
            java.lang.Boolean r7 = zzf(r12, r7, r11)
            goto L_0x032e
        L_0x0318:
            boolean r13 = r7.zzi()
            if (r13 == 0) goto L_0x0369
            java.lang.String r12 = (java.lang.String) r12
            boolean r13 = com.google.android.gms.measurement.internal.zzku.zzy(r12)
            if (r13 == 0) goto L_0x033e
            com.google.android.gms.internal.measurement.zzeq r7 = r7.zzc()
            java.lang.Boolean r7 = zzi(r12, r7)
        L_0x032e:
            if (r7 != 0) goto L_0x0332
            goto L_0x03f2
        L_0x0332:
            boolean r7 = r7.booleanValue()
            if (r7 != r10) goto L_0x020d
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r6)
            goto L_0x03f2
        L_0x033e:
            com.google.android.gms.measurement.internal.zzz r2 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r2 = r2.zzs
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzk()
            com.google.android.gms.measurement.internal.zzz r3 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r3 = r3.zzs
            com.google.android.gms.measurement.internal.zzeg r3 = r3.zzj()
            java.lang.String r3 = r3.zzd(r8)
            com.google.android.gms.measurement.internal.zzz r7 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r7 = r7.zzs
            com.google.android.gms.measurement.internal.zzeg r7 = r7.zzj()
            java.lang.String r7 = r7.zze(r11)
            java.lang.String r8 = "Invalid param value for number filter. event, param"
            r2.zzc(r8, r3, r7)
            goto L_0x03f2
        L_0x0369:
            com.google.android.gms.measurement.internal.zzz r2 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r2 = r2.zzs
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzk()
            com.google.android.gms.measurement.internal.zzz r3 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r3 = r3.zzs
            com.google.android.gms.measurement.internal.zzeg r3 = r3.zzj()
            java.lang.String r3 = r3.zzd(r8)
            com.google.android.gms.measurement.internal.zzz r7 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r7 = r7.zzs
            com.google.android.gms.measurement.internal.zzeg r7 = r7.zzj()
            java.lang.String r7 = r7.zze(r11)
            java.lang.String r8 = "No filter for String param. event, param"
            r2.zzc(r8, r3, r7)
            goto L_0x03f2
        L_0x0393:
            if (r12 != 0) goto L_0x03c3
            com.google.android.gms.measurement.internal.zzz r2 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r2 = r2.zzs
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzj()
            com.google.android.gms.measurement.internal.zzz r3 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r3 = r3.zzs
            com.google.android.gms.measurement.internal.zzeg r3 = r3.zzj()
            java.lang.String r3 = r3.zzd(r8)
            com.google.android.gms.measurement.internal.zzz r5 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r5 = r5.zzs
            com.google.android.gms.measurement.internal.zzeg r5 = r5.zzj()
            java.lang.String r5 = r5.zze(r11)
            java.lang.String r7 = "Missing param for filter. event, param"
            r2.zzc(r7, r3, r5)
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r6)
            goto L_0x03f2
        L_0x03c3:
            com.google.android.gms.measurement.internal.zzz r2 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r2 = r2.zzs
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzk()
            com.google.android.gms.measurement.internal.zzz r3 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r3 = r3.zzs
            com.google.android.gms.measurement.internal.zzeg r3 = r3.zzj()
            java.lang.String r3 = r3.zzd(r8)
            com.google.android.gms.measurement.internal.zzz r7 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r7 = r7.zzs
            com.google.android.gms.measurement.internal.zzeg r7 = r7.zzj()
            java.lang.String r7 = r7.zze(r11)
            java.lang.String r8 = "Unknown param type. event, param"
            r2.zzc(r8, r3, r7)
            goto L_0x03f2
        L_0x03ed:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r9)
        L_0x03f2:
            com.google.android.gms.measurement.internal.zzz r2 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r2 = r2.zzs
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzj()
            if (r5 != 0) goto L_0x0403
            java.lang.String r3 = "null"
            goto L_0x0404
        L_0x0403:
            r3 = r5
        L_0x0404:
            java.lang.String r7 = "Event filter result"
            r2.zzb(r7, r3)
            if (r5 != 0) goto L_0x040c
            return r6
        L_0x040c:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r9)
            r0.zzd = r2
            boolean r3 = r5.booleanValue()
            if (r3 != 0) goto L_0x0419
            return r9
        L_0x0419:
            r0.zze = r2
            if (r4 == 0) goto L_0x0455
            boolean r2 = r18.zzu()
            if (r2 == 0) goto L_0x0455
            long r2 = r18.zzd()
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            com.google.android.gms.internal.measurement.zzej r3 = r0.zzh
            boolean r3 = r3.zzm()
            if (r3 == 0) goto L_0x0445
            if (r1 == 0) goto L_0x0441
            com.google.android.gms.internal.measurement.zzej r1 = r0.zzh
            boolean r1 = r1.zzo()
            if (r1 != 0) goto L_0x043e
            goto L_0x0441
        L_0x043e:
            r2 = r16
            goto L_0x0442
        L_0x0441:
        L_0x0442:
            r0.zzg = r2
            goto L_0x0455
        L_0x0445:
            if (r1 == 0) goto L_0x0452
            com.google.android.gms.internal.measurement.zzej r1 = r0.zzh
            boolean r1 = r1.zzo()
            if (r1 == 0) goto L_0x0452
            r2 = r17
            goto L_0x0453
        L_0x0452:
        L_0x0453:
            r0.zzf = r2
        L_0x0455:
            return r9
        L_0x0456:
            com.google.android.gms.measurement.internal.zzz r1 = r0.zza
            com.google.android.gms.measurement.internal.zzfv r1 = r1.zzs
            com.google.android.gms.measurement.internal.zzel r1 = r1.zzay()
            com.google.android.gms.measurement.internal.zzej r1 = r1.zzk()
            java.lang.String r2 = r0.zzb
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzel.zzn(r2)
            com.google.android.gms.internal.measurement.zzej r3 = r0.zzh
            boolean r3 = r3.zzp()
            if (r3 == 0) goto L_0x047b
            com.google.android.gms.internal.measurement.zzej r3 = r0.zzh
            int r3 = r3.zzb()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)
            goto L_0x047c
        L_0x047b:
        L_0x047c:
            java.lang.String r3 = java.lang.String.valueOf(r5)
            java.lang.String r4 = "Invalid event filter ID. appId, id"
            r1.zzc(r4, r2, r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzw.zzd(java.lang.Long, java.lang.Long, com.google.android.gms.internal.measurement.zzfo, long, com.google.android.gms.measurement.internal.zzap, boolean):boolean");
    }
}
