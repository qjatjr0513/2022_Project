package com.google.android.libraries.places.widget.internal.p012ui;

import androidx.recyclerview.widget.RecyclerView;
import com.google.android.libraries.places.internal.zzdh;

/* renamed from: com.google.android.libraries.places.widget.internal.ui.zzg */
/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzg extends RecyclerView.OnScrollListener {
    final /* synthetic */ AutocompleteImplFragment zza;

    zzg(AutocompleteImplFragment autocompleteImplFragment) {
        this.zza = autocompleteImplFragment;
    }

    public final void onScrollStateChanged(RecyclerView recyclerView, int i) {
        if (i == 1) {
            try {
                this.zza.zze.zzg();
                this.zza.zzg.clearFocus();
            } catch (Error | RuntimeException e) {
                zzdh.zzb(e);
                throw e;
            }
        }
    }
}
