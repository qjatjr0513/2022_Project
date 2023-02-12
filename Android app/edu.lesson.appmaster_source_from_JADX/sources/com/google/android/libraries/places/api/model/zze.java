package com.google.android.libraries.places.api.model;

import com.google.android.libraries.places.api.model.Place;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
abstract class zze extends AutocompletePrediction {
    private final String zza;
    private final Integer zzb;
    private final List<Place.Type> zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final List<zzbb> zzg;
    private final List<zzbb> zzh;
    private final List<zzbb> zzi;

    zze(String str, Integer num, List<Place.Type> list, String str2, String str3, String str4, List<zzbb> list2, List<zzbb> list3, List<zzbb> list4) {
        if (str != null) {
            this.zza = str;
            this.zzb = num;
            if (list != null) {
                this.zzc = list;
                if (str2 != null) {
                    this.zzd = str2;
                    if (str3 != null) {
                        this.zze = str3;
                        if (str4 != null) {
                            this.zzf = str4;
                            this.zzg = list2;
                            this.zzh = list3;
                            this.zzi = list4;
                            return;
                        }
                        throw new NullPointerException("Null secondaryText");
                    }
                    throw new NullPointerException("Null primaryText");
                }
                throw new NullPointerException("Null fullText");
            }
            throw new NullPointerException("Null placeTypes");
        }
        throw new NullPointerException("Null placeId");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005d, code lost:
        r1 = r4.zzg;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0073, code lost:
        r1 = r4.zzh;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0089, code lost:
        r1 = r4.zzi;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        r1 = r4.zzb;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.android.libraries.places.api.model.AutocompletePrediction
            r2 = 0
            if (r1 == 0) goto L_0x00a1
            com.google.android.libraries.places.api.model.AutocompletePrediction r5 = (com.google.android.libraries.places.api.model.AutocompletePrediction) r5
            java.lang.String r1 = r4.zza
            java.lang.String r3 = r5.getPlaceId()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00a0
            java.lang.Integer r1 = r4.zzb
            if (r1 != 0) goto L_0x0022
            java.lang.Integer r1 = r5.getDistanceMeters()
            if (r1 != 0) goto L_0x00a0
        L_0x0021:
            goto L_0x002d
        L_0x0022:
            java.lang.Integer r3 = r5.getDistanceMeters()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00a0
            goto L_0x0021
        L_0x002d:
            java.util.List<com.google.android.libraries.places.api.model.Place$Type> r1 = r4.zzc
            java.util.List r3 = r5.getPlaceTypes()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00a0
            java.lang.String r1 = r4.zzd
            java.lang.String r3 = r5.zza()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00a0
            java.lang.String r1 = r4.zze
            java.lang.String r3 = r5.zzb()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00a0
            java.lang.String r1 = r4.zzf
            java.lang.String r3 = r5.zzc()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00a0
            java.util.List<com.google.android.libraries.places.api.model.zzbb> r1 = r4.zzg
            if (r1 != 0) goto L_0x0068
            java.util.List r1 = r5.zzd()
            if (r1 != 0) goto L_0x00a0
        L_0x0067:
            goto L_0x0073
        L_0x0068:
            java.util.List r3 = r5.zzd()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00a0
            goto L_0x0067
        L_0x0073:
            java.util.List<com.google.android.libraries.places.api.model.zzbb> r1 = r4.zzh
            if (r1 != 0) goto L_0x007e
            java.util.List r1 = r5.zze()
            if (r1 != 0) goto L_0x00a0
        L_0x007d:
            goto L_0x0089
        L_0x007e:
            java.util.List r3 = r5.zze()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00a0
            goto L_0x007d
        L_0x0089:
            java.util.List<com.google.android.libraries.places.api.model.zzbb> r1 = r4.zzi
            if (r1 != 0) goto L_0x0094
            java.util.List r5 = r5.zzf()
            if (r5 != 0) goto L_0x009e
            goto L_0x009f
        L_0x0094:
            java.util.List r5 = r5.zzf()
            boolean r5 = r1.equals(r5)
            if (r5 != 0) goto L_0x009f
        L_0x009e:
            goto L_0x00a0
        L_0x009f:
            return r0
        L_0x00a0:
            return r2
        L_0x00a1:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.api.model.zze.equals(java.lang.Object):boolean");
    }

