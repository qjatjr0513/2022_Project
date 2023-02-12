package com.google.android.libraries.places.api.net;

import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzg extends FetchPlaceRequest {
    private final String zza;
    private final List<Place.Field> zzb;
    private final AutocompleteSessionToken zzc;
    private final CancellationToken zzd;

    /* synthetic */ zzg(String str, List list, AutocompleteSessionToken autocompleteSessionToken, CancellationToken cancellationToken, zzf zzf) {
        this.zza = str;
        this.zzb = list;
        this.zzc = autocompleteSessionToken;
        this.zzd = cancellationToken;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0039, code lost:
        r1 = r4.zzd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0023, code lost:
        r1 = r4.zzc;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.android.libraries.places.api.net.FetchPlaceRequest
            r2 = 0
            if (r1 == 0) goto L_0x0051
            com.google.android.libraries.places.api.net.FetchPlaceRequest r5 = (com.google.android.libraries.places.api.net.FetchPlaceRequest) r5
            java.lang.String r1 = r4.zza
            java.lang.String r3 = r5.getPlaceId()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0050
            java.util.List<com.google.android.libraries.places.api.model.Place$Field> r1 = r4.zzb
            java.util.List r3 = r5.getPlaceFields()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0050
            com.google.android.libraries.places.api.model.AutocompleteSessionToken r1 = r4.zzc
            if (r1 != 0) goto L_0x002e
            com.google.android.libraries.places.api.model.AutocompleteSessionToken r1 = r5.getSessionToken()
            if (r1 != 0) goto L_0x0050
        L_0x002d:
            goto L_0x0039
        L_0x002e:
            com.google.android.libraries.places.api.model.AutocompleteSessionToken r3 = r5.getSessionToken()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0050
            goto L_0x002d
        L_0x0039:
            com.google.android.gms.tasks.CancellationToken r1 = r4.zzd
            if (r1 != 0) goto L_0x0044
            com.google.android.gms.tasks.CancellationToken r5 = r5.getCancellationToken()
            if (r5 != 0) goto L_0x004e
            goto L_0x004f
        L_0x0044:
            com.google.android.gms.tasks.CancellationToken r5 = r5.getCancellationToken()
            boolean r5 = r1.equals(r5)
            if (r5 != 0) goto L_0x004f
        L_0x004e:
            goto L_0x0050
        L_0x004f:
            return r0
        L_0x0050:
            return r2
        L_0x0051:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.api.net.zzg.equals(java.lang.Object):boolean");
    }

    public final CancellationToken getCancellationToken() {
        return this.zzd;
    }

    public final List<Place.Field> getPlaceFields() {
        return this.zzb;
    }

    public final String getPlaceId() {
        return this.zza;
    }

    public final AutocompleteSessionToken getSessionToken() {
        return this.zzc;
    }

    public final int hashCode() {
        int i;
        int hashCode = (((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003;
        AutocompleteSessionToken autocompleteSessionToken = this.zzc;
        int i2 = 0;
        if (autocompleteSessionToken == null) {
            i = 0;
        } else {
            i = autocompleteSessionToken.hashCode();
        }
        int i3 = (hashCode ^ i) * 1000003;
        CancellationToken cancellationToken = this.zzd;
        if (cancellationToken != null) {
            i2 = cancellationToken.hashCode();
        }
        return i3 ^ i2;
    }

    public final String toString() {
        String str = this.zza;
        String valueOf = String.valueOf(this.zzb);
        String valueOf2 = String.valueOf(this.zzc);
        String valueOf3 = String.valueOf(this.zzd);
        int length = str.length();
        int length2 = String.valueOf(valueOf).length();
        StringBuilder sb = new StringBuilder(length + 76 + length2 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append("FetchPlaceRequest{placeId=");
        sb.append(str);
        sb.append(", placeFields=");
        sb.append(valueOf);
        sb.append(", sessionToken=");
        sb.append(valueOf2);
        sb.append(", cancellationToken=");
        sb.append(valueOf3);
        sb.append("}");
        return sb.toString();
    }
}
