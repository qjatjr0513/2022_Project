package com.google.android.libraries.places.widget;

import android.view.View;
import android.widget.EditText;
import androidx.lifecycle.Observer;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final /* synthetic */ class zzh implements Observer {
    public final /* synthetic */ EditText zza;
    public final /* synthetic */ View zzb;

    public /* synthetic */ zzh(EditText editText, View view) {
        this.zza = editText;
        this.zzb = view;
    }

    public final void onChanged(Object obj) {
        AutocompleteSupportFragment.zzd(this.zza, this.zzb, (CharSequence) obj);
    }
}
