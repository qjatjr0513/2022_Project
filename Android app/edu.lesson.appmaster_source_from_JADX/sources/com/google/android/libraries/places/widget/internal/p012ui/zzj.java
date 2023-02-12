package com.google.android.libraries.places.widget.internal.p012ui;

import android.text.Editable;
import android.text.TextWatcher;
import com.google.android.libraries.places.internal.zzdh;

/* renamed from: com.google.android.libraries.places.widget.internal.ui.zzj */
/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzj implements TextWatcher {
    final /* synthetic */ AutocompleteImplFragment zza;

    /* synthetic */ zzj(AutocompleteImplFragment autocompleteImplFragment, zzi zzi) {
        this.zza = autocompleteImplFragment;
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
        try {
            this.zza.zze.zzm(editable.toString());
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }
}
