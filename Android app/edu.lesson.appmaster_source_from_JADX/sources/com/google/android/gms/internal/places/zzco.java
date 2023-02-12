package com.google.android.gms.internal.places;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

final class zzco<T> implements zzda<T> {
    private static final int[] zzkq = new int[0];
    private static final Unsafe zzkr = zzdy.zzdn();
    private final int[] zzks;
    private final Object[] zzkt;
    private final int zzku;
    private final int zzkv;
    private final zzck zzkw;
    private final boolean zzkx;
    private final boolean zzky;
    private final boolean zzkz;
    private final boolean zzla;
    private final int[] zzlb;
    private final int zzlc;
    private final int zzld;
    private final zzcs zzle;
    private final zzbu zzlf;
    private final zzds<?, ?> zzlg;
    private final zzar<?> zzlh;
    private final zzcd zzli;

    private zzco(int[] iArr, Object[] objArr, int i, int i2, zzck zzck, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzcs zzcs, zzbu zzbu, zzds<?, ?> zzds, zzar<?> zzar, zzcd zzcd) {
        this.zzks = iArr;
        this.zzkt = objArr;
        this.zzku = i;
        this.zzkv = i2;
        this.zzky = zzck instanceof zzbc;
        this.zzkz = z;
        this.zzkx = zzar != null && zzar.zzf(zzck);
        this.zzla = false;
        this.zzlb = iArr2;
        this.zzlc = i3;
        this.zzld = i4;
        this.zzle = zzcs;
        this.zzlf = zzbu;
        this.zzlg = zzds;
        this.zzlh = zzar;
        this.zzkw = zzck;
        this.zzli = zzcd;
    }

