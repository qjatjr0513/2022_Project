package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvv */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzvv extends AbstractSafeParcelable implements zzuj<zzvv> {
    public static final Parcelable.Creator<zzvv> CREATOR = new zzvw();
    private static final String zza = zzvv.class.getSimpleName();
    private String zzb;
    private boolean zzc;
    private String zzd;
    private boolean zze;
    private zzxo zzf;
    private List<String> zzg;

    public zzvv() {
        this.zzf = new zzxo((List<String>) null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzc);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zze);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzf, i, false);
        SafeParcelWriter.writeStringList(parcel, 7, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final /* bridge */ /* synthetic */ zzuj zza(String str) throws zzpz {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = jSONObject.optString("authUri", (String) null);
            this.zzc = jSONObject.optBoolean("registered", false);
            this.zzd = jSONObject.optString("providerId", (String) null);
            this.zze = jSONObject.optBoolean("forExistingProvider", false);
            if (!jSONObject.has("allProviders")) {
                this.zzf = new zzxo((List<String>) null);
            } else {
                this.zzf = new zzxo(1, zzyc.zzb(jSONObject.optJSONArray("allProviders")));
            }
            this.zzg = zzyc.zzb(jSONObject.optJSONArray("signinMethods"));
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzyc.zza(e, zza, str);
        }
    }

    public final List<String> zzb() {
        return this.zzg;
    }

    public zzvv(String str, boolean z, String str2, boolean z2, zzxo zzxo, List<String> list) {
        zzxo zzxo2;
        this.zzb = str;
        this.zzc = z;
        this.zzd = str2;
        this.zze = z2;
        if (zzxo == null) {
            zzxo2 = new zzxo((List<String>) null);
        } else {
            zzxo2 = zzxo.zza(zzxo);
        }
        this.zzf = zzxo2;
        this.zzg = list;
    }
}
