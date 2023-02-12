package com.google.android.libraries.places.api.model;

import com.google.android.libraries.places.api.model.PhotoMetadata;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzo extends PhotoMetadata.Builder {
    private String zza;
    private Integer zzb;
    private Integer zzc;
    private String zzd;

    zzo() {
    }

    public final String getAttributions() {
        String str = this.zza;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("Property \"attributions\" has not been set");
    }

    public final int getHeight() {
        Integer num = this.zzb;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("Property \"height\" has not been set");
    }

    public final int getWidth() {
        Integer num = this.zzc;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("Property \"width\" has not been set");
    }

    public final PhotoMetadata.Builder setAttributions(String str) {
        if (str != null) {
            this.zza = str;
            return this;
        }
        throw new NullPointerException("Null attributions");
    }

    public final PhotoMetadata.Builder setHeight(int i) {
        this.zzb = Integer.valueOf(i);
        return this;
    }

    public final PhotoMetadata.Builder setWidth(int i) {
        this.zzc = Integer.valueOf(i);
        return this;
    }

    /* access modifiers changed from: package-private */
    public final PhotoMetadata.Builder zza(String str) {
        if (str != null) {
            this.zzd = str;
            return this;
        }
        throw new NullPointerException("Null photoReference");
    }

    /* access modifiers changed from: package-private */
    public final PhotoMetadata zzb() {
        Integer num;
        String str = this.zza;
        if (str != null && (num = this.zzb) != null && this.zzc != null && this.zzd != null) {
            return new zzap(str, num.intValue(), this.zzc.intValue(), this.zzd);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" attributions");
        }
        if (this.zzb == null) {
            sb.append(" height");
        }
        if (this.zzc == null) {
            sb.append(" width");
        }
        if (this.zzd == null) {
            sb.append(" photoReference");
        }
        String valueOf = String.valueOf(sb);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 28);
        sb2.append("Missing required properties:");
        sb2.append(valueOf);
        throw new IllegalStateException(sb2.toString());
    }
}
