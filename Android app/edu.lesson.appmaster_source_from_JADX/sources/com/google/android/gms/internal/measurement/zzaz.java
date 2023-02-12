package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzaz extends zzaw {
    protected zzaz() {
        this.zza.add(zzbl.APPLY);
        this.zza.add(zzbl.BLOCK);
        this.zza.add(zzbl.BREAK);
        this.zza.add(zzbl.CASE);
        this.zza.add(zzbl.DEFAULT);
        this.zza.add(zzbl.CONTINUE);
        this.zza.add(zzbl.DEFINE_FUNCTION);
        this.zza.add(zzbl.FN);
        this.zza.add(zzbl.IF);
        this.zza.add(zzbl.QUOTE);
        this.zza.add(zzbl.RETURN);
        this.zza.add(zzbl.SWITCH);
        this.zza.add(zzbl.TERNARY);
    }

    private static zzap zzc(zzg zzg, List<zzap> list) {
        zzh.zzi(zzbl.FN.name(), 2, list);
        zzap zzb = zzg.zzb(list.get(0));
        zzap zzb2 = zzg.zzb(list.get(1));
        if (zzb2 instanceof zzae) {
            List<zzap> zzm = ((zzae) zzb2).zzm();
            List<zzap> arrayList = new ArrayList<>();
            if (list.size() > 2) {
                arrayList = list.subList(2, list.size());
            }
            return new zzao(zzb.zzi(), zzm, arrayList, zzg);
        }
        throw new IllegalArgumentException(String.format("FN requires an ArrayValue of parameter names found %s", new Object[]{zzb2.getClass().getCanonicalName()}));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ea, code lost:
        if (r8.equals("continue") == false) goto L_0x00ee;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzap zza(java.lang.String r8, com.google.android.gms.internal.measurement.zzg r9, java.util.List<com.google.android.gms.internal.measurement.zzap> r10) {
        /*
            r7 = this;
            com.google.android.gms.internal.measurement.zzbl r0 = com.google.android.gms.internal.measurement.zzbl.ADD
            com.google.android.gms.internal.measurement.zzbl r0 = com.google.android.gms.internal.measurement.zzh.zze(r8)
            int r0 = r0.ordinal()
            java.lang.String r1 = "return"
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = 0
            switch(r0) {
                case 2: goto L_0x01ef;
                case 11: goto L_0x01e1;
                case 12: goto L_0x01d5;
                case 13: goto L_0x01b3;
                case 15: goto L_0x01a7;
                case 19: goto L_0x01b3;
                case 20: goto L_0x0183;
                case 25: goto L_0x017e;
                case 41: goto L_0x012a;
                case 54: goto L_0x0124;
                case 57: goto L_0x0101;
                case 60: goto L_0x004c;
                case 61: goto L_0x0018;
                default: goto L_0x0013;
            }
        L_0x0013:
            com.google.android.gms.internal.measurement.zzap r8 = super.zzb(r8)
            return r8
        L_0x0018:
            com.google.android.gms.internal.measurement.zzbl r8 = com.google.android.gms.internal.measurement.zzbl.TERNARY
            java.lang.String r8 = r8.name()
            com.google.android.gms.internal.measurement.zzh.zzh(r8, r2, r10)
            java.lang.Object r8 = r10.get(r5)
            com.google.android.gms.internal.measurement.zzap r8 = (com.google.android.gms.internal.measurement.zzap) r8
            com.google.android.gms.internal.measurement.zzap r8 = r9.zzb(r8)
            java.lang.Boolean r8 = r8.zzg()
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x0040
            java.lang.Object r8 = r10.get(r4)
            com.google.android.gms.internal.measurement.zzap r8 = (com.google.android.gms.internal.measurement.zzap) r8
            com.google.android.gms.internal.measurement.zzap r8 = r9.zzb(r8)
            goto L_0x004b
        L_0x0040:
            java.lang.Object r8 = r10.get(r3)
            com.google.android.gms.internal.measurement.zzap r8 = (com.google.android.gms.internal.measurement.zzap) r8
            com.google.android.gms.internal.measurement.zzap r8 = r9.zzb(r8)
        L_0x004b:
            return r8
        L_0x004c:
            com.google.android.gms.internal.measurement.zzbl r8 = com.google.android.gms.internal.measurement.zzbl.SWITCH
            java.lang.String r8 = r8.name()
            com.google.android.gms.internal.measurement.zzh.zzh(r8, r2, r10)
            java.lang.Object r8 = r10.get(r5)
            com.google.android.gms.internal.measurement.zzap r8 = (com.google.android.gms.internal.measurement.zzap) r8
            com.google.android.gms.internal.measurement.zzap r8 = r9.zzb(r8)
            java.lang.Object r0 = r10.get(r4)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r9.zzb(r0)
            java.lang.Object r10 = r10.get(r3)
            com.google.android.gms.internal.measurement.zzap r10 = (com.google.android.gms.internal.measurement.zzap) r10
            com.google.android.gms.internal.measurement.zzap r10 = r9.zzb(r10)
            boolean r2 = r0 instanceof com.google.android.gms.internal.measurement.zzae
            if (r2 == 0) goto L_0x00f9
            boolean r2 = r10 instanceof com.google.android.gms.internal.measurement.zzae
            if (r2 == 0) goto L_0x00f1
            com.google.android.gms.internal.measurement.zzae r0 = (com.google.android.gms.internal.measurement.zzae) r0
            com.google.android.gms.internal.measurement.zzae r10 = (com.google.android.gms.internal.measurement.zzae) r10
            r2 = r5
            r3 = r2
        L_0x0081:
            int r6 = r0.zzc()
            if (r2 >= r6) goto L_0x00bc
            if (r3 != 0) goto L_0x009a
            com.google.android.gms.internal.measurement.zzap r3 = r0.zze(r2)
            com.google.android.gms.internal.measurement.zzap r3 = r9.zzb(r3)
            boolean r3 = r8.equals(r3)
            if (r3 == 0) goto L_0x0098
            goto L_0x009a
        L_0x0098:
            r3 = r5
            goto L_0x00b9
        L_0x009a:
            com.google.android.gms.internal.measurement.zzap r3 = r10.zze(r2)
            com.google.android.gms.internal.measurement.zzap r3 = r9.zzb(r3)
            boolean r6 = r3 instanceof com.google.android.gms.internal.measurement.zzag
            if (r6 == 0) goto L_0x00b8
            r8 = r3
            com.google.android.gms.internal.measurement.zzag r8 = (com.google.android.gms.internal.measurement.zzag) r8
            java.lang.String r8 = r8.zzc()
            java.lang.String r9 = "break"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x00ed
            com.google.android.gms.internal.measurement.zzap r8 = com.google.android.gms.internal.measurement.zzap.zzf
            return r8
        L_0x00b8:
            r3 = r4
        L_0x00b9:
            int r2 = r2 + 1
            goto L_0x0081
        L_0x00bc:
            int r8 = r0.zzc()
            int r8 = r8 + r4
            int r2 = r10.zzc()
            if (r8 != r2) goto L_0x00ee
            int r8 = r0.zzc()
            com.google.android.gms.internal.measurement.zzap r8 = r10.zze(r8)
            com.google.android.gms.internal.measurement.zzap r3 = r9.zzb(r8)
            boolean r8 = r3 instanceof com.google.android.gms.internal.measurement.zzag
            if (r8 == 0) goto L_0x00ee
            r8 = r3
            com.google.android.gms.internal.measurement.zzag r8 = (com.google.android.gms.internal.measurement.zzag) r8
            java.lang.String r8 = r8.zzc()
            boolean r9 = r8.equals(r1)
            if (r9 != 0) goto L_0x00ed
            java.lang.String r9 = "continue"
            boolean r8 = r8.equals(r9)
            if (r8 != 0) goto L_0x00ed
            goto L_0x00ee
        L_0x00ed:
            return r3
        L_0x00ee:
            com.google.android.gms.internal.measurement.zzap r8 = com.google.android.gms.internal.measurement.zzap.zzf
            return r8
        L_0x00f1:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "Malformed SWITCH statement, case statements are not a list"
            r8.<init>(r9)
            throw r8
        L_0x00f9:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "Malformed SWITCH statement, cases are not a list"
            r8.<init>(r9)
            throw r8
        L_0x0101:
            boolean r8 = r10.isEmpty()
            if (r8 == 0) goto L_0x010a
            com.google.android.gms.internal.measurement.zzap r8 = com.google.android.gms.internal.measurement.zzap.zzj
            goto L_0x0123
        L_0x010a:
            com.google.android.gms.internal.measurement.zzbl r8 = com.google.android.gms.internal.measurement.zzbl.RETURN
            java.lang.String r8 = r8.name()
            com.google.android.gms.internal.measurement.zzh.zzh(r8, r4, r10)
            java.lang.Object r8 = r10.get(r5)
            com.google.android.gms.internal.measurement.zzap r8 = (com.google.android.gms.internal.measurement.zzap) r8
            com.google.android.gms.internal.measurement.zzap r8 = r9.zzb(r8)
            com.google.android.gms.internal.measurement.zzag r9 = new com.google.android.gms.internal.measurement.zzag
            r9.<init>(r1, r8)
            r8 = r9
        L_0x0123:
            return r8
        L_0x0124:
            com.google.android.gms.internal.measurement.zzae r8 = new com.google.android.gms.internal.measurement.zzae
            r8.<init>(r10)
            return r8
        L_0x012a:
            com.google.android.gms.internal.measurement.zzbl r8 = com.google.android.gms.internal.measurement.zzbl.IF
            java.lang.String r8 = r8.name()
            com.google.android.gms.internal.measurement.zzh.zzi(r8, r3, r10)
            java.lang.Object r8 = r10.get(r5)
            com.google.android.gms.internal.measurement.zzap r8 = (com.google.android.gms.internal.measurement.zzap) r8
            com.google.android.gms.internal.measurement.zzap r8 = r9.zzb(r8)
            java.lang.Object r0 = r10.get(r4)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r9.zzb(r0)
            int r1 = r10.size()
            if (r1 <= r3) goto L_0x0158
            java.lang.Object r10 = r10.get(r3)
            com.google.android.gms.internal.measurement.zzap r10 = (com.google.android.gms.internal.measurement.zzap) r10
            com.google.android.gms.internal.measurement.zzap r10 = r9.zzb(r10)
            goto L_0x0159
        L_0x0158:
            r10 = 0
        L_0x0159:
            com.google.android.gms.internal.measurement.zzap r1 = com.google.android.gms.internal.measurement.zzap.zzf
            java.lang.Boolean r8 = r8.zzg()
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x016c
            com.google.android.gms.internal.measurement.zzae r0 = (com.google.android.gms.internal.measurement.zzae) r0
            com.google.android.gms.internal.measurement.zzap r1 = r9.zzc(r0)
            goto L_0x0176
        L_0x016c:
            if (r10 == 0) goto L_0x0175
            com.google.android.gms.internal.measurement.zzae r10 = (com.google.android.gms.internal.measurement.zzae) r10
            com.google.android.gms.internal.measurement.zzap r1 = r9.zzc(r10)
            goto L_0x0176
        L_0x0175:
        L_0x0176:
            boolean r8 = r1 instanceof com.google.android.gms.internal.measurement.zzag
            if (r8 == 0) goto L_0x017b
            return r1
        L_0x017b:
            com.google.android.gms.internal.measurement.zzap r8 = com.google.android.gms.internal.measurement.zzap.zzf
            return r8
        L_0x017e:
            com.google.android.gms.internal.measurement.zzap r8 = zzc(r9, r10)
            return r8
        L_0x0183:
            com.google.android.gms.internal.measurement.zzbl r8 = com.google.android.gms.internal.measurement.zzbl.DEFINE_FUNCTION
            java.lang.String r8 = r8.name()
            com.google.android.gms.internal.measurement.zzh.zzi(r8, r3, r10)
            com.google.android.gms.internal.measurement.zzap r8 = zzc(r9, r10)
            r10 = r8
            com.google.android.gms.internal.measurement.zzai r10 = (com.google.android.gms.internal.measurement.zzai) r10
            java.lang.String r0 = r10.zzc()
            if (r0 != 0) goto L_0x019f
            java.lang.String r10 = ""
            r9.zzg(r10, r8)
            goto L_0x01a6
        L_0x019f:
            java.lang.String r10 = r10.zzc()
            r9.zzg(r10, r8)
        L_0x01a6:
            return r8
        L_0x01a7:
            com.google.android.gms.internal.measurement.zzbl r8 = com.google.android.gms.internal.measurement.zzbl.BREAK
            java.lang.String r8 = r8.name()
            com.google.android.gms.internal.measurement.zzh.zzh(r8, r5, r10)
            com.google.android.gms.internal.measurement.zzap r8 = com.google.android.gms.internal.measurement.zzap.zzh
            return r8
        L_0x01b3:
            boolean r8 = r10.isEmpty()
            if (r8 == 0) goto L_0x01bc
            com.google.android.gms.internal.measurement.zzap r8 = com.google.android.gms.internal.measurement.zzap.zzf
            goto L_0x01d4
        L_0x01bc:
            java.lang.Object r8 = r10.get(r5)
            com.google.android.gms.internal.measurement.zzap r8 = (com.google.android.gms.internal.measurement.zzap) r8
            com.google.android.gms.internal.measurement.zzap r8 = r9.zzb(r8)
            boolean r10 = r8 instanceof com.google.android.gms.internal.measurement.zzae
            if (r10 == 0) goto L_0x01d2
            com.google.android.gms.internal.measurement.zzae r8 = (com.google.android.gms.internal.measurement.zzae) r8
            com.google.android.gms.internal.measurement.zzap r8 = r9.zzc(r8)
            goto L_0x01d4
        L_0x01d2:
            com.google.android.gms.internal.measurement.zzap r8 = com.google.android.gms.internal.measurement.zzap.zzf
        L_0x01d4:
            return r8
        L_0x01d5:
            com.google.android.gms.internal.measurement.zzbl r8 = com.google.android.gms.internal.measurement.zzbl.BREAK
            java.lang.String r8 = r8.name()
            com.google.android.gms.internal.measurement.zzh.zzh(r8, r5, r10)
            com.google.android.gms.internal.measurement.zzap r8 = com.google.android.gms.internal.measurement.zzap.zzi
            return r8
        L_0x01e1:
            com.google.android.gms.internal.measurement.zzg r8 = r9.zza()
            com.google.android.gms.internal.measurement.zzae r9 = new com.google.android.gms.internal.measurement.zzae
            r9.<init>(r10)
            com.google.android.gms.internal.measurement.zzap r8 = r8.zzc(r9)
            return r8
        L_0x01ef:
            com.google.android.gms.internal.measurement.zzbl r8 = com.google.android.gms.internal.measurement.zzbl.APPLY
            java.lang.String r8 = r8.name()
            com.google.android.gms.internal.measurement.zzh.zzh(r8, r2, r10)
            java.lang.Object r8 = r10.get(r5)
            com.google.android.gms.internal.measurement.zzap r8 = (com.google.android.gms.internal.measurement.zzap) r8
            com.google.android.gms.internal.measurement.zzap r8 = r9.zzb(r8)
            java.lang.Object r0 = r10.get(r4)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r9.zzb(r0)
            java.lang.String r0 = r0.zzi()
            java.lang.Object r10 = r10.get(r3)
            com.google.android.gms.internal.measurement.zzap r10 = (com.google.android.gms.internal.measurement.zzap) r10
            com.google.android.gms.internal.measurement.zzap r10 = r9.zzb(r10)
            boolean r1 = r10 instanceof com.google.android.gms.internal.measurement.zzae
            if (r1 == 0) goto L_0x0237
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x022f
            com.google.android.gms.internal.measurement.zzae r10 = (com.google.android.gms.internal.measurement.zzae) r10
            java.util.List r10 = r10.zzm()
            com.google.android.gms.internal.measurement.zzap r8 = r8.zzbK(r0, r9, r10)
            return r8
        L_0x022f:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "Function name for apply is undefined"
            r8.<init>(r9)
            throw r8
        L_0x0237:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.Object[] r9 = new java.lang.Object[r4]
            java.lang.Class r10 = r10.getClass()
            java.lang.String r10 = r10.getCanonicalName()
            r9[r5] = r10
            java.lang.String r10 = "Function arguments for Apply are not a list found %s"
            java.lang.String r9 = java.lang.String.format(r10, r9)
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzaz.zza(java.lang.String, com.google.android.gms.internal.measurement.zzg, java.util.List):com.google.android.gms.internal.measurement.zzap");
    }
}
