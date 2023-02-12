package com.google.android.gms.internal.measurement;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzat implements Iterable<zzap>, zzap {
    /* access modifiers changed from: private */
    public final String zza;

    public zzat(String str) {
        if (str != null) {
            this.zza = str;
            return;
        }
        throw new IllegalArgumentException("StringValue cannot be null.");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzat)) {
            return false;
        }
        return this.zza.equals(((zzat) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final Iterator<zzap> iterator() {
        return new zzas(this);
    }

    public final String toString() {
        String str = this.zza;
        StringBuilder sb = new StringBuilder(str.length() + 2);
        sb.append('\"');
        sb.append(str);
        sb.append('\"');
        return sb.toString();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0183, code lost:
        r3 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0186, code lost:
        r3 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0196, code lost:
        r1 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0197, code lost:
        r18 = "toString";
        r0 = "";
        r19 = "undefined";
        r20 = "lastIndexOf";
        r21 = r13;
        r12 = 0.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x01a3, code lost:
        switch(r1) {
            case 0: goto L_0x0682;
            case 1: goto L_0x0648;
            case 2: goto L_0x05ff;
            case 3: goto L_0x05a6;
            case 4: goto L_0x0540;
            case 5: goto L_0x04f1;
            case 6: goto L_0x0441;
            case 7: goto L_0x03f3;
            case 8: goto L_0x0360;
            case 9: goto L_0x02a9;
            case 10: goto L_0x022a;
            case 11: goto L_0x0215;
            case 12: goto L_0x0200;
            case 13: goto L_0x01e9;
            case 14: goto L_0x01dd;
            case 15: goto L_0x01c6;
            case 16: goto L_0x01b0;
            default: goto L_0x01a6;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x01a6, code lost:
        r3 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x01af, code lost:
        throw new java.lang.IllegalArgumentException("Command not supported");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x01b0, code lost:
        com.google.android.gms.internal.measurement.zzh.zzh("toUpperCase", 0, r25);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x01c6, code lost:
        com.google.android.gms.internal.measurement.zzh.zzh("toUpperCase", 0, r25);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x01dd, code lost:
        r3 = r22;
        com.google.android.gms.internal.measurement.zzh.zzh(r18, 0, r25);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x01e9, code lost:
        com.google.android.gms.internal.measurement.zzh.zzh("toLowerCase", 0, r25);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0200, code lost:
        com.google.android.gms.internal.measurement.zzh.zzh("toLocaleLowerCase", 0, r25);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0215, code lost:
        com.google.android.gms.internal.measurement.zzh.zzh(r5, 0, r25);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x022a, code lost:
        r1 = r25;
        com.google.android.gms.internal.measurement.zzh.zzj("substring", 2, r1);
        r0 = r22.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0238, code lost:
        if (r25.size() <= 0) goto L_0x0255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x023a, code lost:
        r2 = r24;
        r4 = (int) com.google.android.gms.internal.measurement.zzh.zza(r2.zzb(r1.get(0)).zzh().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0255, code lost:
        r2 = r24;
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x025d, code lost:
        if (r25.size() <= 1) goto L_0x0277;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x025f, code lost:
        r1 = (int) com.google.android.gms.internal.measurement.zzh.zza(r2.zzb(r1.get(1)).zzh().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0277, code lost:
        r1 = r0.length();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x027b, code lost:
        r4 = java.lang.Math.min(java.lang.Math.max(r4, 0), r0.length());
        r1 = java.lang.Math.min(java.lang.Math.max(r1, 0), r0.length());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x02a9, code lost:
        r3 = r22;
        r2 = r24;
        r1 = r25;
        com.google.android.gms.internal.measurement.zzh.zzj("split", 2, r1);
        r4 = r3.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x02b9, code lost:
        if (r4.length() != 0) goto L_0x02cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x02cc, code lost:
        r5 = new java.util.ArrayList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x02d5, code lost:
        if (r25.size() != 0) goto L_0x02dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x02d7, code lost:
        r5.add(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x02dc, code lost:
        r6 = r2.zzb(r1.get(0)).zzi();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x02f1, code lost:
        if (r25.size() <= 1) goto L_0x030a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x02f3, code lost:
        r1 = com.google.android.gms.internal.measurement.zzh.zzd(r2.zzb(r1.get(1)).zzh().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x030a, code lost:
        r1 = 2147483647L;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0311, code lost:
        if (r1 != 0) goto L_0x031a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x031a, code lost:
        r4 = r4.split(java.util.regex.Pattern.quote(r6), ((int) r1) + 1);
        r7 = r4.length;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x032a, code lost:
        if (r6.equals(r0) == false) goto L_0x0341;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x032c, code lost:
        if (r7 <= 0) goto L_0x0341;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x032e, code lost:
        r13 = r4[0].equals(r0);
        r6 = r7 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x033d, code lost:
        if (r4[r6].equals(r0) != false) goto L_0x0343;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x033f, code lost:
        r6 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x0341, code lost:
        r6 = r7;
        r13 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0346, code lost:
        if (((long) r7) <= r1) goto L_0x034a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0348, code lost:
        r6 = r6 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x034a, code lost:
        if (r13 >= r6) goto L_0x0359;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x034c, code lost:
        r5.add(new com.google.android.gms.internal.measurement.zzat(r4[r13]));
        r13 = r13 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0360, code lost:
        r2 = r24;
        r1 = r25;
        com.google.android.gms.internal.measurement.zzh.zzj("slice", 2, r1);
        r0 = r22.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0370, code lost:
        if (r25.size() <= 0) goto L_0x0386;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0372, code lost:
        r4 = r2.zzb(r1.get(0)).zzh().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0386, code lost:
        r4 = 0.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x0387, code lost:
        r4 = com.google.android.gms.internal.measurement.zzh.zza(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x038d, code lost:
        if (r4 >= 0.0d) goto L_0x039a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x038f, code lost:
        r4 = java.lang.Math.max(((double) r0.length()) + r4, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x039a, code lost:
        r4 = java.lang.Math.min(r4, (double) r0.length());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x03a3, code lost:
        r4 = (int) r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x03a9, code lost:
        if (r25.size() <= 1) goto L_0x03be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x03ab, code lost:
        r1 = r2.zzb(r1.get(1)).zzh().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x03be, code lost:
        r1 = (double) r0.length();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x03c3, code lost:
        r1 = com.google.android.gms.internal.measurement.zzh.zza(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x03c9, code lost:
        if (r1 >= 0.0d) goto L_0x03d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x03cb, code lost:
        r1 = java.lang.Math.max(((double) r0.length()) + r1, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x03d6, code lost:
        r1 = java.lang.Math.min(r1, (double) r0.length());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x03f3, code lost:
        r3 = r22;
        r2 = r24;
        r1 = r25;
        com.google.android.gms.internal.measurement.zzh.zzj(com.google.firebase.analytics.FirebaseAnalytics.Event.SEARCH, 1, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x0401, code lost:
        if (r25.size() <= 0) goto L_0x0414;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0403, code lost:
        r19 = r2.zzb(r1.get(0)).zzi();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0414, code lost:
        r0 = java.util.regex.Pattern.compile(r19).matcher(r3.zza);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0422, code lost:
        if (r0.find() == false) goto L_0x0434;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x0441, code lost:
        r3 = r22;
        r2 = r24;
        r1 = r25;
        com.google.android.gms.internal.measurement.zzh.zzj("replace", 2, r1);
        r0 = com.google.android.gms.internal.measurement.zzap.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x0451, code lost:
        if (r25.size() <= 0) goto L_0x0476;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x0453, code lost:
        r19 = r2.zzb(r1.get(0)).zzi();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x0467, code lost:
        if (r25.size() <= 1) goto L_0x0476;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x0469, code lost:
        r0 = r2.zzb(r1.get(1));
        r1 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x0476, code lost:
        r1 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x0478, code lost:
        r4 = r3.zza;
        r5 = r4.indexOf(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x047e, code lost:
        if (r5 < 0) goto L_0x0680;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x0482, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzai) == false) goto L_0x04ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x0484, code lost:
        r0 = ((com.google.android.gms.internal.measurement.zzai) r0).zza(r2, java.util.Arrays.asList(new com.google.android.gms.internal.measurement.zzap[]{new com.google.android.gms.internal.measurement.zzat(r1), new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf((double) r5)), r3}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x04ab, code lost:
        r6 = r4.substring(0, r5);
        r0 = r0.zzi();
        r1 = r4.substring(r5 + r1.length());
        r8 = new java.lang.StringBuilder((java.lang.String.valueOf(r6).length() + java.lang.String.valueOf(r0).length()) + java.lang.String.valueOf(r1).length());
        r8.append(r6);
        r8.append(r0);
        r8.append(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x04f1, code lost:
        r2 = r24;
        r1 = r25;
        com.google.android.gms.internal.measurement.zzh.zzj("match", 1, r1);
        r4 = r22.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x0501, code lost:
        if (r25.size() > 0) goto L_0x0504;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x0504, code lost:
        r0 = r2.zzb(r1.get(0)).zzi();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x0514, code lost:
        r0 = java.util.regex.Pattern.compile(r0).matcher(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x0520, code lost:
        if (r0.find() == false) goto L_0x053c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x0540, code lost:
        r2 = r24;
        r1 = r25;
        com.google.android.gms.internal.measurement.zzh.zzj(r20, 2, r1);
        r0 = r22.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x0552, code lost:
        if (r25.size() > 0) goto L_0x0557;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x0554, code lost:
        r4 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x0557, code lost:
        r4 = r2.zzb(r1.get(0)).zzi();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x056e, code lost:
        if (r25.size() >= 2) goto L_0x0573;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x0570, code lost:
        r1 = Double.NaN;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x0573, code lost:
        r1 = r2.zzb(r1.get(1)).zzh().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x058b, code lost:
        if (java.lang.Double.isNaN(r1) == false) goto L_0x0590;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x058d, code lost:
        r1 = Double.POSITIVE_INFINITY;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x0590, code lost:
        r1 = com.google.android.gms.internal.measurement.zzh.zza(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x05a6, code lost:
        r2 = r24;
        r1 = r25;
        com.google.android.gms.internal.measurement.zzh.zzj("indexOf", 2, r1);
        r0 = r22.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x05b6, code lost:
        if (r25.size() > 0) goto L_0x05bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x05b8, code lost:
        r4 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x05bb, code lost:
        r4 = r2.zzb(r1.get(0)).zzi();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x05d2, code lost:
        if (r25.size() >= 2) goto L_0x05d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x05d5, code lost:
        r12 = r2.zzb(r1.get(1)).zzh().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x05ff, code lost:
        r1 = r25;
        com.google.android.gms.internal.measurement.zzh.zzh(r3, 1, r1);
        r0 = r22.zza;
        r1 = r24.zzb(r1.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x0621, code lost:
        if ("length".equals(r1.zzi()) == false) goto L_0x0627;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x0627, code lost:
        r1 = r1.zzh().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x0635, code lost:
        if (r1 != java.lang.Math.floor(r1)) goto L_0x0644;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x0637, code lost:
        r1 = (int) r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x0638, code lost:
        if (r1 < 0) goto L_0x0644;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x063e, code lost:
        if (r1 >= r0.length()) goto L_0x0644;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x0648, code lost:
        r3 = r22;
        r2 = r24;
        r1 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x0652, code lost:
        if (r25.size() == 0) goto L_0x0680;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x0654, code lost:
        r0 = new java.lang.StringBuilder(r3.zza);
        r13 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x0660, code lost:
        if (r13 >= r25.size()) goto L_0x0676;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x0662, code lost:
        r0.append(r2.zzb(r1.get(r13)).zzi());
        r13 = r13 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x0682, code lost:
        r3 = r22;
        r2 = r24;
        r1 = r25;
        com.google.android.gms.internal.measurement.zzh.zzj(r21, 1, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x0692, code lost:
        if (r25.size() <= 0) goto L_0x06ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x0694, code lost:
        r13 = (int) com.google.android.gms.internal.measurement.zzh.zza(r2.zzb(r1.get(0)).zzh().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x06ad, code lost:
        r13 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x06af, code lost:
        r0 = r3.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x06b1, code lost:
        if (r13 < 0) goto L_0x06c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x06b7, code lost:
        if (r13 < r0.length()) goto L_0x06ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzm;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:?, code lost:
        return new com.google.android.gms.internal.measurement.zzat(r22.zza.trim());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:?, code lost:
        return new com.google.android.gms.internal.measurement.zzat(r22.zza.toUpperCase(java.util.Locale.ENGLISH));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:254:?, code lost:
        return new com.google.android.gms.internal.measurement.zzat(r22.zza.toLowerCase(java.util.Locale.ENGLISH));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:?, code lost:
        return new com.google.android.gms.internal.measurement.zzat(r22.zza.toLowerCase());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:?, code lost:
        return new com.google.android.gms.internal.measurement.zzat(r22.zza.toUpperCase());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:257:?, code lost:
        return new com.google.android.gms.internal.measurement.zzat(r0.substring(java.lang.Math.min(r4, r1), java.lang.Math.max(r4, r1)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:258:?, code lost:
        return new com.google.android.gms.internal.measurement.zzae(java.util.Arrays.asList(new com.google.android.gms.internal.measurement.zzap[]{r3}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:?, code lost:
        return new com.google.android.gms.internal.measurement.zzae();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:260:?, code lost:
        return new com.google.android.gms.internal.measurement.zzae(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:261:?, code lost:
        return new com.google.android.gms.internal.measurement.zzat(r0.substring(r4, java.lang.Math.max(0, ((int) r1) - r4) + r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:262:?, code lost:
        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf((double) r0.start()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:263:?, code lost:
        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf(-1.0d));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:?, code lost:
        return new com.google.android.gms.internal.measurement.zzat(r8.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:?, code lost:
        return new com.google.android.gms.internal.measurement.zzae(java.util.Arrays.asList(new com.google.android.gms.internal.measurement.zzap[]{new com.google.android.gms.internal.measurement.zzat(r0.group())}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:266:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzg;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:267:?, code lost:
        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf((double) r0.lastIndexOf(r4, (int) r1)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:268:?, code lost:
        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf((double) r0.indexOf(r4, (int) com.google.android.gms.internal.measurement.zzh.zza(r12))));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:269:?, code lost:
        return com.google.android.gms.internal.measurement.zzaf.zzk;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:270:?, code lost:
        return com.google.android.gms.internal.measurement.zzaf.zzk;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:?, code lost:
        return com.google.android.gms.internal.measurement.zzaf.zzl;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:272:?, code lost:
        return new com.google.android.gms.internal.measurement.zzat(r0.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:273:?, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:274:?, code lost:
        return new com.google.android.gms.internal.measurement.zzat(java.lang.String.valueOf(r0.charAt(r13)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x014e, code lost:
        r13 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x015f, code lost:
        r13 = r16;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzap zzbK(java.lang.String r23, com.google.android.gms.internal.measurement.zzg r24, java.util.List<com.google.android.gms.internal.measurement.zzap> r25) {
        /*
            r22 = this;
            r0 = r22
            r1 = r23
            r2 = r24
            r3 = r25
            java.lang.String r4 = "charAt"
            boolean r5 = r4.equals(r1)
            java.lang.String r6 = "indexOf"
            java.lang.String r7 = "replace"
            java.lang.String r8 = "substring"
            java.lang.String r9 = "split"
            java.lang.String r10 = "slice"
            java.lang.String r11 = "match"
            java.lang.String r12 = "lastIndexOf"
            java.lang.String r13 = "toLocaleUpperCase"
            java.lang.String r14 = "search"
            java.lang.String r15 = "toLowerCase"
            java.lang.String r2 = "toLocaleLowerCase"
            java.lang.String r0 = "toString"
            java.lang.String r3 = "hasOwnProperty"
            r16 = r4
            java.lang.String r4 = "toUpperCase"
            r17 = r13
            if (r5 != 0) goto L_0x00d3
            java.lang.String r5 = "concat"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x00d0
            boolean r5 = r3.equals(r1)
            if (r5 != 0) goto L_0x00cd
            boolean r5 = r6.equals(r1)
            if (r5 != 0) goto L_0x00ca
            boolean r5 = r12.equals(r1)
            if (r5 != 0) goto L_0x00c7
            boolean r5 = r11.equals(r1)
            if (r5 != 0) goto L_0x00c4
            boolean r5 = r7.equals(r1)
            if (r5 != 0) goto L_0x00c1
            boolean r5 = r14.equals(r1)
            if (r5 != 0) goto L_0x00be
            boolean r5 = r10.equals(r1)
            if (r5 != 0) goto L_0x00bb
            boolean r5 = r9.equals(r1)
            if (r5 != 0) goto L_0x00b8
            boolean r5 = r8.equals(r1)
            if (r5 != 0) goto L_0x00b5
            boolean r5 = r15.equals(r1)
            if (r5 != 0) goto L_0x00b2
            boolean r5 = r2.equals(r1)
            if (r5 != 0) goto L_0x00af
            boolean r5 = r0.equals(r1)
            if (r5 != 0) goto L_0x00ac
            boolean r5 = r4.equals(r1)
            if (r5 != 0) goto L_0x00a9
            r5 = r17
            boolean r17 = r5.equals(r1)
            if (r17 != 0) goto L_0x00d5
            java.lang.String r13 = "trim"
            boolean r13 = r13.equals(r1)
            if (r13 == 0) goto L_0x0097
            goto L_0x00d5
        L_0x0097:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            r2[r3] = r1
            java.lang.String r1 = "%s is not a String function"
            java.lang.String r1 = java.lang.String.format(r1, r2)
            r0.<init>(r1)
            throw r0
        L_0x00a9:
            r5 = r17
            goto L_0x00d5
        L_0x00ac:
            r5 = r17
            goto L_0x00d5
        L_0x00af:
            r5 = r17
            goto L_0x00d5
        L_0x00b2:
            r5 = r17
            goto L_0x00d5
        L_0x00b5:
            r5 = r17
            goto L_0x00d5
        L_0x00b8:
            r5 = r17
            goto L_0x00d5
        L_0x00bb:
            r5 = r17
            goto L_0x00d5
        L_0x00be:
            r5 = r17
            goto L_0x00d5
        L_0x00c1:
            r5 = r17
            goto L_0x00d5
        L_0x00c4:
            r5 = r17
            goto L_0x00d5
        L_0x00c7:
            r5 = r17
            goto L_0x00d5
        L_0x00ca:
            r5 = r17
            goto L_0x00d5
        L_0x00cd:
            r5 = r17
            goto L_0x00d5
        L_0x00d0:
            r5 = r17
            goto L_0x00d5
        L_0x00d3:
            r5 = r17
        L_0x00d5:
            int r13 = r23.hashCode()
            r18 = r3
            switch(r13) {
                case -1789698943: goto L_0x0189;
                case -1776922004: goto L_0x0179;
                case -1464939364: goto L_0x016e;
                case -1361633751: goto L_0x0162;
                case -1354795244: goto L_0x0151;
                case -1137582698: goto L_0x0146;
                case -906336856: goto L_0x013e;
                case -726908483: goto L_0x0135;
                case -467511597: goto L_0x012d;
                case -399551817: goto L_0x0124;
                case 3568674: goto L_0x0119;
                case 103668165: goto L_0x0111;
                case 109526418: goto L_0x0108;
                case 109648666: goto L_0x00ff;
                case 530542161: goto L_0x00f6;
                case 1094496948: goto L_0x00ee;
                case 1943291465: goto L_0x00e4;
                default: goto L_0x00de;
            }
        L_0x00de:
            r13 = r16
            r3 = r18
            goto L_0x0196
        L_0x00e4:
            boolean r1 = r1.equals(r6)
            if (r1 == 0) goto L_0x015f
            r1 = 3
            goto L_0x014e
        L_0x00ee:
            boolean r1 = r1.equals(r7)
            if (r1 == 0) goto L_0x015f
            r1 = 6
            goto L_0x014e
        L_0x00f6:
            boolean r1 = r1.equals(r8)
            if (r1 == 0) goto L_0x015f
            r1 = 10
            goto L_0x014e
        L_0x00ff:
            boolean r1 = r1.equals(r9)
            if (r1 == 0) goto L_0x015f
            r1 = 9
            goto L_0x014e
        L_0x0108:
            boolean r1 = r1.equals(r10)
            if (r1 == 0) goto L_0x015f
            r1 = 8
            goto L_0x014e
        L_0x0111:
            boolean r1 = r1.equals(r11)
            if (r1 == 0) goto L_0x015f
            r1 = 5
            goto L_0x014e
        L_0x0119:
            java.lang.String r13 = "trim"
            boolean r1 = r1.equals(r13)
            if (r1 == 0) goto L_0x015f
            r1 = 16
            goto L_0x014e
        L_0x0124:
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x015f
            r1 = 15
            goto L_0x014e
        L_0x012d:
            boolean r1 = r1.equals(r12)
            if (r1 == 0) goto L_0x015f
            r1 = 4
            goto L_0x014e
        L_0x0135:
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L_0x015f
            r1 = 11
            goto L_0x014e
        L_0x013e:
            boolean r1 = r1.equals(r14)
            if (r1 == 0) goto L_0x015f
            r1 = 7
            goto L_0x014e
        L_0x0146:
            boolean r1 = r1.equals(r15)
            if (r1 == 0) goto L_0x015f
            r1 = 13
        L_0x014e:
            r13 = r16
            goto L_0x0183
        L_0x0151:
            java.lang.String r13 = "concat"
            boolean r1 = r1.equals(r13)
            if (r1 == 0) goto L_0x015f
            r13 = r16
            r3 = r18
            r1 = 1
            goto L_0x0197
        L_0x015f:
            r13 = r16
            goto L_0x0186
        L_0x0162:
            r13 = r16
            boolean r1 = r1.equals(r13)
            if (r1 == 0) goto L_0x0186
            r3 = r18
            r1 = 0
            goto L_0x0197
        L_0x016e:
            r13 = r16
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0186
            r1 = 12
            goto L_0x0183
        L_0x0179:
            r13 = r16
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0186
            r1 = 14
        L_0x0183:
            r3 = r18
            goto L_0x0197
        L_0x0186:
            r3 = r18
            goto L_0x0196
        L_0x0189:
            r13 = r16
            r3 = r18
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0196
            r1 = 2
            goto L_0x0197
        L_0x0196:
            r1 = -1
        L_0x0197:
            r18 = r0
            java.lang.String r0 = ""
            java.lang.String r19 = "undefined"
            r20 = r12
            r21 = r13
            r12 = 0
            switch(r1) {
                case 0: goto L_0x0682;
                case 1: goto L_0x0648;
                case 2: goto L_0x05ff;
                case 3: goto L_0x05a6;
                case 4: goto L_0x0540;
                case 5: goto L_0x04f1;
                case 6: goto L_0x0441;
                case 7: goto L_0x03f3;
                case 8: goto L_0x0360;
                case 9: goto L_0x02a9;
                case 10: goto L_0x022a;
                case 11: goto L_0x0215;
                case 12: goto L_0x0200;
                case 13: goto L_0x01e9;
                case 14: goto L_0x01dd;
                case 15: goto L_0x01c6;
                case 16: goto L_0x01b0;
                default: goto L_0x01a6;
            }
        L_0x01a6:
            r3 = r22
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Command not supported"
            r0.<init>(r1)
            throw r0
        L_0x01b0:
            r1 = r25
            r0 = 0
            com.google.android.gms.internal.measurement.zzh.zzh(r4, r0, r1)
            r3 = r22
            java.lang.String r0 = r3.zza
            com.google.android.gms.internal.measurement.zzat r1 = new com.google.android.gms.internal.measurement.zzat
            java.lang.String r0 = r0.trim()
            r1.<init>(r0)
            goto L_0x06ca
        L_0x01c6:
            r0 = 0
            r3 = r22
            r1 = r25
            com.google.android.gms.internal.measurement.zzh.zzh(r4, r0, r1)
            java.lang.String r0 = r3.zza
            com.google.android.gms.internal.measurement.zzat r1 = new com.google.android.gms.internal.measurement.zzat
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r0 = r0.toUpperCase(r2)
            r1.<init>(r0)
            goto L_0x06ca
        L_0x01dd:
            r0 = 0
            r3 = r22
            r1 = r25
            r2 = r18
            com.google.android.gms.internal.measurement.zzh.zzh(r2, r0, r1)
            goto L_0x0680
        L_0x01e9:
            r0 = 0
            r3 = r22
            r1 = r25
            com.google.android.gms.internal.measurement.zzh.zzh(r15, r0, r1)
            java.lang.String r0 = r3.zza
            com.google.android.gms.internal.measurement.zzat r1 = new com.google.android.gms.internal.measurement.zzat
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r0 = r0.toLowerCase(r2)
            r1.<init>(r0)
            goto L_0x06ca
        L_0x0200:
            r0 = 0
            r3 = r22
            r1 = r25
            com.google.android.gms.internal.measurement.zzh.zzh(r2, r0, r1)
            java.lang.String r0 = r3.zza
            com.google.android.gms.internal.measurement.zzat r1 = new com.google.android.gms.internal.measurement.zzat
            java.lang.String r0 = r0.toLowerCase()
            r1.<init>(r0)
            goto L_0x06ca
        L_0x0215:
            r0 = 0
            r3 = r22
            r1 = r25
            com.google.android.gms.internal.measurement.zzh.zzh(r5, r0, r1)
            java.lang.String r0 = r3.zza
            com.google.android.gms.internal.measurement.zzat r1 = new com.google.android.gms.internal.measurement.zzat
            java.lang.String r0 = r0.toUpperCase()
            r1.<init>(r0)
            goto L_0x06ca
        L_0x022a:
            r3 = r22
            r1 = r25
            r0 = 2
            com.google.android.gms.internal.measurement.zzh.zzj(r8, r0, r1)
            java.lang.String r0 = r3.zza
            int r2 = r25.size()
            if (r2 <= 0) goto L_0x0255
            r2 = 0
            java.lang.Object r4 = r1.get(r2)
            com.google.android.gms.internal.measurement.zzap r4 = (com.google.android.gms.internal.measurement.zzap) r4
            r2 = r24
            com.google.android.gms.internal.measurement.zzap r4 = r2.zzb(r4)
            java.lang.Double r4 = r4.zzh()
            double r4 = r4.doubleValue()
            double r4 = com.google.android.gms.internal.measurement.zzh.zza(r4)
            int r4 = (int) r4
            goto L_0x0258
        L_0x0255:
            r2 = r24
            r4 = 0
        L_0x0258:
            int r5 = r25.size()
            r6 = 1
            if (r5 <= r6) goto L_0x0277
            java.lang.Object r1 = r1.get(r6)
            com.google.android.gms.internal.measurement.zzap r1 = (com.google.android.gms.internal.measurement.zzap) r1
            com.google.android.gms.internal.measurement.zzap r1 = r2.zzb(r1)
            java.lang.Double r1 = r1.zzh()
            double r1 = r1.doubleValue()
            double r1 = com.google.android.gms.internal.measurement.zzh.zza(r1)
            int r1 = (int) r1
            goto L_0x027b
        L_0x0277:
            int r1 = r0.length()
        L_0x027b:
            r2 = 0
            int r4 = java.lang.Math.max(r4, r2)
            int r5 = r0.length()
            int r4 = java.lang.Math.min(r4, r5)
            int r1 = java.lang.Math.max(r1, r2)
            int r2 = r0.length()
            int r1 = java.lang.Math.min(r1, r2)
            com.google.android.gms.internal.measurement.zzat r2 = new com.google.android.gms.internal.measurement.zzat
            int r5 = java.lang.Math.min(r4, r1)
            int r1 = java.lang.Math.max(r4, r1)
            java.lang.String r0 = r0.substring(r5, r1)
            r2.<init>(r0)
            r1 = r2
            goto L_0x06ca
        L_0x02a9:
            r3 = r22
            r2 = r24
            r1 = r25
            r4 = 2
            com.google.android.gms.internal.measurement.zzh.zzj(r9, r4, r1)
            java.lang.String r4 = r3.zza
            int r5 = r4.length()
            if (r5 != 0) goto L_0x02cc
            com.google.android.gms.internal.measurement.zzae r1 = new com.google.android.gms.internal.measurement.zzae
            r0 = 1
            com.google.android.gms.internal.measurement.zzap[] r0 = new com.google.android.gms.internal.measurement.zzap[r0]
            r2 = 0
            r0[r2] = r3
            java.util.List r0 = java.util.Arrays.asList(r0)
            r1.<init>(r0)
            goto L_0x06ca
        L_0x02cc:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            int r6 = r25.size()
            if (r6 != 0) goto L_0x02dc
            r5.add(r3)
            goto L_0x0359
        L_0x02dc:
            r6 = 0
            java.lang.Object r7 = r1.get(r6)
            com.google.android.gms.internal.measurement.zzap r7 = (com.google.android.gms.internal.measurement.zzap) r7
            com.google.android.gms.internal.measurement.zzap r6 = r2.zzb(r7)
            java.lang.String r6 = r6.zzi()
            int r7 = r25.size()
            r8 = 1
            if (r7 <= r8) goto L_0x030a
            java.lang.Object r1 = r1.get(r8)
            com.google.android.gms.internal.measurement.zzap r1 = (com.google.android.gms.internal.measurement.zzap) r1
            com.google.android.gms.internal.measurement.zzap r1 = r2.zzb(r1)
            java.lang.Double r1 = r1.zzh()
            double r1 = r1.doubleValue()
            long r1 = com.google.android.gms.internal.measurement.zzh.zzd(r1)
            goto L_0x030d
        L_0x030a:
            r1 = 2147483647(0x7fffffff, double:1.060997895E-314)
        L_0x030d:
            r7 = 0
            int r7 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r7 != 0) goto L_0x031a
            com.google.android.gms.internal.measurement.zzae r1 = new com.google.android.gms.internal.measurement.zzae
            r1.<init>()
            goto L_0x06ca
        L_0x031a:
            java.lang.String r7 = java.util.regex.Pattern.quote(r6)
            int r8 = (int) r1
            r9 = 1
            int r8 = r8 + r9
            java.lang.String[] r4 = r4.split(r7, r8)
            int r7 = r4.length
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0341
            if (r7 <= 0) goto L_0x0341
            r6 = 0
            r6 = r4[r6]
            boolean r13 = r6.equals(r0)
            int r6 = r7 + -1
            r8 = r4[r6]
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x0343
            r6 = r7
            goto L_0x0343
        L_0x0341:
            r6 = r7
            r13 = 0
        L_0x0343:
            long r7 = (long) r7
            int r0 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x034a
            int r6 = r6 + -1
        L_0x034a:
            if (r13 >= r6) goto L_0x0359
            com.google.android.gms.internal.measurement.zzat r0 = new com.google.android.gms.internal.measurement.zzat
            r1 = r4[r13]
            r0.<init>(r1)
            r5.add(r0)
            int r13 = r13 + 1
            goto L_0x034a
        L_0x0359:
            com.google.android.gms.internal.measurement.zzae r1 = new com.google.android.gms.internal.measurement.zzae
            r1.<init>(r5)
            goto L_0x06ca
        L_0x0360:
            r3 = r22
            r2 = r24
            r1 = r25
            r0 = 2
            com.google.android.gms.internal.measurement.zzh.zzj(r10, r0, r1)
            java.lang.String r0 = r3.zza
            int r4 = r25.size()
            if (r4 <= 0) goto L_0x0386
            r4 = 0
            java.lang.Object r5 = r1.get(r4)
            com.google.android.gms.internal.measurement.zzap r5 = (com.google.android.gms.internal.measurement.zzap) r5
            com.google.android.gms.internal.measurement.zzap r4 = r2.zzb(r5)
            java.lang.Double r4 = r4.zzh()
            double r4 = r4.doubleValue()
            goto L_0x0387
        L_0x0386:
            r4 = r12
        L_0x0387:
            double r4 = com.google.android.gms.internal.measurement.zzh.zza(r4)
            int r6 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r6 >= 0) goto L_0x039a
            int r6 = r0.length()
            double r6 = (double) r6
            double r6 = r6 + r4
            double r4 = java.lang.Math.max(r6, r12)
            goto L_0x03a3
        L_0x039a:
            int r6 = r0.length()
            double r6 = (double) r6
            double r4 = java.lang.Math.min(r4, r6)
        L_0x03a3:
            int r4 = (int) r4
            int r5 = r25.size()
            r6 = 1
            if (r5 <= r6) goto L_0x03be
            java.lang.Object r1 = r1.get(r6)
            com.google.android.gms.internal.measurement.zzap r1 = (com.google.android.gms.internal.measurement.zzap) r1
            com.google.android.gms.internal.measurement.zzap r1 = r2.zzb(r1)
            java.lang.Double r1 = r1.zzh()
            double r1 = r1.doubleValue()
            goto L_0x03c3
        L_0x03be:
            int r1 = r0.length()
            double r1 = (double) r1
        L_0x03c3:
            double r1 = com.google.android.gms.internal.measurement.zzh.zza(r1)
            int r5 = (r1 > r12 ? 1 : (r1 == r12 ? 0 : -1))
            if (r5 >= 0) goto L_0x03d6
            int r5 = r0.length()
            double r5 = (double) r5
            double r5 = r5 + r1
            double r1 = java.lang.Math.max(r5, r12)
            goto L_0x03df
        L_0x03d6:
            int r5 = r0.length()
            double r5 = (double) r5
            double r1 = java.lang.Math.min(r1, r5)
        L_0x03df:
            int r1 = (int) r1
            int r1 = r1 - r4
            r2 = 0
            int r1 = java.lang.Math.max(r2, r1)
            com.google.android.gms.internal.measurement.zzat r2 = new com.google.android.gms.internal.measurement.zzat
            int r1 = r1 + r4
            java.lang.String r0 = r0.substring(r4, r1)
            r2.<init>(r0)
            r1 = r2
            goto L_0x06ca
        L_0x03f3:
            r3 = r22
            r2 = r24
            r1 = r25
            r0 = 1
            com.google.android.gms.internal.measurement.zzh.zzj(r14, r0, r1)
            int r0 = r25.size()
            if (r0 <= 0) goto L_0x0413
            r0 = 0
            java.lang.Object r0 = r1.get(r0)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r2.zzb(r0)
            java.lang.String r19 = r0.zzi()
            goto L_0x0414
        L_0x0413:
        L_0x0414:
            java.lang.String r0 = r3.zza
            java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r19)
            java.util.regex.Matcher r0 = r1.matcher(r0)
            boolean r1 = r0.find()
            if (r1 == 0) goto L_0x0434
            com.google.android.gms.internal.measurement.zzah r1 = new com.google.android.gms.internal.measurement.zzah
            int r0 = r0.start()
            double r4 = (double) r0
            java.lang.Double r0 = java.lang.Double.valueOf(r4)
            r1.<init>(r0)
            goto L_0x06ca
        L_0x0434:
            com.google.android.gms.internal.measurement.zzah r1 = new com.google.android.gms.internal.measurement.zzah
            r4 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            java.lang.Double r0 = java.lang.Double.valueOf(r4)
            r1.<init>(r0)
            goto L_0x06ca
        L_0x0441:
            r3 = r22
            r2 = r24
            r1 = r25
            r0 = 2
            com.google.android.gms.internal.measurement.zzh.zzj(r7, r0, r1)
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzf
            int r4 = r25.size()
            if (r4 <= 0) goto L_0x0476
            r4 = 0
            java.lang.Object r5 = r1.get(r4)
            com.google.android.gms.internal.measurement.zzap r5 = (com.google.android.gms.internal.measurement.zzap) r5
            com.google.android.gms.internal.measurement.zzap r4 = r2.zzb(r5)
            java.lang.String r19 = r4.zzi()
            int r4 = r25.size()
            r5 = 1
            if (r4 <= r5) goto L_0x0476
            java.lang.Object r0 = r1.get(r5)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r2.zzb(r0)
            r1 = r19
            goto L_0x0478
        L_0x0476:
            r1 = r19
        L_0x0478:
            java.lang.String r4 = r3.zza
            int r5 = r4.indexOf(r1)
            if (r5 < 0) goto L_0x0680
            boolean r6 = r0 instanceof com.google.android.gms.internal.measurement.zzai
            if (r6 == 0) goto L_0x04aa
            com.google.android.gms.internal.measurement.zzai r0 = (com.google.android.gms.internal.measurement.zzai) r0
            r6 = 3
            com.google.android.gms.internal.measurement.zzap[] r6 = new com.google.android.gms.internal.measurement.zzap[r6]
            com.google.android.gms.internal.measurement.zzat r7 = new com.google.android.gms.internal.measurement.zzat
            r7.<init>(r1)
            r8 = 0
            r6[r8] = r7
            com.google.android.gms.internal.measurement.zzah r7 = new com.google.android.gms.internal.measurement.zzah
            double r8 = (double) r5
            java.lang.Double r8 = java.lang.Double.valueOf(r8)
            r7.<init>(r8)
            r8 = 1
            r6[r8] = r7
            r7 = 2
            r6[r7] = r3
            java.util.List r6 = java.util.Arrays.asList(r6)
            com.google.android.gms.internal.measurement.zzap r0 = r0.zza(r2, r6)
            goto L_0x04ab
        L_0x04aa:
        L_0x04ab:
            com.google.android.gms.internal.measurement.zzat r2 = new com.google.android.gms.internal.measurement.zzat
            r6 = 0
            java.lang.String r6 = r4.substring(r6, r5)
            java.lang.String r0 = r0.zzi()
            int r1 = r1.length()
            int r5 = r5 + r1
            java.lang.String r1 = r4.substring(r5)
            java.lang.String r4 = java.lang.String.valueOf(r6)
            int r4 = r4.length()
            java.lang.String r5 = java.lang.String.valueOf(r0)
            int r5 = r5.length()
            java.lang.String r7 = java.lang.String.valueOf(r1)
            int r7 = r7.length()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            int r4 = r4 + r5
            int r4 = r4 + r7
            r8.<init>(r4)
            r8.append(r6)
            r8.append(r0)
            r8.append(r1)
            java.lang.String r0 = r8.toString()
            r2.<init>(r0)
            r1 = r2
            goto L_0x06ca
        L_0x04f1:
            r3 = r22
            r2 = r24
            r1 = r25
            r4 = 1
            com.google.android.gms.internal.measurement.zzh.zzj(r11, r4, r1)
            java.lang.String r4 = r3.zza
            int r5 = r25.size()
            if (r5 > 0) goto L_0x0504
            goto L_0x0514
        L_0x0504:
            r0 = 0
            java.lang.Object r1 = r1.get(r0)
            com.google.android.gms.internal.measurement.zzap r1 = (com.google.android.gms.internal.measurement.zzap) r1
            com.google.android.gms.internal.measurement.zzap r0 = r2.zzb(r1)
            java.lang.String r0 = r0.zzi()
        L_0x0514:
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            java.util.regex.Matcher r0 = r0.matcher(r4)
            boolean r1 = r0.find()
            if (r1 == 0) goto L_0x053c
            com.google.android.gms.internal.measurement.zzae r1 = new com.google.android.gms.internal.measurement.zzae
            r2 = 1
            com.google.android.gms.internal.measurement.zzap[] r2 = new com.google.android.gms.internal.measurement.zzap[r2]
            com.google.android.gms.internal.measurement.zzat r4 = new com.google.android.gms.internal.measurement.zzat
            java.lang.String r0 = r0.group()
            r4.<init>(r0)
            r0 = 0
            r2[r0] = r4
            java.util.List r0 = java.util.Arrays.asList(r2)
            r1.<init>(r0)
            goto L_0x06ca
        L_0x053c:
            com.google.android.gms.internal.measurement.zzap r1 = com.google.android.gms.internal.measurement.zzap.zzg
            goto L_0x06ca
        L_0x0540:
            r3 = r22
            r2 = r24
            r1 = r25
            r0 = r20
            r4 = 2
            com.google.android.gms.internal.measurement.zzh.zzj(r0, r4, r1)
            java.lang.String r0 = r3.zza
            int r4 = r25.size()
            if (r4 > 0) goto L_0x0557
            r4 = r19
            goto L_0x0569
        L_0x0557:
            r4 = 0
            java.lang.Object r4 = r1.get(r4)
            com.google.android.gms.internal.measurement.zzap r4 = (com.google.android.gms.internal.measurement.zzap) r4
            com.google.android.gms.internal.measurement.zzap r4 = r2.zzb(r4)
            java.lang.String r19 = r4.zzi()
            r4 = r19
        L_0x0569:
            int r5 = r25.size()
            r6 = 2
            if (r5 >= r6) goto L_0x0573
            r1 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            goto L_0x0587
        L_0x0573:
            r5 = 1
            java.lang.Object r1 = r1.get(r5)
            com.google.android.gms.internal.measurement.zzap r1 = (com.google.android.gms.internal.measurement.zzap) r1
            com.google.android.gms.internal.measurement.zzap r1 = r2.zzb(r1)
            java.lang.Double r1 = r1.zzh()
            double r1 = r1.doubleValue()
        L_0x0587:
            boolean r5 = java.lang.Double.isNaN(r1)
            if (r5 == 0) goto L_0x0590
            r1 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            goto L_0x0594
        L_0x0590:
            double r1 = com.google.android.gms.internal.measurement.zzh.zza(r1)
        L_0x0594:
            com.google.android.gms.internal.measurement.zzah r5 = new com.google.android.gms.internal.measurement.zzah
            int r1 = (int) r1
            int r0 = r0.lastIndexOf(r4, r1)
            double r0 = (double) r0
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            r5.<init>(r0)
            r1 = r5
            goto L_0x06ca
        L_0x05a6:
            r3 = r22
            r2 = r24
            r1 = r25
            r0 = 2
            com.google.android.gms.internal.measurement.zzh.zzj(r6, r0, r1)
            java.lang.String r0 = r3.zza
            int r4 = r25.size()
            if (r4 > 0) goto L_0x05bb
            r4 = r19
            goto L_0x05cd
        L_0x05bb:
            r4 = 0
            java.lang.Object r4 = r1.get(r4)
            com.google.android.gms.internal.measurement.zzap r4 = (com.google.android.gms.internal.measurement.zzap) r4
            com.google.android.gms.internal.measurement.zzap r4 = r2.zzb(r4)
            java.lang.String r19 = r4.zzi()
            r4 = r19
        L_0x05cd:
            int r5 = r25.size()
            r6 = 2
            if (r5 >= r6) goto L_0x05d5
            goto L_0x05e9
        L_0x05d5:
            r5 = 1
            java.lang.Object r1 = r1.get(r5)
            com.google.android.gms.internal.measurement.zzap r1 = (com.google.android.gms.internal.measurement.zzap) r1
            com.google.android.gms.internal.measurement.zzap r1 = r2.zzb(r1)
            java.lang.Double r1 = r1.zzh()
            double r12 = r1.doubleValue()
        L_0x05e9:
            double r1 = com.google.android.gms.internal.measurement.zzh.zza(r12)
            com.google.android.gms.internal.measurement.zzah r5 = new com.google.android.gms.internal.measurement.zzah
            int r1 = (int) r1
            int r0 = r0.indexOf(r4, r1)
            double r0 = (double) r0
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            r5.<init>(r0)
            r1 = r5
            goto L_0x06ca
        L_0x05ff:
            r2 = r24
            r1 = r25
            r0 = r3
            r3 = r22
            r4 = 1
            com.google.android.gms.internal.measurement.zzh.zzh(r0, r4, r1)
            java.lang.String r0 = r3.zza
            r4 = 0
            java.lang.Object r1 = r1.get(r4)
            com.google.android.gms.internal.measurement.zzap r1 = (com.google.android.gms.internal.measurement.zzap) r1
            com.google.android.gms.internal.measurement.zzap r1 = r2.zzb(r1)
            java.lang.String r2 = r1.zzi()
            java.lang.String r4 = "length"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0627
            com.google.android.gms.internal.measurement.zzap r1 = com.google.android.gms.internal.measurement.zzaf.zzk
            goto L_0x06ca
        L_0x0627:
            java.lang.Double r1 = r1.zzh()
            double r1 = r1.doubleValue()
            double r4 = java.lang.Math.floor(r1)
            int r4 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r4 != 0) goto L_0x0644
            int r1 = (int) r1
            if (r1 < 0) goto L_0x0644
            int r0 = r0.length()
            if (r1 >= r0) goto L_0x0644
            com.google.android.gms.internal.measurement.zzap r1 = com.google.android.gms.internal.measurement.zzaf.zzk
            goto L_0x06ca
        L_0x0644:
            com.google.android.gms.internal.measurement.zzap r1 = com.google.android.gms.internal.measurement.zzaf.zzl
            goto L_0x06ca
        L_0x0648:
            r3 = r22
            r2 = r24
            r1 = r25
            int r0 = r25.size()
            if (r0 == 0) goto L_0x0680
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r4 = r3.zza
            r0.<init>(r4)
            r13 = 0
        L_0x065c:
            int r4 = r25.size()
            if (r13 >= r4) goto L_0x0676
            java.lang.Object r4 = r1.get(r13)
            com.google.android.gms.internal.measurement.zzap r4 = (com.google.android.gms.internal.measurement.zzap) r4
            com.google.android.gms.internal.measurement.zzap r4 = r2.zzb(r4)
            java.lang.String r4 = r4.zzi()
            r0.append(r4)
            int r13 = r13 + 1
            goto L_0x065c
        L_0x0676:
            com.google.android.gms.internal.measurement.zzat r1 = new com.google.android.gms.internal.measurement.zzat
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            goto L_0x06ca
        L_0x0680:
            r1 = r3
            goto L_0x06ca
        L_0x0682:
            r3 = r22
            r2 = r24
            r1 = r25
            r0 = r21
            r4 = 1
            com.google.android.gms.internal.measurement.zzh.zzj(r0, r4, r1)
            int r0 = r25.size()
            if (r0 <= 0) goto L_0x06ad
            r0 = 0
            java.lang.Object r0 = r1.get(r0)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r2.zzb(r0)
            java.lang.Double r0 = r0.zzh()
            double r0 = r0.doubleValue()
            double r0 = com.google.android.gms.internal.measurement.zzh.zza(r0)
            int r13 = (int) r0
            goto L_0x06af
        L_0x06ad:
            r0 = 0
            r13 = r0
        L_0x06af:
            java.lang.String r0 = r3.zza
            if (r13 < 0) goto L_0x06c8
            int r1 = r0.length()
            if (r13 < r1) goto L_0x06ba
            goto L_0x06c8
        L_0x06ba:
            com.google.android.gms.internal.measurement.zzat r1 = new com.google.android.gms.internal.measurement.zzat
            char r0 = r0.charAt(r13)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1.<init>(r0)
            goto L_0x06ca
        L_0x06c8:
            com.google.android.gms.internal.measurement.zzap r1 = com.google.android.gms.internal.measurement.zzap.zzm
        L_0x06ca:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzat.zzbK(java.lang.String, com.google.android.gms.internal.measurement.zzg, java.util.List):com.google.android.gms.internal.measurement.zzap");
    }

    public final zzap zzd() {
        return new zzat(this.zza);
    }

    public final Boolean zzg() {
        return Boolean.valueOf(!this.zza.isEmpty());
    }

    public final Double zzh() {
        if (this.zza.isEmpty()) {
            return Double.valueOf(0.0d);
        }
        try {
            return Double.valueOf(this.zza);
        } catch (NumberFormatException e) {
            return Double.valueOf(Double.NaN);
        }
    }

    public final String zzi() {
        return this.zza;
    }

    public final Iterator<zzap> zzl() {
        return new zzar(this);
    }
}
