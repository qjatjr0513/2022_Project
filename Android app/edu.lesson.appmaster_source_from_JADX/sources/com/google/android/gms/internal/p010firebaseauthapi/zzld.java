package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.util.Arrays;
import javax.crypto.Mac;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzld */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzld implements zzek {
    private final ThreadLocal<Mac> zza;
    /* access modifiers changed from: private */
    public final String zzb;
    /* access modifiers changed from: private */
    public final Key zzc;
    private final int zzd;

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzld(java.lang.String r3, java.security.Key r4) throws java.security.GeneralSecurityException {
        /*
            r2 = this;
            r2.<init>()
            com.google.android.gms.internal.firebase-auth-api.zzlc r0 = new com.google.android.gms.internal.firebase-auth-api.zzlc
            r0.<init>(r2)
            r2.zza = r0
            r2.zzb = r3
            r2.zzc = r4
            byte[] r4 = r4.getEncoded()
            int r4 = r4.length
            r1 = 16
            if (r4 < r1) goto L_0x0084
            int r4 = r3.hashCode()
            switch(r4) {
                case -1823053428: goto L_0x0047;
                case 392315023: goto L_0x003d;
                case 392315118: goto L_0x0033;
                case 392316170: goto L_0x0029;
                case 392317873: goto L_0x001f;
                default: goto L_0x001e;
            }
        L_0x001e:
            goto L_0x0051
        L_0x001f:
            java.lang.String r4 = "HMACSHA512"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x0051
            r4 = 4
            goto L_0x0052
        L_0x0029:
            java.lang.String r4 = "HMACSHA384"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x0051
            r4 = 3
            goto L_0x0052
        L_0x0033:
            java.lang.String r4 = "HMACSHA256"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x0051
            r4 = 2
            goto L_0x0052
        L_0x003d:
            java.lang.String r4 = "HMACSHA224"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x0051
            r4 = 1
            goto L_0x0052
        L_0x0047:
            java.lang.String r4 = "HMACSHA1"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x0051
            r4 = 0
            goto L_0x0052
        L_0x0051:
            r4 = -1
        L_0x0052:
            switch(r4) {
                case 0: goto L_0x0072;
                case 1: goto L_0x006f;
                case 2: goto L_0x006c;
                case 3: goto L_0x0069;
                case 4: goto L_0x0064;
                default: goto L_0x0055;
            }
        L_0x0055:
            java.security.NoSuchAlgorithmException r4 = new java.security.NoSuchAlgorithmException
            java.lang.String r0 = "unknown Hmac algorithm: "
            int r1 = r3.length()
            if (r1 == 0) goto L_0x007b
            java.lang.String r3 = r0.concat(r3)
            goto L_0x0080
        L_0x0064:
            r3 = 64
            r2.zzd = r3
            goto L_0x0076
        L_0x0069:
            r3 = 48
            goto L_0x0074
        L_0x006c:
            r3 = 32
            goto L_0x0074
        L_0x006f:
            r3 = 28
            goto L_0x0074
        L_0x0072:
            r3 = 20
        L_0x0074:
            r2.zzd = r3
        L_0x0076:
            r0.get()
            return
        L_0x007b:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r0)
        L_0x0080:
            r4.<init>(r3)
            throw r4
        L_0x0084:
            java.security.InvalidAlgorithmParameterException r3 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r4 = "key size too small, need at least 16 bytes"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p010firebaseauthapi.zzld.<init>(java.lang.String, java.security.Key):void");
    }

    public final byte[] zza(byte[] bArr, int i) throws GeneralSecurityException {
        if (i <= this.zzd) {
            this.zza.get().update(bArr);
            return Arrays.copyOf(this.zza.get().doFinal(), i);
        }
        throw new InvalidAlgorithmParameterException("tag size too big");
    }
}
