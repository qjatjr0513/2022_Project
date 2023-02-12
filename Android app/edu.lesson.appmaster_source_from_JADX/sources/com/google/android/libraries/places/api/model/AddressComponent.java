package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.libraries.places.internal.zzfm;
import com.google.android.libraries.places.internal.zzge;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class AddressComponent implements Parcelable {

    /* compiled from: com.google.android.libraries.places:places@@2.5.0 */
    public static abstract class Builder {
        public AddressComponent build() {
            AddressComponent zzc = zzc();
            zzfm.zzi(!zzc.getName().isEmpty(), "Name must not be empty.");
            List<String> types = zzc.getTypes();
            for (String isEmpty : types) {
                zzfm.zzi(!TextUtils.isEmpty(isEmpty), "Types must not contain null or empty values.");
            }
            zzb(zzge.zzk(types));
            return zzc();
        }

        public abstract String getShortName();

        public abstract Builder setShortName(String str);

        /* access modifiers changed from: package-private */
        public abstract Builder zzb(List<String> list);

        /* access modifiers changed from: package-private */
        public abstract AddressComponent zzc();
    }

    public static Builder builder(String name, List<String> types) {
        zza zza = new zza();
        zza.zza(name);
        zza.zzb(types);
        return zza;
    }

    public abstract String getName();

    public abstract String getShortName();

    public abstract List<String> getTypes();
}
