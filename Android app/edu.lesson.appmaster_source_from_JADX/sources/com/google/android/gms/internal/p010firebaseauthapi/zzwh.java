package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Strings;
import com.google.firebase.auth.zze;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwh */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzwh extends AbstractSafeParcelable implements zzuj<zzwh> {
    public static final Parcelable.Creator<zzwh> CREATOR = new zzwi();
    private static final String zza = zzwh.class.getSimpleName();
    private zzwl zzb;

    public zzwh() {
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final /* bridge */ /* synthetic */ zzuj zza(String str) throws zzpz {
        zzwl zzwl;
        int i;
        zzwj zzwj;
        String str2 = str;
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (!jSONObject.has("users")) {
                this.zzb = new zzwl();
            } else {
                JSONArray optJSONArray = jSONObject.optJSONArray("users");
                Parcelable.Creator<zzwl> creator = zzwl.CREATOR;
                if (optJSONArray != null) {
                    if (optJSONArray.length() != 0) {
                        ArrayList arrayList = new ArrayList(optJSONArray.length());
                        boolean z = false;
                        int i2 = 0;
                        while (i2 < optJSONArray.length()) {
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                            if (jSONObject2 == null) {
                                zzwj = new zzwj();
                                i = i2;
                            } else {
                                i = i2;
                                zzwj = new zzwj(Strings.emptyToNull(jSONObject2.optString("localId", (String) null)), Strings.emptyToNull(jSONObject2.optString("email", (String) null)), jSONObject2.optBoolean("emailVerified", z), Strings.emptyToNull(jSONObject2.optString("displayName", (String) null)), Strings.emptyToNull(jSONObject2.optString("photoUrl", (String) null)), zzwy.zza(jSONObject2.optJSONArray("providerUserInfo")), Strings.emptyToNull(jSONObject2.optString("rawPassword", (String) null)), Strings.emptyToNull(jSONObject2.optString("phoneNumber", (String) null)), jSONObject2.optLong("createdAt", 0), jSONObject2.optLong("lastLoginAt", 0), false, (zze) null, zzwu.zzf(jSONObject2.optJSONArray("mfaInfo")));
                            }
                            arrayList.add(zzwj);
                            i2 = i + 1;
                            z = false;
                        }
                        zzwl = new zzwl(arrayList);
                        this.zzb = zzwl;
                    }
                }
                zzwl = new zzwl(new ArrayList());
                this.zzb = zzwl;
            }
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzyc.zza(e, zza, str2);
        }
    }

    public final List<zzwj> zzb() {
        return this.zzb.zzb();
    }

    zzwh(zzwl zzwl) {
        zzwl zzwl2;
        if (zzwl == null) {
            zzwl2 = new zzwl();
        } else {
            zzwl2 = zzwl.zza(zzwl);
        }
        this.zzb = zzwl2;
    }
}
