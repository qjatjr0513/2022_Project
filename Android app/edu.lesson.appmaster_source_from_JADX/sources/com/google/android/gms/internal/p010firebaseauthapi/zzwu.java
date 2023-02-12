package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwu */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzwu extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzwu> CREATOR = new zzwv();
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final long zzd;
    private String zze;

    public zzwu(String str, String str2, String str3, long j) {
        this.zza = str;
        this.zzb = Preconditions.checkNotEmpty(str2);
        this.zzc = str3;
        this.zzd = j;
    }

    public static zzwu zzb(JSONObject jSONObject) {
        long j;
        String optString = jSONObject.optString("phoneInfo", (String) null);
        String optString2 = jSONObject.optString("mfaEnrollmentId", (String) null);
        String optString3 = jSONObject.optString("displayName", (String) null);
        long j2 = 0;
        if (jSONObject != null) {
            if (!jSONObject.has("enrolledAt")) {
                j = 0;
            } else {
                JSONObject optJSONObject = jSONObject.optJSONObject("enrolledAt");
                if (optJSONObject != null) {
                    if (!optJSONObject.has("seconds")) {
                        j = 0;
                    } else {
                        j2 = optJSONObject.optLong("seconds", 0);
                    }
                }
            }
            zzwu zzwu = new zzwu(optString, optString2, optString3, j);
            zzwu.zze = jSONObject.optString("unobfuscatedPhoneInfo");
            return zzwu;
        }
        j = j2;
        zzwu zzwu2 = new zzwu(optString, optString2, optString3, j);
        zzwu2.zze = jSONObject.optString("unobfuscatedPhoneInfo");
        return zzwu2;
    }

    public static List<zzwu> zzf(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null || jSONArray.length() == 0) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(zzb(jSONArray.getJSONObject(i)));
        }
        return arrayList;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeLong(parcel, 4, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final long zza() {
        return this.zzd;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zzb;
    }

    public final String zze() {
        return this.zza;
    }
}
