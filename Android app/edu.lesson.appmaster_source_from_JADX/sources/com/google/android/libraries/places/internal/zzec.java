package com.google.android.libraries.places.internal;

import com.google.android.gms.tasks.CancellationTokenSource;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzec extends zzeh {
    private final CancellationTokenSource zza;
    private final String zzb;

    zzec(CancellationTokenSource cancellationTokenSource, String str) {
        this.zza = cancellationTokenSource;
        if (str != null) {
            this.zzb = str;
            return;
        }
        throw new NullPointerException("Null query");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzeh) {
            zzeh zzeh = (zzeh) obj;
            return this.zza.equals(zzeh.zza()) && this.zzb.equals(zzeh.zzb());
        }
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        String str = this.zzb;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 36 + str.length());
        sb.append("AutocompleteRequest{source=");
        sb.append(valueOf);
        sb.append(", query=");
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
