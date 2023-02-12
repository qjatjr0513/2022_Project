package com.google.android.libraries.places.widget.internal.p012ui;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.core.content.ContextCompat;
import com.google.android.libraries.places.internal.zzdh;

/* renamed from: com.google.android.libraries.places.widget.internal.ui.zzl */
/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzl implements View.OnFocusChangeListener {
    private zzl() {
    }

    /* synthetic */ zzl(zzk zzk) {
    }

    public final void onFocusChange(View view, boolean z) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) ContextCompat.getSystemService(view.getContext(), InputMethodManager.class);
            if (inputMethodManager != null) {
                if (z) {
                    inputMethodManager.showSoftInput(view, 1);
                } else {
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }
}
