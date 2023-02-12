package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.net.URL;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
final class zzeq implements Runnable {
    final /* synthetic */ zzer zza;
    private final URL zzb;
    private final byte[] zzc;
    private final zzen zzd;
    private final String zze;
    private final Map<String, String> zzf;

    public zzeq(zzer zzer, String str, URL url, byte[] bArr, Map<String, String> map, zzen zzen) {
        this.zza = zzer;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzen);
        this.zzb = url;
        this.zzc = bArr;
        this.zzd = zzen;
        this.zze = str;
        this.zzf = map;
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x00fa A[SYNTHETIC, Splitter:B:46:0x00fa] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x012d A[SYNTHETIC, Splitter:B:67:0x012d] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x016d A[SYNTHETIC, Splitter:B:78:0x016d] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0189  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r14 = this;
            java.lang.String r0 = "Error closing HTTP compressed POST connection output stream. appId"
            com.google.android.gms.measurement.internal.zzer r1 = r14.zza
            r1.zzax()
            r1 = 0
            r2 = 0
            com.google.android.gms.measurement.internal.zzer r3 = r14.zza     // Catch:{ IOException -> 0x0165, all -> 0x0126 }
            java.net.URL r4 = r14.zzb     // Catch:{ IOException -> 0x0165, all -> 0x0126 }
            java.net.URLConnection r4 = r4.openConnection()     // Catch:{ IOException -> 0x0165, all -> 0x0126 }
            boolean r5 = r4 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x0165, all -> 0x0126 }
            if (r5 == 0) goto L_0x011e
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ IOException -> 0x0165, all -> 0x0126 }
            r4.setDefaultUseCaches(r1)     // Catch:{ IOException -> 0x0165, all -> 0x0126 }
            com.google.android.gms.measurement.internal.zzfv r5 = r3.zzs     // Catch:{ IOException -> 0x0165, all -> 0x0126 }
            r5.zzf()     // Catch:{ IOException -> 0x0165, all -> 0x0126 }
            r5 = 60000(0xea60, float:8.4078E-41)
            r4.setConnectTimeout(r5)     // Catch:{ IOException -> 0x0165, all -> 0x0126 }
            com.google.android.gms.measurement.internal.zzfv r3 = r3.zzs     // Catch:{ IOException -> 0x0165, all -> 0x0126 }
            r3.zzf()     // Catch:{ IOException -> 0x0165, all -> 0x0126 }
            r3 = 61000(0xee48, float:8.5479E-41)
            r4.setReadTimeout(r3)     // Catch:{ IOException -> 0x0165, all -> 0x0126 }
            r4.setInstanceFollowRedirects(r1)     // Catch:{ IOException -> 0x0165, all -> 0x0126 }
            r3 = 1
            r4.setDoInput(r3)     // Catch:{ IOException -> 0x0165, all -> 0x0126 }
            java.util.Map<java.lang.String, java.lang.String> r5 = r14.zzf     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            if (r5 == 0) goto L_0x005f
            java.util.Set r5 = r5.entrySet()     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
        L_0x0043:
            boolean r6 = r5.hasNext()     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            if (r6 == 0) goto L_0x005f
            java.lang.Object r6 = r5.next()     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            java.lang.Object r7 = r6.getKey()     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            java.lang.Object r6 = r6.getValue()     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            r4.addRequestProperty(r7, r6)     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            goto L_0x0043
        L_0x005f:
            byte[] r5 = r14.zzc     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            if (r5 == 0) goto L_0x00af
            com.google.android.gms.measurement.internal.zzer r5 = r14.zza     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            com.google.android.gms.measurement.internal.zzks r5 = r5.zzf     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            com.google.android.gms.measurement.internal.zzku r5 = r5.zzu()     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            byte[] r6 = r14.zzc     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            byte[] r5 = r5.zzz(r6)     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            com.google.android.gms.measurement.internal.zzer r6 = r14.zza     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            com.google.android.gms.measurement.internal.zzfv r6 = r6.zzs     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            com.google.android.gms.measurement.internal.zzel r6 = r6.zzay()     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            com.google.android.gms.measurement.internal.zzej r6 = r6.zzj()     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            int r7 = r5.length     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            java.lang.String r8 = "Uploading data. size"
            java.lang.Integer r9 = java.lang.Integer.valueOf(r7)     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            r6.zzb(r8, r9)     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            r4.setDoOutput(r3)     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            java.lang.String r3 = "Content-Encoding"
            java.lang.String r6 = "gzip"
            r4.addRequestProperty(r3, r6)     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            r4.setFixedLengthStreamingMode(r7)     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            r4.connect()     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            java.io.OutputStream r3 = r4.getOutputStream()     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            r3.write(r5)     // Catch:{ IOException -> 0x00a8, all -> 0x00a2 }
            r3.close()     // Catch:{ IOException -> 0x00a8, all -> 0x00a2 }
            goto L_0x00af
        L_0x00a2:
            r5 = move-exception
            r9 = r1
            r12 = r2
            r2 = r3
            goto L_0x012b
        L_0x00a8:
            r5 = move-exception
            r9 = r1
            r12 = r2
            r2 = r3
            r10 = r5
            goto L_0x016b
        L_0x00af:
            int r8 = r4.getResponseCode()     // Catch:{ IOException -> 0x0119, all -> 0x0114 }
            java.util.Map r11 = r4.getHeaderFields()     // Catch:{ IOException -> 0x010e, all -> 0x010a }
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x00f6 }
            r3.<init>()     // Catch:{ all -> 0x00f6 }
            java.io.InputStream r5 = r4.getInputStream()     // Catch:{ all -> 0x00f6 }
            r6 = 1024(0x400, float:1.435E-42)
            byte[] r6 = new byte[r6]     // Catch:{ all -> 0x00f4 }
        L_0x00c4:
            int r7 = r5.read(r6)     // Catch:{ all -> 0x00f4 }
            if (r7 <= 0) goto L_0x00ce
            r3.write(r6, r1, r7)     // Catch:{ all -> 0x00f4 }
            goto L_0x00c4
        L_0x00ce:
            byte[] r10 = r3.toByteArray()     // Catch:{ all -> 0x00f4 }
            if (r5 == 0) goto L_0x00d7
            r5.close()     // Catch:{ IOException -> 0x0103, all -> 0x00fe }
        L_0x00d7:
            if (r4 == 0) goto L_0x00dc
            r4.disconnect()
        L_0x00dc:
            com.google.android.gms.measurement.internal.zzer r0 = r14.zza
            com.google.android.gms.measurement.internal.zzfv r0 = r0.zzs
            com.google.android.gms.measurement.internal.zzfs r0 = r0.zzaz()
            com.google.android.gms.measurement.internal.zzep r1 = new com.google.android.gms.measurement.internal.zzep
            java.lang.String r6 = r14.zze
            com.google.android.gms.measurement.internal.zzen r7 = r14.zzd
            r9 = 0
            r12 = 0
            r5 = r1
            r5.<init>(r6, r7, r8, r9, r10, r11, r12)
        L_0x00f0:
            r0.zzp(r1)
            return
        L_0x00f4:
            r1 = move-exception
            goto L_0x00f8
        L_0x00f6:
            r1 = move-exception
            r5 = r2
        L_0x00f8:
            if (r5 == 0) goto L_0x00fd
            r5.close()     // Catch:{ IOException -> 0x0103, all -> 0x00fe }
        L_0x00fd:
            throw r1     // Catch:{ IOException -> 0x0103, all -> 0x00fe }
        L_0x00fe:
            r1 = move-exception
            r5 = r1
            r9 = r8
            r12 = r11
            goto L_0x012b
        L_0x0103:
            r1 = move-exception
            r5 = r1
            r10 = r5
            r9 = r8
            r12 = r11
            goto L_0x016b
        L_0x010a:
            r5 = move-exception
            r12 = r2
            r9 = r8
            goto L_0x012b
        L_0x010e:
            r5 = move-exception
            r12 = r2
            r10 = r5
            r9 = r8
            goto L_0x016b
        L_0x0114:
            r3 = move-exception
            r9 = r1
            r12 = r2
            r5 = r3
            goto L_0x012b
        L_0x0119:
            r3 = move-exception
            r9 = r1
            r12 = r2
            r10 = r3
            goto L_0x016b
        L_0x011e:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ IOException -> 0x0165, all -> 0x0126 }
            java.lang.String r4 = "Failed to obtain HTTP connection"
            r3.<init>(r4)     // Catch:{ IOException -> 0x0165, all -> 0x0126 }
            throw r3     // Catch:{ IOException -> 0x0165, all -> 0x0126 }
        L_0x0126:
            r3 = move-exception
            r5 = r3
            r9 = r1
            r4 = r2
            r12 = r4
        L_0x012b:
            if (r2 == 0) goto L_0x0147
            r2.close()     // Catch:{ IOException -> 0x0131 }
            goto L_0x0147
        L_0x0131:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzer r2 = r14.zza
            com.google.android.gms.measurement.internal.zzfv r2 = r2.zzs
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()
            java.lang.String r3 = r14.zze
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzel.zzn(r3)
            r2.zzc(r0, r3, r1)
        L_0x0147:
            if (r4 == 0) goto L_0x014c
            r4.disconnect()
        L_0x014c:
            com.google.android.gms.measurement.internal.zzer r0 = r14.zza
            com.google.android.gms.measurement.internal.zzfv r0 = r0.zzs
            com.google.android.gms.measurement.internal.zzfs r0 = r0.zzaz()
            com.google.android.gms.measurement.internal.zzep r1 = new com.google.android.gms.measurement.internal.zzep
            java.lang.String r7 = r14.zze
            com.google.android.gms.measurement.internal.zzen r8 = r14.zzd
            r10 = 0
            r11 = 0
            r13 = 0
            r6 = r1
            r6.<init>(r7, r8, r9, r10, r11, r12, r13)
            r0.zzp(r1)
            throw r5
        L_0x0165:
            r3 = move-exception
            r5 = r3
            r9 = r1
            r4 = r2
            r12 = r4
            r10 = r5
        L_0x016b:
            if (r2 == 0) goto L_0x0187
            r2.close()     // Catch:{ IOException -> 0x0171 }
            goto L_0x0187
        L_0x0171:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzer r2 = r14.zza
            com.google.android.gms.measurement.internal.zzfv r2 = r2.zzs
            com.google.android.gms.measurement.internal.zzel r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzd()
            java.lang.String r3 = r14.zze
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzel.zzn(r3)
            r2.zzc(r0, r3, r1)
        L_0x0187:
            if (r4 == 0) goto L_0x018c
            r4.disconnect()
        L_0x018c:
            com.google.android.gms.measurement.internal.zzer r0 = r14.zza
            com.google.android.gms.measurement.internal.zzfv r0 = r0.zzs
            com.google.android.gms.measurement.internal.zzfs r0 = r0.zzaz()
            com.google.android.gms.measurement.internal.zzep r1 = new com.google.android.gms.measurement.internal.zzep
            java.lang.String r7 = r14.zze
            com.google.android.gms.measurement.internal.zzen r8 = r14.zzd
            r11 = 0
            r13 = 0
            r6 = r1
            r6.<init>(r7, r8, r9, r10, r11, r12, r13)
            goto L_0x00f0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzeq.run():void");
    }
}
