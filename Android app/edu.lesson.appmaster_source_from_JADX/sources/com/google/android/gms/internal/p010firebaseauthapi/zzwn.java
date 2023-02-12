package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.ActionCodeSettings;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwn */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzwn implements zzui {
    private final String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private ActionCodeSettings zze;
    private String zzf;

    public zzwn(int i) {
        String str;
        switch (i) {
            case 1:
                str = "PASSWORD_RESET";
                break;
            case 4:
                str = "VERIFY_EMAIL";
                break;
            case 6:
                str = "EMAIL_SIGNIN";
                break;
            case 7:
                str = "VERIFY_AND_CHANGE_EMAIL";
                break;
            default:
                str = "REQUEST_TYPE_UNSET_ENUM_VALUE";
                break;
        }
        this.zza = str;
    }

    private zzwn(int i, ActionCodeSettings actionCodeSettings, String str, String str2, String str3, String str4) {
        this.zza = "VERIFY_AND_CHANGE_EMAIL";
        this.zze = (ActionCodeSettings) Preconditions.checkNotNull(actionCodeSettings);
        this.zzb = null;
        this.zzc = str2;
        this.zzd = str3;
        this.zzf = null;
    }

    public static zzwn zzc(ActionCodeSettings actionCodeSettings, String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(actionCodeSettings);
        return new zzwn(7, actionCodeSettings, (String) null, str2, str, (String) null);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zza() throws org.json.JSONException {
        /*
            r5 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = r5.zza
            int r2 = r1.hashCode()
            r3 = 1
            r4 = 0
            switch(r2) {
                case -1452371317: goto L_0x002f;
                case -1341836234: goto L_0x0025;
                case -1099157829: goto L_0x001b;
                case 870738373: goto L_0x0011;
                default: goto L_0x0010;
            }
        L_0x0010:
            goto L_0x0039
        L_0x0011:
            java.lang.String r2 = "EMAIL_SIGNIN"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0039
            r1 = 2
            goto L_0x003a
        L_0x001b:
            java.lang.String r2 = "VERIFY_AND_CHANGE_EMAIL"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0039
            r1 = 3
            goto L_0x003a
        L_0x0025:
            java.lang.String r2 = "VERIFY_EMAIL"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0039
            r1 = r3
            goto L_0x003a
        L_0x002f:
            java.lang.String r2 = "PASSWORD_RESET"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0039
            r1 = r4
            goto L_0x003a
        L_0x0039:
            r1 = -1
        L_0x003a:
            switch(r1) {
                case 0: goto L_0x0045;
                case 1: goto L_0x0043;
                case 2: goto L_0x0041;
                case 3: goto L_0x003f;
                default: goto L_0x003d;
            }
        L_0x003d:
            r3 = r4
            goto L_0x0046
        L_0x003f:
            r3 = 7
            goto L_0x0046
        L_0x0041:
            r3 = 6
            goto L_0x0046
        L_0x0043:
            r3 = 4
            goto L_0x0046
        L_0x0045:
        L_0x0046:
            java.lang.String r1 = "requestType"
            r0.put(r1, r3)
            java.lang.String r1 = r5.zzb
            if (r1 == 0) goto L_0x0055
            java.lang.String r2 = "email"
            r0.put(r2, r1)
        L_0x0055:
            java.lang.String r1 = r5.zzc
            if (r1 == 0) goto L_0x005e
            java.lang.String r2 = "newEmail"
            r0.put(r2, r1)
        L_0x005e:
            java.lang.String r1 = r5.zzd
            if (r1 == 0) goto L_0x0067
            java.lang.String r2 = "idToken"
            r0.put(r2, r1)
        L_0x0067:
            com.google.firebase.auth.ActionCodeSettings r1 = r5.zze
            if (r1 == 0) goto L_0x00f1
            boolean r1 = r1.getAndroidInstallApp()
            java.lang.String r2 = "androidInstallApp"
            r0.put(r2, r1)
            com.google.firebase.auth.ActionCodeSettings r1 = r5.zze
            boolean r1 = r1.canHandleCodeInApp()
            java.lang.String r2 = "canHandleCodeInApp"
            r0.put(r2, r1)
            com.google.firebase.auth.ActionCodeSettings r1 = r5.zze
            java.lang.String r1 = r1.getUrl()
            if (r1 == 0) goto L_0x0092
            com.google.firebase.auth.ActionCodeSettings r1 = r5.zze
            java.lang.String r1 = r1.getUrl()
            java.lang.String r2 = "continueUrl"
            r0.put(r2, r1)
        L_0x0092:
            com.google.firebase.auth.ActionCodeSettings r1 = r5.zze
            java.lang.String r1 = r1.getIOSBundle()
            if (r1 == 0) goto L_0x00a5
            com.google.firebase.auth.ActionCodeSettings r1 = r5.zze
            java.lang.String r1 = r1.getIOSBundle()
            java.lang.String r2 = "iosBundleId"
            r0.put(r2, r1)
        L_0x00a5:
            com.google.firebase.auth.ActionCodeSettings r1 = r5.zze
            java.lang.String r1 = r1.zzd()
            if (r1 == 0) goto L_0x00b8
            com.google.firebase.auth.ActionCodeSettings r1 = r5.zze
            java.lang.String r1 = r1.zzd()
            java.lang.String r2 = "iosAppStoreId"
            r0.put(r2, r1)
        L_0x00b8:
            com.google.firebase.auth.ActionCodeSettings r1 = r5.zze
            java.lang.String r1 = r1.getAndroidPackageName()
            if (r1 == 0) goto L_0x00cb
            com.google.firebase.auth.ActionCodeSettings r1 = r5.zze
            java.lang.String r1 = r1.getAndroidPackageName()
            java.lang.String r2 = "androidPackageName"
            r0.put(r2, r1)
        L_0x00cb:
            com.google.firebase.auth.ActionCodeSettings r1 = r5.zze
            java.lang.String r1 = r1.getAndroidMinimumVersion()
            if (r1 == 0) goto L_0x00de
            com.google.firebase.auth.ActionCodeSettings r1 = r5.zze
            java.lang.String r1 = r1.getAndroidMinimumVersion()
            java.lang.String r2 = "androidMinimumVersion"
            r0.put(r2, r1)
        L_0x00de:
            com.google.firebase.auth.ActionCodeSettings r1 = r5.zze
            java.lang.String r1 = r1.zzc()
            if (r1 == 0) goto L_0x00f1
            com.google.firebase.auth.ActionCodeSettings r1 = r5.zze
            java.lang.String r1 = r1.zzc()
            java.lang.String r2 = "dynamicLinkDomain"
            r0.put(r2, r1)
        L_0x00f1:
            java.lang.String r1 = r5.zzf
            if (r1 == 0) goto L_0x00fa
            java.lang.String r2 = "tenantId"
            r0.put(r2, r1)
        L_0x00fa:
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p010firebaseauthapi.zzwn.zza():java.lang.String");
    }

    public final ActionCodeSettings zzb() {
        return this.zze;
    }

    public final zzwn zzd(ActionCodeSettings actionCodeSettings) {
        this.zze = (ActionCodeSettings) Preconditions.checkNotNull(actionCodeSettings);
        return this;
    }

    public final zzwn zze(String str) {
        this.zzb = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzwn zzf(String str) {
        this.zzf = str;
        return this;
    }

    public final zzwn zzg(String str) {
        this.zzd = Preconditions.checkNotEmpty(str);
        return this;
    }
}
