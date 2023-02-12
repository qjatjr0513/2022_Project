package com.google.android.libraries.places.api.net;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.TypeFilter;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzk extends FindAutocompletePredictionsRequest {
    private final String zza;
    private final LocationBias zzb;
    private final LocationRestriction zzc;
    private final LatLng zzd;
    private final List<String> zze;
    private final AutocompleteSessionToken zzf;
    private final TypeFilter zzg;
    private final CancellationToken zzh;

    /* synthetic */ zzk(String str, LocationBias locationBias, LocationRestriction locationRestriction, LatLng latLng, List list, AutocompleteSessionToken autocompleteSessionToken, TypeFilter typeFilter, CancellationToken cancellationToken, zzj zzj) {
        this.zza = str;
        this.zzb = locationBias;
        this.zzc = locationRestriction;
        this.zzd = latLng;
        this.zze = list;
        this.zzf = autocompleteSessionToken;
        this.zzg = typeFilter;
        this.zzh = cancellationToken;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006f, code lost:
        r1 = r4.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0085, code lost:
        r1 = r4.zzg;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x009b, code lost:
        r1 = r4.zzh;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
            r2 = 0
            if (r1 == 0) goto L_0x00b3
            com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest r5 = (com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest) r5
            java.lang.String r1 = r4.zza
            if (r1 != 0) goto L_0x0016
            java.lang.String r1 = r5.getQuery()
            if (r1 != 0) goto L_0x00b2
        L_0x0015:
            goto L_0x0021
        L_0x0016:
            java.lang.String r3 = r5.getQuery()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00b2
            goto L_0x0015
        L_0x0021:
            com.google.android.libraries.places.api.model.LocationBias r1 = r4.zzb
            if (r1 != 0) goto L_0x002c
            com.google.android.libraries.places.api.model.LocationBias r1 = r5.getLocationBias()
            if (r1 != 0) goto L_0x00b2
        L_0x002b:
            goto L_0x0037
        L_0x002c:
            com.google.android.libraries.places.api.model.LocationBias r3 = r5.getLocationBias()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00b2
            goto L_0x002b
        L_0x0037:
            com.google.android.libraries.places.api.model.LocationRestriction r1 = r4.zzc
            if (r1 != 0) goto L_0x0042
            com.google.android.libraries.places.api.model.LocationRestriction r1 = r5.getLocationRestriction()
            if (r1 != 0) goto L_0x00b2
        L_0x0041:
            goto L_0x004d
        L_0x0042:
            com.google.android.libraries.places.api.model.LocationRestriction r3 = r5.getLocationRestriction()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00b2
            goto L_0x0041
        L_0x004d:
            com.google.android.gms.maps.model.LatLng r1 = r4.zzd
            if (r1 != 0) goto L_0x0058
            com.google.android.gms.maps.model.LatLng r1 = r5.getOrigin()
            if (r1 != 0) goto L_0x00b2
        L_0x0057:
            goto L_0x0063
        L_0x0058:
            com.google.android.gms.maps.model.LatLng r3 = r5.getOrigin()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00b2
            goto L_0x0057
        L_0x0063:
            java.util.List<java.lang.String> r1 = r4.zze
            java.util.List r3 = r5.getCountries()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00b2
            com.google.android.libraries.places.api.model.AutocompleteSessionToken r1 = r4.zzf
            if (r1 != 0) goto L_0x007a
            com.google.android.libraries.places.api.model.AutocompleteSessionToken r1 = r5.getSessionToken()
            if (r1 != 0) goto L_0x00b2
        L_0x0079:
            goto L_0x0085
        L_0x007a:
            com.google.android.libraries.places.api.model.AutocompleteSessionToken r3 = r5.getSessionToken()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00b2
            goto L_0x0079
        L_0x0085:
            com.google.android.libraries.places.api.model.TypeFilter r1 = r4.zzg
            if (r1 != 0) goto L_0x0090
            com.google.android.libraries.places.api.model.TypeFilter r1 = r5.getTypeFilter()
            if (r1 != 0) goto L_0x00b2
        L_0x008f:
            goto L_0x009b
        L_0x0090:
            com.google.android.libraries.places.api.model.TypeFilter r3 = r5.getTypeFilter()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00b2
            goto L_0x008f
        L_0x009b:
            com.google.android.gms.tasks.CancellationToken r1 = r4.zzh
            if (r1 != 0) goto L_0x00a6
            com.google.android.gms.tasks.CancellationToken r5 = r5.getCancellationToken()
            if (r5 != 0) goto L_0x00b0
            goto L_0x00b1
        L_0x00a6:
            com.google.android.gms.tasks.CancellationToken r5 = r5.getCancellationToken()
            boolean r5 = r1.equals(r5)
            if (r5 != 0) goto L_0x00b1
        L_0x00b0:
            goto L_0x00b2
        L_0x00b1:
            return r0
        L_0x00b2:
            return r2
        L_0x00b3:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.api.net.zzk.equals(java.lang.Object):boolean");
    }

    public final CancellationToken getCancellationToken() {
        return this.zzh;
    }

    public final List<String> getCountries() {
        return this.zze;
    }

    public final LocationBias getLocationBias() {
        return this.zzb;
    }

    public final LocationRestriction getLocationRestriction() {
        return this.zzc;
    }

    public final LatLng getOrigin() {
        return this.zzd;
    }

    public final String getQuery() {
        return this.zza;
    }

    public final AutocompleteSessionToken getSessionToken() {
        return this.zzf;
    }

    public final TypeFilter getTypeFilter() {
        return this.zzg;
    }

    public final String toString() {
        String str = this.zza;
        String valueOf = String.valueOf(this.zzb);
        String valueOf2 = String.valueOf(this.zzc);
        String valueOf3 = String.valueOf(this.zzd);
        String valueOf4 = String.valueOf(this.zze);
        String valueOf5 = String.valueOf(this.zzf);
        String valueOf6 = String.valueOf(this.zzg);
        String valueOf7 = String.valueOf(this.zzh);
        int length = String.valueOf(str).length();
        int length2 = String.valueOf(valueOf).length();
        int length3 = String.valueOf(valueOf2).length();
        int length4 = String.valueOf(valueOf3).length();
        int length5 = String.valueOf(valueOf4).length();
        int length6 = String.valueOf(valueOf5).length();
        StringBuilder sb = new StringBuilder(length + 148 + length2 + length3 + length4 + length5 + length6 + String.valueOf(valueOf6).length() + String.valueOf(valueOf7).length());
        sb.append("FindAutocompletePredictionsRequest{query=");
        sb.append(str);
        sb.append(", locationBias=");
        sb.append(valueOf);
        sb.append(", locationRestriction=");
        sb.append(valueOf2);
        sb.append(", origin=");
        sb.append(valueOf3);
        sb.append(", countries=");
        sb.append(valueOf4);
        sb.append(", sessionToken=");
        sb.append(valueOf5);
        sb.append(", typeFilter=");
        sb.append(valueOf6);
        sb.append(", cancellationToken=");
        sb.append(valueOf7);
        sb.append("}");
        return sb.toString();
    }

    public final int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        String str = this.zza;
        int i7 = 0;
        if (str == null) {
            i = 0;
        } else {
            i = str.hashCode();
        }
        int i8 = (i ^ 1000003) * 1000003;
        LocationBias locationBias = this.zzb;
        if (locationBias == null) {
            i2 = 0;
        } else {
            i2 = locationBias.hashCode();
        }
        int i9 = (i8 ^ i2) * 1000003;
        LocationRestriction locationRestriction = this.zzc;
        if (locationRestriction == null) {
            i3 = 0;
        } else {
            i3 = locationRestriction.hashCode();
        }
        int i10 = (i9 ^ i3) * 1000003;
        LatLng latLng = this.zzd;
        if (latLng == null) {
            i4 = 0;
        } else {
            i4 = latLng.hashCode();
        }
        int hashCode = (((i10 ^ i4) * 1000003) ^ this.zze.hashCode()) * 1000003;
        AutocompleteSessionToken autocompleteSessionToken = this.zzf;
        if (autocompleteSessionToken == null) {
            i5 = 0;
        } else {
            i5 = autocompleteSessionToken.hashCode();
        }
        int i11 = (hashCode ^ i5) * 1000003;
        TypeFilter typeFilter = this.zzg;
        if (typeFilter == null) {
            i6 = 0;
        } else {
            i6 = typeFilter.hashCode();
        }
        int i12 = (i11 ^ i6) * 1000003;
        CancellationToken cancellationToken = this.zzh;
        if (cancellationToken != null) {
            i7 = cancellationToken.hashCode();
        }
        return i12 ^ i7;
    }
}
