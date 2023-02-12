package com.google.android.libraries.places.api.model;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzi extends zzbd {
    private Integer zza;
    private Integer zzb;

    zzi() {
    }

    /* access modifiers changed from: package-private */
    public final zzbd zza(int i) {
        this.zza = Integer.valueOf(i);
        return this;
    }

    /* access modifiers changed from: package-private */
    public final zzbd zzb(int i) {
        this.zzb = Integer.valueOf(i);
        return this;
    }

    /* access modifiers changed from: package-private */
    public final LocalTime zzc() {
        Integer num = this.zza;
        if (num != null && this.zzb != null) {
            return new zzaj(num.intValue(), this.zzb.intValue());
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" hours");
        }
        if (this.zzb == null) {
            sb.append(" minutes");
        }
        String valueOf = String.valueOf(sb);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 28);
        sb2.append("Missing required properties:");
        sb2.append(valueOf);
        throw new IllegalStateException(sb2.toString());
    }
}
