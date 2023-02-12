package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.libraries.places.internal.zzfm;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class PhotoMetadata implements Parcelable {

    /* compiled from: com.google.android.libraries.places:places@@2.5.0 */
    public static abstract class Builder {
        public PhotoMetadata build() {
            boolean z;
            PhotoMetadata zzb = zzb();
            int width = zzb.getWidth();
            boolean z2 = false;
            if (width >= 0) {
                z = true;
            } else {
                z = false;
            }
            zzfm.zzj(z, "Width must not be < 0, but was: %s.", width);
            int height = zzb.getHeight();
            if (height >= 0) {
                z2 = true;
            }
            zzfm.zzj(z2, "Height must not be < 0, but was: %s.", height);
            zzfm.zzi(!TextUtils.isEmpty(zzb.zza()), "PhotoReference must not be null or empty.");
            return zzb;
        }

        public abstract String getAttributions();

        public abstract int getHeight();

        public abstract int getWidth();

        public abstract Builder setAttributions(String str);

        public abstract Builder setHeight(int i);

        public abstract Builder setWidth(int i);

        /* access modifiers changed from: package-private */
        public abstract PhotoMetadata zzb();
    }

    public static Builder builder(String photoReference) {
        zzo zzo = new zzo();
        zzo.zza(photoReference);
        zzo.setWidth(0);
        zzo.setHeight(0);
        zzo.setAttributions("");
        return zzo;
    }

    public abstract String getAttributions();

    public abstract int getHeight();

    public abstract int getWidth();

    public abstract String zza();
}
