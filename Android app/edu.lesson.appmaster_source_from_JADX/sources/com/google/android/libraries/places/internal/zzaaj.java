package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzaai;
import com.google.android.libraries.places.internal.zzaaj;
import java.io.IOException;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class zzaaj<MessageType extends zzaaj<MessageType, BuilderType>, BuilderType extends zzaai<MessageType, BuilderType>> implements zzacz {
    protected int zza = 0;

    /* JADX WARNING: type inference failed for: r3v0, types: [java.util.Collection, java.lang.Object, java.lang.Iterable<T>, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static <T> void zzt(java.lang.Iterable<T> r3, java.util.List<? super T> r4) {
        /*
            com.google.android.libraries.places.internal.zzaca.zze(r3)
            boolean r0 = r4 instanceof java.util.ArrayList
            if (r0 == 0) goto L_0x0016
            r0 = r4
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r1 = r4.size()
            int r2 = r3.size()
            int r1 = r1 + r2
            r0.ensureCapacity(r1)
        L_0x0016:
            int r0 = r4.size()
            java.util.Iterator r3 = r3.iterator()
        L_0x001e:
            boolean r1 = r3.hasNext()
            if (r1 == 0) goto L_0x005f
            java.lang.Object r1 = r3.next()
            if (r1 != 0) goto L_0x005b
            int r3 = r4.size()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r2 = 37
            r1.<init>(r2)
            java.lang.String r2 = "Element at index "
            r1.append(r2)
            int r3 = r3 - r0
            r1.append(r3)
            java.lang.String r3 = " is null."
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            int r1 = r4.size()
            int r1 = r1 + -1
        L_0x004d:
            if (r1 < r0) goto L_0x0055
            r4.remove(r1)
            int r1 = r1 + -1
            goto L_0x004d
        L_0x0055:
            java.lang.NullPointerException r4 = new java.lang.NullPointerException
            r4.<init>(r3)
            throw r4
        L_0x005b:
            r4.add(r1)
            goto L_0x001e
        L_0x005f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzaaj.zzt(java.lang.Iterable, java.util.List):void");
    }

    /* access modifiers changed from: package-private */
    public int zzr() {
        throw null;
    }

    public final zzaax zzs() {
        try {
            int zzv = zzv();
            zzaax zzaax = zzaax.zzb;
            byte[] bArr = new byte[zzv];
            zzabf zzC = zzabf.zzC(bArr);
            zzH(zzC);
            zzC.zzD();
            return new zzaau(bArr);
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
    public void zzu(int i) {
        throw null;
    }
}
