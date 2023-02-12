package com.google.android.libraries.places.api.net;

import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.Place;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzo extends FindCurrentPlaceRequest {
    private final List<Place.Field> zza;
    private final CancellationToken zzb;

    /* synthetic */ zzo(List list, CancellationToken cancellationToken, zzn zzn) {
        this.zza = list;
        this.zzb = cancellationToken;
    }

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
            boolean r1 = r5 instanceof com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
            r2 = 0
            if (r1 == 0) goto L_0x002e
            com.google.android.libraries.places.api.net.FindCurrentPlaceRequest r5 = (com.google.android.libraries.places.api.net.FindCurrentPlaceRequest) r5
            java.util.List<com.google.android.libraries.places.api.model.Place$Field> r1 = r4.zza
            java.util.List r3 = r5.getPlaceFields()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x002e
            com.google.android.gms.tasks.CancellationToken r1 = r4.zzb
            if (r1 != 0) goto L_0x0022
            com.google.android.gms.tasks.CancellationToken r5 = r5.getCancellationToken()
            if (r5 != 0) goto L_0x002c
            goto L_0x002d
        L_0x0022:
            com.google.android.gms.tasks.CancellationToken r5 = r5.getCancellationToken()
            boolean r5 = r1.equals(r5)
            if (r5 != 0) goto L_0x002d
        L_0x002c:
            goto L_0x002e
        L_0x002d:
            return r0
        L_0x002e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.api.net.zzo.equals(java.lang.Object):boolean");
    }

    public final CancellationToken getCancellationToken() {
        return this.zzb;
    }

    public final List<Place.Field> getPlaceFields() {
        return this.zza;
    }

    public final int hashCode() {
        int i;
        int hashCode = (this.zza.hashCode() ^ 1000003) * 1000003;
        CancellationToken cancellationToken = this.zzb;
        if (cancellationToken == null) {
            i = 0;
        } else {
            i = cancellationToken.hashCode();
        }
        return hashCode ^ i;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        String valueOf2 = String.valueOf(this.zzb);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 57 + String.valueOf(valueOf2).length());
        sb.append("FindCurrentPlaceRequest{placeFields=");
        sb.append(valueOf);
        sb.append(", cancellationToken=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }
}
