package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzes;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzy extends zzx {
    final /* synthetic */ zzz zza;
    private final zzes zzh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzy(zzz zzz, String str, int i, zzes zzes) {
        super(str, i);
        this.zza = zzz;
        this.zzh = zzes;
    }

    /* access modifiers changed from: package-private */
    public final int zza() {
        return this.zzh.zza();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzb() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzc() {
        return true;
    }

    /* JADX WARNING: type inference failed for: r2v18, types: [java.lang.Integer] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzd(java.lang.Long r11, java.lang.Long r12, com.google.android.gms.internal.measurement.zzgh r13, boolean r14) {
        /*
            r10 = this;
            com.google.android.gms.internal.measurement.zzoa.zzc()
            com.google.android.gms.measurement.internal.zzz r0 = r10.zza
            com.google.android.gms.measurement.internal.zzfv r0 = r0.zzs
            com.google.android.gms.measurement.internal.zzaf r0 = r0.zzf()
            java.lang.String r1 = r10.zzb
            com.google.android.gms.measurement.internal.zzdx<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzdy.zzW
            boolean r0 = r0.zzs(r1, r2)
            com.google.android.gms.internal.measurement.zzes r1 = r10.zzh
            boolean r1 = r1.zzg()
            com.google.android.gms.internal.measurement.zzes r2 = r10.zzh
            boolean r2 = r2.zzh()
            com.google.android.gms.internal.measurement.zzes r3 = r10.zzh
            boolean r3 = r3.zzi()
            r4 = 0
            r5 = 1
            if (r1 != 0) goto L_0x0031
            if (r2 != 0) goto L_0x0031
            if (r3 == 0) goto L_0x002f
            r1 = r5
            goto L_0x0032
        L_0x002f:
            r1 = r4
            goto L_0x0032
        L_0x0031:
            r1 = r5
        L_0x0032:
            r2 = 0
            if (r14 == 0) goto L_0x0064
            if (r1 != 0) goto L_0x0064
            com.google.android.gms.measurement.internal.zzz r11 = r10.zza
            com.google.android.gms.measurement.internal.zzfv r11 = r11.zzs
            com.google.android.gms.measurement.internal.zzel r11 = r11.zzay()
            com.google.android.gms.measurement.internal.zzej r11 = r11.zzj()
            int r12 = r10.zzc
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            com.google.android.gms.internal.measurement.zzes r13 = r10.zzh
            boolean r13 = r13.zzj()
            if (r13 == 0) goto L_0x005c
            com.google.android.gms.internal.measurement.zzes r13 = r10.zzh
            int r13 = r13.zza()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r13)
            goto L_0x005d
        L_0x005c:
        L_0x005d:
            java.lang.String r13 = "Property filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID"
            r11.zzc(r13, r12, r2)
            return r5
        L_0x0064:
            com.google.android.gms.internal.measurement.zzes r6 = r10.zzh
            com.google.android.gms.internal.measurement.zzel r6 = r6.zzb()
            boolean r7 = r6.zzg()
            boolean r8 = r13.zzr()
            if (r8 == 0) goto L_0x00af
            boolean r8 = r6.zzi()
            if (r8 != 0) goto L_0x009d
            com.google.android.gms.measurement.internal.zzz r6 = r10.zza
            com.google.android.gms.measurement.internal.zzfv r6 = r6.zzs
            com.google.android.gms.measurement.internal.zzel r6 = r6.zzay()
            com.google.android.gms.measurement.internal.zzej r6 = r6.zzk()
            com.google.android.gms.measurement.internal.zzz r7 = r10.zza
            com.google.android.gms.measurement.internal.zzfv r7 = r7.zzs
            com.google.android.gms.measurement.internal.zzeg r7 = r7.zzj()
            java.lang.String r8 = r13.zzf()
            java.lang.String r7 = r7.zzf(r8)
            java.lang.String r8 = "No number filter for long property. property"
            r6.zzb(r8, r7)
            goto L_0x01a0
        L_0x009d:
            long r8 = r13.zzb()
            com.google.android.gms.internal.measurement.zzeq r2 = r6.zzc()
            java.lang.Boolean r2 = zzh(r8, r2)
            java.lang.Boolean r2 = zzj(r2, r7)
            goto L_0x01a0
        L_0x00af:
            boolean r8 = r13.zzq()
            if (r8 == 0) goto L_0x00f0
            boolean r8 = r6.zzi()
            if (r8 != 0) goto L_0x00de
            com.google.android.gms.measurement.internal.zzz r6 = r10.zza
            com.google.android.gms.measurement.internal.zzfv r6 = r6.zzs
            com.google.android.gms.measurement.internal.zzel r6 = r6.zzay()
            com.google.android.gms.measurement.internal.zzej r6 = r6.zzk()
            com.google.android.gms.measurement.internal.zzz r7 = r10.zza
            com.google.android.gms.measurement.internal.zzfv r7 = r7.zzs
            com.google.android.gms.measurement.internal.zzeg r7 = r7.zzj()
            java.lang.String r8 = r13.zzf()
            java.lang.String r7 = r7.zzf(r8)
            java.lang.String r8 = "No number filter for double property. property"
            r6.zzb(r8, r7)
            goto L_0x01a0
        L_0x00de:
            double r8 = r13.zza()
            com.google.android.gms.internal.measurement.zzeq r2 = r6.zzc()
            java.lang.Boolean r2 = zzg(r8, r2)
            java.lang.Boolean r2 = zzj(r2, r7)
            goto L_0x01a0
        L_0x00f0:
            boolean r8 = r13.zzt()
            if (r8 == 0) goto L_0x017f
            boolean r8 = r6.zzk()
            if (r8 != 0) goto L_0x0166
            boolean r8 = r6.zzi()
            if (r8 != 0) goto L_0x0125
            com.google.android.gms.measurement.internal.zzz r6 = r10.zza
            com.google.android.gms.measurement.internal.zzfv r6 = r6.zzs
            com.google.android.gms.measurement.internal.zzel r6 = r6.zzay()
            com.google.android.gms.measurement.internal.zzej r6 = r6.zzk()
            com.google.android.gms.measurement.internal.zzz r7 = r10.zza
            com.google.android.gms.measurement.internal.zzfv r7 = r7.zzs
            com.google.android.gms.measurement.internal.zzeg r7 = r7.zzj()
            java.lang.String r8 = r13.zzf()
            java.lang.String r7 = r7.zzf(r8)
            java.lang.String r8 = "No string or number filter defined. property"
            r6.zzb(r8, r7)
            goto L_0x01a0
        L_0x0125:
            java.lang.String r8 = r13.zzg()
            boolean r8 = com.google.android.gms.measurement.internal.zzku.zzy(r8)
            if (r8 == 0) goto L_0x0140
            java.lang.String r2 = r13.zzg()
            com.google.android.gms.internal.measurement.zzeq r6 = r6.zzc()
            java.lang.Boolean r2 = zzi(r2, r6)
            java.lang.Boolean r2 = zzj(r2, r7)
            goto L_0x01a0
        L_0x0140:
            com.google.android.gms.measurement.internal.zzz r6 = r10.zza
            com.google.android.gms.measurement.internal.zzfv r6 = r6.zzs
            com.google.android.gms.measurement.internal.zzel r6 = r6.zzay()
            com.google.android.gms.measurement.internal.zzej r6 = r6.zzk()
            com.google.android.gms.measurement.internal.zzz r7 = r10.zza
            com.google.android.gms.measurement.internal.zzfv r7 = r7.zzs
            com.google.android.gms.measurement.internal.zzeg r7 = r7.zzj()
            java.lang.String r8 = r13.zzf()
            java.lang.String r7 = r7.zzf(r8)
            java.lang.String r8 = r13.zzg()
            java.lang.String r9 = "Invalid user property value for Numeric number filter. property, value"
            r6.zzc(r9, r7, r8)
            goto L_0x01a0
        L_0x0166:
            java.lang.String r2 = r13.zzg()
            com.google.android.gms.internal.measurement.zzex r6 = r6.zzd()
            com.google.android.gms.measurement.internal.zzz r8 = r10.zza
            com.google.android.gms.measurement.internal.zzfv r8 = r8.zzs
            com.google.android.gms.measurement.internal.zzel r8 = r8.zzay()
            java.lang.Boolean r2 = zzf(r2, r6, r8)
            java.lang.Boolean r2 = zzj(r2, r7)
            goto L_0x01a0
        L_0x017f:
            com.google.android.gms.measurement.internal.zzz r6 = r10.zza
            com.google.android.gms.measurement.internal.zzfv r6 = r6.zzs
            com.google.android.gms.measurement.internal.zzel r6 = r6.zzay()
            com.google.android.gms.measurement.internal.zzej r6 = r6.zzk()
            com.google.android.gms.measurement.internal.zzz r7 = r10.zza
            com.google.android.gms.measurement.internal.zzfv r7 = r7.zzs
            com.google.android.gms.measurement.internal.zzeg r7 = r7.zzj()
            java.lang.String r8 = r13.zzf()
            java.lang.String r7 = r7.zzf(r8)
            java.lang.String r8 = "User property has no value, property"
            r6.zzb(r8, r7)
        L_0x01a0:
            com.google.android.gms.measurement.internal.zzz r6 = r10.zza
            com.google.android.gms.measurement.internal.zzfv r6 = r6.zzs
            com.google.android.gms.measurement.internal.zzel r6 = r6.zzay()
            com.google.android.gms.measurement.internal.zzej r6 = r6.zzj()
            if (r2 != 0) goto L_0x01b1
            java.lang.String r7 = "null"
            goto L_0x01b2
        L_0x01b1:
            r7 = r2
        L_0x01b2:
            java.lang.String r8 = "Property filter result"
            r6.zzb(r8, r7)
            if (r2 != 0) goto L_0x01bb
            return r4
        L_0x01bb:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r5)
            r10.zzd = r4
            if (r3 == 0) goto L_0x01cb
            boolean r3 = r2.booleanValue()
            if (r3 == 0) goto L_0x01ca
            goto L_0x01cb
        L_0x01ca:
            return r5
        L_0x01cb:
            if (r14 == 0) goto L_0x01d5
            com.google.android.gms.internal.measurement.zzes r14 = r10.zzh
            boolean r14 = r14.zzg()
            if (r14 == 0) goto L_0x01d7
        L_0x01d5:
            r10.zze = r2
        L_0x01d7:
            boolean r14 = r2.booleanValue()
            if (r14 == 0) goto L_0x0220
            if (r1 == 0) goto L_0x0220
            boolean r14 = r13.zzs()
            if (r14 == 0) goto L_0x0220
            long r13 = r13.zzc()
            if (r11 == 0) goto L_0x01f0
            long r13 = r11.longValue()
            goto L_0x01f1
        L_0x01f0:
        L_0x01f1:
            if (r0 == 0) goto L_0x020a
            com.google.android.gms.internal.measurement.zzes r11 = r10.zzh
            boolean r11 = r11.zzg()
            if (r11 == 0) goto L_0x020a
            com.google.android.gms.internal.measurement.zzes r11 = r10.zzh
            boolean r11 = r11.zzh()
            if (r11 != 0) goto L_0x020a
            if (r12 == 0) goto L_0x020a
            long r13 = r12.longValue()
            goto L_0x020b
        L_0x020a:
        L_0x020b:
            com.google.android.gms.internal.measurement.zzes r11 = r10.zzh
            boolean r11 = r11.zzh()
            if (r11 == 0) goto L_0x021a
            java.lang.Long r11 = java.lang.Long.valueOf(r13)
            r10.zzg = r11
            goto L_0x0220
        L_0x021a:
            java.lang.Long r11 = java.lang.Long.valueOf(r13)
            r10.zzf = r11
        L_0x0220:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzy.zzd(java.lang.Long, java.lang.Long, com.google.android.gms.internal.measurement.zzgh, boolean):boolean");
    }
}
