package com.google.android.libraries.places.widget;

import android.view.View;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzd implements View.OnClickListener {
    public final /* synthetic */ AutocompleteFragment zza;

    public /* synthetic */ zzd(AutocompleteFragment autocompleteFragment) {
        this.zza = autocompleteFragment;
    }

    public final void onClick(View view) {
        this.zza.setText("");
    }
}
