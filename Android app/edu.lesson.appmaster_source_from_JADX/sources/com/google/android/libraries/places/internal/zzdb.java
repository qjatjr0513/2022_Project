package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzdb extends zzde {
    private String zza;
    private Integer zzb;
    private int zzc;

    zzdb() {
    }

    /* access modifiers changed from: package-private */
    public final zzde zza(String str) {
        if (str != null) {
            this.zza = str;
            return this;
        }
        throw new NullPointerException("Null packageName");
    }

    /* access modifiers changed from: package-private */
    public final zzde zzb(int i) {
        this.zzb = Integer.valueOf(i);
        return this;
    }

    /* access modifiers changed from: package-private */
    public final zzdf zzc() {
        Integer num;
        String str = this.zza;
        if (str != null && (num = this.zzb) != null && this.zzc != 0) {
            return new zzdd(str, num.intValue(), this.zzc, (zzdc) null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" packageName");
        }
        if (this.zzb == null) {
            sb.append(" versionCode");
        }
        if (this.zzc == 0) {
            sb.append(" requestSource");
        }
        String valueOf = String.valueOf(sb);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 28);
        sb2.append("Missing required properties:");
        sb2.append(valueOf);
        throw new IllegalStateException(sb2.toString());
    }

    public final zzde zzd(int i) {
        this.zzc = i;
        return this;
    }
}
