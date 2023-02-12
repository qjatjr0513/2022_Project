package com.google.android.gms.internal.places;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

final class zzcq<T> implements zzda<T> {
    private final zzck zzkw;
    private final boolean zzkx;
    private final zzds<?, ?> zzlg;
    private final zzar<?> zzlh;

    private zzcq(zzds<?, ?> zzds, zzar<?> zzar, zzck zzck) {
        this.zzlg = zzds;
        this.zzkx = zzar.zzf(zzck);
        this.zzlh = zzar;
        this.zzkw = zzck;
    }

    static <T> zzcq<T> zzb(zzds<?, ?> zzds, zzar<?> zzar, zzck zzck) {
        return new zzcq<>(zzds, zzar, zzck);
    }

    public final T newInstance() {
        return this.zzkw.zzbl().zzbe();
    }

    public final boolean equals(T t, T t2) {
        if (!this.zzlg.zzr(t).equals(this.zzlg.zzr(t2))) {
            return false;
        }
        if (this.zzkx) {
            return this.zzlh.zzb((Object) t).equals(this.zzlh.zzb((Object) t2));
        }
        return true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzlg.zzr(t).hashCode();
        if (this.zzkx) {
            return (hashCode * 53) + this.zzlh.zzb((Object) t).hashCode();
        }
        return hashCode;
    }

    public final void zzd(T t, T t2) {
        zzdc.zzb(this.zzlg, t, t2);
        if (this.zzkx) {
            zzdc.zzb(this.zzlh, t, t2);
        }
    }

