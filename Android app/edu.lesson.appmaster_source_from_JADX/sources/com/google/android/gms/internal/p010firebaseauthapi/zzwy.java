package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwy */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzwy extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzwy> CREATOR = new zzwz();
    private final List<zzww> zza;

    public zzwy() {
        this.zza = new ArrayList();
    }

    public static zzwy zza(JSONArray jSONArray) throws JSONException {
        zzww zzww;
        if (jSONArray == null || jSONArray.length() == 0) {
            return new zzwy(new ArrayList());
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject == null) {
                zzww = new zzww();
            } else {
                zzww = new zzww(Strings.emptyToNull(jSONObject.optString("federatedId", (String) null)), Strings.emptyToNull(jSONObject.optString("displayName", (String) null)), Strings.emptyToNull(jSONObject.optString("photoUrl", (String) null)), Strings.emptyToNull(jSONObject.optString("providerId", (String) null)), (String) null, Strings.emptyToNull(jSONObject.optString("phoneNumber", (String) null)), Strings.emptyToNull(jSONObject.optString("email", (String) null)));
            }
            arrayList.add(zzww);
        }
        return new zzwy(arrayList);
    }

    public static zzwy zzb(zzwy zzwy) {
        List<zzww> list = zzwy.zza;
        zzwy zzwy2 = new zzwy();
        if (list != null) {
            zzwy2.zza.addAll(list);
        }
        return zzwy2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final List<zzww> zzc() {
        return this.zza;
    }

    zzwy(List<zzww> list) {
        if (list == null || list.isEmpty()) {
            this.zza = Collections.emptyList();
        } else {
            this.zza = Collections.unmodifiableList(list);
        }
    }
}
