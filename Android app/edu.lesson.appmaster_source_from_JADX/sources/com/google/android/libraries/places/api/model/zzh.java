package com.google.android.libraries.places.api.model;

import android.os.ParcelUuid;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
abstract class zzh extends AutocompleteSessionToken {
    private final ParcelUuid zza;

    zzh(ParcelUuid parcelUuid) {
        if (parcelUuid != null) {
            this.zza = parcelUuid;
            return;
        }
        throw new NullPointerException("Null UUID");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AutocompleteSessionToken) {
            return this.zza.equals(((AutocompleteSessionToken) obj).zza());
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode() ^ 1000003;
    }

    /* access modifiers changed from: package-private */
    public final ParcelUuid zza() {
        return this.zza;
    }
}