    public final void zzb(T t, zzel zzel) throws IOException {
        Iterator<Map.Entry<?, Object>> it = this.zzlh.zzb((Object) t).iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            zzax zzax = (zzax) next.getKey();
            if (zzax.zzay() != zzem.MESSAGE || zzax.zzaz() || zzax.zzba()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (next instanceof zzbn) {
                zzel.zzb(zzax.zzaw(), (Object) ((zzbn) next).zzbx().zzv());
            } else {
                zzel.zzb(zzax.zzaw(), next.getValue());
            }
        }
        zzds<?, ?> zzds = this.zzlg;
        zzds.zzd(zzds.zzr(t), zzel);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: com.google.android.gms.internal.places.zzbc$zzf} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(T r9, byte[] r10, int r11, int r12, com.google.android.gms.internal.places.zzr r13) throws java.io.IOException {
        /*
            r8 = this;
            r0 = r9
            com.google.android.gms.internal.places.zzbc r0 = (com.google.android.gms.internal.places.zzbc) r0
            com.google.android.gms.internal.places.zzdr r1 = r0.zzih
            com.google.android.gms.internal.places.zzdr r2 = com.google.android.gms.internal.places.zzdr.zzdh()
            if (r1 != r2) goto L_0x0011
            com.google.android.gms.internal.places.zzdr r1 = com.google.android.gms.internal.places.zzdr.zzdi()
            r0.zzih = r1
        L_0x0011:
            com.google.android.gms.internal.places.zzbc$zzc r9 = (com.google.android.gms.internal.places.zzbc.zzc) r9
            r9.zzbm()
            r9 = 0
            r0 = r9
        L_0x0018:
            if (r11 >= r12) goto L_0x00aa
            int r4 = com.google.android.gms.internal.places.zzs.zzb(r10, r11, r13)
            int r2 = r13.zzdz
            r11 = 11
            r3 = 2
            if (r2 == r11) goto L_0x0053
            r11 = r2 & 7
            if (r11 != r3) goto L_0x004e
            com.google.android.gms.internal.places.zzar<?> r11 = r8.zzlh
            com.google.android.gms.internal.places.zzap r0 = r13.zzec
            com.google.android.gms.internal.places.zzck r3 = r8.zzkw
            int r5 = r2 >>> 3
            java.lang.Object r11 = r11.zzb(r0, r3, r5)
            r0 = r11
            com.google.android.gms.internal.places.zzbc$zzf r0 = (com.google.android.gms.internal.places.zzbc.zzf) r0
            if (r0 != 0) goto L_0x0045
            r3 = r10
            r5 = r12
            r6 = r1
            r7 = r13
            int r11 = com.google.android.gms.internal.places.zzs.zzb((int) r2, (byte[]) r3, (int) r4, (int) r5, (com.google.android.gms.internal.places.zzdr) r6, (com.google.android.gms.internal.places.zzr) r7)
            goto L_0x0018
        L_0x0045:
            com.google.android.gms.internal.places.zzcv.zzcq()
            java.lang.NoSuchMethodError r9 = new java.lang.NoSuchMethodError
            r9.<init>()
            throw r9
        L_0x004e:
            int r11 = com.google.android.gms.internal.places.zzs.zzb((int) r2, (byte[]) r10, (int) r4, (int) r12, (com.google.android.gms.internal.places.zzr) r13)
            goto L_0x0018
        L_0x0053:
            r11 = 0
            r2 = r9
        L_0x0055:
            if (r4 >= r12) goto L_0x009d
            int r4 = com.google.android.gms.internal.places.zzs.zzb(r10, r4, r13)
            int r5 = r13.zzdz
            int r6 = r5 >>> 3
            r7 = r5 & 7
            switch(r6) {
                case 2: goto L_0x007f;
                case 3: goto L_0x0069;
                default: goto L_0x0068;
            }
        L_0x0068:
            goto L_0x0094
        L_0x0069:
            if (r0 != 0) goto L_0x0076
            if (r7 != r3) goto L_0x0094
            int r4 = com.google.android.gms.internal.places.zzs.zzf(r10, r4, r13)
            java.lang.Object r2 = r13.zzeb
            com.google.android.gms.internal.places.zzw r2 = (com.google.android.gms.internal.places.zzw) r2
            goto L_0x0055
        L_0x0076:
            com.google.android.gms.internal.places.zzcv.zzcq()
            java.lang.NoSuchMethodError r9 = new java.lang.NoSuchMethodError
            r9.<init>()
            throw r9
        L_0x007f:
            if (r7 != 0) goto L_0x0094
            int r4 = com.google.android.gms.internal.places.zzs.zzb(r10, r4, r13)
            int r11 = r13.zzdz
            com.google.android.gms.internal.places.zzar<?> r0 = r8.zzlh
            com.google.android.gms.internal.places.zzap r5 = r13.zzec
            com.google.android.gms.internal.places.zzck r6 = r8.zzkw
            java.lang.Object r0 = r0.zzb(r5, r6, r11)
            com.google.android.gms.internal.places.zzbc$zzf r0 = (com.google.android.gms.internal.places.zzbc.zzf) r0
            goto L_0x0055
        L_0x0094:
            r6 = 12
            if (r5 == r6) goto L_0x009d
            int r4 = com.google.android.gms.internal.places.zzs.zzb((int) r5, (byte[]) r10, (int) r4, (int) r12, (com.google.android.gms.internal.places.zzr) r13)
            goto L_0x0055
        L_0x009d:
            if (r2 == 0) goto L_0x00a7
            int r11 = r11 << 3
            r11 = r11 | r3
            r1.zzc(r11, r2)
        L_0x00a7:
            r11 = r4
            goto L_0x0018
        L_0x00aa:
            if (r11 != r12) goto L_0x00ad
            return
        L_0x00ad:
            com.google.android.gms.internal.places.zzbk r9 = com.google.android.gms.internal.places.zzbk.zzbt()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.places.zzcq.zzb(java.lang.Object, byte[], int, int, com.google.android.gms.internal.places.zzr):void");
    }

    public final void zzd(T t) {
        this.zzlg.zzd(t);
        this.zzlh.zzd(t);
    }

    public final boolean zzp(T t) {
        return this.zzlh.zzb((Object) t).isInitialized();
    }

    public final int zzn(T t) {
        zzds<?, ?> zzds = this.zzlg;
        int zzs = zzds.zzs(zzds.zzr(t)) + 0;
        if (this.zzkx) {
            return zzs + this.zzlh.zzb((Object) t).zzav();
        }
        return zzs;
    }
}
