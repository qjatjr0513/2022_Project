package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxb */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzxb extends AbstractSafeParcelable implements zzuj<zzxb> {
    public static final Parcelable.Creator<zzxb> CREATOR = new zzxc();
    private static final String zza = zzxb.class.getSimpleName();
    private String zzb;
    private String zzc;
    private String zzd;
    private zzwu zze;

    public zzxb() {
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zze, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ com.google.android.gms.internal.p010firebaseauthapi.zzuj zza(java.lang.String r12) throws com.google.android.gms.internal.p010firebaseauthapi.zzpz {
        /*
            r11 = this;
            java.lang.String r0 = "mfaInfo"
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00a5, NullPointerException -> 0x00a3 }
            r1.<init>(r12)     // Catch:{ JSONException -> 0x00a5, NullPointerException -> 0x00a3 }
            java.lang.String r2 = "email"
            java.lang.String r2 = r1.optString(r2)     // Catch:{ JSONException -> 0x00a5, NullPointerException -> 0x00a3 }
            java.lang.String r2 = com.google.android.gms.common.util.Strings.emptyToNull(r2)     // Catch:{ JSONException -> 0x00a5, NullPointerException -> 0x00a3 }
            r11.zzb = r2     // Catch:{ JSONException -> 0x00a5, NullPointerException -> 0x00a3 }
            java.lang.String r2 = "newEmail"
            java.lang.String r2 = r1.optString(r2)     // Catch:{ JSONException -> 0x00a5, NullPointerException -> 0x00a3 }
            java.lang.String r2 = com.google.android.gms.common.util.Strings.emptyToNull(r2)     // Catch:{ JSONException -> 0x00a5, NullPointerException -> 0x00a3 }
            r11.zzc = r2     // Catch:{ JSONException -> 0x00a5, NullPointerException -> 0x00a3 }
            java.lang.String r2 = "reqType"
            int r2 = r1.optInt(r2)     // Catch:{ JSONException -> 0x00a5, NullPointerException -> 0x00a3 }
            r3 = 0
            java.lang.String r4 = "REVERT_SECOND_FACTOR_ADDITION"
            java.lang.String r5 = "VERIFY_AND_CHANGE_EMAIL"
            java.lang.String r6 = "EMAIL_SIGNIN"
            java.lang.String r7 = "RECOVER_EMAIL"
            java.lang.String r8 = "VERIFY_EMAIL"
            java.lang.String r9 = "PASSWORD_RESET"
            switch(r2) {
                case 1: goto L_0x0041;
                case 2: goto L_0x0035;
                case 3: goto L_0x0035;
                case 4: goto L_0x003f;
                case 5: goto L_0x003d;
                case 6: goto L_0x003b;
                case 7: goto L_0x0039;
                case 8: goto L_0x0037;
                default: goto L_0x0035;
            }
        L_0x0035:
            r2 = r3
            goto L_0x0042
        L_0x0037:
            r2 = r4
            goto L_0x0042
        L_0x0039:
            r2 = r5
            goto L_0x0042
        L_0x003b:
            r2 = r6
            goto L_0x0042
        L_0x003d:
            r2 = r7
            goto L_0x0042
        L_0x003f:
            r2 = r8
            goto L_0x0042
        L_0x0041:
            r2 = r9
        L_0x0042:
            r11.zzd = r2     // Catch:{ JSONException -> 0x00a5, NullPointerException -> 0x00a3 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ JSONException -> 0x00a5, NullPointerException -> 0x00a3 }
            if (r2 == 0) goto L_0x0091
            java.lang.String r2 = "requestType"
            java.lang.String r2 = r1.optString(r2)     // Catch:{ JSONException -> 0x00a5, NullPointerException -> 0x00a3 }
            int r10 = r2.hashCode()     // Catch:{ JSONException -> 0x00a5, NullPointerException -> 0x00a3 }
            switch(r10) {
                case -1874510116: goto L_0x0081;
                case -1452371317: goto L_0x0079;
                case -1341836234: goto L_0x0071;
                case -1099157829: goto L_0x0069;
                case 870738373: goto L_0x0061;
                case 970484929: goto L_0x0058;
                default: goto L_0x0057;
            }
        L_0x0057:
            goto L_0x0089
        L_0x0058:
            boolean r4 = r2.equals(r7)
            if (r4 == 0) goto L_0x0057
            r4 = 4
            goto L_0x008a
        L_0x0061:
            boolean r4 = r2.equals(r6)
            if (r4 == 0) goto L_0x0057
            r4 = 2
            goto L_0x008a
        L_0x0069:
            boolean r4 = r2.equals(r5)
            if (r4 == 0) goto L_0x0057
            r4 = 3
            goto L_0x008a
        L_0x0071:
            boolean r4 = r2.equals(r8)
            if (r4 == 0) goto L_0x0057
            r4 = 0
            goto L_0x008a
        L_0x0079:
            boolean r4 = r2.equals(r9)
            if (r4 == 0) goto L_0x0057
            r4 = 1
            goto L_0x008a
        L_0x0081:
            boolean r4 = r2.equals(r4)
            if (r4 == 0) goto L_0x0057
            r4 = 5
            goto L_0x008a
        L_0x0089:
            r4 = -1
        L_0x008a:
            switch(r4) {
                case 0: goto L_0x008e;
                case 1: goto L_0x008e;
                case 2: goto L_0x008e;
                case 3: goto L_0x008e;
                case 4: goto L_0x008e;
                case 5: goto L_0x008e;
                default: goto L_0x008d;
            }
        L_0x008d:
            goto L_0x008f
        L_0x008e:
            r3 = r2
        L_0x008f:
            r11.zzd = r3     // Catch:{ JSONException -> 0x00a5, NullPointerException -> 0x00a3 }
        L_0x0091:
            boolean r2 = r1.has(r0)     // Catch:{ JSONException -> 0x00a5, NullPointerException -> 0x00a3 }
            if (r2 == 0) goto L_0x00a2
            org.json.JSONObject r0 = r1.optJSONObject(r0)     // Catch:{ JSONException -> 0x00a5, NullPointerException -> 0x00a3 }
            com.google.android.gms.internal.firebase-auth-api.zzwu r0 = com.google.android.gms.internal.p010firebaseauthapi.zzwu.zzb(r0)     // Catch:{ JSONException -> 0x00a5, NullPointerException -> 0x00a3 }
            r11.zze = r0     // Catch:{ JSONException -> 0x00a5, NullPointerException -> 0x00a3 }
        L_0x00a2:
            return r11
        L_0x00a3:
            r0 = move-exception
            goto L_0x00a6
        L_0x00a5:
            r0 = move-exception
        L_0x00a6:
            java.lang.String r1 = zza
            com.google.android.gms.internal.firebase-auth-api.zzpz r12 = com.google.android.gms.internal.p010firebaseauthapi.zzyc.zza(r0, r1, r12)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p010firebaseauthapi.zzxb.zza(java.lang.String):com.google.android.gms.internal.firebase-auth-api.zzuj");
    }

    public final zzwu zzb() {
        return this.zze;
    }

    public final String zzc() {
        return this.zzb;
    }

    public final String zzd() {
        return this.zzc;
    }

    public final String zze() {
        return this.zzd;
    }

    public final boolean zzf() {
        return this.zzb != null;
    }

    public final boolean zzg() {
        return this.zze != null;
    }

    public final boolean zzh() {
        return this.zzc != null;
    }

    public final boolean zzi() {
        return this.zzd != null;
    }

    zzxb(String str, String str2, String str3, zzwu zzwu) {
        this.zzb = str;
        this.zzc = str2;
        this.zzd = str3;
        this.zze = zzwu;
    }
}
