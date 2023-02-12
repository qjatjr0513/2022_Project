package com.google.android.libraries.places.api.net;

import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zze extends FetchPlaceRequest.Builder {
    private String zza;
    private List<Place.Field> zzb;
    private AutocompleteSessionToken zzc;
    private CancellationToken zzd;

    zze() {
    }

    public final CancellationToken getCancellationToken() {
        return this.zzd;
    }

    public final AutocompleteSessionToken getSessionToken() {
        return this.zzc;
    }

    public final FetchPlaceRequest.Builder setCancellationToken(CancellationToken cancellationToken) {
        this.zzd = cancellationToken;
        return this;
    }

    public final FetchPlaceRequest.Builder setSessionToken(AutocompleteSessionToken autocompleteSessionToken) {
        this.zzc = autocompleteSessionToken;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final FetchPlaceRequest.Builder zza(List<Place.Field> list) {
        if (list != null) {
            this.zzb = list;
            return this;
        }
        throw new NullPointerException("Null placeFields");
    }

    /* access modifiers changed from: package-private */
    public final FetchPlaceRequest.Builder zzb(String str) {
        if (str != null) {
            this.zza = str;
            return this;
        }
        throw new NullPointerException("Null placeId");
    }

    /* access modifiers changed from: package-private */
    public final FetchPlaceRequest zzc() {
        List<Place.Field> list;
        String str = this.zza;
        if (str != null && (list = this.zzb) != null) {
            return new zzg(str, list, this.zzc, this.zzd, (zzf) null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" placeId");
        }
        if (this.zzb == null) {
            sb.append(" placeFields");
        }
        String valueOf = String.valueOf(sb);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 28);
        sb2.append("Missing required properties:");
        sb2.append(valueOf);
        throw new IllegalStateException(sb2.toString());
    }
}
