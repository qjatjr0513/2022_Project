package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;

public final class zzam extends zzaw implements PlaceLikelihood {
    public zzam(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    public final float getLikelihood() {
        return zzb("place_likelihood", -1.0f);
    }

    public final Place getPlace() {
        return new zzar(this.mDataHolder, this.mDataRow);
    }

    public final /* synthetic */ Object freeze() {
        return new zzak((PlaceEntity) Preconditions.checkNotNull((PlaceEntity) getPlace().freeze()), getLikelihood());
    }
}
