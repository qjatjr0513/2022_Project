package com.google.android.gms.location.places;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.location.places.internal.zzak;
import com.google.android.gms.location.places.internal.zzam;
import java.util.Comparator;

@Deprecated
public class PlaceLikelihoodBuffer extends AbstractDataBuffer<PlaceLikelihood> implements Result {
    private static final Comparator<zzak> zzac = new zzj();
    private final int zzad;
    private final boolean zzae;
    private final Status zzp;
    private final String zzq;

    public static int zzb(Bundle bundle) {
        return bundle.getInt("com.google.android.gms.location.places.PlaceLikelihoodBuffer.SOURCE_EXTRA_KEY");
    }

    public PlaceLikelihoodBuffer(DataHolder dataHolder, int i) {
        this(dataHolder, false, i);
    }

    private PlaceLikelihoodBuffer(DataHolder dataHolder, boolean z, int i) {
        super(dataHolder);
        this.zzp = PlacesStatusCodes.zzb(dataHolder.getStatusCode());
        switch (i) {
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
                this.zzad = i;
                this.zzae = false;
                if (dataHolder == null || dataHolder.getMetadata() == null) {
                    this.zzq = null;
                    return;
                } else {
                    this.zzq = dataHolder.getMetadata().getString("com.google.android.gms.location.places.PlaceLikelihoodBuffer.ATTRIBUTIONS_EXTRA_KEY");
                    return;
                }
            default:
                throw new IllegalArgumentException(new StringBuilder(27).append("invalid source: ").append(i).toString());
        }
    }

    public PlaceLikelihood get(int i) {
        return new zzam(this.mDataHolder, i);
    }

    public CharSequence getAttributions() {
        return this.zzq;
    }

    public Status getStatus() {
        return this.zzp;
    }

    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, getStatus()).add("attributions", this.zzq).toString();
    }
}
