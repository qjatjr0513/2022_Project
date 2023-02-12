package com.google.android.libraries.places.api.model;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
abstract class zzp extends PhotoMetadata {
    private final String zza;
    private final int zzb;
    private final int zzc;
    private final String zzd;

    zzp(String str, int i, int i2, String str2) {
        if (str != null) {
            this.zza = str;
            this.zzb = i;
            this.zzc = i2;
            if (str2 != null) {
                this.zzd = str2;
                return;
            }
            throw new NullPointerException("Null photoReference");
        }
        throw new NullPointerException("Null attributions");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PhotoMetadata) {
            PhotoMetadata photoMetadata = (PhotoMetadata) obj;
            return this.zza.equals(photoMetadata.getAttributions()) && this.zzb == photoMetadata.getHeight() && this.zzc == photoMetadata.getWidth() && this.zzd.equals(photoMetadata.zza());
        }
    }

    public String getAttributions() {
        return this.zza;
    }

    public int getHeight() {
        return this.zzb;
    }

    public int getWidth() {
        return this.zzc;
    }

    public final int hashCode() {
        return ((((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb) * 1000003) ^ this.zzc) * 1000003) ^ this.zzd.hashCode();
    }

    public final String toString() {
        String str = this.zza;
        int i = this.zzb;
        int i2 = this.zzc;
        String str2 = this.zzd;
        StringBuilder sb = new StringBuilder(str.length() + 84 + str2.length());
        sb.append("PhotoMetadata{attributions=");
        sb.append(str);
        sb.append(", height=");
        sb.append(i);
        sb.append(", width=");
        sb.append(i2);
        sb.append(", photoReference=");
        sb.append(str2);
        sb.append("}");
        return sb.toString();
    }

    public final String zza() {
        return this.zzd;
    }
}
