package com.google.android.libraries.places.widget.internal.p012ui;

import androidx.activity.OnBackPressedCallback;

/* renamed from: com.google.android.libraries.places.widget.internal.ui.zzf */
/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzf extends OnBackPressedCallback {
    final /* synthetic */ AutocompleteImplFragment zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzf(AutocompleteImplFragment autocompleteImplFragment, boolean z) {
        super(true);
        this.zza = autocompleteImplFragment;
    }

    public final void handleOnBackPressed() {
        this.zza.zze.zzj();
    }
}