    /* JADX WARNING: Removed duplicated region for block: B:161:0x037c  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x03e1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> com.google.android.gms.internal.places.zzco<T> zzb(java.lang.Class<T> r36, com.google.android.gms.internal.places.zzci r37, com.google.android.gms.internal.places.zzcs r38, com.google.android.gms.internal.places.zzbu r39, com.google.android.gms.internal.places.zzds<?, ?> r40, com.google.android.gms.internal.places.zzar<?> r41, com.google.android.gms.internal.places.zzcd r42) {
        /*
            r0 = r37
            boolean r1 = r0 instanceof com.google.android.gms.internal.places.zzcx
            if (r1 == 0) goto L_0x0460
            com.google.android.gms.internal.places.zzcx r0 = (com.google.android.gms.internal.places.zzcx) r0
            int r1 = r0.zzcj()
            int r2 = com.google.android.gms.internal.places.zzbc.zze.zziu
            r3 = 0
            if (r1 != r2) goto L_0x0013
            r11 = 1
            goto L_0x0014
        L_0x0013:
            r11 = r3
        L_0x0014:
            java.lang.String r1 = r0.zzcr()
            int r2 = r1.length()
            char r5 = r1.charAt(r3)
            r7 = 55296(0xd800, float:7.7486E-41)
            if (r5 < r7) goto L_0x003e
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            r8 = 1
            r9 = 13
        L_0x002b:
            int r10 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x003b
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r9
            r5 = r5 | r8
            int r9 = r9 + 13
            r8 = r10
            goto L_0x002b
        L_0x003b:
            int r8 = r8 << r9
            r5 = r5 | r8
            goto L_0x003f
        L_0x003e:
            r10 = 1
        L_0x003f:
            int r8 = r10 + 1
            char r9 = r1.charAt(r10)
            if (r9 < r7) goto L_0x005f
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x004c:
            int r12 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x005c
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r10
            r9 = r9 | r8
            int r10 = r10 + 13
            r8 = r12
            goto L_0x004c
        L_0x005c:
            int r8 = r8 << r10
            r9 = r9 | r8
            r8 = r12
        L_0x005f:
            if (r9 != 0) goto L_0x0073
            int[] r9 = zzkq
            r6 = r3
            r10 = r6
            r12 = r10
            r13 = r12
            r15 = r13
            r14 = r9
            r9 = r15
            goto L_0x019b
        L_0x0073:
            int r9 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x0092
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x007f:
            int r12 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r7) goto L_0x008f
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            int r9 = r9 << r10
            r8 = r8 | r9
            int r10 = r10 + 13
            r9 = r12
            goto L_0x007f
        L_0x008f:
            int r9 = r9 << r10
            r8 = r8 | r9
            r9 = r12
        L_0x0092:
            int r10 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r7) goto L_0x00b2
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x009f:
            int r13 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r7) goto L_0x00af
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            int r10 = r10 << r12
            r9 = r9 | r10
            int r12 = r12 + 13
            r10 = r13
            goto L_0x009f
        L_0x00af:
            int r10 = r10 << r12
            r9 = r9 | r10
            r10 = r13
        L_0x00b2:
            int r12 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r7) goto L_0x00d2
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00bf:
            int r14 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r7) goto L_0x00cf
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r13
            r10 = r10 | r12
            int r13 = r13 + 13
            r12 = r14
            goto L_0x00bf
        L_0x00cf:
            int r12 = r12 << r13
            r10 = r10 | r12
            r12 = r14
        L_0x00d2:
            int r13 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r7) goto L_0x00f2
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x00df:
            int r15 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r7) goto L_0x00ef
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            int r13 = r13 << r14
            r12 = r12 | r13
            int r14 = r14 + 13
            r13 = r15
            goto L_0x00df
        L_0x00ef:
            int r13 = r13 << r14
            r12 = r12 | r13
            r13 = r15
        L_0x00f2:
            int r14 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r7) goto L_0x0114
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x00ff:
            int r16 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r7) goto L_0x0110
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r14 = r14 << r15
            r13 = r13 | r14
            int r15 = r15 + 13
            r14 = r16
            goto L_0x00ff
        L_0x0110:
            int r14 = r14 << r15
            r13 = r13 | r14
            r14 = r16
        L_0x0114:
            int r15 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r7) goto L_0x0138
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x0121:
            int r17 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r7) goto L_0x0133
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r16
            r14 = r14 | r15
            int r16 = r16 + 13
            r15 = r17
            goto L_0x0121
        L_0x0133:
            int r15 = r15 << r16
            r14 = r14 | r15
            r15 = r17
        L_0x0138:
            int r16 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r7) goto L_0x015f
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            r3 = r16
            r16 = 13
        L_0x0147:
            int r17 = r3 + 1
            char r3 = r1.charAt(r3)
            if (r3 < r7) goto L_0x0159
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            int r3 = r3 << r16
            r15 = r15 | r3
            int r16 = r16 + 13
            r3 = r17
            goto L_0x0147
        L_0x0159:
            int r3 = r3 << r16
            r15 = r15 | r3
            r3 = r17
            goto L_0x0161
        L_0x015f:
            r3 = r16
        L_0x0161:
            int r16 = r3 + 1
            char r3 = r1.charAt(r3)
            if (r3 < r7) goto L_0x0187
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r6 = r16
            r16 = 13
        L_0x0170:
            int r17 = r6 + 1
            char r6 = r1.charAt(r6)
            if (r6 < r7) goto L_0x0182
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            int r6 = r6 << r16
            r3 = r3 | r6
            int r16 = r16 + 13
            r6 = r17
            goto L_0x0170
        L_0x0182:
            int r6 = r6 << r16
            r3 = r3 | r6
            r16 = r17
        L_0x0187:
            int r6 = r3 + r14
            int r6 = r6 + r15
            int[] r6 = new int[r6]
            int r15 = r8 << 1
            int r15 = r15 + r9
            r9 = r12
            r12 = r15
            r15 = r3
            r3 = r8
            r8 = r16
            r35 = r14
            r14 = r6
            r6 = r35
        L_0x019b:
            sun.misc.Unsafe r7 = zzkr
            java.lang.Object[] r17 = r0.zzcs()
            com.google.android.gms.internal.places.zzck r18 = r0.zzcl()
            java.lang.Class r4 = r18.getClass()
            r18 = r8
            int r8 = r13 * 3
            int[] r8 = new int[r8]
            r19 = 1
            int r13 = r13 << 1
            java.lang.Object[] r13 = new java.lang.Object[r13]
            int r20 = r15 + r6
            r22 = r15
            r6 = r18
            r23 = r20
            r18 = 0
            r21 = 0
        L_0x01c3:
            if (r6 >= r2) goto L_0x0437
            int r24 = r6 + 1
            char r6 = r1.charAt(r6)
            r25 = r2
            r2 = 55296(0xd800, float:7.7486E-41)
            if (r6 < r2) goto L_0x01f7
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            r2 = r24
            r24 = 13
        L_0x01d8:
            int r26 = r2 + 1
            char r2 = r1.charAt(r2)
            r27 = r15
            r15 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r15) goto L_0x01f1
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r24
            r6 = r6 | r2
            int r24 = r24 + 13
            r2 = r26
            r15 = r27
            goto L_0x01d8
        L_0x01f1:
            int r2 = r2 << r24
            r6 = r6 | r2
            r2 = r26
            goto L_0x01fb
        L_0x01f7:
            r27 = r15
            r2 = r24
        L_0x01fb:
            int r15 = r2 + 1
            char r2 = r1.charAt(r2)
            r24 = r15
            r15 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r15) goto L_0x022e
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r15 = r24
            r24 = 13
        L_0x020f:
            int r26 = r15 + 1
            char r15 = r1.charAt(r15)
            r28 = r11
            r11 = 55296(0xd800, float:7.7486E-41)
            if (r15 < r11) goto L_0x0228
            r11 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r11 = r11 << r24
            r2 = r2 | r11
            int r24 = r24 + 13
            r15 = r26
            r11 = r28
            goto L_0x020f
        L_0x0228:
            int r11 = r15 << r24
            r2 = r2 | r11
            r15 = r26
            goto L_0x0232
        L_0x022e:
            r28 = r11
            r15 = r24
        L_0x0232:
            r11 = r2 & 255(0xff, float:3.57E-43)
            r24 = r9
            r9 = r2 & 1024(0x400, float:1.435E-42)
            if (r9 == 0) goto L_0x0241
            int r9 = r18 + 1
            r14[r18] = r21
            r18 = r9
        L_0x0241:
            r9 = 51
            r30 = r10
            if (r11 < r9) goto L_0x02eb
            int r9 = r15 + 1
            char r15 = r1.charAt(r15)
            r10 = 55296(0xd800, float:7.7486E-41)
            if (r15 < r10) goto L_0x0270
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            r32 = 13
        L_0x0256:
            int r33 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r10) goto L_0x026b
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            int r9 = r9 << r32
            r15 = r15 | r9
            int r32 = r32 + 13
            r9 = r33
            r10 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0256
        L_0x026b:
            int r9 = r9 << r32
            r15 = r15 | r9
            r9 = r33
        L_0x0270:
            int r10 = r11 + -51
            r32 = r9
            r9 = 9
            if (r10 == r9) goto L_0x0296
            r9 = 17
            if (r10 != r9) goto L_0x027e
            goto L_0x0296
        L_0x027e:
            r9 = 12
            if (r10 != r9) goto L_0x0294
            r9 = r5 & 1
            r10 = 1
            if (r9 != r10) goto L_0x0294
            int r9 = r21 / 3
            int r9 = r9 << r10
            int r9 = r9 + r10
            int r10 = r12 + 1
            r12 = r17[r12]
            r13[r9] = r12
            r12 = r10
            r10 = 1
            goto L_0x02a3
        L_0x0294:
            r10 = 1
            goto L_0x02a3
        L_0x0296:
            int r9 = r21 / 3
            r10 = 1
            int r9 = r9 << r10
            int r9 = r9 + r10
            int r19 = r12 + 1
            r12 = r17[r12]
            r13[r9] = r12
            r12 = r19
        L_0x02a3:
            int r9 = r15 << 1
            r10 = r17[r9]
            boolean r15 = r10 instanceof java.lang.reflect.Field
            if (r15 == 0) goto L_0x02ae
            java.lang.reflect.Field r10 = (java.lang.reflect.Field) r10
            goto L_0x02b6
        L_0x02ae:
            java.lang.String r10 = (java.lang.String) r10
            java.lang.reflect.Field r10 = zzb((java.lang.Class<?>) r4, (java.lang.String) r10)
            r17[r9] = r10
        L_0x02b6:
            r33 = r0
            r34 = r1
            long r0 = r7.objectFieldOffset(r10)
            int r0 = (int) r0
            int r9 = r9 + 1
            r1 = r17[r9]
            boolean r10 = r1 instanceof java.lang.reflect.Field
            if (r10 == 0) goto L_0x02ca
            java.lang.reflect.Field r1 = (java.lang.reflect.Field) r1
            goto L_0x02d2
        L_0x02ca:
            java.lang.String r1 = (java.lang.String) r1
            java.lang.reflect.Field r1 = zzb((java.lang.Class<?>) r4, (java.lang.String) r1)
            r17[r9] = r1
        L_0x02d2:
            long r9 = r7.objectFieldOffset(r1)
            int r1 = (int) r9
            r19 = r4
            r31 = r12
            r15 = r32
            r10 = r34
            r12 = 0
            r16 = 1
            r4 = r1
            r1 = r0
            r0 = r3
            r3 = 55296(0xd800, float:7.7486E-41)
            goto L_0x03fe
        L_0x02eb:
            r33 = r0
            r34 = r1
            int r0 = r12 + 1
            r1 = r17[r12]
            java.lang.String r1 = (java.lang.String) r1
            java.lang.reflect.Field r1 = zzb((java.lang.Class<?>) r4, (java.lang.String) r1)
            r9 = 49
            r10 = 9
            if (r11 == r10) goto L_0x0368
            r10 = 17
            if (r11 != r10) goto L_0x0306
            r12 = 1
            goto L_0x0369
        L_0x0306:
            r10 = 27
            if (r11 == r10) goto L_0x035a
            if (r11 != r9) goto L_0x030d
            goto L_0x035a
        L_0x030d:
            r10 = 12
            if (r11 == r10) goto L_0x0346
            r10 = 30
            if (r11 == r10) goto L_0x0346
            r10 = 44
            if (r11 != r10) goto L_0x031a
            goto L_0x0346
        L_0x031a:
            r10 = 50
            if (r11 != r10) goto L_0x0344
            int r10 = r22 + 1
            r14[r22] = r21
            int r12 = r21 / 3
            r19 = 1
            int r12 = r12 << 1
            int r22 = r0 + 1
            r0 = r17[r0]
            r13[r12] = r0
            r0 = r2 & 2048(0x800, float:2.87E-42)
            if (r0 == 0) goto L_0x033e
            int r12 = r12 + 1
            int r0 = r22 + 1
            r22 = r17[r22]
            r13[r12] = r22
            r22 = r10
            r12 = 1
            goto L_0x0373
        L_0x033e:
            r0 = r22
            r12 = 1
            r22 = r10
            goto L_0x0373
        L_0x0344:
            r12 = 1
            goto L_0x0373
        L_0x0346:
            r10 = r5 & 1
            r12 = 1
            if (r10 != r12) goto L_0x0358
            int r10 = r21 / 3
            int r10 = r10 << r12
            int r10 = r10 + r12
            int r12 = r0 + 1
            r0 = r17[r0]
            r13[r10] = r0
            r0 = r12
            r12 = 1
            goto L_0x0373
        L_0x0358:
            r12 = 1
            goto L_0x0373
        L_0x035a:
            int r10 = r21 / 3
            r12 = 1
            int r10 = r10 << r12
            int r10 = r10 + r12
            int r19 = r0 + 1
            r0 = r17[r0]
            r13[r10] = r0
            r0 = r19
            goto L_0x0373
        L_0x0368:
            r12 = 1
        L_0x0369:
            int r10 = r21 / 3
            int r10 = r10 << r12
            int r10 = r10 + r12
            java.lang.Class r19 = r1.getType()
            r13[r10] = r19
        L_0x0373:
            long r9 = r7.objectFieldOffset(r1)
            int r1 = (int) r9
            r9 = r5 & 1
            if (r9 != r12) goto L_0x03e1
            r9 = 17
            if (r11 > r9) goto L_0x03d4
            int r9 = r15 + 1
            r10 = r34
            char r12 = r10.charAt(r15)
            r15 = 55296(0xd800, float:7.7486E-41)
            if (r12 < r15) goto L_0x03a8
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x0391:
            int r29 = r9 + 1
            char r9 = r10.charAt(r9)
            if (r9 < r15) goto L_0x03a3
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            int r9 = r9 << r16
            r12 = r12 | r9
            int r16 = r16 + 13
            r9 = r29
            goto L_0x0391
        L_0x03a3:
            int r9 = r9 << r16
            r12 = r12 | r9
            r9 = r29
        L_0x03a8:
            r16 = 1
            int r19 = r3 << 1
            int r29 = r12 / 32
            int r19 = r19 + r29
            r15 = r17[r19]
            r31 = r0
            boolean r0 = r15 instanceof java.lang.reflect.Field
            if (r0 == 0) goto L_0x03bc
            java.lang.reflect.Field r15 = (java.lang.reflect.Field) r15
            goto L_0x03c4
        L_0x03bc:
            java.lang.String r15 = (java.lang.String) r15
            java.lang.reflect.Field r15 = zzb((java.lang.Class<?>) r4, (java.lang.String) r15)
            r17[r19] = r15
        L_0x03c4:
            r0 = r3
            r19 = r4
            long r3 = r7.objectFieldOffset(r15)
            int r3 = (int) r3
            int r12 = r12 % 32
            r4 = r3
            r15 = r9
            r3 = 55296(0xd800, float:7.7486E-41)
            goto L_0x03f0
        L_0x03d4:
            r31 = r0
            r0 = r3
            r19 = r4
            r10 = r34
            r3 = 55296(0xd800, float:7.7486E-41)
            r16 = 1
            goto L_0x03ed
        L_0x03e1:
            r31 = r0
            r0 = r3
            r19 = r4
            r16 = r12
            r10 = r34
            r3 = 55296(0xd800, float:7.7486E-41)
        L_0x03ed:
            r4 = 0
            r12 = 0
        L_0x03f0:
            r9 = 18
            if (r11 < r9) goto L_0x03fe
            r9 = 49
            if (r11 > r9) goto L_0x03fe
            int r9 = r23 + 1
            r14[r23] = r1
            r23 = r9
        L_0x03fe:
            int r9 = r21 + 1
            r8[r21] = r6
            int r6 = r9 + 1
            r3 = r2 & 512(0x200, float:7.175E-43)
            if (r3 == 0) goto L_0x040b
            r3 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x040c
        L_0x040b:
            r3 = 0
        L_0x040c:
            r2 = r2 & 256(0x100, float:3.59E-43)
            if (r2 == 0) goto L_0x0413
            r2 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x0414
        L_0x0413:
            r2 = 0
        L_0x0414:
            r2 = r2 | r3
            int r3 = r11 << 20
            r2 = r2 | r3
            r1 = r1 | r2
            r8[r9] = r1
            int r21 = r6 + 1
            int r1 = r12 << 20
            r1 = r1 | r4
            r8[r6] = r1
            r3 = r0
            r1 = r10
            r6 = r15
            r4 = r19
            r9 = r24
            r2 = r25
            r15 = r27
            r11 = r28
            r10 = r30
            r12 = r31
            r0 = r33
            goto L_0x01c3
        L_0x0437:
            r33 = r0
            r24 = r9
            r30 = r10
            r28 = r11
            r27 = r15
            com.google.android.gms.internal.places.zzco r0 = new com.google.android.gms.internal.places.zzco
            com.google.android.gms.internal.places.zzck r10 = r33.zzcl()
            r12 = 0
            r5 = r0
            r6 = r8
            r7 = r13
            r8 = r30
            r13 = r14
            r14 = r27
            r15 = r20
            r16 = r38
            r17 = r39
            r18 = r40
            r19 = r41
            r20 = r42
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            return r0
        L_0x0460:
            com.google.android.gms.internal.places.zzdl r0 = (com.google.android.gms.internal.places.zzdl) r0
            r0.zzcj()
            int r0 = com.google.android.gms.internal.places.zzbc.zze.zziu
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.places.zzco.zzb(java.lang.Class, com.google.android.gms.internal.places.zzci, com.google.android.gms.internal.places.zzcs, com.google.android.gms.internal.places.zzbu, com.google.android.gms.internal.places.zzds, com.google.android.gms.internal.places.zzar, com.google.android.gms.internal.places.zzcd):com.google.android.gms.internal.places.zzco");
    }

    private static Field zzb(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            throw new RuntimeException(new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length()).append("Field ").append(str).append(" for ").append(name).append(" not found. Known fields are ").append(arrays).toString());
        }
    }

    public final T newInstance() {
        return this.zzle.newInstance(this.zzkw);
    }

    public final boolean equals(T t, T t2) {
        int length = this.zzks.length;
        int i = 0;
        while (true) {
            boolean z = true;
            if (i < length) {
                int zzai = zzai(i);
                long j = (long) (zzai & 1048575);
                switch ((zzai & 267386880) >>> 20) {
                    case 0:
                        if (!zzd(t, t2, i) || Double.doubleToLongBits(zzdy.zzo(t, j)) != Double.doubleToLongBits(zzdy.zzo(t2, j))) {
                            z = false;
                            break;
                        }
                    case 1:
                        if (!zzd(t, t2, i) || Float.floatToIntBits(zzdy.zzn(t, j)) != Float.floatToIntBits(zzdy.zzn(t2, j))) {
                            z = false;
                            break;
                        }
                    case 2:
                        if (!zzd(t, t2, i) || zzdy.zzl(t, j) != zzdy.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 3:
                        if (!zzd(t, t2, i) || zzdy.zzl(t, j) != zzdy.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 4:
                        if (!zzd(t, t2, i) || zzdy.zzk(t, j) != zzdy.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 5:
                        if (!zzd(t, t2, i) || zzdy.zzl(t, j) != zzdy.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 6:
                        if (!zzd(t, t2, i) || zzdy.zzk(t, j) != zzdy.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 7:
                        if (!zzd(t, t2, i) || zzdy.zzm(t, j) != zzdy.zzm(t2, j)) {
                            z = false;
                            break;
                        }
                    case 8:
                        if (!zzd(t, t2, i) || !zzdc.zze(zzdy.zzp(t, j), zzdy.zzp(t2, j))) {
                            z = false;
                            break;
                        }
                    case 9:
                        if (!zzd(t, t2, i) || !zzdc.zze(zzdy.zzp(t, j), zzdy.zzp(t2, j))) {
                            z = false;
                            break;
                        }
                    case 10:
                        if (!zzd(t, t2, i) || !zzdc.zze(zzdy.zzp(t, j), zzdy.zzp(t2, j))) {
                            z = false;
                            break;
                        }
                    case 11:
                        if (!zzd(t, t2, i) || zzdy.zzk(t, j) != zzdy.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 12:
                        if (!zzd(t, t2, i) || zzdy.zzk(t, j) != zzdy.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 13:
                        if (!zzd(t, t2, i) || zzdy.zzk(t, j) != zzdy.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 14:
                        if (!zzd(t, t2, i) || zzdy.zzl(t, j) != zzdy.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 15:
                        if (!zzd(t, t2, i) || zzdy.zzk(t, j) != zzdy.zzk(t2, j)) {
                            z = false;
                            break;
                        }
                    case 16:
                        if (!zzd(t, t2, i) || zzdy.zzl(t, j) != zzdy.zzl(t2, j)) {
                            z = false;
                            break;
                        }
                    case 17:
                        if (!zzd(t, t2, i) || !zzdc.zze(zzdy.zzp(t, j), zzdy.zzp(t2, j))) {
                            z = false;
                            break;
                        }
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        z = zzdc.zze(zzdy.zzp(t, j), zzdy.zzp(t2, j));
                        break;
                    case 50:
                        z = zzdc.zze(zzdy.zzp(t, j), zzdy.zzp(t2, j));
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                    case 60:
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                    case 68:
                        long zzaj = (long) (zzaj(i) & 1048575);
                        if (zzdy.zzk(t, zzaj) != zzdy.zzk(t2, zzaj) || !zzdc.zze(zzdy.zzp(t, j), zzdy.zzp(t2, j))) {
                            z = false;
                            break;
                        }
                }
                if (!z) {
                    return false;
                }
                i += 3;
            } else if (!this.zzlg.zzr(t).equals(this.zzlg.zzr(t2))) {
                return false;
            } else {
                if (this.zzkx) {
                    return this.zzlh.zzb((Object) t).equals(this.zzlh.zzb((Object) t2));
                }
                return true;
            }
        }
    }

    public final int hashCode(T t) {
        int length = this.zzks.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int zzai = zzai(i2);
            int i3 = this.zzks[i2];
            long j = (long) (1048575 & zzai);
            int i4 = 37;
            switch ((zzai & 267386880) >>> 20) {
                case 0:
                    i = (i * 53) + zzbd.zzl(Double.doubleToLongBits(zzdy.zzo(t, j)));
                    break;
                case 1:
                    i = (i * 53) + Float.floatToIntBits(zzdy.zzn(t, j));
                    break;
                case 2:
                    i = (i * 53) + zzbd.zzl(zzdy.zzl(t, j));
                    break;
                case 3:
                    i = (i * 53) + zzbd.zzl(zzdy.zzl(t, j));
                    break;
                case 4:
                    i = (i * 53) + zzdy.zzk(t, j);
                    break;
                case 5:
                    i = (i * 53) + zzbd.zzl(zzdy.zzl(t, j));
                    break;
                case 6:
                    i = (i * 53) + zzdy.zzk(t, j);
                    break;
                case 7:
                    i = (i * 53) + zzbd.zze(zzdy.zzm(t, j));
                    break;
                case 8:
                    i = (i * 53) + ((String) zzdy.zzp(t, j)).hashCode();
                    break;
                case 9:
                    Object zzp = zzdy.zzp(t, j);
                    if (zzp != null) {
                        i4 = zzp.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 10:
                    i = (i * 53) + zzdy.zzp(t, j).hashCode();
                    break;
                case 11:
                    i = (i * 53) + zzdy.zzk(t, j);
                    break;
                case 12:
                    i = (i * 53) + zzdy.zzk(t, j);
                    break;
                case 13:
                    i = (i * 53) + zzdy.zzk(t, j);
                    break;
                case 14:
                    i = (i * 53) + zzbd.zzl(zzdy.zzl(t, j));
                    break;
                case 15:
                    i = (i * 53) + zzdy.zzk(t, j);
                    break;
                case 16:
                    i = (i * 53) + zzbd.zzl(zzdy.zzl(t, j));
                    break;
                case 17:
                    Object zzp2 = zzdy.zzp(t, j);
                    if (zzp2 != null) {
                        i4 = zzp2.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = (i * 53) + zzdy.zzp(t, j).hashCode();
                    break;
                case 50:
                    i = (i * 53) + zzdy.zzp(t, j).hashCode();
                    break;
                case 51:
                    if (!zzb(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzbd.zzl(Double.doubleToLongBits(zzf(t, j)));
                        break;
                    }
                case 52:
                    if (!zzb(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + Float.floatToIntBits(zzg(t, j));
                        break;
                    }
                case 53:
                    if (!zzb(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzbd.zzl(zzi(t, j));
                        break;
                    }
                case 54:
                    if (!zzb(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzbd.zzl(zzi(t, j));
                        break;
                    }
                case 55:
                    if (!zzb(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 56:
                    if (!zzb(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzbd.zzl(zzi(t, j));
                        break;
                    }
                case 57:
                    if (!zzb(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 58:
                    if (!zzb(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzbd.zze(zzj(t, j));
                        break;
                    }
                case 59:
                    if (!zzb(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ((String) zzdy.zzp(t, j)).hashCode();
                        break;
                    }
                case 60:
                    if (!zzb(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzdy.zzp(t, j).hashCode();
                        break;
                    }
                case 61:
                    if (!zzb(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzdy.zzp(t, j).hashCode();
                        break;
                    }
                case 62:
                    if (!zzb(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 63:
                    if (!zzb(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 64:
                    if (!zzb(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 65:
                    if (!zzb(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzbd.zzl(zzi(t, j));
                        break;
                    }
                case 66:
                    if (!zzb(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 67:
                    if (!zzb(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzbd.zzl(zzi(t, j));
                        break;
                    }
                case 68:
                    if (!zzb(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzdy.zzp(t, j).hashCode();
                        break;
                    }
            }
        }
        int hashCode = (i * 53) + this.zzlg.zzr(t).hashCode();
        if (this.zzkx) {
            return (hashCode * 53) + this.zzlh.zzb((Object) t).hashCode();
        }
        return hashCode;
    }

    public final void zzd(T t, T t2) {
        if (t2 != null) {
            for (int i = 0; i < this.zzks.length; i += 3) {
                int zzai = zzai(i);
                long j = (long) (1048575 & zzai);
                int i2 = this.zzks[i];
                switch ((zzai & 267386880) >>> 20) {
                    case 0:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzdy.zzb((Object) t, j, zzdy.zzo(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 1:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzdy.zzb((Object) t, j, zzdy.zzn(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 2:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzdy.zzb((Object) t, j, zzdy.zzl(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 3:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzdy.zzb((Object) t, j, zzdy.zzl(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 4:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzdy.zzb((Object) t, j, zzdy.zzk(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 5:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzdy.zzb((Object) t, j, zzdy.zzl(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 6:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzdy.zzb((Object) t, j, zzdy.zzk(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 7:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzdy.zzb((Object) t, j, zzdy.zzm(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 8:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzdy.zzb((Object) t, j, zzdy.zzp(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 9:
                        zzb(t, t2, i);
                        break;
                    case 10:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzdy.zzb((Object) t, j, zzdy.zzp(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 11:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzdy.zzb((Object) t, j, zzdy.zzk(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 12:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzdy.zzb((Object) t, j, zzdy.zzk(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 13:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzdy.zzb((Object) t, j, zzdy.zzk(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 14:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzdy.zzb((Object) t, j, zzdy.zzl(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 15:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzdy.zzb((Object) t, j, zzdy.zzk(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 16:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzdy.zzb((Object) t, j, zzdy.zzl(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 17:
                        zzb(t, t2, i);
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        this.zzlf.zzb(t, t2, j);
                        break;
                    case 50:
                        zzdc.zzb(this.zzli, t, t2, j);
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                        if (!zzb(t2, i2, i)) {
                            break;
                        } else {
                            zzdy.zzb((Object) t, j, zzdy.zzp(t2, j));
                            zzc(t, i2, i);
                            break;
                        }
                    case 60:
                        zzc(t, t2, i);
                        break;
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                        if (!zzb(t2, i2, i)) {
                            break;
                        } else {
                            zzdy.zzb((Object) t, j, zzdy.zzp(t2, j));
                            zzc(t, i2, i);
                            break;
                        }
                    case 68:
                        zzc(t, t2, i);
                        break;
                }
            }
            if (!this.zzkz) {
                zzdc.zzb(this.zzlg, t, t2);
                if (this.zzkx) {
                    zzdc.zzb(this.zzlh, t, t2);
                    return;
                }
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    private final void zzb(T t, T t2, int i) {
        long zzai = (long) (zzai(i) & 1048575);
        if (zzb(t2, i)) {
            Object zzp = zzdy.zzp(t, zzai);
            Object zzp2 = zzdy.zzp(t2, zzai);
            if (zzp != null && zzp2 != null) {
                zzdy.zzb((Object) t, zzai, zzbd.zzb(zzp, zzp2));
                zzc(t, i);
            } else if (zzp2 != null) {
                zzdy.zzb((Object) t, zzai, zzp2);
                zzc(t, i);
            }
        }
    }

    private final void zzc(T t, T t2, int i) {
        int zzai = zzai(i);
        int i2 = this.zzks[i];
        long j = (long) (zzai & 1048575);
        if (zzb(t2, i2, i)) {
            Object zzp = zzdy.zzp(t, j);
            Object zzp2 = zzdy.zzp(t2, j);
            if (zzp != null && zzp2 != null) {
                zzdy.zzb((Object) t, j, zzbd.zzb(zzp, zzp2));
                zzc(t, i2, i);
            } else if (zzp2 != null) {
                zzdy.zzb((Object) t, j, zzp2);
                zzc(t, i2, i);
            }
        }
    }

    public final int zzn(T t) {
        int i;
        int i2;
        long j;
        boolean z;
        int i3;
        boolean z2;
        int i4;
        T t2 = t;
        int i5 = 267386880;
        boolean z3 = true;
        int i6 = 0;
        if (this.zzkz) {
            Unsafe unsafe = zzkr;
            int i7 = 0;
            int i8 = 0;
            while (i7 < this.zzks.length) {
                int zzai = zzai(i7);
                int i9 = (zzai & i5) >>> 20;
                int i10 = this.zzks[i7];
                long j2 = (long) (zzai & 1048575);
                if (i9 < zzaw.DOUBLE_LIST_PACKED.mo34787id() || i9 > zzaw.SINT64_LIST_PACKED.mo34787id()) {
                    i4 = 0;
                } else {
                    i4 = this.zzks[i7 + 2] & 1048575;
                }
                switch (i9) {
                    case 0:
                        if (!zzb(t2, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzc(i10, 0.0d);
                            break;
                        }
                    case 1:
                        if (!zzb(t2, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzc(i10, 0.0f);
                            break;
                        }
                    case 2:
                        if (!zzb(t2, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zze(i10, zzdy.zzl(t2, j2));
                            break;
                        }
                    case 3:
                        if (!zzb(t2, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzf(i10, zzdy.zzl(t2, j2));
                            break;
                        }
                    case 4:
                        if (!zzb(t2, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzh(i10, zzdy.zzk(t2, j2));
                            break;
                        }
                    case 5:
                        if (!zzb(t2, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzh(i10, 0);
                            break;
                        }
                    case 6:
                        if (!zzb(t2, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzk(i10, 0);
                            break;
                        }
                    case 7:
                        if (!zzb(t2, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzd(i10, true);
                            break;
                        }
                    case 8:
                        if (!zzb(t2, i7)) {
                            break;
                        } else {
                            Object zzp = zzdy.zzp(t2, j2);
                            if (!(zzp instanceof zzw)) {
                                i8 += zzaj.zzc(i10, (String) zzp);
                                break;
                            } else {
                                i8 += zzaj.zzd(i10, (zzw) zzp);
                                break;
                            }
                        }
                    case 9:
                        if (!zzb(t2, i7)) {
                            break;
                        } else {
                            i8 += zzdc.zzd(i10, zzdy.zzp(t2, j2), zzaf(i7));
                            break;
                        }
                    case 10:
                        if (!zzb(t2, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzd(i10, (zzw) zzdy.zzp(t2, j2));
                            break;
                        }
                    case 11:
                        if (!zzb(t2, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzi(i10, zzdy.zzk(t2, j2));
                            break;
                        }
                    case 12:
                        if (!zzb(t2, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzm(i10, zzdy.zzk(t2, j2));
                            break;
                        }
                    case 13:
                        if (!zzb(t2, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzl(i10, 0);
                            break;
                        }
                    case 14:
                        if (!zzb(t2, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzi(i10, 0);
                            break;
                        }
                    case 15:
                        if (!zzb(t2, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzj(i10, zzdy.zzk(t2, j2));
                            break;
                        }
                    case 16:
                        if (!zzb(t2, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzg(i10, zzdy.zzl(t2, j2));
                            break;
                        }
                    case 17:
                        if (!zzb(t2, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzd(i10, (zzck) zzdy.zzp(t2, j2), zzaf(i7));
                            break;
                        }
                    case 18:
                        i8 += zzdc.zzx(i10, zze(t2, j2), false);
                        break;
                    case 19:
                        i8 += zzdc.zzw(i10, zze(t2, j2), false);
                        break;
                    case 20:
                        i8 += zzdc.zzp(i10, zze(t2, j2), false);
                        break;
                    case 21:
                        i8 += zzdc.zzq(i10, zze(t2, j2), false);
                        break;
                    case 22:
                        i8 += zzdc.zzt(i10, zze(t2, j2), false);
                        break;
                    case 23:
                        i8 += zzdc.zzx(i10, zze(t2, j2), false);
                        break;
                    case 24:
                        i8 += zzdc.zzw(i10, zze(t2, j2), false);
                        break;
                    case 25:
                        i8 += zzdc.zzy(i10, zze(t2, j2), false);
                        break;
                    case 26:
                        i8 += zzdc.zzd(i10, zze(t2, j2));
                        break;
                    case 27:
                        i8 += zzdc.zzd(i10, zze(t2, j2), zzaf(i7));
                        break;
                    case 28:
                        i8 += zzdc.zze(i10, (List<zzw>) zze(t2, j2));
                        break;
                    case 29:
                        i8 += zzdc.zzu(i10, zze(t2, j2), false);
                        break;
                    case 30:
                        i8 += zzdc.zzs(i10, zze(t2, j2), false);
                        break;
                    case 31:
                        i8 += zzdc.zzw(i10, zze(t2, j2), false);
                        break;
                    case 32:
                        i8 += zzdc.zzx(i10, zze(t2, j2), false);
                        break;
                    case 33:
                        i8 += zzdc.zzv(i10, zze(t2, j2), false);
                        break;
                    case 34:
                        i8 += zzdc.zzr(i10, zze(t2, j2), false);
                        break;
                    case 35:
                        int zzm = zzdc.zzm((List) unsafe.getObject(t2, j2));
                        if (zzm > 0) {
                            if (this.zzla) {
                                unsafe.putInt(t2, (long) i4, zzm);
                            }
                            i8 += zzaj.zzr(i10) + zzaj.zzt(zzm) + zzm;
                            break;
                        } else {
                            break;
                        }
                    case 36:
                        int zzl = zzdc.zzl((List) unsafe.getObject(t2, j2));
                        if (zzl > 0) {
                            if (this.zzla) {
                                unsafe.putInt(t2, (long) i4, zzl);
                            }
                            i8 += zzaj.zzr(i10) + zzaj.zzt(zzl) + zzl;
                            break;
                        } else {
                            break;
                        }
                    case 37:
                        int zze = zzdc.zze((List) unsafe.getObject(t2, j2));
                        if (zze > 0) {
                            if (this.zzla) {
                                unsafe.putInt(t2, (long) i4, zze);
                            }
                            i8 += zzaj.zzr(i10) + zzaj.zzt(zze) + zze;
                            break;
                        } else {
                            break;
                        }
                    case 38:
                        int zzf = zzdc.zzf((List<Long>) (List) unsafe.getObject(t2, j2));
                        if (zzf > 0) {
                            if (this.zzla) {
                                unsafe.putInt(t2, (long) i4, zzf);
                            }
                            i8 += zzaj.zzr(i10) + zzaj.zzt(zzf) + zzf;
                            break;
                        } else {
                            break;
                        }
                    case 39:
                        int zzi = zzdc.zzi((List) unsafe.getObject(t2, j2));
                        if (zzi > 0) {
                            if (this.zzla) {
                                unsafe.putInt(t2, (long) i4, zzi);
                            }
                            i8 += zzaj.zzr(i10) + zzaj.zzt(zzi) + zzi;
                            break;
                        } else {
                            break;
                        }
                    case 40:
                        int zzm2 = zzdc.zzm((List) unsafe.getObject(t2, j2));
                        if (zzm2 > 0) {
                            if (this.zzla) {
                                unsafe.putInt(t2, (long) i4, zzm2);
                            }
                            i8 += zzaj.zzr(i10) + zzaj.zzt(zzm2) + zzm2;
                            break;
                        } else {
                            break;
                        }
                    case 41:
                        int zzl2 = zzdc.zzl((List) unsafe.getObject(t2, j2));
                        if (zzl2 > 0) {
                            if (this.zzla) {
                                unsafe.putInt(t2, (long) i4, zzl2);
                            }
                            i8 += zzaj.zzr(i10) + zzaj.zzt(zzl2) + zzl2;
                            break;
                        } else {
                            break;
                        }
                    case 42:
                        int zzn = zzdc.zzn((List) unsafe.getObject(t2, j2));
                        if (zzn > 0) {
                            if (this.zzla) {
                                unsafe.putInt(t2, (long) i4, zzn);
                            }
                            i8 += zzaj.zzr(i10) + zzaj.zzt(zzn) + zzn;
                            break;
                        } else {
                            break;
                        }
                    case 43:
                        int zzj = zzdc.zzj((List) unsafe.getObject(t2, j2));
                        if (zzj > 0) {
                            if (this.zzla) {
                                unsafe.putInt(t2, (long) i4, zzj);
                            }
                            i8 += zzaj.zzr(i10) + zzaj.zzt(zzj) + zzj;
                            break;
                        } else {
                            break;
                        }
                    case 44:
                        int zzh = zzdc.zzh((List) unsafe.getObject(t2, j2));
                        if (zzh > 0) {
                            if (this.zzla) {
                                unsafe.putInt(t2, (long) i4, zzh);
                            }
                            i8 += zzaj.zzr(i10) + zzaj.zzt(zzh) + zzh;
                            break;
                        } else {
                            break;
                        }
                    case 45:
                        int zzl3 = zzdc.zzl((List) unsafe.getObject(t2, j2));
                        if (zzl3 > 0) {
                            if (this.zzla) {
                                unsafe.putInt(t2, (long) i4, zzl3);
                            }
                            i8 += zzaj.zzr(i10) + zzaj.zzt(zzl3) + zzl3;
                            break;
                        } else {
                            break;
                        }
                    case 46:
                        int zzm3 = zzdc.zzm((List) unsafe.getObject(t2, j2));
                        if (zzm3 > 0) {
                            if (this.zzla) {
                                unsafe.putInt(t2, (long) i4, zzm3);
                            }
                            i8 += zzaj.zzr(i10) + zzaj.zzt(zzm3) + zzm3;
                            break;
                        } else {
                            break;
                        }
                    case 47:
                        int zzk = zzdc.zzk((List) unsafe.getObject(t2, j2));
                        if (zzk > 0) {
                            if (this.zzla) {
                                unsafe.putInt(t2, (long) i4, zzk);
                            }
                            i8 += zzaj.zzr(i10) + zzaj.zzt(zzk) + zzk;
                            break;
                        } else {
                            break;
                        }
                    case 48:
                        int zzg = zzdc.zzg((List<Long>) (List) unsafe.getObject(t2, j2));
                        if (zzg > 0) {
                            if (this.zzla) {
                                unsafe.putInt(t2, (long) i4, zzg);
                            }
                            i8 += zzaj.zzr(i10) + zzaj.zzt(zzg) + zzg;
                            break;
                        } else {
                            break;
                        }
                    case 49:
                        i8 += zzdc.zze(i10, zze(t2, j2), zzaf(i7));
                        break;
                    case 50:
                        i8 += this.zzli.zzc(i10, zzdy.zzp(t2, j2), zzag(i7));
                        break;
                    case 51:
                        if (!zzb(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzc(i10, 0.0d);
                            break;
                        }
                    case 52:
                        if (!zzb(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzc(i10, 0.0f);
                            break;
                        }
                    case 53:
                        if (!zzb(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zze(i10, zzi(t2, j2));
                            break;
                        }
                    case 54:
                        if (!zzb(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzf(i10, zzi(t2, j2));
                            break;
                        }
                    case 55:
                        if (!zzb(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzh(i10, zzh(t2, j2));
                            break;
                        }
                    case 56:
                        if (!zzb(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzh(i10, 0);
                            break;
                        }
                    case 57:
                        if (!zzb(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzk(i10, 0);
                            break;
                        }
                    case 58:
                        if (!zzb(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzd(i10, true);
                            break;
                        }
                    case 59:
                        if (!zzb(t2, i10, i7)) {
                            break;
                        } else {
                            Object zzp2 = zzdy.zzp(t2, j2);
                            if (!(zzp2 instanceof zzw)) {
                                i8 += zzaj.zzc(i10, (String) zzp2);
                                break;
                            } else {
                                i8 += zzaj.zzd(i10, (zzw) zzp2);
                                break;
                            }
                        }
                    case 60:
                        if (!zzb(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzdc.zzd(i10, zzdy.zzp(t2, j2), zzaf(i7));
                            break;
                        }
                    case 61:
                        if (!zzb(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzd(i10, (zzw) zzdy.zzp(t2, j2));
                            break;
                        }
                    case 62:
                        if (!zzb(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzi(i10, zzh(t2, j2));
                            break;
                        }
                    case 63:
                        if (!zzb(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzm(i10, zzh(t2, j2));
                            break;
                        }
                    case 64:
                        if (!zzb(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzl(i10, 0);
                            break;
                        }
                    case 65:
                        if (!zzb(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzi(i10, 0);
                            break;
                        }
                    case 66:
                        if (!zzb(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzj(i10, zzh(t2, j2));
                            break;
                        }
                    case 67:
                        if (!zzb(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzg(i10, zzi(t2, j2));
                            break;
                        }
                    case 68:
                        if (!zzb(t2, i10, i7)) {
                            break;
                        } else {
                            i8 += zzaj.zzd(i10, (zzck) zzdy.zzp(t2, j2), zzaf(i7));
                            break;
                        }
                }
                i7 += 3;
                i5 = 267386880;
            }
            return i8 + zzb(this.zzlg, t2);
        }
        Unsafe unsafe2 = zzkr;
        int i11 = -1;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (i12 < this.zzks.length) {
            int zzai2 = zzai(i12);
            int[] iArr = this.zzks;
            int i15 = iArr[i12];
            int i16 = (zzai2 & 267386880) >>> 20;
            if (i16 <= 17) {
                int i17 = iArr[i12 + 2];
                int i18 = i17 & 1048575;
                i = (z3 ? 1 : 0) << (i17 >>> 20);
                if (i18 != i11) {
                    i14 = unsafe2.getInt(t2, (long) i18);
                    i11 = i18;
                }
                i2 = i17;
            } else if (!this.zzla || i16 < zzaw.DOUBLE_LIST_PACKED.mo34787id() || i16 > zzaw.SINT64_LIST_PACKED.mo34787id()) {
                i2 = 0;
                i = 0;
            } else {
                i2 = this.zzks[i12 + 2] & 1048575;
                i = 0;
            }
            long j3 = (long) (zzai2 & 1048575);
            switch (i16) {
                case 0:
                    z2 = true;
                    i3 = 0;
                    z = false;
                    j = 0;
                    if ((i14 & i) == 0) {
                        break;
                    } else {
                        i13 += zzaj.zzc(i15, 0.0d);
                        break;
                    }
                case 1:
                    z2 = true;
                    i3 = 0;
                    j = 0;
                    if ((i14 & i) == 0) {
                        z = false;
                        break;
                    } else {
                        z = false;
                        i13 += zzaj.zzc(i15, 0.0f);
                        break;
                    }
                case 2:
                    z2 = true;
                    i3 = 0;
                    j = 0;
                    if ((i14 & i) == 0) {
                        z = false;
                        break;
                    } else {
                        i13 += zzaj.zze(i15, unsafe2.getLong(t2, j3));
                        z = false;
                        break;
                    }
                case 3:
                    z2 = true;
                    i3 = 0;
                    j = 0;
                    if ((i14 & i) == 0) {
                        z = false;
                        break;
                    } else {
                        i13 += zzaj.zzf(i15, unsafe2.getLong(t2, j3));
                        z = false;
                        break;
                    }
                case 4:
                    z2 = true;
                    i3 = 0;
                    j = 0;
                    if ((i14 & i) == 0) {
                        z = false;
                        break;
                    } else {
                        i13 += zzaj.zzh(i15, unsafe2.getInt(t2, j3));
                        z = false;
                        break;
                    }
                case 5:
                    z2 = true;
                    i3 = 0;
                    if ((i14 & i) == 0) {
                        j = 0;
                        z = false;
                        break;
                    } else {
                        j = 0;
                        i13 += zzaj.zzh(i15, 0);
                        z = false;
                        break;
                    }
                case 6:
                    z2 = true;
                    if ((i14 & i) == 0) {
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i3 = 0;
                        i13 += zzaj.zzk(i15, 0);
                        z = false;
                        j = 0;
                        break;
                    }
                case 7:
                    if ((i14 & i) == 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        z2 = true;
                        i13 += zzaj.zzd(i15, true);
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 8:
                    if ((i14 & i) == 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        Object object = unsafe2.getObject(t2, j3);
                        if (!(object instanceof zzw)) {
                            i13 += zzaj.zzc(i15, (String) object);
                            z2 = true;
                            i3 = 0;
                            z = false;
                            j = 0;
                            break;
                        } else {
                            i13 += zzaj.zzd(i15, (zzw) object);
                            z2 = true;
                            i3 = 0;
                            z = false;
                            j = 0;
                            break;
                        }
                    }
                case 9:
                    if ((i14 & i) == 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzdc.zzd(i15, unsafe2.getObject(t2, j3), zzaf(i12));
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 10:
                    if ((i14 & i) == 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzd(i15, (zzw) unsafe2.getObject(t2, j3));
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 11:
                    if ((i14 & i) == 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzi(i15, unsafe2.getInt(t2, j3));
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 12:
                    if ((i14 & i) == 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzm(i15, unsafe2.getInt(t2, j3));
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 13:
                    if ((i14 & i) == 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzl(i15, 0);
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 14:
                    if ((i14 & i) == 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzi(i15, 0);
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 15:
                    if ((i14 & i) == 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzj(i15, unsafe2.getInt(t2, j3));
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 16:
                    if ((i14 & i) == 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzg(i15, unsafe2.getLong(t2, j3));
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 17:
                    if ((i14 & i) == 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzd(i15, (zzck) unsafe2.getObject(t2, j3), zzaf(i12));
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 18:
                    i13 += zzdc.zzx(i15, (List) unsafe2.getObject(t2, j3), false);
                    z2 = true;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 19:
                    i3 = 0;
                    i13 += zzdc.zzw(i15, (List) unsafe2.getObject(t2, j3), false);
                    z2 = true;
                    z = false;
                    j = 0;
                    break;
                case 20:
                    i3 = 0;
                    i13 += zzdc.zzp(i15, (List) unsafe2.getObject(t2, j3), false);
                    z2 = true;
                    z = false;
                    j = 0;
                    break;
                case 21:
                    i3 = 0;
                    i13 += zzdc.zzq(i15, (List) unsafe2.getObject(t2, j3), false);
                    z2 = true;
                    z = false;
                    j = 0;
                    break;
                case 22:
                    i3 = 0;
                    i13 += zzdc.zzt(i15, (List) unsafe2.getObject(t2, j3), false);
                    z2 = true;
                    z = false;
                    j = 0;
                    break;
                case 23:
                    i3 = 0;
                    i13 += zzdc.zzx(i15, (List) unsafe2.getObject(t2, j3), false);
                    z2 = true;
                    z = false;
                    j = 0;
                    break;
                case 24:
                    i3 = 0;
                    i13 += zzdc.zzw(i15, (List) unsafe2.getObject(t2, j3), false);
                    z2 = true;
                    z = false;
                    j = 0;
                    break;
                case 25:
                    i3 = 0;
                    i13 += zzdc.zzy(i15, (List) unsafe2.getObject(t2, j3), false);
                    z2 = true;
                    z = false;
                    j = 0;
                    break;
                case 26:
                    i13 += zzdc.zzd(i15, (List) unsafe2.getObject(t2, j3));
                    z2 = true;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 27:
                    i13 += zzdc.zzd(i15, (List<?>) (List) unsafe2.getObject(t2, j3), zzaf(i12));
                    z2 = true;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 28:
                    i13 += zzdc.zze(i15, (List<zzw>) (List) unsafe2.getObject(t2, j3));
                    z2 = true;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 29:
                    i13 += zzdc.zzu(i15, (List) unsafe2.getObject(t2, j3), false);
                    z2 = true;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 30:
                    i3 = 0;
                    i13 += zzdc.zzs(i15, (List) unsafe2.getObject(t2, j3), false);
                    z2 = true;
                    z = false;
                    j = 0;
                    break;
                case 31:
                    i3 = 0;
                    i13 += zzdc.zzw(i15, (List) unsafe2.getObject(t2, j3), false);
                    z2 = true;
                    z = false;
                    j = 0;
                    break;
                case 32:
                    i3 = 0;
                    i13 += zzdc.zzx(i15, (List) unsafe2.getObject(t2, j3), false);
                    z2 = true;
                    z = false;
                    j = 0;
                    break;
                case 33:
                    i3 = 0;
                    i13 += zzdc.zzv(i15, (List) unsafe2.getObject(t2, j3), false);
                    z2 = true;
                    z = false;
                    j = 0;
                    break;
                case 34:
                    i3 = 0;
                    i13 += zzdc.zzr(i15, (List) unsafe2.getObject(t2, j3), false);
                    z2 = true;
                    z = false;
                    j = 0;
                    break;
                case 35:
                    int zzm4 = zzdc.zzm((List) unsafe2.getObject(t2, j3));
                    if (zzm4 <= 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        if (this.zzla) {
                            unsafe2.putInt(t2, (long) i2, zzm4);
                        }
                        i13 += zzaj.zzr(i15) + zzaj.zzt(zzm4) + zzm4;
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 36:
                    int zzl4 = zzdc.zzl((List) unsafe2.getObject(t2, j3));
                    if (zzl4 <= 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        if (this.zzla) {
                            unsafe2.putInt(t2, (long) i2, zzl4);
                        }
                        i13 += zzaj.zzr(i15) + zzaj.zzt(zzl4) + zzl4;
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 37:
                    int zze2 = zzdc.zze((List) unsafe2.getObject(t2, j3));
                    if (zze2 <= 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        if (this.zzla) {
                            unsafe2.putInt(t2, (long) i2, zze2);
                        }
                        i13 += zzaj.zzr(i15) + zzaj.zzt(zze2) + zze2;
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 38:
                    int zzf2 = zzdc.zzf((List<Long>) (List) unsafe2.getObject(t2, j3));
                    if (zzf2 <= 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        if (this.zzla) {
                            unsafe2.putInt(t2, (long) i2, zzf2);
                        }
                        i13 += zzaj.zzr(i15) + zzaj.zzt(zzf2) + zzf2;
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 39:
                    int zzi2 = zzdc.zzi((List) unsafe2.getObject(t2, j3));
                    if (zzi2 <= 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        if (this.zzla) {
                            unsafe2.putInt(t2, (long) i2, zzi2);
                        }
                        i13 += zzaj.zzr(i15) + zzaj.zzt(zzi2) + zzi2;
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 40:
                    int zzm5 = zzdc.zzm((List) unsafe2.getObject(t2, j3));
                    if (zzm5 <= 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        if (this.zzla) {
                            unsafe2.putInt(t2, (long) i2, zzm5);
                        }
                        i13 += zzaj.zzr(i15) + zzaj.zzt(zzm5) + zzm5;
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 41:
                    int zzl5 = zzdc.zzl((List) unsafe2.getObject(t2, j3));
                    if (zzl5 <= 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        if (this.zzla) {
                            unsafe2.putInt(t2, (long) i2, zzl5);
                        }
                        i13 += zzaj.zzr(i15) + zzaj.zzt(zzl5) + zzl5;
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 42:
                    int zzn2 = zzdc.zzn((List) unsafe2.getObject(t2, j3));
                    if (zzn2 <= 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        if (this.zzla) {
                            unsafe2.putInt(t2, (long) i2, zzn2);
                        }
                        i13 += zzaj.zzr(i15) + zzaj.zzt(zzn2) + zzn2;
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 43:
                    int zzj2 = zzdc.zzj((List) unsafe2.getObject(t2, j3));
                    if (zzj2 <= 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        if (this.zzla) {
                            unsafe2.putInt(t2, (long) i2, zzj2);
                        }
                        i13 += zzaj.zzr(i15) + zzaj.zzt(zzj2) + zzj2;
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 44:
                    int zzh2 = zzdc.zzh((List) unsafe2.getObject(t2, j3));
                    if (zzh2 <= 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        if (this.zzla) {
                            unsafe2.putInt(t2, (long) i2, zzh2);
                        }
                        i13 += zzaj.zzr(i15) + zzaj.zzt(zzh2) + zzh2;
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 45:
                    int zzl6 = zzdc.zzl((List) unsafe2.getObject(t2, j3));
                    if (zzl6 <= 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        if (this.zzla) {
                            unsafe2.putInt(t2, (long) i2, zzl6);
                        }
                        i13 += zzaj.zzr(i15) + zzaj.zzt(zzl6) + zzl6;
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 46:
                    int zzm6 = zzdc.zzm((List) unsafe2.getObject(t2, j3));
                    if (zzm6 <= 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        if (this.zzla) {
                            unsafe2.putInt(t2, (long) i2, zzm6);
                        }
                        i13 += zzaj.zzr(i15) + zzaj.zzt(zzm6) + zzm6;
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 47:
                    int zzk2 = zzdc.zzk((List) unsafe2.getObject(t2, j3));
                    if (zzk2 <= 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        if (this.zzla) {
                            unsafe2.putInt(t2, (long) i2, zzk2);
                        }
                        i13 += zzaj.zzr(i15) + zzaj.zzt(zzk2) + zzk2;
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 48:
                    int zzg2 = zzdc.zzg((List<Long>) (List) unsafe2.getObject(t2, j3));
                    if (zzg2 <= 0) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        if (this.zzla) {
                            unsafe2.putInt(t2, (long) i2, zzg2);
                        }
                        i13 += zzaj.zzr(i15) + zzaj.zzt(zzg2) + zzg2;
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 49:
                    i13 += zzdc.zze(i15, (List) unsafe2.getObject(t2, j3), zzaf(i12));
                    z2 = true;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 50:
                    i13 += this.zzli.zzc(i15, unsafe2.getObject(t2, j3), zzag(i12));
                    z2 = true;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 51:
                    if (!zzb(t2, i15, i12)) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzc(i15, 0.0d);
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 52:
                    if (!zzb(t2, i15, i12)) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzc(i15, 0.0f);
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 53:
                    if (!zzb(t2, i15, i12)) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zze(i15, zzi(t2, j3));
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 54:
                    if (!zzb(t2, i15, i12)) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzf(i15, zzi(t2, j3));
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 55:
                    if (!zzb(t2, i15, i12)) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzh(i15, zzh(t2, j3));
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 56:
                    if (!zzb(t2, i15, i12)) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzh(i15, 0);
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 57:
                    if (!zzb(t2, i15, i12)) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzk(i15, 0);
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 58:
                    if (!zzb(t2, i15, i12)) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzd(i15, true);
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 59:
                    if (!zzb(t2, i15, i12)) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        Object object2 = unsafe2.getObject(t2, j3);
                        if (!(object2 instanceof zzw)) {
                            i13 += zzaj.zzc(i15, (String) object2);
                            z2 = true;
                            i3 = 0;
                            z = false;
                            j = 0;
                            break;
                        } else {
                            i13 += zzaj.zzd(i15, (zzw) object2);
                            z2 = true;
                            i3 = 0;
                            z = false;
                            j = 0;
                            break;
                        }
                    }
                case 60:
                    if (!zzb(t2, i15, i12)) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzdc.zzd(i15, unsafe2.getObject(t2, j3), zzaf(i12));
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 61:
                    if (!zzb(t2, i15, i12)) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzd(i15, (zzw) unsafe2.getObject(t2, j3));
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 62:
                    if (!zzb(t2, i15, i12)) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzi(i15, zzh(t2, j3));
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 63:
                    if (!zzb(t2, i15, i12)) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzm(i15, zzh(t2, j3));
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 64:
                    if (!zzb(t2, i15, i12)) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzl(i15, 0);
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 65:
                    if (!zzb(t2, i15, i12)) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzi(i15, 0);
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 66:
                    if (!zzb(t2, i15, i12)) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzj(i15, zzh(t2, j3));
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 67:
                    if (!zzb(t2, i15, i12)) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzg(i15, zzi(t2, j3));
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                case 68:
                    if (!zzb(t2, i15, i12)) {
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    } else {
                        i13 += zzaj.zzd(i15, (zzck) unsafe2.getObject(t2, j3), zzaf(i12));
                        z2 = true;
                        i3 = 0;
                        z = false;
                        j = 0;
                        break;
                    }
                default:
                    z2 = true;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
            }
            i12 += 3;
            i6 = i3;
            z3 = z2;
            boolean z4 = z;
            long j4 = j;
        }
        int i19 = i6;
        int zzb = i13 + zzb(this.zzlg, t2);
        if (!this.zzkx) {
            return zzb;
        }
        zzav<?> zzb2 = this.zzlh.zzb((Object) t2);
        for (int i20 = i19; i20 < zzb2.zzfj.zzcu(); i20++) {
            Map.Entry<FieldDescriptorType, Object> zzam = zzb2.zzfj.zzam(i20);
            i19 += zzav.zzc((zzax<?>) (zzax) zzam.getKey(), zzam.getValue());
        }
        for (Map.Entry next : zzb2.zzfj.zzcv()) {
            i19 += zzav.zzc((zzax<?>) (zzax) next.getKey(), next.getValue());
        }
        return zzb + i19;
    }

    private static <UT, UB> int zzb(zzds<UT, UB> zzds, T t) {
        return zzds.zzn(zzds.zzr(t));
    }

    private static List<?> zze(Object obj, long j) {
        return (List) zzdy.zzp(obj, j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x05ad  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x05f0  */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x0b5e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(T r14, com.google.android.gms.internal.places.zzel r15) throws java.io.IOException {
        /*
            r13 = this;
            int r0 = r15.zzam()
            int r1 = com.google.android.gms.internal.places.zzbc.zze.zzix
            r2 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = 0
            r4 = 1
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 != r1) goto L_0x05c3
            com.google.android.gms.internal.places.zzds<?, ?> r0 = r13.zzlg
            zzb(r0, r14, (com.google.android.gms.internal.places.zzel) r15)
            boolean r0 = r13.zzkx
            if (r0 == 0) goto L_0x0036
            com.google.android.gms.internal.places.zzar<?> r0 = r13.zzlh
            com.google.android.gms.internal.places.zzav r0 = r0.zzb((java.lang.Object) r14)
            com.google.android.gms.internal.places.zzdb<FieldDescriptorType, java.lang.Object> r1 = r0.zzfj
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0036
            java.util.Iterator r0 = r0.descendingIterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0038
        L_0x0036:
            r0 = r3
            r1 = r0
        L_0x0038:
            int[] r7 = r13.zzks
            int r7 = r7.length
            int r7 = r7 + -3
        L_0x003d:
            if (r7 < 0) goto L_0x05ab
            int r8 = r13.zzai(r7)
            int[] r9 = r13.zzks
            r9 = r9[r7]
        L_0x0049:
            if (r1 == 0) goto L_0x0067
            com.google.android.gms.internal.places.zzar<?> r10 = r13.zzlh
            int r10 = r10.zzb((java.util.Map.Entry<?, ?>) r1)
            if (r10 <= r9) goto L_0x0067
            com.google.android.gms.internal.places.zzar<?> r10 = r13.zzlh
            r10.zzb(r15, r1)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0065
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0049
        L_0x0065:
            r1 = r3
            goto L_0x0049
        L_0x0067:
            r10 = r8 & r2
            int r10 = r10 >>> 20
            switch(r10) {
                case 0: goto L_0x0596;
                case 1: goto L_0x0584;
                case 2: goto L_0x0572;
                case 3: goto L_0x0560;
                case 4: goto L_0x054e;
                case 5: goto L_0x053c;
                case 6: goto L_0x052a;
                case 7: goto L_0x0517;
                case 8: goto L_0x0505;
                case 9: goto L_0x04ef;
                case 10: goto L_0x04db;
                case 11: goto L_0x04c8;
                case 12: goto L_0x04b5;
                case 13: goto L_0x04a2;
                case 14: goto L_0x048f;
                case 15: goto L_0x047c;
                case 16: goto L_0x0469;
                case 17: goto L_0x0453;
                case 18: goto L_0x043f;
                case 19: goto L_0x042b;
                case 20: goto L_0x0417;
                case 21: goto L_0x0403;
                case 22: goto L_0x03ef;
                case 23: goto L_0x03db;
                case 24: goto L_0x03c7;
                case 25: goto L_0x03b3;
                case 26: goto L_0x039f;
                case 27: goto L_0x0387;
                case 28: goto L_0x0373;
                case 29: goto L_0x035f;
                case 30: goto L_0x034b;
                case 31: goto L_0x0337;
                case 32: goto L_0x0323;
                case 33: goto L_0x030f;
                case 34: goto L_0x02fb;
                case 35: goto L_0x02e7;
                case 36: goto L_0x02d3;
                case 37: goto L_0x02bf;
                case 38: goto L_0x02ab;
                case 39: goto L_0x0297;
                case 40: goto L_0x0283;
                case 41: goto L_0x026f;
                case 42: goto L_0x025b;
                case 43: goto L_0x0247;
                case 44: goto L_0x0233;
                case 45: goto L_0x021f;
                case 46: goto L_0x020b;
                case 47: goto L_0x01f7;
                case 48: goto L_0x01e3;
                case 49: goto L_0x01cb;
                case 50: goto L_0x01bf;
                case 51: goto L_0x01ad;
                case 52: goto L_0x019b;
                case 53: goto L_0x0189;
                case 54: goto L_0x0177;
                case 55: goto L_0x0165;
                case 56: goto L_0x0153;
                case 57: goto L_0x0141;
                case 58: goto L_0x012f;
                case 59: goto L_0x011d;
                case 60: goto L_0x0107;
                case 61: goto L_0x00f3;
                case 62: goto L_0x00e1;
                case 63: goto L_0x00cf;
                case 64: goto L_0x00bd;
                case 65: goto L_0x00ab;
                case 66: goto L_0x0099;
                case 67: goto L_0x0087;
                case 68: goto L_0x0071;
                default: goto L_0x006f;
            }
        L_0x006f:
            goto L_0x05a7
        L_0x0071:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            com.google.android.gms.internal.places.zzda r10 = r13.zzaf(r7)
            r15.zzc((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.places.zzda) r10)
            goto L_0x05a7
        L_0x0087:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzc((int) r9, (long) r10)
            goto L_0x05a7
        L_0x0099:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzf(r9, r8)
            goto L_0x05a7
        L_0x00ab:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzk(r9, r10)
            goto L_0x05a7
        L_0x00bd:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzn(r9, r8)
            goto L_0x05a7
        L_0x00cf:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzo(r9, r8)
            goto L_0x05a7
        L_0x00e1:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zze(r9, r8)
            goto L_0x05a7
        L_0x00f3:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            com.google.android.gms.internal.places.zzw r8 = (com.google.android.gms.internal.places.zzw) r8
            r15.zzb((int) r9, (com.google.android.gms.internal.places.zzw) r8)
            goto L_0x05a7
        L_0x0107:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            com.google.android.gms.internal.places.zzda r10 = r13.zzaf(r7)
            r15.zzb((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.places.zzda) r10)
            goto L_0x05a7
        L_0x011d:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            zzb((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.places.zzel) r15)
            goto L_0x05a7
        L_0x012f:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = zzj(r14, r10)
            r15.zzc((int) r9, (boolean) r8)
            goto L_0x05a7
        L_0x0141:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzg(r9, r8)
            goto L_0x05a7
        L_0x0153:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzd((int) r9, (long) r10)
            goto L_0x05a7
        L_0x0165:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzd((int) r9, (int) r8)
            goto L_0x05a7
        L_0x0177:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzb((int) r9, (long) r10)
            goto L_0x05a7
        L_0x0189:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzj(r9, r10)
            goto L_0x05a7
        L_0x019b:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = zzg(r14, r10)
            r15.zzb((int) r9, (float) r8)
            goto L_0x05a7
        L_0x01ad:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = zzf(r14, r10)
            r15.zzb((int) r9, (double) r10)
            goto L_0x05a7
        L_0x01bf:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            r13.zzb((com.google.android.gms.internal.places.zzel) r15, (int) r9, (java.lang.Object) r8, (int) r7)
            goto L_0x05a7
        L_0x01cb:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzda r10 = r13.zzaf(r7)
            com.google.android.gms.internal.places.zzdc.zzc((int) r9, (java.util.List<?>) r8, (com.google.android.gms.internal.places.zzel) r15, (com.google.android.gms.internal.places.zzda) r10)
            goto L_0x05a7
        L_0x01e3:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzf(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x01f7:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzk(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x020b:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzh(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x021f:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzm(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x0233:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzn(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x0247:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzj(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x025b:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzo(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x026f:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzl(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x0283:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzg(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x0297:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzi(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x02ab:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zze(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x02bf:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzd(r9, r8, r15, r4)
            goto L_0x05a7
        L_0x02d3:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzc((int) r9, (java.util.List<java.lang.Float>) r8, (com.google.android.gms.internal.places.zzel) r15, (boolean) r4)
            goto L_0x05a7
        L_0x02e7:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzb((int) r9, (java.util.List<java.lang.Double>) r8, (com.google.android.gms.internal.places.zzel) r15, (boolean) r4)
            goto L_0x05a7
        L_0x02fb:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzf(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x030f:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzk(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x0323:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzh(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x0337:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzm(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x034b:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzn(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x035f:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzj(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x0373:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzc(r9, r8, r15)
            goto L_0x05a7
        L_0x0387:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzda r10 = r13.zzaf(r7)
            com.google.android.gms.internal.places.zzdc.zzb((int) r9, (java.util.List<?>) r8, (com.google.android.gms.internal.places.zzel) r15, (com.google.android.gms.internal.places.zzda) r10)
            goto L_0x05a7
        L_0x039f:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzb((int) r9, (java.util.List<java.lang.String>) r8, (com.google.android.gms.internal.places.zzel) r15)
            goto L_0x05a7
        L_0x03b3:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzo(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x03c7:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzl(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x03db:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzg(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x03ef:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzi(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x0403:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zze(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x0417:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzd(r9, r8, r15, r5)
            goto L_0x05a7
        L_0x042b:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzc((int) r9, (java.util.List<java.lang.Float>) r8, (com.google.android.gms.internal.places.zzel) r15, (boolean) r5)
            goto L_0x05a7
        L_0x043f:
            int[] r9 = r13.zzks
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.places.zzdc.zzb((int) r9, (java.util.List<java.lang.Double>) r8, (com.google.android.gms.internal.places.zzel) r15, (boolean) r5)
            goto L_0x05a7
        L_0x0453:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            com.google.android.gms.internal.places.zzda r10 = r13.zzaf(r7)
            r15.zzc((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.places.zzda) r10)
            goto L_0x05a7
        L_0x0469:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.places.zzdy.zzl(r14, r10)
            r15.zzc((int) r9, (long) r10)
            goto L_0x05a7
        L_0x047c:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.places.zzdy.zzk(r14, r10)
            r15.zzf(r9, r8)
            goto L_0x05a7
        L_0x048f:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.places.zzdy.zzl(r14, r10)
            r15.zzk(r9, r10)
            goto L_0x05a7
        L_0x04a2:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.places.zzdy.zzk(r14, r10)
            r15.zzn(r9, r8)
            goto L_0x05a7
        L_0x04b5:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.places.zzdy.zzk(r14, r10)
            r15.zzo(r9, r8)
            goto L_0x05a7
        L_0x04c8:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.places.zzdy.zzk(r14, r10)
            r15.zze(r9, r8)
            goto L_0x05a7
        L_0x04db:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            com.google.android.gms.internal.places.zzw r8 = (com.google.android.gms.internal.places.zzw) r8
            r15.zzb((int) r9, (com.google.android.gms.internal.places.zzw) r8)
            goto L_0x05a7
        L_0x04ef:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            com.google.android.gms.internal.places.zzda r10 = r13.zzaf(r7)
            r15.zzb((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.places.zzda) r10)
            goto L_0x05a7
        L_0x0505:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.places.zzdy.zzp(r14, r10)
            zzb((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.places.zzel) r15)
            goto L_0x05a7
        L_0x0517:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = com.google.android.gms.internal.places.zzdy.zzm(r14, r10)
            r15.zzc((int) r9, (boolean) r8)
            goto L_0x05a7
        L_0x052a:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.places.zzdy.zzk(r14, r10)
            r15.zzg(r9, r8)
            goto L_0x05a7
        L_0x053c:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.places.zzdy.zzl(r14, r10)
            r15.zzd((int) r9, (long) r10)
            goto L_0x05a7
        L_0x054e:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.places.zzdy.zzk(r14, r10)
            r15.zzd((int) r9, (int) r8)
            goto L_0x05a7
        L_0x0560:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.places.zzdy.zzl(r14, r10)
            r15.zzb((int) r9, (long) r10)
            goto L_0x05a7
        L_0x0572:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.places.zzdy.zzl(r14, r10)
            r15.zzj(r9, r10)
            goto L_0x05a7
        L_0x0584:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = com.google.android.gms.internal.places.zzdy.zzn(r14, r10)
            r15.zzb((int) r9, (float) r8)
            goto L_0x05a7
        L_0x0596:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x05a7
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = com.google.android.gms.internal.places.zzdy.zzo(r14, r10)
            r15.zzb((int) r9, (double) r10)
        L_0x05a7:
            int r7 = r7 + -3
            goto L_0x003d
        L_0x05ab:
            if (r1 == 0) goto L_0x05c2
            com.google.android.gms.internal.places.zzar<?> r14 = r13.zzlh
            r14.zzb(r15, r1)
            boolean r14 = r0.hasNext()
            if (r14 == 0) goto L_0x05c0
            java.lang.Object r14 = r0.next()
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14
            r1 = r14
            goto L_0x05ab
        L_0x05c0:
            r1 = r3
            goto L_0x05ab
        L_0x05c2:
            return
        L_0x05c3:
            boolean r0 = r13.zzkz
            if (r0 == 0) goto L_0x0b78
            boolean r0 = r13.zzkx
            if (r0 == 0) goto L_0x05e8
            com.google.android.gms.internal.places.zzar<?> r0 = r13.zzlh
            com.google.android.gms.internal.places.zzav r0 = r0.zzb((java.lang.Object) r14)
            com.google.android.gms.internal.places.zzdb<FieldDescriptorType, java.lang.Object> r1 = r0.zzfj
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x05e8
            java.util.Iterator r0 = r0.iterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x05ea
        L_0x05e8:
            r0 = r3
            r1 = r0
        L_0x05ea:
            int[] r7 = r13.zzks
            int r7 = r7.length
            r8 = r5
        L_0x05ee:
            if (r8 >= r7) goto L_0x0b5c
            int r9 = r13.zzai(r8)
            int[] r10 = r13.zzks
            r10 = r10[r8]
        L_0x05fa:
            if (r1 == 0) goto L_0x0618
            com.google.android.gms.internal.places.zzar<?> r11 = r13.zzlh
            int r11 = r11.zzb((java.util.Map.Entry<?, ?>) r1)
            if (r11 > r10) goto L_0x0618
            com.google.android.gms.internal.places.zzar<?> r11 = r13.zzlh
            r11.zzb(r15, r1)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0616
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x05fa
        L_0x0616:
            r1 = r3
            goto L_0x05fa
        L_0x0618:
            r11 = r9 & r2
            int r11 = r11 >>> 20
            switch(r11) {
                case 0: goto L_0x0b47;
                case 1: goto L_0x0b35;
                case 2: goto L_0x0b23;
                case 3: goto L_0x0b11;
                case 4: goto L_0x0aff;
                case 5: goto L_0x0aed;
                case 6: goto L_0x0adb;
                case 7: goto L_0x0ac8;
                case 8: goto L_0x0ab6;
                case 9: goto L_0x0aa0;
                case 10: goto L_0x0a8c;
                case 11: goto L_0x0a79;
                case 12: goto L_0x0a66;
                case 13: goto L_0x0a53;
                case 14: goto L_0x0a40;
                case 15: goto L_0x0a2d;
                case 16: goto L_0x0a1a;
                case 17: goto L_0x0a04;
                case 18: goto L_0x09f0;
                case 19: goto L_0x09dc;
                case 20: goto L_0x09c8;
                case 21: goto L_0x09b4;
                case 22: goto L_0x09a0;
                case 23: goto L_0x098c;
                case 24: goto L_0x0978;
                case 25: goto L_0x0964;
                case 26: goto L_0x0950;
                case 27: goto L_0x0938;
                case 28: goto L_0x0924;
                case 29: goto L_0x0910;
                case 30: goto L_0x08fc;
                case 31: goto L_0x08e8;
                case 32: goto L_0x08d4;
                case 33: goto L_0x08c0;
                case 34: goto L_0x08ac;
                case 35: goto L_0x0898;
                case 36: goto L_0x0884;
                case 37: goto L_0x0870;
                case 38: goto L_0x085c;
                case 39: goto L_0x0848;
                case 40: goto L_0x0834;
                case 41: goto L_0x0820;
                case 42: goto L_0x080c;
                case 43: goto L_0x07f8;
                case 44: goto L_0x07e4;
                case 45: goto L_0x07d0;
                case 46: goto L_0x07bc;
                case 47: goto L_0x07a8;
                case 48: goto L_0x0794;
                case 49: goto L_0x077c;
                case 50: goto L_0x0770;
                case 51: goto L_0x075e;
                case 52: goto L_0x074c;
                case 53: goto L_0x073a;
                case 54: goto L_0x0728;
                case 55: goto L_0x0716;
                case 56: goto L_0x0704;
                case 57: goto L_0x06f2;
                case 58: goto L_0x06e0;
                case 59: goto L_0x06ce;
                case 60: goto L_0x06b8;
                case 61: goto L_0x06a4;
                case 62: goto L_0x0692;
                case 63: goto L_0x0680;
                case 64: goto L_0x066e;
                case 65: goto L_0x065c;
                case 66: goto L_0x064a;
                case 67: goto L_0x0638;
                case 68: goto L_0x0622;
                default: goto L_0x0620;
            }
        L_0x0620:
            goto L_0x0b58
        L_0x0622:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            com.google.android.gms.internal.places.zzda r11 = r13.zzaf(r8)
            r15.zzc((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.places.zzda) r11)
            goto L_0x0b58
        L_0x0638:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzc((int) r10, (long) r11)
            goto L_0x0b58
        L_0x064a:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzf(r10, r9)
            goto L_0x0b58
        L_0x065c:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzk(r10, r11)
            goto L_0x0b58
        L_0x066e:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzn(r10, r9)
            goto L_0x0b58
        L_0x0680:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzo(r10, r9)
            goto L_0x0b58
        L_0x0692:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zze(r10, r9)
            goto L_0x0b58
        L_0x06a4:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            com.google.android.gms.internal.places.zzw r9 = (com.google.android.gms.internal.places.zzw) r9
            r15.zzb((int) r10, (com.google.android.gms.internal.places.zzw) r9)
            goto L_0x0b58
        L_0x06b8:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            com.google.android.gms.internal.places.zzda r11 = r13.zzaf(r8)
            r15.zzb((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.places.zzda) r11)
            goto L_0x0b58
        L_0x06ce:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            zzb((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.places.zzel) r15)
            goto L_0x0b58
        L_0x06e0:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = zzj(r14, r11)
            r15.zzc((int) r10, (boolean) r9)
            goto L_0x0b58
        L_0x06f2:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzg(r10, r9)
            goto L_0x0b58
        L_0x0704:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzd((int) r10, (long) r11)
            goto L_0x0b58
        L_0x0716:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzd((int) r10, (int) r9)
            goto L_0x0b58
        L_0x0728:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzb((int) r10, (long) r11)
            goto L_0x0b58
        L_0x073a:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzj(r10, r11)
            goto L_0x0b58
        L_0x074c:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = zzg(r14, r11)
            r15.zzb((int) r10, (float) r9)
            goto L_0x0b58
        L_0x075e:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = zzf(r14, r11)
            r15.zzb((int) r10, (double) r11)
            goto L_0x0b58
        L_0x0770:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            r13.zzb((com.google.android.gms.internal.places.zzel) r15, (int) r10, (java.lang.Object) r9, (int) r8)
            goto L_0x0b58
        L_0x077c:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzda r11 = r13.zzaf(r8)
            com.google.android.gms.internal.places.zzdc.zzc((int) r10, (java.util.List<?>) r9, (com.google.android.gms.internal.places.zzel) r15, (com.google.android.gms.internal.places.zzda) r11)
            goto L_0x0b58
        L_0x0794:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzf(r10, r9, r15, r4)
            goto L_0x0b58
        L_0x07a8:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzk(r10, r9, r15, r4)
            goto L_0x0b58
        L_0x07bc:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzh(r10, r9, r15, r4)
            goto L_0x0b58
        L_0x07d0:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzm(r10, r9, r15, r4)
            goto L_0x0b58
        L_0x07e4:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzn(r10, r9, r15, r4)
            goto L_0x0b58
        L_0x07f8:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzj(r10, r9, r15, r4)
            goto L_0x0b58
        L_0x080c:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzo(r10, r9, r15, r4)
            goto L_0x0b58
        L_0x0820:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzl(r10, r9, r15, r4)
            goto L_0x0b58
        L_0x0834:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzg(r10, r9, r15, r4)
            goto L_0x0b58
        L_0x0848:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzi(r10, r9, r15, r4)
            goto L_0x0b58
        L_0x085c:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zze(r10, r9, r15, r4)
            goto L_0x0b58
        L_0x0870:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzd(r10, r9, r15, r4)
            goto L_0x0b58
        L_0x0884:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzc((int) r10, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.places.zzel) r15, (boolean) r4)
            goto L_0x0b58
        L_0x0898:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzb((int) r10, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.places.zzel) r15, (boolean) r4)
            goto L_0x0b58
        L_0x08ac:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzf(r10, r9, r15, r5)
            goto L_0x0b58
        L_0x08c0:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzk(r10, r9, r15, r5)
            goto L_0x0b58
        L_0x08d4:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzh(r10, r9, r15, r5)
            goto L_0x0b58
        L_0x08e8:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzm(r10, r9, r15, r5)
            goto L_0x0b58
        L_0x08fc:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzn(r10, r9, r15, r5)
            goto L_0x0b58
        L_0x0910:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzj(r10, r9, r15, r5)
            goto L_0x0b58
        L_0x0924:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzc(r10, r9, r15)
            goto L_0x0b58
        L_0x0938:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzda r11 = r13.zzaf(r8)
            com.google.android.gms.internal.places.zzdc.zzb((int) r10, (java.util.List<?>) r9, (com.google.android.gms.internal.places.zzel) r15, (com.google.android.gms.internal.places.zzda) r11)
            goto L_0x0b58
        L_0x0950:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzb((int) r10, (java.util.List<java.lang.String>) r9, (com.google.android.gms.internal.places.zzel) r15)
            goto L_0x0b58
        L_0x0964:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzo(r10, r9, r15, r5)
            goto L_0x0b58
        L_0x0978:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzl(r10, r9, r15, r5)
            goto L_0x0b58
        L_0x098c:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzg(r10, r9, r15, r5)
            goto L_0x0b58
        L_0x09a0:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzi(r10, r9, r15, r5)
            goto L_0x0b58
        L_0x09b4:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zze(r10, r9, r15, r5)
            goto L_0x0b58
        L_0x09c8:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzd(r10, r9, r15, r5)
            goto L_0x0b58
        L_0x09dc:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzc((int) r10, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.places.zzel) r15, (boolean) r5)
            goto L_0x0b58
        L_0x09f0:
            int[] r10 = r13.zzks
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzb((int) r10, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.places.zzel) r15, (boolean) r5)
            goto L_0x0b58
        L_0x0a04:
            boolean r11 = r13.zzb(r14, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            com.google.android.gms.internal.places.zzda r11 = r13.zzaf(r8)
            r15.zzc((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.places.zzda) r11)
            goto L_0x0b58
        L_0x0a1a:
            boolean r11 = r13.zzb(r14, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.places.zzdy.zzl(r14, r11)
            r15.zzc((int) r10, (long) r11)
            goto L_0x0b58
        L_0x0a2d:
            boolean r11 = r13.zzb(r14, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.places.zzdy.zzk(r14, r11)
            r15.zzf(r10, r9)
            goto L_0x0b58
        L_0x0a40:
            boolean r11 = r13.zzb(r14, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.places.zzdy.zzl(r14, r11)
            r15.zzk(r10, r11)
            goto L_0x0b58
        L_0x0a53:
            boolean r11 = r13.zzb(r14, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.places.zzdy.zzk(r14, r11)
            r15.zzn(r10, r9)
            goto L_0x0b58
        L_0x0a66:
            boolean r11 = r13.zzb(r14, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.places.zzdy.zzk(r14, r11)
            r15.zzo(r10, r9)
            goto L_0x0b58
        L_0x0a79:
            boolean r11 = r13.zzb(r14, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.places.zzdy.zzk(r14, r11)
            r15.zze(r10, r9)
            goto L_0x0b58
        L_0x0a8c:
            boolean r11 = r13.zzb(r14, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            com.google.android.gms.internal.places.zzw r9 = (com.google.android.gms.internal.places.zzw) r9
            r15.zzb((int) r10, (com.google.android.gms.internal.places.zzw) r9)
            goto L_0x0b58
        L_0x0aa0:
            boolean r11 = r13.zzb(r14, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            com.google.android.gms.internal.places.zzda r11 = r13.zzaf(r8)
            r15.zzb((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.places.zzda) r11)
            goto L_0x0b58
        L_0x0ab6:
            boolean r11 = r13.zzb(r14, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.places.zzdy.zzp(r14, r11)
            zzb((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.places.zzel) r15)
            goto L_0x0b58
        L_0x0ac8:
            boolean r11 = r13.zzb(r14, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = com.google.android.gms.internal.places.zzdy.zzm(r14, r11)
            r15.zzc((int) r10, (boolean) r9)
            goto L_0x0b58
        L_0x0adb:
            boolean r11 = r13.zzb(r14, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.places.zzdy.zzk(r14, r11)
            r15.zzg(r10, r9)
            goto L_0x0b58
        L_0x0aed:
            boolean r11 = r13.zzb(r14, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.places.zzdy.zzl(r14, r11)
            r15.zzd((int) r10, (long) r11)
            goto L_0x0b58
        L_0x0aff:
            boolean r11 = r13.zzb(r14, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.places.zzdy.zzk(r14, r11)
            r15.zzd((int) r10, (int) r9)
            goto L_0x0b58
        L_0x0b11:
            boolean r11 = r13.zzb(r14, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.places.zzdy.zzl(r14, r11)
            r15.zzb((int) r10, (long) r11)
            goto L_0x0b58
        L_0x0b23:
            boolean r11 = r13.zzb(r14, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.places.zzdy.zzl(r14, r11)
            r15.zzj(r10, r11)
            goto L_0x0b58
        L_0x0b35:
            boolean r11 = r13.zzb(r14, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = com.google.android.gms.internal.places.zzdy.zzn(r14, r11)
            r15.zzb((int) r10, (float) r9)
            goto L_0x0b58
        L_0x0b47:
            boolean r11 = r13.zzb(r14, (int) r8)
            if (r11 == 0) goto L_0x0b58
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = com.google.android.gms.internal.places.zzdy.zzo(r14, r11)
            r15.zzb((int) r10, (double) r11)
        L_0x0b58:
            int r8 = r8 + 3
            goto L_0x05ee
        L_0x0b5c:
            if (r1 == 0) goto L_0x0b72
            com.google.android.gms.internal.places.zzar<?> r2 = r13.zzlh
            r2.zzb(r15, r1)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0b70
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0b5c
        L_0x0b70:
            r1 = r3
            goto L_0x0b5c
        L_0x0b72:
            com.google.android.gms.internal.places.zzds<?, ?> r0 = r13.zzlg
            zzb(r0, r14, (com.google.android.gms.internal.places.zzel) r15)
            return
        L_0x0b78:
            r13.zzc(r14, (com.google.android.gms.internal.places.zzel) r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.places.zzco.zzb(java.lang.Object, com.google.android.gms.internal.places.zzel):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:189:0x05c7  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0032  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzc(T r19, com.google.android.gms.internal.places.zzel r20) throws java.io.IOException {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            boolean r3 = r0.zzkx
            if (r3 == 0) goto L_0x0025
            com.google.android.gms.internal.places.zzar<?> r3 = r0.zzlh
            com.google.android.gms.internal.places.zzav r3 = r3.zzb((java.lang.Object) r1)
            com.google.android.gms.internal.places.zzdb<FieldDescriptorType, java.lang.Object> r5 = r3.zzfj
            boolean r5 = r5.isEmpty()
            if (r5 != 0) goto L_0x0025
            java.util.Iterator r3 = r3.iterator()
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            goto L_0x0027
        L_0x0025:
            r3 = 0
            r5 = 0
        L_0x0027:
            r6 = -1
            int[] r7 = r0.zzks
            int r7 = r7.length
            sun.misc.Unsafe r8 = zzkr
            r10 = 0
            r11 = 0
        L_0x0030:
            if (r10 >= r7) goto L_0x05c5
            int r12 = r0.zzai(r10)
            int[] r13 = r0.zzks
            r14 = r13[r10]
            r15 = 267386880(0xff00000, float:2.3665827E-29)
            r15 = r15 & r12
            int r15 = r15 >>> 20
            boolean r4 = r0.zzkz
            r16 = 1048575(0xfffff, float:1.469367E-39)
            if (r4 != 0) goto L_0x0069
            r4 = 17
            if (r15 > r4) goto L_0x0069
            int r4 = r10 + 2
            r4 = r13[r4]
            r13 = r4 & r16
            if (r13 == r6) goto L_0x0061
            r17 = r10
            long r9 = (long) r13
            int r11 = r8.getInt(r1, r9)
            r6 = r13
            goto L_0x0063
        L_0x0061:
            r17 = r10
        L_0x0063:
            int r4 = r4 >>> 20
            r9 = 1
            int r4 = r9 << r4
            goto L_0x006c
        L_0x0069:
            r17 = r10
            r4 = 0
        L_0x006c:
            if (r5 == 0) goto L_0x008a
            com.google.android.gms.internal.places.zzar<?> r9 = r0.zzlh
            int r9 = r9.zzb((java.util.Map.Entry<?, ?>) r5)
            if (r9 > r14) goto L_0x008a
            com.google.android.gms.internal.places.zzar<?> r9 = r0.zzlh
            r9.zzb(r2, r5)
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x0088
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            goto L_0x006c
        L_0x0088:
            r5 = 0
            goto L_0x006c
        L_0x008a:
            r9 = r12 & r16
            long r9 = (long) r9
            switch(r15) {
                case 0: goto L_0x05b3;
                case 1: goto L_0x05a4;
                case 2: goto L_0x0596;
                case 3: goto L_0x0588;
                case 4: goto L_0x057a;
                case 5: goto L_0x056c;
                case 6: goto L_0x055e;
                case 7: goto L_0x054e;
                case 8: goto L_0x053f;
                case 9: goto L_0x052c;
                case 10: goto L_0x051b;
                case 11: goto L_0x050c;
                case 12: goto L_0x04fd;
                case 13: goto L_0x04ee;
                case 14: goto L_0x04df;
                case 15: goto L_0x04d0;
                case 16: goto L_0x04c1;
                case 17: goto L_0x04ad;
                case 18: goto L_0x049a;
                case 19: goto L_0x0486;
                case 20: goto L_0x0472;
                case 21: goto L_0x045e;
                case 22: goto L_0x044a;
                case 23: goto L_0x0436;
                case 24: goto L_0x0422;
                case 25: goto L_0x040e;
                case 26: goto L_0x03fb;
                case 27: goto L_0x03e3;
                case 28: goto L_0x03d0;
                case 29: goto L_0x03bc;
                case 30: goto L_0x03a8;
                case 31: goto L_0x0394;
                case 32: goto L_0x0380;
                case 33: goto L_0x036c;
                case 34: goto L_0x0358;
                case 35: goto L_0x0344;
                case 36: goto L_0x0330;
                case 37: goto L_0x031c;
                case 38: goto L_0x0308;
                case 39: goto L_0x02f4;
                case 40: goto L_0x02e0;
                case 41: goto L_0x02cc;
                case 42: goto L_0x02b8;
                case 43: goto L_0x02a4;
                case 44: goto L_0x0290;
                case 45: goto L_0x027c;
                case 46: goto L_0x0268;
                case 47: goto L_0x0254;
                case 48: goto L_0x0240;
                case 49: goto L_0x0228;
                case 50: goto L_0x021c;
                case 51: goto L_0x0207;
                case 52: goto L_0x01f2;
                case 53: goto L_0x01dd;
                case 54: goto L_0x01c8;
                case 55: goto L_0x01b3;
                case 56: goto L_0x019e;
                case 57: goto L_0x0189;
                case 58: goto L_0x0174;
                case 59: goto L_0x015f;
                case 60: goto L_0x0146;
                case 61: goto L_0x012f;
                case 62: goto L_0x011a;
                case 63: goto L_0x0105;
                case 64: goto L_0x00f0;
                case 65: goto L_0x00db;
                case 66: goto L_0x00c6;
                case 67: goto L_0x00b1;
                case 68: goto L_0x0097;
                default: goto L_0x0092;
            }
        L_0x0092:
            r12 = r17
            r13 = 0
            goto L_0x05c1
        L_0x0097:
            r12 = r17
            boolean r4 = r0.zzb(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x00ae
            java.lang.Object r4 = r8.getObject(r1, r9)
            com.google.android.gms.internal.places.zzda r9 = r0.zzaf(r12)
            r2.zzc((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.places.zzda) r9)
            r13 = 0
            goto L_0x05c1
        L_0x00ae:
            r13 = 0
            goto L_0x05c1
        L_0x00b1:
            r12 = r17
            boolean r4 = r0.zzb(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x00c3
            long r9 = zzi(r1, r9)
            r2.zzc((int) r14, (long) r9)
            r13 = 0
            goto L_0x05c1
        L_0x00c3:
            r13 = 0
            goto L_0x05c1
        L_0x00c6:
            r12 = r17
            boolean r4 = r0.zzb(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x00d8
            int r4 = zzh(r1, r9)
            r2.zzf(r14, r4)
            r13 = 0
            goto L_0x05c1
        L_0x00d8:
            r13 = 0
            goto L_0x05c1
        L_0x00db:
            r12 = r17
            boolean r4 = r0.zzb(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x00ed
            long r9 = zzi(r1, r9)
            r2.zzk(r14, r9)
            r13 = 0
            goto L_0x05c1
        L_0x00ed:
            r13 = 0
            goto L_0x05c1
        L_0x00f0:
            r12 = r17
            boolean r4 = r0.zzb(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0102
            int r4 = zzh(r1, r9)
            r2.zzn(r14, r4)
            r13 = 0
            goto L_0x05c1
        L_0x0102:
            r13 = 0
            goto L_0x05c1
        L_0x0105:
            r12 = r17
            boolean r4 = r0.zzb(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0117
            int r4 = zzh(r1, r9)
            r2.zzo(r14, r4)
            r13 = 0
            goto L_0x05c1
        L_0x0117:
            r13 = 0
            goto L_0x05c1
        L_0x011a:
            r12 = r17
            boolean r4 = r0.zzb(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x012c
            int r4 = zzh(r1, r9)
            r2.zze(r14, r4)
            r13 = 0
            goto L_0x05c1
        L_0x012c:
            r13 = 0
            goto L_0x05c1
        L_0x012f:
            r12 = r17
            boolean r4 = r0.zzb(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0143
            java.lang.Object r4 = r8.getObject(r1, r9)
            com.google.android.gms.internal.places.zzw r4 = (com.google.android.gms.internal.places.zzw) r4
            r2.zzb((int) r14, (com.google.android.gms.internal.places.zzw) r4)
            r13 = 0
            goto L_0x05c1
        L_0x0143:
            r13 = 0
            goto L_0x05c1
        L_0x0146:
            r12 = r17
            boolean r4 = r0.zzb(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x015c
            java.lang.Object r4 = r8.getObject(r1, r9)
            com.google.android.gms.internal.places.zzda r9 = r0.zzaf(r12)
            r2.zzb((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.places.zzda) r9)
            r13 = 0
            goto L_0x05c1
        L_0x015c:
            r13 = 0
            goto L_0x05c1
        L_0x015f:
            r12 = r17
            boolean r4 = r0.zzb(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0171
            java.lang.Object r4 = r8.getObject(r1, r9)
            zzb((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.places.zzel) r2)
            r13 = 0
            goto L_0x05c1
        L_0x0171:
            r13 = 0
            goto L_0x05c1
        L_0x0174:
            r12 = r17
            boolean r4 = r0.zzb(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0186
            boolean r4 = zzj(r1, r9)
            r2.zzc((int) r14, (boolean) r4)
            r13 = 0
            goto L_0x05c1
        L_0x0186:
            r13 = 0
            goto L_0x05c1
        L_0x0189:
            r12 = r17
            boolean r4 = r0.zzb(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x019b
            int r4 = zzh(r1, r9)
            r2.zzg(r14, r4)
            r13 = 0
            goto L_0x05c1
        L_0x019b:
            r13 = 0
            goto L_0x05c1
        L_0x019e:
            r12 = r17
            boolean r4 = r0.zzb(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x01b0
            long r9 = zzi(r1, r9)
            r2.zzd((int) r14, (long) r9)
            r13 = 0
            goto L_0x05c1
        L_0x01b0:
            r13 = 0
            goto L_0x05c1
        L_0x01b3:
            r12 = r17
            boolean r4 = r0.zzb(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x01c5
            int r4 = zzh(r1, r9)
            r2.zzd((int) r14, (int) r4)
            r13 = 0
            goto L_0x05c1
        L_0x01c5:
            r13 = 0
            goto L_0x05c1
        L_0x01c8:
            r12 = r17
            boolean r4 = r0.zzb(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x01da
            long r9 = zzi(r1, r9)
            r2.zzb((int) r14, (long) r9)
            r13 = 0
            goto L_0x05c1
        L_0x01da:
            r13 = 0
            goto L_0x05c1
        L_0x01dd:
            r12 = r17
            boolean r4 = r0.zzb(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x01ef
            long r9 = zzi(r1, r9)
            r2.zzj(r14, r9)
            r13 = 0
            goto L_0x05c1
        L_0x01ef:
            r13 = 0
            goto L_0x05c1
        L_0x01f2:
            r12 = r17
            boolean r4 = r0.zzb(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0204
            float r4 = zzg(r1, r9)
            r2.zzb((int) r14, (float) r4)
            r13 = 0
            goto L_0x05c1
        L_0x0204:
            r13 = 0
            goto L_0x05c1
        L_0x0207:
            r12 = r17
            boolean r4 = r0.zzb(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0219
            double r9 = zzf(r1, r9)
            r2.zzb((int) r14, (double) r9)
            r13 = 0
            goto L_0x05c1
        L_0x0219:
            r13 = 0
            goto L_0x05c1
        L_0x021c:
            r12 = r17
            java.lang.Object r4 = r8.getObject(r1, r9)
            r0.zzb((com.google.android.gms.internal.places.zzel) r2, (int) r14, (java.lang.Object) r4, (int) r12)
            r13 = 0
            goto L_0x05c1
        L_0x0228:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzda r10 = r0.zzaf(r12)
            com.google.android.gms.internal.places.zzdc.zzc((int) r4, (java.util.List<?>) r9, (com.google.android.gms.internal.places.zzel) r2, (com.google.android.gms.internal.places.zzda) r10)
            r13 = 0
            goto L_0x05c1
        L_0x0240:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 1
            com.google.android.gms.internal.places.zzdc.zzf(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x0254:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 1
            com.google.android.gms.internal.places.zzdc.zzk(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x0268:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 1
            com.google.android.gms.internal.places.zzdc.zzh(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x027c:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 1
            com.google.android.gms.internal.places.zzdc.zzm(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x0290:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 1
            com.google.android.gms.internal.places.zzdc.zzn(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x02a4:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 1
            com.google.android.gms.internal.places.zzdc.zzj(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x02b8:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 1
            com.google.android.gms.internal.places.zzdc.zzo(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x02cc:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 1
            com.google.android.gms.internal.places.zzdc.zzl(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x02e0:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 1
            com.google.android.gms.internal.places.zzdc.zzg(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x02f4:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 1
            com.google.android.gms.internal.places.zzdc.zzi(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x0308:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 1
            com.google.android.gms.internal.places.zzdc.zze(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x031c:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 1
            com.google.android.gms.internal.places.zzdc.zzd(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x0330:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 1
            com.google.android.gms.internal.places.zzdc.zzc((int) r4, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.places.zzel) r2, (boolean) r10)
            r13 = 0
            goto L_0x05c1
        L_0x0344:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 1
            com.google.android.gms.internal.places.zzdc.zzb((int) r4, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.places.zzel) r2, (boolean) r10)
            r13 = 0
            goto L_0x05c1
        L_0x0358:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 0
            com.google.android.gms.internal.places.zzdc.zzf(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x036c:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 0
            com.google.android.gms.internal.places.zzdc.zzk(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x0380:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 0
            com.google.android.gms.internal.places.zzdc.zzh(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x0394:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 0
            com.google.android.gms.internal.places.zzdc.zzm(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x03a8:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 0
            com.google.android.gms.internal.places.zzdc.zzn(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x03bc:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 0
            com.google.android.gms.internal.places.zzdc.zzj(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x03d0:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzc(r4, r9, r2)
            r13 = 0
            goto L_0x05c1
        L_0x03e3:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzda r10 = r0.zzaf(r12)
            com.google.android.gms.internal.places.zzdc.zzb((int) r4, (java.util.List<?>) r9, (com.google.android.gms.internal.places.zzel) r2, (com.google.android.gms.internal.places.zzda) r10)
            r13 = 0
            goto L_0x05c1
        L_0x03fb:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.places.zzdc.zzb((int) r4, (java.util.List<java.lang.String>) r9, (com.google.android.gms.internal.places.zzel) r2)
            r13 = 0
            goto L_0x05c1
        L_0x040e:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 0
            com.google.android.gms.internal.places.zzdc.zzo(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x0422:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 0
            com.google.android.gms.internal.places.zzdc.zzl(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x0436:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 0
            com.google.android.gms.internal.places.zzdc.zzg(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x044a:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 0
            com.google.android.gms.internal.places.zzdc.zzi(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x045e:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 0
            com.google.android.gms.internal.places.zzdc.zze(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x0472:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 0
            com.google.android.gms.internal.places.zzdc.zzd(r4, r9, r2, r10)
            r13 = 0
            goto L_0x05c1
        L_0x0486:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r10 = 0
            com.google.android.gms.internal.places.zzdc.zzc((int) r4, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.places.zzel) r2, (boolean) r10)
            r13 = 0
            goto L_0x05c1
        L_0x049a:
            r12 = r17
            int[] r4 = r0.zzks
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r13 = 0
            com.google.android.gms.internal.places.zzdc.zzb((int) r4, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.places.zzel) r2, (boolean) r13)
            goto L_0x05c1
        L_0x04ad:
            r12 = r17
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x05c1
            java.lang.Object r4 = r8.getObject(r1, r9)
            com.google.android.gms.internal.places.zzda r9 = r0.zzaf(r12)
            r2.zzc((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.places.zzda) r9)
            goto L_0x05c1
        L_0x04c1:
            r12 = r17
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x05c1
            long r9 = r8.getLong(r1, r9)
            r2.zzc((int) r14, (long) r9)
            goto L_0x05c1
        L_0x04d0:
            r12 = r17
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x05c1
            int r4 = r8.getInt(r1, r9)
            r2.zzf(r14, r4)
            goto L_0x05c1
        L_0x04df:
            r12 = r17
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x05c1
            long r9 = r8.getLong(r1, r9)
            r2.zzk(r14, r9)
            goto L_0x05c1
        L_0x04ee:
            r12 = r17
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x05c1
            int r4 = r8.getInt(r1, r9)
            r2.zzn(r14, r4)
            goto L_0x05c1
        L_0x04fd:
            r12 = r17
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x05c1
            int r4 = r8.getInt(r1, r9)
            r2.zzo(r14, r4)
            goto L_0x05c1
        L_0x050c:
            r12 = r17
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x05c1
            int r4 = r8.getInt(r1, r9)
            r2.zze(r14, r4)
            goto L_0x05c1
        L_0x051b:
            r12 = r17
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x05c1
            java.lang.Object r4 = r8.getObject(r1, r9)
            com.google.android.gms.internal.places.zzw r4 = (com.google.android.gms.internal.places.zzw) r4
            r2.zzb((int) r14, (com.google.android.gms.internal.places.zzw) r4)
            goto L_0x05c1
        L_0x052c:
            r12 = r17
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x05c1
            java.lang.Object r4 = r8.getObject(r1, r9)
            com.google.android.gms.internal.places.zzda r9 = r0.zzaf(r12)
            r2.zzb((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.places.zzda) r9)
            goto L_0x05c1
        L_0x053f:
            r12 = r17
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x05c1
            java.lang.Object r4 = r8.getObject(r1, r9)
            zzb((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.places.zzel) r2)
            goto L_0x05c1
        L_0x054e:
            r12 = r17
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x05c1
            boolean r4 = com.google.android.gms.internal.places.zzdy.zzm(r1, r9)
            r2.zzc((int) r14, (boolean) r4)
            goto L_0x05c1
        L_0x055e:
            r12 = r17
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x05c1
            int r4 = r8.getInt(r1, r9)
            r2.zzg(r14, r4)
            goto L_0x05c1
        L_0x056c:
            r12 = r17
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x05c1
            long r9 = r8.getLong(r1, r9)
            r2.zzd((int) r14, (long) r9)
            goto L_0x05c1
        L_0x057a:
            r12 = r17
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x05c1
            int r4 = r8.getInt(r1, r9)
            r2.zzd((int) r14, (int) r4)
            goto L_0x05c1
        L_0x0588:
            r12 = r17
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x05c1
            long r9 = r8.getLong(r1, r9)
            r2.zzb((int) r14, (long) r9)
            goto L_0x05c1
        L_0x0596:
            r12 = r17
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x05c1
            long r9 = r8.getLong(r1, r9)
            r2.zzj(r14, r9)
            goto L_0x05c1
        L_0x05a4:
            r12 = r17
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x05c1
            float r4 = com.google.android.gms.internal.places.zzdy.zzn(r1, r9)
            r2.zzb((int) r14, (float) r4)
            goto L_0x05c1
        L_0x05b3:
            r12 = r17
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x05c1
            double r9 = com.google.android.gms.internal.places.zzdy.zzo(r1, r9)
            r2.zzb((int) r14, (double) r9)
        L_0x05c1:
            int r10 = r12 + 3
            goto L_0x0030
        L_0x05c5:
            if (r5 == 0) goto L_0x05dc
            com.google.android.gms.internal.places.zzar<?> r4 = r0.zzlh
            r4.zzb(r2, r5)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x05da
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            r5 = r4
            goto L_0x05c5
        L_0x05da:
            r5 = 0
            goto L_0x05c5
        L_0x05dc:
            com.google.android.gms.internal.places.zzds<?, ?> r3 = r0.zzlg
            zzb(r3, r1, (com.google.android.gms.internal.places.zzel) r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.places.zzco.zzc(java.lang.Object, com.google.android.gms.internal.places.zzel):void");
    }

    private final <K, V> void zzb(zzel zzel, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzel.zzb(i, this.zzli.zzl(zzag(i2)), this.zzli.zzh(obj));
        }
    }

    private static <UT, UB> void zzb(zzds<UT, UB> zzds, T t, zzel zzel) throws IOException {
        zzds.zzb(zzds.zzr(t), zzel);
    }

    private static zzdr zzo(Object obj) {
        zzbc zzbc = (zzbc) obj;
        zzdr zzdr = zzbc.zzih;
        if (zzdr != zzdr.zzdh()) {
            return zzdr;
        }
        zzdr zzdi = zzdr.zzdi();
        zzbc.zzih = zzdi;
        return zzdi;
    }

    private static int zzb(byte[] bArr, int i, int i2, zzef zzef, Class<?> cls, zzr zzr) throws IOException {
        switch (zzcn.zzfi[zzef.ordinal()]) {
            case 1:
                int zzc = zzs.zzc(bArr, i, zzr);
                zzr.zzeb = Boolean.valueOf(zzr.zzea != 0);
                return zzc;
            case 2:
                return zzs.zzf(bArr, i, zzr);
            case 3:
                zzr.zzeb = Double.valueOf(zzs.zzd(bArr, i));
                return i + 8;
            case 4:
            case 5:
                zzr.zzeb = Integer.valueOf(zzs.zzb(bArr, i));
                return i + 4;
            case 6:
            case 7:
                zzr.zzeb = Long.valueOf(zzs.zzc(bArr, i));
                return i + 8;
            case 8:
                zzr.zzeb = Float.valueOf(zzs.zze(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                int zzb = zzs.zzb(bArr, i, zzr);
                zzr.zzeb = Integer.valueOf(zzr.zzdz);
                return zzb;
            case 12:
            case 13:
                int zzc2 = zzs.zzc(bArr, i, zzr);
                zzr.zzeb = Long.valueOf(zzr.zzea);
                return zzc2;
            case 14:
                return zzs.zzb((zzda) zzcv.zzcq().zzf(cls), bArr, i, i2, zzr);
            case 15:
                int zzb2 = zzs.zzb(bArr, i, zzr);
                zzr.zzeb = Integer.valueOf(zzai.zzm(zzr.zzdz));
                return zzb2;
            case 16:
                int zzc3 = zzs.zzc(bArr, i, zzr);
                zzr.zzeb = Long.valueOf(zzai.zzb(zzr.zzea));
                return zzc3;
            case 17:
                return zzs.zze(bArr, i, zzr);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final int zzb(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzr zzr) throws IOException {
        int i8;
        int i9;
        int i10;
        int i11;
        T t2 = t;
        byte[] bArr2 = bArr;
        int i12 = i;
        int i13 = i2;
        int i14 = i3;
        int i15 = i5;
        int i16 = i6;
        long j3 = j2;
        zzr zzr2 = zzr;
        Unsafe unsafe = zzkr;
        zzbh zzbh = (zzbh) unsafe.getObject(t2, j3);
        if (!zzbh.zzaa()) {
            int size = zzbh.size();
            zzbh = zzbh.zzh(size == 0 ? 10 : size << 1);
            unsafe.putObject(t2, j3, zzbh);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i15 == 2) {
                    zzao zzao = (zzao) zzbh;
                    int zzb = zzs.zzb(bArr2, i12, zzr2);
                    int i17 = zzr2.zzdz + zzb;
                    while (zzb < i17) {
                        zzao.zzd(zzs.zzd(bArr2, zzb));
                        zzb += 8;
                    }
                    if (zzb == i17) {
                        return zzb;
                    }
                    throw zzbk.zzbp();
                } else if (i15 == 1) {
                    zzao zzao2 = (zzao) zzbh;
                    zzao2.zzd(zzs.zzd(bArr, i));
                    int i18 = i12 + 8;
                    while (i18 < i13) {
                        int zzb2 = zzs.zzb(bArr2, i18, zzr2);
                        if (i14 != zzr2.zzdz) {
                            return i18;
                        }
                        zzao2.zzd(zzs.zzd(bArr2, zzb2));
                        i18 = zzb2 + 8;
                    }
                    return i18;
                }
                break;
            case 19:
            case 36:
                if (i15 == 2) {
                    zzbb zzbb = (zzbb) zzbh;
                    int zzb3 = zzs.zzb(bArr2, i12, zzr2);
                    int i19 = zzr2.zzdz + zzb3;
                    while (zzb3 < i19) {
                        zzbb.zzf(zzs.zze(bArr2, zzb3));
                        zzb3 += 4;
                    }
                    if (zzb3 == i19) {
                        return zzb3;
                    }
                    throw zzbk.zzbp();
                } else if (i15 == 5) {
                    zzbb zzbb2 = (zzbb) zzbh;
                    zzbb2.zzf(zzs.zze(bArr, i));
                    int i20 = i12 + 4;
                    while (i20 < i13) {
                        int zzb4 = zzs.zzb(bArr2, i20, zzr2);
                        if (i14 != zzr2.zzdz) {
                            return i20;
                        }
                        zzbb2.zzf(zzs.zze(bArr2, zzb4));
                        i20 = zzb4 + 4;
                    }
                    return i20;
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i15 == 2) {
                    zzby zzby = (zzby) zzbh;
                    int zzb5 = zzs.zzb(bArr2, i12, zzr2);
                    int i21 = zzr2.zzdz + zzb5;
                    while (zzb5 < i21) {
                        zzb5 = zzs.zzc(bArr2, zzb5, zzr2);
                        zzby.zzm(zzr2.zzea);
                    }
                    if (zzb5 == i21) {
                        return zzb5;
                    }
                    throw zzbk.zzbp();
                } else if (i15 == 0) {
                    zzby zzby2 = (zzby) zzbh;
                    int zzc = zzs.zzc(bArr2, i12, zzr2);
                    zzby2.zzm(zzr2.zzea);
                    while (zzc < i13) {
                        int zzb6 = zzs.zzb(bArr2, zzc, zzr2);
                        if (i14 != zzr2.zzdz) {
                            return zzc;
                        }
                        zzc = zzs.zzc(bArr2, zzb6, zzr2);
                        zzby2.zzm(zzr2.zzea);
                    }
                    return zzc;
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i15 == 2) {
                    return zzs.zzb(bArr2, i12, (zzbh<?>) zzbh, zzr2);
                }
                if (i15 == 0) {
                    return zzs.zzb(i3, bArr, i, i2, (zzbh<?>) zzbh, zzr);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i15 == 2) {
                    zzby zzby3 = (zzby) zzbh;
                    int zzb7 = zzs.zzb(bArr2, i12, zzr2);
                    int i22 = zzr2.zzdz + zzb7;
                    while (zzb7 < i22) {
                        zzby3.zzm(zzs.zzc(bArr2, zzb7));
                        zzb7 += 8;
                    }
                    if (zzb7 == i22) {
                        return zzb7;
                    }
                    throw zzbk.zzbp();
                } else if (i15 == 1) {
                    zzby zzby4 = (zzby) zzbh;
                    zzby4.zzm(zzs.zzc(bArr, i));
                    int i23 = i12 + 8;
                    while (i23 < i13) {
                        int zzb8 = zzs.zzb(bArr2, i23, zzr2);
                        if (i14 != zzr2.zzdz) {
                            return i23;
                        }
                        zzby4.zzm(zzs.zzc(bArr2, zzb8));
                        i23 = zzb8 + 8;
                    }
                    return i23;
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i15 == 2) {
                    zzbe zzbe = (zzbe) zzbh;
                    int zzb9 = zzs.zzb(bArr2, i12, zzr2);
                    int i24 = zzr2.zzdz + zzb9;
                    while (zzb9 < i24) {
                        zzbe.zzac(zzs.zzb(bArr2, zzb9));
                        zzb9 += 4;
                    }
                    if (zzb9 == i24) {
                        return zzb9;
                    }
                    throw zzbk.zzbp();
                } else if (i15 == 5) {
                    zzbe zzbe2 = (zzbe) zzbh;
                    zzbe2.zzac(zzs.zzb(bArr, i));
                    int i25 = i12 + 4;
                    while (i25 < i13) {
                        int zzb10 = zzs.zzb(bArr2, i25, zzr2);
                        if (i14 != zzr2.zzdz) {
                            return i25;
                        }
                        zzbe2.zzac(zzs.zzb(bArr2, zzb10));
                        i25 = zzb10 + 4;
                    }
                    return i25;
                }
                break;
            case 25:
            case 42:
                if (i15 == 2) {
                    zzu zzu = (zzu) zzbh;
                    int zzb11 = zzs.zzb(bArr2, i12, zzr2);
                    int i26 = zzr2.zzdz + zzb11;
                    while (zzb11 < i26) {
                        zzb11 = zzs.zzc(bArr2, zzb11, zzr2);
                        zzu.addBoolean(zzr2.zzea != 0);
                    }
                    if (zzb11 == i26) {
                        return zzb11;
                    }
                    throw zzbk.zzbp();
                } else if (i15 == 0) {
                    zzu zzu2 = (zzu) zzbh;
                    int zzc2 = zzs.zzc(bArr2, i12, zzr2);
                    zzu2.addBoolean(zzr2.zzea != 0);
                    while (zzc2 < i13) {
                        int zzb12 = zzs.zzb(bArr2, zzc2, zzr2);
                        if (i14 != zzr2.zzdz) {
                            return zzc2;
                        }
                        zzc2 = zzs.zzc(bArr2, zzb12, zzr2);
                        zzu2.addBoolean(zzr2.zzea != 0);
                    }
                    return zzc2;
                }
                break;
            case 26:
                if (i15 == 2) {
                    if ((j & 536870912) == 0) {
                        int zzb13 = zzs.zzb(bArr2, i12, zzr2);
                        int i27 = zzr2.zzdz;
                        if (i27 >= 0) {
                            if (i27 == 0) {
                                zzbh.add("");
                            } else {
                                zzbh.add(new String(bArr2, zzb13, i27, zzbd.UTF_8));
                                zzb13 += i27;
                            }
                            while (i9 < i13) {
                                int zzb14 = zzs.zzb(bArr2, i9, zzr2);
                                if (i14 != zzr2.zzdz) {
                                    return i9;
                                }
                                i9 = zzs.zzb(bArr2, zzb14, zzr2);
                                int i28 = zzr2.zzdz;
                                if (i28 < 0) {
                                    throw zzbk.zzbq();
                                } else if (i28 == 0) {
                                    zzbh.add("");
                                } else {
                                    zzbh.add(new String(bArr2, i9, i28, zzbd.UTF_8));
                                    i9 += i28;
                                }
                            }
                            return i9;
                        }
                        throw zzbk.zzbq();
                    }
                    int zzb15 = zzs.zzb(bArr2, i12, zzr2);
                    int i29 = zzr2.zzdz;
                    if (i29 >= 0) {
                        if (i29 == 0) {
                            zzbh.add("");
                        } else {
                            int i30 = zzb15 + i29;
                            if (zzea.zzf(bArr2, zzb15, i30)) {
                                zzbh.add(new String(bArr2, zzb15, i29, zzbd.UTF_8));
                                zzb15 = i30;
                            } else {
                                throw zzbk.zzbu();
                            }
                        }
                        while (i8 < i13) {
                            int zzb16 = zzs.zzb(bArr2, i8, zzr2);
                            if (i14 != zzr2.zzdz) {
                                return i8;
                            }
                            i8 = zzs.zzb(bArr2, zzb16, zzr2);
                            int i31 = zzr2.zzdz;
                            if (i31 < 0) {
                                throw zzbk.zzbq();
                            } else if (i31 == 0) {
                                zzbh.add("");
                            } else {
                                int i32 = i8 + i31;
                                if (zzea.zzf(bArr2, i8, i32)) {
                                    zzbh.add(new String(bArr2, i8, i31, zzbd.UTF_8));
                                    i8 = i32;
                                } else {
                                    throw zzbk.zzbu();
                                }
                            }
                        }
                        return i8;
                    }
                    throw zzbk.zzbq();
                }
                break;
            case 27:
                if (i15 == 2) {
                    return zzs.zzb(zzaf(i16), i3, bArr, i, i2, zzbh, zzr);
                }
                break;
            case 28:
                if (i15 == 2) {
                    int zzb17 = zzs.zzb(bArr2, i12, zzr2);
                    int i33 = zzr2.zzdz;
                    if (i33 < 0) {
                        throw zzbk.zzbq();
                    } else if (i33 <= bArr2.length - zzb17) {
                        if (i33 == 0) {
                            zzbh.add(zzw.zzeg);
                        } else {
                            zzbh.add(zzw.zzc(bArr2, zzb17, i33));
                            zzb17 += i33;
                        }
                        while (i10 < i13) {
                            int zzb18 = zzs.zzb(bArr2, i10, zzr2);
                            if (i14 != zzr2.zzdz) {
                                return i10;
                            }
                            i10 = zzs.zzb(bArr2, zzb18, zzr2);
                            int i34 = zzr2.zzdz;
                            if (i34 < 0) {
                                throw zzbk.zzbq();
                            } else if (i34 > bArr2.length - i10) {
                                throw zzbk.zzbp();
                            } else if (i34 == 0) {
                                zzbh.add(zzw.zzeg);
                            } else {
                                zzbh.add(zzw.zzc(bArr2, i10, i34));
                                i10 += i34;
                            }
                        }
                        return i10;
                    } else {
                        throw zzbk.zzbp();
                    }
                }
                break;
            case 30:
            case 44:
                if (i15 == 2) {
                    i11 = zzs.zzb(bArr2, i12, (zzbh<?>) zzbh, zzr2);
                } else if (i15 == 0) {
                    i11 = zzs.zzb(i3, bArr, i, i2, (zzbh<?>) zzbh, zzr);
                }
                zzbc zzbc = (zzbc) t2;
                zzdr zzdr = zzbc.zzih;
                if (zzdr == zzdr.zzdh()) {
                    zzdr = null;
                }
                zzdr zzdr2 = (zzdr) zzdc.zzb(i4, zzbh, zzah(i16), zzdr, this.zzlg);
                if (zzdr2 != null) {
                    zzbc.zzih = zzdr2;
                }
                return i11;
            case 33:
            case 47:
                if (i15 == 2) {
                    zzbe zzbe3 = (zzbe) zzbh;
                    int zzb19 = zzs.zzb(bArr2, i12, zzr2);
                    int i35 = zzr2.zzdz + zzb19;
                    while (zzb19 < i35) {
                        zzb19 = zzs.zzb(bArr2, zzb19, zzr2);
                        zzbe3.zzac(zzai.zzm(zzr2.zzdz));
                    }
                    if (zzb19 == i35) {
                        return zzb19;
                    }
                    throw zzbk.zzbp();
                } else if (i15 == 0) {
                    zzbe zzbe4 = (zzbe) zzbh;
                    int zzb20 = zzs.zzb(bArr2, i12, zzr2);
                    zzbe4.zzac(zzai.zzm(zzr2.zzdz));
                    while (zzb20 < i13) {
                        int zzb21 = zzs.zzb(bArr2, zzb20, zzr2);
                        if (i14 != zzr2.zzdz) {
                            return zzb20;
                        }
                        zzb20 = zzs.zzb(bArr2, zzb21, zzr2);
                        zzbe4.zzac(zzai.zzm(zzr2.zzdz));
                    }
                    return zzb20;
                }
                break;
            case 34:
            case 48:
                if (i15 == 2) {
                    zzby zzby5 = (zzby) zzbh;
                    int zzb22 = zzs.zzb(bArr2, i12, zzr2);
                    int i36 = zzr2.zzdz + zzb22;
                    while (zzb22 < i36) {
                        zzb22 = zzs.zzc(bArr2, zzb22, zzr2);
                        zzby5.zzm(zzai.zzb(zzr2.zzea));
                    }
                    if (zzb22 == i36) {
                        return zzb22;
                    }
                    throw zzbk.zzbp();
                } else if (i15 == 0) {
                    zzby zzby6 = (zzby) zzbh;
                    int zzc3 = zzs.zzc(bArr2, i12, zzr2);
                    zzby6.zzm(zzai.zzb(zzr2.zzea));
                    while (zzc3 < i13) {
                        int zzb23 = zzs.zzb(bArr2, zzc3, zzr2);
                        if (i14 != zzr2.zzdz) {
                            return zzc3;
                        }
                        zzc3 = zzs.zzc(bArr2, zzb23, zzr2);
                        zzby6.zzm(zzai.zzb(zzr2.zzea));
                    }
                    return zzc3;
                }
                break;
            case 49:
                if (i15 == 3) {
                    zzda zzaf = zzaf(i16);
                    int i37 = (i14 & -8) | 4;
                    int zzb24 = zzs.zzb(zzaf, bArr, i, i2, i37, zzr);
                    zzbh.add(zzr2.zzeb);
                    while (zzb24 < i13) {
                        int zzb25 = zzs.zzb(bArr2, zzb24, zzr2);
                        if (i14 != zzr2.zzdz) {
                            return zzb24;
                        }
                        zzb24 = zzs.zzb(zzaf, bArr, zzb25, i2, i37, zzr);
                        zzbh.add(zzr2.zzeb);
                    }
                    return zzb24;
                }
                break;
        }
        return i12;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v12, resolved type: byte} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <K, V> int zzb(T r8, byte[] r9, int r10, int r11, int r12, long r13, com.google.android.gms.internal.places.zzr r15) throws java.io.IOException {
        /*
            r7 = this;
            sun.misc.Unsafe r0 = zzkr
            java.lang.Object r12 = r7.zzag(r12)
            java.lang.Object r1 = r0.getObject(r8, r13)
            com.google.android.gms.internal.places.zzcd r2 = r7.zzli
            boolean r2 = r2.zzi(r1)
            if (r2 == 0) goto L_0x0022
            com.google.android.gms.internal.places.zzcd r2 = r7.zzli
            java.lang.Object r2 = r2.zzk(r12)
            com.google.android.gms.internal.places.zzcd r3 = r7.zzli
            r3.zzc(r2, r1)
            r0.putObject(r8, r13, r2)
            r1 = r2
        L_0x0022:
            com.google.android.gms.internal.places.zzcd r8 = r7.zzli
            com.google.android.gms.internal.places.zzcb r8 = r8.zzl(r12)
            com.google.android.gms.internal.places.zzcd r12 = r7.zzli
            java.util.Map r12 = r12.zzg(r1)
            int r10 = com.google.android.gms.internal.places.zzs.zzb(r9, r10, r15)
            int r13 = r15.zzdz
            if (r13 < 0) goto L_0x0099
            int r14 = r11 - r10
            if (r13 > r14) goto L_0x0099
            int r13 = r13 + r10
            K r14 = r8.zzkk
            V r0 = r8.zzkm
        L_0x0040:
            if (r10 >= r13) goto L_0x008d
            int r1 = r10 + 1
            byte r10 = r9[r10]
            if (r10 >= 0) goto L_0x0050
            int r1 = com.google.android.gms.internal.places.zzs.zzb((int) r10, (byte[]) r9, (int) r1, (com.google.android.gms.internal.places.zzr) r15)
            int r10 = r15.zzdz
            r2 = r1
            goto L_0x0051
        L_0x0050:
            r2 = r1
        L_0x0051:
            int r1 = r10 >>> 3
            r3 = r10 & 7
            switch(r1) {
                case 1: goto L_0x0073;
                case 2: goto L_0x0059;
                default: goto L_0x0058;
            }
        L_0x0058:
            goto L_0x0088
        L_0x0059:
            com.google.android.gms.internal.places.zzef r1 = r8.zzkl
            int r1 = r1.zzds()
            if (r3 != r1) goto L_0x0088
            com.google.android.gms.internal.places.zzef r4 = r8.zzkl
            V r10 = r8.zzkm
            java.lang.Class r5 = r10.getClass()
            r1 = r9
            r3 = r11
            r6 = r15
            int r10 = zzb((byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.places.zzef) r4, (java.lang.Class<?>) r5, (com.google.android.gms.internal.places.zzr) r6)
            java.lang.Object r0 = r15.zzeb
            goto L_0x0040
        L_0x0073:
            com.google.android.gms.internal.places.zzef r1 = r8.zzkj
            int r1 = r1.zzds()
            if (r3 != r1) goto L_0x0088
            com.google.android.gms.internal.places.zzef r4 = r8.zzkj
            r5 = 0
            r1 = r9
            r3 = r11
            r6 = r15
            int r10 = zzb((byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.places.zzef) r4, (java.lang.Class<?>) r5, (com.google.android.gms.internal.places.zzr) r6)
            java.lang.Object r14 = r15.zzeb
            goto L_0x0040
        L_0x0088:
            int r10 = com.google.android.gms.internal.places.zzs.zzb((int) r10, (byte[]) r9, (int) r2, (int) r11, (com.google.android.gms.internal.places.zzr) r15)
            goto L_0x0040
        L_0x008d:
            if (r10 != r13) goto L_0x0094
            r12.put(r14, r0)
            return r13
        L_0x0094:
            com.google.android.gms.internal.places.zzbk r8 = com.google.android.gms.internal.places.zzbk.zzbt()
            throw r8
        L_0x0099:
            com.google.android.gms.internal.places.zzbk r8 = com.google.android.gms.internal.places.zzbk.zzbp()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.places.zzco.zzb(java.lang.Object, byte[], int, int, int, long, com.google.android.gms.internal.places.zzr):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01a4, code lost:
        r12.putInt(r1, r13, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzb(T r17, byte[] r18, int r19, int r20, int r21, int r22, int r23, int r24, int r25, long r26, int r28, com.google.android.gms.internal.places.zzr r29) throws java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r3 = r18
            r4 = r19
            r2 = r21
            r8 = r22
            r5 = r23
            r9 = r26
            r6 = r28
            r11 = r29
            sun.misc.Unsafe r12 = zzkr
            int[] r7 = r0.zzks
            int r13 = r6 + 2
            r7 = r7[r13]
            r13 = 1048575(0xfffff, float:1.469367E-39)
            r7 = r7 & r13
            long r13 = (long) r7
            r7 = 5
            r15 = 2
            switch(r25) {
                case 51: goto L_0x0193;
                case 52: goto L_0x0183;
                case 53: goto L_0x0173;
                case 54: goto L_0x0173;
                case 55: goto L_0x0163;
                case 56: goto L_0x0152;
                case 57: goto L_0x0142;
                case 58: goto L_0x0129;
                case 59: goto L_0x00f5;
                case 60: goto L_0x00c6;
                case 61: goto L_0x00b9;
                case 62: goto L_0x0163;
                case 63: goto L_0x008b;
                case 64: goto L_0x0142;
                case 65: goto L_0x0152;
                case 66: goto L_0x0076;
                case 67: goto L_0x0061;
                case 68: goto L_0x0028;
                default: goto L_0x0026;
            }
        L_0x0026:
            goto L_0x01a8
        L_0x0028:
            r7 = 3
            if (r5 != r7) goto L_0x01a8
            r2 = r2 & -8
            r7 = r2 | 4
            com.google.android.gms.internal.places.zzda r2 = r0.zzaf(r6)
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r7
            r7 = r29
            int r2 = com.google.android.gms.internal.places.zzs.zzb((com.google.android.gms.internal.places.zzda) r2, (byte[]) r3, (int) r4, (int) r5, (int) r6, (com.google.android.gms.internal.places.zzr) r7)
            int r3 = r12.getInt(r1, r13)
            if (r3 != r8) goto L_0x004c
            java.lang.Object r15 = r12.getObject(r1, r9)
            goto L_0x004d
        L_0x004c:
            r15 = 0
        L_0x004d:
            if (r15 != 0) goto L_0x0056
            java.lang.Object r3 = r11.zzeb
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x0056:
            java.lang.Object r3 = r11.zzeb
            java.lang.Object r3 = com.google.android.gms.internal.places.zzbd.zzb((java.lang.Object) r15, (java.lang.Object) r3)
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x0061:
            if (r5 != 0) goto L_0x01a8
            int r2 = com.google.android.gms.internal.places.zzs.zzc(r3, r4, r11)
            long r3 = r11.zzea
            long r3 = com.google.android.gms.internal.places.zzai.zzb(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x0076:
            if (r5 != 0) goto L_0x01a8
            int r2 = com.google.android.gms.internal.places.zzs.zzb(r3, r4, r11)
            int r3 = r11.zzdz
            int r3 = com.google.android.gms.internal.places.zzai.zzm(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x008b:
            if (r5 != 0) goto L_0x01a8
            int r3 = com.google.android.gms.internal.places.zzs.zzb(r3, r4, r11)
            int r4 = r11.zzdz
            com.google.android.gms.internal.places.zzbf r5 = r0.zzah(r6)
            if (r5 == 0) goto L_0x00af
            boolean r5 = r5.zzad(r4)
            if (r5 == 0) goto L_0x00a0
            goto L_0x00af
        L_0x00a0:
            com.google.android.gms.internal.places.zzdr r1 = zzo(r17)
            long r4 = (long) r4
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r1.zzc(r2, r4)
            r2 = r3
            goto L_0x01a9
        L_0x00af:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            r12.putObject(r1, r9, r2)
            r2 = r3
            goto L_0x01a4
        L_0x00b9:
            if (r5 != r15) goto L_0x01a8
            int r2 = com.google.android.gms.internal.places.zzs.zzf(r3, r4, r11)
            java.lang.Object r3 = r11.zzeb
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x00c6:
            if (r5 != r15) goto L_0x01a8
            com.google.android.gms.internal.places.zzda r2 = r0.zzaf(r6)
            r5 = r20
            int r2 = com.google.android.gms.internal.places.zzs.zzb((com.google.android.gms.internal.places.zzda) r2, (byte[]) r3, (int) r4, (int) r5, (com.google.android.gms.internal.places.zzr) r11)
            int r3 = r12.getInt(r1, r13)
            if (r3 != r8) goto L_0x00de
            java.lang.Object r15 = r12.getObject(r1, r9)
            goto L_0x00df
        L_0x00de:
            r15 = 0
        L_0x00df:
            if (r15 != 0) goto L_0x00e7
            java.lang.Object r3 = r11.zzeb
            r12.putObject(r1, r9, r3)
            goto L_0x00f0
        L_0x00e7:
            java.lang.Object r3 = r11.zzeb
            java.lang.Object r3 = com.google.android.gms.internal.places.zzbd.zzb((java.lang.Object) r15, (java.lang.Object) r3)
            r12.putObject(r1, r9, r3)
        L_0x00f0:
            r12.putInt(r1, r13, r8)
            goto L_0x01a9
        L_0x00f5:
            if (r5 != r15) goto L_0x01a8
            int r2 = com.google.android.gms.internal.places.zzs.zzb(r3, r4, r11)
            int r4 = r11.zzdz
            if (r4 != 0) goto L_0x0105
            java.lang.String r3 = ""
            r12.putObject(r1, r9, r3)
            goto L_0x0124
        L_0x0105:
            r5 = 536870912(0x20000000, float:1.0842022E-19)
            r5 = r24 & r5
            if (r5 == 0) goto L_0x0119
            int r5 = r2 + r4
            boolean r5 = com.google.android.gms.internal.places.zzea.zzf(r3, r2, r5)
            if (r5 == 0) goto L_0x0114
            goto L_0x0119
        L_0x0114:
            com.google.android.gms.internal.places.zzbk r1 = com.google.android.gms.internal.places.zzbk.zzbu()
            throw r1
        L_0x0119:
            java.lang.String r5 = new java.lang.String
            java.nio.charset.Charset r6 = com.google.android.gms.internal.places.zzbd.UTF_8
            r5.<init>(r3, r2, r4, r6)
            r12.putObject(r1, r9, r5)
            int r2 = r2 + r4
        L_0x0124:
            r12.putInt(r1, r13, r8)
            goto L_0x01a9
        L_0x0129:
            if (r5 != 0) goto L_0x01a8
            int r2 = com.google.android.gms.internal.places.zzs.zzc(r3, r4, r11)
            long r3 = r11.zzea
            r5 = 0
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 == 0) goto L_0x0139
            r15 = 1
            goto L_0x013a
        L_0x0139:
            r15 = 0
        L_0x013a:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r15)
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x0142:
            if (r5 != r7) goto L_0x01a8
            int r2 = com.google.android.gms.internal.places.zzs.zzb(r18, r19)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r12.putObject(r1, r9, r2)
            int r2 = r4 + 4
            goto L_0x01a4
        L_0x0152:
            r2 = 1
            if (r5 != r2) goto L_0x01a8
            long r2 = com.google.android.gms.internal.places.zzs.zzc(r18, r19)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r12.putObject(r1, r9, r2)
            int r2 = r4 + 8
            goto L_0x01a4
        L_0x0163:
            if (r5 != 0) goto L_0x01a8
            int r2 = com.google.android.gms.internal.places.zzs.zzb(r3, r4, r11)
            int r3 = r11.zzdz
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x0173:
            if (r5 != 0) goto L_0x01a8
            int r2 = com.google.android.gms.internal.places.zzs.zzc(r3, r4, r11)
            long r3 = r11.zzea
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x01a4
        L_0x0183:
            if (r5 != r7) goto L_0x01a8
            float r2 = com.google.android.gms.internal.places.zzs.zze(r18, r19)
            java.lang.Float r2 = java.lang.Float.valueOf(r2)
            r12.putObject(r1, r9, r2)
            int r2 = r4 + 4
            goto L_0x01a4
        L_0x0193:
            r2 = 1
            if (r5 != r2) goto L_0x01a8
            double r2 = com.google.android.gms.internal.places.zzs.zzd(r18, r19)
            java.lang.Double r2 = java.lang.Double.valueOf(r2)
            r12.putObject(r1, r9, r2)
            int r2 = r4 + 8
        L_0x01a4:
            r12.putInt(r1, r13, r8)
            goto L_0x01a9
        L_0x01a8:
            r2 = r4
        L_0x01a9:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.places.zzco.zzb(java.lang.Object, byte[], int, int, int, int, int, int, int, long, int, com.google.android.gms.internal.places.zzr):int");
    }

    private final zzda zzaf(int i) {
        int i2 = (i / 3) << 1;
        zzda zzda = (zzda) this.zzkt[i2];
        if (zzda != null) {
            return zzda;
        }
        zzda zzf = zzcv.zzcq().zzf((Class) this.zzkt[i2 + 1]);
        this.zzkt[i2] = zzf;
        return zzf;
    }

    private final Object zzag(int i) {
        return this.zzkt[(i / 3) << 1];
    }

    private final zzbf zzah(int i) {
        return (zzbf) this.zzkt[((i / 3) << 1) + 1];
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v31, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v24, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v24, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v28, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v30, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v32, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v27, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v34, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v28, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v36, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v29, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v38, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v30, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v40, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v32, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v41, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v33, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v43, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v24, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v34, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v45, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v27, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v36, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v48, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v28, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v34, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v35, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzb(T r31, byte[] r32, int r33, int r34, int r35, com.google.android.gms.internal.places.zzr r36) throws java.io.IOException {
        /*
            r30 = this;
            r15 = r30
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            r9 = r36
            sun.misc.Unsafe r10 = zzkr
            r16 = 0
            r0 = r33
            r2 = r16
            r3 = r2
            r6 = r3
            r1 = -1
            r7 = -1
        L_0x001c:
            r17 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 >= r13) goto L_0x0563
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0030
            int r0 = com.google.android.gms.internal.places.zzs.zzb((int) r0, (byte[]) r12, (int) r3, (com.google.android.gms.internal.places.zzr) r9)
            int r3 = r9.zzdz
            r4 = r0
            r5 = r3
            goto L_0x0032
        L_0x0030:
            r5 = r0
            r4 = r3
        L_0x0032:
            int r3 = r5 >>> 3
            r0 = r5 & 7
            r8 = 3
            if (r3 <= r1) goto L_0x0040
            int r2 = r2 / r8
            int r1 = r15.zzq(r3, r2)
            r2 = r1
            goto L_0x0045
        L_0x0040:
            int r1 = r15.zzak(r3)
            r2 = r1
        L_0x0045:
            r1 = -1
            if (r2 != r1) goto L_0x0054
            r25 = r3
            r2 = r4
            r26 = r5
            r27 = r10
            r19 = r16
            goto L_0x04b8
        L_0x0054:
            int[] r1 = r15.zzks
            int r19 = r2 + 1
            r8 = r1[r19]
            r19 = 267386880(0xff00000, float:2.3665827E-29)
            r19 = r8 & r19
            int r11 = r19 >>> 20
            r19 = r5
            r5 = r8 & r17
            long r12 = (long) r5
            r5 = 17
            r20 = r8
            if (r11 > r5) goto L_0x0382
            int r5 = r2 + 2
            r1 = r1[r5]
            int r5 = r1 >>> 20
            r8 = 1
            int r22 = r8 << r5
            r1 = r1 & r17
            if (r1 == r7) goto L_0x008b
            r5 = -1
            if (r7 == r5) goto L_0x0083
            long r8 = (long) r7
            r10.putInt(r14, r8, r6)
        L_0x0083:
            long r6 = (long) r1
            int r6 = r10.getInt(r14, r6)
            r7 = r1
            goto L_0x008c
        L_0x008b:
            r5 = -1
        L_0x008c:
            r1 = 5
            switch(r11) {
                case 0: goto L_0x034d;
                case 1: goto L_0x0326;
                case 2: goto L_0x02fb;
                case 3: goto L_0x02fb;
                case 4: goto L_0x02d4;
                case 5: goto L_0x02a0;
                case 6: goto L_0x0278;
                case 7: goto L_0x0242;
                case 8: goto L_0x020e;
                case 9: goto L_0x01cb;
                case 10: goto L_0x01a3;
                case 11: goto L_0x02d4;
                case 12: goto L_0x0159;
                case 13: goto L_0x0278;
                case 14: goto L_0x02a0;
                case 15: goto L_0x012e;
                case 16: goto L_0x00f6;
                case 17: goto L_0x009f;
                default: goto L_0x0090;
            }
        L_0x0090:
            r12 = r32
            r13 = r36
            r9 = r2
            r11 = r3
            r33 = r7
            r8 = r19
            r7 = r4
            r19 = r5
            goto L_0x0375
        L_0x009f:
            r1 = 3
            if (r0 != r1) goto L_0x00e6
            int r0 = r3 << 3
            r8 = r0 | 4
            com.google.android.gms.internal.places.zzda r0 = r15.zzaf(r2)
            r1 = r32
            r9 = r2
            r2 = r4
            r11 = r3
            r3 = r34
            r4 = r8
            r8 = r19
            r19 = r5
            r5 = r36
            int r0 = com.google.android.gms.internal.places.zzs.zzb((com.google.android.gms.internal.places.zzda) r0, (byte[]) r1, (int) r2, (int) r3, (int) r4, (com.google.android.gms.internal.places.zzr) r5)
            r1 = r6 & r22
            if (r1 != 0) goto L_0x00c9
            r5 = r36
            java.lang.Object r1 = r5.zzeb
            r10.putObject(r14, r12, r1)
            goto L_0x00d8
        L_0x00c9:
            r5 = r36
            java.lang.Object r1 = r10.getObject(r14, r12)
            java.lang.Object r2 = r5.zzeb
            java.lang.Object r1 = com.google.android.gms.internal.places.zzbd.zzb((java.lang.Object) r1, (java.lang.Object) r2)
            r10.putObject(r14, r12, r1)
        L_0x00d8:
            r6 = r6 | r22
            r12 = r32
            r13 = r34
            r3 = r8
            r2 = r9
            r1 = r11
            r11 = r35
            r9 = r5
            goto L_0x001c
        L_0x00e6:
            r9 = r2
            r11 = r3
            r8 = r19
            r19 = r5
            r5 = r36
            r12 = r32
            r13 = r5
            r33 = r7
            r7 = r4
            goto L_0x0375
        L_0x00f6:
            r9 = r2
            r11 = r3
            r8 = r19
            r19 = r5
            r5 = r36
            if (r0 != 0) goto L_0x0126
            r2 = r12
            r12 = r32
            int r13 = com.google.android.gms.internal.places.zzs.zzc(r12, r4, r5)
            long r0 = r5.zzea
            long r17 = com.google.android.gms.internal.places.zzai.zzb(r0)
            r0 = r10
            r1 = r31
            r33 = r13
            r13 = r5
            r4 = r17
            r0.putLong(r1, r2, r4)
            r6 = r6 | r22
            r0 = r33
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r13 = r34
            r11 = r35
            goto L_0x001c
        L_0x0126:
            r12 = r32
            r13 = r5
            r33 = r7
            r7 = r4
            goto L_0x0375
        L_0x012e:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r12 = r32
            r13 = r36
            r19 = r5
            if (r0 != 0) goto L_0x0154
            int r0 = com.google.android.gms.internal.places.zzs.zzb(r12, r4, r13)
            int r1 = r13.zzdz
            int r1 = com.google.android.gms.internal.places.zzai.zzm(r1)
            r10.putInt(r14, r2, r1)
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r13 = r34
            r11 = r35
            goto L_0x001c
        L_0x0154:
            r33 = r7
            r7 = r4
            goto L_0x0375
        L_0x0159:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r12 = r32
            r13 = r36
            r19 = r5
            if (r0 != 0) goto L_0x019e
            int r0 = com.google.android.gms.internal.places.zzs.zzb(r12, r4, r13)
            int r1 = r13.zzdz
            com.google.android.gms.internal.places.zzbf r4 = r15.zzah(r9)
            if (r4 == 0) goto L_0x018f
            boolean r4 = r4.zzad(r1)
            if (r4 == 0) goto L_0x0179
            goto L_0x018f
        L_0x0179:
            com.google.android.gms.internal.places.zzdr r2 = zzo(r31)
            long r3 = (long) r1
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            r2.zzc(r8, r1)
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r13 = r34
            r11 = r35
            goto L_0x001c
        L_0x018f:
            r10.putInt(r14, r2, r1)
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r13 = r34
            r11 = r35
            goto L_0x001c
        L_0x019e:
            r33 = r7
            r7 = r4
            goto L_0x0375
        L_0x01a3:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r12 = r32
            r13 = r36
            r19 = r5
            r1 = 2
            if (r0 != r1) goto L_0x01c6
            int r0 = com.google.android.gms.internal.places.zzs.zzf(r12, r4, r13)
            java.lang.Object r1 = r13.zzeb
            r10.putObject(r14, r2, r1)
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r13 = r34
            r11 = r35
            goto L_0x001c
        L_0x01c6:
            r33 = r7
            r7 = r4
            goto L_0x0375
        L_0x01cb:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r12 = r32
            r13 = r36
            r19 = r5
            r1 = 2
            if (r0 != r1) goto L_0x0207
            com.google.android.gms.internal.places.zzda r0 = r15.zzaf(r9)
            r5 = r34
            int r0 = com.google.android.gms.internal.places.zzs.zzb((com.google.android.gms.internal.places.zzda) r0, (byte[]) r12, (int) r4, (int) r5, (com.google.android.gms.internal.places.zzr) r13)
            r1 = r6 & r22
            if (r1 != 0) goto L_0x01ee
            java.lang.Object r1 = r13.zzeb
            r10.putObject(r14, r2, r1)
            goto L_0x01fc
        L_0x01ee:
            java.lang.Object r1 = r10.getObject(r14, r2)
            java.lang.Object r4 = r13.zzeb
            java.lang.Object r1 = com.google.android.gms.internal.places.zzbd.zzb((java.lang.Object) r1, (java.lang.Object) r4)
            r10.putObject(r14, r2, r1)
        L_0x01fc:
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r11 = r35
            r13 = r5
            goto L_0x001c
        L_0x0207:
            r5 = r34
            r33 = r7
            r7 = r4
            goto L_0x0375
        L_0x020e:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r12 = r32
            r13 = r36
            r19 = r5
            r5 = r34
            r1 = 2
            if (r0 != r1) goto L_0x023d
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r20 & r0
            if (r0 != 0) goto L_0x0229
            int r0 = com.google.android.gms.internal.places.zzs.zzd(r12, r4, r13)
            goto L_0x022d
        L_0x0229:
            int r0 = com.google.android.gms.internal.places.zzs.zze(r12, r4, r13)
        L_0x022d:
            java.lang.Object r1 = r13.zzeb
            r10.putObject(r14, r2, r1)
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r11 = r35
            r13 = r5
            goto L_0x001c
        L_0x023d:
            r33 = r7
            r7 = r4
            goto L_0x0375
        L_0x0242:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r12 = r32
            r13 = r36
            r19 = r5
            r5 = r34
            if (r0 != 0) goto L_0x0273
            int r0 = com.google.android.gms.internal.places.zzs.zzc(r12, r4, r13)
            r33 = r0
            long r0 = r13.zzea
            r20 = 0
            int r0 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1))
            if (r0 == 0) goto L_0x0261
            r0 = 1
            goto L_0x0263
        L_0x0261:
            r0 = r16
        L_0x0263:
            com.google.android.gms.internal.places.zzdy.zzb((java.lang.Object) r14, (long) r2, (boolean) r0)
            r6 = r6 | r22
            r0 = r33
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r11 = r35
            r13 = r5
            goto L_0x001c
        L_0x0273:
            r33 = r7
            r7 = r4
            goto L_0x0375
        L_0x0278:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r12 = r32
            r13 = r36
            r19 = r5
            r5 = r34
            if (r0 != r1) goto L_0x029b
            int r0 = com.google.android.gms.internal.places.zzs.zzb(r12, r4)
            r10.putInt(r14, r2, r0)
            int r0 = r4 + 4
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r11 = r35
            r13 = r5
            goto L_0x001c
        L_0x029b:
            r33 = r7
            r7 = r4
            goto L_0x0375
        L_0x02a0:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r12 = r32
            r13 = r36
            r19 = r5
            r5 = r34
            r1 = 1
            if (r0 != r1) goto L_0x02cf
            long r17 = com.google.android.gms.internal.places.zzs.zzc(r12, r4)
            r0 = r10
            r1 = r31
            r33 = r7
            r7 = r4
            r4 = r17
            r0.putLong(r1, r2, r4)
            int r0 = r7 + 8
            r6 = r6 | r22
            r7 = r33
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r13 = r34
            r11 = r35
            goto L_0x001c
        L_0x02cf:
            r33 = r7
            r7 = r4
            goto L_0x0375
        L_0x02d4:
            r9 = r2
            r11 = r3
            r33 = r7
            r2 = r12
            r8 = r19
            r12 = r32
            r13 = r36
            r7 = r4
            r19 = r5
            if (r0 != 0) goto L_0x0375
            int r0 = com.google.android.gms.internal.places.zzs.zzb(r12, r7, r13)
            int r1 = r13.zzdz
            r10.putInt(r14, r2, r1)
            r6 = r6 | r22
            r7 = r33
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r13 = r34
            r11 = r35
            goto L_0x001c
        L_0x02fb:
            r9 = r2
            r11 = r3
            r33 = r7
            r2 = r12
            r8 = r19
            r12 = r32
            r13 = r36
            r7 = r4
            r19 = r5
            if (r0 != 0) goto L_0x0375
            int r7 = com.google.android.gms.internal.places.zzs.zzc(r12, r7, r13)
            long r4 = r13.zzea
            r0 = r10
            r1 = r31
            r0.putLong(r1, r2, r4)
            r6 = r6 | r22
            r0 = r7
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r7 = r33
            r13 = r34
            r11 = r35
            goto L_0x001c
        L_0x0326:
            r9 = r2
            r11 = r3
            r33 = r7
            r2 = r12
            r8 = r19
            r12 = r32
            r13 = r36
            r7 = r4
            r19 = r5
            if (r0 != r1) goto L_0x0375
            float r0 = com.google.android.gms.internal.places.zzs.zze(r12, r7)
            com.google.android.gms.internal.places.zzdy.zzb((java.lang.Object) r14, (long) r2, (float) r0)
            int r0 = r7 + 4
            r6 = r6 | r22
            r7 = r33
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r13 = r34
            r11 = r35
            goto L_0x001c
        L_0x034d:
            r9 = r2
            r11 = r3
            r33 = r7
            r2 = r12
            r8 = r19
            r12 = r32
            r13 = r36
            r7 = r4
            r19 = r5
            r1 = 1
            if (r0 != r1) goto L_0x0375
            double r0 = com.google.android.gms.internal.places.zzs.zzd(r12, r7)
            com.google.android.gms.internal.places.zzdy.zzb((java.lang.Object) r14, (long) r2, (double) r0)
            int r0 = r7 + 8
            r6 = r6 | r22
            r7 = r33
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r13 = r34
            r11 = r35
            goto L_0x001c
        L_0x0375:
            r2 = r7
            r26 = r8
            r19 = r9
            r27 = r10
            r25 = r11
            r7 = r33
            goto L_0x04b8
        L_0x0382:
            r5 = r3
            r18 = r7
            r8 = r19
            r19 = -1
            r7 = r4
            r28 = r12
            r12 = r32
            r13 = r9
            r9 = r2
            r2 = r28
            r1 = 27
            if (r11 != r1) goto L_0x03ec
            r1 = 2
            if (r0 != r1) goto L_0x03de
            java.lang.Object r0 = r10.getObject(r14, r2)
            com.google.android.gms.internal.places.zzbh r0 = (com.google.android.gms.internal.places.zzbh) r0
            boolean r1 = r0.zzaa()
            if (r1 != 0) goto L_0x03ba
            int r1 = r0.size()
            if (r1 != 0) goto L_0x03af
            r1 = 10
            goto L_0x03b1
        L_0x03af:
            int r1 = r1 << 1
        L_0x03b1:
            com.google.android.gms.internal.places.zzbh r0 = r0.zzh(r1)
            r10.putObject(r14, r2, r0)
            r11 = r0
            goto L_0x03bb
        L_0x03ba:
            r11 = r0
        L_0x03bb:
            com.google.android.gms.internal.places.zzda r0 = r15.zzaf(r9)
            r1 = r8
            r2 = r32
            r3 = r7
            r4 = r34
            r7 = r5
            r5 = r11
            r22 = r6
            r6 = r36
            int r0 = com.google.android.gms.internal.places.zzs.zzb(r0, r1, r2, r3, r4, r5, r6)
            r11 = r35
            r1 = r7
            r3 = r8
            r2 = r9
            r9 = r13
            r7 = r18
            r6 = r22
            r13 = r34
            goto L_0x001c
        L_0x03de:
            r22 = r6
            r6 = r5
            r25 = r6
            r15 = r7
            r26 = r8
            r19 = r9
            r27 = r10
            goto L_0x048f
        L_0x03ec:
            r22 = r6
            r6 = r5
            r1 = 49
            if (r11 > r1) goto L_0x0441
            r5 = r20
            long r4 = (long) r5
            r1 = r0
            r0 = r30
            r33 = r1
            r1 = r31
            r23 = r2
            r2 = r32
            r3 = r7
            r20 = r4
            r4 = r34
            r5 = r8
            r25 = r6
            r15 = r7
            r7 = r33
            r26 = r8
            r8 = r9
            r19 = r9
            r27 = r10
            r9 = r20
            r12 = r23
            r14 = r36
            int r0 = r0.zzb(r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (int) r6, (int) r7, (int) r8, (long) r9, (int) r11, (long) r12, (com.google.android.gms.internal.places.zzr) r14)
            if (r0 != r15) goto L_0x0427
            r2 = r0
            r7 = r18
            r6 = r22
            goto L_0x04b8
        L_0x0427:
            r15 = r30
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            r9 = r36
            r7 = r18
            r2 = r19
            r6 = r22
            r1 = r25
            r3 = r26
            r10 = r27
            goto L_0x001c
        L_0x0441:
            r33 = r0
            r23 = r2
            r25 = r6
            r15 = r7
            r26 = r8
            r19 = r9
            r27 = r10
            r5 = r20
            r0 = 50
            if (r11 != r0) goto L_0x0495
            r7 = r33
            r0 = 2
            if (r7 != r0) goto L_0x048f
            r0 = r30
            r1 = r31
            r2 = r32
            r3 = r15
            r4 = r34
            r5 = r19
            r6 = r23
            r8 = r36
            int r0 = r0.zzb(r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (long) r6, (com.google.android.gms.internal.places.zzr) r8)
            if (r0 != r15) goto L_0x0475
            r2 = r0
            r7 = r18
            r6 = r22
            goto L_0x04b8
        L_0x0475:
            r15 = r30
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            r9 = r36
            r7 = r18
            r2 = r19
            r6 = r22
            r1 = r25
            r3 = r26
            r10 = r27
            goto L_0x001c
        L_0x048f:
            r2 = r15
            r7 = r18
            r6 = r22
            goto L_0x04b8
        L_0x0495:
            r7 = r33
            r0 = r30
            r1 = r31
            r2 = r32
            r3 = r15
            r4 = r34
            r8 = r5
            r5 = r26
            r6 = r25
            r9 = r11
            r10 = r23
            r12 = r19
            r13 = r36
            int r0 = r0.zzb(r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (int) r6, (int) r7, (int) r8, (int) r9, (long) r10, (int) r12, (com.google.android.gms.internal.places.zzr) r13)
            if (r0 != r15) goto L_0x0543
            r2 = r0
            r7 = r18
            r6 = r22
        L_0x04b8:
            r8 = r35
            r9 = r26
            if (r9 != r8) goto L_0x04c9
            if (r8 != 0) goto L_0x04c1
            goto L_0x04c9
        L_0x04c1:
            r10 = r30
            r13 = r31
            r0 = r7
            r7 = r2
            goto L_0x0570
        L_0x04c9:
            r10 = r30
            boolean r0 = r10.zzkx
            if (r0 == 0) goto L_0x051d
            r11 = r36
            com.google.android.gms.internal.places.zzap r0 = r11.zzec
            com.google.android.gms.internal.places.zzap r1 = com.google.android.gms.internal.places.zzap.zzao()
            if (r0 == r1) goto L_0x0518
            com.google.android.gms.internal.places.zzck r0 = r10.zzkw
            com.google.android.gms.internal.places.zzap r1 = r11.zzec
            r12 = r25
            com.google.android.gms.internal.places.zzbc$zzf r0 = r1.zzb(r0, r12)
            if (r0 != 0) goto L_0x0507
            com.google.android.gms.internal.places.zzdr r4 = zzo(r31)
            r0 = r9
            r1 = r32
            r3 = r34
            r5 = r36
            int r0 = com.google.android.gms.internal.places.zzs.zzb((int) r0, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.places.zzdr) r4, (com.google.android.gms.internal.places.zzr) r5)
            r14 = r31
            r13 = r34
            r3 = r9
            r15 = r10
            r9 = r11
            r1 = r12
            r2 = r19
            r10 = r27
            r12 = r32
            r11 = r8
            goto L_0x001c
        L_0x0507:
            r13 = r31
            r0 = r13
            com.google.android.gms.internal.places.zzbc$zzc r0 = (com.google.android.gms.internal.places.zzbc.zzc) r0
            r0.zzbm()
            com.google.android.gms.internal.places.zzav<java.lang.Object> r0 = r0.zzik
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        L_0x0518:
            r13 = r31
            r12 = r25
            goto L_0x0523
        L_0x051d:
            r13 = r31
            r11 = r36
            r12 = r25
        L_0x0523:
            com.google.android.gms.internal.places.zzdr r4 = zzo(r31)
            r0 = r9
            r1 = r32
            r3 = r34
            r5 = r36
            int r0 = com.google.android.gms.internal.places.zzs.zzb((int) r0, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.places.zzdr) r4, (com.google.android.gms.internal.places.zzr) r5)
            r3 = r9
            r15 = r10
            r9 = r11
            r1 = r12
            r14 = r13
            r2 = r19
            r10 = r27
            r12 = r32
            r13 = r34
            r11 = r8
            goto L_0x001c
        L_0x0543:
            r10 = r30
            r13 = r31
            r8 = r35
            r11 = r36
            r12 = r25
            r9 = r26
            r3 = r9
            r15 = r10
            r9 = r11
            r1 = r12
            r14 = r13
            r7 = r18
            r2 = r19
            r6 = r22
            r10 = r27
            r12 = r32
            r13 = r34
            r11 = r8
            goto L_0x001c
        L_0x0563:
            r22 = r6
            r18 = r7
            r27 = r10
            r8 = r11
            r13 = r14
            r10 = r15
            r7 = r0
            r9 = r3
            r0 = r18
        L_0x0570:
            r1 = -1
            if (r0 == r1) goto L_0x0579
            long r0 = (long) r0
            r2 = r27
            r2.putInt(r13, r0, r6)
        L_0x0579:
            r0 = 0
            int r1 = r10.zzlc
            r5 = r0
            r11 = r1
        L_0x057e:
            int r0 = r10.zzld
            if (r11 >= r0) goto L_0x05b7
            int[] r0 = r10.zzlb
            r1 = r0[r11]
            com.google.android.gms.internal.places.zzds<?, ?> r6 = r10.zzlg
            int[] r0 = r10.zzks
            r2 = r0[r1]
            int r0 = r10.zzai(r1)
            r0 = r0 & r17
            long r3 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.places.zzdy.zzp(r13, r3)
            if (r0 != 0) goto L_0x059e
            goto L_0x05b2
        L_0x059e:
            com.google.android.gms.internal.places.zzbf r4 = r10.zzah(r1)
            if (r4 != 0) goto L_0x05a5
            goto L_0x05b2
        L_0x05a5:
            com.google.android.gms.internal.places.zzcd r3 = r10.zzli
            java.util.Map r3 = r3.zzg(r0)
            r0 = r30
            java.lang.Object r5 = r0.zzb((int) r1, (int) r2, r3, (com.google.android.gms.internal.places.zzbf) r4, r5, r6)
        L_0x05b2:
            com.google.android.gms.internal.places.zzdr r5 = (com.google.android.gms.internal.places.zzdr) r5
            int r11 = r11 + 1
            goto L_0x057e
        L_0x05b7:
            if (r5 == 0) goto L_0x05be
            com.google.android.gms.internal.places.zzds<?, ?> r0 = r10.zzlg
            r0.zzg(r13, r5)
        L_0x05be:
            if (r8 != 0) goto L_0x05ca
            r0 = r34
            if (r7 != r0) goto L_0x05c5
            goto L_0x05d0
        L_0x05c5:
            com.google.android.gms.internal.places.zzbk r0 = com.google.android.gms.internal.places.zzbk.zzbt()
            throw r0
        L_0x05ca:
            r0 = r34
            if (r7 > r0) goto L_0x05d1
            if (r9 != r8) goto L_0x05d1
        L_0x05d0:
            return r7
        L_0x05d1:
            com.google.android.gms.internal.places.zzbk r0 = com.google.android.gms.internal.places.zzbk.zzbt()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.places.zzco.zzb(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.places.zzr):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v2, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(T r28, byte[] r29, int r30, int r31, com.google.android.gms.internal.places.zzr r32) throws java.io.IOException {
        /*
            r27 = this;
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            boolean r0 = r15.zzkz
            if (r0 == 0) goto L_0x0373
            sun.misc.Unsafe r9 = zzkr
            r10 = -1
            r16 = 0
            r0 = r30
            r1 = r10
            r2 = r16
        L_0x001a:
            if (r0 >= r13) goto L_0x0369
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x002c
            int r0 = com.google.android.gms.internal.places.zzs.zzb((int) r0, (byte[]) r12, (int) r3, (com.google.android.gms.internal.places.zzr) r11)
            int r3 = r11.zzdz
            r8 = r0
            r17 = r3
            goto L_0x002f
        L_0x002c:
            r17 = r0
            r8 = r3
        L_0x002f:
            int r7 = r17 >>> 3
            r6 = r17 & 7
            if (r7 <= r1) goto L_0x003d
            int r2 = r2 / 3
            int r0 = r15.zzq(r7, r2)
            r4 = r0
            goto L_0x0042
        L_0x003d:
            int r0 = r15.zzak(r7)
            r4 = r0
        L_0x0042:
            if (r4 != r10) goto L_0x0050
            r24 = r7
            r2 = r8
            r18 = r9
            r26 = r10
            r19 = r16
            goto L_0x0330
        L_0x0050:
            int[] r0 = r15.zzks
            int r1 = r4 + 1
            r5 = r0[r1]
            r0 = 267386880(0xff00000, float:2.3665827E-29)
            r0 = r0 & r5
            int r3 = r0 >>> 20
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r1 = (long) r0
            r0 = 17
            r10 = 2
            if (r3 > r0) goto L_0x0233
            r0 = 1
            switch(r3) {
                case 0: goto L_0x0216;
                case 1: goto L_0x01f8;
                case 2: goto L_0x01d7;
                case 3: goto L_0x01d7;
                case 4: goto L_0x01ba;
                case 5: goto L_0x0198;
                case 6: goto L_0x017b;
                case 7: goto L_0x0155;
                case 8: goto L_0x012f;
                case 9: goto L_0x00fd;
                case 10: goto L_0x00e1;
                case 11: goto L_0x01ba;
                case 12: goto L_0x00c4;
                case 13: goto L_0x017b;
                case 14: goto L_0x0198;
                case 15: goto L_0x00a3;
                case 16: goto L_0x0079;
                default: goto L_0x006d;
            }
        L_0x006d:
            r10 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x0310
        L_0x0079:
            if (r6 != 0) goto L_0x0097
            int r6 = com.google.android.gms.internal.places.zzs.zzc(r12, r8, r11)
            r19 = r1
            long r0 = r11.zzea
            long r21 = com.google.android.gms.internal.places.zzai.zzb(r0)
            r0 = r9
            r2 = r19
            r1 = r28
            r10 = r4
            r4 = r21
            r0.putLong(r1, r2, r4)
            r0 = r6
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x001a
        L_0x0097:
            r10 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x0310
        L_0x00a3:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x00b9
            int r0 = com.google.android.gms.internal.places.zzs.zzb(r12, r8, r11)
            int r1 = r11.zzdz
            int r1 = com.google.android.gms.internal.places.zzai.zzm(r1)
            r9.putInt(r14, r2, r1)
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x001a
        L_0x00b9:
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x0310
        L_0x00c4:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x00d6
            int r0 = com.google.android.gms.internal.places.zzs.zzb(r12, r8, r11)
            int r1 = r11.zzdz
            r9.putInt(r14, r2, r1)
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x001a
        L_0x00d6:
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x0310
        L_0x00e1:
            r2 = r1
            if (r6 != r10) goto L_0x00f2
            int r0 = com.google.android.gms.internal.places.zzs.zzf(r12, r8, r11)
            java.lang.Object r1 = r11.zzeb
            r9.putObject(r14, r2, r1)
            r2 = r4
            r1 = r7
            r10 = -1
            goto L_0x001a
        L_0x00f2:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x0310
        L_0x00fd:
            r2 = r1
            if (r6 != r10) goto L_0x0124
            com.google.android.gms.internal.places.zzda r0 = r15.zzaf(r4)
            int r0 = com.google.android.gms.internal.places.zzs.zzb((com.google.android.gms.internal.places.zzda) r0, (byte[]) r12, (int) r8, (int) r13, (com.google.android.gms.internal.places.zzr) r11)
            java.lang.Object r1 = r9.getObject(r14, r2)
            if (r1 != 0) goto L_0x0115
            java.lang.Object r1 = r11.zzeb
            r9.putObject(r14, r2, r1)
            goto L_0x011f
        L_0x0115:
            java.lang.Object r5 = r11.zzeb
            java.lang.Object r1 = com.google.android.gms.internal.places.zzbd.zzb((java.lang.Object) r1, (java.lang.Object) r5)
            r9.putObject(r14, r2, r1)
        L_0x011f:
            r2 = r4
            r1 = r7
            r10 = -1
            goto L_0x001a
        L_0x0124:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x0310
        L_0x012f:
            r2 = r1
            if (r6 != r10) goto L_0x014a
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r5
            if (r0 != 0) goto L_0x013c
            int r0 = com.google.android.gms.internal.places.zzs.zzd(r12, r8, r11)
            goto L_0x0140
        L_0x013c:
            int r0 = com.google.android.gms.internal.places.zzs.zze(r12, r8, r11)
        L_0x0140:
            java.lang.Object r1 = r11.zzeb
            r9.putObject(r14, r2, r1)
            r2 = r4
            r1 = r7
            r10 = -1
            goto L_0x001a
        L_0x014a:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x0310
        L_0x0155:
            r2 = r1
            if (r6 != 0) goto L_0x0170
            int r1 = com.google.android.gms.internal.places.zzs.zzc(r12, r8, r11)
            long r5 = r11.zzea
            r19 = 0
            int r5 = (r5 > r19 ? 1 : (r5 == r19 ? 0 : -1))
            if (r5 == 0) goto L_0x0165
            goto L_0x0167
        L_0x0165:
            r0 = r16
        L_0x0167:
            com.google.android.gms.internal.places.zzdy.zzb((java.lang.Object) r14, (long) r2, (boolean) r0)
            r0 = r1
            r2 = r4
            r1 = r7
            r10 = -1
            goto L_0x001a
        L_0x0170:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x0310
        L_0x017b:
            r2 = r1
            r0 = 5
            if (r6 != r0) goto L_0x018d
            int r0 = com.google.android.gms.internal.places.zzs.zzb(r12, r8)
            r9.putInt(r14, r2, r0)
            int r0 = r8 + 4
            r2 = r4
            r1 = r7
            r10 = -1
            goto L_0x001a
        L_0x018d:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x0310
        L_0x0198:
            r2 = r1
            if (r6 != r0) goto L_0x01ae
            long r5 = com.google.android.gms.internal.places.zzs.zzc(r12, r8)
            r0 = r9
            r1 = r28
            r10 = r4
            r4 = r5
            r0.putLong(r1, r2, r4)
            int r0 = r8 + 8
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x001a
        L_0x01ae:
            r10 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x0310
        L_0x01ba:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x01cc
            int r0 = com.google.android.gms.internal.places.zzs.zzb(r12, r8, r11)
            int r1 = r11.zzdz
            r9.putInt(r14, r2, r1)
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x001a
        L_0x01cc:
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x0310
        L_0x01d7:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x01ed
            int r6 = com.google.android.gms.internal.places.zzs.zzc(r12, r8, r11)
            long r4 = r11.zzea
            r0 = r9
            r1 = r28
            r0.putLong(r1, r2, r4)
            r0 = r6
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x001a
        L_0x01ed:
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x0310
        L_0x01f8:
            r2 = r1
            r10 = r4
            r0 = 5
            if (r6 != r0) goto L_0x020b
            float r0 = com.google.android.gms.internal.places.zzs.zze(r12, r8)
            com.google.android.gms.internal.places.zzdy.zzb((java.lang.Object) r14, (long) r2, (float) r0)
            int r0 = r8 + 4
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x001a
        L_0x020b:
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x0310
        L_0x0216:
            r2 = r1
            r10 = r4
            if (r6 != r0) goto L_0x0228
            double r0 = com.google.android.gms.internal.places.zzs.zzd(r12, r8)
            com.google.android.gms.internal.places.zzdy.zzb((java.lang.Object) r14, (long) r2, (double) r0)
            int r0 = r8 + 8
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x001a
        L_0x0228:
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            r26 = -1
            goto L_0x0310
        L_0x0233:
            r0 = 27
            if (r3 != r0) goto L_0x0280
            if (r6 != r10) goto L_0x0275
            java.lang.Object r0 = r9.getObject(r14, r1)
            com.google.android.gms.internal.places.zzbh r0 = (com.google.android.gms.internal.places.zzbh) r0
            boolean r3 = r0.zzaa()
            if (r3 != 0) goto L_0x025a
            int r3 = r0.size()
            if (r3 != 0) goto L_0x024f
            r3 = 10
            goto L_0x0251
        L_0x024f:
            int r3 = r3 << 1
        L_0x0251:
            com.google.android.gms.internal.places.zzbh r0 = r0.zzh(r3)
            r9.putObject(r14, r1, r0)
            r5 = r0
            goto L_0x025b
        L_0x025a:
            r5 = r0
        L_0x025b:
            com.google.android.gms.internal.places.zzda r0 = r15.zzaf(r4)
            r1 = r17
            r2 = r29
            r3 = r8
            r19 = r4
            r4 = r31
            r6 = r32
            int r0 = com.google.android.gms.internal.places.zzs.zzb(r0, r1, r2, r3, r4, r5, r6)
            r1 = r7
            r2 = r19
            r10 = -1
            goto L_0x001a
        L_0x0275:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            goto L_0x0310
        L_0x0280:
            r19 = r4
            r0 = 49
            if (r3 > r0) goto L_0x02cd
            long r4 = (long) r5
            r0 = r27
            r20 = r1
            r1 = r28
            r2 = r29
            r10 = r3
            r3 = r8
            r22 = r4
            r4 = r31
            r5 = r17
            r30 = r6
            r6 = r7
            r24 = r7
            r7 = r30
            r15 = r8
            r8 = r19
            r18 = r9
            r25 = r10
            r26 = -1
            r9 = r22
            r11 = r25
            r12 = r20
            r14 = r32
            int r0 = r0.zzb(r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (int) r6, (int) r7, (int) r8, (long) r9, (int) r11, (long) r12, (com.google.android.gms.internal.places.zzr) r14)
            if (r0 != r15) goto L_0x02b9
            r2 = r0
            goto L_0x0330
        L_0x02b9:
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            r9 = r18
            r2 = r19
            r1 = r24
            r10 = r26
            goto L_0x001a
        L_0x02cd:
            r20 = r1
            r25 = r3
            r30 = r6
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            r0 = 50
            r9 = r25
            if (r9 != r0) goto L_0x0312
            r7 = r30
            if (r7 != r10) goto L_0x0310
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r15
            r4 = r31
            r5 = r19
            r6 = r20
            r8 = r32
            int r0 = r0.zzb(r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (long) r6, (com.google.android.gms.internal.places.zzr) r8)
            if (r0 != r15) goto L_0x02fc
            r2 = r0
            goto L_0x0330
        L_0x02fc:
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            r9 = r18
            r2 = r19
            r1 = r24
            r10 = r26
            goto L_0x001a
        L_0x0310:
            r2 = r15
            goto L_0x0330
        L_0x0312:
            r7 = r30
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r15
            r4 = r31
            r8 = r5
            r5 = r17
            r6 = r24
            r10 = r20
            r12 = r19
            r13 = r32
            int r0 = r0.zzb(r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (int) r6, (int) r7, (int) r8, (int) r9, (long) r10, (int) r12, (com.google.android.gms.internal.places.zzr) r13)
            if (r0 != r15) goto L_0x0355
            r2 = r0
        L_0x0330:
            com.google.android.gms.internal.places.zzdr r4 = zzo(r28)
            r0 = r17
            r1 = r29
            r3 = r31
            r5 = r32
            int r0 = com.google.android.gms.internal.places.zzs.zzb((int) r0, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.places.zzdr) r4, (com.google.android.gms.internal.places.zzr) r5)
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            r9 = r18
            r2 = r19
            r1 = r24
            r10 = r26
            goto L_0x001a
        L_0x0355:
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            r9 = r18
            r2 = r19
            r1 = r24
            r10 = r26
            goto L_0x001a
        L_0x0369:
            r4 = r31
            if (r0 != r4) goto L_0x036e
            return
        L_0x036e:
            com.google.android.gms.internal.places.zzbk r0 = com.google.android.gms.internal.places.zzbk.zzbt()
            throw r0
        L_0x0373:
            r4 = r13
            r5 = 0
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r30
            r4 = r31
            r6 = r32
            r0.zzb(r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (com.google.android.gms.internal.places.zzr) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.places.zzco.zzb(java.lang.Object, byte[], int, int, com.google.android.gms.internal.places.zzr):void");
    }

    public final void zzd(T t) {
        int i;
        int i2 = this.zzlc;
        while (true) {
            i = this.zzld;
            if (i2 >= i) {
                break;
            }
            long zzai = (long) (zzai(this.zzlb[i2]) & 1048575);
            Object zzp = zzdy.zzp(t, zzai);
            if (zzp != null) {
                zzdy.zzb((Object) t, zzai, this.zzli.zzj(zzp));
            }
            i2++;
        }
        int length = this.zzlb.length;
        while (i < length) {
            this.zzlf.zzb(t, (long) this.zzlb[i]);
            i++;
        }
        this.zzlg.zzd(t);
        if (this.zzkx) {
            this.zzlh.zzd(t);
        }
    }

    private final <K, V, UT, UB> UB zzb(int i, int i2, Map<K, V> map, zzbf zzbf, UB ub, zzds<UT, UB> zzds) {
        zzcb<?, ?> zzl = this.zzli.zzl(zzag(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (!zzbf.zzad(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzds.zzdk();
                }
                zzae zzk = zzw.zzk(zzcc.zzb(zzl, next.getKey(), next.getValue()));
                try {
                    zzcc.zzb(zzk.zzai(), zzl, next.getKey(), next.getValue());
                    zzds.zzb(ub, i2, zzk.zzah());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    public final boolean zzp(T t) {
        int i;
        int i2 = -1;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            boolean z = true;
            if (i3 >= this.zzlc) {
                return !this.zzkx || this.zzlh.zzb((Object) t).isInitialized();
            }
            int i5 = this.zzlb[i3];
            int i6 = this.zzks[i5];
            int zzai = zzai(i5);
            if (!this.zzkz) {
                int i7 = this.zzks[i5 + 2];
                int i8 = i7 & 1048575;
                i = 1 << (i7 >>> 20);
                if (i8 != i2) {
                    i4 = zzkr.getInt(t, (long) i8);
                    i2 = i8;
                }
            } else {
                i = 0;
            }
            if (((268435456 & zzai) != 0) && !zzb(t, i5, i4, i)) {
                return false;
            }
            switch ((267386880 & zzai) >>> 20) {
                case 9:
                case 17:
                    if (zzb(t, i5, i4, i) && !zzb((Object) t, zzai, zzaf(i5))) {
                        return false;
                    }
                case 27:
                case 49:
                    List list = (List) zzdy.zzp(t, (long) (zzai & 1048575));
                    if (!list.isEmpty()) {
                        zzda zzaf = zzaf(i5);
                        int i9 = 0;
                        while (true) {
                            if (i9 < list.size()) {
                                if (!zzaf.zzp(list.get(i9))) {
                                    z = false;
                                } else {
                                    i9++;
                                }
                            }
                        }
                    }
                    if (z) {
                        break;
                    } else {
                        return false;
                    }
                case 50:
                    Map<?, ?> zzh = this.zzli.zzh(zzdy.zzp(t, (long) (zzai & 1048575)));
                    if (!zzh.isEmpty()) {
                        if (this.zzli.zzl(zzag(i5)).zzkl.zzdr() == zzem.MESSAGE) {
                            zzda<?> zzda = null;
                            Iterator<?> it = zzh.values().iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    Object next = it.next();
                                    if (zzda == null) {
                                        zzda = zzcv.zzcq().zzf(next.getClass());
                                    }
                                    if (!zzda.zzp(next)) {
                                        z = false;
                                    }
                                }
                            }
                        }
                    }
                    if (z) {
                        break;
                    } else {
                        return false;
                    }
                case 60:
                case 68:
                    if (zzb(t, i6, i5) && !zzb((Object) t, zzai, zzaf(i5))) {
                        return false;
                    }
            }
            i3++;
        }
    }

    private static boolean zzb(Object obj, int i, zzda zzda) {
        return zzda.zzp(zzdy.zzp(obj, (long) (i & 1048575)));
    }

    private static void zzb(int i, Object obj, zzel zzel) throws IOException {
        if (obj instanceof String) {
            zzel.zzb(i, (String) obj);
        } else {
            zzel.zzb(i, (zzw) obj);
        }
    }

    private final int zzai(int i) {
        return this.zzks[i + 1];
    }

    private final int zzaj(int i) {
        return this.zzks[i + 2];
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zzdy.zzp(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zzdy.zzp(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zzdy.zzp(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zzdy.zzp(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zzdy.zzp(t, j)).booleanValue();
    }

    private final boolean zzd(T t, T t2, int i) {
        return zzb(t, i) == zzb(t2, i);
    }

    private final boolean zzb(T t, int i, int i2, int i3) {
        if (this.zzkz) {
            return zzb(t, i);
        }
        return (i2 & i3) != 0;
    }

    private final boolean zzb(T t, int i) {
        if (this.zzkz) {
            int zzai = zzai(i);
            long j = (long) (zzai & 1048575);
            switch ((zzai & 267386880) >>> 20) {
                case 0:
                    return zzdy.zzo(t, j) != 0.0d;
                case 1:
                    return zzdy.zzn(t, j) != 0.0f;
                case 2:
                    return zzdy.zzl(t, j) != 0;
                case 3:
                    return zzdy.zzl(t, j) != 0;
                case 4:
                    return zzdy.zzk(t, j) != 0;
                case 5:
                    return zzdy.zzl(t, j) != 0;
                case 6:
                    return zzdy.zzk(t, j) != 0;
                case 7:
                    return zzdy.zzm(t, j);
                case 8:
                    Object zzp = zzdy.zzp(t, j);
                    if (zzp instanceof String) {
                        return !((String) zzp).isEmpty();
                    }
                    if (zzp instanceof zzw) {
                        return !zzw.zzeg.equals(zzp);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzdy.zzp(t, j) != null;
                case 10:
                    return !zzw.zzeg.equals(zzdy.zzp(t, j));
                case 11:
                    return zzdy.zzk(t, j) != 0;
                case 12:
                    return zzdy.zzk(t, j) != 0;
                case 13:
                    return zzdy.zzk(t, j) != 0;
                case 14:
                    return zzdy.zzl(t, j) != 0;
                case 15:
                    return zzdy.zzk(t, j) != 0;
                case 16:
                    return zzdy.zzl(t, j) != 0;
                case 17:
                    return zzdy.zzp(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            int zzaj = zzaj(i);
            return (zzdy.zzk(t, (long) (zzaj & 1048575)) & (1 << (zzaj >>> 20))) != 0;
        }
    }

    private final void zzc(T t, int i) {
        if (!this.zzkz) {
            int zzaj = zzaj(i);
            long j = (long) (zzaj & 1048575);
            zzdy.zzb((Object) t, j, zzdy.zzk(t, j) | (1 << (zzaj >>> 20)));
        }
    }

    private final boolean zzb(T t, int i, int i2) {
        return zzdy.zzk(t, (long) (zzaj(i2) & 1048575)) == i;
    }

    private final void zzc(T t, int i, int i2) {
        zzdy.zzb((Object) t, (long) (zzaj(i2) & 1048575), i);
    }

    private final int zzak(int i) {
        if (i < this.zzku || i > this.zzkv) {
            return -1;
        }
        return zzr(i, 0);
    }

    private final int zzq(int i, int i2) {
        if (i < this.zzku || i > this.zzkv) {
            return -1;
        }
        return zzr(i, i2);
    }

    private final int zzr(int i, int i2) {
        int length = (this.zzks.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzks[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }
}
