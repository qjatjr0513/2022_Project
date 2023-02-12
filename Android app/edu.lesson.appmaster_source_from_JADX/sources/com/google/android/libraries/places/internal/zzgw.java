package com.google.android.libraries.places.internal;

import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzgw<K, V> extends zzgg<K, V> {
    static final zzgg<Object, Object> zza = new zzgw((Object) null, new Object[0], 0);
    final transient Object[] zzb;
    @CheckForNull
    private final transient Object zzc;
    private final transient int zzd;

    private zzgw(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: short[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: short[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: byte[]} */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0093, code lost:
        r2[r6] = (byte) r3;
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00da, code lost:
        r2[r6] = (short) r3;
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0117, code lost:
        r2[r7] = r4;
        r1 = r1 + 1;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <K, V> com.google.android.libraries.places.internal.zzgw<K, V> zzf(int r10, java.lang.Object[] r11) {
        /*
            if (r10 != 0) goto L_0x0007
            com.google.android.libraries.places.internal.zzgg<java.lang.Object, java.lang.Object> r10 = zza
            com.google.android.libraries.places.internal.zzgw r10 = (com.google.android.libraries.places.internal.zzgw) r10
            return r10
        L_0x0007:
            r0 = 0
            r1 = 0
            r2 = 1
            if (r10 != r2) goto L_0x001f
            r10 = r11[r1]
            r10.getClass()
            r1 = r11[r2]
            r1.getClass()
            com.google.android.libraries.places.internal.zzft.zza(r10, r1)
            com.google.android.libraries.places.internal.zzgw r10 = new com.google.android.libraries.places.internal.zzgw
            r10.<init>(r0, r11, r2)
            return r10
        L_0x001f:
            int r3 = r11.length
            int r3 = r3 >> r2
            java.lang.String r4 = "index"
            com.google.android.libraries.places.internal.zzfm.zzb(r10, r3, r4)
            r3 = 2
            int r3 = java.lang.Math.max(r10, r3)
            r4 = 751619276(0x2ccccccc, float:5.8207657E-12)
            r5 = 1073741824(0x40000000, float:2.0)
            if (r3 >= r4) goto L_0x0048
            int r4 = r3 + -1
            int r4 = java.lang.Integer.highestOneBit(r4)
            int r4 = r4 + r4
            r5 = r4
        L_0x003a:
            double r6 = (double) r5
            r8 = 4604480259023595110(0x3fe6666666666666, double:0.7)
            double r6 = r6 * r8
            double r8 = (double) r3
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 >= 0) goto L_0x0053
            int r5 = r5 + r5
            goto L_0x003a
        L_0x0048:
            if (r3 >= r5) goto L_0x004c
            r3 = r2
            goto L_0x004d
        L_0x004c:
            r3 = r1
        L_0x004d:
            java.lang.String r4 = "collection too large"
            com.google.android.libraries.places.internal.zzfm.zze(r3, r4)
        L_0x0053:
            if (r10 != r2) goto L_0x0064
            r1 = r11[r1]
            r1.getClass()
            r2 = r11[r2]
            r2.getClass()
            com.google.android.libraries.places.internal.zzft.zza(r1, r2)
            goto L_0x012d
        L_0x0064:
            int r0 = r5 + -1
            r2 = 128(0x80, float:1.794E-43)
            r3 = -1
            if (r5 > r2) goto L_0x00ac
            byte[] r2 = new byte[r5]
            java.util.Arrays.fill(r2, r3)
        L_0x0070:
            if (r1 >= r10) goto L_0x00a9
            int r3 = r1 + r1
            r4 = r11[r3]
            r4.getClass()
            r5 = r3 ^ 1
            r5 = r11[r5]
            r5.getClass()
            com.google.android.libraries.places.internal.zzft.zza(r4, r5)
            int r6 = r4.hashCode()
            int r6 = com.google.android.libraries.places.internal.zzga.zza(r6)
        L_0x008b:
            r6 = r6 & r0
            byte r7 = r2[r6]
            r8 = 255(0xff, float:3.57E-43)
            r7 = r7 & r8
            if (r7 != r8) goto L_0x0099
            byte r3 = (byte) r3
            r2[r6] = r3
            int r1 = r1 + 1
            goto L_0x0070
        L_0x0099:
            r8 = r11[r7]
            boolean r8 = r4.equals(r8)
            if (r8 != 0) goto L_0x00a4
            int r6 = r6 + 1
            goto L_0x008b
        L_0x00a4:
            java.lang.IllegalArgumentException r10 = zzg(r4, r5, r11, r7)
            throw r10
        L_0x00a9:
            r0 = r2
            goto L_0x012d
        L_0x00ac:
            r2 = 32768(0x8000, float:4.5918E-41)
            if (r5 > r2) goto L_0x00f2
            short[] r2 = new short[r5]
            java.util.Arrays.fill(r2, r3)
        L_0x00b6:
            if (r1 >= r10) goto L_0x00f0
            int r3 = r1 + r1
            r4 = r11[r3]
            r4.getClass()
            r5 = r3 ^ 1
            r5 = r11[r5]
            r5.getClass()
            com.google.android.libraries.places.internal.zzft.zza(r4, r5)
            int r6 = r4.hashCode()
            int r6 = com.google.android.libraries.places.internal.zzga.zza(r6)
        L_0x00d1:
            r6 = r6 & r0
            short r7 = r2[r6]
            char r7 = (char) r7
            r8 = 65535(0xffff, float:9.1834E-41)
            if (r7 != r8) goto L_0x00e0
            short r3 = (short) r3
            r2[r6] = r3
            int r1 = r1 + 1
            goto L_0x00b6
        L_0x00e0:
            r8 = r11[r7]
            boolean r8 = r4.equals(r8)
            if (r8 != 0) goto L_0x00eb
            int r6 = r6 + 1
            goto L_0x00d1
        L_0x00eb:
            java.lang.IllegalArgumentException r10 = zzg(r4, r5, r11, r7)
            throw r10
        L_0x00f0:
            r0 = r2
            goto L_0x012d
        L_0x00f2:
            int[] r2 = new int[r5]
            java.util.Arrays.fill(r2, r3)
        L_0x00f7:
            if (r1 >= r10) goto L_0x012c
            int r4 = r1 + r1
            r5 = r11[r4]
            r5.getClass()
            r6 = r4 ^ 1
            r6 = r11[r6]
            r6.getClass()
            com.google.android.libraries.places.internal.zzft.zza(r5, r6)
            int r7 = r5.hashCode()
            int r7 = com.google.android.libraries.places.internal.zzga.zza(r7)
        L_0x0112:
            r7 = r7 & r0
            r8 = r2[r7]
            if (r8 != r3) goto L_0x011c
            r2[r7] = r4
            int r1 = r1 + 1
            goto L_0x00f7
        L_0x011c:
            r9 = r11[r8]
            boolean r9 = r5.equals(r9)
            if (r9 != 0) goto L_0x0127
            int r7 = r7 + 1
            goto L_0x0112
        L_0x0127:
            java.lang.IllegalArgumentException r10 = zzg(r5, r6, r11, r8)
            throw r10
        L_0x012c:
            r0 = r2
        L_0x012d:
            com.google.android.libraries.places.internal.zzgw r1 = new com.google.android.libraries.places.internal.zzgw
            r1.<init>(r0, r11, r10)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzgw.zzf(int, java.lang.Object[]):com.google.android.libraries.places.internal.zzgw");
    }

    private static IllegalArgumentException zzg(Object obj, Object obj2, Object[] objArr, int i) {
        String valueOf = String.valueOf(obj);
        String valueOf2 = String.valueOf(obj2);
        String valueOf3 = String.valueOf(objArr[i]);
        String valueOf4 = String.valueOf(objArr[i ^ 1]);
        int length = String.valueOf(valueOf).length();
        int length2 = String.valueOf(valueOf2).length();
        StringBuilder sb = new StringBuilder(length + 39 + length2 + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length());
        sb.append("Multiple entries with same key: ");
        sb.append(valueOf);
        sb.append("=");
        sb.append(valueOf2);
        sb.append(" and ");
        sb.append(valueOf3);
        sb.append("=");
        sb.append(valueOf4);
        return new IllegalArgumentException(sb.toString());
    }

    @CheckForNull
    public final V get(@CheckForNull Object obj) {
        V v;
        Object obj2 = this.zzc;
        V[] vArr = this.zzb;
        int i = this.zzd;
        if (obj == null) {
            v = null;
        } else {
            if (i == 1) {
                V v2 = vArr[0];
                v2.getClass();
                if (v2.equals(obj)) {
                    v = vArr[1];
                    v.getClass();
                }
            } else if (obj2 != null) {
                if (obj2 instanceof byte[]) {
                    byte[] bArr = (byte[]) obj2;
                    int length = bArr.length - 1;
                    int zza2 = zzga.zza(obj.hashCode());
                    while (true) {
                        int i2 = zza2 & length;
                        byte b = bArr[i2] & 255;
                        if (b == 255) {
                            v = null;
                            break;
                        } else if (obj.equals(vArr[b])) {
                            v = vArr[b ^ 1];
                            break;
                        } else {
                            zza2 = i2 + 1;
                        }
                    }
                } else if (obj2 instanceof short[]) {
                    short[] sArr = (short[]) obj2;
                    int length2 = sArr.length - 1;
                    int zza3 = zzga.zza(obj.hashCode());
                    while (true) {
                        int i3 = zza3 & length2;
                        char c = (char) sArr[i3];
                        if (c == 65535) {
                            v = null;
                            break;
                        } else if (obj.equals(vArr[c])) {
                            v = vArr[c ^ 1];
                            break;
                        } else {
                            zza3 = i3 + 1;
                        }
                    }
                } else {
                    int[] iArr = (int[]) obj2;
                    int length3 = iArr.length - 1;
                    int zza4 = zzga.zza(obj.hashCode());
                    while (true) {
                        int i4 = zza4 & length3;
                        int i5 = iArr[i4];
                        if (i5 == -1) {
                            v = null;
                            break;
                        } else if (obj.equals(vArr[i5])) {
                            v = vArr[i5 ^ 1];
                            break;
                        } else {
                            zza4 = i4 + 1;
                        }
                    }
                }
            }
            v = null;
        }
        if (v == null) {
            return null;
        }
        return v;
    }

    public final int size() {
        return this.zzd;
    }

    /* access modifiers changed from: package-private */
    public final zzgb<V> zza() {
        return new zzgv(this.zzb, 1, this.zzd);
    }

    /* access modifiers changed from: package-private */
    public final zzgh<Map.Entry<K, V>> zzc() {
        return new zzgt(this, this.zzb, 0, this.zzd);
    }

    /* access modifiers changed from: package-private */
    public final zzgh<K> zzd() {
        return new zzgu(this, new zzgv(this.zzb, 0, this.zzd));
    }
}
