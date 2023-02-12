package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;

public final class zzap implements PlacePhotoMetadata {
    private final int index;
    private final int maxHeight;
    private final int maxWidth;
    private final String zzcu;
    private final CharSequence zzcv;

    public zzap(String str, int i, int i2, CharSequence charSequence, int i3) {
        this.zzcu = str;
        this.maxWidth = i;
        this.maxHeight = i2;
        this.zzcv = charSequence;
        this.index = i3;
    }

    public final String zzk() {
        return this.zzcu;
    }

    public final int getIndex() {
        return this.index;
    }

    public final int getMaxWidth() {
        return this.maxWidth;
    }

    public final int getMaxHeight() {
        return this.maxHeight;
    }

    public final CharSequence getAttributions() {
        return this.zzcv;
    }

    public final PendingResult<PlacePhotoResult> getPhoto(GoogleApiClient googleApiClient) {
        return getScaledPhoto(googleApiClient, getMaxWidth(), getMaxHeight());
    }

    public final PendingResult<PlacePhotoResult> getScaledPhoto(GoogleApiClient googleApiClient, int i, int i2) {
        return ((zzh) Places.GeoDataApi).zzb(googleApiClient, this, i, i2);
    }

    public final boolean isDataValid() {
        return true;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzap)) {
            return false;
        }
        zzap zzap = (zzap) obj;
        if (zzap.maxWidth != this.maxWidth || zzap.maxHeight != this.maxHeight || !Objects.equal(zzap.zzcu, this.zzcu) || !Objects.equal(zzap.zzcv, this.zzcv)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.maxWidth), Integer.valueOf(this.maxHeight), this.zzcu, this.zzcv);
    }

    public final /* bridge */ /* synthetic */ Object freeze() {
        return this;
    }
}
