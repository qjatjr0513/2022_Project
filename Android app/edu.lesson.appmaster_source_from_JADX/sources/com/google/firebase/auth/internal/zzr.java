package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.zze;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzr implements AuthResult {
    public static final Parcelable.Creator<zzr> CREATOR = new zzs();
    private zzx zza;
    private zzp zzb;
    private zze zzc;

    public zzr(zzx zzx) {
        zzx zzx2 = (zzx) Preconditions.checkNotNull(zzx);
        this.zza = zzx2;
        List<zzt> zzo = zzx2.zzo();
        this.zzb = null;
        for (int i = 0; i < zzo.size(); i++) {
            if (!TextUtils.isEmpty(zzo.get(i).zza())) {
                this.zzb = new zzp(zzo.get(i).getProviderId(), zzo.get(i).zza(), zzx.zzs());
            }
        }
        if (this.zzb == null) {
            this.zzb = new zzp(zzx.zzs());
        }
        this.zzc = zzx.zzj();
    }

    zzr(zzx zzx, zzp zzp, zze zze) {
        this.zza = zzx;
        this.zzb = zzp;
        this.zzc = zze;
    }

    public final int describeContents() {
        return 0;
    }

    public final AdditionalUserInfo getAdditionalUserInfo() {
        return this.zzb;
    }

    public final AuthCredential getCredential() {
        return this.zzc;
    }

    public final FirebaseUser getUser() {
        return this.zza;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzc, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
