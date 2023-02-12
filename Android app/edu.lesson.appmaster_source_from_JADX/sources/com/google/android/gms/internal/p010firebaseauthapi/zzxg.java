package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxg */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzxg implements zzui {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private String zzf;
    private final zzxo zzg = new zzxo((List<String>) null);
    private final zzxo zzh = new zzxo((List<String>) null);
    private String zzi;

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zza() throws org.json.JSONException {
        /*
            r10 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "returnSecureToken"
            r2 = 1
            r0.put(r1, r2)
            com.google.android.gms.internal.firebase-auth-api.zzxo r1 = r10.zzh
            java.util.List r1 = r1.zzb()
            boolean r1 = r1.isEmpty()
            r3 = 0
            if (r1 != 0) goto L_0x003a
            com.google.android.gms.internal.firebase-auth-api.zzxo r1 = r10.zzh
            java.util.List r1 = r1.zzb()
            org.json.JSONArray r4 = new org.json.JSONArray
            r4.<init>()
            r5 = r3
        L_0x0024:
            int r6 = r1.size()
            if (r5 >= r6) goto L_0x0034
            java.lang.Object r6 = r1.get(r5)
            r4.put(r6)
            int r5 = r5 + 1
            goto L_0x0024
        L_0x0034:
            java.lang.String r1 = "deleteProvider"
            r0.put(r1, r4)
        L_0x003a:
            com.google.android.gms.internal.firebase-auth-api.zzxo r1 = r10.zzg
            java.util.List r1 = r1.zzb()
            int r4 = r1.size()
            int[] r5 = new int[r4]
            r6 = r3
        L_0x0047:
            int r7 = r1.size()
            if (r6 >= r7) goto L_0x0096
            java.lang.Object r7 = r1.get(r6)
            java.lang.String r7 = (java.lang.String) r7
            int r8 = r7.hashCode()
            r9 = 2
            switch(r8) {
                case -333046776: goto L_0x007b;
                case 66081660: goto L_0x0071;
                case 1939891618: goto L_0x0067;
                case 1999612571: goto L_0x005c;
                default: goto L_0x005b;
            }
        L_0x005b:
            goto L_0x0085
        L_0x005c:
            java.lang.String r8 = "PASSWORD"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x005b
            r7 = r9
            goto L_0x0086
        L_0x0067:
            java.lang.String r8 = "PHOTO_URL"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x005b
            r7 = 3
            goto L_0x0086
        L_0x0071:
            java.lang.String r8 = "EMAIL"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x005b
            r7 = r3
            goto L_0x0086
        L_0x007b:
            java.lang.String r8 = "DISPLAY_NAME"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x005b
            r7 = r2
            goto L_0x0086
        L_0x0085:
            r7 = -1
        L_0x0086:
            switch(r7) {
                case 0: goto L_0x0090;
                case 1: goto L_0x008f;
                case 2: goto L_0x008d;
                case 3: goto L_0x008b;
                default: goto L_0x0089;
            }
        L_0x0089:
            r9 = r3
            goto L_0x0091
        L_0x008b:
            r9 = 4
            goto L_0x0091
        L_0x008d:
            r9 = 5
            goto L_0x0091
        L_0x008f:
            goto L_0x0091
        L_0x0090:
            r9 = r2
        L_0x0091:
            r5[r6] = r9
            int r6 = r6 + 1
            goto L_0x0047
        L_0x0096:
            if (r4 <= 0) goto L_0x00ad
            org.json.JSONArray r1 = new org.json.JSONArray
            r1.<init>()
        L_0x009d:
            if (r3 >= r4) goto L_0x00a7
            r2 = r5[r3]
            r1.put(r2)
            int r3 = r3 + 1
            goto L_0x009d
        L_0x00a7:
            java.lang.String r2 = "deleteAttribute"
            r0.put(r2, r1)
        L_0x00ad:
            java.lang.String r1 = r10.zza
            if (r1 == 0) goto L_0x00b6
            java.lang.String r2 = "idToken"
            r0.put(r2, r1)
        L_0x00b6:
            java.lang.String r1 = r10.zzc
            if (r1 == 0) goto L_0x00bf
            java.lang.String r2 = "email"
            r0.put(r2, r1)
        L_0x00bf:
            java.lang.String r1 = r10.zzd
            if (r1 == 0) goto L_0x00c8
            java.lang.String r2 = "password"
            r0.put(r2, r1)
        L_0x00c8:
            java.lang.String r1 = r10.zzb
            if (r1 == 0) goto L_0x00d1
            java.lang.String r2 = "displayName"
            r0.put(r2, r1)
        L_0x00d1:
            java.lang.String r1 = r10.zzf
            if (r1 == 0) goto L_0x00da
            java.lang.String r2 = "photoUrl"
            r0.put(r2, r1)
        L_0x00da:
            java.lang.String r1 = r10.zze
            if (r1 == 0) goto L_0x00e3
            java.lang.String r2 = "oobCode"
            r0.put(r2, r1)
        L_0x00e3:
            java.lang.String r1 = r10.zzi
            if (r1 == 0) goto L_0x00ec
            java.lang.String r2 = "tenantId"
            r0.put(r2, r1)
        L_0x00ec:
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p010firebaseauthapi.zzxg.zza():java.lang.String");
    }

    public final zzxg zzb(String str) {
        Preconditions.checkNotEmpty(str);
        this.zzh.zzb().add(str);
        return this;
    }

    public final zzxg zzc(String str) {
        if (str == null) {
            this.zzg.zzb().add("DISPLAY_NAME");
        } else {
            this.zzb = str;
        }
        return this;
    }

    public final zzxg zzd(String str) {
        if (str == null) {
            this.zzg.zzb().add("EMAIL");
        } else {
            this.zzc = str;
        }
        return this;
    }

    public final zzxg zze(String str) {
        this.zza = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzxg zzf(String str) {
        this.zze = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzxg zzg(String str) {
        if (str == null) {
            this.zzg.zzb().add("PASSWORD");
        } else {
            this.zzd = str;
        }
        return this;
    }

    public final zzxg zzh(String str) {
        if (str == null) {
            this.zzg.zzb().add("PHOTO_URL");
        } else {
            this.zzf = str;
        }
        return this;
    }

    public final zzxg zzi(String str) {
        this.zzi = str;
        return this;
    }

    public final String zzj() {
        return this.zzb;
    }

    public final String zzk() {
        return this.zzc;
    }

    public final String zzl() {
        return this.zzd;
    }

    public final String zzm() {
        return this.zzf;
    }

    public final boolean zzn(String str) {
        Preconditions.checkNotEmpty(str);
        return this.zzg.zzb().contains(str);
    }
}
