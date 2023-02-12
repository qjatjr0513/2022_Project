package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzig;
import com.google.android.gms.internal.measurement.zzih;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
public abstract class zzih<MessageType extends zzih<MessageType, BuilderType>, BuilderType extends zzig<MessageType, BuilderType>> implements zzlc {
    protected int zzb = 0;

    /* JADX WARNING: type inference failed for: r6v0, types: [java.util.Collection, java.lang.Object, java.lang.Iterable<T>, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static <T> void zzbq(java.lang.Iterable<T> r6, java.util.List<? super T> r7) {
        /*
            com.google.android.gms.internal.measurement.zzkf.zze(r6)
            boolean r0 = r6 instanceof com.google.android.gms.internal.measurement.zzkm
            java.lang.String r1 = " is null."
            java.lang.String r2 = "Element at index "
            r3 = 37
            if (r0 == 0) goto L_0x0065
            com.google.android.gms.internal.measurement.zzkm r6 = (com.google.android.gms.internal.measurement.zzkm) r6
            java.util.List r6 = r6.zzh()
            r0 = r7
            com.google.android.gms.internal.measurement.zzkm r0 = (com.google.android.gms.internal.measurement.zzkm) r0
            int r7 = r7.size()
            java.util.Iterator r6 = r6.iterator()
        L_0x001e:
            boolean r4 = r6.hasNext()
            if (r4 == 0) goto L_0x00c3
            java.lang.Object r4 = r6.next()
            if (r4 != 0) goto L_0x0055
            int r6 = r0.size()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            r4.append(r2)
            int r6 = r6 - r7
            r4.append(r6)
            r4.append(r1)
            java.lang.String r6 = r4.toString()
            int r1 = r0.size()
            int r1 = r1 + -1
        L_0x0047:
            if (r1 < r7) goto L_0x004f
            r0.remove(r1)
            int r1 = r1 + -1
            goto L_0x0047
        L_0x004f:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            r7.<init>(r6)
            throw r7
        L_0x0055:
            boolean r5 = r4 instanceof com.google.android.gms.internal.measurement.zzix
            if (r5 == 0) goto L_0x005f
            com.google.android.gms.internal.measurement.zzix r4 = (com.google.android.gms.internal.measurement.zzix) r4
            r0.zzi(r4)
            goto L_0x001e
        L_0x005f:
            java.lang.String r4 = (java.lang.String) r4
            r0.add(r4)
            goto L_0x001e
        L_0x0065:
            boolean r0 = r6 instanceof com.google.android.gms.internal.measurement.zzlj
            if (r0 != 0) goto L_0x00c4
            boolean r0 = r7 instanceof java.util.ArrayList
            if (r0 == 0) goto L_0x0080
            boolean r0 = r6 instanceof java.util.Collection
            if (r0 == 0) goto L_0x0080
            r0 = r7
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r4 = r7.size()
            int r5 = r6.size()
            int r4 = r4 + r5
            r0.ensureCapacity(r4)
        L_0x0080:
            int r0 = r7.size()
            java.util.Iterator r6 = r6.iterator()
        L_0x0088:
            boolean r4 = r6.hasNext()
            if (r4 == 0) goto L_0x00c3
            java.lang.Object r4 = r6.next()
            if (r4 != 0) goto L_0x00bf
            int r6 = r7.size()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            r4.append(r2)
            int r6 = r6 - r0
            r4.append(r6)
            r4.append(r1)
            java.lang.String r6 = r4.toString()
            int r1 = r7.size()
            int r1 = r1 + -1
        L_0x00b1:
            if (r1 < r0) goto L_0x00b9
            r7.remove(r1)
            int r1 = r1 + -1
            goto L_0x00b1
        L_0x00b9:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            r7.<init>(r6)
            throw r7
        L_0x00bf:
            r7.add(r4)
            goto L_0x0088
        L_0x00c3:
            return
        L_0x00c4:
            r7.addAll(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzih.zzbq(java.lang.Iterable, java.util.List):void");
    }

    /* access modifiers changed from: package-private */
    public int zzbo() {
        throw null;
    }

    public final zzix zzbp() {
        try {
            int zzbt = zzbt();
            zzix zzix = zzix.zzb;
            byte[] bArr = new byte[zzbt];
            zzje zzC = zzje.zzC(bArr);
            zzbH(zzC);
            zzC.zzD();
            return new zziv(bArr);
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 72);
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ByteString threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    /* access modifiers changed from: package-private */
    public void zzbr(int i) {
        throw null;
    }

    public final byte[] zzbs() {
        try {
            byte[] bArr = new byte[zzbt()];
            zzje zzC = zzje.zzC(bArr);
            zzbH(zzC);
            zzC.zzD();
            return bArr;
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 72);
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a byte array threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }
}
