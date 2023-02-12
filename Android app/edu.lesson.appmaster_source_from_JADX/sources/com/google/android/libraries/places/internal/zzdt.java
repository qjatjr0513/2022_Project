package com.google.android.libraries.places.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzdt extends zzdz {
    private final String zza;
    private final zzge<AutocompletePrediction> zzb;
    private final Place zzc;
    private final AutocompletePrediction zzd;
    private final Status zze;
    private final int zzf;

    /* synthetic */ zzdt(int i, String str, zzge zzge, Place place, AutocompletePrediction autocompletePrediction, Status status, zzds zzds) {
        this.zzf = i;
        this.zza = str;
        this.zzb = zzge;
        this.zzc = place;
        this.zzd = autocompletePrediction;
        this.zze = status;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0029, code lost:
        r1 = r4.zzb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003f, code lost:
        r1 = r4.zzc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0055, code lost:
        r1 = r4.zzd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006b, code lost:
        r1 = r4.zze;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0013, code lost:
        r1 = r4.zza;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.android.libraries.places.internal.zzdz
            r2 = 0
            if (r1 == 0) goto L_0x0083
            com.google.android.libraries.places.internal.zzdz r5 = (com.google.android.libraries.places.internal.zzdz) r5
            int r1 = r4.zzf
            int r3 = r5.zzf()
            if (r1 != r3) goto L_0x0082
            java.lang.String r1 = r4.zza
            if (r1 != 0) goto L_0x001e
            java.lang.String r1 = r5.zze()
            if (r1 != 0) goto L_0x0082
        L_0x001d:
            goto L_0x0029
        L_0x001e:
            java.lang.String r3 = r5.zze()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0082
            goto L_0x001d
        L_0x0029:
            com.google.android.libraries.places.internal.zzge<com.google.android.libraries.places.api.model.AutocompletePrediction> r1 = r4.zzb
            if (r1 != 0) goto L_0x0034
            com.google.android.libraries.places.internal.zzge r1 = r5.zzd()
            if (r1 != 0) goto L_0x0082
        L_0x0033:
            goto L_0x003f
        L_0x0034:
            com.google.android.libraries.places.internal.zzge r3 = r5.zzd()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0082
            goto L_0x0033
        L_0x003f:
            com.google.android.libraries.places.api.model.Place r1 = r4.zzc
            if (r1 != 0) goto L_0x004a
            com.google.android.libraries.places.api.model.Place r1 = r5.zzc()
            if (r1 != 0) goto L_0x0082
        L_0x0049:
            goto L_0x0055
        L_0x004a:
            com.google.android.libraries.places.api.model.Place r3 = r5.zzc()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0082
            goto L_0x0049
        L_0x0055:
            com.google.android.libraries.places.api.model.AutocompletePrediction r1 = r4.zzd
            if (r1 != 0) goto L_0x0060
            com.google.android.libraries.places.api.model.AutocompletePrediction r1 = r5.zzb()
            if (r1 != 0) goto L_0x0082
        L_0x005f:
            goto L_0x006b
        L_0x0060:
            com.google.android.libraries.places.api.model.AutocompletePrediction r3 = r5.zzb()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0082
            goto L_0x005f
        L_0x006b:
            com.google.android.gms.common.api.Status r1 = r4.zze
            if (r1 != 0) goto L_0x0076
            com.google.android.gms.common.api.Status r5 = r5.zza()
            if (r5 != 0) goto L_0x0080
            goto L_0x0081
        L_0x0076:
            com.google.android.gms.common.api.Status r5 = r5.zza()
            boolean r5 = r1.equals(r5)
            if (r5 != 0) goto L_0x0081
        L_0x0080:
            goto L_0x0082
        L_0x0081:
            return r0
        L_0x0082:
            return r2
        L_0x0083:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzdt.equals(java.lang.Object):boolean");
    }

    public final String toString() {
        String str;
        switch (this.zzf) {
            case 1:
                str = "START";
                break;
            case 2:
                str = "RESET";
                break;
            case 3:
                str = "LOADING";
                break;
            case 4:
                str = "TRY_AGAIN_PROGRESS_LOADING";
                break;
            case 5:
                str = "SUCCESS_PREDICTIONS";
                break;
            case 6:
                str = "FAILURE_NO_PREDICTIONS";
                break;
            case 7:
                str = "FAILURE_PREDICTIONS";
                break;
            case 8:
                str = "SUCCESS_SELECTION";
                break;
            case 9:
                str = "FAILURE_SELECTION";
                break;
            default:
                str = "FAILURE_UNRESOLVABLE";
                break;
        }
        String str2 = this.zza;
        String valueOf = String.valueOf(this.zzb);
        String valueOf2 = String.valueOf(this.zzc);
        String valueOf3 = String.valueOf(this.zzd);
        String valueOf4 = String.valueOf(this.zze);
        int length = str.length();
        int length2 = String.valueOf(str2).length();
        int length3 = String.valueOf(valueOf).length();
        int length4 = String.valueOf(valueOf2).length();
        StringBuilder sb = new StringBuilder(length + 76 + length2 + length3 + length4 + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length());
        sb.append("AutocompleteState{type=");
        sb.append(str);
        sb.append(", query=");
        sb.append(str2);
        sb.append(", predictions=");
        sb.append(valueOf);
        sb.append(", place=");
        sb.append(valueOf2);
        sb.append(", prediction=");
        sb.append(valueOf3);
        sb.append(", status=");
        sb.append(valueOf4);
        sb.append("}");
        return sb.toString();
    }

    public final Status zza() {
        return this.zze;
    }

    public final AutocompletePrediction zzb() {
        return this.zzd;
    }

    public final Place zzc() {
        return this.zzc;
    }

    public final zzge<AutocompletePrediction> zzd() {
        return this.zzb;
    }

    public final String zze() {
        return this.zza;
    }

    public final int zzf() {
        return this.zzf;
    }

    public final int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = (this.zzf ^ 1000003) * 1000003;
        String str = this.zza;
        int i6 = 0;
        if (str == null) {
            i = 0;
        } else {
            i = str.hashCode();
        }
        int i7 = (i5 ^ i) * 1000003;
        zzge<AutocompletePrediction> zzge = this.zzb;
        if (zzge == null) {
            i2 = 0;
        } else {
            i2 = zzge.hashCode();
        }
        int i8 = (i7 ^ i2) * 1000003;
        Place place = this.zzc;
        if (place == null) {
            i3 = 0;
        } else {
            i3 = place.hashCode();
        }
        int i9 = (i8 ^ i3) * 1000003;
        AutocompletePrediction autocompletePrediction = this.zzd;
        if (autocompletePrediction == null) {
            i4 = 0;
        } else {
            i4 = autocompletePrediction.hashCode();
        }
        int i10 = (i9 ^ i4) * 1000003;
        Status status = this.zze;
        if (status != null) {
            i6 = status.hashCode();
        }
        return i10 ^ i6;
    }
}
