package com.google.android.libraries.places.api.model;

import com.google.android.libraries.places.internal.zzgf;
import com.google.android.libraries.places.internal.zzgg;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzbe {
    private static final zzgg<Integer, DayOfWeek> zza;
    private static final LocalTime zzb = LocalTime.newInstance(23, 59);

    static {
        zzgf zzgf = new zzgf();
        zzgf.zza(1, DayOfWeek.SUNDAY);
        zzgf.zza(2, DayOfWeek.MONDAY);
        zzgf.zza(3, DayOfWeek.TUESDAY);
        zzgf.zza(4, DayOfWeek.WEDNESDAY);
        zzgf.zza(5, DayOfWeek.THURSDAY);
        zzgf.zza(6, DayOfWeek.FRIDAY);
        zzgf.zza(7, DayOfWeek.SATURDAY);
        zza = zzgf.zzb();
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x007c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.Boolean zza(com.google.android.libraries.places.api.model.Place r12, long r13) {
        /*
            com.google.android.libraries.places.api.model.OpeningHours r0 = r12.getOpeningHours()
            r1 = 0
            if (r0 == 0) goto L_0x01c0
            java.lang.Integer r0 = r12.getUtcOffsetMinutes()
            if (r0 != 0) goto L_0x000f
            goto L_0x01c0
        L_0x000f:
            com.google.android.libraries.places.api.model.OpeningHours r0 = r12.getOpeningHours()
            java.util.List r0 = r0.getPeriods()
            boolean r2 = r0.isEmpty()
            r3 = 0
            if (r2 != 0) goto L_0x01ba
            int r2 = r0.size()
            r4 = 1
            if (r2 != r4) goto L_0x0072
            java.lang.Object r2 = r0.get(r3)
            com.google.android.libraries.places.api.model.Period r2 = (com.google.android.libraries.places.api.model.Period) r2
            com.google.android.libraries.places.api.model.TimeOfWeek r2 = r2.getClose()
            if (r2 != 0) goto L_0x0072
            java.lang.Object r2 = r0.get(r3)
            com.google.android.libraries.places.api.model.Period r2 = (com.google.android.libraries.places.api.model.Period) r2
            com.google.android.libraries.places.api.model.TimeOfWeek r2 = r2.getOpen()
            com.google.android.libraries.places.api.model.DayOfWeek r2 = r2.getDay()
            com.google.android.libraries.places.api.model.DayOfWeek r5 = com.google.android.libraries.places.api.model.DayOfWeek.SUNDAY
            if (r2 != r5) goto L_0x0072
            java.lang.Object r2 = r0.get(r3)
            com.google.android.libraries.places.api.model.Period r2 = (com.google.android.libraries.places.api.model.Period) r2
            com.google.android.libraries.places.api.model.TimeOfWeek r2 = r2.getOpen()
            com.google.android.libraries.places.api.model.LocalTime r2 = r2.getTime()
            int r2 = r2.getHours()
            if (r2 != 0) goto L_0x0072
            java.lang.Object r2 = r0.get(r3)
            com.google.android.libraries.places.api.model.Period r2 = (com.google.android.libraries.places.api.model.Period) r2
            com.google.android.libraries.places.api.model.TimeOfWeek r2 = r2.getOpen()
            com.google.android.libraries.places.api.model.LocalTime r2 = r2.getTime()
            int r2 = r2.getMinutes()
            if (r2 == 0) goto L_0x006c
            goto L_0x0072
        L_0x006c:
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r4)
            return r12
        L_0x0072:
            java.util.Iterator r2 = r0.iterator()
        L_0x0076:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x008f
            java.lang.Object r5 = r2.next()
            com.google.android.libraries.places.api.model.Period r5 = (com.google.android.libraries.places.api.model.Period) r5
            com.google.android.libraries.places.api.model.TimeOfWeek r6 = r5.getOpen()
            if (r6 == 0) goto L_0x008e
            com.google.android.libraries.places.api.model.TimeOfWeek r5 = r5.getClose()
            if (r5 != 0) goto L_0x0076
        L_0x008e:
            return r1
        L_0x008f:
            java.lang.Integer r12 = r12.getUtcOffsetMinutes()
            int r12 = r12.intValue()
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MINUTES
            long r5 = (long) r12
            long r5 = r2.toMillis(r5)
            int r2 = (int) r5
            java.lang.String[] r2 = java.util.TimeZone.getAvailableIDs(r2)
            if (r2 == 0) goto L_0x00b1
            int r5 = r2.length
            if (r5 > 0) goto L_0x00a9
            goto L_0x00b1
        L_0x00a9:
            r12 = r2[r3]
            java.util.TimeZone r12 = java.util.TimeZone.getTimeZone(r12)
            goto L_0x00c5
        L_0x00b1:
            java.lang.Object[] r2 = new java.lang.Object[r4]
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            r2[r3] = r12
            java.lang.String r12 = "Cannot find timezone that associates with utcOffsetMinutes %d from Place object."
            java.lang.String r12 = java.lang.String.format(r12, r2)
            java.lang.String r2 = "Places"
            android.util.Log.w(r2, r12)
            r12 = r1
        L_0x00c5:
            if (r12 != 0) goto L_0x00c8
            return r1
        L_0x00c8:
            java.util.Calendar r12 = java.util.Calendar.getInstance(r12)
            r12.setTimeInMillis(r13)
            com.google.android.libraries.places.internal.zzgg<java.lang.Integer, com.google.android.libraries.places.api.model.DayOfWeek> r13 = zza
            r14 = 7
            int r2 = r12.get(r14)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Object r13 = r13.get(r2)
            com.google.android.libraries.places.api.model.DayOfWeek r13 = (com.google.android.libraries.places.api.model.DayOfWeek) r13
            r2 = 11
            int r2 = r12.get(r2)
            r5 = 12
            int r12 = r12.get(r5)
            com.google.android.libraries.places.api.model.LocalTime r12 = com.google.android.libraries.places.api.model.LocalTime.newInstance(r2, r12)
            java.util.EnumMap r2 = new java.util.EnumMap
            java.lang.Class<com.google.android.libraries.places.api.model.DayOfWeek> r5 = com.google.android.libraries.places.api.model.DayOfWeek.class
            r2.<init>(r5)
            boolean r5 = r0.isEmpty()
            if (r5 == 0) goto L_0x00ff
            goto L_0x018c
        L_0x00ff:
            java.lang.Object r5 = r0.get(r3)
            com.google.android.libraries.places.api.model.Period r5 = (com.google.android.libraries.places.api.model.Period) r5
            r6 = r3
        L_0x0107:
            if (r5 == 0) goto L_0x018c
            com.google.android.libraries.places.api.model.TimeOfWeek r7 = r5.getOpen()
            com.google.android.libraries.places.api.model.TimeOfWeek r8 = r5.getClose()
            com.google.android.libraries.places.api.model.DayOfWeek r9 = r7.getDay()
            com.google.android.libraries.places.api.model.LocalTime r10 = r7.getTime()
            com.google.android.libraries.places.api.model.DayOfWeek r7 = r7.getDay()
            com.google.android.libraries.places.api.model.DayOfWeek r11 = r8.getDay()
            if (r7 == r11) goto L_0x0161
            com.google.android.libraries.places.api.model.LocalTime r7 = zzb
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.lang.Object r8 = zzb(r2, r9, r8)
            java.util.List r8 = (java.util.List) r8
            com.google.android.libraries.places.internal.zzgp r7 = com.google.android.libraries.places.internal.zzgp.zzc(r10, r7)
            r8.add(r7)
            r2.put(r9, r8)
            com.google.android.libraries.places.api.model.DayOfWeek[] r7 = com.google.android.libraries.places.api.model.DayOfWeek.values()
            int r8 = r9.ordinal()
            int r8 = r8 + r4
            int r8 = r8 % r14
            r7 = r7[r8]
            com.google.android.libraries.places.api.model.LocalTime r8 = com.google.android.libraries.places.api.model.LocalTime.newInstance(r3, r3)
            com.google.android.libraries.places.api.model.TimeOfWeek r7 = com.google.android.libraries.places.api.model.TimeOfWeek.newInstance(r7, r8)
            com.google.android.libraries.places.api.model.TimeOfWeek r5 = r5.getClose()
            com.google.android.libraries.places.api.model.Period$Builder r8 = com.google.android.libraries.places.api.model.Period.builder()
            r8.setOpen(r7)
            r8.setClose(r5)
            com.google.android.libraries.places.api.model.Period r5 = r8.build()
            goto L_0x0107
        L_0x0161:
            com.google.android.libraries.places.api.model.LocalTime r5 = r8.getTime()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.lang.Object r7 = zzb(r2, r9, r7)
            java.util.List r7 = (java.util.List) r7
            com.google.android.libraries.places.internal.zzgp r5 = com.google.android.libraries.places.internal.zzgp.zzd(r10, r5)
            r7.add(r5)
            r2.put(r9, r7)
            int r6 = r6 + 1
            int r5 = r0.size()
            if (r6 < r5) goto L_0x0184
            r5 = r1
            goto L_0x0107
        L_0x0184:
            java.lang.Object r5 = r0.get(r6)
            com.google.android.libraries.places.api.model.Period r5 = (com.google.android.libraries.places.api.model.Period) r5
            goto L_0x0107
        L_0x018c:
            java.lang.Object r13 = r2.get(r13)
            java.util.List r13 = (java.util.List) r13
            if (r13 != 0) goto L_0x0199
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r3)
            return r12
        L_0x0199:
            java.util.Iterator r13 = r13.iterator()
        L_0x019d:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x01b4
            java.lang.Object r14 = r13.next()
            com.google.android.libraries.places.internal.zzgp r14 = (com.google.android.libraries.places.internal.zzgp) r14
            boolean r14 = r14.zze(r12)
            if (r14 == 0) goto L_0x019d
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r4)
            return r12
        L_0x01b4:
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r3)
            return r12
        L_0x01ba:
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r3)
            return r12
        L_0x01c0:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.api.model.zzbe.zza(com.google.android.libraries.places.api.model.Place, long):java.lang.Boolean");
    }

    private static <K, V> V zzb(Map<K, V> map, K k, V v) {
        return map.containsKey(k) ? map.get(k) : v;
    }
}
