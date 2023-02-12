package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzbb {
    /* JADX WARNING: type inference failed for: r1v89, types: [com.google.android.gms.internal.measurement.zzap] */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0215, code lost:
        if (r0 <= r24.zzc()) goto L_0x021b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0217, code lost:
        r0 = r24.zzc();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x021b, code lost:
        r2 = r24.zzc();
        r3 = new com.google.android.gms.internal.measurement.zzae();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0229, code lost:
        if (r26.size() <= 1) goto L_0x0292;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x022b, code lost:
        r4 = java.lang.Math.max(0, (int) com.google.android.gms.internal.measurement.zzh.zza(r6.zzb(r1.get(1)).zzh().doubleValue()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0247, code lost:
        if (r4 <= 0) goto L_0x0263;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0249, code lost:
        r5 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0250, code lost:
        if (r5 >= java.lang.Math.min(r2, r0 + r4)) goto L_0x0263;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0252, code lost:
        r3.zzq(r3.zzc(), r9.zze(r0));
        r9.zzp(r0);
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0268, code lost:
        if (r26.size() <= 2) goto L_0x02a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x026a, code lost:
        r2 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x026f, code lost:
        if (r2 >= r26.size()) goto L_0x02a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0271, code lost:
        r4 = r6.zzb(r1.get(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x027d, code lost:
        if ((r4 instanceof com.google.android.gms.internal.measurement.zzag) != false) goto L_0x0289;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x027f, code lost:
        r9.zzo((r0 + r2) - 2, r4);
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0290, code lost:
        throw new java.lang.IllegalArgumentException("Failed to parse elements to add");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0292, code lost:
        if (r0 >= r2) goto L_0x02a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0294, code lost:
        r3.zzq(r3.zzc(), r9.zze(r0));
        r9.zzq(r0, (com.google.android.gms.internal.measurement.zzap) null);
        r0 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x02a9, code lost:
        r9 = r24;
        r6 = r25;
        r1 = r26;
        com.google.android.gms.internal.measurement.zzh.zzj("sort", 1, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x02b8, code lost:
        if (r24.zzc() >= 2) goto L_0x02bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x02bb, code lost:
        r0 = r24.zzm();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x02c3, code lost:
        if (r26.isEmpty() != false) goto L_0x02e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x02c5, code lost:
        r1 = r6.zzb(r1.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x02d2, code lost:
        if ((r1 instanceof com.google.android.gms.internal.measurement.zzai) == false) goto L_0x02d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x02d4, code lost:
        r3 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x02df, code lost:
        throw new java.lang.IllegalArgumentException("Comparator should be a method");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x02e0, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x02e1, code lost:
        java.util.Collections.sort(r0, new com.google.android.gms.internal.measurement.zzba(r3, r6));
        r24.zzn();
        r0 = r0.iterator();
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x02f5, code lost:
        if (r0.hasNext() == false) goto L_0x0304;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x02f7, code lost:
        r9.zzq(r2, r0.next());
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0304, code lost:
        return r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0305, code lost:
        r9 = r24;
        r6 = r25;
        r1 = r26;
        com.google.android.gms.internal.measurement.zzh.zzh("some", 1, r1);
        r0 = r6.zzb(r1.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x031c, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzai) == false) goto L_0x0375;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0322, code lost:
        if (r24.zzc() != 0) goto L_0x0327;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0327, code lost:
        r0 = (com.google.android.gms.internal.measurement.zzai) r0;
        r1 = r24.zzk();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0331, code lost:
        if (r1.hasNext() == false) goto L_0x0372;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0333, code lost:
        r2 = r1.next().intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x0341, code lost:
        if (r9.zzs(r2) == false) goto L_0x032d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x036d, code lost:
        if (r0.zza(r6, java.util.Arrays.asList(new com.google.android.gms.internal.measurement.zzap[]{r9.zze(r2), new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf((double) r2)), r9})).zzg().booleanValue() == false) goto L_0x032d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x037a, code lost:
        throw new java.lang.IllegalArgumentException("Callback should be a method");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x037b, code lost:
        r9 = r24;
        r6 = r25;
        r1 = r26;
        com.google.android.gms.internal.measurement.zzh.zzj("slice", 2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0389, code lost:
        if (r26.isEmpty() == false) goto L_0x0391;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0391, code lost:
        r7 = (double) r24.zzc();
        r10 = com.google.android.gms.internal.measurement.zzh.zza(r6.zzb(r1.get(0)).zzh().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x03af, code lost:
        if (r10 >= 0.0d) goto L_0x03b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x03b1, code lost:
        r10 = java.lang.Math.max(r10 + r7, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x03b7, code lost:
        r10 = java.lang.Math.min(r10, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x03c0, code lost:
        if (r26.size() != 2) goto L_0x03e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x03c2, code lost:
        r0 = com.google.android.gms.internal.measurement.zzh.zza(r6.zzb(r1.get(1)).zzh().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x03db, code lost:
        if (r0 >= 0.0d) goto L_0x03e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x03dd, code lost:
        r7 = java.lang.Math.max(r7 + r0, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x03e3, code lost:
        r7 = java.lang.Math.min(r7, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x03e9, code lost:
        r0 = new com.google.android.gms.internal.measurement.zzae();
        r1 = (int) r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x03f2, code lost:
        if (((double) r1) >= r7) goto L_0x0403;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x03f4, code lost:
        r0.zzq(r0.zzc(), r9.zze(r1));
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0404, code lost:
        r9 = r24;
        com.google.android.gms.internal.measurement.zzh.zzh("shift", 0, r26);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x0410, code lost:
        if (r24.zzc() != 0) goto L_0x0415;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0415, code lost:
        r1 = r9.zze(0);
        r9.zzp(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x041e, code lost:
        r9 = r24;
        com.google.android.gms.internal.measurement.zzh.zzh("reverse", 0, r26);
        r0 = r24.zzc();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x042a, code lost:
        if (r0 == 0) goto L_0x0455;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x042c, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x042f, code lost:
        if (r2 >= (r0 / 2)) goto L_0x0455;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x0435, code lost:
        if (r9.zzs(r2) == false) goto L_0x0452;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x0437, code lost:
        r1 = r9.zze(r2);
        r9.zzq(r2, (com.google.android.gms.internal.measurement.zzap) null);
        r3 = (r0 - 1) - r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x0446, code lost:
        if (r9.zzs(r3) == false) goto L_0x044f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x0448, code lost:
        r9.zzq(r2, r9.zze(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x044f, code lost:
        r9.zzq(r3, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x0452, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x0455, code lost:
        return r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x0461, code lost:
        return zzc(r24, r25, r26, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x046d, code lost:
        return zzc(r24, r25, r26, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x046e, code lost:
        r9 = r24;
        r6 = r25;
        r1 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x0478, code lost:
        if (r26.isEmpty() != false) goto L_0x0496;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x047a, code lost:
        r0 = r26.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x0482, code lost:
        if (r0.hasNext() == false) goto L_0x0496;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x0484, code lost:
        r9.zzq(r24.zzc(), r6.zzb(r0.next()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x04a4, code lost:
        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf((double) r24.zzc()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x04a5, code lost:
        r9 = r24;
        com.google.android.gms.internal.measurement.zzh.zzh("pop", 0, r26);
        r0 = r24.zzc();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x04b1, code lost:
        if (r0 != 0) goto L_0x04b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x04b6, code lost:
        r0 = r0 - 1;
        r1 = r9.zze(r0);
        r9.zzp(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x04c1, code lost:
        r9 = r24;
        r6 = r25;
        r1 = r26;
        com.google.android.gms.internal.measurement.zzh.zzh("map", 1, r1);
        r0 = r6.zzb(r1.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x04d8, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzao) == false) goto L_0x04ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x04de, code lost:
        if (r24.zzc() != 0) goto L_0x04e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x04f3, code lost:
        throw new java.lang.IllegalArgumentException("Callback should be a method");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x04f4, code lost:
        r9 = r24;
        r6 = r25;
        r1 = r26;
        com.google.android.gms.internal.measurement.zzh.zzj("lastIndexOf", 2, r1);
        r0 = com.google.android.gms.internal.measurement.zzap.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x0504, code lost:
        if (r26.isEmpty() != false) goto L_0x0511;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x0506, code lost:
        r0 = r6.zzb(r1.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x0511, code lost:
        r7 = (double) (r24.zzc() - 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x051d, code lost:
        if (r26.size() <= 1) goto L_0x0559;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x051f, code lost:
        r1 = r6.zzb(r1.get(1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x0535, code lost:
        if (java.lang.Double.isNaN(r1.zzh().doubleValue()) == false) goto L_0x0540;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x0537, code lost:
        r7 = (double) (r24.zzc() - 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x0540, code lost:
        r7 = com.google.android.gms.internal.measurement.zzh.zza(r1.zzh().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x054f, code lost:
        if (r7 >= 0.0d) goto L_0x0559;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x0551, code lost:
        r7 = r7 + ((double) r24.zzc());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x055b, code lost:
        if (r7 >= 0.0d) goto L_0x0567;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x0567, code lost:
        r1 = (int) java.lang.Math.min((double) r24.zzc(), r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x0571, code lost:
        if (r1 < 0) goto L_0x0591;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x0577, code lost:
        if (r9.zzs(r1) == false) goto L_0x058e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x0581, code lost:
        if (com.google.android.gms.internal.measurement.zzh.zzl(r9.zze(r1), r0) == false) goto L_0x058e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x058e, code lost:
        r1 = r1 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x059b, code lost:
        r9 = r24;
        r6 = r25;
        r1 = r26;
        com.google.android.gms.internal.measurement.zzh.zzj("join", 1, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x05a9, code lost:
        if (r24.zzc() != 0) goto L_0x05ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x05b2, code lost:
        if (r26.size() <= 0) goto L_0x05d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x05b4, code lost:
        r0 = r6.zzb(r1.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x05c1, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzan) != false) goto L_0x05cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x05c5, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzau) == false) goto L_0x05c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x05c8, code lost:
        r0 = r0.zzi();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:247:0x05cd, code lost:
        r0 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:0x05d0, code lost:
        r0 = ",";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:0x05dd, code lost:
        r9 = r24;
        r6 = r25;
        r1 = r26;
        com.google.android.gms.internal.measurement.zzh.zzj("indexOf", 2, r1);
        r0 = com.google.android.gms.internal.measurement.zzap.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x05ed, code lost:
        if (r26.isEmpty() != false) goto L_0x05fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:0x05ef, code lost:
        r0 = r6.zzb(r1.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:254:0x05ff, code lost:
        if (r26.size() <= 1) goto L_0x0638;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x0601, code lost:
        r1 = com.google.android.gms.internal.measurement.zzh.zza(r6.zzb(r1.get(1)).zzh().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x061e, code lost:
        if (r1 < ((double) r24.zzc())) goto L_0x062a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:0x062c, code lost:
        if (r1 >= 0.0d) goto L_0x0635;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:260:0x062e, code lost:
        r3 = ((double) r24.zzc()) + r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:261:0x0635, code lost:
        r3 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:262:0x0638, code lost:
        r1 = r24.zzk();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x0640, code lost:
        if (r1.hasNext() == false) goto L_0x0665;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:0x0642, code lost:
        r2 = r1.next().intValue();
        r5 = (double) r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:266:0x064f, code lost:
        if (r5 < r3) goto L_0x063c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:268:0x0659, code lost:
        if (com.google.android.gms.internal.measurement.zzh.zzl(r9.zze(r2), r0) == false) goto L_0x063c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:0x066f, code lost:
        r9 = r24;
        r6 = r25;
        r1 = r26;
        com.google.android.gms.internal.measurement.zzh.zzh(r21, 1, r1);
        r0 = r6.zzb(r1.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:272:0x0688, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzao) == false) goto L_0x069c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:274:0x068e, code lost:
        if (r24.zzb() != 0) goto L_0x0693;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:276:0x0693, code lost:
        zzb(r9, r6, (com.google.android.gms.internal.measurement.zzao) r0, (java.lang.Boolean) null, (java.lang.Boolean) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:278:0x06a1, code lost:
        throw new java.lang.IllegalArgumentException("Callback should be a method");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:279:0x06a2, code lost:
        r9 = r24;
        r6 = r25;
        r0 = r26;
        com.google.android.gms.internal.measurement.zzh.zzh(r22, 1, r0);
        r0 = r6.zzb(r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:280:0x06bb, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzao) == false) goto L_0x06fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:282:0x06c1, code lost:
        if (r24.zzb() != 0) goto L_0x06c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:284:0x06c9, code lost:
        r2 = r24.zzd();
        r0 = zzb(r9, r6, (com.google.android.gms.internal.measurement.zzao) r0, (java.lang.Boolean) null, true);
        r1 = new com.google.android.gms.internal.measurement.zzae();
        r0 = r0.zzk();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:286:0x06e1, code lost:
        if (r0.hasNext() == false) goto L_0x06fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:287:0x06e3, code lost:
        r1.zzq(r1.zzc(), ((com.google.android.gms.internal.measurement.zzae) r2).zze(r0.next().intValue()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:290:0x0703, code lost:
        throw new java.lang.IllegalArgumentException("Callback should be a method");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:291:0x0704, code lost:
        r3 = r24;
        r6 = r25;
        r0 = r26;
        com.google.android.gms.internal.measurement.zzh.zzh("every", 1, r0);
        r0 = r6.zzb(r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:292:0x071b, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzao) == false) goto L_0x0741;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:294:0x0721, code lost:
        if (r24.zzc() != 0) goto L_0x0726;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:297:0x0739, code lost:
        if (zzb(r3, r6, (com.google.android.gms.internal.measurement.zzao) r0, false, true).zzc() == r24.zzc()) goto L_0x073e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:301:0x0746, code lost:
        throw new java.lang.IllegalArgumentException("Callback should be a method");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:302:0x0747, code lost:
        r3 = r24;
        r6 = r25;
        r0 = r26;
        r1 = r24.zzd();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:303:0x0755, code lost:
        if (r26.isEmpty() != false) goto L_0x07aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:304:0x0757, code lost:
        r0 = r26.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:306:0x075f, code lost:
        if (r0.hasNext() == false) goto L_0x07aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:307:0x0761, code lost:
        r2 = r6.zzb(r0.next());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:308:0x076d, code lost:
        if ((r2 instanceof com.google.android.gms.internal.measurement.zzag) != false) goto L_0x07a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:309:0x076f, code lost:
        r3 = (com.google.android.gms.internal.measurement.zzae) r1;
        r4 = r3.zzc();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:310:0x0778, code lost:
        if ((r2 instanceof com.google.android.gms.internal.measurement.zzae) == false) goto L_0x079d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:311:0x077a, code lost:
        r2 = (com.google.android.gms.internal.measurement.zzae) r2;
        r5 = r2.zzk();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:313:0x0784, code lost:
        if (r5.hasNext() == false) goto L_0x075b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:314:0x0786, code lost:
        r7 = r5.next();
        r3.zzq(r7.intValue() + r4, r2.zze(r7.intValue()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:315:0x079d, code lost:
        r3.zzq(r4, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:317:0x07a9, code lost:
        throw new java.lang.IllegalStateException("Failed evaluation of arguments");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:318:0x07aa, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:353:?, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:354:?, code lost:
        return new com.google.android.gms.internal.measurement.zzae();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:355:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzl;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:356:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzl;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:357:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzk;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:358:?, code lost:
        return r24.zzd();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:?, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:360:?, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:361:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:362:?, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:363:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:364:?, code lost:
        return zzb(r9, r6, (com.google.android.gms.internal.measurement.zzao) r0, (java.lang.Boolean) null, (java.lang.Boolean) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:365:?, code lost:
        return new com.google.android.gms.internal.measurement.zzae();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:366:?, code lost:
        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf(-1.0d));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:367:?, code lost:
        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf(-1.0d));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:368:?, code lost:
        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf((double) r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:369:?, code lost:
        return new com.google.android.gms.internal.measurement.zzat(r9.zzj(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:370:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzm;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:371:?, code lost:
        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf(-1.0d));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:372:?, code lost:
        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf(-1.0d));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:373:?, code lost:
        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:374:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:375:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:376:?, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:377:?, code lost:
        return new com.google.android.gms.internal.measurement.zzae();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:378:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzk;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:379:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzk;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:380:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzl;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00e3, code lost:
        r2 = r17;
        r4 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00e8, code lost:
        r2 = r17;
        r4 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0107, code lost:
        r2 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0117, code lost:
        r0 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0119, code lost:
        r17 = r2;
        r21 = "forEach";
        r22 = r4;
        r3 = 0.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0125, code lost:
        switch(r0) {
            case 0: goto L_0x0747;
            case 1: goto L_0x0704;
            case 2: goto L_0x06a2;
            case 3: goto L_0x066f;
            case 4: goto L_0x05dd;
            case 5: goto L_0x059b;
            case 6: goto L_0x04f4;
            case 7: goto L_0x04c1;
            case 8: goto L_0x04a5;
            case 9: goto L_0x046e;
            case 10: goto L_0x0462;
            case 11: goto L_0x0456;
            case 12: goto L_0x041e;
            case 13: goto L_0x0404;
            case 14: goto L_0x037b;
            case 15: goto L_0x0305;
            case 16: goto L_0x02a9;
            case 17: goto L_0x01d8;
            case 18: goto L_0x01c2;
            case 19: goto L_0x0130;
            default: goto L_0x0128;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x012f, code lost:
        throw new java.lang.IllegalArgumentException("Command not supported");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0134, code lost:
        if (r26.isEmpty() != false) goto L_0x01b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0136, code lost:
        r0 = new com.google.android.gms.internal.measurement.zzae();
        r1 = r26.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0143, code lost:
        if (r1.hasNext() == false) goto L_0x0165;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0145, code lost:
        r2 = r25.zzb(r1.next());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0153, code lost:
        if ((r2 instanceof com.google.android.gms.internal.measurement.zzag) != false) goto L_0x015d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0155, code lost:
        r0.zzq(r0.zzc(), r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0164, code lost:
        throw new java.lang.IllegalStateException("Argument evaluation failed");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0165, code lost:
        r1 = r0.zzc();
        r2 = r24.zzk();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0171, code lost:
        if (r2.hasNext() == false) goto L_0x018c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0173, code lost:
        r3 = r2.next();
        r0.zzq(r3.intValue() + r1, r24.zze(r3.intValue()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x018c, code lost:
        r9 = r24;
        r24.zzn();
        r1 = r0.zzk();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0199, code lost:
        if (r1.hasNext() == false) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x019b, code lost:
        r2 = r1.next();
        r9.zzq(r2.intValue(), r0.zze(r2.intValue()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01b1, code lost:
        r9 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01c1, code lost:
        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf((double) r24.zzc()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01c2, code lost:
        com.google.android.gms.internal.measurement.zzh.zzh(r17, 0, r26);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01d7, code lost:
        return new com.google.android.gms.internal.measurement.zzat(r24.zzj(","));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01d8, code lost:
        r9 = r24;
        r6 = r25;
        r1 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01e2, code lost:
        if (r26.isEmpty() == false) goto L_0x01eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01eb, code lost:
        r0 = (int) com.google.android.gms.internal.measurement.zzh.zza(r6.zzb(r1.get(0)).zzh().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0204, code lost:
        if (r0 >= 0) goto L_0x0211;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0206, code lost:
        r0 = java.lang.Math.max(0, r0 + r24.zzc());
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.measurement.zzap zza(java.lang.String r23, com.google.android.gms.internal.measurement.zzae r24, com.google.android.gms.internal.measurement.zzg r25, java.util.List<com.google.android.gms.internal.measurement.zzap> r26) {
        /*
            r0 = r23
            r1 = r24
            r2 = r25
            r3 = r26
            int r4 = r23.hashCode()
            java.lang.String r5 = "indexOf"
            java.lang.String r6 = "reverse"
            java.lang.String r7 = "slice"
            java.lang.String r8 = "shift"
            java.lang.String r9 = "every"
            java.lang.String r10 = "sort"
            java.lang.String r11 = "some"
            java.lang.String r12 = "join"
            java.lang.String r13 = "pop"
            java.lang.String r14 = "map"
            java.lang.String r15 = "lastIndexOf"
            java.lang.String r3 = "forEach"
            java.lang.String r1 = "filter"
            java.lang.String r2 = "toString"
            r16 = -1
            r17 = r2
            r2 = 1
            r18 = r1
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r2)
            switch(r4) {
                case -1776922004: goto L_0x010a;
                case -1354795244: goto L_0x00f9;
                case -1274492040: goto L_0x00ed;
                case -934873754: goto L_0x00d9;
                case -895859076: goto L_0x00ce;
                case -678635926: goto L_0x00c6;
                case -467511597: goto L_0x00be;
                case -277637751: goto L_0x00b3;
                case 107868: goto L_0x00ab;
                case 111185: goto L_0x00a2;
                case 3267882: goto L_0x009a;
                case 3452698: goto L_0x008f;
                case 3536116: goto L_0x0086;
                case 3536286: goto L_0x007d;
                case 96891675: goto L_0x0070;
                case 109407362: goto L_0x0066;
                case 109526418: goto L_0x005c;
                case 965561430: goto L_0x0050;
                case 1099846370: goto L_0x0046;
                case 1943291465: goto L_0x003c;
                default: goto L_0x0036;
            }
        L_0x0036:
            r2 = r17
            r4 = r18
            goto L_0x0117
        L_0x003c:
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L_0x00e8
            r0 = 4
            goto L_0x00e3
        L_0x0046:
            boolean r0 = r0.equals(r6)
            if (r0 == 0) goto L_0x00e8
            r0 = 12
            goto L_0x00e3
        L_0x0050:
            java.lang.String r4 = "reduceRight"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x00e8
            r0 = 11
            goto L_0x00e3
        L_0x005c:
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x00e8
            r0 = 14
            goto L_0x00e3
        L_0x0066:
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x00e8
            r0 = 13
            goto L_0x00e3
        L_0x0070:
            boolean r0 = r0.equals(r9)
            if (r0 == 0) goto L_0x00e8
            r2 = r17
            r4 = r18
            r0 = 1
            goto L_0x0119
        L_0x007d:
            boolean r0 = r0.equals(r10)
            if (r0 == 0) goto L_0x00e8
            r0 = 16
            goto L_0x00e3
        L_0x0086:
            boolean r0 = r0.equals(r11)
            if (r0 == 0) goto L_0x00e8
            r0 = 15
            goto L_0x00e3
        L_0x008f:
            java.lang.String r4 = "push"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x00e8
            r0 = 9
            goto L_0x00e3
        L_0x009a:
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x00e8
            r0 = 5
            goto L_0x00e3
        L_0x00a2:
            boolean r0 = r0.equals(r13)
            if (r0 == 0) goto L_0x00e8
            r0 = 8
            goto L_0x00e3
        L_0x00ab:
            boolean r0 = r0.equals(r14)
            if (r0 == 0) goto L_0x00e8
            r0 = 7
            goto L_0x00e3
        L_0x00b3:
            java.lang.String r4 = "unshift"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x00e8
            r0 = 19
            goto L_0x00e3
        L_0x00be:
            boolean r0 = r0.equals(r15)
            if (r0 == 0) goto L_0x00e8
            r0 = 6
            goto L_0x00e3
        L_0x00c6:
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x00e8
            r0 = 3
            goto L_0x00e3
        L_0x00ce:
            java.lang.String r4 = "splice"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x00e8
            r0 = 17
            goto L_0x00e3
        L_0x00d9:
            java.lang.String r4 = "reduce"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x00e8
            r0 = 10
        L_0x00e3:
            r2 = r17
            r4 = r18
            goto L_0x0119
        L_0x00e8:
            r2 = r17
            r4 = r18
            goto L_0x0117
        L_0x00ed:
            r4 = r18
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x0107
            r2 = r17
            r0 = 2
            goto L_0x0119
        L_0x00f9:
            r4 = r18
            java.lang.String r2 = "concat"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0107
            r2 = r17
            r0 = 0
            goto L_0x0119
        L_0x0107:
            r2 = r17
            goto L_0x0117
        L_0x010a:
            r4 = r18
            r2 = r17
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0117
            r0 = 18
            goto L_0x0119
        L_0x0117:
            r0 = r16
        L_0x0119:
            r19 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r17 = r2
            java.lang.String r2 = "Callback should be a method"
            r21 = r3
            r22 = r4
            r3 = 0
            switch(r0) {
                case 0: goto L_0x0747;
                case 1: goto L_0x0704;
                case 2: goto L_0x06a2;
                case 3: goto L_0x066f;
                case 4: goto L_0x05dd;
                case 5: goto L_0x059b;
                case 6: goto L_0x04f4;
                case 7: goto L_0x04c1;
                case 8: goto L_0x04a5;
                case 9: goto L_0x046e;
                case 10: goto L_0x0462;
                case 11: goto L_0x0456;
                case 12: goto L_0x041e;
                case 13: goto L_0x0404;
                case 14: goto L_0x037b;
                case 15: goto L_0x0305;
                case 16: goto L_0x02a9;
                case 17: goto L_0x01d8;
                case 18: goto L_0x01c2;
                case 19: goto L_0x0130;
                default: goto L_0x0128;
            }
        L_0x0128:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Command not supported"
            r0.<init>(r1)
            throw r0
        L_0x0130:
            boolean r0 = r26.isEmpty()
            if (r0 != 0) goto L_0x01b1
            com.google.android.gms.internal.measurement.zzae r0 = new com.google.android.gms.internal.measurement.zzae
            r0.<init>()
            java.util.Iterator r1 = r26.iterator()
        L_0x013f:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0165
            java.lang.Object r2 = r1.next()
            com.google.android.gms.internal.measurement.zzap r2 = (com.google.android.gms.internal.measurement.zzap) r2
            r6 = r25
            com.google.android.gms.internal.measurement.zzap r2 = r6.zzb(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzag
            if (r3 != 0) goto L_0x015d
            int r3 = r0.zzc()
            r0.zzq(r3, r2)
            goto L_0x013f
        L_0x015d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Argument evaluation failed"
            r0.<init>(r1)
            throw r0
        L_0x0165:
            int r1 = r0.zzc()
            java.util.Iterator r2 = r24.zzk()
        L_0x016d:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x018c
            java.lang.Object r3 = r2.next()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r4 = r3.intValue()
            int r4 = r4 + r1
            int r3 = r3.intValue()
            r9 = r24
            com.google.android.gms.internal.measurement.zzap r3 = r9.zze(r3)
            r0.zzq(r4, r3)
            goto L_0x016d
        L_0x018c:
            r9 = r24
            r24.zzn()
            java.util.Iterator r1 = r0.zzk()
        L_0x0195:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x01b3
            java.lang.Object r2 = r1.next()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r3 = r2.intValue()
            int r2 = r2.intValue()
            com.google.android.gms.internal.measurement.zzap r2 = r0.zze(r2)
            r9.zzq(r3, r2)
            goto L_0x0195
        L_0x01b1:
            r9 = r24
        L_0x01b3:
            com.google.android.gms.internal.measurement.zzah r0 = new com.google.android.gms.internal.measurement.zzah
            int r1 = r24.zzc()
            double r1 = (double) r1
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r0.<init>(r1)
            return r0
        L_0x01c2:
            r9 = r24
            r0 = r17
            r1 = r26
            r2 = 0
            com.google.android.gms.internal.measurement.zzh.zzh(r0, r2, r1)
            com.google.android.gms.internal.measurement.zzat r0 = new com.google.android.gms.internal.measurement.zzat
            java.lang.String r1 = ","
            java.lang.String r1 = r9.zzj(r1)
            r0.<init>(r1)
            return r0
        L_0x01d8:
            r9 = r24
            r6 = r25
            r1 = r26
            boolean r0 = r26.isEmpty()
            if (r0 == 0) goto L_0x01eb
            com.google.android.gms.internal.measurement.zzae r0 = new com.google.android.gms.internal.measurement.zzae
            r0.<init>()
            goto L_0x02a8
        L_0x01eb:
            r0 = 0
            java.lang.Object r2 = r1.get(r0)
            com.google.android.gms.internal.measurement.zzap r2 = (com.google.android.gms.internal.measurement.zzap) r2
            com.google.android.gms.internal.measurement.zzap r0 = r6.zzb(r2)
            java.lang.Double r0 = r0.zzh()
            double r2 = r0.doubleValue()
            double r2 = com.google.android.gms.internal.measurement.zzh.zza(r2)
            int r0 = (int) r2
            if (r0 >= 0) goto L_0x0211
            int r2 = r24.zzc()
            int r0 = r0 + r2
            r2 = 0
            int r0 = java.lang.Math.max(r2, r0)
            goto L_0x021b
        L_0x0211:
            int r2 = r24.zzc()
            if (r0 <= r2) goto L_0x021b
            int r0 = r24.zzc()
        L_0x021b:
            int r2 = r24.zzc()
            com.google.android.gms.internal.measurement.zzae r3 = new com.google.android.gms.internal.measurement.zzae
            r3.<init>()
            int r4 = r26.size()
            r5 = 1
            if (r4 <= r5) goto L_0x0292
            java.lang.Object r4 = r1.get(r5)
            com.google.android.gms.internal.measurement.zzap r4 = (com.google.android.gms.internal.measurement.zzap) r4
            com.google.android.gms.internal.measurement.zzap r4 = r6.zzb(r4)
            java.lang.Double r4 = r4.zzh()
            double r4 = r4.doubleValue()
            double r4 = com.google.android.gms.internal.measurement.zzh.zza(r4)
            int r4 = (int) r4
            r5 = 0
            int r4 = java.lang.Math.max(r5, r4)
            if (r4 <= 0) goto L_0x0263
            r5 = r0
        L_0x024a:
            int r7 = r0 + r4
            int r7 = java.lang.Math.min(r2, r7)
            if (r5 >= r7) goto L_0x0263
            com.google.android.gms.internal.measurement.zzap r7 = r9.zze(r0)
            int r8 = r3.zzc()
            r3.zzq(r8, r7)
            r9.zzp(r0)
            int r5 = r5 + 1
            goto L_0x024a
        L_0x0263:
            int r2 = r26.size()
            r4 = 2
            if (r2 <= r4) goto L_0x0291
            r2 = 2
        L_0x026b:
            int r4 = r26.size()
            if (r2 >= r4) goto L_0x0291
            java.lang.Object r4 = r1.get(r2)
            com.google.android.gms.internal.measurement.zzap r4 = (com.google.android.gms.internal.measurement.zzap) r4
            com.google.android.gms.internal.measurement.zzap r4 = r6.zzb(r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.measurement.zzag
            if (r5 != 0) goto L_0x0289
            int r5 = r0 + r2
            int r5 = r5 + -2
            r9.zzo(r5, r4)
            int r2 = r2 + 1
            goto L_0x026b
        L_0x0289:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Failed to parse elements to add"
            r0.<init>(r1)
            throw r0
        L_0x0291:
            goto L_0x02a7
        L_0x0292:
            if (r0 >= r2) goto L_0x02a6
            com.google.android.gms.internal.measurement.zzap r1 = r9.zze(r0)
            int r4 = r3.zzc()
            r3.zzq(r4, r1)
            r1 = 0
            r9.zzq(r0, r1)
            int r0 = r0 + 1
            goto L_0x0292
        L_0x02a6:
        L_0x02a7:
            r0 = r3
        L_0x02a8:
            return r0
        L_0x02a9:
            r9 = r24
            r6 = r25
            r1 = r26
            r0 = 1
            com.google.android.gms.internal.measurement.zzh.zzj(r10, r0, r1)
            int r0 = r24.zzc()
            r2 = 2
            if (r0 >= r2) goto L_0x02bb
            goto L_0x0304
        L_0x02bb:
            java.util.List r0 = r24.zzm()
            boolean r2 = r26.isEmpty()
            if (r2 != 0) goto L_0x02e0
            r2 = 0
            java.lang.Object r1 = r1.get(r2)
            com.google.android.gms.internal.measurement.zzap r1 = (com.google.android.gms.internal.measurement.zzap) r1
            com.google.android.gms.internal.measurement.zzap r1 = r6.zzb(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzai
            if (r2 == 0) goto L_0x02d8
            r3 = r1
            com.google.android.gms.internal.measurement.zzai r3 = (com.google.android.gms.internal.measurement.zzai) r3
            goto L_0x02e1
        L_0x02d8:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Comparator should be a method"
            r0.<init>(r1)
            throw r0
        L_0x02e0:
            r3 = 0
        L_0x02e1:
            com.google.android.gms.internal.measurement.zzba r1 = new com.google.android.gms.internal.measurement.zzba
            r1.<init>(r3, r6)
            java.util.Collections.sort(r0, r1)
            r24.zzn()
            java.util.Iterator r0 = r0.iterator()
            r2 = 0
        L_0x02f1:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0304
            int r1 = r2 + 1
            java.lang.Object r3 = r0.next()
            com.google.android.gms.internal.measurement.zzap r3 = (com.google.android.gms.internal.measurement.zzap) r3
            r9.zzq(r2, r3)
            r2 = r1
            goto L_0x02f1
        L_0x0304:
            return r9
        L_0x0305:
            r9 = r24
            r6 = r25
            r1 = r26
            r0 = 1
            com.google.android.gms.internal.measurement.zzh.zzh(r11, r0, r1)
            r0 = 0
            java.lang.Object r1 = r1.get(r0)
            com.google.android.gms.internal.measurement.zzap r1 = (com.google.android.gms.internal.measurement.zzap) r1
            com.google.android.gms.internal.measurement.zzap r0 = r6.zzb(r1)
            boolean r1 = r0 instanceof com.google.android.gms.internal.measurement.zzai
            if (r1 == 0) goto L_0x0375
            int r1 = r24.zzc()
            if (r1 != 0) goto L_0x0327
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzl
            goto L_0x0374
        L_0x0327:
            com.google.android.gms.internal.measurement.zzai r0 = (com.google.android.gms.internal.measurement.zzai) r0
            java.util.Iterator r1 = r24.zzk()
        L_0x032d:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0372
            java.lang.Object r2 = r1.next()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            boolean r3 = r9.zzs(r2)
            if (r3 == 0) goto L_0x032d
            r3 = 3
            com.google.android.gms.internal.measurement.zzap[] r3 = new com.google.android.gms.internal.measurement.zzap[r3]
            com.google.android.gms.internal.measurement.zzap r4 = r9.zze(r2)
            r5 = 0
            r3[r5] = r4
            com.google.android.gms.internal.measurement.zzah r4 = new com.google.android.gms.internal.measurement.zzah
            double r7 = (double) r2
            java.lang.Double r2 = java.lang.Double.valueOf(r7)
            r4.<init>(r2)
            r2 = 1
            r3[r2] = r4
            r2 = 2
            r3[r2] = r9
            java.util.List r2 = java.util.Arrays.asList(r3)
            com.google.android.gms.internal.measurement.zzap r2 = r0.zza(r6, r2)
            java.lang.Boolean r2 = r2.zzg()
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x032d
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzk
            goto L_0x0374
        L_0x0372:
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzl
        L_0x0374:
            return r0
        L_0x0375:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r2)
            throw r0
        L_0x037b:
            r9 = r24
            r6 = r25
            r1 = r26
            r0 = 2
            com.google.android.gms.internal.measurement.zzh.zzj(r7, r0, r1)
            boolean r0 = r26.isEmpty()
            if (r0 == 0) goto L_0x0391
            com.google.android.gms.internal.measurement.zzap r0 = r24.zzd()
            goto L_0x0403
        L_0x0391:
            int r0 = r24.zzc()
            double r7 = (double) r0
            r0 = 0
            java.lang.Object r0 = r1.get(r0)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r6.zzb(r0)
            java.lang.Double r0 = r0.zzh()
            double r10 = r0.doubleValue()
            double r10 = com.google.android.gms.internal.measurement.zzh.zza(r10)
            int r0 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x03b7
            double r10 = r10 + r7
            double r10 = java.lang.Math.max(r10, r3)
            goto L_0x03bb
        L_0x03b7:
            double r10 = java.lang.Math.min(r10, r7)
        L_0x03bb:
            int r0 = r26.size()
            r2 = 2
            if (r0 != r2) goto L_0x03e8
            r0 = 1
            java.lang.Object r0 = r1.get(r0)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r6.zzb(r0)
            java.lang.Double r0 = r0.zzh()
            double r0 = r0.doubleValue()
            double r0 = com.google.android.gms.internal.measurement.zzh.zza(r0)
            int r2 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r2 >= 0) goto L_0x03e3
            double r7 = r7 + r0
            double r7 = java.lang.Math.max(r7, r3)
            goto L_0x03e9
        L_0x03e3:
            double r7 = java.lang.Math.min(r7, r0)
            goto L_0x03e9
        L_0x03e8:
        L_0x03e9:
            com.google.android.gms.internal.measurement.zzae r0 = new com.google.android.gms.internal.measurement.zzae
            r0.<init>()
            int r1 = (int) r10
        L_0x03ef:
            double r2 = (double) r1
            int r2 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r2 >= 0) goto L_0x0402
            com.google.android.gms.internal.measurement.zzap r2 = r9.zze(r1)
            int r3 = r0.zzc()
            r0.zzq(r3, r2)
            int r1 = r1 + 1
            goto L_0x03ef
        L_0x0402:
        L_0x0403:
            return r0
        L_0x0404:
            r9 = r24
            r1 = r26
            r0 = 0
            com.google.android.gms.internal.measurement.zzh.zzh(r8, r0, r1)
            int r1 = r24.zzc()
            if (r1 != 0) goto L_0x0415
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzf
            goto L_0x041d
        L_0x0415:
            com.google.android.gms.internal.measurement.zzap r1 = r9.zze(r0)
            r9.zzp(r0)
            r0 = r1
        L_0x041d:
            return r0
        L_0x041e:
            r9 = r24
            r1 = r26
            r0 = 0
            com.google.android.gms.internal.measurement.zzh.zzh(r6, r0, r1)
            int r0 = r24.zzc()
            if (r0 == 0) goto L_0x0455
            r2 = 0
        L_0x042d:
            int r1 = r0 / 2
            if (r2 >= r1) goto L_0x0455
            boolean r1 = r9.zzs(r2)
            if (r1 == 0) goto L_0x0452
            com.google.android.gms.internal.measurement.zzap r1 = r9.zze(r2)
            r3 = 0
            r9.zzq(r2, r3)
            int r3 = r0 + -1
            int r3 = r3 - r2
            boolean r4 = r9.zzs(r3)
            if (r4 == 0) goto L_0x044f
            com.google.android.gms.internal.measurement.zzap r4 = r9.zze(r3)
            r9.zzq(r2, r4)
        L_0x044f:
            r9.zzq(r3, r1)
        L_0x0452:
            int r2 = r2 + 1
            goto L_0x042d
        L_0x0455:
            return r9
        L_0x0456:
            r9 = r24
            r6 = r25
            r1 = r26
            r0 = 0
            com.google.android.gms.internal.measurement.zzap r0 = zzc(r9, r6, r1, r0)
            return r0
        L_0x0462:
            r9 = r24
            r6 = r25
            r1 = r26
            r0 = 1
            com.google.android.gms.internal.measurement.zzap r0 = zzc(r9, r6, r1, r0)
            return r0
        L_0x046e:
            r9 = r24
            r6 = r25
            r1 = r26
            boolean r0 = r26.isEmpty()
            if (r0 != 0) goto L_0x0496
            java.util.Iterator r0 = r26.iterator()
        L_0x047e:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0496
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.measurement.zzap r1 = (com.google.android.gms.internal.measurement.zzap) r1
            com.google.android.gms.internal.measurement.zzap r1 = r6.zzb(r1)
            int r2 = r24.zzc()
            r9.zzq(r2, r1)
            goto L_0x047e
        L_0x0496:
            com.google.android.gms.internal.measurement.zzah r0 = new com.google.android.gms.internal.measurement.zzah
            int r1 = r24.zzc()
            double r1 = (double) r1
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r0.<init>(r1)
            return r0
        L_0x04a5:
            r9 = r24
            r1 = r26
            r0 = 0
            com.google.android.gms.internal.measurement.zzh.zzh(r13, r0, r1)
            int r0 = r24.zzc()
            if (r0 != 0) goto L_0x04b6
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzf
            goto L_0x04c0
        L_0x04b6:
            int r0 = r0 + -1
            com.google.android.gms.internal.measurement.zzap r1 = r9.zze(r0)
            r9.zzp(r0)
            r0 = r1
        L_0x04c0:
            return r0
        L_0x04c1:
            r9 = r24
            r6 = r25
            r1 = r26
            r0 = 1
            com.google.android.gms.internal.measurement.zzh.zzh(r14, r0, r1)
            r0 = 0
            java.lang.Object r0 = r1.get(r0)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r6.zzb(r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.measurement.zzao
            if (r1 == 0) goto L_0x04ee
            int r1 = r24.zzc()
            if (r1 != 0) goto L_0x04e6
            com.google.android.gms.internal.measurement.zzae r0 = new com.google.android.gms.internal.measurement.zzae
            r0.<init>()
            goto L_0x04ed
        L_0x04e6:
            com.google.android.gms.internal.measurement.zzao r0 = (com.google.android.gms.internal.measurement.zzao) r0
            r1 = 0
            com.google.android.gms.internal.measurement.zzae r0 = zzb(r9, r6, r0, r1, r1)
        L_0x04ed:
            return r0
        L_0x04ee:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r2)
            throw r0
        L_0x04f4:
            r9 = r24
            r6 = r25
            r1 = r26
            r0 = 2
            com.google.android.gms.internal.measurement.zzh.zzj(r15, r0, r1)
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzf
            boolean r2 = r26.isEmpty()
            if (r2 != 0) goto L_0x0511
            r0 = 0
            java.lang.Object r0 = r1.get(r0)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r6.zzb(r0)
        L_0x0511:
            int r2 = r24.zzc()
            int r2 = r2 + -1
            double r7 = (double) r2
            int r2 = r26.size()
            r5 = 1
            if (r2 <= r5) goto L_0x0559
            java.lang.Object r1 = r1.get(r5)
            com.google.android.gms.internal.measurement.zzap r1 = (com.google.android.gms.internal.measurement.zzap) r1
            com.google.android.gms.internal.measurement.zzap r1 = r6.zzb(r1)
            java.lang.Double r2 = r1.zzh()
            double r5 = r2.doubleValue()
            boolean r2 = java.lang.Double.isNaN(r5)
            if (r2 == 0) goto L_0x0540
            int r1 = r24.zzc()
            int r1 = r1 + -1
            double r1 = (double) r1
            r7 = r1
            goto L_0x054d
        L_0x0540:
            java.lang.Double r1 = r1.zzh()
            double r1 = r1.doubleValue()
            double r1 = com.google.android.gms.internal.measurement.zzh.zza(r1)
            r7 = r1
        L_0x054d:
            int r1 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x0558
            int r1 = r24.zzc()
            double r1 = (double) r1
            double r7 = r7 + r1
            goto L_0x0559
        L_0x0558:
        L_0x0559:
            int r1 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x0567
            com.google.android.gms.internal.measurement.zzah r0 = new com.google.android.gms.internal.measurement.zzah
            java.lang.Double r1 = java.lang.Double.valueOf(r19)
            r0.<init>(r1)
            goto L_0x059a
        L_0x0567:
            int r1 = r24.zzc()
            double r1 = (double) r1
            double r1 = java.lang.Math.min(r1, r7)
            int r1 = (int) r1
        L_0x0571:
            if (r1 < 0) goto L_0x0591
            boolean r2 = r9.zzs(r1)
            if (r2 == 0) goto L_0x058e
            com.google.android.gms.internal.measurement.zzap r2 = r9.zze(r1)
            boolean r2 = com.google.android.gms.internal.measurement.zzh.zzl(r2, r0)
            if (r2 == 0) goto L_0x058e
            com.google.android.gms.internal.measurement.zzah r0 = new com.google.android.gms.internal.measurement.zzah
            double r1 = (double) r1
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r0.<init>(r1)
            goto L_0x059a
        L_0x058e:
            int r1 = r1 + -1
            goto L_0x0571
        L_0x0591:
            com.google.android.gms.internal.measurement.zzah r0 = new com.google.android.gms.internal.measurement.zzah
            java.lang.Double r1 = java.lang.Double.valueOf(r19)
            r0.<init>(r1)
        L_0x059a:
            return r0
        L_0x059b:
            r9 = r24
            r6 = r25
            r1 = r26
            r0 = 1
            com.google.android.gms.internal.measurement.zzh.zzj(r12, r0, r1)
            int r0 = r24.zzc()
            if (r0 != 0) goto L_0x05ae
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzm
            goto L_0x05dc
        L_0x05ae:
            int r0 = r26.size()
            if (r0 <= 0) goto L_0x05d0
            r0 = 0
            java.lang.Object r0 = r1.get(r0)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r6.zzb(r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.measurement.zzan
            if (r1 != 0) goto L_0x05cd
            boolean r1 = r0 instanceof com.google.android.gms.internal.measurement.zzau
            if (r1 == 0) goto L_0x05c8
            goto L_0x05cd
        L_0x05c8:
            java.lang.String r0 = r0.zzi()
            goto L_0x05d2
        L_0x05cd:
            java.lang.String r0 = ""
            goto L_0x05d2
        L_0x05d0:
            java.lang.String r0 = ","
        L_0x05d2:
            com.google.android.gms.internal.measurement.zzat r1 = new com.google.android.gms.internal.measurement.zzat
            java.lang.String r0 = r9.zzj(r0)
            r1.<init>(r0)
            r0 = r1
        L_0x05dc:
            return r0
        L_0x05dd:
            r9 = r24
            r6 = r25
            r1 = r26
            r0 = 2
            com.google.android.gms.internal.measurement.zzh.zzj(r5, r0, r1)
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzf
            boolean r2 = r26.isEmpty()
            if (r2 != 0) goto L_0x05fa
            r0 = 0
            java.lang.Object r0 = r1.get(r0)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r6.zzb(r0)
        L_0x05fa:
            int r2 = r26.size()
            r5 = 1
            if (r2 <= r5) goto L_0x0637
            java.lang.Object r1 = r1.get(r5)
            com.google.android.gms.internal.measurement.zzap r1 = (com.google.android.gms.internal.measurement.zzap) r1
            com.google.android.gms.internal.measurement.zzap r1 = r6.zzb(r1)
            java.lang.Double r1 = r1.zzh()
            double r1 = r1.doubleValue()
            double r1 = com.google.android.gms.internal.measurement.zzh.zza(r1)
            int r5 = r24.zzc()
            double r5 = (double) r5
            int r5 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r5 < 0) goto L_0x062a
            com.google.android.gms.internal.measurement.zzah r0 = new com.google.android.gms.internal.measurement.zzah
            java.lang.Double r1 = java.lang.Double.valueOf(r19)
            r0.<init>(r1)
            goto L_0x066e
        L_0x062a:
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x0635
            int r3 = r24.zzc()
            double r3 = (double) r3
            double r3 = r3 + r1
            goto L_0x0638
        L_0x0635:
            r3 = r1
            goto L_0x0638
        L_0x0637:
        L_0x0638:
            java.util.Iterator r1 = r24.zzk()
        L_0x063c:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0665
            java.lang.Object r2 = r1.next()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            double r5 = (double) r2
            int r7 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r7 < 0) goto L_0x063c
            com.google.android.gms.internal.measurement.zzap r2 = r9.zze(r2)
            boolean r2 = com.google.android.gms.internal.measurement.zzh.zzl(r2, r0)
            if (r2 == 0) goto L_0x063c
            com.google.android.gms.internal.measurement.zzah r0 = new com.google.android.gms.internal.measurement.zzah
            java.lang.Double r1 = java.lang.Double.valueOf(r5)
            r0.<init>(r1)
            goto L_0x066e
        L_0x0665:
            com.google.android.gms.internal.measurement.zzah r0 = new com.google.android.gms.internal.measurement.zzah
            java.lang.Double r1 = java.lang.Double.valueOf(r19)
            r0.<init>(r1)
        L_0x066e:
            return r0
        L_0x066f:
            r9 = r24
            r6 = r25
            r1 = r26
            r0 = r21
            r3 = 1
            com.google.android.gms.internal.measurement.zzh.zzh(r0, r3, r1)
            r0 = 0
            java.lang.Object r0 = r1.get(r0)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r6.zzb(r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.measurement.zzao
            if (r1 == 0) goto L_0x069c
            int r1 = r24.zzb()
            if (r1 != 0) goto L_0x0693
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzf
            goto L_0x069b
        L_0x0693:
            com.google.android.gms.internal.measurement.zzao r0 = (com.google.android.gms.internal.measurement.zzao) r0
            r1 = 0
            zzb(r9, r6, r0, r1, r1)
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzf
        L_0x069b:
            return r0
        L_0x069c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r2)
            throw r0
        L_0x06a2:
            r9 = r24
            r6 = r25
            r0 = r26
            r3 = r22
            r4 = 1
            com.google.android.gms.internal.measurement.zzh.zzh(r3, r4, r0)
            r3 = 0
            java.lang.Object r0 = r0.get(r3)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r6.zzb(r0)
            boolean r3 = r0 instanceof com.google.android.gms.internal.measurement.zzao
            if (r3 == 0) goto L_0x06fe
            int r2 = r24.zzb()
            if (r2 != 0) goto L_0x06c9
            com.google.android.gms.internal.measurement.zzae r0 = new com.google.android.gms.internal.measurement.zzae
            r0.<init>()
            goto L_0x06fd
        L_0x06c9:
            com.google.android.gms.internal.measurement.zzap r2 = r24.zzd()
            com.google.android.gms.internal.measurement.zzao r0 = (com.google.android.gms.internal.measurement.zzao) r0
            r3 = 0
            com.google.android.gms.internal.measurement.zzae r0 = zzb(r9, r6, r0, r3, r1)
            com.google.android.gms.internal.measurement.zzae r1 = new com.google.android.gms.internal.measurement.zzae
            r1.<init>()
            java.util.Iterator r0 = r0.zzk()
        L_0x06dd:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x06fc
            java.lang.Object r3 = r0.next()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            r4 = r2
            com.google.android.gms.internal.measurement.zzae r4 = (com.google.android.gms.internal.measurement.zzae) r4
            com.google.android.gms.internal.measurement.zzap r3 = r4.zze(r3)
            int r4 = r1.zzc()
            r1.zzq(r4, r3)
            goto L_0x06dd
        L_0x06fc:
            r0 = r1
        L_0x06fd:
            return r0
        L_0x06fe:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r2)
            throw r0
        L_0x0704:
            r3 = r24
            r6 = r25
            r0 = r26
            r4 = 1
            com.google.android.gms.internal.measurement.zzh.zzh(r9, r4, r0)
            r4 = 0
            java.lang.Object r0 = r0.get(r4)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r6.zzb(r0)
            boolean r4 = r0 instanceof com.google.android.gms.internal.measurement.zzao
            if (r4 == 0) goto L_0x0741
            int r2 = r24.zzc()
            if (r2 != 0) goto L_0x0726
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzk
            goto L_0x0740
        L_0x0726:
            com.google.android.gms.internal.measurement.zzao r0 = (com.google.android.gms.internal.measurement.zzao) r0
            r2 = 0
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            com.google.android.gms.internal.measurement.zzae r0 = zzb(r3, r6, r0, r2, r1)
            int r0 = r0.zzc()
            int r1 = r24.zzc()
            if (r0 == r1) goto L_0x073e
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzl
            goto L_0x0740
        L_0x073e:
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzk
        L_0x0740:
            return r0
        L_0x0741:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r2)
            throw r0
        L_0x0747:
            r3 = r24
            r6 = r25
            r0 = r26
            com.google.android.gms.internal.measurement.zzap r1 = r24.zzd()
            boolean r2 = r26.isEmpty()
            if (r2 != 0) goto L_0x07aa
            java.util.Iterator r0 = r26.iterator()
        L_0x075b:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x07aa
            java.lang.Object r2 = r0.next()
            com.google.android.gms.internal.measurement.zzap r2 = (com.google.android.gms.internal.measurement.zzap) r2
            com.google.android.gms.internal.measurement.zzap r2 = r6.zzb(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzag
            if (r3 != 0) goto L_0x07a2
            r3 = r1
            com.google.android.gms.internal.measurement.zzae r3 = (com.google.android.gms.internal.measurement.zzae) r3
            int r4 = r3.zzc()
            boolean r5 = r2 instanceof com.google.android.gms.internal.measurement.zzae
            if (r5 == 0) goto L_0x079d
            com.google.android.gms.internal.measurement.zzae r2 = (com.google.android.gms.internal.measurement.zzae) r2
            java.util.Iterator r5 = r2.zzk()
        L_0x0780:
            boolean r7 = r5.hasNext()
            if (r7 == 0) goto L_0x075b
            java.lang.Object r7 = r5.next()
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r8 = r7.intValue()
            int r7 = r7.intValue()
            com.google.android.gms.internal.measurement.zzap r7 = r2.zze(r7)
            int r8 = r8 + r4
            r3.zzq(r8, r7)
            goto L_0x0780
        L_0x079d:
            r3.zzq(r4, r2)
            goto L_0x075b
        L_0x07a2:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Failed evaluation of arguments"
            r0.<init>(r1)
            throw r0
        L_0x07aa:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbb.zza(java.lang.String, com.google.android.gms.internal.measurement.zzae, com.google.android.gms.internal.measurement.zzg, java.util.List):com.google.android.gms.internal.measurement.zzap");
    }

    private static zzae zzb(zzae zzae, zzg zzg, zzai zzai, Boolean bool, Boolean bool2) {
        zzae zzae2 = new zzae();
        Iterator<Integer> zzk = zzae.zzk();
        while (zzk.hasNext()) {
            int intValue = zzk.next().intValue();
            if (zzae.zzs(intValue)) {
                zzap zza = zzai.zza(zzg, Arrays.asList(new zzap[]{zzae.zze(intValue), new zzah(Double.valueOf((double) intValue)), zzae}));
                if (zza.zzg().equals(bool)) {
                    return zzae2;
                }
                if (bool2 == null || zza.zzg().equals(bool2)) {
                    zzae2.zzq(intValue, zza);
                }
            }
        }
        return zzae2;
    }

    private static zzap zzc(zzae zzae, zzg zzg, List<zzap> list, boolean z) {
        zzap zzap;
        int i;
        int i2;
        zzh.zzi("reduce", 1, list);
        zzh.zzj("reduce", 2, list);
        zzap zzb = zzg.zzb(list.get(0));
        if (zzb instanceof zzai) {
            if (list.size() == 2) {
                zzap = zzg.zzb(list.get(1));
                if (zzap instanceof zzag) {
                    throw new IllegalArgumentException("Failed to parse initial value");
                }
            } else if (zzae.zzc() != 0) {
                zzap = null;
            } else {
                throw new IllegalStateException("Empty array with no initial value error");
            }
            zzai zzai = (zzai) zzb;
            int zzc = zzae.zzc();
            if (z) {
                i = 0;
            } else {
                i = zzc - 1;
            }
            int i3 = -1;
            if (z) {
                i2 = zzc - 1;
            } else {
                i2 = 0;
            }
            if (true == z) {
                i3 = 1;
            }
            if (zzap == null) {
                zzap = zzae.zze(i);
                i += i3;
            }
            while ((i2 - i) * i3 >= 0) {
                if (zzae.zzs(i)) {
                    zzap = zzai.zza(zzg, Arrays.asList(new zzap[]{zzap, zzae.zze(i), new zzah(Double.valueOf((double) i)), zzae}));
                    if (zzap instanceof zzag) {
                        throw new IllegalStateException("Reduce operation failed");
                    }
                }
                i += i3;
            }
            return zzap;
        }
        throw new IllegalArgumentException("Callback should be a method");
    }
}
