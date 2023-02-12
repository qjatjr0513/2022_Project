package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.net.URL;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzid implements Runnable {
    final /* synthetic */ zzie zza;
    private final URL zzb;
    private final String zzc;
    private final zzft zzd;

    public zzid(zzie zzie, String str, URL url, byte[] bArr, Map map, zzft zzft, byte[] bArr2) {
        this.zza = zzie;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzft);
        this.zzb = url;
        this.zzd = zzft;
        this.zzc = str;
    }

    private final void zzb(int i, Exception exc, byte[] bArr, Map<String, List<String>> map) {
        this.zza.zzs.zzaz().zzp(new zzic(this, i, exc, bArr, map));
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x006d A[SYNTHETIC, Splitter:B:29:0x006d] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r10 = this;
            com.google.android.gms.measurement.internal.zzie r0 = r10.zza
            r0.zzax()
            r0 = 0
            r1 = 0
            com.google.android.gms.measurement.internal.zzie r2 = r10.zza     // Catch:{ IOException -> 0x009f, all -> 0x008f }
            java.net.URL r3 = r10.zzb     // Catch:{ IOException -> 0x009f, all -> 0x008f }
            java.net.URLConnection r3 = r3.openConnection()     // Catch:{ IOException -> 0x009f, all -> 0x008f }
            boolean r4 = r3 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x009f, all -> 0x008f }
            if (r4 == 0) goto L_0x0087
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch:{ IOException -> 0x009f, all -> 0x008f }
            r3.setDefaultUseCaches(r0)     // Catch:{ IOException -> 0x009f, all -> 0x008f }
            com.google.android.gms.measurement.internal.zzfv r4 = r2.zzs     // Catch:{ IOException -> 0x009f, all -> 0x008f }
            r4.zzf()     // Catch:{ IOException -> 0x009f, all -> 0x008f }
            r4 = 60000(0xea60, float:8.4078E-41)
            r3.setConnectTimeout(r4)     // Catch:{ IOException -> 0x009f, all -> 0x008f }
            com.google.android.gms.measurement.internal.zzfv r2 = r2.zzs     // Catch:{ IOException -> 0x009f, all -> 0x008f }
            r2.zzf()     // Catch:{ IOException -> 0x009f, all -> 0x008f }
            r2 = 61000(0xee48, float:8.5479E-41)
            r3.setReadTimeout(r2)     // Catch:{ IOException -> 0x009f, all -> 0x008f }
            r3.setInstanceFollowRedirects(r0)     // Catch:{ IOException -> 0x009f, all -> 0x008f }
            r2 = 1
            r3.setDoInput(r2)     // Catch:{ IOException -> 0x009f, all -> 0x008f }
            int r2 = r3.getResponseCode()     // Catch:{ IOException -> 0x0081, all -> 0x007b }
            java.util.Map r4 = r3.getHeaderFields()     // Catch:{ IOException -> 0x0078, all -> 0x0075 }
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0069 }
            r5.<init>()     // Catch:{ all -> 0x0069 }
            java.io.InputStream r6 = r3.getInputStream()     // Catch:{ all -> 0x0069 }
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r7 = new byte[r7]     // Catch:{ all -> 0x0067 }
        L_0x004a:
            int r8 = r6.read(r7)     // Catch:{ all -> 0x0067 }
            if (r8 <= 0) goto L_0x0054
            r5.write(r7, r0, r8)     // Catch:{ all -> 0x0067 }
            goto L_0x004a
        L_0x0054:
            byte[] r0 = r5.toByteArray()     // Catch:{ all -> 0x0067 }
            if (r6 == 0) goto L_0x005d
            r6.close()     // Catch:{ IOException -> 0x0073, all -> 0x0071 }
        L_0x005d:
            if (r3 == 0) goto L_0x0062
            r3.disconnect()
        L_0x0062:
            r10.zzb(r2, r1, r0, r4)
            return
        L_0x0067:
            r0 = move-exception
            goto L_0x006b
        L_0x0069:
            r0 = move-exception
            r6 = r1
        L_0x006b:
            if (r6 == 0) goto L_0x0070
            r6.close()     // Catch:{ IOException -> 0x0073, all -> 0x0071 }
        L_0x0070:
            throw r0     // Catch:{ IOException -> 0x0073, all -> 0x0071 }
        L_0x0071:
            r0 = move-exception
            goto L_0x0095
        L_0x0073:
            r0 = move-exception
            goto L_0x00a5
        L_0x0075:
            r0 = move-exception
            r4 = r1
            goto L_0x0095
        L_0x0078:
            r0 = move-exception
            r4 = r1
            goto L_0x00a5
        L_0x007b:
            r2 = move-exception
            r4 = r1
            r9 = r2
            r2 = r0
            r0 = r9
            goto L_0x0095
        L_0x0081:
            r2 = move-exception
            r4 = r1
            r9 = r2
            r2 = r0
            r0 = r9
            goto L_0x00a5
        L_0x0087:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException -> 0x009f, all -> 0x008f }
            java.lang.String r3 = "Failed to obtain HTTP connection"
            r2.<init>(r3)     // Catch:{ IOException -> 0x009f, all -> 0x008f }
            throw r2     // Catch:{ IOException -> 0x009f, all -> 0x008f }
        L_0x008f:
            r2 = move-exception
            r3 = r1
            r4 = r3
            r9 = r2
            r2 = r0
            r0 = r9
        L_0x0095:
            if (r3 == 0) goto L_0x009a
            r3.disconnect()
        L_0x009a:
            r10.zzb(r2, r1, r1, r4)
            throw r0
        L_0x009f:
            r2 = move-exception
            r3 = r1
            r4 = r3
            r9 = r2
            r2 = r0
            r0 = r9
        L_0x00a5:
            if (r3 == 0) goto L_0x00aa
            r3.disconnect()
        L_0x00aa:
            r10.zzb(r2, r0, r1, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzid.run():void");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(int i, Exception exc, byte[] bArr, Map map) {
        zzft zzft = this.zzd;
        zzft.zza.zzC(this.zzc, i, exc, bArr, map);
    }
}
