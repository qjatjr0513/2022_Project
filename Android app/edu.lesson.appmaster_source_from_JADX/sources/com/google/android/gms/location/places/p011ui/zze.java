package com.google.android.gms.location.places.p011ui;

import android.view.View;

/* renamed from: com.google.android.gms.location.places.ui.zze */
final class zze implements View.OnClickListener {
    private final /* synthetic */ PlaceAutocompleteFragment zzdl;

    zze(PlaceAutocompleteFragment placeAutocompleteFragment) {
        this.zzdl = placeAutocompleteFragment;
    }

    public final void onClick(View view) {
        if (!this.zzdl.zzdh) {
            this.zzdl.zzn();
        }
    }
}
