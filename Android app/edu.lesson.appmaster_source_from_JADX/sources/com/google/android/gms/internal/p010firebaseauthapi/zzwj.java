package com.google.android.gms.internal.p010firebaseauthapi;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.auth.zze;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwj */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzwj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzwj> CREATOR = new zzwk();
    private String zza;
    private String zzb;
    private boolean zzc;
    private String zzd;
    private String zze;
    private zzwy zzf;
    private String zzg;
    private String zzh;
    private long zzi;
    private long zzj;
    private boolean zzk;
    private zze zzl;
    private List<zzwu> zzm;

    public zzwj() {
        this.zzf = new zzwy();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzc);
        SafeParcelWriter.writeString(parcel, 5, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 6, this.zze, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzf, i, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzg, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzh, false);
        SafeParcelWriter.writeLong(parcel, 10, this.zzi);
        SafeParcelWriter.writeLong(parcel, 11, this.zzj);
        SafeParcelWriter.writeBoolean(parcel, 12, this.zzk);
        SafeParcelWriter.writeParcelable(parcel, 13, this.zzl, i, false);
        SafeParcelWriter.writeTypedList(parcel, 14, this.zzm, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final long zza() {
        return this.zzi;
    }

    public final long zzb() {
        return this.zzj;
    }

    public final Uri zzc() {
        if (!TextUtils.isEmpty(this.zze)) {
            return Uri.parse(this.zze);
        }
        return null;
    }

    public final zze zzd() {
        return this.zzl;
    }

    public final zzwj zze(zze zze2) {
        this.zzl = zze2;
        return this;
    }

    public final zzwj zzf(String str) {
        this.zzd = str;
        return this;
    }

    public final zzwj zzg(String str) {
        this.zzb = str;
        return this;
    }

    public final zzwj zzh(boolean z) {
        this.zzk = z;
        return this;
    }

    public final zzwj zzi(String str) {
        Preconditions.checkNotEmpty(str);
        this.zzg = str;
        return this;
    }

    public final zzwj zzj(String str) {
        this.zze = str;
        return this;
    }

    public final zzwj zzk(List<zzww> list) {
        Preconditions.checkNotNull(list);
        zzwy zzwy = new zzwy();
        this.zzf = zzwy;
        zzwy.zzc().addAll(list);
        return this;
    }

    public final zzwy zzl() {
        return this.zzf;
    }

    public final String zzm() {
        return this.zzd;
    }

    public final String zzn() {
        return this.zzb;
    }

    public final String zzo() {
        return this.zza;
    }

    public final String zzp() {
        return this.zzh;
    }

    public final List<zzwu> zzq() {
        return this.zzm;
    }

    public final List<zzww> zzr() {
        return this.zzf.zzc();
    }

    public final boolean zzs() {
        return this.zzc;
    }

    public final boolean zzt() {
        return this.zzk;
    }

    public zzwj(String str, String str2, boolean z, String str3, String str4, zzwy zzwy, String str5, String str6, long j, long j2, boolean z2, zze zze2, List<zzwu> list) {
        zzwy zzwy2;
        this.zza = str;
        this.zzb = str2;
        this.zzc = z;
        this.zzd = str3;
        this.zze = str4;
        if (zzwy == null) {
            zzwy2 = new zzwy();
        } else {
            zzwy2 = zzwy.zzb(zzwy);
        }
        this.zzf = zzwy2;
        this.zzg = str5;
        this.zzh = str6;
        this.zzi = j;
        this.zzj = j2;
        this.zzk = z2;
        this.zzl = zze2;
        this.zzm = list == null ? new ArrayList<>() : list;
    }
}
