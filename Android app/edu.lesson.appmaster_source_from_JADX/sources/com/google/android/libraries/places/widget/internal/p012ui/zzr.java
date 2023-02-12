package com.google.android.libraries.places.widget.internal.p012ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.ListAdapter;
import com.google.android.libraries.places.C2432R;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.internal.zzdh;
import java.util.List;

/* renamed from: com.google.android.libraries.places.widget.internal.ui.zzr */
/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzr extends ListAdapter<AutocompletePrediction, zzs> {
    private int zza;
    private boolean zzb = true;
    private final zze zzc;

    public zzr(zze zze, byte[] bArr) {
        super(new zzq((zzp) null));
        this.zzc = zze;
    }

    public final void submitList(List<AutocompletePrediction> list) {
        boolean z;
        try {
            int i = 0;
            if (this.zza != 0) {
                z = false;
            } else if (list == null || list.isEmpty()) {
                z = false;
            } else {
                z = true;
            }
            this.zzb = z;
            if (list != null) {
                i = list.size();
            }
            this.zza = i;
            super.submitList(list);
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    /* renamed from: zza */
    public final zzs onCreateViewHolder(ViewGroup viewGroup, int i) {
        try {
            return new zzs(this.zzc, LayoutInflater.from(viewGroup.getContext()).inflate(C2432R.layout.places_autocomplete_prediction, viewGroup, false), (byte[]) null);
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }

    /* renamed from: zzb */
    public final void onBindViewHolder(zzs zzs, int i) {
        try {
            zzs.zza((AutocompletePrediction) getItem(i), this.zzb);
        } catch (Error | RuntimeException e) {
            zzdh.zzb(e);
            throw e;
        }
    }
}
