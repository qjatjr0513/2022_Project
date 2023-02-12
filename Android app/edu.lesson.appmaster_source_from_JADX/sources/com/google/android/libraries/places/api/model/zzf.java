package com.google.android.libraries.places.api.model;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzf extends zzba {
    private Integer zza;
    private Integer zzb;

    zzf() {
    }

    public final zzba zza(int i) {
        this.zzb = Integer.valueOf(i);
        return this;
    }

    public final zzba zzb(int i) {
        this.zza = Integer.valueOf(i);
        return this;
    }

    public final zzbb zzc() {
        Integer num = this.zza;
        if (num != null && this.zzb != null) {
            return new zzaf(num.intValue(), this.zzb.intValue());
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" offset");
        }
        if (this.zzb == null) {
            sb.append(" length");
        }
        String valueOf = String.valueOf(sb);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 28);
        sb2.append("Missing required properties:");
        sb2.append(valueOf);
        throw new IllegalStateException(sb2.toString());
    }
}
