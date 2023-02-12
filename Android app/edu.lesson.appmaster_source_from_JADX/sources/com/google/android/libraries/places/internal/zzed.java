package com.google.android.libraries.places.internal;

import com.google.android.gms.tasks.CancellationTokenSource;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzed extends zzei {
    private final CancellationTokenSource zza;
    private final String zzb;

    zzed(CancellationTokenSource cancellationTokenSource, String str) {
        this.zza = cancellationTokenSource;
        if (str != null) {
            this.zzb = str;
            return;
        }
        throw new NullPointerException("Null placeId");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzei) {
            zzei zzei = (zzei) obj;
            return this.zza.equals(zzei.zza()) && this.zzb.equals(zzei.zzb());
        }
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        String str = this.zzb;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 31 + str.length());
        sb.append("PlaceRequest{source=");
        sb.append(valueOf);
        sb.append(", placeId=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }

    public final CancellationTokenSource zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzb;
    }
}
