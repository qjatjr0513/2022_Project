package com.google.android.gms.internal.places;

final class zzed extends zzec {
    zzed() {
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0067, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0093, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzc(int r18, byte[] r19, int r20, int r21) {
        /*
            r17 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r1 | r2
            int r4 = r0.length
            int r4 = r4 - r2
            r3 = r3 | r4
            r4 = 2
            r5 = 3
            r6 = 0
            if (r3 < 0) goto L_0x00c2
            long r7 = (long) r1
            long r1 = (long) r2
            long r1 = r1 - r7
            int r1 = (int) r1
            r2 = 16
            r9 = 1
            if (r1 >= r2) goto L_0x001d
            r2 = r6
            goto L_0x002f
        L_0x001d:
            r2 = r6
            r11 = r7
        L_0x001f:
            if (r2 >= r1) goto L_0x002e
            long r13 = r11 + r9
            byte r3 = com.google.android.gms.internal.places.zzdy.zzb(r0, r11)
            if (r3 >= 0) goto L_0x002a
            goto L_0x002f
        L_0x002a:
            int r2 = r2 + 1
            r11 = r13
            goto L_0x001f
        L_0x002e:
            r2 = r1
        L_0x002f:
            int r1 = r1 - r2
            long r2 = (long) r2
            long r7 = r7 + r2
        L_0x0033:
            r2 = r6
        L_0x0034:
            if (r1 <= 0) goto L_0x0047
            long r2 = r7 + r9
            byte r7 = com.google.android.gms.internal.places.zzdy.zzb(r0, r7)
            if (r7 < 0) goto L_0x0044
            int r1 = r1 + -1
            r15 = r2
            r2 = r7
            r7 = r15
            goto L_0x0034
        L_0x0044:
            r15 = r2
            r2 = r7
            r7 = r15
        L_0x0047:
            if (r1 != 0) goto L_0x004a
            return r6
        L_0x004a:
            int r1 = r1 + -1
            r3 = -32
            r11 = -65
            r12 = -1
            if (r2 >= r3) goto L_0x0068
            if (r1 != 0) goto L_0x0056
            return r2
        L_0x0056:
            int r1 = r1 + -1
            r3 = -62
            if (r2 < r3) goto L_0x0067
            long r2 = r7 + r9
            byte r7 = com.google.android.gms.internal.places.zzdy.zzb(r0, r7)
            if (r7 <= r11) goto L_0x0065
            goto L_0x0067
        L_0x0065:
            r7 = r2
            goto L_0x00bf
        L_0x0067:
            return r12
        L_0x0068:
            r13 = -16
            if (r2 >= r13) goto L_0x0094
            if (r1 >= r4) goto L_0x0073
            int r0 = zzb(r0, r2, r7, r1)
            return r0
        L_0x0073:
            int r1 = r1 + -2
            long r13 = r7 + r9
            byte r7 = com.google.android.gms.internal.places.zzdy.zzb(r0, r7)
            if (r7 > r11) goto L_0x0093
            r8 = -96
            if (r2 != r3) goto L_0x0083
            if (r7 < r8) goto L_0x0093
        L_0x0083:
            r3 = -19
            if (r2 != r3) goto L_0x0089
            if (r7 >= r8) goto L_0x0093
        L_0x0089:
            long r7 = r13 + r9
            byte r2 = com.google.android.gms.internal.places.zzdy.zzb(r0, r13)
            if (r2 <= r11) goto L_0x0092
            goto L_0x0093
        L_0x0092:
            goto L_0x0033
        L_0x0093:
            return r12
        L_0x0094:
            if (r1 >= r5) goto L_0x009b
            int r0 = zzb(r0, r2, r7, r1)
            return r0
        L_0x009b:
            int r1 = r1 + -3
            long r13 = r7 + r9
            byte r3 = com.google.android.gms.internal.places.zzdy.zzb(r0, r7)
            if (r3 > r11) goto L_0x00c1
            int r2 = r2 << 28
            int r3 = r3 + 112
            int r2 = r2 + r3
            int r2 = r2 >> 30
            if (r2 != 0) goto L_0x00c1
            long r2 = r13 + r9
            byte r7 = com.google.android.gms.internal.places.zzdy.zzb(r0, r13)
            if (r7 > r11) goto L_0x00c1
            long r7 = r2 + r9
            byte r2 = com.google.android.gms.internal.places.zzdy.zzb(r0, r2)
            if (r2 <= r11) goto L_0x00bf
            goto L_0x00c1
        L_0x00bf:
            goto L_0x0033
        L_0x00c1:
            return r12
        L_0x00c2:
            java.lang.ArrayIndexOutOfBoundsException r3 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.Object[] r5 = new java.lang.Object[r5]
            int r0 = r0.length
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r5[r6] = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r20)
            r1 = 1
            r5[r1] = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r21)
            r5[r4] = r0
            java.lang.String r0 = "Array length=%d, index=%d, limit=%d"
            java.lang.String r0 = java.lang.String.format(r0, r5)
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.places.zzed.zzc(int, byte[], int, int):int");
    }

    /* access modifiers changed from: package-private */
    public final String zzh(byte[] bArr, int i, int i2) throws zzbk {
        if ((i | i2 | ((bArr.length - i) - i2)) >= 0) {
            int i3 = i + i2;
            char[] cArr = new char[i2];
            int i4 = 0;
            while (r13 < i3) {
                byte zzb = zzdy.zzb(bArr, (long) r13);
                if (!zzdz.zze(zzb)) {
                    break;
                }
                i = r13 + 1;
                zzdz.zzb(zzb, cArr, i4);
                i4++;
            }
            int i5 = i4;
            while (r13 < i3) {
                int i6 = r13 + 1;
                byte zzb2 = zzdy.zzb(bArr, (long) r13);
                if (zzdz.zze(zzb2)) {
                    int i7 = i5 + 1;
                    zzdz.zzb(zzb2, cArr, i5);
                    while (i6 < i3) {
                        byte zzb3 = zzdy.zzb(bArr, (long) i6);
                        if (!zzdz.zze(zzb3)) {
                            break;
                        }
                        i6++;
                        zzdz.zzb(zzb3, cArr, i7);
                        i7++;
                    }
                    r13 = i6;
                    i5 = i7;
                } else if (zzdz.zzf(zzb2)) {
                    if (i6 < i3) {
                        zzdz.zzb(zzb2, zzdy.zzb(bArr, (long) i6), cArr, i5);
                        r13 = i6 + 1;
                        i5++;
                    } else {
                        throw zzbk.zzbu();
                    }
                } else if (zzdz.zzg(zzb2)) {
                    if (i6 < i3 - 1) {
                        int i8 = i6 + 1;
                        zzdz.zzb(zzb2, zzdy.zzb(bArr, (long) i6), zzdy.zzb(bArr, (long) i8), cArr, i5);
                        r13 = i8 + 1;
                        i5++;
                    } else {
                        throw zzbk.zzbu();
                    }
                } else if (i6 < i3 - 2) {
                    int i9 = i6 + 1;
                    byte zzb4 = zzdy.zzb(bArr, (long) i6);
                    int i10 = i9 + 1;
                    zzdz.zzb(zzb2, zzb4, zzdy.zzb(bArr, (long) i9), zzdy.zzb(bArr, (long) i10), cArr, i5);
                    r13 = i10 + 1;
                    i5 = i5 + 1 + 1;
                } else {
                    throw zzbk.zzbu();
                }
            }
            return new String(cArr, 0, i5);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0033 A[LOOP:1: B:13:0x0033->B:37:0x00fc, LOOP_START, PHI: r2 r3 r4 r11 
      PHI: (r2v4 int) = (r2v3 int), (r2v6 int) binds: [B:10:0x002f, B:37:0x00fc] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r3v4 char) = (r3v3 char), (r3v5 char) binds: [B:10:0x002f, B:37:0x00fc] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r4v3 long) = (r4v2 long), (r4v5 long) binds: [B:10:0x002f, B:37:0x00fc] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r11v3 long) = (r11v2 long), (r11v5 long) binds: [B:10:0x002f, B:37:0x00fc] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzc(java.lang.CharSequence r22, byte[] r23, int r24, int r25) {
        /*
            r21 = this;
            r0 = r22
            r1 = r23
            r2 = r24
            r3 = r25
            long r4 = (long) r2
            long r6 = (long) r3
            long r6 = r6 + r4
            int r8 = r22.length()
            java.lang.String r9 = " at index "
            java.lang.String r10 = "Failed writing "
            if (r8 > r3) goto L_0x014a
            int r11 = r1.length
            int r11 = r11 - r3
            if (r11 < r2) goto L_0x014a
            r2 = 0
        L_0x001a:
            r3 = 128(0x80, float:1.794E-43)
            r11 = 1
            if (r2 >= r8) goto L_0x002f
            char r13 = r0.charAt(r2)
            if (r13 >= r3) goto L_0x002f
            long r11 = r11 + r4
            byte r3 = (byte) r13
            com.google.android.gms.internal.places.zzdy.zzb((byte[]) r1, (long) r4, (byte) r3)
            int r2 = r2 + 1
            r4 = r11
            goto L_0x001a
        L_0x002f:
            if (r2 != r8) goto L_0x0033
            int r0 = (int) r4
            return r0
        L_0x0033:
            if (r2 >= r8) goto L_0x0148
            char r13 = r0.charAt(r2)
            if (r13 >= r3) goto L_0x004a
            int r14 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r14 >= 0) goto L_0x004a
            long r14 = r4 + r11
            byte r13 = (byte) r13
            com.google.android.gms.internal.places.zzdy.zzb((byte[]) r1, (long) r4, (byte) r13)
            r4 = r11
            r12 = r14
            r11 = r3
            goto L_0x00fc
        L_0x004a:
            r14 = 2048(0x800, float:2.87E-42)
            if (r13 >= r14) goto L_0x0074
            r14 = 2
            long r14 = r6 - r14
            int r14 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
            if (r14 > 0) goto L_0x0074
            long r14 = r4 + r11
            int r3 = r13 >>> 6
            r3 = r3 | 960(0x3c0, float:1.345E-42)
            byte r3 = (byte) r3
            com.google.android.gms.internal.places.zzdy.zzb((byte[]) r1, (long) r4, (byte) r3)
            long r3 = r14 + r11
            r5 = r13 & 63
            r13 = 128(0x80, float:1.794E-43)
            r5 = r5 | r13
            byte r5 = (byte) r5
            com.google.android.gms.internal.places.zzdy.zzb((byte[]) r1, (long) r14, (byte) r5)
            r19 = r11
            r11 = 128(0x80, float:1.794E-43)
            r12 = r3
            r4 = r19
            goto L_0x00fc
        L_0x0074:
            r3 = 57343(0xdfff, float:8.0355E-41)
            r14 = 55296(0xd800, float:7.7486E-41)
            if (r13 < r14) goto L_0x007e
            if (r3 >= r13) goto L_0x00af
        L_0x007e:
            r15 = 3
            long r15 = r6 - r15
            int r15 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r15 > 0) goto L_0x00af
            long r14 = r4 + r11
            int r3 = r13 >>> 12
            r3 = r3 | 480(0x1e0, float:6.73E-43)
            byte r3 = (byte) r3
            com.google.android.gms.internal.places.zzdy.zzb((byte[]) r1, (long) r4, (byte) r3)
            long r3 = r14 + r11
            int r5 = r13 >>> 6
            r5 = r5 & 63
            r11 = 128(0x80, float:1.794E-43)
            r5 = r5 | r11
            byte r5 = (byte) r5
            com.google.android.gms.internal.places.zzdy.zzb((byte[]) r1, (long) r14, (byte) r5)
            r14 = 1
            long r17 = r3 + r14
            r5 = r13 & 63
            r5 = r5 | r11
            byte r5 = (byte) r5
            com.google.android.gms.internal.places.zzdy.zzb((byte[]) r1, (long) r3, (byte) r5)
            r12 = r17
            r4 = 1
            r11 = 128(0x80, float:1.794E-43)
            goto L_0x00fc
        L_0x00af:
            r11 = 4
            long r11 = r6 - r11
            int r11 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r11 > 0) goto L_0x010f
            int r3 = r2 + 1
            if (r3 == r8) goto L_0x0107
            char r2 = r0.charAt(r3)
            boolean r11 = java.lang.Character.isSurrogatePair(r13, r2)
            if (r11 == 0) goto L_0x0106
            int r2 = java.lang.Character.toCodePoint(r13, r2)
            r11 = 1
            long r13 = r4 + r11
            int r15 = r2 >>> 18
            r15 = r15 | 240(0xf0, float:3.36E-43)
            byte r15 = (byte) r15
            com.google.android.gms.internal.places.zzdy.zzb((byte[]) r1, (long) r4, (byte) r15)
            long r4 = r13 + r11
            int r15 = r2 >>> 12
            r15 = r15 & 63
            r11 = 128(0x80, float:1.794E-43)
            r12 = r15 | 128(0x80, float:1.794E-43)
            byte r12 = (byte) r12
            com.google.android.gms.internal.places.zzdy.zzb((byte[]) r1, (long) r13, (byte) r12)
            r12 = 1
            long r14 = r4 + r12
            int r16 = r2 >>> 6
            r12 = r16 & 63
            r12 = r12 | r11
            byte r12 = (byte) r12
            com.google.android.gms.internal.places.zzdy.zzb((byte[]) r1, (long) r4, (byte) r12)
            r4 = 1
            long r12 = r14 + r4
            r2 = r2 & 63
            r2 = r2 | r11
            byte r2 = (byte) r2
            com.google.android.gms.internal.places.zzdy.zzb((byte[]) r1, (long) r14, (byte) r2)
            r2 = r3
        L_0x00fc:
            int r2 = r2 + 1
            r3 = r11
            r19 = r4
            r4 = r12
            r11 = r19
            goto L_0x0033
        L_0x0106:
            r2 = r3
        L_0x0107:
            com.google.android.gms.internal.places.zzee r0 = new com.google.android.gms.internal.places.zzee
            int r2 = r2 + -1
            r0.<init>(r2, r8)
            throw r0
        L_0x010f:
            if (r14 > r13) goto L_0x0127
            if (r13 > r3) goto L_0x0127
            int r1 = r2 + 1
            if (r1 == r8) goto L_0x0121
            char r0 = r0.charAt(r1)
            boolean r0 = java.lang.Character.isSurrogatePair(r13, r0)
            if (r0 != 0) goto L_0x0127
        L_0x0121:
            com.google.android.gms.internal.places.zzee r0 = new com.google.android.gms.internal.places.zzee
            r0.<init>(r2, r8)
            throw r0
        L_0x0127:
            java.lang.ArrayIndexOutOfBoundsException r0 = new java.lang.ArrayIndexOutOfBoundsException
            r1 = 46
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r1)
            java.lang.StringBuilder r1 = r2.append(r10)
            java.lang.StringBuilder r1 = r1.append(r13)
            java.lang.StringBuilder r1 = r1.append(r9)
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0148:
            int r0 = (int) r4
            return r0
        L_0x014a:
            java.lang.ArrayIndexOutOfBoundsException r1 = new java.lang.ArrayIndexOutOfBoundsException
            int r8 = r8 + -1
            char r0 = r0.charAt(r8)
            int r2 = r2 + r3
            r3 = 37
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.StringBuilder r3 = r4.append(r10)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.StringBuilder r0 = r0.append(r9)
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.places.zzed.zzc(java.lang.CharSequence, byte[], int, int):int");
    }

    private static int zzb(byte[] bArr, int i, long j, int i2) {
        switch (i2) {
            case 0:
                return zzea.zzao(i);
            case 1:
                return zzea.zzs(i, zzdy.zzb(bArr, j));
            case 2:
                return zzea.zzd(i, zzdy.zzb(bArr, j), zzdy.zzb(bArr, j + 1));
            default:
                throw new AssertionError();
        }
    }
}
