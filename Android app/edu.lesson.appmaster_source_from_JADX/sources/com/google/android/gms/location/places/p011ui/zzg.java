package com.google.android.gms.location.places.p011ui;

import android.view.View;

/* renamed from: com.google.android.gms.location.places.ui.zzg */
final class zzg implements View.OnClickListener {
    private final /* synthetic */ SupportPlaceAutocompleteFragment zzdm;

    zzg(SupportPlaceAutocompleteFragment supportPlaceAutocompleteFragment) {
        this.zzdm = supportPlaceAutocompleteFragment;
    }

    public final void onClick(View view) {
        if (!this.zzdm.zzdh) {
            this.zzdm.zzn();
        }
    }
}
