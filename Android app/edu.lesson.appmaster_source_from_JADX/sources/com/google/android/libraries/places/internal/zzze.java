package com.google.android.libraries.places.internal;

import java.math.RoundingMode;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzze {
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003e, code lost:
        if (((r7 == java.math.RoundingMode.HALF_EVEN ? 1 : 0) & (r0 & 1)) != 0) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0041, code lost:
        if (r1 > 0) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0049, code lost:
        if (r5 < 0) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004c, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004d, code lost:
        if (r2 != false) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0054, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int zza(int r5, int r6, java.math.RoundingMode r7) {
        /*
            if (r7 == 0) goto L_0x005d
            if (r6 == 0) goto L_0x0055
            int r0 = r5 / r6
            int r1 = r6 * r0
            int r1 = r5 - r1
            if (r1 != 0) goto L_0x000d
            return r0
        L_0x000d:
            r5 = r5 ^ r6
            int r5 = r5 >> 31
            r2 = 1
            r5 = r5 | r2
            int[] r3 = com.google.android.libraries.places.internal.zzzd.zza
            int r4 = r7.ordinal()
            r3 = r3[r4]
            r4 = 0
            switch(r3) {
                case 1: goto L_0x0051;
                case 2: goto L_0x0054;
                case 3: goto L_0x0049;
                case 4: goto L_0x004f;
                case 5: goto L_0x0044;
                case 6: goto L_0x0024;
                case 7: goto L_0x0024;
                case 8: goto L_0x0024;
                default: goto L_0x001e;
            }
        L_0x001e:
            java.lang.AssertionError r5 = new java.lang.AssertionError
            r5.<init>()
            throw r5
        L_0x0024:
            int r1 = java.lang.Math.abs(r1)
            int r6 = java.lang.Math.abs(r6)
            int r6 = r6 - r1
            int r1 = r1 - r6
            if (r1 != 0) goto L_0x0041
            java.math.RoundingMode r6 = java.math.RoundingMode.HALF_UP
            if (r7 == r6) goto L_0x004b
            java.math.RoundingMode r6 = java.math.RoundingMode.HALF_EVEN
            if (r7 != r6) goto L_0x003a
            r6 = r2
            goto L_0x003b
        L_0x003a:
            r6 = r4
        L_0x003b:
            r7 = r0 & 1
            r6 = r6 & r7
            if (r6 == 0) goto L_0x004c
            goto L_0x004b
        L_0x0041:
            if (r1 <= 0) goto L_0x004c
            goto L_0x004b
        L_0x0044:
            if (r5 <= 0) goto L_0x0047
            goto L_0x004b
        L_0x0047:
            r2 = r4
            goto L_0x004d
        L_0x0049:
            if (r5 >= 0) goto L_0x004c
        L_0x004b:
            goto L_0x004d
        L_0x004c:
            r2 = r4
        L_0x004d:
            if (r2 == 0) goto L_0x0054
        L_0x004f:
            int r0 = r0 + r5
            return r0
        L_0x0051:
            com.google.android.libraries.places.internal.zzzf.zza(r4)
        L_0x0054:
            return r0
        L_0x0055:
            java.lang.ArithmeticException r5 = new java.lang.ArithmeticException
            java.lang.String r6 = "/ by zero"
            r5.<init>(r6)
            throw r5
        L_0x005d:
            r5 = 0
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzze.zza(int, int, java.math.RoundingMode):int");
    }

    public static int zzb(int i, RoundingMode roundingMode) {
        boolean z;
        if (i > 0) {
            switch (zzzd.zza[roundingMode.ordinal()]) {
                case 1:
                    if (((i - 1) & i) == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    zzzf.zza(z);
                    break;
                case 2:
                case 3:
                    break;
                case 4:
                case 5:
                    return 32 - Integer.numberOfLeadingZeros(i - 1);
                case 6:
                case 7:
                case 8:
                    int numberOfLeadingZeros = Integer.numberOfLeadingZeros(i);
                    return (31 - numberOfLeadingZeros) + (((-1257966797 >>> numberOfLeadingZeros) - i) >>> 31);
                default:
                    throw new AssertionError();
            }
            return 31 - Integer.numberOfLeadingZeros(i);
        }
        throw new IllegalArgumentException("x (0) must be > 0");
    }
}
