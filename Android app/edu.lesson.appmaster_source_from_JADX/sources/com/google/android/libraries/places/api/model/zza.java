package com.google.android.libraries.places.api.model;

import com.google.android.libraries.places.api.model.AddressComponent;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zza extends AddressComponent.Builder {
    private String zza;
    private String zzb;
    private List<String> zzc;

    zza() {
    }

    public final String getShortName() {
        return this.zzb;
    }

    public final AddressComponent.Builder setShortName(String str) {
        this.zzb = str;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final AddressComponent.Builder zza(String str) {
        if (str != null) {
            this.zza = str;
            return this;
        }
        throw new NullPointerException("Null name");
    }

    /* access modifiers changed from: package-private */
    public final AddressComponent.Builder zzb(List<String> list) {
        if (list != null) {
            this.zzc = list;
            return this;
        }
        throw new NullPointerException("Null types");
    }

    /* access modifiers changed from: package-private */
    public final AddressComponent zzc() {
        List<String> list;
        String str = this.zza;
        if (str != null && (list = this.zzc) != null) {
            return new zzz(str, this.zzb, list);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" name");
        }
        if (this.zzc == null) {
            sb.append(" types");
        }
        String valueOf = String.valueOf(sb);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 28);
        sb2.append("Missing required properties:");
        sb2.append(valueOf);
        throw new IllegalStateException(sb2.toString());
    }
}
