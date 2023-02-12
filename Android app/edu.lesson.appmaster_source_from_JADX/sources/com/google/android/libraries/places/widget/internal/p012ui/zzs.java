package com.google.android.libraries.places.widget.internal.p012ui;

import android.text.SpannableString;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.libraries.places.C2432R;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.internal.zzdh;

/* renamed from: com.google.android.libraries.places.widget.internal.ui.zzs */
/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzs extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView zza;
    private final TextView zzb;
    private AutocompletePrediction zzc;
    private boolean zzd;
    private final zze zze;

    public zzs(zze zze2, View view, byte[] bArr) {
        super(view);
        this.zze = zze2;
        this.zza = (TextView) view.findViewById(C2432R.C2435id.places_autocomplete_prediction_primary_text);
        this.zzb = (TextView) view.findViewById(C2432R.C2435id.places_autocomplete_prediction_secondary_text);
        this.itemView.setOnClickListener(this);
    }

    public final void onClick(View view) {
        try {
            zze zze2 = this.zze;
            zze2.zza.zze(this.zzc, getAdapterPosition());
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    public final void zza(AutocompletePrediction autocompletePrediction, boolean z) {
        this.zzc = autocompletePrediction;
        this.zzd = z;
        this.zza.setText(autocompletePrediction.getPrimaryText(new ForegroundColorSpan(ContextCompat.getColor(this.itemView.getContext(), C2432R.C2433color.places_autocomplete_prediction_primary_text_highlight))));
        SpannableString secondaryText = autocompletePrediction.getSecondaryText((CharacterStyle) null);
        this.zzb.setText(secondaryText);
        if (secondaryText.length() == 0) {
            this.zzb.setVisibility(8);
            this.zza.setGravity(16);
            return;
        }
        this.zzb.setVisibility(0);
        this.zza.setGravity(80);
    }

    public final boolean zzb() {
        return this.zzd;
    }
}
