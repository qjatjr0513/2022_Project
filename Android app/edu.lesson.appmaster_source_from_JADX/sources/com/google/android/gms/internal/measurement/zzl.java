package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzl extends zzam {
    private final zzab zzb;

    public zzl(zzab zzab) {
        this.zzb = zzab;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzap zzbK(java.lang.String r11, com.google.android.gms.internal.measurement.zzg r12, java.util.List<com.google.android.gms.internal.measurement.zzap> r13) {
        /*
            r10 = this;
            int r0 = r11.hashCode()
            r1 = 2
            java.lang.String r2 = "setEventName"
            java.lang.String r3 = "setParamValue"
            java.lang.String r4 = "getParams"
            java.lang.String r5 = "getParamValue"
            java.lang.String r6 = "getTimestamp"
            java.lang.String r7 = "getEventName"
            r8 = 1
            r9 = 0
            switch(r0) {
                case 21624207: goto L_0x0040;
                case 45521504: goto L_0x0038;
                case 146575578: goto L_0x0030;
                case 700587132: goto L_0x0028;
                case 920706790: goto L_0x0020;
                case 1570616835: goto L_0x0017;
                default: goto L_0x0016;
            }
        L_0x0016:
            goto L_0x0048
        L_0x0017:
            boolean r0 = r11.equals(r2)
            if (r0 == 0) goto L_0x0016
            r0 = 4
            goto L_0x0049
        L_0x0020:
            boolean r0 = r11.equals(r3)
            if (r0 == 0) goto L_0x0016
            r0 = 5
            goto L_0x0049
        L_0x0028:
            boolean r0 = r11.equals(r4)
            if (r0 == 0) goto L_0x0016
            r0 = r1
            goto L_0x0049
        L_0x0030:
            boolean r0 = r11.equals(r5)
            if (r0 == 0) goto L_0x0016
            r0 = r8
            goto L_0x0049
        L_0x0038:
            boolean r0 = r11.equals(r6)
            if (r0 == 0) goto L_0x0016
            r0 = 3
            goto L_0x0049
        L_0x0040:
            boolean r0 = r11.equals(r7)
            if (r0 == 0) goto L_0x0016
            r0 = r9
            goto L_0x0049
        L_0x0048:
            r0 = -1
        L_0x0049:
            switch(r0) {
                case 0: goto L_0x0125;
                case 1: goto L_0x0104;
                case 2: goto L_0x00d1;
                case 3: goto L_0x00b8;
                case 4: goto L_0x007b;
                case 5: goto L_0x0051;
                default: goto L_0x004c;
            }
        L_0x004c:
            com.google.android.gms.internal.measurement.zzap r11 = super.zzbK(r11, r12, r13)
            return r11
        L_0x0051:
            com.google.android.gms.internal.measurement.zzh.zzh(r3, r1, r13)
            java.lang.Object r11 = r13.get(r9)
            com.google.android.gms.internal.measurement.zzap r11 = (com.google.android.gms.internal.measurement.zzap) r11
            com.google.android.gms.internal.measurement.zzap r11 = r12.zzb(r11)
            java.lang.String r11 = r11.zzi()
            java.lang.Object r13 = r13.get(r8)
            com.google.android.gms.internal.measurement.zzap r13 = (com.google.android.gms.internal.measurement.zzap) r13
            com.google.android.gms.internal.measurement.zzap r12 = r12.zzb(r13)
            com.google.android.gms.internal.measurement.zzab r13 = r10.zzb
            com.google.android.gms.internal.measurement.zzaa r13 = r13.zzb()
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzh.zzf(r12)
            r13.zzg(r11, r0)
            return r12
        L_0x007b:
            com.google.android.gms.internal.measurement.zzh.zzh(r2, r8, r13)
            java.lang.Object r11 = r13.get(r9)
            com.google.android.gms.internal.measurement.zzap r11 = (com.google.android.gms.internal.measurement.zzap) r11
            com.google.android.gms.internal.measurement.zzap r11 = r12.zzb(r11)
            com.google.android.gms.internal.measurement.zzap r12 = zzf
            boolean r12 = r12.equals(r11)
            if (r12 != 0) goto L_0x00b0
            com.google.android.gms.internal.measurement.zzap r12 = zzg
            boolean r12 = r12.equals(r11)
            if (r12 != 0) goto L_0x00b0
            com.google.android.gms.internal.measurement.zzab r12 = r10.zzb
            com.google.android.gms.internal.measurement.zzaa r12 = r12.zzb()
            java.lang.String r13 = r11.zzi()
            r12.zzf(r13)
            com.google.android.gms.internal.measurement.zzat r12 = new com.google.android.gms.internal.measurement.zzat
            java.lang.String r11 = r11.zzi()
            r12.<init>(r11)
            return r12
        L_0x00b0:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            java.lang.String r12 = "Illegal event name"
            r11.<init>(r12)
            throw r11
        L_0x00b8:
            com.google.android.gms.internal.measurement.zzh.zzh(r6, r9, r13)
            com.google.android.gms.internal.measurement.zzab r11 = r10.zzb
            com.google.android.gms.internal.measurement.zzaa r11 = r11.zzb()
            com.google.android.gms.internal.measurement.zzah r12 = new com.google.android.gms.internal.measurement.zzah
            long r0 = r11.zza()
            double r0 = (double) r0
            java.lang.Double r11 = java.lang.Double.valueOf(r0)
            r12.<init>(r11)
            return r12
        L_0x00d1:
            com.google.android.gms.internal.measurement.zzh.zzh(r4, r9, r13)
            com.google.android.gms.internal.measurement.zzab r11 = r10.zzb
            com.google.android.gms.internal.measurement.zzaa r11 = r11.zzb()
            java.util.Map r11 = r11.zze()
            com.google.android.gms.internal.measurement.zzam r12 = new com.google.android.gms.internal.measurement.zzam
            r12.<init>()
            java.util.Set r13 = r11.keySet()
            java.util.Iterator r13 = r13.iterator()
        L_0x00eb:
            boolean r0 = r13.hasNext()
            if (r0 == 0) goto L_0x0103
            java.lang.Object r0 = r13.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r1 = r11.get(r0)
            com.google.android.gms.internal.measurement.zzap r1 = com.google.android.gms.internal.measurement.zzi.zzb(r1)
            r12.zzr(r0, r1)
            goto L_0x00eb
        L_0x0103:
            return r12
        L_0x0104:
            com.google.android.gms.internal.measurement.zzh.zzh(r5, r8, r13)
            java.lang.Object r11 = r13.get(r9)
            com.google.android.gms.internal.measurement.zzap r11 = (com.google.android.gms.internal.measurement.zzap) r11
            com.google.android.gms.internal.measurement.zzap r11 = r12.zzb(r11)
            java.lang.String r11 = r11.zzi()
            com.google.android.gms.internal.measurement.zzab r12 = r10.zzb
            com.google.android.gms.internal.measurement.zzaa r12 = r12.zzb()
            java.lang.Object r11 = r12.zzc(r11)
            com.google.android.gms.internal.measurement.zzap r11 = com.google.android.gms.internal.measurement.zzi.zzb(r11)
            return r11
        L_0x0125:
            com.google.android.gms.internal.measurement.zzh.zzh(r7, r9, r13)
            com.google.android.gms.internal.measurement.zzab r11 = r10.zzb
            com.google.android.gms.internal.measurement.zzaa r11 = r11.zzb()
            com.google.android.gms.internal.measurement.zzat r12 = new com.google.android.gms.internal.measurement.zzat
            java.lang.String r11 = r11.zzd()
            r12.<init>(r11)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzl.zzbK(java.lang.String, com.google.android.gms.internal.measurement.zzg, java.util.List):com.google.android.gms.internal.measurement.zzap");
    }
}
