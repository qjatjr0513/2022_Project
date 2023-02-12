package com.google.android.libraries.places.widget.internal.p012ui;

import androidx.lifecycle.Observer;
import com.google.android.libraries.places.internal.zzdz;

/* renamed from: com.google.android.libraries.places.widget.internal.ui.zzd */
/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzd implements Observer {
    public final /* synthetic */ AutocompleteImplFragment zza;

    public /* synthetic */ zzd(AutocompleteImplFragment autocompleteImplFragment) {
        this.zza = autocompleteImplFragment;
    }

    public final void onChanged(Object obj) {
        this.zza.zzg((zzdz) obj);
    }
}
