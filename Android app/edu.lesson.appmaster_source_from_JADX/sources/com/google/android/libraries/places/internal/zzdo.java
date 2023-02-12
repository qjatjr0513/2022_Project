package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
abstract class zzdo extends zzdx {
    private final AutocompleteActivityMode zza;
    private final zzge<Place.Field> zzb;
    private final zzdv zzc;
    private final String zzd;
    private final String zze;
    private final LocationBias zzf;
    private final LocationRestriction zzg;
    private final zzge<String> zzh;
    private final TypeFilter zzi;
    private final int zzj;
    private final int zzk;

    zzdo(AutocompleteActivityMode autocompleteActivityMode, zzge<Place.Field> zzge, zzdv zzdv, String str, String str2, LocationBias locationBias, LocationRestriction locationRestriction, zzge<String> zzge2, TypeFilter typeFilter, int i, int i2) {
        if (autocompleteActivityMode != null) {
            this.zza = autocompleteActivityMode;
            if (zzge != null) {
                this.zzb = zzge;
                if (zzdv != null) {
                    this.zzc = zzdv;
                    this.zzd = str;
                    this.zze = str2;
                    this.zzf = locationBias;
                    this.zzg = locationRestriction;
                    if (zzge2 != null) {
                        this.zzh = zzge2;
                        this.zzi = typeFilter;
                        this.zzj = i;
                        this.zzk = i2;
                        return;
                    }
                    throw new NullPointerException("Null countries");
                }
                throw new NullPointerException("Null origin");
            }
            throw new NullPointerException("Null placeFields");
        }
        throw new NullPointerException("Null mode");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002f, code lost:
        r1 = r4.zzd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0045, code lost:
        r1 = r4.zze;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005b, code lost:
        r1 = r4.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0071, code lost:
        r1 = r4.zzg;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0093, code lost:
        r1 = r4.zzi;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.android.libraries.places.internal.zzdx
            r2 = 0
            if (r1 == 0) goto L_0x00bb
            com.google.android.libraries.places.internal.zzdx r5 = (com.google.android.libraries.places.internal.zzdx) r5
            com.google.android.libraries.places.widget.model.AutocompleteActivityMode r1 = r4.zza
            com.google.android.libraries.places.widget.model.AutocompleteActivityMode r3 = r5.zzh()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00ba
            com.google.android.libraries.places.internal.zzge<com.google.android.libraries.places.api.model.Place$Field> r1 = r4.zzb
            com.google.android.libraries.places.internal.zzge r3 = r5.zzj()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00ba
            com.google.android.libraries.places.internal.zzdv r1 = r4.zzc
            com.google.android.libraries.places.internal.zzdv r3 = r5.zzf()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00ba
            java.lang.String r1 = r4.zzd
            if (r1 != 0) goto L_0x003a
            java.lang.String r1 = r5.zzl()
            if (r1 != 0) goto L_0x00ba
        L_0x0039:
            goto L_0x0045
        L_0x003a:
            java.lang.String r3 = r5.zzl()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00ba
            goto L_0x0039
        L_0x0045:
            java.lang.String r1 = r4.zze
            if (r1 != 0) goto L_0x0050
            java.lang.String r1 = r5.zzk()
            if (r1 != 0) goto L_0x00ba
        L_0x004f:
            goto L_0x005b
        L_0x0050:
            java.lang.String r3 = r5.zzk()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00ba
            goto L_0x004f
        L_0x005b:
            com.google.android.libraries.places.api.model.LocationBias r1 = r4.zzf
            if (r1 != 0) goto L_0x0066
            com.google.android.libraries.places.api.model.LocationBias r1 = r5.zzc()
            if (r1 != 0) goto L_0x00ba
        L_0x0065:
            goto L_0x0071
        L_0x0066:
            com.google.android.libraries.places.api.model.LocationBias r3 = r5.zzc()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00ba
            goto L_0x0065
        L_0x0071:
            com.google.android.libraries.places.api.model.LocationRestriction r1 = r4.zzg
            if (r1 != 0) goto L_0x007c
            com.google.android.libraries.places.api.model.LocationRestriction r1 = r5.zzd()
            if (r1 != 0) goto L_0x00ba
        L_0x007b:
            goto L_0x0087
        L_0x007c:
            com.google.android.libraries.places.api.model.LocationRestriction r3 = r5.zzd()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00ba
            goto L_0x007b
        L_0x0087:
            com.google.android.libraries.places.internal.zzge<java.lang.String> r1 = r4.zzh
            com.google.android.libraries.places.internal.zzge r3 = r5.zzi()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00ba
            com.google.android.libraries.places.api.model.TypeFilter r1 = r4.zzi
            if (r1 != 0) goto L_0x009e
            com.google.android.libraries.places.api.model.TypeFilter r1 = r5.zze()
            if (r1 != 0) goto L_0x00a8
            goto L_0x00a9
        L_0x009e:
            com.google.android.libraries.places.api.model.TypeFilter r3 = r5.zze()
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x00a9
        L_0x00a8:
            goto L_0x00ba
        L_0x00a9:
            int r1 = r4.zzj
            int r3 = r5.zza()
            if (r1 != r3) goto L_0x00ba
            int r1 = r4.zzk
            int r5 = r5.zzb()
            if (r1 != r5) goto L_0x00ba
            return r0
        L_0x00ba:
            return r2
        L_0x00bb:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzdo.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        int hashCode = (((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003) ^ this.zzc.hashCode()) * 1000003;
        String str = this.zzd;
        int i5 = 0;
        if (str == null) {
            i = 0;
        } else {
            i = str.hashCode();
        }
        int i6 = (hashCode ^ i) * 1000003;
        String str2 = this.zze;
        if (str2 == null) {
            i2 = 0;
        } else {
            i2 = str2.hashCode();
        }
        int i7 = (i6 ^ i2) * 1000003;
        LocationBias locationBias = this.zzf;
        if (locationBias == null) {
            i3 = 0;
        } else {
            i3 = locationBias.hashCode();
        }
        int i8 = (i7 ^ i3) * 1000003;
        LocationRestriction locationRestriction = this.zzg;
        if (locationRestriction == null) {
            i4 = 0;
        } else {
            i4 = locationRestriction.hashCode();
        }
        int hashCode2 = (((i8 ^ i4) * 1000003) ^ this.zzh.hashCode()) * 1000003;
        TypeFilter typeFilter = this.zzi;
        if (typeFilter != null) {
            i5 = typeFilter.hashCode();
        }
        return ((((hashCode2 ^ i5) * 1000003) ^ this.zzj) * 1000003) ^ this.zzk;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        String valueOf2 = String.valueOf(this.zzb);
        String valueOf3 = String.valueOf(this.zzc);
        String str = this.zzd;
        String str2 = this.zze;
        String valueOf4 = String.valueOf(this.zzf);
        String valueOf5 = String.valueOf(this.zzg);
        String valueOf6 = String.valueOf(this.zzh);
        String valueOf7 = String.valueOf(this.zzi);
        int i = this.zzj;
        int i2 = this.zzk;
        int length = String.valueOf(valueOf).length();
        int length2 = String.valueOf(valueOf2).length();
        int length3 = String.valueOf(valueOf3).length();
        int length4 = String.valueOf(str).length();
        int length5 = String.valueOf(str2).length();
        int length6 = String.valueOf(valueOf4).length();
        int length7 = String.valueOf(valueOf5).length();
        StringBuilder sb = new StringBuilder(length + 189 + length2 + length3 + length4 + length5 + length6 + length7 + String.valueOf(valueOf6).length() + String.valueOf(valueOf7).length());
        sb.append("AutocompleteOptions{mode=");
        sb.append(valueOf);
        sb.append(", placeFields=");
        sb.append(valueOf2);
        sb.append(", origin=");
        sb.append(valueOf3);
        sb.append(", initialQuery=");
        sb.append(str);
        sb.append(", hint=");
        sb.append(str2);
        sb.append(", locationBias=");
        sb.append(valueOf4);
        sb.append(", locationRestriction=");
        sb.append(valueOf5);
        sb.append(", countries=");
        sb.append(valueOf6);
        sb.append(", typeFilter=");
        sb.append(valueOf7);
        sb.append(", primaryColor=");
        sb.append(i);
        sb.append(", primaryColorDark=");
        sb.append(i2);
        sb.append("}");
        return sb.toString();
    }

    public final int zza() {
        return this.zzj;
    }

    public final int zzb() {
        return this.zzk;
    }

    public final LocationBias zzc() {
        return this.zzf;
    }

    public final LocationRestriction zzd() {
        return this.zzg;
    }

    public final TypeFilter zze() {
        return this.zzi;
    }

    public final zzdv zzf() {
        return this.zzc;
    }

    public final zzdw zzg() {
        return new zzdn(this, (zzdm) null);
    }

    public final AutocompleteActivityMode zzh() {
        return this.zza;
    }

    public final zzge<String> zzi() {
        return this.zzh;
    }

    public final zzge<Place.Field> zzj() {
        return this.zzb;
    }

    public final String zzk() {
        return this.zze;
    }

    public final String zzl() {
        return this.zzd;
    }
}
