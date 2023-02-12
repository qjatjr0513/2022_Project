package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zzat extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzat> CREATOR = new zzau();
    public final String zza;
    public final zzar zzb;
    public final String zzc;
    public final long zzd;

    zzat(zzat zzat, long j) {
        Preconditions.checkNotNull(zzat);
        this.zza = zzat.zza;
        this.zzb = zzat.zzb;
        this.zzc = zzat.zzc;
        this.zzd = j;
    }

    public final String toString() {
        String str = this.zzc;
        String str2 = this.zza;
        String valueOf = String.valueOf(this.zzb);
        int length = String.valueOf(str).length();
        StringBuilder sb = new StringBuilder(length + 21 + String.valueOf(str2).length() + String.valueOf(valueOf).length());
        sb.append("origin=");
        sb.append(str);
        sb.append(",name=");
        sb.append(str2);
        sb.append(",params=");
        sb.append(valueOf);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        zzau.zza(this, parcel, i);
    }

    public zzat(String str, zzar zzar, String str2, long j) {
        this.zza = str;
        this.zzb = zzar;
        this.zzc = str2;
        this.zzd = j;
    }
}