    public final Integer getDistanceMeters() {
        return this.zzb;
    }

    public final String getPlaceId() {
        return this.zza;
    }

    public final List<Place.Type> getPlaceTypes() {
        return this.zzc;
    }

    public final int hashCode() {
        int i;
        int i2;
        int i3;
        int hashCode = (this.zza.hashCode() ^ 1000003) * 1000003;
        Integer num = this.zzb;
        int i4 = 0;
        if (num == null) {
            i = 0;
        } else {
            i = num.hashCode();
        }
        int hashCode2 = (((((((((hashCode ^ i) * 1000003) ^ this.zzc.hashCode()) * 1000003) ^ this.zzd.hashCode()) * 1000003) ^ this.zze.hashCode()) * 1000003) ^ this.zzf.hashCode()) * 1000003;
        List<zzbb> list = this.zzg;
        if (list == null) {
            i2 = 0;
        } else {
            i2 = list.hashCode();
        }
        int i5 = (hashCode2 ^ i2) * 1000003;
        List<zzbb> list2 = this.zzh;
        if (list2 == null) {
            i3 = 0;
        } else {
            i3 = list2.hashCode();
        }
        int i6 = (i5 ^ i3) * 1000003;
        List<zzbb> list3 = this.zzi;
        if (list3 != null) {
            i4 = list3.hashCode();
        }
        return i6 ^ i4;
    }

    public final String toString() {
        String str = this.zza;
        String valueOf = String.valueOf(this.zzb);
        String valueOf2 = String.valueOf(this.zzc);
        String str2 = this.zzd;
        String str3 = this.zze;
        String str4 = this.zzf;
        String valueOf3 = String.valueOf(this.zzg);
        String valueOf4 = String.valueOf(this.zzh);
        String valueOf5 = String.valueOf(this.zzi);
        int length = str.length();
        int length2 = String.valueOf(valueOf).length();
        int length3 = String.valueOf(valueOf2).length();
        int length4 = str2.length();
        int length5 = str3.length();
        int length6 = str4.length();
        int length7 = String.valueOf(valueOf3).length();
        StringBuilder sb = new StringBuilder(length + 195 + length2 + length3 + length4 + length5 + length6 + length7 + String.valueOf(valueOf4).length() + String.valueOf(valueOf5).length());
        sb.append("AutocompletePrediction{placeId=");
        sb.append(str);
        sb.append(", distanceMeters=");
        sb.append(valueOf);
        sb.append(", placeTypes=");
        sb.append(valueOf2);
        sb.append(", fullText=");
        sb.append(str2);
        sb.append(", primaryText=");
        sb.append(str3);
        sb.append(", secondaryText=");
        sb.append(str4);
        sb.append(", fullTextMatchedSubstrings=");
        sb.append(valueOf3);
        sb.append(", primaryTextMatchedSubstrings=");
        sb.append(valueOf4);
        sb.append(", secondaryTextMatchedSubstrings=");
        sb.append(valueOf5);
        sb.append("}");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final String zza() {
        return this.zzd;
    }

    /* access modifiers changed from: package-private */
    public final String zzb() {
        return this.zze;
    }

    /* access modifiers changed from: package-private */
    public final String zzc() {
        return this.zzf;
    }

    /* access modifiers changed from: package-private */
    public final List<zzbb> zzd() {
        return this.zzg;
    }

    /* access modifiers changed from: package-private */
    public final List<zzbb> zze() {
        return this.zzh;
    }

    /* access modifiers changed from: package-private */
    public final List<zzbb> zzf() {
        return this.zzi;
    }
}
